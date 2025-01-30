package Version15.Misc;
import Version15.Utility.Utilities;
import battlecode.common.*;
import static Version15.RobotPlayer.staticRC;

public class PaintFill
{
    public static MapLocation getLocationFromIndex(int nodeIndex, MapLocation center)
    {
        return new MapLocation(center.x - 3 + nodeIndex % 7, center.y - 3 + 6 - nodeIndex / 7);
    }

    public static void attemptFill(MapLocation center) throws GameActionException
    {
        boolean[][] pattern = staticRC.getResourcePattern();
        MapLocation l0 = getLocationFromIndex(3, center);
        if(staticRC.canPaint(l0) && !staticRC.senseMapInfo(l0).getPaint().isAlly())
        {
            staticRC.attack(l0, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l1 = getLocationFromIndex(8, center);
        if(staticRC.canPaint(l1) && !staticRC.senseMapInfo(l1).getPaint().isAlly())
        {
            staticRC.attack(l1, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l2 = getLocationFromIndex(9, center);
        if(staticRC.canPaint(l2) && !staticRC.senseMapInfo(l2).getPaint().isAlly())
        {
            staticRC.attack(l2, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l3 = getLocationFromIndex(10, center);
        if(staticRC.canPaint(l3) && !staticRC.senseMapInfo(l3).getPaint().isAlly())
        {
            staticRC.attack(l3, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l4 = getLocationFromIndex(11, center);
        if(staticRC.canPaint(l4) && !staticRC.senseMapInfo(l4).getPaint().isAlly())
        {
            staticRC.attack(l4, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l5 = getLocationFromIndex(12, center);
        if(staticRC.canPaint(l5) && !staticRC.senseMapInfo(l5).getPaint().isAlly())
        {
            staticRC.attack(l5, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l6 = getLocationFromIndex(15, center);
        if(staticRC.canPaint(l6) && !staticRC.senseMapInfo(l6).getPaint().isAlly())
        {
            staticRC.attack(l6, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l7 = getLocationFromIndex(16, center);
        if(staticRC.canPaint(l7) && !staticRC.senseMapInfo(l7).getPaint().isAlly())
        {
            staticRC.attack(l7, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l8 = getLocationFromIndex(17, center);
        if(staticRC.canPaint(l8) && !staticRC.senseMapInfo(l8).getPaint().isAlly())
        {
            staticRC.attack(l8, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l9 = getLocationFromIndex(18, center);
        if(staticRC.canPaint(l9) && !staticRC.senseMapInfo(l9).getPaint().isAlly())
        {
            staticRC.attack(l9, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l10 = getLocationFromIndex(19, center);
        if(staticRC.canPaint(l10) && !staticRC.senseMapInfo(l10).getPaint().isAlly())
        {
            staticRC.attack(l10, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l11 = getLocationFromIndex(21, center);
        if(staticRC.canPaint(l11) && !staticRC.senseMapInfo(l11).getPaint().isAlly())
        {
            staticRC.attack(l11, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l12 = getLocationFromIndex(22, center);
        if(staticRC.canPaint(l12) && !staticRC.senseMapInfo(l12).getPaint().isAlly())
        {
            staticRC.attack(l12, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l13 = getLocationFromIndex(23, center);
        if(staticRC.canPaint(l13) && !staticRC.senseMapInfo(l13).getPaint().isAlly())
        {
            staticRC.attack(l13, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l14 = getLocationFromIndex(24, center);
        if(staticRC.canPaint(l14) && !staticRC.senseMapInfo(l14).getPaint().isAlly())
        {
            staticRC.attack(l14, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l15 = getLocationFromIndex(25, center);
        if(staticRC.canPaint(l15) && !staticRC.senseMapInfo(l15).getPaint().isAlly())
        {
            staticRC.attack(l15, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l16 = getLocationFromIndex(26, center);
        if(staticRC.canPaint(l16) && !staticRC.senseMapInfo(l16).getPaint().isAlly())
        {
            staticRC.attack(l16, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l17 = getLocationFromIndex(27, center);
        if(staticRC.canPaint(l17) && !staticRC.senseMapInfo(l17).getPaint().isAlly())
        {
            staticRC.attack(l17, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l18 = getLocationFromIndex(29, center);
        if(staticRC.canPaint(l18) && !staticRC.senseMapInfo(l18).getPaint().isAlly())
        {
            staticRC.attack(l18, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l19 = getLocationFromIndex(30, center);
        if(staticRC.canPaint(l19) && !staticRC.senseMapInfo(l19).getPaint().isAlly())
        {
            staticRC.attack(l19, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l20 = getLocationFromIndex(31, center);
        if(staticRC.canPaint(l20) && !staticRC.senseMapInfo(l20).getPaint().isAlly())
        {
            staticRC.attack(l20, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l21 = getLocationFromIndex(32, center);
        if(staticRC.canPaint(l21) && !staticRC.senseMapInfo(l21).getPaint().isAlly())
        {
            staticRC.attack(l21, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l22 = getLocationFromIndex(33, center);
        if(staticRC.canPaint(l22) && !staticRC.senseMapInfo(l22).getPaint().isAlly())
        {
            staticRC.attack(l22, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l23 = getLocationFromIndex(36, center);
        if(staticRC.canPaint(l23) && !staticRC.senseMapInfo(l23).getPaint().isAlly())
        {
            staticRC.attack(l23, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l24 = getLocationFromIndex(37, center);
        if(staticRC.canPaint(l24) && !staticRC.senseMapInfo(l24).getPaint().isAlly())
        {
            staticRC.attack(l24, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l25 = getLocationFromIndex(38, center);
        if(staticRC.canPaint(l25) && !staticRC.senseMapInfo(l25).getPaint().isAlly())
        {
            staticRC.attack(l25, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l26 = getLocationFromIndex(39, center);
        if(staticRC.canPaint(l26) && !staticRC.senseMapInfo(l26).getPaint().isAlly())
        {
            staticRC.attack(l26, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l27 = getLocationFromIndex(40, center);
        if(staticRC.canPaint(l27) && !staticRC.senseMapInfo(l27).getPaint().isAlly())
        {
            staticRC.attack(l27, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l28 = getLocationFromIndex(45, center);
        if(staticRC.canPaint(l28) && !staticRC.senseMapInfo(l28).getPaint().isAlly())
        {
            staticRC.attack(l28, Utilities.getColorFromOriginPattern(center, pattern));
        }
    }
}
