package Version4;

import battlecode.common.*;
import battlecode.schema.RobotType;

import java.util.ArrayList;

import static Version4.RobotPlayer.*;

enum mopStates {
    mop, steal, explore, navigate
}

public class Mopper {

    //keeps track of the state for the individual mopper
    private static mopStates state;

    //info the mopper will need at various points throughout its turn
    private static RobotInfo[] enemyRobots;
    private static RobotInfo[] adjacentEnemyRobots;
    private static RobotInfo[] allyRobots;
    private static MapInfo[] nearbyTiles;
    //location data
    private static MapLocation nearestEnemyTower;
    private static MapLocation nearestRuin;
    private int[] paintMap; //going in order that tiles are detected, 1 indicates enemy, 0 empty, -1 ally
    private static MapLocation curObjective = null;

    public static void runMopper(RobotController rc) throws GameActionException {
        updateInfo(rc);
        updateState(rc);
        if(isEnemyTile(rc.senseMapInfo(rc.getLocation()))) {
            for(MapInfo loc : rc.senseNearbyMapInfos(2)) {
                if(!isEnemyTile(loc) && rc.canMove(rc.getLocation().directionTo(loc.getMapLocation()))) {
                    rc.move(rc.getLocation().directionTo(loc.getMapLocation()));
                }
            }
        }
        switch(state) {
            case mop:
                mop(rc);
                break;
            case steal:
                steal(rc);
                break;
            case explore:
                explore(rc);
                break;
            case navigate:
                navigate(rc);
                break;
        }
        //updateInfo(rc);
        if(state != null && curObjective != null) rc.setIndicatorString(state.toString() + " : " + curObjective.toString());
        else if(state != null) rc.setIndicatorString(state.toString());
        //if we are stll action ready, and have paint, lets transfer it to low paint soldiers nearby
        if(rc.isActionReady() && rc.getPaint() > 50 && rc.senseMapInfo(rc.getLocation()).getPaint().isAlly()) {
            for(RobotInfo ally : rc.senseNearbyRobots(2, rc.getTeam())) {
                if((ally.getType() == UnitType.SOLDIER || ally.getType() == UnitType.SPLASHER) && ally.getPaintAmount() < 100 && rc.canTransferPaint(ally.getLocation(), rc.getPaint() - 50)) {
                    rc.transferPaint(ally.getLocation(), rc.getPaint() - 50);
                }
            }
        }
    }

    //updates our knowledege of nearby things
    public static void updateInfo(RobotController rc) throws GameActionException {
        enemyRobots = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        allyRobots = rc.senseNearbyRobots(-1, rc.getTeam());
        adjacentEnemyRobots = rc.senseNearbyRobots(2, rc.getTeam().opponent());
        nearbyTiles = rc.senseNearbyMapInfos(13);
    }

    //determins what state the mopper should be in
    public static void updateState(RobotController rc) throws GameActionException {
        state = (rc.getRoundNum() < 100) ? mopStates.explore : mopStates.navigate;
        if(adjacentEnemyRobots.length > 0) {
            //should probably be stealing
            state = mopStates.steal;
        }
        else {
            for(MapInfo loc : nearbyTiles) {
                if(loc.getPaint().isEnemy()){
                    state = mopStates.mop;
                    return;
                }
            }
        }
    }

    //goal is to steal paint from enemy bots
    public static void steal(RobotController rc) throws GameActionException {
        //try and maximize the amount of paint you can take from enemies
        if(rc.isActionReady()) {
            //SWEEP
            if(adjacentEnemyRobots.length > 1) {
                //determine the amount of enemies we would hit with each cardinal sweep - 0: north, 1: east, etc...
                int[] directionalSweep = {0,0,0,0};
                for(RobotInfo enemy : adjacentEnemyRobots) {
                    Direction dir = rc.getLocation().directionTo(enemy.getLocation());
                    switch(dir) {
                        case NORTH:
                            directionalSweep[0]++;
                            break;
                        case NORTHEAST:
                            directionalSweep[0]++;
                            directionalSweep[1]++;
                            break;
                        case EAST:
                            directionalSweep[1]++;
                            break;
                        case SOUTHEAST:
                            directionalSweep[1]++;
                            directionalSweep[2]++;
                            break;
                        case SOUTH:
                            directionalSweep[2]++;
                            break;
                        case SOUTHWEST:
                            directionalSweep[2]++;
                            directionalSweep[3]++;
                            break;
                        case WEST:
                            directionalSweep[3]++;
                            break;
                        case NORTHWEST:
                            directionalSweep[3]++;
                            directionalSweep[0]++;
                            break;
                    }
                }
                Direction dirToSweep = null;
                int highest = -1;
                for(int i = 0; i < 4; i++) {
                    if(directionalSweep[i] > 1 && directionalSweep[i] > highest) {
                        highest = directionalSweep[i];
                        switch(i) {
                            case 0:
                                dirToSweep = Direction.NORTH;
                                break;
                            case 1:
                                dirToSweep = Direction.EAST;
                                break;
                            case 2:
                                dirToSweep = Direction.SOUTH;
                                break;
                            case 3:
                                dirToSweep = Direction.WEST;
                                break;
                        }
                    }
                }
                if(dirToSweep != null) {
                    if(rc.canMopSwing(dirToSweep)) {
                        rc.mopSwing(dirToSweep);
                    }
                }
            }
            //so we didnt swing our mop
            if(rc.isActionReady()) {
                RobotInfo target = findBestTarget(rc);
                if(target.getLocation().distanceSquaredTo(rc.getLocation()) > rc.getType().actionRadiusSquared) {
                    Direction dir = BFS.moveTowards(rc, target.getLocation());
                    MapLocation newLoc = rc.getLocation().add(dir);
                    if(newLoc.distanceSquaredTo(target.getLocation()) <= rc.getType().actionRadiusSquared && dir != null && rc.canMove(dir) && !isEnemyTile(rc.senseMapInfo(newLoc)) && isSafeFromTower(rc, newLoc)) {
                        rc.move(dir);
                    }
                }
                if(rc.canAttack(target.getLocation())) {
                    rc.attack(target.getLocation());
                }
            }
        }
        //stay near enemies but off enemy tiles
        else {
            return;
        }
    }

    //goal is to clean up some enemy paint
    public static void mop(RobotController rc) throws GameActionException {
        //if the mopper can see a ruin, we should probably stay near it and try to mop it up.. ends up more helpful for our team
        for (MapLocation tile : rc.senseNearbyRuins(-1)) {
            if (rc.canSenseLocation(tile) && rc.senseRobotAtLocation(tile) == null){
                if(rc.getLocation().distanceSquaredTo(tile) > 9) {
                    Direction dir = BFS.moveTowards(rc, tile);
                    if(dir != null && rc.canMove(dir) && !isEnemyTile(rc.senseMapInfo(rc.getLocation().add(dir))) && isSafeFromTower(rc, tile)) {
                        rc.move(dir);
                        break;
                    }
                }
                //try and do our normal turn, but dont leave a certain radius of the ruins
                if(rc.isActionReady()) {
                    MapInfo loc = null;
                    for(MapInfo m : nearbyTiles) {
                        if(m.getPaint().isEnemy()) {
                            loc = m;
                            if(rc.canAttack(m.getMapLocation())) {
                                rc.attack(m.getMapLocation());
                                break;
                            }
                        }
                    }
                    if(rc.isMovementReady()) {
                        assert loc != null;
                        Direction dir = BFS.moveTowards(rc, loc.getMapLocation());
                        if(dir != null) {
                            MapLocation newLoc = rc.getLocation().add(dir);
                            if (newLoc.isWithinDistanceSquared(tile, 9) && rc.canMove(dir) && !isEnemyTile(rc.senseMapInfo(newLoc)) && isSafeFromTower(rc, newLoc)) {
                                rc.move(dir);
                            }
                        }
                    }
                    //still havent mopped anything, try again
                    if(rc.isActionReady()) {
                        for(MapInfo m : nearbyTiles) {
                            if(m.getPaint().isEnemy() && rc.canAttack(m.getMapLocation())) {
                                rc.attack(m.getMapLocation());
                                break;
                            }
                        }
                    }
                }
                return;
            }
        }
        //this is the turn if we dont see any unoccupied ruins
        //mop some shit up!
        if(rc.isActionReady()) {
            MapInfo loc = null;
            for(MapInfo m : nearbyTiles) {
                if(m.getPaint().isEnemy()) {
                    loc = m;
                     if(rc.canAttack(m.getMapLocation())) {
                         rc.attack(m.getMapLocation());
                         break;
                     }
                }
            }
            if(rc.isMovementReady()) {
                assert loc != null;
                Direction dir = BFS.moveTowards(rc, loc.getMapLocation());
                if (dir != null && rc.canMove(dir) && !isEnemyTile(rc.senseMapInfo(rc.getLocation().add(dir))) && isSafeFromTower(rc, loc.getMapLocation())) {
                    rc.move(dir);
                }
            }
            //still havent mopped anything, try again
            if(rc.isActionReady()) {
                for(MapInfo m : nearbyTiles) {
                    if(m.getPaint().isEnemy() && rc.canAttack(m.getMapLocation())) {
                        rc.attack(m.getMapLocation());
                        break;
                    }
                }
            }
        }
    }

    //goal is to explore... but try and stay on our own paint - main exploring is done by soldiers, we just wanna follow
    public static void explore(RobotController rc) throws GameActionException {
        if (curObjective == null) {
            curObjective = new MapLocation(rng.nextInt(rc.getMapWidth() - 6) + 3, rng.nextInt(rc.getMapHeight() - 6) + 3);
        }
        if (rc.getLocation().distanceSquaredTo(curObjective) < 16) {
            curObjective = new MapLocation(rng.nextInt(rc.getMapWidth() - 6) + 3, rng.nextInt(rc.getMapHeight() - 6) + 3);
        }
        Direction dir = BFS.moveTowards(rc, curObjective);
        if(dir != null && rc.canMove(dir)) {
            rc.move(dir);
        }
    }

    //like exploring, but with a destination in mind through comms
    public static void navigate(RobotController rc) throws GameActionException {
        for(Ruin r : Communication.ruinsMemory)
        {
            if(r.status == 2)
            {
                if(nearestEnemyTower == null || rc.getLocation().distanceSquaredTo(r.location) < rc.getLocation().distanceSquaredTo(nearestEnemyTower))
                {
                    nearestEnemyTower = r.location;
                }
            }
            else if(r.status == 0)
            {
                if(nearestRuin == null || rc.getLocation().distanceSquaredTo(r.location) < rc.getLocation().distanceSquaredTo(nearestRuin))
                {
                    nearestRuin = r.location;
                }
            }
        }
        if(nearestEnemyTower != null)
        {
            curObjective = nearestEnemyTower;
            //System.out.println("Splasher moving towards: nearestEnemyTower " + curObjective);
        }
        else if(nearestRuin != null && !rc.canSenseLocation(nearestRuin))
        {
            curObjective = nearestRuin;
            //System.out.println("Splasher moving towards: nearestRuin" + curObjective);
        }
        else
        {
            explore(rc);
            return;

        }

        Direction dir = BFS.moveTowards(rc, curObjective);
        if(dir != null && rc.canMove(dir))
        {
            rc.move(dir);
        }

    }

    //returns the direction where our allies and our ally paint is
    public static Direction determineDirectionOfAllies(RobotController rc) throws GameActionException {
        MapInfo[] adjacentSquares = rc.senseNearbyMapInfos(2);
        if(allyRobots.length == 0) {
            for(MapInfo info : adjacentSquares) {
                if(info.getPaint().isAlly()) {
                    return rc.getLocation().directionTo(info.getMapLocation());
                }
            }
        }
        //try to determine which location has the most allies
        else {
            int[] allyCount = new int[8];
            int i = 0;
            int highest = 0;
            Direction directionOfHighest = null;
            for(int dx = -1; dx <= 1; dx++) {
                for(int dy = -1; dy <= 1; dy++) {
                    if(dx == 0 && dy == 0) {
                        continue;
                    }
                    MapLocation center = new MapLocation(rc.getLocation().x + dx, rc.getLocation().y + dy);
                    allyCount[i] = rc.senseNearbyRobots(center,-1, rc.getTeam()).length;
                    //give a major bonus for having ally paint on the square
                    if(rc.senseMapInfo(center).getPaint().isAlly()) {
                        allyCount[i] += 2;
                    }
                    //conversely, a major penalty for having enemy paint on the square
                    else if(isEnemyTile(rc.senseMapInfo(center))) {
                        allyCount[i] -= 3;
                    }
                    if(allyCount[i] > highest) {
                        highest = allyCount[i];
                        directionOfHighest = rc.getLocation().directionTo(center);
                    }
                    i++;
                }
            }
            return directionOfHighest;
        }
        return null;
    }

    public static RobotInfo findBestTarget(RobotController rc) throws GameActionException {
        int highestScore = -1;
        RobotInfo target = null;
        for(RobotInfo robot : adjacentEnemyRobots) {
            MapInfo loc = rc.senseMapInfo(robot.getLocation());
            int score = (robot.getPaintAmount() == 0) ? 1 : robot.getType().paintCapacity - robot.getPaintAmount();
            //add a big bonus if we are also removing paint from the tile
            if(isEnemyTile(loc)) score += 50;
            if(score > highestScore) {
                highestScore = score;
                target = robot;
            }
        }
        return target;
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
