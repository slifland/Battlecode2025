package Version7;


import battlecode.common.*;
import static Version7.RobotPlayer.*;

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
        for(microInfo m : microArray) {
            if(m.equals(bestMicro)) continue;
            //if one space is passable and the other is not, then passable is better
            if(!m.passable) continue;
            if(m.passable && !bestMicro.passable) {
                bestMicro = m;
                continue;
            }
            if(!bestMicro.passable) continue;
            //depending on our health, and whether we have nearby allies, we may be fine moving into tower range
            if (rc.isActionReady() && health >= soldierHealthAttackThreshold || isRushing) {
                //we are fine being in tower range as long as we can attack, because we have enough health
                if(bestMicro.inTowerRange && !m.inTowerRange) continue;
                if(!bestMicro.inTowerRange && m.inTowerRange) {
                    bestMicro = m;
                    continue;
                }
            }
            //definitely don't be in tower range!
            else {
                //check if one space is in tower range and the other isn't
                if(!bestMicro.inTowerRange && m.inTowerRange) continue;
                if(bestMicro.inTowerRange && !m.inTowerRange) {
                    bestMicro = m;
                    continue;
                }

            }
            //regardless of action readiness, the next considerations are unified:
            //we prioritize allied paint over neutral paint over enemy paint
            if(bestMicro.paint.isAlly() && !m.paint.isAlly()) continue;
            if(!bestMicro.paint.isAlly() && m.paint.isAlly()) {
                bestMicro = m;
                continue;
            }

            if(!bestMicro.paint.isEnemy() && m.paint.isEnemy()) continue;
            if(bestMicro.paint.isEnemy() && !m.paint.isEnemy()) {
                bestMicro = m;
                continue;
            }

            //next, lets try and avoid being next to allies cardinally, so that we dont get swung at by moppers
            if(bestMicro.numAlliesCardinalAdjacent < m.numAlliesCardinalAdjacent) continue;
            if(m.numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = m;
                continue;
            }
            //next, lets try and be near allies
            if(bestMicro.minDistanceToAlly < m.minDistanceToAlly) continue;
            if(m.minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = m;
                continue;
            }
            //at this point, nothing more to consider - unclear how minDistance to enemy would affect soldier micro
        }
        return (bestMicro.passable) ? rc.getLocation().directionTo(bestMicro.loc) : Direction.CENTER;
    }

    //the micro method for soldiers
    public static Direction runMopperMicro(RobotController rc) {
        int actionRadiusSquared = UnitType.MOPPER.actionRadiusSquared;
        microInfo bestMicro = microArray[0];
        for(microInfo m : microArray) {
            if(m.equals(bestMicro)) continue;
            //if one space is passable and the other is not, then passable is better
            if(!m.passable) continue;
            if(!bestMicro.passable) {
                bestMicro = m;
                continue;
            }
            //check if one space is in tower range and the other isn't
            if(!bestMicro.inTowerRange && m.inTowerRange) continue;
            if(bestMicro.inTowerRange && !m.inTowerRange) {
                bestMicro = m;
                continue;
            }
            //no matter the circumstance, avoid enemy paint at all costs
            if(!bestMicro.paint.isEnemy() && m.paint.isEnemy()) continue;
            if(bestMicro.paint.isEnemy() && !m.paint.isEnemy()) {
                bestMicro = m;
                continue;
            }
            //we want to try and mop
            if(rc.isActionReady()) {
                if(bestMicro.canMop && !m.canMop) continue;
                if(!bestMicro.canMop && m.canMop) {
                    bestMicro = m;
                    continue;
                }
            }

            //we want to try and stay on ally tiles
            if(bestMicro.paint.isAlly() && !m.paint.isAlly()) continue;
            if(!bestMicro.paint.isAlly() && m.paint.isAlly()) {
                bestMicro = m;
                continue;
            }
            //if we can act, lets try and be in stealing range of enemies
            if(rc.isActionReady()) {
                if(bestMicro.minDistanceToEnemy <= actionRadiusSquared && !(m.minDistanceToEnemy <= actionRadiusSquared)) continue;
                if(!(bestMicro.minDistanceToEnemy <= actionRadiusSquared) && m.minDistanceToEnemy <= actionRadiusSquared) {
                    bestMicro = m;
                    continue;
                }
            }
            //next, lets try and avoid being next to allies cardinally, so that we dont get swung at by moppers
            if(bestMicro.numAlliesCardinalAdjacent < m.numAlliesCardinalAdjacent) continue;
            if(m.numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = m;
                continue;
            }
            //finally, lets try and be near allies
            if(bestMicro.minDistanceToAlly < m.minDistanceToAlly) continue;
            if(m.minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = m;
                continue;
            }
        }
        return (bestMicro.passable) ? rc.getLocation().directionTo(bestMicro.loc) : Direction.CENTER;
    }

    //the micro method for soldiers
    public static Direction runSplasherMicro(RobotController rc) {
        int actionRadiusSquared = UnitType.SPLASHER.actionRadiusSquared;
        int health = rc.getHealth();
        microInfo bestMicro = microArray[0];
        for(microInfo m : microArray) {
            if(m.equals(bestMicro)) continue;
            //if one space is passable and the other is not, then passable is better
            if(!m.passable) continue;
            if(!bestMicro.passable) {
                bestMicro = m;
                continue;
            }
            //depending on our health, and whether we have nearby allies, we may be fine moving into tower range
            if (rc.isActionReady() && health >= splasherHealthAttackThreshold || isRushing) {
                //we are fine being in tower range as long as we can attack, because we have enough health
                if(bestMicro.inTowerRange && !m.inTowerRange) continue;
                if(!bestMicro.inTowerRange && m.inTowerRange) {
                    bestMicro = m;
                    continue;
                }
            }
            //definitely don't be in tower range!
            else {
                //check if one space is in tower range and the other isn't
                if(!bestMicro.inTowerRange && m.inTowerRange) continue;
                if(bestMicro.inTowerRange && !m.inTowerRange) {
                    bestMicro = m;
                    continue;
                }

            }
            //ty to be in a position to attack an enemy space
            if(rc.isActionReady()) {
                if(bestMicro.canSplash && !m.canSplash) continue;
                if(!bestMicro.canSplash && m.canSplash) {
                    bestMicro = m;
                    continue;
                }
            }
            //regardless of action readiness, the next considerations are unified:
            //we prioritize allied paint over neutral paint over enemy paint
            if(bestMicro.paint.isAlly() && !m.paint.isAlly()) continue;
            if(!bestMicro.paint.isAlly() && m.paint.isAlly()) {
                bestMicro = m;
                continue;
            }

            if(!bestMicro.paint.isEnemy() && m.paint.isEnemy()) continue;
            if(bestMicro.paint.isEnemy() && !m.paint.isEnemy()) {
                bestMicro = m;
                continue;
            }

            //next, lets try and avoid being next to allies cardinally, so that we dont get swung at by moppers
            if(bestMicro.numAlliesCardinalAdjacent < m.numAlliesCardinalAdjacent) continue;
            if(m.numAlliesCardinalAdjacent < bestMicro.numAlliesCardinalAdjacent) {
                bestMicro = m;
                continue;
            }
            //next, lets try and be near allies
            if(bestMicro.minDistanceToAlly < m.minDistanceToAlly) continue;
            if(m.minDistanceToAlly < bestMicro.minDistanceToAlly) {
                bestMicro = m;
                continue;
            }

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
        boolean checkForMop = rc.getType() == UnitType.MOPPER;
        boolean checkForSplash = rc.getType() == UnitType.SPLASHER;
        //loop before being unrolled, in case changes need to be made
//        for(int dx = -1; dx <= 1; dx++) {
//            if(curX + dx >= 0 && curX + dx  <= mapWidth) {
//                for (int dy = -1; dy <= 1; dy++) {
//                    if(curY + dy >= 0 && curY + dy <= mapHeight) {
//                        MapLocation newLoc = new MapLocation(curX + dx, curY + dy);
//                        if(rc.senseRobotAtLocation(newLoc) != null) microArray[totalFilled] = new microInfo();
//                        else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
//                        totalFilled++;
//                    }
//                }
//            }
//        }
        if (curX + -1 >= 0 && curX + -1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + -1);
                if (rc.senseRobotAtLocation(newLoc) != null) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 0);
                if (rc.senseRobotAtLocation(newLoc) != null) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 1);
                if (rc.senseRobotAtLocation(newLoc) != null) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
                totalFilled++;
            }
        }
        if (curX + 0 >= 0 && curX + 0 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + -1);
                if (rc.senseRobotAtLocation(newLoc) != null) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + 0);
                if (rc.senseRobotAtLocation(newLoc) != null) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + 1);
                if (rc.senseRobotAtLocation(newLoc) != null) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
                totalFilled++;
            }
        }
        if (curX + 1 >= 0 && curX + 1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + -1);
                if (rc.senseRobotAtLocation(newLoc) != null) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 0);
                if (rc.senseRobotAtLocation(newLoc) != null) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(rc.senseMapInfo(newLoc), checkForMop, checkForSplash);
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 1);
                if (rc.senseRobotAtLocation(newLoc) != null) microArray[totalFilled] = new microInfo();
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
