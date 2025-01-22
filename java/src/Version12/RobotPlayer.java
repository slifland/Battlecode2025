package Version12;

import battlecode.common.*;
import battlecode.common.UnitType;
import java.util.Random;



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
    static int splashers = 0;
    static int totalBuilt = 0;
    static int BUILD_ROUND_NUM_DIVISOR = 50; //decides the number of robots we can build - lower number = build more frequently
    static int mapSize = 0;
    static UnitType toBuild = null;

    /*
        Variables responsible for tracking average location of enemy paint
     */
    static int distanceThreshold;   //For finding a good distance to start a new average
    static MapLocation paintAverage1 = new MapLocation(0,0);
    static MapLocation paintAverage2 = new MapLocation(0,0);
    static int paintCount1;
    static int paintCount2;
    

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

        mapSize = rc.getMapHeight() * rc.getMapWidth();
        distanceThreshold = (int) (0.0000378191 * mapSize * mapSize + 0.0624966779 * mapSize + 102.2835769561);

        Communication.setup(rc);

        //sectors = new Sector[Sector.ceil(rc.getMapWidth(), 7) * Sector.ceil(rc.getMapHeight(), 7)];
        //Sector.hasTraveled = new boolean[Sector.ceil(rc.getMapWidth(), 7) * Sector.ceil(rc.getMapHeight(), 7)];

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
            if(rc.getMoney() > 2000 && rc.canUpgradeTower(rc.getLocation()) && rc.getNumberTowers() > 3)
                rc.upgradeTower(rc.getLocation());
        }
        else if(rc.getMoney() > 2500 && rc.canUpgradeTower(rc.getLocation()) && rc.getNumberTowers() > 3) {
            rc.upgradeTower(rc.getLocation());
        }
//        if(turnCount % 75 == 0) {
//            totalBuilt = 0;
//            splashers = 0;
//            moppers = 0;
//            soldiers = 0;
//        }
        MapLocation nextLoc = SpawnMicro.bestSpawn(rc);
        if(toBuild == null || turnCount % 10 == 0) {
            toBuild = chooseBuild(rc);
        }
        if(nextLoc != null && toBuild != null && rc.canBuildRobot(toBuild, nextLoc) && (rc.getMoney() > 1200 || totalBuilt < (rc.getRoundNum() / BUILD_ROUND_NUM_DIVISOR))) {
            rc.buildRobot(toBuild, nextLoc);
            switch(toBuild) {
                case SOLDIER: soldiers++; break;
                case MOPPER: moppers++; break;
                case SPLASHER: splashers++; break;
                default: break;
            }
            totalBuilt++;
        }
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
        if(rc.canAttack(null)) rc.attack(null);
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

    //things to consider:
    //number of towers relative to map size
    //number of each bot already built
    //round number
    //if it can see enemy robots
    //map size
    public static UnitType chooseBuild(RobotController rc) {
        if(rc.getNumberTowers() == 2 && totalBuilt <= 1) return UnitType.SOLDIER;
        else if(enemyRobots.length > 1) return UnitType.MOPPER;

        //we have a finite total amount of paint, so make sure we use it wisely
        if(rc.getType() != UnitType.LEVEL_ONE_PAINT_TOWER && rc.getType() != UnitType.LEVEL_THREE_PAINT_TOWER && rc.getType() != UnitType.LEVEL_TWO_PAINT_TOWER) {
            if(rc.getPaint() < 300) {
                if(rc.getPaint() >= 200) return UnitType.SOLDIER;
                if(rc.getPaint() >= 100) return UnitType.MOPPER;
            }
        }

        double[] idealWeights = calculateIdealWeights(rc);
        int selector = rng.nextInt(100);
        double oddsSoldier = idealWeights[0] * 100;
        double oddsMopper = idealWeights[1] * 100;
        double oddsSplasher = idealWeights[2] * 100;
        //System.out.println(oddsSoldier + " : " + oddsMopper + " : " + oddsSplasher + " : " + selector);
        if(selector < oddsSoldier) return UnitType.SOLDIER;
        else if(selector < oddsMopper + oddsSoldier) return UnitType.MOPPER;
        else return UnitType.SPLASHER;
    }

    //calculates the ideal weights for each robot, given the current situation
    public static double[] calculateIdealWeights(RobotController rc) {
        //constants specifically for this method
        int earlyRoundDef = 200;
        //double mapSizeScalar = 1.0 / 200.0;
        //double adjustedMapSize = mapSize * mapSizeScalar;
        //y = 0.003x + 2.8 -> calibrated to 4 on smallest map and 12 on biggest map
        double adjustedMapSize = mapSize *  0.003 + 2.8;
        int earlySoldierBonus = 5 + (int)adjustedMapSize;
        int soldierScore;
        int mopperScore;
        int splasherScore;

        if(rc.getRoundNum() < earlyRoundDef) {
            soldierScore = earlySoldierBonus;
            mopperScore = 1;
            splasherScore = 1;
            if(adjustedMapSize <= 6) {
                mopperScore++;
            }
        }
        else {
            soldierScore = 2;
            mopperScore = 2;
            splasherScore = 2;
            if(adjustedMapSize <= 8) {
                int adjustment = (int) (8 - adjustedMapSize);
                mopperScore += adjustment;
                splasherScore += adjustment / 2;
                soldierScore += adjustment / 4;
            }
            else {
                int adjustment = (int) (adjustedMapSize - 8);
                splasherScore += adjustment;
                mopperScore += adjustment / 2;
                soldierScore += adjustment / 4;
            }
            if(!(rc.getType() != UnitType.LEVEL_ONE_PAINT_TOWER && rc.getType() != UnitType.LEVEL_THREE_PAINT_TOWER && rc.getType() != UnitType.LEVEL_TWO_PAINT_TOWER)) {
                splasherScore += 2;
            }
        }

        int denominator = soldierScore + mopperScore + splasherScore;
        return new double[]{(double) soldierScore / denominator, (double) mopperScore / denominator, (double) splasherScore / denominator};
    }


}
