package Version11;

import battlecode.common.*;

import java.util.*;



/**
 * RobotPlayer is the class that describes your main robot strategy.
 * The run() method inside this class is like your main function: this is what we'll call once your robot
 * is created!
 */
public class RobotPlayer {
    public enum Symmetry
    {
        Horizontal, Vertical, Rotational, Unknown
    }

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
    static MapLocation paintAverage1 = new MapLocation(0,0);
    static MapLocation paintAverage2 = new MapLocation(0,0);
    static int paintCount1;
    static int paintCount2;
    static int greatestDelta;
    static boolean returnFirst = true;
    static RandomQueue locationQueue;


    static final int BUILD_ROUND_NUM_DIVISOR = 50; //decides the number of robots we can build - lower number = build more frequently
    static final int STOP_BUILDING_SOLDIERS = 400;
    static int DISPERSION_RADIUS;
    static int DISTANCE_THRESHOLD;   //For finding a good distance to start a new average
    static int RANDOM_QUEUE_LENGTH = 7;

    /*
    Store all methods initially to avoid redundant calls
     */
    public static MapInfo[] nearbyTiles;
    public static RobotInfo[] allyRobots;
    public static RobotInfo[] enemyRobots;
    public static MapLocation[] nearbyRuins;

    public static int[][] map; //used for map symmetry - 0 is not checked, 1 is not of interest, 2 is wall, 3 is ruin
    public static int symmetries = 0b1110; //used to store what symmetries are true - horizontal, vertical, rotational (LSB is always zero)
    public static Symmetry knownSymmetry = Symmetry.Unknown; //0 - horizontal, 1 = vertical, 2 = rotational, -1 = unknown
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
        DISTANCE_THRESHOLD = (int) (0.0000378191 * mapSize * mapSize + 0.0624966779 * mapSize + 102.2835769561);
        DISPERSION_RADIUS = (int) (0.03875 * (rc.getMapWidth() * rc.getMapHeight()) - 2.5);


        Communication.setup(rc);

        //sectors = new Sector[Sector.ceil(rc.getMapWidth(), 7) * Sector.ceil(rc.getMapHeight(), 7)];
        //Sector.hasTraveled = new boolean[Sector.ceil(rc.getMapWidth(), 7) * Sector.ceil(rc.getMapHeight(), 7)];

        if(rc.getType().isTowerType())
        {
            locationQueue = new RandomQueue(rc, 7);
        }

        while (true) {

            turnCount += 1;  // We have now been alive for one more turn!

            try {
                if(rc.getRoundNum() == 1 || turnCount == 1) {
                    map = new int[rc.getMapWidth()][rc.getMapHeight()];
                }
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
                //rc.setIndicatorString(String.valueOf(knownSymmetry));
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
        if(rc.getType() == UnitType.LEVEL_ONE_PAINT_TOWER || rc.getType() == UnitType.LEVEL_TWO_PAINT_TOWER) {
            if(rc.getMoney() > 3000 && rc.canUpgradeTower(rc.getLocation()))
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
        //if(rc.isActionReady()) {
            // Pick a direction to build in.
            //Direction dir = directions[rng.nextInt(directions.length)];
            //MapLocation nextLoc = rc.getLocation().add(dir);
            MapLocation nextLoc = SpawnMicro.bestSpawn(rc);
            if(nextLoc != null) {
                //int soldierRatio = (rc.getNumberTowers() < 25 && rc.getRoundNum() < 300) ? 2 : 1;
                int soldierRatio = (rc.getRoundNum() < STOP_BUILDING_SOLDIERS) ? 2 : 1;
                int mopperRatio = 2;
                //if you arent our first tower, always build a splasher first
                if (totalBuilt == 0 && rc.getNumberTowers() > 2 && (rc.getMapHeight() <= 50 && rc.getMapWidth() <= 50) && rc.getMoney() > 1200) {
                    if (rc.canBuildRobot(UnitType.SPLASHER, nextLoc)) {
                        rc.buildRobot(UnitType.SPLASHER, nextLoc);
                        totalBuilt++;
                    }
                }
                //if(rc.getRoundNum() < STOP_BUILDING_SOLDIERS || rc.getNumberTowers() < 4){
                else if (((totalBuilt <= 1 && rc.getNumberTowers() == 2) && rc.getRoundNum() < 10) || totalBuilt < rc.getRoundNum() / BUILD_ROUND_NUM_DIVISOR || rc.getMoney() > 1300) {
                    if (rc.getRoundNum() < 100 && rc.getMapHeight() < 25 && rc.getMapWidth() < 25) soldierRatio = 1;

                    if (rc.canBuildRobot(UnitType.SOLDIER, nextLoc) && (soldiers < soldierRatio || (rc.getRoundNum() <= 50))) {
                        rc.buildRobot(UnitType.SOLDIER, nextLoc);
                        //Communication.sendAveragesToRobot(rc, nextLoc);
                        soldiers++;
                        totalBuilt++;
                    }
                    if (rc.canBuildRobot(UnitType.MOPPER, nextLoc) && moppers < mopperRatio && (rc.getRoundNum() > 50 || (rc.getMapHeight() * rc.getMapWidth() < 900))) {
                        rc.buildRobot(UnitType.MOPPER, nextLoc);
                        //Communication.sendAveragesToRobot(rc, nextLoc);
                        moppers++;
                        soldiers = 0;
                        totalBuilt++;
                    }
                    if (rc.canBuildRobot(UnitType.SPLASHER, nextLoc)) {
                        rc.buildRobot(UnitType.SPLASHER, nextLoc);
                        //Communication.sendAveragesToRobot(rc, nextLoc);
                        soldiers = 0;
                        moppers = 0;
                        totalBuilt++;
                    }
                }
            }
        //}
        int minHealth = Integer.MAX_VALUE;
        RobotInfo r = null;
        for(RobotInfo robot : enemyRobots) {
            if(robot.health < minHealth && rc.canAttack(robot.getLocation())) {
                r = robot;
                minHealth = robot.health;
            }
        }
        if(r != null && rc.canAttack(r.getLocation())) {
            rc.attack(r.getLocation());
        }
        if(enemyRobots.length != 0) rc.attack(null);
    }

    static void bytecodeSensitiveOperations(RobotController rc) throws GameActionException
    {
        if(Clock.getBytecodesLeft() > 3000)
        {
            for(MapInfo info : rc.senseNearbyMapInfos(rc.getType().actionRadiusSquared)) {
                Utilities.attemptCompleteResourcePattern(rc, info.getMapLocation());
            }
        }
    }

    static void completeBeginningTasks(RobotController rc) throws GameActionException
    {

        nearbyTiles = rc.senseNearbyMapInfos();
        allyRobots = rc.senseNearbyRobots(-1, rc.getTeam());
        enemyRobots = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        nearbyRuins = rc.senseNearbyRuins(-1);

        Utilities.attemptCompleteResourcePattern(rc, rc.getLocation());

        if(rc.getType().isTowerType())
        {
            Communication.processMessagesTower(rc);
            Communication.broadcastMessages(rc);
            Communication.sendMessagesTower(rc);
            //if(paintAverage1 != null) rc.setIndicatorDot(paintAverage1, 0,0,255);
            //if(paintAverage2 != null) rc.setIndicatorDot(paintAverage2, 0,255,0);
        }
        else
        {
            if(rc.getRoundNum() % 5 == 0 || turnCount == 1)
            {
                //int price = Clock.getBytecodesLeft();
                Communication.processMessagesRobot(rc);
                //System.out.println(price - Clock.getBytecodesLeft());
            }

            Communication.scanForRuins(rc);
            Communication.sendMessagesRobot(rc);
        }
    }
}

class RandomQueue
{
    int size;
    Queue<MapLocation> exploreLocations;

    public RandomQueue(RobotController rc, int size)
    {
        exploreLocations = new LinkedList<>();
        this.size = size;
        for(int i = 0; i < size; i++)
        {
            addRandomLocation(rc);
        }
        exploreLocations = new LinkedList<>();
    }

    public void addRandomLocation(RobotController rc)
    {
        boolean locationFound = false;
        int cutoff = 10;
        int i = 0;
        MapLocation tempLocation = null;
        while(!locationFound && i < cutoff)
        {
            locationFound = true;
            tempLocation = Utilities.generateRandomLocation(rc);
            for(MapLocation location : exploreLocations)
            {
                if(location.isWithinDistanceSquared(tempLocation, RobotPlayer.DISPERSION_RADIUS))
                {
                    locationFound = false;
                    break;
                }
            }
            i++;
        }
        exploreLocations.add(tempLocation);
    }

    public MapLocation remove(RobotController rc)
    {
        addRandomLocation(rc);
        return exploreLocations.remove();
    }
}
