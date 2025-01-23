package Version11;

import battlecode.common.*;
public class Pathfinding
{
    //Bug nav variables
    static MapLocation previousDestination = null;
    static boolean turningRight = true;
    static DirectionStack directionStack = new DirectionStack();
    static ObstacleMap obstacles;
    static int lastResetRound;

    //BFS variables
    static int closestDistanceOnPath = Integer.MAX_VALUE;

    static int RESET_THRESHOLD = 100;


    public static void initializeBugBFS(RobotController rc, MapLocation destination)
    {
        directionStack.clear();
        obstacles = new ObstacleMap(rc.getMapWidth(), rc.getMapHeight());
        previousDestination = destination;
        lastResetRound = rc.getRoundNum();
        closestDistanceOnPath = Integer.MAX_VALUE;
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

    public static Direction bugBFS(RobotController rc, MapLocation destination) throws GameActionException
    {
        if(!destination.equals(previousDestination) || rc.getRoundNum() - lastResetRound >= RESET_THRESHOLD)
        {
            initializeBugBFS(rc, destination);
        }

        if(rc.getLocation().equals(destination))
        {
            return Direction.CENTER;
        }

        Direction direction = BFS_7x7.pathfind(rc, destination);
        if(direction != null)
        {
            return direction;
        }
        else
        {
            return bugNav(rc, destination);
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
    }


    public static boolean bestTurnDirection(RobotController rc, MapLocation destination) throws GameActionException
    {
        int finalDistanceRight = distanceAfterSimulation(rc, destination, true);
        int finalDistanceLeft = distanceAfterSimulation(rc, destination, false);

        return finalDistanceRight < finalDistanceLeft;
    }

    private static int distanceAfterSimulation(RobotController rc, MapLocation destination, boolean turningRight) throws GameActionException
    {
        MapLocation virtualBug = rc.getLocation();
        DirectionStack virtualStack = new DirectionStack();

        int cutOff = 5;
        int i = 0;
        while(i < cutOff)
        {
            if(virtualBug.equals(destination) || !rc.canSenseLocation(virtualBug))
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
                    if(virtualStack.isFull())
                    {
                        virtualStack.clear();
                        virtualStack.push(Direction.CENTER);
                        break;
                    }
                    virtualStack.push(currentDirection);
                }
                virtualBug = virtualBug.add(virtualStack.pop());
                i++;
                continue;

            }

            Direction dirTo = virtualBug.directionTo(destination);
            if(virtualCanMove(rc, virtualBug, dirTo))
            {
                virtualBug = virtualBug.add(dirTo);
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
            i++;
        }

        return virtualBug.distanceSquaredTo(destination);
    }


    static boolean virtualCanMove(RobotController rc, MapLocation start, Direction direction) throws GameActionException
    {
        MapLocation location = start.add(direction);
        if(!rc.onTheMap(location))
        {
            return false;
        }
        else if(!rc.canSenseLocation(location))
        {
            return true;
        }

        return rc.sensePassability(location) && !rc.canSenseRobotAtLocation(location);
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
