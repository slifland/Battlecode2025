package Version14.Utility;

import battlecode.common.MapInfo;
import battlecode.common.MapLocation;

import static Version14.RobotPlayer.staticRC;
import static Version14.RobotPlayer.turnCount;


public class Symmetry {

    public static int symmetries = 0b1110; //used to store what symmetries are true - horizontal, vertical, rotational (LSB is always zero)

    public enum SymmetryType {
        Horizontal, Vertical, Rotational, Unknown
    }

    //keeps track of ruins we know exist, but haven't seen
    public static final FastIterableLocSet unseenRuins = new FastIterableLocSet();
    //keeps track of ruins we have seen
    public static FastIterableLocSet seenRuins = new FastIterableLocSet();

    public static boolean hasProcessedSymmetry = false;

    public static int[][] map; //used for map symmetry - 0 is not checked, 1 is not of interest, 2 is wall, 3 is ruin

    public static SymmetryType knownSymmetry = SymmetryType.Unknown; //0 - horizontal, 1 = vertical, 2 = rotational, -1 = unknown


    public static void processTile(MapInfo toCheck) {
        MapLocation locToCheck = toCheck.getMapLocation();
        //try to figure out what the symmetry is
        if (knownSymmetry == SymmetryType.Unknown) {
            int score = (toCheck.isPassable()) ? 1 : toCheck.isWall() ? 2 : 3;
            map[locToCheck.x][locToCheck.y] = score;
            if(score <= 1) return;
            int x = locToCheck.x;
            int y = locToCheck.y;
            int toCompareHorizontal = map[x][map[0].length - 1 - y];
            int toCompareVertical = map[map.length - 1 - x][y];
            int toCompareRotational = map[map.length - 1 - x][map[0].length - 1 - y];
            int horizontal = 0b1000 & symmetries;
            int vertical = 0b0100 & symmetries;
            int rotational = 0b0010 & symmetries;
            int finalMask = 0b1110;
            if (horizontal != 0 && toCompareHorizontal != score && toCompareHorizontal != 0) {
                finalMask &= 0b0110;
            }
            if (vertical != 0 && toCompareVertical != score && toCompareVertical != 0) {
                finalMask &= 0b1010;
            }
            if (rotational != 0 && toCompareRotational != score && toCompareRotational != 0) {
                finalMask &= 0b1100;
            }
            symmetries &= finalMask;
            switch (symmetries) {
                case 2 -> knownSymmetry = SymmetryType.Rotational;
                case 4 -> knownSymmetry = SymmetryType.Vertical;
                case 8 -> knownSymmetry = SymmetryType.Horizontal;
            }
            if(knownSymmetry != SymmetryType.Unknown) {
                processSymmetry();
                hasProcessedSymmetry = true;
            }
        }
        //if we already know symmetry, then only care if there is a ruin
        else {
            if(!hasProcessedSymmetry) {
                processSymmetry();
                hasProcessedSymmetry = true;
            }
            if (toCheck.hasRuin()) {
                MapLocation newRuin = switch (knownSymmetry) {
                    case Horizontal -> new MapLocation(locToCheck.x, staticRC.getMapHeight() - 1 - locToCheck.y);
                    case Rotational ->
                            new MapLocation(staticRC.getMapWidth() - 1 - locToCheck.x, staticRC.getMapHeight() - 1 - locToCheck.y);
                    case Vertical -> new MapLocation(staticRC.getMapWidth() - 1 - locToCheck.x, locToCheck.y);
                    case Unknown -> null;
                };
                    unseenRuins.remove(locToCheck);
                    if(!seenRuins.contains(newRuin)) unseenRuins.add(newRuin);
            }
        }
    }

    //used to update our knowledge after learning of symmetry
    public static void processSymmetry() {
        seenRuins.updateIterable();
        for(int i = 0; i < seenRuins.size; i++) {
            MapLocation opposite = opposite(seenRuins.get(i));
            if(!seenRuins.contains(opposite)) {
                    unseenRuins.add(opposite);
            }
        }
        unseenRuins.updateIterable();
        for(int i = 0; i < unseenRuins.size; i++) {
            seenRuins.remove(unseenRuins.get(i));
        }
    }

    public static MapLocation opposite(MapLocation locToCheck) {
        return switch (knownSymmetry) {
            case Horizontal -> new MapLocation(locToCheck.x, staticRC.getMapHeight() - 1 - locToCheck.y);
            case Rotational ->
                    new MapLocation(staticRC.getMapWidth() - 1 - locToCheck.x, staticRC.getMapHeight() - 1 - locToCheck.y);
            case Vertical -> new MapLocation(staticRC.getMapWidth() - 1 - locToCheck.x, locToCheck.y);
            case Unknown -> null;
        };
    }

    //returns an array of possible symmetries
    public static SymmetryType[] possibleSymmetry() {
        return switch (symmetries) {
            case 2, 4, 8 -> new SymmetryType[]{knownSymmetry};
            case 6 -> new SymmetryType[]{SymmetryType.Vertical, SymmetryType.Rotational};
            case 10 -> new SymmetryType[]{SymmetryType.Horizontal, SymmetryType.Rotational};
            case 12 -> new SymmetryType[]{SymmetryType.Horizontal, SymmetryType.Vertical};
            default -> new SymmetryType[]{SymmetryType.Horizontal, SymmetryType.Vertical, SymmetryType.Rotational};
        };
    }

}
