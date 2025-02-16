package PathfindingTest;

import battlecode.common.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;


/**
 * RobotPlayer is the class that describes your main robot strategy.
 * The run() method inside this class is like your main function: this is what we'll call once your robot
 * is created!
 */
public class RobotPlayer {
    /**
     * We will use this variable to count the number of turns this robot has been alive.
     * You can use static variables like this to save any information you want. Keep in mind that even though
     * these variables are static, in Battlecode they aren't actually shared between your robots.
     */
    static int turnCount = 0;

    /**
     * A random number generator.
     * We will use this RNG to make some random moves. The Random class is provided by the java.util.Random
     * import at the top of this file. Here, we *seed* the RNG with a constant number (6147); this makes sure
     * we get the same sequence of numbers every time this code is run. This is very useful for debugging!
     */
    static final Random rng = new Random(6147);

    /** Array containing all the possible movement directions. */
    static final Direction[] directions = {
        Direction.NORTH,
        Direction.NORTHEAST,
        Direction.EAST,
        Direction.SOUTHEAST,
        Direction.SOUTH,
        Direction.SOUTHWEST,
        Direction.WEST,
        Direction.NORTHWEST,
    };

    /**
     * run() is the method that is called when a robot is instantiated in the Battlecode world.
     * It is like the main function for your robot. If this method returns, the robot dies!
     *
     * @param rc  The RobotController object. You use it to perform actions from this robot, and to get
     *            information on its current status. Essentially your portal to interacting with the world.
     **/
    @SuppressWarnings("unused")
    public static void run(RobotController rc) throws GameActionException {
        // Hello world! Standard output is very useful for debugging.
        // Everything you say here will be directly viewable in your terminal when you run a match!
        System.out.println("I'm alive");

        // You can also use indicators to save debug notes in replays.
        rc.setIndicatorString("Hello world!");

        Pathfinding.mapKnowledge = new Pathfinding.MapData[rc.getMapWidth()][rc.getMapHeight()];
        MapLocation start = rc.getLocation();


        while (true) {
            // This code runs during the entire lifespan of the robot, which is why it is in an infinite
            // loop. If we ever leave this loop and return from run(), the robot dies! At the end of the
            // loop, we call Clock.yield(), signifying that we've done everything we want to do.

            turnCount += 1;  // We have now been alive for one more turn!

            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode.
            try
            {
                // The same run() function is called for every robot on your team, even if they are
                // different types. Here, we separate the control depending on the UnitType, so we can
                // use different strategies on different robots. If you wish, you are free to rewrite
                // this into a different control structure!
                if (rc.getType() == UnitType.LEVEL_TWO_MONEY_TOWER && turnCount == 1)
                {
                    rc.buildRobot(UnitType.SOLDIER, rc.getLocation().add(Direction.NORTH));
                }
                else
                {
//                  Pathfinding.bugNav(rc, new MapLocation(0,0));
                    if(rc.getRoundNum() == 200)
                    {
                        rc.resign();
                    }

                    if(rc.getType().isRobotType() && rc.getRoundNum() < 100)
                    {
                        Direction dir = Pathfinding.bugBFS(rc, new MapLocation(0,0));
                        //Direction dir = Pathfinding.bugNav(rc, new MapLocation(11, 13));
                        if(rc.canMove(dir))
                        {
                            rc.move(dir);
                        }
                        int bytecodesLeft = Clock.getBytecodesLeft();
                        WallChecker_FullVision.findOverWallTiles(rc);
                        System.out.println(bytecodesLeft - Clock.getBytecodesLeft());
                    }
                    else if(rc.getType().isRobotType())
                    {
                        WallChecker.findOverWallTiles(rc);
                        Direction dir = Pathfinding.bugBFS(rc, start);
                        // Direction dir = Pathfinding.bugNav(rc, new MapLocation(11, 13));
                        if(rc.canMove(dir))
                        {
                            rc.move(dir);
                        }
                    }
                }
            }
             catch (GameActionException e) {
                // Oh no! It looks like we did something illegal in the Battlecode world. You should
                // handle GameActionExceptions judiciously, in case unexpected events occur in the game
                // world. Remember, uncaught exceptions cause your robot to explode!
                System.out.println("GameActionException");
                e.printStackTrace();

            } catch (Exception e) {
                // Oh no! It looks like our code tried to do something bad. This isn't a
                // GameActionException, so it's more likely to be a bug in our code.
                System.out.println("Exception");
                e.printStackTrace();

            } finally {
                // Signify we've done everything we want to do, thereby ending our turn.
                // This will make our code wait until the next turn, and then perform this loop again.
                Clock.yield();
            }
            // End of loop: go back to the top. Clock.yield() has ended, so it's time for another turn!
        }

        // Your code should never reach here (unless it's intentional)! Self-destruction imminent...
    }
}
