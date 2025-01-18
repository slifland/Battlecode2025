package Version8;

import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class Sector
{
    int sectorIndex;
    MapLocation sectorCorner;

    int numCovered; //ranges from 0 - 15


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

    public void updateAllyTiles(int numAllyTiles)
    {
        numCovered = Math.max(numAllyTiles, numCovered);
    }

    public void updateEnemyTiles(int numEnemyTiles)
    {
        numCovered = Math.max(0, numCovered - numEnemyTiles);
    }
}
