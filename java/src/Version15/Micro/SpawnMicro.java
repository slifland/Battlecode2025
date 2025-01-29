package Version15.Micro;

import battlecode.common.*;

import static Version15.RobotPlayer.canSeeEnemyPaint;
import static Version15.RobotPlayer.rc;

public class SpawnMicro {
    //spawn according to these priorities:
    //1. spawn on ally paint - 0b1000
    //2. spawn towards the center - 0b100
    //3. spawn on neutral paint - 0b10
    //4. spawn anywhere - 0b1
    public static MapLocation bestSpawn() throws GameActionException {
        int bestScore = Integer.MIN_VALUE;
        MapLocation bestTile = null;
        MapInfo[] potentialTiles = rc.senseNearbyMapInfos(GameConstants.BUILD_ROBOT_RADIUS_SQUARED);
        MapLocation center = new MapLocation(rc.getMapWidth() / 2, rc.getMapHeight() / 2);
        if(potentialTiles.length == 13) {
            for (int i = 0; i < 13; i++) {
                if (i == 7) continue;
                MapLocation tileLoc = potentialTiles[i].getMapLocation();
                //Direction dirToSquare = staticRC.getLocation().directionTo(tileLoc);
                int score = 0;
                score += (rc.getLocation().distanceSquaredTo(center) - tileLoc.distanceSquaredTo(center));
                score += switch (potentialTiles[i].getPaint()) {
                    case PaintType.ALLY_PRIMARY, ALLY_SECONDARY -> 3;
                    case PaintType.EMPTY -> 0;
                    case PaintType.ENEMY_PRIMARY, PaintType.ENEMY_SECONDARY -> -1;
                };
                if(!canSeeEnemyPaint && potentialTiles[i].getPaint().isEnemy()) canSeeEnemyPaint = true;
                if (score > bestScore) {
                    bestScore = score;
                    bestTile = tileLoc;
                }
            }
        }
        else {
            for(MapInfo tile : potentialTiles) {
                if(tile.getMapLocation().equals(rc.getLocation())) continue;
                MapLocation tileLoc = tile.getMapLocation();
                //Direction dirToSquare = staticRC.getLocation().directionTo(tileLoc);
                int score = 0;
                score += (rc.getLocation().distanceSquaredTo(center) - tileLoc.distanceSquaredTo(center));
                score += switch (tile.getPaint()) {
                    case PaintType.ALLY_PRIMARY, ALLY_SECONDARY -> 3;
                    case PaintType.EMPTY -> 0;
                    case PaintType.ENEMY_PRIMARY, PaintType.ENEMY_SECONDARY -> -1;
                };
                if(!canSeeEnemyPaint && tile.getPaint().isEnemy()) {
                    canSeeEnemyPaint = true;
                }
                if (score > bestScore) {
                    bestScore = score;
                    bestTile = tileLoc;
                }
            }
        }
        return bestTile;
    }
}
