package Version14.Misc;

import Version14.Robots.Mopper;
import Version14.Robots.Soldier;
import Version14.Robots.Splasher;
import Version14.Utility.Symmetry;
import battlecode.common.*;

import static Version14.Utility.Symmetry.SymmetryType;

import static Version14.RobotPlayer.*;
import static Version14.Utility.Symmetry.*;

import java.util.Iterator;
import java.util.LinkedList;

public class Communication {

    private static class QueueRuin {
        //the number of items stored at any given time must not reach this number
        private static final int len = 200;

        private final Ruin[] ruins;
        private int head = 0; //the next item to pop
        private int tail = 0; //the next place to add an item

        public QueueRuin() {
            ruins = new Ruin[len];
        }

        public void add(Ruin r) {
            ruins[tail] = r;
            tail = (tail + 1) % len;
        }

        //pre: !isEmpty()
        public Ruin pop() {
            Ruin ret = ruins[head];
            head = (head + 1) % len;
            return ret;
        }

        public boolean isEmpty() {
            return head == tail;
            /*
                Queue is either empty or full,
                    we assume we never get to len number of items
             */
        }
    }

    public static class QueueMessage///////////////////////////////////////////////////////////////////////
    {
        //the number of items stored at any given time must not reach this number
        private static final int len = 200;

        private final Message[] messages;
        private int head = 0; //the next item to pop
        private int tail = 0; //the next place to add an item

        public QueueMessage() {
            messages = new Message[len];
        }

        public void add(Message m) {
            messages[tail] = m;
            tail = (tail + 1) % len;
        }

        //pre: !isEmpty()
        public Message pop() {
            Message ret = messages[head];
            head = (head + 1) % len;
            return ret;
        }

        public boolean isEmpty() {
            return head == tail;
                /*
                    Queue is either empty or full,
                        we assume we never get to len number of items
                 */
        }

        public void clear() {
            head = tail;
        }

        public int size() {
            return (tail >= head ? tail - head : len - (head - tail));
        }
    }

    static final QueueRuin sendQueue = new QueueRuin();      //between robots and towers
    static final QueueRuin broadcastRuinQueue = new QueueRuin(); //between towers

    public static final QueueMessage processRuinQueue = new QueueMessage();
    /// ////////////////////////////////////////////////////////////////

    public static final LinkedList<Ruin> unclaimedRuins = new LinkedList<>(); //remembered, but not sent in comms

    public static final LinkedList<Ruin> enemyTowers = new LinkedList<>();
    public static final LinkedList<Ruin> alliedPaintTowers = new LinkedList<>();

    /*
        Stores Ruins based on their location for instant access
        [x position][y position]
     */
    static Ruin[][] allMemory;

    static final int numRuinsToSendToEachRobot = 2; //The maximum number of Ruins messages a tower will send in any given turn
    static final double numMessagesToProcessPerRoundFactorRobot = 0.01286; //max number to process = (int)(1 + factor * num in queue)

    //Called only once, when a new robot/tower is born
    public static void setup() throws GameActionException {
        Communication.allMemory = new Ruin[staticRC.getMapWidth()][staticRC.getMapHeight()];

        if (staticRC.getType().isTowerType()) {
            staticRC.broadcastMessage(0b11);

            Ruin r = new Ruin(staticRC.getLocation(), 1, isPaintTower(staticRC.getType()));
            updateRuinsMemory(r);
            broadcastRuinQueue.add(r);
        }
    }

    /*
        Process all incoming messages for robots
    */
    public static void processMessagesRobot() throws GameActionException {

        /*
            Robot can only be in range of 4 towers at once (absolute max)
            Max num ruins messages per round: 4 * numRuinsToSendToEachRobot = 8

            usually we will only be in range of 1-3 towers tho
         */

            /*/
            int price = Clock.getBytecodesLeft(); ////////////////////////////////////////////////////////////////////////////////////////////////////
            /**/

        for (Message m : staticRC.readMessages(staticRC.getRoundNum() - 1)) {
            processRuinQueue.add(m);
        }

            /*/
            price -= Clock.getBytecodesLeft();
            System.out.println("\tPrice of adding to queue: " + price);

            System.out.println("\t\tNum messages in queue: " + processRuinQueue.size());

            price = Clock.getBytecodesLeft();
            /**/

        int size = processRuinQueue.size();
        int max = (int) (1 + numMessagesToProcessPerRoundFactorRobot * (size * size));
        Message m;
        for (int i = 0; i < max && !processRuinQueue.isEmpty(); i++) {
            //System.out.println("*");
            m = processRuinQueue.pop();
            switch (m.getBytes() & 0b11) {
                case 0b00 -> updateRuinsMemory(messageToRuin(m));
                //case 0b01 -> updateExploreLocation(m);
            }
        }

            /*/
            price -= Clock.getBytecodesLeft();
            System.out.println("\tPrice of processing " + max + " messages: " + price);
            /**/
    }

    /*
        Process all incoming messages for towers
    */
    public static void processMessagesTower() {
        for (Message m : staticRC.readMessages(staticRC.getRoundNum() - 1)) {
            switch (m.getBytes() & 0b11) {
                case 0b00:
                    if (updateRuinsMemory(messageToRuin(m))) {
                        //if this Ruin contained new information, then broadcast to nearby towers
                        broadcastRuinQueue.add(messageToRuin(m));
                    }
                    break;
                /*
                case 0b01:

                    break;
                 */
                case 0b10:
                    processSymmetryMessageTower(m.getBytes());
                    break;
                case 0b11:
                    fillBroadcastQueue();
            }

        }
    }

    public static void sendMessagesTower() throws GameActionException {
        sendRuinLocationsToTroops();
    }

    public static void sendMessagesRobot() throws GameActionException {
        //Find available towers to broadcast to, select one randomly

        int i = 0;
        MapLocation[] tower = new MapLocation[30];
        for (RobotInfo robot : allyRobots) {
            if (robot.getType().isTowerType() && staticRC.canSendMessage(robot.location)) {
                tower[i++] = robot.getLocation();
            }
        }
        if (i == 0) return;

        sendRuinLocationsToTower(tower[rng.nextInt(0, i)]);
    }

    /*
        Scan for nearby ruin locations and update ruinsMemory as necessary
     */
    public static void scanForRuins() throws GameActionException {
        Mopper.nearbyRuin = null;
        Mopper.seenEnemyTower = null;
        Soldier.seenEnemyTower = null;
        Splasher.seenEnemyTower = null;

        for (MapLocation ruinLoc : nearbyRuins) {
            seenRuins.add(ruinLoc);
            unseenRuins.remove(ruinLoc);
            int status = 0;
            boolean isPaintTower = false;

            RobotInfo ruinInfo = staticRC.senseRobotAtLocation(ruinLoc);

            /*
                If the ruin is unclaimed, ruinInfo will be null
                    status will be left as 0
                    isPaintTower will be left as false
             */
            if (ruinInfo != null) {
                //get the team information
                if (ruinInfo.team == staticRC.getTeam())
                    status = 1;
                else {
                    status = 2;
                    Splasher.seenEnemyTower = ruinInfo;
                    Mopper.seenEnemyTower = ruinInfo;
                    Soldier.seenEnemyTower = ruinInfo;
                }

                //get whether it is a paint tower
                if (isPaintTower(ruinInfo))
                    isPaintTower = true;
            } else {
                Mopper.nearbyRuin = ruinLoc;
            }

            updateRuinsMemory(new Ruin(ruinLoc, status, isPaintTower));
        }
    }

    /*
        Send known ruin locations to a nearby tower
            Sends one Ruin each time this method is called
        This method should be called by robots and not towers
    */
    public static void sendRuinLocationsToTower(MapLocation tower) throws GameActionException {
        if (unclaimedRuins.isEmpty() && enemyTowers.isEmpty() && alliedPaintTowers.isEmpty()) {
            if (knownSymmetry != SymmetryType.Unknown && staticRC.canSendMessage(tower)) {
                staticRC.sendMessage(tower, symmetriesToMessage(symmetries));
            }
            return;
        }

        if (sendQueue.isEmpty())
            fillSendQueue();

        Ruin r;
        if (staticRC.canSendMessage(tower)) {
            while (!sendQueue.isEmpty()) {
                r = sendQueue.pop();
                if (!validateRuin(r)) continue;

                staticRC.sendMessage(tower, ruinToMessage(r));
                break;
            }
        }
    }

    /*
    Broadcasts information the tower has to nearby towers
     */
    public static void broadcastMessages() throws GameActionException {
        if (staticRC.canBroadcastMessage() && knownSymmetry != Symmetry.SymmetryType.Unknown) {
            staticRC.broadcastMessage(symmetriesToMessage(symmetries));
        }

        Ruin r;
        while (staticRC.canBroadcastMessage() && !broadcastRuinQueue.isEmpty()) {
            r = broadcastRuinQueue.pop();
            if (validateRuin(r))
                staticRC.broadcastMessage(ruinToMessage(r));
        }
    }

    private static void fillBroadcastQueue() {
        for (Ruin r : enemyTowers)
            broadcastRuinQueue.add(r);

        for (Ruin r : alliedPaintTowers)
            broadcastRuinQueue.add(r);
    }

    /*
        Send known ruin locations to a nearby troops
            Sends as many Ruins to as many robots as possible each time this method is called (up to 20 messages total)
        This method should be called by towers and not robots
    */
    public static void sendRuinLocationsToTroops() throws GameActionException {
        if (allyRobots.length == 0 || (unclaimedRuins.isEmpty() && enemyTowers.isEmpty() && alliedPaintTowers.isEmpty()))
            return;

        Ruin r;
        int ct;

        int i = rng.nextInt(0, allyRobots.length);
        int first = i;
        do {
            ct = 0;
            while (ct < numRuinsToSendToEachRobot && staticRC.canSendMessage(allyRobots[i].location)) {
                if (sendQueue.isEmpty())
                    fillSendQueue();

                r = sendQueue.pop();
                if (!validateRuin(r)) continue;

                staticRC.sendMessage(allyRobots[i].location, ruinToMessage(r));
                ct++;
            }

            i = (i + 1) % allyRobots.length;
        }
        while (i != first);
    }

    /*
        Fill the send Queue with Ruins from enemyTowers and
        alliedPaintTowers, alternating between each list

        If one list is longer than the other, adds remaining elements to the end
     */
    private static void fillSendQueue() {
        Iterator<Ruin> enemyIt = enemyTowers.iterator();
        Iterator<Ruin> alliedPaintIt = alliedPaintTowers.iterator();

        boolean en = true, al = true;
        while (en || al) {
            en = enemyIt.hasNext();
            al = alliedPaintIt.hasNext();

            if (en) sendQueue.add(enemyIt.next());
            if (al) sendQueue.add(alliedPaintIt.next());
        }
    }


    //CONVERSIONS:

    public static int ruinToMessage(Ruin ruin) {
        int message = 0; //comm code for Ruin Info
        //use the leftmost three digits to communicate symmetry
        message |= symmetries << 28;
        //use the rightmost 17 digits to communicate ruins
        message |= (ruin.location.x << 2);          //Add x location to message
        message |= (ruin.location.y << 8);          //Add y location to message
        message |= ruin.status << 14;               //Add ruin status to message
        message |= ruin.isPaintTower ? 1 << 16 : 0; //Add isPaintTower to message
        //System.out.println(symmetries);
        return message;
    }

    //Takes in an int message and converts into a representative Ruin object
    public static Ruin messageToRuin(int message) {
        if (knownSymmetry == SymmetryType.Unknown && (message >> 28 & 0b1110) != 0b1110) {
            symmetries = message >> 28 & 0b1110;
            switch (symmetries) {
                case 2 -> knownSymmetry = SymmetryType.Rotational;
                case 4 -> knownSymmetry = SymmetryType.Vertical;
                case 8 -> knownSymmetry = SymmetryType.Horizontal;
            }
        }
        MapLocation loc = new MapLocation((message >> 2) & 63, (message >> 8) & 63);
        return new Ruin(loc, (message >> 14) & 3, ((message >> 16) & 1) == 1);
    }

    public static Ruin messageToRuin(Message m) {
        return messageToRuin(m.getBytes());
    }

    //uses the robots knowledege of symmetry to create a message containing only symmetry information
    public static int symmetriesToMessage(int symmetries) {
        int message = 0b10;
        message |= symmetries << 28;
        return message;
    }

    //takes in a message, and updates known symmetry information
    public static void processSymmetryMessageTower(int message) {
        message >>= 28;
        symmetries = message & 0b1110;
        switch (symmetries) {
            case 2 -> knownSymmetry = SymmetryType.Rotational;
            case 4 -> knownSymmetry = SymmetryType.Vertical;
            case 8 -> knownSymmetry = SymmetryType.Horizontal;
            default -> knownSymmetry = SymmetryType.Unknown;
        }
    }


    public static int createExploreLocationMessage(MapLocation location) {
        int message = 0b01;
        message |= location.x << 2;
        message |= location.y << 8;
        return message;
    }

    public static void updateExploreLocation(Message m) throws GameActionException {
        int message = m.getBytes();
        exploreTarget = new MapLocation((message >> 2) & 63, (message >> 8) & 63);
        //System.out.println(exploreTarget);
    }


    //MEMORY MANAGEMENT:

    //prints out the bytecode usage of a single call to updateRuinsMemory
    private static boolean updateRuinsMemoryTest(Ruin ruin) {
        int price;
        boolean ret;

        //System.out.println("Received: " + ruin);

        price = Clock.getBytecodesLeft();
        ret = updateRuinsMemory(ruin);
        price -= Clock.getBytecodesLeft();

        //System.out.println("\tPrice of processing:" + price);

        return ret;
    }

    /*
        Takes in a Ruin; If we already have knowledge of this Ruin, update the status, otherwise add it to memory
        Return false if we already knew of this exact Ruin, Return true if we had to update our memory
     */
    private static boolean updateRuinsMemory(Ruin ruin) {
        if(!seenRuins.contains(ruin.location)){
        unseenRuins.remove(ruin.location);
        seenRuins.add(ruin.location);
        if (knownSymmetry != SymmetryType.Unknown) {
            MapLocation newRuin = switch (knownSymmetry) {
                case Horizontal -> new MapLocation(ruin.location.x, staticRC.getMapHeight() - 1 - ruin.location.y);
                case Rotational ->
                        new MapLocation(staticRC.getMapWidth() - 1 - ruin.location.x, staticRC.getMapHeight() - 1 - ruin.location.y);
                case Vertical -> new MapLocation(staticRC.getMapWidth() - 1 - ruin.location.x, ruin.location.y);
                case Unknown -> null;
            };
            if (!seenRuins.contains(newRuin)) unseenRuins.add(newRuin);
        }
        }
        Ruin mem = allMemory[ruin.location.x][ruin.location.y]; //might be null

        if (ruin.equals(mem)) //we already know of this exact Ruin
        {
            return false;
        }

        if (mem != null) //we have heard of a Ruin at this location before
        {
            switch (mem.status) //remove the old Ruin from its memory category
            {
                case 0:
                    unclaimedRuins.remove(mem);
                    break;
                case 1:
                    if (mem.isPaintTower) {
                        alliedPaintTowers.remove(mem);
                    }
                    break;
                case 2:
                    enemyTowers.remove(mem);
                    break;
            }
        }

        allMemory[ruin.location.x][ruin.location.y] = ruin; //update the memory map

        switch (ruin.status) //add Ruin to appropriate memory category
        {
            case 0: //unclaimed
                unclaimedRuins.add(ruin);
                break;

            case 1: //ally
                if (ruin.isPaintTower)
                    alliedPaintTowers.add(ruin);
                break;

            case 2: //enemy
                enemyTowers.add(ruin);
                break;
        }

        return true;
    }

    /*
        Validate that a Ruin is "correct", i.e. there is a Ruin x in our memory such that r.equals(x)
            This implies that the location and status are accurate

        Return true if the Ruin r is "correct", false otherwise
     */
    private static boolean validateRuin(Ruin r) {
        return r.equals(allMemory[r.location.x][r.location.y]);
    }


    //UTILITIES:

    /*
        Used for testing and debugging
     */
    public static void printRuinsMemory() {
        //System.out.println("Unclaimed ruins:");
        for (Ruin r : unclaimedRuins) {
            System.out.println(r);
        }

        System.out.println("Enemy towers:");
        for (Ruin r : enemyTowers) {
            System.out.println(r);
        }

        System.out.println("Allied paint towers:");
        for (Ruin r : alliedPaintTowers) {
            System.out.println(r);
        }
    }

    /*
        Returns whether a robot is a paint tower
     */
    public static boolean isPaintTower(UnitType type) {
        return type == UnitType.LEVEL_ONE_PAINT_TOWER
                || type == UnitType.LEVEL_TWO_PAINT_TOWER
                || type == UnitType.LEVEL_THREE_PAINT_TOWER;

    }

    public static boolean isPaintTower(RobotInfo info) {
        return isPaintTower(info.getType());
    }

}
