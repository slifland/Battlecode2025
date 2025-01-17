package Version8;

import battlecode.common.*;

import static Version8.splasherUtil.*;
import static Version8.Communication.*;

import static Version8.RobotPlayer.*;

enum splasherStates {
    navigate, refill, contest
}

public class Splasher {

    private static final int PAINT_TOWER_REFRESH = 25;
    private static final int PAINT_AVERAGE_REFRESH = 3;

    private static MapLocation curObjective;
    private static splasherStates state;
    public static RobotInfo seenEnemyTower; //records whether we can see an enemy tower, and if so, the enemy tower's info
    public static int numEnemyTiles; //records the number of enemy tiles we can see
    private static MapLocation fillingStation;
    private static MapLocation nearestPaintTower;
    public static int distanceToNearestPaintTower = -1;
    //final variables
    private static final int refillThreshold = 100;
    private static final int endRefillThreshold = 200;

    public static MapLocation averageEnemyPaint;



    public static void runSplasher(RobotController rc) throws GameActionException {
        updateInfo(rc);
        updateState(rc);
        switch(state) {
            case navigate:
                navigate(rc);
                break;
            case refill:
                refill(rc);
                break;
            case contest:
                contest(rc);
                break;
        }
//        if(curObjective== null)rc.setIndicatorString(state.toString());
//        else rc.setIndicatorString(state + " : " + curObjective);
    }

    //attempts to navigate to a known location - enemy average, usually
    public static void navigate(RobotController rc) throws GameActionException {
        MapLocation curLoc = rc.getLocation();
        if(curObjective != null && curLoc.distanceSquaredTo(curObjective) < 8) curObjective = null;
        //DETERMINE OBJECTIVE
        //if we have enemy paint averages, go there
//        MapLocation[] enemyAverages = Utilities.getEnemyPaintAverages();
//        if(enemyAverages.length != 0) {
//            curObjective = enemyAverages[0];
//        }
        if(curObjective == null && !enemyTowers.isEmpty()) {
            int minDist = Integer.MAX_VALUE;
            for(Ruin r : enemyTowers) {
                if(r.location.distanceSquaredTo(curLoc) < minDist) {
                    minDist = r.location.distanceSquaredTo(curLoc);
                    curObjective = r.location;
                }
            }
        }
        //finally, navigate to the opposite of where we spawned
        if(curObjective == null) {
            Symmetry[] possible = Utilities.possibleSymmetry();
            int sym = rng.nextInt(possible.length);
            switch(possible[sym]) {
                case Horizontal:
                    curObjective = new MapLocation(curLoc.x, rc.getMapHeight() - 1 - rc.getLocation().y);
                    break;
                case Rotational:
                    curObjective = new MapLocation(rc.getMapWidth() - 1 - curLoc.x, rc.getMapHeight() - 1 - curLoc.y);
                    break;
                case Vertical:
                    curObjective = new MapLocation(rc.getMapWidth() - 1 - curLoc.x, rc.getLocation().y);
                    break;
            }
        }
        //next, if we have enemy towers, go there
        //last resort - just go to the center
        if(curObjective == null) {
            curObjective = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
        }
        //MOVE TO OBJECTIVE
        //int price = Clock.getBytecodesLeft();
        if(rc.isMovementReady()) {
            Direction dir = BFS_7x7.pathfind(rc, curObjective);
            if (rc.canMove(dir)) rc.move(dir);
        }
        //System.out.println(price - Clock.getBytecodesLeft());
    }

    //attempts to return to a known allied paint tower and refill
    public static void refill(RobotController rc) throws GameActionException {
        //navigate to the nearest paint tower
        //if that tower is surrounded, navigate to the next nearest
        if(fillingStation == null || rc.canSenseLocation(fillingStation) && (rc.senseNearbyRobots(fillingStation, 2, rc.getTeam()).length > 2 || rc.canSenseLocation(fillingStation) && rc.senseRobotAtLocation(fillingStation) == null)) {
            fillingStation = nextNearestPaintTower(rc);
            if(fillingStation == null){
                curObjective = null;
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

    //used only when we are refilling and no of know paint towers
    public static void explore(RobotController rc) throws GameActionException {
        if(curObjective == null || rc.getLocation().distanceSquaredTo(curObjective) <= 8) curObjective = new MapLocation(rng.nextInt(rc.getMapWidth() - 6) + 3, rng.nextInt(rc.getMapHeight() - 6) + 3);
        if(rc.isMovementReady()) {
            Direction dir = BFS_7x7.pathfind(rc, curObjective);
            if (rc.canMove(dir)) rc.move(dir);
        }
    }

    //returns the closest paint tower that is not currently the filling station or the nearestPaintTower
    private static MapLocation nextNearestPaintTower(RobotController rc) throws GameActionException {
        MapLocation curLoc = rc.getLocation();
        int curClosest = Integer.MAX_VALUE;
        MapLocation temp = null;
        for (Ruin r : alliedPaintTowers) {
            if (r.location == nearestPaintTower || r.location == fillingStation) continue;
            else {
                if(curLoc.distanceSquaredTo(r.location) < curClosest) {
                    curClosest = curLoc.distanceSquaredTo(r.location);
                    temp = r.location;
                }
            }
        }
        return temp;
    }

    //attempts to steal enemy territory and potentially attack towers
    public static void contest(RobotController rc) throws GameActionException {
        SplasherMicro.integratedSplasherMicro(rc, seenEnemyTower  != null);
//        MapLocation toAttack = cheapBestAttack(rc, seenEnemyTower != null, Math.min(4, numEnemyTiles));
//        //no attacks that good
//        if(toAttack == null) {
//            if(rc.canAttack(seenEnemyTower.getLocation())) {
//                rc.attack(seenEnemyTower.getLocation());
//            }
//            else {
//                Direction dir = Micro.runMicro(rc, allyRobots.length - enemyRobots.length > 5);
//                if (rc.canMove(dir)) rc.move(dir);
//                toAttack = cheapBestAttack(rc, seenEnemyTower != null, Math.min(4, numEnemyTiles));
//                if (toAttack != null && rc.canAttack(toAttack)) {
//                    rc.attack(toAttack);
//                }
//                if (toAttack == null && rc.canAttack(seenEnemyTower.getLocation())) {
//                    rc.attack(seenEnemyTower.getLocation());
//                }
//            }
//        }
//        else if (rc.canAttack(toAttack)) {
//            rc.attack(toAttack);
//        }
//        if(rc.isMovementReady()) {
//            Direction dir = Micro.runMicro(rc);
//            if(rc.canMove(dir)) rc.move(dir);
//        }
    }

    //determines the current state for the splashers
    public static void updateState(RobotController rc) throws GameActionException {
        state = splasherStates.navigate;

        if(rc.getPaint() <= refillThreshold || (state == splasherStates.refill && rc.getPaint() <= endRefillThreshold)) {
            state = splasherStates.refill;
            fillingStation = nearestPaintTower;
            //go back to nearest tower
            return;
        }
        else if(state == splasherStates.refill && rc.getPaint() >= endRefillThreshold){
            state = splasherStates.navigate;
            fillingStation = null;
        }
        if(averageEnemyPaint != null) {
            state = splasherStates.contest;
        }
    }

    //updates the local information necessary for the splasher to run its turn
    public static void updateInfo(RobotController rc) {

        MapLocation curLoc = rc.getLocation();

        //gets the nearest paint tower, updating every so often
        if(rc.getRoundNum() % PAINT_TOWER_REFRESH == 0) {
            for (Ruin r : alliedPaintTowers) {
                if (nearestPaintTower == null || curLoc.distanceSquaredTo(r.location) < distanceToNearestPaintTower) {
                    distanceToNearestPaintTower = curLoc.distanceSquaredTo(r.location);
                    nearestPaintTower = r.location;
                }
            }
        }
        if(rc.getRoundNum() % PAINT_AVERAGE_REFRESH == 0) {
            //reset turn by turn variables
            seenEnemyTower = null;
            numEnemyTiles = 0;
            averageEnemyPaint = null;
            int x = 0;
            int y = 0;
            //now, check if we can see any enemy tiles
            for (MapInfo tile : nearbyTiles) {
                if (knownSymmetry == Symmetry.Unknown) {
                    map[tile.getMapLocation().x][tile.getMapLocation().y] = (tile.isPassable()) ? 1 : (tile.isWall()) ? 2 : 3;
                    if (!tile.isPassable()) Utilities.validateSymmetry(tile.getMapLocation(), tile.hasRuin());
                }
                if (tile.getPaint().isEnemy()) {
                    x += tile.getMapLocation().x;
                    y += tile.getMapLocation().y;
                    numEnemyTiles++;
                }
            }
            averageEnemyPaint = (numEnemyTiles == 0) ? null : new MapLocation(x / numEnemyTiles, y / numEnemyTiles);
        }
    }

    //UTILITY METHODS

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

}
