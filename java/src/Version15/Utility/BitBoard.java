package Version15.Utility;
import battlecode.common.MapLocation;

import static Version15.RobotPlayer.*;

public class BitBoard {
    int[] bitArray;

    public BitBoard() {
        int length = (mapSize % 32 == 0) ? (mapSize / 32) : ((mapSize / 32) + 1);
        bitArray = new int[length];
    }

    public int locationToIndex(MapLocation loc) {
        return (loc.x + loc.y * rc.getMapWidth());
    }

    public MapLocation indexToLocation(int index) {
        return new MapLocation(index % rc.getMapWidth(), index / rc.getMapWidth());
    }

    public boolean getBit(MapLocation loc) {
        int index = locationToIndex(loc);
        return ((bitArray[index / 32] >> (index % 32)) & 1) == 1;
    }

    public void setBit(MapLocation loc, boolean value) {
        int index = locationToIndex(loc);
        if (value) {
            bitArray[index / 32] |= (1 << (index % 32));
        } else {
            bitArray[index / 32] &= ~(1 << (index % 32));
        }
    }
}
