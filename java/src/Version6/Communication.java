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
        Called by RobotPlayer. Calls sendRuinLocationToTower or sendRuinLocationToTroops as
        appropriate, dependent on the type of this unit.
     */
    public static void sendRuinLocations(RobotController rc) throws GameActionException
    {
        if(rc.getType().isRobotType())
        {
            scanForRuins(rc);
            sendRuinLocationsToTower(rc);
        }
        else
        {
            sendRuinLocationsToTroops(rc);
        }
    }

    /*
        Scan for nearby ruin locations and update ruinsMemory as necessary
     */
    private static void scanForRuins(RobotController rc) throws GameActionException
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

    /*
        Send known ruin locations to a nearby tower
        This method should be called by robots and not towers

        Currently, only broadcasts info about paint towers, not ruins or other tower types
    */
    private static void sendRuinLocationsToTower(RobotController rc) throws GameActionException
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

        if(tower == null || ruinsMemory.isEmpty()) return;

        //filter communications to include paint towers only; might be edited/removed later on
        while(!ruinsMemory.get(nextRuinToSend).isPaintTower)
            nextRuinToSend = (nextRuinToSend + 1) % ruinsMemory.size();

        //if there are ruins to be sent and we can message the tower, send a location
        if(rc.canSendMessage(tower, ruinToMessage(ruinsMemory.get(nextRuinToSend))))
        {
            rc.sendMessage(tower, ruinToMessage(ruinsMemory.get(nextRuinToSend)));
            nextRuinToSend = (nextRuinToSend + 1) % ruinsMemory.size();
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
                if((m.getBytes() & 3) != 0) continue;

                updateRuinsMemory(messageToRuin(m));
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
