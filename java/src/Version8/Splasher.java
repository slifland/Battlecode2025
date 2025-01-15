package Version8;

import battlecode.common.*;

import static Version8.splasherUtil.*;

import static Version8.RobotPlayer.*;

enum splasherStates {
    navigate, refill, contest
}

public class Splasher {

    private static MapLocation curObjective;
    private static splasherStates state;
    private static RobotInfo seenEnemyTower; //records whether we can see an enemy tower, and if so, the enemy tower's info
    private static int numEnemyTiles; //records the number of enemy tiles we can see
    private static MapLocation fillingStation;
    private static MapLocation nearestPaintTower;
    //final variables
    private static final int refillThreshold = 51;
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
    }

    //attempts to navigate to a known location - enemy average, usually
    public static void navigate(RobotController rc) throws GameActionException {
        if(rc.getLocation().distanceSquaredTo(curObjective) < 8) curObjective = null;
        //DETERMINE OBJECTIVE
        //if we have enemy paint averages, go there
        MapLocation[] enemyAverages = Utilities.getEnemyPaintAverages();
        if(enemyAverages.length != 0) {
            curObjective = enemyAverages[0];
        }
        //finally, navigate to the opposite of where we spawned
        if(curObjective == null) {
            Symmetry[] possible = Utilities.possibleSymmetry();
            int sym = rng.nextInt(possible.length);
            switch(possible[sym]) {
                case Horizontal:
                    curObjective = new MapLocation(rc.getLocation().x, rc.getMapHeight() - 1 - rc.getLocation().y);
                    break;
                case Rotational:
                    curObjective = new MapLocation(rc.getMapWidth() - 1 - rc.getLocation().x, rc.getMapHeight() - 1 - rc.getLocation().y);
                    break;
                case Vertical:
                    curObjective = new MapLocation(rc.getMapWidth() - 1 - rc.getLocation().x, rc.getLocation().y);
                    break;
            }
        }
        //next, if we have enemy towers, go there
        //last resort - just go to the center
        if(curObjective == null) {
            curObjective = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
        }
        //MOVE TO OBJECTIVE
        Direction dir = BFS_7x7.pathfind(rc, curObjective);
        if(rc.canMove(dir)) rc.move(dir);
    }

    //attempts to return to a known allied paint tower and refill
    public static void refill(RobotController rc) throws GameActionException {
        // we have a problem if it tries to fill but doesn't know of any paint towers
        if(fillingStation == null) return;
        //navigate to the nearest paint tower
        //if that tower is surrounded, navigate to the next nearest
        if(rc.getLocation().isAdjacentTo(fillingStation)) {
            if(rc.canTransferPaint(fillingStation, Math.max(rc.getPaint() - rc.getType().paintCapacity, rc.senseRobotAtLocation(fillingStation).paintAmount * -1))){
                rc.transferPaint(fillingStation, Math.max(rc.getPaint() - rc.getType().paintCapacity, rc.senseRobotAtLocation(fillingStation).paintAmount * -1));
            }
        }
        else {
            Direction dir = BFS_7x7.pathfind(rc, fillingStation);
            if(rc.canMove(dir)) rc.move(dir);
        }
    }

    //attempts to steal enemy territory and potentially attack towers
    public static void contest(RobotController rc) throws GameActionException {
        SplasherMicro.integratedMopperMicro(rc, seenEnemyTower  != null);
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
        //reset turn by turn variables
        seenEnemyTower = null;
        numEnemyTiles = 0;
        averageEnemyPaint = null;

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
        int x = 0;
        int y = 0;
        //now, check if we can see any enemy tiles
        for(MapInfo tile : nearbyTiles) {
            if(tile.getPaint().isEnemy()) {
                state = splasherStates.contest;
                numEnemyTiles++;
                x += tile.getMapLocation().x;
                y += tile.getMapLocation().y;
            }
            RobotInfo robot = rc.senseRobotAtLocation(tile.getMapLocation());
            if(robot != null && robot.getType().isTowerType() && robot.getTeam() != rc.getTeam()) {
                seenEnemyTower = robot;
            }
        }
        averageEnemyPaint = (numEnemyTiles == 0) ? null : new MapLocation(x / numEnemyTiles, y / numEnemyTiles);
    }

    //updates the local information necessary for the splasher to run its turn
    public static void updateInfo(RobotController rc) {
        //TODO: add code for getting the nearest paint tower
        if(knownSymmetry == Symmetry.Unknown) {
            for (MapInfo tile : nearbyTiles) {
                map[tile.getMapLocation().x][tile.getMapLocation().y] = (tile.isPassable()) ? 1 : (tile.isWall()) ? 2 : 3;
                if(!tile.isPassable())  Utilities.validateSymmetry(tile.getMapLocation(), tile.hasRuin());
            }
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
