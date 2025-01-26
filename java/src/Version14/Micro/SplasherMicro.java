package Version14.Micro;

import Version14.Robots.Splasher;
import Version14.Utility.splasherUtil;
import battlecode.common.*;

import static Version14.RobotPlayer.*;
import static Version14.Robots.Splasher.averageEnemyPaint;
import static Version14.Robots.Splasher.seenEnemyTower;

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
        if(!tile.isPassable()) {
            passable = false;
            return;
        }
        passable = true;
        loc = tile.getMapLocation();
        paintType = switch(tile.getPaint()) {
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
        if(averageEnemyPaint != null) distanceToEnemyAverage = loc.distanceSquaredTo(averageEnemyPaint);
        else if(seenEnemyTower != null) distanceToEnemyAverage = loc.distanceSquaredTo(seenEnemyTower.getLocation());
//        MapLocation[] enemyPaintAverages = Utilities.getEnemyPaintAverages();
//        distanceToEnemyAverage = switch(enemyPaintAverages.length) {
//            case 0 -> Integer.MAX_VALUE;
//            case 1 -> loc.distanceSquaredTo(enemyPaintAverages[0]);
//            case 2 -> Math.min(loc.distanceSquaredTo(enemyPaintAverages[0]), loc.distanceSquaredTo(enemyPaintAverages[1]));
//            default -> Integer.MAX_VALUE;
//        };
        //count adjacent allies (depending on ally robots length, might be faster to call sensenearbyrobots?)
        for(RobotInfo robot : allyRobots) {
            if(loc.isWithinDistanceSquared(robot.getLocation(), 2)) {
                adjacentAllies++;
            }
        }
        //find the closest enemy, and also see if we are safe from towers
        for(RobotInfo robot : enemyRobots) {
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
                                        (dist <= GameConstants.SPLASHER_ATTACK_ENEMY_PAINT_RADIUS_SQUARED) ? 1 : 0;
                                case NEUTRAL_PAINT ->
                                        (dist <= GameConstants.SPLASHER_ATTACK_AOE_RADIUS_SQUARED) ? 1 : 0;
                                default -> 0;
                            };
                        }
                    }
                    case MOPPER -> {
                        if (dist <= 2) potentialPaintLoss += 5;
                        else if (dist < 8) potentialPaintLoss += Math.min(adjacentAllies, 5);
                    }
                    case SOLDIER -> {
                        potentialPaintLoss += (paintType == NEUTRAL_PAINT && dist <= UnitType.SOLDIER.actionRadiusSquared) ? 1 : 0;
                    }
                }
            }
        }

        paintLoss = switch(paintType) {
            case ENEMY_PAINT -> 2 + adjacentAllies;
            case ALLY_PAINT -> adjacentAllies;
            case NEUTRAL_PAINT -> 1 + adjacentAllies;
            default -> 0;
        };
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
//        health = staticRC.getHealth();
//        if (staticRC.isActionReady()) {
//            int minScore = 3;
//            //MapLocation bestAttack = splasherUtil.bestAttack(fightingTower, minScore);
//            MapLocation bestAttack = splasherUtil.bestAttack(fightingTower, Math.min(minScore, Splasher.numEnemyTiles));
//            if (bestAttack != null) {
//                if (staticRC.canAttack(bestAttack)) {
//                    staticRC.attack(bestAttack);
//                    staticRC.setIndicatorString("safeSplasherMicro" + " : " + averageEnemyPaint);
//                    runSafeSplasherMicro();
//                } else {
//                    staticRC.setIndicatorString("targeted micro: + "  + bestAttack +  " : " + averageEnemyPaint + " : " + MopperMicro.customLocationTo(staticRC.getLocation(), bestAttack));
//                    runTargetedSplasherMicro(MopperMicro.customLocationTo(staticRC.getLocation(), bestAttack), bestAttack);
//                    if(staticRC.canAttack(bestAttack)) staticRC.attack(bestAttack);
//                    else if(Clock.getBytecodesLeft() > 4000) {
//                        bestAttack = splasherUtil.cheapBestAttack(fightingTower, minScore);
//                        if(bestAttack != null && staticRC.canAttack(bestAttack)){
//                            staticRC.attack(bestAttack);
//                        }
//                    }
//                }
//            } else {
//                staticRC.setIndicatorString("safeSplasherMicro" + " : " + averageEnemyPaint);
//                runSafeSplasherMicro();
//                if(Clock.getBytecodesLeft() > 4000) {
//                    bestAttack = splasherUtil.cheapBestAttack(fightingTower, minScore);
//                    if(bestAttack != null && staticRC.canAttack(bestAttack)) staticRC.attack(bestAttack);
//                }
//            }
//        } else {
//            staticRC.setIndicatorString("safeSplasherMicro" + " : " + averageEnemyPaint);
//            runSafeSplasherMicro();
//        }
//    }
    //same as above, but takes in a best attack
    public static void integratedSplasherMicro(boolean fightingTower, MapLocation bestAttack) throws GameActionException {
        if(staticRC.isMovementReady()) {
            health = staticRC.getHealth();
            if (staticRC.isActionReady()) {
                int minScore = 2;
                //MapLocation bestAttack = splasherUtil.bestAttack(fightingTower, minScore);
                //MapLocation bestAttack = splasherUtil.bestAttack(fightingTower, Math.min(minScore, Splasher.numEnemyTiles));
                if (staticRC.canAttack(bestAttack)) {
                    staticRC.attack(bestAttack);
                    staticRC.setIndicatorString("safeSplasherMicro" + " : " + averageEnemyPaint);
                    runSafeSplasherMicro();
                } else {
                    staticRC.setIndicatorString("targeted micro: + " + bestAttack + " : " + averageEnemyPaint + " : " + MopperMicro.customLocationTo(staticRC.getLocation(), bestAttack));
                    runTargetedSplasherMicro(MopperMicro.customLocationTo(staticRC.getLocation(), bestAttack), bestAttack);
                    if (staticRC.canAttack(bestAttack)) staticRC.attack(bestAttack);
                    else if (Clock.getBytecodesLeft() > 4100) {
                        bestAttack = splasherUtil.cheapBestAttack(fightingTower, minScore);
                        if (bestAttack != null && staticRC.canAttack(bestAttack)) {
                            staticRC.attack(bestAttack);
                        }
                    }
                }
//            } else {
//                staticRC.setIndicatorString("safeSplasherMicro" + " : " + averageEnemyPaint);
//                runSafeSplasherMicro();
//                if(Clock.getBytecodesLeft() > 4000) {
//                    bestAttack = splasherUtil.cheapBestAttack(fightingTower, minScore);
//                    if(bestAttack != null && staticRC.canAttack(bestAttack)) staticRC.attack(bestAttack);
//                }
//            }
            } else {
                staticRC.setIndicatorString("safeSplasherMicro" + " : " + averageEnemyPaint);
                runSafeSplasherMicro();
            }
        }
        else {
            if(staticRC.isActionReady()) {
                MapLocation bestLoc = splasherUtil.cheapBestAttack(fightingTower, 2);
                if(bestLoc != null && staticRC.canAttack(bestLoc)) {
                    staticRC.attack(bestLoc);
                }
            }
        }
    }

    //runs the splasher micro trying to get within range of a target attack location
    public static void runTargetedSplasherMicro(Direction direction, MapLocation target) throws GameActionException {
        microArray = new splasherMicroInfo[3];
        switch (direction) {
            case NORTH -> {
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + -1, staticRC.getLocation().y + 1);
                    microArray[0] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x , staticRC.getLocation().y + 1);
                    microArray[1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y + 1);
                    microArray[2] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                }
            }
            case NORTHEAST -> {
                MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y + 1);
                microArray[0] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y);
                microArray[1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                newLoc = new MapLocation(staticRC.getLocation().x, staticRC.getLocation().y + 1);
                microArray[2] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
            }
            case EAST -> {
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y + -1);
                    microArray[0] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y );
                    microArray[1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y + 1);
                    microArray[2] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                }
            }
            case SOUTHEAST -> {
                MapLocation newLoc = new MapLocation(staticRC.getLocation().x, staticRC.getLocation().y - 1);
                microArray[0] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y - 1);
                microArray[1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y);
                microArray[2] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
            }
            case SOUTH -> {
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + -1, staticRC.getLocation().y - 1);
                    microArray[0] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x , staticRC.getLocation().y - 1);
                    microArray[1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x + 1, staticRC.getLocation().y - 1);
                    microArray[2] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                }
            }
            case SOUTHWEST -> {
                MapLocation newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y - 1);
                microArray[0] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                newLoc = new MapLocation(staticRC.getLocation().x, staticRC.getLocation().y - 1);
                microArray[1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y);
                microArray[2] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
            }
            case WEST -> {
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y + -1);
                    microArray[0] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y );
                    microArray[1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                }
                {
                    MapLocation newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y + 1);
                    microArray[2] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                }
            }
            case NORTHWEST -> {
                MapLocation newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y + 1);
                microArray[0] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                newLoc = new MapLocation(staticRC.getLocation().x - 1, staticRC.getLocation().y);
                microArray[1] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                newLoc = new MapLocation(staticRC.getLocation().x, staticRC.getLocation().y + 1);
                microArray[2] = (staticRC.canSenseRobotAtLocation(newLoc) || !staticRC.onTheMap(newLoc)) ? new splasherMicroInfo() : new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
            }
            case CENTER -> {
                microArray = null;
                return;
            }
        };
        //determining the best space
        //splasherMicroInfo bestMicro = microArray[0];
        int actionRadius = UnitType.SPLASHER.actionRadiusSquared;
        splasherMicroInfo bestMicro = new splasherMicroInfo(staticRC.senseMapInfo(staticRC.getLocation()));
        for(int i = 0; i < 3; i++) {
            splasherMicroInfo m = microArray[i];
            if(!m.passable) continue;
            if(!bestMicro.passable) {
                bestMicro = microArray[i];
                continue;
            }

            if(health <= 100) {
                //if one is in tower range and the other isnt, get out of tower range
                if(!bestMicro.inTowerRange && m.inTowerRange) continue;
                if(bestMicro.inTowerRange && !m.inTowerRange) {
                    bestMicro = m;
                    continue;
                }
            }


            int dist = bestMicro.loc.distanceSquaredTo(target);
            int altDist = m.loc.distanceSquaredTo(target);
            if(dist <= actionRadius && altDist > actionRadius) continue;
            if(dist > actionRadius && altDist <= actionRadius) {
                bestMicro = m;
                continue;
            }

            if(bestMicro.paintLoss < m.paintLoss) continue;
            if(m.paintLoss < bestMicro.paintLoss) {
                bestMicro = m;
                continue;
            }

            if(bestMicro.potentialPaintLoss < microArray[i].potentialPaintLoss) continue;
            if(microArray[i].potentialPaintLoss < bestMicro.potentialPaintLoss) {
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

            //finally, lets avoid being adjacent to allies
            if(bestMicro.adjacentAllies < m.adjacentAllies) continue;
            if(bestMicro.adjacentAllies > m.adjacentAllies) {
                bestMicro = m;
                continue;
            }
        }
        if(bestMicro.passable && staticRC.canMove(staticRC.getLocation().directionTo(bestMicro.loc))) {
            staticRC.move(staticRC.getLocation().directionTo(bestMicro.loc));
        }
    }

    //attempts to stay safe while running a micro that optimizes for splashers
    private static void runSafeSplasherMicro() throws GameActionException {
        if(!staticRC.isMovementReady()) return;
        microArray = new splasherMicroInfo[9];
        populateSplasherMicroArray();
        splasherMicroInfo bestMicro = microArray[0];
        for(int i = 1; i < microArray.length; i++) {
            splasherMicroInfo m = microArray[i];
            if(!m.passable) continue;
            if(!bestMicro.passable) {
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

            //if one space is on allied paint and the other isnt, go to allied paint
            if(bestMicro.paintType == ALLY_PAINT && m.paintType != ALLY_PAINT) continue;
            if(bestMicro.paintType != ALLY_PAINT && m.paintType == ALLY_PAINT) {
                bestMicro = m;
                continue;
            }

            //look at which place will lose us the least paint at the end of this turn
            if(bestMicro.paintLoss < microArray[i].paintLoss) continue;
            if(microArray[i].paintLoss < bestMicro.paintLoss) {
                bestMicro = microArray[i];
                continue;
            }

            if(bestMicro.potentialPaintLoss < microArray[i].potentialPaintLoss) continue;
            if(microArray[i].potentialPaintLoss < bestMicro.potentialPaintLoss) {
                bestMicro = microArray[i];
                continue;
            }

            if(bestMicro.distanceToEnemyAverage < m.distanceToEnemyAverage) continue;
            if(bestMicro.distanceToEnemyAverage > m.distanceToEnemyAverage) {
                bestMicro = m;
                continue;
            }

            //if one space avoids enemy paint and the other doesnt, go to the one avoiding enemy paint
            if(bestMicro.paintType != ENEMY_PAINT && m.paintType == ENEMY_PAINT) continue;
            if(bestMicro.paintType == ENEMY_PAINT && m.paintType != ENEMY_PAINT) {
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

            if(rng.nextInt(2) == 0) {
                bestMicro = m;
            }
        }
        if(bestMicro.loc != null && staticRC.canMove(staticRC.getLocation().directionTo(bestMicro.loc))) staticRC.move(staticRC.getLocation().directionTo(bestMicro.loc));
    }

    public static void populateSplasherMicroArray() throws GameActionException {
        microArray = new splasherMicroInfo[9];
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
//                        if(staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
//                        else microArray[totalFilled] = new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
//                        totalFilled++;
//                    }
//                }
//            }
//        }
        if (curX + -1 >= 0 && curX + -1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + -1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                else microArray[totalFilled] = new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY  >= 0 && curY  <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY );
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                else microArray[totalFilled] = new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                else microArray[totalFilled] = new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
        }
        if (curX  >= 0 && curX  <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX , curY + -1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                else microArray[totalFilled] = new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY  >= 0 && curY  <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX , curY );
                //if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                microArray[totalFilled] = new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX , curY + 1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                else microArray[totalFilled] = new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
        }
        if (curX + 1 >= 0 && curX + 1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + -1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                else microArray[totalFilled] = new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY  >= 0 && curY  <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY );
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                else microArray[totalFilled] = new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new splasherMicroInfo();
                else microArray[totalFilled] = new splasherMicroInfo(staticRC.senseMapInfo(newLoc));
                totalFilled++;
            }
        }
        //populate the rest of the array in case we are on the edge of the map and couldn't fill it all
        for(int i = totalFilled; i < 9; i++) {
            microArray[i] = new splasherMicroInfo();
        }
    }
}
