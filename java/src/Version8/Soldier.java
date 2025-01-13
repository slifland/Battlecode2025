package Version8;

import battlecode.common.*;

import java.awt.*;

import static Version8.RobotPlayer.*;

enum states {
}

public class Soldier {

    public static void runSoldier(RobotController rc) throws GameActionException{
        
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
