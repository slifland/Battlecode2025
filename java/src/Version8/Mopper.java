package Version8;

import battlecode.common.*;
import battlecode.instrumenter.inject.RobotMonitor;
import battlecode.schema.RobotType;

import java.util.ArrayList;

import static Version8.RobotPlayer.*;

enum mopStates {
    navigate, contest, refill
}

public class Mopper {

    private static mopStates state;

    private static MapLocation curObjective;

    //constants to tweak
    private static final int refillThreshold = 25;
    private static final int endRefillThreshold = 70;

    public static MapLocation nearbyRuin;

    private static MapLocation spawnLocation;

    public static void runMopper(RobotController rc) throws GameActionException {
        if(turnCount == 1) spawnLocation = rc.getLocation();
        updateInfo(rc);
        updateState(rc);
        switch(state) {
            case navigate:
                navigate(rc);
                break;
            case contest:
                contest(rc);
                break;
            case refill:
                refill(rc);
                break;
        }
    }

    //attempts to navigate to a known location
    public static void navigate(RobotController rc) throws GameActionException {
        if(curObjective != null && rc.getLocation().distanceSquaredTo(curObjective) < 8) curObjective = null;
        //if(curObjective == null) curObjective = new MapLocation(rng.nextInt(rc.getMapWidth()), rng.nextInt(rc.getMapHeight()));
        //DETERMINE OBJECTIVE
        //if we have enemy paint averages, go there
        MapLocation[] enemyAverages = Utilities.getEnemyPaintAverages();
        if(enemyAverages.length != 0) {
            curObjective = enemyAverages[0];
        }
        //next, if we have enemy towers, go there
        //finally, navigate to the opposite of where we spawned
        if(curObjective == null) {
            symmetry[] possible = Utilities.possibleSymmetry();
            int sym = rng.nextInt(possible.length);
            switch(possible[sym]) {
                case symmetry.horizontal:
                    curObjective = new MapLocation(rc.getLocation().x, rc.getMapHeight() - 1 - rc.getLocation().y);
                    break;
                case symmetry.rotational:
                    curObjective = new MapLocation(rc.getMapWidth() - 1 - rc.getLocation().x, rc.getMapHeight() - 1 - rc.getLocation().y);
                    break;
                case symmetry.vertical:
                    curObjective = new MapLocation(rc.getMapWidth() - 1 - rc.getLocation().x, rc.getLocation().y);
                    break;
            }
        }
        //last resort - just go to the center
        if(curObjective == null) {
            curObjective = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
        }
        //MOVE TO OBJECTIVE
        Direction dir = BFS_7x7.pathfind(rc, curObjective);
        if(rc.canMove(dir)) rc.move(dir);
    }

    //attempts to contest by mopping up enemy stuff, and stealing paint from enemies
    public static void contest(RobotController rc) throws GameActionException {
//        if(rc.isActionReady()) {
//            MapLocation bestMop = findBestMop(rc);
//            if(rc.canAttack(bestMop)) rc.attack(bestMop);
//        }
//        Direction dir = Micro.runMicro(rc);
//        if(rc.canMove(dir)) rc.move(dir);
//        if(rc.isActionReady()) {
//            MapLocation bestMop = findBestMop(rc);
//            if(rc.canAttack(bestMop)) rc.attack(bestMop);
//        }
        MopperMicro.integratedMopperMicro(rc);
    }

    //attempts to refill by stealing paint from enemy robots
    public static void refill(RobotController rc) throws GameActionException {
        RobotInfo bestTarget = findBestTarget(rc);
        if(bestTarget != null && rc.canAttack(bestTarget.getLocation())) {
            rc.attack(bestTarget.getLocation());
        }
        if(rc.isActionReady()) {
            Direction dirToSweep = dirToSweep(rc);
            if(dirToSweep != null && rc.canMopSwing(dirToSweep)) {
                rc.mopSwing(dirToSweep);
            }
        }
        Direction dir = Micro.runMicro(rc);
        if(rc.canMove(dir)) rc.move(dir);
        if(rc.isActionReady()) {
            bestTarget = findBestTarget(rc);
            if(bestTarget != null && rc.canAttack(bestTarget.getLocation())) {
                rc.attack(bestTarget.getLocation());
            }
        }
    }

    //determine a moppers state for this turn
    public static void updateState(RobotController rc) {
        state = mopStates.navigate;
        //try to steal paint from the enemy
        if((rc.getPaint() <= refillThreshold || (rc.getPaint() <= endRefillThreshold && state == mopStates.refill)) && enemyRobots.length > 2) {
            state = mopStates.refill;
            return;
        }
        for(MapInfo tile : nearbyTiles) {
            if(tile.getPaint().isEnemy()) {
                state = mopStates.contest;
                return;
            }
        }
    }

    //returns the best robot to target
    public static RobotInfo findBestTarget(RobotController rc) throws GameActionException {
        int highestScore = -1;
        RobotInfo target = null;
        MapLocation curLoc = rc.getLocation();
        for(RobotInfo robot : enemyRobots) {
            if(robot.getLocation().distanceSquaredTo(curLoc) > UnitType.MOPPER.actionRadiusSquared) continue;
            MapInfo loc = rc.senseMapInfo(robot.getLocation());
            int score = (robot.getPaintAmount() == 0) ? 1 : robot.getType().paintCapacity - robot.getPaintAmount();
            //add a big bonus if we are also removing paint from the tile
            if(loc.getPaint().isEnemy()) score += 50;
            if(score > highestScore) {
                highestScore = score;
                target = robot;
            }
        }
        return target;
    }

    //finds the best tile to mop
    //TODO: if we have the bytecodes available, also check if there are enemies on the tile so we can steal paint too
    public static MapLocation findBestMop(RobotController rc) {
        MapLocation toMop = null;
        int minDist = Integer.MAX_VALUE;
        boolean nearRuin = false;
        MapLocation curLoc = rc.getLocation();
        boolean checkRuin = (nearbyRuin != null);
        for(MapInfo tile : nearbyTiles) {
            MapLocation tileLoc = tile.getMapLocation();
            if(checkRuin) {
                if(!nearRuin && nearbyRuin.distanceSquaredTo(tileLoc) <= 8) {
                    nearRuin = true;
                    minDist = curLoc.distanceSquaredTo(tileLoc);
                    toMop = tileLoc;
                }
                else if(nearRuin && curLoc.distanceSquaredTo(tileLoc) < minDist) {
                    minDist = curLoc.distanceSquaredTo(tileLoc);
                    toMop = tileLoc;
                }
                else if(!nearRuin) {
                    int dist = curLoc.distanceSquaredTo(tileLoc);
                    if(dist < minDist) {
                        minDist = dist;
                        toMop = tileLoc;
                    }
                }
            }
            else {
                int dist = curLoc.distanceSquaredTo(tileLoc);
                if(dist < minDist) {
                    minDist = dist;
                    toMop = tileLoc;
                }
            }
        }
        return toMop;
    }

    //updates necessary info specific to the mopper
    public static void updateInfo(RobotController rc) throws GameActionException {
        //finds a nearby unclaimed ruins
        nearbyRuin = null;
        for(MapInfo tile : nearbyTiles) {
            if(tile.hasRuin() && !rc.canSenseRobotAtLocation(tile.getMapLocation())){
                nearbyRuin = tile.getMapLocation();
                if(knownSymmetry != symmetry.unknown) break;
            }
            if(knownSymmetry == symmetry.unknown) {
                map[tile.getMapLocation().x][tile.getMapLocation().y] = (tile.isPassable()) ? 1 : (tile.isWall()) ? 2 : 3;
                if(!tile.isPassable())  Utilities.validateSymmetry(tile.getMapLocation(), tile.hasRuin());
            }
        }
    }

    public static Direction dirToSweep(RobotController rc) throws GameActionException {
        RobotInfo[] adjacentEnemyRobots = rc.senseNearbyRobots(8, rc.getTeam());
        Direction dirToSweep = null;
        if(adjacentEnemyRobots.length > 1) {
            //determine the amount of enemies we would hit with each cardinal sweep - 0: north, 1: east, etc...
            int[] directionalSweep = {0,0,0,0};
            for(RobotInfo enemy : adjacentEnemyRobots) {
                Direction dir = rc.getLocation().directionTo(enemy.getLocation());
                switch(dir) {
                    case NORTH:
                        directionalSweep[0]++;
                        break;
                    case NORTHEAST:
                        directionalSweep[0]++;
                        directionalSweep[1]++;
                        break;
                    case EAST:
                        directionalSweep[1]++;
                        break;
                    case SOUTHEAST:
                        directionalSweep[1]++;
                        directionalSweep[2]++;
                        break;
                    case SOUTH:
                        directionalSweep[2]++;
                        break;
                    case SOUTHWEST:
                        directionalSweep[2]++;
                        directionalSweep[3]++;
                        break;
                    case WEST:
                        directionalSweep[3]++;
                        break;
                    case NORTHWEST:
                        directionalSweep[3]++;
                        directionalSweep[0]++;
                        break;
                }
            }
            int highest = -1;
            for(int i = 0; i < 4; i++) {
                if(directionalSweep[i] > 1 && directionalSweep[i] > highest) {
                    highest = directionalSweep[i];
                    dirToSweep = switch (i) {
                        case 0 -> Direction.NORTH;
                        case 1 -> Direction.EAST;
                        case 2 -> Direction.SOUTH;
                        case 3 -> Direction.WEST;
                        default -> dirToSweep;
                    };
                }
            }
        }
        return dirToSweep;
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
}
