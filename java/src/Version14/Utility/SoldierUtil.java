package Version14.Utility;

import battlecode.common.*;

import static Version14.RobotPlayer.*;
import static Version14.Robots.Soldier.*;
import static Version14.Utility.Symmetry.processTile;

public class SoldierUtil {
    static boolean[][] pattern;
    static MapLocation ruin;

//    //checks whether we should still "claim" this ruin
//    //if there is an obstacle stopping us from completing it, then we should abandon t
//    public static void validateRuinClaim() throws GameActionException {
//        if(staticRC.canSenseRobotAtLocation(claimedRuin)) {
//            claimedRuin = null;
//            return;
//        }
//        if (staticRC.getLocation().distanceSquaredTo(claimedRuin) > 8) {
//            return;
//        }
////        for(MapInfo tile : tilesNearRuin) {
////            if(tile.getPaint().isEnemy()) {
////                claimedRuin = null;
////                return;
////            }
////        }
//        switch (tilesNearRuin.length) {
//            case 1 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 2 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 3 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 4 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 5 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 6 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 7 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 8 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 9 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 10 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[9].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 11 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[9].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[10].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 12 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[9].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[10].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[11].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 13 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[9].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[10].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[11].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[12].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 14 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[9].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[10].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[11].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[12].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[13].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 15 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[9].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[10].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[11].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[12].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[13].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[14].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 16 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[9].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[10].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[11].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[12].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[13].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[14].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[15].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 17 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[9].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[10].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[11].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[12].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[13].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[14].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[15].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[16].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 18 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[9].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[10].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[11].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[12].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[13].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[14].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[15].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[16].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[17].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 19 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[9].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[10].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[11].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[12].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[13].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[14].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[15].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[16].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[17].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[18].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 20 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[9].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[10].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[11].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[12].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[13].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[14].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[15].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[16].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[17].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[18].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[19].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 21 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[9].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[10].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[11].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[12].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[13].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[14].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[15].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[16].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[17].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[18].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[19].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[20].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 22 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[9].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[10].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[11].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[12].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[13].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[14].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[15].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[16].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[17].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[18].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[19].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[20].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[21].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 23 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[9].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[10].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[11].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[12].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[13].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[14].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[15].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[16].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[17].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[18].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[19].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[20].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[21].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[22].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 24 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[9].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[10].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[11].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[12].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[13].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[14].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[15].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[16].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[17].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[18].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[19].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[20].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[21].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[22].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[23].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//            case 25 -> {
//                if (tilesNearRuin[0].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[1].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[2].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[3].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[4].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[5].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[6].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[7].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[8].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[9].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[10].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[11].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[12].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[13].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[14].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[15].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[16].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[17].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[18].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[19].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[20].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[21].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[22].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[23].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//                if (tilesNearRuin[24].getPaint().isEnemy()) {
//                    claimedRuin = null;
//                    return;
//                }
//            }
//
//        }
//    }

    public static boolean check(int index) {
        if (tilesNearRuin[index].getPaint() == PaintType.EMPTY || (pattern != null && tilesNearRuin[index].getPaint().isAlly() && Utilities.getColorFromCustomPattern(tilesNearRuin[index].getMapLocation(), pattern, ruin) != tilesNearRuin[index].getPaint().isSecondary())) {
            return true;
        }
        return false;
    }

    public static boolean needsHelp(MapLocation ruinInput) throws GameActionException {
        ruin = ruinInput;
        pattern = (curPattern != null) ? curPattern : Utilities.getPatternFromWeightedHash(ruinInput);
        boolean hasEmpty = false;
        for(MapInfo tile : tilesNearRuin) {
            PaintType paint = tile.getPaint();
            if(paint.isEnemy()) return false;
            if(!hasEmpty) if(paint == PaintType.EMPTY || paint.isSecondary() != Utilities.getColorFromCustomPattern(tile.getMapLocation(), pattern, ruin)) hasEmpty = true;
        }
        if(hasEmpty) return true;
        if (staticRC.senseNearbyRobots(ruin, 8, staticRC.getTeam()).length == 0) return true;
        return false;
    }

    public static void scanNearbyTilesSoldier() throws GameActionException {
        closestUnfilledPatternCenter = null;
        averageEnemyPaint = null;
        int enemyCount = 0;
        int x = 0;
        int y = 0;
        int resourcePatternDist = Integer.MAX_VALUE;
        int failedPlacementLocations = 0;
        int minDistanceToValidLocation = Integer.MAX_VALUE;
        MapLocation tileLoc;
        int dist;
        PaintType mark;
        if(nearbyTiles.length == 69) {
            Symmetry.processTile(nearbyTiles[0]);
            tileLoc = nearbyTiles[0].getMapLocation();
            if(nearbyTiles[0].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[0].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[0].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[0].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[0].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[0].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[0].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[0].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[1]);
            tileLoc = nearbyTiles[1].getMapLocation();
            if(nearbyTiles[1].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[1].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[1].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[1].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[1].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[1].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[1].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[1].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[2]);
            tileLoc = nearbyTiles[2].getMapLocation();
            if(nearbyTiles[2].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[2].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[2].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[2].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[2].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[2].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[2].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[2].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[3]);
            tileLoc = nearbyTiles[3].getMapLocation();
            if(nearbyTiles[3].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[3].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[3].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[3].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[3].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[3].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[3].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[3].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[4]);
            tileLoc = nearbyTiles[4].getMapLocation();
            if(nearbyTiles[4].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[4].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[4].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[4].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[4].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[4].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[4].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[4].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[5]);
            tileLoc = nearbyTiles[5].getMapLocation();
            if(nearbyTiles[5].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[5].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[5].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[5].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[5].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[5].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[5].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[5].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[6]);
            tileLoc = nearbyTiles[6].getMapLocation();
            if(nearbyTiles[6].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[6].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[6].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[6].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[6].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[6].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[6].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[6].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[7]);
            tileLoc = nearbyTiles[7].getMapLocation();
            if(nearbyTiles[7].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[7].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[7].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[7].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[7].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[7].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[7].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[7].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[8]);
            tileLoc = nearbyTiles[8].getMapLocation();
            if(nearbyTiles[8].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[8].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[8].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[8].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[8].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[8].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[8].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[8].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[9]);
            tileLoc = nearbyTiles[9].getMapLocation();
            if(nearbyTiles[9].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[9].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[9].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[9].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[9].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[9].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[9].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[9].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[10]);
            tileLoc = nearbyTiles[10].getMapLocation();
            if(nearbyTiles[10].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[10].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[10].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[10].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[10].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[10].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[10].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[10].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[11]);
            tileLoc = nearbyTiles[11].getMapLocation();
            if(nearbyTiles[11].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[11].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[11].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[11].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[11].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[11].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[11].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[11].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[12]);
            tileLoc = nearbyTiles[12].getMapLocation();
            if(nearbyTiles[12].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[12].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[12].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[12].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[12].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[12].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[12].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[12].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[13]);
            tileLoc = nearbyTiles[13].getMapLocation();
            if(nearbyTiles[13].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[13].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[13].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[13].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[13].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[13].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[13].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[13].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[14]);
            tileLoc = nearbyTiles[14].getMapLocation();
            if(nearbyTiles[14].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[14].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[14].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[14].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[14].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[14].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[14].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[14].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[15]);
            tileLoc = nearbyTiles[15].getMapLocation();
            if(nearbyTiles[15].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[15].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[15].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[15].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[15].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[15].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[15].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[15].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[16]);
            tileLoc = nearbyTiles[16].getMapLocation();
            if(nearbyTiles[16].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[16].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[16].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[16].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[16].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[16].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[16].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[16].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[17]);
            tileLoc = nearbyTiles[17].getMapLocation();
            if(nearbyTiles[17].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[17].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[17].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[17].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[17].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[17].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[17].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[17].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[18]);
            tileLoc = nearbyTiles[18].getMapLocation();
            if(nearbyTiles[18].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[18].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[18].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[18].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[18].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[18].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[18].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[18].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[19]);
            tileLoc = nearbyTiles[19].getMapLocation();
            if(nearbyTiles[19].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[19].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[19].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[19].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[19].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[19].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[19].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[19].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[20]);
            tileLoc = nearbyTiles[20].getMapLocation();
            if(nearbyTiles[20].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[20].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[20].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[20].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[20].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[20].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[20].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[20].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[21]);
            tileLoc = nearbyTiles[21].getMapLocation();
            if(nearbyTiles[21].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[21].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[21].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[21].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[21].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[21].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[21].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[21].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[22]);
            tileLoc = nearbyTiles[22].getMapLocation();
            if(nearbyTiles[22].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[22].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[22].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[22].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[22].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[22].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[22].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[22].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[23]);
            tileLoc = nearbyTiles[23].getMapLocation();
            if(nearbyTiles[23].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[23].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[23].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[23].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[23].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[23].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[23].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[23].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[24]);
            tileLoc = nearbyTiles[24].getMapLocation();
            if(nearbyTiles[24].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[24].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[24].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[24].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[24].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[24].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[24].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[24].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[25]);
            tileLoc = nearbyTiles[25].getMapLocation();
            if(nearbyTiles[25].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[25].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[25].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[25].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[25].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[25].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[25].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[25].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[26]);
            tileLoc = nearbyTiles[26].getMapLocation();
            if(nearbyTiles[26].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[26].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[26].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[26].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[26].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[26].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[26].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[26].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[27]);
            tileLoc = nearbyTiles[27].getMapLocation();
            if(nearbyTiles[27].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[27].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[27].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[27].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[27].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[27].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[27].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[27].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[28]);
            tileLoc = nearbyTiles[28].getMapLocation();
            if(nearbyTiles[28].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[28].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[28].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[28].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[28].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[28].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[28].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[28].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[29]);
            tileLoc = nearbyTiles[29].getMapLocation();
            if(nearbyTiles[29].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[29].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[29].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[29].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[29].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[29].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[29].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[29].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[30]);
            tileLoc = nearbyTiles[30].getMapLocation();
            if(nearbyTiles[30].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[30].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[30].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[30].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[30].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[30].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[30].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[30].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[31]);
            tileLoc = nearbyTiles[31].getMapLocation();
            if(nearbyTiles[31].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[31].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[31].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[31].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[31].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[31].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[31].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[31].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[32]);
            tileLoc = nearbyTiles[32].getMapLocation();
            if(nearbyTiles[32].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[32].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[32].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[32].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[32].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[32].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[32].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[32].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[33]);
            tileLoc = nearbyTiles[33].getMapLocation();
            if(nearbyTiles[33].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[33].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[33].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[33].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[33].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[33].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[33].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[33].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[34]);
            tileLoc = nearbyTiles[34].getMapLocation();
            if(nearbyTiles[34].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[34].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[34].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[34].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[34].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[34].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[34].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[34].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[35]);
            tileLoc = nearbyTiles[35].getMapLocation();
            if(nearbyTiles[35].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[35].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[35].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[35].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[35].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[35].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[35].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[35].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[36]);
            tileLoc = nearbyTiles[36].getMapLocation();
            if(nearbyTiles[36].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[36].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[36].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[36].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[36].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[36].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[36].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[36].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[37]);
            tileLoc = nearbyTiles[37].getMapLocation();
            if(nearbyTiles[37].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[37].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[37].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[37].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[37].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[37].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[37].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[37].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[38]);
            tileLoc = nearbyTiles[38].getMapLocation();
            if(nearbyTiles[38].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[38].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[38].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[38].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[38].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[38].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[38].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[38].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[39]);
            tileLoc = nearbyTiles[39].getMapLocation();
            if(nearbyTiles[39].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[39].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[39].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[39].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[39].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[39].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[39].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[39].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[40]);
            tileLoc = nearbyTiles[40].getMapLocation();
            if(nearbyTiles[40].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[40].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[40].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[40].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[40].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[40].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[40].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[40].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[41]);
            tileLoc = nearbyTiles[41].getMapLocation();
            if(nearbyTiles[41].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[41].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[41].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[41].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[41].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[41].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[41].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[41].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[42]);
            tileLoc = nearbyTiles[42].getMapLocation();
            if(nearbyTiles[42].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[42].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[42].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[42].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[42].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[42].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[42].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[42].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[43]);
            tileLoc = nearbyTiles[43].getMapLocation();
            if(nearbyTiles[43].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[43].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[43].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[43].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[43].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[43].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[43].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[43].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[44]);
            tileLoc = nearbyTiles[44].getMapLocation();
            if(nearbyTiles[44].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[44].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[44].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[44].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[44].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[44].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[44].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[44].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[45]);
            tileLoc = nearbyTiles[45].getMapLocation();
            if(nearbyTiles[45].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[45].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[45].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[45].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[45].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[45].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[45].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[45].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[46]);
            tileLoc = nearbyTiles[46].getMapLocation();
            if(nearbyTiles[46].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[46].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[46].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[46].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[46].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[46].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[46].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[46].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[47]);
            tileLoc = nearbyTiles[47].getMapLocation();
            if(nearbyTiles[47].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[47].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[47].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[47].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[47].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[47].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[47].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[47].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[48]);
            tileLoc = nearbyTiles[48].getMapLocation();
            if(nearbyTiles[48].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[48].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[48].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[48].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[48].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[48].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[48].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[48].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[49]);
            tileLoc = nearbyTiles[49].getMapLocation();
            if(nearbyTiles[49].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[49].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[49].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[49].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[49].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[49].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[49].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[49].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[50]);
            tileLoc = nearbyTiles[50].getMapLocation();
            if(nearbyTiles[50].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[50].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[50].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[50].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[50].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[50].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[50].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[50].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[51]);
            tileLoc = nearbyTiles[51].getMapLocation();
            if(nearbyTiles[51].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[51].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[51].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[51].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[51].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[51].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[51].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[51].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[52]);
            tileLoc = nearbyTiles[52].getMapLocation();
            if(nearbyTiles[52].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[52].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[52].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[52].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[52].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[52].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[52].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[52].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[53]);
            tileLoc = nearbyTiles[53].getMapLocation();
            if(nearbyTiles[53].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[53].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[53].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[53].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[53].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[53].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[53].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[53].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[54]);
            tileLoc = nearbyTiles[54].getMapLocation();
            if(nearbyTiles[54].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[54].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[54].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[54].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[54].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[54].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[54].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[54].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[55]);
            tileLoc = nearbyTiles[55].getMapLocation();
            if(nearbyTiles[55].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[55].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[55].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[55].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[55].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[55].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[55].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[55].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[56]);
            tileLoc = nearbyTiles[56].getMapLocation();
            if(nearbyTiles[56].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[56].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[56].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[56].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[56].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[56].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[56].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[56].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[57]);
            tileLoc = nearbyTiles[57].getMapLocation();
            if(nearbyTiles[57].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[57].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[57].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[57].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[57].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[57].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[57].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[57].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[58]);
            tileLoc = nearbyTiles[58].getMapLocation();
            if(nearbyTiles[58].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[58].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[58].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[58].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[58].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[58].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[58].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[58].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[59]);
            tileLoc = nearbyTiles[59].getMapLocation();
            if(nearbyTiles[59].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[59].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[59].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[59].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[59].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[59].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[59].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[59].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[60]);
            tileLoc = nearbyTiles[60].getMapLocation();
            if(nearbyTiles[60].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[60].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[60].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[60].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[60].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[60].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[60].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[60].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[61]);
            tileLoc = nearbyTiles[61].getMapLocation();
            if(nearbyTiles[61].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[61].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[61].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[61].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[61].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[61].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[61].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[61].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[62]);
            tileLoc = nearbyTiles[62].getMapLocation();
            if(nearbyTiles[62].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[62].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[62].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[62].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[62].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[62].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[62].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[62].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[63]);
            tileLoc = nearbyTiles[63].getMapLocation();
            if(nearbyTiles[63].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[63].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[63].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[63].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[63].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[63].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[63].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[63].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[64]);
            tileLoc = nearbyTiles[64].getMapLocation();
            if(nearbyTiles[64].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[64].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[64].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[64].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[64].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[64].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[64].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[64].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[65]);
            tileLoc = nearbyTiles[65].getMapLocation();
            if(nearbyTiles[65].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[65].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[65].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[65].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[65].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[65].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[65].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[65].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[66]);
            tileLoc = nearbyTiles[66].getMapLocation();
            if(nearbyTiles[66].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[66].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[66].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[66].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[66].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[66].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[66].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[66].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[67]);
            tileLoc = nearbyTiles[67].getMapLocation();
            if(nearbyTiles[67].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[67].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[67].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[67].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[67].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[67].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[67].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[67].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }
            Symmetry.processTile(nearbyTiles[68]);
            tileLoc = nearbyTiles[68].getMapLocation();
            if(nearbyTiles[68].getPaint().isEnemy())
            {
                x += tileLoc.x;
                y += tileLoc.y;
                enemyCount++;
            }
            mark = nearbyTiles[68].getMark();
            dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
            if(mark.isAlly())
            {
                if (!nearbyTiles[68].isResourcePatternCenter() && dist < resourcePatternDist)
                {
                    closestUnfilledPatternCenter = tileLoc;
                    resourcePatternDist = dist;
                }
                if (dist < minDistanceToValidLocation)
                {
                    minDistanceToValidLocation = dist;
                }
                if(staticRC.canCompleteResourcePattern(nearbyTiles[68].getMapLocation()))
                {
                    staticRC.completeResourcePattern(nearbyTiles[68].getMapLocation());
                }
            }
            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0)
            {
                if(!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc))
                {
                    if(dist < minDistanceToValidLocation)
                    {
                        minDistanceToValidLocation = dist;
                    }
                    if(staticRC.canMark(nearbyTiles[68].getMapLocation()))
                    {
                        staticRC.mark(nearbyTiles[68].getMapLocation(), false);
                        closestUnfilledPatternCenter = nearbyTiles[68].getMapLocation();
                    }
                    else if (dist < resourcePatternDist)
                    {
                        closestUnfilledPatternCenter = tileLoc;
                        resourcePatternDist = dist;
                    }
                }
                else
                {
                    failedPlacementLocations++;
                }
            }

        }
        else {
            for (MapInfo tile : nearbyTiles) {
                processTile(tile);
                tileLoc = tile.getMapLocation();
                if (tile.getPaint().isEnemy()) {
                    x += tileLoc.x;
                    y += tileLoc.y;
                    enemyCount++;
                }
                mark = tile.getMark();
                dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                if (mark.isAlly()) {
                    if (!tile.isResourcePatternCenter() && dist < resourcePatternDist) {
                        resourcePatternDist = dist;
                        closestUnfilledPatternCenter = tileLoc;
                    }
                    if (dist < minDistanceToValidLocation) {
                        minDistanceToValidLocation = dist;

                    }
                    if (staticRC.canCompleteResourcePattern(tile.getMapLocation()))
                        staticRC.completeResourcePattern(tile.getMapLocation());
                } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                    if (!invalidResourceCenters.contains(tileLoc) && validatePlacement(tileLoc)) {
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canMark(tile.getMapLocation())) {
                            staticRC.mark(tile.getMapLocation(), false);
                            closestUnfilledPatternCenter = tile.getMapLocation();
                        } else if (dist < resourcePatternDist) {
                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                    } else {
                        failedPlacementLocations++;
                    }
                }
            }
        }
        if (failedPlacementLocations >= 4 && minDistanceToValidLocation >= 25) {
            if (validatePlacement(staticRC.getLocation())) {
                if (staticRC.canMark(staticRC.getLocation())) {
                    staticRC.mark(staticRC.getLocation(), true);
                    closestUnfilledPatternCenter = staticRC.getLocation();
                }
            }
        }
        numEnemyTiles = enemyCount;
        averageEnemyPaint = (enemyCount != 0) ? new MapLocation(x / enemyCount, y / enemyCount) : null;
//        if (claimedRuin != null && staticRC.canSenseLocation(claimedRuin) && staticRC.canSenseRobotAtLocation(claimedRuin)) {
//            claimedRuin = null;
//        }
    }
}