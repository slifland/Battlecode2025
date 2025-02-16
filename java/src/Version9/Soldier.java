package Version9;

import battlecode.common.*;

import static Version9.RobotPlayer.rng;
import static Version9.Communication.alliedPaintTowers;
import static Version9.RobotPlayer.*;

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
    //static MapLocation currentSector;

    public static MapLocation averageEnemyPaint;


    //Constants
    final static int refillThreshold = 20;    //Paint level at which soldiers go to refill
    final static int doneRefillingThreshold = 150;    //Paint level at which soldiers can stop refilling
    final static int attackThreshold = 2;     //Number of soldiers we need to rush a tower
    final static int chipThreshold = 0; //Build money towers if we have fewer chips than the threshold
    final static int congestionThreshold = 2;
    public static final int randomRadius = 100;
    final static int SECTOR_CHECK = 2; //indicates how often we will check if we are in a previously unseen sector
    final static int TURN_TO_NAVIGATE_TO_TOWERS = 200; //indicates at what turn we will prioritize going towards enemy towers
    final static int STOP_EXPLORING = 200; //indicates when soldiers will began defaulting to navigate instead of explore
    final static int VALIDATE_RUIN_CLAIM_FREQUENCY = 10; //records how often we will checked if our ruin claim has been thwarted by enemies
    final static int TURN_TO_FILL = 20; //turn at which filling becomes allowed

    public static void runSoldier(RobotController rc) throws GameActionException
    {
        if(turnCount == 1) clockwise = rng.nextInt(2) == 0;
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
        //if(claimedRuin != null) rc.setIndicatorString(claimedRuin.toString());

//        if(target == null) rc.setIndicatorString(state.toString());
//        else rc.setIndicatorString(state.toString() + " : " + target.toString());
//        if(rc.isMovementReady() && !rc.senseMapInfo(rc.getLocation()).getPaint().isAlly()){
//            moveToSafety(rc);
//        }
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
//        if(rc.getRoundNum() % SECTOR_CHECK == 0) {
//            recordSector(rc);
//        }

        closestUnclaimedRuin = closestUnclaimedRuin(rc);
        closestEnemyTower = closestEnemyTower(rc);

        int enemyCount = 0;
        int x = 0;
        int y = 0;
        int resourcePatternDist = Integer.MAX_VALUE;
        //do all the tasks which require looping through all nearbyTiles
        for(MapInfo tile : nearbyTiles) {
            MapLocation tileLoc = tile.getMapLocation();
            if(tile.getPaint().isEnemy()) {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            else if(!canSeeEmpty && !tile.getPaint().isAlly()) {
                canSeeEmpty = true;
            }
            if(tile.getMark() == PaintType.ALLY_PRIMARY && !tile.isResourcePatternCenter() && rc.getLocation().distanceSquaredTo(tileLoc) < resourcePatternDist) {
                closestUnfilledPatternCenter = tileLoc;
                resourcePatternDist = rc.getLocation().distanceSquaredTo(tileLoc);
            }
            if(tile.getMark() != PaintType.EMPTY && rc.canCompleteResourcePattern(tile.getMapLocation())) rc.completeResourcePattern(tile.getMapLocation());
            if(tile.getMark() == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                //if(rc.canCompleteResourcePattern(tileLoc)) rc.completeResourcePattern(tileLoc);
                if(validateLocation(rc, tile.getMapLocation())) {
                    if(rc.canMark(tile.getMapLocation())) rc.mark(tile.getMapLocation(), false);
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
            MapInfo[] tilesNearRuin = rc.senseNearbyMapInfos(claimedRuin, 8);
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
        if((rc.getPaint() <= refillThreshold || (rc.getPaint() <= doneRefillingThreshold && state == SoldierState.Refill)) && !canFinishRuin){
            state = SoldierState.Refill;
            if(fillingStation == null) fillingStation = nextNearestPaintTower(rc);
            return;
        }
        //default to navigate, which defaults to explore if there is nothing to navigate to
        state = (rc.getRoundNum() < STOP_EXPLORING) ? SoldierState.Explore : SoldierState.Navigate;
        fillingStation = null;
        //if we can see an enemy tower, maybe lets worry about that
        if(closestEnemyTower != null && closestEnemyTower.isWithinDistanceSquared(rc.getLocation(), GameConstants.VISION_RADIUS_SQUARED)) {
            state = SoldierState.Tower;
            return;
        }
        //check if we see any nearby unclaimed ruins
        if(closestUnclaimedRuin != null && closestUnclaimedRuin.isWithinDistanceSquared(rc.getLocation(), GameConstants.VISION_RADIUS_SQUARED) && needsHelp(rc, closestUnclaimedRuin) && rc.getNumberTowers() < 25) {
            state = SoldierState.RuinBuilding;
            return;
        }
        canFinishRuin = false;
        if((numEnemyTiles > 7 && enemyRobots.length >= 2)) {
            state = SoldierState.Tower;
            return;
        }

        //check if we see any uncompleted resource patterns marked out
        if(closestUnfilledPatternCenter != null && rc.senseNearbyRobots(closestUnfilledPatternCenter, 8, rc.getTeam()).length <= 1) {
            if((closestUnclaimedRuin == null || closestUnclaimedRuin.distanceSquaredTo(closestUnfilledPatternCenter) > 25) && validateLocation(rc, closestUnfilledPatternCenter)){
                if(rc.getRoundNum() > TURN_TO_FILL)state = SoldierState.Fill;
            }
        }
    }

    public static void explore(RobotController rc) throws GameActionException // use symmetry for this method
    {
        if(claimedRuin != null && state != SoldierState.Refill) {
            validateRuinClaim(rc);
            navigate(rc);
            return;
        }
        if((turnCount == 1 && rng.nextInt(6) == 0) || wallHug) {
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
                Direction dir = Version8.BFS_7x7.pathfind(rc, target);
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
            Direction dir = BFS_7x7.pathfind(rc, target);
            //rc.setIndicatorLine(rc.getLocation(), target, 255, 255, 255);
            if (rc.canMove(dir)) rc.move(dir);
            attemptFill(rc);
        }
    }

    //attempts to navigate to a known good place to be - lets first check unclaimed ruins, then lets go towards enemy towers
    //if we have nothing to do, just explore
    public static void navigate(RobotController rc) throws GameActionException
    {
        if(claimedRuin != null) validateRuinClaim(rc);
        if(claimedRuin != null) {
            Direction dir = BFS_7x7.pathfind(rc, claimedRuin);
            if(rc.canMove(dir)) rc.move(dir);
            attemptFill(rc);
        }
        else if (rc.getRoundNum() >= TURN_TO_NAVIGATE_TO_TOWERS && !Communication.enemyTowers.isEmpty()) {
            Direction dir = BFS_7x7.pathfind(rc, closestEnemyTower(rc));
            if(rc.canMove(dir)) rc.move(dir);
            attemptFill(rc);
        }
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
        MapInfo bestTile = null;
        boolean isSecondary = false;
        int dist = rc.getLocation().distanceSquaredTo(closestUnfilledPatternCenter);
        if(dist >= 8) {
            Direction dir = BFS_7x7.pathfind(rc, closestUnfilledPatternCenter);
            if(rc.canMove(dir)) rc.move(dir);
        }
        else {
            if(rc.senseMapInfo(rc.getLocation()).getPaint() == PaintType.EMPTY) {
                rc.attack(rc.getLocation(), Utilities.getColorFromOriginPattern(rc.getLocation(), rc.getResourcePattern()));
                return;
            }
        }
        for(MapInfo tile : potentialTiles) {
            boolean shouldBeSecondary = Utilities.getColorFromOriginPattern(tile.getMapLocation(), rc.getResourcePattern());
            if(tile.getPaint() == PaintType.EMPTY && tile.isPassable()) {
                if(rc.canAttack(tile.getMapLocation())) {
                    rc.attack(tile.getMapLocation(), shouldBeSecondary);
                    return;
                }
                else {
                    bestTile = tile;
                    isSecondary = shouldBeSecondary;
                }
            }
            else if((bestTile == null || !bestTile.getPaint().isEnemy()) && tile.getPaint().isAlly() && tile.isPassable() && tile.getPaint().isSecondary() != shouldBeSecondary) {
                bestTile = tile;
                isSecondary = shouldBeSecondary;
            }
        }
        if(bestTile != null) {
            if(rc.canAttack(bestTile.getMapLocation())) rc.attack(bestTile.getMapLocation(), isSecondary);
            else if(rc.isMovementReady()) {
                Direction dir = BFS_7x7.pathfind(rc, bestTile.getMapLocation());
                if(rc.canMove(dir)) rc.move(dir);
                if(rc.canAttack(bestTile.getMapLocation())) rc.attack(bestTile.getMapLocation(), isSecondary);
            }
        }
//        else if (rc.isMovementReady() && rc.senseNearbyRobots(closestUnfilledPatternCenter, 2, rc.getTeam()).length == 0) {
//            Direction dir = BFS_7x7.pathfind(rc, closestUnfilledPatternCenter);
//            if(rc.canMove(dir)) rc.move(dir);
//            Utilities.attemptCompleteResourcePattern(rc, closestUnfilledPatternCenter);
//        }
    }

    //TODO: implement congestion control and prio q
    public static void refill(RobotController rc) throws GameActionException
    {
        //navigate to the nearest paint tower
        //if that tower is surrounded, navigate to the next nearest
        if(fillingStation == null || (rc.canSenseLocation(fillingStation) && (rc.senseNearbyRobots(fillingStation, 2, rc.getTeam()).length > 3 )||( rc.canSenseLocation(fillingStation) && !rc.canSenseRobotAtLocation(fillingStation)))) {
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
        if(rc.getLocation().isAdjacentTo(fillingStation)) {
            RobotInfo[] adjacentAllies = rc.senseNearbyRobots(2, rc.getTeam());
            MapLocation[] fillingSpots = rc.getAllLocationsWithinRadiusSquared(fillingStation, 2);
            for(RobotInfo r : adjacentAllies) {
                if(!r.getType().isTowerType() && rc.getLocation().isAdjacentTo(r.getLocation())) {
                    for(MapLocation spot : fillingSpots) {
                        if(rc.canMove(rc.getLocation().directionTo(spot)) && !spot.isAdjacentTo(r.getLocation())) {
                            rc.move(rc.getLocation().directionTo(spot));
                            break;
                        }
                    }
                }
            }
            if(rc.canTransferPaint(fillingStation, Math.max(rc.getPaint() - rc.getType().paintCapacity, rc.senseRobotAtLocation(fillingStation).paintAmount * -1))){
                rc.transferPaint(fillingStation, Math.max(rc.getPaint() - rc.getType().paintCapacity, rc.senseRobotAtLocation(fillingStation).paintAmount * -1));
            }
        }
        else {
            if(rc.isMovementReady()) {
                Direction dir = BFS_7x7.pathfind(rc, fillingStation);
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
            Direction dir = Micro.runMicro(rc, allyRobots.length - enemyRobots.length > 5 && rc.getHealth() > 25);
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
        claimedRuin = closestUnclaimedRuin;
        boolean[][] desiredPattern = Utilities.inferPatternFromExistingSpots(rc, closestUnclaimedRuin, tilesNearRuin);
        if (desiredPattern == null) {
            desiredPattern = chooseDesiredPattern(rc, closestUnclaimedRuin);
        }
        canFinishRuin = false;
        int neededToFinish = 0;
        if(rc.getPaint() <= 50) {
            for (MapInfo tile : tilesNearRuin) {
                if (tile.getPaint() == PaintType.EMPTY || tile.getPaint().isSecondary() != Utilities.getColorFromCustomPattern(tile.getMapLocation(), desiredPattern, closestUnclaimedRuin)) neededToFinish++;
            }
            if(neededToFinish * 5 < rc.getPaint()) canFinishRuin = true;
        }
        Micro.ruinBuildingMicro(rc, closestUnclaimedRuin, desiredPattern, tilesNearRuin); //finds the best spaces to move and attack, and acts on that
        if (attemptCompleteTowerPattern(rc, closestUnclaimedRuin)) {
            claimedRuin = null;
            return;
        }
        if (rc.isActionReady()) attemptFill(rc);
    }

    //where we decide what kind of pattern to use
    //logic currently copied from version 8
    public static boolean[][] chooseDesiredPattern(RobotController rc, MapLocation m) throws GameActionException {

            int ranNum = (rc.getRoundNum() < 100) ? 0 : RobotPlayer.rng.nextInt(2);
            if(ranNum <= 1) {
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
                isSecondary = Utilities.getColorFromOriginPattern(tile.getMapLocation(), rc.getResourcePattern());
                if(rc.canAttack(tile.getMapLocation())) {
                    rc.attack(tile.getMapLocation(), isSecondary);
                    return;
                }
            }
            else if(tile.getPaint().isAlly() && tile.isPassable() && (closestUnclaimedRuin == null || !tile.getMapLocation().isWithinDistanceSquared(closestUnclaimedRuin, 8))) {
                if(tile.getPaint().isSecondary() != Utilities.getColorFromOriginPattern(tile.getMapLocation(), rc.getResourcePattern())) {
                    bestTile = tile;
                    isSecondary = Utilities.getColorFromOriginPattern(tile.getMapLocation(), rc.getResourcePattern());
                }
            }
        }
        if(bestTile != null && rc.canAttack(bestTile.getMapLocation())) {
            if(state == SoldierState.RuinBuilding && bestTile.getPaint().isAlly()) return;
            rc.attack(bestTile.getMapLocation(), isSecondary);
        }
    }

    //checks whether the 5x5 area around a location is empty of obstacles - currently also includes enemy paint as an obstacle
    public static boolean validateLocation(RobotController rc, MapLocation loc) throws GameActionException {
        MapInfo[] tiles = rc.senseNearbyMapInfos(loc, 8);
        if(tiles.length < 25) return false;
//        for(MapInfo tile : tiles) {
//            if(!tile.isPassable() || tile.getPaint().isEnemy()) return false;
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
        Direction directionToMove = BFS_7x7.pathfind(rc, target);
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
        //if(claimedRuin != null && claimedRuin.equals(ruin)) return true;
        boolean hasEmpty = false;
        tilesNearRuin = rc.senseNearbyMapInfos(ruin, 8);
        boolean[][] pattern = Utilities.inferPatternFromExistingSpots(rc, ruin, tilesNearRuin);
        for(MapInfo tile : tilesNearRuin) {
            if(!tile.isPassable()) continue;
            if(tile.getPaint() == PaintType.EMPTY) {
                hasEmpty = true;
            }
            if(tile.getPaint().isEnemy()){
                return false;
            }
            if(pattern != null && tile.getPaint().isAlly() && Utilities.getColorFromCustomPattern(tile.getMapLocation(), pattern, ruin) != tile.getPaint().isSecondary()) {
                hasEmpty = true;
            }
        }
        if(hasEmpty) return true;
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
            closestX = mapWidth;
        }
        else {
            closestX = 0;
        }
        int curY = rc.getLocation().y;
        int mapHeight = rc.getMapHeight() - 2;
        int mapHeightHalf = mapHeight / 2;
        int closestY;
        if (curY > mapHeightHalf) {
            closestY = mapHeight;
        }
        else {
            closestY = 0;
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
        if(!(target.x == 0 || target.x == mapWidth && target.y == 0 || target.y == mapHeight)) {
            int newX;
            int newY;
            //top edge
            if(mapHeight - curY <= 4) {
                if(clockwise) {
                    newX = mapWidth;
                }
                else {
                    newX = 0;
                }
                newY = mapHeight;
            }
            //right edge
            else if(mapWidth - curX <= 4) {
                if(clockwise) {
                    newY = 0;
                }
                else {
                    newY = mapHeight;
                }
                newX = mapWidth;
            }
            //bottom edge
            else if (curY <= 4) {
                if(clockwise) {
                    newX = 0;
                }
                else {
                    newX = mapWidth;
                }
                newY = 0;
            }
            // left edge
            else {
                if(clockwise) {
                    newY = mapHeight;
                }
                else {
                    newY = 0;
                }
                newX = 0;
            }
            return new MapLocation(newX, newY);
        }
        //bottom corner -> clockwise needs to go to top left, CCW needs to go to bottom right
        if(curX < 5 && curY < 5) {
            if(clockwise) {
                return new MapLocation(0, mapHeight);
            }
            else {
                return new MapLocation(mapWidth, 0);
            }
        }
        //bottom right corner
        else if(curX > 5 && curY < 5) {
            if(clockwise) {
                return new MapLocation(0, 0);
            }
            else {
                return new MapLocation(mapWidth, mapHeight);
            }
        }
        //top right
        else if(curX > 5 && curY > 5) {
            if(clockwise) {
                return new MapLocation(mapWidth, 0);
            }
            else {
                return new MapLocation(0, mapHeight);
            }
        }
        //top left
        else {
            if(clockwise) {
                return new MapLocation(mapWidth, mapHeight);
            }
            else {
                return new MapLocation(0, 0);
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