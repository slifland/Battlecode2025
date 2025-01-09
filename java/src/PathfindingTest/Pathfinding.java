package PathfindingTest;

import battlecode.common.*;
import java.util.Stack;

public class Pathfinding
{
    static Stack<Direction> directionStack = new Stack<>();
    public static void bugNav(RobotController rc, MapLocation target) throws GameActionException
    {
        //if stack is not empty peek direction and see if it's movable
        //if not keep adding directions to stack and rotating until direction is movable
        //if stack is not empty and direction to is movable, move

        if(!directionStack.isEmpty())
        {
            Direction currentDirection = directionStack.peek();
            while(!rc.canMove(currentDirection))
            {
                currentDirection = currentDirection.rotateRight();
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
                currentDirection = currentDirection.rotateRight();
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
}
