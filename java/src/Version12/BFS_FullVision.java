package Version12;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class BFS_FullVision
{
    static Direction[][] lookup;

    static MapLocation l2;
    static int dist2;
    static int h2;
    static boolean exists2;
    static int cost2;

    static MapLocation l3;
    static int dist3;
    static int h3;
    static boolean exists3;
    static int cost3;

    static MapLocation l4;
    static int dist4;
    static int h4;
    static boolean exists4;
    static int cost4;

    static MapLocation l5;
    static int dist5;
    static int h5;
    static boolean exists5;
    static int cost5;

    static MapLocation l6;
    static int dist6;
    static int h6;
    static boolean exists6;
    static int cost6;

    static MapLocation l10;
    static int dist10;
    static int h10;
    static boolean exists10;
    static int cost10;

    static MapLocation l11;
    static int dist11;
    static int h11;
    static boolean exists11;
    static int cost11;

    static MapLocation l12;
    static int dist12;
    static int h12;
    static boolean exists12;
    static int cost12;

    static MapLocation l13;
    static int dist13;
    static int h13;
    static boolean exists13;
    static int cost13;

    static MapLocation l14;
    static int dist14;
    static int h14;
    static boolean exists14;
    static int cost14;

    static MapLocation l15;
    static int dist15;
    static int h15;
    static boolean exists15;
    static int cost15;

    static MapLocation l16;
    static int dist16;
    static int h16;
    static boolean exists16;
    static int cost16;

    static MapLocation l18;
    static int dist18;
    static int h18;
    static boolean exists18;
    static int cost18;

    static MapLocation l19;
    static int dist19;
    static int h19;
    static boolean exists19;
    static int cost19;

    static MapLocation l20;
    static int dist20;
    static int h20;
    static boolean exists20;
    static int cost20;

    static MapLocation l21;
    static int dist21;
    static int h21;
    static boolean exists21;
    static int cost21;

    static MapLocation l22;
    static int dist22;
    static int h22;
    static boolean exists22;
    static int cost22;

    static MapLocation l23;
    static int dist23;
    static int h23;
    static boolean exists23;
    static int cost23;

    static MapLocation l24;
    static int dist24;
    static int h24;
    static boolean exists24;
    static int cost24;

    static MapLocation l25;
    static int dist25;
    static int h25;
    static boolean exists25;
    static int cost25;

    static MapLocation l26;
    static int dist26;
    static int h26;
    static boolean exists26;
    static int cost26;

    static MapLocation l27;
    static int dist27;
    static int h27;
    static boolean exists27;
    static int cost27;

    static MapLocation l28;
    static int dist28;
    static int h28;
    static boolean exists28;
    static int cost28;

    static MapLocation l29;
    static int dist29;
    static int h29;
    static boolean exists29;
    static int cost29;

    static MapLocation l30;
    static int dist30;
    static int h30;
    static boolean exists30;
    static int cost30;

    static MapLocation l31;
    static int dist31;
    static int h31;
    static boolean exists31;
    static int cost31;

    static MapLocation l32;
    static int dist32;
    static int h32;
    static boolean exists32;
    static int cost32;

    static MapLocation l33;
    static int dist33;
    static int h33;
    static boolean exists33;
    static int cost33;

    static MapLocation l34;
    static int dist34;
    static int h34;
    static boolean exists34;
    static int cost34;

    static MapLocation l35;
    static int dist35;
    static int h35;
    static boolean exists35;
    static int cost35;

    static MapLocation l36;
    static int dist36;
    static int h36;
    static boolean exists36;
    static int cost36;

    static MapLocation l37;
    static int dist37;
    static int h37;
    static boolean exists37;
    static int cost37;

    static MapLocation l38;
    static int dist38;
    static int h38;
    static boolean exists38;
    static int cost38;

    static MapLocation l39;
    static int dist39;
    static int h39;
    static boolean exists39;
    static int cost39;

    static MapLocation l40;
    static int dist40;
    static int h40;
    static boolean exists40;
    static int cost40;

    static MapLocation l41;
    static int dist41;
    static int h41;
    static boolean exists41;
    static int cost41;

    static MapLocation l42;
    static int dist42;
    static int h42;
    static boolean exists42;
    static int cost42;

    static MapLocation l43;
    static int dist43;
    static int h43;
    static boolean exists43;
    static int cost43;

    static MapLocation l44;
    static int dist44;
    static int h44;
    static boolean exists44;
    static int cost44;

    static MapLocation l45;
    static int dist45;
    static int h45;
    static boolean exists45;
    static int cost45;

    static MapLocation l46;
    static int dist46;
    static int h46;
    static boolean exists46;
    static int cost46;

    static MapLocation l47;
    static int dist47;
    static int h47;
    static boolean exists47;
    static int cost47;

    static MapLocation l48;
    static int dist48;
    static int h48;
    static boolean exists48;
    static int cost48;

    static MapLocation l49;
    static int dist49;
    static int h49;
    static boolean exists49;
    static int cost49;

    static MapLocation l50;
    static int dist50;
    static int h50;
    static boolean exists50;
    static int cost50;

    static MapLocation l51;
    static int dist51;
    static int h51;
    static boolean exists51;
    static int cost51;

    static MapLocation l52;
    static int dist52;
    static int h52;
    static boolean exists52;
    static int cost52;

    static MapLocation l53;
    static int dist53;
    static int h53;
    static boolean exists53;
    static int cost53;

    static MapLocation l54;
    static int dist54;
    static int h54;
    static boolean exists54;
    static int cost54;

    static MapLocation l55;
    static int dist55;
    static int h55;
    static boolean exists55;
    static int cost55;

    static MapLocation l56;
    static int dist56;
    static int h56;
    static boolean exists56;
    static int cost56;

    static MapLocation l57;
    static int dist57;
    static int h57;
    static boolean exists57;
    static int cost57;

    static MapLocation l58;
    static int dist58;
    static int h58;
    static boolean exists58;
    static int cost58;

    static MapLocation l59;
    static int dist59;
    static int h59;
    static boolean exists59;
    static int cost59;

    static MapLocation l60;
    static int dist60;
    static int h60;
    static boolean exists60;
    static int cost60;

    static MapLocation l61;
    static int dist61;
    static int h61;
    static boolean exists61;
    static int cost61;

    static MapLocation l62;
    static int dist62;
    static int h62;
    static boolean exists62;
    static int cost62;

    static MapLocation l64;
    static int dist64;
    static int h64;
    static boolean exists64;
    static int cost64;

    static MapLocation l65;
    static int dist65;
    static int h65;
    static boolean exists65;
    static int cost65;

    static MapLocation l66;
    static int dist66;
    static int h66;
    static boolean exists66;
    static int cost66;

    static MapLocation l67;
    static int dist67;
    static int h67;
    static boolean exists67;
    static int cost67;

    static MapLocation l68;
    static int dist68;
    static int h68;
    static boolean exists68;
    static int cost68;

    static MapLocation l69;
    static int dist69;
    static int h69;
    static boolean exists69;
    static int cost69;

    static MapLocation l70;
    static int dist70;
    static int h70;
    static boolean exists70;
    static int cost70;

    static MapLocation l74;
    static int dist74;
    static int h74;
    static boolean exists74;
    static int cost74;

    static MapLocation l75;
    static int dist75;
    static int h75;
    static boolean exists75;
    static int cost75;

    static MapLocation l76;
    static int dist76;
    static int h76;
    static boolean exists76;
    static int cost76;

    static MapLocation l77;
    static int dist77;
    static int h77;
    static boolean exists77;
    static int cost77;

    static MapLocation l78;
    static int dist78;
    static int h78;
    static boolean exists78;
    static int cost78;

    public static MapLocation getLocationFromIndex(int nodeIndex, MapLocation center)
    {
        return new MapLocation(center.x - 4 + nodeIndex % 9, center.y - 4 + 8 - nodeIndex / 9);
    }


    public static int indexToLocalX(int nodeIndex)
    {
        return nodeIndex % 9;
    }

    public static int indexToLocalY(int nodeIndex)
    {
        return nodeIndex / 9;
    }

    public static Direction dirToMove(int nodeIndex)
    {
        int currentNodeIndex = nodeIndex;
        Direction currentDirection = lookup[indexToLocalY(nodeIndex)][indexToLocalX(nodeIndex)];
        while (currentDirection != null)
        {
            Direction tempDirection = currentDirection;
            switch(currentDirection)
            {
                case NORTH:
                    currentNodeIndex = currentNodeIndex - 9;
                    break;
                case NORTHEAST:
                    currentNodeIndex = currentNodeIndex - 8;
                    break;
                case EAST:
                    currentNodeIndex = currentNodeIndex + 1;
                    break;
                case SOUTHEAST:
                    currentNodeIndex = currentNodeIndex + 10;
                    break;
                case SOUTH:
                    currentNodeIndex = currentNodeIndex + 9;
                    break;
                case SOUTHWEST:
                    currentNodeIndex = currentNodeIndex + 8;
                    break;
                case WEST:
                    currentNodeIndex = currentNodeIndex - 1;
                    break;
                case NORTHWEST:
                    currentNodeIndex = currentNodeIndex - 10;
                    break;
            }
            currentDirection = lookup[currentNodeIndex / 9][currentNodeIndex % 9];
            if(currentDirection == null) return tempDirection.opposite();
        }
        return null;
    }

    public static void initializeVariables(RobotController rc, MapLocation destination, MapLocation start) throws GameActionException
    {
        l2 = getLocationFromIndex(2, start);
        dist2 = 1000000;
        h2 = l2.distanceSquaredTo(destination);
        exists2 = rc.onTheMap(l2) && rc.sensePassability(l2) && !rc.canSenseRobotAtLocation(l2);
        cost2 = exists2 && rc.senseMapInfo(l2).getPaint().isAlly() ? 10 : 20;

        l3 = getLocationFromIndex(3, start);
        dist3 = 1000000;
        h3 = l3.distanceSquaredTo(destination);
        exists3 = rc.onTheMap(l3) && rc.sensePassability(l3) && !rc.canSenseRobotAtLocation(l3);
        cost3 = exists3 && rc.senseMapInfo(l3).getPaint().isAlly() ? 10 : 20;

        l4 = getLocationFromIndex(4, start);
        dist4 = 1000000;
        h4 = l4.distanceSquaredTo(destination);
        exists4 = rc.onTheMap(l4) && rc.sensePassability(l4) && !rc.canSenseRobotAtLocation(l4);
        cost4 = exists4 && rc.senseMapInfo(l4).getPaint().isAlly() ? 10 : 20;

        l5 = getLocationFromIndex(5, start);
        dist5 = 1000000;
        h5 = l5.distanceSquaredTo(destination);
        exists5 = rc.onTheMap(l5) && rc.sensePassability(l5) && !rc.canSenseRobotAtLocation(l5);
        cost5 = exists5 && rc.senseMapInfo(l5).getPaint().isAlly() ? 10 : 20;

        l6 = getLocationFromIndex(6, start);
        dist6 = 1000000;
        h6 = l6.distanceSquaredTo(destination);
        exists6 = rc.onTheMap(l6) && rc.sensePassability(l6) && !rc.canSenseRobotAtLocation(l6);
        cost6 = exists6 && rc.senseMapInfo(l6).getPaint().isAlly() ? 10 : 20;

        l10 = getLocationFromIndex(10, start);
        dist10 = 1000000;
        h10 = l10.distanceSquaredTo(destination);
        exists10 = rc.onTheMap(l10) && rc.sensePassability(l10) && !rc.canSenseRobotAtLocation(l10);
        cost10 = exists10 && rc.senseMapInfo(l10).getPaint().isAlly() ? 10 : 20;

        l11 = getLocationFromIndex(11, start);
        dist11 = 1000000;
        h11 = l11.distanceSquaredTo(destination);
        exists11 = rc.onTheMap(l11) && rc.sensePassability(l11) && !rc.canSenseRobotAtLocation(l11);
        cost11 = exists11 && rc.senseMapInfo(l11).getPaint().isAlly() ? 10 : 20;

        l12 = getLocationFromIndex(12, start);
        dist12 = 1000000;
        h12 = l12.distanceSquaredTo(destination);
        exists12 = rc.onTheMap(l12) && rc.sensePassability(l12) && !rc.canSenseRobotAtLocation(l12);
        cost12 = exists12 && rc.senseMapInfo(l12).getPaint().isAlly() ? 10 : 20;

        l13 = getLocationFromIndex(13, start);
        dist13 = 1000000;
        h13 = l13.distanceSquaredTo(destination);
        exists13 = rc.onTheMap(l13) && rc.sensePassability(l13) && !rc.canSenseRobotAtLocation(l13);
        cost13 = exists13 && rc.senseMapInfo(l13).getPaint().isAlly() ? 10 : 20;

        l14 = getLocationFromIndex(14, start);
        dist14 = 1000000;
        h14 = l14.distanceSquaredTo(destination);
        exists14 = rc.onTheMap(l14) && rc.sensePassability(l14) && !rc.canSenseRobotAtLocation(l14);
        cost14 = exists14 && rc.senseMapInfo(l14).getPaint().isAlly() ? 10 : 20;

        l15 = getLocationFromIndex(15, start);
        dist15 = 1000000;
        h15 = l15.distanceSquaredTo(destination);
        exists15 = rc.onTheMap(l15) && rc.sensePassability(l15) && !rc.canSenseRobotAtLocation(l15);
        cost15 = exists15 && rc.senseMapInfo(l15).getPaint().isAlly() ? 10 : 20;

        l16 = getLocationFromIndex(16, start);
        dist16 = 1000000;
        h16 = l16.distanceSquaredTo(destination);
        exists16 = rc.onTheMap(l16) && rc.sensePassability(l16) && !rc.canSenseRobotAtLocation(l16);
        cost16 = exists16 && rc.senseMapInfo(l16).getPaint().isAlly() ? 10 : 20;

        l18 = getLocationFromIndex(18, start);
        dist18 = 1000000;
        h18 = l18.distanceSquaredTo(destination);
        exists18 = rc.onTheMap(l18) && rc.sensePassability(l18) && !rc.canSenseRobotAtLocation(l18);
        cost18 = exists18 && rc.senseMapInfo(l18).getPaint().isAlly() ? 10 : 20;

        l19 = getLocationFromIndex(19, start);
        dist19 = 1000000;
        h19 = l19.distanceSquaredTo(destination);
        exists19 = rc.onTheMap(l19) && rc.sensePassability(l19) && !rc.canSenseRobotAtLocation(l19);
        cost19 = exists19 && rc.senseMapInfo(l19).getPaint().isAlly() ? 10 : 20;

        l20 = getLocationFromIndex(20, start);
        dist20 = 1000000;
        h20 = l20.distanceSquaredTo(destination);
        exists20 = rc.onTheMap(l20) && rc.sensePassability(l20) && !rc.canSenseRobotAtLocation(l20);
        cost20 = exists20 && rc.senseMapInfo(l20).getPaint().isAlly() ? 10 : 20;

        l21 = getLocationFromIndex(21, start);
        dist21 = 1000000;
        h21 = l21.distanceSquaredTo(destination);
        exists21 = rc.onTheMap(l21) && rc.sensePassability(l21) && !rc.canSenseRobotAtLocation(l21);
        cost21 = exists21 && rc.senseMapInfo(l21).getPaint().isAlly() ? 10 : 20;

        l22 = getLocationFromIndex(22, start);
        dist22 = 1000000;
        h22 = l22.distanceSquaredTo(destination);
        exists22 = rc.onTheMap(l22) && rc.sensePassability(l22) && !rc.canSenseRobotAtLocation(l22);
        cost22 = exists22 && rc.senseMapInfo(l22).getPaint().isAlly() ? 10 : 20;

        l23 = getLocationFromIndex(23, start);
        dist23 = 1000000;
        h23 = l23.distanceSquaredTo(destination);
        exists23 = rc.onTheMap(l23) && rc.sensePassability(l23) && !rc.canSenseRobotAtLocation(l23);
        cost23 = exists23 && rc.senseMapInfo(l23).getPaint().isAlly() ? 10 : 20;

        l24 = getLocationFromIndex(24, start);
        dist24 = 1000000;
        h24 = l24.distanceSquaredTo(destination);
        exists24 = rc.onTheMap(l24) && rc.sensePassability(l24) && !rc.canSenseRobotAtLocation(l24);
        cost24 = exists24 && rc.senseMapInfo(l24).getPaint().isAlly() ? 10 : 20;

        l25 = getLocationFromIndex(25, start);
        dist25 = 1000000;
        h25 = l25.distanceSquaredTo(destination);
        exists25 = rc.onTheMap(l25) && rc.sensePassability(l25) && !rc.canSenseRobotAtLocation(l25);
        cost25 = exists25 && rc.senseMapInfo(l25).getPaint().isAlly() ? 10 : 20;

        l26 = getLocationFromIndex(26, start);
        dist26 = 1000000;
        h26 = l26.distanceSquaredTo(destination);
        exists26 = rc.onTheMap(l26) && rc.sensePassability(l26) && !rc.canSenseRobotAtLocation(l26);
        cost26 = exists26 && rc.senseMapInfo(l26).getPaint().isAlly() ? 10 : 20;

        l27 = getLocationFromIndex(27, start);
        dist27 = 1000000;
        h27 = l27.distanceSquaredTo(destination);
        exists27 = rc.onTheMap(l27) && rc.sensePassability(l27) && !rc.canSenseRobotAtLocation(l27);
        cost27 = exists27 && rc.senseMapInfo(l27).getPaint().isAlly() ? 10 : 20;

        l28 = getLocationFromIndex(28, start);
        dist28 = 1000000;
        h28 = l28.distanceSquaredTo(destination);
        exists28 = rc.onTheMap(l28) && rc.sensePassability(l28) && !rc.canSenseRobotAtLocation(l28);
        cost28 = exists28 && rc.senseMapInfo(l28).getPaint().isAlly() ? 10 : 20;

        l29 = getLocationFromIndex(29, start);
        dist29 = 1000000;
        h29 = l29.distanceSquaredTo(destination);
        exists29 = rc.onTheMap(l29) && rc.sensePassability(l29) && !rc.canSenseRobotAtLocation(l29);
        cost29 = exists29 && rc.senseMapInfo(l29).getPaint().isAlly() ? 10 : 20;

        l30 = getLocationFromIndex(30, start);
        dist30 = 1000000;
        h30 = l30.distanceSquaredTo(destination);
        exists30 = rc.onTheMap(l30) && rc.sensePassability(l30) && !rc.canSenseRobotAtLocation(l30);
        cost30 = exists30 && rc.senseMapInfo(l30).getPaint().isAlly() ? 10 : 20;

        l31 = getLocationFromIndex(31, start);
        dist31 = 1000000;
        h31 = l31.distanceSquaredTo(destination);
        exists31 = rc.onTheMap(l31) && rc.sensePassability(l31) && !rc.canSenseRobotAtLocation(l31);
        cost31 = exists31 && rc.senseMapInfo(l31).getPaint().isAlly() ? 10 : 20;

        l32 = getLocationFromIndex(32, start);
        dist32 = 1000000;
        h32 = l32.distanceSquaredTo(destination);
        exists32 = rc.onTheMap(l32) && rc.sensePassability(l32) && !rc.canSenseRobotAtLocation(l32);
        cost32 = exists32 && rc.senseMapInfo(l32).getPaint().isAlly() ? 10 : 20;

        l33 = getLocationFromIndex(33, start);
        dist33 = 1000000;
        h33 = l33.distanceSquaredTo(destination);
        exists33 = rc.onTheMap(l33) && rc.sensePassability(l33) && !rc.canSenseRobotAtLocation(l33);
        cost33 = exists33 && rc.senseMapInfo(l33).getPaint().isAlly() ? 10 : 20;

        l34 = getLocationFromIndex(34, start);
        dist34 = 1000000;
        h34 = l34.distanceSquaredTo(destination);
        exists34 = rc.onTheMap(l34) && rc.sensePassability(l34) && !rc.canSenseRobotAtLocation(l34);
        cost34 = exists34 && rc.senseMapInfo(l34).getPaint().isAlly() ? 10 : 20;

        l35 = getLocationFromIndex(35, start);
        dist35 = 1000000;
        h35 = l35.distanceSquaredTo(destination);
        exists35 = rc.onTheMap(l35) && rc.sensePassability(l35) && !rc.canSenseRobotAtLocation(l35);
        cost35 = exists35 && rc.senseMapInfo(l35).getPaint().isAlly() ? 10 : 20;

        l36 = getLocationFromIndex(36, start);
        dist36 = 1000000;
        h36 = l36.distanceSquaredTo(destination);
        exists36 = rc.onTheMap(l36) && rc.sensePassability(l36) && !rc.canSenseRobotAtLocation(l36);
        cost36 = exists36 && rc.senseMapInfo(l36).getPaint().isAlly() ? 10 : 20;

        l37 = getLocationFromIndex(37, start);
        dist37 = 1000000;
        h37 = l37.distanceSquaredTo(destination);
        exists37 = rc.onTheMap(l37) && rc.sensePassability(l37) && !rc.canSenseRobotAtLocation(l37);
        cost37 = exists37 && rc.senseMapInfo(l37).getPaint().isAlly() ? 10 : 20;

        l38 = getLocationFromIndex(38, start);
        dist38 = 1000000;
        h38 = l38.distanceSquaredTo(destination);
        exists38 = rc.onTheMap(l38) && rc.sensePassability(l38) && !rc.canSenseRobotAtLocation(l38);
        cost38 = exists38 && rc.senseMapInfo(l38).getPaint().isAlly() ? 10 : 20;

        l39 = getLocationFromIndex(39, start);
        dist39 = 1000000;
        h39 = l39.distanceSquaredTo(destination);
        exists39 = rc.onTheMap(l39) && rc.sensePassability(l39) && !rc.canSenseRobotAtLocation(l39);
        cost39 = exists39 && rc.senseMapInfo(l39).getPaint().isAlly() ? 10 : 20;

        l40 = getLocationFromIndex(40, start);
        dist40 = 0;
        h40 = l40.distanceSquaredTo(destination);
        exists40 = true;
        cost40 = 0;

        l41 = getLocationFromIndex(41, start);
        dist41 = 1000000;
        h41 = l41.distanceSquaredTo(destination);
        exists41 = rc.onTheMap(l41) && rc.sensePassability(l41) && !rc.canSenseRobotAtLocation(l41);
        cost41 = exists41 && rc.senseMapInfo(l41).getPaint().isAlly() ? 10 : 20;

        l42 = getLocationFromIndex(42, start);
        dist42 = 1000000;
        h42 = l42.distanceSquaredTo(destination);
        exists42 = rc.onTheMap(l42) && rc.sensePassability(l42) && !rc.canSenseRobotAtLocation(l42);
        cost42 = exists42 && rc.senseMapInfo(l42).getPaint().isAlly() ? 10 : 20;

        l43 = getLocationFromIndex(43, start);
        dist43 = 1000000;
        h43 = l43.distanceSquaredTo(destination);
        exists43 = rc.onTheMap(l43) && rc.sensePassability(l43) && !rc.canSenseRobotAtLocation(l43);
        cost43 = exists43 && rc.senseMapInfo(l43).getPaint().isAlly() ? 10 : 20;

        l44 = getLocationFromIndex(44, start);
        dist44 = 1000000;
        h44 = l44.distanceSquaredTo(destination);
        exists44 = rc.onTheMap(l44) && rc.sensePassability(l44) && !rc.canSenseRobotAtLocation(l44);
        cost44 = exists44 && rc.senseMapInfo(l44).getPaint().isAlly() ? 10 : 20;

        l45 = getLocationFromIndex(45, start);
        dist45 = 1000000;
        h45 = l45.distanceSquaredTo(destination);
        exists45 = rc.onTheMap(l45) && rc.sensePassability(l45) && !rc.canSenseRobotAtLocation(l45);
        cost45 = exists45 && rc.senseMapInfo(l45).getPaint().isAlly() ? 10 : 20;

        l46 = getLocationFromIndex(46, start);
        dist46 = 1000000;
        h46 = l46.distanceSquaredTo(destination);
        exists46 = rc.onTheMap(l46) && rc.sensePassability(l46) && !rc.canSenseRobotAtLocation(l46);
        cost46 = exists46 && rc.senseMapInfo(l46).getPaint().isAlly() ? 10 : 20;

        l47 = getLocationFromIndex(47, start);
        dist47 = 1000000;
        h47 = l47.distanceSquaredTo(destination);
        exists47 = rc.onTheMap(l47) && rc.sensePassability(l47) && !rc.canSenseRobotAtLocation(l47);
        cost47 = exists47 && rc.senseMapInfo(l47).getPaint().isAlly() ? 10 : 20;

        l48 = getLocationFromIndex(48, start);
        dist48 = 1000000;
        h48 = l48.distanceSquaredTo(destination);
        exists48 = rc.onTheMap(l48) && rc.sensePassability(l48) && !rc.canSenseRobotAtLocation(l48);
        cost48 = exists48 && rc.senseMapInfo(l48).getPaint().isAlly() ? 10 : 20;

        l49 = getLocationFromIndex(49, start);
        dist49 = 1000000;
        h49 = l49.distanceSquaredTo(destination);
        exists49 = rc.onTheMap(l49) && rc.sensePassability(l49) && !rc.canSenseRobotAtLocation(l49);
        cost49 = exists49 && rc.senseMapInfo(l49).getPaint().isAlly() ? 10 : 20;

        l50 = getLocationFromIndex(50, start);
        dist50 = 1000000;
        h50 = l50.distanceSquaredTo(destination);
        exists50 = rc.onTheMap(l50) && rc.sensePassability(l50) && !rc.canSenseRobotAtLocation(l50);
        cost50 = exists50 && rc.senseMapInfo(l50).getPaint().isAlly() ? 10 : 20;

        l51 = getLocationFromIndex(51, start);
        dist51 = 1000000;
        h51 = l51.distanceSquaredTo(destination);
        exists51 = rc.onTheMap(l51) && rc.sensePassability(l51) && !rc.canSenseRobotAtLocation(l51);
        cost51 = exists51 && rc.senseMapInfo(l51).getPaint().isAlly() ? 10 : 20;

        l52 = getLocationFromIndex(52, start);
        dist52 = 1000000;
        h52 = l52.distanceSquaredTo(destination);
        exists52 = rc.onTheMap(l52) && rc.sensePassability(l52) && !rc.canSenseRobotAtLocation(l52);
        cost52 = exists52 && rc.senseMapInfo(l52).getPaint().isAlly() ? 10 : 20;

        l53 = getLocationFromIndex(53, start);
        dist53 = 1000000;
        h53 = l53.distanceSquaredTo(destination);
        exists53 = rc.onTheMap(l53) && rc.sensePassability(l53) && !rc.canSenseRobotAtLocation(l53);
        cost53 = exists53 && rc.senseMapInfo(l53).getPaint().isAlly() ? 10 : 20;

        l54 = getLocationFromIndex(54, start);
        dist54 = 1000000;
        h54 = l54.distanceSquaredTo(destination);
        exists54 = rc.onTheMap(l54) && rc.sensePassability(l54) && !rc.canSenseRobotAtLocation(l54);
        cost54 = exists54 && rc.senseMapInfo(l54).getPaint().isAlly() ? 10 : 20;

        l55 = getLocationFromIndex(55, start);
        dist55 = 1000000;
        h55 = l55.distanceSquaredTo(destination);
        exists55 = rc.onTheMap(l55) && rc.sensePassability(l55) && !rc.canSenseRobotAtLocation(l55);
        cost55 = exists55 && rc.senseMapInfo(l55).getPaint().isAlly() ? 10 : 20;

        l56 = getLocationFromIndex(56, start);
        dist56 = 1000000;
        h56 = l56.distanceSquaredTo(destination);
        exists56 = rc.onTheMap(l56) && rc.sensePassability(l56) && !rc.canSenseRobotAtLocation(l56);
        cost56 = exists56 && rc.senseMapInfo(l56).getPaint().isAlly() ? 10 : 20;

        l57 = getLocationFromIndex(57, start);
        dist57 = 1000000;
        h57 = l57.distanceSquaredTo(destination);
        exists57 = rc.onTheMap(l57) && rc.sensePassability(l57) && !rc.canSenseRobotAtLocation(l57);
        cost57 = exists57 && rc.senseMapInfo(l57).getPaint().isAlly() ? 10 : 20;

        l58 = getLocationFromIndex(58, start);
        dist58 = 1000000;
        h58 = l58.distanceSquaredTo(destination);
        exists58 = rc.onTheMap(l58) && rc.sensePassability(l58) && !rc.canSenseRobotAtLocation(l58);
        cost58 = exists58 && rc.senseMapInfo(l58).getPaint().isAlly() ? 10 : 20;

        l59 = getLocationFromIndex(59, start);
        dist59 = 1000000;
        h59 = l59.distanceSquaredTo(destination);
        exists59 = rc.onTheMap(l59) && rc.sensePassability(l59) && !rc.canSenseRobotAtLocation(l59);
        cost59 = exists59 && rc.senseMapInfo(l59).getPaint().isAlly() ? 10 : 20;

        l60 = getLocationFromIndex(60, start);
        dist60 = 1000000;
        h60 = l60.distanceSquaredTo(destination);
        exists60 = rc.onTheMap(l60) && rc.sensePassability(l60) && !rc.canSenseRobotAtLocation(l60);
        cost60 = exists60 && rc.senseMapInfo(l60).getPaint().isAlly() ? 10 : 20;

        l61 = getLocationFromIndex(61, start);
        dist61 = 1000000;
        h61 = l61.distanceSquaredTo(destination);
        exists61 = rc.onTheMap(l61) && rc.sensePassability(l61) && !rc.canSenseRobotAtLocation(l61);
        cost61 = exists61 && rc.senseMapInfo(l61).getPaint().isAlly() ? 10 : 20;

        l62 = getLocationFromIndex(62, start);
        dist62 = 1000000;
        h62 = l62.distanceSquaredTo(destination);
        exists62 = rc.onTheMap(l62) && rc.sensePassability(l62) && !rc.canSenseRobotAtLocation(l62);
        cost62 = exists62 && rc.senseMapInfo(l62).getPaint().isAlly() ? 10 : 20;

        l64 = getLocationFromIndex(64, start);
        dist64 = 1000000;
        h64 = l64.distanceSquaredTo(destination);
        exists64 = rc.onTheMap(l64) && rc.sensePassability(l64) && !rc.canSenseRobotAtLocation(l64);
        cost64 = exists64 && rc.senseMapInfo(l64).getPaint().isAlly() ? 10 : 20;

        l65 = getLocationFromIndex(65, start);
        dist65 = 1000000;
        h65 = l65.distanceSquaredTo(destination);
        exists65 = rc.onTheMap(l65) && rc.sensePassability(l65) && !rc.canSenseRobotAtLocation(l65);
        cost65 = exists65 && rc.senseMapInfo(l65).getPaint().isAlly() ? 10 : 20;

        l66 = getLocationFromIndex(66, start);
        dist66 = 1000000;
        h66 = l66.distanceSquaredTo(destination);
        exists66 = rc.onTheMap(l66) && rc.sensePassability(l66) && !rc.canSenseRobotAtLocation(l66);
        cost66 = exists66 && rc.senseMapInfo(l66).getPaint().isAlly() ? 10 : 20;

        l67 = getLocationFromIndex(67, start);
        dist67 = 1000000;
        h67 = l67.distanceSquaredTo(destination);
        exists67 = rc.onTheMap(l67) && rc.sensePassability(l67) && !rc.canSenseRobotAtLocation(l67);
        cost67 = exists67 && rc.senseMapInfo(l67).getPaint().isAlly() ? 10 : 20;

        l68 = getLocationFromIndex(68, start);
        dist68 = 1000000;
        h68 = l68.distanceSquaredTo(destination);
        exists68 = rc.onTheMap(l68) && rc.sensePassability(l68) && !rc.canSenseRobotAtLocation(l68);
        cost68 = exists68 && rc.senseMapInfo(l68).getPaint().isAlly() ? 10 : 20;

        l69 = getLocationFromIndex(69, start);
        dist69 = 1000000;
        h69 = l69.distanceSquaredTo(destination);
        exists69 = rc.onTheMap(l69) && rc.sensePassability(l69) && !rc.canSenseRobotAtLocation(l69);
        cost69 = exists69 && rc.senseMapInfo(l69).getPaint().isAlly() ? 10 : 20;

        l70 = getLocationFromIndex(70, start);
        dist70 = 1000000;
        h70 = l70.distanceSquaredTo(destination);
        exists70 = rc.onTheMap(l70) && rc.sensePassability(l70) && !rc.canSenseRobotAtLocation(l70);
        cost70 = exists70 && rc.senseMapInfo(l70).getPaint().isAlly() ? 10 : 20;

        l74 = getLocationFromIndex(74, start);
        dist74 = 1000000;
        h74 = l74.distanceSquaredTo(destination);
        exists74 = rc.onTheMap(l74) && rc.sensePassability(l74) && !rc.canSenseRobotAtLocation(l74);
        cost74 = exists74 && rc.senseMapInfo(l74).getPaint().isAlly() ? 10 : 20;

        l75 = getLocationFromIndex(75, start);
        dist75 = 1000000;
        h75 = l75.distanceSquaredTo(destination);
        exists75 = rc.onTheMap(l75) && rc.sensePassability(l75) && !rc.canSenseRobotAtLocation(l75);
        cost75 = exists75 && rc.senseMapInfo(l75).getPaint().isAlly() ? 10 : 20;

        l76 = getLocationFromIndex(76, start);
        dist76 = 1000000;
        h76 = l76.distanceSquaredTo(destination);
        exists76 = rc.onTheMap(l76) && rc.sensePassability(l76) && !rc.canSenseRobotAtLocation(l76);
        cost76 = exists76 && rc.senseMapInfo(l76).getPaint().isAlly() ? 10 : 20;

        l77 = getLocationFromIndex(77, start);
        dist77 = 1000000;
        h77 = l77.distanceSquaredTo(destination);
        exists77 = rc.onTheMap(l77) && rc.sensePassability(l77) && !rc.canSenseRobotAtLocation(l77);
        cost77 = exists77 && rc.senseMapInfo(l77).getPaint().isAlly() ? 10 : 20;

        l78 = getLocationFromIndex(78, start);
        dist78 = 1000000;
        h78 = l78.distanceSquaredTo(destination);
        exists78 = rc.onTheMap(l78) && rc.sensePassability(l78) && !rc.canSenseRobotAtLocation(l78);
        cost78 = exists78 && rc.senseMapInfo(l78).getPaint().isAlly() ? 10 : 20;

    }

    public static Direction pathfind(RobotController rc, MapLocation destination) throws GameActionException
    {
        MapLocation start = rc.getLocation();
        initializeVariables(rc, destination, start);
        lookup = new Direction[9][9];

        int closestDistance = Integer.MAX_VALUE;
        int closestIndex = -1;
        if(exists40)
        {
            if(exists30 && dist40 + cost30 + 14 < dist30)
            {
                dist30 = dist40 + cost30 + 14;
                lookup[indexToLocalY(30)][indexToLocalX(30)] = Direction.SOUTHEAST;
                if(h30 < closestDistance)
                {
                    closestDistance = h30;
                    closestIndex = 30;
                }
            }
            if(exists31 && dist40 + cost31 + 10 < dist31)
            {
                dist31 = dist40 + cost31 + 10;
                lookup[indexToLocalY(31)][indexToLocalX(31)] = Direction.SOUTH;
                if(h31 < closestDistance)
                {
                    closestDistance = h31;
                    closestIndex = 31;
                }
            }
            if(exists32 && dist40 + cost32 + 14 < dist32)
            {
                dist32 = dist40 + cost32 + 14;
                lookup[indexToLocalY(32)][indexToLocalX(32)] = Direction.SOUTHWEST;
                if(h32 < closestDistance)
                {
                    closestDistance = h32;
                    closestIndex = 32;
                }
            }
            if(exists39 && dist40 + cost39 + 10 < dist39)
            {
                dist39 = dist40 + cost39 + 10;
                lookup[indexToLocalY(39)][indexToLocalX(39)] = Direction.EAST;
                if(h39 < closestDistance)
                {
                    closestDistance = h39;
                    closestIndex = 39;
                }
            }
            if(exists41 && dist40 + cost41 + 10 < dist41)
            {
                dist41 = dist40 + cost41 + 10;
                lookup[indexToLocalY(41)][indexToLocalX(41)] = Direction.WEST;
                if(h41 < closestDistance)
                {
                    closestDistance = h41;
                    closestIndex = 41;
                }
            }
            if(exists48 && dist40 + cost48 + 14 < dist48)
            {
                dist48 = dist40 + cost48 + 14;
                lookup[indexToLocalY(48)][indexToLocalX(48)] = Direction.NORTHEAST;
                if(h48 < closestDistance)
                {
                    closestDistance = h48;
                    closestIndex = 48;
                }
            }
            if(exists49 && dist40 + cost49 + 10 < dist49)
            {
                dist49 = dist40 + cost49 + 10;
                lookup[indexToLocalY(49)][indexToLocalX(49)] = Direction.NORTH;
                if(h49 < closestDistance)
                {
                    closestDistance = h49;
                    closestIndex = 49;
                }
            }
            if(exists50 && dist40 + cost50 + 14 < dist50)
            {
                dist50 = dist40 + cost50 + 14;
                lookup[indexToLocalY(50)][indexToLocalX(50)] = Direction.NORTHWEST;
                if(h50 < closestDistance)
                {
                    closestDistance = h50;
                    closestIndex = 50;
                }
            }
        }
        if(exists30)
        {
            if(exists20 && dist30 + cost20 + 14 < dist20)
            {
                dist20 = dist30 + cost20 + 14;
                lookup[indexToLocalY(20)][indexToLocalX(20)] = Direction.SOUTHEAST;
                if(h20 < closestDistance)
                {
                    closestDistance = h20;
                    closestIndex = 20;
                }
            }
            if(exists21 && dist30 + cost21 + 10 < dist21)
            {
                dist21 = dist30 + cost21 + 10;
                lookup[indexToLocalY(21)][indexToLocalX(21)] = Direction.SOUTH;
                if(h21 < closestDistance)
                {
                    closestDistance = h21;
                    closestIndex = 21;
                }
            }
            if(exists22 && dist30 + cost22 + 14 < dist22)
            {
                dist22 = dist30 + cost22 + 14;
                lookup[indexToLocalY(22)][indexToLocalX(22)] = Direction.SOUTHWEST;
                if(h22 < closestDistance)
                {
                    closestDistance = h22;
                    closestIndex = 22;
                }
            }
            if(exists29 && dist30 + cost29 + 10 < dist29)
            {
                dist29 = dist30 + cost29 + 10;
                lookup[indexToLocalY(29)][indexToLocalX(29)] = Direction.EAST;
                if(h29 < closestDistance)
                {
                    closestDistance = h29;
                    closestIndex = 29;
                }
            }
            if(exists31 && dist30 + cost31 + 10 < dist31)
            {
                dist31 = dist30 + cost31 + 10;
                lookup[indexToLocalY(31)][indexToLocalX(31)] = Direction.WEST;
                if(h31 < closestDistance)
                {
                    closestDistance = h31;
                    closestIndex = 31;
                }
            }
            if(exists38 && dist30 + cost38 + 14 < dist38)
            {
                dist38 = dist30 + cost38 + 14;
                lookup[indexToLocalY(38)][indexToLocalX(38)] = Direction.NORTHEAST;
                if(h38 < closestDistance)
                {
                    closestDistance = h38;
                    closestIndex = 38;
                }
            }
            if(exists39 && dist30 + cost39 + 10 < dist39)
            {
                dist39 = dist30 + cost39 + 10;
                lookup[indexToLocalY(39)][indexToLocalX(39)] = Direction.NORTH;
                if(h39 < closestDistance)
                {
                    closestDistance = h39;
                    closestIndex = 39;
                }
            }
            if(exists40 && dist30 + cost40 + 14 < dist40)
            {
                dist40 = dist30 + cost40 + 14;
                lookup[indexToLocalY(40)][indexToLocalX(40)] = Direction.NORTHWEST;
                if(h40 < closestDistance)
                {
                    closestDistance = h40;
                    closestIndex = 40;
                }
            }
        }
        if(exists31)
        {
            if(exists21 && dist31 + cost21 + 14 < dist21)
            {
                dist21 = dist31 + cost21 + 14;
                lookup[indexToLocalY(21)][indexToLocalX(21)] = Direction.SOUTHEAST;
                if(h21 < closestDistance)
                {
                    closestDistance = h21;
                    closestIndex = 21;
                }
            }
            if(exists22 && dist31 + cost22 + 10 < dist22)
            {
                dist22 = dist31 + cost22 + 10;
                lookup[indexToLocalY(22)][indexToLocalX(22)] = Direction.SOUTH;
                if(h22 < closestDistance)
                {
                    closestDistance = h22;
                    closestIndex = 22;
                }
            }
            if(exists23 && dist31 + cost23 + 14 < dist23)
            {
                dist23 = dist31 + cost23 + 14;
                lookup[indexToLocalY(23)][indexToLocalX(23)] = Direction.SOUTHWEST;
                if(h23 < closestDistance)
                {
                    closestDistance = h23;
                    closestIndex = 23;
                }
            }
            if(exists30 && dist31 + cost30 + 10 < dist30)
            {
                dist30 = dist31 + cost30 + 10;
                lookup[indexToLocalY(30)][indexToLocalX(30)] = Direction.EAST;
                if(h30 < closestDistance)
                {
                    closestDistance = h30;
                    closestIndex = 30;
                }
            }
            if(exists32 && dist31 + cost32 + 10 < dist32)
            {
                dist32 = dist31 + cost32 + 10;
                lookup[indexToLocalY(32)][indexToLocalX(32)] = Direction.WEST;
                if(h32 < closestDistance)
                {
                    closestDistance = h32;
                    closestIndex = 32;
                }
            }
            if(exists39 && dist31 + cost39 + 14 < dist39)
            {
                dist39 = dist31 + cost39 + 14;
                lookup[indexToLocalY(39)][indexToLocalX(39)] = Direction.NORTHEAST;
                if(h39 < closestDistance)
                {
                    closestDistance = h39;
                    closestIndex = 39;
                }
            }
            if(exists40 && dist31 + cost40 + 10 < dist40)
            {
                dist40 = dist31 + cost40 + 10;
                lookup[indexToLocalY(40)][indexToLocalX(40)] = Direction.NORTH;
                if(h40 < closestDistance)
                {
                    closestDistance = h40;
                    closestIndex = 40;
                }
            }
            if(exists41 && dist31 + cost41 + 14 < dist41)
            {
                dist41 = dist31 + cost41 + 14;
                lookup[indexToLocalY(41)][indexToLocalX(41)] = Direction.NORTHWEST;
                if(h41 < closestDistance)
                {
                    closestDistance = h41;
                    closestIndex = 41;
                }
            }
        }
        if(exists32)
        {
            if(exists22 && dist32 + cost22 + 14 < dist22)
            {
                dist22 = dist32 + cost22 + 14;
                lookup[indexToLocalY(22)][indexToLocalX(22)] = Direction.SOUTHEAST;
                if(h22 < closestDistance)
                {
                    closestDistance = h22;
                    closestIndex = 22;
                }
            }
            if(exists23 && dist32 + cost23 + 10 < dist23)
            {
                dist23 = dist32 + cost23 + 10;
                lookup[indexToLocalY(23)][indexToLocalX(23)] = Direction.SOUTH;
                if(h23 < closestDistance)
                {
                    closestDistance = h23;
                    closestIndex = 23;
                }
            }
            if(exists24 && dist32 + cost24 + 14 < dist24)
            {
                dist24 = dist32 + cost24 + 14;
                lookup[indexToLocalY(24)][indexToLocalX(24)] = Direction.SOUTHWEST;
                if(h24 < closestDistance)
                {
                    closestDistance = h24;
                    closestIndex = 24;
                }
            }
            if(exists31 && dist32 + cost31 + 10 < dist31)
            {
                dist31 = dist32 + cost31 + 10;
                lookup[indexToLocalY(31)][indexToLocalX(31)] = Direction.EAST;
                if(h31 < closestDistance)
                {
                    closestDistance = h31;
                    closestIndex = 31;
                }
            }
            if(exists33 && dist32 + cost33 + 10 < dist33)
            {
                dist33 = dist32 + cost33 + 10;
                lookup[indexToLocalY(33)][indexToLocalX(33)] = Direction.WEST;
                if(h33 < closestDistance)
                {
                    closestDistance = h33;
                    closestIndex = 33;
                }
            }
            if(exists40 && dist32 + cost40 + 14 < dist40)
            {
                dist40 = dist32 + cost40 + 14;
                lookup[indexToLocalY(40)][indexToLocalX(40)] = Direction.NORTHEAST;
                if(h40 < closestDistance)
                {
                    closestDistance = h40;
                    closestIndex = 40;
                }
            }
            if(exists41 && dist32 + cost41 + 10 < dist41)
            {
                dist41 = dist32 + cost41 + 10;
                lookup[indexToLocalY(41)][indexToLocalX(41)] = Direction.NORTH;
                if(h41 < closestDistance)
                {
                    closestDistance = h41;
                    closestIndex = 41;
                }
            }
            if(exists42 && dist32 + cost42 + 14 < dist42)
            {
                dist42 = dist32 + cost42 + 14;
                lookup[indexToLocalY(42)][indexToLocalX(42)] = Direction.NORTHWEST;
                if(h42 < closestDistance)
                {
                    closestDistance = h42;
                    closestIndex = 42;
                }
            }
        }
        if(exists39)
        {
            if(exists29 && dist39 + cost29 + 14 < dist29)
            {
                dist29 = dist39 + cost29 + 14;
                lookup[indexToLocalY(29)][indexToLocalX(29)] = Direction.SOUTHEAST;
                if(h29 < closestDistance)
                {
                    closestDistance = h29;
                    closestIndex = 29;
                }
            }
            if(exists30 && dist39 + cost30 + 10 < dist30)
            {
                dist30 = dist39 + cost30 + 10;
                lookup[indexToLocalY(30)][indexToLocalX(30)] = Direction.SOUTH;
                if(h30 < closestDistance)
                {
                    closestDistance = h30;
                    closestIndex = 30;
                }
            }
            if(exists31 && dist39 + cost31 + 14 < dist31)
            {
                dist31 = dist39 + cost31 + 14;
                lookup[indexToLocalY(31)][indexToLocalX(31)] = Direction.SOUTHWEST;
                if(h31 < closestDistance)
                {
                    closestDistance = h31;
                    closestIndex = 31;
                }
            }
            if(exists38 && dist39 + cost38 + 10 < dist38)
            {
                dist38 = dist39 + cost38 + 10;
                lookup[indexToLocalY(38)][indexToLocalX(38)] = Direction.EAST;
                if(h38 < closestDistance)
                {
                    closestDistance = h38;
                    closestIndex = 38;
                }
            }
            if(exists40 && dist39 + cost40 + 10 < dist40)
            {
                dist40 = dist39 + cost40 + 10;
                lookup[indexToLocalY(40)][indexToLocalX(40)] = Direction.WEST;
                if(h40 < closestDistance)
                {
                    closestDistance = h40;
                    closestIndex = 40;
                }
            }
            if(exists47 && dist39 + cost47 + 14 < dist47)
            {
                dist47 = dist39 + cost47 + 14;
                lookup[indexToLocalY(47)][indexToLocalX(47)] = Direction.NORTHEAST;
                if(h47 < closestDistance)
                {
                    closestDistance = h47;
                    closestIndex = 47;
                }
            }
            if(exists48 && dist39 + cost48 + 10 < dist48)
            {
                dist48 = dist39 + cost48 + 10;
                lookup[indexToLocalY(48)][indexToLocalX(48)] = Direction.NORTH;
                if(h48 < closestDistance)
                {
                    closestDistance = h48;
                    closestIndex = 48;
                }
            }
            if(exists49 && dist39 + cost49 + 14 < dist49)
            {
                dist49 = dist39 + cost49 + 14;
                lookup[indexToLocalY(49)][indexToLocalX(49)] = Direction.NORTHWEST;
                if(h49 < closestDistance)
                {
                    closestDistance = h49;
                    closestIndex = 49;
                }
            }
        }
        if(exists41)
        {
            if(exists31 && dist41 + cost31 + 14 < dist31)
            {
                dist31 = dist41 + cost31 + 14;
                lookup[indexToLocalY(31)][indexToLocalX(31)] = Direction.SOUTHEAST;
                if(h31 < closestDistance)
                {
                    closestDistance = h31;
                    closestIndex = 31;
                }
            }
            if(exists32 && dist41 + cost32 + 10 < dist32)
            {
                dist32 = dist41 + cost32 + 10;
                lookup[indexToLocalY(32)][indexToLocalX(32)] = Direction.SOUTH;
                if(h32 < closestDistance)
                {
                    closestDistance = h32;
                    closestIndex = 32;
                }
            }
            if(exists33 && dist41 + cost33 + 14 < dist33)
            {
                dist33 = dist41 + cost33 + 14;
                lookup[indexToLocalY(33)][indexToLocalX(33)] = Direction.SOUTHWEST;
                if(h33 < closestDistance)
                {
                    closestDistance = h33;
                    closestIndex = 33;
                }
            }
            if(exists40 && dist41 + cost40 + 10 < dist40)
            {
                dist40 = dist41 + cost40 + 10;
                lookup[indexToLocalY(40)][indexToLocalX(40)] = Direction.EAST;
                if(h40 < closestDistance)
                {
                    closestDistance = h40;
                    closestIndex = 40;
                }
            }
            if(exists42 && dist41 + cost42 + 10 < dist42)
            {
                dist42 = dist41 + cost42 + 10;
                lookup[indexToLocalY(42)][indexToLocalX(42)] = Direction.WEST;
                if(h42 < closestDistance)
                {
                    closestDistance = h42;
                    closestIndex = 42;
                }
            }
            if(exists49 && dist41 + cost49 + 14 < dist49)
            {
                dist49 = dist41 + cost49 + 14;
                lookup[indexToLocalY(49)][indexToLocalX(49)] = Direction.NORTHEAST;
                if(h49 < closestDistance)
                {
                    closestDistance = h49;
                    closestIndex = 49;
                }
            }
            if(exists50 && dist41 + cost50 + 10 < dist50)
            {
                dist50 = dist41 + cost50 + 10;
                lookup[indexToLocalY(50)][indexToLocalX(50)] = Direction.NORTH;
                if(h50 < closestDistance)
                {
                    closestDistance = h50;
                    closestIndex = 50;
                }
            }
            if(exists51 && dist41 + cost51 + 14 < dist51)
            {
                dist51 = dist41 + cost51 + 14;
                lookup[indexToLocalY(51)][indexToLocalX(51)] = Direction.NORTHWEST;
                if(h51 < closestDistance)
                {
                    closestDistance = h51;
                    closestIndex = 51;
                }
            }
        }
        if(exists48)
        {
            if(exists38 && dist48 + cost38 + 14 < dist38)
            {
                dist38 = dist48 + cost38 + 14;
                lookup[indexToLocalY(38)][indexToLocalX(38)] = Direction.SOUTHEAST;
                if(h38 < closestDistance)
                {
                    closestDistance = h38;
                    closestIndex = 38;
                }
            }
            if(exists39 && dist48 + cost39 + 10 < dist39)
            {
                dist39 = dist48 + cost39 + 10;
                lookup[indexToLocalY(39)][indexToLocalX(39)] = Direction.SOUTH;
                if(h39 < closestDistance)
                {
                    closestDistance = h39;
                    closestIndex = 39;
                }
            }
            if(exists40 && dist48 + cost40 + 14 < dist40)
            {
                dist40 = dist48 + cost40 + 14;
                lookup[indexToLocalY(40)][indexToLocalX(40)] = Direction.SOUTHWEST;
                if(h40 < closestDistance)
                {
                    closestDistance = h40;
                    closestIndex = 40;
                }
            }
            if(exists47 && dist48 + cost47 + 10 < dist47)
            {
                dist47 = dist48 + cost47 + 10;
                lookup[indexToLocalY(47)][indexToLocalX(47)] = Direction.EAST;
                if(h47 < closestDistance)
                {
                    closestDistance = h47;
                    closestIndex = 47;
                }
            }
            if(exists49 && dist48 + cost49 + 10 < dist49)
            {
                dist49 = dist48 + cost49 + 10;
                lookup[indexToLocalY(49)][indexToLocalX(49)] = Direction.WEST;
                if(h49 < closestDistance)
                {
                    closestDistance = h49;
                    closestIndex = 49;
                }
            }
            if(exists56 && dist48 + cost56 + 14 < dist56)
            {
                dist56 = dist48 + cost56 + 14;
                lookup[indexToLocalY(56)][indexToLocalX(56)] = Direction.NORTHEAST;
                if(h56 < closestDistance)
                {
                    closestDistance = h56;
                    closestIndex = 56;
                }
            }
            if(exists57 && dist48 + cost57 + 10 < dist57)
            {
                dist57 = dist48 + cost57 + 10;
                lookup[indexToLocalY(57)][indexToLocalX(57)] = Direction.NORTH;
                if(h57 < closestDistance)
                {
                    closestDistance = h57;
                    closestIndex = 57;
                }
            }
            if(exists58 && dist48 + cost58 + 14 < dist58)
            {
                dist58 = dist48 + cost58 + 14;
                lookup[indexToLocalY(58)][indexToLocalX(58)] = Direction.NORTHWEST;
                if(h58 < closestDistance)
                {
                    closestDistance = h58;
                    closestIndex = 58;
                }
            }
        }
        if(exists49)
        {
            if(exists39 && dist49 + cost39 + 14 < dist39)
            {
                dist39 = dist49 + cost39 + 14;
                lookup[indexToLocalY(39)][indexToLocalX(39)] = Direction.SOUTHEAST;
                if(h39 < closestDistance)
                {
                    closestDistance = h39;
                    closestIndex = 39;
                }
            }
            if(exists40 && dist49 + cost40 + 10 < dist40)
            {
                dist40 = dist49 + cost40 + 10;
                lookup[indexToLocalY(40)][indexToLocalX(40)] = Direction.SOUTH;
                if(h40 < closestDistance)
                {
                    closestDistance = h40;
                    closestIndex = 40;
                }
            }
            if(exists41 && dist49 + cost41 + 14 < dist41)
            {
                dist41 = dist49 + cost41 + 14;
                lookup[indexToLocalY(41)][indexToLocalX(41)] = Direction.SOUTHWEST;
                if(h41 < closestDistance)
                {
                    closestDistance = h41;
                    closestIndex = 41;
                }
            }
            if(exists48 && dist49 + cost48 + 10 < dist48)
            {
                dist48 = dist49 + cost48 + 10;
                lookup[indexToLocalY(48)][indexToLocalX(48)] = Direction.EAST;
                if(h48 < closestDistance)
                {
                    closestDistance = h48;
                    closestIndex = 48;
                }
            }
            if(exists50 && dist49 + cost50 + 10 < dist50)
            {
                dist50 = dist49 + cost50 + 10;
                lookup[indexToLocalY(50)][indexToLocalX(50)] = Direction.WEST;
                if(h50 < closestDistance)
                {
                    closestDistance = h50;
                    closestIndex = 50;
                }
            }
            if(exists57 && dist49 + cost57 + 14 < dist57)
            {
                dist57 = dist49 + cost57 + 14;
                lookup[indexToLocalY(57)][indexToLocalX(57)] = Direction.NORTHEAST;
                if(h57 < closestDistance)
                {
                    closestDistance = h57;
                    closestIndex = 57;
                }
            }
            if(exists58 && dist49 + cost58 + 10 < dist58)
            {
                dist58 = dist49 + cost58 + 10;
                lookup[indexToLocalY(58)][indexToLocalX(58)] = Direction.NORTH;
                if(h58 < closestDistance)
                {
                    closestDistance = h58;
                    closestIndex = 58;
                }
            }
            if(exists59 && dist49 + cost59 + 14 < dist59)
            {
                dist59 = dist49 + cost59 + 14;
                lookup[indexToLocalY(59)][indexToLocalX(59)] = Direction.NORTHWEST;
                if(h59 < closestDistance)
                {
                    closestDistance = h59;
                    closestIndex = 59;
                }
            }
        }
        if(exists50)
        {
            if(exists40 && dist50 + cost40 + 14 < dist40)
            {
                dist40 = dist50 + cost40 + 14;
                lookup[indexToLocalY(40)][indexToLocalX(40)] = Direction.SOUTHEAST;
                if(h40 < closestDistance)
                {
                    closestDistance = h40;
                    closestIndex = 40;
                }
            }
            if(exists41 && dist50 + cost41 + 10 < dist41)
            {
                dist41 = dist50 + cost41 + 10;
                lookup[indexToLocalY(41)][indexToLocalX(41)] = Direction.SOUTH;
                if(h41 < closestDistance)
                {
                    closestDistance = h41;
                    closestIndex = 41;
                }
            }
            if(exists42 && dist50 + cost42 + 14 < dist42)
            {
                dist42 = dist50 + cost42 + 14;
                lookup[indexToLocalY(42)][indexToLocalX(42)] = Direction.SOUTHWEST;
                if(h42 < closestDistance)
                {
                    closestDistance = h42;
                    closestIndex = 42;
                }
            }
            if(exists49 && dist50 + cost49 + 10 < dist49)
            {
                dist49 = dist50 + cost49 + 10;
                lookup[indexToLocalY(49)][indexToLocalX(49)] = Direction.EAST;
                if(h49 < closestDistance)
                {
                    closestDistance = h49;
                    closestIndex = 49;
                }
            }
            if(exists51 && dist50 + cost51 + 10 < dist51)
            {
                dist51 = dist50 + cost51 + 10;
                lookup[indexToLocalY(51)][indexToLocalX(51)] = Direction.WEST;
                if(h51 < closestDistance)
                {
                    closestDistance = h51;
                    closestIndex = 51;
                }
            }
            if(exists58 && dist50 + cost58 + 14 < dist58)
            {
                dist58 = dist50 + cost58 + 14;
                lookup[indexToLocalY(58)][indexToLocalX(58)] = Direction.NORTHEAST;
                if(h58 < closestDistance)
                {
                    closestDistance = h58;
                    closestIndex = 58;
                }
            }
            if(exists59 && dist50 + cost59 + 10 < dist59)
            {
                dist59 = dist50 + cost59 + 10;
                lookup[indexToLocalY(59)][indexToLocalX(59)] = Direction.NORTH;
                if(h59 < closestDistance)
                {
                    closestDistance = h59;
                    closestIndex = 59;
                }
            }
            if(exists60 && dist50 + cost60 + 14 < dist60)
            {
                dist60 = dist50 + cost60 + 14;
                lookup[indexToLocalY(60)][indexToLocalX(60)] = Direction.NORTHWEST;
                if(h60 < closestDistance)
                {
                    closestDistance = h60;
                    closestIndex = 60;
                }
            }
        }
        if(exists20)
        {
            if(exists10 && dist20 + cost10 + 14 < dist10)
            {
                dist10 = dist20 + cost10 + 14;
                lookup[indexToLocalY(10)][indexToLocalX(10)] = Direction.SOUTHEAST;
                if(h10 < closestDistance)
                {
                    closestDistance = h10;
                    closestIndex = 10;
                }
            }
            if(exists11 && dist20 + cost11 + 10 < dist11)
            {
                dist11 = dist20 + cost11 + 10;
                lookup[indexToLocalY(11)][indexToLocalX(11)] = Direction.SOUTH;
                if(h11 < closestDistance)
                {
                    closestDistance = h11;
                    closestIndex = 11;
                }
            }
            if(exists12 && dist20 + cost12 + 14 < dist12)
            {
                dist12 = dist20 + cost12 + 14;
                lookup[indexToLocalY(12)][indexToLocalX(12)] = Direction.SOUTHWEST;
                if(h12 < closestDistance)
                {
                    closestDistance = h12;
                    closestIndex = 12;
                }
            }
            if(exists19 && dist20 + cost19 + 10 < dist19)
            {
                dist19 = dist20 + cost19 + 10;
                lookup[indexToLocalY(19)][indexToLocalX(19)] = Direction.EAST;
                if(h19 < closestDistance)
                {
                    closestDistance = h19;
                    closestIndex = 19;
                }
            }
            if(exists21 && dist20 + cost21 + 10 < dist21)
            {
                dist21 = dist20 + cost21 + 10;
                lookup[indexToLocalY(21)][indexToLocalX(21)] = Direction.WEST;
                if(h21 < closestDistance)
                {
                    closestDistance = h21;
                    closestIndex = 21;
                }
            }
            if(exists28 && dist20 + cost28 + 14 < dist28)
            {
                dist28 = dist20 + cost28 + 14;
                lookup[indexToLocalY(28)][indexToLocalX(28)] = Direction.NORTHEAST;
                if(h28 < closestDistance)
                {
                    closestDistance = h28;
                    closestIndex = 28;
                }
            }
            if(exists29 && dist20 + cost29 + 10 < dist29)
            {
                dist29 = dist20 + cost29 + 10;
                lookup[indexToLocalY(29)][indexToLocalX(29)] = Direction.NORTH;
                if(h29 < closestDistance)
                {
                    closestDistance = h29;
                    closestIndex = 29;
                }
            }
            if(exists30 && dist20 + cost30 + 14 < dist30)
            {
                dist30 = dist20 + cost30 + 14;
                lookup[indexToLocalY(30)][indexToLocalX(30)] = Direction.NORTHWEST;
                if(h30 < closestDistance)
                {
                    closestDistance = h30;
                    closestIndex = 30;
                }
            }
        }
        if(exists21)
        {
            if(exists11 && dist21 + cost11 + 14 < dist11)
            {
                dist11 = dist21 + cost11 + 14;
                lookup[indexToLocalY(11)][indexToLocalX(11)] = Direction.SOUTHEAST;
                if(h11 < closestDistance)
                {
                    closestDistance = h11;
                    closestIndex = 11;
                }
            }
            if(exists12 && dist21 + cost12 + 10 < dist12)
            {
                dist12 = dist21 + cost12 + 10;
                lookup[indexToLocalY(12)][indexToLocalX(12)] = Direction.SOUTH;
                if(h12 < closestDistance)
                {
                    closestDistance = h12;
                    closestIndex = 12;
                }
            }
            if(exists13 && dist21 + cost13 + 14 < dist13)
            {
                dist13 = dist21 + cost13 + 14;
                lookup[indexToLocalY(13)][indexToLocalX(13)] = Direction.SOUTHWEST;
                if(h13 < closestDistance)
                {
                    closestDistance = h13;
                    closestIndex = 13;
                }
            }
            if(exists20 && dist21 + cost20 + 10 < dist20)
            {
                dist20 = dist21 + cost20 + 10;
                lookup[indexToLocalY(20)][indexToLocalX(20)] = Direction.EAST;
                if(h20 < closestDistance)
                {
                    closestDistance = h20;
                    closestIndex = 20;
                }
            }
            if(exists22 && dist21 + cost22 + 10 < dist22)
            {
                dist22 = dist21 + cost22 + 10;
                lookup[indexToLocalY(22)][indexToLocalX(22)] = Direction.WEST;
                if(h22 < closestDistance)
                {
                    closestDistance = h22;
                    closestIndex = 22;
                }
            }
            if(exists29 && dist21 + cost29 + 14 < dist29)
            {
                dist29 = dist21 + cost29 + 14;
                lookup[indexToLocalY(29)][indexToLocalX(29)] = Direction.NORTHEAST;
                if(h29 < closestDistance)
                {
                    closestDistance = h29;
                    closestIndex = 29;
                }
            }
            if(exists30 && dist21 + cost30 + 10 < dist30)
            {
                dist30 = dist21 + cost30 + 10;
                lookup[indexToLocalY(30)][indexToLocalX(30)] = Direction.NORTH;
                if(h30 < closestDistance)
                {
                    closestDistance = h30;
                    closestIndex = 30;
                }
            }
            if(exists31 && dist21 + cost31 + 14 < dist31)
            {
                dist31 = dist21 + cost31 + 14;
                lookup[indexToLocalY(31)][indexToLocalX(31)] = Direction.NORTHWEST;
                if(h31 < closestDistance)
                {
                    closestDistance = h31;
                    closestIndex = 31;
                }
            }
        }
        if(exists22)
        {
            if(exists12 && dist22 + cost12 + 14 < dist12)
            {
                dist12 = dist22 + cost12 + 14;
                lookup[indexToLocalY(12)][indexToLocalX(12)] = Direction.SOUTHEAST;
                if(h12 < closestDistance)
                {
                    closestDistance = h12;
                    closestIndex = 12;
                }
            }
            if(exists13 && dist22 + cost13 + 10 < dist13)
            {
                dist13 = dist22 + cost13 + 10;
                lookup[indexToLocalY(13)][indexToLocalX(13)] = Direction.SOUTH;
                if(h13 < closestDistance)
                {
                    closestDistance = h13;
                    closestIndex = 13;
                }
            }
            if(exists14 && dist22 + cost14 + 14 < dist14)
            {
                dist14 = dist22 + cost14 + 14;
                lookup[indexToLocalY(14)][indexToLocalX(14)] = Direction.SOUTHWEST;
                if(h14 < closestDistance)
                {
                    closestDistance = h14;
                    closestIndex = 14;
                }
            }
            if(exists21 && dist22 + cost21 + 10 < dist21)
            {
                dist21 = dist22 + cost21 + 10;
                lookup[indexToLocalY(21)][indexToLocalX(21)] = Direction.EAST;
                if(h21 < closestDistance)
                {
                    closestDistance = h21;
                    closestIndex = 21;
                }
            }
            if(exists23 && dist22 + cost23 + 10 < dist23)
            {
                dist23 = dist22 + cost23 + 10;
                lookup[indexToLocalY(23)][indexToLocalX(23)] = Direction.WEST;
                if(h23 < closestDistance)
                {
                    closestDistance = h23;
                    closestIndex = 23;
                }
            }
            if(exists30 && dist22 + cost30 + 14 < dist30)
            {
                dist30 = dist22 + cost30 + 14;
                lookup[indexToLocalY(30)][indexToLocalX(30)] = Direction.NORTHEAST;
                if(h30 < closestDistance)
                {
                    closestDistance = h30;
                    closestIndex = 30;
                }
            }
            if(exists31 && dist22 + cost31 + 10 < dist31)
            {
                dist31 = dist22 + cost31 + 10;
                lookup[indexToLocalY(31)][indexToLocalX(31)] = Direction.NORTH;
                if(h31 < closestDistance)
                {
                    closestDistance = h31;
                    closestIndex = 31;
                }
            }
            if(exists32 && dist22 + cost32 + 14 < dist32)
            {
                dist32 = dist22 + cost32 + 14;
                lookup[indexToLocalY(32)][indexToLocalX(32)] = Direction.NORTHWEST;
                if(h32 < closestDistance)
                {
                    closestDistance = h32;
                    closestIndex = 32;
                }
            }
        }
        if(exists29)
        {
            if(exists19 && dist29 + cost19 + 14 < dist19)
            {
                dist19 = dist29 + cost19 + 14;
                lookup[indexToLocalY(19)][indexToLocalX(19)] = Direction.SOUTHEAST;
                if(h19 < closestDistance)
                {
                    closestDistance = h19;
                    closestIndex = 19;
                }
            }
            if(exists20 && dist29 + cost20 + 10 < dist20)
            {
                dist20 = dist29 + cost20 + 10;
                lookup[indexToLocalY(20)][indexToLocalX(20)] = Direction.SOUTH;
                if(h20 < closestDistance)
                {
                    closestDistance = h20;
                    closestIndex = 20;
                }
            }
            if(exists21 && dist29 + cost21 + 14 < dist21)
            {
                dist21 = dist29 + cost21 + 14;
                lookup[indexToLocalY(21)][indexToLocalX(21)] = Direction.SOUTHWEST;
                if(h21 < closestDistance)
                {
                    closestDistance = h21;
                    closestIndex = 21;
                }
            }
            if(exists28 && dist29 + cost28 + 10 < dist28)
            {
                dist28 = dist29 + cost28 + 10;
                lookup[indexToLocalY(28)][indexToLocalX(28)] = Direction.EAST;
                if(h28 < closestDistance)
                {
                    closestDistance = h28;
                    closestIndex = 28;
                }
            }
            if(exists30 && dist29 + cost30 + 10 < dist30)
            {
                dist30 = dist29 + cost30 + 10;
                lookup[indexToLocalY(30)][indexToLocalX(30)] = Direction.WEST;
                if(h30 < closestDistance)
                {
                    closestDistance = h30;
                    closestIndex = 30;
                }
            }
            if(exists37 && dist29 + cost37 + 14 < dist37)
            {
                dist37 = dist29 + cost37 + 14;
                lookup[indexToLocalY(37)][indexToLocalX(37)] = Direction.NORTHEAST;
                if(h37 < closestDistance)
                {
                    closestDistance = h37;
                    closestIndex = 37;
                }
            }
            if(exists38 && dist29 + cost38 + 10 < dist38)
            {
                dist38 = dist29 + cost38 + 10;
                lookup[indexToLocalY(38)][indexToLocalX(38)] = Direction.NORTH;
                if(h38 < closestDistance)
                {
                    closestDistance = h38;
                    closestIndex = 38;
                }
            }
            if(exists39 && dist29 + cost39 + 14 < dist39)
            {
                dist39 = dist29 + cost39 + 14;
                lookup[indexToLocalY(39)][indexToLocalX(39)] = Direction.NORTHWEST;
                if(h39 < closestDistance)
                {
                    closestDistance = h39;
                    closestIndex = 39;
                }
            }
        }
        if(exists38)
        {
            if(exists28 && dist38 + cost28 + 14 < dist28)
            {
                dist28 = dist38 + cost28 + 14;
                lookup[indexToLocalY(28)][indexToLocalX(28)] = Direction.SOUTHEAST;
                if(h28 < closestDistance)
                {
                    closestDistance = h28;
                    closestIndex = 28;
                }
            }
            if(exists29 && dist38 + cost29 + 10 < dist29)
            {
                dist29 = dist38 + cost29 + 10;
                lookup[indexToLocalY(29)][indexToLocalX(29)] = Direction.SOUTH;
                if(h29 < closestDistance)
                {
                    closestDistance = h29;
                    closestIndex = 29;
                }
            }
            if(exists30 && dist38 + cost30 + 14 < dist30)
            {
                dist30 = dist38 + cost30 + 14;
                lookup[indexToLocalY(30)][indexToLocalX(30)] = Direction.SOUTHWEST;
                if(h30 < closestDistance)
                {
                    closestDistance = h30;
                    closestIndex = 30;
                }
            }
            if(exists37 && dist38 + cost37 + 10 < dist37)
            {
                dist37 = dist38 + cost37 + 10;
                lookup[indexToLocalY(37)][indexToLocalX(37)] = Direction.EAST;
                if(h37 < closestDistance)
                {
                    closestDistance = h37;
                    closestIndex = 37;
                }
            }
            if(exists39 && dist38 + cost39 + 10 < dist39)
            {
                dist39 = dist38 + cost39 + 10;
                lookup[indexToLocalY(39)][indexToLocalX(39)] = Direction.WEST;
                if(h39 < closestDistance)
                {
                    closestDistance = h39;
                    closestIndex = 39;
                }
            }
            if(exists46 && dist38 + cost46 + 14 < dist46)
            {
                dist46 = dist38 + cost46 + 14;
                lookup[indexToLocalY(46)][indexToLocalX(46)] = Direction.NORTHEAST;
                if(h46 < closestDistance)
                {
                    closestDistance = h46;
                    closestIndex = 46;
                }
            }
            if(exists47 && dist38 + cost47 + 10 < dist47)
            {
                dist47 = dist38 + cost47 + 10;
                lookup[indexToLocalY(47)][indexToLocalX(47)] = Direction.NORTH;
                if(h47 < closestDistance)
                {
                    closestDistance = h47;
                    closestIndex = 47;
                }
            }
            if(exists48 && dist38 + cost48 + 14 < dist48)
            {
                dist48 = dist38 + cost48 + 14;
                lookup[indexToLocalY(48)][indexToLocalX(48)] = Direction.NORTHWEST;
                if(h48 < closestDistance)
                {
                    closestDistance = h48;
                    closestIndex = 48;
                }
            }
        }
        if(exists23)
        {
            if(exists13 && dist23 + cost13 + 14 < dist13)
            {
                dist13 = dist23 + cost13 + 14;
                lookup[indexToLocalY(13)][indexToLocalX(13)] = Direction.SOUTHEAST;
                if(h13 < closestDistance)
                {
                    closestDistance = h13;
                    closestIndex = 13;
                }
            }
            if(exists14 && dist23 + cost14 + 10 < dist14)
            {
                dist14 = dist23 + cost14 + 10;
                lookup[indexToLocalY(14)][indexToLocalX(14)] = Direction.SOUTH;
                if(h14 < closestDistance)
                {
                    closestDistance = h14;
                    closestIndex = 14;
                }
            }
            if(exists15 && dist23 + cost15 + 14 < dist15)
            {
                dist15 = dist23 + cost15 + 14;
                lookup[indexToLocalY(15)][indexToLocalX(15)] = Direction.SOUTHWEST;
                if(h15 < closestDistance)
                {
                    closestDistance = h15;
                    closestIndex = 15;
                }
            }
            if(exists22 && dist23 + cost22 + 10 < dist22)
            {
                dist22 = dist23 + cost22 + 10;
                lookup[indexToLocalY(22)][indexToLocalX(22)] = Direction.EAST;
                if(h22 < closestDistance)
                {
                    closestDistance = h22;
                    closestIndex = 22;
                }
            }
            if(exists24 && dist23 + cost24 + 10 < dist24)
            {
                dist24 = dist23 + cost24 + 10;
                lookup[indexToLocalY(24)][indexToLocalX(24)] = Direction.WEST;
                if(h24 < closestDistance)
                {
                    closestDistance = h24;
                    closestIndex = 24;
                }
            }
            if(exists31 && dist23 + cost31 + 14 < dist31)
            {
                dist31 = dist23 + cost31 + 14;
                lookup[indexToLocalY(31)][indexToLocalX(31)] = Direction.NORTHEAST;
                if(h31 < closestDistance)
                {
                    closestDistance = h31;
                    closestIndex = 31;
                }
            }
            if(exists32 && dist23 + cost32 + 10 < dist32)
            {
                dist32 = dist23 + cost32 + 10;
                lookup[indexToLocalY(32)][indexToLocalX(32)] = Direction.NORTH;
                if(h32 < closestDistance)
                {
                    closestDistance = h32;
                    closestIndex = 32;
                }
            }
            if(exists33 && dist23 + cost33 + 14 < dist33)
            {
                dist33 = dist23 + cost33 + 14;
                lookup[indexToLocalY(33)][indexToLocalX(33)] = Direction.NORTHWEST;
                if(h33 < closestDistance)
                {
                    closestDistance = h33;
                    closestIndex = 33;
                }
            }
        }
        if(exists24)
        {
            if(exists14 && dist24 + cost14 + 14 < dist14)
            {
                dist14 = dist24 + cost14 + 14;
                lookup[indexToLocalY(14)][indexToLocalX(14)] = Direction.SOUTHEAST;
                if(h14 < closestDistance)
                {
                    closestDistance = h14;
                    closestIndex = 14;
                }
            }
            if(exists15 && dist24 + cost15 + 10 < dist15)
            {
                dist15 = dist24 + cost15 + 10;
                lookup[indexToLocalY(15)][indexToLocalX(15)] = Direction.SOUTH;
                if(h15 < closestDistance)
                {
                    closestDistance = h15;
                    closestIndex = 15;
                }
            }
            if(exists16 && dist24 + cost16 + 14 < dist16)
            {
                dist16 = dist24 + cost16 + 14;
                lookup[indexToLocalY(16)][indexToLocalX(16)] = Direction.SOUTHWEST;
                if(h16 < closestDistance)
                {
                    closestDistance = h16;
                    closestIndex = 16;
                }
            }
            if(exists23 && dist24 + cost23 + 10 < dist23)
            {
                dist23 = dist24 + cost23 + 10;
                lookup[indexToLocalY(23)][indexToLocalX(23)] = Direction.EAST;
                if(h23 < closestDistance)
                {
                    closestDistance = h23;
                    closestIndex = 23;
                }
            }
            if(exists25 && dist24 + cost25 + 10 < dist25)
            {
                dist25 = dist24 + cost25 + 10;
                lookup[indexToLocalY(25)][indexToLocalX(25)] = Direction.WEST;
                if(h25 < closestDistance)
                {
                    closestDistance = h25;
                    closestIndex = 25;
                }
            }
            if(exists32 && dist24 + cost32 + 14 < dist32)
            {
                dist32 = dist24 + cost32 + 14;
                lookup[indexToLocalY(32)][indexToLocalX(32)] = Direction.NORTHEAST;
                if(h32 < closestDistance)
                {
                    closestDistance = h32;
                    closestIndex = 32;
                }
            }
            if(exists33 && dist24 + cost33 + 10 < dist33)
            {
                dist33 = dist24 + cost33 + 10;
                lookup[indexToLocalY(33)][indexToLocalX(33)] = Direction.NORTH;
                if(h33 < closestDistance)
                {
                    closestDistance = h33;
                    closestIndex = 33;
                }
            }
            if(exists34 && dist24 + cost34 + 14 < dist34)
            {
                dist34 = dist24 + cost34 + 14;
                lookup[indexToLocalY(34)][indexToLocalX(34)] = Direction.NORTHWEST;
                if(h34 < closestDistance)
                {
                    closestDistance = h34;
                    closestIndex = 34;
                }
            }
        }
        if(exists33)
        {
            if(exists23 && dist33 + cost23 + 14 < dist23)
            {
                dist23 = dist33 + cost23 + 14;
                lookup[indexToLocalY(23)][indexToLocalX(23)] = Direction.SOUTHEAST;
                if(h23 < closestDistance)
                {
                    closestDistance = h23;
                    closestIndex = 23;
                }
            }
            if(exists24 && dist33 + cost24 + 10 < dist24)
            {
                dist24 = dist33 + cost24 + 10;
                lookup[indexToLocalY(24)][indexToLocalX(24)] = Direction.SOUTH;
                if(h24 < closestDistance)
                {
                    closestDistance = h24;
                    closestIndex = 24;
                }
            }
            if(exists25 && dist33 + cost25 + 14 < dist25)
            {
                dist25 = dist33 + cost25 + 14;
                lookup[indexToLocalY(25)][indexToLocalX(25)] = Direction.SOUTHWEST;
                if(h25 < closestDistance)
                {
                    closestDistance = h25;
                    closestIndex = 25;
                }
            }
            if(exists32 && dist33 + cost32 + 10 < dist32)
            {
                dist32 = dist33 + cost32 + 10;
                lookup[indexToLocalY(32)][indexToLocalX(32)] = Direction.EAST;
                if(h32 < closestDistance)
                {
                    closestDistance = h32;
                    closestIndex = 32;
                }
            }
            if(exists34 && dist33 + cost34 + 10 < dist34)
            {
                dist34 = dist33 + cost34 + 10;
                lookup[indexToLocalY(34)][indexToLocalX(34)] = Direction.WEST;
                if(h34 < closestDistance)
                {
                    closestDistance = h34;
                    closestIndex = 34;
                }
            }
            if(exists41 && dist33 + cost41 + 14 < dist41)
            {
                dist41 = dist33 + cost41 + 14;
                lookup[indexToLocalY(41)][indexToLocalX(41)] = Direction.NORTHEAST;
                if(h41 < closestDistance)
                {
                    closestDistance = h41;
                    closestIndex = 41;
                }
            }
            if(exists42 && dist33 + cost42 + 10 < dist42)
            {
                dist42 = dist33 + cost42 + 10;
                lookup[indexToLocalY(42)][indexToLocalX(42)] = Direction.NORTH;
                if(h42 < closestDistance)
                {
                    closestDistance = h42;
                    closestIndex = 42;
                }
            }
            if(exists43 && dist33 + cost43 + 14 < dist43)
            {
                dist43 = dist33 + cost43 + 14;
                lookup[indexToLocalY(43)][indexToLocalX(43)] = Direction.NORTHWEST;
                if(h43 < closestDistance)
                {
                    closestDistance = h43;
                    closestIndex = 43;
                }
            }
        }
        if(exists42)
        {
            if(exists32 && dist42 + cost32 + 14 < dist32)
            {
                dist32 = dist42 + cost32 + 14;
                lookup[indexToLocalY(32)][indexToLocalX(32)] = Direction.SOUTHEAST;
                if(h32 < closestDistance)
                {
                    closestDistance = h32;
                    closestIndex = 32;
                }
            }
            if(exists33 && dist42 + cost33 + 10 < dist33)
            {
                dist33 = dist42 + cost33 + 10;
                lookup[indexToLocalY(33)][indexToLocalX(33)] = Direction.SOUTH;
                if(h33 < closestDistance)
                {
                    closestDistance = h33;
                    closestIndex = 33;
                }
            }
            if(exists34 && dist42 + cost34 + 14 < dist34)
            {
                dist34 = dist42 + cost34 + 14;
                lookup[indexToLocalY(34)][indexToLocalX(34)] = Direction.SOUTHWEST;
                if(h34 < closestDistance)
                {
                    closestDistance = h34;
                    closestIndex = 34;
                }
            }
            if(exists41 && dist42 + cost41 + 10 < dist41)
            {
                dist41 = dist42 + cost41 + 10;
                lookup[indexToLocalY(41)][indexToLocalX(41)] = Direction.EAST;
                if(h41 < closestDistance)
                {
                    closestDistance = h41;
                    closestIndex = 41;
                }
            }
            if(exists43 && dist42 + cost43 + 10 < dist43)
            {
                dist43 = dist42 + cost43 + 10;
                lookup[indexToLocalY(43)][indexToLocalX(43)] = Direction.WEST;
                if(h43 < closestDistance)
                {
                    closestDistance = h43;
                    closestIndex = 43;
                }
            }
            if(exists50 && dist42 + cost50 + 14 < dist50)
            {
                dist50 = dist42 + cost50 + 14;
                lookup[indexToLocalY(50)][indexToLocalX(50)] = Direction.NORTHEAST;
                if(h50 < closestDistance)
                {
                    closestDistance = h50;
                    closestIndex = 50;
                }
            }
            if(exists51 && dist42 + cost51 + 10 < dist51)
            {
                dist51 = dist42 + cost51 + 10;
                lookup[indexToLocalY(51)][indexToLocalX(51)] = Direction.NORTH;
                if(h51 < closestDistance)
                {
                    closestDistance = h51;
                    closestIndex = 51;
                }
            }
            if(exists52 && dist42 + cost52 + 14 < dist52)
            {
                dist52 = dist42 + cost52 + 14;
                lookup[indexToLocalY(52)][indexToLocalX(52)] = Direction.NORTHWEST;
                if(h52 < closestDistance)
                {
                    closestDistance = h52;
                    closestIndex = 52;
                }
            }
        }
        if(exists47)
        {
            if(exists37 && dist47 + cost37 + 14 < dist37)
            {
                dist37 = dist47 + cost37 + 14;
                lookup[indexToLocalY(37)][indexToLocalX(37)] = Direction.SOUTHEAST;
                if(h37 < closestDistance)
                {
                    closestDistance = h37;
                    closestIndex = 37;
                }
            }
            if(exists38 && dist47 + cost38 + 10 < dist38)
            {
                dist38 = dist47 + cost38 + 10;
                lookup[indexToLocalY(38)][indexToLocalX(38)] = Direction.SOUTH;
                if(h38 < closestDistance)
                {
                    closestDistance = h38;
                    closestIndex = 38;
                }
            }
            if(exists39 && dist47 + cost39 + 14 < dist39)
            {
                dist39 = dist47 + cost39 + 14;
                lookup[indexToLocalY(39)][indexToLocalX(39)] = Direction.SOUTHWEST;
                if(h39 < closestDistance)
                {
                    closestDistance = h39;
                    closestIndex = 39;
                }
            }
            if(exists46 && dist47 + cost46 + 10 < dist46)
            {
                dist46 = dist47 + cost46 + 10;
                lookup[indexToLocalY(46)][indexToLocalX(46)] = Direction.EAST;
                if(h46 < closestDistance)
                {
                    closestDistance = h46;
                    closestIndex = 46;
                }
            }
            if(exists48 && dist47 + cost48 + 10 < dist48)
            {
                dist48 = dist47 + cost48 + 10;
                lookup[indexToLocalY(48)][indexToLocalX(48)] = Direction.WEST;
                if(h48 < closestDistance)
                {
                    closestDistance = h48;
                    closestIndex = 48;
                }
            }
            if(exists55 && dist47 + cost55 + 14 < dist55)
            {
                dist55 = dist47 + cost55 + 14;
                lookup[indexToLocalY(55)][indexToLocalX(55)] = Direction.NORTHEAST;
                if(h55 < closestDistance)
                {
                    closestDistance = h55;
                    closestIndex = 55;
                }
            }
            if(exists56 && dist47 + cost56 + 10 < dist56)
            {
                dist56 = dist47 + cost56 + 10;
                lookup[indexToLocalY(56)][indexToLocalX(56)] = Direction.NORTH;
                if(h56 < closestDistance)
                {
                    closestDistance = h56;
                    closestIndex = 56;
                }
            }
            if(exists57 && dist47 + cost57 + 14 < dist57)
            {
                dist57 = dist47 + cost57 + 14;
                lookup[indexToLocalY(57)][indexToLocalX(57)] = Direction.NORTHWEST;
                if(h57 < closestDistance)
                {
                    closestDistance = h57;
                    closestIndex = 57;
                }
            }
        }
        if(exists51)
        {
            if(exists41 && dist51 + cost41 + 14 < dist41)
            {
                dist41 = dist51 + cost41 + 14;
                lookup[indexToLocalY(41)][indexToLocalX(41)] = Direction.SOUTHEAST;
                if(h41 < closestDistance)
                {
                    closestDistance = h41;
                    closestIndex = 41;
                }
            }
            if(exists42 && dist51 + cost42 + 10 < dist42)
            {
                dist42 = dist51 + cost42 + 10;
                lookup[indexToLocalY(42)][indexToLocalX(42)] = Direction.SOUTH;
                if(h42 < closestDistance)
                {
                    closestDistance = h42;
                    closestIndex = 42;
                }
            }
            if(exists43 && dist51 + cost43 + 14 < dist43)
            {
                dist43 = dist51 + cost43 + 14;
                lookup[indexToLocalY(43)][indexToLocalX(43)] = Direction.SOUTHWEST;
                if(h43 < closestDistance)
                {
                    closestDistance = h43;
                    closestIndex = 43;
                }
            }
            if(exists50 && dist51 + cost50 + 10 < dist50)
            {
                dist50 = dist51 + cost50 + 10;
                lookup[indexToLocalY(50)][indexToLocalX(50)] = Direction.EAST;
                if(h50 < closestDistance)
                {
                    closestDistance = h50;
                    closestIndex = 50;
                }
            }
            if(exists52 && dist51 + cost52 + 10 < dist52)
            {
                dist52 = dist51 + cost52 + 10;
                lookup[indexToLocalY(52)][indexToLocalX(52)] = Direction.WEST;
                if(h52 < closestDistance)
                {
                    closestDistance = h52;
                    closestIndex = 52;
                }
            }
            if(exists59 && dist51 + cost59 + 14 < dist59)
            {
                dist59 = dist51 + cost59 + 14;
                lookup[indexToLocalY(59)][indexToLocalX(59)] = Direction.NORTHEAST;
                if(h59 < closestDistance)
                {
                    closestDistance = h59;
                    closestIndex = 59;
                }
            }
            if(exists60 && dist51 + cost60 + 10 < dist60)
            {
                dist60 = dist51 + cost60 + 10;
                lookup[indexToLocalY(60)][indexToLocalX(60)] = Direction.NORTH;
                if(h60 < closestDistance)
                {
                    closestDistance = h60;
                    closestIndex = 60;
                }
            }
            if(exists61 && dist51 + cost61 + 14 < dist61)
            {
                dist61 = dist51 + cost61 + 14;
                lookup[indexToLocalY(61)][indexToLocalX(61)] = Direction.NORTHWEST;
                if(h61 < closestDistance)
                {
                    closestDistance = h61;
                    closestIndex = 61;
                }
            }
        }
        if(exists56)
        {
            if(exists46 && dist56 + cost46 + 14 < dist46)
            {
                dist46 = dist56 + cost46 + 14;
                lookup[indexToLocalY(46)][indexToLocalX(46)] = Direction.SOUTHEAST;
                if(h46 < closestDistance)
                {
                    closestDistance = h46;
                    closestIndex = 46;
                }
            }
            if(exists47 && dist56 + cost47 + 10 < dist47)
            {
                dist47 = dist56 + cost47 + 10;
                lookup[indexToLocalY(47)][indexToLocalX(47)] = Direction.SOUTH;
                if(h47 < closestDistance)
                {
                    closestDistance = h47;
                    closestIndex = 47;
                }
            }
            if(exists48 && dist56 + cost48 + 14 < dist48)
            {
                dist48 = dist56 + cost48 + 14;
                lookup[indexToLocalY(48)][indexToLocalX(48)] = Direction.SOUTHWEST;
                if(h48 < closestDistance)
                {
                    closestDistance = h48;
                    closestIndex = 48;
                }
            }
            if(exists55 && dist56 + cost55 + 10 < dist55)
            {
                dist55 = dist56 + cost55 + 10;
                lookup[indexToLocalY(55)][indexToLocalX(55)] = Direction.EAST;
                if(h55 < closestDistance)
                {
                    closestDistance = h55;
                    closestIndex = 55;
                }
            }
            if(exists57 && dist56 + cost57 + 10 < dist57)
            {
                dist57 = dist56 + cost57 + 10;
                lookup[indexToLocalY(57)][indexToLocalX(57)] = Direction.WEST;
                if(h57 < closestDistance)
                {
                    closestDistance = h57;
                    closestIndex = 57;
                }
            }
            if(exists64 && dist56 + cost64 + 14 < dist64)
            {
                dist64 = dist56 + cost64 + 14;
                lookup[indexToLocalY(64)][indexToLocalX(64)] = Direction.NORTHEAST;
                if(h64 < closestDistance)
                {
                    closestDistance = h64;
                    closestIndex = 64;
                }
            }
            if(exists65 && dist56 + cost65 + 10 < dist65)
            {
                dist65 = dist56 + cost65 + 10;
                lookup[indexToLocalY(65)][indexToLocalX(65)] = Direction.NORTH;
                if(h65 < closestDistance)
                {
                    closestDistance = h65;
                    closestIndex = 65;
                }
            }
            if(exists66 && dist56 + cost66 + 14 < dist66)
            {
                dist66 = dist56 + cost66 + 14;
                lookup[indexToLocalY(66)][indexToLocalX(66)] = Direction.NORTHWEST;
                if(h66 < closestDistance)
                {
                    closestDistance = h66;
                    closestIndex = 66;
                }
            }
        }
        if(exists57)
        {
            if(exists47 && dist57 + cost47 + 14 < dist47)
            {
                dist47 = dist57 + cost47 + 14;
                lookup[indexToLocalY(47)][indexToLocalX(47)] = Direction.SOUTHEAST;
                if(h47 < closestDistance)
                {
                    closestDistance = h47;
                    closestIndex = 47;
                }
            }
            if(exists48 && dist57 + cost48 + 10 < dist48)
            {
                dist48 = dist57 + cost48 + 10;
                lookup[indexToLocalY(48)][indexToLocalX(48)] = Direction.SOUTH;
                if(h48 < closestDistance)
                {
                    closestDistance = h48;
                    closestIndex = 48;
                }
            }
            if(exists49 && dist57 + cost49 + 14 < dist49)
            {
                dist49 = dist57 + cost49 + 14;
                lookup[indexToLocalY(49)][indexToLocalX(49)] = Direction.SOUTHWEST;
                if(h49 < closestDistance)
                {
                    closestDistance = h49;
                    closestIndex = 49;
                }
            }
            if(exists56 && dist57 + cost56 + 10 < dist56)
            {
                dist56 = dist57 + cost56 + 10;
                lookup[indexToLocalY(56)][indexToLocalX(56)] = Direction.EAST;
                if(h56 < closestDistance)
                {
                    closestDistance = h56;
                    closestIndex = 56;
                }
            }
            if(exists58 && dist57 + cost58 + 10 < dist58)
            {
                dist58 = dist57 + cost58 + 10;
                lookup[indexToLocalY(58)][indexToLocalX(58)] = Direction.WEST;
                if(h58 < closestDistance)
                {
                    closestDistance = h58;
                    closestIndex = 58;
                }
            }
            if(exists65 && dist57 + cost65 + 14 < dist65)
            {
                dist65 = dist57 + cost65 + 14;
                lookup[indexToLocalY(65)][indexToLocalX(65)] = Direction.NORTHEAST;
                if(h65 < closestDistance)
                {
                    closestDistance = h65;
                    closestIndex = 65;
                }
            }
            if(exists66 && dist57 + cost66 + 10 < dist66)
            {
                dist66 = dist57 + cost66 + 10;
                lookup[indexToLocalY(66)][indexToLocalX(66)] = Direction.NORTH;
                if(h66 < closestDistance)
                {
                    closestDistance = h66;
                    closestIndex = 66;
                }
            }
            if(exists67 && dist57 + cost67 + 14 < dist67)
            {
                dist67 = dist57 + cost67 + 14;
                lookup[indexToLocalY(67)][indexToLocalX(67)] = Direction.NORTHWEST;
                if(h67 < closestDistance)
                {
                    closestDistance = h67;
                    closestIndex = 67;
                }
            }
        }
        if(exists58)
        {
            if(exists48 && dist58 + cost48 + 14 < dist48)
            {
                dist48 = dist58 + cost48 + 14;
                lookup[indexToLocalY(48)][indexToLocalX(48)] = Direction.SOUTHEAST;
                if(h48 < closestDistance)
                {
                    closestDistance = h48;
                    closestIndex = 48;
                }
            }
            if(exists49 && dist58 + cost49 + 10 < dist49)
            {
                dist49 = dist58 + cost49 + 10;
                lookup[indexToLocalY(49)][indexToLocalX(49)] = Direction.SOUTH;
                if(h49 < closestDistance)
                {
                    closestDistance = h49;
                    closestIndex = 49;
                }
            }
            if(exists50 && dist58 + cost50 + 14 < dist50)
            {
                dist50 = dist58 + cost50 + 14;
                lookup[indexToLocalY(50)][indexToLocalX(50)] = Direction.SOUTHWEST;
                if(h50 < closestDistance)
                {
                    closestDistance = h50;
                    closestIndex = 50;
                }
            }
            if(exists57 && dist58 + cost57 + 10 < dist57)
            {
                dist57 = dist58 + cost57 + 10;
                lookup[indexToLocalY(57)][indexToLocalX(57)] = Direction.EAST;
                if(h57 < closestDistance)
                {
                    closestDistance = h57;
                    closestIndex = 57;
                }
            }
            if(exists59 && dist58 + cost59 + 10 < dist59)
            {
                dist59 = dist58 + cost59 + 10;
                lookup[indexToLocalY(59)][indexToLocalX(59)] = Direction.WEST;
                if(h59 < closestDistance)
                {
                    closestDistance = h59;
                    closestIndex = 59;
                }
            }
            if(exists66 && dist58 + cost66 + 14 < dist66)
            {
                dist66 = dist58 + cost66 + 14;
                lookup[indexToLocalY(66)][indexToLocalX(66)] = Direction.NORTHEAST;
                if(h66 < closestDistance)
                {
                    closestDistance = h66;
                    closestIndex = 66;
                }
            }
            if(exists67 && dist58 + cost67 + 10 < dist67)
            {
                dist67 = dist58 + cost67 + 10;
                lookup[indexToLocalY(67)][indexToLocalX(67)] = Direction.NORTH;
                if(h67 < closestDistance)
                {
                    closestDistance = h67;
                    closestIndex = 67;
                }
            }
            if(exists68 && dist58 + cost68 + 14 < dist68)
            {
                dist68 = dist58 + cost68 + 14;
                lookup[indexToLocalY(68)][indexToLocalX(68)] = Direction.NORTHWEST;
                if(h68 < closestDistance)
                {
                    closestDistance = h68;
                    closestIndex = 68;
                }
            }
        }
        if(exists59)
        {
            if(exists49 && dist59 + cost49 + 14 < dist49)
            {
                dist49 = dist59 + cost49 + 14;
                lookup[indexToLocalY(49)][indexToLocalX(49)] = Direction.SOUTHEAST;
                if(h49 < closestDistance)
                {
                    closestDistance = h49;
                    closestIndex = 49;
                }
            }
            if(exists50 && dist59 + cost50 + 10 < dist50)
            {
                dist50 = dist59 + cost50 + 10;
                lookup[indexToLocalY(50)][indexToLocalX(50)] = Direction.SOUTH;
                if(h50 < closestDistance)
                {
                    closestDistance = h50;
                    closestIndex = 50;
                }
            }
            if(exists51 && dist59 + cost51 + 14 < dist51)
            {
                dist51 = dist59 + cost51 + 14;
                lookup[indexToLocalY(51)][indexToLocalX(51)] = Direction.SOUTHWEST;
                if(h51 < closestDistance)
                {
                    closestDistance = h51;
                    closestIndex = 51;
                }
            }
            if(exists58 && dist59 + cost58 + 10 < dist58)
            {
                dist58 = dist59 + cost58 + 10;
                lookup[indexToLocalY(58)][indexToLocalX(58)] = Direction.EAST;
                if(h58 < closestDistance)
                {
                    closestDistance = h58;
                    closestIndex = 58;
                }
            }
            if(exists60 && dist59 + cost60 + 10 < dist60)
            {
                dist60 = dist59 + cost60 + 10;
                lookup[indexToLocalY(60)][indexToLocalX(60)] = Direction.WEST;
                if(h60 < closestDistance)
                {
                    closestDistance = h60;
                    closestIndex = 60;
                }
            }
            if(exists67 && dist59 + cost67 + 14 < dist67)
            {
                dist67 = dist59 + cost67 + 14;
                lookup[indexToLocalY(67)][indexToLocalX(67)] = Direction.NORTHEAST;
                if(h67 < closestDistance)
                {
                    closestDistance = h67;
                    closestIndex = 67;
                }
            }
            if(exists68 && dist59 + cost68 + 10 < dist68)
            {
                dist68 = dist59 + cost68 + 10;
                lookup[indexToLocalY(68)][indexToLocalX(68)] = Direction.NORTH;
                if(h68 < closestDistance)
                {
                    closestDistance = h68;
                    closestIndex = 68;
                }
            }
            if(exists69 && dist59 + cost69 + 14 < dist69)
            {
                dist69 = dist59 + cost69 + 14;
                lookup[indexToLocalY(69)][indexToLocalX(69)] = Direction.NORTHWEST;
                if(h69 < closestDistance)
                {
                    closestDistance = h69;
                    closestIndex = 69;
                }
            }
        }
        if(exists60)
        {
            if(exists50 && dist60 + cost50 + 14 < dist50)
            {
                dist50 = dist60 + cost50 + 14;
                lookup[indexToLocalY(50)][indexToLocalX(50)] = Direction.SOUTHEAST;
                if(h50 < closestDistance)
                {
                    closestDistance = h50;
                    closestIndex = 50;
                }
            }
            if(exists51 && dist60 + cost51 + 10 < dist51)
            {
                dist51 = dist60 + cost51 + 10;
                lookup[indexToLocalY(51)][indexToLocalX(51)] = Direction.SOUTH;
                if(h51 < closestDistance)
                {
                    closestDistance = h51;
                    closestIndex = 51;
                }
            }
            if(exists52 && dist60 + cost52 + 14 < dist52)
            {
                dist52 = dist60 + cost52 + 14;
                lookup[indexToLocalY(52)][indexToLocalX(52)] = Direction.SOUTHWEST;
                if(h52 < closestDistance)
                {
                    closestDistance = h52;
                    closestIndex = 52;
                }
            }
            if(exists59 && dist60 + cost59 + 10 < dist59)
            {
                dist59 = dist60 + cost59 + 10;
                lookup[indexToLocalY(59)][indexToLocalX(59)] = Direction.EAST;
                if(h59 < closestDistance)
                {
                    closestDistance = h59;
                    closestIndex = 59;
                }
            }
            if(exists61 && dist60 + cost61 + 10 < dist61)
            {
                dist61 = dist60 + cost61 + 10;
                lookup[indexToLocalY(61)][indexToLocalX(61)] = Direction.WEST;
                if(h61 < closestDistance)
                {
                    closestDistance = h61;
                    closestIndex = 61;
                }
            }
            if(exists68 && dist60 + cost68 + 14 < dist68)
            {
                dist68 = dist60 + cost68 + 14;
                lookup[indexToLocalY(68)][indexToLocalX(68)] = Direction.NORTHEAST;
                if(h68 < closestDistance)
                {
                    closestDistance = h68;
                    closestIndex = 68;
                }
            }
            if(exists69 && dist60 + cost69 + 10 < dist69)
            {
                dist69 = dist60 + cost69 + 10;
                lookup[indexToLocalY(69)][indexToLocalX(69)] = Direction.NORTH;
                if(h69 < closestDistance)
                {
                    closestDistance = h69;
                    closestIndex = 69;
                }
            }
            if(exists70 && dist60 + cost70 + 14 < dist70)
            {
                dist70 = dist60 + cost70 + 14;
                lookup[indexToLocalY(70)][indexToLocalX(70)] = Direction.NORTHWEST;
                if(h70 < closestDistance)
                {
                    closestDistance = h70;
                    closestIndex = 70;
                }
            }
        }
        if(exists10)
        {
            if(exists2 && dist10 + cost2 + 14 < dist2)
            {
                dist2 = dist10 + cost2 + 14;
                lookup[indexToLocalY(2)][indexToLocalX(2)] = Direction.SOUTHWEST;
                if(h2 < closestDistance)
                {
                    closestDistance = h2;
                    closestIndex = 2;
                }
            }
            if(exists11 && dist10 + cost11 + 10 < dist11)
            {
                dist11 = dist10 + cost11 + 10;
                lookup[indexToLocalY(11)][indexToLocalX(11)] = Direction.WEST;
                if(h11 < closestDistance)
                {
                    closestDistance = h11;
                    closestIndex = 11;
                }
            }
            if(exists18 && dist10 + cost18 + 14 < dist18)
            {
                dist18 = dist10 + cost18 + 14;
                lookup[indexToLocalY(18)][indexToLocalX(18)] = Direction.NORTHEAST;
                if(h18 < closestDistance)
                {
                    closestDistance = h18;
                    closestIndex = 18;
                }
            }
            if(exists19 && dist10 + cost19 + 10 < dist19)
            {
                dist19 = dist10 + cost19 + 10;
                lookup[indexToLocalY(19)][indexToLocalX(19)] = Direction.NORTH;
                if(h19 < closestDistance)
                {
                    closestDistance = h19;
                    closestIndex = 19;
                }
            }
            if(exists20 && dist10 + cost20 + 14 < dist20)
            {
                dist20 = dist10 + cost20 + 14;
                lookup[indexToLocalY(20)][indexToLocalX(20)] = Direction.NORTHWEST;
                if(h20 < closestDistance)
                {
                    closestDistance = h20;
                    closestIndex = 20;
                }
            }
        }
        if(exists11)
        {
            if(exists2 && dist11 + cost2 + 10 < dist2)
            {
                dist2 = dist11 + cost2 + 10;
                lookup[indexToLocalY(2)][indexToLocalX(2)] = Direction.SOUTH;
                if(h2 < closestDistance)
                {
                    closestDistance = h2;
                    closestIndex = 2;
                }
            }
            if(exists3 && dist11 + cost3 + 14 < dist3)
            {
                dist3 = dist11 + cost3 + 14;
                lookup[indexToLocalY(3)][indexToLocalX(3)] = Direction.SOUTHWEST;
                if(h3 < closestDistance)
                {
                    closestDistance = h3;
                    closestIndex = 3;
                }
            }
            if(exists10 && dist11 + cost10 + 10 < dist10)
            {
                dist10 = dist11 + cost10 + 10;
                lookup[indexToLocalY(10)][indexToLocalX(10)] = Direction.EAST;
                if(h10 < closestDistance)
                {
                    closestDistance = h10;
                    closestIndex = 10;
                }
            }
            if(exists12 && dist11 + cost12 + 10 < dist12)
            {
                dist12 = dist11 + cost12 + 10;
                lookup[indexToLocalY(12)][indexToLocalX(12)] = Direction.WEST;
                if(h12 < closestDistance)
                {
                    closestDistance = h12;
                    closestIndex = 12;
                }
            }
            if(exists19 && dist11 + cost19 + 14 < dist19)
            {
                dist19 = dist11 + cost19 + 14;
                lookup[indexToLocalY(19)][indexToLocalX(19)] = Direction.NORTHEAST;
                if(h19 < closestDistance)
                {
                    closestDistance = h19;
                    closestIndex = 19;
                }
            }
            if(exists20 && dist11 + cost20 + 10 < dist20)
            {
                dist20 = dist11 + cost20 + 10;
                lookup[indexToLocalY(20)][indexToLocalX(20)] = Direction.NORTH;
                if(h20 < closestDistance)
                {
                    closestDistance = h20;
                    closestIndex = 20;
                }
            }
            if(exists21 && dist11 + cost21 + 14 < dist21)
            {
                dist21 = dist11 + cost21 + 14;
                lookup[indexToLocalY(21)][indexToLocalX(21)] = Direction.NORTHWEST;
                if(h21 < closestDistance)
                {
                    closestDistance = h21;
                    closestIndex = 21;
                }
            }
        }
        if(exists12)
        {
            if(exists2 && dist12 + cost2 + 14 < dist2)
            {
                dist2 = dist12 + cost2 + 14;
                lookup[indexToLocalY(2)][indexToLocalX(2)] = Direction.SOUTHEAST;
                if(h2 < closestDistance)
                {
                    closestDistance = h2;
                    closestIndex = 2;
                }
            }
            if(exists3 && dist12 + cost3 + 10 < dist3)
            {
                dist3 = dist12 + cost3 + 10;
                lookup[indexToLocalY(3)][indexToLocalX(3)] = Direction.SOUTH;
                if(h3 < closestDistance)
                {
                    closestDistance = h3;
                    closestIndex = 3;
                }
            }
            if(exists4 && dist12 + cost4 + 14 < dist4)
            {
                dist4 = dist12 + cost4 + 14;
                lookup[indexToLocalY(4)][indexToLocalX(4)] = Direction.SOUTHWEST;
                if(h4 < closestDistance)
                {
                    closestDistance = h4;
                    closestIndex = 4;
                }
            }
            if(exists11 && dist12 + cost11 + 10 < dist11)
            {
                dist11 = dist12 + cost11 + 10;
                lookup[indexToLocalY(11)][indexToLocalX(11)] = Direction.EAST;
                if(h11 < closestDistance)
                {
                    closestDistance = h11;
                    closestIndex = 11;
                }
            }
            if(exists13 && dist12 + cost13 + 10 < dist13)
            {
                dist13 = dist12 + cost13 + 10;
                lookup[indexToLocalY(13)][indexToLocalX(13)] = Direction.WEST;
                if(h13 < closestDistance)
                {
                    closestDistance = h13;
                    closestIndex = 13;
                }
            }
            if(exists20 && dist12 + cost20 + 14 < dist20)
            {
                dist20 = dist12 + cost20 + 14;
                lookup[indexToLocalY(20)][indexToLocalX(20)] = Direction.NORTHEAST;
                if(h20 < closestDistance)
                {
                    closestDistance = h20;
                    closestIndex = 20;
                }
            }
            if(exists21 && dist12 + cost21 + 10 < dist21)
            {
                dist21 = dist12 + cost21 + 10;
                lookup[indexToLocalY(21)][indexToLocalX(21)] = Direction.NORTH;
                if(h21 < closestDistance)
                {
                    closestDistance = h21;
                    closestIndex = 21;
                }
            }
            if(exists22 && dist12 + cost22 + 14 < dist22)
            {
                dist22 = dist12 + cost22 + 14;
                lookup[indexToLocalY(22)][indexToLocalX(22)] = Direction.NORTHWEST;
                if(h22 < closestDistance)
                {
                    closestDistance = h22;
                    closestIndex = 22;
                }
            }
        }
        if(exists19)
        {
            if(exists10 && dist19 + cost10 + 10 < dist10)
            {
                dist10 = dist19 + cost10 + 10;
                lookup[indexToLocalY(10)][indexToLocalX(10)] = Direction.SOUTH;
                if(h10 < closestDistance)
                {
                    closestDistance = h10;
                    closestIndex = 10;
                }
            }
            if(exists11 && dist19 + cost11 + 14 < dist11)
            {
                dist11 = dist19 + cost11 + 14;
                lookup[indexToLocalY(11)][indexToLocalX(11)] = Direction.SOUTHWEST;
                if(h11 < closestDistance)
                {
                    closestDistance = h11;
                    closestIndex = 11;
                }
            }
            if(exists18 && dist19 + cost18 + 10 < dist18)
            {
                dist18 = dist19 + cost18 + 10;
                lookup[indexToLocalY(18)][indexToLocalX(18)] = Direction.EAST;
                if(h18 < closestDistance)
                {
                    closestDistance = h18;
                    closestIndex = 18;
                }
            }
            if(exists20 && dist19 + cost20 + 10 < dist20)
            {
                dist20 = dist19 + cost20 + 10;
                lookup[indexToLocalY(20)][indexToLocalX(20)] = Direction.WEST;
                if(h20 < closestDistance)
                {
                    closestDistance = h20;
                    closestIndex = 20;
                }
            }
            if(exists27 && dist19 + cost27 + 14 < dist27)
            {
                dist27 = dist19 + cost27 + 14;
                lookup[indexToLocalY(27)][indexToLocalX(27)] = Direction.NORTHEAST;
                if(h27 < closestDistance)
                {
                    closestDistance = h27;
                    closestIndex = 27;
                }
            }
            if(exists28 && dist19 + cost28 + 10 < dist28)
            {
                dist28 = dist19 + cost28 + 10;
                lookup[indexToLocalY(28)][indexToLocalX(28)] = Direction.NORTH;
                if(h28 < closestDistance)
                {
                    closestDistance = h28;
                    closestIndex = 28;
                }
            }
            if(exists29 && dist19 + cost29 + 14 < dist29)
            {
                dist29 = dist19 + cost29 + 14;
                lookup[indexToLocalY(29)][indexToLocalX(29)] = Direction.NORTHWEST;
                if(h29 < closestDistance)
                {
                    closestDistance = h29;
                    closestIndex = 29;
                }
            }
        }
        if(exists28)
        {
            if(exists18 && dist28 + cost18 + 14 < dist18)
            {
                dist18 = dist28 + cost18 + 14;
                lookup[indexToLocalY(18)][indexToLocalX(18)] = Direction.SOUTHEAST;
                if(h18 < closestDistance)
                {
                    closestDistance = h18;
                    closestIndex = 18;
                }
            }
            if(exists19 && dist28 + cost19 + 10 < dist19)
            {
                dist19 = dist28 + cost19 + 10;
                lookup[indexToLocalY(19)][indexToLocalX(19)] = Direction.SOUTH;
                if(h19 < closestDistance)
                {
                    closestDistance = h19;
                    closestIndex = 19;
                }
            }
            if(exists20 && dist28 + cost20 + 14 < dist20)
            {
                dist20 = dist28 + cost20 + 14;
                lookup[indexToLocalY(20)][indexToLocalX(20)] = Direction.SOUTHWEST;
                if(h20 < closestDistance)
                {
                    closestDistance = h20;
                    closestIndex = 20;
                }
            }
            if(exists27 && dist28 + cost27 + 10 < dist27)
            {
                dist27 = dist28 + cost27 + 10;
                lookup[indexToLocalY(27)][indexToLocalX(27)] = Direction.EAST;
                if(h27 < closestDistance)
                {
                    closestDistance = h27;
                    closestIndex = 27;
                }
            }
            if(exists29 && dist28 + cost29 + 10 < dist29)
            {
                dist29 = dist28 + cost29 + 10;
                lookup[indexToLocalY(29)][indexToLocalX(29)] = Direction.WEST;
                if(h29 < closestDistance)
                {
                    closestDistance = h29;
                    closestIndex = 29;
                }
            }
            if(exists36 && dist28 + cost36 + 14 < dist36)
            {
                dist36 = dist28 + cost36 + 14;
                lookup[indexToLocalY(36)][indexToLocalX(36)] = Direction.NORTHEAST;
                if(h36 < closestDistance)
                {
                    closestDistance = h36;
                    closestIndex = 36;
                }
            }
            if(exists37 && dist28 + cost37 + 10 < dist37)
            {
                dist37 = dist28 + cost37 + 10;
                lookup[indexToLocalY(37)][indexToLocalX(37)] = Direction.NORTH;
                if(h37 < closestDistance)
                {
                    closestDistance = h37;
                    closestIndex = 37;
                }
            }
            if(exists38 && dist28 + cost38 + 14 < dist38)
            {
                dist38 = dist28 + cost38 + 14;
                lookup[indexToLocalY(38)][indexToLocalX(38)] = Direction.NORTHWEST;
                if(h38 < closestDistance)
                {
                    closestDistance = h38;
                    closestIndex = 38;
                }
            }
        }
        if(exists13)
        {
            if(exists3 && dist13 + cost3 + 14 < dist3)
            {
                dist3 = dist13 + cost3 + 14;
                lookup[indexToLocalY(3)][indexToLocalX(3)] = Direction.SOUTHEAST;
                if(h3 < closestDistance)
                {
                    closestDistance = h3;
                    closestIndex = 3;
                }
            }
            if(exists4 && dist13 + cost4 + 10 < dist4)
            {
                dist4 = dist13 + cost4 + 10;
                lookup[indexToLocalY(4)][indexToLocalX(4)] = Direction.SOUTH;
                if(h4 < closestDistance)
                {
                    closestDistance = h4;
                    closestIndex = 4;
                }
            }
            if(exists5 && dist13 + cost5 + 14 < dist5)
            {
                dist5 = dist13 + cost5 + 14;
                lookup[indexToLocalY(5)][indexToLocalX(5)] = Direction.SOUTHWEST;
                if(h5 < closestDistance)
                {
                    closestDistance = h5;
                    closestIndex = 5;
                }
            }
            if(exists12 && dist13 + cost12 + 10 < dist12)
            {
                dist12 = dist13 + cost12 + 10;
                lookup[indexToLocalY(12)][indexToLocalX(12)] = Direction.EAST;
                if(h12 < closestDistance)
                {
                    closestDistance = h12;
                    closestIndex = 12;
                }
            }
            if(exists14 && dist13 + cost14 + 10 < dist14)
            {
                dist14 = dist13 + cost14 + 10;
                lookup[indexToLocalY(14)][indexToLocalX(14)] = Direction.WEST;
                if(h14 < closestDistance)
                {
                    closestDistance = h14;
                    closestIndex = 14;
                }
            }
            if(exists21 && dist13 + cost21 + 14 < dist21)
            {
                dist21 = dist13 + cost21 + 14;
                lookup[indexToLocalY(21)][indexToLocalX(21)] = Direction.NORTHEAST;
                if(h21 < closestDistance)
                {
                    closestDistance = h21;
                    closestIndex = 21;
                }
            }
            if(exists22 && dist13 + cost22 + 10 < dist22)
            {
                dist22 = dist13 + cost22 + 10;
                lookup[indexToLocalY(22)][indexToLocalX(22)] = Direction.NORTH;
                if(h22 < closestDistance)
                {
                    closestDistance = h22;
                    closestIndex = 22;
                }
            }
            if(exists23 && dist13 + cost23 + 14 < dist23)
            {
                dist23 = dist13 + cost23 + 14;
                lookup[indexToLocalY(23)][indexToLocalX(23)] = Direction.NORTHWEST;
                if(h23 < closestDistance)
                {
                    closestDistance = h23;
                    closestIndex = 23;
                }
            }
        }
        if(exists14)
        {
            if(exists4 && dist14 + cost4 + 14 < dist4)
            {
                dist4 = dist14 + cost4 + 14;
                lookup[indexToLocalY(4)][indexToLocalX(4)] = Direction.SOUTHEAST;
                if(h4 < closestDistance)
                {
                    closestDistance = h4;
                    closestIndex = 4;
                }
            }
            if(exists5 && dist14 + cost5 + 10 < dist5)
            {
                dist5 = dist14 + cost5 + 10;
                lookup[indexToLocalY(5)][indexToLocalX(5)] = Direction.SOUTH;
                if(h5 < closestDistance)
                {
                    closestDistance = h5;
                    closestIndex = 5;
                }
            }
            if(exists6 && dist14 + cost6 + 14 < dist6)
            {
                dist6 = dist14 + cost6 + 14;
                lookup[indexToLocalY(6)][indexToLocalX(6)] = Direction.SOUTHWEST;
                if(h6 < closestDistance)
                {
                    closestDistance = h6;
                    closestIndex = 6;
                }
            }
            if(exists13 && dist14 + cost13 + 10 < dist13)
            {
                dist13 = dist14 + cost13 + 10;
                lookup[indexToLocalY(13)][indexToLocalX(13)] = Direction.EAST;
                if(h13 < closestDistance)
                {
                    closestDistance = h13;
                    closestIndex = 13;
                }
            }
            if(exists15 && dist14 + cost15 + 10 < dist15)
            {
                dist15 = dist14 + cost15 + 10;
                lookup[indexToLocalY(15)][indexToLocalX(15)] = Direction.WEST;
                if(h15 < closestDistance)
                {
                    closestDistance = h15;
                    closestIndex = 15;
                }
            }
            if(exists22 && dist14 + cost22 + 14 < dist22)
            {
                dist22 = dist14 + cost22 + 14;
                lookup[indexToLocalY(22)][indexToLocalX(22)] = Direction.NORTHEAST;
                if(h22 < closestDistance)
                {
                    closestDistance = h22;
                    closestIndex = 22;
                }
            }
            if(exists23 && dist14 + cost23 + 10 < dist23)
            {
                dist23 = dist14 + cost23 + 10;
                lookup[indexToLocalY(23)][indexToLocalX(23)] = Direction.NORTH;
                if(h23 < closestDistance)
                {
                    closestDistance = h23;
                    closestIndex = 23;
                }
            }
            if(exists24 && dist14 + cost24 + 14 < dist24)
            {
                dist24 = dist14 + cost24 + 14;
                lookup[indexToLocalY(24)][indexToLocalX(24)] = Direction.NORTHWEST;
                if(h24 < closestDistance)
                {
                    closestDistance = h24;
                    closestIndex = 24;
                }
            }
        }
        if(exists37)
        {
            if(exists27 && dist37 + cost27 + 14 < dist27)
            {
                dist27 = dist37 + cost27 + 14;
                lookup[indexToLocalY(27)][indexToLocalX(27)] = Direction.SOUTHEAST;
                if(h27 < closestDistance)
                {
                    closestDistance = h27;
                    closestIndex = 27;
                }
            }
            if(exists28 && dist37 + cost28 + 10 < dist28)
            {
                dist28 = dist37 + cost28 + 10;
                lookup[indexToLocalY(28)][indexToLocalX(28)] = Direction.SOUTH;
                if(h28 < closestDistance)
                {
                    closestDistance = h28;
                    closestIndex = 28;
                }
            }
            if(exists29 && dist37 + cost29 + 14 < dist29)
            {
                dist29 = dist37 + cost29 + 14;
                lookup[indexToLocalY(29)][indexToLocalX(29)] = Direction.SOUTHWEST;
                if(h29 < closestDistance)
                {
                    closestDistance = h29;
                    closestIndex = 29;
                }
            }
            if(exists36 && dist37 + cost36 + 10 < dist36)
            {
                dist36 = dist37 + cost36 + 10;
                lookup[indexToLocalY(36)][indexToLocalX(36)] = Direction.EAST;
                if(h36 < closestDistance)
                {
                    closestDistance = h36;
                    closestIndex = 36;
                }
            }
            if(exists38 && dist37 + cost38 + 10 < dist38)
            {
                dist38 = dist37 + cost38 + 10;
                lookup[indexToLocalY(38)][indexToLocalX(38)] = Direction.WEST;
                if(h38 < closestDistance)
                {
                    closestDistance = h38;
                    closestIndex = 38;
                }
            }
            if(exists45 && dist37 + cost45 + 14 < dist45)
            {
                dist45 = dist37 + cost45 + 14;
                lookup[indexToLocalY(45)][indexToLocalX(45)] = Direction.NORTHEAST;
                if(h45 < closestDistance)
                {
                    closestDistance = h45;
                    closestIndex = 45;
                }
            }
            if(exists46 && dist37 + cost46 + 10 < dist46)
            {
                dist46 = dist37 + cost46 + 10;
                lookup[indexToLocalY(46)][indexToLocalX(46)] = Direction.NORTH;
                if(h46 < closestDistance)
                {
                    closestDistance = h46;
                    closestIndex = 46;
                }
            }
            if(exists47 && dist37 + cost47 + 14 < dist47)
            {
                dist47 = dist37 + cost47 + 14;
                lookup[indexToLocalY(47)][indexToLocalX(47)] = Direction.NORTHWEST;
                if(h47 < closestDistance)
                {
                    closestDistance = h47;
                    closestIndex = 47;
                }
            }
        }
        if(exists46)
        {
            if(exists36 && dist46 + cost36 + 14 < dist36)
            {
                dist36 = dist46 + cost36 + 14;
                lookup[indexToLocalY(36)][indexToLocalX(36)] = Direction.SOUTHEAST;
                if(h36 < closestDistance)
                {
                    closestDistance = h36;
                    closestIndex = 36;
                }
            }
            if(exists37 && dist46 + cost37 + 10 < dist37)
            {
                dist37 = dist46 + cost37 + 10;
                lookup[indexToLocalY(37)][indexToLocalX(37)] = Direction.SOUTH;
                if(h37 < closestDistance)
                {
                    closestDistance = h37;
                    closestIndex = 37;
                }
            }
            if(exists38 && dist46 + cost38 + 14 < dist38)
            {
                dist38 = dist46 + cost38 + 14;
                lookup[indexToLocalY(38)][indexToLocalX(38)] = Direction.SOUTHWEST;
                if(h38 < closestDistance)
                {
                    closestDistance = h38;
                    closestIndex = 38;
                }
            }
            if(exists45 && dist46 + cost45 + 10 < dist45)
            {
                dist45 = dist46 + cost45 + 10;
                lookup[indexToLocalY(45)][indexToLocalX(45)] = Direction.EAST;
                if(h45 < closestDistance)
                {
                    closestDistance = h45;
                    closestIndex = 45;
                }
            }
            if(exists47 && dist46 + cost47 + 10 < dist47)
            {
                dist47 = dist46 + cost47 + 10;
                lookup[indexToLocalY(47)][indexToLocalX(47)] = Direction.WEST;
                if(h47 < closestDistance)
                {
                    closestDistance = h47;
                    closestIndex = 47;
                }
            }
            if(exists54 && dist46 + cost54 + 14 < dist54)
            {
                dist54 = dist46 + cost54 + 14;
                lookup[indexToLocalY(54)][indexToLocalX(54)] = Direction.NORTHEAST;
                if(h54 < closestDistance)
                {
                    closestDistance = h54;
                    closestIndex = 54;
                }
            }
            if(exists55 && dist46 + cost55 + 10 < dist55)
            {
                dist55 = dist46 + cost55 + 10;
                lookup[indexToLocalY(55)][indexToLocalX(55)] = Direction.NORTH;
                if(h55 < closestDistance)
                {
                    closestDistance = h55;
                    closestIndex = 55;
                }
            }
            if(exists56 && dist46 + cost56 + 14 < dist56)
            {
                dist56 = dist46 + cost56 + 14;
                lookup[indexToLocalY(56)][indexToLocalX(56)] = Direction.NORTHWEST;
                if(h56 < closestDistance)
                {
                    closestDistance = h56;
                    closestIndex = 56;
                }
            }
        }
        if(exists15)
        {
            if(exists5 && dist15 + cost5 + 14 < dist5)
            {
                dist5 = dist15 + cost5 + 14;
                lookup[indexToLocalY(5)][indexToLocalX(5)] = Direction.SOUTHEAST;
                if(h5 < closestDistance)
                {
                    closestDistance = h5;
                    closestIndex = 5;
                }
            }
            if(exists6 && dist15 + cost6 + 10 < dist6)
            {
                dist6 = dist15 + cost6 + 10;
                lookup[indexToLocalY(6)][indexToLocalX(6)] = Direction.SOUTH;
                if(h6 < closestDistance)
                {
                    closestDistance = h6;
                    closestIndex = 6;
                }
            }
            if(exists14 && dist15 + cost14 + 10 < dist14)
            {
                dist14 = dist15 + cost14 + 10;
                lookup[indexToLocalY(14)][indexToLocalX(14)] = Direction.EAST;
                if(h14 < closestDistance)
                {
                    closestDistance = h14;
                    closestIndex = 14;
                }
            }
            if(exists16 && dist15 + cost16 + 10 < dist16)
            {
                dist16 = dist15 + cost16 + 10;
                lookup[indexToLocalY(16)][indexToLocalX(16)] = Direction.WEST;
                if(h16 < closestDistance)
                {
                    closestDistance = h16;
                    closestIndex = 16;
                }
            }
            if(exists23 && dist15 + cost23 + 14 < dist23)
            {
                dist23 = dist15 + cost23 + 14;
                lookup[indexToLocalY(23)][indexToLocalX(23)] = Direction.NORTHEAST;
                if(h23 < closestDistance)
                {
                    closestDistance = h23;
                    closestIndex = 23;
                }
            }
            if(exists24 && dist15 + cost24 + 10 < dist24)
            {
                dist24 = dist15 + cost24 + 10;
                lookup[indexToLocalY(24)][indexToLocalX(24)] = Direction.NORTH;
                if(h24 < closestDistance)
                {
                    closestDistance = h24;
                    closestIndex = 24;
                }
            }
            if(exists25 && dist15 + cost25 + 14 < dist25)
            {
                dist25 = dist15 + cost25 + 14;
                lookup[indexToLocalY(25)][indexToLocalX(25)] = Direction.NORTHWEST;
                if(h25 < closestDistance)
                {
                    closestDistance = h25;
                    closestIndex = 25;
                }
            }
        }
        if(exists16)
        {
            if(exists6 && dist16 + cost6 + 14 < dist6)
            {
                dist6 = dist16 + cost6 + 14;
                lookup[indexToLocalY(6)][indexToLocalX(6)] = Direction.SOUTHEAST;
                if(h6 < closestDistance)
                {
                    closestDistance = h6;
                    closestIndex = 6;
                }
            }
            if(exists15 && dist16 + cost15 + 10 < dist15)
            {
                dist15 = dist16 + cost15 + 10;
                lookup[indexToLocalY(15)][indexToLocalX(15)] = Direction.EAST;
                if(h15 < closestDistance)
                {
                    closestDistance = h15;
                    closestIndex = 15;
                }
            }
            if(exists24 && dist16 + cost24 + 14 < dist24)
            {
                dist24 = dist16 + cost24 + 14;
                lookup[indexToLocalY(24)][indexToLocalX(24)] = Direction.NORTHEAST;
                if(h24 < closestDistance)
                {
                    closestDistance = h24;
                    closestIndex = 24;
                }
            }
            if(exists25 && dist16 + cost25 + 10 < dist25)
            {
                dist25 = dist16 + cost25 + 10;
                lookup[indexToLocalY(25)][indexToLocalX(25)] = Direction.NORTH;
                if(h25 < closestDistance)
                {
                    closestDistance = h25;
                    closestIndex = 25;
                }
            }
            if(exists26 && dist16 + cost26 + 14 < dist26)
            {
                dist26 = dist16 + cost26 + 14;
                lookup[indexToLocalY(26)][indexToLocalX(26)] = Direction.NORTHWEST;
                if(h26 < closestDistance)
                {
                    closestDistance = h26;
                    closestIndex = 26;
                }
            }
        }
        if(exists25)
        {
            if(exists15 && dist25 + cost15 + 14 < dist15)
            {
                dist15 = dist25 + cost15 + 14;
                lookup[indexToLocalY(15)][indexToLocalX(15)] = Direction.SOUTHEAST;
                if(h15 < closestDistance)
                {
                    closestDistance = h15;
                    closestIndex = 15;
                }
            }
            if(exists16 && dist25 + cost16 + 10 < dist16)
            {
                dist16 = dist25 + cost16 + 10;
                lookup[indexToLocalY(16)][indexToLocalX(16)] = Direction.SOUTH;
                if(h16 < closestDistance)
                {
                    closestDistance = h16;
                    closestIndex = 16;
                }
            }
            if(exists24 && dist25 + cost24 + 10 < dist24)
            {
                dist24 = dist25 + cost24 + 10;
                lookup[indexToLocalY(24)][indexToLocalX(24)] = Direction.EAST;
                if(h24 < closestDistance)
                {
                    closestDistance = h24;
                    closestIndex = 24;
                }
            }
            if(exists26 && dist25 + cost26 + 10 < dist26)
            {
                dist26 = dist25 + cost26 + 10;
                lookup[indexToLocalY(26)][indexToLocalX(26)] = Direction.WEST;
                if(h26 < closestDistance)
                {
                    closestDistance = h26;
                    closestIndex = 26;
                }
            }
            if(exists33 && dist25 + cost33 + 14 < dist33)
            {
                dist33 = dist25 + cost33 + 14;
                lookup[indexToLocalY(33)][indexToLocalX(33)] = Direction.NORTHEAST;
                if(h33 < closestDistance)
                {
                    closestDistance = h33;
                    closestIndex = 33;
                }
            }
            if(exists34 && dist25 + cost34 + 10 < dist34)
            {
                dist34 = dist25 + cost34 + 10;
                lookup[indexToLocalY(34)][indexToLocalX(34)] = Direction.NORTH;
                if(h34 < closestDistance)
                {
                    closestDistance = h34;
                    closestIndex = 34;
                }
            }
            if(exists35 && dist25 + cost35 + 14 < dist35)
            {
                dist35 = dist25 + cost35 + 14;
                lookup[indexToLocalY(35)][indexToLocalX(35)] = Direction.NORTHWEST;
                if(h35 < closestDistance)
                {
                    closestDistance = h35;
                    closestIndex = 35;
                }
            }
        }
        if(exists34)
        {
            if(exists24 && dist34 + cost24 + 14 < dist24)
            {
                dist24 = dist34 + cost24 + 14;
                lookup[indexToLocalY(24)][indexToLocalX(24)] = Direction.SOUTHEAST;
                if(h24 < closestDistance)
                {
                    closestDistance = h24;
                    closestIndex = 24;
                }
            }
            if(exists25 && dist34 + cost25 + 10 < dist25)
            {
                dist25 = dist34 + cost25 + 10;
                lookup[indexToLocalY(25)][indexToLocalX(25)] = Direction.SOUTH;
                if(h25 < closestDistance)
                {
                    closestDistance = h25;
                    closestIndex = 25;
                }
            }
            if(exists26 && dist34 + cost26 + 14 < dist26)
            {
                dist26 = dist34 + cost26 + 14;
                lookup[indexToLocalY(26)][indexToLocalX(26)] = Direction.SOUTHWEST;
                if(h26 < closestDistance)
                {
                    closestDistance = h26;
                    closestIndex = 26;
                }
            }
            if(exists33 && dist34 + cost33 + 10 < dist33)
            {
                dist33 = dist34 + cost33 + 10;
                lookup[indexToLocalY(33)][indexToLocalX(33)] = Direction.EAST;
                if(h33 < closestDistance)
                {
                    closestDistance = h33;
                    closestIndex = 33;
                }
            }
            if(exists35 && dist34 + cost35 + 10 < dist35)
            {
                dist35 = dist34 + cost35 + 10;
                lookup[indexToLocalY(35)][indexToLocalX(35)] = Direction.WEST;
                if(h35 < closestDistance)
                {
                    closestDistance = h35;
                    closestIndex = 35;
                }
            }
            if(exists42 && dist34 + cost42 + 14 < dist42)
            {
                dist42 = dist34 + cost42 + 14;
                lookup[indexToLocalY(42)][indexToLocalX(42)] = Direction.NORTHEAST;
                if(h42 < closestDistance)
                {
                    closestDistance = h42;
                    closestIndex = 42;
                }
            }
            if(exists43 && dist34 + cost43 + 10 < dist43)
            {
                dist43 = dist34 + cost43 + 10;
                lookup[indexToLocalY(43)][indexToLocalX(43)] = Direction.NORTH;
                if(h43 < closestDistance)
                {
                    closestDistance = h43;
                    closestIndex = 43;
                }
            }
            if(exists44 && dist34 + cost44 + 14 < dist44)
            {
                dist44 = dist34 + cost44 + 14;
                lookup[indexToLocalY(44)][indexToLocalX(44)] = Direction.NORTHWEST;
                if(h44 < closestDistance)
                {
                    closestDistance = h44;
                    closestIndex = 44;
                }
            }
        }
        if(exists43)
        {
            if(exists33 && dist43 + cost33 + 14 < dist33)
            {
                dist33 = dist43 + cost33 + 14;
                lookup[indexToLocalY(33)][indexToLocalX(33)] = Direction.SOUTHEAST;
                if(h33 < closestDistance)
                {
                    closestDistance = h33;
                    closestIndex = 33;
                }
            }
            if(exists34 && dist43 + cost34 + 10 < dist34)
            {
                dist34 = dist43 + cost34 + 10;
                lookup[indexToLocalY(34)][indexToLocalX(34)] = Direction.SOUTH;
                if(h34 < closestDistance)
                {
                    closestDistance = h34;
                    closestIndex = 34;
                }
            }
            if(exists35 && dist43 + cost35 + 14 < dist35)
            {
                dist35 = dist43 + cost35 + 14;
                lookup[indexToLocalY(35)][indexToLocalX(35)] = Direction.SOUTHWEST;
                if(h35 < closestDistance)
                {
                    closestDistance = h35;
                    closestIndex = 35;
                }
            }
            if(exists42 && dist43 + cost42 + 10 < dist42)
            {
                dist42 = dist43 + cost42 + 10;
                lookup[indexToLocalY(42)][indexToLocalX(42)] = Direction.EAST;
                if(h42 < closestDistance)
                {
                    closestDistance = h42;
                    closestIndex = 42;
                }
            }
            if(exists44 && dist43 + cost44 + 10 < dist44)
            {
                dist44 = dist43 + cost44 + 10;
                lookup[indexToLocalY(44)][indexToLocalX(44)] = Direction.WEST;
                if(h44 < closestDistance)
                {
                    closestDistance = h44;
                    closestIndex = 44;
                }
            }
            if(exists51 && dist43 + cost51 + 14 < dist51)
            {
                dist51 = dist43 + cost51 + 14;
                lookup[indexToLocalY(51)][indexToLocalX(51)] = Direction.NORTHEAST;
                if(h51 < closestDistance)
                {
                    closestDistance = h51;
                    closestIndex = 51;
                }
            }
            if(exists52 && dist43 + cost52 + 10 < dist52)
            {
                dist52 = dist43 + cost52 + 10;
                lookup[indexToLocalY(52)][indexToLocalX(52)] = Direction.NORTH;
                if(h52 < closestDistance)
                {
                    closestDistance = h52;
                    closestIndex = 52;
                }
            }
            if(exists53 && dist43 + cost53 + 14 < dist53)
            {
                dist53 = dist43 + cost53 + 14;
                lookup[indexToLocalY(53)][indexToLocalX(53)] = Direction.NORTHWEST;
                if(h53 < closestDistance)
                {
                    closestDistance = h53;
                    closestIndex = 53;
                }
            }
        }
        if(exists52)
        {
            if(exists42 && dist52 + cost42 + 14 < dist42)
            {
                dist42 = dist52 + cost42 + 14;
                lookup[indexToLocalY(42)][indexToLocalX(42)] = Direction.SOUTHEAST;
                if(h42 < closestDistance)
                {
                    closestDistance = h42;
                    closestIndex = 42;
                }
            }
            if(exists43 && dist52 + cost43 + 10 < dist43)
            {
                dist43 = dist52 + cost43 + 10;
                lookup[indexToLocalY(43)][indexToLocalX(43)] = Direction.SOUTH;
                if(h43 < closestDistance)
                {
                    closestDistance = h43;
                    closestIndex = 43;
                }
            }
            if(exists44 && dist52 + cost44 + 14 < dist44)
            {
                dist44 = dist52 + cost44 + 14;
                lookup[indexToLocalY(44)][indexToLocalX(44)] = Direction.SOUTHWEST;
                if(h44 < closestDistance)
                {
                    closestDistance = h44;
                    closestIndex = 44;
                }
            }
            if(exists51 && dist52 + cost51 + 10 < dist51)
            {
                dist51 = dist52 + cost51 + 10;
                lookup[indexToLocalY(51)][indexToLocalX(51)] = Direction.EAST;
                if(h51 < closestDistance)
                {
                    closestDistance = h51;
                    closestIndex = 51;
                }
            }
            if(exists53 && dist52 + cost53 + 10 < dist53)
            {
                dist53 = dist52 + cost53 + 10;
                lookup[indexToLocalY(53)][indexToLocalX(53)] = Direction.WEST;
                if(h53 < closestDistance)
                {
                    closestDistance = h53;
                    closestIndex = 53;
                }
            }
            if(exists60 && dist52 + cost60 + 14 < dist60)
            {
                dist60 = dist52 + cost60 + 14;
                lookup[indexToLocalY(60)][indexToLocalX(60)] = Direction.NORTHEAST;
                if(h60 < closestDistance)
                {
                    closestDistance = h60;
                    closestIndex = 60;
                }
            }
            if(exists61 && dist52 + cost61 + 10 < dist61)
            {
                dist61 = dist52 + cost61 + 10;
                lookup[indexToLocalY(61)][indexToLocalX(61)] = Direction.NORTH;
                if(h61 < closestDistance)
                {
                    closestDistance = h61;
                    closestIndex = 61;
                }
            }
            if(exists62 && dist52 + cost62 + 14 < dist62)
            {
                dist62 = dist52 + cost62 + 14;
                lookup[indexToLocalY(62)][indexToLocalX(62)] = Direction.NORTHWEST;
                if(h62 < closestDistance)
                {
                    closestDistance = h62;
                    closestIndex = 62;
                }
            }
        }
        if(exists55)
        {
            if(exists45 && dist55 + cost45 + 14 < dist45)
            {
                dist45 = dist55 + cost45 + 14;
                lookup[indexToLocalY(45)][indexToLocalX(45)] = Direction.SOUTHEAST;
                if(h45 < closestDistance)
                {
                    closestDistance = h45;
                    closestIndex = 45;
                }
            }
            if(exists46 && dist55 + cost46 + 10 < dist46)
            {
                dist46 = dist55 + cost46 + 10;
                lookup[indexToLocalY(46)][indexToLocalX(46)] = Direction.SOUTH;
                if(h46 < closestDistance)
                {
                    closestDistance = h46;
                    closestIndex = 46;
                }
            }
            if(exists47 && dist55 + cost47 + 14 < dist47)
            {
                dist47 = dist55 + cost47 + 14;
                lookup[indexToLocalY(47)][indexToLocalX(47)] = Direction.SOUTHWEST;
                if(h47 < closestDistance)
                {
                    closestDistance = h47;
                    closestIndex = 47;
                }
            }
            if(exists54 && dist55 + cost54 + 10 < dist54)
            {
                dist54 = dist55 + cost54 + 10;
                lookup[indexToLocalY(54)][indexToLocalX(54)] = Direction.EAST;
                if(h54 < closestDistance)
                {
                    closestDistance = h54;
                    closestIndex = 54;
                }
            }
            if(exists56 && dist55 + cost56 + 10 < dist56)
            {
                dist56 = dist55 + cost56 + 10;
                lookup[indexToLocalY(56)][indexToLocalX(56)] = Direction.WEST;
                if(h56 < closestDistance)
                {
                    closestDistance = h56;
                    closestIndex = 56;
                }
            }
            if(exists64 && dist55 + cost64 + 10 < dist64)
            {
                dist64 = dist55 + cost64 + 10;
                lookup[indexToLocalY(64)][indexToLocalX(64)] = Direction.NORTH;
                if(h64 < closestDistance)
                {
                    closestDistance = h64;
                    closestIndex = 64;
                }
            }
            if(exists65 && dist55 + cost65 + 14 < dist65)
            {
                dist65 = dist55 + cost65 + 14;
                lookup[indexToLocalY(65)][indexToLocalX(65)] = Direction.NORTHWEST;
                if(h65 < closestDistance)
                {
                    closestDistance = h65;
                    closestIndex = 65;
                }
            }
        }
        if(exists61)
        {
            if(exists51 && dist61 + cost51 + 14 < dist51)
            {
                dist51 = dist61 + cost51 + 14;
                lookup[indexToLocalY(51)][indexToLocalX(51)] = Direction.SOUTHEAST;
                if(h51 < closestDistance)
                {
                    closestDistance = h51;
                    closestIndex = 51;
                }
            }
            if(exists52 && dist61 + cost52 + 10 < dist52)
            {
                dist52 = dist61 + cost52 + 10;
                lookup[indexToLocalY(52)][indexToLocalX(52)] = Direction.SOUTH;
                if(h52 < closestDistance)
                {
                    closestDistance = h52;
                    closestIndex = 52;
                }
            }
            if(exists53 && dist61 + cost53 + 14 < dist53)
            {
                dist53 = dist61 + cost53 + 14;
                lookup[indexToLocalY(53)][indexToLocalX(53)] = Direction.SOUTHWEST;
                if(h53 < closestDistance)
                {
                    closestDistance = h53;
                    closestIndex = 53;
                }
            }
            if(exists60 && dist61 + cost60 + 10 < dist60)
            {
                dist60 = dist61 + cost60 + 10;
                lookup[indexToLocalY(60)][indexToLocalX(60)] = Direction.EAST;
                if(h60 < closestDistance)
                {
                    closestDistance = h60;
                    closestIndex = 60;
                }
            }
            if(exists62 && dist61 + cost62 + 10 < dist62)
            {
                dist62 = dist61 + cost62 + 10;
                lookup[indexToLocalY(62)][indexToLocalX(62)] = Direction.WEST;
                if(h62 < closestDistance)
                {
                    closestDistance = h62;
                    closestIndex = 62;
                }
            }
            if(exists69 && dist61 + cost69 + 14 < dist69)
            {
                dist69 = dist61 + cost69 + 14;
                lookup[indexToLocalY(69)][indexToLocalX(69)] = Direction.NORTHEAST;
                if(h69 < closestDistance)
                {
                    closestDistance = h69;
                    closestIndex = 69;
                }
            }
            if(exists70 && dist61 + cost70 + 10 < dist70)
            {
                dist70 = dist61 + cost70 + 10;
                lookup[indexToLocalY(70)][indexToLocalX(70)] = Direction.NORTH;
                if(h70 < closestDistance)
                {
                    closestDistance = h70;
                    closestIndex = 70;
                }
            }
        }
        if(exists64)
        {
            if(exists54 && dist64 + cost54 + 14 < dist54)
            {
                dist54 = dist64 + cost54 + 14;
                lookup[indexToLocalY(54)][indexToLocalX(54)] = Direction.SOUTHEAST;
                if(h54 < closestDistance)
                {
                    closestDistance = h54;
                    closestIndex = 54;
                }
            }
            if(exists55 && dist64 + cost55 + 10 < dist55)
            {
                dist55 = dist64 + cost55 + 10;
                lookup[indexToLocalY(55)][indexToLocalX(55)] = Direction.SOUTH;
                if(h55 < closestDistance)
                {
                    closestDistance = h55;
                    closestIndex = 55;
                }
            }
            if(exists56 && dist64 + cost56 + 14 < dist56)
            {
                dist56 = dist64 + cost56 + 14;
                lookup[indexToLocalY(56)][indexToLocalX(56)] = Direction.SOUTHWEST;
                if(h56 < closestDistance)
                {
                    closestDistance = h56;
                    closestIndex = 56;
                }
            }
            if(exists65 && dist64 + cost65 + 10 < dist65)
            {
                dist65 = dist64 + cost65 + 10;
                lookup[indexToLocalY(65)][indexToLocalX(65)] = Direction.WEST;
                if(h65 < closestDistance)
                {
                    closestDistance = h65;
                    closestIndex = 65;
                }
            }
            if(exists74 && dist64 + cost74 + 14 < dist74)
            {
                dist74 = dist64 + cost74 + 14;
                lookup[indexToLocalY(74)][indexToLocalX(74)] = Direction.NORTHWEST;
                if(h74 < closestDistance)
                {
                    closestDistance = h74;
                    closestIndex = 74;
                }
            }
        }
        if(exists65)
        {
            if(exists55 && dist65 + cost55 + 14 < dist55)
            {
                dist55 = dist65 + cost55 + 14;
                lookup[indexToLocalY(55)][indexToLocalX(55)] = Direction.SOUTHEAST;
                if(h55 < closestDistance)
                {
                    closestDistance = h55;
                    closestIndex = 55;
                }
            }
            if(exists56 && dist65 + cost56 + 10 < dist56)
            {
                dist56 = dist65 + cost56 + 10;
                lookup[indexToLocalY(56)][indexToLocalX(56)] = Direction.SOUTH;
                if(h56 < closestDistance)
                {
                    closestDistance = h56;
                    closestIndex = 56;
                }
            }
            if(exists57 && dist65 + cost57 + 14 < dist57)
            {
                dist57 = dist65 + cost57 + 14;
                lookup[indexToLocalY(57)][indexToLocalX(57)] = Direction.SOUTHWEST;
                if(h57 < closestDistance)
                {
                    closestDistance = h57;
                    closestIndex = 57;
                }
            }
            if(exists64 && dist65 + cost64 + 10 < dist64)
            {
                dist64 = dist65 + cost64 + 10;
                lookup[indexToLocalY(64)][indexToLocalX(64)] = Direction.EAST;
                if(h64 < closestDistance)
                {
                    closestDistance = h64;
                    closestIndex = 64;
                }
            }
            if(exists66 && dist65 + cost66 + 10 < dist66)
            {
                dist66 = dist65 + cost66 + 10;
                lookup[indexToLocalY(66)][indexToLocalX(66)] = Direction.WEST;
                if(h66 < closestDistance)
                {
                    closestDistance = h66;
                    closestIndex = 66;
                }
            }
            if(exists74 && dist65 + cost74 + 10 < dist74)
            {
                dist74 = dist65 + cost74 + 10;
                lookup[indexToLocalY(74)][indexToLocalX(74)] = Direction.NORTH;
                if(h74 < closestDistance)
                {
                    closestDistance = h74;
                    closestIndex = 74;
                }
            }
            if(exists75 && dist65 + cost75 + 14 < dist75)
            {
                dist75 = dist65 + cost75 + 14;
                lookup[indexToLocalY(75)][indexToLocalX(75)] = Direction.NORTHWEST;
                if(h75 < closestDistance)
                {
                    closestDistance = h75;
                    closestIndex = 75;
                }
            }
        }
        if(exists66)
        {
            if(exists56 && dist66 + cost56 + 14 < dist56)
            {
                dist56 = dist66 + cost56 + 14;
                lookup[indexToLocalY(56)][indexToLocalX(56)] = Direction.SOUTHEAST;
                if(h56 < closestDistance)
                {
                    closestDistance = h56;
                    closestIndex = 56;
                }
            }
            if(exists57 && dist66 + cost57 + 10 < dist57)
            {
                dist57 = dist66 + cost57 + 10;
                lookup[indexToLocalY(57)][indexToLocalX(57)] = Direction.SOUTH;
                if(h57 < closestDistance)
                {
                    closestDistance = h57;
                    closestIndex = 57;
                }
            }
            if(exists58 && dist66 + cost58 + 14 < dist58)
            {
                dist58 = dist66 + cost58 + 14;
                lookup[indexToLocalY(58)][indexToLocalX(58)] = Direction.SOUTHWEST;
                if(h58 < closestDistance)
                {
                    closestDistance = h58;
                    closestIndex = 58;
                }
            }
            if(exists65 && dist66 + cost65 + 10 < dist65)
            {
                dist65 = dist66 + cost65 + 10;
                lookup[indexToLocalY(65)][indexToLocalX(65)] = Direction.EAST;
                if(h65 < closestDistance)
                {
                    closestDistance = h65;
                    closestIndex = 65;
                }
            }
            if(exists67 && dist66 + cost67 + 10 < dist67)
            {
                dist67 = dist66 + cost67 + 10;
                lookup[indexToLocalY(67)][indexToLocalX(67)] = Direction.WEST;
                if(h67 < closestDistance)
                {
                    closestDistance = h67;
                    closestIndex = 67;
                }
            }
            if(exists74 && dist66 + cost74 + 14 < dist74)
            {
                dist74 = dist66 + cost74 + 14;
                lookup[indexToLocalY(74)][indexToLocalX(74)] = Direction.NORTHEAST;
                if(h74 < closestDistance)
                {
                    closestDistance = h74;
                    closestIndex = 74;
                }
            }
            if(exists75 && dist66 + cost75 + 10 < dist75)
            {
                dist75 = dist66 + cost75 + 10;
                lookup[indexToLocalY(75)][indexToLocalX(75)] = Direction.NORTH;
                if(h75 < closestDistance)
                {
                    closestDistance = h75;
                    closestIndex = 75;
                }
            }
            if(exists76 && dist66 + cost76 + 14 < dist76)
            {
                dist76 = dist66 + cost76 + 14;
                lookup[indexToLocalY(76)][indexToLocalX(76)] = Direction.NORTHWEST;
                if(h76 < closestDistance)
                {
                    closestDistance = h76;
                    closestIndex = 76;
                }
            }
        }
        if(exists67)
        {
            if(exists57 && dist67 + cost57 + 14 < dist57)
            {
                dist57 = dist67 + cost57 + 14;
                lookup[indexToLocalY(57)][indexToLocalX(57)] = Direction.SOUTHEAST;
                if(h57 < closestDistance)
                {
                    closestDistance = h57;
                    closestIndex = 57;
                }
            }
            if(exists58 && dist67 + cost58 + 10 < dist58)
            {
                dist58 = dist67 + cost58 + 10;
                lookup[indexToLocalY(58)][indexToLocalX(58)] = Direction.SOUTH;
                if(h58 < closestDistance)
                {
                    closestDistance = h58;
                    closestIndex = 58;
                }
            }
            if(exists59 && dist67 + cost59 + 14 < dist59)
            {
                dist59 = dist67 + cost59 + 14;
                lookup[indexToLocalY(59)][indexToLocalX(59)] = Direction.SOUTHWEST;
                if(h59 < closestDistance)
                {
                    closestDistance = h59;
                    closestIndex = 59;
                }
            }
            if(exists66 && dist67 + cost66 + 10 < dist66)
            {
                dist66 = dist67 + cost66 + 10;
                lookup[indexToLocalY(66)][indexToLocalX(66)] = Direction.EAST;
                if(h66 < closestDistance)
                {
                    closestDistance = h66;
                    closestIndex = 66;
                }
            }
            if(exists68 && dist67 + cost68 + 10 < dist68)
            {
                dist68 = dist67 + cost68 + 10;
                lookup[indexToLocalY(68)][indexToLocalX(68)] = Direction.WEST;
                if(h68 < closestDistance)
                {
                    closestDistance = h68;
                    closestIndex = 68;
                }
            }
            if(exists75 && dist67 + cost75 + 14 < dist75)
            {
                dist75 = dist67 + cost75 + 14;
                lookup[indexToLocalY(75)][indexToLocalX(75)] = Direction.NORTHEAST;
                if(h75 < closestDistance)
                {
                    closestDistance = h75;
                    closestIndex = 75;
                }
            }
            if(exists76 && dist67 + cost76 + 10 < dist76)
            {
                dist76 = dist67 + cost76 + 10;
                lookup[indexToLocalY(76)][indexToLocalX(76)] = Direction.NORTH;
                if(h76 < closestDistance)
                {
                    closestDistance = h76;
                    closestIndex = 76;
                }
            }
            if(exists77 && dist67 + cost77 + 14 < dist77)
            {
                dist77 = dist67 + cost77 + 14;
                lookup[indexToLocalY(77)][indexToLocalX(77)] = Direction.NORTHWEST;
                if(h77 < closestDistance)
                {
                    closestDistance = h77;
                    closestIndex = 77;
                }
            }
        }
        if(exists68)
        {
            if(exists58 && dist68 + cost58 + 14 < dist58)
            {
                dist58 = dist68 + cost58 + 14;
                lookup[indexToLocalY(58)][indexToLocalX(58)] = Direction.SOUTHEAST;
                if(h58 < closestDistance)
                {
                    closestDistance = h58;
                    closestIndex = 58;
                }
            }
            if(exists59 && dist68 + cost59 + 10 < dist59)
            {
                dist59 = dist68 + cost59 + 10;
                lookup[indexToLocalY(59)][indexToLocalX(59)] = Direction.SOUTH;
                if(h59 < closestDistance)
                {
                    closestDistance = h59;
                    closestIndex = 59;
                }
            }
            if(exists60 && dist68 + cost60 + 14 < dist60)
            {
                dist60 = dist68 + cost60 + 14;
                lookup[indexToLocalY(60)][indexToLocalX(60)] = Direction.SOUTHWEST;
                if(h60 < closestDistance)
                {
                    closestDistance = h60;
                    closestIndex = 60;
                }
            }
            if(exists67 && dist68 + cost67 + 10 < dist67)
            {
                dist67 = dist68 + cost67 + 10;
                lookup[indexToLocalY(67)][indexToLocalX(67)] = Direction.EAST;
                if(h67 < closestDistance)
                {
                    closestDistance = h67;
                    closestIndex = 67;
                }
            }
            if(exists69 && dist68 + cost69 + 10 < dist69)
            {
                dist69 = dist68 + cost69 + 10;
                lookup[indexToLocalY(69)][indexToLocalX(69)] = Direction.WEST;
                if(h69 < closestDistance)
                {
                    closestDistance = h69;
                    closestIndex = 69;
                }
            }
            if(exists76 && dist68 + cost76 + 14 < dist76)
            {
                dist76 = dist68 + cost76 + 14;
                lookup[indexToLocalY(76)][indexToLocalX(76)] = Direction.NORTHEAST;
                if(h76 < closestDistance)
                {
                    closestDistance = h76;
                    closestIndex = 76;
                }
            }
            if(exists77 && dist68 + cost77 + 10 < dist77)
            {
                dist77 = dist68 + cost77 + 10;
                lookup[indexToLocalY(77)][indexToLocalX(77)] = Direction.NORTH;
                if(h77 < closestDistance)
                {
                    closestDistance = h77;
                    closestIndex = 77;
                }
            }
            if(exists78 && dist68 + cost78 + 14 < dist78)
            {
                dist78 = dist68 + cost78 + 14;
                lookup[indexToLocalY(78)][indexToLocalX(78)] = Direction.NORTHWEST;
                if(h78 < closestDistance)
                {
                    closestDistance = h78;
                    closestIndex = 78;
                }
            }
        }
        if(exists69)
        {
            if(exists59 && dist69 + cost59 + 14 < dist59)
            {
                dist59 = dist69 + cost59 + 14;
                lookup[indexToLocalY(59)][indexToLocalX(59)] = Direction.SOUTHEAST;
                if(h59 < closestDistance)
                {
                    closestDistance = h59;
                    closestIndex = 59;
                }
            }
            if(exists60 && dist69 + cost60 + 10 < dist60)
            {
                dist60 = dist69 + cost60 + 10;
                lookup[indexToLocalY(60)][indexToLocalX(60)] = Direction.SOUTH;
                if(h60 < closestDistance)
                {
                    closestDistance = h60;
                    closestIndex = 60;
                }
            }
            if(exists61 && dist69 + cost61 + 14 < dist61)
            {
                dist61 = dist69 + cost61 + 14;
                lookup[indexToLocalY(61)][indexToLocalX(61)] = Direction.SOUTHWEST;
                if(h61 < closestDistance)
                {
                    closestDistance = h61;
                    closestIndex = 61;
                }
            }
            if(exists68 && dist69 + cost68 + 10 < dist68)
            {
                dist68 = dist69 + cost68 + 10;
                lookup[indexToLocalY(68)][indexToLocalX(68)] = Direction.EAST;
                if(h68 < closestDistance)
                {
                    closestDistance = h68;
                    closestIndex = 68;
                }
            }
            if(exists70 && dist69 + cost70 + 10 < dist70)
            {
                dist70 = dist69 + cost70 + 10;
                lookup[indexToLocalY(70)][indexToLocalX(70)] = Direction.WEST;
                if(h70 < closestDistance)
                {
                    closestDistance = h70;
                    closestIndex = 70;
                }
            }
            if(exists77 && dist69 + cost77 + 14 < dist77)
            {
                dist77 = dist69 + cost77 + 14;
                lookup[indexToLocalY(77)][indexToLocalX(77)] = Direction.NORTHEAST;
                if(h77 < closestDistance)
                {
                    closestDistance = h77;
                    closestIndex = 77;
                }
            }
            if(exists78 && dist69 + cost78 + 10 < dist78)
            {
                dist78 = dist69 + cost78 + 10;
                lookup[indexToLocalY(78)][indexToLocalX(78)] = Direction.NORTH;
                if(h78 < closestDistance)
                {
                    closestDistance = h78;
                    closestIndex = 78;
                }
            }
        }
        if(exists70)
        {
            if(exists60 && dist70 + cost60 + 14 < dist60)
            {
                dist60 = dist70 + cost60 + 14;
                lookup[indexToLocalY(60)][indexToLocalX(60)] = Direction.SOUTHEAST;
                if(h60 < closestDistance)
                {
                    closestDistance = h60;
                    closestIndex = 60;
                }
            }
            if(exists61 && dist70 + cost61 + 10 < dist61)
            {
                dist61 = dist70 + cost61 + 10;
                lookup[indexToLocalY(61)][indexToLocalX(61)] = Direction.SOUTH;
                if(h61 < closestDistance)
                {
                    closestDistance = h61;
                    closestIndex = 61;
                }
            }
            if(exists62 && dist70 + cost62 + 14 < dist62)
            {
                dist62 = dist70 + cost62 + 14;
                lookup[indexToLocalY(62)][indexToLocalX(62)] = Direction.SOUTHWEST;
                if(h62 < closestDistance)
                {
                    closestDistance = h62;
                    closestIndex = 62;
                }
            }
            if(exists69 && dist70 + cost69 + 10 < dist69)
            {
                dist69 = dist70 + cost69 + 10;
                lookup[indexToLocalY(69)][indexToLocalX(69)] = Direction.EAST;
                if(h69 < closestDistance)
                {
                    closestDistance = h69;
                    closestIndex = 69;
                }
            }
            if(exists78 && dist70 + cost78 + 14 < dist78)
            {
                dist78 = dist70 + cost78 + 14;
                lookup[indexToLocalY(78)][indexToLocalX(78)] = Direction.NORTHEAST;
                if(h78 < closestDistance)
                {
                    closestDistance = h78;
                    closestIndex = 78;
                }
            }
        }
        if(exists2)
        {
            if(exists3 && dist2 + cost3 + 10 < dist3)
            {
                dist3 = dist2 + cost3 + 10;
                lookup[indexToLocalY(3)][indexToLocalX(3)] = Direction.WEST;
                if(h3 < closestDistance)
                {
                    closestDistance = h3;
                    closestIndex = 3;
                }
            }
            if(exists10 && dist2 + cost10 + 14 < dist10)
            {
                dist10 = dist2 + cost10 + 14;
                lookup[indexToLocalY(10)][indexToLocalX(10)] = Direction.NORTHEAST;
                if(h10 < closestDistance)
                {
                    closestDistance = h10;
                    closestIndex = 10;
                }
            }
            if(exists11 && dist2 + cost11 + 10 < dist11)
            {
                dist11 = dist2 + cost11 + 10;
                lookup[indexToLocalY(11)][indexToLocalX(11)] = Direction.NORTH;
                if(h11 < closestDistance)
                {
                    closestDistance = h11;
                    closestIndex = 11;
                }
            }
            if(exists12 && dist2 + cost12 + 14 < dist12)
            {
                dist12 = dist2 + cost12 + 14;
                lookup[indexToLocalY(12)][indexToLocalX(12)] = Direction.NORTHWEST;
                if(h12 < closestDistance)
                {
                    closestDistance = h12;
                    closestIndex = 12;
                }
            }
        }
        if(exists18)
        {
            if(exists10 && dist18 + cost10 + 14 < dist10)
            {
                dist10 = dist18 + cost10 + 14;
                lookup[indexToLocalY(10)][indexToLocalX(10)] = Direction.SOUTHWEST;
                if(h10 < closestDistance)
                {
                    closestDistance = h10;
                    closestIndex = 10;
                }
            }
            if(exists19 && dist18 + cost19 + 10 < dist19)
            {
                dist19 = dist18 + cost19 + 10;
                lookup[indexToLocalY(19)][indexToLocalX(19)] = Direction.WEST;
                if(h19 < closestDistance)
                {
                    closestDistance = h19;
                    closestIndex = 19;
                }
            }
            if(exists27 && dist18 + cost27 + 10 < dist27)
            {
                dist27 = dist18 + cost27 + 10;
                lookup[indexToLocalY(27)][indexToLocalX(27)] = Direction.NORTH;
                if(h27 < closestDistance)
                {
                    closestDistance = h27;
                    closestIndex = 27;
                }
            }
            if(exists28 && dist18 + cost28 + 14 < dist28)
            {
                dist28 = dist18 + cost28 + 14;
                lookup[indexToLocalY(28)][indexToLocalX(28)] = Direction.NORTHWEST;
                if(h28 < closestDistance)
                {
                    closestDistance = h28;
                    closestIndex = 28;
                }
            }
        }
        if(exists3)
        {
            if(exists2 && dist3 + cost2 + 10 < dist2)
            {
                dist2 = dist3 + cost2 + 10;
                lookup[indexToLocalY(2)][indexToLocalX(2)] = Direction.EAST;
                if(h2 < closestDistance)
                {
                    closestDistance = h2;
                    closestIndex = 2;
                }
            }
            if(exists4 && dist3 + cost4 + 10 < dist4)
            {
                dist4 = dist3 + cost4 + 10;
                lookup[indexToLocalY(4)][indexToLocalX(4)] = Direction.WEST;
                if(h4 < closestDistance)
                {
                    closestDistance = h4;
                    closestIndex = 4;
                }
            }
            if(exists11 && dist3 + cost11 + 14 < dist11)
            {
                dist11 = dist3 + cost11 + 14;
                lookup[indexToLocalY(11)][indexToLocalX(11)] = Direction.NORTHEAST;
                if(h11 < closestDistance)
                {
                    closestDistance = h11;
                    closestIndex = 11;
                }
            }
            if(exists12 && dist3 + cost12 + 10 < dist12)
            {
                dist12 = dist3 + cost12 + 10;
                lookup[indexToLocalY(12)][indexToLocalX(12)] = Direction.NORTH;
                if(h12 < closestDistance)
                {
                    closestDistance = h12;
                    closestIndex = 12;
                }
            }
            if(exists13 && dist3 + cost13 + 14 < dist13)
            {
                dist13 = dist3 + cost13 + 14;
                lookup[indexToLocalY(13)][indexToLocalX(13)] = Direction.NORTHWEST;
                if(h13 < closestDistance)
                {
                    closestDistance = h13;
                    closestIndex = 13;
                }
            }
        }
        if(exists4)
        {
            if(exists3 && dist4 + cost3 + 10 < dist3)
            {
                dist3 = dist4 + cost3 + 10;
                lookup[indexToLocalY(3)][indexToLocalX(3)] = Direction.EAST;
                if(h3 < closestDistance)
                {
                    closestDistance = h3;
                    closestIndex = 3;
                }
            }
            if(exists5 && dist4 + cost5 + 10 < dist5)
            {
                dist5 = dist4 + cost5 + 10;
                lookup[indexToLocalY(5)][indexToLocalX(5)] = Direction.WEST;
                if(h5 < closestDistance)
                {
                    closestDistance = h5;
                    closestIndex = 5;
                }
            }
            if(exists12 && dist4 + cost12 + 14 < dist12)
            {
                dist12 = dist4 + cost12 + 14;
                lookup[indexToLocalY(12)][indexToLocalX(12)] = Direction.NORTHEAST;
                if(h12 < closestDistance)
                {
                    closestDistance = h12;
                    closestIndex = 12;
                }
            }
            if(exists13 && dist4 + cost13 + 10 < dist13)
            {
                dist13 = dist4 + cost13 + 10;
                lookup[indexToLocalY(13)][indexToLocalX(13)] = Direction.NORTH;
                if(h13 < closestDistance)
                {
                    closestDistance = h13;
                    closestIndex = 13;
                }
            }
            if(exists14 && dist4 + cost14 + 14 < dist14)
            {
                dist14 = dist4 + cost14 + 14;
                lookup[indexToLocalY(14)][indexToLocalX(14)] = Direction.NORTHWEST;
                if(h14 < closestDistance)
                {
                    closestDistance = h14;
                    closestIndex = 14;
                }
            }
        }
        if(exists27)
        {
            if(exists18 && dist27 + cost18 + 10 < dist18)
            {
                dist18 = dist27 + cost18 + 10;
                lookup[indexToLocalY(18)][indexToLocalX(18)] = Direction.SOUTH;
                if(h18 < closestDistance)
                {
                    closestDistance = h18;
                    closestIndex = 18;
                }
            }
            if(exists19 && dist27 + cost19 + 14 < dist19)
            {
                dist19 = dist27 + cost19 + 14;
                lookup[indexToLocalY(19)][indexToLocalX(19)] = Direction.SOUTHWEST;
                if(h19 < closestDistance)
                {
                    closestDistance = h19;
                    closestIndex = 19;
                }
            }
            if(exists28 && dist27 + cost28 + 10 < dist28)
            {
                dist28 = dist27 + cost28 + 10;
                lookup[indexToLocalY(28)][indexToLocalX(28)] = Direction.WEST;
                if(h28 < closestDistance)
                {
                    closestDistance = h28;
                    closestIndex = 28;
                }
            }
            if(exists36 && dist27 + cost36 + 10 < dist36)
            {
                dist36 = dist27 + cost36 + 10;
                lookup[indexToLocalY(36)][indexToLocalX(36)] = Direction.NORTH;
                if(h36 < closestDistance)
                {
                    closestDistance = h36;
                    closestIndex = 36;
                }
            }
            if(exists37 && dist27 + cost37 + 14 < dist37)
            {
                dist37 = dist27 + cost37 + 14;
                lookup[indexToLocalY(37)][indexToLocalX(37)] = Direction.NORTHWEST;
                if(h37 < closestDistance)
                {
                    closestDistance = h37;
                    closestIndex = 37;
                }
            }
        }
        if(exists36)
        {
            if(exists27 && dist36 + cost27 + 10 < dist27)
            {
                dist27 = dist36 + cost27 + 10;
                lookup[indexToLocalY(27)][indexToLocalX(27)] = Direction.SOUTH;
                if(h27 < closestDistance)
                {
                    closestDistance = h27;
                    closestIndex = 27;
                }
            }
            if(exists28 && dist36 + cost28 + 14 < dist28)
            {
                dist28 = dist36 + cost28 + 14;
                lookup[indexToLocalY(28)][indexToLocalX(28)] = Direction.SOUTHWEST;
                if(h28 < closestDistance)
                {
                    closestDistance = h28;
                    closestIndex = 28;
                }
            }
            if(exists37 && dist36 + cost37 + 10 < dist37)
            {
                dist37 = dist36 + cost37 + 10;
                lookup[indexToLocalY(37)][indexToLocalX(37)] = Direction.WEST;
                if(h37 < closestDistance)
                {
                    closestDistance = h37;
                    closestIndex = 37;
                }
            }
            if(exists45 && dist36 + cost45 + 10 < dist45)
            {
                dist45 = dist36 + cost45 + 10;
                lookup[indexToLocalY(45)][indexToLocalX(45)] = Direction.NORTH;
                if(h45 < closestDistance)
                {
                    closestDistance = h45;
                    closestIndex = 45;
                }
            }
            if(exists46 && dist36 + cost46 + 14 < dist46)
            {
                dist46 = dist36 + cost46 + 14;
                lookup[indexToLocalY(46)][indexToLocalX(46)] = Direction.NORTHWEST;
                if(h46 < closestDistance)
                {
                    closestDistance = h46;
                    closestIndex = 46;
                }
            }
        }
        if(exists5)
        {
            if(exists4 && dist5 + cost4 + 10 < dist4)
            {
                dist4 = dist5 + cost4 + 10;
                lookup[indexToLocalY(4)][indexToLocalX(4)] = Direction.EAST;
                if(h4 < closestDistance)
                {
                    closestDistance = h4;
                    closestIndex = 4;
                }
            }
            if(exists6 && dist5 + cost6 + 10 < dist6)
            {
                dist6 = dist5 + cost6 + 10;
                lookup[indexToLocalY(6)][indexToLocalX(6)] = Direction.WEST;
                if(h6 < closestDistance)
                {
                    closestDistance = h6;
                    closestIndex = 6;
                }
            }
            if(exists13 && dist5 + cost13 + 14 < dist13)
            {
                dist13 = dist5 + cost13 + 14;
                lookup[indexToLocalY(13)][indexToLocalX(13)] = Direction.NORTHEAST;
                if(h13 < closestDistance)
                {
                    closestDistance = h13;
                    closestIndex = 13;
                }
            }
            if(exists14 && dist5 + cost14 + 10 < dist14)
            {
                dist14 = dist5 + cost14 + 10;
                lookup[indexToLocalY(14)][indexToLocalX(14)] = Direction.NORTH;
                if(h14 < closestDistance)
                {
                    closestDistance = h14;
                    closestIndex = 14;
                }
            }
            if(exists15 && dist5 + cost15 + 14 < dist15)
            {
                dist15 = dist5 + cost15 + 14;
                lookup[indexToLocalY(15)][indexToLocalX(15)] = Direction.NORTHWEST;
                if(h15 < closestDistance)
                {
                    closestDistance = h15;
                    closestIndex = 15;
                }
            }
        }
        if(exists6)
        {
            if(exists5 && dist6 + cost5 + 10 < dist5)
            {
                dist5 = dist6 + cost5 + 10;
                lookup[indexToLocalY(5)][indexToLocalX(5)] = Direction.EAST;
                if(h5 < closestDistance)
                {
                    closestDistance = h5;
                    closestIndex = 5;
                }
            }
            if(exists14 && dist6 + cost14 + 14 < dist14)
            {
                dist14 = dist6 + cost14 + 14;
                lookup[indexToLocalY(14)][indexToLocalX(14)] = Direction.NORTHEAST;
                if(h14 < closestDistance)
                {
                    closestDistance = h14;
                    closestIndex = 14;
                }
            }
            if(exists15 && dist6 + cost15 + 10 < dist15)
            {
                dist15 = dist6 + cost15 + 10;
                lookup[indexToLocalY(15)][indexToLocalX(15)] = Direction.NORTH;
                if(h15 < closestDistance)
                {
                    closestDistance = h15;
                    closestIndex = 15;
                }
            }
            if(exists16 && dist6 + cost16 + 14 < dist16)
            {
                dist16 = dist6 + cost16 + 14;
                lookup[indexToLocalY(16)][indexToLocalX(16)] = Direction.NORTHWEST;
                if(h16 < closestDistance)
                {
                    closestDistance = h16;
                    closestIndex = 16;
                }
            }
        }
        if(exists45)
        {
            if(exists36 && dist45 + cost36 + 10 < dist36)
            {
                dist36 = dist45 + cost36 + 10;
                lookup[indexToLocalY(36)][indexToLocalX(36)] = Direction.SOUTH;
                if(h36 < closestDistance)
                {
                    closestDistance = h36;
                    closestIndex = 36;
                }
            }
            if(exists37 && dist45 + cost37 + 14 < dist37)
            {
                dist37 = dist45 + cost37 + 14;
                lookup[indexToLocalY(37)][indexToLocalX(37)] = Direction.SOUTHWEST;
                if(h37 < closestDistance)
                {
                    closestDistance = h37;
                    closestIndex = 37;
                }
            }
            if(exists46 && dist45 + cost46 + 10 < dist46)
            {
                dist46 = dist45 + cost46 + 10;
                lookup[indexToLocalY(46)][indexToLocalX(46)] = Direction.WEST;
                if(h46 < closestDistance)
                {
                    closestDistance = h46;
                    closestIndex = 46;
                }
            }
            if(exists54 && dist45 + cost54 + 10 < dist54)
            {
                dist54 = dist45 + cost54 + 10;
                lookup[indexToLocalY(54)][indexToLocalX(54)] = Direction.NORTH;
                if(h54 < closestDistance)
                {
                    closestDistance = h54;
                    closestIndex = 54;
                }
            }
            if(exists55 && dist45 + cost55 + 14 < dist55)
            {
                dist55 = dist45 + cost55 + 14;
                lookup[indexToLocalY(55)][indexToLocalX(55)] = Direction.NORTHWEST;
                if(h55 < closestDistance)
                {
                    closestDistance = h55;
                    closestIndex = 55;
                }
            }
        }
        if(exists54)
        {
            if(exists45 && dist54 + cost45 + 10 < dist45)
            {
                dist45 = dist54 + cost45 + 10;
                lookup[indexToLocalY(45)][indexToLocalX(45)] = Direction.SOUTH;
                if(h45 < closestDistance)
                {
                    closestDistance = h45;
                    closestIndex = 45;
                }
            }
            if(exists46 && dist54 + cost46 + 14 < dist46)
            {
                dist46 = dist54 + cost46 + 14;
                lookup[indexToLocalY(46)][indexToLocalX(46)] = Direction.SOUTHWEST;
                if(h46 < closestDistance)
                {
                    closestDistance = h46;
                    closestIndex = 46;
                }
            }
            if(exists55 && dist54 + cost55 + 10 < dist55)
            {
                dist55 = dist54 + cost55 + 10;
                lookup[indexToLocalY(55)][indexToLocalX(55)] = Direction.WEST;
                if(h55 < closestDistance)
                {
                    closestDistance = h55;
                    closestIndex = 55;
                }
            }
            if(exists64 && dist54 + cost64 + 14 < dist64)
            {
                dist64 = dist54 + cost64 + 14;
                lookup[indexToLocalY(64)][indexToLocalX(64)] = Direction.NORTHWEST;
                if(h64 < closestDistance)
                {
                    closestDistance = h64;
                    closestIndex = 64;
                }
            }
        }
        if(exists26)
        {
            if(exists16 && dist26 + cost16 + 14 < dist16)
            {
                dist16 = dist26 + cost16 + 14;
                lookup[indexToLocalY(16)][indexToLocalX(16)] = Direction.SOUTHEAST;
                if(h16 < closestDistance)
                {
                    closestDistance = h16;
                    closestIndex = 16;
                }
            }
            if(exists25 && dist26 + cost25 + 10 < dist25)
            {
                dist25 = dist26 + cost25 + 10;
                lookup[indexToLocalY(25)][indexToLocalX(25)] = Direction.EAST;
                if(h25 < closestDistance)
                {
                    closestDistance = h25;
                    closestIndex = 25;
                }
            }
            if(exists34 && dist26 + cost34 + 14 < dist34)
            {
                dist34 = dist26 + cost34 + 14;
                lookup[indexToLocalY(34)][indexToLocalX(34)] = Direction.NORTHEAST;
                if(h34 < closestDistance)
                {
                    closestDistance = h34;
                    closestIndex = 34;
                }
            }
            if(exists35 && dist26 + cost35 + 10 < dist35)
            {
                dist35 = dist26 + cost35 + 10;
                lookup[indexToLocalY(35)][indexToLocalX(35)] = Direction.NORTH;
                if(h35 < closestDistance)
                {
                    closestDistance = h35;
                    closestIndex = 35;
                }
            }
        }
        if(exists35)
        {
            if(exists25 && dist35 + cost25 + 14 < dist25)
            {
                dist25 = dist35 + cost25 + 14;
                lookup[indexToLocalY(25)][indexToLocalX(25)] = Direction.SOUTHEAST;
                if(h25 < closestDistance)
                {
                    closestDistance = h25;
                    closestIndex = 25;
                }
            }
            if(exists26 && dist35 + cost26 + 10 < dist26)
            {
                dist26 = dist35 + cost26 + 10;
                lookup[indexToLocalY(26)][indexToLocalX(26)] = Direction.SOUTH;
                if(h26 < closestDistance)
                {
                    closestDistance = h26;
                    closestIndex = 26;
                }
            }
            if(exists34 && dist35 + cost34 + 10 < dist34)
            {
                dist34 = dist35 + cost34 + 10;
                lookup[indexToLocalY(34)][indexToLocalX(34)] = Direction.EAST;
                if(h34 < closestDistance)
                {
                    closestDistance = h34;
                    closestIndex = 34;
                }
            }
            if(exists43 && dist35 + cost43 + 14 < dist43)
            {
                dist43 = dist35 + cost43 + 14;
                lookup[indexToLocalY(43)][indexToLocalX(43)] = Direction.NORTHEAST;
                if(h43 < closestDistance)
                {
                    closestDistance = h43;
                    closestIndex = 43;
                }
            }
            if(exists44 && dist35 + cost44 + 10 < dist44)
            {
                dist44 = dist35 + cost44 + 10;
                lookup[indexToLocalY(44)][indexToLocalX(44)] = Direction.NORTH;
                if(h44 < closestDistance)
                {
                    closestDistance = h44;
                    closestIndex = 44;
                }
            }
        }
        if(exists44)
        {
            if(exists34 && dist44 + cost34 + 14 < dist34)
            {
                dist34 = dist44 + cost34 + 14;
                lookup[indexToLocalY(34)][indexToLocalX(34)] = Direction.SOUTHEAST;
                if(h34 < closestDistance)
                {
                    closestDistance = h34;
                    closestIndex = 34;
                }
            }
            if(exists35 && dist44 + cost35 + 10 < dist35)
            {
                dist35 = dist44 + cost35 + 10;
                lookup[indexToLocalY(35)][indexToLocalX(35)] = Direction.SOUTH;
                if(h35 < closestDistance)
                {
                    closestDistance = h35;
                    closestIndex = 35;
                }
            }
            if(exists43 && dist44 + cost43 + 10 < dist43)
            {
                dist43 = dist44 + cost43 + 10;
                lookup[indexToLocalY(43)][indexToLocalX(43)] = Direction.EAST;
                if(h43 < closestDistance)
                {
                    closestDistance = h43;
                    closestIndex = 43;
                }
            }
            if(exists52 && dist44 + cost52 + 14 < dist52)
            {
                dist52 = dist44 + cost52 + 14;
                lookup[indexToLocalY(52)][indexToLocalX(52)] = Direction.NORTHEAST;
                if(h52 < closestDistance)
                {
                    closestDistance = h52;
                    closestIndex = 52;
                }
            }
            if(exists53 && dist44 + cost53 + 10 < dist53)
            {
                dist53 = dist44 + cost53 + 10;
                lookup[indexToLocalY(53)][indexToLocalX(53)] = Direction.NORTH;
                if(h53 < closestDistance)
                {
                    closestDistance = h53;
                    closestIndex = 53;
                }
            }
        }
        if(exists53)
        {
            if(exists43 && dist53 + cost43 + 14 < dist43)
            {
                dist43 = dist53 + cost43 + 14;
                lookup[indexToLocalY(43)][indexToLocalX(43)] = Direction.SOUTHEAST;
                if(h43 < closestDistance)
                {
                    closestDistance = h43;
                    closestIndex = 43;
                }
            }
            if(exists44 && dist53 + cost44 + 10 < dist44)
            {
                dist44 = dist53 + cost44 + 10;
                lookup[indexToLocalY(44)][indexToLocalX(44)] = Direction.SOUTH;
                if(h44 < closestDistance)
                {
                    closestDistance = h44;
                    closestIndex = 44;
                }
            }
            if(exists52 && dist53 + cost52 + 10 < dist52)
            {
                dist52 = dist53 + cost52 + 10;
                lookup[indexToLocalY(52)][indexToLocalX(52)] = Direction.EAST;
                if(h52 < closestDistance)
                {
                    closestDistance = h52;
                    closestIndex = 52;
                }
            }
            if(exists61 && dist53 + cost61 + 14 < dist61)
            {
                dist61 = dist53 + cost61 + 14;
                lookup[indexToLocalY(61)][indexToLocalX(61)] = Direction.NORTHEAST;
                if(h61 < closestDistance)
                {
                    closestDistance = h61;
                    closestIndex = 61;
                }
            }
            if(exists62 && dist53 + cost62 + 10 < dist62)
            {
                dist62 = dist53 + cost62 + 10;
                lookup[indexToLocalY(62)][indexToLocalX(62)] = Direction.NORTH;
                if(h62 < closestDistance)
                {
                    closestDistance = h62;
                    closestIndex = 62;
                }
            }
        }
        if(exists62)
        {
            if(exists52 && dist62 + cost52 + 14 < dist52)
            {
                dist52 = dist62 + cost52 + 14;
                lookup[indexToLocalY(52)][indexToLocalX(52)] = Direction.SOUTHEAST;
                if(h52 < closestDistance)
                {
                    closestDistance = h52;
                    closestIndex = 52;
                }
            }
            if(exists53 && dist62 + cost53 + 10 < dist53)
            {
                dist53 = dist62 + cost53 + 10;
                lookup[indexToLocalY(53)][indexToLocalX(53)] = Direction.SOUTH;
                if(h53 < closestDistance)
                {
                    closestDistance = h53;
                    closestIndex = 53;
                }
            }
            if(exists61 && dist62 + cost61 + 10 < dist61)
            {
                dist61 = dist62 + cost61 + 10;
                lookup[indexToLocalY(61)][indexToLocalX(61)] = Direction.EAST;
                if(h61 < closestDistance)
                {
                    closestDistance = h61;
                    closestIndex = 61;
                }
            }
            if(exists70 && dist62 + cost70 + 14 < dist70)
            {
                dist70 = dist62 + cost70 + 14;
                lookup[indexToLocalY(70)][indexToLocalX(70)] = Direction.NORTHEAST;
                if(h70 < closestDistance)
                {
                    closestDistance = h70;
                    closestIndex = 70;
                }
            }
        }
        if(exists74)
        {
            if(exists64 && dist74 + cost64 + 14 < dist64)
            {
                dist64 = dist74 + cost64 + 14;
                lookup[indexToLocalY(64)][indexToLocalX(64)] = Direction.SOUTHEAST;
                if(h64 < closestDistance)
                {
                    closestDistance = h64;
                    closestIndex = 64;
                }
            }
            if(exists65 && dist74 + cost65 + 10 < dist65)
            {
                dist65 = dist74 + cost65 + 10;
                lookup[indexToLocalY(65)][indexToLocalX(65)] = Direction.SOUTH;
                if(h65 < closestDistance)
                {
                    closestDistance = h65;
                    closestIndex = 65;
                }
            }
            if(exists66 && dist74 + cost66 + 14 < dist66)
            {
                dist66 = dist74 + cost66 + 14;
                lookup[indexToLocalY(66)][indexToLocalX(66)] = Direction.SOUTHWEST;
                if(h66 < closestDistance)
                {
                    closestDistance = h66;
                    closestIndex = 66;
                }
            }
            if(exists75 && dist74 + cost75 + 10 < dist75)
            {
                dist75 = dist74 + cost75 + 10;
                lookup[indexToLocalY(75)][indexToLocalX(75)] = Direction.WEST;
                if(h75 < closestDistance)
                {
                    closestDistance = h75;
                    closestIndex = 75;
                }
            }
        }
        if(exists75)
        {
            if(exists65 && dist75 + cost65 + 14 < dist65)
            {
                dist65 = dist75 + cost65 + 14;
                lookup[indexToLocalY(65)][indexToLocalX(65)] = Direction.SOUTHEAST;
                if(h65 < closestDistance)
                {
                    closestDistance = h65;
                    closestIndex = 65;
                }
            }
            if(exists66 && dist75 + cost66 + 10 < dist66)
            {
                dist66 = dist75 + cost66 + 10;
                lookup[indexToLocalY(66)][indexToLocalX(66)] = Direction.SOUTH;
                if(h66 < closestDistance)
                {
                    closestDistance = h66;
                    closestIndex = 66;
                }
            }
            if(exists67 && dist75 + cost67 + 14 < dist67)
            {
                dist67 = dist75 + cost67 + 14;
                lookup[indexToLocalY(67)][indexToLocalX(67)] = Direction.SOUTHWEST;
                if(h67 < closestDistance)
                {
                    closestDistance = h67;
                    closestIndex = 67;
                }
            }
            if(exists74 && dist75 + cost74 + 10 < dist74)
            {
                dist74 = dist75 + cost74 + 10;
                lookup[indexToLocalY(74)][indexToLocalX(74)] = Direction.EAST;
                if(h74 < closestDistance)
                {
                    closestDistance = h74;
                    closestIndex = 74;
                }
            }
            if(exists76 && dist75 + cost76 + 10 < dist76)
            {
                dist76 = dist75 + cost76 + 10;
                lookup[indexToLocalY(76)][indexToLocalX(76)] = Direction.WEST;
                if(h76 < closestDistance)
                {
                    closestDistance = h76;
                    closestIndex = 76;
                }
            }
        }
        if(exists76)
        {
            if(exists66 && dist76 + cost66 + 14 < dist66)
            {
                dist66 = dist76 + cost66 + 14;
                lookup[indexToLocalY(66)][indexToLocalX(66)] = Direction.SOUTHEAST;
                if(h66 < closestDistance)
                {
                    closestDistance = h66;
                    closestIndex = 66;
                }
            }
            if(exists67 && dist76 + cost67 + 10 < dist67)
            {
                dist67 = dist76 + cost67 + 10;
                lookup[indexToLocalY(67)][indexToLocalX(67)] = Direction.SOUTH;
                if(h67 < closestDistance)
                {
                    closestDistance = h67;
                    closestIndex = 67;
                }
            }
            if(exists68 && dist76 + cost68 + 14 < dist68)
            {
                dist68 = dist76 + cost68 + 14;
                lookup[indexToLocalY(68)][indexToLocalX(68)] = Direction.SOUTHWEST;
                if(h68 < closestDistance)
                {
                    closestDistance = h68;
                    closestIndex = 68;
                }
            }
            if(exists75 && dist76 + cost75 + 10 < dist75)
            {
                dist75 = dist76 + cost75 + 10;
                lookup[indexToLocalY(75)][indexToLocalX(75)] = Direction.EAST;
                if(h75 < closestDistance)
                {
                    closestDistance = h75;
                    closestIndex = 75;
                }
            }
            if(exists77 && dist76 + cost77 + 10 < dist77)
            {
                dist77 = dist76 + cost77 + 10;
                lookup[indexToLocalY(77)][indexToLocalX(77)] = Direction.WEST;
                if(h77 < closestDistance)
                {
                    closestDistance = h77;
                    closestIndex = 77;
                }
            }
        }
        if(exists77)
        {
            if(exists67 && dist77 + cost67 + 14 < dist67)
            {
                dist67 = dist77 + cost67 + 14;
                lookup[indexToLocalY(67)][indexToLocalX(67)] = Direction.SOUTHEAST;
                if(h67 < closestDistance)
                {
                    closestDistance = h67;
                    closestIndex = 67;
                }
            }
            if(exists68 && dist77 + cost68 + 10 < dist68)
            {
                dist68 = dist77 + cost68 + 10;
                lookup[indexToLocalY(68)][indexToLocalX(68)] = Direction.SOUTH;
                if(h68 < closestDistance)
                {
                    closestDistance = h68;
                    closestIndex = 68;
                }
            }
            if(exists69 && dist77 + cost69 + 14 < dist69)
            {
                dist69 = dist77 + cost69 + 14;
                lookup[indexToLocalY(69)][indexToLocalX(69)] = Direction.SOUTHWEST;
                if(h69 < closestDistance)
                {
                    closestDistance = h69;
                    closestIndex = 69;
                }
            }
            if(exists76 && dist77 + cost76 + 10 < dist76)
            {
                dist76 = dist77 + cost76 + 10;
                lookup[indexToLocalY(76)][indexToLocalX(76)] = Direction.EAST;
                if(h76 < closestDistance)
                {
                    closestDistance = h76;
                    closestIndex = 76;
                }
            }
            if(exists78 && dist77 + cost78 + 10 < dist78)
            {
                dist78 = dist77 + cost78 + 10;
                lookup[indexToLocalY(78)][indexToLocalX(78)] = Direction.WEST;
                if(h78 < closestDistance)
                {
                    closestDistance = h78;
                    closestIndex = 78;
                }
            }
        }
        if(exists78)
        {
            if(exists68 && dist78 + cost68 + 14 < dist68)
            {
                dist68 = dist78 + cost68 + 14;
                lookup[indexToLocalY(68)][indexToLocalX(68)] = Direction.SOUTHEAST;
                if(h68 < closestDistance)
                {
                    closestDistance = h68;
                    closestIndex = 68;
                }
            }
            if(exists69 && dist78 + cost69 + 10 < dist69)
            {
                dist69 = dist78 + cost69 + 10;
                lookup[indexToLocalY(69)][indexToLocalX(69)] = Direction.SOUTH;
                if(h69 < closestDistance)
                {
                    closestDistance = h69;
                    closestIndex = 69;
                }
            }
            if(exists70 && dist78 + cost70 + 14 < dist70)
            {
                dist70 = dist78 + cost70 + 14;
                lookup[indexToLocalY(70)][indexToLocalX(70)] = Direction.SOUTHWEST;
                if(h70 < closestDistance)
                {
                    closestDistance = h70;
                    closestIndex = 70;
                }
            }
            if(exists77 && dist78 + cost77 + 10 < dist77)
            {
                dist77 = dist78 + cost77 + 10;
                lookup[indexToLocalY(77)][indexToLocalX(77)] = Direction.EAST;
                if(h77 < closestDistance)
                {
                    closestDistance = h77;
                    closestIndex = 77;
                }
            }
        }

        return closestIndex != -1 ? dirToMove(closestIndex) : Direction.CENTER;
    }
}
