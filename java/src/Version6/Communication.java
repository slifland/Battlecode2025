package Version6;

import battlecode.common.*;
import static Version6.RobotPlayer.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Communication
{
    static ArrayList<Ruin> ruinsMemory = new ArrayList<>();

    /*
        Cycles through ruinsMemory, determines which ruin is next to be broadcast
        Ensures that, over time, all Ruin locations are sent
    */
    private static int nextRuinToSend = 0;
    /*
        Scan for nearby ruin locations and update ruinsMemory as necessary
     */
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
                switch (m.getBytes() >> 2 & 0b11)
                {
                    case 0b00 -> updateRuinsMemory(messageToRuin(m));
                    case 0b01 -> updatePaintAveragesTower(rc, readAverageMessage(m));
                }
            }
        }
    }

    public static void sendMessagesTower(RobotController rc) throws GameActionException
    {
        Communication.sendRuinLocationsToTroops(rc);
    }

    public static void sendMessagesRobot(RobotController rc) throws GameActionException
    {
        //Check if there is an available tower to broadcast to
        MapLocation tower = null;
        for(RobotInfo robot : allyRobots)
        {
            if(robot.getType().isTowerType())
            {
                tower = robot.getLocation();
                break;
            }
        }
        if(tower == null || !rc.canSendMessage(tower)) return;

        //We have different kinds of messages to send so let's alternate every round
        switch (rc.getRoundNum() % 2)
        {
            case 0 -> sendRuinLocationsToTower(rc, tower);
            case 1 -> sendAveragesToTower(rc, tower);
        }

    }

    public static void scanForRuins(RobotController rc) throws GameActionException
    {
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
                else
                    status = 2;

                //get whether it is a paint tower
                if(isPaintTower(ruinInfo))
                    isPaintTower = true;
            }

            updateRuinsMemory(new Ruin(ruinLoc, status, isPaintTower));
        }
    }

    public static void sendAveragesToTower(RobotController rc, MapLocation tower) throws GameActionException
    {
        rc.sendMessage(tower, createAverageMessage());
    }

    /*
        Send known ruin locations to a nearby tower
        This method should be called by robots and not towers

        Currently, only broadcasts info about paint towers, not ruins or other tower types
    */
    public static void sendRuinLocationsToTower(RobotController rc, MapLocation tower) throws GameActionException
    {

        if(ruinsMemory.isEmpty()) return;

        ////////////////////////filter communications to include allied paint towers only; might be edited/removed later on///////////////////////////////
        while(!(ruinsMemory.get(nextRuinToSend).isAllied() && ruinsMemory.get(nextRuinToSend).isPaintTower))
            nextRuinToSend = (nextRuinToSend + 1) % ruinsMemory.size();

        //if there are ruins to be sent and we can message the tower, send a location
        if(rc.canSendMessage(tower, ruinToMessage(ruinsMemory.get(nextRuinToSend))))
        {
            rc.sendMessage(tower, ruinToMessage(ruinsMemory.get(nextRuinToSend)));
            nextRuinToSend = (nextRuinToSend + 1) % ruinsMemory.size();
        }
    }

    public static void sendRuinLocationsToTroops(RobotController rc) throws GameActionException
    {
        if(ruinsMemory.isEmpty()) return;

        int startIndex;

        for(RobotInfo ri : allyRobots)
        {
            startIndex = nextRuinToSend;
            while(rc.canSendMessage(ri.location) && nextRuinToSend != startIndex)
            {
                rc.sendMessage(ri.location, ruinToMessage(ruinsMemory.get(nextRuinToSend)));
                nextRuinToSend = (nextRuinToSend + 1) % ruinsMemory.size();
            }
        }

    }

    public static int ruinToMessage(Ruin ruin)
    {
        int message = 0; //comm code for Ruin Info

        message |= (ruin.location.x << 2);          //Add x location to message
        message |= (ruin.location.y << 8);          //Add y location to message
        message |= ruin.status << 14;               //Add ruin status to message
        message |= ruin.isPaintTower ? 1 << 16 : 0; //Add isPaintTower to message

        return message;
    }


    //Takes in an int message and converts into a representative Ruin object
    public static Ruin messageToRuin(int message)
    {
        MapLocation loc = new MapLocation((message >> 2) & 63, (message >> 8) & 63);
        return new Ruin(loc, (message >> 14) & 3, ((message >> 16) & 1) == 1);
    }
    public static Ruin messageToRuin(Message m)
    {
        return messageToRuin(m.getBytes());
    }

    //Takes in a Ruin; If we already have knowledge of this Ruin, update the status, otherwise add it to memory
    public static void updateRuinsMemory(Ruin ruin)
    {
        for(int i = 0; i < ruinsMemory.size(); i++)
        {
            if(ruinsMemory.get(i).location.equals(ruin.location))
            {
                ruinsMemory.set(i, ruin);
                return;
            }
        }

        ruinsMemory.add(ruin);
    }

    public static int createAverageMessage()
    {
        int message = 0b01;
        message |= (paintCount1 == 0 ? 0 : 1) << 2; //Indicate average1's emptiness
        message |= paintAverage1.x << 3;
        message |= paintAverage1.y << 9;
        message |= (paintCount2 == 0 ? 0 : 1) << 15; //Indicate average2's emptiness
        message |= paintAverage2.x << 16;
        message |= paintAverage2.y << 22;
        return message;
    }

    public static MapLocation[] readAverageMessage(Message m)
    {
        return readAverageMessage(m.getBytes());
    }

    public static MapLocation[] readAverageMessage(int message)
    {
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
//        for(MapLocation location : locations)
//        {
//            System.out.println(location);
//            if(paintCount1 == 0)
//            {
//                paintAverage1 = location;
//                paintCount1++;
//                continue;
//            }
//
//            if(location.isWithinDistanceSquared(paintAverage1, distanceThreshold))
//            {
//                int x = (location.x + paintAverage1.x * paintCount1) / (1 + paintCount1);
//                int y = (location.y + paintAverage1.y * paintCount1) / (1 + paintCount1);
//                paintAverage1 = new MapLocation(x, y);
//                paintCount1++;
//            }
//            else
//            {
//                int x = (location.x + paintAverage2.x * paintCount2) / (1 + paintCount2);
//                int y = (location.y + paintAverage1.y * paintCount2) / (1 + paintCount2);
//                paintAverage2 = new MapLocation(x, y);
//                paintCount2++;
//            }
//        }
    }

    /*
        Used for testing and debugging
     */
    public static void printRuinsMemory()
    {
        System.out.println("Num ruins in memory: " + ruinsMemory.size());
        for(Ruin ruin : ruinsMemory)
        {
            System.out.println("\t" + ruin);
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
