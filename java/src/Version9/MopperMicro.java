package Version9;

import battlecode.common.*;
import static Version9.Mopper.*;
import static Version9.RobotPlayer.*;


class mopperMicroInfo {
    private static final int ALLY_PAINT = 1;
    private static final int ENEMY_PAINT = -1;
    private static final int NEUTRAL_PAINT = 0;
    boolean passable;
    MapLocation loc;
    int minDistanceToEnemy;
    int adjacentAllies;
    int paintType;
    boolean inTowerRange;
    int distanceToEnemyAverage;
    int paintLoss;

    public mopperMicroInfo(MapInfo tile) {
        if(!tile.isPassable()) {
            passable = false;
            return;
        }
        passable = true;
        loc = tile.getMapLocation();
        paintType = switch(tile.getPaint()) {
            case PaintType.EMPTY -> NEUTRAL_PAINT;
            case PaintType.ALLY_PRIMARY -> ALLY_PAINT;
            case PaintType.ALLY_SECONDARY -> ALLY_PAINT;
            case PaintType.ENEMY_SECONDARY -> ENEMY_PAINT;
            case PaintType.ENEMY_PRIMARY -> ENEMY_PAINT;
        };
        populateMopperMicroInfo();
    }

    public mopperMicroInfo() {
        passable = false;
    }

    //populates the info you can't get from only knowing the tile
    void populateMopperMicroInfo() {
        distanceToEnemyAverage = loc.distanceSquaredTo(averageEnemyPaint);
//        MapLocation[] enemyPaintAverages = Utilities.getEnemyPaintAverages();
//        distanceToEnemyAverage = switch(enemyPaintAverages.length) {
//            case 0 -> Integer.MAX_VALUE;
//            case 1 -> loc.distanceSquaredTo(enemyPaintAverages[0]);
//            case 2 -> Math.min(loc.distanceSquaredTo(enemyPaintAverages[0]), loc.distanceSquaredTo(enemyPaintAverages[1]));
//            default -> Integer.MAX_VALUE;
//        };
        //find the closest enemy, and also see if we are safe from towers
        for(RobotInfo robot : enemyRobots) {
            int dist = loc.distanceSquaredTo(robot.getLocation());
            if(dist < minDistanceToEnemy) {
                minDistanceToEnemy = dist;
            }
            if(robot.type.isTowerType() && dist <= robot.type.actionRadiusSquared) {
                inTowerRange = true;
                if(minDistanceToEnemy == 1) break;
            }
            if(minDistanceToEnemy == 1 && inTowerRange) break;
        }
        //count adjacent allies (depending on ally robots length, might be faster to call sensenearbyrobots?)
        for(RobotInfo robot : allyRobots) {
            if(loc.isWithinDistanceSquared(robot.getLocation(), 1)) {
                adjacentAllies++;
            }
        }
        paintLoss = switch(paintType) {
            case ENEMY_PAINT -> 4 + adjacentAllies;
            case ALLY_PAINT -> adjacentAllies;
            case NEUTRAL_PAINT -> 2 + adjacentAllies;
            default -> 0;
        };
    }
}

public class MopperMicro {
    private static final int ALLY_PAINT = 1;
    private static final int ENEMY_PAINT = -1;
    private static final int NEUTRAL_PAINT = 0;

    static mopperMicroInfo[] microArray;

    //mopper micro - will find the best place to mop, and move, both within this method
    //priorities list:
    //1. Attack square within radius of contested ruin - 0b10000
    //2. Attack square with self -0b1000
    //3. Attack square with enemy -0b100
    //4. Attack square without moving -0b10
    //5. Attack square with ally robot - 0b01
    public static void integratedMopperMicro(RobotController rc) throws GameActionException {
        //MapInfo[] tiles = rc.senseNearbyMapInfos(8);
        if(rc.isActionReady()) {
            //int price = Clock.getBytecodesLeft();
            Direction dirToSweep = dirToSweep(rc, (rc.getPaint() > 30) ? 2 : 3);
            //System.out.println(price - Clock.getBytecodesLeft());
            if(dirToSweep != null && rc.canMopSwing(dirToSweep)) {
                rc.mopSwing(dirToSweep);
                safeMopperMicro(rc);
                return;
            }
            int x = rc.getLocation().x;
            int y = rc.getLocation().y;
            int bestX = -1;
            int bestY = -1;
            int bestScore = -1;
            int basDist = 0;
            int bestDist = Integer.MAX_VALUE;
            //int[][] scores = new int[5][5];
//            for (int dx = -2; dx <= 2; dx++) {
//                for (int dy = -2; dy <= 2; dy++) {
//                    basDist = Math.abs(dx) + Math.abs(dy);
//                    int score = determineScore(rc, x + dx, y + dy);
//                    if (score > bestScore || (score == bestScore && basDist < bestDist)) {
//                        bestX = x + dx;
//                        bestY = y + dy;
//                        bestScore = score;
//                        bestDist = basDist;
//                    }
//                }
//            }
            {
                basDist = 4;
                int score = determineScore(rc, x + -2, y + -2);
                if (score >= bestScore) {
                    bestX = x + -2;
                    bestY = y + -2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 3;
                int score = determineScore(rc, x + -2, y + -1);
                if (score >= bestScore) {
                    bestX = x + -2;
                    bestY = y + -1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 2;
                int score = determineScore(rc, x + -2, y + 0);
                if (score >= bestScore) {
                    bestX = x + -2;
                    bestY = y;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 3;
                int score = determineScore(rc, x + -2, y + 1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + -2;
                    bestY = y + 1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 4;
                int score = determineScore(rc, x + -2, y + 2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + -2;
                    bestY = y + 2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 3;
                int score = determineScore(rc, x + -1, y + -2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + -1;
                    bestY = y + -2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 2;
                int score = determineScore(rc, x + -1, y + -1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + -1;
                    bestY = y + -1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 1;
                int score = determineScore(rc, x + -1, y);
                if (score >= bestScore) {
                    bestX = x + -1;
                    bestY = y;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 2;
                int score = determineScore(rc, x + -1, y + 1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + -1;
                    bestY = y + 1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 3;
                int score = determineScore(rc, x + -1, y + 2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + -1;
                    bestY = y + 2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 2;
                int score = determineScore(rc, x, y + -2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x;
                    bestY = y + -2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 1;
                int score = determineScore(rc, x, y + -1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x;
                    bestY = y + -1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 0;
                int score = determineScore(rc, x, y);
                if (score >= bestScore) {
                    bestX = x;
                    bestY = y;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 1;
                int score = determineScore(rc, x, y + 1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x;
                    bestY = y + 1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 2;
                int score = determineScore(rc, x, y + 2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x;
                    bestY = y + 2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 3;
                int score = determineScore(rc, x + 1, y + -2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 1;
                    bestY = y + -2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 2;
                int score = determineScore(rc, x + 1, y + -1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 1;
                    bestY = y + -1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 1;
                int score = determineScore(rc, x + 1, y);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 1;
                    bestY = y;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 2;
                int score = determineScore(rc, x + 1, y + 1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 1;
                    bestY = y + 1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 3;
                int score = determineScore(rc, x + 1, y + 2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 1;
                    bestY = y + 2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 4;
                int score = determineScore(rc, x + 2, y + -2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 2;
                    bestY = y + -2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 3;
                int score = determineScore(rc, x + 2, y + -1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 2;
                    bestY = y + -1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 2;
                int score = determineScore(rc, x + 2, y);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 2;
                    bestY = y;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 3;
                int score = determineScore(rc, x + 2, y + 1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 2;
                    bestY = y + 1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 4;
                int score = determineScore(rc, x + 2, y + 2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 2;
                    bestY = y + 2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            //nowhere to attack
            if (bestScore == -1) {
                rc.setIndicatorString("aggroMopperMicro");
                aggroMopperMicro(rc);
                if (Clock.getBytecodesLeft() > 3000)
                    attackAnything(rc);
                return;
            } else {
                MapLocation target = new MapLocation(bestX, bestY);
                if (rc.canAttack(target)) {
                    rc.attack(target);
                    rc.setIndicatorString("safeMopperMicro");
                    safeMopperMicro(rc);
                    return;
                } else {
                    rc.setIndicatorString("targetedMopperMicro: " + target.toString() + " : " + customLocationTo(rc.getLocation(), target));
                    targetedMopperMicro(rc, customLocationTo(rc.getLocation(), target), target);
                    if (rc.canAttack(target)) {
                        rc.attack(target);
                    } else {
                        if (Clock.getBytecodesLeft() > 3000)
                            attackAnything(rc);
                    }
                }
            }
        }
        else {
            rc.setIndicatorString("safeMopperMicro");
            safeMopperMicro(rc);
        }
    }

    //if we are at the end of a turn and haven't been able to attack what we wanted, just attack the first thing we can
    public static void attackAnything(RobotController rc) throws GameActionException {
        for(MapInfo tile : nearbyTiles) {
            if(rc.canAttack(tile.getMapLocation()) && (tile.getPaint().isEnemy() || rc.canSenseRobotAtLocation(tile.getMapLocation()) && rc.senseRobotAtLocation(tile.getMapLocation()).getTeam() == rc.getTeam().opponent())) {
                rc.attack(tile.getMapLocation());
                return;
            }
        }
    }

    //determines the score of a space mopping wise
    public static int determineScore(RobotController rc, int x, int y) throws GameActionException {
        int score = 0;
        MapLocation temp = new MapLocation(x, y);
        if(rc.onTheMap(temp)) {
            MapInfo m = rc.senseMapInfo(temp);
            if(!m.getPaint().isEnemy() || !m.isPassable()) return -1;
            RobotInfo r = rc.senseRobotAtLocation(temp);
            //roughly determines whether we would have to move into tower range to attack this square
            if(seenEnemyTower != null) {
                boolean withinFunctionalTowerRange = switch (seenEnemyTower.getType()) {
                    case LEVEL_ONE_DEFENSE_TOWER, LEVEL_TWO_DEFENSE_TOWER, LEVEL_THREE_DEFENSE_TOWER ->
                            temp.isWithinDistanceSquared(seenEnemyTower.getLocation(), 8);
                    default -> temp.isWithinDistanceSquared(seenEnemyTower.getLocation(), 3);
                };
                if (withinFunctionalTowerRange) return -1;
            }
            if(Utilities.basicLocationIsBehindWall(rc, m.getMapLocation())){
                rc.setIndicatorDot(temp, 0, 0, 255);
                return -1;
            }
            if(r != null) {
                if(rc.getTeam() == r.getTeam()) {
                    score |= 0b01;
                }
                else if (r.getPaintAmount() > 0){
                    score |= 0b100;
                }
            }
            if(temp.equals(rc.getLocation())) {
                score |= 0b1000;
            }
            if(nearbyRuin != null && temp.isWithinDistanceSquared(nearbyRuin, 8)) {
                score |= 0b10000;
            }
            if(temp.isWithinDistanceSquared(rc.getLocation(), UnitType.MOPPER.actionRadiusSquared)) {
                score |= 0b10;
            }
        }
        else {
            return -1;
        }
        return score;
    }

    //returns the best direction to sweep, if there is any sweep direction which will have a score greater than
    //or equal to minScore. If there is no such direction, return null
    public static Direction dirToSweep(RobotController rc, int minScore) throws GameActionException {
        RobotInfo[] adjacentEnemyRobots = rc.senseNearbyRobots(7, rc.getTeam().opponent());
        Direction dirToSweep = null;
        if(adjacentEnemyRobots.length >= minScore) {
            //determine the amount of enemies we would hit with each cardinal sweep - 0: north, 1: east, etc...
            int[] directionalSweep = {0,0,0,0};
            for(RobotInfo enemy : adjacentEnemyRobots) {
                //Direction dir = rc.getLocation().directionTo(enemy.getLocation());
                Direction dir = customLocationTo(rc.getLocation(), enemy.getLocation());
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
            int highest = -1;
            for(int i = 0; i < 4; i++) {
                if(directionalSweep[i] >= minScore && directionalSweep[i] > highest) {
                    highest = directionalSweep[i];
                    dirToSweep = switch (i) {
                        case 0 -> Direction.NORTH;
                        case 1 -> Direction.EAST;
                        case 2 -> Direction.SOUTH;
                        case 3 -> Direction.WEST;
                        default -> dirToSweep;
                    };
                }
            }
        }
        return dirToSweep;
    }

    //we can't mop anything, so we should try to move towards enemies and be aggressive
    public static void aggroMopperMicro(RobotController rc) throws GameActionException {
        if(!rc.isMovementReady()) return;
        rc.setIndicatorString("aggroMopperMicro");
        microArray = new mopperMicroInfo[9];
        populateMopperMicroArray(rc);
        mopperMicroInfo bestMicro = microArray[0];
        for(int i = 1; i < microArray.length; i++) {
            mopperMicroInfo m = microArray[i];
            if(!m.passable) continue;
            if(!bestMicro.passable) {
                bestMicro = m;
                continue;
            }

            //even tho we want to be aggressive, we still want to stay away from towers and stay on friendly paint
            //if one is in tower range and the other isnt, get out of tower range
            if(!bestMicro.inTowerRange && m.inTowerRange) continue;
            if(bestMicro.inTowerRange && !m.inTowerRange) {
                bestMicro = m;
                continue;
            }


            if(bestMicro.distanceToEnemyAverage < m.distanceToEnemyAverage) continue;
            if(bestMicro.distanceToEnemyAverage > m.distanceToEnemyAverage) {
                bestMicro = m;
                continue;
            }

            //look at which place will lose us the least paint at the end of this turn
            if(bestMicro.paintLoss < microArray[i].paintLoss) continue;
            if(microArray[i].paintLoss < bestMicro.paintLoss) {
                bestMicro = microArray[i];
                continue;
            }

            //if one space is on allied paint and the other isnt, go to allied paint
            if(bestMicro.paintType == ALLY_PAINT && m.paintType != ALLY_PAINT) continue;
            if(bestMicro.paintType != ALLY_PAINT && m.paintType == ALLY_PAINT) {
                bestMicro = m;
                continue;
            }

            //however, now lets minimize our distance to an enemy
            //if one space avoids enemy paint and the other doesnt, go to the one avoiding enemy paint
            if(bestMicro.minDistanceToEnemy < m.minDistanceToEnemy) continue;
            if(bestMicro.minDistanceToEnemy > m.minDistanceToEnemy) {
                bestMicro = m;
                continue;
            }

            //finally, lets avoid being adjacent to allies
            if(bestMicro.adjacentAllies < m.adjacentAllies) continue;
            if(bestMicro.adjacentAllies > m.adjacentAllies) {
                bestMicro = m;
                continue;
            }
        }
        if(bestMicro.loc != null && rc.canMove(rc.getLocation().directionTo(bestMicro.loc))) rc.move(rc.getLocation().directionTo(bestMicro.loc));
    }
    //we already mopped, or otherwise have no action cooldown, so we should try and stay out of harms way
    public static void safeMopperMicro(RobotController rc) throws GameActionException {
        if(!rc.isMovementReady()) return;
        rc.setIndicatorString("safeMopperMicro");
        microArray = new mopperMicroInfo[9];
        populateMopperMicroArray(rc);
        mopperMicroInfo bestMicro = microArray[0];
        for(int i = 1; i < microArray.length; i++) {
            mopperMicroInfo m = microArray[i];
            if(!m.passable) continue;
            if(!bestMicro.passable) {
                bestMicro = m;
                continue;
            }

            //since we want to be safe, lets try and stay somewhat away from enemies - otherwise, the usual criteria
            //if one is in tower range and the other isnt, get out of tower range
            if(!bestMicro.inTowerRange && m.inTowerRange) continue;
            if(bestMicro.inTowerRange && !m.inTowerRange) {
                bestMicro = m;
                continue;
            }

            //look at which place will lose us the least paint at the end of this turn
            if(bestMicro.paintLoss < microArray[i].paintLoss) continue;
            if(microArray[i].paintLoss < bestMicro.paintLoss) {
                bestMicro = microArray[i];
                continue;
            }

            //if one space is on allied paint and the other isnt, go to allied paint
            if(bestMicro.paintType == ALLY_PAINT && m.paintType != ALLY_PAINT) continue;
            if(bestMicro.paintType != ALLY_PAINT && m.paintType == ALLY_PAINT) {
                bestMicro = m;
                continue;
            }

            //if one space avoids enemy paint and the other doesnt, go to the one avoiding enemy paint
            if(bestMicro.paintType != ENEMY_PAINT && m.paintType == ENEMY_PAINT) continue;
            if(bestMicro.paintType == ENEMY_PAINT && m.paintType != ENEMY_PAINT) {
                bestMicro = m;
                continue;
            }

            if(bestMicro.distanceToEnemyAverage < m.distanceToEnemyAverage) continue;
            if(bestMicro.distanceToEnemyAverage > m.distanceToEnemyAverage) {
                bestMicro = m;
                continue;
            }

            //lets avoid being adjacent to allies
            if(bestMicro.adjacentAllies < m.adjacentAllies) continue;
            if(bestMicro.adjacentAllies > m.adjacentAllies) {
                bestMicro = m;
                continue;
            }

            //finally, lets try not to be directly next to an enemy
            if(bestMicro.minDistanceToEnemy > 2 && m.minDistanceToEnemy <= 2) continue;
            if(bestMicro.minDistanceToEnemy <= 2 && m.minDistanceToEnemy > 2) {
                bestMicro = m;
                continue;
            }
        }
        if(bestMicro.loc != null && rc.canMove(rc.getLocation().directionTo(bestMicro.loc))) rc.move(rc.getLocation().directionTo(bestMicro.loc));
    }
    //we have a target that we can't mop yet, so we should try and move towards it then mop it
    public static void targetedMopperMicro(RobotController rc, Direction dir, MapLocation target) throws GameActionException {
        //mopperMicroInfo currentSquare = new mopperMicroInfo(rc.senseMapInfo(rc.getLocation()));
        //populating the array
        switch(dir) {
            case NORTH -> {
                microArray = new mopperMicroInfo[3];
                for(int dx = -1; dx <= 1; dx++) {
                    MapLocation newLoc = new MapLocation(rc.getLocation().x + dx, rc.getLocation().y + 1);
                    microArray[dx + 1] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(rc.senseMapInfo(newLoc));
                }
            }
            case NORTHEAST -> {
                microArray = new mopperMicroInfo[1];
                MapLocation newLoc = new MapLocation(rc.getLocation().x + 1, rc.getLocation().y + 1);
                microArray[0] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(rc.senseMapInfo(newLoc));
            }
            case EAST -> {
                microArray = new mopperMicroInfo[3];
                for(int dy = -1; dy <= 1; dy++) {
                    MapLocation newLoc = new MapLocation(rc.getLocation().x + 1, rc.getLocation().y + dy);
                    microArray[dy + 1] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(rc.senseMapInfo(newLoc));
                }
            }
            case SOUTHEAST -> {
                microArray = new mopperMicroInfo[1];
                MapLocation newLoc = new MapLocation(rc.getLocation().x + 1, rc.getLocation().y - 1);
                microArray[0] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(rc.senseMapInfo(newLoc));
            }
            case SOUTH -> {
                microArray = new mopperMicroInfo[3];
                for(int dx = -1; dx <= 1; dx++) {
                    MapLocation newLoc = new MapLocation(rc.getLocation().x + dx, rc.getLocation().y - 1);
                    microArray[dx + 1] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(rc.senseMapInfo(newLoc));
                }
            }
            case SOUTHWEST -> {
                microArray = new mopperMicroInfo[1];
                MapLocation newLoc = new MapLocation(rc.getLocation().x - 1, rc.getLocation().y - 1);
                microArray[0] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(rc.senseMapInfo(newLoc));
            }
            case WEST -> {
                microArray = new mopperMicroInfo[3];
                for(int dy = -1; dy <= 1; dy++) {
                    MapLocation newLoc = new MapLocation(rc.getLocation().x - 1, rc.getLocation().y + dy);
                    microArray[dy + 1] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(rc.senseMapInfo(newLoc));
                }
            }
            case NORTHWEST -> {
                microArray = new mopperMicroInfo[1];
                MapLocation newLoc = new MapLocation(rc.getLocation().x - 1, rc.getLocation().y + 1);
                microArray[0] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(rc.senseMapInfo(newLoc));
            }
            case CENTER -> {
                microArray = null;
                return;
            }
        };
        int actionRadius = UnitType.MOPPER.actionRadiusSquared;
        //determining the best space
        //mopperMicroInfo bestMicro = microArray[0];
        //TURN 176 DEFAULT MEDIUM VERSION 9 GRAY VERSION 8 GOLD
        mopperMicroInfo bestMicro = new mopperMicroInfo(rc.senseMapInfo(rc.getLocation()));
        for(int i = 0; i < microArray.length; i++) {
            mopperMicroInfo m = microArray[i];
            if(!m.passable) continue;
            if(!bestMicro.passable) {
                bestMicro = microArray[i];
                continue;
            }

            //if one is in tower range and the other isnt, get out of tower range
            if(!bestMicro.inTowerRange && m.inTowerRange) {
                continue;
            }
            if(bestMicro.inTowerRange && !m.inTowerRange) {
                bestMicro = m;
                continue;
            }

            //if one space avoids enemy paint and the other doesnt, go to the one avoiding enemy paint
            if(bestMicro.paintType != ENEMY_PAINT && m.paintType == ENEMY_PAINT) continue;
            if(bestMicro.paintType == ENEMY_PAINT && m.paintType != ENEMY_PAINT) {
                bestMicro = m;
                continue;
            }

            int dist = bestMicro.loc.distanceSquaredTo(target);
            int altDist = m.loc.distanceSquaredTo(target);
            //System.out.println(dist + " " + altDist + " : " + bestMicro.loc + " " + m.loc);
            if(dist <= actionRadius && altDist > actionRadius) continue;
            if(dist > actionRadius && altDist <= actionRadius) {
                bestMicro = m;
                continue;
            }

            //look at which place will lose us the least paint at the end of this turn
            if(bestMicro.paintLoss < microArray[i].paintLoss) continue;
            if(microArray[i].paintLoss < bestMicro.paintLoss) {
                bestMicro = microArray[i];
                continue;
            }

            //if one space is on allied paint and the other isnt, go to allied paint
            if(bestMicro.paintType == ALLY_PAINT && m.paintType != ALLY_PAINT) continue;
            if(bestMicro.paintType != ALLY_PAINT && m.paintType == ALLY_PAINT) {
                bestMicro = m;
                continue;
            }


            if(bestMicro.distanceToEnemyAverage < m.distanceToEnemyAverage) continue;
            if(bestMicro.distanceToEnemyAverage > m.distanceToEnemyAverage) {
                bestMicro = m;
                continue;
            }

            //finally, lets avoid being adjacent to allies
            if(bestMicro.adjacentAllies < m.adjacentAllies) continue;
            if(bestMicro.adjacentAllies > m.adjacentAllies) {
                bestMicro = m;
                continue;
            }
        }
        //Systemout.println(bestMicro.loc);
        if(bestMicro.passable && rc.canMove(rc.getLocation().directionTo(bestMicro.loc))) {
            rc.move(rc.getLocation().directionTo(bestMicro.loc));
        }
    }

    public static void populateMopperMicroArray(RobotController rc) throws GameActionException {
        microArray = new mopperMicroInfo[9];
        int mapHeight = rc.getMapHeight() - 1;
        int mapWidth = rc.getMapWidth() - 1;
        MapLocation curLoc = rc.getLocation();
        int curX = curLoc.x;
        int curY = curLoc.y;
        int totalFilled = 0;
        //loop before being unrolled, in case changes need to be made
//        for(int dx = -1; dx <= 1; dx++) {
//            if(curX + dx >= 0 && curX + dx <= mapWidth) {
//                for (int dy = -1; dy <= 1; dy++) {
//                    if(curY + dy >= 0 && curY + dy <= mapHeight) {
//                        MapLocation newLoc = new MapLocation(curX + dx, curY + dy);
//                        if(rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
//                        else microArray[totalFilled] = new mopperMicroInfo(rc.senseMapInfo(newLoc));
//                        totalFilled++;
//                    }
//                }
//            }
//        }
        if (curX + -1 >= 0 && curX + -1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + -1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                else microArray[totalFilled] = new mopperMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 0);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                else microArray[totalFilled] = new mopperMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                else microArray[totalFilled] = new mopperMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
        }
        if (curX + 0 >= 0 && curX + 0 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + -1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                else microArray[totalFilled] = new mopperMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + 0);
                //if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                /*else*/ microArray[totalFilled] = new mopperMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + 1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                else microArray[totalFilled] = new mopperMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
        }
        if (curX + 1 >= 0 && curX + 1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + -1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                else microArray[totalFilled] = new mopperMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 0);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                else microArray[totalFilled] = new mopperMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                else microArray[totalFilled] = new mopperMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
        }
        //populate the rest of the array in case we are on the edge of the map and couldn't fill it all
        for(int i = totalFilled; i < 9; i++) {
            microArray[i] = new mopperMicroInfo();
        }
    }

    //used to only return a corner direction if the offsets are equal
    //only will be used with a range of dx -2 to 2, same for dy
    //returns the direction from location 1 to location 2
    public static Direction customLocationTo(MapLocation loc1, MapLocation loc2) {
        int dx = loc2.x - loc1.x;
        int dy = loc2.y - loc1.y;
        return switch(dx) {
            case 0 -> switch(dy) {
                case -2, -1, -3, -4 -> Direction.SOUTH;
                case 2, 1, 3, 4 -> Direction.NORTH;
                default -> Direction.CENTER;
            };
            case 2 -> switch(dy) {
                case 3, 4 -> Direction.NORTH;
                case -3, -4 -> Direction.SOUTH;
                case 2 -> Direction.NORTHEAST;
                case -2 -> Direction.SOUTHEAST;
                case -1,0,1 -> Direction.EAST;
                default -> Direction.CENTER;
            };
            case 1 -> switch(dy) {
                case 1 -> Direction.NORTHEAST;
                case -1 -> Direction.SOUTHEAST;
                case 2, 3, 4 -> Direction.NORTH;
                case -2, -3, -4 -> Direction.SOUTH;
                case 0 -> Direction.EAST;
                default -> Direction.CENTER;
            };
            case -1 -> switch(dy) {
                case 1 -> Direction.NORTHWEST;
                case -1 -> Direction.SOUTHWEST;
                case 2, 3, 4 -> Direction.NORTH;
                case -2, -3, -4 -> Direction.SOUTH;
                case 0 -> Direction.WEST;
                default -> Direction.CENTER;
            };
            case -2 -> switch(dy) {
                case 3, 4 -> Direction.NORTH;
                case -3, -4 -> Direction.SOUTH;
                case 2 -> Direction.NORTHWEST;
                case -2 -> Direction.SOUTHWEST;
                case -1,0,1 -> Direction.WEST;
                default -> Direction.CENTER;
            };
            case 3 -> switch(dy) {
                case -2, -1, 0, 1, 2 -> Direction.EAST;
                case -4 -> Direction.SOUTH;
                case 4 -> Direction.NORTH;
                case 3 -> Direction.NORTHEAST;
                case -3 -> Direction.SOUTHEAST;
                default -> Direction.CENTER;
            };
            case -3 -> switch(dy) {
                case -2, -1, 0, 1, 2 -> Direction.WEST;
                case -4 -> Direction.SOUTH;
                case 4 -> Direction.NORTH;
                case 3 -> Direction.NORTHWEST;
                case -3 -> Direction.SOUTHWEST;
                default -> Direction.CENTER;
            };
            case -4 -> switch(dy) {
                case -2, -1, 0, 1, 2, 3, -3 -> Direction.WEST;
                case -4 -> Direction.SOUTHWEST;
                case 4 -> Direction.NORTHWEST;
                default -> Direction.CENTER;
            };
            case 4 -> switch(dy) {
                case -2, -1, 0, 1, 2, 3, -3 -> Direction.EAST;
                case -4 -> Direction.SOUTHEAST;
                case 4 -> Direction.NORTHEAST;
                default -> Direction.CENTER;
            };
            default -> Direction.CENTER;
        };
    }
}
