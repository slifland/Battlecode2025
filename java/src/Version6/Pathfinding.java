package Version6;

import battlecode.common.*;

import java.util.Stack;

public class Pathfinding
{
    static boolean[][] mapKnowledge;
    static boolean turningRight = true;
    static Stack<Direction> directionStack = new Stack<>();
    public static Direction bugNav(RobotController rc, MapLocation destination) throws GameActionException
    {
        //if stack is not empty peek direction and see if it's movable
        //if not keep adding directions to stack and rotating until direction is movable
        //if stack is not empty and direction to is movable, move


        //We are currently going around an obstacle and should follow the wall
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
                if(directionStack.size() == 8)
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
            Direction currentDirection = dirTo;

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
                if(directionStack.size() == 8)
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
        Direction bfsDirection = BFS_7x7.pathfind(rc, destination);
        if(bfsDirection != null)
        {
            directionStack.clear();
            return bfsDirection;
        }

        rc.setIndicatorDot(rc.getLocation(), 0, 0,255);
        return bugNav(rc, destination);
    }

    static void initializeMapKnowledge(RobotController rc)
    {
        mapKnowledge = new boolean[rc.getMapWidth()][rc.getMapHeight()];
    }

    static void updateMapKnowledge(RobotController rc)
    {
        MapInfo[] locations = rc.senseNearbyMapInfos();
        for(int i = 0; i < locations.length; i++)
        {
            MapLocation tempLocation = locations[i].getMapLocation();
            if(locations[i].isPassable() && !rc.canSenseRobotAtLocation(tempLocation))
            {
                mapKnowledge[tempLocation.x][tempLocation.y] = true;
            }
            else
            {
                mapKnowledge[tempLocation.x][tempLocation.y] = false;
            }
        }
    }


}
