package PathfindingTest;

import battlecode.common.*;

import java.awt.*;
import java.util.Stack;

public class Pathfinding
{
    static boolean[][] mapKnowledge;
    static boolean turningRight = true;
    static Stack<Direction> directionStack = new Stack<>();
    public static void bugNav(RobotController rc, MapLocation target) throws GameActionException
    {
        updateMapKnowledge(rc);
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
                    bugNav(rc, target);
                    return;
                }
                directionStack.push(currentDirection);
                if(directionStack.size() == 8)
                {
                    directionStack.clear();
                    return;
                }
            }
            rc.move(directionStack.pop());
            return;
        }

        Direction dirTo = rc.getLocation().directionTo(target);
        if(rc.canMove(dirTo))
        {
            rc.move(dirTo);
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
                    return;
                }
            }
            rc.move(directionStack.pop());
        }
    }
    /*
        Will perform a turn simulation for n turns and return whether to turn right
     */
    public static boolean turnSimulation(RobotController rc, MapLocation target, int numTurns)
    {
        Stack<Direction> leftStack = new Stack<>();
        int leftDistance;

        Stack<Direction> rightStack = new Stack<>();
        int rightDistance;

        MapLocation virtualRobotLocation = rc.getLocation();
        for(int i = 0; i < numTurns; i++)
        {

        }
        return false;
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
