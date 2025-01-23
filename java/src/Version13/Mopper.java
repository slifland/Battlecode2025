package Version13;

import battlecode.common.*;
import battlecode.instrumenter.inject.RobotMonitor;
import battlecode.schema.RobotType;

import java.util.ArrayList;

import static Version13.Communication.enemyTowers;
import static Version13.Communication.unclaimedRuins;
import static Version13.RobotPlayer.*;

enum mopStates {
    navigate, contest, refill, clear
}

//used to track what we are currently navigating towards
enum navState {
    random, tower, ruin, horizontal, vertical, rotational
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

    static boolean correctSymmetry = false;

    static int numEnemyTiles;

    static boolean needsClearing = false;

    static boolean exploredSymmetry = false;

    static boolean[][] checkedRuin;

    static navState navTarget;


    //static int uselessTurnsCount = 0;

    public static void runMopper() throws GameActionException {
        if(turnCount == 1){
            spawnLocation = staticRC.getLocation();
            checkedRuin = new boolean[staticRC.getMapWidth()][staticRC.getMapHeight()];
            navTarget = null;
        }
        updateInfo();
        updateState();
        switch(state) {
            case navigate:
                navigate();
                break;
            case contest:
                contest();
                //if(staticRC.isActionReady()) uselessTurnsCount++;
                //else uselessTurnsCount = 0;
                break;
            case refill:
                refill();
                //if(staticRC.isActionReady()) uselessTurnsCount++;
                //else uselessTurnsCount = 0;
                break;
            case clear:
                clear();
                break;
        }
//        if(curObjective != null ) staticRC.setIndicatorString(state + " : " + curObjective);
//        else staticRC.setIndicatorString(state.toString());
        //System.out.println(Clock.getBytecodesLeft());
    }

    //attempts to navigate to a known location
    public static void navigate() throws GameActionException {
        if(turnCount % 200 == 0) {
            exploredSymmetry = false;
        }
        MapLocation curLoc = staticRC.getLocation();
        if(curObjective != null && curLoc.distanceSquaredTo(curObjective) < 8) {
            switch(navTarget) {
                case navState.horizontal, navState.rotational, navState.vertical -> exploredSymmetry = true;
                case navState.ruin -> checkedRuin[curObjective.x][curObjective.y] = true;
            }
            curObjective = null;
            navTarget = null;
        }
        else if(curObjective != null && knownSymmetry != Symmetry.Unknown && !correctSymmetry) {
            switch(navTarget) {
                case navState.horizontal -> {
                    if(knownSymmetry != Symmetry.Horizontal) {
                        switch(knownSymmetry) {
                            case Rotational:
                                curObjective = new MapLocation(staticRC.getMapWidth() - 1 - spawnLocation.x, staticRC.getMapHeight() - 1 - spawnLocation.y);
                                navTarget = navState.rotational;
                                break;
                            case Vertical:
                                curObjective = new MapLocation(staticRC.getMapWidth() - 1 - spawnLocation.x, spawnLocation.y);
                                navTarget = navState.vertical;
                                break;
                        }
                    }
                }
                case navState.rotational -> {
                    if(knownSymmetry != Symmetry.Rotational) {
                        switch(knownSymmetry) {
                            case Vertical:
                                curObjective = new MapLocation(staticRC.getMapWidth() - 1 - spawnLocation.x, spawnLocation.y);
                                navTarget = navState.vertical;
                                break;
                            case Horizontal:
                                curObjective = new MapLocation(spawnLocation.x, staticRC.getMapHeight() - 1 - spawnLocation.y);
                                navTarget = navState.horizontal;
                                break;
                        }
                    }
                }
                case navState.vertical -> {
                    if(knownSymmetry != Symmetry.Vertical) {
                        switch(knownSymmetry) {
                            case Rotational:
                                curObjective = new MapLocation(staticRC.getMapWidth() - 1 - spawnLocation.x, staticRC.getMapHeight() - 1 - spawnLocation.y);
                                navTarget = navState.rotational;
                                break;
                            case Horizontal:
                                curObjective = new MapLocation(spawnLocation.x, staticRC.getMapHeight() - 1 - spawnLocation.y);
                                navTarget = navState.horizontal;
                                break;
                        }
                    }
                }
            }
            correctSymmetry = true;
        }
        //if we know of any unclaimed ruins, lets try to help out there
        if(curObjective == null && !unclaimedRuins.isEmpty()) {
            int minDist = Integer.MAX_VALUE;
            for(Ruin r : unclaimedRuins) {
                if(checkedRuin[r.location.x][r.location.y]) continue;
                if(r.location.distanceSquaredTo(curLoc) < minDist) {
                    minDist = r.location.distanceSquaredTo(curLoc);
                    curObjective = r.location;
                    navTarget = navState.ruin;
                }
            }
        }
        if(curObjective == null && !enemyTowers.isEmpty()) {
            int minDist = Integer.MAX_VALUE;
            for(Ruin r : enemyTowers) {
                if(r.location.distanceSquaredTo(curLoc) < minDist) {
                    minDist = r.location.distanceSquaredTo(curLoc);
                    curObjective = r.location;
                    navTarget = navState.tower;
                }
            }
        }
        //next, if we have enemy towers, go there
        //finally, navigate to the opposite of where we spawned
        if(curObjective == null && !exploredSymmetry) {
            Symmetry[] possible = Utilities.possibleSymmetry();
            int sym = rng.nextInt(possible.length);
            switch(possible[sym]) {
                case Horizontal:
                    curObjective = new MapLocation(spawnLocation.x, staticRC.getMapHeight() - 1 - spawnLocation.y);
                    navTarget = navState.horizontal;
                    break;
                case Rotational:
                    curObjective = new MapLocation(staticRC.getMapWidth() - 1 - spawnLocation.x, staticRC.getMapHeight() - 1 - spawnLocation.y);
                    navTarget = navState.rotational;
                    break;
                case Vertical:
                    curObjective = new MapLocation(staticRC.getMapWidth() - 1 - spawnLocation.x, spawnLocation.y);
                    navTarget = navState.vertical;
                    break;
            }
        }
        //last resort - just go to the center
        if(curObjective == null) {
            curObjective = new MapLocation(rng.nextInt(staticRC.getMapHeight() - 6) + 3, rng.nextInt(staticRC.getMapHeight() - 6) + 3);
            navTarget = navState.random;
        }
        //MOVE TO OBJECTIVE
        Direction dir = Pathfinding.bugBFS(curObjective);
        if(staticRC.canMove(dir)) staticRC.move(dir);
        if(staticRC.isActionReady() && enemyRobots.length >= 1) {
            RobotInfo bestTarget = findBestTarget();
            if(bestTarget != null && staticRC.canAttack(bestTarget.getLocation())) {
                staticRC.attack(bestTarget.getLocation());
            }
            else {
                Direction dirToSweep = MopperMicro.dirToSweep(1);
                if(dirToSweep != null && staticRC.canMopSwing(dirToSweep)) staticRC.mopSwing(dirToSweep);
            }
        }
        staticRC.setIndicatorString("navigate! " + curObjective + " : " + navTarget);
    }

    //attempts to contest by mopping up enemy stuff, and stealing paint from enemies
    public static void contest() throws GameActionException {
//        if(staticRC.isActionReady()) {
//            MapLocation bestMop = findBestMop();
//            if(staticRC.canAttack(bestMop)) staticRC.attack(bestMop);
//        }
//        Direction dir = Micro.runMicro();
//        if(staticRC.canMove(dir)) staticRC.move(dir);
//        if(staticRC.isActionReady()) {
//            MapLocation bestMop = findBestMop();
//            if(staticRC.canAttack(bestMop)) staticRC.attack(bestMop);
//        }
        MopperMicro.integratedMopperMicro();
    }

    //attempts to refill by stealing paint from enemy robots
    public static void refill() throws GameActionException {
        RobotInfo bestTarget = findBestTarget();
        if(bestTarget != null && staticRC.canAttack(bestTarget.getLocation())) {
            staticRC.attack(bestTarget.getLocation());
        }
        //Direction dir = Micro.runMicro();
        //if(staticRC.canMove(dir)) staticRC.move(dir);
        if(averageEnemyPaint != null) MopperMicro.integratedMopperMicro();
        else {
            Direction dirToSweep = MopperMicro.dirToSweep(3);
            if(dirToSweep != null && staticRC.canMopSwing(dirToSweep)) {
                staticRC.mopSwing(dirToSweep);
                return;
            }
        }
        if(staticRC.isActionReady() && Clock.getBytecodesLeft() > 3000) {
            bestTarget = findBestTarget();
            if(bestTarget != null && staticRC.canAttack(bestTarget.getLocation())) {
                staticRC.attack(bestTarget.getLocation());
            }
        }
        if(staticRC.isActionReady()) MopperMicro.attackAnything();
    }

    //tries to clear the enemy paint around one of our ruins
    public static void clear() throws GameActionException {
        MapLocation bestLoc = bestClear(nearbyRuin);
        Direction dir = MopperMicro.dirToSweep(2);
        if(dir != null && staticRC.canMopSwing(dir)) {
            staticRC.mopSwing(dir);
        }
        if(bestLoc != null && staticRC.isActionReady()) {
            MopperMicro.targetedMopperMicro(MopperMicro.customLocationTo(staticRC.getLocation(), bestLoc), bestLoc);
        }
        else {
            MopperMicro.integratedMopperMicro();
        }
        if(staticRC.canAttack(bestLoc)) staticRC.attack(bestLoc);
        if(staticRC.isActionReady()) {
            MopperMicro.attackAnything();
        }
        staticRC.setIndicatorString("clear! " + bestLoc);
    }

    //determine a moppers state for this turn
    public static void updateState() throws GameActionException {
        state = mopStates.navigate;
        //try to steal paint from the enemy
        if((staticRC.getPaint() <= refillThreshold || (staticRC.getPaint() <= endRefillThreshold && state == mopStates.refill)) && enemyRobots.length > 2) {
            state = mopStates.refill;
            return;
        }
        if(needsClearing) {
            state = mopStates.clear;
            return;
        }
        if(averageEnemyPaint != null) state = mopStates.contest;
    }

    public static MapLocation bestClear(MapLocation ruin) throws GameActionException {
        int minDist = Integer.MAX_VALUE;
        MapLocation bestLoc = null;
        for(MapInfo tile : staticRC.senseNearbyMapInfos(ruin, 8)) {
            int dist = staticRC.getLocation().distanceSquaredTo(tile.getMapLocation());
            if(tile.getPaint().isEnemy() && dist < minDist) {
                bestLoc = tile.getMapLocation();
                minDist = dist;
                if(minDist <= 2) return bestLoc;
            }
        }
        return bestLoc;
    }

    //returns the best robot to target
    public static RobotInfo findBestTarget() throws GameActionException {
        int highestScore = -1;
        RobotInfo target = null;
        MapLocation curLoc = staticRC.getLocation();
        for(RobotInfo robot : enemyRobots) {
            if(robot.getLocation().distanceSquaredTo(curLoc) > UnitType.MOPPER.actionRadiusSquared) continue;
            MapInfo loc = staticRC.senseMapInfo(robot.getLocation());
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
    public static void updateInfo() throws GameActionException {
        //finds a nearby unclaimed ruins
        //updates turn by turn variables
        averageEnemyPaint = null;
        needsClearing = false;

//        for(MapInfo tile : nearbyTiles) {
//            Utilities.attemptCompleteResourcePattern(tile.getMapLocation());
//            if(knownSymmetry == RobotPlayer.Symmetry.Unknown) {
//                map[tile.getMapLocation().x][tile.getMapLocation().y] = (tile.isPassable()) ? 1 : (tile.isWall()) ? 2 : 3;
//                if(!tile.isPassable())  Utilities.validateSymmetry(tile.getMapLocation(), tile.hasRuin());
//            }
//            if(tile.getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(tile.getMapLocation())) {
//                if(!needsClearing && nearbyRuin != null && tile.getMapLocation().isWithinDistanceSquared(nearbyRuin, 8)) {
//                    needsClearing = true;
//                }
//                x += tile.getMapLocation().x;
//                y += tile.getMapLocation().y;
//                count++;
//            }
//        }
        MopperUtil.scanNearbyTiles();
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
}
