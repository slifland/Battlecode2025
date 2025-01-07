package Version1;

import battlecode.common.*;

import static Version1.RobotPlayer.*;

public class Mopper {
    /**
     * Run a single turn for a Mopper.
     * This code is wrapped inside the infinite loop in run(), so it is called once per turn.
     */
    public static void runMopper(RobotController rc) throws GameActionException {
        RobotInfo[] enemyRobots = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        if(rc.isActionReady() && enemyRobots.length > 0) {
            if(rc.canMove(rc.getLocation().directionTo(enemyRobots[0].getLocation())) && !isEnemyTile(rc.senseMapInfo(enemyRobots[0].getLocation()))){
                rc.move(rc.getLocation().directionTo(enemyRobots[0].getLocation()));
            }
        }
        // Move and attack randomly.
        Direction dir = directions[rng.nextInt(directions.length)];
        MapLocation nextLoc = rc.getLocation().add(dir);
        if(rc.canAttack(nextLoc) && isEnemyTile(rc.senseMapInfo(nextLoc))){
            rc.attack(nextLoc);
        }
        else if (rc.canMove(dir) && !isEnemyTile(rc.senseMapInfo(nextLoc))){
            rc.move(dir);
        }
        if(enemyRobots.length > 0){
            if(rc.canAttack(enemyRobots[0].getLocation())){
                rc.attack(enemyRobots[0].getLocation());
            }
            else if (rc.canMopSwing(dir)){
                rc.mopSwing(dir);
                System.out.println("Mop Swing! Booyah!");
            }
        }

        // We can also move our code into different methods or classes to better organize it!
        updateEnemyRobots(rc);
    }

    //returns whether a location is controlled by the enemy
    public static boolean isEnemyTile(MapInfo loc) {
        PaintType temp = loc.getPaint();
        return temp == PaintType.ENEMY_PRIMARY || temp == PaintType.ENEMY_SECONDARY;
    }
}
