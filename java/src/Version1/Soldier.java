package Version1;

import battlecode.common.*;

import static Version0.RobotPlayer.rng;

enum states {
    ruin, explore, attack
}

public class Soldier {
    /**
     * Run a single turn for a Soldier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    //keeps track of the soldiers current objective - starts as going to a random place on map, can change
    private static MapLocation curObjective = null;

    private static states state;

    public static void runSoldier(RobotController rc) throws GameActionException {
        if(state != null) rc.setIndicatorString(state.toString());
        updateInfo(rc);
        switch(state) {
            case explore:
                explore(rc);
                break;
            case ruin:
                fillRuin(rc);
                break;
            case attack:
                attack(rc);
                break;
            default:
                break;
        }
        updateInfo(rc);
        // Try to paint beneath us as we walk to avoid paint penalties.
        // Avoiding wasting paint by re-painting our own tiles.
        MapInfo currentTile = rc.senseMapInfo(rc.getLocation());
        if (!currentTile.getPaint().isAlly() && rc.canAttack(rc.getLocation())){
            rc.attack(rc.getLocation());
        }
        //otherwise, if we have a lot of paint, just paint something
        else if (rc.getPaint() > 100) {
            for(MapInfo loc : rc.senseNearbyMapInfos(rc.getType().actionRadiusSquared)){
                if(loc.getPaint() == PaintType.EMPTY && rc.canAttack(loc.getMapLocation()) && !loc.isWall() && !loc.hasRuin()){
                    rc.attack(loc.getMapLocation());
                    rc.setIndicatorString(state.toString() + " : " + loc.toString());
                }
            }
        }
        //lets try and transfer some paint, if we can
        for(RobotInfo ally : rc.senseNearbyRobots(2, rc.getTeam())) {
            if(ally.getType().isTowerType() && rc.canTransferPaint(ally.getLocation(), -50)) {
                rc.transferPaint(ally.getLocation(), -50);
            }
        }
    }

    //attempt to move to the random location we have been assigned, or choose a new random location
    public static void explore(RobotController rc) throws GameActionException {
        Direction d = rc.getLocation().directionTo(curObjective);
        if(rc.canMove(d)) {
            rc.move(d);
        }
        else if(rc.canMove(d.rotateLeft())) {
            rc.move(d.rotateLeft());
        }
        else if(rc.canMove(d.rotateRight())) {
            rc.move(d.rotateRight());
        }
        rc.setIndicatorString(state.toString() + " : " + curObjective.toString());
    }

    //attempts to fill the ruin we can see
    public static void fillRuin(RobotController rc) throws GameActionException {
        MapLocation targetLoc = curObjective;
        Direction dir = rc.getLocation().directionTo(targetLoc);
        if (rc.canMove(dir) && rc.senseMapInfo(rc.getLocation().add(dir)).getPaint() != PaintType.ENEMY_PRIMARY && rc.senseMapInfo(rc.getLocation().add(dir)).getPaint() != PaintType.ENEMY_SECONDARY)
            rc.move(dir);
        int towerType = rng.nextInt(10);
        // Mark the pattern we need to draw to build a tower here if we haven't already.
        MapLocation shouldBeMarked = curObjective.subtract(dir);
        if ((towerType > 3 || RobotPlayer.turnCount < 400) && rc.senseMapInfo(shouldBeMarked).getMark() == PaintType.EMPTY && rc.canMarkTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, targetLoc)){
            rc.markTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, targetLoc);
            System.out.println("Trying to build a tower at " + targetLoc);
        }
        else if (rc.senseMapInfo(shouldBeMarked).getMark() == PaintType.EMPTY && rc.canMarkTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, targetLoc)){
            rc.markTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, targetLoc);
            System.out.println("Trying to build a tower at " + targetLoc);
        }
        // Fill in any spots in the pattern with the appropriate paint, but first try to paint ur own tile so u can stay alive longer.
        MapInfo currentTile = rc.senseMapInfo(rc.getLocation());
        if(currentTile.getPaint() == PaintType.EMPTY && currentTile.getMark() != currentTile.getPaint()){
            boolean useSecondaryColor = currentTile.getMark() == PaintType.ALLY_SECONDARY;
            if (rc.canAttack(currentTile.getMapLocation()))
                rc.attack(currentTile.getMapLocation(), useSecondaryColor);
        }
        else {
            for (MapInfo patternTile : rc.senseNearbyMapInfos(targetLoc, 20)) {
                if (patternTile.getMark() != patternTile.getPaint() && patternTile.getMark() != PaintType.EMPTY && patternTile.getPaint() != PaintType.ENEMY_SECONDARY && patternTile.getPaint() != PaintType.ENEMY_PRIMARY) {
                    boolean useSecondaryColor = patternTile.getMark() == PaintType.ALLY_SECONDARY;
                    if (rc.canAttack(patternTile.getMapLocation()))
                        rc.attack(patternTile.getMapLocation(), useSecondaryColor);
                }
            }
        }
        // Complete the ruin if we can.
        if (rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, targetLoc)){
            rc.completeTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, targetLoc);
            rc.setTimelineMarker("Tower built", 0, 255, 0);
            System.out.println("Built a tower at " + targetLoc + "!");
            state = null;
            curObjective = null;
        }
        else if (rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, targetLoc)){
            rc.completeTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, targetLoc);
            rc.setTimelineMarker("Tower built", 0, 255, 0);
            System.out.println("Built a tower at " + targetLoc + "!");
            state = null;
            curObjective = null;
        }
    }
    //attempting to attack the tower we can see
    public static void attack(RobotController rc) throws GameActionException {
        if(rc.senseMapInfo(rc.getLocation()).getPaint() == PaintType.EMPTY) {
            if(rc.canAttack(rc.getLocation())) {
                rc.attack(rc.getLocation());
            }
        }
        if(rc.canAttack(curObjective)) {
            rc.attack(curObjective);
        }
        if(rc.senseRobotAtLocation(curObjective) == null) {
            curObjective = null;
        }
    }

    //update our info, changing our state as necessary
    public static void updateInfo(RobotController rc) throws GameActionException {
        if(curObjective == null) {
            curObjective = new MapLocation(rng.nextInt(rc.getMapWidth() - 6) + 3, rng.nextInt(rc.getMapHeight() - 6) + 3);
            state = states.explore;
        }
        // Sense information about all visible nearby tiles.
        MapInfo[] nearbyTiles = rc.senseNearbyMapInfos();
        RobotInfo[] enemyRobots = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        // Search for a nearby ruin to complete.
        for (MapInfo tile : nearbyTiles){
            if (tile.hasRuin() && rc.senseRobotAtLocation(tile.getMapLocation()) == null){
                curObjective = tile.getMapLocation();
                state = states.ruin;
                return;
            }
        }
        if(state == states.ruin && rc.senseRobotAtLocation(curObjective) != null) {
            state = states.explore;
            curObjective = null;
        }
        //check for towers
        for(RobotInfo enemy : enemyRobots) {
            if(enemy.getType().isTowerType()) {
                curObjective = enemy.getLocation();
                state = states.attack;
                return;
            }
        }
        if(state == states.explore && rc.getLocation().distanceSquaredTo(curObjective) < 16) {
            curObjective = new MapLocation(rng.nextInt(rc.getMapWidth() - 6) + 3, rng.nextInt(rc.getMapHeight() - 6) + 3);
            return;
        }

    }
}
