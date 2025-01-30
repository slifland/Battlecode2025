package Version15.Micro;

import Version15.Robots.Splasher;
import Version15.Utility.splasherUtil;
import battlecode.common.*;

import static Version15.RobotPlayer.*;
import static Version15.Robots.Splasher.averageEnemyPaint;
import static Version15.Robots.Splasher.seenEnemyTower;

class splasherMicroInfo {
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
    int potentialPaintLoss;

    public splasherMicroInfo(MapInfo tile) {
        if (!tile.isPassable()) {
            passable = false;
            return;
        }
        passable = true;
        loc = tile.getMapLocation();
        paintType = switch (tile.getPaint()) {
            case EMPTY -> NEUTRAL_PAINT;
            case ALLY_PRIMARY -> ALLY_PAINT;
            case ALLY_SECONDARY -> ALLY_PAINT;
            case ENEMY_SECONDARY -> ENEMY_PAINT;
            case ENEMY_PRIMARY -> ENEMY_PAINT;
        };
        populateSplasherMicroInfo();
    }

    public splasherMicroInfo() {
        passable = false;
    }

    //populates the info you can't get from only knowing the tile
    void populateSplasherMicroInfo() {
        if (averageEnemyPaint != null) distanceToEnemyAverage = loc.distanceSquaredTo(averageEnemyPaint);
        else if (seenEnemyTower != null) distanceToEnemyAverage = loc.distanceSquaredTo(seenEnemyTower.getLocation());
        //count adjacent allies (depending on ally robots length, might be faster to call sensenearbyrobots?)
        for (RobotInfo robot : allyRobots) {
            if (loc.isWithinDistanceSquared(robot.getLocation(), 2)) {
                adjacentAllies++;
            }
        }
        //find the closest enemy, and also see if we are safe from towers
//        for(RobotInfo robot : enemyRobots) {
//            int dist = loc.distanceSquaredTo(robot.getLocation());
//            if(dist < minDistanceToEnemy) {
//                minDistanceToEnemy = dist;
//            }
//            if(robot.type.isTowerType() && dist <= robot.type.actionRadiusSquared) {
//                inTowerRange = true;
//            }
//            if(robot.getPaintAmount() > 0) {
//                switch (robot.type) {
//                    case SPLASHER -> {
//                        if(robot.getPaintAmount() >= 50) {
//                            potentialPaintLoss += switch (paintType) {
//                                case ALLY_PAINT ->
//                                        (dist <= GameConstants.SPLASHER_ATTACK_ENEMY_PAINT_RADIUS_SQUARED) ? 1 : 0;
//                                case NEUTRAL_PAINT ->
//                                        (dist <= GameConstants.SPLASHER_ATTACK_AOE_RADIUS_SQUARED) ? 1 : 0;
//                                default -> 0;
//                            };
//                        }
//                    }
//                    case MOPPER -> {
//                        if (dist <= 2) potentialPaintLoss += 5;
//                        else if (dist < 8) potentialPaintLoss += Math.min(adjacentAllies, 5);
//                    }
//                    case SOLDIER -> {
//                        potentialPaintLoss += (paintType == NEUTRAL_PAINT && dist <= UnitType.SOLDIER.actionRadiusSquared) ? 1 : 0;
//                    }
//                }
//            }
//        }
//        for(int i = 0; i < enemyRobots.length; i++) {
//            int dist = loc.distanceSquaredTo(enemyRobots[i].getLocation());
//            if(dist < minDistanceToEnemy) {
//                minDistanceToEnemy = dist;
//            }
//            if(enemyRobots[i].type.isTowerType() && dist <= enemyRobots[i].type.actionRadiusSquared) {
//                inTowerRange = true;
//            }
//            if(enemyRobots[i].getPaintAmount() > 0) {
//                switch (enemyRobots[i].type) {
//                    case SPLASHER -> {
//                        if(enemyRobots[i].getPaintAmount() >= 50) {
//                            potentialPaintLoss += switch (paintType) {
//                                case ALLY_PAINT ->
//                                        (dist <= GameConstants.SPLASHER_ATTACK_ENEMY_PAINT_RADIUS_SQUARED) ? 1 : 0;
//                                case NEUTRAL_PAINT ->
//                                        (dist <= GameConstants.SPLASHER_ATTACK_AOE_RADIUS_SQUARED) ? 1 : 0;
//                                default -> 0;
//                            };
//                        }
//                    }
//                    case MOPPER -> {
//                        if (dist <= 2) potentialPaintLoss += 5;
//                        else if (dist < 8) potentialPaintLoss += Math.min(adjacentAllies, 5);
//                    }
//                    case SOLDIER -> {
//                        potentialPaintLoss += (paintType == NEUTRAL_PAINT && dist <= UnitType.SOLDIER.actionRadiusSquared) ? 1 : 0;
//                    }
//                }
//            }
//
//        }
//        for(int i = 0; i < enemyRobots.length; i++) {
//            processEnemyRobot(enemyRobots[i]);
//        }
        switch (enemyRobots.length) {
            case 0 -> {
            }
            case 1 -> {
                processEnemyRobot(enemyRobots[0]);
            }
            case 2 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
            }
            case 3 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
            }
            case 4 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
            }
            case 5 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
            }
            case 6 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
            }
            case 7 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
            }
            case 8 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
            }
            case 9 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
            }
            case 10 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
            }
            case 11 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
            }
            case 12 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
            }
            case 13 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
            }
            case 14 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
            }
            case 15 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
            }
            case 16 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
            }
            case 17 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
            }
            case 18 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
            }
            case 19 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
            }
            case 20 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
            }
            case 21 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
            }
            case 22 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
            }
            case 23 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
            }
            case 24 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
            }
            case 25 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
            }
            case 26 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
            }
            case 27 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
            }
            case 28 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
            }
            case 29 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
            }
            case 30 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
            }
            case 31 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
            }
            case 32 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
            }
            case 33 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
            }
            case 34 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
            }
            case 35 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
            }
            case 36 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
            }
            case 37 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
            }
            case 38 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
            }
            case 39 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
            }
            case 40 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
            }
            case 41 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
            }
            case 42 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
            }
            case 43 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
            }
            case 44 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
            }
            case 45 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
            }
            case 46 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
            }
            case 47 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
            }
            case 48 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
            }
            case 49 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
            }
            case 50 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
            }
            case 51 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
            }
            case 52 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
            }
            case 53 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
                processEnemyRobot(enemyRobots[52]);
            }
            case 54 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
                processEnemyRobot(enemyRobots[52]);
                processEnemyRobot(enemyRobots[53]);
            }
            case 55 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
                processEnemyRobot(enemyRobots[52]);
                processEnemyRobot(enemyRobots[53]);
                processEnemyRobot(enemyRobots[54]);
            }
            case 56 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
                processEnemyRobot(enemyRobots[52]);
                processEnemyRobot(enemyRobots[53]);
                processEnemyRobot(enemyRobots[54]);
                processEnemyRobot(enemyRobots[55]);
            }
            case 57 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
                processEnemyRobot(enemyRobots[52]);
                processEnemyRobot(enemyRobots[53]);
                processEnemyRobot(enemyRobots[54]);
                processEnemyRobot(enemyRobots[55]);
                processEnemyRobot(enemyRobots[56]);
            }
            case 58 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
                processEnemyRobot(enemyRobots[52]);
                processEnemyRobot(enemyRobots[53]);
                processEnemyRobot(enemyRobots[54]);
                processEnemyRobot(enemyRobots[55]);
                processEnemyRobot(enemyRobots[56]);
                processEnemyRobot(enemyRobots[57]);
            }
            case 59 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
                processEnemyRobot(enemyRobots[52]);
                processEnemyRobot(enemyRobots[53]);
                processEnemyRobot(enemyRobots[54]);
                processEnemyRobot(enemyRobots[55]);
                processEnemyRobot(enemyRobots[56]);
                processEnemyRobot(enemyRobots[57]);
                processEnemyRobot(enemyRobots[58]);
            }
            case 60 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
                processEnemyRobot(enemyRobots[52]);
                processEnemyRobot(enemyRobots[53]);
                processEnemyRobot(enemyRobots[54]);
                processEnemyRobot(enemyRobots[55]);
                processEnemyRobot(enemyRobots[56]);
                processEnemyRobot(enemyRobots[57]);
                processEnemyRobot(enemyRobots[58]);
                processEnemyRobot(enemyRobots[59]);
            }
            case 61 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
                processEnemyRobot(enemyRobots[52]);
                processEnemyRobot(enemyRobots[53]);
                processEnemyRobot(enemyRobots[54]);
                processEnemyRobot(enemyRobots[55]);
                processEnemyRobot(enemyRobots[56]);
                processEnemyRobot(enemyRobots[57]);
                processEnemyRobot(enemyRobots[58]);
                processEnemyRobot(enemyRobots[59]);
                processEnemyRobot(enemyRobots[60]);
            }
            case 62 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
                processEnemyRobot(enemyRobots[52]);
                processEnemyRobot(enemyRobots[53]);
                processEnemyRobot(enemyRobots[54]);
                processEnemyRobot(enemyRobots[55]);
                processEnemyRobot(enemyRobots[56]);
                processEnemyRobot(enemyRobots[57]);
                processEnemyRobot(enemyRobots[58]);
                processEnemyRobot(enemyRobots[59]);
                processEnemyRobot(enemyRobots[60]);
                processEnemyRobot(enemyRobots[61]);
            }
            case 63 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
                processEnemyRobot(enemyRobots[52]);
                processEnemyRobot(enemyRobots[53]);
                processEnemyRobot(enemyRobots[54]);
                processEnemyRobot(enemyRobots[55]);
                processEnemyRobot(enemyRobots[56]);
                processEnemyRobot(enemyRobots[57]);
                processEnemyRobot(enemyRobots[58]);
                processEnemyRobot(enemyRobots[59]);
                processEnemyRobot(enemyRobots[60]);
                processEnemyRobot(enemyRobots[61]);
                processEnemyRobot(enemyRobots[62]);
            }
            case 64 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
                processEnemyRobot(enemyRobots[52]);
                processEnemyRobot(enemyRobots[53]);
                processEnemyRobot(enemyRobots[54]);
                processEnemyRobot(enemyRobots[55]);
                processEnemyRobot(enemyRobots[56]);
                processEnemyRobot(enemyRobots[57]);
                processEnemyRobot(enemyRobots[58]);
                processEnemyRobot(enemyRobots[59]);
                processEnemyRobot(enemyRobots[60]);
                processEnemyRobot(enemyRobots[61]);
                processEnemyRobot(enemyRobots[62]);
                processEnemyRobot(enemyRobots[63]);
            }
            case 65 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
                processEnemyRobot(enemyRobots[52]);
                processEnemyRobot(enemyRobots[53]);
                processEnemyRobot(enemyRobots[54]);
                processEnemyRobot(enemyRobots[55]);
                processEnemyRobot(enemyRobots[56]);
                processEnemyRobot(enemyRobots[57]);
                processEnemyRobot(enemyRobots[58]);
                processEnemyRobot(enemyRobots[59]);
                processEnemyRobot(enemyRobots[60]);
                processEnemyRobot(enemyRobots[61]);
                processEnemyRobot(enemyRobots[62]);
                processEnemyRobot(enemyRobots[63]);
                processEnemyRobot(enemyRobots[64]);
            }
            case 66 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
                processEnemyRobot(enemyRobots[52]);
                processEnemyRobot(enemyRobots[53]);
                processEnemyRobot(enemyRobots[54]);
                processEnemyRobot(enemyRobots[55]);
                processEnemyRobot(enemyRobots[56]);
                processEnemyRobot(enemyRobots[57]);
                processEnemyRobot(enemyRobots[58]);
                processEnemyRobot(enemyRobots[59]);
                processEnemyRobot(enemyRobots[60]);
                processEnemyRobot(enemyRobots[61]);
                processEnemyRobot(enemyRobots[62]);
                processEnemyRobot(enemyRobots[63]);
                processEnemyRobot(enemyRobots[64]);
                processEnemyRobot(enemyRobots[65]);
            }
            case 67 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
                processEnemyRobot(enemyRobots[52]);
                processEnemyRobot(enemyRobots[53]);
                processEnemyRobot(enemyRobots[54]);
                processEnemyRobot(enemyRobots[55]);
                processEnemyRobot(enemyRobots[56]);
                processEnemyRobot(enemyRobots[57]);
                processEnemyRobot(enemyRobots[58]);
                processEnemyRobot(enemyRobots[59]);
                processEnemyRobot(enemyRobots[60]);
                processEnemyRobot(enemyRobots[61]);
                processEnemyRobot(enemyRobots[62]);
                processEnemyRobot(enemyRobots[63]);
                processEnemyRobot(enemyRobots[64]);
                processEnemyRobot(enemyRobots[65]);
                processEnemyRobot(enemyRobots[66]);
            }
            case 68 -> {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
                processEnemyRobot(enemyRobots[8]);
                processEnemyRobot(enemyRobots[9]);
                processEnemyRobot(enemyRobots[10]);
                processEnemyRobot(enemyRobots[11]);
                processEnemyRobot(enemyRobots[12]);
                processEnemyRobot(enemyRobots[13]);
                processEnemyRobot(enemyRobots[14]);
                processEnemyRobot(enemyRobots[15]);
                processEnemyRobot(enemyRobots[16]);
                processEnemyRobot(enemyRobots[17]);
                processEnemyRobot(enemyRobots[18]);
                processEnemyRobot(enemyRobots[19]);
                processEnemyRobot(enemyRobots[20]);
                processEnemyRobot(enemyRobots[21]);
                processEnemyRobot(enemyRobots[22]);
                processEnemyRobot(enemyRobots[23]);
                processEnemyRobot(enemyRobots[24]);
                processEnemyRobot(enemyRobots[25]);
                processEnemyRobot(enemyRobots[26]);
                processEnemyRobot(enemyRobots[27]);
                processEnemyRobot(enemyRobots[28]);
                processEnemyRobot(enemyRobots[29]);
                processEnemyRobot(enemyRobots[30]);
                processEnemyRobot(enemyRobots[31]);
                processEnemyRobot(enemyRobots[32]);
                processEnemyRobot(enemyRobots[33]);
                processEnemyRobot(enemyRobots[34]);
                processEnemyRobot(enemyRobots[35]);
                processEnemyRobot(enemyRobots[36]);
                processEnemyRobot(enemyRobots[37]);
                processEnemyRobot(enemyRobots[38]);
                processEnemyRobot(enemyRobots[39]);
                processEnemyRobot(enemyRobots[40]);
                processEnemyRobot(enemyRobots[41]);
                processEnemyRobot(enemyRobots[42]);
                processEnemyRobot(enemyRobots[43]);
                processEnemyRobot(enemyRobots[44]);
                processEnemyRobot(enemyRobots[45]);
                processEnemyRobot(enemyRobots[46]);
                processEnemyRobot(enemyRobots[47]);
                processEnemyRobot(enemyRobots[48]);
                processEnemyRobot(enemyRobots[49]);
                processEnemyRobot(enemyRobots[50]);
                processEnemyRobot(enemyRobots[51]);
                processEnemyRobot(enemyRobots[52]);
                processEnemyRobot(enemyRobots[53]);
                processEnemyRobot(enemyRobots[54]);
                processEnemyRobot(enemyRobots[55]);
                processEnemyRobot(enemyRobots[56]);
                processEnemyRobot(enemyRobots[57]);
                processEnemyRobot(enemyRobots[58]);
                processEnemyRobot(enemyRobots[59]);
                processEnemyRobot(enemyRobots[60]);
                processEnemyRobot(enemyRobots[61]);
                processEnemyRobot(enemyRobots[62]);
                processEnemyRobot(enemyRobots[63]);
                processEnemyRobot(enemyRobots[64]);
                processEnemyRobot(enemyRobots[65]);
                processEnemyRobot(enemyRobots[66]);
                processEnemyRobot(enemyRobots[67]);
            }
        }

        paintLoss = switch (paintType) {
            case ENEMY_PAINT -> 2 + adjacentAllies;
            case ALLY_PAINT -> adjacentAllies;
            case NEUTRAL_PAINT -> 1 + adjacentAllies;
            default -> 0;
        };
    }

    public void processEnemyRobot(RobotInfo robot) {
        int dist = loc.distanceSquaredTo(robot.getLocation());
        if (dist < minDistanceToEnemy) {
            minDistanceToEnemy = dist;
        }
        if (robot.type.isTowerType() && dist <= robot.type.actionRadiusSquared) {
            inTowerRange = true;
        }
        if (robot.getPaintAmount() > 0) {
            switch (robot.type) {
                case SPLASHER -> {
                    if (robot.getPaintAmount() >= 50) {
                        potentialPaintLoss += switch (paintType) {
                            case ALLY_PAINT -> (dist <= 9) ? 1 : 0;
                            case NEUTRAL_PAINT -> (dist <= 16) ? 1 : 0;
                            default -> 0;
                        };
                    }
                }
                case MOPPER -> {
                    if (dist <= 2) potentialPaintLoss += Math.min(5, adjacentAllies);
                    if (dist < 8) potentialPaintLoss += Math.min(adjacentAllies, 5);
                }
                case SOLDIER -> {
                    potentialPaintLoss += (paintType == NEUTRAL_PAINT && dist <= UnitType.SOLDIER.actionRadiusSquared) ? 1 : 0;
                }
            }
        }
    }

}

public class SplasherMicro {
    private static splasherMicroInfo[] microArray;

    private static final int ALLY_PAINT = 1;
    private static final int ENEMY_PAINT = -1;
    private static final int NEUTRAL_PAINT = 0;

    private static int health;

    //splasher micro - will find the best place to splash, and move, both within this method
    //priorities list:
    //1. Attack square which will have high impact - 0b1000
    //2. Attack square with enemy tower - 0b100
    //3. Attack square with self -0b10
    //4. Attack square without moving -0b1
//    public static void integratedSplasherMicro(boolean fightingTower) throws GameActionException {
//        health = rc.getHealth();
//        if (rc.isActionReady()) {
//            int minScore = 3;
//            //MapLocation bestAttack = splasherUtil.bestAttack(fightingTower, minScore);
//            MapLocation bestAttack = splasherUtil.bestAttack(fightingTower, Math.min(minScore, Splasher.numEnemyTiles));
//            if (bestAttack != null) {
//                if (rc.canAttack(bestAttack)) {
//                    rc.attack(bestAttack);
//                    rc.setIndicatorString("safeSplasherMicro" + " : " + averageEnemyPaint);
//                    runSafeSplasherMicro();
//                } else {
//                    rc.setIndicatorString("targeted micro: + "  + bestAttack +  " : " + averageEnemyPaint + " : " + MopperMicro.customLocationTo(rc.getLocation(), bestAttack));
//                    runTargetedSplasherMicro(MopperMicro.customLocationTo(rc.getLocation(), bestAttack), bestAttack);
//                    if(rc.canAttack(bestAttack)) rc.attack(bestAttack);
//                    else if(Clock.getBytecodesLeft() > 4000) {
//                        bestAttack = splasherUtil.cheapBestAttack(fightingTower, minScore);
//                        if(bestAttack != null && rc.canAttack(bestAttack)){
//                            rc.attack(bestAttack);
//                        }
//                    }
//                }
//            } else {
//                rc.setIndicatorString("safeSplasherMicro" + " : " + averageEnemyPaint);
//                runSafeSplasherMicro();
//                if(Clock.getBytecodesLeft() > 4000) {
//                    bestAttack = splasherUtil.cheapBestAttack(fightingTower, minScore);
//                    if(bestAttack != null && rc.canAttack(bestAttack)) rc.attack(bestAttack);
//                }
//            }
//        } else {
//            rc.setIndicatorString("safeSplasherMicro" + " : " + averageEnemyPaint);
//            runSafeSplasherMicro();
//        }
//    }
    //same as above, but takes in a best attack
    public static void integratedSplasherMicro(boolean fightingTower) throws GameActionException {
        int minScore = 1;
        if (rc.isActionReady() && rc.isMovementReady()) {
            MapLocation bestAttack = splasherUtil.bestAttack(fightingTower, minScore);
            //we have a best place to attack
            if (bestAttack != null) {
                //System.out.println(bestAttack);
                //attack it
                if (rc.canAttack(bestAttack)) {
                    rc.attack(bestAttack);
                    runSafeSplasherMicro();
                }
                //we need to move, then attack
                else {
                    runTargetedSplasherMicro(MopperMicro.customLocationTo(rc.getLocation(), bestAttack), bestAttack);
                    if (rc.canAttack(bestAttack)) {
                        rc.attack(bestAttack);
                    }
                    //just see if we can get anything
                    else if (Clock.getBytecodesLeft() > 4000) {
                        bestAttack = splasherUtil.cheapBestAttack(fightingTower, 1);
                        if (rc.canAttack(bestAttack)) {
                            rc.attack(bestAttack);
                        }
                    }
                }
            }
            //nowhere to attack - try and move towards average enemy paint?
            else {
                if(averageEnemyPaint != null) runTargetedSplasherMicro(MopperMicro.customLocationTo(rc.getLocation(), averageEnemyPaint), averageEnemyPaint);
                else if(seenEnemyTower != null) runTargetedSplasherMicro(MopperMicro.customLocationTo(rc.getLocation(), seenEnemyTower.getLocation()), seenEnemyTower.getLocation());
                if (Clock.getBytecodesLeft() > 4000) {
                    bestAttack = splasherUtil.cheapBestAttack(fightingTower, 1);
                    if (rc.canAttack(bestAttack)) {
                        rc.attack(bestAttack);
                    }
                }
            }
        } else if (rc.isActionReady()) {
            MapLocation bestAttack = splasherUtil.cheapBestAttack(fightingTower, 1);
            if (bestAttack != null && rc.canAttack(bestAttack)) {
                rc.attack(bestAttack);
            }
        } else if (rc.isMovementReady()) {
            runSafeSplasherMicro();
        }

    }

    //runs the splasher micro trying to get within range of a target attack location
    public static void runTargetedSplasherMicro(Direction direction, MapLocation target) throws GameActionException {
        health = rc.getHealth();
        microArray = new splasherMicroInfo[3];
        switch (direction) {
            case NORTH -> {
                {
                    MapLocation newLoc = new MapLocation(rc.getLocation().x + -1, rc.getLocation().y + 1);
                    microArray[0] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(rc.getLocation().x, rc.getLocation().y + 1);
                    microArray[1] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(rc.getLocation().x + 1, rc.getLocation().y + 1);
                    microArray[2] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                }
            }
            case NORTHEAST -> {
                MapLocation newLoc = new MapLocation(rc.getLocation().x + 1, rc.getLocation().y + 1);
                microArray[0] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                newLoc = new MapLocation(rc.getLocation().x + 1, rc.getLocation().y);
                microArray[1] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                newLoc = new MapLocation(rc.getLocation().x, rc.getLocation().y + 1);
                microArray[2] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
            }
            case EAST -> {
                {
                    MapLocation newLoc = new MapLocation(rc.getLocation().x + 1, rc.getLocation().y + -1);
                    microArray[0] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(rc.getLocation().x + 1, rc.getLocation().y);
                    microArray[1] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(rc.getLocation().x + 1, rc.getLocation().y + 1);
                    microArray[2] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                }
            }
            case SOUTHEAST -> {
                MapLocation newLoc = new MapLocation(rc.getLocation().x, rc.getLocation().y - 1);
                microArray[0] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                newLoc = new MapLocation(rc.getLocation().x + 1, rc.getLocation().y - 1);
                microArray[1] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                newLoc = new MapLocation(rc.getLocation().x + 1, rc.getLocation().y);
                microArray[2] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
            }
            case SOUTH -> {
                {
                    MapLocation newLoc = new MapLocation(rc.getLocation().x + -1, rc.getLocation().y - 1);
                    microArray[0] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(rc.getLocation().x, rc.getLocation().y - 1);
                    microArray[1] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(rc.getLocation().x + 1, rc.getLocation().y - 1);
                    microArray[2] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                }
            }
            case SOUTHWEST -> {
                MapLocation newLoc = new MapLocation(rc.getLocation().x - 1, rc.getLocation().y - 1);
                microArray[0] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                newLoc = new MapLocation(rc.getLocation().x, rc.getLocation().y - 1);
                microArray[1] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                newLoc = new MapLocation(rc.getLocation().x - 1, rc.getLocation().y);
                microArray[2] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
            }
            case WEST -> {
                {
                    MapLocation newLoc = new MapLocation(rc.getLocation().x - 1, rc.getLocation().y + -1);
                    microArray[0] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(rc.getLocation().x - 1, rc.getLocation().y);
                    microArray[1] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(rc.getLocation().x - 1, rc.getLocation().y + 1);
                    microArray[2] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                }
            }
            case NORTHWEST -> {
                MapLocation newLoc = new MapLocation(rc.getLocation().x - 1, rc.getLocation().y + 1);
                microArray[0] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                newLoc = new MapLocation(rc.getLocation().x - 1, rc.getLocation().y);
                microArray[1] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
                newLoc = new MapLocation(rc.getLocation().x, rc.getLocation().y + 1);
                microArray[2] = (rc.canSenseRobotAtLocation(newLoc) || !rc.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(rc.senseMapInfo(newLoc));
            }
            case CENTER -> {
                microArray = null;
                return;
            }
        }
        ;
        //determining the best space
        //splasherMicroInfo bestMicro = microArray[0];
        int actionRadius = UnitType.SPLASHER.actionRadiusSquared;
        splasherMicroInfo bestMicro = new splasherMicroInfo(rc.senseMapInfo(rc.getLocation()));
        for (int i = 0; i < 3; i++) {
            splasherMicroInfo m = microArray[i];
            if (!m.passable) continue;
            if (!bestMicro.passable) {
                bestMicro = microArray[i];
                continue;
            }

//            if (health <= 100 || !rc.isActionReady()) {
//                //if one is in tower range and the other isnt, get out of tower range
//                if (!bestMicro.inTowerRange && m.inTowerRange) continue;
//                if (bestMicro.inTowerRange && !m.inTowerRange) {
//                    bestMicro = m;
//                    continue;
//                }
//            }


            int dist = bestMicro.loc.distanceSquaredTo(target);
            int altDist = m.loc.distanceSquaredTo(target);
            if (dist <= actionRadius && altDist > actionRadius) continue;
            if (dist > actionRadius && altDist <= actionRadius) {
                bestMicro = m;
                continue;
            }

            if (bestMicro.paintLoss < m.paintLoss) continue;
            if (m.paintLoss < bestMicro.paintLoss) {
                bestMicro = m;
                continue;
            }

            if (bestMicro.potentialPaintLoss < microArray[i].potentialPaintLoss) continue;
            if (microArray[i].potentialPaintLoss < bestMicro.potentialPaintLoss) {
                bestMicro = microArray[i];
                continue;
            }

            //if one space is on allied paint and the other isnt, go to allied paint
            if (bestMicro.paintType == ALLY_PAINT && m.paintType != ALLY_PAINT) continue;
            if (bestMicro.paintType != ALLY_PAINT && m.paintType == ALLY_PAINT) {
                bestMicro = m;
                continue;
            }

            //if one space avoids enemy paint and the other doesnt, go to the one avoiding enemy paint
            if (bestMicro.paintType != ENEMY_PAINT && m.paintType == ENEMY_PAINT) continue;
            if (bestMicro.paintType == ENEMY_PAINT && m.paintType != ENEMY_PAINT) {
                bestMicro = m;
                continue;
            }

            if (bestMicro.distanceToEnemyAverage < m.distanceToEnemyAverage) continue;
            if (bestMicro.distanceToEnemyAverage > m.distanceToEnemyAverage) {
                bestMicro = m;
                continue;
            }

            //finally, lets avoid being adjacent to allies
            if (bestMicro.adjacentAllies < m.adjacentAllies) continue;
            if (bestMicro.adjacentAllies > m.adjacentAllies) {
                bestMicro = m;
                continue;
            }
        }
        if (bestMicro.passable && rc.canMove(rc.getLocation().directionTo(bestMicro.loc))) {
            rc.move(rc.getLocation().directionTo(bestMicro.loc));
        }
    }

    //attempts to stay safe while running a micro that optimizes for splashers
    private static void runSafeSplasherMicro() throws GameActionException {
        health = rc.getHealth();
        if (!rc.isMovementReady()) return;
        microArray = new splasherMicroInfo[9];
        populateSplasherMicroArray();
        splasherMicroInfo bestMicro = microArray[0];
        for (int i = 1; i < microArray.length; i++) {
            splasherMicroInfo m = microArray[i];
            if (!m.passable) continue;
            if (!bestMicro.passable) {
                bestMicro = m;
                continue;
            }

            if(!rc.isActionReady() || health <= 100) {
                //since we want to be safe, lets try and stay somewhat away from enemies - otherwise, the usual criteria
                //if one is in tower range and the other isnt, get out of tower range
                if (!bestMicro.inTowerRange && m.inTowerRange) continue;
                if (bestMicro.inTowerRange && !m.inTowerRange) {
                    bestMicro = m;
                    continue;
                }
            }

            //if one space is on allied paint and the other isnt, go to allied paint
            if (bestMicro.paintType == ALLY_PAINT && m.paintType != ALLY_PAINT) continue;
            if (bestMicro.paintType != ALLY_PAINT && m.paintType == ALLY_PAINT) {
                bestMicro = m;
                continue;
            }

            //look at which place will lose us the least paint at the end of this turn
            if (bestMicro.paintLoss < microArray[i].paintLoss) continue;
            if (microArray[i].paintLoss < bestMicro.paintLoss) {
                bestMicro = microArray[i];
                continue;
            }

            if (bestMicro.potentialPaintLoss < microArray[i].potentialPaintLoss) continue;
            if (microArray[i].potentialPaintLoss < bestMicro.potentialPaintLoss) {
                bestMicro = microArray[i];
                continue;
            }

            if (bestMicro.distanceToEnemyAverage < m.distanceToEnemyAverage) continue;
            if (bestMicro.distanceToEnemyAverage > m.distanceToEnemyAverage) {
                bestMicro = m;
                continue;
            }

            //if one space avoids enemy paint and the other doesnt, go to the one avoiding enemy paint
            if (bestMicro.paintType != ENEMY_PAINT && m.paintType == ENEMY_PAINT) continue;
            if (bestMicro.paintType == ENEMY_PAINT && m.paintType != ENEMY_PAINT) {
                bestMicro = m;
                continue;
            }

            //lets avoid being adjacent to allies
            if (bestMicro.adjacentAllies < m.adjacentAllies) continue;
            if (bestMicro.adjacentAllies > m.adjacentAllies) {
                bestMicro = m;
                continue;
            }


            //finally, lets try not to be directly next to an enemy
            if (bestMicro.minDistanceToEnemy > 2 && m.minDistanceToEnemy <= 2) continue;
            if (bestMicro.minDistanceToEnemy <= 2 && m.minDistanceToEnemy > 2) {
                bestMicro = m;
                continue;
            }

            if (rng.nextInt(2) == 0) {
                bestMicro = m;
            }
        }
        if (bestMicro.loc != null && rc.canMove(rc.getLocation().directionTo(bestMicro.loc)))
            rc.move(rc.getLocation().directionTo(bestMicro.loc));
    }

    public static void populateSplasherMicroArray() throws GameActionException {
        microArray = new splasherMicroInfo[9];
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
//                        if(rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
//                        else microArray[totalFilled] = new splasherMicroInfo(rc.senseMapInfo(newLoc));
//                        totalFilled++;
//                    }
//                }
//            }
//        }
        if (curX + -1 >= 0 && curX + -1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + -1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                else microArray[totalFilled] = new splasherMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY >= 0 && curY <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                else microArray[totalFilled] = new splasherMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                else microArray[totalFilled] = new splasherMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
        }
        if (curX >= 0 && curX <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX, curY + -1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                else microArray[totalFilled] = new splasherMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY >= 0 && curY <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX, curY);
                //if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                microArray[totalFilled] = new splasherMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX, curY + 1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                else microArray[totalFilled] = new splasherMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
        }
        if (curX + 1 >= 0 && curX + 1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + -1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                else microArray[totalFilled] = new splasherMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY >= 0 && curY <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                else microArray[totalFilled] = new splasherMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                else microArray[totalFilled] = new splasherMicroInfo(rc.senseMapInfo(newLoc));
                totalFilled++;
            }
        }
        //populate the rest of the array in case we are on the edge of the map and couldn't fill it all
        for (int i = totalFilled; i < 9; i++) {
            microArray[i] = new splasherMicroInfo();
        }
    }
}
