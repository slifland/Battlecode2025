package Version5;

import battlecode.common.*;

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


        while (true) {

            turnCount += 1;  // We have now been alive for one more turn!

            try {


                Communication.sendRuinLocations(rc); //Scan for rune locations and send messages
                Communication.receiveRuinLocations(rc);
                Utilities.attemptCompleteResourcePattern(rc);
                switch (rc.getType()){
                    case SOLDIER: Soldier.runSoldier(rc); break;
                    case MOPPER: Mopper.runMopper(rc); break;
                    case SPLASHER: Splasher.runSplasher(rc); break;
                    default: runTower(rc); break;
                    }
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
        if(totalBuilt == 0 && rc.getNumberTowers() > 2 && rc.getMapHeight() < 35 && rc.getMapWidth() < 35) {
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
                    soldiers++;
                    totalBuilt++;
                }
                if (rc.canBuildRobot(UnitType.MOPPER, nextLoc) && moppers < mopperRatio) {
                    rc.buildRobot(UnitType.MOPPER, nextLoc);
                    moppers++;
                    totalBuilt++;
                }
                if (rc.canBuildRobot(UnitType.SPLASHER, nextLoc)) {
                    rc.buildRobot(UnitType.SPLASHER, nextLoc);
                    soldiers = 0;
                    moppers = 0;
                    totalBuilt++;
                }
            }
        }
       // }


        // Read incoming messages
//        Message[] messages = rc.readMessages(-1);
//        for (Message m : messages) {
//            System.out.println("Tower received message: '#" + m.getSenderID() + " " + m.getBytes());
//        }

        //try and attack if we can
        RobotInfo[] enemyRobots = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        for(RobotInfo robot : enemyRobots) {
            if(rc.canAttack(robot.getLocation())) {
                rc.attack(robot.getLocation());
            }
        }
        if(enemyRobots.length != 0) rc.attack(null);
    }


    public static void updateEnemyRobots(RobotController rc) throws GameActionException{
        // Sensing methods can be passed in a radius of -1 to automatically 
        // use the largest possible value.
        RobotInfo[] enemyRobots = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        if (enemyRobots.length != 0){
            rc.setIndicatorString("There are nearby enemy robots! Scary!");
            // Save an array of locations with enemy robots in them for possible future use.
            MapLocation[] enemyLocations = new MapLocation[enemyRobots.length];
            for (int i = 0; i < enemyRobots.length; i++){
                enemyLocations[i] = enemyRobots[i].getLocation();
            }
            RobotInfo[] allyRobots = rc.senseNearbyRobots(-1, rc.getTeam());
            // Occasionally try to tell nearby allies how many enemy robots we see.
            if (rc.getRoundNum() % 20 == 0){
                for (RobotInfo ally : allyRobots){
                    if (rc.canSendMessage(ally.location, enemyRobots.length)){
                        rc.sendMessage(ally.location, enemyRobots.length);

                    }
                }
            }
        }
    }
}
