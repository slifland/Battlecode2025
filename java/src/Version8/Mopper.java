package Version8;

import battlecode.common.*;
import battlecode.schema.RobotType;

import java.util.ArrayList;

import static Version8.RobotPlayer.*;

enum mopStates {
}

public class Mopper {

    public static void runMopper(RobotController rc) throws GameActionException {

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
