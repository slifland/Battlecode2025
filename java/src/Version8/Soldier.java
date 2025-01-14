package Version8;

import Version6.BFS_7x7;
import battlecode.common.*;
import battlecode.schema.RobotType;

import static Version8.RobotPlayer.*;

public class Soldier {
    enum SoldierState {
        Explore,    //We have nowhere to go to, let's explore
        Navigate,   //We have a target in mind, let's pursue it
        Refill,     //We need paint, let's go back to our own tower
        Contest,    //We are on the frontlines, let's stop moppers from taking our paint
        Tower,     //We are near a tower, let's use micro to efficiently fight them
    }

    static SoldierState currentState;
    static MapLocation target;

    //Constants
    static float refillThreshold = 15;

    public static void runSoldier(RobotController rc) throws GameActionException{
        //update the state
        updateState(rc);
        //run the state
        switch(currentState)
        {
            case Explore -> explore(rc);
            case Navigate -> navigate(rc);
            case Refill -> refill(rc);
            case Contest -> contest(rc);
            case Tower -> tower(rc);
        }
    }
    public static void updateState(RobotController rc)
    {
        //first let's check if we're low on paint
        if(rc.getPaint() < refillThreshold)
        {
            currentState = SoldierState.Refill;
            return;
        }

        //next let's check if there's a tower in sight
        for(RobotInfo enemy : enemyRobots)
        {
            if(enemy.getType().isTowerType())
            {
                target = enemy.location;
                currentState = SoldierState.Tower;
                return;
            }
        }

        //third we'll check if we're on the frontlines of battle
        if(enemyRobots.length > 0)
        {
            currentState = SoldierState.Contest;
            return;
        }


        //TODO: when nathan updates comms use seperate list for byte code reasons
        //fourth we'll check if we have any objectives to navigate to

        //We have an ememy ruin in memory, let's go there
        for(Ruin ruin : Communication.alliedPaintTowers)
        {

        }

        //We have an enemy paint average we can see, let's follow it
        MapLocation[] paintAverages = Utilities.getEnemyPaintAverages();
        if(paintAverages.length == 1)
        {
            currentState = SoldierState.Navigate;
            target = paintAverages[0];
            return;
        }
        else if(paintAverages.length == 2)
        {
            currentState = SoldierState.Navigate;
            if(rc.getLocation().distanceSquaredTo(paintAverages[0]) <
                    rc.getLocation().distanceSquaredTo(paintAverages[1]))
            {
                target  = paintAverages[0];
            }
            else
            {
                target = paintAverages[1];
            }
            return;
        }
        //if all prior are false we should explore
        currentState = SoldierState.Explore;
    }

    public static void explore(RobotController rc)
    {

    }

    public static void navigate(RobotController rc) throws GameActionException
    {
        Direction directionToTarget = BFS_7x7.pathfind(rc, target);
        if(rc.canMove(directionToTarget))
        {
            rc.move(directionToTarget);
        }
    }

    public static void refill(RobotController rc)
    {
        //navigate back towards an allied ruin
        //find the nearest tower and try and navigate to that
        //
    }

    public static void contest(RobotController rc)
    {

    }

    public static void tower(RobotController rc)
    {
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
