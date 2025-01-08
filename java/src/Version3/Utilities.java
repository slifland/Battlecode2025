package Version3;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

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
