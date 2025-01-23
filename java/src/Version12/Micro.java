package Version12;

import battlecode.common.*;

import java.util.Arrays;

import static Version12.RobotPlayer.*;

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
            if(loc.isWithinDistanceSquared(robot.getLocation(), 2)) {
                adjacentAllies++;
            }
        }
        paintLoss = switch(paint) {
            case ENEMY_PRIMARY, ENEMY_SECONDARY -> 2 + adjacentAllies;
            case ALLY_PRIMARY, ALLY_SECONDARY-> adjacentAllies;
            case EMPTY -> 1 + adjacentAllies;
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
    private static microInfo[] microArray;
    private static boolean isRushing;
    //the method any robot will use to interact with micro - directs the robot to the correct micro method
    public static Direction runMicro( boolean isRushing) throws GameActionException {
        if(!staticRC.isMovementReady()) return Direction.CENTER;
        populateMicroArray();
        Micro.isRushing = isRushing;
        return switch (staticRC.getType()) {
            case SOLDIER -> runSoldierMicro();
            default -> null;
        };
    }

    //the method any robot will use to interact with micro - directs the robot to the correct micro method
    public static Direction runMicro() throws GameActionException {
        if(!staticRC.isMovementReady()) return Direction.CENTER;
        populateMicroArray();
        Micro.isRushing = false;
        return switch (staticRC.getType()) {
            case SOLDIER -> runSoldierMicro();
            default -> Direction.CENTER;
        };
    }
    
    //attempts to find the best place near the ruin to fill in, and also move to the best adjacent square
    public static void ruinBuildingMicro( MapLocation ruin, boolean[][] desiredPattern, MapInfo[] tilesToFill) throws GameActionException {
        staticRC.setIndicatorString("Building a ruin!" + ruin.toString());
        int curDist = staticRC.getLocation().distanceSquaredTo(ruin);
        MapLocation bestAttack = null;
        if(staticRC.isActionReady()) {
            bestAttack = bestRuinAttack(desiredPattern, tilesToFill, ruin);
            if (bestAttack != null && staticRC.canAttack(bestAttack)) staticRC.attack(bestAttack, Utilities.getColorFromCustomPattern(bestAttack, desiredPattern, ruin));
        }
        if(bestAttack == null && staticRC.getChips() > 1000 && staticRC.isMovementReady()) {
            Direction dir = Pathfinding.bugBFS(ruin);
            if(staticRC.canMove(dir)) staticRC.move(dir);
            Soldier.attemptCompleteTowerPattern(ruin);
            return;
        }
        if(staticRC.isMovementReady()) {
            if(curDist <= 8)
                populateMicroArrayRuin(ruin);
            else
                populateMicroArray();
            microInfo bestMicro = new microInfo(staticRC.senseMapInfo(staticRC.getLocation()));
            for(int i = 0; i < 9; i++) {
                do {
                    if(bestMicro.equals(microArray[i])) continue;
                    //if one space is passable and the other is not, then passable is better
                    if (!microArray[i].passable) break;
                    if (!bestMicro.passable) {
                        bestMicro = microArray[i];
                        break;
                    }

                    if(curDist > 8) {
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

                    if (rng.nextInt(2) == 0) {
                        bestMicro = microArray[i];
                    }
                } while(false);
            }
            if(bestMicro.passable && staticRC.canMove(staticRC.getLocation().directionTo(bestMicro.loc))) {
                staticRC.move(staticRC.getLocation().directionTo(bestMicro.loc));
            }
        }
        if(staticRC.isActionReady() && bestAttack != null) {
            if(staticRC.canAttack(bestAttack)) staticRC.attack(bestAttack, Utilities.getColorFromCustomPattern(bestAttack, desiredPattern, ruin));
        }
    }

    //finds and moves to the best square for refilling
    //differentiates if we are:
    //1. able to refill this turn (actionReady AND tower has paint)
    //2. not able to refill this turn (not action ready, tower doesnt have paint)
    //in case 1, we should move to the best spot available next to the paint tower
    //in case 2, we move to the best spot within radius squared 8 of the paint tower
    public static void refillingMicro( MapLocation refillLoc) throws GameActionException {
        if(!staticRC.canSenseRobotAtLocation(refillLoc)) {
            return;
        }
        int towerPaint = staticRC.senseRobotAtLocation(refillLoc).paintAmount;
        boolean canRefill = staticRC.isActionReady() && towerPaint > 10;
        if (!staticRC.canSenseRobotAtLocation(refillLoc)) {
            System.out.println("Somethings wrong!");
            return;
        }
        if(staticRC.isMovementReady()) {
            populateMicroArrayRuin(refillLoc);
            microInfo bestMicro = microArray[0];
//            for (int i = 1; i < 9; i++) {
//                do {
//                    //if one space is passable and the other is not, then passable is better
//                    if (!microArray[i].passable) break;
//                    if (!bestMicro.passable) {
//                        bestMicro = microArray[i];
//                        break;
//                    }
//
//                    if (canRefill) {
//                        int distToRefill = bestMicro.loc.distanceSquaredTo(refillLoc);
//                        int altDistToRefill = microArray[i].loc.distanceSquaredTo(refillLoc);
//                        if (distToRefill < altDistToRefill && altDistToRefill > 2) break;
//                        if (altDistToRefill < distToRefill && distToRefill > 2) {
//                            bestMicro = microArray[i];
//                        }
//                    }
//
//                    //look at which place will lose us the least paint at the end of this turn
//                    if (bestMicro.paintLoss < microArray[i].paintLoss) break;
//                    if (microArray[i].paintLoss < bestMicro.paintLoss) {
//                        bestMicro = microArray[i];
//                        break;
//                    }
//                    //regardless of action readiness, the next considerations are unified:
//                    //we prioritize allied paint over neutral paint over enemy paint
//                    if (bestMicro.paint.isAlly() && !microArray[i].paint.isAlly()) break;
//                    if (!bestMicro.paint.isAlly() && microArray[i].paint.isAlly()) {
//                        bestMicro = microArray[i];
//                        break;
//                    }
//
//                    if (!bestMicro.paint.isEnemy() && microArray[i].paint.isEnemy()) break;
//                    if (bestMicro.paint.isEnemy() && !microArray[i].paint.isEnemy()) {
//                        bestMicro = microArray[i];
//                        break;
//                    }
//
//                    //next, lets try and avoid being next to allies cardinally, so that we dont get swung at by moppers
//                    if (bestMicro.adjacentAllies < microArray[i].adjacentAllies) break;
//                    if (microArray[i].adjacentAllies < bestMicro.adjacentAllies) {
//                        bestMicro = microArray[i];
//                        break;
//                    }
//                } while (false);
//            }
            for (int i = 1; i < 9; i++) {
                do {
                    //if one space is passable and the other is not, then passable is better
                    if (!microArray[i].passable) break;
                    if (!bestMicro.passable) {
                        bestMicro = microArray[i];
                        break;
                    }

                    if (canRefill) {
                        int distToRefill = bestMicro.loc.distanceSquaredTo(refillLoc);
                        int altDistToRefill = microArray[i].loc.distanceSquaredTo(refillLoc);
                        if (distToRefill < altDistToRefill && altDistToRefill > 2) break;
                        if (altDistToRefill < distToRefill && distToRefill > 2) {
                            bestMicro = microArray[i];
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
                } while (false);
            }
            if(bestMicro.passable && staticRC.canMove(staticRC.getLocation().directionTo(bestMicro.loc))) {
                staticRC.move(staticRC.getLocation().directionTo(bestMicro.loc));
            }
        }
        if(canRefill && staticRC.canTransferPaint(refillLoc, Math.max(staticRC.getPaint() - staticRC.getType().paintCapacity, staticRC.senseRobotAtLocation(refillLoc).paintAmount * -1))){
            staticRC.transferPaint(refillLoc, Math.max(staticRC.getPaint() - staticRC.getType().paintCapacity, staticRC.senseRobotAtLocation(refillLoc).paintAmount * -1));
        }
    }

    //finds the best square near the ruin for us to attack
    //1. attack the tile we are on if empty
    //2. attack an empty tile close to us
    //3. attack an empty tile further away
    //4. attack an allied tile with the wrong pattern close to us
    //5. attack an allied tile further away with the wrong pattern
    public static MapLocation bestRuinAttack( boolean[][] desiredPattern, MapInfo[] tilesToFill, MapLocation ruin) throws GameActionException {
        MapInfo bestTile = null;
        int minDist = Integer.MAX_VALUE;
        int minDistToEnemy = Integer.MAX_VALUE;
        if(staticRC.senseMapInfo(staticRC.getLocation()).getPaint() == PaintType.EMPTY && staticRC.getLocation().isWithinDistanceSquared(ruin, 8)) return staticRC.getLocation();
        for(MapInfo tile : tilesToFill) {
            int distToEnemy = (Soldier.averageEnemyPaint == null) ? Integer.MAX_VALUE : tile.getMapLocation().distanceSquaredTo(Soldier.averageEnemyPaint);
            int dist = staticRC.getLocation().distanceSquaredTo(tile.getMapLocation());
            if(!tile.isPassable()) continue;
            if(tile.getPaint() == PaintType.EMPTY && (bestTile == null || dist < minDist || bestTile.getPaint().isAlly() || (dist == minDist && distToEnemy < minDistToEnemy))) {
                bestTile = tile;
                minDist = dist;
                minDistToEnemy = distToEnemy;
            }
            else if(tile.getPaint().isAlly() && (bestTile == null || (dist < minDist && bestTile.getPaint().isAlly()))
             && Utilities.getColorFromCustomPattern(tile.getMapLocation(), desiredPattern, ruin) != tile.getPaint().isSecondary()) {
                minDist = dist;
                bestTile = tile;
            }
        }
        return (bestTile != null) ? bestTile.getMapLocation() : null;
    }

    //attempts to find the best place near the ruin to pattern in, and also move to the best adjacent square
    public static void patternFillingMicro( MapLocation patternCenter, boolean[][] desiredPattern, MapInfo[] tilesToFill) throws GameActionException {
        staticRC.setIndicatorString("Building a patternCenter!" + patternCenter.toString());
        int curDist = staticRC.getLocation().distanceSquaredTo(patternCenter);
        MapLocation bestAttack = null;
        if (staticRC.isActionReady()) {
            bestAttack = bestRuinAttack(desiredPattern, tilesToFill, patternCenter);
            if (bestAttack != null && staticRC.canAttack(bestAttack))
                staticRC.attack(bestAttack, Utilities.getColorFromCustomPattern(bestAttack, desiredPattern, patternCenter));
        }
//        if (bestAttack == null && staticRC.getChips() > 1000) {
//            Direction dir = Pathfinding.bugBFS(patternCenter);
//            if (staticRC.canMove(dir)) staticRC.move(dir);
//            return;
//        }
        if (staticRC.isMovementReady()) {
            if (curDist <= 8)
                populateMicroArrayRuin(patternCenter);
            else
                populateMicroArray();
            microInfo bestMicro = new microInfo(staticRC.senseMapInfo(staticRC.getLocation()));
            for (int i = 0; i < 9; i++) {
                do {
                    if (bestMicro.equals(microArray[i])) continue;
                    //if one space is passable and the other is not, then passable is better
                    if (!microArray[i].passable) break;
                    if (!bestMicro.passable) {
                        bestMicro = microArray[i];
                        break;
                    }

                    if (curDist >= 8) {
                        int distTopatternCenter = bestMicro.loc.distanceSquaredTo(patternCenter);
                        int altDistTopatternCenter = microArray[i].loc.distanceSquaredTo(patternCenter);
                        if (distTopatternCenter < altDistTopatternCenter) break;
                        if (altDistTopatternCenter < distTopatternCenter) {
                            bestMicro = microArray[i];
                            break;
                        }
                    } else if (curDist < 5) {
                        //int distTopatternCenter = bestMicro.loc.distanceSquaredTo(patternCenter);
                        int altDistTopatternCenter = microArray[i].loc.distanceSquaredTo(patternCenter);
                        if (altDistTopatternCenter >= 5) break;
                    }


                    //look at which place will lose us the least paint at the end of this turn
                    if (bestMicro.paintLoss < microArray[i].paintLoss) break;
                    if (microArray[i].paintLoss < bestMicro.paintLoss) {
                        bestMicro = microArray[i];
                        break;
                    }


                    if (bestAttack != null) {
                        int distToAction = bestMicro.loc.distanceSquaredTo(bestAttack);
                        int altDistToAction = microArray[i].loc.distanceSquaredTo(bestAttack);
                        if (distToAction < altDistToAction) break;
                        if (altDistToAction < distToAction) {
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

//                    if (!bestMicro.paint.isEnemy() && microArray[i].paint.isEnemy()) break;
//                    if (bestMicro.paint.isEnemy() && !microArray[i].paint.isEnemy()) {
//                        bestMicro = microArray[i];
//                        break;
//                    }

                    //next, lets try and avoid being next to allies cardinally, so that we dont get swung at by moppers
                    if (bestMicro.adjacentAllies < microArray[i].adjacentAllies) break;
                    if (microArray[i].adjacentAllies < bestMicro.adjacentAllies) {
                        bestMicro = microArray[i];
                        break;
                    }
                } while (false);
            }
            if (bestMicro.passable && staticRC.canMove(staticRC.getLocation().directionTo(bestMicro.loc))) {
                staticRC.move(staticRC.getLocation().directionTo(bestMicro.loc));
            }
        }
        if (staticRC.isActionReady() && bestAttack != null) {
            if (staticRC.canAttack(bestAttack))
                staticRC.attack(bestAttack, Utilities.getColorFromCustomPattern(bestAttack, desiredPattern, patternCenter));
            Utilities.attemptCompleteResourcePattern(bestAttack);
        }
    }

    //the micro method for soldiers
    public static Direction runSoldierMicro() throws GameActionException {
        int health = staticRC.getHealth();
        microInfo bestMicro = microArray[0];
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
//                if (staticRC.isActionReady() && (health >= soldierHealthAttackThreshold || isRushing)) {
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
//                //look at which place will lose us the least paint at the end of this turn
//                if (bestMicro.paintLoss < microArray[i].paintLoss) break;
//                if (microArray[i].paintLoss < bestMicro.paintLoss) {
//                    bestMicro = microArray[i];
//                    break;
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
//                if (bestMicro.adjacentAllies < microArray[i].adjacentAllies) break;
//                if (microArray[i].adjacentAllies < bestMicro.adjacentAllies) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//
//                if(bestMicro.distanceToEnemyAverage < microArray[i].distanceToEnemyAverage) break;
//                if(microArray[i].distanceToEnemyAverage < bestMicro.distanceToEnemyAverage) {
//                    bestMicro = microArray[i];
//                    break;
//                }
//
//                if(rng.nextInt(2) == 0) {
//                    bestMicro = microArray[i];
//                }
//            } while(false);
//
//           // at this point, nothing more to consider - unclear how minDistance to enemy would affect soldier micro
//        }
        // Unrolling the loop for i = 1 to 8
        {
            // i = 1
            do {
                if (!microArray[1].passable) break;
                if (!bestMicro.passable) {
                    bestMicro = microArray[1];
                    break;
                }

                if (staticRC.isActionReady() && (health >= soldierHealthAttackThreshold || isRushing)) {
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

                if (bestMicro.paintLoss < microArray[1].paintLoss) break;
                if (microArray[1].paintLoss < bestMicro.paintLoss) {
                    bestMicro = microArray[1];
                    break;
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

                if (bestMicro.adjacentAllies < microArray[1].adjacentAllies) break;
                if (microArray[1].adjacentAllies < bestMicro.adjacentAllies) {
                    bestMicro = microArray[1];
                    break;
                }

                if (bestMicro.distanceToEnemyAverage < microArray[1].distanceToEnemyAverage) break;
                if (microArray[1].distanceToEnemyAverage < bestMicro.distanceToEnemyAverage) {
                    bestMicro = microArray[1];
                    break;
                }

                if (rng.nextInt(2) == 0) {
                    bestMicro = microArray[1];
                }
            } while(false);

            // i = 2
            do {
                if (!microArray[2].passable) break;
                if (!bestMicro.passable) {
                    bestMicro = microArray[2];
                    break;
                }

                if (staticRC.isActionReady() && (health >= soldierHealthAttackThreshold || isRushing)) {
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

                if (bestMicro.paintLoss < microArray[2].paintLoss) break;
                if (microArray[2].paintLoss < bestMicro.paintLoss) {
                    bestMicro = microArray[2];
                    break;
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

                if (bestMicro.adjacentAllies < microArray[2].adjacentAllies) break;
                if (microArray[2].adjacentAllies < bestMicro.adjacentAllies) {
                    bestMicro = microArray[2];
                    break;
                }

                if (bestMicro.distanceToEnemyAverage < microArray[2].distanceToEnemyAverage) break;
                if (microArray[2].distanceToEnemyAverage < bestMicro.distanceToEnemyAverage) {
                    bestMicro = microArray[2];
                    break;
                }

                if (rng.nextInt(2) == 0) {
                    bestMicro = microArray[2];
                }
            } while(false);

            // i = 3
            do {
                if (!microArray[3].passable) break;
                if (!bestMicro.passable) {
                    bestMicro = microArray[3];
                    break;
                }

                if (staticRC.isActionReady() && (health >= soldierHealthAttackThreshold || isRushing)) {
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

                if (bestMicro.paintLoss < microArray[3].paintLoss) break;
                if (microArray[3].paintLoss < bestMicro.paintLoss) {
                    bestMicro = microArray[3];
                    break;
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

                if (bestMicro.adjacentAllies < microArray[3].adjacentAllies) break;
                if (microArray[3].adjacentAllies < bestMicro.adjacentAllies) {
                    bestMicro = microArray[3];
                    break;
                }

                if (bestMicro.distanceToEnemyAverage < microArray[3].distanceToEnemyAverage) break;
                if (microArray[3].distanceToEnemyAverage < bestMicro.distanceToEnemyAverage) {
                    bestMicro = microArray[3];
                    break;
                }

                if (rng.nextInt(2) == 0) {
                    bestMicro = microArray[3];
                }
            } while(false);

            // i = 4
            do {
                if (!microArray[4].passable) break;
                if (!bestMicro.passable) {
                    bestMicro = microArray[4];
                    break;
                }

                if (staticRC.isActionReady() && (health >= soldierHealthAttackThreshold || isRushing)) {
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

                if (bestMicro.paintLoss < microArray[4].paintLoss) break;
                if (microArray[4].paintLoss < bestMicro.paintLoss) {
                    bestMicro = microArray[4];
                    break;
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

                if (bestMicro.adjacentAllies < microArray[4].adjacentAllies) break;
                if (microArray[4].adjacentAllies < bestMicro.adjacentAllies) {
                    bestMicro = microArray[4];
                    break;
                }

                if (bestMicro.distanceToEnemyAverage < microArray[4].distanceToEnemyAverage) break;
                if (microArray[4].distanceToEnemyAverage < bestMicro.distanceToEnemyAverage) {
                    bestMicro = microArray[4];
                    break;
                }

                if (rng.nextInt(2) == 0) {
                    bestMicro = microArray[4];
                }
            } while(false);

            // i = 5
            do {
                if (!microArray[5].passable) break;
                if (!bestMicro.passable) {
                    bestMicro = microArray[5];
                    break;
                }

                if (staticRC.isActionReady() && (health >= soldierHealthAttackThreshold || isRushing)) {
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

                if (bestMicro.paintLoss < microArray[5].paintLoss) break;
                if (microArray[5].paintLoss < bestMicro.paintLoss) {
                    bestMicro = microArray[5];
                    break;
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

                if (bestMicro.adjacentAllies < microArray[5].adjacentAllies) break;
                if (microArray[5].adjacentAllies < bestMicro.adjacentAllies) {
                    bestMicro = microArray[5];
                    break;
                }

                if (bestMicro.distanceToEnemyAverage < microArray[5].distanceToEnemyAverage) break;
                if (microArray[5].distanceToEnemyAverage < bestMicro.distanceToEnemyAverage) {
                    bestMicro = microArray[5];
                    break;
                }

                if (rng.nextInt(2) == 0) {
                    bestMicro = microArray[5];
                }
            } while(false);

            // i = 6
            do {
                if (!microArray[6].passable) break;
                if (!bestMicro.passable) {
                    bestMicro = microArray[6];
                    break;
                }

                if (staticRC.isActionReady() && (health >= soldierHealthAttackThreshold || isRushing)) {
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

                if (bestMicro.paintLoss < microArray[6].paintLoss) break;
                if (microArray[6].paintLoss < bestMicro.paintLoss) {
                    bestMicro = microArray[6];
                    break;
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

                if (bestMicro.adjacentAllies < microArray[6].adjacentAllies) break;
                if (microArray[6].adjacentAllies < bestMicro.adjacentAllies) {
                    bestMicro = microArray[6];
                    break;
                }

                if (bestMicro.distanceToEnemyAverage < microArray[6].distanceToEnemyAverage) break;
                if (microArray[6].distanceToEnemyAverage < bestMicro.distanceToEnemyAverage) {
                    bestMicro = microArray[6];
                    break;
                }

                if (rng.nextInt(2) == 0) {
                    bestMicro = microArray[6];
                }
            } while(false);

            // i = 7
            do {
                if (!microArray[7].passable) break;
                if (!bestMicro.passable) {
                    bestMicro = microArray[7];
                    break;
                }

                if (staticRC.isActionReady() && (health >= soldierHealthAttackThreshold || isRushing)) {
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

                if (bestMicro.paintLoss < microArray[7].paintLoss) break;
                if (microArray[7].paintLoss < bestMicro.paintLoss) {
                    bestMicro = microArray[7];
                    break;
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

                if (bestMicro.adjacentAllies < microArray[7].adjacentAllies) break;
                if (microArray[7].adjacentAllies < bestMicro.adjacentAllies) {
                    bestMicro = microArray[7];
                    break;
                }

                if (bestMicro.distanceToEnemyAverage < microArray[7].distanceToEnemyAverage) break;
                if (microArray[7].distanceToEnemyAverage < bestMicro.distanceToEnemyAverage) {
                    bestMicro = microArray[7];
                    break;
                }

                if (rng.nextInt(2) == 0) {
                    bestMicro = microArray[7];
                }
            } while(false);

            // i = 8
            do {
                if (!microArray[8].passable) break;
                if (!bestMicro.passable) {
                    bestMicro = microArray[8];
                    break;
                }

                if (staticRC.isActionReady() && (health >= soldierHealthAttackThreshold || isRushing)) {
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

                if (bestMicro.paintLoss < microArray[8].paintLoss) break;
                if (microArray[8].paintLoss < bestMicro.paintLoss) {
                    bestMicro = microArray[8];
                    break;
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

                if (bestMicro.adjacentAllies < microArray[8].adjacentAllies) break;
                if (microArray[8].adjacentAllies < bestMicro.adjacentAllies) {
                    bestMicro = microArray[8];
                    break;
                }

                if (bestMicro.distanceToEnemyAverage < microArray[8].distanceToEnemyAverage) break;
                if (microArray[8].distanceToEnemyAverage < bestMicro.distanceToEnemyAverage) {
                    bestMicro = microArray[8];
                    break;
                }

                if (rng.nextInt(2) == 0) {
                    bestMicro = microArray[8];
                }
            } while(false);
        }


        return (bestMicro.passable) ? staticRC.getLocation().directionTo(bestMicro.loc) : Direction.CENTER;
    }


    //UTILITY METHODS

    //populates the array of potential spaces we can move too for this turn
    public static void populateMicroArray() throws GameActionException {
        microArray = new microInfo[9];
        int mapHeight = staticRC.getMapHeight() - 1;
        int mapWidth = staticRC.getMapWidth() - 1;
        MapLocation curLoc = staticRC.getLocation();
        int curX = curLoc.x;
        int curY = curLoc.y;
        int totalFilled = 0;
        //loop before being unrolled, in case changes need to be made
//        for(int dx = -1; dx <= 1; dx++) {
//            if(curX + dx >= 0 && curX + dx  <= mapWidth) {
//                for (int dy = -1; dy <= 1; dy++) {
//                    if(curY + dy >= 0 && curY + dy <= mapHeight) {
//                        MapLocation newLoc = new MapLocation(curX + dx, curY + dy);
//                        if(staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
//                        else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc));
//                        totalFilled++;
//                    }
//                }
//            }
//        }
        if (curX + -1 >= 0 && curX + -1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + -1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 0);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
        }
        if (curX + 0 >= 0 && curX + 0 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + -1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + 0);
                //if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                /*else*/ microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + 1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
        }
        if (curX + 1 >= 0 && curX + 1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + -1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 0);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 1);
                if (staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
        }
        //populate the rest of the array in case we are on the edge of the map and couldn't fill it all
        for(int i = totalFilled; i < 9; i++) {
            microArray[i] = new microInfo();
        }
    }
    //populates the array of potential spaces we can move too for this turn
    public static void populateMicroArrayRuin( MapLocation ruin) throws GameActionException {
        microArray = new microInfo[9];
        int mapHeight = staticRC.getMapHeight() - 1;
        int mapWidth = staticRC.getMapWidth() - 1;
        MapLocation curLoc = staticRC.getLocation();
        int curX = curLoc.x;
        int curY = curLoc.y;
        int totalFilled = 0;
        //loop before being unrolled, in case changes need to be made
//        for(int dx = -1; dx <= 1; dx++) {
//            if(curX + dx >= 0 && curX + dx  <= mapWidth) {
//                for (int dy = -1; dy <= 1; dy++) {
//                    if(curY + dy >= 0 && curY + dy <= mapHeight) {
//                        MapLocation newLoc = new MapLocation(curX + dx, curY + dy);
//                        if(staticRC.canSenseRobotAtLocation(newLoc)) microArray[totalFilled] = new microInfo();
//                        else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc));
//                        totalFilled++;
//                    }
//                }
//            }
//        }
        if (curX + -1 >= 0 && curX + -1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + -1);
                if (staticRC.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 0);
                if (staticRC.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + -1, curY + 1);
                if (staticRC.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
        }
        if (curX + 0 >= 0 && curX + 0 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + -1);
                if (staticRC.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + 0);
                //if (staticRC.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                /*else*/ microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 0, curY + 1);
                if (staticRC.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
        }
        if (curX + 1 >= 0 && curX + 1 <= mapWidth) {
            if (curY + -1 >= 0 && curY + -1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + -1);
                if (staticRC.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 0 >= 0 && curY + 0 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 0);
                if (staticRC.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
            if (curY + 1 >= 0 && curY + 1 <= mapHeight) {
                MapLocation newLoc = new MapLocation(curX + 1, curY + 1);
                if (staticRC.canSenseRobotAtLocation(newLoc) || !newLoc.isWithinDistanceSquared(ruin, 8)) microArray[totalFilled] = new microInfo();
                else microArray[totalFilled] = new microInfo(staticRC.senseMapInfo((newLoc)));
                totalFilled++;
            }
        }
        //populate the rest of the array in case we are on the edge of the map and couldn't fill it all
        for(int i = totalFilled; i < 9; i++) {
            microArray[i] = new microInfo();
        }
    }



    //checks whether a space is in firing range of any seen enemy towers
    public static boolean isSafeFromTower( MapLocation loc) {
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
