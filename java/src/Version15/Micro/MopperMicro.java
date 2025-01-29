package Version15.Micro;

import Version15.Utility.Utilities;
import battlecode.common.*;

import static Version15.Robots.Mopper.*;
import static Version15.RobotPlayer.*;


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
    int potentialPaintLoss;

    public mopperMicroInfo(MapInfo tile) {
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
        populateMopperMicroInfo();
    }

    public mopperMicroInfo() {
        passable = false;
    }

    //populates the info you can't get from only knowing the tile
    void populateMopperMicroInfo() {
        distanceToEnemyAverage = loc.distanceSquaredTo(averageEnemyPaint);
        potentialPaintLoss = 0;
//        MapLocation[] enemyPaintAverages = Utilities.getEnemyPaintAverages();
//        distanceToEnemyAverage = switch(enemyPaintAverages.length) {
//            case 0 -> Integer.MAX_VALUE;
//            case 1 -> loc.distanceSquaredTo(enemyPaintAverages[0]);
//            case 2 -> Math.min(loc.distanceSquaredTo(enemyPaintAverages[0]), loc.distanceSquaredTo(enemyPaintAverages[1]));
//            default -> Integer.MAX_VALUE;
//        };
        //count adjacent allies (depending on ally robots length, might be faster to call sensenearbyrobots?)
        for (RobotInfo robot : allyRobots) {
            if (loc.isWithinDistanceSquared(robot.getLocation(), 2)) {
                adjacentAllies++;
            }
        }
//        //find the closest enemy, and also see if we are safe from towers
//        for (RobotInfo robot : enemyRobots) {
//            int dist = loc.distanceSquaredTo(robot.getLocation());
//            if (dist < minDistanceToEnemy) {
//                minDistanceToEnemy = dist;
//            }
//            if (robot.type.isTowerType() && dist <= robot.type.actionRadiusSquared) {
//                inTowerRange = true;
//                if (minDistanceToEnemy == 1) break;
//            }
//            if (robot.getPaintAmount() > 0) {
//                switch (robot.type) {
//                    case SPLASHER -> {
//                        if (robot.getPaintAmount() >= 50) {
//                            //potentialPaintLoss += (paintType != ENEMY_PAINT && dist <= GameConstants.SPLASHER_ATTACK_AOE_RADIUS_SQUARED) ? 2 : 0;
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
        switch(enemyRobots.length)
        {
            case 0->
            {
            }
            case 1->
            {
                processEnemyRobot(enemyRobots[0]);
            }
            case 2->
            {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
            }
            case 3->
            {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
            }
            case 4->
            {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
            }
            case 5->
            {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
            }
            case 6->
            {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
            }
            case 7->
            {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
            }
            case 8->
            {
                processEnemyRobot(enemyRobots[0]);
                processEnemyRobot(enemyRobots[1]);
                processEnemyRobot(enemyRobots[2]);
                processEnemyRobot(enemyRobots[3]);
                processEnemyRobot(enemyRobots[4]);
                processEnemyRobot(enemyRobots[5]);
                processEnemyRobot(enemyRobots[6]);
                processEnemyRobot(enemyRobots[7]);
            }
            case 9->
            {
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
            case 10->
            {
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
            case 11->
            {
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
            case 12->
            {
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
            case 13->
            {
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
            case 14->
            {
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
            case 15->
            {
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
            case 16->
            {
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
            case 17->
            {
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
            case 18->
            {
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
            case 19->
            {
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
            case 20->
            {
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
            case 21->
            {
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
            case 22->
            {
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
            case 23->
            {
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
            case 24->
            {
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
            case 25->
            {
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
            case 26->
            {
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
            case 27->
            {
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
            case 28->
            {
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
            case 29->
            {
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
            case 30->
            {
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
            case 31->
            {
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
            case 32->
            {
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
            case 33->
            {
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
            case 34->
            {
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
            case 35->
            {
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
            case 36->
            {
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
            case 37->
            {
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
            case 38->
            {
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
            case 39->
            {
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
            case 40->
            {
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
            case 41->
            {
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
            case 42->
            {
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
            case 43->
            {
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
            case 44->
            {
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
            case 45->
            {
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
            case 46->
            {
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
            case 47->
            {
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
            case 48->
            {
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
            case 49->
            {
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
            case 50->
            {
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
            case 51->
            {
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
            case 52->
            {
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
            case 53->
            {
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
            case 54->
            {
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
            case 55->
            {
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
            case 56->
            {
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
            case 57->
            {
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
            case 58->
            {
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
            case 59->
            {
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
            case 60->
            {
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
            case 61->
            {
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
            case 62->
            {
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
            case 63->
            {
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
            case 64->
            {
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
            case 65->
            {
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
            case 66->
            {
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
            case 67->
            {
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
            case 68->
            {
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
            case ENEMY_PAINT -> 4 + adjacentAllies;
            case ALLY_PAINT -> adjacentAllies;
            case NEUTRAL_PAINT -> 2 + adjacentAllies;
            default -> 0;
        };
    }

    public void processEnemyRobot(RobotInfo robot) {
        int dist = loc.distanceSquaredTo(robot.getLocation());
        if(dist < minDistanceToEnemy) {
            minDistanceToEnemy = dist;
        }
        if(robot.type.isTowerType() && dist <= robot.type.actionRadiusSquared) {
            inTowerRange = true;
        }
        if(robot.getPaintAmount() > 0) {
            switch (robot.type) {
                case SPLASHER -> {
                    if(robot.getPaintAmount() >= 50) {
                        potentialPaintLoss += switch (paintType) {
                            case ALLY_PAINT ->
                                    (dist <= 9) ? 1 : 0;
                            case NEUTRAL_PAINT ->
                                    (dist <= 16) ? 1 : 0;
                            default -> 0;
                        };
                    }
                }
                case MOPPER -> {
                    if (dist <= 2) potentialPaintLoss += (5-Math.min(5, adjacentAllies));
                    if (dist < 8) potentialPaintLoss += Math.min(adjacentAllies, 5);
                }
                case SOLDIER -> {
                    potentialPaintLoss += (paintType == NEUTRAL_PAINT && dist <= UnitType.SOLDIER.actionRadiusSquared) ? 1 : 0;
                }
            }
        }
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
    public static void integratedMopperMicro() throws GameActionException {
        //MapInfo[] tiles = staticRC.senseNearbyMapInfos(8);
        if (staticRC.isActionReady()) {
            //int price = Clock.getBytecodesLeft();
            Direction dirToSweep = dirToSweep((staticRC.getPaint() > 70) ? 2 : 3);
            //System.out.println(price - Clock.getBytecodesLeft());
            if (dirToSweep != null && staticRC.canMopSwing(dirToSweep)) {
                staticRC.mopSwing(dirToSweep);
                safeMopperMicro();
                return;
            }
            int x = staticRC.getLocation().x;
            int y = staticRC.getLocation().y;
            int bestX = -1;
            int bestY = -1;
            int bestScore = -1;
            int basDist = 0;
            int bestDist = Integer.MAX_VALUE;
            //int[][] scores = new int[5][5];
//            for (int dx = -2; dx <= 2; dx++) {
//                for (int dy = -2; dy <= 2; dy++) {
//                    basDist = Math.abs(dx) + Math.abs(dy);
//                    int score = determineScore(x + dx, y + dy);
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
                int score = determineScore(x + -2, y + -2);
                if (score >= bestScore) {
                    bestX = x + -2;
                    bestY = y + -2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 3;
                int score = determineScore(x + -2, y + -1);
                if (score >= bestScore) {
                    bestX = x + -2;
                    bestY = y + -1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 2;
                int score = determineScore(x + -2, y );
                if (score >= bestScore) {
                    bestX = x + -2;
                    bestY = y;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 3;
                int score = determineScore(x + -2, y + 1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + -2;
                    bestY = y + 1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 4;
                int score = determineScore(x + -2, y + 2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + -2;
                    bestY = y + 2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 3;
                int score = determineScore(x + -1, y + -2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + -1;
                    bestY = y + -2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 2;
                int score = determineScore(x + -1, y + -1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + -1;
                    bestY = y + -1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 1;
                int score = determineScore(x + -1, y);
                if (score >= bestScore) {
                    bestX = x + -1;
                    bestY = y;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 2;
                int score = determineScore(x + -1, y + 1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + -1;
                    bestY = y + 1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 3;
                int score = determineScore(x + -1, y + 2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + -1;
                    bestY = y + 2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 2;
                int score = determineScore(x, y + -2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x;
                    bestY = y + -2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 1;
                int score = determineScore(x, y + -1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x;
                    bestY = y + -1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 0;
                int score = determineScore(x, y);
                if (score >= bestScore) {
                    bestX = x;
                    bestY = y;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 1;
                int score = determineScore(x, y + 1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x;
                    bestY = y + 1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 2;
                int score = determineScore(x, y + 2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x;
                    bestY = y + 2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 3;
                int score = determineScore(x + 1, y + -2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 1;
                    bestY = y + -2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 2;
                int score = determineScore(x + 1, y + -1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 1;
                    bestY = y + -1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 1;
                int score = determineScore(x + 1, y);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 1;
                    bestY = y;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 2;
                int score = determineScore(x + 1, y + 1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 1;
                    bestY = y + 1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 3;
                int score = determineScore(x + 1, y + 2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 1;
                    bestY = y + 2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 4;
                int score = determineScore(x + 2, y + -2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 2;
                    bestY = y + -2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 3;
                int score = determineScore(x + 2, y + -1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 2;
                    bestY = y + -1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 2;
                int score = determineScore(x + 2, y);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 2;
                    bestY = y;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 3;
                int score = determineScore(x + 2, y + 1);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 2;
                    bestY = y + 1;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            {
                basDist = 4;
                int score = determineScore(x + 2, y + 2);
                if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                    bestX = x + 2;
                    bestY = y + 2;
                    bestScore = score;
                    bestDist = basDist;
                }
            }
            //nowhere to attack
            if (bestScore == -1) {
                staticRC.setIndicatorString("aggroMopperMicro");
                aggroMopperMicro();
                attackAnything();
                return;
            } else {
                MapLocation target = new MapLocation(bestX, bestY);
                if (staticRC.canAttack(target)) {
                    staticRC.attack(target);
                    staticRC.setIndicatorString("safeMopperMicro");
                    safeMopperMicro();
                    return;
                } else {
                    staticRC.setIndicatorString("targetedMopperMicro: " + target.toString() + " : " + customLocationTo(staticRC.getLocation(), target));
                    targetedMopperMicro(customLocationTo(staticRC.getLocation(), target), target);
                    if (staticRC.canAttack(target)) {
                        staticRC.attack(target);
                    } else {
                        attackAnything();
                    }
                }
            }
        } else {
            staticRC.setIndicatorString("safeMopperMicro");
            safeMopperMicro();
        }
        if (staticRC.isActionReady()) {
            int minScore = (staticRC.getPaint() > 70) ? 2 : 3;
            Direction dir = dirToSweep(minScore);
            if (dir != null && staticRC.canMopSwing(dir)) {
                staticRC.mopSwing(dir);
            }
        }
    }

    //if we are at the end of a turn and haven't been able to attack what we wanted, just attack the first thing we can
    public static void attackAnything() throws GameActionException {
        if(Clock.getBytecodesLeft() > 600) {
            MapLocation bestLoc = determineBestStationaryAttack();
            if(staticRC.canAttack(bestLoc)) {
                staticRC.attack(bestLoc);
            }
        }
        else {
            if (staticRC.senseMapInfo(staticRC.getLocation()).getPaint().isEnemy()) {
                if (staticRC.canAttack(staticRC.getLocation())) {
                    staticRC.attack(staticRC.getLocation());
                }
            }
            for (MapInfo tile : nearbyTiles) {
                if (staticRC.canAttack(tile.getMapLocation()) && (tile.getPaint().isEnemy() || staticRC.canSenseRobotAtLocation(tile.getMapLocation()) && staticRC.senseRobotAtLocation(tile.getMapLocation()).getTeam() == staticRC.getTeam().opponent())) {
                    staticRC.attack(tile.getMapLocation());
                    return;
                }
            }
        }
    }

    public static MapLocation determineBestStationaryAttack() throws GameActionException {
        int x = staticRC.getLocation().x;
        int y = staticRC.getLocation().y;
        int bestX = -1;
        int bestY = -1;
        int bestScore = -1;
        int basDist = 0;
        int bestDist = Integer.MAX_VALUE;
        {
            basDist = 2;
            int score = determineScore(x + -1, y + -1);
            if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                bestX = x + -1;
                bestY = y + -1;
                bestScore = score;
                bestDist = basDist;
            }
        }
        {
            basDist = 1;
            int score = determineScore(x + -1, y );
            if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                bestX = x + -1;
                bestY = y ;
                bestScore = score;
                bestDist = basDist;
            }
        }
        {
            basDist = 2;
            int score = determineScore(x + -1, y + 1);
            if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                bestX = x + -1;
                bestY = y + 1;
                bestScore = score;
                bestDist = basDist;
            }
        }
        {
            basDist = 1;
            int score = determineScore(x , y + -1);
            if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                bestX = x ;
                bestY = y + -1;
                bestScore = score;
                bestDist = basDist;
            }
        }
        {
            basDist = 0;
            int score = determineScore(x , y );
            if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                bestX = x ;
                bestY = y ;
                bestScore = score;
                bestDist = basDist;
            }
        }
        {
            basDist = 1;
            int score = determineScore(x , y + 1);
            if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                bestX = x ;
                bestY = y + 1;
                bestScore = score;
                bestDist = basDist;
            }
        }
        {
            basDist = 2;
            int score = determineScore(x + 1, y + -1);
            if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                bestX = x + 1;
                bestY = y + -1;
                bestScore = score;
                bestDist = basDist;
            }
        }
        {
            basDist = 1;
            int score = determineScore(x + 1, y );
            if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                bestX = x + 1;
                bestY = y ;
                bestScore = score;
                bestDist = basDist;
            }
        }
        {
            basDist = 2;
            int score = determineScore(x + 1, y + 1);
            if (score > bestScore || (score == bestScore && basDist < bestDist)) {
                bestX = x + 1;
                bestY = y + 1;
                bestScore = score;
                bestDist = basDist;
            }
        }
        return new MapLocation(bestX, bestY);
    }

    //determines the score of a space mopping wise
    public static int determineScore(int x, int y) throws GameActionException {
        int score = 0;
        MapLocation temp = new MapLocation(x, y);
        if (staticRC.onTheMap(temp)) {
            MapInfo m = staticRC.senseMapInfo(temp);
            if (!m.getPaint().isEnemy() || !m.isPassable()) return -2;
            RobotInfo r = staticRC.senseRobotAtLocation(temp);
            //roughly determines whether we would have to move into tower range to attack this square
            if (seenEnemyTower != null) {
                boolean withinFunctionalTowerRange = switch (seenEnemyTower.getType()) {
                    case LEVEL_ONE_DEFENSE_TOWER, LEVEL_TWO_DEFENSE_TOWER, LEVEL_THREE_DEFENSE_TOWER ->
                            temp.isWithinDistanceSquared(seenEnemyTower.getLocation(), 8);
                    default -> temp.isWithinDistanceSquared(seenEnemyTower.getLocation(), 3);
                };
                if (withinFunctionalTowerRange) return -2;
            }
            if (Utilities.basicLocationIsBehindWall(m.getMapLocation())) {
                return -2;
            }
            if (r != null) {
                if (staticRC.getTeam() == r.getTeam()) {
                    score |= 0b01;
                } else if (r.getPaintAmount() > 0) {
                    score |= 0b100;
                }
            }
            if (temp.equals(staticRC.getLocation())) {
                score |= 0b1000;
            }
            if (nearbyRuin != null && temp.isWithinDistanceSquared(nearbyRuin, 8)) {
                score |= 0b10000;
            }
            if (temp.isWithinDistanceSquared(staticRC.getLocation(), UnitType.MOPPER.actionRadiusSquared)) {
                score |= 0b10;
            }
        } else {
            return -2;
        }
        return score;
    }

    //scores a maplocation for the clear() method specifically
    public static int determineScoreClear(MapLocation temp, MapInfo m) throws GameActionException {
        int score = 0;
        if (!m.getPaint().isEnemy() || !m.isPassable()) return -2;
        RobotInfo r = staticRC.senseRobotAtLocation(temp);
        //roughly determines whether we would have to move into tower range to attack this square
        if (seenEnemyTower != null) {
            boolean withinFunctionalTowerRange = switch (seenEnemyTower.getType()) {
                case LEVEL_ONE_DEFENSE_TOWER, LEVEL_TWO_DEFENSE_TOWER, LEVEL_THREE_DEFENSE_TOWER ->
                        temp.isWithinDistanceSquared(seenEnemyTower.getLocation(), 8);
                default -> temp.isWithinDistanceSquared(seenEnemyTower.getLocation(), 3);
            };
            if (withinFunctionalTowerRange) return -2;
        }
        if (r != null) {
            if (staticRC.getTeam() == r.getTeam()) {
                score |= 0b01;
            } else if (r.getPaintAmount() > 0) {
                score |= 0b100;
            }
        }
        if (temp.equals(staticRC.getLocation())) {
            score |= 0b1000;
        }
        else if (temp.isWithinDistanceSquared(staticRC.getLocation(), UnitType.MOPPER.actionRadiusSquared)) {
            score |= 0b10;
        }
        return score;
    }

    //returns the best direction to sweep, if there is any sweep direction which will have a score greater than
    //or equal to minScore. If there is no such direction, return null
    public static Direction dirToSweep(int minScore) throws GameActionException {
        RobotInfo[] adjacentEnemyRobots = staticRC.senseNearbyRobots(7, staticRC.getTeam().opponent());
        Direction dirToSweep = null;
        if (adjacentEnemyRobots.length >= minScore) {
            //determine the amount of enemies we would hit with each cardinal sweep - 0: north, 1: east, etc...
            int[] directionalSweep = {0, 0, 0, 0};
            for (RobotInfo enemy : adjacentEnemyRobots) {
                if (enemy.getPaintAmount() == 0) continue;
                //Direction dir = staticRC.getLocation().directionTo(enemy.getLocation());
                Direction dir = customLocationTo(staticRC.getLocation(), enemy.getLocation());
                switch (dir) {
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
            for (int i = 0; i < 4; i++) {
                if (directionalSweep[i] >= minScore && directionalSweep[i] > highest) {
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
    public static void aggroMopperMicro() throws GameActionException {
        if (!staticRC.isMovementReady()) return;
        staticRC.setIndicatorString("aggroMopperMicro");
        microArray = new mopperMicroInfo[9];
        populateMopperMicroArray();
        mopperMicroInfo bestMicro = microArray[0];
        for (int i = 1; i < 9; i++) {
            mopperMicroInfo m = microArray[i];
            if (!m.passable) continue;
            if (!bestMicro.passable) {
                bestMicro = m;
                continue;
            }

            //even tho we want to be aggressive, we still want to stay away from towers and stay on friendly paint
            //if one is in tower range and the other isnt, get out of tower range
            if (!bestMicro.inTowerRange && m.inTowerRange) continue;
            if (bestMicro.inTowerRange && !m.inTowerRange) {
                bestMicro = m;
                continue;
            }

            if (bestMicro.distanceToEnemyAverage < m.distanceToEnemyAverage) continue;
            if (bestMicro.distanceToEnemyAverage > m.distanceToEnemyAverage) {
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


            //if one space is on allied paint and the other isnt, go to allied paint
            if (bestMicro.paintType == ALLY_PAINT && m.paintType != ALLY_PAINT) continue;
            if (bestMicro.paintType != ALLY_PAINT && m.paintType == ALLY_PAINT) {
                bestMicro = m;
                continue;
            }

            //however, now lets minimize our distance to an enemy
            //if one space avoids enemy paint and the other doesnt, go to the one avoiding enemy paint
            if (bestMicro.minDistanceToEnemy < m.minDistanceToEnemy) continue;
            if (bestMicro.minDistanceToEnemy > m.minDistanceToEnemy) {
                bestMicro = m;
                continue;
            }

            //finally, lets avoid being adjacent to allies
            if (bestMicro.adjacentAllies < m.adjacentAllies) continue;
            if (bestMicro.adjacentAllies > m.adjacentAllies) {
                bestMicro = m;
                continue;
            }

            if (rng.nextInt(2) == 0) {
                bestMicro = m;
            }
        }
        if (bestMicro.loc != null && staticRC.canMove(staticRC.getLocation().directionTo(bestMicro.loc)))
            staticRC.move(staticRC.getLocation().directionTo(bestMicro.loc));
    }

    //we already mopped, or otherwise have no action cooldown, so we should try and stay out of harms way
    public static void safeMopperMicro() throws GameActionException {
        if (!staticRC.isMovementReady()) return;
        staticRC.setIndicatorString("safeMopperMicro");
        microArray = new mopperMicroInfo[9];
        populateMopperMicroArray();
        mopperMicroInfo bestMicro = microArray[0];
        for (int i = 1; i < 9; i++) {
            mopperMicroInfo m = microArray[i];
            if (!m.passable) continue;
            if (!bestMicro.passable) {
                bestMicro = m;
                continue;
            }

            //since we want to be safe, lets try and stay somewhat away from enemies - otherwise, the usual criteria
            //if one is in tower range and the other isnt, get out of tower range
            if (!bestMicro.inTowerRange && m.inTowerRange) continue;
            if (bestMicro.inTowerRange && !m.inTowerRange) {
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
        if (bestMicro.loc != null && staticRC.canMove(staticRC.getLocation().directionTo(bestMicro.loc)))
            staticRC.move(staticRC.getLocation().directionTo(bestMicro.loc));
    }

    //we have a target that we can't mop yet, so we should try and move towards it then mop it
    public static void targetedMopperMicro(Direction dir, MapLocation target) throws GameActionException {
        //mopperMicroInfo currentSquare = new mopperMicroInfo(staticRC.senseMapInfo(staticRC.getLocation()));
        //populating the array
        switch (dir) {
            case NORTH -> {
                microArray = new mopperMicroInfo[3];
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + -1, staticRC.getLocation().y + 1);
                    microArray[-1 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x , staticRC.getLocation().y + 1);
                    microArray[0 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y + 1);
                    microArray[1 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
            }
            case NORTHEAST -> {
                microArray = new mopperMicroInfo[1];
                MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y + 1);
                microArray[0] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
            }
            case EAST -> {
                microArray = new mopperMicroInfo[3];
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y + -1);
                    microArray[-1 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y );
                    microArray[0 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y + 1);
                    microArray[1 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
            }
            case SOUTHEAST -> {
                microArray = new mopperMicroInfo[1];
                MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y - 1);
                microArray[0] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
            }
            case SOUTH -> {
                microArray = new mopperMicroInfo[3];
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + -1, staticRC.getLocation().y - 1);
                    microArray[-1 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x , staticRC.getLocation().y - 1);
                    microArray[0 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y - 1);
                    microArray[1 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
            }
            case SOUTHWEST -> {
                microArray = new mopperMicroInfo[1];
                MapLocation newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y - 1);
                microArray[0] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
            }
            case WEST -> {
                microArray = new mopperMicroInfo[3];
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y + -1);
                    microArray[-1 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y );
                    microArray[0 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y + 1);
                    microArray[1 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
            }
            case NORTHWEST -> {
                microArray = new mopperMicroInfo[1];
                MapLocation newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y + 1);
                microArray[0] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
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
        mopperMicroInfo bestMicro = new mopperMicroInfo(staticRC.senseMapInfo(staticRC.getLocation()));
        for (int i = 0; i < microArray.length; i++) {
            mopperMicroInfo m = microArray[i];
            if (!m.passable) continue;
            if (!bestMicro.passable) {
                bestMicro = microArray[i];
                continue;
            }

            //if one is in tower range and the other isnt, get out of tower range
            if (!bestMicro.inTowerRange && m.inTowerRange) {
                continue;
            }
            if (bestMicro.inTowerRange && !m.inTowerRange) {
                bestMicro = m;
                continue;
            }

            int dist = bestMicro.loc.distanceSquaredTo(target);
            int altDist = m.loc.distanceSquaredTo(target);
            //System.out.println(dist + " " + altDist + " : " + bestMicro.loc + " " + m.loc);
//            if(dist <= actionRadius && altDist > actionRadius) continue;
//            if(dist > actionRadius && altDist <= actionRadius) {
//                bestMicro = m;
//                continue;
//            }
            if (dist > actionRadius || altDist > actionRadius) {
                if (dist < altDist) continue;
                if (dist > altDist) {
                    bestMicro = m;
                    continue;
                }
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

            //if one space avoids enemy paint and the other doesnt, go to the one avoiding enemy paint
            if (bestMicro.paintType != ENEMY_PAINT && m.paintType == ENEMY_PAINT) continue;
            if (bestMicro.paintType == ENEMY_PAINT && m.paintType != ENEMY_PAINT) {
                bestMicro = m;
                continue;
            }

            //if one space is on allied paint and the other isnt, go to allied paint
            if (bestMicro.paintType == ALLY_PAINT && m.paintType != ALLY_PAINT) continue;
            if (bestMicro.paintType != ALLY_PAINT && m.paintType == ALLY_PAINT) {
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
        //Systemout.println(bestMicro.loc);
        if (bestMicro.passable && staticRC.canMove(staticRC.getLocation().directionTo(bestMicro.loc))) {
            staticRC.move(staticRC.getLocation().directionTo(bestMicro.loc));
        }
    }
    public static void targetedClearMicro(Direction dir, MapLocation target) throws GameActionException {
        //mopperMicroInfo currentSquare = new mopperMicroInfo(staticRC.senseMapInfo(staticRC.getLocation()));
        //populating the array
        switch (dir) {
            case NORTH -> {
                microArray = new mopperMicroInfo[3];
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + -1, staticRC.getLocation().y + 1);
                    microArray[-1 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x , staticRC.getLocation().y + 1);
                    microArray[0 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y + 1);
                    microArray[1 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
            }
            case NORTHEAST -> {
                microArray = new mopperMicroInfo[1];
                MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y + 1);
                microArray[0] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
            }
            case EAST -> {
                microArray = new mopperMicroInfo[3];
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y + -1);
                    microArray[-1 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y );
                    microArray[0 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y + 1);
                    microArray[1 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
            }
            case SOUTHEAST -> {
                microArray = new mopperMicroInfo[1];
                MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y - 1);
                microArray[0] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
            }
            case SOUTH -> {
                microArray = new mopperMicroInfo[3];
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + -1, staticRC.getLocation().y - 1);
                    microArray[-1 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x , staticRC.getLocation().y - 1);
                    microArray[0 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y - 1);
                    microArray[1 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
            }
            case SOUTHWEST -> {
                microArray = new mopperMicroInfo[1];
                MapLocation newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y - 1);
                microArray[0] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
            }
            case WEST -> {
                microArray = new mopperMicroInfo[3];
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y + -1);
                    microArray[-1 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y );
                    microArray[0 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y + 1);
                    microArray[1 + 1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                }
            }
            case NORTHWEST -> {
                microArray = new mopperMicroInfo[1];
                MapLocation newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y + 1);
                microArray[0] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new mopperMicroInfo() : new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
            }
            case CENTER -> {
                microArray = null;
                return;
            }
        }
        ;
        int actionRadius = UnitType.MOPPER.actionRadiusSquared;
        //determining the best space
        //mopperMicroInfo bestMicro = microArray[0];
        //TURN 176 DEFAULT MEDIUM VERSION 9 GRAY VERSION 8 GOLD
        mopperMicroInfo bestMicro = new mopperMicroInfo(staticRC.senseMapInfo(staticRC.getLocation()));
        for (int i = 0; i < microArray.length; i++) {
            mopperMicroInfo m = microArray[i];
            if (!m.passable) continue;
            if (!bestMicro.passable) {
                bestMicro = microArray[i];
                continue;
            }

            //if one is in tower range and the other isnt, get out of tower range
            if (!bestMicro.inTowerRange && m.inTowerRange) continue;
            if (bestMicro.inTowerRange && !m.inTowerRange) {
                bestMicro = m;
                continue;
            }

            int dist = bestMicro.loc.distanceSquaredTo(target);
            int altDist = m.loc.distanceSquaredTo(target);
            if(dist > 8 || altDist > 8) {
                if (dist < altDist) continue;
                if (dist > altDist) {
                    bestMicro = m;
                    continue;
                }
            }
            //System.out.println(dist + " " + altDist + " : " + bestMicro.loc + " " + m.loc);
//            if(dist <= actionRadius && altDist > actionRadius) continue;
//            if(dist > actionRadius && altDist <= actionRadius) {
//                bestMicro = m;
//                continue;
//            }
//            if (dist > actionRadius || altDist > actionRadius) {
//                if (dist < altDist) continue;
//                if (dist > altDist) {
//                    bestMicro = m;
//                    continue;
//                }
//            }

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

            //if one space avoids enemy paint and the other doesnt, go to the one avoiding enemy paint
            if (bestMicro.paintType != ENEMY_PAINT && m.paintType == ENEMY_PAINT) continue;
            if (bestMicro.paintType == ENEMY_PAINT && m.paintType != ENEMY_PAINT) {
                bestMicro = m;
                continue;
            }

            //if one space is on allied paint and the other isnt, go to allied paint
            if (bestMicro.paintType == ALLY_PAINT && m.paintType != ALLY_PAINT) continue;
            if (bestMicro.paintType != ALLY_PAINT && m.paintType == ALLY_PAINT) {
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
        //Systemout.println(bestMicro.loc);
        if (bestMicro.passable && staticRC.canMove(staticRC.getLocation().directionTo(bestMicro.loc))) {
            staticRC.move(staticRC.getLocation().directionTo(bestMicro.loc));
        }
    }

    public static void populateMopperMicroArray() throws GameActionException {
        microArray = new mopperMicroInfo[9];
        int mapHeight = staticRC.getMapHeight() - 1;
        int mapWidth = staticRC.getMapWidth() - 1;
        MapLocation curLoc = staticRC.getLocation();
        int curX = curLoc.x;
        int curY = curLoc.y;
        int totalFilled = 0;
        //loop before being unrolled, in case changes need to be made
//        for(int dx = -1; dx <= 1; dx++) {
//            if(curX + dx >= 0 && curX + dx <= mapWidth) {
//                for (int dy = -1; dy <= 1; dy++) {
//                    if(curY + dy >= 0 && curY + dy <= mapHeight) {
//                        MapLocation newLoc = new MapLocation(curX + dx, curY + dy);
//                        if(staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
//                        else microArray[totalFilled] = new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
//                        totalFilled++;
//                    }
//                }
//            }
//        }
        if (curX + -1 >= 0 && curX + -1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + -1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                else microArray[totalFilled] = new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY  >= 0 && curY  <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY );
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                else microArray[totalFilled] = new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                else microArray[totalFilled] = new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
        }
        if (curX  >= 0 && curX  <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX , curY + -1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                else microArray[totalFilled] = new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY  >= 0 && curY  <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX , curY );
                //if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                /*else*/
                microArray[totalFilled] = new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX , curY + 1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                else microArray[totalFilled] = new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
        }
        if (curX + 1 >= 0 && curX + 1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + -1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                else microArray[totalFilled] = new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY  >= 0 && curY  <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY );
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                else microArray[totalFilled] = new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new mopperMicroInfo();
                else microArray[totalFilled] = new mopperMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
        }
        //populate the rest of the array in case we are on the edge of the map and couldn't fill it all
        for (int i = totalFilled; i < 9; i++) {
            microArray[i] = new mopperMicroInfo();
        }
    }

    //used to only return a corner direction if the offsets are equal
    //only will be used with a range of dx -2 to 2, same for dy
    //returns the direction from location 1 to location 2
    public static Direction customLocationTo(MapLocation loc1, MapLocation loc2) {
        int dx = loc2.x - loc1.x;
        int dy = loc2.y - loc1.y;
        return switch (dx) {
            case 0 -> switch (dy) {
                case -2, -1, -3, -4 -> Direction.SOUTH;
                case 2, 1, 3, 4 -> Direction.NORTH;
                default -> Direction.CENTER;
            };
            case 2 -> switch (dy) {
                case 3, 4 -> Direction.NORTH;
                case -3, -4 -> Direction.SOUTH;
                case 2 -> Direction.NORTHEAST;
                case -2 -> Direction.SOUTHEAST;
                case -1, 0, 1 -> Direction.EAST;
                default -> Direction.CENTER;
            };
            case 1 -> switch (dy) {
                case 1 -> Direction.NORTHEAST;
                case -1 -> Direction.SOUTHEAST;
                case 2, 3, 4 -> Direction.NORTH;
                case -2, -3, -4 -> Direction.SOUTH;
                case 0 -> Direction.EAST;
                default -> Direction.CENTER;
            };
            case -1 -> switch (dy) {
                case 1 -> Direction.NORTHWEST;
                case -1 -> Direction.SOUTHWEST;
                case 2, 3, 4 -> Direction.NORTH;
                case -2, -3, -4 -> Direction.SOUTH;
                case 0 -> Direction.WEST;
                default -> Direction.CENTER;
            };
            case -2 -> switch (dy) {
                case 3, 4 -> Direction.NORTH;
                case -3, -4 -> Direction.SOUTH;
                case 2 -> Direction.NORTHWEST;
                case -2 -> Direction.SOUTHWEST;
                case -1, 0, 1 -> Direction.WEST;
                default -> Direction.CENTER;
            };
            case 3 -> switch (dy) {
                case -2, -1, 0, 1, 2 -> Direction.EAST;
                case -4 -> Direction.SOUTH;
                case 4 -> Direction.NORTH;
                case 3 -> Direction.NORTHEAST;
                case -3 -> Direction.SOUTHEAST;
                default -> Direction.CENTER;
            };
            case -3 -> switch (dy) {
                case -2, -1, 0, 1, 2 -> Direction.WEST;
                case -4 -> Direction.SOUTH;
                case 4 -> Direction.NORTH;
                case 3 -> Direction.NORTHWEST;
                case -3 -> Direction.SOUTHWEST;
                default -> Direction.CENTER;
            };
            case -4 -> switch (dy) {
                case -2, -1, 0, 1, 2, 3, -3 -> Direction.WEST;
                case -4 -> Direction.SOUTHWEST;
                case 4 -> Direction.NORTHWEST;
                default -> Direction.CENTER;
            };
            case 4 -> switch (dy) {
                case -2, -1, 0, 1, 2, 3, -3 -> Direction.EAST;
                case -4 -> Direction.SOUTHEAST;
                case 4 -> Direction.NORTHEAST;
                default -> Direction.CENTER;
            };
            default -> Direction.CENTER;
        };
    }
}
