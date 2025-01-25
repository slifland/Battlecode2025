package Version13.Utility;

import Version13.RobotPlayer;
import battlecode.common.*;

import static Version13.RobotPlayer.*;
import static Version13.Robots.Soldier.*;

public class SoldierUtil {
    static boolean[][] pattern;
    static MapLocation ruin;

    //checks whether we should still "claim" this ruin
    //if there is an obstacle stopping us from completing it, then we should abandon t
    public static void validateRuinClaim() throws GameActionException {
        if (staticRC.getLocation().distanceSquaredTo(claimedRuin) > 8) {
            return;
        }
//        for(MapInfo tile : tilesNearRuin) {
//            if(tile.getPaint().isEnemy()) {
//                claimedRuin = null;
//                return;
//            }
//        }
        switch (tilesNearRuin.length) {
            case 1 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 2 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 3 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 4 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 5 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 6 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 7 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 8 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 9 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 10 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 11 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 12 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 13 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 14 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 15 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 16 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 17 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 18 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[17].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 19 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[17].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[18].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 20 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[17].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[18].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[19].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 21 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[17].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[18].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[19].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[20].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 22 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[17].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[18].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[19].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[20].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[21].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 23 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[17].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[18].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[19].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[20].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[21].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[22].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 24 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[17].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[18].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[19].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[20].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[21].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[22].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[23].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }
            case 25 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[17].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[18].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[19].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[20].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[21].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[22].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[23].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
                if (tilesNearRuin[24].getPaint().isEnemy()) {
                    claimedRuin = null;
                    return;
                }
            }

        }
    }

    public static boolean check(int index) {
        if (tilesNearRuin[index].getPaint() == PaintType.EMPTY || (pattern != null && tilesNearRuin[index].getPaint().isAlly() && Utilities.getColorFromCustomPattern(tilesNearRuin[index].getMapLocation(), pattern, ruin) != tilesNearRuin[index].getPaint().isSecondary())) {
            neededToFinish++;
            return true;
        }
        return false;
    }

    public static boolean needsHelp(MapLocation ruinInput) throws GameActionException {
        ruin = ruinInput;
        tilesNearRuin = staticRC.senseNearbyMapInfos(ruin, 8);
        neededToFinish = 0;
        canFinishRuin = false;
        boolean hasEmpty = false;
        pattern = (curPattern == null) ? Utilities.inferPatternFromExistingSpots(ruin, tilesNearRuin) : curPattern;
        curPattern = pattern;
        switch (tilesNearRuin.length) {
            case 1 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
            }
            case 2 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
            }
            case 3 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
            }
            case 4 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
            }
            case 5 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
            }
            case 6 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
            }
            case 7 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
            }
            case 8 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
            }
            case 9 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
            }
            case 10 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
                if (tilesNearRuin[9].isPassable()) {
                    if (tilesNearRuin[9].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(9);
                    }
                }
            }
            case 11 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
                if (tilesNearRuin[9].isPassable()) {
                    if (tilesNearRuin[9].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(9);
                    }
                }
                if (tilesNearRuin[10].isPassable()) {
                    if (tilesNearRuin[10].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(10);
                    }
                }
            }
            case 12 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
                if (tilesNearRuin[9].isPassable()) {
                    if (tilesNearRuin[9].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(9);
                    }
                }
                if (tilesNearRuin[10].isPassable()) {
                    if (tilesNearRuin[10].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(10);
                    }
                }
                if (tilesNearRuin[11].isPassable()) {
                    if (tilesNearRuin[11].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(11);
                    }
                }
            }
            case 13 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
                if (tilesNearRuin[9].isPassable()) {
                    if (tilesNearRuin[9].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(9);
                    }
                }
                if (tilesNearRuin[10].isPassable()) {
                    if (tilesNearRuin[10].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(10);
                    }
                }
                if (tilesNearRuin[11].isPassable()) {
                    if (tilesNearRuin[11].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(11);
                    }
                }
                if (tilesNearRuin[12].isPassable()) {
                    if (tilesNearRuin[12].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(12);
                    }
                }
            }
            case 14 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
                if (tilesNearRuin[9].isPassable()) {
                    if (tilesNearRuin[9].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(9);
                    }
                }
                if (tilesNearRuin[10].isPassable()) {
                    if (tilesNearRuin[10].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(10);
                    }
                }
                if (tilesNearRuin[11].isPassable()) {
                    if (tilesNearRuin[11].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(11);
                    }
                }
                if (tilesNearRuin[12].isPassable()) {
                    if (tilesNearRuin[12].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(12);
                    }
                }
                if (tilesNearRuin[13].isPassable()) {
                    if (tilesNearRuin[13].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(13);
                    }
                }
            }
            case 15 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
                if (tilesNearRuin[9].isPassable()) {
                    if (tilesNearRuin[9].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(9);
                    }
                }
                if (tilesNearRuin[10].isPassable()) {
                    if (tilesNearRuin[10].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(10);
                    }
                }
                if (tilesNearRuin[11].isPassable()) {
                    if (tilesNearRuin[11].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(11);
                    }
                }
                if (tilesNearRuin[12].isPassable()) {
                    if (tilesNearRuin[12].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(12);
                    }
                }
                if (tilesNearRuin[13].isPassable()) {
                    if (tilesNearRuin[13].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(13);
                    }
                }
                if (tilesNearRuin[14].isPassable()) {
                    if (tilesNearRuin[14].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(14);
                    }
                }
            }
            case 16 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
                if (tilesNearRuin[9].isPassable()) {
                    if (tilesNearRuin[9].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(9);
                    }
                }
                if (tilesNearRuin[10].isPassable()) {
                    if (tilesNearRuin[10].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(10);
                    }
                }
                if (tilesNearRuin[11].isPassable()) {
                    if (tilesNearRuin[11].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(11);
                    }
                }
                if (tilesNearRuin[12].isPassable()) {
                    if (tilesNearRuin[12].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(12);
                    }
                }
                if (tilesNearRuin[13].isPassable()) {
                    if (tilesNearRuin[13].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(13);
                    }
                }
                if (tilesNearRuin[14].isPassable()) {
                    if (tilesNearRuin[14].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(14);
                    }
                }
                if (tilesNearRuin[15].isPassable()) {
                    if (tilesNearRuin[15].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(15);
                    }
                }
            }
            case 17 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
                if (tilesNearRuin[9].isPassable()) {
                    if (tilesNearRuin[9].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(9);
                    }
                }
                if (tilesNearRuin[10].isPassable()) {
                    if (tilesNearRuin[10].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(10);
                    }
                }
                if (tilesNearRuin[11].isPassable()) {
                    if (tilesNearRuin[11].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(11);
                    }
                }
                if (tilesNearRuin[12].isPassable()) {
                    if (tilesNearRuin[12].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(12);
                    }
                }
                if (tilesNearRuin[13].isPassable()) {
                    if (tilesNearRuin[13].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(13);
                    }
                }
                if (tilesNearRuin[14].isPassable()) {
                    if (tilesNearRuin[14].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(14);
                    }
                }
                if (tilesNearRuin[15].isPassable()) {
                    if (tilesNearRuin[15].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(15);
                    }
                }
                if (tilesNearRuin[16].isPassable()) {
                    if (tilesNearRuin[16].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(16);
                    }
                }
            }
            case 18 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
                if (tilesNearRuin[9].isPassable()) {
                    if (tilesNearRuin[9].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(9);
                    }
                }
                if (tilesNearRuin[10].isPassable()) {
                    if (tilesNearRuin[10].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(10);
                    }
                }
                if (tilesNearRuin[11].isPassable()) {
                    if (tilesNearRuin[11].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(11);
                    }
                }
                if (tilesNearRuin[12].isPassable()) {
                    if (tilesNearRuin[12].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(12);
                    }
                }
                if (tilesNearRuin[13].isPassable()) {
                    if (tilesNearRuin[13].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(13);
                    }
                }
                if (tilesNearRuin[14].isPassable()) {
                    if (tilesNearRuin[14].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(14);
                    }
                }
                if (tilesNearRuin[15].isPassable()) {
                    if (tilesNearRuin[15].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(15);
                    }
                }
                if (tilesNearRuin[16].isPassable()) {
                    if (tilesNearRuin[16].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(16);
                    }
                }
                if (tilesNearRuin[17].isPassable()) {
                    if (tilesNearRuin[17].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(17);
                    }
                }
            }
            case 19 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
                if (tilesNearRuin[9].isPassable()) {
                    if (tilesNearRuin[9].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(9);
                    }
                }
                if (tilesNearRuin[10].isPassable()) {
                    if (tilesNearRuin[10].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(10);
                    }
                }
                if (tilesNearRuin[11].isPassable()) {
                    if (tilesNearRuin[11].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(11);
                    }
                }
                if (tilesNearRuin[12].isPassable()) {
                    if (tilesNearRuin[12].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(12);
                    }
                }
                if (tilesNearRuin[13].isPassable()) {
                    if (tilesNearRuin[13].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(13);
                    }
                }
                if (tilesNearRuin[14].isPassable()) {
                    if (tilesNearRuin[14].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(14);
                    }
                }
                if (tilesNearRuin[15].isPassable()) {
                    if (tilesNearRuin[15].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(15);
                    }
                }
                if (tilesNearRuin[16].isPassable()) {
                    if (tilesNearRuin[16].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(16);
                    }
                }
                if (tilesNearRuin[17].isPassable()) {
                    if (tilesNearRuin[17].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(17);
                    }
                }
                if (tilesNearRuin[18].isPassable()) {
                    if (tilesNearRuin[18].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(18);
                    }
                }
            }
            case 20 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
                if (tilesNearRuin[9].isPassable()) {
                    if (tilesNearRuin[9].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(9);
                    }
                }
                if (tilesNearRuin[10].isPassable()) {
                    if (tilesNearRuin[10].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(10);
                    }
                }
                if (tilesNearRuin[11].isPassable()) {
                    if (tilesNearRuin[11].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(11);
                    }
                }
                if (tilesNearRuin[12].isPassable()) {
                    if (tilesNearRuin[12].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(12);
                    }
                }
                if (tilesNearRuin[13].isPassable()) {
                    if (tilesNearRuin[13].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(13);
                    }
                }
                if (tilesNearRuin[14].isPassable()) {
                    if (tilesNearRuin[14].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(14);
                    }
                }
                if (tilesNearRuin[15].isPassable()) {
                    if (tilesNearRuin[15].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(15);
                    }
                }
                if (tilesNearRuin[16].isPassable()) {
                    if (tilesNearRuin[16].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(16);
                    }
                }
                if (tilesNearRuin[17].isPassable()) {
                    if (tilesNearRuin[17].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(17);
                    }
                }
                if (tilesNearRuin[18].isPassable()) {
                    if (tilesNearRuin[18].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(18);
                    }
                }
                if (tilesNearRuin[19].isPassable()) {
                    if (tilesNearRuin[19].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(19);
                    }
                }
            }
            case 21 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
                if (tilesNearRuin[9].isPassable()) {
                    if (tilesNearRuin[9].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(9);
                    }
                }
                if (tilesNearRuin[10].isPassable()) {
                    if (tilesNearRuin[10].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(10);
                    }
                }
                if (tilesNearRuin[11].isPassable()) {
                    if (tilesNearRuin[11].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(11);
                    }
                }
                if (tilesNearRuin[12].isPassable()) {
                    if (tilesNearRuin[12].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(12);
                    }
                }
                if (tilesNearRuin[13].isPassable()) {
                    if (tilesNearRuin[13].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(13);
                    }
                }
                if (tilesNearRuin[14].isPassable()) {
                    if (tilesNearRuin[14].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(14);
                    }
                }
                if (tilesNearRuin[15].isPassable()) {
                    if (tilesNearRuin[15].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(15);
                    }
                }
                if (tilesNearRuin[16].isPassable()) {
                    if (tilesNearRuin[16].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(16);
                    }
                }
                if (tilesNearRuin[17].isPassable()) {
                    if (tilesNearRuin[17].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(17);
                    }
                }
                if (tilesNearRuin[18].isPassable()) {
                    if (tilesNearRuin[18].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(18);
                    }
                }
                if (tilesNearRuin[19].isPassable()) {
                    if (tilesNearRuin[19].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(19);
                    }
                }
                if (tilesNearRuin[20].isPassable()) {
                    if (tilesNearRuin[20].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(20);
                    }
                }
            }
            case 22 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
                if (tilesNearRuin[9].isPassable()) {
                    if (tilesNearRuin[9].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(9);
                    }
                }
                if (tilesNearRuin[10].isPassable()) {
                    if (tilesNearRuin[10].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(10);
                    }
                }
                if (tilesNearRuin[11].isPassable()) {
                    if (tilesNearRuin[11].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(11);
                    }
                }
                if (tilesNearRuin[12].isPassable()) {
                    if (tilesNearRuin[12].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(12);
                    }
                }
                if (tilesNearRuin[13].isPassable()) {
                    if (tilesNearRuin[13].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(13);
                    }
                }
                if (tilesNearRuin[14].isPassable()) {
                    if (tilesNearRuin[14].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(14);
                    }
                }
                if (tilesNearRuin[15].isPassable()) {
                    if (tilesNearRuin[15].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(15);
                    }
                }
                if (tilesNearRuin[16].isPassable()) {
                    if (tilesNearRuin[16].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(16);
                    }
                }
                if (tilesNearRuin[17].isPassable()) {
                    if (tilesNearRuin[17].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(17);
                    }
                }
                if (tilesNearRuin[18].isPassable()) {
                    if (tilesNearRuin[18].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(18);
                    }
                }
                if (tilesNearRuin[19].isPassable()) {
                    if (tilesNearRuin[19].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(19);
                    }
                }
                if (tilesNearRuin[20].isPassable()) {
                    if (tilesNearRuin[20].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(20);
                    }
                }
                if (tilesNearRuin[21].isPassable()) {
                    if (tilesNearRuin[21].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(21);
                    }
                }
            }
            case 23 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
                if (tilesNearRuin[9].isPassable()) {
                    if (tilesNearRuin[9].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(9);
                    }
                }
                if (tilesNearRuin[10].isPassable()) {
                    if (tilesNearRuin[10].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(10);
                    }
                }
                if (tilesNearRuin[11].isPassable()) {
                    if (tilesNearRuin[11].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(11);
                    }
                }
                if (tilesNearRuin[12].isPassable()) {
                    if (tilesNearRuin[12].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(12);
                    }
                }
                if (tilesNearRuin[13].isPassable()) {
                    if (tilesNearRuin[13].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(13);
                    }
                }
                if (tilesNearRuin[14].isPassable()) {
                    if (tilesNearRuin[14].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(14);
                    }
                }
                if (tilesNearRuin[15].isPassable()) {
                    if (tilesNearRuin[15].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(15);
                    }
                }
                if (tilesNearRuin[16].isPassable()) {
                    if (tilesNearRuin[16].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(16);
                    }
                }
                if (tilesNearRuin[17].isPassable()) {
                    if (tilesNearRuin[17].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(17);
                    }
                }
                if (tilesNearRuin[18].isPassable()) {
                    if (tilesNearRuin[18].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(18);
                    }
                }
                if (tilesNearRuin[19].isPassable()) {
                    if (tilesNearRuin[19].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(19);
                    }
                }
                if (tilesNearRuin[20].isPassable()) {
                    if (tilesNearRuin[20].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(20);
                    }
                }
                if (tilesNearRuin[21].isPassable()) {
                    if (tilesNearRuin[21].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(21);
                    }
                }
                if (tilesNearRuin[22].isPassable()) {
                    if (tilesNearRuin[22].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(22);
                    }
                }
            }
            case 24 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
                if (tilesNearRuin[9].isPassable()) {
                    if (tilesNearRuin[9].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(9);
                    }
                }
                if (tilesNearRuin[10].isPassable()) {
                    if (tilesNearRuin[10].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(10);
                    }
                }
                if (tilesNearRuin[11].isPassable()) {
                    if (tilesNearRuin[11].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(11);
                    }
                }
                if (tilesNearRuin[12].isPassable()) {
                    if (tilesNearRuin[12].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(12);
                    }
                }
                if (tilesNearRuin[13].isPassable()) {
                    if (tilesNearRuin[13].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(13);
                    }
                }
                if (tilesNearRuin[14].isPassable()) {
                    if (tilesNearRuin[14].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(14);
                    }
                }
                if (tilesNearRuin[15].isPassable()) {
                    if (tilesNearRuin[15].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(15);
                    }
                }
                if (tilesNearRuin[16].isPassable()) {
                    if (tilesNearRuin[16].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(16);
                    }
                }
                if (tilesNearRuin[17].isPassable()) {
                    if (tilesNearRuin[17].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(17);
                    }
                }
                if (tilesNearRuin[18].isPassable()) {
                    if (tilesNearRuin[18].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(18);
                    }
                }
                if (tilesNearRuin[19].isPassable()) {
                    if (tilesNearRuin[19].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(19);
                    }
                }
                if (tilesNearRuin[20].isPassable()) {
                    if (tilesNearRuin[20].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(20);
                    }
                }
                if (tilesNearRuin[21].isPassable()) {
                    if (tilesNearRuin[21].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(21);
                    }
                }
                if (tilesNearRuin[22].isPassable()) {
                    if (tilesNearRuin[22].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(22);
                    }
                }
                if (tilesNearRuin[23].isPassable()) {
                    if (tilesNearRuin[23].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(23);
                    }
                }
            }
            case 25 -> {
                if (tilesNearRuin[0].isPassable()) {
                    if (tilesNearRuin[0].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(0);
                    }
                }
                if (tilesNearRuin[1].isPassable()) {
                    if (tilesNearRuin[1].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(1);
                    }
                }
                if (tilesNearRuin[2].isPassable()) {
                    if (tilesNearRuin[2].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(2);
                    }
                }
                if (tilesNearRuin[3].isPassable()) {
                    if (tilesNearRuin[3].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(3);
                    }
                }
                if (tilesNearRuin[4].isPassable()) {
                    if (tilesNearRuin[4].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(4);
                    }
                }
                if (tilesNearRuin[5].isPassable()) {
                    if (tilesNearRuin[5].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(5);
                    }
                }
                if (tilesNearRuin[6].isPassable()) {
                    if (tilesNearRuin[6].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(6);
                    }
                }
                if (tilesNearRuin[7].isPassable()) {
                    if (tilesNearRuin[7].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(7);
                    }
                }
                if (tilesNearRuin[8].isPassable()) {
                    if (tilesNearRuin[8].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(8);
                    }
                }
                if (tilesNearRuin[9].isPassable()) {
                    if (tilesNearRuin[9].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(9);
                    }
                }
                if (tilesNearRuin[10].isPassable()) {
                    if (tilesNearRuin[10].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(10);
                    }
                }
                if (tilesNearRuin[11].isPassable()) {
                    if (tilesNearRuin[11].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(11);
                    }
                }
                if (tilesNearRuin[12].isPassable()) {
                    if (tilesNearRuin[12].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(12);
                    }
                }
                if (tilesNearRuin[13].isPassable()) {
                    if (tilesNearRuin[13].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(13);
                    }
                }
                if (tilesNearRuin[14].isPassable()) {
                    if (tilesNearRuin[14].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(14);
                    }
                }
                if (tilesNearRuin[15].isPassable()) {
                    if (tilesNearRuin[15].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(15);
                    }
                }
                if (tilesNearRuin[16].isPassable()) {
                    if (tilesNearRuin[16].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(16);
                    }
                }
                if (tilesNearRuin[17].isPassable()) {
                    if (tilesNearRuin[17].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(17);
                    }
                }
                if (tilesNearRuin[18].isPassable()) {
                    if (tilesNearRuin[18].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(18);
                    }
                }
                if (tilesNearRuin[19].isPassable()) {
                    if (tilesNearRuin[19].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(19);
                    }
                }
                if (tilesNearRuin[20].isPassable()) {
                    if (tilesNearRuin[20].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(20);
                    }
                }
                if (tilesNearRuin[21].isPassable()) {
                    if (tilesNearRuin[21].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(21);
                    }
                }
                if (tilesNearRuin[22].isPassable()) {
                    if (tilesNearRuin[22].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(22);
                    }
                }
                if (tilesNearRuin[23].isPassable()) {
                    if (tilesNearRuin[23].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(23);
                    }
                }
                if (tilesNearRuin[24].isPassable()) {
                    if (tilesNearRuin[24].getPaint().isEnemy()) {
                        return false;
                    } else if (!hasEmpty) {
                        hasEmpty = check(24);
                    }
                }
            }
        }
        if (neededToFinish * 5 < staticRC.getPaint()) canFinishRuin = true;
        if (hasEmpty || (claimedRuin != null && claimedRuin.equals(ruin))) return true;
        if (staticRC.senseNearbyRobots(ruin, 8, staticRC.getTeam()).length == 0) return true;
        return false;
    }

    public static void scanNearbyTilesSoldier() throws GameActionException {
        int enemyCount = 0;
        int x = 0;
        int y = 0;
        int resourcePatternDist = Integer.MAX_VALUE;
        int failedPlacementLocations = 0;
        int minDistanceToValidLocation = Integer.MAX_VALUE;
        MapLocation tileLoc;
        int dist;
        PaintType mark;
//        for(MapInfo tile : nearbyTiles) {
//            if(knownSymmetry == RobotPlayer.Symmetry.Unknown) {
//                map[tile.getMapLocation().x][tile.getMapLocation().y] = (tile.isPassable()) ? 1 : (tile.isWall()) ? 2 : 3;
//                if(!tile.isPassable())  Utilities.validateSymmetry(tile.getMapLocation(), tile.hasRuin());
//            }
//            MapLocation tileLoc = tile.getMapLocation();
//            if(tile.getPaint().isEnemy()) {
//                x += tileLoc.x;
//                y += tileLoc.y;
//                enemyCount++;
//            }
//            PaintType mark = tile.getMark();
//            int dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
//            if(mark.isAlly()) {
//                if (!tile.isResourcePatternCenter() && dist < resourcePatternDist) {
//
//                    resourcePatternDist = dist;
//closestUnfilledPatternCenter = tileLoc;
//                }
//                if (dist < minDistanceToValidLocation) {
//                    minDistanceToValidLocation = dist;
//
//                }
//                if (staticRC.canCompleteResourcePattern(tile.getMapLocation()))
//                    staticRC.completeResourcePattern(tile.getMapLocation());
//            }
//            else if(mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
//                if(!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
//                    if(dist < minDistanceToValidLocation) {
//                        minDistanceToValidLocation = dist;
//
//                    }
//                    if(staticRC.canMark(tile.getMapLocation())) {
//                        staticRC.mark(tile.getMapLocation(), false);
//                        closestUnfilledPatternCenter = tile.getMapLocation();
//                    }
//                }
//                else {
//                    failedPlacementLocations++;
//                }
//            }
//        }
        if (nearbyTiles.length == 69) {
            switch (nearbyTiles.length) {
                case 69 -> {
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[0].getMapLocation().x][nearbyTiles[0].getMapLocation().y] = (nearbyTiles[0].isPassable()) ? 1 : (nearbyTiles[0].isWall()) ? 2 : 3;
                        if (!nearbyTiles[0].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[0].getMapLocation(), nearbyTiles[0].hasRuin());
                    }
                    tileLoc = nearbyTiles[0].getMapLocation();
                    if (nearbyTiles[0].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[0].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[0].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[0].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[0].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[0].getMapLocation())) {
                                staticRC.mark(nearbyTiles[0].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[0].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[1].getMapLocation().x][nearbyTiles[1].getMapLocation().y] = (nearbyTiles[1].isPassable()) ? 1 : (nearbyTiles[1].isWall()) ? 2 : 3;
                        if (!nearbyTiles[1].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[1].getMapLocation(), nearbyTiles[1].hasRuin());
                    }
                    tileLoc = nearbyTiles[1].getMapLocation();
                    if (nearbyTiles[1].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[1].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[1].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[1].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[1].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[1].getMapLocation())) {
                                staticRC.mark(nearbyTiles[1].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[1].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[2].getMapLocation().x][nearbyTiles[2].getMapLocation().y] = (nearbyTiles[2].isPassable()) ? 1 : (nearbyTiles[2].isWall()) ? 2 : 3;
                        if (!nearbyTiles[2].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[2].getMapLocation(), nearbyTiles[2].hasRuin());
                    }
                    tileLoc = nearbyTiles[2].getMapLocation();
                    if (nearbyTiles[2].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[2].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[2].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[2].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[2].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[2].getMapLocation())) {
                                staticRC.mark(nearbyTiles[2].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[2].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[3].getMapLocation().x][nearbyTiles[3].getMapLocation().y] = (nearbyTiles[3].isPassable()) ? 1 : (nearbyTiles[3].isWall()) ? 2 : 3;
                        if (!nearbyTiles[3].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[3].getMapLocation(), nearbyTiles[3].hasRuin());
                    }
                    tileLoc = nearbyTiles[3].getMapLocation();
                    if (nearbyTiles[3].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[3].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[3].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[3].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[3].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[3].getMapLocation())) {
                                staticRC.mark(nearbyTiles[3].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[3].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[4].getMapLocation().x][nearbyTiles[4].getMapLocation().y] = (nearbyTiles[4].isPassable()) ? 1 : (nearbyTiles[4].isWall()) ? 2 : 3;
                        if (!nearbyTiles[4].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[4].getMapLocation(), nearbyTiles[4].hasRuin());
                    }
                    tileLoc = nearbyTiles[4].getMapLocation();
                    if (nearbyTiles[4].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[4].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[4].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[4].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[4].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[4].getMapLocation())) {
                                staticRC.mark(nearbyTiles[4].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[4].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[5].getMapLocation().x][nearbyTiles[5].getMapLocation().y] = (nearbyTiles[5].isPassable()) ? 1 : (nearbyTiles[5].isWall()) ? 2 : 3;
                        if (!nearbyTiles[5].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[5].getMapLocation(), nearbyTiles[5].hasRuin());
                    }
                    tileLoc = nearbyTiles[5].getMapLocation();
                    if (nearbyTiles[5].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[5].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[5].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[5].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[5].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[5].getMapLocation())) {
                                staticRC.mark(nearbyTiles[5].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[5].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[6].getMapLocation().x][nearbyTiles[6].getMapLocation().y] = (nearbyTiles[6].isPassable()) ? 1 : (nearbyTiles[6].isWall()) ? 2 : 3;
                        if (!nearbyTiles[6].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[6].getMapLocation(), nearbyTiles[6].hasRuin());
                    }
                    tileLoc = nearbyTiles[6].getMapLocation();
                    if (nearbyTiles[6].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[6].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[6].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[6].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[6].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[6].getMapLocation())) {
                                staticRC.mark(nearbyTiles[6].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[6].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[7].getMapLocation().x][nearbyTiles[7].getMapLocation().y] = (nearbyTiles[7].isPassable()) ? 1 : (nearbyTiles[7].isWall()) ? 2 : 3;
                        if (!nearbyTiles[7].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[7].getMapLocation(), nearbyTiles[7].hasRuin());
                    }
                    tileLoc = nearbyTiles[7].getMapLocation();
                    if (nearbyTiles[7].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[7].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[7].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[7].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[7].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[7].getMapLocation())) {
                                staticRC.mark(nearbyTiles[7].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[7].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[8].getMapLocation().x][nearbyTiles[8].getMapLocation().y] = (nearbyTiles[8].isPassable()) ? 1 : (nearbyTiles[8].isWall()) ? 2 : 3;
                        if (!nearbyTiles[8].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[8].getMapLocation(), nearbyTiles[8].hasRuin());
                    }
                    tileLoc = nearbyTiles[8].getMapLocation();
                    if (nearbyTiles[8].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[8].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[8].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[8].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[8].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[8].getMapLocation())) {
                                staticRC.mark(nearbyTiles[8].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[8].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[9].getMapLocation().x][nearbyTiles[9].getMapLocation().y] = (nearbyTiles[9].isPassable()) ? 1 : (nearbyTiles[9].isWall()) ? 2 : 3;
                        if (!nearbyTiles[9].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[9].getMapLocation(), nearbyTiles[9].hasRuin());
                    }
                    tileLoc = nearbyTiles[9].getMapLocation();
                    if (nearbyTiles[9].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[9].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[9].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[9].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[9].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[9].getMapLocation())) {
                                staticRC.mark(nearbyTiles[9].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[9].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[10].getMapLocation().x][nearbyTiles[10].getMapLocation().y] = (nearbyTiles[10].isPassable()) ? 1 : (nearbyTiles[10].isWall()) ? 2 : 3;
                        if (!nearbyTiles[10].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[10].getMapLocation(), nearbyTiles[10].hasRuin());
                    }
                    tileLoc = nearbyTiles[10].getMapLocation();
                    if (nearbyTiles[10].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[10].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[10].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[10].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[10].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[10].getMapLocation())) {
                                staticRC.mark(nearbyTiles[10].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[10].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[11].getMapLocation().x][nearbyTiles[11].getMapLocation().y] = (nearbyTiles[11].isPassable()) ? 1 : (nearbyTiles[11].isWall()) ? 2 : 3;
                        if (!nearbyTiles[11].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[11].getMapLocation(), nearbyTiles[11].hasRuin());
                    }
                    tileLoc = nearbyTiles[11].getMapLocation();
                    if (nearbyTiles[11].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[11].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[11].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[11].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[11].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[11].getMapLocation())) {
                                staticRC.mark(nearbyTiles[11].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[11].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[12].getMapLocation().x][nearbyTiles[12].getMapLocation().y] = (nearbyTiles[12].isPassable()) ? 1 : (nearbyTiles[12].isWall()) ? 2 : 3;
                        if (!nearbyTiles[12].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[12].getMapLocation(), nearbyTiles[12].hasRuin());
                    }
                    tileLoc = nearbyTiles[12].getMapLocation();
                    if (nearbyTiles[12].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[12].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[12].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[12].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[12].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[12].getMapLocation())) {
                                staticRC.mark(nearbyTiles[12].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[12].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[13].getMapLocation().x][nearbyTiles[13].getMapLocation().y] = (nearbyTiles[13].isPassable()) ? 1 : (nearbyTiles[13].isWall()) ? 2 : 3;
                        if (!nearbyTiles[13].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[13].getMapLocation(), nearbyTiles[13].hasRuin());
                    }
                    tileLoc = nearbyTiles[13].getMapLocation();
                    if (nearbyTiles[13].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[13].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[13].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[13].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[13].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[13].getMapLocation())) {
                                staticRC.mark(nearbyTiles[13].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[13].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[14].getMapLocation().x][nearbyTiles[14].getMapLocation().y] = (nearbyTiles[14].isPassable()) ? 1 : (nearbyTiles[14].isWall()) ? 2 : 3;
                        if (!nearbyTiles[14].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[14].getMapLocation(), nearbyTiles[14].hasRuin());
                    }
                    tileLoc = nearbyTiles[14].getMapLocation();
                    if (nearbyTiles[14].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[14].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[14].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[14].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[14].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[14].getMapLocation())) {
                                staticRC.mark(nearbyTiles[14].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[14].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[15].getMapLocation().x][nearbyTiles[15].getMapLocation().y] = (nearbyTiles[15].isPassable()) ? 1 : (nearbyTiles[15].isWall()) ? 2 : 3;
                        if (!nearbyTiles[15].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[15].getMapLocation(), nearbyTiles[15].hasRuin());
                    }
                    tileLoc = nearbyTiles[15].getMapLocation();
                    if (nearbyTiles[15].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[15].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[15].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[15].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[15].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[15].getMapLocation())) {
                                staticRC.mark(nearbyTiles[15].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[15].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[16].getMapLocation().x][nearbyTiles[16].getMapLocation().y] = (nearbyTiles[16].isPassable()) ? 1 : (nearbyTiles[16].isWall()) ? 2 : 3;
                        if (!nearbyTiles[16].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[16].getMapLocation(), nearbyTiles[16].hasRuin());
                    }
                    tileLoc = nearbyTiles[16].getMapLocation();
                    if (nearbyTiles[16].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[16].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[16].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[16].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[16].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[16].getMapLocation())) {
                                staticRC.mark(nearbyTiles[16].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[16].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[17].getMapLocation().x][nearbyTiles[17].getMapLocation().y] = (nearbyTiles[17].isPassable()) ? 1 : (nearbyTiles[17].isWall()) ? 2 : 3;
                        if (!nearbyTiles[17].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[17].getMapLocation(), nearbyTiles[17].hasRuin());
                    }
                    tileLoc = nearbyTiles[17].getMapLocation();
                    if (nearbyTiles[17].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[17].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[17].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[17].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[17].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[17].getMapLocation())) {
                                staticRC.mark(nearbyTiles[17].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[17].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[18].getMapLocation().x][nearbyTiles[18].getMapLocation().y] = (nearbyTiles[18].isPassable()) ? 1 : (nearbyTiles[18].isWall()) ? 2 : 3;
                        if (!nearbyTiles[18].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[18].getMapLocation(), nearbyTiles[18].hasRuin());
                    }
                    tileLoc = nearbyTiles[18].getMapLocation();
                    if (nearbyTiles[18].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[18].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[18].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[18].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[18].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[18].getMapLocation())) {
                                staticRC.mark(nearbyTiles[18].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[18].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[19].getMapLocation().x][nearbyTiles[19].getMapLocation().y] = (nearbyTiles[19].isPassable()) ? 1 : (nearbyTiles[19].isWall()) ? 2 : 3;
                        if (!nearbyTiles[19].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[19].getMapLocation(), nearbyTiles[19].hasRuin());
                    }
                    tileLoc = nearbyTiles[19].getMapLocation();
                    if (nearbyTiles[19].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[19].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[19].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[19].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[19].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[19].getMapLocation())) {
                                staticRC.mark(nearbyTiles[19].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[19].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[20].getMapLocation().x][nearbyTiles[20].getMapLocation().y] = (nearbyTiles[20].isPassable()) ? 1 : (nearbyTiles[20].isWall()) ? 2 : 3;
                        if (!nearbyTiles[20].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[20].getMapLocation(), nearbyTiles[20].hasRuin());
                    }
                    tileLoc = nearbyTiles[20].getMapLocation();
                    if (nearbyTiles[20].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[20].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[20].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[20].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[20].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[20].getMapLocation())) {
                                staticRC.mark(nearbyTiles[20].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[20].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[21].getMapLocation().x][nearbyTiles[21].getMapLocation().y] = (nearbyTiles[21].isPassable()) ? 1 : (nearbyTiles[21].isWall()) ? 2 : 3;
                        if (!nearbyTiles[21].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[21].getMapLocation(), nearbyTiles[21].hasRuin());
                    }
                    tileLoc = nearbyTiles[21].getMapLocation();
                    if (nearbyTiles[21].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[21].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[21].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[21].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[21].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[21].getMapLocation())) {
                                staticRC.mark(nearbyTiles[21].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[21].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[22].getMapLocation().x][nearbyTiles[22].getMapLocation().y] = (nearbyTiles[22].isPassable()) ? 1 : (nearbyTiles[22].isWall()) ? 2 : 3;
                        if (!nearbyTiles[22].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[22].getMapLocation(), nearbyTiles[22].hasRuin());
                    }
                    tileLoc = nearbyTiles[22].getMapLocation();
                    if (nearbyTiles[22].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[22].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[22].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[22].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[22].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[22].getMapLocation())) {
                                staticRC.mark(nearbyTiles[22].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[22].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[23].getMapLocation().x][nearbyTiles[23].getMapLocation().y] = (nearbyTiles[23].isPassable()) ? 1 : (nearbyTiles[23].isWall()) ? 2 : 3;
                        if (!nearbyTiles[23].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[23].getMapLocation(), nearbyTiles[23].hasRuin());
                    }
                    tileLoc = nearbyTiles[23].getMapLocation();
                    if (nearbyTiles[23].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[23].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[23].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[23].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[23].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[23].getMapLocation())) {
                                staticRC.mark(nearbyTiles[23].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[23].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[24].getMapLocation().x][nearbyTiles[24].getMapLocation().y] = (nearbyTiles[24].isPassable()) ? 1 : (nearbyTiles[24].isWall()) ? 2 : 3;
                        if (!nearbyTiles[24].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[24].getMapLocation(), nearbyTiles[24].hasRuin());
                    }
                    tileLoc = nearbyTiles[24].getMapLocation();
                    if (nearbyTiles[24].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[24].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[24].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[24].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[24].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[24].getMapLocation())) {
                                staticRC.mark(nearbyTiles[24].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[24].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[25].getMapLocation().x][nearbyTiles[25].getMapLocation().y] = (nearbyTiles[25].isPassable()) ? 1 : (nearbyTiles[25].isWall()) ? 2 : 3;
                        if (!nearbyTiles[25].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[25].getMapLocation(), nearbyTiles[25].hasRuin());
                    }
                    tileLoc = nearbyTiles[25].getMapLocation();
                    if (nearbyTiles[25].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[25].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[25].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[25].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[25].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[25].getMapLocation())) {
                                staticRC.mark(nearbyTiles[25].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[25].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[26].getMapLocation().x][nearbyTiles[26].getMapLocation().y] = (nearbyTiles[26].isPassable()) ? 1 : (nearbyTiles[26].isWall()) ? 2 : 3;
                        if (!nearbyTiles[26].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[26].getMapLocation(), nearbyTiles[26].hasRuin());
                    }
                    tileLoc = nearbyTiles[26].getMapLocation();
                    if (nearbyTiles[26].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[26].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[26].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[26].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[26].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[26].getMapLocation())) {
                                staticRC.mark(nearbyTiles[26].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[26].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[27].getMapLocation().x][nearbyTiles[27].getMapLocation().y] = (nearbyTiles[27].isPassable()) ? 1 : (nearbyTiles[27].isWall()) ? 2 : 3;
                        if (!nearbyTiles[27].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[27].getMapLocation(), nearbyTiles[27].hasRuin());
                    }
                    tileLoc = nearbyTiles[27].getMapLocation();
                    if (nearbyTiles[27].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[27].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[27].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[27].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[27].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[27].getMapLocation())) {
                                staticRC.mark(nearbyTiles[27].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[27].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[28].getMapLocation().x][nearbyTiles[28].getMapLocation().y] = (nearbyTiles[28].isPassable()) ? 1 : (nearbyTiles[28].isWall()) ? 2 : 3;
                        if (!nearbyTiles[28].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[28].getMapLocation(), nearbyTiles[28].hasRuin());
                    }
                    tileLoc = nearbyTiles[28].getMapLocation();
                    if (nearbyTiles[28].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[28].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[28].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[28].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[28].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[28].getMapLocation())) {
                                staticRC.mark(nearbyTiles[28].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[28].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[29].getMapLocation().x][nearbyTiles[29].getMapLocation().y] = (nearbyTiles[29].isPassable()) ? 1 : (nearbyTiles[29].isWall()) ? 2 : 3;
                        if (!nearbyTiles[29].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[29].getMapLocation(), nearbyTiles[29].hasRuin());
                    }
                    tileLoc = nearbyTiles[29].getMapLocation();
                    if (nearbyTiles[29].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[29].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[29].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[29].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[29].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[29].getMapLocation())) {
                                staticRC.mark(nearbyTiles[29].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[29].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[30].getMapLocation().x][nearbyTiles[30].getMapLocation().y] = (nearbyTiles[30].isPassable()) ? 1 : (nearbyTiles[30].isWall()) ? 2 : 3;
                        if (!nearbyTiles[30].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[30].getMapLocation(), nearbyTiles[30].hasRuin());
                    }
                    tileLoc = nearbyTiles[30].getMapLocation();
                    if (nearbyTiles[30].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[30].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[30].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[30].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[30].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[30].getMapLocation())) {
                                staticRC.mark(nearbyTiles[30].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[30].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[31].getMapLocation().x][nearbyTiles[31].getMapLocation().y] = (nearbyTiles[31].isPassable()) ? 1 : (nearbyTiles[31].isWall()) ? 2 : 3;
                        if (!nearbyTiles[31].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[31].getMapLocation(), nearbyTiles[31].hasRuin());
                    }
                    tileLoc = nearbyTiles[31].getMapLocation();
                    if (nearbyTiles[31].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[31].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[31].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[31].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[31].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[31].getMapLocation())) {
                                staticRC.mark(nearbyTiles[31].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[31].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[32].getMapLocation().x][nearbyTiles[32].getMapLocation().y] = (nearbyTiles[32].isPassable()) ? 1 : (nearbyTiles[32].isWall()) ? 2 : 3;
                        if (!nearbyTiles[32].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[32].getMapLocation(), nearbyTiles[32].hasRuin());
                    }
                    tileLoc = nearbyTiles[32].getMapLocation();
                    if (nearbyTiles[32].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[32].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[32].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[32].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[32].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[32].getMapLocation())) {
                                staticRC.mark(nearbyTiles[32].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[32].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[33].getMapLocation().x][nearbyTiles[33].getMapLocation().y] = (nearbyTiles[33].isPassable()) ? 1 : (nearbyTiles[33].isWall()) ? 2 : 3;
                        if (!nearbyTiles[33].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[33].getMapLocation(), nearbyTiles[33].hasRuin());
                    }
                    tileLoc = nearbyTiles[33].getMapLocation();
                    if (nearbyTiles[33].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[33].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[33].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[33].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[33].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[33].getMapLocation())) {
                                staticRC.mark(nearbyTiles[33].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[33].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[34].getMapLocation().x][nearbyTiles[34].getMapLocation().y] = (nearbyTiles[34].isPassable()) ? 1 : (nearbyTiles[34].isWall()) ? 2 : 3;
                        if (!nearbyTiles[34].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[34].getMapLocation(), nearbyTiles[34].hasRuin());
                    }
                    tileLoc = nearbyTiles[34].getMapLocation();
                    if (nearbyTiles[34].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[34].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[34].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[34].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[34].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[34].getMapLocation())) {
                                staticRC.mark(nearbyTiles[34].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[34].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[35].getMapLocation().x][nearbyTiles[35].getMapLocation().y] = (nearbyTiles[35].isPassable()) ? 1 : (nearbyTiles[35].isWall()) ? 2 : 3;
                        if (!nearbyTiles[35].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[35].getMapLocation(), nearbyTiles[35].hasRuin());
                    }
                    tileLoc = nearbyTiles[35].getMapLocation();
                    if (nearbyTiles[35].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[35].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[35].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[35].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[35].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[35].getMapLocation())) {
                                staticRC.mark(nearbyTiles[35].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[35].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[36].getMapLocation().x][nearbyTiles[36].getMapLocation().y] = (nearbyTiles[36].isPassable()) ? 1 : (nearbyTiles[36].isWall()) ? 2 : 3;
                        if (!nearbyTiles[36].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[36].getMapLocation(), nearbyTiles[36].hasRuin());
                    }
                    tileLoc = nearbyTiles[36].getMapLocation();
                    if (nearbyTiles[36].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[36].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[36].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[36].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[36].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[36].getMapLocation())) {
                                staticRC.mark(nearbyTiles[36].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[36].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[37].getMapLocation().x][nearbyTiles[37].getMapLocation().y] = (nearbyTiles[37].isPassable()) ? 1 : (nearbyTiles[37].isWall()) ? 2 : 3;
                        if (!nearbyTiles[37].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[37].getMapLocation(), nearbyTiles[37].hasRuin());
                    }
                    tileLoc = nearbyTiles[37].getMapLocation();
                    if (nearbyTiles[37].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[37].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[37].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[37].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[37].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[37].getMapLocation())) {
                                staticRC.mark(nearbyTiles[37].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[37].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[38].getMapLocation().x][nearbyTiles[38].getMapLocation().y] = (nearbyTiles[38].isPassable()) ? 1 : (nearbyTiles[38].isWall()) ? 2 : 3;
                        if (!nearbyTiles[38].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[38].getMapLocation(), nearbyTiles[38].hasRuin());
                    }
                    tileLoc = nearbyTiles[38].getMapLocation();
                    if (nearbyTiles[38].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[38].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[38].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[38].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[38].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[38].getMapLocation())) {
                                staticRC.mark(nearbyTiles[38].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[38].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[39].getMapLocation().x][nearbyTiles[39].getMapLocation().y] = (nearbyTiles[39].isPassable()) ? 1 : (nearbyTiles[39].isWall()) ? 2 : 3;
                        if (!nearbyTiles[39].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[39].getMapLocation(), nearbyTiles[39].hasRuin());
                    }
                    tileLoc = nearbyTiles[39].getMapLocation();
                    if (nearbyTiles[39].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[39].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[39].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[39].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[39].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[39].getMapLocation())) {
                                staticRC.mark(nearbyTiles[39].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[39].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[40].getMapLocation().x][nearbyTiles[40].getMapLocation().y] = (nearbyTiles[40].isPassable()) ? 1 : (nearbyTiles[40].isWall()) ? 2 : 3;
                        if (!nearbyTiles[40].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[40].getMapLocation(), nearbyTiles[40].hasRuin());
                    }
                    tileLoc = nearbyTiles[40].getMapLocation();
                    if (nearbyTiles[40].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[40].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[40].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[40].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[40].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[40].getMapLocation())) {
                                staticRC.mark(nearbyTiles[40].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[40].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[41].getMapLocation().x][nearbyTiles[41].getMapLocation().y] = (nearbyTiles[41].isPassable()) ? 1 : (nearbyTiles[41].isWall()) ? 2 : 3;
                        if (!nearbyTiles[41].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[41].getMapLocation(), nearbyTiles[41].hasRuin());
                    }
                    tileLoc = nearbyTiles[41].getMapLocation();
                    if (nearbyTiles[41].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[41].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[41].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[41].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[41].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[41].getMapLocation())) {
                                staticRC.mark(nearbyTiles[41].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[41].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[42].getMapLocation().x][nearbyTiles[42].getMapLocation().y] = (nearbyTiles[42].isPassable()) ? 1 : (nearbyTiles[42].isWall()) ? 2 : 3;
                        if (!nearbyTiles[42].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[42].getMapLocation(), nearbyTiles[42].hasRuin());
                    }
                    tileLoc = nearbyTiles[42].getMapLocation();
                    if (nearbyTiles[42].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[42].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[42].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[42].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[42].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[42].getMapLocation())) {
                                staticRC.mark(nearbyTiles[42].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[42].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[43].getMapLocation().x][nearbyTiles[43].getMapLocation().y] = (nearbyTiles[43].isPassable()) ? 1 : (nearbyTiles[43].isWall()) ? 2 : 3;
                        if (!nearbyTiles[43].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[43].getMapLocation(), nearbyTiles[43].hasRuin());
                    }
                    tileLoc = nearbyTiles[43].getMapLocation();
                    if (nearbyTiles[43].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[43].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[43].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[43].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[43].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[43].getMapLocation())) {
                                staticRC.mark(nearbyTiles[43].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[43].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[44].getMapLocation().x][nearbyTiles[44].getMapLocation().y] = (nearbyTiles[44].isPassable()) ? 1 : (nearbyTiles[44].isWall()) ? 2 : 3;
                        if (!nearbyTiles[44].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[44].getMapLocation(), nearbyTiles[44].hasRuin());
                    }
                    tileLoc = nearbyTiles[44].getMapLocation();
                    if (nearbyTiles[44].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[44].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[44].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[44].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[44].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[44].getMapLocation())) {
                                staticRC.mark(nearbyTiles[44].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[44].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[45].getMapLocation().x][nearbyTiles[45].getMapLocation().y] = (nearbyTiles[45].isPassable()) ? 1 : (nearbyTiles[45].isWall()) ? 2 : 3;
                        if (!nearbyTiles[45].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[45].getMapLocation(), nearbyTiles[45].hasRuin());
                    }
                    tileLoc = nearbyTiles[45].getMapLocation();
                    if (nearbyTiles[45].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[45].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[45].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[45].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[45].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[45].getMapLocation())) {
                                staticRC.mark(nearbyTiles[45].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[45].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[46].getMapLocation().x][nearbyTiles[46].getMapLocation().y] = (nearbyTiles[46].isPassable()) ? 1 : (nearbyTiles[46].isWall()) ? 2 : 3;
                        if (!nearbyTiles[46].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[46].getMapLocation(), nearbyTiles[46].hasRuin());
                    }
                    tileLoc = nearbyTiles[46].getMapLocation();
                    if (nearbyTiles[46].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[46].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[46].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[46].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[46].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[46].getMapLocation())) {
                                staticRC.mark(nearbyTiles[46].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[46].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[47].getMapLocation().x][nearbyTiles[47].getMapLocation().y] = (nearbyTiles[47].isPassable()) ? 1 : (nearbyTiles[47].isWall()) ? 2 : 3;
                        if (!nearbyTiles[47].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[47].getMapLocation(), nearbyTiles[47].hasRuin());
                    }
                    tileLoc = nearbyTiles[47].getMapLocation();
                    if (nearbyTiles[47].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[47].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[47].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[47].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[47].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[47].getMapLocation())) {
                                staticRC.mark(nearbyTiles[47].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[47].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[48].getMapLocation().x][nearbyTiles[48].getMapLocation().y] = (nearbyTiles[48].isPassable()) ? 1 : (nearbyTiles[48].isWall()) ? 2 : 3;
                        if (!nearbyTiles[48].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[48].getMapLocation(), nearbyTiles[48].hasRuin());
                    }
                    tileLoc = nearbyTiles[48].getMapLocation();
                    if (nearbyTiles[48].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[48].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[48].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[48].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[48].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[48].getMapLocation())) {
                                staticRC.mark(nearbyTiles[48].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[48].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[49].getMapLocation().x][nearbyTiles[49].getMapLocation().y] = (nearbyTiles[49].isPassable()) ? 1 : (nearbyTiles[49].isWall()) ? 2 : 3;
                        if (!nearbyTiles[49].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[49].getMapLocation(), nearbyTiles[49].hasRuin());
                    }
                    tileLoc = nearbyTiles[49].getMapLocation();
                    if (nearbyTiles[49].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[49].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[49].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[49].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[49].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[49].getMapLocation())) {
                                staticRC.mark(nearbyTiles[49].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[49].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[50].getMapLocation().x][nearbyTiles[50].getMapLocation().y] = (nearbyTiles[50].isPassable()) ? 1 : (nearbyTiles[50].isWall()) ? 2 : 3;
                        if (!nearbyTiles[50].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[50].getMapLocation(), nearbyTiles[50].hasRuin());
                    }
                    tileLoc = nearbyTiles[50].getMapLocation();
                    if (nearbyTiles[50].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[50].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[50].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[50].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[50].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[50].getMapLocation())) {
                                staticRC.mark(nearbyTiles[50].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[50].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[51].getMapLocation().x][nearbyTiles[51].getMapLocation().y] = (nearbyTiles[51].isPassable()) ? 1 : (nearbyTiles[51].isWall()) ? 2 : 3;
                        if (!nearbyTiles[51].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[51].getMapLocation(), nearbyTiles[51].hasRuin());
                    }
                    tileLoc = nearbyTiles[51].getMapLocation();
                    if (nearbyTiles[51].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[51].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[51].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[51].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[51].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[51].getMapLocation())) {
                                staticRC.mark(nearbyTiles[51].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[51].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[52].getMapLocation().x][nearbyTiles[52].getMapLocation().y] = (nearbyTiles[52].isPassable()) ? 1 : (nearbyTiles[52].isWall()) ? 2 : 3;
                        if (!nearbyTiles[52].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[52].getMapLocation(), nearbyTiles[52].hasRuin());
                    }
                    tileLoc = nearbyTiles[52].getMapLocation();
                    if (nearbyTiles[52].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[52].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[52].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[52].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[52].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[52].getMapLocation())) {
                                staticRC.mark(nearbyTiles[52].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[52].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[53].getMapLocation().x][nearbyTiles[53].getMapLocation().y] = (nearbyTiles[53].isPassable()) ? 1 : (nearbyTiles[53].isWall()) ? 2 : 3;
                        if (!nearbyTiles[53].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[53].getMapLocation(), nearbyTiles[53].hasRuin());
                    }
                    tileLoc = nearbyTiles[53].getMapLocation();
                    if (nearbyTiles[53].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[53].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[53].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[53].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[53].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[53].getMapLocation())) {
                                staticRC.mark(nearbyTiles[53].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[53].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[54].getMapLocation().x][nearbyTiles[54].getMapLocation().y] = (nearbyTiles[54].isPassable()) ? 1 : (nearbyTiles[54].isWall()) ? 2 : 3;
                        if (!nearbyTiles[54].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[54].getMapLocation(), nearbyTiles[54].hasRuin());
                    }
                    tileLoc = nearbyTiles[54].getMapLocation();
                    if (nearbyTiles[54].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[54].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[54].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[54].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[54].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[54].getMapLocation())) {
                                staticRC.mark(nearbyTiles[54].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[54].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[55].getMapLocation().x][nearbyTiles[55].getMapLocation().y] = (nearbyTiles[55].isPassable()) ? 1 : (nearbyTiles[55].isWall()) ? 2 : 3;
                        if (!nearbyTiles[55].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[55].getMapLocation(), nearbyTiles[55].hasRuin());
                    }
                    tileLoc = nearbyTiles[55].getMapLocation();
                    if (nearbyTiles[55].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[55].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[55].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[55].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[55].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[55].getMapLocation())) {
                                staticRC.mark(nearbyTiles[55].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[55].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[56].getMapLocation().x][nearbyTiles[56].getMapLocation().y] = (nearbyTiles[56].isPassable()) ? 1 : (nearbyTiles[56].isWall()) ? 2 : 3;
                        if (!nearbyTiles[56].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[56].getMapLocation(), nearbyTiles[56].hasRuin());
                    }
                    tileLoc = nearbyTiles[56].getMapLocation();
                    if (nearbyTiles[56].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[56].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[56].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[56].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[56].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[56].getMapLocation())) {
                                staticRC.mark(nearbyTiles[56].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[56].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[57].getMapLocation().x][nearbyTiles[57].getMapLocation().y] = (nearbyTiles[57].isPassable()) ? 1 : (nearbyTiles[57].isWall()) ? 2 : 3;
                        if (!nearbyTiles[57].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[57].getMapLocation(), nearbyTiles[57].hasRuin());
                    }
                    tileLoc = nearbyTiles[57].getMapLocation();
                    if (nearbyTiles[57].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[57].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[57].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[57].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[57].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[57].getMapLocation())) {
                                staticRC.mark(nearbyTiles[57].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[57].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[58].getMapLocation().x][nearbyTiles[58].getMapLocation().y] = (nearbyTiles[58].isPassable()) ? 1 : (nearbyTiles[58].isWall()) ? 2 : 3;
                        if (!nearbyTiles[58].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[58].getMapLocation(), nearbyTiles[58].hasRuin());
                    }
                    tileLoc = nearbyTiles[58].getMapLocation();
                    if (nearbyTiles[58].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[58].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[58].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[58].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[58].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[58].getMapLocation())) {
                                staticRC.mark(nearbyTiles[58].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[58].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[59].getMapLocation().x][nearbyTiles[59].getMapLocation().y] = (nearbyTiles[59].isPassable()) ? 1 : (nearbyTiles[59].isWall()) ? 2 : 3;
                        if (!nearbyTiles[59].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[59].getMapLocation(), nearbyTiles[59].hasRuin());
                    }
                    tileLoc = nearbyTiles[59].getMapLocation();
                    if (nearbyTiles[59].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[59].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[59].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[59].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[59].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[59].getMapLocation())) {
                                staticRC.mark(nearbyTiles[59].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[59].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[60].getMapLocation().x][nearbyTiles[60].getMapLocation().y] = (nearbyTiles[60].isPassable()) ? 1 : (nearbyTiles[60].isWall()) ? 2 : 3;
                        if (!nearbyTiles[60].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[60].getMapLocation(), nearbyTiles[60].hasRuin());
                    }
                    tileLoc = nearbyTiles[60].getMapLocation();
                    if (nearbyTiles[60].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[60].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[60].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[60].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[60].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[60].getMapLocation())) {
                                staticRC.mark(nearbyTiles[60].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[60].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[61].getMapLocation().x][nearbyTiles[61].getMapLocation().y] = (nearbyTiles[61].isPassable()) ? 1 : (nearbyTiles[61].isWall()) ? 2 : 3;
                        if (!nearbyTiles[61].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[61].getMapLocation(), nearbyTiles[61].hasRuin());
                    }
                    tileLoc = nearbyTiles[61].getMapLocation();
                    if (nearbyTiles[61].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[61].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[61].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[61].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[61].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[61].getMapLocation())) {
                                staticRC.mark(nearbyTiles[61].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[61].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[62].getMapLocation().x][nearbyTiles[62].getMapLocation().y] = (nearbyTiles[62].isPassable()) ? 1 : (nearbyTiles[62].isWall()) ? 2 : 3;
                        if (!nearbyTiles[62].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[62].getMapLocation(), nearbyTiles[62].hasRuin());
                    }
                    tileLoc = nearbyTiles[62].getMapLocation();
                    if (nearbyTiles[62].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[62].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[62].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[62].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[62].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[62].getMapLocation())) {
                                staticRC.mark(nearbyTiles[62].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[62].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[63].getMapLocation().x][nearbyTiles[63].getMapLocation().y] = (nearbyTiles[63].isPassable()) ? 1 : (nearbyTiles[63].isWall()) ? 2 : 3;
                        if (!nearbyTiles[63].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[63].getMapLocation(), nearbyTiles[63].hasRuin());
                    }
                    tileLoc = nearbyTiles[63].getMapLocation();
                    if (nearbyTiles[63].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[63].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[63].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[63].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[63].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[63].getMapLocation())) {
                                staticRC.mark(nearbyTiles[63].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[63].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[64].getMapLocation().x][nearbyTiles[64].getMapLocation().y] = (nearbyTiles[64].isPassable()) ? 1 : (nearbyTiles[64].isWall()) ? 2 : 3;
                        if (!nearbyTiles[64].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[64].getMapLocation(), nearbyTiles[64].hasRuin());
                    }
                    tileLoc = nearbyTiles[64].getMapLocation();
                    if (nearbyTiles[64].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[64].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[64].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[64].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[64].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[64].getMapLocation())) {
                                staticRC.mark(nearbyTiles[64].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[64].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[65].getMapLocation().x][nearbyTiles[65].getMapLocation().y] = (nearbyTiles[65].isPassable()) ? 1 : (nearbyTiles[65].isWall()) ? 2 : 3;
                        if (!nearbyTiles[65].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[65].getMapLocation(), nearbyTiles[65].hasRuin());
                    }
                    tileLoc = nearbyTiles[65].getMapLocation();
                    if (nearbyTiles[65].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[65].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[65].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[65].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[65].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[65].getMapLocation())) {
                                staticRC.mark(nearbyTiles[65].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[65].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[66].getMapLocation().x][nearbyTiles[66].getMapLocation().y] = (nearbyTiles[66].isPassable()) ? 1 : (nearbyTiles[66].isWall()) ? 2 : 3;
                        if (!nearbyTiles[66].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[66].getMapLocation(), nearbyTiles[66].hasRuin());
                    }
                    tileLoc = nearbyTiles[66].getMapLocation();
                    if (nearbyTiles[66].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[66].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[66].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[66].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[66].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[66].getMapLocation())) {
                                staticRC.mark(nearbyTiles[66].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[66].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[67].getMapLocation().x][nearbyTiles[67].getMapLocation().y] = (nearbyTiles[67].isPassable()) ? 1 : (nearbyTiles[67].isWall()) ? 2 : 3;
                        if (!nearbyTiles[67].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[67].getMapLocation(), nearbyTiles[67].hasRuin());
                    }
                    tileLoc = nearbyTiles[67].getMapLocation();
                    if (nearbyTiles[67].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[67].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[67].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[67].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[67].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[67].getMapLocation())) {
                                staticRC.mark(nearbyTiles[67].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[67].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                    if (knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[nearbyTiles[68].getMapLocation().x][nearbyTiles[68].getMapLocation().y] = (nearbyTiles[68].isPassable()) ? 1 : (nearbyTiles[68].isWall()) ? 2 : 3;
                        if (!nearbyTiles[68].isPassable())
                            Utilities.validateSymmetry(nearbyTiles[68].getMapLocation(), nearbyTiles[68].hasRuin());
                    }
                    tileLoc = nearbyTiles[68].getMapLocation();
                    if (nearbyTiles[68].getPaint().isEnemy()) {
                        x += tileLoc.x;
                        y += tileLoc.y;
                        enemyCount++;
                    }
                    mark = nearbyTiles[68].getMark();
                    dist = staticRC.getLocation().distanceSquaredTo(tileLoc);
                    if (mark.isAlly()) {
                        if (!nearbyTiles[68].isResourcePatternCenter() && dist < resourcePatternDist) {

                            resourcePatternDist = dist;
                            closestUnfilledPatternCenter = tileLoc;
                        }
                        if (dist < minDistanceToValidLocation) {
                            minDistanceToValidLocation = dist;

                        }
                        if (staticRC.canCompleteResourcePattern(nearbyTiles[68].getMapLocation())) {
                            staticRC.completeResourcePattern(nearbyTiles[68].getMapLocation());
                        }
                    } else if (mark == PaintType.EMPTY && (tileLoc.x - 2) % 4 == 0 && (tileLoc.y - 2) % 4 == 0) {
                        if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
                            if (dist < minDistanceToValidLocation) {
                                minDistanceToValidLocation = dist;

                            }
                            if (staticRC.canMark(nearbyTiles[68].getMapLocation())) {
                                staticRC.mark(nearbyTiles[68].getMapLocation(), false);
                                closestUnfilledPatternCenter = nearbyTiles[68].getMapLocation();
                            } else if (dist < resourcePatternDist) {
                                closestUnfilledPatternCenter = tileLoc;
                                resourcePatternDist = dist;
                            }
                        } else {
                            failedPlacementLocations++;
                        }
                    }
                }
            }

        } else {
            for (MapInfo tile : nearbyTiles) {
                if (knownSymmetry == Symmetry.Unknown) {
                    map[tile.getMapLocation().x][tile.getMapLocation().y] = (tile.isPassable()) ? 1 : (tile.isWall()) ? 2 : 3;
                    if (!tile.isPassable()) Utilities.validateSymmetry(tile.getMapLocation(), tile.hasRuin());
                }
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
                    if (!invalidResourceCenters[tileLoc.x][tileLoc.y] && validatePlacement(tileLoc)) {
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
        if (claimedRuin != null && staticRC.canSenseLocation(claimedRuin) && staticRC.canSenseRobotAtLocation(claimedRuin)) {
            claimedRuin = null;
        }
    }
}