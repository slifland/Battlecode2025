package Version9;


import battlecode.common.*;
import static Version9.RobotPlayer.*;

class microInfo {
    boolean passable;
    public MapLocation loc;
    public int minDistanceToEnemy;
    public PaintType paint;
    public boolean inTowerRange;
    public int adjacentAllies;
    public int distanceToEnemyAverage;
    public int paintLoss;

    //creates a micro Info tile, populating its information based on a map info tile
    public microInfo(MapInfo tile) {
        passable = tile.isPassable();
        //we don't need to populate info if the tile is not passable - waste of bytecodes
        if(!passable) return;
        loc = tile.getMapLocation();
        paint = tile.getPaint();
        adjacentAllies = 0;
        minDistanceToEnemy = Integer.MAX_VALUE;
        populateMicro();
    }
    //default constructor for a microInfo, setting passable to false so it will never be considered
    //used for spaces which are not on the map
    public microInfo() {
        passable = false;
    }

    //UTILITY METHODS
    

    //takes in a map info, and populates this relevant micro info object using it, as well as known information
    public void populateMicro() {
        //find the closest enemy, and also see if we are safe from towers
        for(RobotInfo robot : enemyRobots) {
            int dist = loc.distanceSquaredTo(robot.getLocation());
            if(dist < minDistanceToEnemy) {
                minDistanceToEnemy = dist;
            }
            if(robot.type.isTowerType() && dist <= robot.type.actionRadiusSquared) {
                inTowerRange = true;
            }
            //nothing can be closer, and we have already determined tower safety
            //if we haven't, then breaking out can risk missing out on information
            if(!inTowerRange && minDistanceToEnemy == 1) break;
        }
        //find the closest ally, and count allies that are cardinally adjacent to this square
        for(RobotInfo robot : allyRobots) {
            int dist = loc.distanceSquaredTo(robot.getLocation());
            if(dist == 0) continue;
            if(dist <= 2) {
                adjacentAllies++;
            }
        }
        paintLoss = switch(paint) {
            case PaintType.ENEMY_PRIMARY, PaintType.ENEMY_SECONDARY -> 2 + adjacentAllies;
            case PaintType.ALLY_PRIMARY, PaintType.ALLY_SECONDARY-> adjacentAllies;
            case PaintType.EMPTY -> 1 + adjacentAllies;
            default -> 0;
        };
        distanceToEnemyAverage = (Soldier.averageEnemyPaint != null) ? loc.distanceSquaredTo(Soldier.averageEnemyPaint) : Integer.MAX_VALUE;
//        MapLocation[] enemyPaintAverages = Utilities.getEnemyPaintAverages();
//        distanceToEnemyAverage = switch(enemyPaintAverages.length) {
//            case 0 -> Integer.MAX_VALUE;
//            case 1 -> loc.distanceSquaredTo(enemyPaintAverages[0]);
//            case 2 -> Math.min(loc.distanceSquaredTo(enemyPaintAverages[0]), loc.distanceSquaredTo(enemyPaintAverages[1]));
//            default -> Integer.MAX_VALUE;
//        };
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        microInfo other = (microInfo) obj;
        if(loc == null) return false;
        return loc.equals(other.loc);
    }

}

public class Micro {

    private static final int soldierHealthAttackThreshold = 100;
    private static final int splasherHealthAttackThreshold = 100;
    private static microInfo[] microArray;
    private static boolean isRushing;
    //the method any robot will use to interact with micro - directs the robot to the correct micro method
    public static Direction runMicro(RobotController rc, boolean isRushing) throws GameActionException {
        populateMicroArray(rc);
        Micro.isRushing = isRushing;
        return switch (rc.getType()) {
            case UnitType.SOLDIER -> runSoldierMicro(rc);
            default -> null;
        };
    }

    //the method any robot will use to interact with micro - directs the robot to the correct micro method
    public static Direction runMicro(RobotController rc) throws GameActionException {
        if(!rc.isMovementReady()) return Direction.CENTER;
        populateMicroArray(rc);
        Micro.isRushing = false;
        return switch (rc.getType()) {
            case UnitType.SOLDIER -> runSoldierMicro(rc);
            default -> Direction.CENTER;
        };
    }
    
    //attempts to find the best place near the ruin to fill in, and also move to the best adjacent square
    public static void ruinBuildingMicro(RobotController rc, MapLocation ruin, boolean[][] desiredPattern, MapInfo[] tilesToFill) throws GameActionException {
        rc.setIndicatorString("Building a ruin!" + ruin.toString());
        int curDist = rc.getLocation().distanceSquaredTo(ruin);
        MapLocation bestAttack = null;
        if(rc.isActionReady()) {
            bestAttack = bestRuinAttack(rc, desiredPattern, tilesToFill, ruin);
            if (bestAttack != null && rc.canAttack(bestAttack)) rc.attack(bestAttack, Utilities.getColorFromCustomPattern(bestAttack, desiredPattern, ruin));
        }
        if(bestAttack == null && rc.getChips() > 1000) {
            Direction dir = BFS_7x7.pathfind(rc, ruin);
            if(rc.canMove(dir)) rc.move(dir);
            return;
        }
        if(rc.isMovementReady()) {
            if(curDist <= 8)
                populateMicroArrayRuin(rc, ruin);
            else
                populateMicroArray(rc);
            microInfo bestMicro = microArray[0];
            for(int i = 1; i < 9; i++) {
                do {
                    //if one space is passable and the other is not, then passable is better
                    if (!microArray[i].passable) break;
                    if (!bestMicro.passable) {
                        bestMicro = microArray[i];
                        break;
                    }

                    if(curDist > 8 ) {
                        int distToRuin = bestMicro.loc.distanceSquaredTo(ruin);
                        int altDistToRuin = microArray[i].loc.distanceSquaredTo(ruin);
                        if(distToRuin < altDistToRuin) break;
                        if(altDistToRuin < distToRuin) {
                            bestMicro = microArray[i];
                            break;
                        }
                    }

                    //look at which place will lose us the least paint at the end of this turn
                    if (bestMicro.paintLoss < microArray[i].paintLoss) break;
                    if (microArray[i].paintLoss < bestMicro.paintLoss) {
                        bestMicro = microArray[i];
                        break;
                    }



                    if(bestAttack != null) {
                        int distToAction = bestMicro.loc.distanceSquaredTo(bestAttack);
                        int altDistToAction = microArray[i].loc.distanceSquaredTo(bestAttack);
                        if(distToAction < altDistToAction) break;
                        if(altDistToAction < distToAction) {
                            bestMicro = microArray[i];
                            break;
                        }
                    }

                    //regardless of action readiness, the next considerations are unified:
                    //we prioritize allied paint over neutral paint over enemy paint
                    if (bestMicro.paint.isAlly() && !microArray[i].paint.isAlly()) break;
                    if (!bestMicro.paint.isAlly() && microArray[i].paint.isAlly()) {
                        bestMicro = microArray[i];
                        break;
                    }

                    if (!bestMicro.paint.isEnemy() && microArray[i].paint.isEnemy()) break;
                    if (bestMicro.paint.isEnemy() && !microArray[i].paint.isEnemy()) {
                        bestMicro = microArray[i];
                        break;
                    }

                    //next, lets try and avoid being next to allies cardinally, so that we dont get swung at by moppers
                    if (bestMicro.adjacentAllies < microArray[i].adjacentAllies) break;
                    if (microArray[i].adjacentAllies < bestMicro.adjacentAllies) {
                        bestMicro = microArray[i];
                        break;
                    }
                } while(false);
            }
            if(bestMicro.passable && rc.canMove(rc.getLocation().directionTo(bestMicro.loc))) {
                rc.move(rc.getLocation().directionTo(bestMicro.loc));
            }
        }
        if(rc.isActionReady() && bestAttack != null) {
            if(rc.canAttack(bestAttack)) rc.attack(bestAttack, Utilities.getColorFromCustomPattern(bestAttack, desiredPattern, ruin));
        }

    }

    //finds the best square near the ruin for us to attack
    //1. attack the tile we are on if empty
    //2. attack an empty tile close to us
    //3. attack an empty tile further away
    //4. attack an allied tile with the wrong pattern close to us
    //5. attack an allied tile further away with the wrong pattern
    public static MapLocation bestRuinAttack(RobotController rc, boolean[][] desiredPattern, MapInfo[] tilesToFill, MapLocation ruin) throws GameActionException {
        MapInfo bestTile = null;
        int minDist = Integer.MAX_VALUE;
        if(rc.senseMapInfo(rc.getLocation()).getPaint() == PaintType.EMPTY && rc.getLocation().isWithinDistanceSquared(ruin, 8)) return rc.getLocation();
        for(MapInfo tile : tilesToFill) {
            int dist = rc.getLocation().distanceSquaredTo(tile.getMapLocation());
            if(!tile.isPassable()) continue;
            if(tile.getPaint() == PaintType.EMPTY && (bestTile == null || dist < minDist || bestTile.getPaint().isAlly())) {
                bestTile = tile;
                minDist = dist;
            }
            else if(tile.getPaint().isAlly() && (bestTile == null || (dist < minDist && bestTile.getPaint().isAlly()))
             && Utilities.getColorFromCustomPattern(tile.getMapLocation(), desiredPattern, ruin) != tile.getPaint().isSecondary()) {
                minDist = dist;
                bestTile = tile;
            }
        }
        return (bestTile != null) ? bestTile.getMapLocation() : null;
    }

    //the micro method for soldiers
    public static Direction runSoldierMicro(RobotController rc) throws GameActionException {
        int health = rc.getHealth();
        microInfo bestMicro = microArray[0];
//        for(microInfo m : microArray) {
//            if (m.equals(bestMicro)) continue;
//            //if one space is passable and the other is not, then passable is better
//            if (!m.passable) continue;
//            if (!bestMicro.passable) {
//                bestMicro = m;
//                continue;
//            }
//            //depending on our health, and whether we have nearby allies, we may be fine moving into tower range
//            if (rc.isActionReady() && health >= soldierHealthAttackThreshold || isRushing) {
//                //we are fine being in tower range as long as we can attack, because we have enough health
//                if (bestMicro.inTowerRange && !m.inTowerRange) continue;
//                if (!bestMicro.inTowerRange && m.inTowerRange) {
//                    bestMicro = m;
//                    continue;
//                }
//            }
//            //definitely don't be in tower range!
//            else {
//                //check if one space is in tower range and the other isn't
//                if (!bestMicro.inTowerRange && m.inTowerRange) continue;
//                if (bestMicro.inTowerRange && !m.inTowerRange) {
//                    bestMicro = m;
//                    continue;
//                }
//
//            }
//            //regardless of action readiness, the next considerations are unified:
//            //we prioritize allied paint over neutral paint over enemy paint
//            if (bestMicro.paint.isAlly() && !m.paint.isAlly()) continue;
//            if (!bestMicro.paint.isAlly() && m.paint.isAlly()) {
//                bestMicro = m;
//                continue;
//            }
//
//            if (!bestMicro.paint.isEnemy() && m.paint.isEnemy()) continue;
//            if (bestMicro.paint.isEnemy() && !m.paint.isEnemy()) {
//                bestMicro = m;
//                continue;
//            }
//
//            //next, lets try and avoid being next to allies cardinally, so that we dont get swung at by moppers
//            if (bestMicro.adjacentAllies < m.adjacentAllies) continue;
//            if (m.adjacentAllies < bestMicro.adjacentAllies) {
//                bestMicro = m;
//                continue;
//            }
//            //next, lets try and be near allies
//            if (bestMicro.minDistanceToAlly < m.minDistanceToAlly) continue;
//            if (m.minDistanceToAlly < bestMicro.minDistanceToAlly) {
//                bestMicro = m;
//                continue;
//            }
//        }
        for(int i = 1; i < 9; i++) {
            do {
                //if one space is passable and the other is not, then passable is better
                if (!microArray[i].passable) break;
                if (!bestMicro.passable) {
                    bestMicro = microArray[i];
                    break;
                }

                //depending on our health, and whether we have nearby allies, we may be fine moving into tower range
                if (rc.isActionReady() && health >= soldierHealthAttackThreshold || isRushing) {
                    //we are fine being in tower range as long as we can attack, because we have enough health
                    if (bestMicro.inTowerRange && !microArray[i].inTowerRange) break;
                    if (!bestMicro.inTowerRange && microArray[i].inTowerRange) {
                        bestMicro = microArray[i];
                        break;
                    }
                }
                //definitely don't be in tower range!
                else {
                    //check if one space is in tower range and the other isn't
                    if (!bestMicro.inTowerRange && microArray[i].inTowerRange) break;
                    if (bestMicro.inTowerRange && !microArray[i].inTowerRange) {
                        bestMicro = microArray[i];
                        break;
                    }

                }
                //look at which place will lose us the least paint at the end of this turn
                if (bestMicro.paintLoss < microArray[i].paintLoss) break;
                if (microArray[i].paintLoss < bestMicro.paintLoss) {
                    bestMicro = microArray[i];
                    break;
                }
                //regardless of action readiness, the next considerations are unified:
                //we prioritize allied paint over neutral paint over enemy paint
                if (bestMicro.paint.isAlly() && !microArray[i].paint.isAlly()) break;
                if (!bestMicro.paint.isAlly() && microArray[i].paint.isAlly()) {
                    bestMicro = microArray[i];
                    break;
                }

                if (!bestMicro.paint.isEnemy() && microArray[i].paint.isEnemy()) break;
                if (bestMicro.paint.isEnemy() && !microArray[i].paint.isEnemy()) {
                    bestMicro = microArray[i];
                    break;
                }

                //next, lets try and avoid being next to allies cardinally, so that we dont get swung at by moppers
                if (bestMicro.adjacentAllies < microArray[i].adjacentAllies) break;
                if (microArray[i].adjacentAllies < bestMicro.adjacentAllies) {
                    bestMicro = microArray[i];
                    break;
                }

                if(bestMicro.distanceToEnemyAverage < microArray[i].distanceToEnemyAverage) break;
                if(microArray[i].distanceToEnemyAverage < bestMicro.distanceToEnemyAverage) {
                    bestMicro = microArray[i];
                    break;
                }
            } while(false);

           // at this point, nothing more to consider - unclear how minDistance to enemy would affect soldier micro
        }
        return (bestMicro.passable) ? rc.getLocation().directionTo(bestMicro.loc) : Direction.CENTER;
    }


    //UTILITY METHODS

    //populates the array of potential spaces we can move too for this turn
    public static void populateMicroArray(RobotController rc) throws GameActionException {
        microArray = new microInfo[9];
        int mapHeight = rc.getMapHeight() - 1;
        int mapWidth = rc.getMapWidth() - 1;
        MapLocation curLoc = rc.getLocation();
        int curX = curLoc.x;
        int curY = curLoc.y;
        int totalFilled = 0;
        //loop before being unrolled, in case changes need to be made
//        for(int dx = -1; dx <= 1; dx++) {
//            if(curX + dx >= 0 && curX + dx  <= mapWidth) {
//                for (int dy = -1; dy <= 1; dy++) {
//                    if(curY + dy >= 0 && curY + dy <= mapHeight) {
//                        MapLocation newLoc = new MapLocation(curX + dx, curY + dy);
//                        if(rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
//                        else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc));
//                        totalFilled++;
//                    }
//                }
//            }
//        }
        if (curX + -1 >= 0 && curX + -1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + -1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 0);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
        }
        if (curX + 0 >= 0 && curX + 0 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + -1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + 0);
                //if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                /*else*/ microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + 1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
        }
        if (curX + 1 >= 0 && curX + 1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + -1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 0);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
        }
        //populate the rest of the array in case we are on the edge of the map and couldn't fill it all
        for(int i = totalFilled; i < 9; i++) {
            microArray[i] = new microInfo();
        }
    }
    //populates the array of potential spaces we can move too for this turn
    public static void populateMicroArrayRuin(RobotController rc, MapLocation ruin) throws GameActionException {
        microArray = new microInfo[9];
        int mapHeight = rc.getMapHeight() - 1;
        int mapWidth = rc.getMapWidth() - 1;
        MapLocation curLoc = rc.getLocation();
        int curX = curLoc.x;
        int curY = curLoc.y;
        int totalFilled = 0;
        //loop before being unrolled, in case changes need to be made
//        for(int dx = -1; dx <= 1; dx++) {
//            if(curX + dx >= 0 && curX + dx  <= mapWidth) {
//                for (int dy = -1; dy <= 1; dy++) {
//                    if(curY + dy >= 0 && curY + dy <= mapHeight) {
//                        MapLocation newLoc = new MapLocation(curX + dx, curY + dy);
//                        if(rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
//                        else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc));
//                        totalFilled++;
//                    }
//                }
//            }
//        }
        if (curX + -1 >= 0 && curX + -1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + -1);
                if (rc.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 0);
                if (rc.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 1);
                if (rc.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
        }
        if (curX + 0 >= 0 && curX + 0 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + -1);
                if (rc.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + 0);
                //if (rc.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                /*else*/ microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + 1);
                if (rc.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
        }
        if (curX + 1 >= 0 && curX + 1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + -1);
                if (rc.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 0);
                if (rc.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 1);
                if (rc.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo((newLoc)));
                totalFilled++;
            }
        }
        //populate the rest of the array in case we are on the edge of the map and couldn't fill it all
        for(int i = totalFilled; i < 9; i++) {
            microArray[i] = new microInfo();
        }
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
