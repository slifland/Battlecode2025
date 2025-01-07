package Version0;

import battlecode.common.*;

import static Version0.RobotPlayer.directions;
import static Version0.RobotPlayer.rng;

public class Soldier {
    /**
     * Run a single turn for a Soldier.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    public static void runSoldier(RobotController rc) throws GameActionException {
        // Sense information about all visible nearby tiles.
        MapInfo[] nearbyTiles = rc.senseNearbyMapInfos();
        RobotInfo[] enemyRobots = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        // Search for a nearby ruin to complete.
        MapInfo curRuin = null;
        for (MapInfo tile : nearbyTiles){
            if (tile.hasRuin()){
                curRuin = tile;
            }
        }
        if (curRuin != null){
            MapLocation targetLoc = curRuin.getMapLocation();
            Direction dir = rc.getLocation().directionTo(targetLoc);
            if (rc.canMove(dir))
                rc.move(dir);
            // Mark the pattern we need to draw to build a tower here if we haven't already.
            MapLocation shouldBeMarked = curRuin.getMapLocation().subtract(dir);
            if (rc.senseMapInfo(shouldBeMarked).getMark() == PaintType.EMPTY && rc.canMarkTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, targetLoc)){
                rc.markTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, targetLoc);
                System.out.println("Trying to build a tower at " + targetLoc);
            }
            // Fill in any spots in the pattern with the appropriate paint, but first try to paint ur own tile so u can stay alive longer.
            MapInfo currentTile = rc.senseMapInfo(rc.getLocation());
            if(!currentTile.getPaint().isAlly() && currentTile.getMark() != currentTile.getPaint()){
                boolean useSecondaryColor = currentTile.getMark() == PaintType.ALLY_SECONDARY;
                if (rc.canAttack(currentTile.getMapLocation()))
                    rc.attack(currentTile.getMapLocation(), useSecondaryColor);
            }
            else {
                for (MapInfo patternTile : rc.senseNearbyMapInfos(targetLoc, 8)) {
                    if (patternTile.getMark() != patternTile.getPaint() && patternTile.getMark() != PaintType.EMPTY) {
                        boolean useSecondaryColor = patternTile.getMark() == PaintType.ALLY_SECONDARY;
                        if (rc.canAttack(patternTile.getMapLocation()))
                            rc.attack(patternTile.getMapLocation(), useSecondaryColor);
                    }
                }
            }
            // Complete the ruin if we can.
            if (rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, targetLoc)){
                rc.completeTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, targetLoc);
                rc.setTimelineMarker("Tower built", 0, 255, 0);
                System.out.println("Built a tower at " + targetLoc + "!");
            }
        }


        // Move and attack randomly if no objective.
        Direction dir = directions[rng.nextInt(directions.length)];
        MapLocation nextLoc = rc.getLocation().add(dir);
        if (rc.canMove(dir)){
            rc.move(dir);
        }
        // Try to paint beneath us as we walk to avoid paint penalties.
        // Avoiding wasting paint by re-painting our own tiles.
        MapInfo currentTile = rc.senseMapInfo(rc.getLocation());
        if (!currentTile.getPaint().isAlly() && rc.canAttack(rc.getLocation())){
            rc.attack(rc.getLocation());
        }
        //otherwise, if we have a lot of paint, just paint something
        else if (rc.getPaint() > 100) {
            for(MapInfo loc : rc.senseNearbyMapInfos(nextLoc, rc.getType().actionRadiusSquared)){
                if(loc.getPaint() == PaintType.EMPTY && rc.canAttack(loc.getMapLocation())){
                    rc.attack(loc.getMapLocation());
                }
            }
        }
        //check for towers
        for(RobotInfo enemy : enemyRobots) {
            if(enemy.getType().isTowerType()) {
                if(rc.canAttack(enemy.getLocation())) {
                    rc.attack(enemy.getLocation());
                }
            }
        }
        //lets try and transfer some paint, if we can
        for(RobotInfo ally : rc.senseNearbyRobots(2, rc.getTeam())) {
            if(ally.getType().isTowerType() && rc.canTransferPaint(ally.getLocation(), -50)) {
                rc.transferPaint(ally.getLocation(), -50);
            }
        }
    }
}
