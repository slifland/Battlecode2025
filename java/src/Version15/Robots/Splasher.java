package Version15.Robots;

import Version15.Micro.MopperMicro;
import Version15.Misc.Communication;
import Version15.Micro.Micro;
import Version15.Micro.SplasherMicro;
import Version15.Pathfinding.Pathfinding;
import Version15.Misc.Ruin;
import Version15.Utility.BitBoard;
import Version15.Utility.FastIterableLocSet;
import Version15.Utility.Utilities;
import Version15.Utility.splasherUtil;
import battlecode.common.*;
import Version15.Utility.Symmetry.SymmetryType;
import Version15.Utility.Symmetry;

import static Version15.Misc.Communication.*;

import static Version15.RobotPlayer.*;
import static Version15.Robots.Soldier.closestCorner;
import static Version15.Utility.Symmetry.*;

enum splasherStates {
    navigate, refill, contest, hunt
}


public class Splasher {

    private static final int PAINT_TOWER_REFRESH = 25;
    private static final int PAINT_AVERAGE_REFRESH = 15;
    public static int turnsSincePaintRefresh = 0;
    private static final int CLOSEST_EMPTY_RUIN_REFRESH = 3;

    private static boolean splasherInitialized = false;
    private static MapLocation curObjective;
    private static splasherStates state;
    public static RobotInfo seenEnemyTower; //records whether we can see an enemy tower, and if so, the enemy tower's info
    public static int numEnemyTiles; //records the number of enemy tiles we can see
    private static MapLocation fillingStation;
    private static MapLocation nearestPaintTower;
    public static int distanceToNearestPaintTower = -1;
    //final variables
    private static final int refillThreshold = 50;
    private static int endRefillThreshold = 200;

    public static MapLocation nearestUnfilledRuin;
    public static MapLocation averageEnemyPaint;

    public static MapLocation closestUnseenRuin;

    static boolean correctSymmetry = false;

    private static MapLocation home;

    static boolean exploredSymmetry = false;

    //public static BitBoard enemyDefenseTowers;
    public static FastIterableLocSet enemyDefenseTowers;

    static navState navTarget;

    //public static BitBoard checkedRuin;
    public static FastIterableLocSet checkedRuin;




    public static void runSplasher() throws GameActionException {
        if(rc.getRoundNum() > 1000) {
            endRefillThreshold = 250;
        }
        if(!splasherInitialized) {
            home = rc.getLocation();
            //checkedRuin = new BitBoard();
            checkedRuin = new FastIterableLocSet();
            //enemyDefenseTowers = new BitBoard();
            enemyDefenseTowers = new FastIterableLocSet();
        }

        updateInfo();
        updateState();
        if(rc.isMovementReady() || rc.isActionReady()) {
            switch (state) {
                case navigate:
                    navigate();
                    break;
                case refill:
                    refill();
                    break;
                case contest:
                    contest();
                    break;
            }
        }
        if(Clock.getBytecodesLeft() > 6500) {
            //int price = Clock.getBytecodesLeft();
            splasherUtil.refreshPaintAverages();
            //System.out.println(price - Clock.getBytecodesLeft());
        }
//        if(curObjective== null)rc.setIndicatorString(state.toString());
//        else rc.setIndicatorString(state + " : " + curObjective);
    }


    //attempts to navigate to a known location - enemy average, usually
    public static void navigate() throws GameActionException {
        if(turnCount % 200 == 0) {
            exploredSymmetry = false;
        }
        MapLocation curLoc = rc.getLocation();
        if(curObjective != null && curLoc.distanceSquaredTo(curObjective) < 8) {
            if(navTarget != null) {
                switch (navTarget) {
                    case horizontal, rotational, vertical -> exploredSymmetry = true;
                    //case ruin -> {checkedRuin.setBit(curObjective, true);}
                    case ruin -> checkedRuin.add(curObjective);
                }
            }
            curObjective = null;
            navTarget = null;
        }
        else if(curObjective != null && knownSymmetry != SymmetryType.Unknown && !correctSymmetry && navTarget != null) {
            switch(navTarget) {
                case horizontal -> {
                    if(knownSymmetry != SymmetryType.Horizontal) {
                        switch(knownSymmetry) {
                            case Rotational:
                                curObjective = new MapLocation(rc.getMapWidth() - 1 - home.x, rc.getMapHeight() - 1 - home.y);
                                navTarget = navState.rotational;
                                break;
                            case Vertical:
                                curObjective = new MapLocation(rc.getMapWidth() - 1 - home.x, home.y);
                                navTarget = navState.vertical;
                                break;
                        }
                    }
                }
                case rotational -> {
                    if(knownSymmetry != SymmetryType.Rotational) {
                        switch(knownSymmetry) {
                            case Vertical:
                                curObjective = new MapLocation(rc.getMapWidth() - 1 - home.x, home.y);
                                navTarget = navState.vertical;
                                break;
                            case Horizontal:
                                curObjective = new MapLocation(home.x, rc.getMapHeight() - 1 - home.y);
                                navTarget = navState.horizontal;
                                break;
                        }
                    }
                }
                case vertical -> {
                    if(knownSymmetry != SymmetryType.Vertical) {
                        switch(knownSymmetry) {
                            case Rotational:
                                curObjective = new MapLocation(rc.getMapWidth() - 1 - home.x, rc.getMapHeight() - 1 - home.y);
                                navTarget = navState.rotational;
                                break;
                            case Horizontal:
                                curObjective = new MapLocation(home.x, rc.getMapHeight() - 1 - home.y);
                                navTarget = navState.horizontal;
                                break;
                        }
                    }
                }
                default ->{
                    switch(knownSymmetry) {
                        case Rotational:
                            curObjective = new MapLocation(rc.getMapWidth() - 1 - home.x, rc.getMapHeight() - 1 - home.y);
                            navTarget = navState.rotational;
                            break;
                        case Horizontal:
                            curObjective = new MapLocation(home.x, rc.getMapHeight() - 1 - home.y);
                            navTarget = navState.horizontal;
                            break;
                        case Vertical:
                            curObjective = new MapLocation(rc.getMapWidth() - 1 - home.x, home.y);
                            navTarget = navState.vertical;
                            break;
                    }
                }
            }
            correctSymmetry = true;
        }
        if((curObjective == null) && !enemyTowers.isEmpty()) {
            int minDist = Integer.MAX_VALUE;
            for(Ruin r : enemyTowers) {
                //if(enemyDefenseTowers.getBit(r.location)) continue;
                if(enemyDefenseTowers.contains(r.location)) continue;
                if(r.location.distanceSquaredTo(curLoc) < minDist) {
                    minDist = r.location.distanceSquaredTo(curLoc);
                    curObjective = r.location;
                    navTarget = navState.tower;
                }
            }
        }
        //if we know of any unclaimed ruins, lets try to help out there
        if(curObjective == null && (!unclaimedRuins.isEmpty() || closestUnseenRuin != null)) {
            int minDist = Integer.MAX_VALUE;
            for(Ruin r : unclaimedRuins) {
                //if(checkedRuin.getBit(r.location)) continue;
                if(checkedRuin.contains(r.location)) continue;
                if(r.location.distanceSquaredTo(curLoc) < minDist) {
                    minDist = r.location.distanceSquaredTo(curLoc);
                    curObjective = r.location;
                    navTarget = navState.ruin;
                }
            }
            if(closestUnseenRuin != null && rc.getLocation().distanceSquaredTo(closestUnseenRuin) < minDist) {
                curObjective = closestUnseenRuin;
                navTarget = navState.ruin;
                rc.setIndicatorLine(rc.getLocation(), closestUnseenRuin, 0, 255, 0);
            }
        }
        //next, if we have enemy towers, go there
        //finally, navigate to the opposite of where we spawned
        if(curObjective == null && !exploredSymmetry) {
            SymmetryType[] possible = Symmetry.possibleSymmetry();
            int sym = rng.nextInt(possible.length);
            switch(possible[sym]) {
                case Horizontal:
                    curObjective = new MapLocation(home.x, rc.getMapHeight() - 1 - home.y);
                    navTarget = navState.horizontal;
                    break;
                case Rotational:
                    curObjective = new MapLocation(rc.getMapWidth() - 1 - home.x, rc.getMapHeight() - 1 - home.y);
                    navTarget = navState.rotational;
                    break;
                case Vertical:
                    curObjective = new MapLocation(rc.getMapWidth() - 1 - home.x, home.y);
                    navTarget = navState.vertical;
                    break;
            }
        }
        //last resort - just go to the center
        if(curObjective == null) {
            double d = rng.nextDouble();
            if(d <= 0.6) {
                MapLocation closestCorner = closestCorner();
                curObjective = new MapLocation(Math.abs(rc.getMapWidth() - 1 - closestCorner.x), Math.abs(rc.getMapHeight() - 1 - closestCorner.y));
                //System.out.println("hello!");
            }
            else {
                curObjective = new MapLocation(rng.nextInt(rc.getMapHeight() - 6) + 3, rng.nextInt(rc.getMapHeight() - 6) + 3);
            }
            navTarget = navState.random;
        }


        if((navTarget == navState.ruin || navTarget == navState.random) && closestUnseenRuin != null) {
            if(navTarget == navState.random) curObjective = closestUnseenRuin;
            else if(rc.getLocation().distanceSquaredTo(closestUnseenRuin) < rc.getLocation().distanceSquaredTo(curObjective)){
                curObjective = closestUnseenRuin;
                rc.setIndicatorLine(rc.getLocation(), closestUnseenRuin, 0, 255, 0);
            }
            //System.out.println(curObjective);
        }
        //MOVE TO OBJECTIVE
        //int price = Clock.getBytecodesLeft();
        Direction dir = Pathfinding.bugBFS(curObjective);
        //System.out.println(price - Clock.getBytecodesLeft());
        if(rc.canMove(dir)) rc.move(dir);
        rc.setIndicatorString("navigate! " + curObjective + " : " + navTarget);
    }

    //attempts to return to a known allied paint tower and refill
    public static void refill() throws GameActionException {
        //navigate to the nearest paint tower
        //if that tower is surrounded, navigate to the next nearest
        if(fillingStation == null || rc.canSenseLocation(fillingStation) && (rc.senseNearbyRobots(fillingStation, 2, rc.getTeam()).length > 2 || rc.canSenseLocation(fillingStation) && rc.senseRobotAtLocation(fillingStation) == null)) {
            fillingStation = nextNearestPaintTower();
            if(fillingStation == null){
                curObjective = null;
                explore();
                return;
            }
        }
        if(rc.canSenseRobotAtLocation(fillingStation) && (rc.senseRobotAtLocation(fillingStation).getType() != UnitType.LEVEL_ONE_PAINT_TOWER && rc.senseRobotAtLocation(fillingStation).getType() != UnitType.LEVEL_TWO_PAINT_TOWER && rc.senseRobotAtLocation(fillingStation).getType() != UnitType.LEVEL_THREE_PAINT_TOWER)){
            alliedPaintTowers.remove(new Ruin(fillingStation, 1, true));
            fillingStation = nextNearestPaintTower();
            if(fillingStation == null){
                if(seenEnemyTower != null) {
                    if(rc.canAttack(seenEnemyTower.getLocation())) rc.attack(seenEnemyTower.getLocation());
                    Direction dir = Micro.runMicro(true);
                    if(rc.canMove(dir)) rc.move(dir);
                    if(rc.canAttack(seenEnemyTower.getLocation())) rc.attack(seenEnemyTower.getLocation());
                }
                else{
                    explore();
                }
                return;
            }

        }
        if(rc.getLocation().isWithinDistanceSquared(fillingStation, 9)) {
            Micro.refillingMicro(fillingStation);
        }
        else {
            if(rc.isMovementReady()) {
                Direction dir = Pathfinding.bugBFS(fillingStation);
                if(rc.canMove(dir)) rc.move(dir);
            }
        }
    }

    //used only when we are refilling and no of know paint towers
    public static void explore() throws GameActionException {
        if(curObjective == null || rc.getLocation().distanceSquaredTo(curObjective) <= 8) curObjective = new MapLocation(rng.nextInt(rc.getMapWidth() - 6) + 3, rng.nextInt(rc.getMapHeight() - 6) + 3);
        if(rc.isMovementReady()) {
            Direction dir = Pathfinding.bugBFS(curObjective);
            if (rc.canMove(dir)) rc.move(dir);
        }
    }

    //returns the closest paint tower that is not currently the filling station or the nearestPaintTower
    private static MapLocation nextNearestPaintTower() throws GameActionException {
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
    public static void contest() throws GameActionException {
//        int minScore = 3;
//        if(rc.isActionReady()) {
//            MapLocation bestAttack = splasherUtil.bestAttack(seenEnemyTower != null, minScore);
//            if (bestAttack != null) SplasherMicro.integratedSplasherMicro(seenEnemyTower != null, bestAttack);
//            else {
//                navigate();
//            }
//        }
//        else {
//            if(seenEnemyTower != null && rc.getLocation().isWithinDistanceSquared(seenEnemyTower.getLocation(), seenEnemyTower.getType().actionRadiusSquared)) {
//                MapLocation target = rc.getLocation().add(rc.getLocation().directionTo(seenEnemyTower.getLocation()).opposite());
//                SplasherMicro.runTargetedSplasherMicro(MopperMicro.customLocationTo(rc.getLocation(), target), target);
//            }
//            else if(averageEnemyPaint != null) SplasherMicro.runTargetedSplasherMicro(MopperMicro.customLocationTo(rc.getLocation(), averageEnemyPaint), averageEnemyPaint);
//            else if(seenEnemyTower != null) SplasherMicro.runTargetedSplasherMicro(MopperMicro.customLocationTo(rc.getLocation(), seenEnemyTower.getLocation()), seenEnemyTower.getLocation());
//        }
        SplasherMicro.integratedSplasherMicro(seenEnemyTower != null);
    }

    //determines the current state for the splashers
    public static void updateState() throws GameActionException {
        //state = (rc.getRoundNum() > 70 + (adjustedMapSize * 12)) ? splasherStates.navigate : splasherStates.hunt;
        state = splasherStates.navigate;
        if((rc.getPaint() <= refillThreshold || (state == splasherStates.refill && rc.getPaint() <= endRefillThreshold)) && (nearestPaintTower != null || rc.getPaint() < 50)) {
            state = splasherStates.refill;
            fillingStation = nearestPaintTower;
            //go back to nearest tower
            return;
        }
        else if(state == splasherStates.refill && rc.getPaint() >= endRefillThreshold){
            state = splasherStates.navigate;
            fillingStation = null;
        }
        if((averageEnemyPaint != null && numEnemyTiles > 2) || (seenEnemyTower != null && !Soldier.isDefenseTower(seenEnemyTower))) {
            state = splasherStates.contest;
        }
    }

    //updates the local information necessary for the splasher to run its turn
    public static void updateInfo() throws GameActionException {
        closestUnseenRuin = closestUnseenRuin();
        MapLocation curLoc = rc.getLocation();
        //gets the nearest paint tower, updating every so often
        if(turnCount % PAINT_TOWER_REFRESH == 0) {
            for (Ruin r : alliedPaintTowers) {
                if (nearestPaintTower == null || curLoc.distanceSquaredTo(r.location) < distanceToNearestPaintTower) {
                    distanceToNearestPaintTower = curLoc.distanceSquaredTo(r.location);
                    nearestPaintTower = r.location;
                }
            }
        }
//        for(MapInfo tile : nearbyTiles) {
//            if(tile.getPaint().isEnemy()) {
//                if (!Utilities.basicLocationIsBehindWall(tile.getMapLocation()))
//                    rc.setIndicatorDot(tile.getMapLocation(), 0, 255, 0);
//            }
//        }
        if(turnCount % CLOSEST_EMPTY_RUIN_REFRESH == 0 || !splasherInitialized) {
            nearestUnfilledRuin = closestUnclaimedRuin();
        }
        //if(turnCount % PAINT_AVERAGE_REFRESH == 0) {
        if(!splasherInitialized) {
            splasherUtil.refreshPaintAverages();
            splasherInitialized = true;
        }
        if(seenEnemyTower != null && Soldier.isDefenseTower(seenEnemyTower)) {
            //enemyDefenseTowers.setBit(seenEnemyTower.getLocation(), true);
            enemyDefenseTowers.add(seenEnemyTower.getLocation());
        }
        //System.out.println(price - Clock.getBytecodesLeft());
    }

    //unrolled version is in splasherUtil
//    public static void refreshPaintAverages() throws GameActionException {
//        numEnemyTiles = 0;
//        averageEnemyPaint = null;
//        //boolean hasSeenNoWall = false;
//        int x = 0;
//        int y = 0;
//        //now, check if we can see any enemy tiles
//        for (MapInfo tile : nearbyTiles) {
//            Utilities.attemptCompleteResourcePattern(tile.getMapLocation());
//            if (knownSymmetry == SymmetryType.Unknown) {
//                map[tile.getMapLocation().x][tile.getMapLocation().y] = (tile.isPassable()) ? 1 : (tile.isWall()) ? 2 : 3;
//                if (!tile.isPassable()) Utilities.validateSymmetry(tile.getMapLocation(), tile.hasRuin());
//            }
//            if (tile.getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(tile.getMapLocation())) {
//                x += tile.getMapLocation().x;
//                y += tile.getMapLocation().y;
//                numEnemyTiles++;
//            }
//        }
//        averageEnemyPaint = (numEnemyTiles == 0) ? null : new MapLocation(x / numEnemyTiles, y / numEnemyTiles);
//    }

    //UTILITY METHODS
    private static MapLocation closestUnclaimedRuin() {
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
