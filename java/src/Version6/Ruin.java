package Version6;

import battlecode.common.*;

public class Ruin
{
    public MapLocation location;
    public int status;              //0 - empty, 1 - ally, 2 - enemy

    public Ruin(MapLocation location, int status)
    {
        this.location = location;
        this.status = status;
    }

    public String toString()
    {
        String ownership = "Unclaimed";
        if(status == 1)
        {
            ownership = "Ally";
        }
        else if(status == 2)
        {
            ownership = "Enemy";
        }
        return "Location: " + location + ", Owned by: " + ownership;
    }
}
