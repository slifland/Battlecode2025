package Version8;


import battlecode.common.*;
import static Version8.RobotPlayer.*;

class microInfo {
    boolean passable;
    public MapLocation loc;
    public int minDistanceToEnemy;
    public PaintType paint;
    public int minDistanceToAlly;
    public boolean inTowerRange;
    public int numAlliesCardinalAdjacent;
    public boolean canMop;
    public boolean canSplash;

    //creates a micro Info tile, populating its information based on a map info tile
    public microInfo(MapInfo tile, boolean checkForMop, boolean checkForSplash) {
        passable = tile.isPassable();
        //we don't need to populate info if the tile is not passable - waste of bytecodes
        if(!passable) return;
        loc = tile.getMapLocation();
        paint = tile.getPaint();
        numAlliesCardinalAdjacent = 0;
        minDistanceToAlly = Integer.MAX_VALUE;
        minDistanceToEnemy = Integer.MAX_VALUE;
        populateMicro();
        if(checkForMop) {
            checkForMop();
        }
        if(checkForSplash) {
            checkForSplash();
        }
    }
    //default constructor for a microInfo, setting passable to false so it will never be considered
    //used for spaces which are not on the map
    public microInfo() {
        passable = false;
    }

    //UTILITY METHODS

    //checks for:
    // if there is a tile in mopping radius (canMop)
    public void checkForMop() {
        for(MapInfo tile : nearbyTiles) {
            if(tile.getPaint().isEnemy() && tile.getMapLocation().isWithinDistanceSquared(loc, UnitType.MOPPER.actionRadiusSquared)) {
                canMop = true;
                return;
            }
        }
    }
    //checks for:
    // if there is a tile in splashing radius (canSplash)
    public void checkForSplash() {
        for(MapInfo tile : nearbyTiles) {
            if(tile.getPaint().isEnemy() && tile.getMapLocation().isWithinDistanceSquared(loc, UnitType.SPLASHER.actionRadiusSquared)) {
                canSplash = true;
                return;
            }
        }
    }

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
            if(dist < minDistanceToAlly) {
                minDistanceToAlly = dist;
            }
            if(dist == 1) {
                numAlliesCardinalAdjacent++;
            }
        }
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
            case UnitType.MOPPER -> runMopperMicro(rc);
            case UnitType.SPLASHER -> runSplasherMicro(rc);
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
            case UnitType.MOPPER -> runMopperMicro(rc);
            case UnitType.SPLASHER -> runSplasherMicro(rc);
            default -> Direction.CENTER;
        };
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
//            if (bestMicro.numAlliesCardinalAdjacent < m.numAlliesCardinalAdjacent) continue;
//            if (m.numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
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
//        for(int i = 1; i < 9; i++) {
//            do {
//                //if one space is passable and the other is not, then passable is better
//                if (!microArray[i].passable) break;
//                if (!bestMicro.passable) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//                
//                //depending on our health, and whether we have nearby allies, we may be fine moving into tower range
//                if (rc.isActionReady() && health >= soldierHealthAttackThreshold || isRushing) {
//                    //we are fine being in tower range as long as we can attack, because we have enough health
//                    if (bestMicro.inTowerRange && !microArray[i].inTowerRange) break;
//                    if (!bestMicro.inTowerRange && microArray[i].inTowerRange) {
//                        bestMicro = microArray[i];
//                        break;
//                    }
//                }
//                //definitely don't be in tower range!
//                else {
//                    //check if one space is in tower range and the other isn't
//                    if (!bestMicro.inTowerRange && microArray[i].inTowerRange) break;
//                    if (bestMicro.inTowerRange && !microArray[i].inTowerRange) {
//                        bestMicro = microArray[i];
//                        break;
//                    }
//
//                }
//                //regardless of action readiness, the next considerations are unified:
//                //we prioritize allied paint over neutral paint over enemy paint
//                if (bestMicro.paint.isAlly() && !microArray[i].paint.isAlly()) break;
//                if (!bestMicro.paint.isAlly() && microArray[i].paint.isAlly()) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//
//                if (!bestMicro.paint.isEnemy() && microArray[i].paint.isEnemy()) break;
//                if (bestMicro.paint.isEnemy() && !microArray[i].paint.isEnemy()) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//
//                //next, lets try and avoid being next to allies cardinally, so that we dont get swung at by moppers
//                if (bestMicro.numAlliesCardinalAdjacent < microArray[i].numAlliesCardinalAdjacent) break;
//                if (microArray[i].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//                //next, lets try and be near allies
//                if (bestMicro.minDistanceToAlly < microArray[i].minDistanceToAlly) break;
//                if (microArray[i].minDistanceToAlly < bestMicro.minDistanceToAlly) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//            } while(false);

            //at this point, nothing more to consider - unclear how minDistance to enemy would affect soldier micro
        //}
        // Initialize 'bestMicro' before the loop starts
// (Assumed to be initialized elsewhere in the program)

// Unrolling the loop for i = 1 to 8
// For i = 1:
        do {
            if (!microArray[1].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[1];
                break;
            }

            if (rc.isActionReady() && health >= soldierHealthAttackThreshold || isRushing) {
                if (bestMicro.inTowerRange && !microArray[1].inTowerRange) break;
                if (!bestMicro.inTowerRange && microArray[1].inTowerRange) {
                    bestMicro = microArray[1];
                    break;
                }
            } else {
                if (!bestMicro.inTowerRange && microArray[1].inTowerRange) break;
                if (bestMicro.inTowerRange && !microArray[1].inTowerRange) {
                    bestMicro = microArray[1];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[1].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[1].paint.isAlly()) {
                bestMicro = microArray[1];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[1].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[1].paint.isEnemy()) {
                bestMicro = microArray[1];
                break;
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[1].numAlliesCardinalAdjacent) break;
            if (microArray[1].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[1];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[1].minDistanceToAlly) break;
            if (microArray[1].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[1];
                break;
            }
        } while(false);

// For i = 2:
        do {
            if (!microArray[2].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[2];
                break;
            }
            if (rc.isActionReady() && health >= soldierHealthAttackThreshold || isRushing) {
                if (bestMicro.inTowerRange && !microArray[2].inTowerRange) break;
                if (!bestMicro.inTowerRange && microArray[2].inTowerRange) {
                    bestMicro = microArray[2];
                    break;
                }
            } else {
                if (!bestMicro.inTowerRange && microArray[2].inTowerRange) break;
                if (bestMicro.inTowerRange && !microArray[2].inTowerRange) {
                    bestMicro = microArray[2];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[2].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[2].paint.isAlly()) {
                bestMicro = microArray[2];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[2].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[2].paint.isEnemy()) {
                bestMicro = microArray[2];
                break;
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[2].numAlliesCardinalAdjacent) break;
            if (microArray[2].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[2];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[2].minDistanceToAlly) break;
            if (microArray[2].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[2];
                break;
            }
        } while(false);

// Repeat similarly for i = 3 to 8
// For i = 3:
        do {
            if (!microArray[3].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[3];
                break;
            }
            

            if (rc.isActionReady() && health >= soldierHealthAttackThreshold || isRushing) {
                if (bestMicro.inTowerRange && !microArray[3].inTowerRange) break;
                if (!bestMicro.inTowerRange && microArray[3].inTowerRange) {
                    bestMicro = microArray[3];
                    break;
                }
            } else {
                if (!bestMicro.inTowerRange && microArray[3].inTowerRange) break;
                if (bestMicro.inTowerRange && !microArray[3].inTowerRange) {
                    bestMicro = microArray[3];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[3].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[3].paint.isAlly()) {
                bestMicro = microArray[3];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[3].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[3].paint.isEnemy()) {
                bestMicro = microArray[3];
                break;
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[3].numAlliesCardinalAdjacent) break;
            if (microArray[3].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[3];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[3].minDistanceToAlly) break;
            if (microArray[3].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[3];
                break;
            }
        } while(false);

// Continue the same pattern for i = 4 to i = 8...
        // For i = 4:
        do {
            if (!microArray[4].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[4];
                break;
            }
            

            if (rc.isActionReady() && health >= soldierHealthAttackThreshold || isRushing) {
                if (bestMicro.inTowerRange && !microArray[4].inTowerRange) break;
                if (!bestMicro.inTowerRange && microArray[4].inTowerRange) {
                    bestMicro = microArray[4];
                    break;
                }
            } else {
                if (!bestMicro.inTowerRange && microArray[4].inTowerRange) break;
                if (bestMicro.inTowerRange && !microArray[4].inTowerRange) {
                    bestMicro = microArray[4];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[4].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[4].paint.isAlly()) {
                bestMicro = microArray[4];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[4].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[4].paint.isEnemy()) {
                bestMicro = microArray[4];
                break;
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[4].numAlliesCardinalAdjacent) break;
            if (microArray[4].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[4];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[4].minDistanceToAlly) break;
            if (microArray[4].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[4];
                break;
            }
        } while(false);

// For i = 5:
        do {
            if (!microArray[5].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[5];
                break;
            }
            

            if (rc.isActionReady() && health >= soldierHealthAttackThreshold || isRushing) {
                if (bestMicro.inTowerRange && !microArray[5].inTowerRange) break;
                if (!bestMicro.inTowerRange && microArray[5].inTowerRange) {
                    bestMicro = microArray[5];
                    break;
                }
            } else {
                if (!bestMicro.inTowerRange && microArray[5].inTowerRange) break;
                if (bestMicro.inTowerRange && !microArray[5].inTowerRange) {
                    bestMicro = microArray[5];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[5].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[5].paint.isAlly()) {
                bestMicro = microArray[5];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[5].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[5].paint.isEnemy()) {
                bestMicro = microArray[5];
                break;
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[5].numAlliesCardinalAdjacent) break;
            if (microArray[5].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[5];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[5].minDistanceToAlly) break;
            if (microArray[5].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[5];
                break;
            }
        } while(false);

// For i = 6:
        do {
            if (!microArray[6].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[6];
                break;
            }
            

            if (rc.isActionReady() && health >= soldierHealthAttackThreshold || isRushing) {
                if (bestMicro.inTowerRange && !microArray[6].inTowerRange) break;
                if (!bestMicro.inTowerRange && microArray[6].inTowerRange) {
                    bestMicro = microArray[6];
                    break;
                }
            } else {
                if (!bestMicro.inTowerRange && microArray[6].inTowerRange) break;
                if (bestMicro.inTowerRange && !microArray[6].inTowerRange) {
                    bestMicro = microArray[6];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[6].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[6].paint.isAlly()) {
                bestMicro = microArray[6];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[6].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[6].paint.isEnemy()) {
                bestMicro = microArray[6];
                break;
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[6].numAlliesCardinalAdjacent) break;
            if (microArray[6].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[6];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[6].minDistanceToAlly) break;
            if (microArray[6].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[6];
                break;
            }
        } while(false);

// For i = 7:
        do {
            if (!microArray[7].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[7];
                break;
            }

            if (rc.isActionReady() && health >= soldierHealthAttackThreshold || isRushing) {
                if (bestMicro.inTowerRange && !microArray[7].inTowerRange) break;
                if (!bestMicro.inTowerRange && microArray[7].inTowerRange) {
                    bestMicro = microArray[7];
                    break;
                }
            } else {
                if (!bestMicro.inTowerRange && microArray[7].inTowerRange) break;
                if (bestMicro.inTowerRange && !microArray[7].inTowerRange) {
                    bestMicro = microArray[7];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[7].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[7].paint.isAlly()) {
                bestMicro = microArray[7];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[7].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[7].paint.isEnemy()) {
                bestMicro = microArray[7];
                break;
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[7].numAlliesCardinalAdjacent) break;
            if (microArray[7].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[7];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[7].minDistanceToAlly) break;
            if (microArray[7].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[7];
                break;
            }
        } while(false);

// For i = 8:
        do {
            if (!microArray[8].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[8];
                break;
            }
            

            if (rc.isActionReady() && health >= soldierHealthAttackThreshold || isRushing) {
                if (bestMicro.inTowerRange && !microArray[8].inTowerRange) break;
                if (!bestMicro.inTowerRange && microArray[8].inTowerRange) {
                    bestMicro = microArray[8];
                    break;
                }
            } else {
                if (!bestMicro.inTowerRange && microArray[8].inTowerRange) break;
                if (bestMicro.inTowerRange && !microArray[8].inTowerRange) {
                    bestMicro = microArray[8];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[8].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[8].paint.isAlly()) {
                bestMicro = microArray[8];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[8].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[8].paint.isEnemy()) {
                bestMicro = microArray[8];
                break;
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[8].numAlliesCardinalAdjacent) break;
            if (microArray[8].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[8];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[8].minDistanceToAlly) break;
            if (microArray[8].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[8];
                break;
            }
        } while(false);
        return (bestMicro.passable) ? rc.getLocation().directionTo(bestMicro.loc) : Direction.CENTER;
    }

    //the micro method for soldiers
    public static Direction runMopperMicro(RobotController rc) {
        int actionRadiusSquared = UnitType.MOPPER.actionRadiusSquared;
        microInfo bestMicro = microArray[0];
//        for(microInfo m : microArray) {
//            if(m.equals(bestMicro)) continue;
//            //if one space is passable and the other is not, then passable is better
//            if(!m.passable) continue;
//            if(!bestMicro.passable) {
//                bestMicro = m;
//                continue;
//            }
//            //check if one space is in tower range and the other isn't
//            if(!bestMicro.inTowerRange && m.inTowerRange) continue;
//            if(bestMicro.inTowerRange && !m.inTowerRange) {
//                bestMicro = m;
//                continue;
//            }
//            //no matter the circumstance, avoid enemy paint at all costs
//            if(!bestMicro.paint.isEnemy() && m.paint.isEnemy()) continue;
//            if(bestMicro.paint.isEnemy() && !m.paint.isEnemy()) {
//                bestMicro = m;
//                continue;
//            }
//            //we want to try and mop
//            if(rc.isActionReady()) {
//                if(bestMicro.canMop && !m.canMop) continue;
//                if(!bestMicro.canMop && m.canMop) {
//                    bestMicro = m;
//                    continue;
//                }
//            }
//
//            //we want to try and stay on ally tiles
//            if(bestMicro.paint.isAlly() && !m.paint.isAlly()) continue;
//            if(!bestMicro.paint.isAlly() && m.paint.isAlly()) {
//                bestMicro = m;
//                continue;
//            }
//            //if we can act, lets try and be in stealing range of enemies
//            if(rc.isActionReady()) {
//                if(bestMicro.minDistanceToEnemy <= actionRadiusSquared && !(m.minDistanceToEnemy <= actionRadiusSquared)) continue;
//                if(!(bestMicro.minDistanceToEnemy <= actionRadiusSquared) && m.minDistanceToEnemy <= actionRadiusSquared) {
//                    bestMicro = m;
//                    continue;
//                }
//            }
//            //next, lets try and avoid being next to allies cardinally, so that we dont get swung at by moppers
//            if(bestMicro.numAlliesCardinalAdjacent < m.numAlliesCardinalAdjacent) continue;
//            if(m.numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
//                bestMicro = m;
//                continue;
//            }
//            //finally, lets try and be near allies
//            if(bestMicro.minDistanceToAlly < m.minDistanceToAlly) continue;
//            if(m.minDistanceToAlly < bestMicro.minDistanceToAlly) {
//                bestMicro = m;
//                continue;
//            }
//        }
//        for(int i = 1; i < 9; i++) {
//            do {
//                if (microArray[i].equals(bestMicro)) break;
//                //if one space is passable and the other is not, then passable is better
//                if (!microArray[i].passable) break;
//                if (!bestMicro.passable) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//                //check if one space is in tower range and the other isn't
//                if (!bestMicro.inTowerRange && microArray[i].inTowerRange) break;
//                if (bestMicro.inTowerRange && !microArray[i].inTowerRange) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//                //no matter the circumstance, avoid enemy paint at all costs
//                if (!bestMicro.paint.isEnemy() && microArray[i].paint.isEnemy()) break;
//                if (bestMicro.paint.isEnemy() && !microArray[i].paint.isEnemy()) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//                //we want to try and mop
//                if (rc.isActionReady()) {
//                    if (bestMicro.canMop && !microArray[i].canMop) break;
//                    if (!bestMicro.canMop && microArray[i].canMop) {
//                        bestMicro = microArray[i];
//                        break;
//                    }
//                }
//
//                //we want to try and stay on ally tiles
//                if (bestMicro.paint.isAlly() && !microArray[i].paint.isAlly()) break;
//                if (!bestMicro.paint.isAlly() && microArray[i].paint.isAlly()) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//                //if we can act, lets try and be in stealing range of enemies
//                if (rc.isActionReady()) {
//                    if (bestMicro.minDistanceToEnemy <= actionRadiusSquared && !(microArray[i].minDistanceToEnemy <= actionRadiusSquared))
//                        break;
//                    if (!(bestMicro.minDistanceToEnemy <= actionRadiusSquared) && microArray[i].minDistanceToEnemy <= actionRadiusSquared) {
//                        bestMicro = microArray[i];
//                        break;
//                    }
//                }
//                //next, lets try and avoid being next to allies cardinally, so that we dont get swung at by moppers
//                if (bestMicro.numAlliesCardinalAdjacent < microArray[i].numAlliesCardinalAdjacent) break;
//                if (microArray[i].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//                //finally, lets try and be near allies
//                if (bestMicro.minDistanceToAlly < microArray[i].minDistanceToAlly) break;
//                if (microArray[i].minDistanceToAlly < bestMicro.minDistanceToAlly) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//            } while(false);
//        }
        // Assuming 'bestMicro' is initialized somewhere before the loop

// For i = 1:
        do {
            if (microArray[1].equals(bestMicro)) break;

            if (!microArray[1].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[1];
                break;
            }

            if (!bestMicro.inTowerRange && microArray[1].inTowerRange) break;
            if (bestMicro.inTowerRange && !microArray[1].inTowerRange) {
                bestMicro = microArray[1];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[1].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[1].paint.isEnemy()) {
                bestMicro = microArray[1];
                break;
            }

            if (rc.isActionReady()) {
                if (bestMicro.canMop && !microArray[1].canMop) break;
                if (!bestMicro.canMop && microArray[1].canMop) {
                    bestMicro = microArray[1];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[1].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[1].paint.isAlly()) {
                bestMicro = microArray[1];
                break;
            }

            if (rc.isActionReady()) {
                if (bestMicro.minDistanceToEnemy <= actionRadiusSquared && !(microArray[1].minDistanceToEnemy <= actionRadiusSquared)) break;
                if (!(bestMicro.minDistanceToEnemy <= actionRadiusSquared) && microArray[1].minDistanceToEnemy <= actionRadiusSquared) {
                    bestMicro = microArray[1];
                    break;
                }
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[1].numAlliesCardinalAdjacent) break;
            if (microArray[1].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[1];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[1].minDistanceToAlly) break;
            if (microArray[1].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[1];
                break;
            }
        } while(false);

// For i = 2:
        do {
            if (microArray[2].equals(bestMicro)) break;

            if (!microArray[2].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[2];
                break;
            }

            if (!bestMicro.inTowerRange && microArray[2].inTowerRange) break;
            if (bestMicro.inTowerRange && !microArray[2].inTowerRange) {
                bestMicro = microArray[2];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[2].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[2].paint.isEnemy()) {
                bestMicro = microArray[2];
                break;
            }

            if (rc.isActionReady()) {
                if (bestMicro.canMop && !microArray[2].canMop) break;
                if (!bestMicro.canMop && microArray[2].canMop) {
                    bestMicro = microArray[2];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[2].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[2].paint.isAlly()) {
                bestMicro = microArray[2];
                break;
            }

            if (rc.isActionReady()) {
                if (bestMicro.minDistanceToEnemy <= actionRadiusSquared && !(microArray[2].minDistanceToEnemy <= actionRadiusSquared)) break;
                if (!(bestMicro.minDistanceToEnemy <= actionRadiusSquared) && microArray[2].minDistanceToEnemy <= actionRadiusSquared) {
                    bestMicro = microArray[2];
                    break;
                }
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[2].numAlliesCardinalAdjacent) break;
            if (microArray[2].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[2];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[2].minDistanceToAlly) break;
            if (microArray[2].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[2];
                break;
            }
        } while(false);

// For i = 3:
        do {
            if (microArray[3].equals(bestMicro)) break;

            if (!microArray[3].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[3];
                break;
            }

            if (!bestMicro.inTowerRange && microArray[3].inTowerRange) break;
            if (bestMicro.inTowerRange && !microArray[3].inTowerRange) {
                bestMicro = microArray[3];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[3].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[3].paint.isEnemy()) {
                bestMicro = microArray[3];
                break;
            }

            if (rc.isActionReady()) {
                if (bestMicro.canMop && !microArray[3].canMop) break;
                if (!bestMicro.canMop && microArray[3].canMop) {
                    bestMicro = microArray[3];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[3].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[3].paint.isAlly()) {
                bestMicro = microArray[3];
                break;
            }

            if (rc.isActionReady()) {
                if (bestMicro.minDistanceToEnemy <= actionRadiusSquared && !(microArray[3].minDistanceToEnemy <= actionRadiusSquared)) break;
                if (!(bestMicro.minDistanceToEnemy <= actionRadiusSquared) && microArray[3].minDistanceToEnemy <= actionRadiusSquared) {
                    bestMicro = microArray[3];
                    break;
                }
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[3].numAlliesCardinalAdjacent) break;
            if (microArray[3].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[3];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[3].minDistanceToAlly) break;
            if (microArray[3].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[3];
                break;
            }
        } while(false);

// For i = 4:
        do {
            if (microArray[4].equals(bestMicro)) break;

            if (!microArray[4].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[4];
                break;
            }

            if (!bestMicro.inTowerRange && microArray[4].inTowerRange) break;
            if (bestMicro.inTowerRange && !microArray[4].inTowerRange) {
                bestMicro = microArray[4];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[4].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[4].paint.isEnemy()) {
                bestMicro = microArray[4];
                break;
            }

            if (rc.isActionReady()) {
                if (bestMicro.canMop && !microArray[4].canMop) break;
                if (!bestMicro.canMop && microArray[4].canMop) {
                    bestMicro = microArray[4];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[4].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[4].paint.isAlly()) {
                bestMicro = microArray[4];
                break;
            }

            if (rc.isActionReady()) {
                if (bestMicro.minDistanceToEnemy <= actionRadiusSquared && !(microArray[4].minDistanceToEnemy <= actionRadiusSquared)) break;
                if (!(bestMicro.minDistanceToEnemy <= actionRadiusSquared) && microArray[4].minDistanceToEnemy <= actionRadiusSquared) {
                    bestMicro = microArray[4];
                    break;
                }
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[4].numAlliesCardinalAdjacent) break;
            if (microArray[4].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[4];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[4].minDistanceToAlly) break;
            if (microArray[4].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[4];
                break;
            }
        } while(false);

// For i = 5:
        do {
            if (microArray[5].equals(bestMicro)) break;

            if (!microArray[5].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[5];
                break;
            }

            if (!bestMicro.inTowerRange && microArray[5].inTowerRange) break;
            if (bestMicro.inTowerRange && !microArray[5].inTowerRange) {
                bestMicro = microArray[5];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[5].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[5].paint.isEnemy()) {
                bestMicro = microArray[5];
                break;
            }

            if (rc.isActionReady()) {
                if (bestMicro.canMop && !microArray[5].canMop) break;
                if (!bestMicro.canMop && microArray[5].canMop) {
                    bestMicro = microArray[5];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[5].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[5].paint.isAlly()) {
                bestMicro = microArray[5];
                break;
            }

            if (rc.isActionReady()) {
                if (bestMicro.minDistanceToEnemy <= actionRadiusSquared && !(microArray[5].minDistanceToEnemy <= actionRadiusSquared)) break;
                if (!(bestMicro.minDistanceToEnemy <= actionRadiusSquared) && microArray[5].minDistanceToEnemy <= actionRadiusSquared) {
                    bestMicro = microArray[5];
                    break;
                }
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[5].numAlliesCardinalAdjacent) break;
            if (microArray[5].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[5];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[5].minDistanceToAlly) break;
            if (microArray[5].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[5];
                break;
            }
        } while(false);

// Repeat the same unrolling pattern for i = 6, i = 7, and i = 8 as above.
        // For i = 6:
        do {
            if (microArray[6].equals(bestMicro)) break;

            if (!microArray[6].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[6];
                break;
            }

            if (!bestMicro.inTowerRange && microArray[6].inTowerRange) break;
            if (bestMicro.inTowerRange && !microArray[6].inTowerRange) {
                bestMicro = microArray[6];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[6].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[6].paint.isEnemy()) {
                bestMicro = microArray[6];
                break;
            }

            if (rc.isActionReady()) {
                if (bestMicro.canMop && !microArray[6].canMop) break;
                if (!bestMicro.canMop && microArray[6].canMop) {
                    bestMicro = microArray[6];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[6].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[6].paint.isAlly()) {
                bestMicro = microArray[6];
                break;
            }

            if (rc.isActionReady()) {
                if (bestMicro.minDistanceToEnemy <= actionRadiusSquared && !(microArray[6].minDistanceToEnemy <= actionRadiusSquared)) break;
                if (!(bestMicro.minDistanceToEnemy <= actionRadiusSquared) && microArray[6].minDistanceToEnemy <= actionRadiusSquared) {
                    bestMicro = microArray[6];
                    break;
                }
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[6].numAlliesCardinalAdjacent) break;
            if (microArray[6].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[6];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[6].minDistanceToAlly) break;
            if (microArray[6].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[6];
                break;
            }
        } while(false);

// For i = 7:
        do {
            if (microArray[7].equals(bestMicro)) break;

            if (!microArray[7].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[7];
                break;
            }

            if (!bestMicro.inTowerRange && microArray[7].inTowerRange) break;
            if (bestMicro.inTowerRange && !microArray[7].inTowerRange) {
                bestMicro = microArray[7];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[7].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[7].paint.isEnemy()) {
                bestMicro = microArray[7];
                break;
            }

            if (rc.isActionReady()) {
                if (bestMicro.canMop && !microArray[7].canMop) break;
                if (!bestMicro.canMop && microArray[7].canMop) {
                    bestMicro = microArray[7];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[7].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[7].paint.isAlly()) {
                bestMicro = microArray[7];
                break;
            }

            if (rc.isActionReady()) {
                if (bestMicro.minDistanceToEnemy <= actionRadiusSquared && !(microArray[7].minDistanceToEnemy <= actionRadiusSquared)) break;
                if (!(bestMicro.minDistanceToEnemy <= actionRadiusSquared) && microArray[7].minDistanceToEnemy <= actionRadiusSquared) {
                    bestMicro = microArray[7];
                    break;
                }
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[7].numAlliesCardinalAdjacent) break;
            if (microArray[7].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[7];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[7].minDistanceToAlly) break;
            if (microArray[7].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[7];
                break;
            }
        } while(false);

// For i = 8:
        do {
            if (microArray[8].equals(bestMicro)) break;

            if (!microArray[8].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[8];
                break;
            }

            if (!bestMicro.inTowerRange && microArray[8].inTowerRange) break;
            if (bestMicro.inTowerRange && !microArray[8].inTowerRange) {
                bestMicro = microArray[8];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[8].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[8].paint.isEnemy()) {
                bestMicro = microArray[8];
                break;
            }

            if (rc.isActionReady()) {
                if (bestMicro.canMop && !microArray[8].canMop) break;
                if (!bestMicro.canMop && microArray[8].canMop) {
                    bestMicro = microArray[8];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[8].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[8].paint.isAlly()) {
                bestMicro = microArray[8];
                break;
            }

            if (rc.isActionReady()) {
                if (bestMicro.minDistanceToEnemy <= actionRadiusSquared && !(microArray[8].minDistanceToEnemy <= actionRadiusSquared)) break;
                if (!(bestMicro.minDistanceToEnemy <= actionRadiusSquared) && microArray[8].minDistanceToEnemy <= actionRadiusSquared) {
                    bestMicro = microArray[8];
                    break;
                }
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[8].numAlliesCardinalAdjacent) break;
            if (microArray[8].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[8];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[8].minDistanceToAlly) break;
            if (microArray[8].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[8];
                break;
            }
        } while(false);

        return (bestMicro.passable) ? rc.getLocation().directionTo(bestMicro.loc) : Direction.CENTER;
    }

    //the micro method for soldiers
    public static Direction runSplasherMicro(RobotController rc) {
        int actionRadiusSquared = UnitType.SPLASHER.actionRadiusSquared;
        int health = rc.getHealth();
        microInfo bestMicro = microArray[0];
//        for(microInfo m : microArray) {
//            if(m.equals(bestMicro)) continue;
//            //if one space is passable and the other is not, then passable is better
//            if(!m.passable) continue;
//            if(!bestMicro.passable) {
//                bestMicro = m;
//                continue;
//            }
//            //depending on our health, and whether we have nearby allies, we may be fine moving into tower range
//            if (rc.isActionReady() && health >= splasherHealthAttackThreshold || isRushing) {
//                //we are fine being in tower range as long as we can attack, because we have enough health
//                if(bestMicro.inTowerRange && !m.inTowerRange) continue;
//                if(!bestMicro.inTowerRange && m.inTowerRange) {
//                    bestMicro = m;
//                    continue;
//                }
//            }
//            //definitely don't be in tower range!
//            else {
//                //check if one space is in tower range and the other isn't
//                if(!bestMicro.inTowerRange && m.inTowerRange) continue;
//                if(bestMicro.inTowerRange && !m.inTowerRange) {
//                    bestMicro = m;
//                    continue;
//                }
//
//            }
//            //ty to be in a position to attack an enemy space
//            if(rc.isActionReady()) {
//                if(bestMicro.canSplash && !m.canSplash) continue;
//                if(!bestMicro.canSplash && m.canSplash) {
//                    bestMicro = m;
//                    continue;
//                }
//            }
//            //regardless of action readiness, the next considerations are unified:
//            //we prioritize allied paint over neutral paint over enemy paint
//            if(bestMicro.paint.isAlly() && !m.paint.isAlly()) continue;
//            if(!bestMicro.paint.isAlly() && m.paint.isAlly()) {
//                bestMicro = m;
//                continue;
//            }
//
//            if(!bestMicro.paint.isEnemy() && m.paint.isEnemy()) continue;
//            if(bestMicro.paint.isEnemy() && !m.paint.isEnemy()) {
//                bestMicro = m;
//                continue;
//            }
//
//            //next, lets try and avoid being next to allies cardinally, so that we dont get swung at by moppers
//            if(bestMicro.numAlliesCardinalAdjacent < m.numAlliesCardinalAdjacent) continue;
//            if(m.numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
//                bestMicro = m;
//                continue;
//            }
//            //next, lets try and be near allies
//            if(bestMicro.minDistanceToAlly < m.minDistanceToAlly) continue;
//            if(m.minDistanceToAlly < bestMicro.minDistanceToAlly) {
//                bestMicro = m;
//                continue;
//            }
//
//        }
//        for(int i = 1; i < 9; i++) {
//            do {
//                //if one space is passable and the other is not, then passable is better
//                if (!microArray[i].passable) break;
//                if (!bestMicro.passable) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//                //depending on our health, and whether we have nearby allies, we may be fine moving into tower range
//                if (rc.isActionReady() && health >= splasherHealthAttackThreshold || isRushing) {
//                    //we are fine being in tower range as long as we can attack, because we have enough health
//                    if (bestMicro.inTowerRange && !microArray[i].inTowerRange) break;
//                    if (!bestMicro.inTowerRange && microArray[i].inTowerRange) {
//                        bestMicro = microArray[i];
//                        break;
//                    }
//                }
//                //definitely don't be in tower range!
//                else {
//                    //check if one space is in tower range and the other isn't
//                    if (!bestMicro.inTowerRange && microArray[i].inTowerRange) break;
//                    if (bestMicro.inTowerRange && !microArray[i].inTowerRange) {
//                        bestMicro = microArray[i];
//                        break;
//                    }
//
//                }
//                //ty to be in a position to attack an enemy space
//                if (rc.isActionReady()) {
//                    if (bestMicro.canSplash && !microArray[i].canSplash) break;
//                    if (!bestMicro.canSplash && microArray[i].canSplash) {
//                        bestMicro = microArray[i];
//                        break;
//                    }
//                }
//                //regardless of action readiness, the next considerations are unified:
//                //we prioritize allied paint over neutral paint over enemy paint
//                if (bestMicro.paint.isAlly() && !microArray[i].paint.isAlly()) break;
//                if (!bestMicro.paint.isAlly() && microArray[i].paint.isAlly()) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//
//                if (!bestMicro.paint.isEnemy() && microArray[i].paint.isEnemy()) break;
//                if (bestMicro.paint.isEnemy() && !microArray[i].paint.isEnemy()) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//
//                //next, lets try and avoid being next to allies cardinally, so that we dont get swung at by moppers
//                if (bestMicro.numAlliesCardinalAdjacent < microArray[i].numAlliesCardinalAdjacent) break;
//                if (microArray[i].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//                //next, lets try and be near allies
//                if (bestMicro.minDistanceToAlly < microArray[i].minDistanceToAlly) break;
//                if (microArray[i].minDistanceToAlly < bestMicro.minDistanceToAlly) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//            } while(false);
//        }
        // For i = 1:
        do {
            if (!microArray[1].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[1];
                break;
            }
            if (rc.isActionReady() && health >= splasherHealthAttackThreshold || isRushing) {
                if (bestMicro.inTowerRange && !microArray[1].inTowerRange) break;
                if (!bestMicro.inTowerRange && microArray[1].inTowerRange) {
                    bestMicro = microArray[1];
                    break;
                }
            } else {
                if (!bestMicro.inTowerRange && microArray[1].inTowerRange) break;
                if (bestMicro.inTowerRange && !microArray[1].inTowerRange) {
                    bestMicro = microArray[1];
                    break;
                }
            }

            if (rc.isActionReady()) {
                if (bestMicro.canSplash && !microArray[1].canSplash) break;
                if (!bestMicro.canSplash && microArray[1].canSplash) {
                    bestMicro = microArray[1];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[1].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[1].paint.isAlly()) {
                bestMicro = microArray[1];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[1].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[1].paint.isEnemy()) {
                bestMicro = microArray[1];
                break;
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[1].numAlliesCardinalAdjacent) break;
            if (microArray[1].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[1];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[1].minDistanceToAlly) break;
            if (microArray[1].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[1];
                break;
            }
        } while(false);

// For i = 2:
        do {
            if (!microArray[2].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[2];
                break;
            }
            if (rc.isActionReady() && health >= splasherHealthAttackThreshold || isRushing) {
                if (bestMicro.inTowerRange && !microArray[2].inTowerRange) break;
                if (!bestMicro.inTowerRange && microArray[2].inTowerRange) {
                    bestMicro = microArray[2];
                    break;
                }
            } else {
                if (!bestMicro.inTowerRange && microArray[2].inTowerRange) break;
                if (bestMicro.inTowerRange && !microArray[2].inTowerRange) {
                    bestMicro = microArray[2];
                    break;
                }
            }

            if (rc.isActionReady()) {
                if (bestMicro.canSplash && !microArray[2].canSplash) break;
                if (!bestMicro.canSplash && microArray[2].canSplash) {
                    bestMicro = microArray[2];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[2].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[2].paint.isAlly()) {
                bestMicro = microArray[2];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[2].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[2].paint.isEnemy()) {
                bestMicro = microArray[2];
                break;
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[2].numAlliesCardinalAdjacent) break;
            if (microArray[2].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[2];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[2].minDistanceToAlly) break;
            if (microArray[2].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[2];
                break;
            }
        } while(false);

// For i = 3:
        do {
            if (!microArray[3].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[3];
                break;
            }
            if (rc.isActionReady() && health >= splasherHealthAttackThreshold || isRushing) {
                if (bestMicro.inTowerRange && !microArray[3].inTowerRange) break;
                if (!bestMicro.inTowerRange && microArray[3].inTowerRange) {
                    bestMicro = microArray[3];
                    break;
                }
            } else {
                if (!bestMicro.inTowerRange && microArray[3].inTowerRange) break;
                if (bestMicro.inTowerRange && !microArray[3].inTowerRange) {
                    bestMicro = microArray[3];
                    break;
                }
            }

            if (rc.isActionReady()) {
                if (bestMicro.canSplash && !microArray[3].canSplash) break;
                if (!bestMicro.canSplash && microArray[3].canSplash) {
                    bestMicro = microArray[3];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[3].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[3].paint.isAlly()) {
                bestMicro = microArray[3];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[3].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[3].paint.isEnemy()) {
                bestMicro = microArray[3];
                break;
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[3].numAlliesCardinalAdjacent) break;
            if (microArray[3].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[3];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[3].minDistanceToAlly) break;
            if (microArray[3].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[3];
                break;
            }
        } while(false);

// For i = 4:
        do {
            if (!microArray[4].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[4];
                break;
            }
            if (rc.isActionReady() && health >= splasherHealthAttackThreshold || isRushing) {
                if (bestMicro.inTowerRange && !microArray[4].inTowerRange) break;
                if (!bestMicro.inTowerRange && microArray[4].inTowerRange) {
                    bestMicro = microArray[4];
                    break;
                }
            } else {
                if (!bestMicro.inTowerRange && microArray[4].inTowerRange) break;
                if (bestMicro.inTowerRange && !microArray[4].inTowerRange) {
                    bestMicro = microArray[4];
                    break;
                }
            }

            if (rc.isActionReady()) {
                if (bestMicro.canSplash && !microArray[4].canSplash) break;
                if (!bestMicro.canSplash && microArray[4].canSplash) {
                    bestMicro = microArray[4];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[4].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[4].paint.isAlly()) {
                bestMicro = microArray[4];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[4].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[4].paint.isEnemy()) {
                bestMicro = microArray[4];
                break;
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[4].numAlliesCardinalAdjacent) break;
            if (microArray[4].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[4];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[4].minDistanceToAlly) break;
            if (microArray[4].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[4];
                break;
            }
        } while(false);

// Repeat similar unrolling for i = 5, i = 6, i = 7, and i = 8
        // For i = 5:
        do {
            if (!microArray[5].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[5];
                break;
            }
            if (rc.isActionReady() && health >= splasherHealthAttackThreshold || isRushing) {
                if (bestMicro.inTowerRange && !microArray[5].inTowerRange) break;
                if (!bestMicro.inTowerRange && microArray[5].inTowerRange) {
                    bestMicro = microArray[5];
                    break;
                }
            } else {
                if (!bestMicro.inTowerRange && microArray[5].inTowerRange) break;
                if (bestMicro.inTowerRange && !microArray[5].inTowerRange) {
                    bestMicro = microArray[5];
                    break;
                }
            }

            if (rc.isActionReady()) {
                if (bestMicro.canSplash && !microArray[5].canSplash) break;
                if (!bestMicro.canSplash && microArray[5].canSplash) {
                    bestMicro = microArray[5];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[5].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[5].paint.isAlly()) {
                bestMicro = microArray[5];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[5].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[5].paint.isEnemy()) {
                bestMicro = microArray[5];
                break;
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[5].numAlliesCardinalAdjacent) break;
            if (microArray[5].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[5];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[5].minDistanceToAlly) break;
            if (microArray[5].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[5];
                break;
            }
        } while(false);

// For i = 6:
        do {
            if (!microArray[6].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[6];
                break;
            }
            if (rc.isActionReady() && health >= splasherHealthAttackThreshold || isRushing) {
                if (bestMicro.inTowerRange && !microArray[6].inTowerRange) break;
                if (!bestMicro.inTowerRange && microArray[6].inTowerRange) {
                    bestMicro = microArray[6];
                    break;
                }
            } else {
                if (!bestMicro.inTowerRange && microArray[6].inTowerRange) break;
                if (bestMicro.inTowerRange && !microArray[6].inTowerRange) {
                    bestMicro = microArray[6];
                    break;
                }
            }

            if (rc.isActionReady()) {
                if (bestMicro.canSplash && !microArray[6].canSplash) break;
                if (!bestMicro.canSplash && microArray[6].canSplash) {
                    bestMicro = microArray[6];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[6].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[6].paint.isAlly()) {
                bestMicro = microArray[6];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[6].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[6].paint.isEnemy()) {
                bestMicro = microArray[6];
                break;
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[6].numAlliesCardinalAdjacent) break;
            if (microArray[6].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[6];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[6].minDistanceToAlly) break;
            if (microArray[6].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[6];
                break;
            }
        } while(false);

// For i = 7:
        do {
            if (!microArray[7].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[7];
                break;
            }
            if (rc.isActionReady() && health >= splasherHealthAttackThreshold || isRushing) {
                if (bestMicro.inTowerRange && !microArray[7].inTowerRange) break;
                if (!bestMicro.inTowerRange && microArray[7].inTowerRange) {
                    bestMicro = microArray[7];
                    break;
                }
            } else {
                if (!bestMicro.inTowerRange && microArray[7].inTowerRange) break;
                if (bestMicro.inTowerRange && !microArray[7].inTowerRange) {
                    bestMicro = microArray[7];
                    break;
                }
            }

            if (rc.isActionReady()) {
                if (bestMicro.canSplash && !microArray[7].canSplash) break;
                if (!bestMicro.canSplash && microArray[7].canSplash) {
                    bestMicro = microArray[7];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[7].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[7].paint.isAlly()) {
                bestMicro = microArray[7];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[7].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[7].paint.isEnemy()) {
                bestMicro = microArray[7];
                break;
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[7].numAlliesCardinalAdjacent) break;
            if (microArray[7].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[7];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[7].minDistanceToAlly) break;
            if (microArray[7].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[7];
                break;
            }
        } while(false);

// For i = 8:
        do {
            if (!microArray[8].passable) break;
            if (!bestMicro.passable) {
                bestMicro = microArray[8];
                break;
            }
            if (rc.isActionReady() && health >= splasherHealthAttackThreshold || isRushing) {
                if (bestMicro.inTowerRange && !microArray[8].inTowerRange) break;
                if (!bestMicro.inTowerRange && microArray[8].inTowerRange) {
                    bestMicro = microArray[8];
                    break;
                }
            } else {
                if (!bestMicro.inTowerRange && microArray[8].inTowerRange) break;
                if (bestMicro.inTowerRange && !microArray[8].inTowerRange) {
                    bestMicro = microArray[8];
                    break;
                }
            }

            if (rc.isActionReady()) {
                if (bestMicro.canSplash && !microArray[8].canSplash) break;
                if (!bestMicro.canSplash && microArray[8].canSplash) {
                    bestMicro = microArray[8];
                    break;
                }
            }

            if (bestMicro.paint.isAlly() && !microArray[8].paint.isAlly()) break;
            if (!bestMicro.paint.isAlly() && microArray[8].paint.isAlly()) {
                bestMicro = microArray[8];
                break;
            }

            if (!bestMicro.paint.isEnemy() && microArray[8].paint.isEnemy()) break;
            if (bestMicro.paint.isEnemy() && !microArray[8].paint.isEnemy()) {
                bestMicro = microArray[8];
                break;
            }

            if (bestMicro.numAlliesCardinalAdjacent < microArray[8].numAlliesCardinalAdjacent) break;
            if (microArray[8].numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = microArray[8];
                break;
            }

            if (bestMicro.minDistanceToAlly < microArray[8].minDistanceToAlly) break;
            if (microArray[8].minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = microArray[8];
                break;
            }
        } while(false);


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
        boolean checkForMop = rc.getType() == UnitType.MOPPER;
        boolean checkForSplash = rc.getType() == UnitType.SPLASHER;
        //loop before being unrolled, in case changes need to be made
//        for(int dx = -1; dx <= 1; dx++) {
//            if(curX + dx >= 0 && curX + dx  <= mapWidth) {
//                for (int dy = -1; dy <= 1; dy++) {
//                    if(curY + dy >= 0 && curY + dy <= mapHeight) {
//                        MapLocation newLoc = new MapLocation(curX + dx, curY + dy);
//                        if(rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
//                        else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
//                        totalFilled++;
//                    }
//                }
//            }
//        }
        if (curX + -1 >= 0 && curX + -1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + -1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 0);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
                totalFilled++;
            }
        }
        if (curX + 0 >= 0 && curX + 0 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + -1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + 0);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + 1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
                totalFilled++;
            }
        }
        if (curX + 1 >= 0 && curX + 1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + -1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 0);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 1);
                if (rc.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
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
