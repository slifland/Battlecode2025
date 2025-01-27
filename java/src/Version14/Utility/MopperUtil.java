package Version14.Utility;
import Version14.RobotPlayer;
import battlecode.common.*;

import static Version14.Robots.Mopper.*;
import static Version14.RobotPlayer.*;

public class MopperUtil {

    public static void checkSymmetry(int index) {
        if(knownSymmetry == RobotPlayer.Symmetry.Unknown) {
            map[nearbyTiles[index].getMapLocation().x][nearbyTiles[index].getMapLocation().y] = (nearbyTiles[index].isPassable()) ? 1 : (nearbyTiles[index].isWall()) ? 2 : 3;
            if(!nearbyTiles[index].isPassable())  Utilities.validateSymmetry(nearbyTiles[index].getMapLocation(), nearbyTiles[index].hasRuin());
        }
    }
    public static void checkClearing(int index){
        if(!needsClearing && nearbyRuin != null && nearbyTiles[index].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8)) {
            needsClearing = true;
        }
    }
    public static void scanNearbyTiles() throws GameActionException {
        int x = 0;
        int y = 0;
        int count = 0;
        FastIterableLocSet behindWall = WallChecker_FullVision.findOverWallTiles(staticRC);
        for (MapInfo tile : nearbyTiles) {
            MapLocation tileLoc = tile.getMapLocation();
            if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                map[tileLoc.x][tileLoc.y] = (tile.isPassable()) ? 1 : (tile.isWall()) ? 2 : 3;
                if (!tile.isPassable()) Utilities.validateSymmetry(tileLoc, tile.hasRuin());
            }
            if (tile.getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                    needsClearing = true;
                }
                x += tileLoc.x;
                y += tileLoc.y;
                count++;
            }
        }
        numEnemyTiles = count;
        averageEnemyPaint = (count == 0) ? null : new MapLocation(x / count, y / count);
    }
}

