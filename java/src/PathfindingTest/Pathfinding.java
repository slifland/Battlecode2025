package PathfindingTest;

import battlecode.common.*;
public class Pathfinding
{
    //Bug nav variables
    static MapLocation previousDestination = null;
    static boolean turningRight = true;
    static DirectionStack directionStack = new DirectionStack();
    static ObstacleMap obstacles;
    static int lastResetRound;
    static int maxSimBytecode = 2000;


    //BFS variables
    static int closestDistanceOnPath = Integer.MAX_VALUE;

    static int RESET_THRESHOLD = 100;

    //map knowledge stuff
    static MapData[][] mapKnowledge;
    enum MapData
    {
        Passable,
        Impassable
    }


    public static void initializeBugBFS(RobotController rc, MapLocation destination)
    {
        directionStack.clear();
        obstacles = new ObstacleMap(rc.getMapWidth(), rc.getMapHeight());
        previousDestination = destination;
        lastResetRound = rc.getRoundNum();
        closestDistanceOnPath = Integer.MAX_VALUE;
    }

    public static Direction bugBFS(RobotController rc, MapLocation destination) throws GameActionException
    {
        return bugBFSInternal(rc, destination, -1);
    }

    public static Direction bugBFS(RobotController rc, MapLocation destination, int bytecodeLimit) throws GameActionException
    {
        return bugBFSInternal(rc, destination, bytecodeLimit);
    }

    private static Direction bugBFSInternal(RobotController rc, MapLocation destination, int bytecodeLimit) throws GameActionException
    {
        if(!destination.equals(previousDestination) || rc.getRoundNum() - lastResetRound >= RESET_THRESHOLD)
        {
            initializeBugBFS(rc, destination);
        }

        if(rc.getLocation().equals(destination) || !rc.isMovementReady())
        {
            return Direction.CENTER;
        }

        Direction direction = BFS_7x7.pathfind(rc, destination);
        if(direction != null)
        {
            rc.setIndicatorString("BFS");
            return direction;
        }
        else
        {
            rc.setIndicatorString("bugNav");
            if(bytecodeLimit == -1)
            {
                bytecodeLimit = 17500;
            }
            maxSimBytecode = Math.max(0, bytecodeLimit - Clock.getBytecodeNum());
            return bugNav(rc, destination);
        }
    }

    public static Direction bugNav(RobotController rc, MapLocation destination) throws GameActionException
    {
        if(!directionStack.isEmpty())
        {
            Direction currentDirection = directionStack.peek();
            while(!rc.canMove(currentDirection))
            {
                if(turningRight)
                {
                    currentDirection = currentDirection.rotateRight();
                }
                else
                {
                    currentDirection = currentDirection.rotateLeft();
                }

                /*
                    We don't want to follow a border all the way around so let's turn around and go the opposite way
                 */
                if(!rc.onTheMap(rc.getLocation().add(currentDirection)))
                {
                    turningRight = !turningRight;
                    directionStack.clear();
                    return bugNav(rc, destination);
                }

                directionStack.push(currentDirection);
                if(directionStack.isFull())
                {
                    directionStack.clear();
                    return Direction.CENTER;
                }
            }
            return directionStack.pop();
        }

        Direction dirTo = rc.getLocation().directionTo(destination);
        if(rc.canMove(dirTo))
        {
            return dirTo;
        }
        else
        {
            turningRight = decideTurningDirection(rc, destination);
            Direction currentDirection = dirTo;
            directionStack.push(currentDirection);
            while(!rc.canMove(currentDirection))
            {
                if(turningRight)
                {
                    currentDirection = currentDirection.rotateRight();
                }
                else
                {
                    currentDirection = currentDirection.rotateLeft();
                }
                directionStack.push(currentDirection);
                if(directionStack.isFull())
                {
                    directionStack.clear();
                    return Direction.CENTER;
                }
            }
            return directionStack.pop();
        }
    }

    public static boolean decideTurningDirection(RobotController rc, MapLocation destination) throws GameActionException
    {
        MapLocation currentLocation = rc.getLocation();
        //if we see come to a new obstacle, decide the best turning direction and put in in the map
        if(obstacles.obstacleExists(currentLocation))
        {
            boolean bestTurningDirection = bestTurnDirection(rc, destination);
            obstacles.setTurnDirection(currentLocation, bestTurningDirection);
            return bestTurningDirection;
        }
        else
        {
            boolean previousDirection = obstacles.getTurnDirection(currentLocation);
            obstacles.setTurnDirection(currentLocation, !previousDirection);
            return !previousDirection;
        }
//        return bestTurnDirection(rc, destination);
    }


    public static boolean bestTurnDirection(RobotController rc, MapLocation destination) throws GameActionException
    {
        VirtualBug rightBug = new VirtualBug(rc.getLocation(), true, destination);
        VirtualBug leftBug = new VirtualBug(rc.getLocation(), false, destination);

        boolean continueRight = true;
        boolean continueLeft = true;
        int startBytecode = Clock.getBytecodesLeft();
        int maxBytecodeUsage = maxSimBytecode - 500;
        while(startBytecode - Clock.getBytecodesLeft() < maxBytecodeUsage && (continueRight || continueLeft))
        {
            if(continueRight)
                continueRight = rightBug.step(rc);
            if(continueLeft)
                continueLeft = leftBug.step(rc);
        }

        return rightBug.distanceToDestination() < leftBug.distanceToDestination();
    }

    private static int distanceAfterSimulation(RobotController rc, MapLocation destination, boolean turningRight) throws GameActionException
    {
        MapLocation virtualBug = rc.getLocation();
        DirectionStack virtualStack = new DirectionStack();

        int cutOff = 20;
        int i = 0;
        while(i < cutOff && mapKnowledge[virtualBug.x][virtualBug.y] != null)
        {
            int bytecodes = Clock.getBytecodesLeft();
            if(virtualBug.equals(destination))
            {
                break;
            }

            if(!virtualStack.isEmpty())
            {
                Direction currentDirection = virtualStack.peek();
                while(!virtualCanMove(rc, virtualBug, currentDirection))
                {
                    if(turningRight)
                        currentDirection = currentDirection.rotateRight();
                    else
                        currentDirection =  currentDirection.rotateLeft();
                    virtualStack.push(currentDirection);
                    if(virtualStack.isFull())
                    {
                        virtualStack.clear();
                        virtualStack.push(Direction.CENTER);
                        break;
                    }
                }
                virtualBug = virtualBug.add(virtualStack.pop());
//                rc.setIndicatorDot(virtualBug, 255, 0, 0);
                i++;
                continue;
            }

            Direction dirTo = virtualBug.directionTo(destination);
            if(virtualCanMove(rc, virtualBug, dirTo))
            {
                virtualBug = virtualBug.add(dirTo);
//                rc.setIndicatorDot(virtualBug, 255, 0, 0);
                i++;
                continue;

            }
            else
            {
                Direction currentDirection = dirTo;
                while(!virtualCanMove(rc, virtualBug, currentDirection))
                {
                    if(turningRight)
                        currentDirection = currentDirection.rotateRight();
                    else
                        currentDirection =  currentDirection.rotateLeft();
                    virtualStack.push(currentDirection);
                    if(virtualStack.isFull())
                    {
                        virtualStack.clear();
                        virtualStack.push(Direction.CENTER);
                        break;
                    }
                }
            }
            virtualBug = virtualBug.add(virtualStack.pop());
//            rc.setIndicatorDot(virtualBug, 255, 0, 0);
            i++;

            System.out.println(bytecodes - Clock.getBytecodesLeft());
        }


        return virtualBug.distanceSquaredTo(destination);
    }


    static boolean virtualCanMove(RobotController rc, MapLocation start, Direction direction)
    {
        MapLocation location = start.add(direction);
        if(rc.onTheMap(location))
        {
            return mapKnowledge[location.x][location.y] == null || mapKnowledge[location.x][location.y] == MapData.Passable;
        }
        else
        {
            return false;
        }

    }


}

class DirectionStack
{
    private Direction[] directionStack;
    private int stackPointer = 0;

    public DirectionStack()
    {
        directionStack = new Direction[8];
    }

    public void push(Direction direction)
    {
        directionStack[stackPointer] = direction;
        stackPointer++;
    }

    public Direction pop()
    {
        stackPointer--;
        return directionStack[stackPointer];
    }

    public Direction peek()
    {
        return directionStack[stackPointer - 1];
    }

    public boolean isEmpty()
    {
        return stackPointer == 0;
    }

    public boolean isFull()
    {
        return stackPointer == 8;
    }

    public void clear()
    {
        stackPointer = 0;
    }

    public void displayDirections(RobotController rc) throws GameActionException
    {
        System.out.println(stackPointer);
        for(int i = 0; i < stackPointer; i++)
        {
            if(rc.onTheMap(rc.getLocation().add(directionStack[i])))
            {
                rc.setIndicatorDot(rc.getLocation().add(directionStack[i]), 255, 255, 0);
            }
        }
    }
}

class ObstacleMap
{
    enum TurnDirections
    {
        RIGHT,
        LEFT
    }
    private TurnDirections[][] obstacles;

    public ObstacleMap(int width, int height)
    {
        obstacles = new TurnDirections[width][height];
    }

    public boolean obstacleExists(MapLocation location)
    {
        return obstacles[location.x][location.y] == null;
    }

    public boolean getTurnDirection(MapLocation location)
    {
        return obstacles[location.x][location.y].equals(TurnDirections.RIGHT);
    }

    public void setTurnDirection(MapLocation location, boolean turningRight)
    {
        obstacles[location.x][location.y] = turningRight ? TurnDirections.RIGHT : TurnDirections.LEFT;
    }
}

class VirtualBug
{
    public MapLocation virtualBug;
    boolean turningRight;
    MapLocation destination;
    DirectionStack virtualStack;

    public VirtualBug(MapLocation start, boolean turningRight, MapLocation destination)
    {
        this.virtualBug = start;
        this.turningRight = turningRight;
        this.destination = destination;
        virtualStack = new DirectionStack();
    }

    //returns whether to continue
    public boolean step(RobotController rc) throws GameActionException
    {
        if(virtualBug.equals(destination) || Pathfinding.mapKnowledge[virtualBug.x][virtualBug.y] == null)
        {
            return false;
        }

        if(!virtualStack.isEmpty())
        {
            Direction currentDirection = virtualStack.peek();
            while(!Pathfinding.virtualCanMove(rc, virtualBug, currentDirection))
            {
                if(turningRight)
                    currentDirection = currentDirection.rotateRight();
                else
                    currentDirection =  currentDirection.rotateLeft();
                virtualStack.push(currentDirection);
                if(virtualStack.isFull())
                {
                    virtualStack.clear();
                    virtualStack.push(Direction.CENTER);
                    break;
                }
            }
            virtualBug = virtualBug.add(virtualStack.pop());
//            rc.setIndicatorDot(virtualBug, 255, 0, 0);
            return true;
        }

        Direction dirTo = virtualBug.directionTo(destination);
        if(Pathfinding.virtualCanMove(rc, virtualBug, dirTo))
        {
            virtualBug = virtualBug.add(dirTo);
//            rc.setIndicatorDot(virtualBug, 255, 0, 0);
            return false;
        }
        else
        {
            Direction currentDirection = dirTo;
            while(!Pathfinding.virtualCanMove(rc, virtualBug, currentDirection))
            {
                if(turningRight)
                    currentDirection = currentDirection.rotateRight();
                else
                    currentDirection =  currentDirection.rotateLeft();
                virtualStack.push(currentDirection);
                if(virtualStack.isFull())
                {
                    virtualStack.clear();
                    virtualStack.push(Direction.CENTER);
                    return false;
                }
            }
            virtualBug = virtualBug.add(virtualStack.pop());
//            rc.setIndicatorDot(virtualBug, 255, 0, 0);
            return true;
        }
    }

    public int distanceToDestination()
    {
        return virtualBug.distanceSquaredTo(destination);
    }

}
