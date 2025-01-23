package Version13.Micro;

import battlecode.common.*;
import static Version13.RobotPlayer.staticRC;

public class SpawnMicro {
    //spawn according to these priorities:
    //1. spawn on ally paint - 0b1000
    //2. spawn towards the center - 0b100
    //3. spawn on neutral paint - 0b10
    //4. spawn anywhere - 0b1
    public static MapLocation bestSpawn() throws GameActionException {
        int bestScore = Integer.MIN_VALUE;
        MapLocation bestTile = null;
        MapInfo[] potentialTiles = staticRC.senseNearbyMapInfos(GameConstants.BUILD_ROBOT_RADIUS_SQUARED);
        MapLocation center = new MapLocation(staticRC.getMapWidth() / 2, staticRC.getMapHeight() / 2);
        for(int i = 0; i < 13; i++) {
            if(i == 7) continue;
            MapLocation tileLoc = potentialTiles[i].getMapLocation();
            Direction dirToSquare = staticRC.getLocation().directionTo(tileLoc);
            int score = 0;
            score += (staticRC.getLocation().distanceSquaredTo(center) - tileLoc.distanceSquaredTo(center));
            score += switch(staticRC.senseMapInfo(tileLoc).getPaint()) {
                case PaintType.ALLY_PRIMARY, ALLY_SECONDARY -> 1;
                case PaintType.EMPTY -> 0;
                case PaintType.ENEMY_PRIMARY, PaintType.ENEMY_SECONDARY -> -1;
            };
            if(score > bestScore) {
                bestScore = score;
                bestTile = tileLoc;
            }
        }
        return bestTile;
    }
}
