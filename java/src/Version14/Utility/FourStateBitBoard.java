package Version14.Utility;
import battlecode.common.MapLocation;

import static Version14.RobotPlayer.*;

public class FourStateBitBoard {
    int[] bitArray;

    public FourStateBitBoard() {
        int length = (mapSize % 16 == 0) ? (mapSize / 16) : ((mapSize / 16) + 1);
        bitArray = new int[length];
    }

    public int locationToIndex(MapLocation loc) {
        return (loc.x + loc.y * staticRC.getMapWidth()) * 2;
    }

    public MapLocation indexToLocation(int index) {
        return new MapLocation((index / 2) % staticRC.getMapWidth(), (index / 2) / staticRC.getMapWidth());    }

//    public int getBits(MapLocation loc) {
//        int index = locationToIndex(loc);
//        return ((bitArray[index / 32] >> (index % 32)) & 3);
//    }
    int getBits(MapLocation loc) {
        int index = locationToIndex(loc);
        int intIdx = index / 32;
        int bitOffset = index % 32;

        if (bitOffset == 31) {
            // Handle case where bits span two integers
            int firstBit = (bitArray[intIdx] >>> bitOffset) & 1;
            int secondBit = (bitArray[intIdx + 1] & 1) << 1;
            return firstBit | secondBit;
        } else {
            return (bitArray[intIdx] >>> bitOffset) & 3;
        }
    }

    public void setBits(MapLocation loc, int value) {
        int index = locationToIndex(loc); // Compute bit-level index
        int intIdx = index / 32;          // The integer in `bitArray` to modify
        int bitOffset = index % 32;       // The bit position within this integer

        // Clear the existing 2 bits (if they don't span integers)
        bitArray[intIdx] &= ~(3 << bitOffset);

        if (bitOffset == 31) {
            // Special case: the 2 bits span across two integers in the array
            bitArray[intIdx] &= ~(1 << 31); // Clear the last bit of this integer
            bitArray[intIdx] |= (value & 1) << 31; // Set the first bit of the value

            if (intIdx + 1 < bitArray.length) {
                bitArray[intIdx + 1] &= ~1; // Clear the first bit of the next integer
                bitArray[intIdx + 1] |= (value >>> 1); // Set the second bit of the value
            }
        } else if (bitOffset == 30) {
            // Special case: the second bit of the value is the last bit of this integer
            bitArray[intIdx] |= (value << bitOffset) & (3 << bitOffset); // Set two bits

            if (intIdx + 1 < bitArray.length) {
                // Carry over the second bit into the next integer
                bitArray[intIdx + 1] &= ~1; // Clear the first bit of the next integer
                bitArray[intIdx + 1] |= (value >>> 2);
            }
        } else {
            // Normal case: both bits fit within a single integer
            bitArray[intIdx] |= (value & 3) << bitOffset;
        }
    }


}
