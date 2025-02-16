package Version15.Robots;

import Version15.Misc.Communication;
import Version15.Micro.Micro;
import Version15.Pathfinding.Pathfinding;
import Version15.Misc.Ruin;
import Version15.Utility.*;
import battlecode.common.*;
import battlecode.schema.RobotType;

import static Version15.Misc.Communication.unclaimedRuins;
import static Version15.RobotPlayer.rng;
import static Version15.Misc.Communication.alliedPaintTowers;
import static Version15.RobotPlayer.*;
import static Version15.Utility.SoldierUtil.needsHelp;
import static Version15.Utility.Symmetry.SymmetryType.*;
import static Version15.Utility.Symmetry.*;
//import static Version15.Utility.SoldierUtil.validateRuinClaim;

public class Soldier {
    enum SoldierState {
        Explore,        //We have nowhere to go to, let's explore
        Navigate,       //We have a target in mind, let's pursue it - if we end up with no target, explore
        Refill,         //We need paint, let's go back to our own tower
        Tower,          //We are near a tower, let's use micro to efficiently fight them
        RuinBuilding,   //We are near a ruin and we want to paint around it
        Fill
    }

    public static SoldierState state = SoldierState.Explore;
    public static MapLocation target;
    public static MapLocation closestUnclaimedRuin; //keeps track of the closest unclaimed ruin we know of
    public static MapLocation closestUnfilledPatternCenter;
    public static MapLocation closestEnemyTower;
    public static MapLocation fillingStation;
    //public static MapLocation claimedRuin = null;
    public static int numEnemyTiles;
    public static MapInfo[] tilesNearRuin;
    public static RobotInfo seenEnemyTower;
    public static boolean wallHug;
    public static boolean clockwise;
    public static MapLocation spawnLocation;
    //public static boolean[][] invalidResourceCenters;
    public static FastIterableLocSet invalidResourceCenters;
    //public static BitBoard checkedRuin;
    public static FastIterableLocSet checkedRuin;

    public static boolean[][] curPattern = null;

    public static boolean checkedSymmetry = false;
    public static MapLocation oppositeHome = null;

    //public static BitBoard enemyDefenseTowers;
    public static FastIterableLocSet enemyDefenseTowers;

    public static boolean waitingToFinishRuin;

    public static MapLocation workingOnRuin;

    public static MapLocation closestUnseenRuin;

    public static MapLocation averageEnemyPaint;

    public static FastIterableLocSet checkedPattern;




    public static final int ENEMY_TOWER_REFRESH = 3;
    //Constants
    public final static int refillThreshold = 35;    //Paint level at which soldiers go to refill
    public final static int doneRefillingThreshold = 150;    //Paint level at which soldiers can stop refilling
    //final static int REFRESH_ENEMY_PAINT_AVERAGES = 5;
    public static int EXPLORE_FILL_TOWER_THRESHOLD; //determines at what round we will fill indiscriminately while exploring
    public static int TURN_TO_NAVIGATE_TO_TOWERS; //indicates at what turn we will prioritize going towards enemy towers
    public static int STOP_EXPLORING; //indicates when soldiers will began defaulting to navigate instead of explore
    public final static int VALIDATE_RUIN_CLAIM_FREQUENCY = 20; //records how often we will checked if our ruin claim has been thwarted by enemies
    public static int TURN_TO_FILL; //turn at which filling becomes allowed
    public static int INCENTIVIZE_MONEY_ROUND; //turn at which any time before that soldiers will give a slight weight to building money towers
    public static int FORCE_MONEY_ROUND; //turn at which any time before that soldiers will always build money towers
    public static int START_RUSHING_TOWER_NUMBER; //indicate at which point we should start rushing because we just have too many towers and therefore prob too many robots

    public static void runSoldier() throws GameActionException
    {
        if(turnCount == 1){
            clockwise = rng.nextInt(2) == 0;
            initializeMapDependentVariables();
            spawnLocation = rc.getLocation();
            invalidResourceCenters = new FastIterableLocSet();
            checkedRuin = new FastIterableLocSet();
            enemyDefenseTowers = new FastIterableLocSet();
            checkedPattern = new FastIterableLocSet();
        }
        //attemptCompleteTowerPattern();
        updateInfo();
        //update the state
        updateState();
        //run the state
        if(rc.isMovementReady() || rc.isActionReady()) {
            switch (state) {
                case Explore -> {
                    rc.setIndicatorString("Exploring");
                    explore();
                }
                case Navigate -> {
                    rc.setIndicatorString("Navigating");
                    navigate();
                }
                case Refill -> {
                    rc.setIndicatorString("Refilling");
                    refill();
                }
                case Tower -> {
                    rc.setIndicatorString("Attacking Tower");
                    tower();
                }
                case RuinBuilding -> {
                    rc.setIndicatorString("Building a Ruin");
                    ruinBuilding();
                }
                case Fill -> {
                    rc.setIndicatorString("Filling a pattern");
                    fill();
                }
            }
        }
        if(closestUnclaimedRuin != null) {
            attemptCompleteTowerPattern(closestUnclaimedRuin);
        }
        //lets still fill in some around it to prevent the enemy from getting it
        if(rc.isActionReady() && closestUnclaimedRuin != null && checkedRuin.contains(closestUnclaimedRuin)) {
            attemptFillAroundCenter(closestUnclaimedRuin);
        }
        if(knownSymmetry != SymmetryType.Unknown) {
            if(Clock.getBytecodesLeft() > 4200)
                SoldierUtil.scanNearbyTilesSoldier();
        }
        else if(Clock.getBytecodesLeft() > 5500) {
            SoldierUtil.scanNearbyTilesSoldier();
        }

    }

    private static void attemptFillAroundCenter(MapLocation center) throws GameActionException {
        MapInfo[] tilesToFill = rc.senseNearbyMapInfos(center, 8);
        int minDist = Integer.MAX_VALUE;
        boolean hasRobot= false;
        MapLocation bestLoc = null;
        boolean[][] desiredPattern = Utilities.getPatternFromWeightedHash(center);
        for(MapInfo m : tilesToFill) {
            if(m.getPaint() == PaintType.EMPTY) {
                MapLocation tileLoc = m.getMapLocation();
                if(tileLoc.equals(rc.getLocation()) && rc.canAttack(rc.getLocation())) {
                    rc.attack(rc.getLocation());
                    return;
                }
                int dist = (averageEnemyPaint != null) ? tileLoc.distanceSquaredTo(averageEnemyPaint) : tileLoc.distanceSquaredTo(center);
                boolean curRobot = rc.canSenseRobotAtLocation(tileLoc);
                if(!hasRobot && dist < minDist) {
                    hasRobot = curRobot;
                    minDist = dist;
                    bestLoc = tileLoc;
                }
                else if(hasRobot && curRobot && dist < minDist) {
                    minDist = dist;
                    bestLoc = tileLoc;
                    if(minDist <= 4 && rc.canAttack(bestLoc)) {
                        rc.attack(bestLoc, Utilities.getColorFromCustomPattern(bestLoc, desiredPattern, center));
                        return;
                    }
                }
            }
        }
        if(bestLoc != null && rc.canAttack(bestLoc)) {
            rc.attack(bestLoc, Utilities.getColorFromCustomPattern(bestLoc, desiredPattern, center));
        }
    }

    public static void initializeMapDependentVariables() throws GameActionException {
        int mapSize = rc.getMapHeight() * rc.getMapWidth();
        //y = 0.016x + 93.6 - > calibrated to 100 at smallest and 150 at largest
        TURN_TO_NAVIGATE_TO_TOWERS = (int)(0.016 * mapSize + 93.6); //indicates at what turn we will prioritize going towards enemy towers
        //y = y = 0.027x + 24.2 -> calibrated to 35 on smallest and 120 on largest
        STOP_EXPLORING = (int) (mapSize * 0.027 + 24.2); //indicates when soldiers will began defaulting to navigate instead of explore
        TURN_TO_FILL = (int)((4000 - mapSize) / 25); //turn at which filling becomes allowed
        INCENTIVIZE_MONEY_ROUND = 80; //turn at which any time before that soldiers will give a slight weight to building money towers
        //y = 0.01x + 34 -> calibrates it to 38 at min size and 70 at max size
        FORCE_MONEY_ROUND = (int)(0.01 * mapSize + 34);//turn at which any time before that soldiers will always build money towers
        //y = y = y = 0.003x + 4.8-> calibrates it to be 6 on the smallest map size and 15 on the largest map size
        START_RUSHING_TOWER_NUMBER = (int) ((0.003 * mapSize) + 4.8);
        //y = 0.004x + 5.4 -> calibrates to 8 on smallest map and 20 on largest map
        EXPLORE_FILL_TOWER_THRESHOLD = (int) ((0.004 * mapSize) + 6.4);
        //EXPLORE_FILL_TOWER_THRESHOLD = 0;

    }


    static void updateInfo() throws GameActionException
    {
        //reset turn by turn variables
        //closestCustomPattern = null; //tracks the closest center of a custom pattern, so that it doesn't get overridden by attemptFill
//        if(claimedRuin != null && rc.getRoundNum() % VALIDATE_RUIN_CLAIM_FREQUENCY == 0) {
//            validateRuinClaim();
//        }
//        if(turnCount == 1 || turnCount % ENEMY_TOWER_REFRESH == 0) {
//            closestEnemyTower = closestEnemyTower();
//        }
        if(seenEnemyTower != null && isDefenseTower(seenEnemyTower)) {
            //enemyDefenseTowers.setBit(seenEnemyTower.getLocation(), true);
            enemyDefenseTowers.add(seenEnemyTower.getLocation());
        }
        closestEnemyTower = closestEnemyTower();
        if(seenEnemyTower != null && closestEnemyTower != null && !seenEnemyTower.getLocation().equals(closestEnemyTower) && rc.canSenseRobotAtLocation(closestEnemyTower)) {
            seenEnemyTower = rc.senseRobotAtLocation(closestEnemyTower);
        }
        closestUnclaimedRuin = closestUnclaimedRuin();
        closestUnseenRuin = closestUnseenRuin();
        if(turnCount == 1)  SoldierUtil.scanNearbyTilesSoldier();

        if(workingOnRuin != null && rc.canSenseRobotAtLocation(workingOnRuin)) {
            workingOnRuin = null;
        }

        if(turnCount % 10 == 0) {
            checkedRuin.clear();
        }
        if(turnCount % 40 == 0) {
            checkedPattern.clear();
        }
    }

    public static boolean isDefenseTower(RobotInfo r) {
        UnitType type = r.getType();
        return type == UnitType.LEVEL_ONE_DEFENSE_TOWER || type == UnitType.LEVEL_TWO_DEFENSE_TOWER || type == UnitType.LEVEL_THREE_DEFENSE_TOWER;
    }



    //sets the state for the soldier for this round
    //priorities:
    //1. if we are low on paint, try and refill
    //2. if we see an unclaimed ruin with spots to fill, try and fill around it
    //3. if we see an enemy tower, lets go into micro mode
    //4. if we see a mark indicating an attempt at filling in a resource pattern, lets try and fill it
    //5. if we have a target in mind, navigate there
    //6. just explore
    public static void updateState() throws GameActionException
    {
        //check if we need to refill on paint
        if((rc.getPaint() <= refillThreshold || (rc.getPaint() <= doneRefillingThreshold && state == SoldierState.Refill)) && !(state == SoldierState.RuinBuilding && rc.getPaint() >= 6)){
            state = SoldierState.Refill;
            if(fillingStation == null) fillingStation = nextNearestPaintTower();
            return;
        }
        fillingStation = null;
        //default to navigate, which defaults to explore if there is nothing to navigate to
        state = (rc.getRoundNum() < STOP_EXPLORING || (state == SoldierState.Explore) || rng.nextInt(19 - (int)adjustedMapSize) == 0) ? SoldierState.Explore : SoldierState.Navigate;
        //check if we see any nearby unclaimed ruins

        if(rc.getNumberTowers() < 25 && closestUnclaimedRuin != null && /*!checkedRuin.getBit(closestUnclaimedRuin)*/!checkedRuin.contains(closestUnclaimedRuin) && closestUnclaimedRuin.isWithinDistanceSquared(rc.getLocation(), GameConstants.VISION_RADIUS_SQUARED) && ! Utilities.basicLocationIsBehindWall(closestUnclaimedRuin)) {
            tilesNearRuin = rc.senseNearbyMapInfos(closestUnclaimedRuin, 8);
            if(workingOnRuin == null) {
                if(needsHelp(closestUnclaimedRuin)) {
                    workingOnRuin = closestUnclaimedRuin;
                    state = SoldierState.RuinBuilding;
                    return;
                }
//                else {
//                    //checkedRuin.setBit(closestUnclaimedRuin, true);
//                    checkedRuin.add(closestUnclaimedRuin);
//                }
            }
            else if(workingOnRuin.equals(closestUnclaimedRuin)) {
                    state = SoldierState.RuinBuilding;
                    return;
            }
        }
        curPattern = null;
        waitingToFinishRuin = false;
        //if we can see an enemy tower, maybe lets worry about that
        if(closestEnemyTower != null && closestEnemyTower.isWithinDistanceSquared(rc.getLocation(), GameConstants.VISION_RADIUS_SQUARED)) {
            state = SoldierState.Tower;
            return;
        }
//        if((numEnemyTiles > 7 && enemyRobots.length >= 2)) {
//            state = SoldierState.Tower;
//            return;
//        }

        //check if we see any uncompleted resource patterns marked out
        if(closestUnfilledPatternCenter != null && enemyRobots.length == 0) {
            if((closestUnclaimedRuin == null || closestUnclaimedRuin.distanceSquaredTo(closestUnfilledPatternCenter) > 25 || rc.getNumberTowers() == 25) && !checkedPattern.contains(closestUnfilledPatternCenter) && validateLocation(closestUnfilledPatternCenter)) {
                if(rc.getRoundNum() > TURN_TO_FILL) state = SoldierState.Fill;
            }
        }
    }

    public static boolean waitingToComplete(MapLocation ruin) throws GameActionException {
        for(RobotInfo r : rc.senseNearbyRobots(ruin, 2, rc.getTeam())) {
            if(r.getType() == UnitType.SOLDIER && r.getPaintAmount() > 0) return true;
        }
        if(rc.getLocation().isAdjacentTo(ruin))waitingToFinishRuin = true;
        return false;
    }

    public static void explore() throws GameActionException // use symmetry for this method
    {
//        if(claimedRuin != null && state != SoldierState.Refill) {
//            validateRuinClaim();
//            navigate();
//        }
        if((turnCount == 1 && rng.nextInt(15-(int)adjustedMapSize) == 0) || wallHug) {
            wallHug = true;
            //find the closest wall to you, and set that as your current objective
            if(target == null) {
                target = findClosestWall();
            }
            if(rc.getLocation().distanceSquaredTo(target) <= 4) {
                target = rotateCorner(clockwise);
                if(clockwise) rc.setIndicatorString("clockwise");
                else rc.setIndicatorString("reverse");
            }
            else {
                Direction dir = Pathfinding.bugBFS(target);
                if(dir != null && rc.canMove(dir)) {
                    rc.move(dir);
                }
            }

        }
        else {
            if (turnCount % 30 == 0 || target == null || rc.getLocation().distanceSquaredTo(target) < 8) {
                generateExploreLocation();
            }
            Direction dir = Pathfinding.bugBFS(target);
            if (dir != null && rc.canMove(dir)) rc.move(dir);
        }
        if(rc.getNumberTowers() > EXPLORE_FILL_TOWER_THRESHOLD) {
            attemptFill();
        }
        //rc.setIndicatorLine(rc.getLocation(), target, 255, 255,255);
    }

    //returns a location to explore
    public static void generateExploreLocation() throws GameActionException {
        if(closestUnseenRuin != null) {
            target = closestUnseenRuin;
            rc.setIndicatorLine(rc.getLocation(), closestUnseenRuin, 0, 0,255);
        }
        else {
            double ran = rng.nextDouble();
//            if (ran <= .10 && rc.getRoundNum() > 10) {
//                MapLocation closestCorner = closestCorner();
//                target = new MapLocation(Math.abs(rc.getMapWidth() - 1 - closestCorner.x), Math.abs(rc.getMapHeight() - 1 - closestCorner.y));
//                //rc.setIndicatorLine(rc.getLocation(), target, 255, 255,255);
//            } else {
                int i = 0;
                target = new MapLocation(rng.nextInt(rc.getMapWidth()), rng.nextInt(rc.getMapHeight()));
                while (target.isWithinDistanceSquared(rc.getLocation(), (int) (adjustedMapSize * 15)) || target.isWithinDistanceSquared(spawnLocation, (int) (adjustedMapSize * 15))) {
                    target = new MapLocation(rng.nextInt(rc.getMapWidth()), rng.nextInt(rc.getMapHeight()));
                    if (i++ >= 12) break;
                }
            //}
        }
    }

    //returns the closest corner to the robot
    public static MapLocation closestCorner() {
        int mapHeight = rc.getMapHeight() - 1;
        int mapWidth = rc.getMapWidth() - 1;
        int x = rc.getLocation().x;
        int y = rc.getLocation().y;
        int halfHeight = mapHeight / 2;
        int halfWidth = mapWidth / 2;
        if(x < halfWidth) {
            if(y < halfHeight) return new MapLocation(0, 0);
            else return new MapLocation(0, mapHeight);
        }
        else {
            if(y < halfHeight) return new MapLocation(mapWidth, 0);
            else return new MapLocation(mapWidth, mapHeight);
        }
    }

    //attempts to navigate to a known good place to be - lets first check unclaimed ruins, then lets go towards enemy towers
    //if we have nothing to do, just explore
    public static void navigate() throws GameActionException
    {
        if(oppositeHome != null && rc.getLocation().isWithinDistanceSquared(oppositeHome, 8)) checkedSymmetry = true;
        //if(claimedRuin != null) validateRuinClaim();
//        if(claimedRuin != null && rc.getNumberTowers() < 25) {
//            Direction dir = Pathfinding.bugBFS(claimedRuin);
//            if(dir != null && rc.canMove(dir)) rc.move(dir);
//            attemptFill();
//        }
        if(closestUnseenRuin != null) {
            //System.out.println("hello!");
            Direction dir = Pathfinding.bugBFS(closestUnseenRuin);
            if(rc.canMove(dir)) rc.move(dir);
            rc.setIndicatorLine(rc.getLocation(), closestUnseenRuin, 0, 0,255);
            attemptFill();
        }
        else if (rc.getRoundNum() >= TURN_TO_NAVIGATE_TO_TOWERS && closestEnemyTower != null) {
            Direction dir = Pathfinding.bugBFS(closestEnemyTower);
            if(dir != null && rc.canMove(dir)) rc.move(dir);
            attemptFill();
        }

        else if (knownSymmetry != SymmetryType.Unknown && !checkedSymmetry){
            if(oppositeHome == null) {
                SymmetryType[] possible = Symmetry.possibleSymmetry();
                int sym = rng.nextInt(possible.length);
                switch (possible[sym]) {
                    case Horizontal:
                        target = new MapLocation(spawnLocation.x, rc.getMapHeight() - 1 - spawnLocation.y);
                        oppositeHome = new MapLocation(spawnLocation.x, rc.getMapHeight() - 1 - spawnLocation.y);
                        break;
                    case Rotational:
                        target = new MapLocation(rc.getMapWidth() - 1 - spawnLocation.x, rc.getMapHeight() - 1 - spawnLocation.y);
                        oppositeHome = new MapLocation(rc.getMapWidth() - 1 - spawnLocation.x, rc.getMapHeight() - 1 - spawnLocation.y);
                        break;
                    case Vertical:
                        target = new MapLocation(rc.getMapWidth() - 1 - spawnLocation.x, spawnLocation.y);
                        oppositeHome = new MapLocation(rc.getMapWidth() - 1 - spawnLocation.x, spawnLocation.y);
                        break;
                }
            }
            target = oppositeHome;
            Direction dir = Pathfinding.bugBFS(oppositeHome);
            if(rc.canMove(dir)) rc.move(dir);
            attemptFill();
        }
//        else if(closestUnseenRuin != null) {
//            System.out.println("hello!");
//            Direction dir = Pathfinding.bugBFS(closestUnseenRuin);
//            if(rc.canMove(dir)) rc.move(dir);
//            rc.setIndicatorLine(rc.getLocation(), closestUnseenRuin, 0, 0,255);
//            attemptFill();
//        }
//        else if(!Communication.unclaimedRuins.isEmpty()) {
//            Direction dir = Pathfinding.bugBFS(closestUnclaimedRuin);
//            if(rc.canMove(dir)) rc.move(dir);
//            attemptFill();
//        }
        else {
            explore();
        }
    }

    public static void fill() throws GameActionException{
        MapInfo[] potentialTiles = rc.senseNearbyMapInfos(closestUnfilledPatternCenter, 8);
        Micro.patternFillingMicro(closestUnfilledPatternCenter, rc.getResourcePattern(), potentialTiles);
        if(rc.canCompleteResourcePattern(closestUnfilledPatternCenter)) {
            rc.completeResourcePattern(closestUnfilledPatternCenter);
        }
//        MapInfo bestTile = null;
//        boolean isSecondary = false;
//        int neededToFinish = 0;
//        //use the origin if the mark is primary, custom if the mark is primary
//        boolean markIsSecondary = rc.senseMapInfo(closestUnfilledPatternCenter).getMark().isSecondary();
//        int dist = rc.getLocation().distanceSquaredTo(closestUnfilledPatternCenter);
//        if (dist > 8) {
//            Direction dir = Pathfinding.bugBFS(closestUnfilledPatternCenter);
//            if (rc.canMove(dir)) rc.move(dir);
//        } else {
//            if (rc.senseMapInfo(rc.getLocation()).getPaint() == PaintType.EMPTY) {
//                boolean useSecondary = (markIsSecondary) ? Utilities.getColorFromCustomPattern(rc.getLocation(), rc.getResourcePattern(), closestUnfilledPatternCenter) : Utilities.getColorFromOriginPattern(rc.getLocation(), rc.getResourcePattern());
//                //rc.attack(rc.getLocation(), Utilities.getColorFromOriginPattern(rc.getLocation(), rc.getResourcePattern()));
//                rc.attack(rc.getLocation(), useSecondary);
//                if(rc.getPaint() >= 100) return;
//                else neededToFinish++;
//            }
//        }
//        for (MapInfo tile : potentialTiles) {
//            //boolean shouldBeSecondary = Utilities.getColorFromOriginPattern(tile.getMapLocation(), rc.getResourcePattern());
//            boolean shouldBeSecondary = (markIsSecondary) ? Utilities.getColorFromCustomPattern(tile.getMapLocation(), rc.getResourcePattern(), closestUnfilledPatternCenter) : Utilities.getColorFromOriginPattern(tile.getMapLocation(), rc.getResourcePattern());
//            if (tile.getPaint() == PaintType.EMPTY && tile.isPassable()) {
//                if (rc.canAttack(tile.getMapLocation()) && rc.getPaint() >= 100) {
//                    rc.attack(tile.getMapLocation(), shouldBeSecondary);
//                    return;
//                } else {
//                    bestTile = tile;
//                    isSecondary = shouldBeSecondary;
//                    neededToFinish++;
//                }
//            } else if (tile.getPaint().isAlly() && tile.isPassable() && tile.getPaint().isSecondary() != shouldBeSecondary) {
//                neededToFinish++;
//                if((bestTile == null || !bestTile.getPaint().isEnemy())) {
//                    bestTile = tile;
//                    isSecondary = shouldBeSecondary;
//                }
//            }
//        }
        //canFinishPattern = neededToFinish * 5 < rc.getPaint();
//        if (bestTile != null) {
//            if (rc.canAttack(bestTile.getMapLocation())) rc.attack(bestTile.getMapLocation(), isSecondary);
//            else if (rc.isMovementReady()) {
//                //Direction dir = Pathfinding.bugBFS(bestTile.getMapLocation());
//                Direction dir = rc.getLocation().directionTo(bestTile.getMapLocation());
//                if (rc.canMove(dir) && rc.getLocation().add(dir).isWithinDistanceSquared(closestUnfilledPatternCenter, 4)) rc.move(dir);
//                if (rc.canAttack(bestTile.getMapLocation())) rc.attack(bestTile.getMapLocation(), isSecondary);
//            }
//        }
    }

    //TODO: implement congestion control and prio q
    public static void refill() throws GameActionException
    {
        //navigate to the nearest paint tower
        //if that tower is surrounded, navigate to the next nearest
        if(fillingStation == null || (rc.canSenseLocation(fillingStation) && (rc.senseNearbyRobots(fillingStation, 2, rc.getTeam()).length > 3)||(rc.canSenseLocation(fillingStation) && !rc.canSenseRobotAtLocation(fillingStation)))) {
            fillingStation = nextNearestPaintTower();
            if(fillingStation == null){
                if(seenEnemyTower != null) {
                    if(rc.canAttack(seenEnemyTower.getLocation())) rc.attack(seenEnemyTower.getLocation());
                    Direction dir = Micro.runMicro(true);
                    if(rc.canMove(dir)) rc.move(dir);
                    if(rc.canAttack(seenEnemyTower.getLocation())) rc.attack(seenEnemyTower.getLocation());
                }
                else{
                    explore();
                }
                return;

            }
        }
        if(rc.canSenseRobotAtLocation(fillingStation) && (rc.senseRobotAtLocation(fillingStation).getType() != UnitType.LEVEL_ONE_PAINT_TOWER && rc.senseRobotAtLocation(fillingStation).getType() != UnitType.LEVEL_TWO_PAINT_TOWER && rc.senseRobotAtLocation(fillingStation).getType() != UnitType.LEVEL_THREE_PAINT_TOWER)){
            alliedPaintTowers.remove(new Ruin(fillingStation, 1, true));
            fillingStation = nextNearestPaintTower();
            if(fillingStation == null){
                if(seenEnemyTower != null) {
                    if(rc.canAttack(seenEnemyTower.getLocation())) rc.attack(seenEnemyTower.getLocation());
                    Direction dir = Micro.runMicro(true);
                    if(rc.canMove(dir)) rc.move(dir);
                    if(rc.canAttack(seenEnemyTower.getLocation())) rc.attack(seenEnemyTower.getLocation());
                }
                else{
                    explore();
                }
                return;
            }

        }
        if(rc.getLocation().isWithinDistanceSquared(fillingStation, 9)) {
            Micro.refillingMicro(fillingStation);
        }
        else {
            if(rc.isMovementReady()) {
                Direction dir = Pathfinding.bugBFS(fillingStation);
                if(rc.canMove(dir)) rc.move(dir);
            }
        }
    }

    public static void tower() throws GameActionException
    {
        if(rc.canAttack(closestEnemyTower)) {
            rc.attack(closestEnemyTower);
        }
        System.out.println(rc.canAttack(closestEnemyTower));
        if(rc.isMovementReady()) {
            boolean isRushing = (closestEnemyTower == null || !isDefenseTower(seenEnemyTower) && (rc.getHealth() > 50 || rc.getRoundNum() > START_RUSHING_TOWER_NUMBER) || allyRobots.length - enemyRobots.length > 4);
            Direction dir = Micro.runMicro(isRushing);
            if(rc.canMove(dir)) rc.move(dir);
        }
        if(rc.canAttack(closestEnemyTower)) {
            rc.attack(closestEnemyTower);
        }
        attemptFill();
    }


    public static void ruinBuilding() throws GameActionException {

        if (attemptCompleteTowerPattern(closestUnclaimedRuin)) {
            workingOnRuin = null;
            return;
        }
        //int dist = rc.getLocation().distanceSquaredTo(closestUnclaimedRuin);
        //claimedRuin = closestUnclaimedRuin;
        if(curPattern == null || (turnCount % 25 == 0 && allyRobots.length > 0)) {
            //curPattern = Utilities.inferPatternFromExistingSpots(closestUnclaimedRuin, tilesNearRuin);
            curPattern = Utilities.getPatternFromWeightedHash(closestUnclaimedRuin);
        }
        Micro.ruinBuildingMicro(closestUnclaimedRuin, curPattern, tilesNearRuin); //finds the best spaces to move and attack, and acts on that
        if (attemptCompleteTowerPattern(closestUnclaimedRuin)) {
            workingOnRuin = null;
            return;
        }
        if (rc.isActionReady() && 100 < rc.getPaint() && rc.getRoundNum() > EXPLORE_FILL_TOWER_THRESHOLD) attemptFill();
    }


    //attempt fill assumes we have already moved - move, then paint
    //TODO: instead make this a method that will heavily prioritize filling around pattern centers
    public static void attemptFill() throws GameActionException
    {
        if(!rc.isActionReady() || rc.getPaint() == 5) return;
        MapLocation curLoc = rc.getLocation();
        MapInfo curTile = rc.senseMapInfo(rc.getLocation());
        if(curTile.getPaint() == PaintType.EMPTY) {
            boolean isSecondary = Utilities.getColorFromOriginPattern(curLoc, rc.getResourcePattern());
            if(rc.canAttack(rc.getLocation())) rc.attack(rc.getLocation(), isSecondary);
            return;
        }
        //find the best tile to attack - just attack any tile we can see
        MapInfo bestTile = null;
        boolean isSecondary = false;
        for(MapInfo tile : rc.senseNearbyMapInfos(UnitType.SOLDIER.actionRadiusSquared)) {
            if(tile.getPaint() == PaintType.EMPTY && tile.isPassable()) {
                //isSecondary = Utilities.getColorFromOriginPattern(tile.getMapLocation(), rc.getResourcePattern());
//                if(rc.canAttack(tile.getMapLocation())) {
//                    rc.attack(tile.getMapLocation(), isSecondary);
//                    return;
//                }
                if(bestTile == null || (rc.canSenseRobotAtLocation(tile.getMapLocation()) && !rc.canSenseRobotAtLocation(bestTile.getMapLocation()))) {
                    bestTile = tile;
                    isSecondary = Utilities.getColorFromOriginPattern(tile.getMapLocation(), rc.getResourcePattern());
                }

            }
//            else if(tile.getPaint().isAlly() && tile.isPassable() && (closestUnclaimedRuin == null || !tile.getMapLocation().isWithinDistanceSquared(closestUnclaimedRuin, 8))) {
//                if((closestCustomPattern == null || !tile.getMapLocation().isWithinDistanceSquared(closestCustomPattern, 8)) && tile.getPaint().isSecondary() != Utilities.getColorFromOriginPattern(tile.getMapLocation(), rc.getResourcePattern())) {
//                    bestTile = tile;
//                    isSecondary = Utilities.getColorFromOriginPattern(tile.getMapLocation(), rc.getResourcePattern());
//                }
//            }
        }
        if(bestTile != null && rc.canAttack(bestTile.getMapLocation())) {
            //if(state == SoldierState.RuinBuilding && bestTile.getPaint().isAlly()) return;
            rc.attack(bestTile.getMapLocation(), isSecondary);
        }
    }

    //checks whether the 5x5 area around a location is empty of obstacles - currently also includes enemy paint as an obstacle
    public static boolean validateLocation(MapLocation loc) throws GameActionException {
        // int price = Clock.getBytecodesLeft();
        if(!rc.getLocation().isWithinDistanceSquared(loc, 4)) return false;
        MapInfo[] tiles = rc.senseNearbyMapInfos(loc, 8);
        if(tiles.length < 25) {
            for(int i = 0; i < tiles.length; i++) {
                if(!tiles[i].isPassable() || tiles[i].getPaint().isEnemy()) {
                    checkedPattern.add(tiles[i].getMapLocation());
                    return false;
                }
            }
            return true;
        }
        else {
            if (!tiles[0].isPassable() || tiles[0].getPaint().isEnemy()) {
                checkedPattern.add(tiles[0].getMapLocation());
                return false;
            }
            if (!tiles[1].isPassable() || tiles[1].getPaint().isEnemy()) {
                checkedPattern.add(tiles[1].getMapLocation());
                return false;
            }
            if (!tiles[2].isPassable() || tiles[2].getPaint().isEnemy()) {
                checkedPattern.add(tiles[2].getMapLocation());
                return false;
            }
            if (!tiles[3].isPassable() || tiles[3].getPaint().isEnemy()) {
                checkedPattern.add(tiles[3].getMapLocation());
                return false;
            }
            if (!tiles[4].isPassable() || tiles[4].getPaint().isEnemy()) {
                checkedPattern.add(tiles[4].getMapLocation());
                return false;
            }
            if (!tiles[5].isPassable() || tiles[5].getPaint().isEnemy()) {
                checkedPattern.add(tiles[5].getMapLocation());
                return false;
            }
            if (!tiles[6].isPassable() || tiles[6].getPaint().isEnemy()) {
                checkedPattern.add(tiles[6].getMapLocation());
                return false;
            }
            if (!tiles[7].isPassable() || tiles[7].getPaint().isEnemy()) {
                checkedPattern.add(tiles[7].getMapLocation());
                return false;
            }
            if (!tiles[8].isPassable() || tiles[8].getPaint().isEnemy()) {
                checkedPattern.add(tiles[8].getMapLocation());
                return false;
            }
            if (!tiles[9].isPassable() || tiles[9].getPaint().isEnemy()) {
                checkedPattern.add(tiles[9].getMapLocation());
                return false;
            }
            if (!tiles[10].isPassable() || tiles[10].getPaint().isEnemy()) {
                checkedPattern.add(tiles[10].getMapLocation());
                return false;
            }
            if (!tiles[11].isPassable() || tiles[11].getPaint().isEnemy()) {
                checkedPattern.add(tiles[11].getMapLocation());
                return false;
            }
            if (!tiles[12].isPassable() || tiles[12].getPaint().isEnemy()) {
                checkedPattern.add(tiles[12].getMapLocation());
                return false;
            }
            if (!tiles[13].isPassable() || tiles[13].getPaint().isEnemy()) {
                checkedPattern.add(tiles[13].getMapLocation());
                return false;
            }
            if (!tiles[14].isPassable() || tiles[14].getPaint().isEnemy()) {
                checkedPattern.add(tiles[14].getMapLocation());
                return false;
            }
            if (!tiles[15].isPassable() || tiles[15].getPaint().isEnemy()) {
                checkedPattern.add(tiles[15].getMapLocation());
                return false;
            }
            if (!tiles[16].isPassable() || tiles[16].getPaint().isEnemy()) {
                checkedPattern.add(tiles[16].getMapLocation());
                return false;
            }
            if (!tiles[17].isPassable() || tiles[17].getPaint().isEnemy()) {
                checkedPattern.add(tiles[17].getMapLocation());
                return false;
            }
            if (!tiles[18].isPassable() || tiles[18].getPaint().isEnemy()) {
                checkedPattern.add(tiles[18].getMapLocation());
                return false;
            }
            if (!tiles[19].isPassable() || tiles[19].getPaint().isEnemy()) {
                checkedPattern.add(tiles[19].getMapLocation());
                return false;
            }
            if (!tiles[20].isPassable() || tiles[20].getPaint().isEnemy()) {
                checkedPattern.add(tiles[20].getMapLocation());
                return false;
            }
            if (!tiles[21].isPassable() || tiles[21].getPaint().isEnemy()) {
                checkedPattern.add(tiles[21].getMapLocation());
                return false;
            }
            if (!tiles[22].isPassable() || tiles[22].getPaint().isEnemy()) {
                checkedPattern.add(tiles[22].getMapLocation());
                return false;
            }
            if (!tiles[23].isPassable() || tiles[23].getPaint().isEnemy()) {
                checkedPattern.add(tiles[23].getMapLocation());
                return false;
            }
            if (!tiles[24].isPassable() || tiles[24].getPaint().isEnemy()) {
                checkedPattern.add(tiles[24].getMapLocation());
                return false;
            }
        }
        // System.out.println("normal price:" + (price - Clock.getBytecodesLeft()));
        return true;
    }
    //checks whether the 5x5 area around a location is empty of obstacles
    public static boolean validatePlacement(MapLocation loc) throws GameActionException {
        MapInfo[] tiles = rc.senseNearbyMapInfos(loc, 8);
        if(!farFromEdge(loc)) {
            //invalidResourceCenters.add(loc);
            //invalidResourceCenters[loc.x][loc.y] = true;
            invalidResourceCenters.add(loc);
            //invalidResourceCenters.setBit(loc, true);
            return false;
        }
        //if(tiles.length < 25) return false;
        for (MapInfo tile : tiles) {
            if (!tile.isPassable() || tile.getMark().isAlly()){
                //invalidResourceCenters.add(loc);
                //invalidResourceCenters[loc.x][loc.y] = true;
                invalidResourceCenters.add(loc);
                //invalidResourceCenters.setBit(loc, true);
                return false;
            }
        }
//        for (int i = 0; i < 25; i++) {
//            if (!tiles[i].isPassable()) return false;
//        }
        //        if (!tiles[0].isPassable()) return false;
//        if (!tiles[1].isPassable()) return false;
//        if (!tiles[2].isPassable()) return false;
//        if (!tiles[3].isPassable()) return false;
//        if (!tiles[4].isPassable()) return false;
//        if (!tiles[5].isPassable()) return false;
//        if (!tiles[6].isPassable()) return false;
//        if (!tiles[7].isPassable()) return false;
//        if (!tiles[8].isPassable()) return false;
//        if (!tiles[9].isPassable()) return false;
//        if (!tiles[10].isPassable()) return false;
//        if (!tiles[11].isPassable()) return false;
//        if (!tiles[12].isPassable()) return false;
//        if (!tiles[13].isPassable()) return false;
//        if (!tiles[14].isPassable()) return false;
//        if (!tiles[15].isPassable()) return false;
//        if (!tiles[16].isPassable()) return false;
//        if (!tiles[17].isPassable()) return false;
//        if (!tiles[18].isPassable()) return false;
//        if (!tiles[19].isPassable()) return false;
//        if (!tiles[20].isPassable()) return false;
//        if (!tiles[21].isPassable()) return false;
//        if (!tiles[22].isPassable()) return false;
//        if (!tiles[23].isPassable()) return false;
//        if (!tiles[24].isPassable()) return false;
        // System.out.println("normal price:" + (price - Clock.getBytecodesLeft()));
        return true;
    }
    //checks whether the 5x5 area around a location is empty of obstacles - currently also includes enemy paint as an obstacle
    public static boolean validateLocationTest(MapLocation loc) throws GameActionException {
        int price = Clock.getBytecodesLeft();
        MapInfo[] tiles = rc.senseNearbyMapInfos(loc, 8);
        if(!farFromEdge(loc)) return false;
        for(MapInfo tile : tiles) {
            if(!tile.isPassable() || tile.getPaint().isEnemy()) return false;
        }
        //out.println("test price: " + (price - Clock.getBytecodesLeft()));
        return true;
    }

    public static boolean attemptCompleteTowerPattern(MapLocation ruin) throws GameActionException
    {
//        for(MapLocation ruin : nearbyRuins)
//        {
            if(!rc.canSenseRobotAtLocation(ruin) &&
                    rc.getLocation().isWithinDistanceSquared(ruin, UnitType.SOLDIER.actionRadiusSquared))
            {
                if(rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_DEFENSE_TOWER, ruin))
                {
                    rc.completeTowerPattern(UnitType.LEVEL_ONE_DEFENSE_TOWER, ruin);
                    return true;
                }

                if(rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, ruin))
                {
                    rc.completeTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, ruin);
                    return true;
                }

                if(rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, ruin))
                {
                    rc.completeTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, ruin);
                    return true;
                }
            //
        }
        return false;
    }

    //returns whether the location is far enough from the edge to be a valid resource location center
    public static boolean farFromEdge(MapLocation loc) {
        int x = loc.x;
        int y = loc.y;
        int mapHeight = rc.getMapHeight() - 1;
        int mapWidth = rc.getMapWidth() - 1;
        return x >= 2 && x <= mapWidth - 2 && y >= 2 && y <= mapHeight - 2;
    }

    static void paintMove(MapLocation target, boolean isSecondary) throws GameActionException
    {
        Direction directionToMove = Pathfinding.bugBFS(target);
        MapLocation futurePosition = rc.getLocation().add(directionToMove);
        if(rc.canPaint(futurePosition) && rc.canAttack(futurePosition) && !rc.senseMapInfo(futurePosition).getPaint().isAlly())
        {
            rc.attack(futurePosition, isSecondary);
        }
        if(rc.canMove(directionToMove))
        {
            rc.move(directionToMove);
        }
    }

    static void paintMove(Direction directionToMove) throws GameActionException
    {
        MapLocation futurePosition = rc.getLocation().add(directionToMove);
        if(rc.canPaint(futurePosition) && rc.canAttack(futurePosition))
        {
            rc.attack(futurePosition);
        }
        if(rc.canMove(directionToMove))
        {
            rc.move(directionToMove);
        }
    }

    //checks whether a space is in firing range of any seen enemy towers
    public static boolean isSafeFromTower(MapLocation loc) {
        for(RobotInfo enemy : enemyRobots) {
            if(enemy.type.isTowerType()) {
                if(loc.distanceSquaredTo(enemy.getLocation()) <= enemy.getType().actionRadiusSquared) {
                    return false;
                }
            }
        }
        return true;
    }

    //returns the closest enemy tower we know of
    public static MapLocation closestEnemyTower() {
        int minDist = Integer.MAX_VALUE;
        Ruin r = null;
        for(Ruin ruin : Communication.enemyTowers) {
            //if(enemyDefenseTowers.getBit(ruin.location)) continue;
            if(enemyDefenseTowers.contains(ruin.location)) continue;
            if(ruin.location.distanceSquaredTo(rc.getLocation()) < minDist) {
                minDist = ruin.location.distanceSquaredTo(rc.getLocation());
                r = ruin;
                if (minDist <= 8) return r.location;
            }
        }
        return (minDist == Integer.MAX_VALUE) ? null : r.location;
    }

    private static MapLocation closestUnclaimedRuin() {
        int minDist = Integer.MAX_VALUE;
        Ruin r = null;
        for(Ruin ruin : Communication.unclaimedRuins) {
            if(ruin.location.distanceSquaredTo(rc.getLocation()) < minDist) {
                minDist = ruin.location.distanceSquaredTo(rc.getLocation());
                r = ruin;
                if (minDist <= 8) return r.location;
            }
        }
        return (minDist == Integer.MAX_VALUE) ? null : r.location;
    }
    //returns the closest paint tower that is not currently the filling station or the nearestPaintTower
    private static MapLocation nextNearestPaintTower() throws GameActionException {
        MapLocation curLoc = rc.getLocation();
        int curClosest = Integer.MAX_VALUE;
        MapLocation temp = null;
        for (Ruin r : alliedPaintTowers) {
            if (r.location == fillingStation) continue;
            else {
                if(curLoc.distanceSquaredTo(r.location) < curClosest) {
                    curClosest = curLoc.distanceSquaredTo(r.location);
                    temp = r.location;
                }
            }
        }
        if(temp == fillingStation) return null;
        return temp;
    }

    //returns whether a ruin is completely tiled in, and just waiting for completion
    public static boolean isOccupied(MapLocation ruin) throws GameActionException {
        for(MapInfo tile : rc.senseNearbyMapInfos(ruin, 2)) {
            if(tile.getMark().isSecondary()) {
                return rc.senseNearbyRobots(ruin, 2, rc.getTeam()).length > 0;
            }
        }
        return false;
    }
    //determines the closest wall to this robot
    private static MapLocation findClosestWall() throws GameActionException {
        int curX = rc.getLocation().x;
        int mapWidth = rc.getMapWidth() - 2;
        int mapWidthHalf = mapWidth / 2;
        int closestX;
        if (curX > mapWidthHalf) {
            closestX = mapWidth - 1;
        }
        else {
            closestX = 1;
        }
        int curY = rc.getLocation().y;
        int mapHeight = rc.getMapHeight() - 2;
        int mapHeightHalf = mapHeight / 2;
        int closestY;
        if (curY > mapHeightHalf) {
            closestY = mapHeight - 1;
        }
        else {
            closestY = 1;
        }
        int diffX = Math.abs(closestX - curX);
        int diffY = Math.abs(closestY - curY);
        if(diffX < diffY) {
            return new MapLocation(closestX, curY);
        }
        else {
            return new MapLocation(curX, closestY);
        }
    }
    //returns the closest corner to this robot
    public static MapLocation nearestCorner() throws GameActionException {
        MapLocation curLoc = rc.getLocation();
        if(curLoc.x > rc.getMapWidth() / 2) {
            if(curLoc.y > rc.getMapHeight() / 2) {
                return new MapLocation(rc.getMapWidth() - 1, rc.getMapHeight() - 1);
            }
            else {
                return new MapLocation(rc.getMapWidth() - 1, 0);
            }
        }
        else {
            if(curLoc.y > rc.getMapHeight() / 2) {
                return new MapLocation(0, rc.getMapHeight() - 1);
            }
            else {
                return new MapLocation(0, 0);
            }
        }
    }
    //finds the closest corner in either the clockwise or counterclockwise direction, given the edge you are on
    public static MapLocation rotateCorner(boolean clockwise) throws GameActionException {
        int curX = rc.getLocation().x;
        int mapWidth = rc.getMapWidth() - 1;
        int curY = rc.getLocation().y;
        int mapHeight = rc.getMapHeight() - 1;
        //we aren't even chasing a corner yet - need to return the closest one in the direction we are going
        if(!(target.x == 1 || target.x == mapWidth - 1 && target.y == 1 || target.y == mapHeight - 1)) {
            int newX;
            int newY;
            //top edge
            if(mapHeight - curY <= 4) {
                if(clockwise) {
                    newX = mapWidth - 1;
                }
                else {
                    newX = 1;
                }
                newY = mapHeight - 1;
            }
            //right edge
            else if(mapWidth - curX <= 4) {
                if(clockwise) {
                    newY = 1;
                }
                else {
                    newY = mapHeight - 1;
                }
                newX = mapWidth - 1;
            }
            //bottom edge
            else if (curY <= 4) {
                if(clockwise) {
                    newX = 1;
                }
                else {
                    newX = mapWidth - 1;
                }
                newY = 1;
            }
            // left edge
            else {
                if(clockwise) {
                    newY = mapHeight - 1;
                }
                else {
                    newY = 1;
                }
                newX = 1;
            }
            return new MapLocation(newX, newY);
        }
        //bottom corner -> clockwise needs to go to top left, CCW needs to go to bottom right
        if(curX < 5 && curY < 5) {
            if(clockwise) {
                return new MapLocation(1, mapHeight - 1);
            }
            else {
                return new MapLocation(mapWidth - 1, 1);
            }
        }
        //bottom right corner
        else if(curX > 5 && curY < 5) {
            if(clockwise) {
                return new MapLocation(1, 1);
            }
            else {
                return new MapLocation(mapWidth - 1, mapHeight - 1);
            }
        }
        //top right
        else if(curX > 5 && curY > 5) {
            if(clockwise) {
                return new MapLocation(mapWidth - 1, 1);
            }
            else {
                return new MapLocation(1, mapHeight - 1);
            }
        }
        //top left
        else {
            if(clockwise) {
                return new MapLocation(mapWidth - 1, mapHeight - 1);
            }
            else {
                return new MapLocation(1, 1);
            }
        }
    }


//    //tries to move to an adjacent ally or non enemy tile
//    public static void moveToSafety() throws GameActionException{
//        PaintType paint = rc.senseMapInfo(rc.getLocation()).getPaint();
//        switch(paint) {
//            case PaintType.ENEMY_SECONDARY, PaintType.ENEMY_PRIMARY -> {
//                MapInfo bestTile = null;
//                for(MapInfo tile : rc.senseNearbyMapInfos(rc.getLocation(), 2)) {
//                    if(tile.getPaint().isAlly() && tile.isPassable()) {
//                        bestTile = tile;
//                    }
//                    else if(tile.getPaint().isEnemy() == null)
//                }
//            }
//            default -> {
//
//            }
//        }
//    }
}
