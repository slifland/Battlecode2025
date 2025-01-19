package Version10;

import battlecode.common.MapLocation;
import battlecode.common.RobotController;

import static Version10.RobotPlayer.rng;

public class Sector
{
    int sectorIndex;
    MapLocation sectorCorner;

    int numCovered;//ranges from 0 - 15

    public static boolean[] hasTraveled;


    public Sector(RobotController rc, int sectorIndex)
    {
        this.sectorIndex = sectorIndex;
        this.sectorCorner = getSectorCorner(rc, sectorIndex);
        this.numCovered = 0;
    }

    public static int ceil(int a, int b)
    {
        return a / b + ((a % b == 0) ? 0 : 1);
    }

    public static int getSector(RobotController rc, MapLocation location)
    {
        MapLocation corner = new MapLocation(location.x - (location.x % 7), location.y - (location.y % 7));
        return corner.x / 7 + (corner.y / 7) * ceil(rc.getMapWidth(), 7);
    }

    public static MapLocation getSectorCorner(RobotController rc, int sectorIndex)
    {
        int numPerRow = ceil(rc.getMapWidth(), 7);
        return new MapLocation( sectorIndex % numPerRow * 7 , sectorIndex / numPerRow * 7 );
    }

    //returns a random location within the given sector
    public static MapLocation getRandomLocation(RobotController rc, int sectorIndex) {
        int numPerRow = ceil(rc.getMapWidth(), 7);
        return new MapLocation( sectorIndex % numPerRow * 7 + rng.nextInt(7) , sectorIndex / numPerRow * 7 + rng.nextInt(7));
    }

    public void updateAllyTiles(int numAllyTiles)
    {
        numCovered = Math.max(numAllyTiles, numCovered);
    }

    public void updateEnemyTiles(int numEnemyTiles)
    {
        numCovered = Math.max(0, numCovered - numEnemyTiles);
    }
}