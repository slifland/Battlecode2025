package PathfindingTest;
import battlecode.common.*;
import java.util.*;
public class WallChecker_FullVision
{
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

    static MapLocation l49;
    static boolean exists49;
    static boolean reachable49;

    static MapLocation l50;
    static boolean exists50;
    static boolean reachable50;

    static MapLocation l51;
    static boolean exists51;
    static boolean reachable51;

    static MapLocation l52;
    static boolean exists52;
    static boolean reachable52;

    static MapLocation l53;
    static boolean exists53;
    static boolean reachable53;

    static MapLocation l54;
    static boolean exists54;
    static boolean reachable54;

    static MapLocation l55;
    static boolean exists55;
    static boolean reachable55;

    static MapLocation l56;
    static boolean exists56;
    static boolean reachable56;

    static MapLocation l57;
    static boolean exists57;
    static boolean reachable57;

    static MapLocation l58;
    static boolean exists58;
    static boolean reachable58;

    static MapLocation l59;
    static boolean exists59;
    static boolean reachable59;

    static MapLocation l60;
    static boolean exists60;
    static boolean reachable60;

    static MapLocation l61;
    static boolean exists61;
    static boolean reachable61;

    static MapLocation l62;
    static boolean exists62;
    static boolean reachable62;

    static MapLocation l64;
    static boolean exists64;
    static boolean reachable64;

    static MapLocation l65;
    static boolean exists65;
    static boolean reachable65;

    static MapLocation l66;
    static boolean exists66;
    static boolean reachable66;

    static MapLocation l67;
    static boolean exists67;
    static boolean reachable67;

    static MapLocation l68;
    static boolean exists68;
    static boolean reachable68;

    static MapLocation l69;
    static boolean exists69;
    static boolean reachable69;

    static MapLocation l70;
    static boolean exists70;
    static boolean reachable70;

    static MapLocation l74;
    static boolean exists74;
    static boolean reachable74;

    static MapLocation l75;
    static boolean exists75;
    static boolean reachable75;

    static MapLocation l76;
    static boolean exists76;
    static boolean reachable76;

    static MapLocation l77;
    static boolean exists77;
    static boolean reachable77;

    static MapLocation l78;
    static boolean exists78;
    static boolean reachable78;

    public static FastIterableLocSet findOverWallTiles(RobotController rc) throws GameActionException
    {
        FastIterableLocSet overWall = new FastIterableLocSet();
        MapLocation start = rc.getLocation();

        l2 = new MapLocation(start.x - 2, start.y + 4);
        exists2 = rc.onTheMap(l2) && rc.sensePassability(l2);
        reachable2 = false;

        l3 = new MapLocation(start.x - 1, start.y + 4);
        exists3 = rc.onTheMap(l3) && rc.sensePassability(l3);
        reachable3 = false;

        l4 = new MapLocation(start.x - 0, start.y + 4);
        exists4 = rc.onTheMap(l4) && rc.sensePassability(l4);
        reachable4 = false;

        l5 = new MapLocation(start.x + 1, start.y + 4);
        exists5 = rc.onTheMap(l5) && rc.sensePassability(l5);
        reachable5 = false;

        l6 = new MapLocation(start.x + 2, start.y + 4);
        exists6 = rc.onTheMap(l6) && rc.sensePassability(l6);
        reachable6 = false;

        l10 = new MapLocation(start.x - 3, start.y + 3);
        exists10 = rc.onTheMap(l10) && rc.sensePassability(l10);
        reachable10 = false;

        l11 = new MapLocation(start.x - 2, start.y + 3);
        exists11 = rc.onTheMap(l11) && rc.sensePassability(l11);
        reachable11 = false;

        l12 = new MapLocation(start.x - 1, start.y + 3);
        exists12 = rc.onTheMap(l12) && rc.sensePassability(l12);
        reachable12 = false;

        l13 = new MapLocation(start.x - 0, start.y + 3);
        exists13 = rc.onTheMap(l13) && rc.sensePassability(l13);
        reachable13 = false;

        l14 = new MapLocation(start.x + 1, start.y + 3);
        exists14 = rc.onTheMap(l14) && rc.sensePassability(l14);
        reachable14 = false;

        l15 = new MapLocation(start.x + 2, start.y + 3);
        exists15 = rc.onTheMap(l15) && rc.sensePassability(l15);
        reachable15 = false;

        l16 = new MapLocation(start.x + 3, start.y + 3);
        exists16 = rc.onTheMap(l16) && rc.sensePassability(l16);
        reachable16 = false;

        l18 = new MapLocation(start.x - 4, start.y + 2);
        exists18 = rc.onTheMap(l18) && rc.sensePassability(l18);
        reachable18 = false;

        l19 = new MapLocation(start.x - 3, start.y + 2);
        exists19 = rc.onTheMap(l19) && rc.sensePassability(l19);
        reachable19 = false;

        l20 = new MapLocation(start.x - 2, start.y + 2);
        exists20 = rc.onTheMap(l20) && rc.sensePassability(l20);
        reachable20 = false;

        l21 = new MapLocation(start.x - 1, start.y + 2);
        exists21 = rc.onTheMap(l21) && rc.sensePassability(l21);
        reachable21 = false;

        l22 = new MapLocation(start.x - 0, start.y + 2);
        exists22 = rc.onTheMap(l22) && rc.sensePassability(l22);
        reachable22 = false;

        l23 = new MapLocation(start.x + 1, start.y + 2);
        exists23 = rc.onTheMap(l23) && rc.sensePassability(l23);
        reachable23 = false;

        l24 = new MapLocation(start.x + 2, start.y + 2);
        exists24 = rc.onTheMap(l24) && rc.sensePassability(l24);
        reachable24 = false;

        l25 = new MapLocation(start.x + 3, start.y + 2);
        exists25 = rc.onTheMap(l25) && rc.sensePassability(l25);
        reachable25 = false;

        l26 = new MapLocation(start.x + 4, start.y + 2);
        exists26 = rc.onTheMap(l26) && rc.sensePassability(l26);
        reachable26 = false;

        l27 = new MapLocation(start.x - 4, start.y + 1);
        exists27 = rc.onTheMap(l27) && rc.sensePassability(l27);
        reachable27 = false;

        l28 = new MapLocation(start.x - 3, start.y + 1);
        exists28 = rc.onTheMap(l28) && rc.sensePassability(l28);
        reachable28 = false;

        l29 = new MapLocation(start.x - 2, start.y + 1);
        exists29 = rc.onTheMap(l29) && rc.sensePassability(l29);
        reachable29 = false;

        l30 = new MapLocation(start.x - 1, start.y + 1);
        exists30 = rc.onTheMap(l30) && rc.sensePassability(l30);
        reachable30 = false;

        l31 = new MapLocation(start.x - 0, start.y + 1);
        exists31 = rc.onTheMap(l31) && rc.sensePassability(l31);
        reachable31 = false;

        l32 = new MapLocation(start.x + 1, start.y + 1);
        exists32 = rc.onTheMap(l32) && rc.sensePassability(l32);
        reachable32 = false;

        l33 = new MapLocation(start.x + 2, start.y + 1);
        exists33 = rc.onTheMap(l33) && rc.sensePassability(l33);
        reachable33 = false;

        l34 = new MapLocation(start.x + 3, start.y + 1);
        exists34 = rc.onTheMap(l34) && rc.sensePassability(l34);
        reachable34 = false;

        l35 = new MapLocation(start.x + 4, start.y + 1);
        exists35 = rc.onTheMap(l35) && rc.sensePassability(l35);
        reachable35 = false;

        l36 = new MapLocation(start.x - 4, start.y - 0);
        exists36 = rc.onTheMap(l36) && rc.sensePassability(l36);
        reachable36 = false;

        l37 = new MapLocation(start.x - 3, start.y - 0);
        exists37 = rc.onTheMap(l37) && rc.sensePassability(l37);
        reachable37 = false;

        l38 = new MapLocation(start.x - 2, start.y - 0);
        exists38 = rc.onTheMap(l38) && rc.sensePassability(l38);
        reachable38 = false;

        l39 = new MapLocation(start.x - 1, start.y - 0);
        exists39 = rc.onTheMap(l39) && rc.sensePassability(l39);
        reachable39 = false;

        l40 = start;
        exists40 = true;
        reachable40 = true;

        l41 = new MapLocation(start.x + 1, start.y - 0);
        exists41 = rc.onTheMap(l41) && rc.sensePassability(l41);
        reachable41 = false;

        l42 = new MapLocation(start.x + 2, start.y - 0);
        exists42 = rc.onTheMap(l42) && rc.sensePassability(l42);
        reachable42 = false;

        l43 = new MapLocation(start.x + 3, start.y - 0);
        exists43 = rc.onTheMap(l43) && rc.sensePassability(l43);
        reachable43 = false;

        l44 = new MapLocation(start.x + 4, start.y - 0);
        exists44 = rc.onTheMap(l44) && rc.sensePassability(l44);
        reachable44 = false;

        l45 = new MapLocation(start.x - 4, start.y - 1);
        exists45 = rc.onTheMap(l45) && rc.sensePassability(l45);
        reachable45 = false;

        l46 = new MapLocation(start.x - 3, start.y - 1);
        exists46 = rc.onTheMap(l46) && rc.sensePassability(l46);
        reachable46 = false;

        l47 = new MapLocation(start.x - 2, start.y - 1);
        exists47 = rc.onTheMap(l47) && rc.sensePassability(l47);
        reachable47 = false;

        l48 = new MapLocation(start.x - 1, start.y - 1);
        exists48 = rc.onTheMap(l48) && rc.sensePassability(l48);
        reachable48 = false;

        l49 = new MapLocation(start.x - 0, start.y - 1);
        exists49 = rc.onTheMap(l49) && rc.sensePassability(l49);
        reachable49 = false;

        l50 = new MapLocation(start.x + 1, start.y - 1);
        exists50 = rc.onTheMap(l50) && rc.sensePassability(l50);
        reachable50 = false;

        l51 = new MapLocation(start.x + 2, start.y - 1);
        exists51 = rc.onTheMap(l51) && rc.sensePassability(l51);
        reachable51 = false;

        l52 = new MapLocation(start.x + 3, start.y - 1);
        exists52 = rc.onTheMap(l52) && rc.sensePassability(l52);
        reachable52 = false;

        l53 = new MapLocation(start.x + 4, start.y - 1);
        exists53 = rc.onTheMap(l53) && rc.sensePassability(l53);
        reachable53 = false;

        l54 = new MapLocation(start.x - 4, start.y - 2);
        exists54 = rc.onTheMap(l54) && rc.sensePassability(l54);
        reachable54 = false;

        l55 = new MapLocation(start.x - 3, start.y - 2);
        exists55 = rc.onTheMap(l55) && rc.sensePassability(l55);
        reachable55 = false;

        l56 = new MapLocation(start.x - 2, start.y - 2);
        exists56 = rc.onTheMap(l56) && rc.sensePassability(l56);
        reachable56 = false;

        l57 = new MapLocation(start.x - 1, start.y - 2);
        exists57 = rc.onTheMap(l57) && rc.sensePassability(l57);
        reachable57 = false;

        l58 = new MapLocation(start.x - 0, start.y - 2);
        exists58 = rc.onTheMap(l58) && rc.sensePassability(l58);
        reachable58 = false;

        l59 = new MapLocation(start.x + 1, start.y - 2);
        exists59 = rc.onTheMap(l59) && rc.sensePassability(l59);
        reachable59 = false;

        l60 = new MapLocation(start.x + 2, start.y - 2);
        exists60 = rc.onTheMap(l60) && rc.sensePassability(l60);
        reachable60 = false;

        l61 = new MapLocation(start.x + 3, start.y - 2);
        exists61 = rc.onTheMap(l61) && rc.sensePassability(l61);
        reachable61 = false;

        l62 = new MapLocation(start.x + 4, start.y - 2);
        exists62 = rc.onTheMap(l62) && rc.sensePassability(l62);
        reachable62 = false;

        l64 = new MapLocation(start.x - 3, start.y - 3);
        exists64 = rc.onTheMap(l64) && rc.sensePassability(l64);
        reachable64 = false;

        l65 = new MapLocation(start.x - 2, start.y - 3);
        exists65 = rc.onTheMap(l65) && rc.sensePassability(l65);
        reachable65 = false;

        l66 = new MapLocation(start.x - 1, start.y - 3);
        exists66 = rc.onTheMap(l66) && rc.sensePassability(l66);
        reachable66 = false;

        l67 = new MapLocation(start.x - 0, start.y - 3);
        exists67 = rc.onTheMap(l67) && rc.sensePassability(l67);
        reachable67 = false;

        l68 = new MapLocation(start.x + 1, start.y - 3);
        exists68 = rc.onTheMap(l68) && rc.sensePassability(l68);
        reachable68 = false;

        l69 = new MapLocation(start.x + 2, start.y - 3);
        exists69 = rc.onTheMap(l69) && rc.sensePassability(l69);
        reachable69 = false;

        l70 = new MapLocation(start.x + 3, start.y - 3);
        exists70 = rc.onTheMap(l70) && rc.sensePassability(l70);
        reachable70 = false;

        l74 = new MapLocation(start.x - 2, start.y - 4);
        exists74 = rc.onTheMap(l74) && rc.sensePassability(l74);
        reachable74 = false;

        l75 = new MapLocation(start.x - 1, start.y - 4);
        exists75 = rc.onTheMap(l75) && rc.sensePassability(l75);
        reachable75 = false;

        l76 = new MapLocation(start.x - 0, start.y - 4);
        exists76 = rc.onTheMap(l76) && rc.sensePassability(l76);
        reachable76 = false;

        l77 = new MapLocation(start.x + 1, start.y - 4);
        exists77 = rc.onTheMap(l77) && rc.sensePassability(l77);
        reachable77 = false;

        l78 = new MapLocation(start.x + 2, start.y - 4);
        exists78 = rc.onTheMap(l78) && rc.sensePassability(l78);
        reachable78 = false;

        if(exists40)
        {
            if(exists30 && reachable40)
            {
                reachable30 = true;
            }
            if(exists31 && reachable40)
            {
                reachable31 = true;
            }
            if(exists32 && reachable40)
            {
                reachable32 = true;
            }
            if(exists39 && reachable40)
            {
                reachable39 = true;
            }
            if(exists41 && reachable40)
            {
                reachable41 = true;
            }
            if(exists48 && reachable40)
            {
                reachable48 = true;
            }
            if(exists49 && reachable40)
            {
                reachable49 = true;
            }
            if(exists50 && reachable40)
            {
                reachable50 = true;
            }
        }
        if(exists30)
        {
            if(exists20 && reachable30)
            {
                reachable20 = true;
            }
            if(exists21 && reachable30)
            {
                reachable21 = true;
            }
            if(exists22 && reachable30)
            {
                reachable22 = true;
            }
            if(exists29 && reachable30)
            {
                reachable29 = true;
            }
            if(exists31 && reachable30)
            {
                reachable31 = true;
            }
            if(exists38 && reachable30)
            {
                reachable38 = true;
            }
            if(exists39 && reachable30)
            {
                reachable39 = true;
            }
            if(exists40 && reachable30)
            {
                reachable40 = true;
            }
        }
        if(exists31)
        {
            if(exists21 && reachable31)
            {
                reachable21 = true;
            }
            if(exists22 && reachable31)
            {
                reachable22 = true;
            }
            if(exists23 && reachable31)
            {
                reachable23 = true;
            }
            if(exists30 && reachable31)
            {
                reachable30 = true;
            }
            if(exists32 && reachable31)
            {
                reachable32 = true;
            }
            if(exists39 && reachable31)
            {
                reachable39 = true;
            }
            if(exists40 && reachable31)
            {
                reachable40 = true;
            }
            if(exists41 && reachable31)
            {
                reachable41 = true;
            }
        }
        if(exists32)
        {
            if(exists22 && reachable32)
            {
                reachable22 = true;
            }
            if(exists23 && reachable32)
            {
                reachable23 = true;
            }
            if(exists24 && reachable32)
            {
                reachable24 = true;
            }
            if(exists31 && reachable32)
            {
                reachable31 = true;
            }
            if(exists33 && reachable32)
            {
                reachable33 = true;
            }
            if(exists40 && reachable32)
            {
                reachable40 = true;
            }
            if(exists41 && reachable32)
            {
                reachable41 = true;
            }
            if(exists42 && reachable32)
            {
                reachable42 = true;
            }
        }
        if(exists39)
        {
            if(exists29 && reachable39)
            {
                reachable29 = true;
            }
            if(exists30 && reachable39)
            {
                reachable30 = true;
            }
            if(exists31 && reachable39)
            {
                reachable31 = true;
            }
            if(exists38 && reachable39)
            {
                reachable38 = true;
            }
            if(exists40 && reachable39)
            {
                reachable40 = true;
            }
            if(exists47 && reachable39)
            {
                reachable47 = true;
            }
            if(exists48 && reachable39)
            {
                reachable48 = true;
            }
            if(exists49 && reachable39)
            {
                reachable49 = true;
            }
        }
        if(exists41)
        {
            if(exists31 && reachable41)
            {
                reachable31 = true;
            }
            if(exists32 && reachable41)
            {
                reachable32 = true;
            }
            if(exists33 && reachable41)
            {
                reachable33 = true;
            }
            if(exists40 && reachable41)
            {
                reachable40 = true;
            }
            if(exists42 && reachable41)
            {
                reachable42 = true;
            }
            if(exists49 && reachable41)
            {
                reachable49 = true;
            }
            if(exists50 && reachable41)
            {
                reachable50 = true;
            }
            if(exists51 && reachable41)
            {
                reachable51 = true;
            }
        }
        if(exists48)
        {
            if(exists38 && reachable48)
            {
                reachable38 = true;
            }
            if(exists39 && reachable48)
            {
                reachable39 = true;
            }
            if(exists40 && reachable48)
            {
                reachable40 = true;
            }
            if(exists47 && reachable48)
            {
                reachable47 = true;
            }
            if(exists49 && reachable48)
            {
                reachable49 = true;
            }
            if(exists56 && reachable48)
            {
                reachable56 = true;
            }
            if(exists57 && reachable48)
            {
                reachable57 = true;
            }
            if(exists58 && reachable48)
            {
                reachable58 = true;
            }
        }
        if(exists49)
        {
            if(exists39 && reachable49)
            {
                reachable39 = true;
            }
            if(exists40 && reachable49)
            {
                reachable40 = true;
            }
            if(exists41 && reachable49)
            {
                reachable41 = true;
            }
            if(exists48 && reachable49)
            {
                reachable48 = true;
            }
            if(exists50 && reachable49)
            {
                reachable50 = true;
            }
            if(exists57 && reachable49)
            {
                reachable57 = true;
            }
            if(exists58 && reachable49)
            {
                reachable58 = true;
            }
            if(exists59 && reachable49)
            {
                reachable59 = true;
            }
        }
        if(exists50)
        {
            if(exists40 && reachable50)
            {
                reachable40 = true;
            }
            if(exists41 && reachable50)
            {
                reachable41 = true;
            }
            if(exists42 && reachable50)
            {
                reachable42 = true;
            }
            if(exists49 && reachable50)
            {
                reachable49 = true;
            }
            if(exists51 && reachable50)
            {
                reachable51 = true;
            }
            if(exists58 && reachable50)
            {
                reachable58 = true;
            }
            if(exists59 && reachable50)
            {
                reachable59 = true;
            }
            if(exists60 && reachable50)
            {
                reachable60 = true;
            }
        }
        if(exists20)
        {
            if(exists10 && reachable20)
            {
                reachable10 = true;
            }
            if(exists11 && reachable20)
            {
                reachable11 = true;
            }
            if(exists12 && reachable20)
            {
                reachable12 = true;
            }
            if(exists19 && reachable20)
            {
                reachable19 = true;
            }
            if(exists21 && reachable20)
            {
                reachable21 = true;
            }
            if(exists28 && reachable20)
            {
                reachable28 = true;
            }
            if(exists29 && reachable20)
            {
                reachable29 = true;
            }
            if(exists30 && reachable20)
            {
                reachable30 = true;
            }
        }
        if(exists21)
        {
            if(exists11 && reachable21)
            {
                reachable11 = true;
            }
            if(exists12 && reachable21)
            {
                reachable12 = true;
            }
            if(exists13 && reachable21)
            {
                reachable13 = true;
            }
            if(exists20 && reachable21)
            {
                reachable20 = true;
            }
            if(exists22 && reachable21)
            {
                reachable22 = true;
            }
            if(exists29 && reachable21)
            {
                reachable29 = true;
            }
            if(exists30 && reachable21)
            {
                reachable30 = true;
            }
            if(exists31 && reachable21)
            {
                reachable31 = true;
            }
        }
        if(exists22)
        {
            if(exists12 && reachable22)
            {
                reachable12 = true;
            }
            if(exists13 && reachable22)
            {
                reachable13 = true;
            }
            if(exists14 && reachable22)
            {
                reachable14 = true;
            }
            if(exists21 && reachable22)
            {
                reachable21 = true;
            }
            if(exists23 && reachable22)
            {
                reachable23 = true;
            }
            if(exists30 && reachable22)
            {
                reachable30 = true;
            }
            if(exists31 && reachable22)
            {
                reachable31 = true;
            }
            if(exists32 && reachable22)
            {
                reachable32 = true;
            }
        }
        if(exists29)
        {
            if(exists19 && reachable29)
            {
                reachable19 = true;
            }
            if(exists20 && reachable29)
            {
                reachable20 = true;
            }
            if(exists21 && reachable29)
            {
                reachable21 = true;
            }
            if(exists28 && reachable29)
            {
                reachable28 = true;
            }
            if(exists30 && reachable29)
            {
                reachable30 = true;
            }
            if(exists37 && reachable29)
            {
                reachable37 = true;
            }
            if(exists38 && reachable29)
            {
                reachable38 = true;
            }
            if(exists39 && reachable29)
            {
                reachable39 = true;
            }
        }
        if(exists38)
        {
            if(exists28 && reachable38)
            {
                reachable28 = true;
            }
            if(exists29 && reachable38)
            {
                reachable29 = true;
            }
            if(exists30 && reachable38)
            {
                reachable30 = true;
            }
            if(exists37 && reachable38)
            {
                reachable37 = true;
            }
            if(exists39 && reachable38)
            {
                reachable39 = true;
            }
            if(exists46 && reachable38)
            {
                reachable46 = true;
            }
            if(exists47 && reachable38)
            {
                reachable47 = true;
            }
            if(exists48 && reachable38)
            {
                reachable48 = true;
            }
        }
        if(exists23)
        {
            if(exists13 && reachable23)
            {
                reachable13 = true;
            }
            if(exists14 && reachable23)
            {
                reachable14 = true;
            }
            if(exists15 && reachable23)
            {
                reachable15 = true;
            }
            if(exists22 && reachable23)
            {
                reachable22 = true;
            }
            if(exists24 && reachable23)
            {
                reachable24 = true;
            }
            if(exists31 && reachable23)
            {
                reachable31 = true;
            }
            if(exists32 && reachable23)
            {
                reachable32 = true;
            }
            if(exists33 && reachable23)
            {
                reachable33 = true;
            }
        }
        if(exists24)
        {
            if(exists14 && reachable24)
            {
                reachable14 = true;
            }
            if(exists15 && reachable24)
            {
                reachable15 = true;
            }
            if(exists16 && reachable24)
            {
                reachable16 = true;
            }
            if(exists23 && reachable24)
            {
                reachable23 = true;
            }
            if(exists25 && reachable24)
            {
                reachable25 = true;
            }
            if(exists32 && reachable24)
            {
                reachable32 = true;
            }
            if(exists33 && reachable24)
            {
                reachable33 = true;
            }
            if(exists34 && reachable24)
            {
                reachable34 = true;
            }
        }
        if(exists33)
        {
            if(exists23 && reachable33)
            {
                reachable23 = true;
            }
            if(exists24 && reachable33)
            {
                reachable24 = true;
            }
            if(exists25 && reachable33)
            {
                reachable25 = true;
            }
            if(exists32 && reachable33)
            {
                reachable32 = true;
            }
            if(exists34 && reachable33)
            {
                reachable34 = true;
            }
            if(exists41 && reachable33)
            {
                reachable41 = true;
            }
            if(exists42 && reachable33)
            {
                reachable42 = true;
            }
            if(exists43 && reachable33)
            {
                reachable43 = true;
            }
        }
        if(exists42)
        {
            if(exists32 && reachable42)
            {
                reachable32 = true;
            }
            if(exists33 && reachable42)
            {
                reachable33 = true;
            }
            if(exists34 && reachable42)
            {
                reachable34 = true;
            }
            if(exists41 && reachable42)
            {
                reachable41 = true;
            }
            if(exists43 && reachable42)
            {
                reachable43 = true;
            }
            if(exists50 && reachable42)
            {
                reachable50 = true;
            }
            if(exists51 && reachable42)
            {
                reachable51 = true;
            }
            if(exists52 && reachable42)
            {
                reachable52 = true;
            }
        }
        if(exists47)
        {
            if(exists37 && reachable47)
            {
                reachable37 = true;
            }
            if(exists38 && reachable47)
            {
                reachable38 = true;
            }
            if(exists39 && reachable47)
            {
                reachable39 = true;
            }
            if(exists46 && reachable47)
            {
                reachable46 = true;
            }
            if(exists48 && reachable47)
            {
                reachable48 = true;
            }
            if(exists55 && reachable47)
            {
                reachable55 = true;
            }
            if(exists56 && reachable47)
            {
                reachable56 = true;
            }
            if(exists57 && reachable47)
            {
                reachable57 = true;
            }
        }
        if(exists51)
        {
            if(exists41 && reachable51)
            {
                reachable41 = true;
            }
            if(exists42 && reachable51)
            {
                reachable42 = true;
            }
            if(exists43 && reachable51)
            {
                reachable43 = true;
            }
            if(exists50 && reachable51)
            {
                reachable50 = true;
            }
            if(exists52 && reachable51)
            {
                reachable52 = true;
            }
            if(exists59 && reachable51)
            {
                reachable59 = true;
            }
            if(exists60 && reachable51)
            {
                reachable60 = true;
            }
            if(exists61 && reachable51)
            {
                reachable61 = true;
            }
        }
        if(exists56)
        {
            if(exists46 && reachable56)
            {
                reachable46 = true;
            }
            if(exists47 && reachable56)
            {
                reachable47 = true;
            }
            if(exists48 && reachable56)
            {
                reachable48 = true;
            }
            if(exists55 && reachable56)
            {
                reachable55 = true;
            }
            if(exists57 && reachable56)
            {
                reachable57 = true;
            }
            if(exists64 && reachable56)
            {
                reachable64 = true;
            }
            if(exists65 && reachable56)
            {
                reachable65 = true;
            }
            if(exists66 && reachable56)
            {
                reachable66 = true;
            }
        }
        if(exists57)
        {
            if(exists47 && reachable57)
            {
                reachable47 = true;
            }
            if(exists48 && reachable57)
            {
                reachable48 = true;
            }
            if(exists49 && reachable57)
            {
                reachable49 = true;
            }
            if(exists56 && reachable57)
            {
                reachable56 = true;
            }
            if(exists58 && reachable57)
            {
                reachable58 = true;
            }
            if(exists65 && reachable57)
            {
                reachable65 = true;
            }
            if(exists66 && reachable57)
            {
                reachable66 = true;
            }
            if(exists67 && reachable57)
            {
                reachable67 = true;
            }
        }
        if(exists58)
        {
            if(exists48 && reachable58)
            {
                reachable48 = true;
            }
            if(exists49 && reachable58)
            {
                reachable49 = true;
            }
            if(exists50 && reachable58)
            {
                reachable50 = true;
            }
            if(exists57 && reachable58)
            {
                reachable57 = true;
            }
            if(exists59 && reachable58)
            {
                reachable59 = true;
            }
            if(exists66 && reachable58)
            {
                reachable66 = true;
            }
            if(exists67 && reachable58)
            {
                reachable67 = true;
            }
            if(exists68 && reachable58)
            {
                reachable68 = true;
            }
        }
        if(exists59)
        {
            if(exists49 && reachable59)
            {
                reachable49 = true;
            }
            if(exists50 && reachable59)
            {
                reachable50 = true;
            }
            if(exists51 && reachable59)
            {
                reachable51 = true;
            }
            if(exists58 && reachable59)
            {
                reachable58 = true;
            }
            if(exists60 && reachable59)
            {
                reachable60 = true;
            }
            if(exists67 && reachable59)
            {
                reachable67 = true;
            }
            if(exists68 && reachable59)
            {
                reachable68 = true;
            }
            if(exists69 && reachable59)
            {
                reachable69 = true;
            }
        }
        if(exists60)
        {
            if(exists50 && reachable60)
            {
                reachable50 = true;
            }
            if(exists51 && reachable60)
            {
                reachable51 = true;
            }
            if(exists52 && reachable60)
            {
                reachable52 = true;
            }
            if(exists59 && reachable60)
            {
                reachable59 = true;
            }
            if(exists61 && reachable60)
            {
                reachable61 = true;
            }
            if(exists68 && reachable60)
            {
                reachable68 = true;
            }
            if(exists69 && reachable60)
            {
                reachable69 = true;
            }
            if(exists70 && reachable60)
            {
                reachable70 = true;
            }
        }
        if(exists10)
        {
            if(exists2 && reachable10)
            {
                reachable2 = true;
            }
            if(exists11 && reachable10)
            {
                reachable11 = true;
            }
            if(exists18 && reachable10)
            {
                reachable18 = true;
            }
            if(exists19 && reachable10)
            {
                reachable19 = true;
            }
            if(exists20 && reachable10)
            {
                reachable20 = true;
            }
        }
        if(exists11)
        {
            if(exists2 && reachable11)
            {
                reachable2 = true;
            }
            if(exists3 && reachable11)
            {
                reachable3 = true;
            }
            if(exists10 && reachable11)
            {
                reachable10 = true;
            }
            if(exists12 && reachable11)
            {
                reachable12 = true;
            }
            if(exists19 && reachable11)
            {
                reachable19 = true;
            }
            if(exists20 && reachable11)
            {
                reachable20 = true;
            }
            if(exists21 && reachable11)
            {
                reachable21 = true;
            }
        }
        if(exists12)
        {
            if(exists2 && reachable12)
            {
                reachable2 = true;
            }
            if(exists3 && reachable12)
            {
                reachable3 = true;
            }
            if(exists4 && reachable12)
            {
                reachable4 = true;
            }
            if(exists11 && reachable12)
            {
                reachable11 = true;
            }
            if(exists13 && reachable12)
            {
                reachable13 = true;
            }
            if(exists20 && reachable12)
            {
                reachable20 = true;
            }
            if(exists21 && reachable12)
            {
                reachable21 = true;
            }
            if(exists22 && reachable12)
            {
                reachable22 = true;
            }
        }
        if(exists19)
        {
            if(exists10 && reachable19)
            {
                reachable10 = true;
            }
            if(exists11 && reachable19)
            {
                reachable11 = true;
            }
            if(exists18 && reachable19)
            {
                reachable18 = true;
            }
            if(exists20 && reachable19)
            {
                reachable20 = true;
            }
            if(exists27 && reachable19)
            {
                reachable27 = true;
            }
            if(exists28 && reachable19)
            {
                reachable28 = true;
            }
            if(exists29 && reachable19)
            {
                reachable29 = true;
            }
        }
        if(exists28)
        {
            if(exists18 && reachable28)
            {
                reachable18 = true;
            }
            if(exists19 && reachable28)
            {
                reachable19 = true;
            }
            if(exists20 && reachable28)
            {
                reachable20 = true;
            }
            if(exists27 && reachable28)
            {
                reachable27 = true;
            }
            if(exists29 && reachable28)
            {
                reachable29 = true;
            }
            if(exists36 && reachable28)
            {
                reachable36 = true;
            }
            if(exists37 && reachable28)
            {
                reachable37 = true;
            }
            if(exists38 && reachable28)
            {
                reachable38 = true;
            }
        }
        if(exists13)
        {
            if(exists3 && reachable13)
            {
                reachable3 = true;
            }
            if(exists4 && reachable13)
            {
                reachable4 = true;
            }
            if(exists5 && reachable13)
            {
                reachable5 = true;
            }
            if(exists12 && reachable13)
            {
                reachable12 = true;
            }
            if(exists14 && reachable13)
            {
                reachable14 = true;
            }
            if(exists21 && reachable13)
            {
                reachable21 = true;
            }
            if(exists22 && reachable13)
            {
                reachable22 = true;
            }
            if(exists23 && reachable13)
            {
                reachable23 = true;
            }
        }
        if(exists14)
        {
            if(exists4 && reachable14)
            {
                reachable4 = true;
            }
            if(exists5 && reachable14)
            {
                reachable5 = true;
            }
            if(exists6 && reachable14)
            {
                reachable6 = true;
            }
            if(exists13 && reachable14)
            {
                reachable13 = true;
            }
            if(exists15 && reachable14)
            {
                reachable15 = true;
            }
            if(exists22 && reachable14)
            {
                reachable22 = true;
            }
            if(exists23 && reachable14)
            {
                reachable23 = true;
            }
            if(exists24 && reachable14)
            {
                reachable24 = true;
            }
        }
        if(exists37)
        {
            if(exists27 && reachable37)
            {
                reachable27 = true;
            }
            if(exists28 && reachable37)
            {
                reachable28 = true;
            }
            if(exists29 && reachable37)
            {
                reachable29 = true;
            }
            if(exists36 && reachable37)
            {
                reachable36 = true;
            }
            if(exists38 && reachable37)
            {
                reachable38 = true;
            }
            if(exists45 && reachable37)
            {
                reachable45 = true;
            }
            if(exists46 && reachable37)
            {
                reachable46 = true;
            }
            if(exists47 && reachable37)
            {
                reachable47 = true;
            }
        }
        if(exists46)
        {
            if(exists36 && reachable46)
            {
                reachable36 = true;
            }
            if(exists37 && reachable46)
            {
                reachable37 = true;
            }
            if(exists38 && reachable46)
            {
                reachable38 = true;
            }
            if(exists45 && reachable46)
            {
                reachable45 = true;
            }
            if(exists47 && reachable46)
            {
                reachable47 = true;
            }
            if(exists54 && reachable46)
            {
                reachable54 = true;
            }
            if(exists55 && reachable46)
            {
                reachable55 = true;
            }
            if(exists56 && reachable46)
            {
                reachable56 = true;
            }
        }
        if(exists15)
        {
            if(exists5 && reachable15)
            {
                reachable5 = true;
            }
            if(exists6 && reachable15)
            {
                reachable6 = true;
            }
            if(exists14 && reachable15)
            {
                reachable14 = true;
            }
            if(exists16 && reachable15)
            {
                reachable16 = true;
            }
            if(exists23 && reachable15)
            {
                reachable23 = true;
            }
            if(exists24 && reachable15)
            {
                reachable24 = true;
            }
            if(exists25 && reachable15)
            {
                reachable25 = true;
            }
        }
        if(exists16)
        {
            if(exists6 && reachable16)
            {
                reachable6 = true;
            }
            if(exists15 && reachable16)
            {
                reachable15 = true;
            }
            if(exists24 && reachable16)
            {
                reachable24 = true;
            }
            if(exists25 && reachable16)
            {
                reachable25 = true;
            }
            if(exists26 && reachable16)
            {
                reachable26 = true;
            }
        }
        if(exists25)
        {
            if(exists15 && reachable25)
            {
                reachable15 = true;
            }
            if(exists16 && reachable25)
            {
                reachable16 = true;
            }
            if(exists24 && reachable25)
            {
                reachable24 = true;
            }
            if(exists26 && reachable25)
            {
                reachable26 = true;
            }
            if(exists33 && reachable25)
            {
                reachable33 = true;
            }
            if(exists34 && reachable25)
            {
                reachable34 = true;
            }
            if(exists35 && reachable25)
            {
                reachable35 = true;
            }
        }
        if(exists34)
        {
            if(exists24 && reachable34)
            {
                reachable24 = true;
            }
            if(exists25 && reachable34)
            {
                reachable25 = true;
            }
            if(exists26 && reachable34)
            {
                reachable26 = true;
            }
            if(exists33 && reachable34)
            {
                reachable33 = true;
            }
            if(exists35 && reachable34)
            {
                reachable35 = true;
            }
            if(exists42 && reachable34)
            {
                reachable42 = true;
            }
            if(exists43 && reachable34)
            {
                reachable43 = true;
            }
            if(exists44 && reachable34)
            {
                reachable44 = true;
            }
        }
        if(exists43)
        {
            if(exists33 && reachable43)
            {
                reachable33 = true;
            }
            if(exists34 && reachable43)
            {
                reachable34 = true;
            }
            if(exists35 && reachable43)
            {
                reachable35 = true;
            }
            if(exists42 && reachable43)
            {
                reachable42 = true;
            }
            if(exists44 && reachable43)
            {
                reachable44 = true;
            }
            if(exists51 && reachable43)
            {
                reachable51 = true;
            }
            if(exists52 && reachable43)
            {
                reachable52 = true;
            }
            if(exists53 && reachable43)
            {
                reachable53 = true;
            }
        }
        if(exists52)
        {
            if(exists42 && reachable52)
            {
                reachable42 = true;
            }
            if(exists43 && reachable52)
            {
                reachable43 = true;
            }
            if(exists44 && reachable52)
            {
                reachable44 = true;
            }
            if(exists51 && reachable52)
            {
                reachable51 = true;
            }
            if(exists53 && reachable52)
            {
                reachable53 = true;
            }
            if(exists60 && reachable52)
            {
                reachable60 = true;
            }
            if(exists61 && reachable52)
            {
                reachable61 = true;
            }
            if(exists62 && reachable52)
            {
                reachable62 = true;
            }
        }
        if(exists55)
        {
            if(exists45 && reachable55)
            {
                reachable45 = true;
            }
            if(exists46 && reachable55)
            {
                reachable46 = true;
            }
            if(exists47 && reachable55)
            {
                reachable47 = true;
            }
            if(exists54 && reachable55)
            {
                reachable54 = true;
            }
            if(exists56 && reachable55)
            {
                reachable56 = true;
            }
            if(exists64 && reachable55)
            {
                reachable64 = true;
            }
            if(exists65 && reachable55)
            {
                reachable65 = true;
            }
        }
        if(exists61)
        {
            if(exists51 && reachable61)
            {
                reachable51 = true;
            }
            if(exists52 && reachable61)
            {
                reachable52 = true;
            }
            if(exists53 && reachable61)
            {
                reachable53 = true;
            }
            if(exists60 && reachable61)
            {
                reachable60 = true;
            }
            if(exists62 && reachable61)
            {
                reachable62 = true;
            }
            if(exists69 && reachable61)
            {
                reachable69 = true;
            }
            if(exists70 && reachable61)
            {
                reachable70 = true;
            }
        }
        if(exists64)
        {
            if(exists54 && reachable64)
            {
                reachable54 = true;
            }
            if(exists55 && reachable64)
            {
                reachable55 = true;
            }
            if(exists56 && reachable64)
            {
                reachable56 = true;
            }
            if(exists65 && reachable64)
            {
                reachable65 = true;
            }
            if(exists74 && reachable64)
            {
                reachable74 = true;
            }
        }
        if(exists65)
        {
            if(exists55 && reachable65)
            {
                reachable55 = true;
            }
            if(exists56 && reachable65)
            {
                reachable56 = true;
            }
            if(exists57 && reachable65)
            {
                reachable57 = true;
            }
            if(exists64 && reachable65)
            {
                reachable64 = true;
            }
            if(exists66 && reachable65)
            {
                reachable66 = true;
            }
            if(exists74 && reachable65)
            {
                reachable74 = true;
            }
            if(exists75 && reachable65)
            {
                reachable75 = true;
            }
        }
        if(exists66)
        {
            if(exists56 && reachable66)
            {
                reachable56 = true;
            }
            if(exists57 && reachable66)
            {
                reachable57 = true;
            }
            if(exists58 && reachable66)
            {
                reachable58 = true;
            }
            if(exists65 && reachable66)
            {
                reachable65 = true;
            }
            if(exists67 && reachable66)
            {
                reachable67 = true;
            }
            if(exists74 && reachable66)
            {
                reachable74 = true;
            }
            if(exists75 && reachable66)
            {
                reachable75 = true;
            }
            if(exists76 && reachable66)
            {
                reachable76 = true;
            }
        }
        if(exists67)
        {
            if(exists57 && reachable67)
            {
                reachable57 = true;
            }
            if(exists58 && reachable67)
            {
                reachable58 = true;
            }
            if(exists59 && reachable67)
            {
                reachable59 = true;
            }
            if(exists66 && reachable67)
            {
                reachable66 = true;
            }
            if(exists68 && reachable67)
            {
                reachable68 = true;
            }
            if(exists75 && reachable67)
            {
                reachable75 = true;
            }
            if(exists76 && reachable67)
            {
                reachable76 = true;
            }
            if(exists77 && reachable67)
            {
                reachable77 = true;
            }
        }
        if(exists68)
        {
            if(exists58 && reachable68)
            {
                reachable58 = true;
            }
            if(exists59 && reachable68)
            {
                reachable59 = true;
            }
            if(exists60 && reachable68)
            {
                reachable60 = true;
            }
            if(exists67 && reachable68)
            {
                reachable67 = true;
            }
            if(exists69 && reachable68)
            {
                reachable69 = true;
            }
            if(exists76 && reachable68)
            {
                reachable76 = true;
            }
            if(exists77 && reachable68)
            {
                reachable77 = true;
            }
            if(exists78 && reachable68)
            {
                reachable78 = true;
            }
        }
        if(exists69)
        {
            if(exists59 && reachable69)
            {
                reachable59 = true;
            }
            if(exists60 && reachable69)
            {
                reachable60 = true;
            }
            if(exists61 && reachable69)
            {
                reachable61 = true;
            }
            if(exists68 && reachable69)
            {
                reachable68 = true;
            }
            if(exists70 && reachable69)
            {
                reachable70 = true;
            }
            if(exists77 && reachable69)
            {
                reachable77 = true;
            }
            if(exists78 && reachable69)
            {
                reachable78 = true;
            }
        }
        if(exists70)
        {
            if(exists60 && reachable70)
            {
                reachable60 = true;
            }
            if(exists61 && reachable70)
            {
                reachable61 = true;
            }
            if(exists62 && reachable70)
            {
                reachable62 = true;
            }
            if(exists69 && reachable70)
            {
                reachable69 = true;
            }
            if(exists78 && reachable70)
            {
                reachable78 = true;
            }
        }
        if(exists2)
        {
            if(exists3 && reachable2)
            {
                reachable3 = true;
            }
            if(exists10 && reachable2)
            {
                reachable10 = true;
            }
            if(exists11 && reachable2)
            {
                reachable11 = true;
            }
            if(exists12 && reachable2)
            {
                reachable12 = true;
            }
        }
        if(exists18)
        {
            if(exists10 && reachable18)
            {
                reachable10 = true;
            }
            if(exists19 && reachable18)
            {
                reachable19 = true;
            }
            if(exists27 && reachable18)
            {
                reachable27 = true;
            }
            if(exists28 && reachable18)
            {
                reachable28 = true;
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
            if(exists11 && reachable3)
            {
                reachable11 = true;
            }
            if(exists12 && reachable3)
            {
                reachable12 = true;
            }
            if(exists13 && reachable3)
            {
                reachable13 = true;
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
            if(exists12 && reachable4)
            {
                reachable12 = true;
            }
            if(exists13 && reachable4)
            {
                reachable13 = true;
            }
            if(exists14 && reachable4)
            {
                reachable14 = true;
            }
        }
        if(exists27)
        {
            if(exists18 && reachable27)
            {
                reachable18 = true;
            }
            if(exists19 && reachable27)
            {
                reachable19 = true;
            }
            if(exists28 && reachable27)
            {
                reachable28 = true;
            }
            if(exists36 && reachable27)
            {
                reachable36 = true;
            }
            if(exists37 && reachable27)
            {
                reachable37 = true;
            }
        }
        if(exists36)
        {
            if(exists27 && reachable36)
            {
                reachable27 = true;
            }
            if(exists28 && reachable36)
            {
                reachable28 = true;
            }
            if(exists37 && reachable36)
            {
                reachable37 = true;
            }
            if(exists45 && reachable36)
            {
                reachable45 = true;
            }
            if(exists46 && reachable36)
            {
                reachable46 = true;
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
            if(exists13 && reachable5)
            {
                reachable13 = true;
            }
            if(exists14 && reachable5)
            {
                reachable14 = true;
            }
            if(exists15 && reachable5)
            {
                reachable15 = true;
            }
        }
        if(exists6)
        {
            if(exists5 && reachable6)
            {
                reachable5 = true;
            }
            if(exists14 && reachable6)
            {
                reachable14 = true;
            }
            if(exists15 && reachable6)
            {
                reachable15 = true;
            }
            if(exists16 && reachable6)
            {
                reachable16 = true;
            }
        }
        if(exists45)
        {
            if(exists36 && reachable45)
            {
                reachable36 = true;
            }
            if(exists37 && reachable45)
            {
                reachable37 = true;
            }
            if(exists46 && reachable45)
            {
                reachable46 = true;
            }
            if(exists54 && reachable45)
            {
                reachable54 = true;
            }
            if(exists55 && reachable45)
            {
                reachable55 = true;
            }
        }
        if(exists54)
        {
            if(exists45 && reachable54)
            {
                reachable45 = true;
            }
            if(exists46 && reachable54)
            {
                reachable46 = true;
            }
            if(exists55 && reachable54)
            {
                reachable55 = true;
            }
            if(exists64 && reachable54)
            {
                reachable64 = true;
            }
        }
        if(exists26)
        {
            if(exists16 && reachable26)
            {
                reachable16 = true;
            }
            if(exists25 && reachable26)
            {
                reachable25 = true;
            }
            if(exists34 && reachable26)
            {
                reachable34 = true;
            }
            if(exists35 && reachable26)
            {
                reachable35 = true;
            }
        }
        if(exists35)
        {
            if(exists25 && reachable35)
            {
                reachable25 = true;
            }
            if(exists26 && reachable35)
            {
                reachable26 = true;
            }
            if(exists34 && reachable35)
            {
                reachable34 = true;
            }
            if(exists43 && reachable35)
            {
                reachable43 = true;
            }
            if(exists44 && reachable35)
            {
                reachable44 = true;
            }
        }
        if(exists44)
        {
            if(exists34 && reachable44)
            {
                reachable34 = true;
            }
            if(exists35 && reachable44)
            {
                reachable35 = true;
            }
            if(exists43 && reachable44)
            {
                reachable43 = true;
            }
            if(exists52 && reachable44)
            {
                reachable52 = true;
            }
            if(exists53 && reachable44)
            {
                reachable53 = true;
            }
        }
        if(exists53)
        {
            if(exists43 && reachable53)
            {
                reachable43 = true;
            }
            if(exists44 && reachable53)
            {
                reachable44 = true;
            }
            if(exists52 && reachable53)
            {
                reachable52 = true;
            }
            if(exists61 && reachable53)
            {
                reachable61 = true;
            }
            if(exists62 && reachable53)
            {
                reachable62 = true;
            }
        }
        if(exists62)
        {
            if(exists52 && reachable62)
            {
                reachable52 = true;
            }
            if(exists53 && reachable62)
            {
                reachable53 = true;
            }
            if(exists61 && reachable62)
            {
                reachable61 = true;
            }
            if(exists70 && reachable62)
            {
                reachable70 = true;
            }
        }
        if(exists74)
        {
            if(exists64 && reachable74)
            {
                reachable64 = true;
            }
            if(exists65 && reachable74)
            {
                reachable65 = true;
            }
            if(exists66 && reachable74)
            {
                reachable66 = true;
            }
            if(exists75 && reachable74)
            {
                reachable75 = true;
            }
        }
        if(exists75)
        {
            if(exists65 && reachable75)
            {
                reachable65 = true;
            }
            if(exists66 && reachable75)
            {
                reachable66 = true;
            }
            if(exists67 && reachable75)
            {
                reachable67 = true;
            }
            if(exists74 && reachable75)
            {
                reachable74 = true;
            }
            if(exists76 && reachable75)
            {
                reachable76 = true;
            }
        }
        if(exists76)
        {
            if(exists66 && reachable76)
            {
                reachable66 = true;
            }
            if(exists67 && reachable76)
            {
                reachable67 = true;
            }
            if(exists68 && reachable76)
            {
                reachable68 = true;
            }
            if(exists75 && reachable76)
            {
                reachable75 = true;
            }
            if(exists77 && reachable76)
            {
                reachable77 = true;
            }
        }
        if(exists77)
        {
            if(exists67 && reachable77)
            {
                reachable67 = true;
            }
            if(exists68 && reachable77)
            {
                reachable68 = true;
            }
            if(exists69 && reachable77)
            {
                reachable69 = true;
            }
            if(exists76 && reachable77)
            {
                reachable76 = true;
            }
            if(exists78 && reachable77)
            {
                reachable78 = true;
            }
        }
        if(exists78)
        {
            if(exists68 && reachable78)
            {
                reachable68 = true;
            }
            if(exists69 && reachable78)
            {
                reachable69 = true;
            }
            if(exists70 && reachable78)
            {
                reachable70 = true;
            }
            if(exists77 && reachable78)
            {
                reachable77 = true;
            }
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
        if(exists49 && !reachable49)
        {
            overWall.add(l49);
            rc.setIndicatorDot(l49, 0, 255,0);
        }
        if(exists50 && !reachable50)
        {
            overWall.add(l50);
            rc.setIndicatorDot(l50, 0, 255,0);
        }
        if(exists51 && !reachable51)
        {
            overWall.add(l51);
            rc.setIndicatorDot(l51, 0, 255,0);
        }
        if(exists52 && !reachable52)
        {
            overWall.add(l52);
            rc.setIndicatorDot(l52, 0, 255,0);
        }
        if(exists53 && !reachable53)
        {
            overWall.add(l53);
            rc.setIndicatorDot(l53, 0, 255,0);
        }
        if(exists54 && !reachable54)
        {
            overWall.add(l54);
            rc.setIndicatorDot(l54, 0, 255,0);
        }
        if(exists55 && !reachable55)
        {
            overWall.add(l55);
            rc.setIndicatorDot(l55, 0, 255,0);
        }
        if(exists56 && !reachable56)
        {
            overWall.add(l56);
            rc.setIndicatorDot(l56, 0, 255,0);
        }
        if(exists57 && !reachable57)
        {
            overWall.add(l57);
            rc.setIndicatorDot(l57, 0, 255,0);
        }
        if(exists58 && !reachable58)
        {
            overWall.add(l58);
            rc.setIndicatorDot(l58, 0, 255,0);
        }
        if(exists59 && !reachable59)
        {
            overWall.add(l59);
            rc.setIndicatorDot(l59, 0, 255,0);
        }
        if(exists60 && !reachable60)
        {
            overWall.add(l60);
            rc.setIndicatorDot(l60, 0, 255,0);
        }
        if(exists61 && !reachable61)
        {
            overWall.add(l61);
            rc.setIndicatorDot(l61, 0, 255,0);
        }
        if(exists62 && !reachable62)
        {
            overWall.add(l62);
            rc.setIndicatorDot(l62, 0, 255,0);
        }
        if(exists64 && !reachable64)
        {
            overWall.add(l64);
            rc.setIndicatorDot(l64, 0, 255,0);
        }
        if(exists65 && !reachable65)
        {
            overWall.add(l65);
            rc.setIndicatorDot(l65, 0, 255,0);
        }
        if(exists66 && !reachable66)
        {
            overWall.add(l66);
            rc.setIndicatorDot(l66, 0, 255,0);
        }
        if(exists67 && !reachable67)
        {
            overWall.add(l67);
            rc.setIndicatorDot(l67, 0, 255,0);
        }
        if(exists68 && !reachable68)
        {
            overWall.add(l68);
            rc.setIndicatorDot(l68, 0, 255,0);
        }
        if(exists69 && !reachable69)
        {
            overWall.add(l69);
            rc.setIndicatorDot(l69, 0, 255,0);
        }
        if(exists70 && !reachable70)
        {
            overWall.add(l70);
            rc.setIndicatorDot(l70, 0, 255,0);
        }
        if(exists74 && !reachable74)
        {
            overWall.add(l74);
            rc.setIndicatorDot(l74, 0, 255,0);
        }
        if(exists75 && !reachable75)
        {
            overWall.add(l75);
            rc.setIndicatorDot(l75, 0, 255,0);
        }
        if(exists76 && !reachable76)
        {
            overWall.add(l76);
            rc.setIndicatorDot(l76, 0, 255,0);
        }
        if(exists77 && !reachable77)
        {
            overWall.add(l77);
            rc.setIndicatorDot(l77, 0, 255,0);
        }
        if(exists78 && !reachable78)
        {
            overWall.add(l78);
            rc.setIndicatorDot(l78, 0, 255,0);
        }
        return overWall;
    }
}
