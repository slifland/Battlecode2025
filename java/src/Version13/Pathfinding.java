package Version13;

import battlecode.common.*;
import static Version13.RobotPlayer.*;
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



    public static Direction bugNav(MapLocation destination) throws GameActionException
    {
        if(!directionStack.isEmpty())
        {
            Direction currentDirection = directionStack.peek();
            while(!staticRC.canMove(currentDirection))
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
                if(!staticRC.onTheMap(staticRC.getLocation().add(currentDirection)))
                {
                    turningRight = !turningRight;
                    directionStack.clear();
                    return bugNav(destination);
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

        Direction dirTo = staticRC.getLocation().directionTo(destination);
        if(staticRC.canMove(dirTo))
        {
            return dirTo;
        }
        else
        {
            turningRight = decideTurningDirection(destination);
            Direction currentDirection = dirTo;
            directionStack.push(currentDirection);
            while(!staticRC.canMove(currentDirection))
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

    public static Direction bugBFS(MapLocation destination) throws GameActionException
    {
        if(!destination.equals(previousDestination) || staticRC.getRoundNum() - lastResetRound >= RESET_THRESHOLD)
        {
            initializeBugBFS(staticRC, destination);
        }

        if(staticRC.getLocation().equals(destination))
        {
            return Direction.CENTER;
        }

        Direction direction = BFS_7x7.pathfind(destination);
        if(direction != null)
        {
            return direction;
        }
        else
        {
            return bugNav(destination);
        }
    }

    public static boolean decideTurningDirection(MapLocation destination) throws GameActionException
    {
        MapLocation currentLocation = staticRC.getLocation();
        //if we see come to a new obstacle, decide the best turning direction and put in in the map
        if(obstacles.obstacleExists(currentLocation))
        {
            boolean bestTurningDirection = bestTurnDirection(destination);
            obstacles.setTurnDirection(currentLocation, bestTurningDirection);
            return bestTurningDirection;
        }
        else
        {
            boolean previousDirection = obstacles.getTurnDirection(currentLocation);
            obstacles.setTurnDirection(currentLocation, !previousDirection);
            return !previousDirection;
        }

        //return bestTurnDirection(destination);
    }


    public static boolean bestTurnDirection(MapLocation destination) throws GameActionException
    {
        int finalDistanceRight = distanceAfterSimulation(destination, true);
        int finalDistanceLeft = distanceAfterSimulation(destination, false);

        return finalDistanceRight < finalDistanceLeft;
    }

    private static int distanceAfterSimulation(MapLocation destination, boolean turningRight) throws GameActionException
    {
        MapLocation virtualBug = staticRC.getLocation();
        DirectionStack virtualStack = new DirectionStack();

        int cutOff = 5;
        int i = 0;
        while(i < cutOff)
        {
            if(virtualBug.equals(destination) || !staticRC.canSenseLocation(virtualBug))
            {
                break;
            }

            if(!virtualStack.isEmpty())
            {
                Direction currentDirection = virtualStack.peek();
                while(!virtualCanMove(virtualBug, currentDirection))
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
            if(virtualCanMove(virtualBug, dirTo))
            {
                virtualBug = virtualBug.add(dirTo);
                i++;
                continue;

            }
            else
            {
                Direction currentDirection = dirTo;
                while(!virtualCanMove(virtualBug, currentDirection))
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


    static boolean virtualCanMove(MapLocation start, Direction direction) throws GameActionException
    {
        MapLocation location = start.add(direction);
        if(!staticRC.onTheMap(location))
        {
            return false;
        }
        else if(!staticRC.canSenseLocation(location))
        {
            return true;
        }

        return staticRC.sensePassability(location) && !staticRC.canSenseRobotAtLocation(location);
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
