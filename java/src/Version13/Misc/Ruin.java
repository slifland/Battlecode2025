package Version13.Misc;

import battlecode.common.*;

public class Ruin
{
    public MapLocation location;
    public int status;              //0 - unclaimed, 1 - ally, 2 - enemy
    public boolean isPaintTower;

    public Ruin(MapLocation location, int status, boolean isPaintTower)
    {
        this.location = location;
        this.status = status;
        this.isPaintTower = isPaintTower;
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

        return "Location: " + location + ", Owned by: " + ownership + (isPaintTower ? ", Paint Tower" : ", Non-Paint Tower");
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == this) return true;
        if(o == null) return false;
        if(!(o instanceof Ruin)) return false;

        Ruin r = (Ruin)o;

        return location.equals(r.location) && status == r.status && isPaintTower == r.isPaintTower;
    }
}
