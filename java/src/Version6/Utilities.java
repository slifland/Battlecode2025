package Version6;

import battlecode.common.*;

import java.awt.*;

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
    //returns the color a square should be in a pattern starting from a custom origin
    public static boolean getColorFromCustomPattern(MapLocation location, boolean[][] pattern, MapLocation center)
    {
        //MapLocation origin = new MapLocation(center.x - 2, center.y - 2);
        return pattern[location.x - (center.x - 2)][location.y - (center.y - 2)];
    }

    //looks at the area around a map location, and infers which tower pattern is matched
    //for now only considers the two patterns we build, money and paint
    public static boolean[][] inferPatternFromExistingSpots(RobotController rc, MapLocation center, MapInfo[] ruinTiles) throws GameActionException {
        int moneyScore = 0;
        int paintScore = 0;
        int totalAlly = 0;
        MapLocation fakeOrigin = new MapLocation(center.x - 2, center.y - 2);
        boolean[][] moneyPattern = rc.getTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER);
        boolean[][] paintPattern = rc.getTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER);
        for(MapInfo tile : ruinTiles) {
            PaintType paint = tile.getPaint();
            if(paint.isAlly()) {
                int offsetX = tile.getMapLocation().x - fakeOrigin.x;
                int offsetY = tile.getMapLocation().y - fakeOrigin.y;
                if(paint.isSecondary() == moneyPattern[offsetX][offsetY]) moneyScore++;
                if(paint.isSecondary() == paintPattern[offsetX][offsetY]) paintScore++;
                totalAlly++;
            }
        }
        if(totalAlly == 0) return null;
        if(moneyScore >= paintScore) return moneyPattern;
        else return paintPattern;
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
        if(rc.getRoundNum() % 50 == 0)        // Clear frontlines every so often
        {
            paintCount1 = 0;
            paintCount2 = 0;
            paintAverage1 = new MapLocation(0,0);
            paintAverage2 = new MapLocation(0,0);
        }

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
            paintCount1 += count1;
        }
        if(count2 != 0)
        {
            xTotal2 = (xTotal2 + paintAverage2.x * paintCount2) / (count2 + paintCount2);
            yTotal2 = (yTotal2 + paintAverage2.y * paintCount2) / (count2 + paintCount2);
            paintAverage2 = new MapLocation(xTotal2, yTotal2);
            paintCount2 += count2;
        }
    }

    public static MapLocation[] getEnemyPaintAverages()
    {
        MapLocation empty = new MapLocation(0,0);
        if(!paintAverage1.equals(empty) && !paintAverage2.equals(empty))
        {
            return new MapLocation[]{paintAverage1, paintAverage2};
        }
        else if(!paintAverage1.equals(empty) && paintAverage2.equals(empty))
        {
            return new MapLocation[]{paintAverage1};
        }
        else if(paintAverage1.equals(empty) && !paintAverage2.equals(empty))
        {
            return new MapLocation[]{paintAverage2};
        }
        else
        {
            return new MapLocation[0];
        }
    }

    public static void setDotsAtGridStart(RobotController rc) throws GameActionException
    {
        for(int i = 0; i < rc.getMapHeight(); i += 5)
        {
            for(int j = 0; j < rc.getMapWidth(); j += 5)
            {
                //rc.setIndicatorDot(new MapLocation(j, i), 0,0,255);
            }
        }
    }


}
