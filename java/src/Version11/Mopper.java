package Version11;

import battlecode.common.*;
import battlecode.instrumenter.inject.RobotMonitor;
import battlecode.schema.RobotType;

import java.util.ArrayList;

import static Version11.Communication.enemyTowers;
import static Version11.Communication.unclaimedRuins;
import static Version11.RobotPlayer.*;

enum mopStates {
    navigate, contest, refill, clear
}

public class Mopper {

    private static mopStates state;

    private static MapLocation curObjective;

    //constants to tweak
    private static final int refillThreshold = 40;
    private static final int endRefillThreshold = 70;

    public static MapLocation nearbyRuin;

    private static MapLocation spawnLocation;

    public static RobotInfo seenEnemyTower;

    static MapLocation averageEnemyPaint;

    static int numEnemyTiles;

    static boolean needsClearing = false;

    //static int uselessTurnsCount = 0;

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
                //if(rc.isActionReady()) uselessTurnsCount++;
                //else uselessTurnsCount = 0;
                break;
            case refill:
                refill(rc);
                //if(rc.isActionReady()) uselessTurnsCount++;
                //else uselessTurnsCount = 0;
                break;
            case clear:
                clear(rc);
                break;
        }
//        if(curObjective != null ) rc.setIndicatorString(state + " : " + curObjective);
//        else rc.setIndicatorString(state.toString());
        //System.out.println(Clock.getBytecodesLeft());
    }

    //attempts to navigate to a known location
    public static void navigate(RobotController rc) throws GameActionException {
        MapLocation curLoc = rc.getLocation();
        if(curObjective != null && curLoc.distanceSquaredTo(curObjective) < 8) {
            curObjective = null;
            //if(uselessTurnsCount > 0) uselessTurnsCount = 0;
        }
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
//        //if we know of any unclaimed ruins, lets try to help out there
//        if(curObjective == null && knownSymmetry == Symmetry.Unknown && !unclaimedRuins.isEmpty()) {
//            int minDist = Integer.MAX_VALUE;
//            for(Ruin r : unclaimedRuins) {
//                if(r.location.distanceSquaredTo(curLoc) < minDist) {
//                    minDist = r.location.distanceSquaredTo(curLoc);
//                    curObjective = r.location;
//                }
//            }
//        }
        //next, if we have enemy towers, go there
        //finally, navigate to the opposite of where we spawned
        if(curObjective == null) {
            Symmetry[] possible = Utilities.possibleSymmetry();
            int sym = rng.nextInt(possible.length);
            switch(possible[sym]) {
                case Horizontal:
                    curObjective = new MapLocation(spawnLocation.x, rc.getMapHeight() - 1 - spawnLocation.y);
                    break;
                case Rotational:
                    curObjective = new MapLocation(rc.getMapWidth() - 1 - spawnLocation.x, rc.getMapHeight() - 1 - spawnLocation.y);
                    break;
                case Vertical:
                    curObjective = new MapLocation(rc.getMapWidth() - 1 - spawnLocation.x, spawnLocation.y);
                    break;
            }
        }
        //last resort - just go to the center
        if(curObjective == null) {
            curObjective = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
        }
        //MOVE TO OBJECTIVE
        Direction dir = Pathfinding.bugBFS(rc, curObjective);
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
        //Direction dir = Micro.runMicro(rc);
        //if(rc.canMove(dir)) rc.move(dir);
        if(averageEnemyPaint != null) MopperMicro.integratedMopperMicro(rc);
        else {
            Direction dirToSweep = MopperMicro.dirToSweep(rc, 3);
            if(dirToSweep != null && rc.canMopSwing(dirToSweep)) {
                rc.mopSwing(dirToSweep);
                return;
            }
        }
        if(rc.isActionReady() && Clock.getBytecodesLeft() > 3000) {
            bestTarget = findBestTarget(rc);
            if(bestTarget != null && rc.canAttack(bestTarget.getLocation())) {
                rc.attack(bestTarget.getLocation());
            }
        }
        if(rc.isActionReady()) MopperMicro.attackAnything(rc);
    }

    //tries to clear the enemy paint around one of our ruins
    public static void clear(RobotController rc) throws GameActionException {
        MapLocation bestLoc = bestClear(rc, nearbyRuin);
        Direction dir = MopperMicro.dirToSweep(rc, 2);
        if(dir != null && rc.canMopSwing(dir)) {
            rc.mopSwing(dir);
        }
        if(bestLoc != null && rc.isActionReady()) {
            MopperMicro.targetedMopperMicro(rc, MopperMicro.customLocationTo(rc.getLocation(), bestLoc), bestLoc);
        }
        else {
            MopperMicro.integratedMopperMicro(rc);
        }
        if(rc.canAttack(bestLoc)) rc.attack(bestLoc);
        if(rc.isActionReady()) {
            MopperMicro.attackAnything(rc);
        }
        rc.setIndicatorString("clear! " + bestLoc);
    }

    //determine a moppers state for this turn
    public static void updateState(RobotController rc) throws GameActionException {
        state = mopStates.navigate;
        //try to steal paint from the enemy
        if((rc.getPaint() <= refillThreshold || (rc.getPaint() <= endRefillThreshold && state == mopStates.refill)) && enemyRobots.length > 2) {
            state = mopStates.refill;
            return;
        }
        if(needsClearing) {
            state = mopStates.clear;
            return;
        }
        if(averageEnemyPaint != null) state = mopStates.contest;
    }

    public static MapLocation bestClear(RobotController rc, MapLocation ruin) throws GameActionException {
        int minDist = Integer.MAX_VALUE;
        MapLocation bestLoc = null;
        for(MapInfo tile : rc.senseNearbyMapInfos(ruin, 8)) {
            int dist = rc.getLocation().distanceSquaredTo(tile.getMapLocation());
            if(tile.getPaint().isEnemy() && dist < minDist) {
                bestLoc = tile.getMapLocation();
                minDist = dist;
                if(minDist <= 2) return bestLoc;
            }
        }
        return bestLoc;
    }

    //returns the best robot to target
    public static RobotInfo findBestTarget(RobotController rc) throws GameActionException {
        int highestScore = -1;
        RobotInfo target = null;
        MapLocation curLoc = rc.getLocation();
        for(RobotInfo robot : enemyRobots) {
            if(robot.getLocation().distanceSquaredTo(curLoc) > UnitType.MOPPER.actionRadiusSquared) continue;
            MapInfo loc = rc.senseMapInfo(robot.getLocation());
            int score = (robot.getPaintAmount() == 0) ? 10 : robot.getType().paintCapacity - robot.getPaintAmount();
            //add a big bonus if we are also removing paint from the tile
            if(loc.getPaint().isEnemy()) score += 50;
            if(score > highestScore) {
                highestScore = score;
                target = robot;
            }
        }
        return target;
    }


    //updates necessary info specific to the mopper
    public static void updateInfo(RobotController rc) throws GameActionException {
        //finds a nearby unclaimed ruins
        //updates turn by turn variables
        averageEnemyPaint = null;
        needsClearing = false;
        int count = 0;
        int x = 0;
        int y = 0;
        //boolean hasSeenNoWall = false;
        for(MapInfo tile : nearbyTiles) {
            Utilities.attemptCompleteResourcePattern(rc, tile.getMapLocation());
//            if(tile.hasRuin() && !rc.canSenseRobotAtLocation(tile.getMapLocation())){
//                nearbyRuin = tile.getMapLocation();
//            }
            if(knownSymmetry == Symmetry.Unknown) {
                map[tile.getMapLocation().x][tile.getMapLocation().y] = (tile.isPassable()) ? 1 : (tile.isWall()) ? 2 : 3;
                if(!tile.isPassable())  Utilities.validateSymmetry(tile.getMapLocation(), tile.hasRuin());
            }
            if(tile.getPaint().isEnemy() && /*(hasSeenNoWall || */!Utilities.basicLocationIsBehindWall(rc, tile.getMapLocation()))/*)*/ {
                if(!needsClearing && nearbyRuin != null && tile.getMapLocation().isWithinDistanceSquared(nearbyRuin, 8)) {
                    needsClearing = true;
                }
                x += tile.getMapLocation().x;
                y += tile.getMapLocation().y;
                count++;
                //hasSeenNoWall = true;
                //rc.setIndicatorDot(tile.getMapLocation(), 255, 0, 0);
            }
//            else if (tile.getPaint().isEnemy()){
//                rc.setIndicatorDot(tile.getMapLocation(), 0, 255, 0);
//            }
        }
        numEnemyTiles = count;
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
