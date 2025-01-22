package Version12;
import battlecode.common.*;

public class PaintFill
{
    public static MapLocation getLocationFromIndex(int nodeIndex, MapLocation center)
    {
        return new MapLocation(center.x - 3 + nodeIndex % 7, center.y - 3 + 6 - nodeIndex / 7);
    }

    public static void attemptFill(RobotController rc, MapLocation center) throws GameActionException
    {
        boolean[][] pattern = rc.getResourcePattern();
        MapLocation l0 = getLocationFromIndex(3, center);
        if(rc.canPaint(l0) && !rc.senseMapInfo(l0).getPaint().isAlly())
        {
            rc.attack(l0, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l1 = getLocationFromIndex(8, center);
        if(rc.canPaint(l1) && !rc.senseMapInfo(l1).getPaint().isAlly())
        {
            rc.attack(l1, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l2 = getLocationFromIndex(9, center);
        if(rc.canPaint(l2) && !rc.senseMapInfo(l2).getPaint().isAlly())
        {
            rc.attack(l2, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l3 = getLocationFromIndex(10, center);
        if(rc.canPaint(l3) && !rc.senseMapInfo(l3).getPaint().isAlly())
        {
            rc.attack(l3, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l4 = getLocationFromIndex(11, center);
        if(rc.canPaint(l4) && !rc.senseMapInfo(l4).getPaint().isAlly())
        {
            rc.attack(l4, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l5 = getLocationFromIndex(12, center);
        if(rc.canPaint(l5) && !rc.senseMapInfo(l5).getPaint().isAlly())
        {
            rc.attack(l5, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l6 = getLocationFromIndex(15, center);
        if(rc.canPaint(l6) && !rc.senseMapInfo(l6).getPaint().isAlly())
        {
            rc.attack(l6, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l7 = getLocationFromIndex(16, center);
        if(rc.canPaint(l7) && !rc.senseMapInfo(l7).getPaint().isAlly())
        {
            rc.attack(l7, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l8 = getLocationFromIndex(17, center);
        if(rc.canPaint(l8) && !rc.senseMapInfo(l8).getPaint().isAlly())
        {
            rc.attack(l8, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l9 = getLocationFromIndex(18, center);
        if(rc.canPaint(l9) && !rc.senseMapInfo(l9).getPaint().isAlly())
        {
            rc.attack(l9, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l10 = getLocationFromIndex(19, center);
        if(rc.canPaint(l10) && !rc.senseMapInfo(l10).getPaint().isAlly())
        {
            rc.attack(l10, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l11 = getLocationFromIndex(21, center);
        if(rc.canPaint(l11) && !rc.senseMapInfo(l11).getPaint().isAlly())
        {
            rc.attack(l11, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l12 = getLocationFromIndex(22, center);
        if(rc.canPaint(l12) && !rc.senseMapInfo(l12).getPaint().isAlly())
        {
            rc.attack(l12, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l13 = getLocationFromIndex(23, center);
        if(rc.canPaint(l13) && !rc.senseMapInfo(l13).getPaint().isAlly())
        {
            rc.attack(l13, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l14 = getLocationFromIndex(24, center);
        if(rc.canPaint(l14) && !rc.senseMapInfo(l14).getPaint().isAlly())
        {
            rc.attack(l14, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l15 = getLocationFromIndex(25, center);
        if(rc.canPaint(l15) && !rc.senseMapInfo(l15).getPaint().isAlly())
        {
            rc.attack(l15, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l16 = getLocationFromIndex(26, center);
        if(rc.canPaint(l16) && !rc.senseMapInfo(l16).getPaint().isAlly())
        {
            rc.attack(l16, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l17 = getLocationFromIndex(27, center);
        if(rc.canPaint(l17) && !rc.senseMapInfo(l17).getPaint().isAlly())
        {
            rc.attack(l17, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l18 = getLocationFromIndex(29, center);
        if(rc.canPaint(l18) && !rc.senseMapInfo(l18).getPaint().isAlly())
        {
            rc.attack(l18, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l19 = getLocationFromIndex(30, center);
        if(rc.canPaint(l19) && !rc.senseMapInfo(l19).getPaint().isAlly())
        {
            rc.attack(l19, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l20 = getLocationFromIndex(31, center);
        if(rc.canPaint(l20) && !rc.senseMapInfo(l20).getPaint().isAlly())
        {
            rc.attack(l20, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l21 = getLocationFromIndex(32, center);
        if(rc.canPaint(l21) && !rc.senseMapInfo(l21).getPaint().isAlly())
        {
            rc.attack(l21, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l22 = getLocationFromIndex(33, center);
        if(rc.canPaint(l22) && !rc.senseMapInfo(l22).getPaint().isAlly())
        {
            rc.attack(l22, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l23 = getLocationFromIndex(36, center);
        if(rc.canPaint(l23) && !rc.senseMapInfo(l23).getPaint().isAlly())
        {
            rc.attack(l23, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l24 = getLocationFromIndex(37, center);
        if(rc.canPaint(l24) && !rc.senseMapInfo(l24).getPaint().isAlly())
        {
            rc.attack(l24, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l25 = getLocationFromIndex(38, center);
        if(rc.canPaint(l25) && !rc.senseMapInfo(l25).getPaint().isAlly())
        {
            rc.attack(l25, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l26 = getLocationFromIndex(39, center);
        if(rc.canPaint(l26) && !rc.senseMapInfo(l26).getPaint().isAlly())
        {
            rc.attack(l26, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l27 = getLocationFromIndex(40, center);
        if(rc.canPaint(l27) && !rc.senseMapInfo(l27).getPaint().isAlly())
        {
            rc.attack(l27, Utilities.getColorFromOriginPattern(center, pattern));
        }
        MapLocation l28 = getLocationFromIndex(45, center);
        if(rc.canPaint(l28) && !rc.senseMapInfo(l28).getPaint().isAlly())
        {
            rc.attack(l28, Utilities.getColorFromOriginPattern(center, pattern));
        }
    }
}
