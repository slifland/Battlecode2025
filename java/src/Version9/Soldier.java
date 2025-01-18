package Version9;

import battlecode.common.*;

import java.util.HashSet;

import static Version9.Communication.unclaimedRuins;
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

    static SoldierState state = SoldierState.Navigate;
    static MapLocation target;
    static HashSet<MapLocation> completedRuins = new HashSet<>();
    static MapLocation averageUnfilledLocation;
    static MapLocation closestUnclaimedRuin; //keeps track of the closest unclaimed ruin we know of
    static MapLocation closestUnfilledPatternCenter;
    static MapLocation closestEnemyTower;
    static boolean canSeeEmpty;
    static MapLocation fillingStation;
    static MapLocation claimedRuin = null;
    static int numEnemyTiles;
    static boolean visitingCorner = false;
    //static MapLocation currentSector;

    public static MapLocation averageEnemyPaint;


    //Constants
    final static int refillThreshold = 30;    //Paint level at which soldiers go to refill
    final static int doneRefillingThreshold = 150;    //Paint level at which soldiers can stop refilling
    final static int attackThreshold = 2;     //Number of soldiers we need to rush a tower
    final static int chipThreshold = 0; //Build money towers if we have fewer chips than the threshold
    final static int congestionThreshold = 2;
    public static final int randomRadius = 100;
    final static int SECTOR_CHECK = 2; //indicates how often we will check if we are in a previously unseen sector
    final static int TURN_TO_NAVIGATE_TO_TOWERS = 200; //indicates at what turn we will prioritize going towards enemy towers
    final static int STOP_EXPLORING = 200; //indicates when soldiers will began defaulting to navigate instead of explore

    public static void runSoldier(RobotController rc) throws GameActionException
    {
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
//        if(target == null) rc.setIndicatorString(state.toString());
//        else rc.setIndicatorString(state.toString() + " : " + target.toString());
//        if(rc.isMovementReady() && !rc.senseMapInfo(rc.getLocation()).getPaint().isAlly()){
//            moveToSafety(rc);
//        }
    }
    static void updateInfo(RobotController rc) throws GameActionException
    {
        //reset turn by turn variables
        closestUnclaimedRuin = null;
        closestEnemyTower = null;
        averageEnemyPaint = null;
        closestUnfilledPatternCenter = null;
        if(rc.getRoundNum() % SECTOR_CHECK == 0) {
            recordSector(rc);
        }

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
        //default to navigate, which defaults to explore if there is nothing to navigate to
        state = (rc.getRoundNum() < STOP_EXPLORING) ? SoldierState.Explore : SoldierState.Navigate;
        //check if we need to refill on paint
        if(rc.getPaint() <= refillThreshold || (rc.getPaint() <= doneRefillingThreshold && state == SoldierState.Refill)){
            state = SoldierState.Refill;
            if(fillingStation == null) fillingStation = nextNearestPaintTower(rc);
            return;
        }
        if(numEnemyTiles > 7 && enemyRobots.length >= 2 ) {
            state = SoldierState.Tower;
            return;
        }
        fillingStation = null;
        //check if we see any nearby unclaimed ruins
        if(closestUnclaimedRuin != null && closestUnclaimedRuin.isWithinDistanceSquared(rc.getLocation(), GameConstants.VISION_RADIUS_SQUARED) && !isOccupied(rc, closestUnclaimedRuin)) {
            state = SoldierState.RuinBuilding;
            return;
        }
        //if we can see an enemy tower, maybe lets worry about that
        if(closestEnemyTower != null && closestEnemyTower.isWithinDistanceSquared(rc.getLocation(), GameConstants.VISION_RADIUS_SQUARED)) {
            state = SoldierState.Tower;
            return;
        }
        //check if we see any uncompleted resource patterns marked out
        if(closestUnfilledPatternCenter != null && rc.senseNearbyRobots(closestUnfilledPatternCenter, 8, rc.getTeam()).length <= 1) {
            if(closestUnclaimedRuin == null || closestUnclaimedRuin.distanceSquaredTo(closestUnfilledPatternCenter) > 25)   state = SoldierState.Fill;
        }
    }

    public static void explore(RobotController rc) throws GameActionException // use symmetry for this method
    {
        if(claimedRuin != null) {
            navigate(rc);
            return;
        }
        if(rng.nextInt(10) == 0 && rc.getRoundNum() < 100 || visitingCorner) {
            visitingCorner = true;
            MapLocation corner = nearestCorner(rc);
            Direction dir = BFS_7x7.pathfind(rc, corner);
            if(rc.canMove(dir)) rc.move(dir);
            attemptFill(rc);
            if(rc.getLocation().distanceSquaredTo(corner) <= 8) {
                visitingCorner = false;
            }
        }
        else {
            if (rc.getRoundNum() % 20 == 0 || target == null || rc.getLocation().distanceSquaredTo(target) < 8) {
                target = generateExploreLocation(rc);
            }
            Direction dir = BFS_7x7.pathfind(rc, target);
            if (rc.canMove(dir)) rc.move(dir);
            attemptFill(rc);
        }
    }

    //attempts to navigate to a known good place to be - lets first check unclaimed ruins, then lets go towards enemy towers
    //if we have nothing to do, just explore
    public static void navigate(RobotController rc) throws GameActionException
    {
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
        if(fillingStation == null || rc.canSenseLocation(fillingStation) && (rc.senseNearbyRobots(fillingStation, 2, rc.getTeam()).length > 3 || rc.canSenseLocation(fillingStation) && rc.senseRobotAtLocation(fillingStation) == null)) {
            fillingStation = nextNearestPaintTower(rc);
            if(fillingStation == null){
                explore(rc);
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

    public static void ruinBuilding(RobotController rc) throws GameActionException
    {
        MapInfo[] attemptToFill = rc.senseNearbyMapInfos(closestUnclaimedRuin, 8);
        int dist = rc.getLocation().distanceSquaredTo(closestUnclaimedRuin);
        boolean[][] desiredPattern = Utilities.inferPatternFromExistingSpots(rc, closestUnclaimedRuin, attemptToFill);
        if(desiredPattern == null) {
            desiredPattern = chooseDesiredPattern(rc, closestUnclaimedRuin);
        }
        if(!isOccupied(rc, closestUnclaimedRuin) && claimedRuin == null) {
            MapLocation toMark = closestUnclaimedRuin.add(rc.getLocation().directionTo(closestUnclaimedRuin).opposite());
            if(rc.canMark(toMark)){
                rc.mark(toMark, true);
                claimedRuin = closestUnclaimedRuin;
            }
        }
        if(dist > 2 && rc.isMovementReady()) {
            Direction dir = BFS_7x7.pathfind(rc, closestUnclaimedRuin);
            if(rc.canMove(dir)) rc.move(dir);
        }
        //if we are on the pattern, and we are on empty paint, prioritize filling in around us
        if (dist <= 8 && rc.senseMapInfo(rc.getLocation()).getPaint() == PaintType.EMPTY) {
            if(rc.canAttack(rc.getLocation())) rc.attack(rc.getLocation(), Utilities.getColorFromCustomPattern(rc.getLocation(), desiredPattern, closestUnclaimedRuin));
        }
        MapInfo bestTile = null;
        boolean isSecondary = false;
        int bestDistToSelf = Integer.MAX_VALUE;
        //find the best tile to attack, according to these priorities
        //1. tile is within the radius of the unclaimed ruin
        //2. tile is empty
        //3. tile is allied
        //4. distance to current location is tiebreak
        //if we find a tile that is empty within the radius, just shirt circuit, otherwise keep going in case we find a better one
        for(MapInfo tile : attemptToFill) {
            if(!tile.isPassable()) continue;
            PaintType paint = tile.getPaint();
            int distToRuin = tile.getMapLocation().distanceSquaredTo(closestUnclaimedRuin);
            int distToSelf = tile.getMapLocation().distanceSquaredTo(rc.getLocation());
            boolean tempIsSecondary = Utilities.getColorFromCustomPattern(tile.getMapLocation(), desiredPattern, closestUnclaimedRuin);
            if(paint == PaintType.EMPTY && distToRuin <= 8 && (distToSelf < bestDistToSelf || bestTile == null || !bestTile.getPaint().isEnemy())) {
                if(rc.canAttack(tile.getMapLocation())) {
                    rc.attack(tile.getMapLocation(), tempIsSecondary);
                    break;
                }
                else {
                    bestTile = tile;
                    bestDistToSelf = distToSelf;
                    isSecondary = tempIsSecondary;
                }
            }
            else if (paint.isAlly() && distToRuin <= 8 && (distToSelf < bestDistToSelf || bestTile == null) && paint.isSecondary() != tempIsSecondary) {
                bestTile = tile;
                bestDistToSelf = distToSelf;
                isSecondary = tempIsSecondary;
            }
        }
        if(bestTile != null && rc.canAttack(bestTile.getMapLocation())) {
            rc.attack(bestTile.getMapLocation(), isSecondary);
        }
        else if(bestTile != null && rc.isMovementReady()) {
            Direction dir = BFS_7x7.pathfind(rc, bestTile.getMapLocation());
            if(rc.canMove(dir)) rc.move(dir);
            if(rc.canAttack(bestTile.getMapLocation())) rc.attack(bestTile.getMapLocation(), isSecondary);
        }
        if(bestTile == null && rc.isMovementReady()) {
            Direction dir = Direction.allDirections()[rng.nextInt(Direction.allDirections().length)];
            if(rc.canMove(dir) && rc.getLocation().add(dir).isWithinDistanceSquared(closestUnclaimedRuin, 2)) rc.move(dir);
        }
        if(rc.isActionReady() && rc.getPaint() > refillThreshold + 5) {
            attemptFill(rc);
        }

        //try and complete the ruin pattern
        if ((rc.getNumberTowers() > 2 || rc.getMoney() >= 1200) && rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, closestUnclaimedRuin)){
            rc.completeTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, closestUnclaimedRuin);
            claimedRuin = null;
        }
        else if (rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, closestUnclaimedRuin)){
            rc.completeTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, closestUnclaimedRuin);
            claimedRuin = null;
        }
        if(rc.isMovementReady() && rc.senseMapInfo(rc.getLocation()).getPaint().isEnemy()) {
            for(MapLocation loc : rc.getAllLocationsWithinRadiusSquared(rc.getLocation(), 2)) {
                if(rc.canMove(rc.getLocation().directionTo(loc)) && loc.isWithinDistanceSquared(closestUnclaimedRuin, 8) && !rc.senseMapInfo(loc).getPaint().isEnemy()) {
                    rc.move(rc.getLocation().directionTo(loc));
                    break;
                }
            }
        }

    }

    //where we decide what kind of pattern to use
    //logic currently copied from version 8
    public static boolean[][] chooseDesiredPattern(RobotController rc, MapLocation m) throws GameActionException {
        if(rc.getRoundNum() <= 5) {
            return rc.getTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER);
        }
        else {
            int ranNum = (rc.getRoundNum() < 100) ? 0 : RobotPlayer.rng.nextInt(3);
            if(ranNum <= 1) {
                return rc.getTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER);
            }
            else {
                return rc.getTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER);
            }
        }
    }

    //attempt fill assumes we have already moved - move, then paint
    //TODO: instead make this a method that will heavily prioritize filling around pattern centers
    public static void attemptFill(RobotController rc) throws GameActionException
    {
        if(!rc.isActionReady()) return;
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

    public static void attemptCompleteTowerPattern(RobotController rc) throws GameActionException
    {
        for(MapLocation ruin : nearbyRuins)
        {
            if(!rc.canSenseRobotAtLocation(ruin) &&
                    rc.getLocation().isWithinDistanceSquared(ruin, UnitType.SOLDIER.actionRadiusSquared))
            {
                if(rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_DEFENSE_TOWER, target))
                {
                    rc.completeTowerPattern(UnitType.LEVEL_ONE_DEFENSE_TOWER, target);
                }

                if(rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, target))
                {
                    rc.completeTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, target);
                }

                if(rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, target))
                {
                    rc.completeTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, target);
                }
            }
        }
    }



    //generates a random location of a nearby sector you haven't explored
    static MapLocation generateExploreLocation(RobotController rc)
    {
        int curSector = Sector.getSector(rc, rc.getLocation());
        int ranOffset = (rng.nextInt(2) == 1) ? rng.nextInt(3) * -1 : rng.nextInt(3);
        int newSector = curSector + ranOffset;
        int i = 0;
        while(newSector <= 0 || newSector >= Sector.hasTraveled.length || Sector.hasTraveled[newSector] && i <= 20) {
            ranOffset += (rng.nextInt(2) == 1) ? rng.nextInt(3) * -1 : rng.nextInt(3);
            newSector = curSector + ranOffset;
            i++;
        }
        if (i >= 20) return new MapLocation(rng.nextInt(rc.getMapWidth()), rng.nextInt(rc.getMapHeight()));
        return Sector.getRandomLocation(rc, newSector);
    }

    //sets the relevant sector in hasTraveled to true if we are in a sector we haven't seen before
    public static void recordSector(RobotController rc) {
        Sector.hasTraveled[Sector.getSector(rc, rc.getLocation())] = true;
    }

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
        return temp;
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