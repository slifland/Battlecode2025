package Version12;

import battlecode.common.*;

import static Version12.splasherUtil.*;
import static Version12.Communication.*;

import static Version12.RobotPlayer.*;

enum splasherStates {
    navigate, refill, contest
}

public class Splasher {

    private static final int PAINT_TOWER_REFRESH = 25;
    private static final int PAINT_AVERAGE_REFRESH = 15;
    static int turnsSincePaintRefresh = 0;
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

    private static MapLocation home;

    static boolean exploredSymmetry = false;

    static int refreshedCount = 0;



    public static void runSplasher() throws GameActionException {
        if(staticRC.getRoundNum() > 1000) {
            endRefillThreshold = 250;
        }
        if(turnCount == 1) home = staticRC.getLocation();
        updateInfo();
        updateState();
        switch(state) {
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
        if(turnsSincePaintRefresh >= PAINT_AVERAGE_REFRESH || Clock.getBytecodesLeft() > 4000) {
            refreshPaintAverages();
            turnsSincePaintRefresh = 0;
            //refreshedCount++;
            //System.out.println(turnCount + " : " + refreshedCount);
        }
        else {
            turnsSincePaintRefresh++;
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
        //lets try and see if there are any unclaimed ruins that need our help
        //finally, navigate to the opposite of where we spawned
        if(curObjective == null && !exploredSymmetry) {
            Symmetry[] possible = Utilities.possibleSymmetry();
            int sym = rng.nextInt(possible.length);
            switch(possible[sym]) {
                case Horizontal:
                    curObjective = new MapLocation(home.x, staticRC.getMapHeight() - 1 - home.y);
                    break;
                case Rotational:
                    curObjective = new MapLocation(staticRC.getMapWidth() - 1 - home.x, staticRC.getMapHeight() - 1 - home.y);
                    break;
                case Vertical:
                    curObjective = new MapLocation(staticRC.getMapWidth() - 1 - home.x, home.y);
                    break;
            }
        }
        if(knownSymmetry != Symmetry.Unknown && curObjective != null && staticRC.getLocation().isWithinDistanceSquared(curObjective, 5) && !exploredSymmetry) {
            exploredSymmetry = true;
            curObjective = null;
        }
        //next, if we have enemy towers, go there
        //last resort - just go to the center
        if(curObjective == null) {
            //curObjective = new MapLocation(staticRC.getMapWidth() / 2, staticRC.getMapHeight() / 2);
            curObjective = new MapLocation(rng.nextInt(staticRC.getMapHeight() - 6) + 3, rng.nextInt(staticRC.getMapHeight() - 6) + 3);
        }
        //MOVE TO OBJECTIVE
        //int price = Clock.getBytecodesLeft();
        if(staticRC.isMovementReady()) {
            Direction dir = Pathfinding.bugBFS(curObjective);
            if (staticRC.canMove(dir)) staticRC.move(dir);
        }
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
        SplasherMicro.integratedSplasherMicro(seenEnemyTower  != null);
//        MapLocation toAttack = cheapBestAttack(seenEnemyTower != null, Math.min(4, numEnemyTiles));
//        //no attacks that good
//        if(toAttack == null) {
//            if(staticRC.canAttack(seenEnemyTower.getLocation())) {
//                staticRC.attack(seenEnemyTower.getLocation());
//            }
//            else {
//                Direction dir = Micro.runMicro(allyRobots.length - enemyRobots.length > 5);
//                if (staticRC.canMove(dir)) staticRC.move(dir);
//                toAttack = cheapBestAttack(seenEnemyTower != null, Math.min(4, numEnemyTiles));
//                if (toAttack != null && staticRC.canAttack(toAttack)) {
//                    staticRC.attack(toAttack);
//                }
//                if (toAttack == null && staticRC.canAttack(seenEnemyTower.getLocation())) {
//                    staticRC.attack(seenEnemyTower.getLocation());
//                }
//            }
//        }
//        else if (staticRC.canAttack(toAttack)) {
//            staticRC.attack(toAttack);
//        }
//        if(staticRC.isMovementReady()) {
//            Direction dir = Micro.runMicro();
//            if(staticRC.canMove(dir)) staticRC.move(dir);
//        }
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
        if(averageEnemyPaint != null && numEnemyTiles > 1) {
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
        //System.out.println(price - Clock.getBytecodesLeft());
    }

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
