package Version15;

import Version15.Misc.Communication;
import Version15.Micro.SpawnMicro;
import Version15.Robots.HunterSplasher;
import Version15.Robots.Mopper;
import Version15.Robots.Soldier;
import Version15.Robots.Splasher;
import Version15.Utility.Utilities;
import battlecode.common.*;
import battlecode.common.UnitType;

import static Version15.Utility.Symmetry.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;



/**
 * RobotPlayer is the class that describes your main robot strategy.
 * The run() method inside this class is like your main function: this is what we'll call once your robot
 * is created!
 */
public class  RobotPlayer {

    /**
     * We will use this variable to count the number of turns this robot has been alive.
     * You can use static variables like this to save any information you want. Keep in mind that even though
     * these variables are static, in Battlecode they aren't actually shared between your robots.
     */
    public static boolean canSeeEnemyPaint = false;
    public static int turnCount = 0;
    public static int soldiers = 0;
    public static int moppers = 0;
    public static int splashers = 0;
    public static int totalBuilt = 0;
    public static boolean hunterBuilder;
    public static int BUILD_ROUND_NUM_DIVISOR = 50; //decides the number of robots we can build - lower number = build more frequently
    public static int mapSize = 0;
    public static int turnsSinceSeenEnemy = 0;
    public static UnitType toBuild = null;
    public static RobotController rc;

    public static MapLocation exploreTarget;
    public static int DISPERSION_RADIUS;
    public static RandomQueue locationQueue;

    /*
        Variables responsible for tracking average location of enemy paint
     */
    public static int distanceThreshold;   //For finding a good distance to start a new average
    public static MapLocation paintAverage1 = new MapLocation(0,0);
    public static MapLocation paintAverage2 = new MapLocation(0,0);
    public static int paintCount1;
    public static int paintCount2;

    public static double adjustedMapSize;


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
    public static final Random rng = new Random();

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

    public static void run(RobotController rc) throws GameActionException {
        RobotPlayer.rc = rc;
        mapSize = RobotPlayer.rc.getMapHeight() * RobotPlayer.rc.getMapWidth();
        distanceThreshold = (int) (0.0000378191 * mapSize * mapSize + 0.0624966779 * mapSize + 102.2835769561);
        DISPERSION_RADIUS = (int) (0.03875 * (rc.getMapWidth() * rc.getMapHeight()) - 2.5);
        //y = 0.003x + 2.8 -> calibrated to 4 on smallest map and 12 on biggest map
        adjustedMapSize = mapSize *  0.003 + 2.8;
        //y = 0.009x + 11.4 - > calibrated to be 15 on smallest map and 45 on biggest map
        Utilities.RADIUS_FROM_CENTER = (int)(mapSize * 0.009 + 11.4);

        if(rc.getRoundNum() == 1)
        {
            hunterBuilder = true;
        }

        Communication.setup();

//        if(rc.getType().isTowerType())
//        {
//            locationQueue = new RandomQueue(rc, 7);
//        }

        //sectors = new Sector[Sector.ceil(staticRC.getMapWidth(), 7) * Sector.ceil(staticRC.getMapHeight(), 7)];
        //Sector.hasTraveled = new boolean[Sector.ceil(staticRC.getMapWidth(), 7) * Sector.ceil(staticRC.getMapHeight(), 7)];

        while (true) {

            turnCount += 1;  // We have now been alive for one more turn!
            RobotPlayer.rc = rc;
            try {
                if(RobotPlayer.rc.getRoundNum() == 1 || turnCount == 1) {
                    map = new int[RobotPlayer.rc.getMapWidth()][RobotPlayer.rc.getMapHeight()];
                }
                /*
                    Complete all tasks which need to be completed in the beginning of the round
                */
                completeBeginningTasks();
                switch (RobotPlayer.rc.getType()){
                    case SOLDIER: Soldier.runSoldier(); break;
                    case MOPPER: Mopper.runMopper(); break;
                    case SPLASHER:
                    {
                        Splasher.runSplasher();

//                        if(rc.getRoundNum() > 200)
//                        {
//                            Splasher.runSplasher();
//                        }
//                        else
//                        {
//                            HunterSplasher.runHunterSplasher();
//                        }
                        break;
                       //Splasher.runSplasher();
                    }

                    default: runTower(); break;
                }
                bytecodeSensitiveOperations();
                //staticRC.setIndicatorString(knownSymmetry.toString());
                //staticRC.setIndicatorString("" + Communication.processRuinQueue.size());
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
    public static void runTower() throws GameActionException{
        //We're a tower so might as well try and complete patterns with our extra bytecode
        Utilities.attemptCompletePatterns();
        if(enemyRobots.length == 0) turnsSinceSeenEnemy++;
        else turnsSinceSeenEnemy = 0;
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
        canSeeEnemyPaint = false;
        MapLocation nextLoc = SpawnMicro.bestSpawn();
        if(toBuild == null || turnCount % 15 == 0) {
            toBuild = chooseBuild();
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

//            if(staticRC.canSendMessage(nextLoc))
//            {
//                staticRC.sendMessage(nextLoc, Communication.createExploreLocationMessage(locationQueue.remove(staticRC)));
//            }
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
        if(enemyRobots.length == 0 && (rc.getType() == UnitType.LEVEL_ONE_MONEY_TOWER || rc.getType() == UnitType.LEVEL_THREE_MONEY_TOWER || rc.getType() == UnitType.LEVEL_TWO_MONEY_TOWER) && rc.getMoney() > 8500 && rc.getPaint() <= 100) {
            checkDisintegrate();
        }
        else if(enemyRobots.length == 0 && turnsSinceSeenEnemy > 50 && (rc.getType() == UnitType.LEVEL_ONE_DEFENSE_TOWER || rc.getType() == UnitType.LEVEL_THREE_DEFENSE_TOWER || rc.getType() == UnitType.LEVEL_TWO_DEFENSE_TOWER || rc.getType() == UnitType.LEVEL_THREE_DEFENSE_TOWER) && rc.getPaint() <= 100) {
            checkDisintegrate();
        }
    }

    private static void checkDisintegrate() throws GameActionException {
        MapInfo[] square = rc.senseNearbyMapInfos(8);
        boolean hasRebuilder = false;
        for(int i = 0; i < 25; i++) {
            if(i == 12) continue;
            if(square[i].getPaint().isEnemy()) return;
            if(rc.canSenseRobotAtLocation(square[i].getMapLocation())) {
                RobotInfo r = rc.senseRobotAtLocation(square[i].getMapLocation());
                if(r.getTeam() != rc.getTeam()) return;
                if(r.type == UnitType.SOLDIER && r.getPaintAmount() > 100) {
                    hasRebuilder = true;
                }
            }
        }
        if(hasRebuilder) {
            System.out.println("hello!");
            rc.disintegrate();
        }
    }

    static void bytecodeSensitiveOperations() throws GameActionException
    {
        if(Clock.getBytecodesLeft() > 5000)
        {
            for(MapInfo info : rc.senseNearbyMapInfos(rc.getType().actionRadiusSquared)) {
                Utilities.attemptCompleteResourcePattern( info.getMapLocation());
            }
        }
    }

    private static int totalPrice = 0, numMessages = 0;

    static void completeBeginningTasks() throws GameActionException
    {

        nearbyTiles = rc.senseNearbyMapInfos();
        allyRobots = rc.senseNearbyRobots(-1, rc.getTeam());
        enemyRobots = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        nearbyRuins = rc.senseNearbyRuins(-1);

        Utilities.attemptCompleteResourcePattern( rc.getLocation());

        if(rc.getType().isTowerType())
        {
            Communication.processMessagesTower();
            Communication.broadcastMessages();
            Communication.sendMessagesTower();
            //if(paintAverage1 != null) staticRC.setIndicatorDot(paintAverage1, 0,0,255);
            //if(paintAverage2 != null) staticRC.setIndicatorDot(paintAverage2, 0,255,0);
        }
        else
        {
                /*/
                int len = staticRC.readMessages(staticRC.getRoundNum() - 1).length;
                System.out.println("Processing " + len + " messages");
                int price = Clock.getBytecodesLeft();
                /**/
            Communication.processMessagesRobot(); ////////////////////////////////////////////////////////////////
                /*/
                price -= Clock.getBytecodesLeft();
                System.out.println("\tTotal price of processing messages: " + price);
                System.out.println();
                numMessages += len;
                totalPrice += price;
                /**/

            Communication.scanForRuins();
            Communication.sendMessagesRobot();


            //////////////////////////////////////////////////////////////////////////////////////////////////
            //boolean p = false;
            /*/
            if(Communication.enemyTowers.isEmpty())
            {
                System.out.println("We know no enemy towers");
                p = true;
            }
            if(Communication.alliedPaintTowers.size() < 2)
            {
                System.out.println("We know of only " + Communication.alliedPaintTowers.size() + " allied paint towers");
                p = true;
            }
            /**/

            /*/
            if(turnCount % 150 == 0)
            {
                System.out.println("Average price/msg over 150 turns: " + ((double)(totalPrice)) / numMessages);
            }
            /**/

            //if(p) System.out.println();
        }
    }

    //things to consider:
    //number of towers relative to map size
    //number of each bot already built
    //round number
    //if it can see enemy robots
    //map size
    public static UnitType chooseBuild() {
        if(rc.getNumberTowers() == 2 && totalBuilt <= 1) return UnitType.SOLDIER;
        else if(enemyRobots.length > 1) return UnitType.MOPPER;
        else if(mapSize > 1600 && rc.getNumberTowers() > 3 && rc.getRoundNum() < 100 && rc.getMoney() > 1300) return UnitType.SPLASHER;
        //else if (totalBuilt == 0 && knownSymmetry != SymmetryType.Unknown) return UnitType.SPLASHER;

        //we have a finite total amount of paint, so make sure we use it wisely
        if(rc.getType() != UnitType.LEVEL_ONE_PAINT_TOWER && rc.getType() != UnitType.LEVEL_THREE_PAINT_TOWER && rc.getType() != UnitType.LEVEL_TWO_PAINT_TOWER) {
            if(rc.getPaint() < 300) {
                if(rc.getPaint() >= 200) return UnitType.SOLDIER;
                if(rc.getPaint() >= 100) return UnitType.MOPPER;
            }
        }

        double[] idealWeights = calculateIdealWeights();
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
    public static double[] calculateIdealWeights() {
        //constants specifically for this method
        int earlyRoundDef = 70 + (int)(adjustedMapSize * 8);
        //double mapSizeScalar = 1.0 / 200.0;
        //double adjustedMapSize = mapSize * mapSizeScalar;
        int earlySoldierBonus = 5 + (int)adjustedMapSize;
        int soldierScore;
        int mopperScore;
        int splasherScore;

        if(rc.getRoundNum() < earlyRoundDef) {
            soldierScore = earlySoldierBonus;
            mopperScore = 0;
            splasherScore = (knownSymmetry == SymmetryType.Unknown) ? 0 : 2;
//            if(adjustedMapSize <= 6) {
//                mopperScore++;
//            }
        }
        else {
            soldierScore = 2;
            mopperScore = 2;
            splasherScore = 4;
//            if(turnsSinceSeenEnemy <= 20) {
//                splasherScore++;
//                mopperScore++;
//            }
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
        if(canSeeEnemyPaint && moppers == 0) {
            mopperScore++;
        }
        int denominator = soldierScore + mopperScore + splasherScore;
        return new double[]{(double) soldierScore / denominator, (double) mopperScore / denominator, (double) splasherScore / denominator};
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
