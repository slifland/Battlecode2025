package Version12;

import battlecode.common.*;

import static Version12.RobotPlayer.nearbyTiles;

import static Version12.Splasher.nearestUnfilledRuin;
import static Version12.RobotPlayer.staticRC;

public class splasherUtil {
    //static final int 3 = 3;
    public static MapLocation bestAttackBinary() {
        return null;

    }

    //used to see if we can run cheapBestAttack
    public static boolean farFromEdge(MapLocation loc) {
        //if(!loc.equals(staticRC.getLocation()) && !farFromEdge( staticRC.getLocation())) return true;
        int mapHeight = staticRC.getMapHeight() - 1;
        int mapWidth = staticRC.getMapWidth()- 1;
        return loc.x >= 3 && loc.y >= 3 && loc.x <= mapWidth - 3 && loc.y <= mapHeight - 3;
    }
    //used to see if we can run bestAttack
    public static boolean farFromEdgeNonMovement(MapLocation loc) {
        int mapHeight = staticRC.getMapHeight() - 1;
        int mapWidth = staticRC.getMapWidth()- 1;
        return loc.x >= 4 && loc.y >= 4 && loc.x <= mapWidth - 4 && loc.y <= mapHeight - 4;
    }
    //returns a basic location to attack - used when very close to edge
    public static MapLocation basicBestAttack() {
        MapInfo bestTile = null;
        int bestDist = Integer.MAX_VALUE;
        boolean isEnemyTile = false;
        MapLocation curLoc = staticRC.getLocation();
        for(MapInfo tile : nearbyTiles) {
            PaintType paint = tile.getPaint();
            if(paint.isEnemy() && !isEnemyTile) {
                isEnemyTile = true;
                bestTile = tile;
                bestDist = curLoc.distanceSquaredTo(tile.getMapLocation());
            }
            else if(paint.isEnemy() && curLoc.distanceSquaredTo(tile.getMapLocation()) < bestDist) {
                bestTile = tile;
                bestDist = curLoc.distanceSquaredTo(tile.getMapLocation());
            }
            else if(paint == PaintType.EMPTY && curLoc.distanceSquaredTo(tile.getMapLocation()) < bestDist) {
                bestTile = tile;
                bestDist = curLoc.distanceSquaredTo(tile.getMapLocation());
            }
        }
        return (bestTile == null) ? null : bestTile.getMapLocation();
    }

    //returns the best location to attack based on how much impact the attack will have
    //returns null if the best attack has less than or equal impact to the minScore
    public static MapLocation bestAttack(boolean fightingTower, int minScore) throws GameActionException {
        if(!farFromEdgeNonMovement( staticRC.getLocation())) return cheapBestAttack( fightingTower, minScore);
        //keeps track of total potential points, so we can short circuit and save bytecode if possible
        int totalPoints = 0;
        int[] localSquares = new int[81];
        int[] potentialAttackSquares = new int[81];
        //int index = 0;
        int totalTracker = 0;
//        for(int index = 0; index < 69; index++) {
//            while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
//                totalTracker++;
//            }
//            PaintType paint = nearbyTiles[index].getPaint();
//            if(nearbyTiles[index].hasRuin()) {
//                if(fightingTower) {
//                    localSquares[totalTracker] += 2;
//                }
//            }
//            //favor the enemy most, but also like empty squares
//            else if(paint == PaintType.EMPTY && !nearbyTiles[index].isWall()) {
//                localSquares[totalTracker]++;
//                totalPoints++;
//            }
//            else if(paint.isEnemy()){
//                if(nearestUnfilledRuin != null && nearbyTiles[index].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
//                    localSquares[totalTracker]+= 3;
//                    totalPoints+= 3;
//                }
//                else {
//                    localSquares[totalTracker] += 2;
//                    totalPoints += 2;
//                }
//            }
//            totalTracker++;
//        }
        // Unrolled loop for all 69 iterations

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint0 = nearbyTiles[0].getPaint();
        if (nearbyTiles[0].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint0 == PaintType.EMPTY && !nearbyTiles[0].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint0.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[0].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint1 = nearbyTiles[1].getPaint();
        if (nearbyTiles[1].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint1 == PaintType.EMPTY && !nearbyTiles[1].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint1.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[1].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint2 = nearbyTiles[2].getPaint();
        if (nearbyTiles[2].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint2 == PaintType.EMPTY && !nearbyTiles[2].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint2.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[2].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint3 = nearbyTiles[3].getPaint();
        if (nearbyTiles[3].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint3 == PaintType.EMPTY && !nearbyTiles[3].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint3.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[3].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint4 = nearbyTiles[4].getPaint();
        if (nearbyTiles[4].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint4 == PaintType.EMPTY && !nearbyTiles[4].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint4.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[4].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint5 = nearbyTiles[5].getPaint();
        if (nearbyTiles[5].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint5 == PaintType.EMPTY && !nearbyTiles[5].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint5.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[5].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint6 = nearbyTiles[6].getPaint();
        if (nearbyTiles[6].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint6 == PaintType.EMPTY && !nearbyTiles[6].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint6.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[6].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;
        // Unrolling the remaining loop iterations from index 7 to index 68

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint7 = nearbyTiles[7].getPaint();
        if (nearbyTiles[7].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint7 == PaintType.EMPTY && !nearbyTiles[7].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint7.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[7].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint8 = nearbyTiles[8].getPaint();
        if (nearbyTiles[8].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint8 == PaintType.EMPTY && !nearbyTiles[8].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint8.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[8].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint9 = nearbyTiles[9].getPaint();
        if (nearbyTiles[9].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint9 == PaintType.EMPTY && !nearbyTiles[9].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint9.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[9].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint10 = nearbyTiles[10].getPaint();
        if (nearbyTiles[10].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint10 == PaintType.EMPTY && !nearbyTiles[10].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint10.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[10].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint11 = nearbyTiles[11].getPaint();
        if (nearbyTiles[11].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint11 == PaintType.EMPTY && !nearbyTiles[11].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint11.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[11].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint12 = nearbyTiles[12].getPaint();
        if (nearbyTiles[12].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint12 == PaintType.EMPTY && !nearbyTiles[12].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint12.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[12].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint13 = nearbyTiles[13].getPaint();
        if (nearbyTiles[13].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint13 == PaintType.EMPTY && !nearbyTiles[13].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint13.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[13].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;
        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint14 = nearbyTiles[14].getPaint();
        if (nearbyTiles[14].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint14 == PaintType.EMPTY && !nearbyTiles[14].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint14.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[14].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint15 = nearbyTiles[15].getPaint();
        if (nearbyTiles[15].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint15 == PaintType.EMPTY && !nearbyTiles[15].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint15.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[15].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint16 = nearbyTiles[16].getPaint();
        if (nearbyTiles[16].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint16 == PaintType.EMPTY && !nearbyTiles[16].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint16.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[16].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint17 = nearbyTiles[17].getPaint();
        if (nearbyTiles[17].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint17 == PaintType.EMPTY && !nearbyTiles[17].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint17.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[17].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint18 = nearbyTiles[18].getPaint();
        if (nearbyTiles[18].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint18 == PaintType.EMPTY && !nearbyTiles[18].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint18.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[18].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint19 = nearbyTiles[19].getPaint();
        if (nearbyTiles[19].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint19 == PaintType.EMPTY && !nearbyTiles[19].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint19.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[19].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint20 = nearbyTiles[20].getPaint();
        if (nearbyTiles[20].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint20 == PaintType.EMPTY && !nearbyTiles[20].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint20.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[20].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint21 = nearbyTiles[21].getPaint();
        if (nearbyTiles[21].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint21 == PaintType.EMPTY && !nearbyTiles[21].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint21.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[21].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;
        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint22 = nearbyTiles[22].getPaint();
        if (nearbyTiles[22].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint22 == PaintType.EMPTY && !nearbyTiles[22].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint22.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[22].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint23 = nearbyTiles[23].getPaint();
        if (nearbyTiles[23].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint23 == PaintType.EMPTY && !nearbyTiles[23].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint23.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[23].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint24 = nearbyTiles[24].getPaint();
        if (nearbyTiles[24].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint24 == PaintType.EMPTY && !nearbyTiles[24].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint24.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[24].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint25 = nearbyTiles[25].getPaint();
        if (nearbyTiles[25].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint25 == PaintType.EMPTY && !nearbyTiles[25].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint25.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[25].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint26 = nearbyTiles[26].getPaint();
        if (nearbyTiles[26].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint26 == PaintType.EMPTY && !nearbyTiles[26].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint26.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[26].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint27 = nearbyTiles[27].getPaint();
        if (nearbyTiles[27].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint27 == PaintType.EMPTY && !nearbyTiles[27].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint27.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[27].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint28 = nearbyTiles[28].getPaint();
        if (nearbyTiles[28].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint28 == PaintType.EMPTY && !nearbyTiles[28].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint28.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[28].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint29 = nearbyTiles[29].getPaint();
        if (nearbyTiles[29].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint29 == PaintType.EMPTY && !nearbyTiles[29].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint29.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[29].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;
        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint30 = nearbyTiles[30].getPaint();
        if (nearbyTiles[30].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint30 == PaintType.EMPTY && !nearbyTiles[30].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint30.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[30].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint31 = nearbyTiles[31].getPaint();
        if (nearbyTiles[31].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint31 == PaintType.EMPTY && !nearbyTiles[31].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint31.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[31].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint32 = nearbyTiles[32].getPaint();
        if (nearbyTiles[32].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint32 == PaintType.EMPTY && !nearbyTiles[32].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint32.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[32].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint33 = nearbyTiles[33].getPaint();
        if (nearbyTiles[33].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint33 == PaintType.EMPTY && !nearbyTiles[33].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint33.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[33].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint34 = nearbyTiles[34].getPaint();
        if (nearbyTiles[34].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint34 == PaintType.EMPTY && !nearbyTiles[34].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint34.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[34].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint35 = nearbyTiles[35].getPaint();
        if (nearbyTiles[35].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint35 == PaintType.EMPTY && !nearbyTiles[35].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint35.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[35].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint36 = nearbyTiles[36].getPaint();
        if (nearbyTiles[36].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint36 == PaintType.EMPTY && !nearbyTiles[36].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint36.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[36].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint37 = nearbyTiles[37].getPaint();
        if (nearbyTiles[37].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint37 == PaintType.EMPTY && !nearbyTiles[37].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint37.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[37].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;
        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint38 = nearbyTiles[38].getPaint();
        if (nearbyTiles[38].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint38 == PaintType.EMPTY && !nearbyTiles[38].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint38.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[38].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint39 = nearbyTiles[39].getPaint();
        if (nearbyTiles[39].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint39 == PaintType.EMPTY && !nearbyTiles[39].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint39.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[39].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint40 = nearbyTiles[40].getPaint();
        if (nearbyTiles[40].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint40 == PaintType.EMPTY && !nearbyTiles[40].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint40.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[40].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint41 = nearbyTiles[41].getPaint();
        if (nearbyTiles[41].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint41 == PaintType.EMPTY && !nearbyTiles[41].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint41.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[41].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint42 = nearbyTiles[42].getPaint();
        if (nearbyTiles[42].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint42 == PaintType.EMPTY && !nearbyTiles[42].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint42.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[42].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint43 = nearbyTiles[43].getPaint();
        if (nearbyTiles[43].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint43 == PaintType.EMPTY && !nearbyTiles[43].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint43.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[43].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint44 = nearbyTiles[44].getPaint();
        if (nearbyTiles[44].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint44 == PaintType.EMPTY && !nearbyTiles[44].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint44.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[44].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint45 = nearbyTiles[45].getPaint();
        if (nearbyTiles[45].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint45 == PaintType.EMPTY && !nearbyTiles[45].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint45.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[45].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;
        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint46 = nearbyTiles[46].getPaint();
        if (nearbyTiles[46].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint46 == PaintType.EMPTY && !nearbyTiles[46].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint46.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[46].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint47 = nearbyTiles[47].getPaint();
        if (nearbyTiles[47].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint47 == PaintType.EMPTY && !nearbyTiles[47].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint47.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[47].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint48 = nearbyTiles[48].getPaint();
        if (nearbyTiles[48].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint48 == PaintType.EMPTY && !nearbyTiles[48].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint48.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[48].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint49 = nearbyTiles[49].getPaint();
        if (nearbyTiles[49].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint49 == PaintType.EMPTY && !nearbyTiles[49].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint49.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[49].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint50 = nearbyTiles[50].getPaint();
        if (nearbyTiles[50].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint50 == PaintType.EMPTY && !nearbyTiles[50].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint50.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[50].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint51 = nearbyTiles[51].getPaint();
        if (nearbyTiles[51].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint51 == PaintType.EMPTY && !nearbyTiles[51].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint51.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[51].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint52 = nearbyTiles[52].getPaint();
        if (nearbyTiles[52].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint52 == PaintType.EMPTY && !nearbyTiles[52].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint52.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[52].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint53 = nearbyTiles[53].getPaint();
        if (nearbyTiles[53].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint53 == PaintType.EMPTY && !nearbyTiles[53].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint53.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[53].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint54 = nearbyTiles[54].getPaint();
        if (nearbyTiles[54].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint54 == PaintType.EMPTY && !nearbyTiles[54].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint54.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[54].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint55 = nearbyTiles[55].getPaint();
        if (nearbyTiles[55].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint55 == PaintType.EMPTY && !nearbyTiles[55].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint55.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[55].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint56 = nearbyTiles[56].getPaint();
        if (nearbyTiles[56].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint56 == PaintType.EMPTY && !nearbyTiles[56].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint56.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[56].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint57 = nearbyTiles[57].getPaint();
        if (nearbyTiles[57].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint57 == PaintType.EMPTY && !nearbyTiles[57].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint57.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[57].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint58 = nearbyTiles[58].getPaint();
        if (nearbyTiles[58].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint58 == PaintType.EMPTY && !nearbyTiles[58].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint58.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[58].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint59 = nearbyTiles[59].getPaint();
        if (nearbyTiles[59].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint59 == PaintType.EMPTY && !nearbyTiles[59].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint59.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[59].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint60 = nearbyTiles[60].getPaint();
        if (nearbyTiles[60].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint60 == PaintType.EMPTY && !nearbyTiles[60].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint60.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[60].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint61 = nearbyTiles[61].getPaint();
        if (nearbyTiles[61].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint61 == PaintType.EMPTY && !nearbyTiles[61].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint61.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[61].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint62 = nearbyTiles[62].getPaint();
        if (nearbyTiles[62].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint62 == PaintType.EMPTY && !nearbyTiles[62].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint62.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[62].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint63 = nearbyTiles[63].getPaint();
        if (nearbyTiles[63].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint63 == PaintType.EMPTY && !nearbyTiles[63].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint63.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[63].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint64 = nearbyTiles[64].getPaint();
        if (nearbyTiles[64].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint64 == PaintType.EMPTY && !nearbyTiles[64].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint64.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[64].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint65 = nearbyTiles[65].getPaint();
        if (nearbyTiles[65].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint65 == PaintType.EMPTY && !nearbyTiles[65].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint65.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[65].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint66 = nearbyTiles[66].getPaint();
        if (nearbyTiles[66].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint66 == PaintType.EMPTY && !nearbyTiles[66].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint66.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[66].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint67 = nearbyTiles[67].getPaint();
        if (nearbyTiles[67].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint67 == PaintType.EMPTY && !nearbyTiles[67].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint67.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[67].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;

        while (totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
            totalTracker++;
        }

        PaintType paint68 = nearbyTiles[68].getPaint();
        if (nearbyTiles[68].hasRuin()) {
            if (fightingTower) {
                localSquares[totalTracker] += 2;
            }
        }
        else if (paint68 == PaintType.EMPTY && !nearbyTiles[68].isWall()) {
            localSquares[totalTracker]++;
            totalPoints++;
        }
        else if (paint68.isEnemy()) {
            if (nearestUnfilledRuin != null && nearbyTiles[68].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        totalTracker++;
        
        if(totalPoints < minScore) {return null;}
//        for(int i = 20; i <= 24; i++) {
//            for(int j = 10; j >= 8; j--) {
//                potentialAttackSquares[i] += localSquares[i + j];
//                potentialAttackSquares[i] += localSquares[i-j];
//            }
//            for(int j = -1; j <= 1; j++) {
//                potentialAttackSquares[i] += localSquares[i + j];
//            }
//            potentialAttackSquares[i] += (localSquares[i - 18] < 2) ? localSquares[i - 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 18] < 2) ? localSquares[i + 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i - 2] < 2) ? localSquares[i - 2] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 2] < 2) ? localSquares[i + 2] : 0;
//        }
//        for(int i = 29; i <= 33; i++) {
//            for(int j = 10; j >= 8; j--) {
//                potentialAttackSquares[i] += localSquares[i + j];
//                potentialAttackSquares[i] += localSquares[i-j];
//            }
//            for(int j = -1; j <= 1; j++) {
//                potentialAttackSquares[i] += localSquares[i + j];
//            }
//            potentialAttackSquares[i] += (localSquares[i - 18] < 2) ? localSquares[i - 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 18] < 2) ? localSquares[i + 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i - 2] < 2) ? localSquares[i - 2] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 2] < 2) ? localSquares[i + 2] : 0;
//        }
//        for(int i = 38; i <= 42; i++) {
//            for(int j = 10; j >= 8; j--) {
//                potentialAttackSquares[i] += localSquares[i + j];
//                potentialAttackSquares[i] += localSquares[i-j];
//            }
//            for(int j = -1; j <= 1; j++) {
//                potentialAttackSquares[i] += localSquares[i + j];
//            }
//            potentialAttackSquares[i] += (localSquares[i - 18] < 2) ? localSquares[i - 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 18] < 2) ? localSquares[i + 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i - 2] < 2) ? localSquares[i - 2] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 2] < 2) ? localSquares[i + 2] : 0;
//        }
//        for(int i = 47; i <= 51; i++) {
//            for(int j = 10; j >= 8; j--) {
//                potentialAttackSquares[i] += localSquares[i + j];
//                potentialAttackSquares[i] += localSquares[i-j];
//            }
//            for(int j = -1; j <= 1; j++) {
//                potentialAttackSquares[i] += localSquares[i + j];
//            }
//            potentialAttackSquares[i] += (localSquares[i - 18] < 2) ? localSquares[i - 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 18] < 2) ? localSquares[i + 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i - 2] < 2) ? localSquares[i - 2] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 2] < 2) ? localSquares[i + 2] : 0;
//        }
//        for(int i = 56; i <= 60; i++) {
//            for(int j = 10; j >= 8; j--) {
//                potentialAttackSquares[i] += localSquares[i + j];
//                potentialAttackSquares[i] += localSquares[i-j];
//            }
//            for(int j = -1; j <= 1; j++) {
//                potentialAttackSquares[i] += localSquares[i + j];
//            }
//            potentialAttackSquares[i] += (localSquares[i - 18] < 2) ? localSquares[i - 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 18] < 2) ? localSquares[i + 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i - 2] < 2) ? localSquares[i - 2] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 2] < 2) ? localSquares[i + 2] : 0;
//        }
        potentialAttackSquares[20] += localSquares[20 + 10];
        potentialAttackSquares[20] += localSquares[20 - 10];
        potentialAttackSquares[20] += localSquares[20 + 9];
        potentialAttackSquares[20] += localSquares[20 - 9];
        potentialAttackSquares[20] += localSquares[20 + 8];
        potentialAttackSquares[20] += localSquares[20 - 8];
        potentialAttackSquares[20] += localSquares[20 + -1];
        potentialAttackSquares[20] += localSquares[20 ];
        potentialAttackSquares[20] += localSquares[20 + 1];
        potentialAttackSquares[20] += (localSquares[20 - 18] < 2) ? localSquares[20 - 18] : 0;
        potentialAttackSquares[20] += (localSquares[20 + 18] < 2) ? localSquares[20 + 18] : 0;
        potentialAttackSquares[20] += (localSquares[20 - 2] < 2) ? localSquares[20 - 2] : 0;
        potentialAttackSquares[20] += (localSquares[20 + 2] < 2) ? localSquares[20 + 2] : 0;
        potentialAttackSquares[21] += localSquares[21 + 10];
        potentialAttackSquares[21] += localSquares[21 - 10];
        potentialAttackSquares[21] += localSquares[21 + 9];
        potentialAttackSquares[21] += localSquares[21 - 9];
        potentialAttackSquares[21] += localSquares[21 + 8];
        potentialAttackSquares[21] += localSquares[21 - 8];
        potentialAttackSquares[21] += localSquares[21 + -1];
        potentialAttackSquares[21] += localSquares[21 ];
        potentialAttackSquares[21] += localSquares[21 + 1];
        potentialAttackSquares[21] += (localSquares[21 - 18] < 2) ? localSquares[21 - 18] : 0;
        potentialAttackSquares[21] += (localSquares[21 + 18] < 2) ? localSquares[21 + 18] : 0;
        potentialAttackSquares[21] += (localSquares[21 - 2] < 2) ? localSquares[21 - 2] : 0;
        potentialAttackSquares[21] += (localSquares[21 + 2] < 2) ? localSquares[21 + 2] : 0;
        potentialAttackSquares[22] += localSquares[22 + 10];
        potentialAttackSquares[22] += localSquares[22 - 10];
        potentialAttackSquares[22] += localSquares[22 + 9];
        potentialAttackSquares[22] += localSquares[22 - 9];
        potentialAttackSquares[22] += localSquares[22 + 8];
        potentialAttackSquares[22] += localSquares[22 - 8];
        potentialAttackSquares[22] += localSquares[22 + -1];
        potentialAttackSquares[22] += localSquares[22 ];
        potentialAttackSquares[22] += localSquares[22 + 1];
        potentialAttackSquares[22] += (localSquares[22 - 18] < 2) ? localSquares[22 - 18] : 0;
        potentialAttackSquares[22] += (localSquares[22 + 18] < 2) ? localSquares[22 + 18] : 0;
        potentialAttackSquares[22] += (localSquares[22 - 2] < 2) ? localSquares[22 - 2] : 0;
        potentialAttackSquares[22] += (localSquares[22 + 2] < 2) ? localSquares[22 + 2] : 0;
        potentialAttackSquares[23] += localSquares[23 + 10];
        potentialAttackSquares[23] += localSquares[23 - 10];
        potentialAttackSquares[23] += localSquares[23 + 9];
        potentialAttackSquares[23] += localSquares[23 - 9];
        potentialAttackSquares[23] += localSquares[23 + 8];
        potentialAttackSquares[23] += localSquares[23 - 8];
        potentialAttackSquares[23] += localSquares[23 + -1];
        potentialAttackSquares[23] += localSquares[23 ];
        potentialAttackSquares[23] += localSquares[23 + 1];
        potentialAttackSquares[23] += (localSquares[23 - 18] < 2) ? localSquares[23 - 18] : 0;
        potentialAttackSquares[23] += (localSquares[23 + 18] < 2) ? localSquares[23 + 18] : 0;
        potentialAttackSquares[23] += (localSquares[23 - 2] < 2) ? localSquares[23 - 2] : 0;
        potentialAttackSquares[23] += (localSquares[23 + 2] < 2) ? localSquares[23 + 2] : 0;
        potentialAttackSquares[24] += localSquares[24 + 10];
        potentialAttackSquares[24] += localSquares[24 - 10];
        potentialAttackSquares[24] += localSquares[24 + 9];
        potentialAttackSquares[24] += localSquares[24 - 9];
        potentialAttackSquares[24] += localSquares[24 + 8];
        potentialAttackSquares[24] += localSquares[24 - 8];
        potentialAttackSquares[24] += localSquares[24 + -1];
        potentialAttackSquares[24] += localSquares[24 ];
        potentialAttackSquares[24] += localSquares[24 + 1];
        potentialAttackSquares[24] += (localSquares[24 - 18] < 2) ? localSquares[24 - 18] : 0;
        potentialAttackSquares[24] += (localSquares[24 + 18] < 2) ? localSquares[24 + 18] : 0;
        potentialAttackSquares[24] += (localSquares[24 - 2] < 2) ? localSquares[24 - 2] : 0;
        potentialAttackSquares[24] += (localSquares[24 + 2] < 2) ? localSquares[24 + 2] : 0;
        potentialAttackSquares[29] += localSquares[29 + 10];
        potentialAttackSquares[29] += localSquares[29 - 10];
        potentialAttackSquares[29] += localSquares[29 + 9];
        potentialAttackSquares[29] += localSquares[29 - 9];
        potentialAttackSquares[29] += localSquares[29 + 8];
        potentialAttackSquares[29] += localSquares[29 - 8];
        potentialAttackSquares[29] += localSquares[29 + -1];
        potentialAttackSquares[29] += localSquares[29 ];
        potentialAttackSquares[29] += localSquares[29 + 1];
        potentialAttackSquares[29] += (localSquares[29 - 18] < 2) ? localSquares[29 - 18] : 0;
        potentialAttackSquares[29] += (localSquares[29 + 18] < 2) ? localSquares[29 + 18] : 0;
        potentialAttackSquares[29] += (localSquares[29 - 2] < 2) ? localSquares[29 - 2] : 0;
        potentialAttackSquares[29] += (localSquares[29 + 2] < 2) ? localSquares[29 + 2] : 0;
        potentialAttackSquares[30] += localSquares[30 + 10];
        potentialAttackSquares[30] += localSquares[30 - 10];
        potentialAttackSquares[30] += localSquares[30 + 9];
        potentialAttackSquares[30] += localSquares[30 - 9];
        potentialAttackSquares[30] += localSquares[30 + 8];
        potentialAttackSquares[30] += localSquares[30 - 8];
        potentialAttackSquares[30] += localSquares[30 + -1];
        potentialAttackSquares[30] += localSquares[30 ];
        potentialAttackSquares[30] += localSquares[30 + 1];
        potentialAttackSquares[30] += (localSquares[30 - 18] < 2) ? localSquares[30 - 18] : 0;
        potentialAttackSquares[30] += (localSquares[30 + 18] < 2) ? localSquares[30 + 18] : 0;
        potentialAttackSquares[30] += (localSquares[30 - 2] < 2) ? localSquares[30 - 2] : 0;
        potentialAttackSquares[30] += (localSquares[30 + 2] < 2) ? localSquares[30 + 2] : 0;
        potentialAttackSquares[31] += localSquares[31 + 10];
        potentialAttackSquares[31] += localSquares[31 - 10];
        potentialAttackSquares[31] += localSquares[31 + 9];
        potentialAttackSquares[31] += localSquares[31 - 9];
        potentialAttackSquares[31] += localSquares[31 + 8];
        potentialAttackSquares[31] += localSquares[31 - 8];
        potentialAttackSquares[31] += localSquares[31 + -1];
        potentialAttackSquares[31] += localSquares[31 ];
        potentialAttackSquares[31] += localSquares[31 + 1];
        potentialAttackSquares[31] += (localSquares[31 - 18] < 2) ? localSquares[31 - 18] : 0;
        potentialAttackSquares[31] += (localSquares[31 + 18] < 2) ? localSquares[31 + 18] : 0;
        potentialAttackSquares[31] += (localSquares[31 - 2] < 2) ? localSquares[31 - 2] : 0;
        potentialAttackSquares[31] += (localSquares[31 + 2] < 2) ? localSquares[31 + 2] : 0;
        potentialAttackSquares[32] += localSquares[32 + 10];
        potentialAttackSquares[32] += localSquares[32 - 10];
        potentialAttackSquares[32] += localSquares[32 + 9];
        potentialAttackSquares[32] += localSquares[32 - 9];
        potentialAttackSquares[32] += localSquares[32 + 8];
        potentialAttackSquares[32] += localSquares[32 - 8];
        potentialAttackSquares[32] += localSquares[32 + -1];
        potentialAttackSquares[32] += localSquares[32 ];
        potentialAttackSquares[32] += localSquares[32 + 1];
        potentialAttackSquares[32] += (localSquares[32 - 18] < 2) ? localSquares[32 - 18] : 0;
        potentialAttackSquares[32] += (localSquares[32 + 18] < 2) ? localSquares[32 + 18] : 0;
        potentialAttackSquares[32] += (localSquares[32 - 2] < 2) ? localSquares[32 - 2] : 0;
        potentialAttackSquares[32] += (localSquares[32 + 2] < 2) ? localSquares[32 + 2] : 0;
        potentialAttackSquares[33] += localSquares[33 + 10];
        potentialAttackSquares[33] += localSquares[33 - 10];
        potentialAttackSquares[33] += localSquares[33 + 9];
        potentialAttackSquares[33] += localSquares[33 - 9];
        potentialAttackSquares[33] += localSquares[33 + 8];
        potentialAttackSquares[33] += localSquares[33 - 8];
        potentialAttackSquares[33] += localSquares[33 + -1];
        potentialAttackSquares[33] += localSquares[33 ];
        potentialAttackSquares[33] += localSquares[33 + 1];
        potentialAttackSquares[33] += (localSquares[33 - 18] < 2) ? localSquares[33 - 18] : 0;
        potentialAttackSquares[33] += (localSquares[33 + 18] < 2) ? localSquares[33 + 18] : 0;
        potentialAttackSquares[33] += (localSquares[33 - 2] < 2) ? localSquares[33 - 2] : 0;
        potentialAttackSquares[33] += (localSquares[33 + 2] < 2) ? localSquares[33 + 2] : 0;
        potentialAttackSquares[38] += localSquares[38 + 10];
        potentialAttackSquares[38] += localSquares[38 - 10];
        potentialAttackSquares[38] += localSquares[38 + 9];
        potentialAttackSquares[38] += localSquares[38 - 9];
        potentialAttackSquares[38] += localSquares[38 + 8];
        potentialAttackSquares[38] += localSquares[38 - 8];
        potentialAttackSquares[38] += localSquares[38 + -1];
        potentialAttackSquares[38] += localSquares[38 ];
        potentialAttackSquares[38] += localSquares[38 + 1];
        potentialAttackSquares[38] += (localSquares[38 - 18] < 2) ? localSquares[38 - 18] : 0;
        potentialAttackSquares[38] += (localSquares[38 + 18] < 2) ? localSquares[38 + 18] : 0;
        potentialAttackSquares[38] += (localSquares[38 - 2] < 2) ? localSquares[38 - 2] : 0;
        potentialAttackSquares[38] += (localSquares[38 + 2] < 2) ? localSquares[38 + 2] : 0;
        potentialAttackSquares[39] += localSquares[39 + 10];
        potentialAttackSquares[39] += localSquares[39 - 10];
        potentialAttackSquares[39] += localSquares[39 + 9];
        potentialAttackSquares[39] += localSquares[39 - 9];
        potentialAttackSquares[39] += localSquares[39 + 8];
        potentialAttackSquares[39] += localSquares[39 - 8];
        potentialAttackSquares[39] += localSquares[39 + -1];
        potentialAttackSquares[39] += localSquares[39 ];
        potentialAttackSquares[39] += localSquares[39 + 1];
        potentialAttackSquares[39] += (localSquares[39 - 18] < 2) ? localSquares[39 - 18] : 0;
        potentialAttackSquares[39] += (localSquares[39 + 18] < 2) ? localSquares[39 + 18] : 0;
        potentialAttackSquares[39] += (localSquares[39 - 2] < 2) ? localSquares[39 - 2] : 0;
        potentialAttackSquares[39] += (localSquares[39 + 2] < 2) ? localSquares[39 + 2] : 0;
        potentialAttackSquares[40] += localSquares[40 + 10];
        potentialAttackSquares[40] += localSquares[40 - 10];
        potentialAttackSquares[40] += localSquares[40 + 9];
        potentialAttackSquares[40] += localSquares[40 - 9];
        potentialAttackSquares[40] += localSquares[40 + 8];
        potentialAttackSquares[40] += localSquares[40 - 8];
        potentialAttackSquares[40] += localSquares[40 + -1];
        potentialAttackSquares[40] += localSquares[40 ];
        potentialAttackSquares[40] += localSquares[40 + 1];
        potentialAttackSquares[40] += (localSquares[40 - 18] < 2) ? localSquares[40 - 18] : 0;
        potentialAttackSquares[40] += (localSquares[40 + 18] < 2) ? localSquares[40 + 18] : 0;
        potentialAttackSquares[40] += (localSquares[40 - 2] < 2) ? localSquares[40 - 2] : 0;
        potentialAttackSquares[40] += (localSquares[40 + 2] < 2) ? localSquares[40 + 2] : 0;
        potentialAttackSquares[41] += localSquares[41 + 10];
        potentialAttackSquares[41] += localSquares[41 - 10];
        potentialAttackSquares[41] += localSquares[41 + 9];
        potentialAttackSquares[41] += localSquares[41 - 9];
        potentialAttackSquares[41] += localSquares[41 + 8];
        potentialAttackSquares[41] += localSquares[41 - 8];
        potentialAttackSquares[41] += localSquares[41 + -1];
        potentialAttackSquares[41] += localSquares[41 ];
        potentialAttackSquares[41] += localSquares[41 + 1];
        potentialAttackSquares[41] += (localSquares[41 - 18] < 2) ? localSquares[41 - 18] : 0;
        potentialAttackSquares[41] += (localSquares[41 + 18] < 2) ? localSquares[41 + 18] : 0;
        potentialAttackSquares[41] += (localSquares[41 - 2] < 2) ? localSquares[41 - 2] : 0;
        potentialAttackSquares[41] += (localSquares[41 + 2] < 2) ? localSquares[41 + 2] : 0;
        potentialAttackSquares[42] += localSquares[42 + 10];
        potentialAttackSquares[42] += localSquares[42 - 10];
        potentialAttackSquares[42] += localSquares[42 + 9];
        potentialAttackSquares[42] += localSquares[42 - 9];
        potentialAttackSquares[42] += localSquares[42 + 8];
        potentialAttackSquares[42] += localSquares[42 - 8];
        potentialAttackSquares[42] += localSquares[42 + -1];
        potentialAttackSquares[42] += localSquares[42 ];
        potentialAttackSquares[42] += localSquares[42 + 1];
        potentialAttackSquares[42] += (localSquares[42 - 18] < 2) ? localSquares[42 - 18] : 0;
        potentialAttackSquares[42] += (localSquares[42 + 18] < 2) ? localSquares[42 + 18] : 0;
        potentialAttackSquares[42] += (localSquares[42 - 2] < 2) ? localSquares[42 - 2] : 0;
        potentialAttackSquares[42] += (localSquares[42 + 2] < 2) ? localSquares[42 + 2] : 0;
        potentialAttackSquares[47] += localSquares[47 + 10];
        potentialAttackSquares[47] += localSquares[47 - 10];
        potentialAttackSquares[47] += localSquares[47 + 9];
        potentialAttackSquares[47] += localSquares[47 - 9];
        potentialAttackSquares[47] += localSquares[47 + 8];
        potentialAttackSquares[47] += localSquares[47 - 8];
        potentialAttackSquares[47] += localSquares[47 + -1];
        potentialAttackSquares[47] += localSquares[47 ];
        potentialAttackSquares[47] += localSquares[47 + 1];
        potentialAttackSquares[47] += (localSquares[47 - 18] < 2) ? localSquares[47 - 18] : 0;
        potentialAttackSquares[47] += (localSquares[47 + 18] < 2) ? localSquares[47 + 18] : 0;
        potentialAttackSquares[47] += (localSquares[47 - 2] < 2) ? localSquares[47 - 2] : 0;
        potentialAttackSquares[47] += (localSquares[47 + 2] < 2) ? localSquares[47 + 2] : 0;
        potentialAttackSquares[48] += localSquares[48 + 10];
        potentialAttackSquares[48] += localSquares[48 - 10];
        potentialAttackSquares[48] += localSquares[48 + 9];
        potentialAttackSquares[48] += localSquares[48 - 9];
        potentialAttackSquares[48] += localSquares[48 + 8];
        potentialAttackSquares[48] += localSquares[48 - 8];
        potentialAttackSquares[48] += localSquares[48 + -1];
        potentialAttackSquares[48] += localSquares[48 ];
        potentialAttackSquares[48] += localSquares[48 + 1];
        potentialAttackSquares[48] += (localSquares[48 - 18] < 2) ? localSquares[48 - 18] : 0;
        potentialAttackSquares[48] += (localSquares[48 + 18] < 2) ? localSquares[48 + 18] : 0;
        potentialAttackSquares[48] += (localSquares[48 - 2] < 2) ? localSquares[48 - 2] : 0;
        potentialAttackSquares[48] += (localSquares[48 + 2] < 2) ? localSquares[48 + 2] : 0;
        potentialAttackSquares[49] += localSquares[49 + 10];
        potentialAttackSquares[49] += localSquares[49 - 10];
        potentialAttackSquares[49] += localSquares[49 + 9];
        potentialAttackSquares[49] += localSquares[49 - 9];
        potentialAttackSquares[49] += localSquares[49 + 8];
        potentialAttackSquares[49] += localSquares[49 - 8];
        potentialAttackSquares[49] += localSquares[49 + -1];
        potentialAttackSquares[49] += localSquares[49 ];
        potentialAttackSquares[49] += localSquares[49 + 1];
        potentialAttackSquares[49] += (localSquares[49 - 18] < 2) ? localSquares[49 - 18] : 0;
        potentialAttackSquares[49] += (localSquares[49 + 18] < 2) ? localSquares[49 + 18] : 0;
        potentialAttackSquares[49] += (localSquares[49 - 2] < 2) ? localSquares[49 - 2] : 0;
        potentialAttackSquares[49] += (localSquares[49 + 2] < 2) ? localSquares[49 + 2] : 0;
        potentialAttackSquares[50] += localSquares[50 + 10];
        potentialAttackSquares[50] += localSquares[50 - 10];
        potentialAttackSquares[50] += localSquares[50 + 9];
        potentialAttackSquares[50] += localSquares[50 - 9];
        potentialAttackSquares[50] += localSquares[50 + 8];
        potentialAttackSquares[50] += localSquares[50 - 8];
        potentialAttackSquares[50] += localSquares[50 + -1];
        potentialAttackSquares[50] += localSquares[50 ];
        potentialAttackSquares[50] += localSquares[50 + 1];
        potentialAttackSquares[50] += (localSquares[50 - 18] < 2) ? localSquares[50 - 18] : 0;
        potentialAttackSquares[50] += (localSquares[50 + 18] < 2) ? localSquares[50 + 18] : 0;
        potentialAttackSquares[50] += (localSquares[50 - 2] < 2) ? localSquares[50 - 2] : 0;
        potentialAttackSquares[50] += (localSquares[50 + 2] < 2) ? localSquares[50 + 2] : 0;
        potentialAttackSquares[51] += localSquares[51 + 10];
        potentialAttackSquares[51] += localSquares[51 - 10];
        potentialAttackSquares[51] += localSquares[51 + 9];
        potentialAttackSquares[51] += localSquares[51 - 9];
        potentialAttackSquares[51] += localSquares[51 + 8];
        potentialAttackSquares[51] += localSquares[51 - 8];
        potentialAttackSquares[51] += localSquares[51 + -1];
        potentialAttackSquares[51] += localSquares[51 ];
        potentialAttackSquares[51] += localSquares[51 + 1];
        potentialAttackSquares[51] += (localSquares[51 - 18] < 2) ? localSquares[51 - 18] : 0;
        potentialAttackSquares[51] += (localSquares[51 + 18] < 2) ? localSquares[51 + 18] : 0;
        potentialAttackSquares[51] += (localSquares[51 - 2] < 2) ? localSquares[51 - 2] : 0;
        potentialAttackSquares[51] += (localSquares[51 + 2] < 2) ? localSquares[51 + 2] : 0;
        potentialAttackSquares[56] += localSquares[56 + 10];
        potentialAttackSquares[56] += localSquares[56 - 10];
        potentialAttackSquares[56] += localSquares[56 + 9];
        potentialAttackSquares[56] += localSquares[56 - 9];
        potentialAttackSquares[56] += localSquares[56 + 8];
        potentialAttackSquares[56] += localSquares[56 - 8];
        potentialAttackSquares[56] += localSquares[56 + -1];
        potentialAttackSquares[56] += localSquares[56 ];
        potentialAttackSquares[56] += localSquares[56 + 1];
        potentialAttackSquares[56] += (localSquares[56 - 18] < 2) ? localSquares[56 - 18] : 0;
        potentialAttackSquares[56] += (localSquares[56 + 18] < 2) ? localSquares[56 + 18] : 0;
        potentialAttackSquares[56] += (localSquares[56 - 2] < 2) ? localSquares[56 - 2] : 0;
        potentialAttackSquares[56] += (localSquares[56 + 2] < 2) ? localSquares[56 + 2] : 0;
        potentialAttackSquares[57] += localSquares[57 + 10];
        potentialAttackSquares[57] += localSquares[57 - 10];
        potentialAttackSquares[57] += localSquares[57 + 9];
        potentialAttackSquares[57] += localSquares[57 - 9];
        potentialAttackSquares[57] += localSquares[57 + 8];
        potentialAttackSquares[57] += localSquares[57 - 8];
        potentialAttackSquares[57] += localSquares[57 + -1];
        potentialAttackSquares[57] += localSquares[57 ];
        potentialAttackSquares[57] += localSquares[57 + 1];
        potentialAttackSquares[57] += (localSquares[57 - 18] < 2) ? localSquares[57 - 18] : 0;
        potentialAttackSquares[57] += (localSquares[57 + 18] < 2) ? localSquares[57 + 18] : 0;
        potentialAttackSquares[57] += (localSquares[57 - 2] < 2) ? localSquares[57 - 2] : 0;
        potentialAttackSquares[57] += (localSquares[57 + 2] < 2) ? localSquares[57 + 2] : 0;
        potentialAttackSquares[58] += localSquares[58 + 10];
        potentialAttackSquares[58] += localSquares[58 - 10];
        potentialAttackSquares[58] += localSquares[58 + 9];
        potentialAttackSquares[58] += localSquares[58 - 9];
        potentialAttackSquares[58] += localSquares[58 + 8];
        potentialAttackSquares[58] += localSquares[58 - 8];
        potentialAttackSquares[58] += localSquares[58 + -1];
        potentialAttackSquares[58] += localSquares[58 ];
        potentialAttackSquares[58] += localSquares[58 + 1];
        potentialAttackSquares[58] += (localSquares[58 - 18] < 2) ? localSquares[58 - 18] : 0;
        potentialAttackSquares[58] += (localSquares[58 + 18] < 2) ? localSquares[58 + 18] : 0;
        potentialAttackSquares[58] += (localSquares[58 - 2] < 2) ? localSquares[58 - 2] : 0;
        potentialAttackSquares[58] += (localSquares[58 + 2] < 2) ? localSquares[58 + 2] : 0;
        potentialAttackSquares[59] += localSquares[59 + 10];
        potentialAttackSquares[59] += localSquares[59 - 10];
        potentialAttackSquares[59] += localSquares[59 + 9];
        potentialAttackSquares[59] += localSquares[59 - 9];
        potentialAttackSquares[59] += localSquares[59 + 8];
        potentialAttackSquares[59] += localSquares[59 - 8];
        potentialAttackSquares[59] += localSquares[59 + -1];
        potentialAttackSquares[59] += localSquares[59 ];
        potentialAttackSquares[59] += localSquares[59 + 1];
        potentialAttackSquares[59] += (localSquares[59 - 18] < 2) ? localSquares[59 - 18] : 0;
        potentialAttackSquares[59] += (localSquares[59 + 18] < 2) ? localSquares[59 + 18] : 0;
        potentialAttackSquares[59] += (localSquares[59 - 2] < 2) ? localSquares[59 - 2] : 0;
        potentialAttackSquares[59] += (localSquares[59 + 2] < 2) ? localSquares[59 + 2] : 0;
        potentialAttackSquares[60] += localSquares[60 + 10];
        potentialAttackSquares[60] += localSquares[60 - 10];
        potentialAttackSquares[60] += localSquares[60 + 9];
        potentialAttackSquares[60] += localSquares[60 - 9];
        potentialAttackSquares[60] += localSquares[60 + 8];
        potentialAttackSquares[60] += localSquares[60 - 8];
        potentialAttackSquares[60] += localSquares[60 + -1];
        potentialAttackSquares[60] += localSquares[60 ];
        potentialAttackSquares[60] += localSquares[60 + 1];
        potentialAttackSquares[60] += (localSquares[60 - 18] < 2) ? localSquares[60 - 18] : 0;
        potentialAttackSquares[60] += (localSquares[60 + 18] < 2) ? localSquares[60 + 18] : 0;
        potentialAttackSquares[60] += (localSquares[60 - 2] < 2) ? localSquares[60 - 2] : 0;
        potentialAttackSquares[60] += (localSquares[60 + 2] < 2) ? localSquares[60 + 2] : 0;
        int highest = potentialAttackSquares[20];
        int highestIndex = 20;
//        for(int i = 21; i <= 60; i++) {
//            if(potentialAttackSquares[i] > highest) {
//                highestIndex = i;
//                highest = potentialAttackSquares[i];
//            }
//        }
        if(potentialAttackSquares[21] > highest) {
            highestIndex = 21;
            highest = potentialAttackSquares[21];
        }
        if(potentialAttackSquares[22] > highest) {
            highestIndex = 22;
            highest = potentialAttackSquares[22];
        }
        if(potentialAttackSquares[23] > highest) {
            highestIndex = 23;
            highest = potentialAttackSquares[23];
        }
        if(potentialAttackSquares[24] > highest) {
            highestIndex = 24;
            highest = potentialAttackSquares[24];
        }
        if(potentialAttackSquares[25] > highest) {
            highestIndex = 25;
            highest = potentialAttackSquares[25];
        }
        if(potentialAttackSquares[26] > highest) {
            highestIndex = 26;
            highest = potentialAttackSquares[26];
        }
        if(potentialAttackSquares[27] > highest) {
            highestIndex = 27;
            highest = potentialAttackSquares[27];
        }
        if(potentialAttackSquares[28] > highest) {
            highestIndex = 28;
            highest = potentialAttackSquares[28];
        }
        if(potentialAttackSquares[29] > highest) {
            highestIndex = 29;
            highest = potentialAttackSquares[29];
        }
        if(potentialAttackSquares[30] > highest) {
            highestIndex = 30;
            highest = potentialAttackSquares[30];
        }
        if(potentialAttackSquares[31] > highest) {
            highestIndex = 31;
            highest = potentialAttackSquares[31];
        }
        if(potentialAttackSquares[32] > highest) {
            highestIndex = 32;
            highest = potentialAttackSquares[32];
        }
        if(potentialAttackSquares[33] > highest) {
            highestIndex = 33;
            highest = potentialAttackSquares[33];
        }
        if(potentialAttackSquares[34] > highest) {
            highestIndex = 34;
            highest = potentialAttackSquares[34];
        }
        if(potentialAttackSquares[35] > highest) {
            highestIndex = 35;
            highest = potentialAttackSquares[35];
        }
        if(potentialAttackSquares[36] > highest) {
            highestIndex = 36;
            highest = potentialAttackSquares[36];
        }
        if(potentialAttackSquares[37] > highest) {
            highestIndex = 37;
            highest = potentialAttackSquares[37];
        }
        if(potentialAttackSquares[38] > highest) {
            highestIndex = 38;
            highest = potentialAttackSquares[38];
        }
        if(potentialAttackSquares[39] > highest) {
            highestIndex = 39;
            highest = potentialAttackSquares[39];
        }
        if(potentialAttackSquares[40] > highest) {
            highestIndex = 40;
            highest = potentialAttackSquares[40];
        }
        if(potentialAttackSquares[41] > highest) {
            highestIndex = 41;
            highest = potentialAttackSquares[41];
        }
        if(potentialAttackSquares[42] > highest) {
            highestIndex = 42;
            highest = potentialAttackSquares[42];
        }
        if(potentialAttackSquares[43] > highest) {
            highestIndex = 43;
            highest = potentialAttackSquares[43];
        }
        if(potentialAttackSquares[44] > highest) {
            highestIndex = 44;
            highest = potentialAttackSquares[44];
        }

        if(potentialAttackSquares[45] > highest) {
            highestIndex = 45;
            highest = potentialAttackSquares[45];
        }
        if(potentialAttackSquares[46] > highest) {
            highestIndex = 46;
            highest = potentialAttackSquares[46];
        }
        if(potentialAttackSquares[47] > highest) {
            highestIndex = 47;
            highest = potentialAttackSquares[47];
        }
        if(potentialAttackSquares[48] > highest) {
            highestIndex = 48;
            highest = potentialAttackSquares[48];
        }
        if(potentialAttackSquares[49] > highest) {
            highestIndex = 49;
            highest = potentialAttackSquares[49];
        }
        if(potentialAttackSquares[50] > highest) {
            highestIndex = 50;
            highest = potentialAttackSquares[50];
        }
        if(potentialAttackSquares[51] > highest) {
            highestIndex = 51;
            highest = potentialAttackSquares[51];
        }
        if(potentialAttackSquares[52] > highest) {
            highestIndex = 52;
            highest = potentialAttackSquares[52];
        }
        if(potentialAttackSquares[53] > highest) {
            highestIndex = 53;
            highest = potentialAttackSquares[53];
        }
        if(potentialAttackSquares[54] > highest) {
            highestIndex = 54;
            highest = potentialAttackSquares[54];
        }
        if(potentialAttackSquares[55] > highest) {
            highestIndex = 55;
            highest = potentialAttackSquares[55];
        }
        if(potentialAttackSquares[56] > highest) {
            highestIndex = 56;
            highest = potentialAttackSquares[56];
        }
        if(potentialAttackSquares[57] > highest) {
            highestIndex = 57;
            highest = potentialAttackSquares[57];
        }
        if(potentialAttackSquares[58] > highest) {
            highestIndex = 58;
            highest = potentialAttackSquares[58];
        }
        if(potentialAttackSquares[59] > highest) {
            highestIndex = 59;
            highest = potentialAttackSquares[59];
        }
        if(potentialAttackSquares[60] > highest) {
            highestIndex = 60;
            highest = potentialAttackSquares[60];
        }

        if(highest < minScore) return null;
        int offSetX, offSetY;
        if(highestIndex <= 24) offSetX = -2;
        else if(highestIndex <= 33) offSetX = -1;
        else if(highestIndex <= 42) offSetX = 0;
        else if(highestIndex <= 51) offSetX = 1;
        else offSetX = 2;
        offSetY = (highestIndex % 9) - 4;
        //System.out.println("Price: " + (price - Clock.getBytecodesLeft()));
        return new MapLocation(staticRC.getLocation().x + offSetX, staticRC.getLocation().y + offSetY);
    }

    //returns the best attack adjacent to the robot - cheaper, and allows the robot to go closer to the edge
    public static MapLocation cheapBestAttack(boolean fightingTower, int minScore) throws GameActionException {
        if(!farFromEdge( staticRC.getLocation())) return basicBestAttack();
        int price = Clock.getBytecodesLeft();
        //keeps track of total potential points, so we can short circuit and save bytecode if possible
        int totalPoints = 0;
        int[] localSquares = new int[45];
        int[] potentialAttackSquares = new int[45];
        int index = 0;
        for(MapInfo tile : staticRC.senseNearbyMapInfos(13)) {
            if(index == 0 || index == 4 || index == 5 || index == 11 || index == 33 || index == 39 || index == 40 || index == 44) {
                index++;
                continue;
            }
            PaintType paint = tile.getPaint();
            if(tile.hasRuin()) {
                if(fightingTower && staticRC.senseRobotAtLocation(tile.getMapLocation()) != null && staticRC.senseRobotAtLocation(tile.getMapLocation()) != null && staticRC.senseRobotAtLocation(tile.getMapLocation()).team == staticRC.getTeam().opponent()) {
                    paint = PaintType.ENEMY_PRIMARY;
                }
                else {
                    paint = PaintType.ALLY_PRIMARY;
                }
            }
            else if(tile.isWall()) {
                paint = PaintType.ALLY_PRIMARY;
            }
            //favor the enemy most, but also like empty squares
            if(paint == PaintType.EMPTY) {
                localSquares[index]++;
                totalPoints++;
            }
            else if(paint.isAlly()){
                localSquares[index] = 0;
            }
            else {
                if(nearestUnfilledRuin != null && tile.getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                    localSquares[index] += 3;
                    totalPoints+= 3;
                }
                else {
                    localSquares[index] += 2;
                    totalPoints += 2;
                }
            }
            index++;
        }
        if(totalPoints < minScore) {return null;}
//        for(int i = 14; i <= 16; i++) {
//            for(int j = 8; j >= 6; j--) {
//                potentialAttackSquares[i] += localSquares[i + j];
//                potentialAttackSquares[i] += localSquares[i - j];
//            }
//            for(int j = -1; j <= 1; j++) {
//                potentialAttackSquares[i] += localSquares[i + j];
//            }
//            potentialAttackSquares[i] += (localSquares[i - 13] <2) ? localSquares[i - 13] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 14] <2) ? localSquares[i + 14] : 0;
//            potentialAttackSquares[i] += (localSquares[i - 2] <2) ? localSquares[i - 2] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 2] <2) ? localSquares[i + 2] : 0;
//
//        }
//        for(int i = 21; i <= 23; i++) {
//            for(int j = 8; j >= 6; j--) {
//                potentialAttackSquares[i] += localSquares[i + j];
//                potentialAttackSquares[i] += localSquares[i - j];
//            }
//            for(int j = -1; j <= 1; j++) {
//                potentialAttackSquares[i] += localSquares[i + j];
//            }
//            potentialAttackSquares[i] += (localSquares[i - 14] <2) ? localSquares[i - 14] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 14] <2) ? localSquares[i + 14] : 0;
//            potentialAttackSquares[i] += (localSquares[i - 2] <2) ? localSquares[i - 2] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 2] <2) ? localSquares[i + 2] : 0;
//        }
//        for(int i = 28; i <= 30; i++) {
//            for(int j = 8; j >= 6; j--) {
//                potentialAttackSquares[i] += localSquares[i + j];
//                potentialAttackSquares[i] += localSquares[i - j];
//            }
//            for(int j = -1; j <= 1; j++) {
//                potentialAttackSquares[i] += localSquares[i + j];
//            }
//            potentialAttackSquares[i] += (localSquares[i - 14] <2) ? localSquares[i - 14] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 13] <2) ? localSquares[i + 13] : 0;
//            potentialAttackSquares[i] += (localSquares[i - 2] <2) ? localSquares[i - 2] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 2] <2) ? localSquares[i + 2] : 0;
//        }
        potentialAttackSquares[14] += localSquares[14 + 8];
        potentialAttackSquares[14] += localSquares[14 - 8];
        potentialAttackSquares[14] += localSquares[14 + 7];
        potentialAttackSquares[14] += localSquares[14 - 7];
        potentialAttackSquares[14] += localSquares[14 + 6];
        potentialAttackSquares[14] += localSquares[14 - 6];
        potentialAttackSquares[14] += localSquares[14 + -1];
        potentialAttackSquares[14] += localSquares[14 ];
        potentialAttackSquares[14] += localSquares[14 + 1];
        potentialAttackSquares[14] += (localSquares[14 - 13] < 2) ? localSquares[14 - 13] : 0;
        potentialAttackSquares[14] += (localSquares[14 + 14] < 2) ? localSquares[14 + 14] : 0;
        potentialAttackSquares[14] += (localSquares[14 - 2] < 2) ? localSquares[14 - 2] : 0;
        potentialAttackSquares[14] += (localSquares[14 + 2] < 2) ? localSquares[14 + 2] : 0;

        potentialAttackSquares[15] += localSquares[15 + 8];
        potentialAttackSquares[15] += localSquares[15 - 8];
        potentialAttackSquares[15] += localSquares[15 + 7];
        potentialAttackSquares[15] += localSquares[15 - 7];
        potentialAttackSquares[15] += localSquares[15 + 6];
        potentialAttackSquares[15] += localSquares[15 - 6];
        potentialAttackSquares[15] += localSquares[15 + -1];
        potentialAttackSquares[15] += localSquares[15 ];
        potentialAttackSquares[15] += localSquares[15 + 1];
        potentialAttackSquares[15] += (localSquares[15 - 13] < 2) ? localSquares[15 - 13] : 0;
        potentialAttackSquares[15] += (localSquares[15 + 14] < 2) ? localSquares[15 + 14] : 0;
        potentialAttackSquares[15] += (localSquares[15 - 2] < 2) ? localSquares[15 - 2] : 0;
        potentialAttackSquares[15] += (localSquares[15 + 2] < 2) ? localSquares[15 + 2] : 0;

        potentialAttackSquares[16] += localSquares[16 + 8];
        potentialAttackSquares[16] += localSquares[16 - 8];
        potentialAttackSquares[16] += localSquares[16 + 7];
        potentialAttackSquares[16] += localSquares[16 - 7];
        potentialAttackSquares[16] += localSquares[16 + 6];
        potentialAttackSquares[16] += localSquares[16 - 6];
        potentialAttackSquares[16] += localSquares[16 + -1];
        potentialAttackSquares[16] += localSquares[16 ];
        potentialAttackSquares[16] += localSquares[16 + 1];
        potentialAttackSquares[16] += (localSquares[16 - 13] < 2) ? localSquares[16 - 13] : 0;
        potentialAttackSquares[16] += (localSquares[16 + 14] < 2) ? localSquares[16 + 14] : 0;
        potentialAttackSquares[16] += (localSquares[16 - 2] < 2) ? localSquares[16 - 2] : 0;
        potentialAttackSquares[16] += (localSquares[16 + 2] < 2) ? localSquares[16 + 2] : 0;

        potentialAttackSquares[21] += localSquares[21 + 8];
        potentialAttackSquares[21] += localSquares[21 - 8];
        potentialAttackSquares[21] += localSquares[21 + 7];
        potentialAttackSquares[21] += localSquares[21 - 7];
        potentialAttackSquares[21] += localSquares[21 + 6];
        potentialAttackSquares[21] += localSquares[21 - 6];
        potentialAttackSquares[21] += localSquares[21 + -1];
        potentialAttackSquares[21] += localSquares[21 ];
        potentialAttackSquares[21] += localSquares[21 + 1];
        potentialAttackSquares[21] += (localSquares[21 - 14] < 2) ? localSquares[21 - 14] : 0;
        potentialAttackSquares[21] += (localSquares[21 + 14] < 2) ? localSquares[21 + 14] : 0;
        potentialAttackSquares[21] += (localSquares[21 - 2] < 2) ? localSquares[21 - 2] : 0;
        potentialAttackSquares[21] += (localSquares[21 + 2] < 2) ? localSquares[21 + 2] : 0;
        potentialAttackSquares[22] += localSquares[22 + 8];
        potentialAttackSquares[22] += localSquares[22 - 8];
        potentialAttackSquares[22] += localSquares[22 + 7];
        potentialAttackSquares[22] += localSquares[22 - 7];
        potentialAttackSquares[22] += localSquares[22 + 6];
        potentialAttackSquares[22] += localSquares[22 - 6];
        potentialAttackSquares[22] += localSquares[22 + -1];
        potentialAttackSquares[22] += localSquares[22 ];
        potentialAttackSquares[22] += localSquares[22 + 1];
        potentialAttackSquares[22] += (localSquares[22 - 14] < 2) ? localSquares[22 - 14] : 0;
        potentialAttackSquares[22] += (localSquares[22 + 14] < 2) ? localSquares[22 + 14] : 0;
        potentialAttackSquares[22] += (localSquares[22 - 2] < 2) ? localSquares[22 - 2] : 0;
        potentialAttackSquares[22] += (localSquares[22 + 2] < 2) ? localSquares[22 + 2] : 0;
        potentialAttackSquares[23] += localSquares[23 + 8];
        potentialAttackSquares[23] += localSquares[23 - 8];
        potentialAttackSquares[23] += localSquares[23 + 7];
        potentialAttackSquares[23] += localSquares[23 - 7];
        potentialAttackSquares[23] += localSquares[23 + 6];
        potentialAttackSquares[23] += localSquares[23 - 6];
        potentialAttackSquares[23] += localSquares[23 + -1];
        potentialAttackSquares[23] += localSquares[23 ];
        potentialAttackSquares[23] += localSquares[23 + 1];
        potentialAttackSquares[23] += (localSquares[23 - 14] < 2) ? localSquares[23 - 14] : 0;
        potentialAttackSquares[23] += (localSquares[23 + 14] < 2) ? localSquares[23 + 14] : 0;
        potentialAttackSquares[23] += (localSquares[23 - 2] < 2) ? localSquares[23 - 2] : 0;
        potentialAttackSquares[23] += (localSquares[23 + 2] < 2) ? localSquares[23 + 2] : 0;
        potentialAttackSquares[28] += localSquares[28 + 8];
        potentialAttackSquares[28] += localSquares[28 - 8];
        potentialAttackSquares[28] += localSquares[28 + 7];
        potentialAttackSquares[28] += localSquares[28 - 7];
        potentialAttackSquares[28] += localSquares[28 + 6];
        potentialAttackSquares[28] += localSquares[28 - 6];
        potentialAttackSquares[28] += localSquares[28 + -1];
        potentialAttackSquares[28] += localSquares[28 ];
        potentialAttackSquares[28] += localSquares[28 + 1];
        potentialAttackSquares[28] += (localSquares[28 - 14] < 2) ? localSquares[28 - 14] : 0;
        potentialAttackSquares[28] += (localSquares[28 + 13] < 2) ? localSquares[28 + 13] : 0;
        potentialAttackSquares[28] += (localSquares[28 - 2] < 2) ? localSquares[28 - 2] : 0;
        potentialAttackSquares[28] += (localSquares[28 + 2] < 2) ? localSquares[28 + 2] : 0;
        potentialAttackSquares[29] += localSquares[29 + 8];
        potentialAttackSquares[29] += localSquares[29 - 8];
        potentialAttackSquares[29] += localSquares[29 + 7];
        potentialAttackSquares[29] += localSquares[29 - 7];
        potentialAttackSquares[29] += localSquares[29 + 6];
        potentialAttackSquares[29] += localSquares[29 - 6];
        potentialAttackSquares[29] += localSquares[29 + -1];
        potentialAttackSquares[29] += localSquares[29 ];
        potentialAttackSquares[29] += localSquares[29 + 1];
        potentialAttackSquares[29] += (localSquares[29 - 14] < 2) ? localSquares[29 - 14] : 0;
        potentialAttackSquares[29] += (localSquares[29 + 13] < 2) ? localSquares[29 + 13] : 0;
        potentialAttackSquares[29] += (localSquares[29 - 2] < 2) ? localSquares[29 - 2] : 0;
        potentialAttackSquares[29] += (localSquares[29 + 2] < 2) ? localSquares[29 + 2] : 0;
        potentialAttackSquares[30] += localSquares[30 + 8];
        potentialAttackSquares[30] += localSquares[30 - 8];
        potentialAttackSquares[30] += localSquares[30 + 7];
        potentialAttackSquares[30] += localSquares[30 - 7];
        potentialAttackSquares[30] += localSquares[30 + 6];
        potentialAttackSquares[30] += localSquares[30 - 6];
        potentialAttackSquares[30] += localSquares[30 + -1];
        potentialAttackSquares[30] += localSquares[30 ];
        potentialAttackSquares[30] += localSquares[30 + 1];
        potentialAttackSquares[30] += (localSquares[30 - 14] < 2) ? localSquares[30 - 14] : 0;
        potentialAttackSquares[30] += (localSquares[30 + 13] < 2) ? localSquares[30 + 13] : 0;
        potentialAttackSquares[30] += (localSquares[30 - 2] < 2) ? localSquares[30 - 2] : 0;
        potentialAttackSquares[30] += (localSquares[30 + 2] < 2) ? localSquares[30 + 2] : 0;
        int highest = potentialAttackSquares[14];
        int highestIndex = 14;
        for(int i = 15; i <= 30; i++) {
            if(potentialAttackSquares[i] > highest) {
                highestIndex = i;
                highest = potentialAttackSquares[i];
            }
        }
        if(highest < minScore) return null;
        int offSetX, offSetY;
        if(highestIndex <= 18) offSetX = -1;
        else if(highestIndex <= 25) offSetX = 0;
        else offSetX = 1;
        offSetY = (highestIndex % 7) - 1;
        //System.out.println("Cheap Price: " + (price - Clock.getBytecodesLeft()));
        return new MapLocation(staticRC.getLocation().x + offSetX, staticRC.getLocation().y + offSetY);
    }
}
