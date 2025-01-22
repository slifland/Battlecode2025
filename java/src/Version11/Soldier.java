package Version11;

import battlecode.common.*;

import static Version11.RobotPlayer.rng;
import static Version11.Communication.alliedPaintTowers;
import static Version11.RobotPlayer.*;
import java.util.HashSet;
import java.util.Map;

public class Soldier {
    enum SoldierState {
        Explore,        //We have nowhere to go to, let's explore
        Navigate,       //We have a target in mind, let's pursue it - if we end up with no target, explore
        Refill,         //We need paint, let's go back to our own tower
        Tower,          //We are near a tower, let's use micro to efficiently fight them
        RuinBuilding,   //We are near a ruin and we want to paint around it
        Fill
    }

    static SoldierState state = SoldierState.Explore;
    static MapLocation target;
    //static HashSet<MapLocation> completedRuins = new HashSet<>();
    //static MapLocation averageUnfilledLocation;
    static MapLocation closestUnclaimedRuin; //keeps track of the closest unclaimed ruin we know of
    static MapLocation closestUnfilledPatternCenter;
    static MapLocation closestEnemyTower;
    static boolean canSeeEmpty;
    static MapLocation fillingStation;
    static MapLocation claimedRuin = null;
    static int numEnemyTiles;
    static boolean visitingCorner = false;
    static boolean[][] sectors;
    static int sector_sizeX;
    static int sector_sizeY;
    static boolean beenToCorner = false;
    static MapInfo[] tilesNearRuin;
    public static RobotInfo seenEnemyTower;
    static boolean wallHug;
    static boolean clockwise;
    static boolean canFinishRuin = false;
    static int neededToFinish = Integer.MAX_VALUE;
    static MapLocation spawnLocation;
    static boolean[][] invalidResourceCenters;
    static boolean canFinishPattern =false;

    static final int clearContestedRuinsAndWaitingRuins = 50; //how often we should clear this hashSet
    //static MapLocation currentSector;

    public static MapLocation averageEnemyPaint;


    //Constants
    final static int refillThreshold = 20;    //Paint level at which soldiers go to refill
    final static int doneRefillingThreshold = 150;    //Paint level at which soldiers can stop refilling
    //final static int REFRESH_ENEMY_PAINT_AVERAGES = 5;
     static int EXPLORE_FILL_TOWER_THRESHOLD; //determines at what round we will fill indiscriminately while exploring
    static int TURN_TO_NAVIGATE_TO_TOWERS; //indicates at what turn we will prioritize going towards enemy towers
    static int STOP_EXPLORING; //indicates when soldiers will began defaulting to navigate instead of explore
    final static int VALIDATE_RUIN_CLAIM_FREQUENCY = 20; //records how often we will checked if our ruin claim has been thwarted by enemies
    static int TURN_TO_FILL; //turn at which filling becomes allowed
    static int INCENTIVIZE_MONEY_ROUND; //turn at which any time before that soldiers will give a slight weight to building money towers
    static int FORCE_MONEY_ROUND; //turn at which any time before that soldiers will always build money towers
    static int START_RUSHING_TOWER_NUMBER; //indicate at which point we should start rushing because we just have too many towers and therefore prob too many robots

    public static void runSoldier(RobotController rc) throws GameActionException
    {
        if(turnCount == 1){
            clockwise = rng.nextInt(2) == 0;
            initializeMapDependentVariables(rc);
            spawnLocation = rc.getLocation();
            //invalidResourceCenters = new HashSet<MapLocation>();
            invalidResourceCenters = new boolean[rc.getMapWidth()][rc.getMapHeight()];
        }
//        int price = Clock.getBytecodesLeft();
//        invalidResourceCenters.add(new MapLocation(-1, -1));
//        if(invalidResourceCenters.contains(rc.getLocation())) {
//            System.out.println("LOL!");
//        }
//        System.out.println(price - Clock.getBytecodesLeft());

        //attemptCompleteTowerPattern(rc);
        updateInfo(rc);
        //update the state
        updateState(rc);
        //run the state
        switch(state)
        {
            case Explore ->
            {
                rc.setIndicatorString("Exploring");
                explore(rc);
            }
            case Navigate ->
            {
                rc.setIndicatorString("Navigating");
                navigate(rc);
            }
            case Refill ->
            {
                rc.setIndicatorString("Refilling");
                refill(rc);
            }
            case Tower ->
            {
                rc.setIndicatorString("Attacking Tower");
                tower(rc);
            }
            case RuinBuilding ->
            {
                rc.setIndicatorString("Building a Ruin");
                ruinBuilding(rc);
            }
            case Fill -> {
                rc.setIndicatorString("Filling a pattern");
                fill(rc);
            }
        }
        if(closestUnclaimedRuin != null) attemptCompleteTowerPattern(rc, closestUnclaimedRuin);
        if(claimedRuin != null && VALIDATE_RUIN_CLAIM_FREQUENCY % turnCount == 0) {
            validateRuinClaim(rc);
        }
//        if(Clock.getBytecodesLeft() > 3000) {
//            Utilities.updatePaintAverages(rc);
//        }
        //if(claimedRuin != null) rc.setIndicatorString(claimedRuin.toString());

//        if(target == null) rc.setIndicatorString(state.toString());
//        else rc.setIndicatorString(state.toString() + " : " + target.toString());
//        if(rc.isMovementReady() && !rc.senseMapInfo(rc.getLocation()).getPaint().isAlly()){
//            moveToSafety(rc);
//        }
    }

    public static void initializeMapDependentVariables(RobotController rc) throws GameActionException {
        int mapSize = rc.getMapHeight() * rc.getMapWidth();
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
    //initalizes the sector array to be the right size, and full of falses
    private static void initializeSectors(RobotController rc) throws GameActionException{
        int sizeX = 2 + rc.getMapWidth() / 10;
        int sizeY = 2 + rc.getMapHeight() / 10;
        sector_sizeX = sizeX;
        sector_sizeY = sizeY;
        int width = rc.getMapWidth() / sizeX;
        if(rc.getMapWidth() % sizeX != 0) width++;
        int height = rc.getMapHeight() / sizeY;
        if(rc.getMapHeight() % sizeY != 0) height++;
        sectors = new boolean[width][height];
    }

    //returns the coordinates of the sector in an array (width)(height)
    private static int[] getSector(MapLocation m) {

        return new int[] {m.x / sector_sizeX, m.y / sector_sizeY};
    }


    //gets a random location within a given sector
    private static MapLocation getRandomLocationInSector(RobotController rc, int x, int y) {
        MapLocation ran = new MapLocation(x * sector_sizeX + (rc.getMapWidth() / sector_sizeX), y * sector_sizeY + (rc.getMapHeight() / sector_sizeY));
        int i = 0;
        while(!rc.onTheMap(ran)) {
            ran = new MapLocation(x * sector_sizeX + rng.nextInt(rc.getMapWidth() / sector_sizeX), y * sector_sizeY + rng.nextInt(rc.getMapHeight() / sector_sizeY));
            if(i++ > 20) {
                return new MapLocation(rng.nextInt(rc.getMapWidth()), rng.nextInt(rc.getMapHeight()));
            }
        }
        return ran;
    }

    static void updateInfo(RobotController rc) throws GameActionException
    {
        //reset turn by turn variables
        closestUnclaimedRuin = null;
        closestEnemyTower = null;
        averageEnemyPaint = null;
        closestUnfilledPatternCenter = null;
        //closestCustomPattern = null; //tracks the closest center of a custom pattern, so that it doesn't get overridden by attemptFill
//        if(claimedRuin != null && rc.getRoundNum() % VALIDATE_RUIN_CLAIM_FREQUENCY == 0) {
//            validateRuinClaim(rc);
//        }
        closestUnclaimedRuin = closestUnclaimedRuin(rc);
        closestEnemyTower = closestEnemyTower(rc);
        int enemyCount = 0;
        int x = 0;
        int y = 0;
        int resourcePatternDist = Integer.MAX_VALUE;
        int failedPlacementLocations = 0;
        int minDistanceToValidLocation = Integer.MAX_VALUE;
        //int minDistanceToCustomPatternCenter = Integer.MAX_VALUE;
        //do all the tasks which require looping through all nearbyTiles
        for(MapInfo tile : nearbyTiles) {
//            if(knownSymmetry == Symmetry.Unknown) {
//                map[tile.getMapLocation().x][tile.getMapLocation().y] = (tile.isPassable()) ? 1 : (tile.isWall()) ? 2 : 3;
//                if(!tile.isPassable())  Utilities.validateSymmetry(tile.getMapLocation(), tile.hasRuin());
//            }
            MapLocation tileLoc = tile.getMapLocation();
            if(tile.getPaint().isEnemy()) {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            else if(!canSeeEmpty && !tile.getPaint().isAlly()) {
                canSeeEmpty = true;
            }
            if(tile.getMark().isAlly() && !tile.isResourcePatternCenter() && rc.getLocation().distanceSquaredTo(tileLoc) < resourcePatternDist) {
                closestUnfilledPatternCenter = tileLoc;
                resourcePatternDist = rc.getLocation().distanceSquaredTo(tileLoc);
            }
            if(tile.getMark() != PaintType.EMPTY){
                int dist = rc.getLocation().distanceSquaredTo(tileLoc);
                if(dist < minDistanceToValidLocation) {
                    minDistanceToValidLocation = dist;
                }
                if(rc.canCompleteResourcePattern(tile.getMapLocation())) rc.completeResourcePattern(tile.getMapLocation());
//                if(tile.getMark().isSecondary() && dist < minDistanceToCustomPatternCenter) {
//                    minDistanceToCustomPatternCenter = dist;
//                    closestCustomPattern = tileLoc;
//                }
            }
            if(tile.getMark() == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                //if(rc.canCompleteResourcePattern(tileLoc)) rc.completeResourcePattern(tileLoc);
                if(!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(rc, tileLoc)) {
                    int dist = rc.getLocation().distanceSquaredTo(tileLoc);
                    if(dist < minDistanceToValidLocation) {
                        minDistanceToValidLocation = dist;
                    }
                    if(rc.canMark(tile.getMapLocation())) {
                        rc.mark(tile.getMapLocation(), false);
                        closestUnfilledPatternCenter = tile.getMapLocation();
                    }
                }
                else {
                    failedPlacementLocations++;
                    //rc.setIndicatorDot(tileLoc, 255, 255, 255);
                }
                //check if there is a nearby valid placement
//                else if(dir != null){
//                    Direction opposite = dir.opposite();
//                    opposite = opposite.rotateLeft();
//                    for(int i =0; i < 3; i++) {
//                        Direction dir2 = validatePlacement(rc, tileLoc.add(opposite));
//                        if(dir2 == Direction.CENTER){
//                            if(rc.canMark(tile.getMapLocation())) {
//                                rc.mark(tile.getMapLocation(), true);
//                                closestUnfilledPatternCenter = tile.getMapLocation();
//                            }
//                            break;
//                        }
//                        dir = dir.rotateRight();
//                    }
//                }
            }
//            else if(validateLocation(rc, rc.getLocation())) {
//                if(rc.canMark(tile.getMapLocation())) {
//                    rc.mark(tile.getMapLocation(), false);
//                    closestUnfilledPatternCenter = tile.getMapLocation();
//                }
//            }
        }
        if(failedPlacementLocations >= 4 && minDistanceToValidLocation >= 25) {
            if(validatePlacement(rc, rc.getLocation())) {
                if(rc.canMark(rc.getLocation())) {
                    rc.mark(rc.getLocation(), true);
                    closestUnfilledPatternCenter = rc.getLocation();
                    //out.println(rc.getLocation());
                    //rc.resign();
                }
            }
        }
        numEnemyTiles = enemyCount;
        averageEnemyPaint = (enemyCount != 0) ? new MapLocation(x/enemyCount, y/enemyCount) : null;
        if(claimedRuin != null && rc.canSenseLocation(claimedRuin) && rc.canSenseRobotAtLocation(claimedRuin)) {
            claimedRuin = null;
        }
    }

    //checks whether we should still "claim" this ruin
    //if there is an obstacle stopping us from completing it, then we should abandon t
    public static void validateRuinClaim(RobotController rc) throws GameActionException {
        if(rc.getLocation().distanceSquaredTo(claimedRuin) <= 8) {
            for(MapInfo tile : tilesNearRuin) {
                if(tile.getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
        }
    }


    //sets the state for the soldier for this round
    //priorities:
    //1. if we are low on paint, try and refill
    //2. if we see an unclaimed ruin with spots to fill, try and fill around it
    //3. if we see an enemy tower, lets go into micro mode
    //4. if we see a mark indicating an attempt at filling in a resource pattern, lets try and fill it
    //5. if we have a target in mind, navigate there
    //6. just explore
    public static void updateState(RobotController rc) throws GameActionException
    {
        //check if we need to refill on paint
        if((rc.getPaint() <= refillThreshold || (rc.getPaint() <= doneRefillingThreshold && state == SoldierState.Refill)) && !canFinishRuin && !canFinishPattern){
            state = SoldierState.Refill;
            if(fillingStation == null) fillingStation = nextNearestPaintTower(rc);
            return;
        }
        canFinishPattern = false;
        canFinishRuin = false;
        //default to navigate, which defaults to explore if there is nothing to navigate to
        state = (rc.getRoundNum() < STOP_EXPLORING || (state == SoldierState.Explore) || rng.nextInt(20) == 0) ? SoldierState.Explore : SoldierState.Navigate;
        fillingStation = null;
        //check if we see any nearby unclaimed ruins
        if(rc.getNumberTowers() < 25 && closestUnclaimedRuin != null && closestUnclaimedRuin.isWithinDistanceSquared(rc.getLocation(), GameConstants.VISION_RADIUS_SQUARED)  && needsHelp(rc, closestUnclaimedRuin)) {
            state = SoldierState.RuinBuilding;
            return;
        }
        //if we can see an enemy tower, maybe lets worry about that
        if(closestEnemyTower != null && closestEnemyTower.isWithinDistanceSquared(rc.getLocation(), GameConstants.VISION_RADIUS_SQUARED)) {
            state = SoldierState.Tower;
            return;
        }
        if((numEnemyTiles > 7 && enemyRobots.length >= 2)) {
            state = SoldierState.Tower;
            return;
        }

        //check if we see any uncompleted resource patterns marked out
        if(closestUnfilledPatternCenter != null && enemyRobots.length == 0) {
            if((closestUnclaimedRuin == null || closestUnclaimedRuin.distanceSquaredTo(closestUnfilledPatternCenter) > 25 || rc.getNumberTowers() == 25) && validateLocation(rc, closestUnfilledPatternCenter)) {
                if(rc.getRoundNum() > TURN_TO_FILL)state = SoldierState.Fill;
            }
        }
    }

    public static void explore(RobotController rc) throws GameActionException // use symmetry for this method
    {
        if(claimedRuin != null && state != SoldierState.Refill) {
            validateRuinClaim(rc);
            navigate(rc);
        }
        else if((turnCount == 1 && rng.nextInt(5) == 0) || wallHug) {
            wallHug = true;
            //find the closest wall to you, and set that as your current objective
            if(target == null) {
                target = findClosestWall(rc);
            }
            if(rc.getLocation().distanceSquaredTo(target) <= 4) {
                target = rotateCorner(rc, clockwise);
                if(clockwise) rc.setIndicatorString("clockwise");
                else rc.setIndicatorString("reverse");
            }
            else {
                Direction dir = Pathfinding.bugBFS(rc, target);
                if(dir != null && rc.canMove(dir)) {
                    rc.move(dir);
                }
            }

        }
        else {
            if (rc.getRoundNum() % 25 == 0 || target == null || rc.getLocation().distanceSquaredTo(target) < 8) {
                //target = generateExploreLocation(rc);
                target = new MapLocation(rng.nextInt(rc.getMapWidth()), rng.nextInt(rc.getMapHeight()));
            }
            Direction dir = Pathfinding.bugBFS(rc, target);
            //rc.setIndicatorLine(rc.getLocation(), target, 255, 255, 255);
            if (dir != null && rc.canMove(dir)) rc.move(dir);
            //attemptFill(rc);
        }
        if(rc.getNumberTowers() > EXPLORE_FILL_TOWER_THRESHOLD) {
            attemptFill(rc);
        }
    }

    //attempts to navigate to a known good place to be - lets first check unclaimed ruins, then lets go towards enemy towers
    //if we have nothing to do, just explore
    public static void navigate(RobotController rc) throws GameActionException
    {
        if(claimedRuin != null) validateRuinClaim(rc);
        if(claimedRuin != null && rc.getNumberTowers() < 25) {
            Direction dir = Pathfinding.bugBFS(rc, claimedRuin);
            if(dir != null && rc.canMove(dir)) rc.move(dir);
            attemptFill(rc);
        }
        else if (rc.getRoundNum() >= TURN_TO_NAVIGATE_TO_TOWERS && !Communication.enemyTowers.isEmpty()) {
            Direction dir = Pathfinding.bugBFS(rc, closestEnemyTower);
            if(dir != null && rc.canMove(dir)) rc.move(dir);
            attemptFill(rc);
        }
//        else {
//            Symmetry[] possible = Utilities.possibleSymmetry();
//            int sym = rng.nextInt(possible.length);
//            switch(possible[sym]) {
//                case Horizontal:
//                    target = new MapLocation(spawnLocation.x, rc.getMapHeight() - 1 - spawnLocation.y);
//                    break;
//                case Rotational:
//                    target = new MapLocation(rc.getMapWidth() - 1 - spawnLocation.x, rc.getMapHeight() - 1 - spawnLocation.y);
//                    break;
//                case Vertical:
//                    target = new MapLocation(rc.getMapWidth() - 1 - spawnLocation.x, spawnLocation.y);
//                    break;
//            }
//            Direction dir = BFS_7x7.pathfind(rc, target);
//            if(rc.canMove(dir)) rc.move(dir);
//            attemptFill(rc);
//        }
//        else if(!Communication.unclaimedRuins.isEmpty()) {
//            Direction dir = BFS_7x7.pathfind(rc, closestUnclaimedRuin);
//            if(rc.canMove(dir)) rc.move(dir);
//            attemptFill(rc);
//        }
        else {
            explore(rc);
        }
    }

    public static void fill(RobotController rc) throws GameActionException{
        MapInfo[] potentialTiles = rc.senseNearbyMapInfos(closestUnfilledPatternCenter, 8);
        //Micro.patternFillingMicro(rc, closestUnfilledPatternCenter, rc.getResourcePattern(), potentialTiles);
        MapInfo bestTile = null;
        boolean isSecondary = false;
        int neededToFinish = 0;
        //use the origin if the mark is primary, custom if the mark is primary
        boolean markIsSecondary = rc.senseMapInfo(closestUnfilledPatternCenter).getMark().isSecondary();
        int dist = rc.getLocation().distanceSquaredTo(closestUnfilledPatternCenter);
        if (dist > 8) {
            Direction dir = Pathfinding.bugBFS(rc, closestUnfilledPatternCenter);
            if (rc.canMove(dir)) rc.move(dir);
        } else {
            if (rc.senseMapInfo(rc.getLocation()).getPaint() == PaintType.EMPTY) {
                boolean useSecondary = (markIsSecondary) ? Utilities.getColorFromCustomPattern(rc.getLocation(), rc.getResourcePattern(), closestUnfilledPatternCenter) : Utilities.getColorFromOriginPattern(rc.getLocation(), rc.getResourcePattern());
                //rc.attack(rc.getLocation(), Utilities.getColorFromOriginPattern(rc.getLocation(), rc.getResourcePattern()));
                rc.attack(rc.getLocation(), useSecondary);
                if(rc.getPaint() >= 100) return;
                else neededToFinish++;
            }
        }
        for (MapInfo tile : potentialTiles) {
            //boolean shouldBeSecondary = Utilities.getColorFromOriginPattern(tile.getMapLocation(), rc.getResourcePattern());
            boolean shouldBeSecondary = (markIsSecondary) ? Utilities.getColorFromCustomPattern(tile.getMapLocation(), rc.getResourcePattern(), closestUnfilledPatternCenter) : Utilities.getColorFromOriginPattern(tile.getMapLocation(), rc.getResourcePattern());
            if (tile.getPaint() == PaintType.EMPTY && tile.isPassable()) {
                if (rc.canAttack(tile.getMapLocation()) && rc.getPaint() >= 100) {
                    rc.attack(tile.getMapLocation(), shouldBeSecondary);
                    return;
                } else {
                    bestTile = tile;
                    isSecondary = shouldBeSecondary;
                    neededToFinish++;
                }
            } else if (tile.getPaint().isAlly() && tile.isPassable() && tile.getPaint().isSecondary() != shouldBeSecondary) {
                neededToFinish++;
                if((bestTile == null || !bestTile.getPaint().isEnemy())) {
                    bestTile = tile;
                    isSecondary = shouldBeSecondary;
                }
            }
        }
        canFinishPattern = neededToFinish * 5 < rc.getPaint();
        if (bestTile != null) {
            if (rc.canAttack(bestTile.getMapLocation())) rc.attack(bestTile.getMapLocation(), isSecondary);
            else if (rc.isMovementReady()) {
                Direction dir = Pathfinding.bugBFS(rc, bestTile.getMapLocation());
                if (rc.canMove(dir) && rc.getLocation().add(dir).isWithinDistanceSquared(closestUnfilledPatternCenter, 5)) rc.move(dir);
                if (rc.canAttack(bestTile.getMapLocation())) rc.attack(bestTile.getMapLocation(), isSecondary);
            }
        }
    }

    //TODO: implement congestion control and prio q
    public static void refill(RobotController rc) throws GameActionException
    {
        //navigate to the nearest paint tower
        //if that tower is surrounded, navigate to the next nearest
        if(fillingStation == null || (rc.canSenseLocation(fillingStation) && (rc.senseNearbyRobots(fillingStation, 2, rc.getTeam()).length > 3)||( rc.canSenseLocation(fillingStation) && !rc.canSenseRobotAtLocation(fillingStation)))) {
            fillingStation = nextNearestPaintTower(rc);
            if(fillingStation == null){
                if(seenEnemyTower != null) {
                    if(rc.canAttack(seenEnemyTower.getLocation())) rc.attack(seenEnemyTower.getLocation());
                    Direction dir = Micro.runMicro(rc, true);
                    if(rc.canMove(dir)) rc.move(dir);
                    if(rc.canAttack(seenEnemyTower.getLocation())) rc.attack(seenEnemyTower.getLocation());
                }
                else{
                    explore(rc);
                }
                return;

            }
        }
        if(rc.getLocation().isWithinDistanceSquared(fillingStation, 9)) {
            Micro.refillingMicro(rc, fillingStation);
        }
        else {
            if(rc.isMovementReady()) {
                Direction dir = Pathfinding.bugBFS(rc, fillingStation);
                if(rc.canMove(dir)) rc.move(dir);
            }
        }
    }

    public static void tower(RobotController rc) throws GameActionException
    {
        if(rc.canAttack(closestEnemyTower)) {
            rc.attack(closestEnemyTower);
        }
        if(rc.isMovementReady()) {
            Direction dir = Micro.runMicro(rc, (((allyRobots.length - enemyRobots.length > 4) || allyRobots.length > 10) && rc.getHealth() > 25) || rc.getNumberTowers() >= START_RUSHING_TOWER_NUMBER);
            if(rc.canMove(dir)) rc.move(dir);
        }
        if(rc.canAttack(closestEnemyTower)) {
            rc.attack(closestEnemyTower);
        }
        attemptFill(rc);
    }

    public static void ruinBuilding(RobotController rc) throws GameActionException {

        if (attemptCompleteTowerPattern(rc, closestUnclaimedRuin)) {
            claimedRuin = null;
            return;
        }
        int dist = rc.getLocation().distanceSquaredTo(closestUnclaimedRuin);
        if(claimedRuin == null) {
            if (dist > 8) {
                if (rc.senseNearbyRobots(closestUnclaimedRuin, 8, rc.getTeam()).length == 0) {
                    claimedRuin = closestUnclaimedRuin;
                }
            } else {
                if (rc.senseNearbyRobots(closestUnclaimedRuin, 8, rc.getTeam()).length == 1) {
                    claimedRuin = closestUnclaimedRuin;
                }
            }
        }
        else {
                if(dist > 2) {
                    if (rc.senseNearbyRobots(closestUnclaimedRuin, 2, rc.getTeam()).length > 0) {
                        claimedRuin = null;
                    }
                }
                else {
                    if (rc.senseNearbyRobots(closestUnclaimedRuin, 2, rc.getTeam()).length > 1) {
                        claimedRuin = null;
                    }
                }
        }
        boolean[][] desiredPattern = Utilities.inferPatternFromExistingSpots(rc, closestUnclaimedRuin, tilesNearRuin);
        if (desiredPattern == null) {
            desiredPattern = chooseDesiredPattern(rc, closestUnclaimedRuin);
        }
        Micro.ruinBuildingMicro(rc, closestUnclaimedRuin, desiredPattern, tilesNearRuin); //finds the best spaces to move and attack, and acts on that
        if (attemptCompleteTowerPattern(rc, closestUnclaimedRuin)) {
            claimedRuin = null;
            return;
        }
        if (rc.isActionReady() && (neededToFinish * 5) + 5 < rc.getPaint()) attemptFill(rc);
    }

    //where we decide what kind of pattern to use
    //logic currently copied from version 8
    public static boolean[][] chooseDesiredPattern(RobotController rc, MapLocation m) throws GameActionException {
        //if(enemyRobots.length >= 1) return rc.getTowerPattern(UnitType.LEVEL_ONE_DEFENSE_TOWER);
        int ranNum = (rc.getRoundNum() < FORCE_MONEY_ROUND) ? 0 : rng.nextInt(2);
        if(ranNum == 0) {
            return (rc.getMapHeight() * rc.getMapWidth() <= 750 && rc.getRoundNum() > 100) ? rc.getTowerPattern(UnitType.LEVEL_ONE_DEFENSE_TOWER) : rc.getTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER);
        }
        else {
            return rc.getTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER);
        }
    }

    //attempt fill assumes we have already moved - move, then paint
    //TODO: instead make this a method that will heavily prioritize filling around pattern centers
    public static void attemptFill(RobotController rc) throws GameActionException
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
    public static boolean validateLocation(RobotController rc, MapLocation loc) throws GameActionException {
       // int price = Clock.getBytecodesLeft();
        MapInfo[] tiles = rc.senseNearbyMapInfos(loc, 8);
        if(tiles.length < 25) return false;
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
    public static boolean validatePlacement(RobotController rc, MapLocation loc) throws GameActionException {
        MapInfo[] tiles = rc.senseNearbyMapInfos(loc, 8);
        if(!farFromEdge(rc, loc)) {
            //invalidResourceCenters.add(loc);
            invalidResourceCenters[loc.x][loc.y] = true;
            return false;
        }
        //if(tiles.length < 25) return false;
        for (MapInfo tile : tiles) {
            if (!tile.isPassable() || tile.getMark().isAlly()){
                //invalidResourceCenters.add(loc);
                invalidResourceCenters[loc.x][loc.y] = true;
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
    public static boolean validateLocationTest(RobotController rc, MapLocation loc) throws GameActionException {
        int price = Clock.getBytecodesLeft();
        MapInfo[] tiles = rc.senseNearbyMapInfos(loc, 8);
        if(!farFromEdge(rc, loc)) return false;
        for(MapInfo tile : tiles) {
            if(!tile.isPassable() || tile.getPaint().isEnemy()) return false;
        }
        //out.println("test price: " + (price - Clock.getBytecodesLeft()));
        return true;
    }

    public static boolean attemptCompleteTowerPattern(RobotController rc, MapLocation ruin) throws GameActionException
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
    public static boolean farFromEdge(RobotController rc, MapLocation loc) {
        int x = loc.x;
        int y = loc.y;
        int mapHeight = rc.getMapHeight() - 1;
        int mapWidth = rc.getMapWidth() - 1;
        return x >= 2 && x <= mapWidth - 2 && y >= 2 && y <= mapHeight - 2;
    }



    //generates a random location of a nearby sector you haven't explored
    static MapLocation generateExploreLocation(RobotController rc)
    {
        int xOffset = (rng.nextInt(2) == 0 ) ? rng.nextInt(3) : -rng.nextInt(3);
        int yOffset = (rng.nextInt(2) == 0) ?  rng.nextInt(3) : -rng.nextInt(3);
        int[] sector = getSector(rc.getLocation());
        int x = sector[0];
        int y = sector[1];
        int i = 0;
        while(x + xOffset < 0 || x + xOffset >= sectors.length || y + yOffset < 0 || y + yOffset >= sectors[0].length || sectors[x + xOffset][y + yOffset]) {
            xOffset = rng.nextInt(3) - 1;
            yOffset = rng.nextInt(3) - 1;
            if(i++ > 20) {
                return new MapLocation(rng.nextInt(rc.getMapWidth()), rng.nextInt(rc.getMapHeight()));
            }
        }
        return getRandomLocationInSector(rc, x + xOffset, y + yOffset);
    }

    //sets the relevant sector in hasTraveled to true if we are in a sector we haven't seen before
//    public static void recordSector(RobotController rc) {
//        int[] sector = getSector(rc.getLocation());
//        sectors[sector[0]][sector[1]] = true;
//    }

    static void paintMove(RobotController rc, MapLocation target, boolean isSecondary) throws GameActionException
    {
        Direction directionToMove = Pathfinding.bugBFS(rc, target);
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

    static void paintMove(RobotController rc, Direction directionToMove) throws GameActionException
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
    public static boolean isSafeFromTower(RobotController rc, MapLocation loc) {
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
    public static MapLocation closestEnemyTower(RobotController rc) {
        int minDist = Integer.MAX_VALUE;
        Ruin r = null;
        for(Ruin ruin : Communication.enemyTowers) {
            if(ruin.location.distanceSquaredTo(rc.getLocation()) < minDist) {
                minDist = ruin.location.distanceSquaredTo(rc.getLocation());
                r = ruin;
                if (minDist <= 25) return r.location;
            }
        }
        return (minDist == Integer.MAX_VALUE) ? null : r.location;
    }

    private static MapLocation closestUnclaimedRuin(RobotController rc) {
        int minDist = Integer.MAX_VALUE;
        Ruin r = null;
        boolean isOccupied = false;
        for(Ruin ruin : Communication.unclaimedRuins) {
            if(ruin.location.distanceSquaredTo(rc.getLocation()) < minDist) {
                minDist = ruin.location.distanceSquaredTo(rc.getLocation());
                r = ruin;
                if (minDist <= 25) return r.location;
            }
        }
        return (minDist == Integer.MAX_VALUE) ? null : r.location;
    }
    //returns the closest paint tower that is not currently the filling station or the nearestPaintTower
    private static MapLocation nextNearestPaintTower(RobotController rc) throws GameActionException {
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

    //checks whether a ruin is claimed, but also if it needs help
    public static boolean needsHelp(RobotController rc, MapLocation ruin) throws GameActionException {
        neededToFinish = 0;
        canFinishRuin = false;
        tilesNearRuin = rc.senseNearbyMapInfos(ruin, 8);
        boolean hasEmpty = false;
        boolean[][] pattern = Utilities.inferPatternFromExistingSpots(rc, ruin, tilesNearRuin);
        for(MapInfo tile : tilesNearRuin) {
            if(!tile.isPassable()) continue;
            if(tile.getPaint().isEnemy()){
                return false;
            }
            else if(!hasEmpty) {
                if (tile.getPaint() == PaintType.EMPTY) {
                    neededToFinish++;
                    hasEmpty = true;
                }
                if (pattern != null && tile.getPaint().isAlly() && Utilities.getColorFromCustomPattern(tile.getMapLocation(), pattern, ruin) != tile.getPaint().isSecondary()) {
                    neededToFinish++;
                    hasEmpty = true;
                }
            }
        }
        if(neededToFinish * 5 < rc.getPaint()) canFinishRuin = true;
        if(hasEmpty || (claimedRuin != null && claimedRuin.equals(ruin))) return true;
        //int dist = rc.getLocation().distanceSquaredTo(ruin);
        //if((dist <= 8 && rc.senseNearbyRobots(ruin, 8, rc.getTeam()).length == 1) || (dist >8 && rc.senseNearbyRobots(ruin, 8, rc.getTeam()).length == 0)) return true;
        if(rc.senseNearbyRobots(ruin, 8, rc.getTeam()).length == 0) return true;
        return false;
    }

    //returns whether a ruin is completely tiled in, and just waiting for completion
    public static boolean isOccupied(RobotController rc, MapLocation ruin) throws GameActionException {
        if(claimedRuin != null && claimedRuin.equals(ruin)) return false;
        for(MapInfo tile : rc.senseNearbyMapInfos(ruin, 2)) {
            if(tile.getMark().isSecondary()) {
                return rc.senseNearbyRobots(ruin, 2, rc.getTeam()).length > 0;
            }
        }
        return false;
    }
    //determines the closest wall to this robot
    private static MapLocation findClosestWall(RobotController rc) throws GameActionException {
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
    public static MapLocation nearestCorner(RobotController rc) throws GameActionException {
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
    public static MapLocation rotateCorner(RobotController rc, boolean clockwise) throws GameActionException {
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
//    public static void moveToSafety(RobotController rc) throws GameActionException{
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
