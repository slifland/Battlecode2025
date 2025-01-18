package Version9;

import battlecode.common.*;

import static Version9.RobotPlayer.*;

import java.util.LinkedList;

public class Communication
{

    private static class Queue
    {
        private static final int len = 200;

        private final Ruin[] ruins;
        private int head = 0, tail = -1;

        public Queue()
        {
            ruins = new Ruin[len];
        }

        public void add(Ruin r)
        {
            tail = (tail + 1) % len;
            ruins[tail] = r;
        }

        //pre: !isEmpty()
        public Ruin pop()
        {
            Ruin ret = ruins[head];
            head = (head + 1) % len;
            return ret;
        }

        public boolean isEmpty()
        {
            return tail == head - 1; //slight jank, but I'm assuming we never get to len number of items
        }
    }

    static final Queue sendQueue = new Queue();

    static final LinkedList<Ruin> unclaimedRuins = new LinkedList<>(); //remembered, but not sent in comms
    static final LinkedList<Ruin> enemyTowers = new LinkedList<>();
    static final LinkedList<Ruin> alliedPaintTowers = new LinkedList<>();

    /*
        Stores Ruins based on their location for instant access
        [x position][y position]
     */
    static Ruin[][] allMemory;

    /*
        The maximum number of Ruins messages a tower will send in any given turn
            We might want to limit this to limit bytecode usage
     */
    static final int maxRuinsToSend = GameConstants.MAX_MESSAGES_SENT_TOWER; //20

    /*
        Process all incoming messages for robots
    */
    public static void processMessagesRobot(RobotController rc)
    {
        Message[][] messages = {rc.readMessages(rc.getRoundNum()), rc.readMessages(rc.getRoundNum() - 1)};

        for(Message[] mArr : messages)
        {
            for(Message m : mArr)
            {
                switch (m.getBytes() & 0b11)
                {
                    case 0b00 -> updateRuinsMemory(messageToRuin(m));
                    //case 0b01 -> updatePaintAveragesRobot(readAverageMessage(m));
                }

            }
        }

        //TESTING*/System.out.println("-");
    }

    /*
        Process all incoming messages for towers
    */
    public static void processMessagesTower(RobotController rc)
    {
        Message[][] messages = {rc.readMessages(rc.getRoundNum()), rc.readMessages(rc.getRoundNum() - 1)};

        for(Message[] mArr : messages)
        {
            for(Message m : mArr)
            {
                switch (m.getBytes() & 0b11)
                {
                    case 0b00 -> updateRuinsMemory(messageToRuin(m));
                    //case 0b01 -> updatePaintAveragesRobot(readAverageMessage(m));
                    case 0b01 -> processSymmetryMessageTower(m.getBytes());
                }

            }
        }

        //TESTING*/System.out.println("-");
    }

    public static void sendMessagesTower(RobotController rc) throws GameActionException
    {
        sendRuinLocationsToTroops(rc);
    }

    public static void sendMessagesRobot(RobotController rc) throws GameActionException
    {
        //Find available towers to broadcast to, select one randomly

        int i = 0;
        MapLocation[] tower = new MapLocation[30];
        for(RobotInfo robot : allyRobots)
        {
            if(robot.getType().isTowerType() && rc.canSendMessage(robot.location))
            {
                tower[i++] = robot.getLocation();
            }
        }
        if(i == 0) return;

        sendRuinLocationsToTower(rc, tower[rng.nextInt(0, i)]);


        /* uncomment this if we decide to send paint averages in comms

        MapLocation towerToSend = tower[rng.nextInt(0, i)];
        //We have different kinds of messages to send so let's alternate every round
        switch (rc.getRoundNum() % 2)
        {
            case 0 -> sendRuinLocationsToTower(rc, towerToSend);
            case 1 -> sendAveragesToTower(rc, towerToSend);
        }
        */
    }

    /*
        Scan for nearby ruin locations and update ruinsMemory as necessary
     */
    public static void scanForRuins(RobotController rc) throws GameActionException
    {
        Mopper.nearbyRuin = null;
        //TESTING*/System.out.println("~Scanning for ruins");

        for(MapLocation ruinLoc : nearbyRuins)
        {
            int status = 0;
            boolean isPaintTower = false;

            RobotInfo ruinInfo = rc.senseRobotAtLocation(ruinLoc);

            /*
                If the ruin is unclaimed, ruinInfo will be null
                    status will be left as 0
                    isPaintTower will be left as false
             */
            if(ruinInfo != null)
            {
                //get the team information
                if(ruinInfo.team == rc.getTeam())
                    status = 1;
                else {
                    status = 2;
                    Version9.Splasher.seenEnemyTower = ruinInfo;
                    Version9.Mopper.seenEnemyTower = ruinInfo;
                }

                //get whether it is a paint tower
                if(isPaintTower(ruinInfo))
                    isPaintTower = true;
            }
            else {
                Mopper.nearbyRuin = ruinLoc;
            }

            updateRuinsMemory(new Ruin(ruinLoc, status, isPaintTower));
        }

        //TESTING*/System.out.println("~");
    }


    /*
    public static void sendAveragesToTower(RobotController rc, MapLocation tower) throws GameActionException
    {
        rc.sendMessage(tower, createAverageMessage());
    }

    public static void sendAveragesToRobot(RobotController rc, MapLocation robot) throws GameActionException
    {
        int message = createAverageMessage();
        if(rc.canSendMessage(robot, message))
        {
            rc.sendMessage(robot, message);
        }
    }
    */


    /*
        Send known ruin locations to a nearby tower
            Sends one Ruin each time this method is called
        This method should be called by robots and not towers

        Robot will alternate between sending an enemy tower and sending an allied paint tower
    */
    public static void sendRuinLocationsToTower(RobotController rc, MapLocation tower) throws GameActionException
    {
        if(unclaimedRuins.isEmpty() && enemyTowers.isEmpty() && alliedPaintTowers.isEmpty()) {
            if(knownSymmetry != Symmetry.Unknown && rc.canSendMessage(tower)) {
                rc.sendMessage(tower, symmetriesToMessage(symmetries));
            }
            return;
        }

        if(sendQueue.isEmpty())
            fillSendQueue();

        if(rc.canSendMessage(tower) && !sendQueue.isEmpty())
        {
            rc.sendMessage(tower, ruinToMessage(sendQueue.pop()));
        }
    }

    /*
    Broadcasts information the tower has to nearby towers - currently only sends info about symmetry
     */
    public static void broadcastMessages(RobotController rc) throws GameActionException {
        if(rc.canBroadcastMessage() && knownSymmetry != Symmetry.Unknown) {
            rc.broadcastMessage(symmetriesToMessage(symmetries));
        }
    }

    /*
        Send known ruin locations to a nearby troops
            Sends as many Ruins to as many robots as possible each time this method is called (up to 20 messages total)
        This method should be called by towers and not robots
    */
    public static void sendRuinLocationsToTroops(RobotController rc) throws GameActionException
    {
        if(allyRobots.length == 0 || (unclaimedRuins.isEmpty() && enemyTowers.isEmpty() && alliedPaintTowers.isEmpty())) return;

        if(sendQueue.isEmpty())
            fillSendQueue();

        int messagesSent = 0;

        int i = rng.nextInt(0, allyRobots.length);
        int first = i;
        do
        {
            if(rc.canSendMessage(allyRobots[i].getLocation()))
            {
                while(!sendQueue.isEmpty() && rc.canSendMessage(allyRobots[i].location))
                {
                    rc.sendMessage(allyRobots[i].location, ruinToMessage(sendQueue.pop()));
                    if(++messagesSent >= maxRuinsToSend) return;
                }
            }

            i = (i + 1) % allyRobots.length;
        }
        while(i != first);
    }

    private static void fillSendQueue()
    {
//        for(Ruin r : unclaimedRuins)
//            sendQueue.add(r);

        for(Ruin r : enemyTowers)
            sendQueue.add(r);

        for(Ruin r : alliedPaintTowers)
            sendQueue.add(r);
    }

    public static int ruinToMessage(Ruin ruin)
    {
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
    public static Ruin messageToRuin(int message)
    {
        if(knownSymmetry == Symmetry.Unknown && (message >> 28 & 0b1110) != 0b1110) {
            symmetries = message >> 28 & 0b1110;
                switch (symmetries) {
                    case 2 -> knownSymmetry = Symmetry.Rotational;
                    case 4 -> knownSymmetry = Symmetry.Vertical;
                    case 8 -> knownSymmetry = Symmetry.Horizontal;
                }
        }
        MapLocation loc = new MapLocation((message >> 2) & 63, (message >> 8) & 63);
        return new Ruin(loc, (message >> 14) & 3, ((message >> 16) & 1) == 1);
    }
    public static Ruin messageToRuin(Message m)
    {
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
        switch(symmetries) {
            case 2 -> knownSymmetry = Symmetry.Rotational;
            case 4 -> knownSymmetry = Symmetry.Vertical;
            case 8 -> knownSymmetry = Symmetry.Horizontal;
            default -> knownSymmetry = Symmetry.Unknown;
        }
    }

    //prints out the bytecode usage of a single call to updateRuinsMemory
    private static void updateRuinsMemoryTest(Ruin ruin)
    {
        int price = Clock.getBytecodesLeft();
        updateRuinsMemory(ruin);
        System.out.println("Price of processing:" + (price - Clock.getBytecodesLeft()));
    }

    //Takes in a Ruin; If we already have knowledge of this Ruin, update the status, otherwise add it to memory
    private static void updateRuinsMemory(Ruin ruin)
    {
        Ruin mem = allMemory[ruin.location.x][ruin.location.y]; //might be null

        if(ruin.equals(mem)) //we already know of this exact Ruin
        {
            return;
        }
        else if(mem == null) //this is the first Ruin at this location we have heard of
        {
            allMemory[ruin.location.x][ruin.location.y] = ruin;

            switch(ruin.status)
            {
                case 0: //unclaimed
                    unclaimedRuins.add(ruin);
                    break;

                case 1: //ally
                    if(ruin.isPaintTower)
                    {
                        alliedPaintTowers.add(ruin);
                    }
                    break;

                case 2: //enemy
                    enemyTowers.add(ruin);
                    break;
            }
        }
        else //we have heard of a Ruin at this location before, and need to update its status
        {
            switch(mem.status) {
                case 0:
                    unclaimedRuins.remove(mem);
                    break;
                case 1:
                    if(ruin.isPaintTower) {
                        alliedPaintTowers.remove(mem);
                    }
                    break;
                case 2:
                    enemyTowers.remove(mem);
                    break;
            }
            allMemory[ruin.location.x][ruin.location.y] = ruin;

            switch(ruin.status)
            {
                case 0: //unclaimed
                    unclaimedRuins.add(ruin);
                    break;

                case 1: //ally
                    if(ruin.isPaintTower)
                    {
                        alliedPaintTowers.add(ruin);
                    }
                    break;

                case 2: //enemy
                    enemyTowers.add(ruin);
                    break;
            }
        }


            /* OLD - nothing below here

        Ruin next;

        ListIterator<Ruin> iterator = unclaimedRuins.listIterator();
        while(iterator.hasNext())
        {
            next = iterator.next();
            if(next.location.equals(ruin.location))
            {
                if(next.equals(ruin))
                {
                    return; //we already know of this exact Ruin
                }
                else
                {
                    iterator.remove();
                }
            }
        }

        iterator = enemyTowers.listIterator();
        while(iterator.hasNext())
        {
            next = iterator.next();
            if(next.location.equals(ruin.location))
            {
                if(next.equals(ruin))
                {
                    return; //we already know of this exact Ruin
                }
                else
                {
                    iterator.remove();
                }
            }
        }

        iterator = alliedPaintTowers.listIterator();
        while(iterator.hasNext())
        {
            next = iterator.next();
            if(next.location.equals(ruin.location))
            {
                if(next.equals(ruin))
                {
                    return; //we already know of this exact Ruin
                }
                else
                {
                    iterator.remove();
                }
            }
        }

        switch(ruin.status)
        {
            case 0: //unclaimed
                unclaimedRuins.add(ruin);
                break;

            case 1: //ally
                if(ruin.isPaintTower)
                {
                    alliedPaintTowers.add(ruin);
                }
                break;

            case 2: //enemy
                enemyTowers.add(ruin);
                break;
        }

            */
    }

    /*
    public static int createAverageMessage()
    {
        int message = 0b01;
        message |= symmetries << 28;
        message |= (paintCount1 == 0 ? 0 : 1) << 2; //Indicate average1's emptiness
        message |= paintAverage1.x << 3;
        message |= paintAverage1.y << 9;
        message |= (paintCount2 == 0 ? 0 : 1) << 15; //Indicate average2's emptiness
        message |= paintAverage2.x << 16;
        message |= paintAverage2.y << 22;
        return message;
    }
    */

    public static MapLocation[] readAverageMessage(Message m)
    {
        return readAverageMessage(m.getBytes());
    }

    public static MapLocation[] readAverageMessage(int message)
    {
        //System.out.println("HIIIIII");
        symmetries = message >> 28 & 0b1110;
        //System.out.println(symmetries);
        if(knownSymmetry == Symmetry.Unknown) {
            switch (symmetries) {
                case 2 -> knownSymmetry = Symmetry.Rotational;
                case 4 -> knownSymmetry = Symmetry.Vertical;
                case 8 -> knownSymmetry = Symmetry.Horizontal;
            }
        }
        if((message >> 2 & 1) == 0 && (message >> 15 & 1) == 0)      //Both locations are empty
        {
            return new MapLocation[0];
        }
        else if((message >> 2 & 1) == 1 && (message >> 15 & 1) == 0) //Location1 is used, Location2 is empty
        {
            return new MapLocation[]{new MapLocation(message >> 3 & 63, message >> 9 & 63)};
        }
        else if((message >> 2 & 1) == 0 && (message >> 15 & 1) == 1) //Location2 is used, Location1 is empty
        {
            return new MapLocation[]{new MapLocation(message >> 16 & 63, message >> 22 & 63)};
        }
        else                                                         //Location 1 is used, Location2 is empty
        {
            return new MapLocation[]{new MapLocation(message >> 3 & 63, message >> 9 & 63),
                    new MapLocation(message >> 16 & 63, message >> 22 & 63)};
        }
    }

    public static void updatePaintAveragesTower(RobotController rc, MapLocation[] locations)
    {
        if(locations.length == 2)
        {
            paintAverage1 = locations[0];
            paintAverage2 = locations[1];
        }
        if(locations.length == 1)
        {
            paintAverage2 = locations[0];
        }
        for(MapLocation location : locations)
        {
            //System.out.println(location);
            if(paintCount1 == 0)
            {
                paintAverage1 = location;
                paintCount1++;
                continue;
            }

            if(location.isWithinDistanceSquared(paintAverage1, distanceThreshold))
            {
                int x = (location.x + paintAverage1.x * paintCount1) / (1 + paintCount1);
                int y = (location.y + paintAverage1.y * paintCount1) / (1 + paintCount1);
                paintAverage1 = new MapLocation(x, y);
                paintCount1++;
            }
            else
            {
                int x = (location.x + paintAverage2.x * paintCount2) / (1 + paintCount2);
                int y = (location.y + paintAverage1.y * paintCount2) / (1 + paintCount2);
                paintAverage2 = new MapLocation(x, y);
                paintCount2++;
            }
        }
    }


    /*
        Used for testing and debugging
     */
    public static void printRuinsMemory()
    {
        System.out.println(unclaimedRuins.size() + " Unclaimed ruins:");
        for(Ruin r : unclaimedRuins)
        {
            System.out.println(r);
        }

        System.out.println(enemyTowers.size() + " Enemy towers:");
        for(Ruin r : enemyTowers)
        {
            System.out.println(r);
        }

        System.out.println(alliedPaintTowers.size() + " Allied paint towers:");
        for(Ruin r : alliedPaintTowers)
        {
            System.out.println(r);
        }
    }

    private static void updatePaintAveragesRobot(MapLocation[] locations)
    {
        for(MapLocation location : locations)
        {
            if(paintAverage1.equals(new MapLocation(0,0)))
            {
                paintAverage1 = location;
                paintCount1 = 1;
            }
            else if(paintAverage2.equals(new MapLocation(0,0)))
            {
                paintAverage2 = location;
                paintCount2 = 1;
            }
        }
    }

    /*
        Returns whether a robot is a paint tower
     */
    public static boolean isPaintTower(RobotInfo info)
    {
        UnitType type = info.getType();

        return     type == UnitType.LEVEL_ONE_PAINT_TOWER
                || type == UnitType.LEVEL_TWO_PAINT_TOWER
                || type == UnitType.LEVEL_THREE_PAINT_TOWER;
    }

}
