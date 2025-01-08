package Version3;

import battlecode.common.*;

import java.util.Arrays;

import static Version3.RobotPlayer.directions;
import static Version3.RobotPlayer.rng;

enum splasherStates {
    contestedRuin, attack, refill, placeholder
}

public class Splasher {
    //keeps track of the soldiers current objective - starts as going to a random place on map, can change
    private static MapLocation curObjective = null;

    //state tracker - prevState used for when refilling
    private static splasherStates state;
    private static splasherStates prevState;
    //used for hugging the wall
    private static boolean clockwise;
    //used for refilling
    private static MapLocation nearestPaintTower = null;

    //information updated each turn
    private static RobotInfo[] enemyRobots;
    private static RobotInfo[] allyRobots;
    private static MapInfo[] nearbyTiles;

    public static void runSplasher(RobotController rc) throws GameActionException {
        updateInfo(rc);
        updateState(rc);
        switch(state) {
            case contestedRuin:
                fightContestedRuin(rc);
                break;
            case attack:
                attack(rc);
                break;
            case refill:
                refill(rc);
                break;
            default:
                break;
        }
        updateInfo(rc);
        updateState(rc);
        if(state != null && curObjective != null) rc.setIndicatorString(state.toString() + " : " + curObjective.toString());
        else if(state != null) rc.setIndicatorString(state.toString());
        // Try to paint beneath us as we walk to avoid paint penalties.
        // Avoiding wasting paint by re-painting our own tiles.
        MapInfo currentTile = rc.senseMapInfo(rc.getLocation());
        boolean currentTileIsSecondary = Utilities.getColorFromOriginPattern(rc.getLocation(), rc.getResourcePattern());
        if ((!currentTile.getPaint().isAlly() || currentTileIsSecondary != currentTile.getPaint().isSecondary())
                && rc.canAttack(rc.getLocation()) && rc.getPaint() > 5){
            rc.attack(rc.getLocation(), currentTileIsSecondary);
        }
    }

    public static void fightContestedRuin(RobotController rc) throws GameActionException {

    }

    //attempting to attack the tower we can see
    public static void attack(RobotController rc) throws GameActionException {
        if(rc.isActionReady()) {
            MapLocation toAttack = bestAttack(rc);
            if(rc.canAttack(toAttack)) {
                rc.attack(toAttack);
            }
            else if(rc.canAttack(curObjective)) {
                rc.attack(curObjective, Utilities.getColorFromOriginPattern(curObjective, rc.getResourcePattern()));
            }
            else if(rc.senseMapInfo(rc.getLocation()).getPaint() == PaintType.EMPTY) {
                if(rc.canAttack(rc.getLocation())) {
                    rc.attack(rc.getLocation(), Utilities.getColorFromOriginPattern(rc.getLocation(), rc.getResourcePattern()));
                }
            }
        }
        if(!(isSafeFromTower(rc, rc.getLocation()) || !isEnemyTile(rc.senseMapInfo(rc.getLocation())))) {
            if(curObjective != null) {
                if(rc.canMove(rc.getLocation().directionTo(curObjective).opposite())) {
                    rc.move(rc.getLocation().directionTo(curObjective).opposite());
                }
            }
        }

    }

    private static MapLocation bestAttack(RobotController rc) throws GameActionException {
        int[] localSquares = new int[81];
        int[] potentialAttackSquares = new int[81];
        Arrays.fill(potentialAttackSquares, 0);
        int index = 0;
        for(MapInfo tile : rc.senseNearbyMapInfos(-1)) {
            while(index == 0 || index == 1 || index == 7 || index == 8 || index == 17 || index == 9 || index == 63 || index == 71 || index == 72 || index == 73 || index ==79 || index == 80) {
                localSquares[index] = 0;
                index++;
            }
            PaintType paint = tile.getPaint();
            //favor the enemy most, but also like empty squares
            if(paint == PaintType.EMPTY) localSquares[index]++;
            else if(paint.isAlly()) localSquares[index] = 0;
            else localSquares[index]+= 2;
            index++;
        }
        for(int i = 20; i <= 24; i++) {
            for(int j = 10; j >= 8; j--) {
                potentialAttackSquares[i] += localSquares[i+j];
                potentialAttackSquares[i] += localSquares[i-j];
            }
            for(int j = -1; j <= 1; j++) {
                potentialAttackSquares[i] += localSquares[i+j];
            }
            potentialAttackSquares[i] += (localSquares[i-18] != 2) ? localSquares[i-18] : 0;
            potentialAttackSquares[i] += (localSquares[i+18] != 2) ? localSquares[i+18] : 0;
            potentialAttackSquares[i] += (localSquares[i-2] != 2) ? localSquares[i-2] : 0;
            potentialAttackSquares[i] += (localSquares[i+2] != 2) ? localSquares[i+2] : 0;
            i++;
        }
        for(int i = 29; i <= 33; i++) {
            for(int j = 10; j >= 8; j--) {
                potentialAttackSquares[i] += localSquares[i+j];
                potentialAttackSquares[i] += localSquares[i-j];
            }
            for(int j = -1; j <= 1; j++) {
                potentialAttackSquares[i] += localSquares[i+j];
            }
            potentialAttackSquares[i] += (localSquares[i-18] != 2) ? localSquares[i-18] : 0;
            potentialAttackSquares[i] += (localSquares[i+18] != 2) ? localSquares[i+18] : 0;
            potentialAttackSquares[i] += (localSquares[i-2] != 2) ? localSquares[i-2] : 0;
            potentialAttackSquares[i] += (localSquares[i+2] != 2) ? localSquares[i+2] : 0;
            i++;
        }
        for(int i = 38; i <= 42; i++) {
            for(int j = 10; j >= 8; j--) {
                potentialAttackSquares[i] += localSquares[i+j];
                potentialAttackSquares[i] += localSquares[i-j];
            }
            for(int j = -1; j <= 1; j++) {
                potentialAttackSquares[i] += localSquares[i+j];
            }
            potentialAttackSquares[i] += (localSquares[i-18] != 2) ? localSquares[i-18] : 0;
            potentialAttackSquares[i] += (localSquares[i+18] != 2) ? localSquares[i+18] : 0;
            potentialAttackSquares[i] += (localSquares[i-2] != 2) ? localSquares[i-2] : 0;
            potentialAttackSquares[i] += (localSquares[i+2] != 2) ? localSquares[i+2] : 0;
            i++;
        }
        for(int i = 47; i <= 51; i++) {
            for(int j = 10; j >= 8; j--) {
                potentialAttackSquares[i] += localSquares[i+j];
                potentialAttackSquares[i] += localSquares[i-j];
            }
            for(int j = -1; j <= 1; j++) {
                potentialAttackSquares[i] += localSquares[i+j];
            }
            potentialAttackSquares[i] += (localSquares[i-18] != 2) ? localSquares[i-18] : 0;
            potentialAttackSquares[i] += (localSquares[i+18] != 2) ? localSquares[i+18] : 0;
            potentialAttackSquares[i] += (localSquares[i-2] != 2) ? localSquares[i-2] : 0;
            potentialAttackSquares[i] += (localSquares[i+2] != 2) ? localSquares[i+2] : 0;
            i++;
        }
        for(int i = 56; i <= 60; i++) {
            for(int j = 10; j >= 8; j--) {
                potentialAttackSquares[i] += localSquares[i+j];
                potentialAttackSquares[i] += localSquares[i-j];
            }
            for(int j = -1; j <= 1; j++) {
                potentialAttackSquares[i] += localSquares[i+j];
            }
            potentialAttackSquares[i] += (localSquares[i-18] != 2) ? localSquares[i-18] : 0;
            potentialAttackSquares[i] += (localSquares[i+18] != 2) ? localSquares[i+18] : 0;
            potentialAttackSquares[i] += (localSquares[i-2] != 2) ? localSquares[i-2] : 0;
            potentialAttackSquares[i] += (localSquares[i+2] != 2) ? localSquares[i+2] : 0;
            i++;
        }
        int highest = potentialAttackSquares[20];
        int highestIndex = 20;
        for(int i = 21; i <= 60; i++) {
            if(potentialAttackSquares[i] > highest) {
                highestIndex = i;
                highest = potentialAttackSquares[i];
            }
        }
        int offSetX, offSetY;
        if(highestIndex <= 24) offSetX = -2;
        else if(highestIndex <= 33) offSetX = -1;
        else if(highestIndex <= 42) offSetX = 0;
        else if(highestIndex <= 51) offSetX = 1;
        else offSetX = 2;
        offSetY = (highestIndex % 9) - 4;
        return new MapLocation(rc.getLocation().x + offSetX, rc.getLocation().y + offSetY);
    }

    //tries to return to nearest paint tower to refill
    public static void refill(RobotController rc) throws GameActionException {
        if(rc.getLocation().isAdjacentTo(nearestPaintTower)) {
            if(rc.canTransferPaint(nearestPaintTower, Math.max(200-rc.getPaint(), rc.senseRobotAtLocation(nearestPaintTower).paintAmount))){
                rc.transferPaint(nearestPaintTower, Math.max(200-rc.getPaint(), rc.senseRobotAtLocation(nearestPaintTower).paintAmount));
            }
        }
        else {
            Direction dir = BFS.moveTowards(rc, nearestPaintTower);
            if (dir != null && rc.canMove(dir)) {
                rc.move(dir);
            }
        }
    }


    //updates the static arrays which keep track of useful info for the robots turn - also updates nearest paint tower
    public static void updateInfo(RobotController rc) throws GameActionException {
        allyRobots = rc.senseNearbyRobots(-1, rc.getTeam());
        enemyRobots = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        nearbyTiles = rc.senseNearbyMapInfos();

        for(RobotInfo robot : allyRobots) {
            if(robot.getType() == UnitType.LEVEL_ONE_PAINT_TOWER  || robot.getType() == UnitType.LEVEL_TWO_PAINT_TOWER || robot.getType() == UnitType.LEVEL_THREE_PAINT_TOWER && robot.getTeam() == rc.getTeam()) {
                nearestPaintTower = robot.getLocation();
            }
        }
        if(nearestPaintTower != null && rc.canSenseLocation(nearestPaintTower) && rc.senseRobotAtLocation(nearestPaintTower) == null) {
            nearestPaintTower = null;
        }
    }

    //update our current state
    public static void updateState(RobotController rc) throws GameActionException {
        if(rc.getPaint() < 60 && nearestPaintTower != null) {
            if(prevState == null) prevState = state;
            state = splasherStates.refill;
            return;
        }
        if(state == splasherStates.refill && (rc.getPaint() > 150 || nearestPaintTower == null)) {
            state = prevState;
            prevState = null;
            return;
        }
        else if(state == splasherStates.refill) return;
        //check all nearby ruins
        for(MapLocation loc : rc.senseNearbyRuins(-1)) {
            //see if contested, aka if there is any enemy tiles we need to change
            if(rc.senseRobotAtLocation(loc) == null) {
                for(MapInfo tile : nearbyTiles) {
                    if(isEnemyTile(tile)) {
                        state = splasherStates.contestedRuin;
                        curObjective = loc;
                        return;
                    }
                }
            }
            //otherwise, make sure it is an enemy tower
            else if(rc.senseRobotAtLocation(loc).team != rc.getTeam()) {
                curObjective = loc;
                state = splasherStates.attack;
                return;
            }
        }
        state = splasherStates.attack;
    }
    //returns whether a location is controlled by the enemy
    public static boolean isEnemyTile(MapInfo loc) {
        PaintType temp = loc.getPaint();
        return temp == PaintType.ENEMY_PRIMARY || temp == PaintType.ENEMY_SECONDARY;
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
