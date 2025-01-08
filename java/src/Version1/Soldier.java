package Version1;

import battlecode.common.*;

import static Version1.RobotPlayer.rng;

enum states {
    ruin, explore, attack, refill, wallHug
}

public class Soldier {
    /**
     * Run a single turn for a Soldier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    //keeps track of the soldiers current objective - starts as going to a random place on map, can change
    private static MapLocation curObjective = null;

    //state tracker - prevState used for when refilling
    private static states state;
    private static states prevState;


    private static MapLocation nearestPaintTower = null;

    //information updated each turn
    private static RobotInfo[] enemyRobots;
    private static RobotInfo[] allyRobots;
    private static MapInfo[] nearbyTiles;

    public static void runSoldier(RobotController rc) throws GameActionException {
        if(state != null && curObjective != null) rc.setIndicatorString(state.toString() + " : " + curObjective.toString());
        else if(state != null) rc.setIndicatorString(state.toString());
        updateInfo(rc);
        updateState(rc);
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
            case refill:
                refill(rc);
                break;
            default:
                break;
        }
        updateInfo(rc);
        updateState(rc);
        // Try to paint beneath us as we walk to avoid paint penalties.
        // Avoiding wasting paint by re-painting our own tiles.
        MapInfo currentTile = rc.senseMapInfo(rc.getLocation());
        if (!currentTile.getPaint().isAlly() && rc.canAttack(rc.getLocation()) && rc.getPaint() > 5){
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
        Direction dir = BFS.moveTowards(rc, curObjective);
        if(dir != null && rc.canMove(dir)) {
            rc.move(dir);
        }
        rc.setIndicatorString(state.toString() + " : " + curObjective.toString());
    }

    //attempts to fill the ruin we can see
    public static void fillRuin(RobotController rc) throws GameActionException {
        MapLocation targetLoc = curObjective;
        Direction dir = BFS.moveTowards(rc, targetLoc);
        if (dir != null && rc.canMove(dir) && rc.senseMapInfo(rc.getLocation().add(dir)).getPaint() != PaintType.ENEMY_PRIMARY && rc.senseMapInfo(rc.getLocation().add(dir)).getPaint() != PaintType.ENEMY_SECONDARY)
            rc.move(dir);
        dir = rc.getLocation().directionTo(curObjective);
        // Mark the pattern we need to draw to build a tower here if we haven't already.
        MapLocation shouldBeMarked = curObjective.subtract(dir);
        if (rc.getRoundNum() < 400 && rc.senseMapInfo(shouldBeMarked).getMark() == PaintType.EMPTY && rc.canMarkTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, targetLoc)){
            rc.markTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, targetLoc);
        }
        else if (rc.canSenseLocation(shouldBeMarked) && rc.senseMapInfo(shouldBeMarked).getMark() == PaintType.EMPTY && rc.canMarkTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, targetLoc)){
            rc.markTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, targetLoc);
        }
        // Fill in any spots in the pattern with the appropriate paint, but first try to paint ur own tile so u can stay alive longer.
        MapInfo currentTile = rc.senseMapInfo(rc.getLocation());
        if (currentTile.getPaint() == PaintType.EMPTY && currentTile.getMark() != currentTile.getPaint()){
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
            state = null;
            curObjective = null;
        }
        else if (rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, targetLoc)){
            rc.completeTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, targetLoc);
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
        if(rc.canSenseLocation(curObjective) && rc.senseRobotAtLocation(curObjective) == null) {
            curObjective = null;
        }
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

    //update our info, changing our state as necessary
    public static void updateState(RobotController rc) throws GameActionException {
        if(rc.getPaint() < 15 && nearestPaintTower != null) {
            if(prevState == null) prevState = state;
            state = states.refill;
            return;
        }
        if(state == states.refill && (rc.getPaint() > 50 || nearestPaintTower == null)) {
            state = prevState;
            prevState = null;
            return;
        }
        else if(state == states.refill) return;
        if(curObjective == null) {
            curObjective = new MapLocation(rng.nextInt(rc.getMapWidth() - 6) + 3, rng.nextInt(rc.getMapHeight() - 6) + 3);
            state = states.explore;
        }
        // Search for a nearby ruin to complete.
        for (MapLocation tile : rc.senseNearbyRuins(-1)) {
            if (rc.senseRobotAtLocation(tile) == null && rc.getNumberTowers() < 25){
                curObjective = tile;
                state = states.ruin;
                return;
            }
        }
        if(state == states.ruin && ((rc.canSenseLocation(curObjective) && rc.senseRobotAtLocation(curObjective) != null)) || rc.getNumberTowers() >= 25) {
            rc.setIndicatorLine(rc.getLocation(), curObjective, 150, 150, 150);
            state = states.explore;
            curObjective = new MapLocation(rng.nextInt(rc.getMapWidth() - 6) + 3, rng.nextInt(rc.getMapHeight() - 6) + 3);
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
        if(state == null) {
            state = states.explore;
            curObjective = new MapLocation(rng.nextInt(rc.getMapWidth() - 6) + 3, rng.nextInt(rc.getMapHeight() - 6) + 3);
            return;
        }
    }
}
