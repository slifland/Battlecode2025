package Version7;

import battlecode.common.*;

import java.util.HashSet;

import static Version7.RobotPlayer.*;
import Version7.Splasher.*;

enum splasherStates {
    attack, refill, navigate, conquer, explore
}

public class Splasher {
    //keeps track of the soldiers current objective - starts as going to a random place on map, can change
    private static MapLocation curObjective = null;

    //private static boolean emptyTiles = false;
    private static int maxScore = 0; //used to cheaply calculate whether its worth it to run best Attack
    //state tracker - prevState used for when refilling
    private static splasherStates state;
    private static splasherStates prevState;
    //used for hugging the wall
    private static boolean clockwise;
    //used for refilling
    private static MapLocation nearestPaintTower = null;
    //used for navigating
    private static MapLocation nearestEnemyTower = null;
    private static MapLocation closestUnvisitedAlliedTower = null;
    private static final HashSet<MapLocation> visitedAlliedTowers = new HashSet<MapLocation>();
    private static MapLocation nearestRuin = null;
    private static MapLocation nearestUnoccupiedRuin = null;
    //used to direct splashers to other nearby paint towers if the current one is occupied
    private static MapLocation fillingStation = null;

    public static MapLocation averageEnemyPaint;

    //information updated each turn
    public static void runSplasher(RobotController rc) throws GameActionException {
        updateInfo(rc);
        updateState(rc);
        switch(state) {
            case attack:
                //attack(rc);
                SplasherMicro.integratedSplasherMicro(rc, nearestEnemyTower != null);
                break;
            case refill:
                refill(rc);
                break;
//            case navigate:
//                navigate(rc);
//                break;
            case explore:
                explore(rc);
                break;
            case conquer:
                //conquer(rc);
                SplasherMicro.integratedSplasherMicro(rc, nearestEnemyTower != null);
                break;
            default:
                break;
        }
        if(state != null && curObjective != null) rc.setIndicatorString(state.toString() + " : " + curObjective.toString());
        else if(state != null) rc.setIndicatorString(state.toString());
        // Try to paint beneath us as we walk to avoid paint penalties.
        // Avoiding wasting paint by re-painting our own tiles.
//        MapInfo currentTile = rc.senseMapInfo(rc.getLocation());
//        boolean currentTileIsSecondary = Utilities.getColorFromOriginPattern(rc.getLocation(), rc.getResourcePattern());
//        if ((!currentTile.getPaint().isAlly())
//                && rc.canAttack(rc.getLocation()) && rc.getPaint() > 200){
//            rc.attack(rc.getLocation(), currentTileIsSecondary);
//        }
        if(rc.isMovementReady()) {
//            if (!farFromEdge(rc, rc.getLocation()) && (state != splasherStates.refill)) {
//                moveFromEdge(rc);
//            }
            if (!rc.senseMapInfo(rc.getLocation()).getPaint().isAlly()) {
                findSafety(rc);
            }
        }
    }

    //attempts to move away from the closest edge
    private static void moveFromEdge(RobotController rc) throws GameActionException {
        int edgeX = 0;
        int edgeY = 0;
        MapLocation curLoc = rc.getLocation();
        if(curLoc.x <= 3) {
            edgeX = 0;
        }
        else if(curLoc.x >= rc.getMapWidth()-4) {
            edgeX = rc.getMapWidth()-1;
        }
        else {
            edgeX = curLoc.x;
        }
        if(curLoc.y <= 3) {
            edgeY = 0;
        }
        else if(curLoc.y >= rc.getMapHeight()-4) {
            edgeY = rc.getMapHeight()-1;
        }
        else {
            edgeY = curLoc.y;
        }
        MapLocation edge = new MapLocation(edgeX, edgeY);
        Direction dir = rc.getLocation().directionTo(edge);
        if(rc.canMove(dir)) {
            rc.move(dir);
        }
        else if(rc.canMove(dir.rotateRight())) {
            rc.move(dir.rotateRight());
        }
        else if(rc.canMove(dir.rotateLeft())) {
            rc.move(dir.rotateLeft());
        }
    }

    //attempts to take over the enemy territory we can see, using its awesome splashing power
    private static void conquer(RobotController rc) throws GameActionException {
        //System.out.println("Starting to conquer:" + Clock.getBytecodesLeft());
        if(rc.isActionReady()) {
            MapLocation toAttack = splasherUtil.bestAttack(rc, false, 2);
            //lets try and attack that position
            if (toAttack != null) {
                //if we are close enough, just attack
                if (rc.getLocation().isWithinDistanceSquared(toAttack, rc.getType().actionRadiusSquared)) {
                    if (rc.canAttack(toAttack)) {
                        rc.attack(toAttack);
                    }
                }
                //otherwise, move closer, then attack
                Direction dir = rc.getLocation().directionTo(toAttack);
                if (rc.canMove(dir)) {
                    rc.move(dir);
                } else if (rc.canMove(dir.rotateLeft())) {
                    rc.move(dir.rotateLeft());
                } else if (rc.canMove(dir.rotateRight())) {
                    rc.move(dir.rotateRight());
                }
                if (rc.canAttack(toAttack)) {
                    rc.attack(toAttack);
                }
            }
            else {
                Direction dir = rc.getLocation().directionTo(curObjective);
                MapLocation newLoc = rc.getLocation().add(dir);
                MapInfo newLocInfo = rc.senseMapInfo(newLoc);
                if(rc.senseMapInfo(rc.getLocation()).getPaint().isEnemy() || !isSafeFromTower(rc, rc.getLocation())) {
                    if(rc.canMove(dir.opposite())) {
                        rc.move(dir.opposite());
                    }
                }
                else {
                    if(isSafeFromTower(rc, newLoc) && rc.canMove(dir) && !isEnemyTile(newLocInfo)) {
                        rc.move(dir);
                    }
                }
            }
        }
        else {
            if(!rc.senseMapInfo(rc.getLocation()).getPaint().isAlly() || !isSafeFromTower(rc, rc.getLocation())) {
                findSafety(rc);
            }
            else {
                Direction dir = (Clock.getBytecodesLeft() > 6000) ? BFS_7x7.pathfind(rc, curObjective) : rc.getLocation().directionTo(curObjective);
                if(dir != null && rc.canMove(dir) && rc.senseMapInfo(rc.getLocation().add(dir)).getPaint().isAlly() && isSafeFromTower(rc, rc.getLocation().add(dir))) {
                    rc.move(dir);
                }
            }
        }
    }

    //looks to retreat to safety by finding a square that isnt the enemies
    public static void findSafety(RobotController rc) throws GameActionException {
//        for(MapInfo tile : nearbyTiles) {
//            if(rc.canMove(rc.getLocation().directionTo(tile.getMapLocation())) && !isEnemyTile(tile) && isSafeFromTower(rc, tile.getMapLocation())) {
//                rc.move(rc.getLocation().directionTo(tile.getMapLocation()));
//            }
//        }
        MapInfo bestTile = null;
        for(MapInfo tile : rc.senseNearbyMapInfos(2)) {
            if(tile.getPaint().isAlly() && isSafeFromTower(rc, tile.getMapLocation())) {
                bestTile = tile;
            }
            else if(isSafeFromTower(rc, tile.getMapLocation()) && !tile.getPaint().isEnemy() && (bestTile == null || !bestTile.getPaint().isAlly())) {
                bestTile = tile;
            }
        }
        if(bestTile == null) return;
        Direction dir = rc.getLocation().directionTo(bestTile.getMapLocation());
        if(rc.canMove(dir)) {
            rc.move(dir);
        }
    }

    //attempting to attack the tower we can see
    public static void attack(RobotController rc) throws GameActionException {
        if(rc.isActionReady() && rc.getPaint() > 50) {
            //MapLocation toAttack = splasherUtil.bestAttack(rc, true, 2);
            MapLocation toAttack = splasherUtil.cheapBestAttack(rc,true, 2);
            if(toAttack != null && rc.canAttack(toAttack)) {
                rc.attack(toAttack);
            }
            else if(rc.canAttack(curObjective)) {
                rc.attack(curObjective, Utilities.getColorFromOriginPattern(curObjective, rc.getResourcePattern()));
            }
//            else if(rc.senseMapInfo(rc.getLocation()).getPaint() == PaintType.EMPTY) {
//                if(rc.canAttack(rc.getLocation())) {
//                    rc.attack(rc.getLocation(), Utilities.getColorFromOriginPattern(rc.getLocation(), rc.getResourcePattern()));
//                }
//            }
            //if we still havent moved, means we need to move closer to attack our target location
            if(rc.isActionReady() && toAttack != null) {
                //Direction dir = BFS.moveTowards(rc, toAttack);
                Direction dir = rc.getLocation().directionTo(toAttack);
                if(dir == null) return;
                MapLocation newLoc = rc.getLocation().add(dir);
                MapInfo newLocInfo = rc.senseMapInfo(newLoc);
                if(rc.canMove(dir) && newLoc.distanceSquaredTo(toAttack) <= rc.getType().actionRadiusSquared && isSafeFromTowerModified(rc, newLoc)) {
                    rc.move(dir);
                    if(rc.canAttack(toAttack)) {
                        rc.attack(toAttack);
                    }
                }
            }
            //to attack is null, lets see if we should maybe move closer to the tower and attack it
            if(rc.isActionReady()) {
                Direction dir = rc.getLocation().directionTo(curObjective);
                MapLocation newLoc = rc.getLocation().add(dir);
                if(rc.canMove(dir) && newLoc.distanceSquaredTo(curObjective) <= rc.getType().actionRadiusSquared && isSafeFromTowerModified(rc, newLoc)) {
                    rc.move(dir);
                    if(rc.canAttack(curObjective)) {
                        rc.attack(curObjective);
                    }
                }
            }
        }
        //if we cant attack, stay safe until we can
        if(!isSafeFromTower(rc,rc.getLocation()) || isEnemyTile(rc.senseMapInfo(rc.getLocation()))) {
            findSafety(rc);
        }
    }

    //tries to go to the current objective
    public static void explore(RobotController rc) throws GameActionException {
        if(curObjective == null || !farFromEdge(rc, curObjective)){
            curObjective = new MapLocation(rc.getMapWidth() /2, rc.getMapHeight()/2);
        }
        //if there cant even conceivably be a place with that many empty tiles, then dont bother running that expensive method
        MapLocation toAttack = (maxScore > 6) ? splasherUtil.bestAttack(rc, false, 7) : null;
        if(toAttack != null && nearestUnoccupiedRuin != null && toAttack.distanceSquaredTo(nearestUnoccupiedRuin) <= 8) toAttack = null;
        if(toAttack != null && rc.canAttack(toAttack)) {
            rc.attack(toAttack);
        }
        if(toAttack != null && rc.isActionReady()) {
            //Direction dir = BFS.moveTowards(rc, toAttack);
            Direction dir = rc.getLocation().directionTo(toAttack);
            if(dir != null && rc.canMove(dir)) {
                rc.move(dir);
                if(rc.canAttack(toAttack)) {
                    rc.attack(toAttack);
                }
            }
        }
        if(rc.isMovementReady()) {
            //if we didnt run best attack, we have plenty of bytecodes to use pathfinding
            if(maxScore <= 6) {
                Direction dir = BFS_7x7.pathfind(rc, curObjective);
                if(dir != null && rc.canMove(dir) && isSafeFromTower(rc, rc.getLocation().add(dir))) {
                    rc.move(dir);
                }
            }
            else {
                Direction dir = rc.getLocation().directionTo(curObjective);
                if(rc.getPaint() < 50 && !rc.senseMapInfo(rc.getLocation().add(dir)).getPaint().isAlly())
                    return;
                if(rc.canMove(dir) && isSafeFromTower(rc, rc.getLocation().add(dir))) {
                    rc.move(dir);
                } else if (rc.canMove(dir.rotateLeft()) && isSafeFromTower(rc, rc.getLocation().add(dir))) {
                    rc.move(dir.rotateLeft());
                } else if (rc.canMove(dir.rotateRight()) && isSafeFromTower(rc, rc.getLocation().add(dir))) {
                    rc.move(dir.rotateRight());
                }
            }
        }
    }

    //tries to return to nearest paint tower to refill
    public static void refill(RobotController rc) throws GameActionException {
//        if(rc.getLocation().isAdjacentTo(nearestPaintTower)) {
//            if(rc.canTransferPaint(nearestPaintTower, Math.max(rc.getPaint() - rc.getType().paintCapacity, rc.senseRobotAtLocation(nearestPaintTower).paintAmount * -1))){
//                rc.transferPaint(nearestPaintTower, Math.max(rc.getPaint() - rc.getType().paintCapacity, rc.senseRobotAtLocation(nearestPaintTower).paintAmount * -1));
//            }
//        }
//        else {
//            Direction dir = BFS_7x7.pathfind(rc, nearestPaintTower);
//            if (dir != null && rc.canMove(dir) && isSafeFromTower(rc, rc.getLocation().add(dir))) {
//                rc.move(dir);
//            }
//        }
            //station is crowded, lets find another
            if(rc.senseNearbyRobots(fillingStation, 2, rc.getTeam()).length > 2 || (rc.canSenseLocation(fillingStation) && rc.senseRobotAtLocation(fillingStation) == null)) {
                MapLocation nextNearestPaintTower = null;
                for(Ruin r : Communication.ruinsMemory)
                {
                    if(r.isPaintTower && !r.location.equals(fillingStation) && ((nextNearestPaintTower == null || rc.getLocation().distanceSquaredTo(r.location) < rc.getLocation().distanceSquaredTo(nextNearestPaintTower)) && r.status == 1)){
                        nextNearestPaintTower = r.location;
                    }
                }
                if(nextNearestPaintTower != null) {
                    fillingStation = nextNearestPaintTower;
                }
            }
            if(rc.getLocation().isAdjacentTo(fillingStation)) {
            if(rc.canTransferPaint(fillingStation, Math.max(rc.getPaint() - rc.getType().paintCapacity, rc.senseRobotAtLocation(fillingStation).paintAmount * -1))){
                rc.transferPaint(fillingStation, Math.max(rc.getPaint() - rc.getType().paintCapacity, rc.senseRobotAtLocation(fillingStation).paintAmount * -1));
            }
        }
        else {
            Direction dir = BFS_7x7.pathfind(rc, fillingStation);
            if (dir != null && rc.canMove(dir) && isSafeFromTower(rc, rc.getLocation().add(dir))) {
                rc.move(dir);
            }
        }
    }

    /*
        moves towards the closest enemy tower if known
        If there is no known enemy towers, moves towards the closest known, unvisited, allied tower.
        If there is no known unvisited allied tower, moves towards the nearest known ruin
        Else
            move towards a random location
            (If it gets here multiple rounds in a row, it will move towards the same location each round)
     */
    public static void navigate(RobotController rc) throws GameActionException
    {
        if(nearestEnemyTower != null)
        {
            curObjective = nearestEnemyTower;
            //System.out.println("Splasher moving towards: nearestEnemyTower " + curObjective);
        }
        else if(closestUnvisitedAlliedTower != null)
        {
            curObjective = closestUnvisitedAlliedTower;
            //System.out.println("Splasher moving towards: closestUnvisitedAlliedTower" + curObjective);
        }
        else if(nearestRuin != null)
        {
            curObjective = nearestRuin;
            //System.out.println("Splasher moving towards: nearestRuin" + curObjective);
        }
        else
        {
            if(curObjective == null || rc.getLocation().distanceSquaredTo(curObjective) < 8) curObjective = new MapLocation(rc.getMapWidth()/2, rc.getMapHeight()/2);
            //System.out.println("Splasher moving towards: random" + curObjective);
        }

        Direction dir = BFS.moveTowards(rc, curObjective);
        if(dir != null && rc.canMove(dir))
        {
            rc.move(dir);
        }
    }


    //updates the static arrays which keep track of useful info for the robots turn - also updates nearest paint tower
    public static void updateInfo(RobotController rc) throws GameActionException {
        maxScore = 0;
        int x = 0;
        int y = 0;
        int count = 0;
        averageEnemyPaint = null;
        //calculate the max score for a splasher attack to save bytecodes later
        for(MapInfo tile : nearbyTiles) {
            if(tile.getPaint() == PaintType.EMPTY && !tile.isWall() && !tile.hasRuin()) {
                maxScore += 1;
            }
            else if(tile.getPaint().isEnemy() || (tile.hasRuin())) {
                if(tile.hasRuin() && rc.senseRobotAtLocation(tile.getMapLocation()) != null && rc.senseRobotAtLocation(tile.getMapLocation()).team != rc.getTeam()) {
                    maxScore += 2;
                } else if (!tile.hasRuin()) {
                    maxScore += 2;
                }
            }
            if(tile.hasRuin() && rc.senseRobotAtLocation(tile.getMapLocation()) == null) {
                nearestUnoccupiedRuin = tile.getMapLocation();
            }
            if(tile.getPaint().isEnemy()) {
                count++;
                x += tile.getMapLocation().x;
                y += tile.getMapLocation().y;
                count++;
            }
            //if(maxScore > 6) break;
        }
        averageEnemyPaint = (count == 0) ? null : new MapLocation(x / count, y / count);
        /*
        for(RobotInfo robot : allyRobots)
        {
            if(robot.getType().isTowerType())
            {
                visitedAlliedTowers.add(robot.getLocation());
                if(robot.getType() == UnitType.LEVEL_ONE_PAINT_TOWER || robot.getType() == UnitType.LEVEL_TWO_PAINT_TOWER || robot.getType() == UnitType.LEVEL_THREE_PAINT_TOWER && robot.getTeam() == rc.getTeam())
                {
                    nearestPaintTower = robot.getLocation();
                }
            }
        }
        */
        nearestPaintTower = null;
        for(Ruin r : Communication.ruinsMemory)
        {
            if(r.isPaintTower && (nearestPaintTower == null || rc.getLocation().distanceSquaredTo(r.location) < rc.getLocation().distanceSquaredTo(nearestPaintTower)) && r.status == 1){
                nearestPaintTower = r.location;
            }
        }
        /*
        if(nearestRuin != null && rc.canSenseLocation(nearestRuin) && (rc.senseRobotAtLocation(nearestRuin) != null || rc.getNumberTowers() >= 25)) {
            if(nearestRuin.equals(curObjective)) curObjective = null;
            nearestRuin = null;
        }
        for(Ruin r : Communication.ruinsMemory)
        {
            if(visitedAlliedTowers.contains(r.location) && r.status != 1) //if an allied tower has died, remove it from the list of visitedAlliedTowers
            {
                visitedAlliedTowers.remove(r.location);
            }

            if(r.status == 2)
            {
                if(nearestEnemyTower == null || rc.getLocation().distanceSquaredTo(r.location) < rc.getLocation().distanceSquaredTo(nearestEnemyTower))
                {
                    nearestEnemyTower = r.location;
                }
            }
            else if(r.status == 1)
            {
                if(     !visitedAlliedTowers.contains(r.location)
                     && (closestUnvisitedAlliedTower == null || rc.getLocation().distanceSquaredTo(r.location) < rc.getLocation().distanceSquaredTo(closestUnvisitedAlliedTower)))
                {
                    closestUnvisitedAlliedTower = r.location;
                }
            }
            else if(r.status == 0)
            {
                if((nearestRuin == null || rc.getLocation().distanceSquaredTo(r.location) < rc.getLocation().distanceSquaredTo(nearestRuin)) && !(rc.getNumberTowers() == 25))
                {
                    nearestRuin = r.location;
                }
            }
        }

         */
    }

    //update our current state
    public static void updateState(RobotController rc) throws GameActionException {
        if(rc.getPaint() < 60 && nearestPaintTower != null) {
            if(prevState == null) prevState = state;
            state = splasherStates.refill;
            if(fillingStation == null) fillingStation = nearestPaintTower;
            return;
        }
        else if(rc.getPaint() < 60 && nearestPaintTower == null) {
            state = splasherStates.explore;
            curObjective = new MapLocation(rc.getMapWidth()/ 2, rc.getMapHeight() / 2);
            fillingStation = null;
            return;
        }
        if(state == splasherStates.refill && (rc.getPaint() > 200 || nearestPaintTower == null)) {
            fillingStation = null;
            state = prevState;
            prevState = null;
            return;
        }
        else if(state == splasherStates.refill) return;
        //check all nearby ruins
        if(maxScore > 0) {
            for (MapLocation loc : rc.senseNearbyRuins(-1)) {
                //make sure it is an enemy tower
                if (rc.senseRobotAtLocation(loc) != null && rc.senseRobotAtLocation(loc).team != rc.getTeam()) {
                    curObjective = loc;
                    state = splasherStates.attack;
                    return;
                }
            }
        }
        if(maxScore > 1) {
            MapInfo bestTile = null;
            for (MapInfo info : nearbyTiles) {
                if (info.getPaint().isEnemy()) {
                    if (bestTile == null) bestTile = info;
                    else {
                        if (farFromEdgeNonMovement(rc, info.getMapLocation()) && !farFromEdgeNonMovement(rc, bestTile.getMapLocation())) {
                            bestTile = info;
                        }
                    }
                }
            }
            if (bestTile != null) {
                state = splasherStates.conquer;
                curObjective = bestTile.getMapLocation();
                return;
            }
        }
        state = splasherStates.explore;

        if(curObjective == null || rc.getLocation().distanceSquaredTo(curObjective) < 8) {
            curObjective = new MapLocation(rc.getMapWidth()/ 2, rc.getMapHeight() / 2);
        }
//        if(state != splasherStates.navigate || (curObjective == null || (!rc.canSenseLocation(curObjective) && state == splasherStates.navigate)))
//        {
//            state = splasherStates.navigate;
//        }
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
    //checks whether a space is in firing range of any seen enemy towers, but also if we could kill them and/or have overwhelming odds
    public static boolean isSafeFromTowerModified(RobotController rc, MapLocation loc) {
        for(RobotInfo enemy : enemyRobots) {
            if(enemy.type.isTowerType()) {
                if(loc.distanceSquaredTo(enemy.getLocation()) <= enemy.getType().actionRadiusSquared && enemy.getHealth() > rc.getType().attackStrength && allyRobots.length - enemyRobots.length < 4) {
                    return false;
                }
            }
        }
        return true;
    }
    //makes sure splashers dont move closer than vision radius towards the wall, because it completely messes with bestAttack logic
    //returns if the location is at least four blocks away from the edge in x and y direction - if we somehow are close to edge, never run bestAttack
    //differs from other because this will return true if you are close to edge so you dont get trapped
    public static boolean farFromEdge(RobotController rc, MapLocation loc) {
        //if(!loc.equals(rc.getLocation()) && !farFromEdge(rc, rc.getLocation())) return true;
        int mapHeight = rc.getMapHeight() - 1;
        int mapWidth = rc.getMapWidth()- 1;
        return loc.x >= 3 && loc.y >= 3 && loc.x <= mapWidth - 3 && loc.y <= mapHeight - 3;
    }
    //makes sure splashers dont move closer than vision radius towards the wall, because it completely messes with bestAttack logic
    //returns if the location is at least three blocks away from the edge in x and y direction - if we somehow are close to edge, never run bestAttack
    //non movement because 1. no exceptions for being close to wall and 2. can be used to trigger cheap best attack method
    public static boolean farFromEdgeNonMovement(RobotController rc, MapLocation loc) {
        int mapHeight = rc.getMapHeight() - 1;
        int mapWidth = rc.getMapWidth()- 1;
        return loc.x >= 4 && loc.y >= 4 && loc.x <= mapWidth - 4 && loc.y <= mapHeight - 4;
    }
}
