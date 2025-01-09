package Version4;

import battlecode.common.*;

import java.util.ArrayList;

import static Version4.RobotPlayer.*;

enum mopStates {
    mop, steal, explore
}

public class Mopper {

    //keeps track of the state for the individual mopper
    private static mopStates state;

    //info the mopper will need at various points throughout its turn
    private static RobotInfo[] enemyRobots;
    private static RobotInfo[] adjacentEnemyRobots;
    private static RobotInfo[] allyRobots;
    private static ArrayList<MapInfo> nearbyEnemyTiles;
    private static MapLocation curObjective = null;

    public static void runMopper(RobotController rc) throws GameActionException {
        if(RobotPlayer.turnCount == 1) nearbyEnemyTiles = new ArrayList<>();
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
        }
        updateInfo(rc);
        if(state != null) rc.setIndicatorString(state.toString());
    }

    //updates our knowledege of nearby things
    public static void updateInfo(RobotController rc) throws GameActionException {
        enemyRobots = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        allyRobots = rc.senseNearbyRobots(-1, rc.getTeam());
        adjacentEnemyRobots = rc.senseNearbyRobots(2, rc.getTeam().opponent());
        nearbyEnemyTiles.clear();
        for(MapInfo loc : rc.senseNearbyMapInfos(-1)) {
            if(!loc.getPaint().isAlly() && !(loc.getPaint() == PaintType.EMPTY)) {
                nearbyEnemyTiles.add(loc);
            }
        }
    }

    //determins what state the mopper should be in
    public static void updateState(RobotController rc) throws GameActionException {
        state = mopStates.explore;
        if(adjacentEnemyRobots.length > 0) {
            //should probably be stealing
            state = mopStates.steal;
        }
        else if(!nearbyEnemyTiles.isEmpty()) {
            //should probably be mopping
            state = mopStates.mop;
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
                Direction dir = BFS.moveTowards(rc, tile);
                if(dir != null && rc.canMove(dir) && !isEnemyTile(rc.senseMapInfo(rc.getLocation().add(dir))) && isSafeFromTower(rc, tile)) {
                    rc.move(dir);
                }
            }
        }
        //mop some shit up!
        if(rc.isActionReady()) {
            MapInfo loc = nearbyEnemyTiles.getFirst();
            for(MapInfo m : nearbyEnemyTiles) {
                if(rc.canAttack(m.getMapLocation())) {
                    loc = m;
                    rc.attack(m.getMapLocation());
                    break;
                }
            }
            Direction dir = BFS.moveTowards(rc, loc.getMapLocation());
            if(dir != null && rc.canMove(dir) && !isEnemyTile(rc.senseMapInfo(rc.getLocation().add(dir))) && isSafeFromTower(rc, loc.getMapLocation())) {
                rc.move(dir);
            }
            //still havent mopped anything, try again
            if(rc.isActionReady()) {
                for(MapInfo m : nearbyEnemyTiles) {
                    if(rc.canAttack(m.getMapLocation())) {
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
            int score = robot.getType().paintCapacity - robot.getPaintAmount();
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
