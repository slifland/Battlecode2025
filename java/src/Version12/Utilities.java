package Version12;

import battlecode.common.*;

import static Version12.RobotPlayer.*;

public class Utilities
{
    final static int RADIUS_FROM_CENTER = 35;
    /*
        Uses the origin as the beginning of a tiling pattern and returns what color a tile on a specific MapLocation
        should be.
     */
    public static boolean getColorFromOriginPattern(MapLocation location, boolean[][] pattern)
    {
        MapLocation corner = new MapLocation((location.x / 4) * 4,
                (location.y / 4) * 4);
        return pattern[location.y - corner.y][location.x - corner.x];
    }
    //returns the color a square should be in a pattern starting from a custom origin
    public static boolean getColorFromCustomPattern(MapLocation location, boolean[][] pattern, MapLocation center)
    {
        //MapLocation origin = new MapLocation(center.x - 2, center.y - 2);
        return pattern[location.x - (center.x - 2)][location.y - (center.y - 2)];
    }

    //looks at the area around a map location, and infers which tower pattern is matched
    //for now only considers the two patterns we build, money and paint
    public static boolean[][] inferPatternFromExistingSpots(MapLocation center, MapInfo[] ruinTiles) throws GameActionException {
        if(staticRC.getRoundNum() <= Soldier.FORCE_MONEY_ROUND) return staticRC.getTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER);
        else if(staticRC.getNumberTowers() > 8 && center.distanceSquaredTo(new MapLocation(staticRC.getMapWidth()/ 2, staticRC.getMapHeight()/ 2)) <= RADIUS_FROM_CENTER && Soldier.numEnemyTiles > 1) {
            return staticRC.getTowerPattern(UnitType.LEVEL_ONE_DEFENSE_TOWER);
        }
        int moneyScore = 0;
        int paintScore = 0;
        int defenseScore = 0;
        int totalAlly = 0;
        MapLocation fakeOrigin = new MapLocation(center.x - 2, center.y - 2);
        boolean[][] moneyPattern = staticRC.getTowerPattern(UnitType.LEVEL_ONE_MONEY_TOWER);
        boolean [][] defensePattern = staticRC.getTowerPattern(UnitType.LEVEL_ONE_DEFENSE_TOWER);
        boolean[][] paintPattern = staticRC.getTowerPattern(UnitType.LEVEL_ONE_PAINT_TOWER);
        for(MapInfo tile : ruinTiles) {
            PaintType paint = tile.getPaint();
            if(paint.isAlly()) {
                int offsetX = tile.getMapLocation().x - fakeOrigin.x;
                int offsetY = tile.getMapLocation().y - fakeOrigin.y;
                if(paint.isSecondary() == moneyPattern[offsetX][offsetY]) moneyScore++;
                if(paint.isSecondary() == paintPattern[offsetX][offsetY]) paintScore++;
                if(paint.isSecondary() == paintPattern[offsetX][offsetY]) defenseScore++;
                totalAlly++;
            }
        }
        if(staticRC.getMoney() > 3500) paintScore += (staticRC.getMoney() / 3500);
        if(totalAlly == 0) return null;
        if(moneyScore >= paintScore && moneyScore >= defenseScore) return moneyPattern;
        else if (paintScore >= moneyScore && paintScore >= defenseScore) return paintPattern;
        else return defensePattern;
    }

    public static void attemptCompleteResourcePattern(MapLocation location) throws GameActionException
    {
        if((location.x - 2) % 4 == 0 && (location.y - 2) % 4 == 0)
        {
            if(staticRC.canCompleteResourcePattern(staticRC.getLocation()))
            {
                staticRC.completeResourcePattern(staticRC.getLocation());
            }
        }
    }

    /*
        Attempt to complete resource pattern at all visible locations
     */

    public static void attemptCompletePatterns() throws GameActionException
    {
        for(int i = 0; i < nearbyTiles.length; i++)
        {
            if(staticRC.canCompleteResourcePattern(nearbyTiles[i].getMapLocation()))
            {
                staticRC.completeResourcePattern(nearbyTiles[i].getMapLocation());
            }
        }
    }

    public static void updatePaintAverages()
    {
        if(staticRC.getType().isTowerType()) return; // We don't want towers contributing for simplicity
        if(turnCount % 50 == 0)        // Clear frontlines every so often
        {
            paintCount1 = 0;
            paintCount2 = 0;
            paintAverage1 = new MapLocation(0,0);
            paintAverage2 = new MapLocation(0,0);
        }

        int xTotal1 = 0;
        int yTotal1 = 0;
        int count1 = 0;

        int xTotal2 = 0;
        int yTotal2 = 0;
        int count2 = 0;
        for (MapInfo nearbyTile : nearbyTiles)
        {
            if (nearbyTile.getPaint().isEnemy() &&
                    nearbyTile.getMapLocation().isWithinDistanceSquared(paintAverage1, distanceThreshold))
            {
                xTotal1 += nearbyTile.getMapLocation().x;
                yTotal1 += nearbyTile.getMapLocation().y;
                count1++;
            }
            else if(nearbyTile.getPaint().isEnemy())
            {
                xTotal2 += nearbyTile.getMapLocation().x;
                yTotal2 += nearbyTile.getMapLocation().y;
                count2++;
            }
        }

        if(count1 != 0)
        {
            xTotal1 = (xTotal1 + paintAverage1.x * paintCount1) / (count1 + paintCount1);
            yTotal1 = (yTotal1 + paintAverage1.y * paintCount1) / (count1 + paintCount1);
            paintAverage1 = new MapLocation(xTotal1, yTotal1);
            paintCount1 += count1;
        }
        if(count2 != 0)
        {
            xTotal2 = (xTotal2 + paintAverage2.x * paintCount2) / (count2 + paintCount2);
            yTotal2 = (yTotal2 + paintAverage2.y * paintCount2) / (count2 + paintCount2);
            paintAverage2 = new MapLocation(xTotal2, yTotal2);
            paintCount2 += count2;
        }
    }

    public static MapLocation[] getEnemyPaintAverages()
    {
        MapLocation empty = new MapLocation(0,0);
        if(!paintAverage1.equals(empty) && !paintAverage2.equals(empty))
        {
            return new MapLocation[]{paintAverage1, paintAverage2};
        }
        else if(!paintAverage1.equals(empty) && paintAverage2.equals(empty))
        {
            return new MapLocation[]{paintAverage1};
        }
        else if(paintAverage1.equals(empty) && !paintAverage2.equals(empty))
        {
            return new MapLocation[]{paintAverage2};
        }
        else
        {
            return new MapLocation[0];
        }
    }

    public static void setDotsAtGridStart() throws GameActionException
    {
        for(int i = 0; i < staticRC.getMapHeight(); i += 5)
        {
            for(int j = 0; j < staticRC.getMapWidth(); j += 5)
            {
                //staticRC.setIndicatorDot(new MapLocation(j, i), 0,0,255);
            }
        }
    }

    public static MapLocation oppositeLocation(MapLocation location, Symmetry symmetry)
    {
        switch (symmetry)
        {
            case Horizontal ->
            {
                return new MapLocation(location.x, staticRC.getMapHeight() - location.y - 1);
            }
            case Vertical ->
            {
                return new MapLocation(staticRC.getMapWidth() - location.x - 1, location.y);
            }
            case Rotational ->
            {
                return new MapLocation(staticRC.getMapWidth() - location.x - 1, staticRC.getMapHeight() - location.y - 1);
            }
            default ->
            {
                return new MapLocation(0,0);
            }
        }
    }

    //if isRuin, check for a ruin - otherwise check for a wall
    public static void validateSymmetry(MapLocation toCheck, boolean isRuin) {
        if(knownSymmetry != Symmetry.Unknown) return;
        int x = toCheck.x;
        int y = toCheck.y;
        int toCompareHorizontal = map[x][map[0].length - 1 - y];
        int toCompareVertical = map[map.length - 1 - x][y];
        int toCompareRotational = map[map.length - 1 - x][map[0].length - 1 - y];
        int intendedResult = (isRuin) ? 3 : 2;
        int horizontal = 0b1000 & symmetries;
        int vertical = 0b0100 & symmetries;
        int rotational = 0b0010 & symmetries;
        int finalMask = 0b1110;
        if(horizontal != 0 && toCompareHorizontal != intendedResult && toCompareHorizontal != 0) {
            finalMask &= 0b0110;
        }
        if(vertical != 0 && toCompareVertical != intendedResult && toCompareVertical != 0) {
            finalMask &= 0b1010;
        }
        if(rotational != 0 && toCompareRotational != intendedResult && toCompareRotational != 0) {
            finalMask &= 0b1100;
        }
        symmetries &= finalMask;
        switch (symmetries) {
            case 2 -> knownSymmetry = Symmetry.Rotational;
            case 4 -> knownSymmetry = Symmetry.Vertical;
            case 8 -> knownSymmetry = Symmetry.Horizontal;
        }
    }

    //returns an array of possible symmetries
    public static Symmetry[] possibleSymmetry() {
        return switch (symmetries) {
            case 2, 4, 8 -> new Symmetry[]{knownSymmetry};
            case 6 -> new Symmetry[]{Symmetry.Vertical, Symmetry.Rotational};
            case 10 -> new Symmetry[]{Symmetry.Horizontal, Symmetry.Rotational};
            case 12 -> new Symmetry[]{Symmetry.Horizontal, Symmetry.Vertical};
            default -> new Symmetry[]{Symmetry.Horizontal, Symmetry.Vertical, Symmetry.Rotational};
        };
    }

    public static boolean angleIsGreaterThan90(Direction a, Direction b){
        while(a!=Direction.SOUTH){
            a = a.rotateLeft();
            b = b.rotateLeft();
        }
        return b==Direction.NORTH || b==Direction.NORTHEAST || b==Direction.NORTHWEST;

    }
    public static boolean locationIsBehindWall(MapLocation L) throws GameActionException {
        //return locationIsBehindWall(rc,L,staticRC.getLocation());
        return locationIsBehindWall(L, staticRC.getLocation(), 4);
    }
    public static boolean locationIsBehindWall(MapLocation L, MapLocation R){
        double m  = (R.y-L.y+0.0)/(R.x-L.x);
        double c = (m*L.x-L.y);
        for(MapInfo T: nearbyTiles){
            MapLocation t = T.getMapLocation();
            if(t.equals(L)) continue;
            if(!angleIsGreaterThan90(t.directionTo(L),t.directionTo(R))){
                /*If the directions point in the same way that means that t
                is not inbetween L and R. If it was inbetween the the
                angle would be obtuse
                 */
                continue;
            }
            double d1 = Math.pow(t.y-m*t.x+c,2);
            /*
            this comparison is kinda weird but basically it uses the distance from a point
            to a line formula* and compares it to 1. However since division and sqrt are expensive,
            I've done some algebra to get rid of them

            * given aX + bY + C = 0 and (P,Q)
            distance = (aP + bQ + c)/sqrt(A^2+B^2)
             */

            if(T.isWall()&&1+m*m>=d1){
                return true;
            }
        }
        return false;
    }
    public static boolean locationIsBehindWall(MapLocation L, MapLocation R, int radius) throws GameActionException {
        double m  = (R.y-L.y+0.0)/(R.x-L.x);
        double c = (m*L.x-L.y);
        for(MapInfo T: staticRC.senseNearbyMapInfos(radius)){
            MapLocation t = T.getMapLocation();
            if(t.equals(L)) continue;
            if(!angleIsGreaterThan90(t.directionTo(L),t.directionTo(R))){
                /*If the directions point in the same way that means that t
                is not inbetween L and R. If it was inbetween the the
                angle would be obtuse
                 */
                continue;
            }
            double d1 = Math.pow(t.y-m*t.x+c,2);
            /*
            this comparison is kinda weird but basically it uses the distance from a point
            to a line formula* and compares it to 1. However since division and sqrt are expensive,
            I've done some algebra to get rid of them

            * given aX + bY + C = 0 and (P,Q)
            distance = (aP + bQ + c)/sqrt(A^2+B^2)
             */

            if(T.isWall()&&1+m*m>=d1){
                return true;
            }
        }
        return false;
    }

    public static boolean basicLocationIsBehindWall(MapLocation L) throws GameActionException {
        //Direction dir = staticRC.getLocation().directionTo(L);
        Direction dir = MopperMicro.customLocationTo(staticRC.getLocation(), L); //turn 340
        //System.out.println(staticRC.getLocation() + " : " + dir + " : " + L);
        MapLocation check1 = staticRC.getLocation().add(dir);
        MapLocation check2 = staticRC.getLocation().add(dir.rotateLeft());
        MapLocation check3 = staticRC.getLocation().add(dir.rotateRight());
        int numTrue = 0;
        if(staticRC.onTheMap(check1) && !staticRC.senseMapInfo(check1).isPassable()) numTrue++;
        if(staticRC.onTheMap(check2) && !staticRC.senseMapInfo(check2).isPassable()) numTrue++;
        if(staticRC.onTheMap(check3) && !staticRC.senseMapInfo(check3).isPassable()) numTrue++;
        return (dir == Direction.NORTHEAST || dir == Direction.NORTHWEST || dir == Direction.SOUTHEAST || dir == Direction.SOUTHWEST) ? !staticRC.senseMapInfo(check1).isPassable() : numTrue >= 2;
        //return staticRC.senseMapInfo(check1).isWall() && staticRC.senseMapInfo(check2).isWall() && staticRC.senseMapInfo(check3).isWall();
    }

    public static MapLocation generateRandomLocation(MapLocation currentLocation, int radiusSquared)
    {
        int max = (int) Math.sqrt(radiusSquared);
        int xOffset = rng.nextInt(-1 * max, max + 1);
        int yOffset = (int) Math.sqrt(radiusSquared - (xOffset * xOffset));
        yOffset = rng.nextBoolean() ? -1 * yOffset : yOffset;

        int origX = xOffset;
        int origY = yOffset;

        if(currentLocation.x + xOffset < 0)
        {
            xOffset *= -1;
        }
        if(currentLocation.x + xOffset >= staticRC.getMapWidth())
        {
            xOffset *= -1;
        }
        if(currentLocation.y + yOffset < 0)
        {
            yOffset *= -1;
        }
        if(currentLocation.y + yOffset >= staticRC.getMapHeight())
        {
            yOffset *= -1;
        }

//        if(Sector.getSector(rc, new MapLocation(currentLocation.x + xOffset, currentLocation.y + yOffset)) < 0)
//        {
//            System.out.println(currentLocation + " " +  new MapLocation(currentLocation.x + origX, currentLocation.y + origY));
//        }

        return new MapLocation(currentLocation.x + xOffset, currentLocation.y + yOffset);
    }

    public static MapLocation generateRandomLocation(RobotController rc)
    {
        return new MapLocation(rng.nextInt(rc.getMapWidth()), rng.nextInt(rc.getMapHeight()));
    }
}
