package Version8;

import battlecode.common.*;
import battlecode.instrumenter.inject.RobotMonitor;
import battlecode.schema.RobotType;

import java.util.ArrayList;

import static Version8.Communication.enemyTowers;
import static Version8.RobotPlayer.*;

enum mopStates {
    navigate, contest, refill
}

public class Mopper {

    private static mopStates state;

    private static MapLocation curObjective;

    //constants to tweak
    private static final int refillThreshold = 40;
    private static final int endRefillThreshold = 70;

    public static MapLocation nearbyRuin;

    private static MapLocation spawnLocation;

    static MapLocation averageEnemyPaint;

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
//        if(curObjective != null ) rc.setIndicatorString(state + " : " + curObjective);
//        else rc.setIndicatorString(state.toString());
        //System.out.println(Clock.getBytecodesLeft());
    }

    //attempts to navigate to a known location
    public static void navigate(RobotController rc) throws GameActionException {
        MapLocation curLoc = rc.getLocation();
        if(curObjective != null && curLoc.distanceSquaredTo(curObjective) < 8) curObjective = null;
        //System.out.println("hi!");
        //if(curObjective == null) curObjective = new MapLocation(rng.nextInt(rc.getMapWidth()), rng.nextInt(rc.getMapHeight()));
        //DETERMINE OBJECTIVE
        //if we have enemy paint averages, go there
        //MapLocation[] enemyAverages = Utilities.getEnemyPaintAverages();
//        if(enemyAverages.length != 0) {
//            curObjective = enemyAverages[0];
//            //System.out.println(curObjective);
//            rc.setIndicatorDot(curObjective, 255, 0, 0);
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
        //next, if we have enemy towers, go there
        //finally, navigate to the opposite of where we spawned
        if(curObjective == null) {
            Symmetry[] possible = Utilities.possibleSymmetry();
            int sym = rng.nextInt(possible.length);
            switch(possible[sym]) {
                case Horizontal:
                    curObjective = new MapLocation(curLoc.x, rc.getMapHeight() - 1 - curLoc.y);
                    break;
                case Rotational:
                    curObjective = new MapLocation(rc.getMapWidth() - 1 - curLoc.x, rc.getMapHeight() - 1 - curLoc.y);
                    break;
                case Vertical:
                    curObjective = new MapLocation(rc.getMapWidth() - 1 - curLoc.x, curLoc.y);
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
            Direction dirToSweep = MopperMicro.dirToSweep(rc, 2);
            if(dirToSweep != null && rc.canMopSwing(dirToSweep)) {
                rc.mopSwing(dirToSweep);
            }
        }
        //Direction dir = Micro.runMicro(rc);
        //if(rc.canMove(dir)) rc.move(dir);
        MopperMicro.integratedMopperMicro(rc);
        if(rc.isActionReady() && Clock.getBytecodesLeft() > 3000) {
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
//        for(MapInfo tile : nearbyTiles) {
//            if(tile.getPaint().isEnemy()) {
//                state = mopStates.contest;
//                return;
//            }
//        }
        if(averageEnemyPaint != null) state = mopStates.contest;
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
        //averageEnemyPaint = null;
        int count = 0;
        int x = 0;
        int y = 0;
        for(MapInfo tile : nearbyTiles) {
            if(tile.hasRuin() && !rc.canSenseRobotAtLocation(tile.getMapLocation())){
                nearbyRuin = tile.getMapLocation();
                //if(knownSymmetry != symmetry.unknown) break;
            }
            if(knownSymmetry == Symmetry.Unknown) {
                map[tile.getMapLocation().x][tile.getMapLocation().y] = (tile.isPassable()) ? 1 : (tile.isWall()) ? 2 : 3;
                if(!tile.isPassable())  Utilities.validateSymmetry(tile.getMapLocation(), tile.hasRuin());
            }
            if(tile.getPaint().isEnemy()) {
                x += tile.getMapLocation().x;
                y += tile.getMapLocation().y;
                count++;
            }
        }
        averageEnemyPaint = (count == 0) ? null : new MapLocation(x / count, y / count);
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
