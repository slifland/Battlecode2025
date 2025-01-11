package Version6;

import battlecode.common.*;
import static Version6.RobotPlayer.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Communication
{
    static Queue<Integer> ruinsQueue = new LinkedList<>();
    static ArrayList<Ruin> ruinsMemory = new ArrayList<>();

    /*
        used by towers to ensure all Ruin locations are sent, important in the situation where tower cannot
        send every ruin location to every robot
    */
    private static int nextRuinToSend = 0;

    /*
        Called by RobotPlayer. Calls sendRuinLocationToTower or sendRuinLocationToTroops as
        appropriate, dependent on the type of this unit.
     */
    public static void sendRuinLocations(RobotController rc) throws GameActionException
    {
        if(rc.getType().isRobotType())
        {
            sendRuinLocationsToTower(rc);
        }
        else
        {
            sendRuinLocationsToTroops(rc);
        }
    }

    /*
        Scan all rune locations and send them to a nearby tower, this method should be
        called by robots and not towers
    */
    private static void sendRuinLocationsToTower(RobotController rc) throws GameActionException
    {
        //First determine all ruins nearby and add them to the queue
        for(MapLocation ruin : nearbyRuins)
        {
            int message = 0;
            message |= (ruin.x << 2);   //Add x location to message
            message |= (ruin.y << 8);  //Add y location to message

            //Add the team information to the message
            RobotInfo ruinTeam = rc.senseRobotAtLocation(ruin);
            if(ruinTeam != null)
            {
                message = ruinTeam.team == rc.getTeam() ?
                        message | 1 << 14 : message | 2 << 14;
            }
            if(!ruinsQueue.contains(message))
            {
                ruinsQueue.add(message);
            }

            updateRuinsMemory(messageToRuin(message));
        }

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
        if(tower == null)
        {
            return;
        }

        //if there are ruins to be sent and we can message the tower, send a location
        if(!ruinsQueue.isEmpty() && rc.canSendMessage(tower, ruinsQueue.peek()))
        {
            int message = ruinsQueue.remove();
            rc.sendMessage(tower, message);
            //MapLocation location = new MapLocation((message >> 2) & 63, (message >> 8) & 63);
            //Ruin ruin = new Ruin(location, (message >> 14) & 3);
        }
    }

    private static void sendRuinLocationsToTroops(RobotController rc) throws GameActionException
    {
        if(ruinsMemory.size() == 0) return;

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

    //looks through messages from the current round and the previous round, updating ruinsMemory
    public static void receiveRuinLocations(RobotController rc)
    {
        Message[][] messages = {rc.readMessages(rc.getRoundNum()), rc.readMessages(rc.getRoundNum() - 1)};

        for(Message[] mArr : messages)
        {
            for(Message m : mArr)
            {
                updateRuinsMemory(messageToRuin(m));
            }
        }
    }

    public static int ruinToMessage(Ruin ruin)
    {
        int message = 0;
        message |= (ruin.location.x << 2);   //Add x location to message
        message |= (ruin.location.y << 8);  //Add y location to message
        message |= ruin.status << 14;       //Add ruin status to message

        return message;
    }


    //Takes in an int message and converts into a representative Ruin object
    public static Ruin messageToRuin(int message)
    {
        MapLocation loc = new MapLocation((message >> 2) & 63, (message >> 8) & 63);
        return new Ruin(loc, (message >> 14) & 3);
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

}
