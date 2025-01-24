package Version14.Robots;

import Version14.Misc.Communication;
import Version14.Micro.Micro;
import Version14.Pathfinding.Pathfinding;
import Version14.Misc.Ruin;
import Version14.RobotPlayer;
import Version14.Utility.BitBoard;
import Version14.Utility.SoldierUtil;
import Version14.Utility.Utilities;
import battlecode.common.*;

import static Version14.RobotPlayer.staticRC;
import static Version14.RobotPlayer.rng;
import static Version14.Misc.Communication.alliedPaintTowers;
import static Version14.RobotPlayer.*;
import static Version14.Utility.SoldierUtil.needsHelp;
import static Version14.Utility.SoldierUtil.validateRuinClaim;

public class Soldier {
    enum SoldierState {
        Explore,        //We have nowhere to go to, let's explore
        Navigate,       //We have a target in mind, let's pursue it - if we end up with no target, explore
        Refill,         //We need paint, let's go back to our own tower
        Tower,          //We are near a tower, let's use micro to efficiently fight them
        RuinBuilding,   //We are near a ruin and we want to paint around it
        Fill
    }

    enum ExploreState {
        wallHug,
        centralExploration,
        oppositeCorner,
        random
    }

    public static SoldierState state = SoldierState.Explore;
    
    public static MapLocation curObjective = null;
    public static MapLocation closestUnclaimedRuin; //keeps track of the closest unclaimed ruin we know of
    public static MapLocation closestUnfilledPatternCenter;
    public static MapLocation closestEnemyTower;

    public static SoldierState prevState = null;
    //static boolean canSeeEmpty;
    public static MapLocation fillingStation;
    public static MapLocation claimedRuin = null;
    public static int numEnemyTiles;
    public static boolean visitingCorner = false;
    public static boolean beenToCorner = false;
    public static MapInfo[] tilesNearRuin;
    public static RobotInfo seenEnemyTower;
    //public static boolean wallHug;
    public static ExploreState exploration;
    public static boolean clockwise;
    public static boolean canFinishRuin = false;
    public static int neededToFinish = Integer.MAX_VALUE;
    public static MapLocation spawnLocation;
    public static boolean[][] invalidResourceCenters;
    //public static BitBoard invalidResourceCenters;
    public static boolean canFinishPattern =false;

    public static boolean checkedSymmetry = false;
    public static MapLocation oppositeHome = null;

    //static int failedPlacementLocations = 0;
    //static int minDistanceToValidLocation = Integer.MAX_VALUE;

    public static final int clearContestedRuinsAndWaitingRuins = 50; //how often we should clear this hashSet

    public static MapLocation averageEnemyPaint;

    public static final int ENEMY_TOWER_REFRESH = 3;
    //Constants
    public final static int refillThreshold = 20;    //Paint level at which soldiers go to refill
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
            spawnLocation = staticRC.getLocation();
            invalidResourceCenters = new boolean[staticRC.getMapWidth()][staticRC.getMapHeight()];
        }

        updateInfo();
        //update the state
        updateState();
        //run the state
        switch(state)
        {
            case Explore ->
            {
                staticRC.setIndicatorString("Exploring");
                explore();
            }
            case Navigate ->
            {
                staticRC.setIndicatorString("Navigating");
                navigate();
            }
            case Refill ->
            {
                staticRC.setIndicatorString("Refilling");
                refill();
            }
            case Tower ->
            {
                staticRC.setIndicatorString("Attacking Tower");
                tower();
            }
            case RuinBuilding ->
            {
                staticRC.setIndicatorString("Building a Ruin");
                ruinBuilding();
            }
            case Fill -> {
                staticRC.setIndicatorString("Filling a pattern");
                fill();
            }
        }
        if(closestUnclaimedRuin != null) attemptCompleteTowerPattern(closestUnclaimedRuin);
        if(claimedRuin != null && VALIDATE_RUIN_CLAIM_FREQUENCY % turnCount == 0) {
            validateRuinClaim();
        }
        if(state == SoldierState.Explore){
            staticRC.setIndicatorString("Exploring: " + exploration.toString() + " : " + curObjective.toString());
            staticRC.setIndicatorLine(staticRC.getLocation(), curObjective, 255, 255, 255);
        }
//        if(Clock.getBytecodesLeft() > 3000) {
//            Utilities.updatePaintAverages();
//        }
        //if(claimedRuin != null) staticRC.setIndicatorString(claimedRuin.toString());

//        if(target == null) staticRC.setIndicatorString(state.toString());
//        else staticRC.setIndicatorString(state.toString() + " : " + target.toString());
//        if(staticRC.isMovementReady() && !staticRC.senseMapInfo(staticRC.getLocation()).getPaint().isAlly()){
//            moveToSafety();
//        }
    }

    public static void initializeMapDependentVariables() throws GameActionException {
        int mapSize = staticRC.getMapHeight() * staticRC.getMapWidth();
        TURN_TO_NAVIGATE_TO_TOWERS = (int)(mapSize / 12.5); //indicates at what turn we will prioritize going towards enemy towers
        STOP_EXPLORING =  (int)(mapSize / 12.5); //indicates when soldiers will began defaulting to navigate instead of explore
        TURN_TO_FILL = (int)(mapSize / 37.5); //turn at which filling becomes allowed
        INCENTIVIZE_MONEY_ROUND = 80; //turn at which any time before that soldiers will give a slight weight to building money towers
        //y = 0.019x + 32.4 -> calibrates it to 40 at min size and 100 at max size
        FORCE_MONEY_ROUND = (int)(0.019 * mapSize + 32.4);//turn at which any time before that soldiers will always build money towers
        //y = y = y = 0.003x + 4.8-> calibrates it to be 6 on the smallest map size and 15 on the largest map size
        START_RUSHING_TOWER_NUMBER = (int) ((0.003 * mapSize) + 4.8);
        //y = 0.004x + 5.4 -> calibrates to 8 on smallest map and 20 on largest map
        EXPLORE_FILL_TOWER_THRESHOLD = (int) ((0.004 * mapSize) + 6.4);

    }


    static void updateInfo() throws GameActionException
    {
        //reset turn by turn variables
        closestUnclaimedRuin = null;
        closestEnemyTower = null;
        averageEnemyPaint = null;
        closestUnfilledPatternCenter = null;
        //closestCustomPattern = null; //tracks the closest center of a custom pattern, so that it doesn't get overridden by attemptFill
//        if(claimedRuin != null && staticRC.getRoundNum() % VALIDATE_RUIN_CLAIM_FREQUENCY == 0) {
//            validateRuinClaim();
//        }
//        if(turnCount == 1 || turnCount % ENEMY_TOWER_REFRESH == 0) {
//            closestEnemyTower = closestEnemyTower();
//        }
        closestEnemyTower = closestEnemyTower();
        closestUnclaimedRuin = closestUnclaimedRuin();
        SoldierUtil.scanNearbyTilesSoldier();
//        int enemyCount = 0;
//        int x = 0;
//        int y = 0;
//        int resourcePatternDist = Integer.MAX_VALUE;
//        int failedPlacementLocations = 0;
//        int minDistanceToValidLocation = Integer.MAX_VALUE;
        //int minDistanceToCustomPatternCenter = Integer.MAX_VALUE;
        //do all the tasks which require looping through all nearbyTiles
//        for(MapInfo tile : nearbyTiles) {
//            if(knownSymmetry == Symmetry.Unknown) {
//                map[tile.getMapLocation().x][tile.getMapLocation().y] = (tile.isPassable()) ? 1 : (tile.isWall()) ? 2 : 3;
//                if(!tile.isPassable())  Utilities.validateSymmetry(tile.getMapLocation(), tile.hasRuin());
//            }
//            MapLocation tileLoc = tile.getMapLocation();
//            if(tile.getPaint().isEnemy()) {
//                x += tileLoc.x;
//                y += tileLoc.y;
//                enemyCount++;
//            }
//            PaintType mark = tile.getMark();
//            int dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
//            if(mark.isAlly()) {
//                if (!tile.isResourcePatternCenter() && dist < resourcePatternDist) {
//                    closestUnfilledPatternCenter = tileLoc;
//                    resourcePatternDist = dist;
//                }
//                if (dist < minDistanceToValidLocation) {
//                    minDistanceToValidLocation = dist;
//                }
//                if (staticRC.canCompleteResourcePattern(tile.getMapLocation()))
//                    staticRC.completeResourcePattern(tile.getMapLocation());
//            }
//            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
//                if(!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
//                    if(dist < minDistanceToValidLocation) {
//                        minDistanceToValidLocation = dist;
//                    }
//                    if(staticRC.canMark(tile.getMapLocation())) {
//                        staticRC.mark(tile.getMapLocation(), false);
//                        closestUnfilledPatternCenter = tile.getMapLocation();
//                    }
//                }
//                else {
//                    failedPlacementLocations++;
//                    //staticRC.setIndicatorDot(tileLoc, 255, 255, 255);
//                }
//            }
//        }
//        if(failedPlacementLocations >= 4 && minDistanceToValidLocation >= 25) {
//            if(validatePlacement(staticRC.getLocation())) {
//                if(staticRC.canMark(staticRC.getLocation())) {
//                    staticRC.mark(staticRC.getLocation(), true);
//                    closestUnfilledPatternCenter = staticRC.getLocation();
//                }
//            }
//        }
//        numEnemyTiles = enemyCount;
//        averageEnemyPaint = (enemyCount != 0) ? new MapLocation(x/enemyCount, y/enemyCount) : null;
//        if(claimedRuin != null && staticRC.canSenseLocation(claimedRuin) && staticRC.canSenseRobotAtLocation(claimedRuin)) {
//            claimedRuin = null;
//        }
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
        if((staticRC.getPaint() <= refillThreshold || (staticRC.getPaint() <= doneRefillingThreshold && state == SoldierState.Refill)) && !canFinishRuin && !canFinishPattern){
            state = SoldierState.Refill;
            if(fillingStation == null) fillingStation = nextNearestPaintTower();
            return;
        }
        canFinishPattern = false;
        canFinishRuin = false;
        fillingStation = null;
        //default to navigate, which defaults to explore if there is nothing to navigate to
        state = (staticRC.getRoundNum() < STOP_EXPLORING || (state == SoldierState.Explore) || rng.nextInt(18) == 0) ? SoldierState.Explore : SoldierState.Navigate;
        //check if we see any nearby unclaimed ruins
        if(staticRC.getNumberTowers() < 25 && closestUnclaimedRuin != null && closestUnclaimedRuin.isWithinDistanceSquared(staticRC.getLocation(), GameConstants.VISION_RADIUS_SQUARED)) {
            if(needsHelp(closestUnclaimedRuin)) {
                state = SoldierState.RuinBuilding;
                return;
            }
            else if (claimedRuin != null) {
                claimedRuin = null;
            }
        }
        //if we can see an enemy tower, maybe lets worry about that
        if(closestEnemyTower != null && closestEnemyTower.isWithinDistanceSquared(staticRC.getLocation(), GameConstants.VISION_RADIUS_SQUARED)) {
            state = SoldierState.Tower;
            return;
        }
        if((numEnemyTiles > 7 && enemyRobots.length >= 2)) {
            state = SoldierState.Tower;
            return;
        }

        //check if we see any uncompleted resource patterns marked out
        if(closestUnfilledPatternCenter != null && enemyRobots.length == 0) {
            if((closestUnclaimedRuin == null || closestUnclaimedRuin.distanceSquaredTo(closestUnfilledPatternCenter) > 25 || staticRC.getNumberTowers() == 25) && validateLocation(closestUnfilledPatternCenter)) {
                if(staticRC.getRoundNum() > TURN_TO_FILL) state = SoldierState.Fill;
            }
        }
    }

    public static void explore() throws GameActionException // use symmetry for this method
    {
        if(claimedRuin != null && state != SoldierState.Refill && staticRC.getNumberTowers() <= 25) {
            validateRuinClaim();
            navigate();
        }
        if(exploration == null) {
            chooseExplorationState();
        }
        if(exploration == ExploreState.wallHug) {
            if(curObjective == null) {
                curObjective = findClosestWall();
            }
            if(staticRC.getLocation().distanceSquaredTo(curObjective) <= 4) {
                curObjective = rotateCorner(clockwise);
                if(clockwise) staticRC.setIndicatorString("clockwise");
                else staticRC.setIndicatorString("reverse");
            }
        }
        else {
            if(curObjective != null && staticRC.getLocation().isWithinDistanceSquared(curObjective, 8)) {
                curObjective = null;
                switch(exploration) {
                    case ExploreState.centralExploration, ExploreState.oppositeCorner -> {
                        exploration = ExploreState.random;
                        break;
                    }
                }
            }
            if (curObjective == null) {
                switch (exploration) {
                    case ExploreState.centralExploration -> {
                        curObjective = Utilities.generateRandomLocationWithinRadius(new MapLocation(staticRC.getMapWidth()/ 2, staticRC.getMapHeight() / 2), 25 + EXPLORE_FILL_TOWER_THRESHOLD * 2);
                        break;
                    }
                    //try to explore to the opposite corner
                    case ExploreState.oppositeCorner -> {
                        MapLocation closestCorner = closestCorner();
                        //MapLocation oppositeCorner = new MapLocation(Math.abs(staticRC.getMapWidth() - 1 - closestCorner.x), Math.abs(staticRC.getMapHeight() - 1 - closestCorner.y));
                        curObjective = new MapLocation(Math.abs(staticRC.getMapWidth() - 1 - closestCorner.x), Math.abs(staticRC.getMapHeight() - 1 - closestCorner.y));
                        break;
                    }
                    case ExploreState.random -> {
                        curObjective = new MapLocation(rng.nextInt(staticRC.getMapWidth()), rng.nextInt(staticRC.getMapHeight()));
                        break;
                    }
                }
            }
        }
        Direction dir = Pathfinding.bugBFS(curObjective);
        if(staticRC.canMove(dir)) staticRC.move(dir);
        if(staticRC.getNumberTowers() > EXPLORE_FILL_TOWER_THRESHOLD) {
            attemptFill();
        }
    }
    
    //selects an exploration state probabilistically
    public static void chooseExplorationState() {
        //if(staticRC.getRoundNum() <= 2) exploration = ExploreState.oppositeCorner;
        //else {
            double r = rng.nextDouble();
            //exploration = ExploreState.values()[rng.nextInt(ExploreState.values().length)];
            //after round 40, we will allow wallHugging
            if (staticRC.getRoundNum() > 50) {
                if (r <= 0.06) exploration = ExploreState.wallHug;
                else if (r <= 0.45) exploration = ExploreState.centralExploration;
                else if (r <= 0.8) exploration = ExploreState.oppositeCorner;
                else exploration = ExploreState.random;
            } else {
                if (r <= 0.3) exploration = ExploreState.centralExploration;
                else if (r <= 0.8) exploration = ExploreState.oppositeCorner;
                else exploration = ExploreState.random;
            }
      //  }
    }

    //returns the closest corner to the robot
    public static MapLocation closestCorner() {
        int mapHeight = staticRC.getMapHeight() - 1;
        int mapWidth = staticRC.getMapWidth() - 1;
        int x = staticRC.getLocation().x;
        int y = staticRC.getLocation().y;
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
        if(oppositeHome != null && staticRC.getLocation().isWithinDistanceSquared(oppositeHome, 8)) checkedSymmetry = true;
        if(claimedRuin != null) validateRuinClaim();
        if(claimedRuin != null && staticRC.getNumberTowers() <= 25) {
            Direction dir = Pathfinding.bugBFS(claimedRuin);
            if(dir != null && staticRC.canMove(dir)) staticRC.move(dir);
            attemptFill();
        }
        else if (staticRC.getRoundNum() >= TURN_TO_NAVIGATE_TO_TOWERS && closestEnemyTower != null) {
            Direction dir = Pathfinding.bugBFS(closestEnemyTower);
            if(dir != null && staticRC.canMove(dir)) staticRC.move(dir);
            attemptFill();
        }
        else if (knownSymmetry != Symmetry.Unknown && !checkedSymmetry){
            if(oppositeHome == null) {
                Symmetry[] possible = Utilities.possibleSymmetry();
                int sym = rng.nextInt(possible.length);
                switch (possible[sym]) {
                    case Horizontal:
                        oppositeHome = new MapLocation(spawnLocation.x, staticRC.getMapHeight() - 1 - spawnLocation.y);
                        break;
                    case Rotational:
                        oppositeHome = new MapLocation(staticRC.getMapWidth() - 1 - spawnLocation.x, staticRC.getMapHeight() - 1 - spawnLocation.y);
                        break;
                    case Vertical:
                        oppositeHome = new MapLocation(staticRC.getMapWidth() - 1 - spawnLocation.x, spawnLocation.y);
                        break;
                }
            }
            Direction dir = Pathfinding.bugBFS(oppositeHome);
            if(staticRC.canMove(dir)) staticRC.move(dir);
            attemptFill();
        }
//        else if(!Communication.unclaimedRuins.isEmpty()) {
//            Direction dir = BFS_7x7.pathfind(closestUnclaimedRuin);
//            if(staticRC.canMove(dir)) staticRC.move(dir);
//            attemptFill();
//        }
        else {
            explore();
        }
    }

    public static void fill() throws GameActionException{
        MapInfo[] potentialTiles = staticRC.senseNearbyMapInfos(closestUnfilledPatternCenter, 8);
        Micro.patternFillingMicro(closestUnfilledPatternCenter, staticRC.getResourcePattern(), potentialTiles);
        canFinishPattern = neededToFinish * 5 < staticRC.getPaint();
    }

    //TODO: implement congestion control and prio q
    public static void refill() throws GameActionException
    {
        //navigate to the nearest paint tower
        //if that tower is surrounded, navigate to the next nearest
        if(fillingStation == null || (staticRC.canSenseLocation(fillingStation) && (staticRC.senseNearbyRobots(fillingStation, 2, staticRC.getTeam()).length > 3)||(staticRC.canSenseLocation(fillingStation) && !staticRC.canSenseRobotAtLocation(fillingStation)))) {
            fillingStation = nextNearestPaintTower();
            if(fillingStation == null){
                if(seenEnemyTower != null) {
                    if(staticRC.canAttack(seenEnemyTower.getLocation())) staticRC.attack(seenEnemyTower.getLocation());
                    Direction dir = Micro.runMicro(true);
                    if(staticRC.canMove(dir)) staticRC.move(dir);
                    if(staticRC.canAttack(seenEnemyTower.getLocation())) staticRC.attack(seenEnemyTower.getLocation());
                }
                else{
                    explore();
                }
                return;

            }
        }
        if(staticRC.getLocation().isWithinDistanceSquared(fillingStation, 9)) {
            Micro.refillingMicro(fillingStation);
        }
        else {
            if(staticRC.isMovementReady()) {
                Direction dir = Pathfinding.bugBFS(fillingStation);
                if(staticRC.canMove(dir)) staticRC.move(dir);
            }
        }
    }

    public static void tower() throws GameActionException
    {
        if(staticRC.canAttack(closestEnemyTower)) {
            staticRC.attack(closestEnemyTower);
        }
        if(staticRC.isMovementReady()) {
            Direction dir = Micro.runMicro((((allyRobots.length - enemyRobots.length > 4) || allyRobots.length > 10) && staticRC.getHealth() > 25) || staticRC.getNumberTowers() >= START_RUSHING_TOWER_NUMBER);
            if(staticRC.canMove(dir)) staticRC.move(dir);
        }
        if(staticRC.canAttack(closestEnemyTower)) {
            staticRC.attack(closestEnemyTower);
        }
        attemptFill();
    }

    public static void ruinBuilding() throws GameActionException {

        if (attemptCompleteTowerPattern(closestUnclaimedRuin)) {
            claimedRuin = null;
            return;
        }
        int dist = staticRC.getLocation().distanceSquaredTo(closestUnclaimedRuin);
        if(claimedRuin == null) {
                if (staticRC.senseNearbyRobots(closestUnclaimedRuin, 8, staticRC.getTeam()).length == 0) {
                    claimedRuin = closestUnclaimedRuin;
                }
        }
        else {
            if(staticRC.senseNearbyRobots(closestUnclaimedRuin, 2, staticRC.getTeam()).length > 0) {
                claimedRuin = null;
            }
        }
        boolean[][] desiredPattern = Utilities.inferPatternFromExistingSpots(closestUnclaimedRuin, tilesNearRuin);
        if (desiredPattern == null) {
            desiredPattern = chooseDesiredPattern(closestUnclaimedRuin);
        }
        Micro.ruinBuildingMicro(closestUnclaimedRuin, desiredPattern, tilesNearRuin); //finds the best spaces to move and attack, and acts on that
        if (attemptCompleteTowerPattern(closestUnclaimedRuin)) {
            claimedRuin = null;
            return;
        }
        if (staticRC.isActionReady() && (neededToFinish * 5) + 5 < staticRC.getPaint() && staticRC.getRoundNum() > EXPLORE_FILL_TOWER_THRESHOLD) attemptFill();
    }

    //where we decide what kind of pattern to use
    //logic currently copied from version 8
    public static boolean[][] chooseDesiredPattern(MapLocation m) throws GameActionException {
        //if(enemyRobots.length >= 1) return staticRC.getTowerPattern(UnitType.LEVEL_ONE_DEFENSE_TOWER);
        int ranNum = (staticRC.getRoundNum() < FORCE_MONEY_ROUND) ? 0 : rng.nextInt(2);
        if(staticRC.getRoundNum() > FORCE_MONEY_ROUND && staticRC.getMoney() > 5000) return staticRC.getTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER);
        if(ranNum == 0) {
            return (staticRC.getMapHeight() * staticRC.getMapWidth() <= 750 && staticRC.getRoundNum() > 100) ? staticRC.getTowerPattern(UnitType.LEVEL_ONE_DEFENSE_TOWER) : staticRC.getTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER);
        }
        else {
            return staticRC.getTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER);
        }
    }

    //attempt fill assumes we have already moved - move, then paint
    //TODO: instead make this a method that will heavily prioritize filling around pattern centers
    public static void attemptFill() throws GameActionException
    {
        if(!staticRC.isActionReady() || staticRC.getPaint() == 5) return;
        MapLocation curLoc = staticRC.getLocation();
        MapInfo curTile = staticRC.senseMapInfo(staticRC.getLocation());
        if(curTile.getPaint() == PaintType.EMPTY) {
            boolean isSecondary = Utilities.getColorFromOriginPattern(curLoc, staticRC.getResourcePattern());
            if(staticRC.canAttack(staticRC.getLocation())) staticRC.attack(staticRC.getLocation(), isSecondary);
            return;
        }
        //find the best tile to attack - just attack any tile we can see
        MapInfo bestTile = null;
        boolean isSecondary = false;
        for(MapInfo tile : staticRC.senseNearbyMapInfos(UnitType.SOLDIER.actionRadiusSquared)) {
            if(tile.getPaint() == PaintType.EMPTY && tile.isPassable()) {
                //isSecondary = Utilities.getColorFromOriginPattern(tile.getMapLocation(), staticRC.getResourcePattern());
//                if(staticRC.canAttack(tile.getMapLocation())) {
//                    staticRC.attack(tile.getMapLocation(), isSecondary);
//                    return;
//                }
                if(bestTile == null || (staticRC.canSenseRobotAtLocation(tile.getMapLocation()) && !staticRC.canSenseRobotAtLocation(bestTile.getMapLocation()))) {
                    bestTile = tile;
                    isSecondary = Utilities.getColorFromOriginPattern(tile.getMapLocation(), staticRC.getResourcePattern());
                }

            }
//            else if(tile.getPaint().isAlly() && tile.isPassable() && (closestUnclaimedRuin == null || !tile.getMapLocation().isWithinDistanceSquared(closestUnclaimedRuin, 8))) {
//                if((closestCustomPattern == null || !tile.getMapLocation().isWithinDistanceSquared(closestCustomPattern, 8)) && tile.getPaint().isSecondary() != Utilities.getColorFromOriginPattern(tile.getMapLocation(), staticRC.getResourcePattern())) {
//                    bestTile = tile;
//                    isSecondary = Utilities.getColorFromOriginPattern(tile.getMapLocation(), staticRC.getResourcePattern());
//                }
//            }
        }
        if(bestTile != null && staticRC.canAttack(bestTile.getMapLocation())) {
            //if(state == SoldierState.RuinBuilding && bestTile.getPaint().isAlly()) return;
            staticRC.attack(bestTile.getMapLocation(), isSecondary);
        }
    }

    //checks whether the 5x5 area around a location is empty of obstacles - currently also includes enemy paint as an obstacle
    public static boolean validateLocation(MapLocation loc) throws GameActionException {
        // int price = Clock.getBytecodesLeft();
        if(!staticRC.getLocation().isWithinDistanceSquared(loc, 4)) return false;
        MapInfo[] tiles = staticRC.senseNearbyMapInfos(loc, 8);
        if(tiles.length < 25) {
            return false;
        }
//        for(int i = 0; i < 25; i++) {
//            if(!tiles[i].isPassable() || tiles[i].getPaint().isEnemy()) return false;
//        }
        if (!tiles[0].isPassable() || tiles[0].getPaint().isEnemy()) return false;
        if (!tiles[1].isPassable() || tiles[1].getPaint().isEnemy()) return false;
        if (!tiles[2].isPassable() || tiles[2].getPaint().isEnemy()) return false;
        if (!tiles[3].isPassable() || tiles[3].getPaint().isEnemy()) return false;
        if (!tiles[4].isPassable() || tiles[4].getPaint().isEnemy()) return false;
        if (!tiles[5].isPassable() || tiles[5].getPaint().isEnemy()) return false;
        if (!tiles[6].isPassable() || tiles[6].getPaint().isEnemy()) return false;
        if (!tiles[7].isPassable() || tiles[7].getPaint().isEnemy()) return false;
        if (!tiles[8].isPassable() || tiles[8].getPaint().isEnemy()) return false;
        if (!tiles[9].isPassable() || tiles[9].getPaint().isEnemy()) return false;
        if (!tiles[10].isPassable() || tiles[10].getPaint().isEnemy()) return false;
        if (!tiles[11].isPassable() || tiles[11].getPaint().isEnemy()) return false;
        if (!tiles[12].isPassable() || tiles[12].getPaint().isEnemy()) return false;
        if (!tiles[13].isPassable() || tiles[13].getPaint().isEnemy()) return false;
        if (!tiles[14].isPassable() || tiles[14].getPaint().isEnemy()) return false;
        if (!tiles[15].isPassable() || tiles[15].getPaint().isEnemy()) return false;
        if (!tiles[16].isPassable() || tiles[16].getPaint().isEnemy()) return false;
        if (!tiles[17].isPassable() || tiles[17].getPaint().isEnemy()) return false;
        if (!tiles[18].isPassable() || tiles[18].getPaint().isEnemy()) return false;
        if (!tiles[19].isPassable() || tiles[19].getPaint().isEnemy()) return false;
        if (!tiles[20].isPassable() || tiles[20].getPaint().isEnemy()) return false;
        if (!tiles[21].isPassable() || tiles[21].getPaint().isEnemy()) return false;
        if (!tiles[22].isPassable() || tiles[22].getPaint().isEnemy()) return false;
        if (!tiles[23].isPassable() || tiles[23].getPaint().isEnemy()) return false;
        if (!tiles[24].isPassable() || tiles[24].getPaint().isEnemy()) return false;
       // System.out.println("normal price:" + (price - Clock.getBytecodesLeft()));
        return true;
    }
    //checks whether the 5x5 area around a location is empty of obstacles
    public static boolean validatePlacement(MapLocation loc) throws GameActionException {
        MapInfo[] tiles = staticRC.senseNearbyMapInfos(loc, 8);
        if(!farFromEdge(loc)) {
            //invalidResourceCenters.add(loc);
            invalidResourceCenters[loc.x][loc.y] = true;
            //invalidResourceCenters.setBit(loc, true);
            return false;
        }
        //if(tiles.length < 25) return false;
        for (MapInfo tile : tiles) {
            if (!tile.isPassable() || tile.getMark().isAlly()){
                //invalidResourceCenters.add(loc);
                invalidResourceCenters[loc.x][loc.y] = true;
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
        MapInfo[] tiles = staticRC.senseNearbyMapInfos(loc, 8);
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
            if(!staticRC.canSenseRobotAtLocation(ruin) &&
                    staticRC.getLocation().isWithinDistanceSquared(ruin, UnitType.SOLDIER.actionRadiusSquared))
            {
                if(staticRC.canCompleteTowerPattern(UnitType.LEVEL_ONE_DEFENSE_TOWER, ruin))
                {
                    staticRC.completeTowerPattern(UnitType.LEVEL_ONE_DEFENSE_TOWER, ruin);
                    return true;
                }

                if(staticRC.canCompleteTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, ruin))
                {
                    staticRC.completeTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, ruin);
                    return true;
                }

                if(staticRC.canCompleteTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, ruin))
                {
                    staticRC.completeTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, ruin);
                    return true;
                }
        }
        return false;
    }

    //returns whether the location is far enough from the edge to be a valid resource location center
    public static boolean farFromEdge(MapLocation loc) {
        int x = loc.x;
        int y = loc.y;
        int mapHeight = staticRC.getMapHeight() - 1;
        int mapWidth = staticRC.getMapWidth() - 1;
        return x >= 2 && x <= mapWidth - 2 && y >= 2 && y <= mapHeight - 2;
    }

    static void paintMove(MapLocation target, boolean isSecondary) throws GameActionException
    {
        Direction directionToMove = Pathfinding.bugBFS(target);
        MapLocation futurePosition = staticRC.getLocation().add(directionToMove);
        if(staticRC.canPaint(futurePosition) && staticRC.canAttack(futurePosition) && !staticRC.senseMapInfo(futurePosition).getPaint().isAlly())
        {
            staticRC.attack(futurePosition, isSecondary);
        }
        if(staticRC.canMove(directionToMove))
        {
            staticRC.move(directionToMove);
        }
    }

    static void paintMove(Direction directionToMove) throws GameActionException
    {
        MapLocation futurePosition = staticRC.getLocation().add(directionToMove);
        if(staticRC.canPaint(futurePosition) && staticRC.canAttack(futurePosition))
        {
            staticRC.attack(futurePosition);
        }
        if(staticRC.canMove(directionToMove))
        {
            staticRC.move(directionToMove);
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
            if(ruin.location.distanceSquaredTo(staticRC.getLocation()) < minDist) {
                minDist = ruin.location.distanceSquaredTo(staticRC.getLocation());
                r = ruin;
                if (minDist <= 25) return r.location;
            }
        }
        return (minDist == Integer.MAX_VALUE) ? null : r.location;
    }

    private static MapLocation closestUnclaimedRuin() {
        int minDist = Integer.MAX_VALUE;
        Ruin r = null;
        boolean isOccupied = false;
        for(Ruin ruin : Communication.unclaimedRuins) {
            if(ruin.location.distanceSquaredTo(staticRC.getLocation()) < minDist) {
                minDist = ruin.location.distanceSquaredTo(staticRC.getLocation());
                r = ruin;
                if (minDist <= 25) return r.location;
            }
        }
        return (minDist == Integer.MAX_VALUE) ? null : r.location;
    }
    //returns the closest paint tower that is not currently the filling station or the nearestPaintTower
    private static MapLocation nextNearestPaintTower() throws GameActionException {
        MapLocation curLoc = staticRC.getLocation();
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
        if(claimedRuin != null && claimedRuin.equals(ruin)) return false;
        for(MapInfo tile : staticRC.senseNearbyMapInfos(ruin, 2)) {
            if(tile.getMark().isSecondary()) {
                return staticRC.senseNearbyRobots(ruin, 2, staticRC.getTeam()).length > 0;
            }
        }
        return false;
    }
    //determines the closest wall to this robot
    private static MapLocation findClosestWall() throws GameActionException {
        int curX = staticRC.getLocation().x;
        int mapWidth = staticRC.getMapWidth() - 2;
        int mapWidthHalf = mapWidth / 2;
        int closestX;
        if (curX > mapWidthHalf) {
            closestX = mapWidth - 1;
        }
        else {
            closestX = 1;
        }
        int curY = staticRC.getLocation().y;
        int mapHeight = staticRC.getMapHeight() - 2;
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
        MapLocation curLoc = staticRC.getLocation();
        if(curLoc.x > staticRC.getMapWidth() / 2) {
            if(curLoc.y > staticRC.getMapHeight() / 2) {
                return new MapLocation(staticRC.getMapWidth() - 1, staticRC.getMapHeight() - 1);
            }
            else {
                return new MapLocation(staticRC.getMapWidth() - 1, 0);
            }
        }
        else {
            if(curLoc.y > staticRC.getMapHeight() / 2) {
                return new MapLocation(0, staticRC.getMapHeight() - 1);
            }
            else {
                return new MapLocation(0, 0);
            }
        }
    }
    //finds the closest corner in either the clockwise or counterclockwise direction, given the edge you are on
    public static MapLocation rotateCorner(boolean clockwise) throws GameActionException {
        int curX = staticRC.getLocation().x;
        int mapWidth = staticRC.getMapWidth() - 1;
        int curY = staticRC.getLocation().y;
        int mapHeight = staticRC.getMapHeight() - 1;
        //we aren't even chasing a corner yet - need to return the closest one in the direction we are going
        if(!(curObjective.x == 1 || curObjective.x == mapWidth - 1 && curObjective.y == 1 || curObjective.y == mapHeight - 1)) {
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
//        PaintType paint = staticRC.senseMapInfo(staticRC.getLocation()).getPaint();
//        switch(paint) {
//            case PaintType.ENEMY_SECONDARY, PaintType.ENEMY_PRIMARY -> {
//                MapInfo bestTile = null;
//                for(MapInfo tile : staticRC.senseNearbyMapInfos(staticRC.getLocation(), 2)) {
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
