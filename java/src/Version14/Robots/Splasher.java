package Version14.Robots;

import Version14.Misc.Communication;
import Version14.Micro.Micro;
import Version14.Micro.SplasherMicro;
import Version14.Pathfinding.Pathfinding;
import Version14.Misc.Ruin;
import Version14.Utility.BitBoard;
import Version14.Utility.Utilities;
import Version14.Utility.splasherUtil;
import Version7.MopperMicro;
import battlecode.common.*;

import static Version14.Misc.Communication.*;

import static Version14.RobotPlayer.*;

enum splasherStates {
    navigate, refill, contest
}


//TODO: ALTER SPLASHER MCIRO so that ally squares are negative, neutral scores 0,
// and enemy squares positive so that we won't randomly attack neutral
// also - call best attack first, and if null, dont go into micro mode
// if not null, go into micro mode, targeting the best attack location - this way avoid the issue of getting stuck on
// rogue enemy tiles

public class Splasher {

    private static final int PAINT_TOWER_REFRESH = 25;
    private static final int PAINT_AVERAGE_REFRESH = 15;
    public static int turnsSincePaintRefresh = 0;
    private static final int CLOSEST_EMPTY_RUIN_REFRESH = 3;

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

    static boolean correctSymmetry = false;

    private static MapLocation home;

    static boolean exploredSymmetry = false;

    public static BitBoard enemyDefenseTowers;

    static navState navTarget;

    public static BitBoard checkedRuin;




    public static void runSplasher() throws GameActionException {
        if(staticRC.getRoundNum() > 1000) {
            endRefillThreshold = 250;
        }
        if(turnCount == 1) {
            home = staticRC.getLocation();
            checkedRuin = new BitBoard();
            enemyDefenseTowers = new BitBoard();
        }
        updateInfo();
        updateState();
        if(staticRC.isMovementReady() || staticRC.isActionReady()) {
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
        if(Clock.getBytecodesLeft() > 7000) {
            splasherUtil.refreshPaintAverages();
        }
        if(curObjective== null)staticRC.setIndicatorString(state.toString());
        else staticRC.setIndicatorString(state + " : " + curObjective);
    }

    //attempts to navigate to a known location - enemy average, usually
    public static void navigate() throws GameActionException {
        if(turnCount % 200 == 0) {
            exploredSymmetry = false;
        }
        MapLocation curLoc = staticRC.getLocation();
        if(curObjective != null && curLoc.distanceSquaredTo(curObjective) < 8) {
            if(navTarget != null) {
                switch (navTarget) {
                    case navState.horizontal, navState.rotational, navState.vertical -> exploredSymmetry = true;
                    case navState.ruin -> {checkedRuin.setBit(curObjective, true);}
                }
            }
            curObjective = null;
            navTarget = null;
        }
        else if(curObjective != null && knownSymmetry != Symmetry.Unknown && !correctSymmetry && navTarget != null) {
            switch(navTarget) {
                case navState.horizontal -> {
                    if(knownSymmetry != Symmetry.Horizontal) {
                        switch(knownSymmetry) {
                            case Rotational:
                                curObjective = new MapLocation(staticRC.getMapWidth() - 1 - home.x, staticRC.getMapHeight() - 1 - home.y);
                                navTarget = navState.rotational;
                                break;
                            case Vertical:
                                curObjective = new MapLocation(staticRC.getMapWidth() - 1 - home.x, home.y);
                                navTarget = navState.vertical;
                                break;
                        }
                    }
                }
                case navState.rotational -> {
                    if(knownSymmetry != Symmetry.Rotational) {
                        switch(knownSymmetry) {
                            case Vertical:
                                curObjective = new MapLocation(staticRC.getMapWidth() - 1 - home.x, home.y);
                                navTarget = navState.vertical;
                                break;
                            case Horizontal:
                                curObjective = new MapLocation(home.x, staticRC.getMapHeight() - 1 - home.y);
                                navTarget = navState.horizontal;
                                break;
                        }
                    }
                }
                case navState.vertical -> {
                    if(knownSymmetry != Symmetry.Vertical) {
                        switch(knownSymmetry) {
                            case Rotational:
                                curObjective = new MapLocation(staticRC.getMapWidth() - 1 - home.x, staticRC.getMapHeight() - 1 - home.y);
                                navTarget = navState.rotational;
                                break;
                            case Horizontal:
                                curObjective = new MapLocation(home.x, staticRC.getMapHeight() - 1 - home.y);
                                navTarget = navState.horizontal;
                                break;
                        }
                    }
                }
            }
            correctSymmetry = true;
        }
        if((curObjective == null || navTarget == navState.ruin) && !enemyTowers.isEmpty()) {
            int minDist = Integer.MAX_VALUE;
            for(Ruin r : enemyTowers) {
                if(enemyDefenseTowers.getBit(r.location)) continue;
                if(r.location.distanceSquaredTo(curLoc) < minDist) {
                    minDist = r.location.distanceSquaredTo(curLoc);
                    curObjective = r.location;
                    navTarget = navState.tower;
                }
            }
        }
        //if we know of any unclaimed ruins, lets try to help out there
        if(curObjective == null && !unclaimedRuins.isEmpty()) {
            int minDist = Integer.MAX_VALUE;
            for(Ruin r : unclaimedRuins) {
                if(checkedRuin.getBit(r.location)) continue;
                if(r.location.distanceSquaredTo(curLoc) < minDist) {
                    minDist = r.location.distanceSquaredTo(curLoc);
                    curObjective = r.location;
                    navTarget = navState.ruin;
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
                    curObjective = new MapLocation(home.x, staticRC.getMapHeight() - 1 - home.y);
                    navTarget = navState.horizontal;
                    break;
                case Rotational:
                    curObjective = new MapLocation(staticRC.getMapWidth() - 1 - home.x, staticRC.getMapHeight() - 1 - home.y);
                    navTarget = navState.rotational;
                    break;
                case Vertical:
                    curObjective = new MapLocation(staticRC.getMapWidth() - 1 - home.x, home.y);
                    navTarget = navState.vertical;
                    break;
            }
        }
        //last resort - just go to the center
        if(curObjective == null) {
            double d = rng.nextDouble();
            if(d <= 0.6) {
                MapLocation closestCorner = Soldier.closestCorner();
                curObjective = new MapLocation(Math.abs(staticRC.getMapWidth() - 1 - closestCorner.x), Math.abs(staticRC.getMapHeight() - 1 - closestCorner.y));
                System.out.println("hello!");
            }
            else {
                curObjective = new MapLocation(rng.nextInt(staticRC.getMapHeight() - 6) + 3, rng.nextInt(staticRC.getMapHeight() - 6) + 3);
            }
            navTarget = navState.random;
        }
        //MOVE TO OBJECTIVE
        //int price = Clock.getBytecodesLeft();
        Direction dir = Pathfinding.bugBFS(curObjective);
        //System.out.println(price - Clock.getBytecodesLeft());
        if(staticRC.canMove(dir)) staticRC.move(dir);
        staticRC.setIndicatorString("navigate! " + curObjective + " : " + navTarget);
    }

    //attempts to return to a known allied paint tower and refill
    public static void refill() throws GameActionException {
        //navigate to the nearest paint tower
        //if that tower is surrounded, navigate to the next nearest
        if(fillingStation == null || staticRC.canSenseLocation(fillingStation) && (staticRC.senseNearbyRobots(fillingStation, 2, staticRC.getTeam()).length > 2 || staticRC.canSenseLocation(fillingStation) && staticRC.senseRobotAtLocation(fillingStation) == null)) {
            fillingStation = nextNearestPaintTower();
            if(fillingStation == null){
                curObjective = null;
                explore();
                return;
            }
        }
        if(staticRC.getLocation().isWithinDistanceSquared(fillingStation, 9)) {
            Micro.refillingMicro(fillingStation);
        }
        else {
            if(staticRC.isMovementReady()) {
                Direction dir = Pathfinding.bugBFS(fillingStation);
                if(staticRC.canMove(dir)) staticRC.move(dir);
            }
        }
    }

    //used only when we are refilling and no of know paint towers
    public static void explore() throws GameActionException {
        if(curObjective == null || staticRC.getLocation().distanceSquaredTo(curObjective) <= 8) curObjective = new MapLocation(rng.nextInt(staticRC.getMapWidth() - 6) + 3, rng.nextInt(staticRC.getMapHeight() - 6) + 3);
        if(staticRC.isMovementReady()) {
            Direction dir = Pathfinding.bugBFS(curObjective);
            if (staticRC.canMove(dir)) staticRC.move(dir);
        }
    }

    //returns the closest paint tower that is not currently the filling station or the nearestPaintTower
    private static MapLocation nextNearestPaintTower() throws GameActionException {
        MapLocation curLoc = staticRC.getLocation();
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
        int minScore = 3;
        if(staticRC.isActionReady()) {
            MapLocation bestAttack = splasherUtil.bestAttack(seenEnemyTower != null, minScore);
            if (bestAttack != null) SplasherMicro.integratedSplasherMicro(seenEnemyTower != null, bestAttack);
            else {
                navigate();
            }
        }
        else {
            if(averageEnemyPaint != null) SplasherMicro.runTargetedSplasherMicro(MopperMicro.customLocationTo(staticRC.getLocation(), averageEnemyPaint), averageEnemyPaint);
            else if(seenEnemyTower != null) SplasherMicro.runTargetedSplasherMicro(MopperMicro.customLocationTo(staticRC.getLocation(), seenEnemyTower.getLocation()), seenEnemyTower.getLocation());
        }
    }

    //determines the current state for the splashers
    public static void updateState() throws GameActionException {
        state = splasherStates.navigate;

        if((staticRC.getPaint() <= refillThreshold || (state == splasherStates.refill && staticRC.getPaint() <= endRefillThreshold)) && (nearestPaintTower != null || staticRC.getPaint() < 50)) {
            state = splasherStates.refill;
            fillingStation = nearestPaintTower;
            //go back to nearest tower
            return;
        }
        else if(state == splasherStates.refill && staticRC.getPaint() >= endRefillThreshold){
            state = splasherStates.navigate;
            fillingStation = null;
        }
        if((averageEnemyPaint != null && numEnemyTiles > 1) || (seenEnemyTower != null && staticRC.getHealth() > 100)) {
            state = splasherStates.contest;
        }
    }

    //updates the local information necessary for the splasher to run its turn
    public static void updateInfo() throws GameActionException {
        MapLocation curLoc = staticRC.getLocation();
        //gets the nearest paint tower, updating every so often
        if(turnCount % PAINT_TOWER_REFRESH == 0) {
            for (Ruin r : alliedPaintTowers) {
                if (nearestPaintTower == null || curLoc.distanceSquaredTo(r.location) < distanceToNearestPaintTower) {
                    distanceToNearestPaintTower = curLoc.distanceSquaredTo(r.location);
                    nearestPaintTower = r.location;
                }
            }
        }
        if(turnCount % CLOSEST_EMPTY_RUIN_REFRESH == 0 || turnCount == 1) {
            nearestUnfilledRuin = closestUnclaimedRuin();
        }
        //if(turnCount % PAINT_AVERAGE_REFRESH == 0) {
        if(turnCount == 1) {
            splasherUtil.refreshPaintAverages();
        }
        if(seenEnemyTower != null && Soldier.isDefenseTower(seenEnemyTower)) {
            enemyDefenseTowers.setBit(seenEnemyTower.getLocation(), true);
        }
        //System.out.println(price - Clock.getBytecodesLeft());
    }

    //unrolled version is in splasherUtil
    public static void refreshPaintAverages() throws GameActionException {
        numEnemyTiles = 0;
        averageEnemyPaint = null;
        //boolean hasSeenNoWall = false;
        int x = 0;
        int y = 0;
        //now, check if we can see any enemy tiles
        for (MapInfo tile : nearbyTiles) {
            Utilities.attemptCompleteResourcePattern(tile.getMapLocation());
            if (knownSymmetry == Symmetry.Unknown) {
                map[tile.getMapLocation().x][tile.getMapLocation().y] = (tile.isPassable()) ? 1 : (tile.isWall()) ? 2 : 3;
                if (!tile.isPassable()) Utilities.validateSymmetry(tile.getMapLocation(), tile.hasRuin());
            }
            if (tile.getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(tile.getMapLocation())) {
                x += tile.getMapLocation().x;
                y += tile.getMapLocation().y;
                numEnemyTiles++;
            }
        }
        averageEnemyPaint = (numEnemyTiles == 0) ? null : new MapLocation(x / numEnemyTiles, y / numEnemyTiles);
    }

    //UTILITY METHODS
    private static MapLocation closestUnclaimedRuin() {
        int minDist = Integer.MAX_VALUE;
        Ruin r = null;
        boolean isOccupied = false;
        for(Ruin ruin : Communication.unclaimedRuins) {
            if(ruin.location.distanceSquaredTo(staticRC.getLocation()) < minDist) {
                minDist = ruin.location.distanceSquaredTo(staticRC.getLocation());
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
