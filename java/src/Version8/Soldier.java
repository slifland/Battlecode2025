package Version8;

import battlecode.common.*;

import java.util.HashSet;

import static Version8.RobotPlayer.*;

public class Soldier {
    enum SoldierState {
        Explore,        //We have nowhere to go to, let's explore
        Navigate,       //We have a target in mind, let's pursue it
        Refill,         //We need paint, let's go back to our own tower
        Tower,          //We are near a tower, let's use micro to efficiently fight them
        RuinBuilding,   //We are near a ruin and we want to paint around it
    }

    static SoldierState currentState = SoldierState.Explore;
    static MapLocation target;
    static HashSet<MapLocation> completedRuins = new HashSet<>();
    static MapLocation averageUnfilledLocation;
    static MapLocation closestRuin;
    static boolean canSeeEmpty;
    static MapLocation currentSector;

    public static MapLocation averageEnemyPaint;


    //Constants
    static int refillThreshold = 30;    //Paint level at which soldiers go to refill
    static int doneRefillingThreshold = 150;    //Paint level at which soldiers can stop refilling
    static int attackThreshold = 2;     //Number of soldiers we need to rush a tower
    static int chipThreshold = 0; //Build money towers if we have fewer chips than the threshold
    static int congestionThreshold = 2;
    public static int randomRadius = 300;

    public static void runSoldier(RobotController rc) throws GameActionException
    {
        attemptCompleteTowerPattern(rc);
        updateInfo(rc);
        //update the state
        updateState(rc);
        //run the state
        switch(currentState)
        {
            case Explore ->
            {
                rc.setIndicatorString("Exploring");
                explore(rc);
            }
            case Navigate ->
            {
                rc.setIndicatorString("Navigating");
                navigate(rc);
            }
            case Refill ->
            {
                rc.setIndicatorString("Refilling");
                refill(rc);
            }
            case Tower ->
            {
                rc.setIndicatorString("Attacking Tower");
                tower(rc);
            }
            case RuinBuilding ->
            {
                rc.setIndicatorString("Building a Ruin");
                ruinBuilding(rc);
            }
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

        //if there's any ruins which can be completed go complete those
        //else if there's any other ruins which can be painted go to those

        if(rc.getChips() > UnitType.LEVEL_ONE_PAINT_TOWER.moneyCost && !completedRuins.isEmpty())
        {
            int closestDistance = Integer.MAX_VALUE;
            MapLocation closestLocation = null;
            for(MapLocation location : completedRuins)
            {
                int tempDistance = rc.getLocation().distanceSquaredTo(location);
                if(tempDistance < closestDistance)
                {
                    closestDistance = tempDistance;
                    closestLocation = location;
                }
            }

            if(closestLocation != null)
            {
                currentState = SoldierState.RuinBuilding;
                return;
            }
        }

        for(MapLocation ruin : nearbyRuins)
        {
            RobotInfo tempRobot = rc.senseRobotAtLocation(ruin);
            if(tempRobot == null && !completedRuins.contains(ruin))
            {
                target = ruin;
                currentState = SoldierState.RuinBuilding;
                return;
            }
            else if(tempRobot != null)
            {
                completedRuins.remove(ruin);
            }
        }

        //fourth we'll see if we should go for any open ruins
        int closestDistance = Integer.MAX_VALUE;
        MapLocation closestLocation = null;

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
        if(currentSector == null || rc.getRoundNum() % 20 == 0)
        {
            currentSector = generateExploreLocation(rc);
        }

        paintMove(rc, currentSector);
        return;


//        Symmetry symmetryToUse = Utilities.possibleSymmetry()[0];
//
//        int closestDistance = Integer.MAX_VALUE;
//        MapLocation closestLocation = null;
//
//        for(Ruin tower : Communication.alliedPaintTowers)
//        {
//            int tempDistance = Utilities.oppositeLocation(rc, tower.location, symmetryToUse)
//                    .distanceSquaredTo(rc.getLocation());
//            if(tempDistance < closestDistance)
//            {
//                closestDistance = tempDistance;
//                closestLocation = Utilities.oppositeLocation(rc, tower.location, symmetryToUse);
//            }
//        }
//
//        if(closestLocation != null)
//        {
//            directionToMove = BFS_7x7.pathfind(rc, closestLocation);
//            if(rc.canMove(directionToMove))
//            {
//                rc.move(directionToMove);
//            }
//        }
    }

    public static void navigate(RobotController rc) throws GameActionException
    {
        paintMove(rc, target);
        attemptFill(rc);
    }

    //TODO: implement congestion control and prio q
    public static void refill(RobotController rc) throws GameActionException
    {
        //navigate back towards an allied ruin
        //find the nearest tower and try and navigate to that
        int closestDistance = Integer.MAX_VALUE;
        MapLocation closestLocation = null;
        if(Communication.alliedPaintTowers.size() == 1)
        {
            closestLocation = Communication.alliedPaintTowers.getFirst().location;
        }
        else
        {
            for(Ruin tower : Communication.alliedPaintTowers)
            {
                if(tower.location.isWithinDistanceSquared(rc.getLocation(), GameConstants.VISION_RADIUS_SQUARED))
                {
                    RobotInfo[] robotsRefilling = rc.senseNearbyRobots(tower.location, 2, rc.getTeam());
                    if(robotsRefilling.length > congestionThreshold)
                    {
                        continue;
                    }
                }
                int tempDistance = rc.getLocation().distanceSquaredTo(tower.location);
                if(tempDistance < closestDistance)
                {
                    closestDistance = tempDistance;
                    closestLocation = tower.location;
                }
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

    }

    public static void ruinBuilding(RobotController rc) throws GameActionException
    {
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


        boolean patternComplete = true;
        MapLocation spotToPaint = null;
        boolean spotIsSecondary = false;
        MapInfo[] possiblePaintLocations = rc.senseNearbyMapInfos(target, 8);
        for(MapInfo location : possiblePaintLocations)
        {
            if(location.hasRuin())
            {
                continue;
            }
            MapLocation tempLocation = location.getMapLocation();
            PaintType paint = location.getPaint();
            boolean isSecondary = possiblePattern[tempLocation.y - (target.y - 2)][tempLocation.x - (target.x - 2)];
            if((paint.equals(PaintType.EMPTY) || (paint.isAlly() && paint.isSecondary() != isSecondary)))
            {
                patternComplete = false;
                if(rc.canPaint(tempLocation) && rc.canAttack(tempLocation))
                {
                    spotIsSecondary = isSecondary;
                    spotToPaint = tempLocation;
                }
            }
        }
        int numBuilders = rc.senseNearbyRobots(target, 2, rc.getTeam()).length;
        if(patternComplete && numBuilders == 0)
        {
            MapLocation rand = Utilities.generateRandomLocation(rc, target, 2);
            paintMove(rc, rand, spotIsSecondary);
            attemptFill(rc);
        }
        else if(patternComplete)
        {
            explore(rc);
        }
        else if(spotToPaint != null)
        {
            MapLocation rand = Utilities.generateRandomLocation(rc, target, 2);
            paintMove(rc, rand, spotIsSecondary);
            if(rc.canAttack(spotToPaint))
            {
                rc.attack(spotToPaint);
            }
            attemptFill(rc);
        }
        else
        {
            paintMove(rc, target);
        }

    }
    public static void attemptFill(RobotController rc) throws GameActionException
    {
//        if(rc.isActionReady())
//        {
//            PaintFill.attemptFill(rc, rc.getLocation());
//        }

        if(!rc.isActionReady())
        {
            return;
        }

        boolean[][] pattern = rc.getResourcePattern();

        if(rc.canPaint(rc.getLocation()) && rc.canAttack(rc.getLocation()) &&
                rc.senseMapInfo(rc.getLocation()).getPaint().equals(PaintType.EMPTY))
        {
            boolean isSecondary = Utilities.getColorFromOriginPattern(rc.getLocation(), pattern);
            rc.attack(rc.getLocation(), isSecondary);
            return;
        }

        for(MapInfo location : rc.senseNearbyMapInfos(rc.getLocation(), UnitType.SOLDIER.actionRadiusSquared))
        {
            MapLocation tempLocation = location.getMapLocation();
            PaintType paint = location.getPaint();
            boolean isSecondary = Utilities.getColorFromOriginPattern(tempLocation, pattern);
            if(rc.canPaint(tempLocation) && rc.canAttack(tempLocation))
            {
                if(paint.equals(PaintType.EMPTY))
                {
                    rc.attack(tempLocation, isSecondary);
                    return;
                }

//                else if(paint.isAlly() && paint.isSecondary() != isSecondary
//                && (closestRuin == null || !tempLocation.isWithinDistanceSquared(closestRuin, 8)))
//                {
//                    rc.attack(tempLocation, isSecondary);
//                }
            }
        }
    }

    public static void attemptCompleteTowerPattern(RobotController rc) throws GameActionException
    {
        for(MapLocation ruin : nearbyRuins)
        {
            if(!rc.canSenseRobotAtLocation(ruin) &&
                    rc.getLocation().isWithinDistanceSquared(ruin, UnitType.SOLDIER.actionRadiusSquared))
            {
                if(rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_DEFENSE_TOWER, target))
                {
                    rc.completeTowerPattern(UnitType.LEVEL_ONE_DEFENSE_TOWER, target);
                }

                if(rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, target))
                {
                    rc.completeTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER, target);
                }

                if(rc.canCompleteTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, target))
                {
                    rc.completeTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER, target);
                }
            }
        }
    }

    static void updateInfo(RobotController rc) throws GameActionException
    {
        int closestDistance = Integer.MAX_VALUE;
        for(MapLocation location : nearbyRuins)
        {
            if(!rc.canSenseRobotAtLocation(location))
            {
                int tempDistance = location.distanceSquaredTo(rc.getLocation());
                if (tempDistance < closestDistance)
                {
                    closestDistance = tempDistance;
                    closestRuin = location;
                }
            }
        }

        canSeeEmpty = false;
        int x = 0; int y = 0; int enemyCount = 0; int allyCount = 0;
        //update average unfilled
        for(MapInfo location : nearbyTiles)
        {
            if(!canSeeEmpty &&
                    location.getMapLocation().isWithinDistanceSquared
                            (rc.getLocation(), UnitType.SOLDIER.actionRadiusSquared) &&
            location.getPaint().equals(PaintType.EMPTY))
            {
                canSeeEmpty = true;
            }
            MapLocation tempLocation = rc.getLocation();
            if(tempLocation.x % 4 == 0 && tempLocation.y % 4 == 0 && location.isResourcePatternCenter()
                    && tempLocation.isWithinDistanceSquared(rc.getLocation(), UnitType.SOLDIER.actionRadiusSquared))
            {
                if(rc.canCompleteResourcePattern(tempLocation))
                {
                    rc.completeResourcePattern(tempLocation);
                }
            }

            int index = Sector.getSector(rc, tempLocation);
            if(location.getPaint().isEnemy())
            {
                x += location.getMapLocation().x;
                y += location.getMapLocation().y;
                enemyCount++;
                if(sectors[index] == null)
                {
                    sectors[index] = new Sector(rc, index);
                }
                sectors[index].updateEnemyTiles(enemyCount);
            }
            else if(location.getPaint().isAlly())
            {
                allyCount++;
                if(sectors[index] == null)
                {
                    sectors[index] = new Sector(rc, index);
                }
                sectors[index].updateAllyTiles(enemyCount);
            }
        }
        averageEnemyPaint = (enemyCount != 0) ? new MapLocation(x/enemyCount, y/enemyCount) : null;
    }

    static MapLocation generateExploreLocation(RobotController rc)
    {
        int currentIndex = -1;
        while(currentIndex == -1 || (sectors[currentIndex] != null && sectors[currentIndex].numCovered < 15))
        {
            MapLocation randomLocation = Utilities.generateRandomLocation(rc, rc.getLocation(), randomRadius);
            currentIndex = Sector.getSector(rc, randomLocation);
        }
        return Sector.getSectorCorner(rc, currentIndex);
    }

    static void paintMove(RobotController rc, MapLocation target, boolean isSecondary) throws GameActionException
    {
        Direction directionToMove = BFS_7x7.pathfind(rc, target);
        MapLocation futurePosition = rc.getLocation().add(directionToMove);
        if(rc.canPaint(futurePosition) && rc.canAttack(futurePosition) && !rc.senseMapInfo(futurePosition).getPaint().isAlly())
        {
            rc.attack(futurePosition, isSecondary);
        }
        if(rc.canMove(directionToMove))
        {
            rc.move(directionToMove);
        }
    }

    static void paintMove(RobotController rc, Direction directionToMove) throws GameActionException
    {
        MapLocation futurePosition = rc.getLocation().add(directionToMove);
        if(rc.canPaint(futurePosition) && rc.canAttack(futurePosition))
        {
            rc.attack(futurePosition);
        }
        if(rc.canMove(directionToMove))
        {
            rc.move(directionToMove);
        }
    }

    static void paintMove(RobotController rc, MapLocation target) throws GameActionException
    {
        Direction directionToMove = BFS_7x7.pathfind(rc, target);
        MapLocation futurePosition = rc.getLocation().add(directionToMove);
        if(rc.canPaint(futurePosition) && rc.canAttack(futurePosition) && !rc.senseMapInfo(futurePosition).getPaint().isAlly())
        {
            boolean isSecondary = Utilities.getColorFromOriginPattern(target, rc.getResourcePattern());
            rc.attack(futurePosition, isSecondary);
        }
        if(rc.canMove(directionToMove))
        {
            rc.move(directionToMove);
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
