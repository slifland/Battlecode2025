package Version9;

import battlecode.common.*;

import java.awt.*;

import static Version9.RobotPlayer.*;

enum states {
    ruin, explore, attack, refill, wallHug, fill
}

public class Soldier {

    //keeps track of the soldiers current objective - starts as going to a random place on map, can change
    private static MapLocation curObjective = null;

    //state tracker - prevState used for when refilling
    private static states state;
    private static states prevState;
    private static MapLocation averageEmpty;
    private static MapLocation averageFilled;
    private static int countEmpty;
    //used for hugging the wall
    private static int edge = 0; //0 = not on edge, 1 = top, 2 = right, 3 = bottom, 4 = left
    private static boolean clockwise;
    //used for refilling
    private static MapLocation nearestPaintTower = null;
    //used to track if we should be painting under ourselves for the resource pattern
    private static boolean nearRuin = false;
    public static MapLocation averageEnemyPaint;

    //information updated each turn

    public static void runSoldier(RobotController rc) throws GameActionException {
        //int price = Clock.getBytecodesLeft();
        //Micro.runMicro(rc);
        //System.out.println(price - Clock.getBytecodesLeft());
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
            case fill:
                fill(rc);
                break;
            default:
                break;
        }
        if(state != null && curObjective != null) rc.setIndicatorString(state.toString() + " : " + curObjective.toString());
        else if(state != null) rc.setIndicatorString(state.toString());
        // Try to paint beneath us as we walk to avoid paint penalties.
        // Avoiding wasting paint by re-painting our own tiles.
        if(rc.isActionReady()) {
            MapInfo currentTile = rc.senseMapInfo(rc.getLocation());
            boolean currentTileIsSecondary = Utilities.getColorFromOriginPattern(rc.getLocation(), rc.getResourcePattern());
            if (!nearRuin && (!currentTile.getPaint().isAlly() || (currentTileIsSecondary != currentTile.getPaint().isSecondary() && currentTile.getMark() == PaintType.EMPTY))
                    && rc.canAttack(rc.getLocation()) && rc.getPaint() > 10) {
                rc.attack(rc.getLocation(), currentTileIsSecondary);
            }
            //otherwise, if we have a lot of paint, just paint something
            else if (rc.getPaint() > 75 || (rc.getPaint() > 35 && nearestPaintTower != null)) {
                if(Clock.getBytecodesLeft() > 4000) {
                    calculateAverageFilled(rc);
                    MapLocation toFill = bestFill(rc, state != states.ruin);
                    if(toFill != null && rc.canAttack(toFill)) {
                        //rc.setIndicatorLine(rc.getLocation(), toFill, 255, 255, 255);
                        rc.attack(toFill, Utilities.getColorFromOriginPattern(toFill, rc.getResourcePattern()));
                    }
                }
                if(rc.isActionReady() && Clock.getBytecodesLeft() > 2000) {
                    for (MapInfo loc : rc.senseNearbyMapInfos(rc.getType().actionRadiusSquared)) {
                        if (loc.getPaint() == PaintType.EMPTY && rc.canAttack(loc.getMapLocation()) && !loc.hasRuin()) {
                            rc.attack(loc.getMapLocation(), Utilities.getColorFromOriginPattern(loc.getMapLocation(), rc.getResourcePattern()));
                            break;
                        }
                    }
                }
            }
            if(rc.canCompleteResourcePattern(rc.getLocation())) rc.completeResourcePattern(rc.getLocation());
            //lets try and transfer some paint, if we can
//            for (RobotInfo ally : rc.senseNearbyRobots(2, rc.getTeam())) {
//                if (ally.getType().isTowerType() && rc.canTransferPaint(ally.getLocation(), -50)) {
//                    rc.transferPaint(ally.getLocation(), -50);
//                }
//            }
        }
        if(Clock.getBytecodesLeft() > 4000) {
            nearbyTiles = rc.senseNearbyMapInfos(rc.getType().actionRadiusSquared);
            for(MapInfo tile : nearbyTiles) {
                if(rc.canCompleteResourcePattern(tile.getMapLocation())) {
                    rc.completeResourcePattern(tile.getMapLocation());
                    break;
                }
            }
        }
    }

    //attempt to move to the random location we have been assigned, or choose a new random location
    public static void explore(RobotController rc) throws GameActionException {
        //Direction dir = BFS.moveTowards(rc, curObjective);
        Direction dir = BFS_7x7.pathfind(rc, curObjective);
        if(dir != null && rc.canMove(dir)) {
            rc.move(dir);
        }
    }
    //attempts to fill the ruin we can see
    public static void fillRuin(RobotController rc) throws GameActionException {
        MapLocation targetLoc = curObjective;
        if(rc.getLocation().distanceSquaredTo(curObjective) > 2) {
            Direction dir = BFS_7x7.pathfind(rc, targetLoc);
            if(dir != null && rc.canMove(dir)) {
                rc.move(dir);
            }
        }
        if(rc.getLocation().distanceSquaredTo(curObjective) > GameConstants.VISION_RADIUS_SQUARED) return;
        MapInfo[] ruinTiles = rc.senseNearbyMapInfos(curObjective, 8);
        boolean[][] desiredPattern = Utilities.inferPatternFromExistingSpots(rc, curObjective, ruinTiles);
        //time to choose the pattern for ourselves!
        if(desiredPattern == null) {
            if(rc.getRoundNum() <= 3) {
                desiredPattern = rc.getTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER);
            }
            else {
                int ranNum = (rc.getRoundNum() < 100) ? 0 : rng.nextInt(3);
                if(ranNum <= 1) {
                    desiredPattern = rc.getTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER);
                }
                else {
                    desiredPattern = rc.getTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER);
                }
            }
        }
        if(rc.getLocation().distanceSquaredTo(curObjective) <= 8) {
            MapInfo curTile = rc.senseMapInfo(rc.getLocation());
            PaintType paint = curTile.getPaint();
            if(paint.equals(PaintType.EMPTY) && rc.canAttack(rc.getLocation())) {
                rc.attack(rc.getLocation(), Utilities.getColorFromCustomPattern(rc.getLocation(), desiredPattern, curObjective));
            }
        }
        //otherwise, lets try and fill it in
        MapInfo desiredTile = null;
        if(rc.isActionReady()) {
            for(MapInfo tile : ruinTiles) {
                if (!tile.getPaint().isEnemy() && !tile.hasRuin()) {
                    boolean isSecondary = Utilities.getColorFromCustomPattern(tile.getMapLocation(), desiredPattern, curObjective);
                    if (tile.getPaint().isSecondary() != isSecondary || tile.getPaint() == PaintType.EMPTY) {
                        desiredTile = tile;
                        if (rc.canAttack(tile.getMapLocation())) {
                            //rc.setIndicatorLine(rc.getLocation(), tile.getMapLocation(), 255, 255, 255);
                            rc.attack(tile.getMapLocation(), isSecondary);
                        }
                        break;

                    }
                }
            }
        }
        //potentially just need to move closer to some of the tiles
        if(rc.isActionReady() && desiredTile != null) {
            Direction dir = BFS_7x7.pathfind(rc, desiredTile.getMapLocation());
            if(rc.canMove(dir)) {
                rc.move(dir);
            }
            if(rc.canAttack(desiredTile.getMapLocation())) {
                rc.attack(desiredTile.getMapLocation(), Utilities.getColorFromCustomPattern(desiredTile.getMapLocation(), desiredPattern, curObjective));
            }
        }
        //try and complete the ruin pattern
        if ((rc.getNumberTowers() > 2 || rc.getMoney() >= 1500) && rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, targetLoc)){
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
    //attempt to fill the empty space around us, prioritizing spaces that might complete resource patterns
    public static void fill(RobotController rc) throws GameActionException {
        int ranNum = rng.nextInt(2);
        if(ranNum == 0 || rc.getRoundNum() < 150) {
            Direction dir = BFS_7x7.pathfind(rc, averageEmpty);
            if(dir != null && rc.canMove(dir)) {
                rc.move(dir);
            }
        }
        if(rc.senseMapInfo(rc.getLocation()).getPaint() == PaintType.EMPTY && rc.canAttack(rc.getLocation())) {
            rc.attack(rc.getLocation(), Utilities.getColorFromOriginPattern(rc.getLocation(), rc.getResourcePattern()));
            if(rc.canCompleteResourcePattern(rc.getLocation())) {
                rc.completeResourcePattern(rc.getLocation());
            }
        }
        else {
            if(rc.isActionReady()) {
                //try and find a spot near other filled spots
                MapLocation bestFill = bestFill(rc, false);
                if(rc.canAttack(bestFill)){
                    rc.attack(bestFill, Utilities.getColorFromOriginPattern(bestFill, rc.getResourcePattern()));
                    if(rc.canCompleteResourcePattern(bestFill)) {
                        rc.completeResourcePattern(bestFill);
                    }
                }
                else {
                    for(MapInfo tile : nearbyTiles) {
                        if(tile.getPaint() == PaintType.EMPTY && rc.canAttack(tile.getMapLocation())) {
                            rc.attack(tile.getMapLocation(), Utilities.getColorFromOriginPattern(tile.getMapLocation(), rc.getResourcePattern()));
                            if(rc.canCompleteResourcePattern(tile.getMapLocation())) {
                                rc.completeResourcePattern(tile.getMapLocation());
                            }
                            break;
                        }
                    }
                }
            }
        }

    }

    //returns the square the robot can fill that has the most adjacent filled in squares
    //approximation - based on averageFill, the average location of filled ally squares the robot can see
    private static MapLocation bestFill(RobotController rc, boolean acceptingCorrections) throws GameActionException {
        if(averageFilled == null) return null;
        // MapInfo[] closeToAvgFill = rc.senseNearbyMapInfos(averageFilled, 4);
        MapLocation closest = null;
        int closestDistance = Integer.MAX_VALUE;
        MapLocation curLoc = rc.getLocation();
        int actionRadiusSquared = UnitType.SOLDIER.actionRadiusSquared;
//        for(MapInfo patternTile : closeToAvgFill) {
//            if(patternTile.getMapLocation().distanceSquaredTo(curLoc) < closestDistance && patternTile.getPaint() == PaintType.EMPTY && patternTile.getMapLocation().isWithinDistanceSquared(curLoc, actionRadiusSquared)) {
//                closest = patternTile.getMapLocation();
//                closestDistance = patternTile.getMapLocation().distanceSquaredTo(curLoc);
//            }
//        }
        MapLocation wrongTypePaint = null;
        for(MapInfo tile : nearbyTiles) {
            MapLocation tempLoc = tile.getMapLocation();
            int dist = curLoc.distanceSquaredTo(tempLoc);
            int distFromAvg = curLoc.distanceSquaredTo(averageFilled);
            if(dist <= actionRadiusSquared && distFromAvg < closestDistance && tile.getPaint() == PaintType.EMPTY && !tile.hasRuin()) {
                closest = tempLoc;
                closestDistance = distFromAvg;
            }
            if(wrongTypePaint == null && dist <= actionRadiusSquared && tile.getPaint().isAlly() && tile.getPaint().isSecondary() != Utilities.getColorFromOriginPattern(tile.getMapLocation(), rc.getResourcePattern()) && !tile.hasRuin()) {
                wrongTypePaint = tile.getMapLocation();
            }
        }
        if(closest != null) return closest;
        else if (acceptingCorrections && wrongTypePaint != null) return wrongTypePaint;
        else return null;
    }

    //calculates average filled in case you wanna use bestFill and you areont on fill mode
    //costs roughly 1800 bytecode
    private static void calculateAverageFilled(RobotController rc) throws GameActionException {
        int price = Clock.getBytecodesLeft();
        int x = 0;
        int y = 0;
        int countFilled = 0;
        for(MapInfo tile : nearbyTiles) {
            if(tile.getPaint().isAlly()) {
                countFilled++;
                x += tile.getMapLocation().x;
                y += tile.getMapLocation().y;
            }
        }
        averageFilled = countFilled != 0 ? new MapLocation(x/countFilled, y/countFilled) : null;
    }


    //attempting to attack the tower we can see
    public static void attack(RobotController rc) throws GameActionException {
        //attempt to paint under us to save paint in the long run
        if(rc.senseMapInfo(rc.getLocation()).getPaint() == PaintType.EMPTY) {
            if(rc.canAttack(rc.getLocation())) {
                rc.attack(rc.getLocation(), Utilities.getColorFromOriginPattern(rc.getLocation(), rc.getResourcePattern()));
            }
        }
        if(rc.canAttack(curObjective)) rc.attack(curObjective);
        //attempt to move towards the enemy if we are out of range, and the tile is not an enemy tile
        if(rc.getLocation().distanceSquaredTo(curObjective) > rc.getType().actionRadiusSquared) {
            Direction dir = BFS.moveTowards(rc, curObjective);
            if(dir != null) {
                MapLocation newLoc = rc.getLocation().add(dir);
                if (rc.canMove(dir) && !isEnemyTile(rc.senseMapInfo(newLoc)) && (isSafeFromTower(rc, newLoc) || rc.getHealth() > 200)) {
                    rc.move(dir);
                }
            }
        }
        //if we have good enough numbers, just move towards it anyway
        if(rc.isMovementReady() && allyRobots.length - enemyRobots.length > 4 && !rc.getLocation().isAdjacentTo(curObjective)) {
            Direction dir = BFS.moveTowards(rc, curObjective);
            if(dir != null && rc.canMove(dir)) {
                rc.move(dir);
            }
        }

        //if we are low on health and in tower range and dont have the numbers, back up
        else if(rc.isMovementReady() && rc.getHealth() <= 100 && rc.getLocation().distanceSquaredTo(curObjective) <= rc.getType().actionRadiusSquared) {
            Direction dir = rc.getLocation().directionTo(curObjective).opposite();
            if(rc.canAttack(curObjective)) rc.attack(curObjective, Utilities.getColorFromOriginPattern(curObjective, rc.getResourcePattern()));
            if(rc.canMove(dir)) rc.move(dir);
        }

        if(rc.canSenseLocation(curObjective) && rc.senseRobotAtLocation(curObjective) == null) {
            state = null;
            curObjective = null;
        }
    }

    //tries to return to nearest paint tower to refill
    public static void refill(RobotController rc) throws GameActionException {
//        if(rc.getLocation().isAdjacentTo(nearestPaintTower)) {
//            if(rc.canTransferPaint(nearestPaintTower, Math.max(200-rc.getPaint(), rc.senseRobotAtLocation(nearestPaintTower).paintAmount))){
//                rc.transferPaint(nearestPaintTower, Math.max(200-rc.getPaint(), rc.senseRobotAtLocation(nearestPaintTower).paintAmount));
//            }
//        }
        if(rc.getLocation().isAdjacentTo(nearestPaintTower)) {
            if(rc.canTransferPaint(nearestPaintTower, Math.max(rc.getPaint() - UnitType.SOLDIER.paintCapacity, -1 * rc.senseRobotAtLocation(nearestPaintTower).paintAmount))){
                rc.transferPaint(nearestPaintTower, Math.max(rc.getPaint() - UnitType.SOLDIER.paintCapacity, -1 * rc.senseRobotAtLocation(nearestPaintTower).paintAmount));
            }
        }
        else {
            //Direction dir = BFS.moveTowards(rc, nearestPaintTower);
            Direction dir = BFS_FullVision.pathfind(rc, nearestPaintTower);
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
            Direction dir = BFS_7x7.pathfind(rc, curObjective);
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
        /*
        for(RobotInfo robot : allyRobots) {
            if(robot.getType() == UnitType.LEVEL_ONE_PAINT_TOWER  || robot.getType() == UnitType.LEVEL_TWO_PAINT_TOWER || robot.getType() == UnitType.LEVEL_THREE_PAINT_TOWER && robot.getTeam() == rc.getTeam()) {
                nearestPaintTower = robot.getLocation();
            }
        }
        */
        nearestPaintTower = null;
        for(Ruin r : Communication.unclaimedRuins)
        {
            if(r.isPaintTower && (nearestPaintTower == null || rc.getLocation().distanceSquaredTo(r.location) < rc.getLocation().distanceSquaredTo(nearestPaintTower)))
                nearestPaintTower = r.location;
        }
        int x = 0; int y = 0; int count = 0;
        for(MapInfo tile : nearbyTiles) {
            if(tile.getPaint().isEnemy()) {
                x += tile.getMapLocation().x;
                y += tile.getMapLocation().y;
                count++;
            }
        }
        averageEnemyPaint = (count != 0) ? new MapLocation(x/count, y/count) : null;
        /*
        if(nearestPaintTower != null && rc.canSenseLocation(nearestPaintTower) && rc.senseRobotAtLocation(nearestPaintTower) == null) {
            nearestPaintTower = null;
        }
        */
    }

    //update our info, changing our state as necessary
    public static void updateState(RobotController rc) throws GameActionException {
        nearRuin = false;
        if(state != states.refill && state != states.fill && prevState != null) prevState = null;
        if(rc.getPaint() < 20 && nearestPaintTower != null) {
            if(state == states.fill) {
                prevState = null;
                state = states.explore;
                curObjective = averageEmpty;
            }
            if(prevState == null) prevState = state;
            state = states.refill;
            return;
        }
        if(state == states.refill && (rc.getPaint() > 100 || nearestPaintTower == null)) {
            state = prevState;
            prevState = null;
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
        if(state != states.ruin) {
            // Search for a nearby ruin to complete.
            for (MapLocation tile : rc.senseNearbyRuins(-1)) {
                nearRuin = true;
                if (rc.senseRobotAtLocation(tile) == null && rc.getNumberTowers() < 25) {
                    if (rc.getMoney() < 1000 && rc.senseNearbyRobots(tile, 2, rc.getTeam()).length > 0) {
                        if (state == states.ruin) state = states.explore;
                        continue;
                    }
                    curObjective = tile;
                    state = states.ruin;
                    return;
                }
            }
        }
        else {
            nearRuin = true;
        }
        if(state == states.ruin && rc.getLocation().isAdjacentTo(curObjective)) {
            if ((rc.getMoney() < 1000 || enemyRobots.length == 0) && rc.senseNearbyRobots(curObjective, 2, rc.getTeam()).length > 0)
                state = states.explore;
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
        countEmpty = 0;
        int countFilled = 0;
        int fX = 0;
        int fY = 0;
        int x = 0;
        int y = 0;
        if((state == states.explore) || state == states.wallHug || state == states.fill) {
            for(MapInfo tile : nearbyTiles) {
                if(tile.getPaint() == PaintType.EMPTY && !tile.isWall() && !tile.hasRuin()) {
                    countEmpty++;
                    x += tile.getMapLocation().x;
                    y += tile.getMapLocation().y;
                }
                else if(tile.getPaint().isAlly()) {
                    countFilled++;
                    fX += tile.getMapLocation().x;
                    fY += tile.getMapLocation().y;
                }
            }
            averageEmpty = (countEmpty != 0) ? new MapLocation(x/countEmpty, y/countEmpty) : null;
            averageFilled = countFilled != 0 ? new MapLocation(fX/countFilled, fY/countFilled) : null;
            if(countEmpty > 6 || state == states.fill && countEmpty >= 1){
                if(prevState == null) prevState = state;
                state = states.fill;
                return;
            }
            else if(state == states.fill) {
                state = prevState;
                prevState = null;
            }
        }
        if(state == states.explore && rc.getLocation().distanceSquaredTo(curObjective) < 16) {
            curObjective = new MapLocation(rng.nextInt(rc.getMapWidth() - 6) + 3, rng.nextInt(rc.getMapHeight() - 6) + 3);
            return;
        }
        if(state == null) {
            state = states.explore;
            //if(rc.getRoundNum() < 10) curObjective = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
            curObjective = new MapLocation(rng.nextInt(rc.getMapWidth() - 6) + 3, rng.nextInt(rc.getMapHeight() - 6) + 3);
            return;
        }
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
