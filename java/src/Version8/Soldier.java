package Version8;

import battlecode.common.*;
import battlecode.schema.RobotType;

import java.util.Map;

import static Version8.RobotPlayer.*;

public class Soldier {
    enum SoldierState {
        Explore,        //We have nowhere to go to, let's explore
        Navigate,       //We have a target in mind, let's pursue it
        Refill,         //We need paint, let's go back to our own tower
        Contest,        //We are on the frontlines, let's stop moppers from taking our paint
        Tower,          //We are near a tower, let's use micro to efficiently fight them
        RuinBuilding,   //We are near a ruin and we want to paint around it
    }

    static SoldierState currentState;
    static MapLocation target;


    //Constants
    static int refillThreshold = 15;    //Paint level at which soldiers go to refill
    static int doneRefillingThreshold = 70;    //Paint level at which soldiers can stop refilling
    static int attackThreshold = 2;     //Number of soldiers we need to rush a tower
    static int chipThreshold = 500; //Build money towers if we have fewer chips than the threshold

    public static void runSoldier(RobotController rc) throws GameActionException{
        //update the state
        updateState(rc);
        //run the state
        switch(currentState)
        {
            case Explore -> explore(rc);
            case Navigate -> navigate(rc);
            case Refill -> refill(rc);
            case Tower -> tower(rc);
            case RuinBuilding -> ruinBuilding(rc);
        }
    }
    public static void updateState(RobotController rc) throws GameActionException
    {

        //first let's check if we're low on paint
        //we don't want to leave until we have enough paint
        if(currentState.equals(SoldierState.Refill) && rc.getPaint() < doneRefillingThreshold)
        {
            return;
        }

        if(rc.getPaint() < refillThreshold && !Communication.alliedPaintTowers.isEmpty())
        {
            currentState = SoldierState.Refill;
            return;
        }

        //next let's check if there's an enemy tower in sight
        for(RobotInfo enemy : enemyRobots)
        {
            if(enemy.getType().isTowerType())
            {
                target = enemy.location;
                currentState = SoldierState.Tower;
                return;
            }
        }

        //third let's check if we should try and build a ruin
        for(MapLocation ruin : nearbyRuins)
        {
            RobotInfo tempRobot = rc.senseRobotAtLocation(ruin);
            if(tempRobot == null)
            {
                target = ruin;
                currentState = SoldierState.RuinBuilding;
                return;
            }
        }

        //fourth we'll see if we should go for any open ruins
        int closestDistance = Integer.MAX_VALUE;
        MapLocation closestLocation = null;
        for(Ruin ruin : Communication.unclaimedRuins)
        {
            int tempDistance = rc.getLocation().distanceSquaredTo(ruin.location);
            if(tempDistance < closestDistance)
            {
                closestDistance = tempDistance;
                closestLocation = ruin.location;
            }
        }

        if(closestLocation != null)
        {
            currentState = SoldierState.Navigate;
            target = closestLocation;
            return;
        }

        //We have an ememy ruin in memory, let's go there
        for(Ruin ruin : Communication.enemyTowers)
        {
            int tempDistance = rc.getLocation().distanceSquaredTo(ruin.location);
            if(tempDistance < closestDistance)
            {
                closestDistance = tempDistance;
                closestLocation = ruin.location;
            }
        }

        if(closestLocation != null)
        {
            currentState = SoldierState.Navigate;
            target = closestLocation;
            return;
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

    public static void explore(RobotController rc) throws GameActionException // use symmetry for this method
    {
        Symmetry symmetryToUse = Utilities.possibleSymmetry()[0];

        int closestDistance = Integer.MAX_VALUE;
        MapLocation closestLocation = null;

        for(Ruin tower : Communication.alliedPaintTowers)
        {
            int tempDistance = Utilities.oppositeLocation(rc, tower.location, symmetryToUse)
                    .distanceSquaredTo(rc.getLocation());
            if(tempDistance < closestDistance)
            {
                closestDistance = tempDistance;
                closestLocation = Utilities.oppositeLocation(rc, tower.location, symmetryToUse);
            }
        }

        if(closestLocation != null)
        {
            Direction directionToMove = BFS_7x7.pathfind(rc, closestLocation);
            if(rc.canMove(directionToMove))
            {
                rc.move(directionToMove);
            }
        }
    }

    public static void navigate(RobotController rc) throws GameActionException
    {

        Direction directionToTarget = BFS_7x7.pathfind(rc, target);
        if(rc.canMove(directionToTarget))
        {
            rc.move(directionToTarget);
        }

        attemptFill(rc);
    }

    //TODO: implement congestion control and prio q
    public static void refill(RobotController rc) throws GameActionException
    {
        //navigate back towards an allied ruin
        //find the nearest tower and try and navigate to that
        int closestDistance = Integer.MAX_VALUE;
        MapLocation closestLocation = null;
        for(Ruin tower : Communication.alliedPaintTowers)
        {
            int tempDistance = rc.getLocation().distanceSquaredTo(tower.location);
            if(tempDistance < closestDistance)
            {
                closestDistance = tempDistance;
                closestLocation = tower.location;
            }
        }

        if(rc.getLocation().isAdjacentTo(closestLocation))
        {
           int paintToTransfer = Math.min(rc.getType().paintCapacity - rc.getPaint(),
                   rc.senseRobotAtLocation(closestLocation).paintAmount);
           if(rc.canTransferPaint(closestLocation, -1 * paintToTransfer))
           {
               rc.transferPaint(closestLocation, -1 * paintToTransfer);
           }
        }
        else
        {
            Direction dirToMove = BFS_7x7.pathfind(rc, closestLocation);
            if(rc.canMove(dirToMove))
            {
                rc.move(dirToMove);
            }
        }
    }

    public static void contest(RobotController rc)
    {
        //if we're low health let's just retreat and look for other unfilled spots
        //otherwise let's be aggressive and navigate towards enemy ruins

    }

    public static void tower(RobotController rc) throws GameActionException
    {
        int numSoldiers = 0;
        for(RobotInfo robot : allyRobots)
        {
            if(robot.getType().equals(UnitType.SOLDIER))
            {
                numSoldiers++;
            }
        }

        if(rc.canAttack(target))
        {
            rc.attack(target);
        }

        Direction directionToMove;
        if(numSoldiers > attackThreshold)
        {
            directionToMove = Micro.runMicro(rc, true);
        }
        else
        {
            directionToMove = Micro.runMicro(rc, false);
        }
        if(rc.canMove(directionToMove))
        {
            rc.move(directionToMove);
        }

        attemptFill(rc);
    }

    public static void ruinBuilding(RobotController rc) throws GameActionException
    {
        Direction directionToMove = BFS_7x7.pathfind(rc, target);
        if(rc.canMove(directionToMove))
        {
            rc.move(directionToMove);
        }

        boolean[][] possiblePattern = Utilities.inferPatternFromExistingSpots(rc, target, rc.senseNearbyMapInfos(target, 8));
        if(possiblePattern == null) //we need to choose the pattern
        {
            if(rc.getChips() < chipThreshold)
            {
                possiblePattern = rc.getTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER);
            }
            else
            {
                possiblePattern = rc.getTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER);
            }
        }

        MapInfo[] possiblePaintLocations = rc.senseNearbyMapInfos(target, 8);
        for(MapInfo location : possiblePaintLocations)
        {
            MapLocation tempLocation = location.getMapLocation();
            boolean isSecondary = possiblePattern[tempLocation.y - rc.getLocation().y][tempLocation.x - rc.getLocation().x];
            if(!location.getPaint().isEnemy() &&
                    location.getPaint().isSecondary() == isSecondary &&
                    rc.canAttack(location.getMapLocation()))
            {
                rc.attack(tempLocation, isSecondary);
                break;
            }
        }
    }
    public static void attemptFill(RobotController rc) throws GameActionException
    {
//        if(rc.isActionReady())
//        {
//            PaintFill.attemptFill(rc, rc.getLocation());
//        }

        boolean[][] pattern = rc.getResourcePattern();

        if(rc.isActionReady())
        {
            for(MapInfo location : rc.senseNearbyMapInfos(rc.getLocation(), UnitType.SOLDIER.actionRadiusSquared))
            {
                MapLocation tempLocation = location.getMapLocation();
                boolean isSecondary = Utilities.getColorFromOriginPattern(tempLocation, pattern);
                if(rc.canPaint(tempLocation) && rc.canAttack(tempLocation) &&
                        (!location.getPaint().isAlly() || location.getPaint().isSecondary() != isSecondary))
                {
                    rc.attack(tempLocation);
                    return;
                }
            }
        }
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
