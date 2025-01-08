package Version3;

import battlecode.common.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Communication
{
    static Queue<Integer> ruinsQueue = new LinkedList<>();
    static ArrayList<Ruin> ruinsMemory = new ArrayList<>();

    /*
        Scan all rune locations and send them to a nearby tower, this method should be
        called by robots and not towers
    */
    public static void sendRuinLocationToTower(RobotController rc) throws GameActionException
    {
        //First determine all ruins nearby and add them to the queue
        MapLocation[] ruins = rc.senseNearbyRuins(-1);
        for(MapLocation ruin : ruins)
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
        RobotInfo[] allies = rc.senseNearbyRobots(-1, rc.getTeam());
        MapLocation tower = null;
        for(RobotInfo robot : allies)
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

    //looks through messages from the current round and the previous round, updating ruinsMemory
    public static void receiveRuinLocations(RobotController rc)
    {
        Message[][] messages = {rc.readMessages(rc.getRoundNum()), rc.readMessages(rc.getRoundNum() - 1)};

        for(Message[] mArr : messages)
        {
            for(Message m : mArr)
            {
                updateRuinsMemory(messageToRuin(m.getBytes()));
            }
        }
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

}
