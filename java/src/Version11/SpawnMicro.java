package Version11;

import battlecode.common.*;

public class SpawnMicro {
    //spawn according to these priorities:
    //1. spawn on ally paint - 0b1000
    //2. spawn towards the center - 0b100
    //3. spawn on neutral paint - 0b10
    //4. spawn anywhere - 0b1
    public static MapLocation bestSpawn(RobotController rc) throws GameActionException {
        int bestScore = 0;
        MapLocation bestTile = null;
        Direction dirToCenter = rc.getLocation().directionTo(new MapLocation(rc.getMapWidth(), rc.getMapHeight()));
        MapInfo[] potentialTiles = rc.senseNearbyMapInfos(2);
//        for(int i = 0; i < 9; i++) {
//            if(i == 4) continue;
//            MapLocation tileLoc = potentialTiles[i].getMapLocation();
//            Direction dirToSquare = rc.getLocation().directionTo(tileLoc);
//            int score = 0;
//            PaintType paint = potentialTiles[i].getPaint();
//            score |= switch(paint) {
//                case EMPTY -> 0b10;
//                case ALLY_PRIMARY, ALLY_SECONDARY -> 0b1000;
//                default -> 0;
//            };
//            if(!rc.canSenseRobotAtLocation(tileLoc)) {
//                score |= 0b1;
//            }
//            if(dirToSquare == dirToCenter || dirToSquare == dirToCenter.rotateLeft() || dirToSquare == dirToCenter.rotateRight()) {
//                score |= 0b100;
//            }
//            if(score > bestScore) {
//                bestScore = score;
//                bestTile = tileLoc;
//            }
//        }
        for(int i = 0; i < 9; i++) {
            if(i == 4) continue;
            MapLocation tileLoc = potentialTiles[i].getMapLocation();
            Direction dirToSquare = rc.getLocation().directionTo(tileLoc);
            int score = 0;
            PaintType paint = potentialTiles[i].getPaint();
            score |= switch(paint) {
                case EMPTY -> 0b10;
                case ALLY_PRIMARY, ALLY_SECONDARY -> 0b1000;
                default -> 0;
            };
            if(!rc.canSenseRobotAtLocation(tileLoc)) {
                score |= 0b1;
            }
            if(dirToSquare == dirToCenter || dirToSquare == dirToCenter.rotateLeft() || dirToSquare == dirToCenter.rotateRight()) {
                score |= 0b100;
            }
            if(score > bestScore) {
                bestScore = score;
                bestTile = tileLoc;
            }
        }
        return bestTile;
    }
}
