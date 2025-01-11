package Version6;

import battlecode.common.*;
import static Version6.RobotPlayer.*;

public class Utilities
{
    /*
        Uses the origin as the beginning of a tiling pattern and returns what color a tile on a specific MapLocation
        should be.
     */
    public static boolean getColorFromOriginPattern(MapLocation location, boolean[][] pattern)
    {
        MapLocation corner = new MapLocation((location.x / pattern[0].length) * pattern[0].length,
                (location.y / pattern.length) * pattern.length);
        return pattern[location.y - corner.y][location.x - corner.x];
    }

    public static void attemptCompleteResourcePattern(RobotController rc) throws GameActionException
    {
        if(rc.canCompleteResourcePattern(rc.getLocation()))
        {
            rc.completeResourcePattern(rc.getLocation());
        }
    }

    /*
        Attempt to complete resource pattern at all visible locations
     */

    public static void attemptCompletePatterns(RobotController rc) throws GameActionException
    {
        for(int i = 0; i < nearbyTiles.length; i++)
        {
            if(rc.canCompleteResourcePattern(nearbyTiles[i].getMapLocation()))
            {
                rc.completeResourcePattern(nearbyTiles[i].getMapLocation());
            }
        }
    }

    public static void updatePaintAverages(RobotController rc)
    {
        if(rc.getType().isTowerType()) return; // We don't want towers contributing for simplicity

        int xTotal1 = 0;
        int yTotal1 = 0;
        int count1 = 0;

        int xTotal2 = 0;
        int yTotal2 = 0;
        int count2 = 0;
        for (MapInfo nearbyTile : nearbyTiles)
        {
            if (nearbyTile.getPaint().isEnemy() &&
                    nearbyTile.getMapLocation().isWithinDistanceSquared(paintAverage1, distanceThreshold))
            {
                xTotal1 += nearbyTile.getMapLocation().x;
                yTotal1 += nearbyTile.getMapLocation().y;
                count1++;
            }
            else if(nearbyTile.getPaint().isEnemy())
            {
                xTotal2 += nearbyTile.getMapLocation().x;
                yTotal2 += nearbyTile.getMapLocation().y;
                count2++;
            }
        }

        if(count1 != 0)
        {
            xTotal1 = (xTotal1 + paintAverage1.x * paintCount1) / (count1 + paintCount1);
            yTotal1 = (yTotal1 + paintAverage1.y * paintCount1) / (count1 + paintCount1);
            paintAverage1 = new MapLocation(xTotal1, yTotal1);
        }
        if(count2 != 0)
        {
            xTotal2 = (xTotal2 + paintAverage2.x * paintCount2) / (count2 + paintCount2);
            yTotal2 = (yTotal2 + paintAverage2.y * paintCount2) / (count2 + paintCount2);
            paintAverage2 = new MapLocation(xTotal2, yTotal2);
        }
    }

    public static void setDotsAtGridStart(RobotController rc) throws GameActionException
    {
        for(int i = 0; i < rc.getMapHeight(); i += 5)
        {
            for(int j = 0; j < rc.getMapWidth(); j += 5)
            {
                rc.setIndicatorDot(new MapLocation(j, i), 0,0,255);
            }
        }
    }


}
