package Version6;

import battlecode.common.*;

import java.util.Arrays;
import java.util.Random;


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
    static int soldiers = 0;
    static int moppers = 0;
    static int totalBuilt = 0;


    /*
        Variables responsible for tracking average location of enemy paint
     */
    static int distanceThreshold;   //For finding a good distance to start a new average
    static MapLocation paintAverage1 = new MapLocation(0,0);
    static MapLocation paintAverage2 = new MapLocation(0,0);
    static int paintCount1;
    static int paintCount2;
    static int greatestDelta;
    static boolean returnFirst = true;

    /*
    Store all methods initially to avoid redundant calls
     */
    public static MapInfo[] nearbyTiles;
    public static RobotInfo[] allyRobots;
    public static RobotInfo[] enemyRobots;
    public static MapLocation[] nearbyRuins;

    /**
     * A random number generator.
     * We will use this RNG to make some random moves. The Random class is provided by the java.util.Random
     * import at the top of this file. Here, we *seed* the RNG with a constant number (6147); this makes sure
     * we get the same sequence of numbers every time this code is run. This is very useful for debugging!
     */
    static final Random rng = new Random();

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

        int mapSize = rc.getMapHeight() * rc.getMapWidth();
        distanceThreshold = (int) (0.0000378191 * mapSize * mapSize + 0.0624966779 * mapSize + 102.2835769561);

        while (true) {

            turnCount += 1;  // We have now been alive for one more turn!

            try {
                /*
                    Complete all tasks which need to be completed in the beginning of the round
                */
                completeBeginningTasks(rc);
                switch (rc.getType()){
                    case SOLDIER: Soldier.runSoldier(rc); break;
                    case MOPPER: Mopper.runMopper(rc); break;
                    case SPLASHER: Splasher.runSplasher(rc); break;
                    default: runTower(rc); break;
                    }
                bytecodeSensitiveOperations(rc);
            }
             catch (GameActionException e) {
                System.out.println("GameActionException");
                e.printStackTrace();

            } catch (Exception e) {
                System.out.println("Exception");
                e.printStackTrace();

            } finally {
                Clock.yield();
            }
        }
    }
    public static void runTower(RobotController rc) throws GameActionException{
        //We're a tower so might as well try and complete patterns with our extra bytecode
        Utilities.attemptCompletePatterns(rc);
        if(rc.getType() == UnitType.LEVEL_ONE_PAINT_TOWER || rc.getType() == UnitType.LEVEL_TWO_PAINT_TOWER || rc.getType() == UnitType.LEVEL_THREE_PAINT_TOWER) {
            if(rc.getMoney() > 4000 && rc.canUpgradeTower(rc.getLocation()))
                rc.upgradeTower(rc.getLocation());
        }
        else if(rc.getMoney() > 4500 && rc.canUpgradeTower(rc.getLocation())) {
            rc.upgradeTower(rc.getLocation());
        }
        /* USE LATER...
        if (isDefenseTower()) runDefenseTower();
        if (isMoneyTower()) runMoneyTower();
        if (isPaintTower()) runPaintTower();
        */
        // Pick a direction to build in.
        Direction dir = directions[rng.nextInt(directions.length)];
        MapLocation nextLoc = rc.getLocation().add(dir);
        int soldierRatio = (rc.getNumberTowers() < 25 && rc.getRoundNum() < 300) ? 3 : 2;
        int mopperRatio = 1;
        //if you arent our first tower, always build a splasher first
        if(totalBuilt == 0 && rc.getNumberTowers() > 2 && (rc.getMapHeight() < 50 || rc.getMapWidth() < 50)) {
            if (rc.canBuildRobot(UnitType.SPLASHER, nextLoc)) {
                rc.buildRobot(UnitType.SPLASHER, nextLoc);
                totalBuilt++;
            }
        }
        else {
            if (totalBuilt <= 1 || totalBuilt < rc.getRoundNum() / 50 || rc.getMoney() > 1200) {
                if (rc.getRoundNum() < 100 && rc.getMapHeight() < 25 && rc.getMapWidth() < 25) soldierRatio = 1;

                if (rc.canBuildRobot(UnitType.SOLDIER, nextLoc) && soldiers < soldierRatio) {
                    rc.buildRobot(UnitType.SOLDIER, nextLoc);
                    //System.out.println(Arrays.toString(Utilities.getEnemyPaintAverages()));
                    Communication.sendAveragesToRobot(rc, nextLoc);
                    soldiers++;
                    totalBuilt++;
                }
                if (rc.canBuildRobot(UnitType.MOPPER, nextLoc) && moppers < mopperRatio) {
                    rc.buildRobot(UnitType.MOPPER, nextLoc);
                    Communication.sendAveragesToRobot(rc, nextLoc);
                    moppers++;
                    totalBuilt++;
                }
                if (rc.canBuildRobot(UnitType.SPLASHER, nextLoc)) {
                    rc.buildRobot(UnitType.SPLASHER, nextLoc);
                    Communication.sendAveragesToRobot(rc, nextLoc);
                    soldiers = 0;
                    moppers = 0;
                    totalBuilt++;
                }
            }
        }
       // }


        //try and attack if we can
        RobotInfo[] enemyRobots = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        for(RobotInfo robot : enemyRobots) {
            if(rc.canAttack(robot.getLocation())) {
                rc.attack(robot.getLocation());
            }
        }
        if(enemyRobots.length != 0) rc.attack(null);
    }

    static void bytecodeSensitiveOperations(RobotController rc) throws GameActionException
    {
        if(Clock.getBytecodesLeft() > 3000 && rc.getType().isRobotType())
        {
            Utilities.updatePaintAverages(rc);
        }
    }

    static void completeBeginningTasks(RobotController rc) throws GameActionException
    {
        nearbyTiles = rc.senseNearbyMapInfos();
        allyRobots = rc.senseNearbyRobots(-1, rc.getTeam());
        enemyRobots = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        nearbyRuins = rc.senseNearbyRuins(-1);

        Utilities.attemptCompleteResourcePattern(rc);

        if(rc.getType().isTowerType())
        {
            Communication.processMessagesTower(rc);
            Communication.sendMessagesTower(rc);;
        }
        else
        {
            Communication.scanForRuins(rc);
            Communication.processMessagesRobot(rc);
            Communication.sendMessagesRobot(rc);
            if(turnCount < 5)
            {
                rc.setIndicatorDot(paintAverage1, 0,255,0);
                rc.setIndicatorDot(paintAverage2, 0,0,255);
            }
        }
    }
}
