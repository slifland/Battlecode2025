package Version8;

import battlecode.common.*;

import java.util.HashSet;

import static Version8.RobotPlayer.*;

enum splasherStates {
}

public class Splasher {
    public static void runSplasher(RobotController rc) throws GameActionException {

    }

    //checks whether a space is in firing range of any seen enemy towers
    public static boolean isSafeFromTower(RobotController rc, MapLocation loc) {
        for(RobotInfo enemy : enemyRobots) {
            if(enemy.type.isTowerType()) {
                if(loc.distanceSquaredTo(enemy.getLocation()) <= enemy.getType().actionRadiusSquared) {
                    return false;
                }
            }
        }
        return true;
    }

}
