package Version3;

import battlecode.common.*;

import static Version3.RobotPlayer.rng;

enum states {
    ruin, explore, attack, refill, wallHug
}

public class Soldier {

    //keeps track of the soldiers current objective - starts as going to a random place on map, can change
    private static MapLocation curObjective = null;

    //state tracker - prevState used for when refilling
    private static states state;
    private static states prevState;
    //used for hugging the wall
    private static int edge = 0; //0 = not on edge, 1 = top, 2 = right, 3 = bottom, 4 = left
    private static boolean clockwise;
    //used for refilling
    private static MapLocation nearestPaintTower = null;

    //information updated each turn
    private static RobotInfo[] enemyRobots;
    private static RobotInfo[] allyRobots;
    private static MapInfo[] nearbyTiles;

    public static void runSoldier(RobotController rc) throws GameActionException {
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
            case wallHug:
                wallHug(rc);
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
        //otherwise, if we have a lot of paint, just paint something
        else if (rc.getPaint() > 100) {
            for(MapInfo loc : rc.senseNearbyMapInfos(rc.getType().actionRadiusSquared)){
                if(loc.getPaint() == PaintType.EMPTY && rc.canAttack(loc.getMapLocation()) && !loc.isWall() && !loc.hasRuin()){
                    rc.attack(loc.getMapLocation(), Utilities.getColorFromOriginPattern(loc.getMapLocation(), rc.getResourcePattern()));
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
    }

    //attempts to fill the ruin we can see
    public static void fillRuin(RobotController rc) throws GameActionException {
        MapLocation targetLoc = curObjective;
        Direction dir = BFS.moveTowards(rc, targetLoc);
        if(rc.getLocation().distanceSquaredTo(curObjective) > 2) {
            if (dir != null && rc.canMove(dir) && rc.senseMapInfo(rc.getLocation().add(dir)).getPaint() != PaintType.ENEMY_PRIMARY && rc.senseMapInfo(rc.getLocation().add(dir)).getPaint() != PaintType.ENEMY_SECONDARY)
                rc.move(dir);
        }
        dir = rc.getLocation().directionTo(curObjective);
        // Mark the pattern we need to draw to build a tower here if we haven't already.
        MapLocation shouldBeMarked = curObjective.subtract(dir);
        int ranNum = (rc.getRoundNum() > 200) ? rng.nextInt(2) : rng.nextInt(3);
        if (ranNum == 0 && rc.canSenseLocation(shouldBeMarked) && rc.senseMapInfo(shouldBeMarked).getMark() == PaintType.EMPTY && rc.canMarkTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, targetLoc)){
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
            for (MapInfo patternTile : rc.senseNearbyMapInfos(targetLoc, rc.getType().actionRadiusSquared)) {
                if (patternTile.getMark() != patternTile.getPaint() && patternTile.getMark() != PaintType.EMPTY && !isEnemyTile(patternTile)) {
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
        //there is more to complete, but its out of our action radius - try to move closer to it
        if(rc.isActionReady()) {
            for (MapInfo patternTile : rc.senseNearbyMapInfos(targetLoc, rc.getType().actionRadiusSquared)) {
                if (patternTile.getMark() != patternTile.getPaint() && patternTile.getMark() != PaintType.EMPTY && !isEnemyTile(patternTile)) {
                    boolean useSecondaryColor = patternTile.getMark() == PaintType.ALLY_SECONDARY;
                    Direction d = BFS.moveTowards(rc, patternTile.getMapLocation());
                    if(d != null && rc.canMove(d)) rc.move(d);
                    if (rc.canAttack(patternTile.getMapLocation()))
                        rc.attack(patternTile.getMapLocation(), useSecondaryColor);
                }
            }
        }
    }
    //attempting to attack the tower we can see
    public static void attack(RobotController rc) throws GameActionException {
        if(rc.senseMapInfo(rc.getLocation()).getPaint() == PaintType.EMPTY) {
            if(rc.canAttack(rc.getLocation())) {
                rc.attack(rc.getLocation(), Utilities.getColorFromOriginPattern(rc.getLocation(), rc.getResourcePattern()));
            }
        }
        if(rc.getLocation().distanceSquaredTo(curObjective) > rc.getType().actionRadiusSquared) {
            Direction dir = BFS.moveTowards(rc, curObjective);
            if(dir != null && rc.canMove(dir) && !isEnemyTile(rc.senseMapInfo(rc.getLocation().add(dir)))) {
                rc.move(dir);
            }
        }
        if(rc.canAttack(curObjective)) {
            rc.attack(curObjective, Utilities.getColorFromOriginPattern(curObjective, rc.getResourcePattern()));
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
    //attempts to find and then follow a wall in a random direction, filling in paint as it goes - goes back to refill as necessary
    public static void wallHug(RobotController rc) throws GameActionException {
        //find the closest wall to you, and set that as your current objective
        if(curObjective == null) {
            curObjective = findClosestWall(rc);
        }
        if(rc.getLocation().distanceSquaredTo(curObjective) <= 4) {
            curObjective = rotateCorner(rc, clockwise);
            if(clockwise) rc.setIndicatorString("clockwise");
            else rc.setIndicatorString("reverse");
        }
        else {
            Direction dir = BFS.moveTowards(rc, curObjective);
            if(dir != null && rc.canMove(dir)) {
                rc.move(dir);
            }
        }
    }

    //finds the closest corner in either the clockwise or counterclockwise direction, given the edge you are on
    public static MapLocation rotateCorner(RobotController rc, boolean clockwise) throws GameActionException {
        int curX = rc.getLocation().x;
        int mapWidth = rc.getMapWidth() - 1;
        int curY = rc.getLocation().y;
        int mapHeight = rc.getMapHeight() - 1;
        //we aren't even chasing a corner yet - need to return the closest one in the direction we are going
        if(!(curObjective.x == 0 || curObjective.x == mapWidth && curObjective.y == 0 || curObjective.y == mapHeight)) {
            int newX;
            int newY;
            //top edge
            if(mapHeight - curY <= 4) {
                if(clockwise) {
                    newX = mapWidth;
                }
                else {
                    newX = 0;
                }
                newY = mapHeight;
            }
            //right edge
            else if(mapWidth - curX <= 4) {
                if(clockwise) {
                    newY = 0;
                }
                else {
                    newY = mapHeight;
                }
                newX = mapWidth;
            }
            //bottom edge
            else if (curY <= 4) {
                if(clockwise) {
                    newX = 0;
                }
                else {
                   newX = mapWidth;
                }
                newY = 0;
            }
            // left edge
            else {
                if(clockwise) {
                   newY = mapHeight;
                }
                else {
                    newY = 0;
                }
                newX = 0;
            }
            return new MapLocation(newX, newY);
        }
        //bottom corner -> clockwise needs to go to top left, CCW needs to go to bottom right
        if(curX < 5 && curY < 5) {
            if(clockwise) {
                return new MapLocation(0, mapHeight);
            }
            else {
                return new MapLocation(mapWidth, 0);
            }
        }
        //bottom right corner
        else if(curX > 5 && curY < 5) {
            if(clockwise) {
                return new MapLocation(0, 0);
            }
            else {
                return new MapLocation(mapWidth, mapHeight);
            }
        }
        //top right
        else if(curX > 5 && curY > 5) {
            if(clockwise) {
                return new MapLocation(mapWidth, 0);
            }
            else {
                return new MapLocation(0, mapHeight);
            }
        }
        //top left
        else {
            if(clockwise) {
                return new MapLocation(mapWidth, mapHeight);
            }
            else {
                return new MapLocation(0, 0);
            }
        }
    }
    //moves the best counter clockwise direction for this robot
    private static void moveBestCounterClockwiseDirection(RobotController rc) throws GameActionException {
        Direction dir = null;
        int curX = rc.getLocation().x;
        int mapWidth = rc.getMapWidth() - 1;
        int curY = rc.getLocation().y;
        int mapHeight = rc.getMapHeight() - 1;
        //top edge, go west if possible, then south
        if(mapHeight - curY <= 4) {
            if(rc.canMove(Direction.WEST)) {
                rc.move(Direction.WEST);
            }
            else if(rc.canMove(Direction.SOUTH)) {
                rc.move(Direction.SOUTH);
            }
        }
        //right edge, go south if possible, then east
        else if(mapWidth - curX <= 4) {
            if(rc.canMove(Direction.NORTH)) {
                rc.move(Direction.NORTH);
            }
            else if(rc.canMove(Direction.EAST)) {
                rc.move(Direction.EAST);
            }
        }
        //bottom edge - go east if possible, then north
        else if (curY <= 4) {
            if(rc.canMove(Direction.EAST)) {
                rc.move(Direction.EAST);
            }
            else if(rc.canMove(Direction.NORTH)) {
                rc.move(Direction.NORTH);
            }
        }
        // left edge - go north if possible, then west
        else {
            if(rc.canMove(Direction.SOUTH)) {
                rc.move(Direction.SOUTH);
            }
            else if(rc.canMove(Direction.WEST)) {
                rc.move(Direction.WEST);
            }
        }
    }

    //determines the best clockwise direction to go
    private static void moveBestClockwiseDirection(RobotController rc) throws GameActionException {
        Direction dir = null;
        int curX = rc.getLocation().x;
        int mapWidth = rc.getMapWidth() - 1;
        int curY = rc.getLocation().y;
        int mapHeight = rc.getMapHeight() - 1;
        //top edge, go east if possible, then south
        if(mapHeight - curY <= 4) {
            if(rc.canMove(Direction.EAST)) {
                rc.move(Direction.EAST);
            }
            else if(rc.canMove(Direction.SOUTH)) {
                rc.move(Direction.SOUTH);
            }
        }
        //right edge, go south if possible, then west
        else if(mapWidth - curX <= 4) {
            if(rc.canMove(Direction.SOUTH)) {
                rc.move(Direction.SOUTH);
            }
            else if(rc.canMove(Direction.WEST)) {
                rc.move(Direction.WEST);
            }
        }
        //bottom edge - go west if possible, then north
        else if (curY <= 4) {
            if(rc.canMove(Direction.WEST)) {
                rc.move(Direction.WEST);
            }
            else if(rc.canMove(Direction.NORTH)) {
                rc.move(Direction.NORTH);
            }
        }
        // left edge - go north if possible, then east
        else {
            if(rc.canMove(Direction.NORTH)) {
                rc.move(Direction.NORTH);
            }
            else if(rc.canMove(Direction.EAST)) {
                rc.move(Direction.EAST);
            }
        }
    }

    //determines the closest wall to this robot
    private static MapLocation findClosestWall(RobotController rc) throws GameActionException {
        int curX = rc.getLocation().x;
        int mapWidth = rc.getMapWidth() - 2;
        int mapWidthHalf = mapWidth / 2;
        int closestX;
        if (curX > mapWidthHalf) {
            closestX = mapWidth;
        }
        else {
            closestX = 0;
        }
        int curY = rc.getLocation().y;
        int mapHeight = rc.getMapHeight() - 2;
        int mapHeightHalf = mapHeight / 2;
        int closestY;
        if (curY > mapHeightHalf) {
            closestY = mapHeight;
        }
        else {
            closestY = 0;
        }
        int diffX = Math.abs(closestX - curX);
        int diffY = Math.abs(closestY - curY);
        if(diffX < diffY) {
            return new MapLocation(closestX, curY);
        }
        else {
            return new MapLocation(curX, closestY);
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
        if(rc.getPaint() < 20 && nearestPaintTower != null) {
            if(prevState == null) prevState = state;
            state = states.refill;
            return;
        }
        if(state == states.refill && (rc.getPaint() > 75 || nearestPaintTower == null)) {
            state = prevState;
            prevState = null;
            return;
        }
        else if(state == states.refill) return;
        if(curObjective == null) {
            int ran = rng.nextInt(10);
            if(ran < 2 && rc.getRoundNum() > 200) {
                state = states.wallHug;
                curObjective = null;
                ran = rng.nextInt(2);
                clockwise = ran == 0;
                return;
            }
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
    //returns whether a location is controlled by the enemy
    public static boolean isEnemyTile(MapInfo loc) {
        PaintType temp = loc.getPaint();
        return temp == PaintType.ENEMY_PRIMARY || temp == PaintType.ENEMY_SECONDARY;
    }
}
