package Version15.Utility;
import battlecode.common.*;
import java.util.*;
public class WallChecker
{
    static MapLocation l0;
    static boolean exists0;
    static boolean reachable0;

    static MapLocation l1;
    static boolean exists1;
    static boolean reachable1;

    static MapLocation l2;
    static boolean exists2;
    static boolean reachable2;

    static MapLocation l3;
    static boolean exists3;
    static boolean reachable3;

    static MapLocation l4;
    static boolean exists4;
    static boolean reachable4;

    static MapLocation l5;
    static boolean exists5;
    static boolean reachable5;

    static MapLocation l6;
    static boolean exists6;
    static boolean reachable6;

    static MapLocation l7;
    static boolean exists7;
    static boolean reachable7;

    static MapLocation l8;
    static boolean exists8;
    static boolean reachable8;

    static MapLocation l9;
    static boolean exists9;
    static boolean reachable9;

    static MapLocation l10;
    static boolean exists10;
    static boolean reachable10;

    static MapLocation l11;
    static boolean exists11;
    static boolean reachable11;

    static MapLocation l12;
    static boolean exists12;
    static boolean reachable12;

    static MapLocation l13;
    static boolean exists13;
    static boolean reachable13;

    static MapLocation l14;
    static boolean exists14;
    static boolean reachable14;

    static MapLocation l15;
    static boolean exists15;
    static boolean reachable15;

    static MapLocation l16;
    static boolean exists16;
    static boolean reachable16;

    static MapLocation l17;
    static boolean exists17;
    static boolean reachable17;

    static MapLocation l18;
    static boolean exists18;
    static boolean reachable18;

    static MapLocation l19;
    static boolean exists19;
    static boolean reachable19;

    static MapLocation l20;
    static boolean exists20;
    static boolean reachable20;

    static MapLocation l21;
    static boolean exists21;
    static boolean reachable21;

    static MapLocation l22;
    static boolean exists22;
    static boolean reachable22;

    static MapLocation l23;
    static boolean exists23;
    static boolean reachable23;

    static MapLocation l24;
    static boolean exists24;
    static boolean reachable24;

    static MapLocation l25;
    static boolean exists25;
    static boolean reachable25;

    static MapLocation l26;
    static boolean exists26;
    static boolean reachable26;

    static MapLocation l27;
    static boolean exists27;
    static boolean reachable27;

    static MapLocation l28;
    static boolean exists28;
    static boolean reachable28;

    static MapLocation l29;
    static boolean exists29;
    static boolean reachable29;

    static MapLocation l30;
    static boolean exists30;
    static boolean reachable30;

    static MapLocation l31;
    static boolean exists31;
    static boolean reachable31;

    static MapLocation l32;
    static boolean exists32;
    static boolean reachable32;

    static MapLocation l33;
    static boolean exists33;
    static boolean reachable33;

    static MapLocation l34;
    static boolean exists34;
    static boolean reachable34;

    static MapLocation l35;
    static boolean exists35;
    static boolean reachable35;

    static MapLocation l36;
    static boolean exists36;
    static boolean reachable36;

    static MapLocation l37;
    static boolean exists37;
    static boolean reachable37;

    static MapLocation l38;
    static boolean exists38;
    static boolean reachable38;

    static MapLocation l39;
    static boolean exists39;
    static boolean reachable39;

    static MapLocation l40;
    static boolean exists40;
    static boolean reachable40;

    static MapLocation l41;
    static boolean exists41;
    static boolean reachable41;

    static MapLocation l42;
    static boolean exists42;
    static boolean reachable42;

    static MapLocation l43;
    static boolean exists43;
    static boolean reachable43;

    static MapLocation l44;
    static boolean exists44;
    static boolean reachable44;

    static MapLocation l45;
    static boolean exists45;
    static boolean reachable45;

    static MapLocation l46;
    static boolean exists46;
    static boolean reachable46;

    static MapLocation l47;
    static boolean exists47;
    static boolean reachable47;

    static MapLocation l48;
    static boolean exists48;
    static boolean reachable48;

    public static FastIterableLocSet findOverWallTiles(RobotController rc) throws GameActionException
    {
        FastIterableLocSet overWall = new FastIterableLocSet();
        MapLocation start = rc.getLocation();

        l0 = new MapLocation(start.x - 3, start.y + 3);
        exists0 = rc.onTheMap(l0) && rc.sensePassability(l0);
        reachable0 = false;

        l1 = new MapLocation(start.x - 2, start.y + 3);
        exists1 = rc.onTheMap(l1) && rc.sensePassability(l1);
        reachable1 = false;

        l2 = new MapLocation(start.x - 1, start.y + 3);
        exists2 = rc.onTheMap(l2) && rc.sensePassability(l2);
        reachable2 = false;

        l3 = new MapLocation(start.x - 0, start.y + 3);
        exists3 = rc.onTheMap(l3) && rc.sensePassability(l3);
        reachable3 = false;

        l4 = new MapLocation(start.x + 1, start.y + 3);
        exists4 = rc.onTheMap(l4) && rc.sensePassability(l4);
        reachable4 = false;

        l5 = new MapLocation(start.x + 2, start.y + 3);
        exists5 = rc.onTheMap(l5) && rc.sensePassability(l5);
        reachable5 = false;

        l6 = new MapLocation(start.x + 3, start.y + 3);
        exists6 = rc.onTheMap(l6) && rc.sensePassability(l6);
        reachable6 = false;

        l7 = new MapLocation(start.x - 3, start.y + 2);
        exists7 = rc.onTheMap(l7) && rc.sensePassability(l7);
        reachable7 = false;

        l8 = new MapLocation(start.x - 2, start.y + 2);
        exists8 = rc.onTheMap(l8) && rc.sensePassability(l8);
        reachable8 = false;

        l9 = new MapLocation(start.x - 1, start.y + 2);
        exists9 = rc.onTheMap(l9) && rc.sensePassability(l9);
        reachable9 = false;

        l10 = new MapLocation(start.x - 0, start.y + 2);
        exists10 = rc.onTheMap(l10) && rc.sensePassability(l10);
        reachable10 = false;

        l11 = new MapLocation(start.x + 1, start.y + 2);
        exists11 = rc.onTheMap(l11) && rc.sensePassability(l11);
        reachable11 = false;

        l12 = new MapLocation(start.x + 2, start.y + 2);
        exists12 = rc.onTheMap(l12) && rc.sensePassability(l12);
        reachable12 = false;

        l13 = new MapLocation(start.x + 3, start.y + 2);
        exists13 = rc.onTheMap(l13) && rc.sensePassability(l13);
        reachable13 = false;

        l14 = new MapLocation(start.x - 3, start.y + 1);
        exists14 = rc.onTheMap(l14) && rc.sensePassability(l14);
        reachable14 = false;

        l15 = new MapLocation(start.x - 2, start.y + 1);
        exists15 = rc.onTheMap(l15) && rc.sensePassability(l15);
        reachable15 = false;

        l16 = new MapLocation(start.x - 1, start.y + 1);
        exists16 = rc.onTheMap(l16) && rc.sensePassability(l16);
        reachable16 = false;

        l17 = new MapLocation(start.x - 0, start.y + 1);
        exists17 = rc.onTheMap(l17) && rc.sensePassability(l17);
        reachable17 = false;

        l18 = new MapLocation(start.x + 1, start.y + 1);
        exists18 = rc.onTheMap(l18) && rc.sensePassability(l18);
        reachable18 = false;

        l19 = new MapLocation(start.x + 2, start.y + 1);
        exists19 = rc.onTheMap(l19) && rc.sensePassability(l19);
        reachable19 = false;

        l20 = new MapLocation(start.x + 3, start.y + 1);
        exists20 = rc.onTheMap(l20) && rc.sensePassability(l20);
        reachable20 = false;

        l21 = new MapLocation(start.x - 3, start.y - 0);
        exists21 = rc.onTheMap(l21) && rc.sensePassability(l21);
        reachable21 = false;

        l22 = new MapLocation(start.x - 2, start.y - 0);
        exists22 = rc.onTheMap(l22) && rc.sensePassability(l22);
        reachable22 = false;

        l23 = new MapLocation(start.x - 1, start.y - 0);
        exists23 = rc.onTheMap(l23) && rc.sensePassability(l23);
        reachable23 = false;

        l24 = start;
        exists24 = true;
        reachable24 = true;

        l25 = new MapLocation(start.x + 1, start.y - 0);
        exists25 = rc.onTheMap(l25) && rc.sensePassability(l25);
        reachable25 = false;

        l26 = new MapLocation(start.x + 2, start.y - 0);
        exists26 = rc.onTheMap(l26) && rc.sensePassability(l26);
        reachable26 = false;

        l27 = new MapLocation(start.x + 3, start.y - 0);
        exists27 = rc.onTheMap(l27) && rc.sensePassability(l27);
        reachable27 = false;

        l28 = new MapLocation(start.x - 3, start.y - 1);
        exists28 = rc.onTheMap(l28) && rc.sensePassability(l28);
        reachable28 = false;

        l29 = new MapLocation(start.x - 2, start.y - 1);
        exists29 = rc.onTheMap(l29) && rc.sensePassability(l29);
        reachable29 = false;

        l30 = new MapLocation(start.x - 1, start.y - 1);
        exists30 = rc.onTheMap(l30) && rc.sensePassability(l30);
        reachable30 = false;

        l31 = new MapLocation(start.x - 0, start.y - 1);
        exists31 = rc.onTheMap(l31) && rc.sensePassability(l31);
        reachable31 = false;

        l32 = new MapLocation(start.x + 1, start.y - 1);
        exists32 = rc.onTheMap(l32) && rc.sensePassability(l32);
        reachable32 = false;

        l33 = new MapLocation(start.x + 2, start.y - 1);
        exists33 = rc.onTheMap(l33) && rc.sensePassability(l33);
        reachable33 = false;

        l34 = new MapLocation(start.x + 3, start.y - 1);
        exists34 = rc.onTheMap(l34) && rc.sensePassability(l34);
        reachable34 = false;

        l35 = new MapLocation(start.x - 3, start.y - 2);
        exists35 = rc.onTheMap(l35) && rc.sensePassability(l35);
        reachable35 = false;

        l36 = new MapLocation(start.x - 2, start.y - 2);
        exists36 = rc.onTheMap(l36) && rc.sensePassability(l36);
        reachable36 = false;

        l37 = new MapLocation(start.x - 1, start.y - 2);
        exists37 = rc.onTheMap(l37) && rc.sensePassability(l37);
        reachable37 = false;

        l38 = new MapLocation(start.x - 0, start.y - 2);
        exists38 = rc.onTheMap(l38) && rc.sensePassability(l38);
        reachable38 = false;

        l39 = new MapLocation(start.x + 1, start.y - 2);
        exists39 = rc.onTheMap(l39) && rc.sensePassability(l39);
        reachable39 = false;

        l40 = new MapLocation(start.x + 2, start.y - 2);
        exists40 = rc.onTheMap(l40) && rc.sensePassability(l40);
        reachable40 = false;

        l41 = new MapLocation(start.x + 3, start.y - 2);
        exists41 = rc.onTheMap(l41) && rc.sensePassability(l41);
        reachable41 = false;

        l42 = new MapLocation(start.x - 3, start.y - 3);
        exists42 = rc.onTheMap(l42) && rc.sensePassability(l42);
        reachable42 = false;

        l43 = new MapLocation(start.x - 2, start.y - 3);
        exists43 = rc.onTheMap(l43) && rc.sensePassability(l43);
        reachable43 = false;

        l44 = new MapLocation(start.x - 1, start.y - 3);
        exists44 = rc.onTheMap(l44) && rc.sensePassability(l44);
        reachable44 = false;

        l45 = new MapLocation(start.x - 0, start.y - 3);
        exists45 = rc.onTheMap(l45) && rc.sensePassability(l45);
        reachable45 = false;

        l46 = new MapLocation(start.x + 1, start.y - 3);
        exists46 = rc.onTheMap(l46) && rc.sensePassability(l46);
        reachable46 = false;

        l47 = new MapLocation(start.x + 2, start.y - 3);
        exists47 = rc.onTheMap(l47) && rc.sensePassability(l47);
        reachable47 = false;

        l48 = new MapLocation(start.x + 3, start.y - 3);
        exists48 = rc.onTheMap(l48) && rc.sensePassability(l48);
        reachable48 = false;

        if(exists24)
        {
            if(exists16 && reachable24)
            {
                reachable16 = true;
            }
            if(exists17 && reachable24)
            {
                reachable17 = true;
            }
            if(exists18 && reachable24)
            {
                reachable18 = true;
            }
            if(exists23 && reachable24)
            {
                reachable23 = true;
            }
            if(exists25 && reachable24)
            {
                reachable25 = true;
            }
            if(exists30 && reachable24)
            {
                reachable30 = true;
            }
            if(exists31 && reachable24)
            {
                reachable31 = true;
            }
            if(exists32 && reachable24)
            {
                reachable32 = true;
            }
        }
        if(exists16)
        {
            if(exists8 && reachable16)
            {
                reachable8 = true;
            }
            if(exists9 && reachable16)
            {
                reachable9 = true;
            }
            if(exists10 && reachable16)
            {
                reachable10 = true;
            }
            if(exists15 && reachable16)
            {
                reachable15 = true;
            }
            if(exists17 && reachable16)
            {
                reachable17 = true;
            }
            if(exists22 && reachable16)
            {
                reachable22 = true;
            }
            if(exists23 && reachable16)
            {
                reachable23 = true;
            }
            if(exists24 && reachable16)
            {
                reachable24 = true;
            }
        }
        if(exists17)
        {
            if(exists9 && reachable17)
            {
                reachable9 = true;
            }
            if(exists10 && reachable17)
            {
                reachable10 = true;
            }
            if(exists11 && reachable17)
            {
                reachable11 = true;
            }
            if(exists16 && reachable17)
            {
                reachable16 = true;
            }
            if(exists18 && reachable17)
            {
                reachable18 = true;
            }
            if(exists23 && reachable17)
            {
                reachable23 = true;
            }
            if(exists24 && reachable17)
            {
                reachable24 = true;
            }
            if(exists25 && reachable17)
            {
                reachable25 = true;
            }
        }
        if(exists18)
        {
            if(exists10 && reachable18)
            {
                reachable10 = true;
            }
            if(exists11 && reachable18)
            {
                reachable11 = true;
            }
            if(exists12 && reachable18)
            {
                reachable12 = true;
            }
            if(exists17 && reachable18)
            {
                reachable17 = true;
            }
            if(exists19 && reachable18)
            {
                reachable19 = true;
            }
            if(exists24 && reachable18)
            {
                reachable24 = true;
            }
            if(exists25 && reachable18)
            {
                reachable25 = true;
            }
            if(exists26 && reachable18)
            {
                reachable26 = true;
            }
        }
        if(exists23)
        {
            if(exists15 && reachable23)
            {
                reachable15 = true;
            }
            if(exists16 && reachable23)
            {
                reachable16 = true;
            }
            if(exists17 && reachable23)
            {
                reachable17 = true;
            }
            if(exists22 && reachable23)
            {
                reachable22 = true;
            }
            if(exists24 && reachable23)
            {
                reachable24 = true;
            }
            if(exists29 && reachable23)
            {
                reachable29 = true;
            }
            if(exists30 && reachable23)
            {
                reachable30 = true;
            }
            if(exists31 && reachable23)
            {
                reachable31 = true;
            }
        }
        if(exists25)
        {
            if(exists17 && reachable25)
            {
                reachable17 = true;
            }
            if(exists18 && reachable25)
            {
                reachable18 = true;
            }
            if(exists19 && reachable25)
            {
                reachable19 = true;
            }
            if(exists24 && reachable25)
            {
                reachable24 = true;
            }
            if(exists26 && reachable25)
            {
                reachable26 = true;
            }
            if(exists31 && reachable25)
            {
                reachable31 = true;
            }
            if(exists32 && reachable25)
            {
                reachable32 = true;
            }
            if(exists33 && reachable25)
            {
                reachable33 = true;
            }
        }
        if(exists30)
        {
            if(exists22 && reachable30)
            {
                reachable22 = true;
            }
            if(exists23 && reachable30)
            {
                reachable23 = true;
            }
            if(exists24 && reachable30)
            {
                reachable24 = true;
            }
            if(exists29 && reachable30)
            {
                reachable29 = true;
            }
            if(exists31 && reachable30)
            {
                reachable31 = true;
            }
            if(exists36 && reachable30)
            {
                reachable36 = true;
            }
            if(exists37 && reachable30)
            {
                reachable37 = true;
            }
            if(exists38 && reachable30)
            {
                reachable38 = true;
            }
        }
        if(exists31)
        {
            if(exists23 && reachable31)
            {
                reachable23 = true;
            }
            if(exists24 && reachable31)
            {
                reachable24 = true;
            }
            if(exists25 && reachable31)
            {
                reachable25 = true;
            }
            if(exists30 && reachable31)
            {
                reachable30 = true;
            }
            if(exists32 && reachable31)
            {
                reachable32 = true;
            }
            if(exists37 && reachable31)
            {
                reachable37 = true;
            }
            if(exists38 && reachable31)
            {
                reachable38 = true;
            }
            if(exists39 && reachable31)
            {
                reachable39 = true;
            }
        }
        if(exists32)
        {
            if(exists24 && reachable32)
            {
                reachable24 = true;
            }
            if(exists25 && reachable32)
            {
                reachable25 = true;
            }
            if(exists26 && reachable32)
            {
                reachable26 = true;
            }
            if(exists31 && reachable32)
            {
                reachable31 = true;
            }
            if(exists33 && reachable32)
            {
                reachable33 = true;
            }
            if(exists38 && reachable32)
            {
                reachable38 = true;
            }
            if(exists39 && reachable32)
            {
                reachable39 = true;
            }
            if(exists40 && reachable32)
            {
                reachable40 = true;
            }
        }
        if(exists8)
        {
            if(exists0 && reachable8)
            {
                reachable0 = true;
            }
            if(exists1 && reachable8)
            {
                reachable1 = true;
            }
            if(exists2 && reachable8)
            {
                reachable2 = true;
            }
            if(exists7 && reachable8)
            {
                reachable7 = true;
            }
            if(exists9 && reachable8)
            {
                reachable9 = true;
            }
            if(exists14 && reachable8)
            {
                reachable14 = true;
            }
            if(exists15 && reachable8)
            {
                reachable15 = true;
            }
            if(exists16 && reachable8)
            {
                reachable16 = true;
            }
        }
        if(exists9)
        {
            if(exists1 && reachable9)
            {
                reachable1 = true;
            }
            if(exists2 && reachable9)
            {
                reachable2 = true;
            }
            if(exists3 && reachable9)
            {
                reachable3 = true;
            }
            if(exists8 && reachable9)
            {
                reachable8 = true;
            }
            if(exists10 && reachable9)
            {
                reachable10 = true;
            }
            if(exists15 && reachable9)
            {
                reachable15 = true;
            }
            if(exists16 && reachable9)
            {
                reachable16 = true;
            }
            if(exists17 && reachable9)
            {
                reachable17 = true;
            }
        }
        if(exists10)
        {
            if(exists2 && reachable10)
            {
                reachable2 = true;
            }
            if(exists3 && reachable10)
            {
                reachable3 = true;
            }
            if(exists4 && reachable10)
            {
                reachable4 = true;
            }
            if(exists9 && reachable10)
            {
                reachable9 = true;
            }
            if(exists11 && reachable10)
            {
                reachable11 = true;
            }
            if(exists16 && reachable10)
            {
                reachable16 = true;
            }
            if(exists17 && reachable10)
            {
                reachable17 = true;
            }
            if(exists18 && reachable10)
            {
                reachable18 = true;
            }
        }
        if(exists15)
        {
            if(exists7 && reachable15)
            {
                reachable7 = true;
            }
            if(exists8 && reachable15)
            {
                reachable8 = true;
            }
            if(exists9 && reachable15)
            {
                reachable9 = true;
            }
            if(exists14 && reachable15)
            {
                reachable14 = true;
            }
            if(exists16 && reachable15)
            {
                reachable16 = true;
            }
            if(exists21 && reachable15)
            {
                reachable21 = true;
            }
            if(exists22 && reachable15)
            {
                reachable22 = true;
            }
            if(exists23 && reachable15)
            {
                reachable23 = true;
            }
        }
        if(exists22)
        {
            if(exists14 && reachable22)
            {
                reachable14 = true;
            }
            if(exists15 && reachable22)
            {
                reachable15 = true;
            }
            if(exists16 && reachable22)
            {
                reachable16 = true;
            }
            if(exists21 && reachable22)
            {
                reachable21 = true;
            }
            if(exists23 && reachable22)
            {
                reachable23 = true;
            }
            if(exists28 && reachable22)
            {
                reachable28 = true;
            }
            if(exists29 && reachable22)
            {
                reachable29 = true;
            }
            if(exists30 && reachable22)
            {
                reachable30 = true;
            }
        }
        if(exists11)
        {
            if(exists3 && reachable11)
            {
                reachable3 = true;
            }
            if(exists4 && reachable11)
            {
                reachable4 = true;
            }
            if(exists5 && reachable11)
            {
                reachable5 = true;
            }
            if(exists10 && reachable11)
            {
                reachable10 = true;
            }
            if(exists12 && reachable11)
            {
                reachable12 = true;
            }
            if(exists17 && reachable11)
            {
                reachable17 = true;
            }
            if(exists18 && reachable11)
            {
                reachable18 = true;
            }
            if(exists19 && reachable11)
            {
                reachable19 = true;
            }
        }
        if(exists12)
        {
            if(exists4 && reachable12)
            {
                reachable4 = true;
            }
            if(exists5 && reachable12)
            {
                reachable5 = true;
            }
            if(exists6 && reachable12)
            {
                reachable6 = true;
            }
            if(exists11 && reachable12)
            {
                reachable11 = true;
            }
            if(exists13 && reachable12)
            {
                reachable13 = true;
            }
            if(exists18 && reachable12)
            {
                reachable18 = true;
            }
            if(exists19 && reachable12)
            {
                reachable19 = true;
            }
            if(exists20 && reachable12)
            {
                reachable20 = true;
            }
        }
        if(exists19)
        {
            if(exists11 && reachable19)
            {
                reachable11 = true;
            }
            if(exists12 && reachable19)
            {
                reachable12 = true;
            }
            if(exists13 && reachable19)
            {
                reachable13 = true;
            }
            if(exists18 && reachable19)
            {
                reachable18 = true;
            }
            if(exists20 && reachable19)
            {
                reachable20 = true;
            }
            if(exists25 && reachable19)
            {
                reachable25 = true;
            }
            if(exists26 && reachable19)
            {
                reachable26 = true;
            }
            if(exists27 && reachable19)
            {
                reachable27 = true;
            }
        }
        if(exists26)
        {
            if(exists18 && reachable26)
            {
                reachable18 = true;
            }
            if(exists19 && reachable26)
            {
                reachable19 = true;
            }
            if(exists20 && reachable26)
            {
                reachable20 = true;
            }
            if(exists25 && reachable26)
            {
                reachable25 = true;
            }
            if(exists27 && reachable26)
            {
                reachable27 = true;
            }
            if(exists32 && reachable26)
            {
                reachable32 = true;
            }
            if(exists33 && reachable26)
            {
                reachable33 = true;
            }
            if(exists34 && reachable26)
            {
                reachable34 = true;
            }
        }
        if(exists29)
        {
            if(exists21 && reachable29)
            {
                reachable21 = true;
            }
            if(exists22 && reachable29)
            {
                reachable22 = true;
            }
            if(exists23 && reachable29)
            {
                reachable23 = true;
            }
            if(exists28 && reachable29)
            {
                reachable28 = true;
            }
            if(exists30 && reachable29)
            {
                reachable30 = true;
            }
            if(exists35 && reachable29)
            {
                reachable35 = true;
            }
            if(exists36 && reachable29)
            {
                reachable36 = true;
            }
            if(exists37 && reachable29)
            {
                reachable37 = true;
            }
        }
        if(exists33)
        {
            if(exists25 && reachable33)
            {
                reachable25 = true;
            }
            if(exists26 && reachable33)
            {
                reachable26 = true;
            }
            if(exists27 && reachable33)
            {
                reachable27 = true;
            }
            if(exists32 && reachable33)
            {
                reachable32 = true;
            }
            if(exists34 && reachable33)
            {
                reachable34 = true;
            }
            if(exists39 && reachable33)
            {
                reachable39 = true;
            }
            if(exists40 && reachable33)
            {
                reachable40 = true;
            }
            if(exists41 && reachable33)
            {
                reachable41 = true;
            }
        }
        if(exists36)
        {
            if(exists28 && reachable36)
            {
                reachable28 = true;
            }
            if(exists29 && reachable36)
            {
                reachable29 = true;
            }
            if(exists30 && reachable36)
            {
                reachable30 = true;
            }
            if(exists35 && reachable36)
            {
                reachable35 = true;
            }
            if(exists37 && reachable36)
            {
                reachable37 = true;
            }
            if(exists42 && reachable36)
            {
                reachable42 = true;
            }
            if(exists43 && reachable36)
            {
                reachable43 = true;
            }
            if(exists44 && reachable36)
            {
                reachable44 = true;
            }
        }
        if(exists37)
        {
            if(exists29 && reachable37)
            {
                reachable29 = true;
            }
            if(exists30 && reachable37)
            {
                reachable30 = true;
            }
            if(exists31 && reachable37)
            {
                reachable31 = true;
            }
            if(exists36 && reachable37)
            {
                reachable36 = true;
            }
            if(exists38 && reachable37)
            {
                reachable38 = true;
            }
            if(exists43 && reachable37)
            {
                reachable43 = true;
            }
            if(exists44 && reachable37)
            {
                reachable44 = true;
            }
            if(exists45 && reachable37)
            {
                reachable45 = true;
            }
        }
        if(exists38)
        {
            if(exists30 && reachable38)
            {
                reachable30 = true;
            }
            if(exists31 && reachable38)
            {
                reachable31 = true;
            }
            if(exists32 && reachable38)
            {
                reachable32 = true;
            }
            if(exists37 && reachable38)
            {
                reachable37 = true;
            }
            if(exists39 && reachable38)
            {
                reachable39 = true;
            }
            if(exists44 && reachable38)
            {
                reachable44 = true;
            }
            if(exists45 && reachable38)
            {
                reachable45 = true;
            }
            if(exists46 && reachable38)
            {
                reachable46 = true;
            }
        }
        if(exists39)
        {
            if(exists31 && reachable39)
            {
                reachable31 = true;
            }
            if(exists32 && reachable39)
            {
                reachable32 = true;
            }
            if(exists33 && reachable39)
            {
                reachable33 = true;
            }
            if(exists38 && reachable39)
            {
                reachable38 = true;
            }
            if(exists40 && reachable39)
            {
                reachable40 = true;
            }
            if(exists45 && reachable39)
            {
                reachable45 = true;
            }
            if(exists46 && reachable39)
            {
                reachable46 = true;
            }
            if(exists47 && reachable39)
            {
                reachable47 = true;
            }
        }
        if(exists40)
        {
            if(exists32 && reachable40)
            {
                reachable32 = true;
            }
            if(exists33 && reachable40)
            {
                reachable33 = true;
            }
            if(exists34 && reachable40)
            {
                reachable34 = true;
            }
            if(exists39 && reachable40)
            {
                reachable39 = true;
            }
            if(exists41 && reachable40)
            {
                reachable41 = true;
            }
            if(exists46 && reachable40)
            {
                reachable46 = true;
            }
            if(exists47 && reachable40)
            {
                reachable47 = true;
            }
            if(exists48 && reachable40)
            {
                reachable48 = true;
            }
        }
        if(exists0)
        {
            if(exists1 && reachable0)
            {
                reachable1 = true;
            }
            if(exists7 && reachable0)
            {
                reachable7 = true;
            }
            if(exists8 && reachable0)
            {
                reachable8 = true;
            }
        }
        if(exists1)
        {
            if(exists0 && reachable1)
            {
                reachable0 = true;
            }
            if(exists2 && reachable1)
            {
                reachable2 = true;
            }
            if(exists7 && reachable1)
            {
                reachable7 = true;
            }
            if(exists8 && reachable1)
            {
                reachable8 = true;
            }
            if(exists9 && reachable1)
            {
                reachable9 = true;
            }
        }
        if(exists2)
        {
            if(exists1 && reachable2)
            {
                reachable1 = true;
            }
            if(exists3 && reachable2)
            {
                reachable3 = true;
            }
            if(exists8 && reachable2)
            {
                reachable8 = true;
            }
            if(exists9 && reachable2)
            {
                reachable9 = true;
            }
            if(exists10 && reachable2)
            {
                reachable10 = true;
            }
        }
        if(exists7)
        {
            if(exists0 && reachable7)
            {
                reachable0 = true;
            }
            if(exists1 && reachable7)
            {
                reachable1 = true;
            }
            if(exists8 && reachable7)
            {
                reachable8 = true;
            }
            if(exists14 && reachable7)
            {
                reachable14 = true;
            }
            if(exists15 && reachable7)
            {
                reachable15 = true;
            }
        }
        if(exists14)
        {
            if(exists7 && reachable14)
            {
                reachable7 = true;
            }
            if(exists8 && reachable14)
            {
                reachable8 = true;
            }
            if(exists15 && reachable14)
            {
                reachable15 = true;
            }
            if(exists21 && reachable14)
            {
                reachable21 = true;
            }
            if(exists22 && reachable14)
            {
                reachable22 = true;
            }
        }
        if(exists3)
        {
            if(exists2 && reachable3)
            {
                reachable2 = true;
            }
            if(exists4 && reachable3)
            {
                reachable4 = true;
            }
            if(exists9 && reachable3)
            {
                reachable9 = true;
            }
            if(exists10 && reachable3)
            {
                reachable10 = true;
            }
            if(exists11 && reachable3)
            {
                reachable11 = true;
            }
        }
        if(exists4)
        {
            if(exists3 && reachable4)
            {
                reachable3 = true;
            }
            if(exists5 && reachable4)
            {
                reachable5 = true;
            }
            if(exists10 && reachable4)
            {
                reachable10 = true;
            }
            if(exists11 && reachable4)
            {
                reachable11 = true;
            }
            if(exists12 && reachable4)
            {
                reachable12 = true;
            }
        }
        if(exists21)
        {
            if(exists14 && reachable21)
            {
                reachable14 = true;
            }
            if(exists15 && reachable21)
            {
                reachable15 = true;
            }
            if(exists22 && reachable21)
            {
                reachable22 = true;
            }
            if(exists28 && reachable21)
            {
                reachable28 = true;
            }
            if(exists29 && reachable21)
            {
                reachable29 = true;
            }
        }
        if(exists28)
        {
            if(exists21 && reachable28)
            {
                reachable21 = true;
            }
            if(exists22 && reachable28)
            {
                reachable22 = true;
            }
            if(exists29 && reachable28)
            {
                reachable29 = true;
            }
            if(exists35 && reachable28)
            {
                reachable35 = true;
            }
            if(exists36 && reachable28)
            {
                reachable36 = true;
            }
        }
        if(exists5)
        {
            if(exists4 && reachable5)
            {
                reachable4 = true;
            }
            if(exists6 && reachable5)
            {
                reachable6 = true;
            }
            if(exists11 && reachable5)
            {
                reachable11 = true;
            }
            if(exists12 && reachable5)
            {
                reachable12 = true;
            }
            if(exists13 && reachable5)
            {
                reachable13 = true;
            }
        }
        if(exists6)
        {
            if(exists5 && reachable6)
            {
                reachable5 = true;
            }
            if(exists12 && reachable6)
            {
                reachable12 = true;
            }
            if(exists13 && reachable6)
            {
                reachable13 = true;
            }
        }
        if(exists13)
        {
            if(exists5 && reachable13)
            {
                reachable5 = true;
            }
            if(exists6 && reachable13)
            {
                reachable6 = true;
            }
            if(exists12 && reachable13)
            {
                reachable12 = true;
            }
            if(exists19 && reachable13)
            {
                reachable19 = true;
            }
            if(exists20 && reachable13)
            {
                reachable20 = true;
            }
        }
        if(exists20)
        {
            if(exists12 && reachable20)
            {
                reachable12 = true;
            }
            if(exists13 && reachable20)
            {
                reachable13 = true;
            }
            if(exists19 && reachable20)
            {
                reachable19 = true;
            }
            if(exists26 && reachable20)
            {
                reachable26 = true;
            }
            if(exists27 && reachable20)
            {
                reachable27 = true;
            }
        }
        if(exists27)
        {
            if(exists19 && reachable27)
            {
                reachable19 = true;
            }
            if(exists20 && reachable27)
            {
                reachable20 = true;
            }
            if(exists26 && reachable27)
            {
                reachable26 = true;
            }
            if(exists33 && reachable27)
            {
                reachable33 = true;
            }
            if(exists34 && reachable27)
            {
                reachable34 = true;
            }
        }
        if(exists34)
        {
            if(exists26 && reachable34)
            {
                reachable26 = true;
            }
            if(exists27 && reachable34)
            {
                reachable27 = true;
            }
            if(exists33 && reachable34)
            {
                reachable33 = true;
            }
            if(exists40 && reachable34)
            {
                reachable40 = true;
            }
            if(exists41 && reachable34)
            {
                reachable41 = true;
            }
        }
        if(exists35)
        {
            if(exists28 && reachable35)
            {
                reachable28 = true;
            }
            if(exists29 && reachable35)
            {
                reachable29 = true;
            }
            if(exists36 && reachable35)
            {
                reachable36 = true;
            }
            if(exists42 && reachable35)
            {
                reachable42 = true;
            }
            if(exists43 && reachable35)
            {
                reachable43 = true;
            }
        }
        if(exists41)
        {
            if(exists33 && reachable41)
            {
                reachable33 = true;
            }
            if(exists34 && reachable41)
            {
                reachable34 = true;
            }
            if(exists40 && reachable41)
            {
                reachable40 = true;
            }
            if(exists47 && reachable41)
            {
                reachable47 = true;
            }
            if(exists48 && reachable41)
            {
                reachable48 = true;
            }
        }
        if(exists42)
        {
            if(exists35 && reachable42)
            {
                reachable35 = true;
            }
            if(exists36 && reachable42)
            {
                reachable36 = true;
            }
            if(exists43 && reachable42)
            {
                reachable43 = true;
            }
        }
        if(exists43)
        {
            if(exists35 && reachable43)
            {
                reachable35 = true;
            }
            if(exists36 && reachable43)
            {
                reachable36 = true;
            }
            if(exists37 && reachable43)
            {
                reachable37 = true;
            }
            if(exists42 && reachable43)
            {
                reachable42 = true;
            }
            if(exists44 && reachable43)
            {
                reachable44 = true;
            }
        }
        if(exists44)
        {
            if(exists36 && reachable44)
            {
                reachable36 = true;
            }
            if(exists37 && reachable44)
            {
                reachable37 = true;
            }
            if(exists38 && reachable44)
            {
                reachable38 = true;
            }
            if(exists43 && reachable44)
            {
                reachable43 = true;
            }
            if(exists45 && reachable44)
            {
                reachable45 = true;
            }
        }
        if(exists45)
        {
            if(exists37 && reachable45)
            {
                reachable37 = true;
            }
            if(exists38 && reachable45)
            {
                reachable38 = true;
            }
            if(exists39 && reachable45)
            {
                reachable39 = true;
            }
            if(exists44 && reachable45)
            {
                reachable44 = true;
            }
            if(exists46 && reachable45)
            {
                reachable46 = true;
            }
        }
        if(exists46)
        {
            if(exists38 && reachable46)
            {
                reachable38 = true;
            }
            if(exists39 && reachable46)
            {
                reachable39 = true;
            }
            if(exists40 && reachable46)
            {
                reachable40 = true;
            }
            if(exists45 && reachable46)
            {
                reachable45 = true;
            }
            if(exists47 && reachable46)
            {
                reachable47 = true;
            }
        }
        if(exists47)
        {
            if(exists39 && reachable47)
            {
                reachable39 = true;
            }
            if(exists40 && reachable47)
            {
                reachable40 = true;
            }
            if(exists41 && reachable47)
            {
                reachable41 = true;
            }
            if(exists46 && reachable47)
            {
                reachable46 = true;
            }
            if(exists48 && reachable47)
            {
                reachable48 = true;
            }
        }
        if(exists48)
        {
            if(exists40 && reachable48)
            {
                reachable40 = true;
            }
            if(exists41 && reachable48)
            {
                reachable41 = true;
            }
            if(exists47 && reachable48)
            {
                reachable47 = true;
            }
        }

        if(exists0 && !reachable0)
        {
            overWall.add(l0);
            rc.setIndicatorDot(l0, 0, 255,0);
        }
        if(exists1 && !reachable1)
        {
            overWall.add(l1);
            rc.setIndicatorDot(l1, 0, 255,0);
        }
        if(exists2 && !reachable2)
        {
            overWall.add(l2);
            rc.setIndicatorDot(l2, 0, 255,0);
        }
        if(exists3 && !reachable3)
        {
            overWall.add(l3);
            rc.setIndicatorDot(l3, 0, 255,0);
        }
        if(exists4 && !reachable4)
        {
            overWall.add(l4);
            rc.setIndicatorDot(l4, 0, 255,0);
        }
        if(exists5 && !reachable5)
        {
            overWall.add(l5);
            rc.setIndicatorDot(l5, 0, 255,0);
        }
        if(exists6 && !reachable6)
        {
            overWall.add(l6);
            rc.setIndicatorDot(l6, 0, 255,0);
        }
        if(exists7 && !reachable7)
        {
            overWall.add(l7);
            rc.setIndicatorDot(l7, 0, 255,0);
        }
        if(exists8 && !reachable8)
        {
            overWall.add(l8);
            rc.setIndicatorDot(l8, 0, 255,0);
        }
        if(exists9 && !reachable9)
        {
            overWall.add(l9);
            rc.setIndicatorDot(l9, 0, 255,0);
        }
        if(exists10 && !reachable10)
        {
            overWall.add(l10);
            rc.setIndicatorDot(l10, 0, 255,0);
        }
        if(exists11 && !reachable11)
        {
            overWall.add(l11);
            rc.setIndicatorDot(l11, 0, 255,0);
        }
        if(exists12 && !reachable12)
        {
            overWall.add(l12);
            rc.setIndicatorDot(l12, 0, 255,0);
        }
        if(exists13 && !reachable13)
        {
            overWall.add(l13);
            rc.setIndicatorDot(l13, 0, 255,0);
        }
        if(exists14 && !reachable14)
        {
            overWall.add(l14);
            rc.setIndicatorDot(l14, 0, 255,0);
        }
        if(exists15 && !reachable15)
        {
            overWall.add(l15);
            rc.setIndicatorDot(l15, 0, 255,0);
        }
        if(exists16 && !reachable16)
        {
            overWall.add(l16);
            rc.setIndicatorDot(l16, 0, 255,0);
        }
        if(exists17 && !reachable17)
        {
            overWall.add(l17);
            rc.setIndicatorDot(l17, 0, 255,0);
        }
        if(exists18 && !reachable18)
        {
            overWall.add(l18);
            rc.setIndicatorDot(l18, 0, 255,0);
        }
        if(exists19 && !reachable19)
        {
            overWall.add(l19);
            rc.setIndicatorDot(l19, 0, 255,0);
        }
        if(exists20 && !reachable20)
        {
            overWall.add(l20);
            rc.setIndicatorDot(l20, 0, 255,0);
        }
        if(exists21 && !reachable21)
        {
            overWall.add(l21);
            rc.setIndicatorDot(l21, 0, 255,0);
        }
        if(exists22 && !reachable22)
        {
            overWall.add(l22);
            rc.setIndicatorDot(l22, 0, 255,0);
        }
        if(exists23 && !reachable23)
        {
            overWall.add(l23);
            rc.setIndicatorDot(l23, 0, 255,0);
        }
        if(exists24 && !reachable24)
        {
            overWall.add(l24);
            rc.setIndicatorDot(l24, 0, 255,0);
        }
        if(exists25 && !reachable25)
        {
            overWall.add(l25);
            rc.setIndicatorDot(l25, 0, 255,0);
        }
        if(exists26 && !reachable26)
        {
            overWall.add(l26);
            rc.setIndicatorDot(l26, 0, 255,0);
        }
        if(exists27 && !reachable27)
        {
            overWall.add(l27);
            rc.setIndicatorDot(l27, 0, 255,0);
        }
        if(exists28 && !reachable28)
        {
            overWall.add(l28);
            rc.setIndicatorDot(l28, 0, 255,0);
        }
        if(exists29 && !reachable29)
        {
            overWall.add(l29);
            rc.setIndicatorDot(l29, 0, 255,0);
        }
        if(exists30 && !reachable30)
        {
            overWall.add(l30);
            rc.setIndicatorDot(l30, 0, 255,0);
        }
        if(exists31 && !reachable31)
        {
            overWall.add(l31);
            rc.setIndicatorDot(l31, 0, 255,0);
        }
        if(exists32 && !reachable32)
        {
            overWall.add(l32);
            rc.setIndicatorDot(l32, 0, 255,0);
        }
        if(exists33 && !reachable33)
        {
            overWall.add(l33);
            rc.setIndicatorDot(l33, 0, 255,0);
        }
        if(exists34 && !reachable34)
        {
            overWall.add(l34);
            rc.setIndicatorDot(l34, 0, 255,0);
        }
        if(exists35 && !reachable35)
        {
            overWall.add(l35);
            rc.setIndicatorDot(l35, 0, 255,0);
        }
        if(exists36 && !reachable36)
        {
            overWall.add(l36);
            rc.setIndicatorDot(l36, 0, 255,0);
        }
        if(exists37 && !reachable37)
        {
            overWall.add(l37);
            rc.setIndicatorDot(l37, 0, 255,0);
        }
        if(exists38 && !reachable38)
        {
            overWall.add(l38);
            rc.setIndicatorDot(l38, 0, 255,0);
        }
        if(exists39 && !reachable39)
        {
            overWall.add(l39);
            rc.setIndicatorDot(l39, 0, 255,0);
        }
        if(exists40 && !reachable40)
        {
            overWall.add(l40);
            rc.setIndicatorDot(l40, 0, 255,0);
        }
        if(exists41 && !reachable41)
        {
            overWall.add(l41);
            rc.setIndicatorDot(l41, 0, 255,0);
        }
        if(exists42 && !reachable42)
        {
            overWall.add(l42);
            rc.setIndicatorDot(l42, 0, 255,0);
        }
        if(exists43 && !reachable43)
        {
            overWall.add(l43);
            rc.setIndicatorDot(l43, 0, 255,0);
        }
        if(exists44 && !reachable44)
        {
            overWall.add(l44);
            rc.setIndicatorDot(l44, 0, 255,0);
        }
        if(exists45 && !reachable45)
        {
            overWall.add(l45);
            rc.setIndicatorDot(l45, 0, 255,0);
        }
        if(exists46 && !reachable46)
        {
            overWall.add(l46);
            rc.setIndicatorDot(l46, 0, 255,0);
        }
        if(exists47 && !reachable47)
        {
            overWall.add(l47);
            rc.setIndicatorDot(l47, 0, 255,0);
        }
        if(exists48 && !reachable48)
        {
            overWall.add(l48);
            rc.setIndicatorDot(l48, 0, 255,0);
        }
        return overWall;
    }
}
