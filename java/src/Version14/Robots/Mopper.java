package Version14.Robots;

import Version14.Micro.MopperMicro;
import Version14.Pathfinding.Pathfinding;
import Version14.Misc.Ruin;
import Version14.Utility.*;
import Version14.Utility.Symmetry.SymmetryType;
import battlecode.common.*;

import static Version14.Misc.Communication.enemyTowers;
import static Version14.Misc.Communication.unclaimedRuins;
import static Version14.RobotPlayer.*;
import static Version14.Utility.BitBoard.*;
import static Version14.Utility.Symmetry.knownSymmetry;

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

    //public static BitBoard enemyDefenseTowers;
    public static FastIterableLocSet enemyDefenseTowers;

    private static MapLocation spawnLocation;

    public static RobotInfo seenEnemyTower;

    public static MapLocation averageEnemyPaint;

    public static boolean correctSymmetry = false;

    public static int numEnemyTiles;

    public static boolean needsClearing = false;

    public static MapLocation closestUnseenRuin;

    public static boolean exploredSymmetry = false;

    //public static boolean[][] checkedRuin;

    //public static BitBoard checkedRuin;
    public static FastIterableLocSet checkedRuin;

    //public static BitBoard checkedTower;
    public static FastIterableLocSet checkedTower;

    public static navState navTarget;

    static int scanCount = 0;


    //static int uselessTurnsCount = 0;

    public static void runMopper() throws GameActionException {
        if(turnCount == 1){
            spawnLocation = staticRC.getLocation();
            //checkedRuin = new boolean[staticRC.getMapWidth()][staticRC.getMapHeight()];
            //checkedRuin = new BitBoard();
            checkedRuin = new FastIterableLocSet();
            navTarget = null;
            //checkedTower = new BitBoard();
            checkedTower = new FastIterableLocSet();
            //enemyDefenseTowers = new BitBoard();
            enemyDefenseTowers = new FastIterableLocSet();
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
        if(staticRC.isActionReady() && enemyRobots.length > 0) {
            MopperMicro.attackAnything();
            if(!staticRC.isActionReady()) {
                System.out.println("hi");
            }
        }
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
                //case navState.ruin -> checkedRuin[curObjective.x][curObjective.y] = true;
                //case navState.ruin -> checkedRuin.setBit(curObjective, true);
                case navState.ruin -> checkedRuin.add(curObjective);
                //case navState.tower -> checkedTower.setBit(curObjective, true);
                case navState.tower -> checkedTower.add(curObjective);
            }
            curObjective = null;
            navTarget = null;
        }
        else if(curObjective != null && knownSymmetry != SymmetryType.Unknown && !correctSymmetry && navTarget != null) {
            switch(navTarget) {
                case navState.horizontal -> {
                    if(knownSymmetry != SymmetryType.Horizontal) {
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
                    if(knownSymmetry != SymmetryType.Rotational) {
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
                    if(knownSymmetry != SymmetryType.Vertical) {
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
        if(curObjective == null && (!unclaimedRuins.isEmpty() || closestUnseenRuin != null)) {
            int minDist = Integer.MAX_VALUE;
            for(Ruin r : unclaimedRuins) {
                //if(checkedRuin[r.location.x][r.location.y]) continue;
                //if(checkedRuin.getBit(r.location)) continue;
                if(checkedRuin.contains(r.location)) continue;
                if(r.location.distanceSquaredTo(curLoc) < minDist) {
                    minDist = r.location.distanceSquaredTo(curLoc);
                    curObjective = r.location;
                    navTarget = navState.ruin;
                }
            }
            if(closestUnseenRuin != null && staticRC.getLocation().distanceSquaredTo(closestUnseenRuin) < minDist) {
                staticRC.setIndicatorLine(staticRC.getLocation(), closestUnseenRuin, 255, 0 , 0);
                curObjective = closestUnseenRuin;
                navTarget = navState.ruin;
            }
        }
        if(curObjective == null && !enemyTowers.isEmpty()) {
            int minDist = Integer.MAX_VALUE;
            for(Ruin r : enemyTowers) {
                //if(checkedTower.getBit(r.location) || enemyDefenseTowers.getBit(r.location)) continue;
                if(checkedTower.contains(r.location) || enemyDefenseTowers.contains(r.location)) continue;
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
            SymmetryType[] possible = Symmetry.possibleSymmetry();
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
            double d = rng.nextDouble();
            if(d <= 0.5) {
                MapLocation closestCorner = Soldier.closestCorner();
                curObjective = new MapLocation(Math.abs(staticRC.getMapWidth() - 1 - closestCorner.x), Math.abs(staticRC.getMapHeight() - 1 - closestCorner.y));
            }
            else {
                curObjective = new MapLocation(rng.nextInt(staticRC.getMapHeight() - 6) + 3, rng.nextInt(staticRC.getMapHeight() - 6) + 3);
            }
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
            if(staticRC.canAttack(bestLoc)) {
                staticRC.attack(bestLoc);
                MopperMicro.targetedClearMicro(MopperMicro.customLocationTo(staticRC.getLocation(), nearbyRuin), nearbyRuin);
            }
            else {
                MopperMicro.targetedMopperMicro(MopperMicro.customLocationTo(staticRC.getLocation(), bestLoc), bestLoc);
            }
        }
        else {
            //MopperMicro.targetedMopperMicro(MopperMicro.customLocationTo(staticRC.getLocation(), nearbyRuin), nearbyRuin);
            MopperMicro.targetedClearMicro(MopperMicro.customLocationTo(staticRC.getLocation(), nearbyRuin), nearbyRuin);
        }
        if(bestLoc != null && staticRC.canAttack(bestLoc)) staticRC.attack(bestLoc);
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

    //priorities
    //1. within action radius
    //2. has enemy
    //3. has enemy and within action radius
    //4. close and has enemy
    public static MapLocation bestClear(MapLocation ruin) throws GameActionException {
        int minDist = Integer.MAX_VALUE;
        MapLocation bestLoc = null;
        int bestScore = -1;
        for(MapInfo tile : staticRC.senseNearbyMapInfos(ruin, 8)) {
            int dist = tile.getMapLocation().distanceSquaredTo(staticRC.getLocation());
            int score = MopperMicro.determineScoreClear(tile.getMapLocation(), tile);
            if(score > bestScore || (score == bestScore && dist < minDist)) {
                bestScore = score;
                minDist = dist;
                bestLoc = tile.getMapLocation();
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

        if(seenEnemyTower != null && Soldier.isDefenseTower(seenEnemyTower)) {
            //enemyDefenseTowers.setBit(seenEnemyTower.getLocation(), true);
            enemyDefenseTowers.add(seenEnemyTower.getLocation());
        }

        closestUnseenRuin = Symmetry.closestUnseenRuin();

//        for(MapInfo tile : nearbyTiles) {
//            Utilities.attemptCompleteResourcePattern(tile.getMapLocation());
//            if(knownSymmetry == RobotPlayer.SymmetryType.Unknown) {
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
        int price = Clock.getBytecodesLeft();
        MopperUtil.scanNearbyTiles();
        System.out.println(price - Clock.getBytecodesLeft());
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
