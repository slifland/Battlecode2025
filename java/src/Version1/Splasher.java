package Version1;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

import static Version0.RobotPlayer.directions;
import static Version0.RobotPlayer.rng;

public class Splasher {
    private static MapLocation home;
    public static void runSplasher(RobotController rc) throws GameActionException {
        //set home to where you are when you are spawned
        if(home == null) home = rc.getLocation();
        //determine best place to splash, then do it if possible
        MapLocation loc = bestSplash(rc);
        if(rc.canAttack(loc)) {
            rc.attack(loc);
        }
        //random movement for now
        if(rc.canMove(rc.getLocation().directionTo(loc))) {
            rc.move(rc.getLocation().directionTo(loc));
        }
        //if we are running out of paint, definitely go home?
        if(rc.getPaint() < 100) {
            goHome(rc);
        }
    }
    public static void goHome(RobotController rc) throws GameActionException {
        //steal paint for use
        for(Direction d : Direction.values()) {
            if(rc.canTransferPaint(rc.getLocation().add(d), -100)) {
                rc.transferPaint(rc.getLocation().add(d), -100);
                break;
            }
        }
        Direction d = rc.getLocation().directionTo(home);
        if(rc.canMove(d)) {
            rc.move(d);
        }
    }

    public static MapLocation bestSplash(RobotController rc) throws GameActionException {
        Direction dir = directions[rng.nextInt(directions.length)];
        MapLocation nextLoc = rc.getLocation().add(dir);
        return nextLoc;
    }
}
