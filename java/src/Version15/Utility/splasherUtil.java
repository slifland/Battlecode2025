package Version15.Utility;

import battlecode.common.*;

import static Version15.RobotPlayer.*;
import static Version15.Robots.Soldier.tilesNearRuin;
import static Version15.Robots.Splasher.nearestUnfilledRuin;
import static Version15.Robots.Splasher.*;

public class splasherUtil {
    //static final int 3 = 3;
    public static MapLocation bestAttackBinary() {
        return null;

    }

    //used to see if we can run cheapBestAttack
    public static boolean farFromEdge(MapLocation loc) {
        //if(!loc.equals(rc.getLocation()) && !farFromEdge( rc.getLocation())) return true;
        int mapHeight = rc.getMapHeight() - 1;
        int mapWidth = rc.getMapWidth()- 1;
        return loc.x >= 3 && loc.y >= 3 && loc.x <= mapWidth - 3 && loc.y <= mapHeight - 3;
    }
    //used to see if we can run bestAttack
    public static boolean farFromEdgeNonMovement(MapLocation loc) {
        int mapHeight = rc.getMapHeight() - 1;
        int mapWidth = rc.getMapWidth()- 1;
        return loc.x >= 4 && loc.y >= 4 && loc.x <= mapWidth - 4 && loc.y <= mapHeight - 4;
    }
    //refreshes paint averages
    public static void refreshPaintAverages() throws GameActionException {
        numEnemyTiles = 0;
        averageEnemyPaint = null;
        //boolean hasSeenNoWall = false;
        int x = 0;
        int y = 0;
        switch(nearbyTiles.length)
        {
            case 57->
            {
                //Symmetry.processTile(nearbyTiles[0]);
                if(nearbyTiles[0].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[0].getMapLocation()))
                {
                    x += nearbyTiles[0].getMapLocation().x;
                    y += nearbyTiles[0].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[1]);
                if(nearbyTiles[1].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[1].getMapLocation()))
                {
                    x += nearbyTiles[1].getMapLocation().x;
                    y += nearbyTiles[1].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[2]);
                if(nearbyTiles[2].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[2].getMapLocation()))
                {
                    x += nearbyTiles[2].getMapLocation().x;
                    y += nearbyTiles[2].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[3]);
                if(nearbyTiles[3].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[3].getMapLocation()))
                {
                    x += nearbyTiles[3].getMapLocation().x;
                    y += nearbyTiles[3].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[4]);
                if(nearbyTiles[4].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[4].getMapLocation()))
                {
                    x += nearbyTiles[4].getMapLocation().x;
                    y += nearbyTiles[4].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[5]);
                if(nearbyTiles[5].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[5].getMapLocation()))
                {
                    x += nearbyTiles[5].getMapLocation().x;
                    y += nearbyTiles[5].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[6]);
                if(nearbyTiles[6].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[6].getMapLocation()))
                {
                    x += nearbyTiles[6].getMapLocation().x;
                    y += nearbyTiles[6].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[7]);
                if(nearbyTiles[7].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[7].getMapLocation()))
                {
                    x += nearbyTiles[7].getMapLocation().x;
                    y += nearbyTiles[7].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[8]);
                if(nearbyTiles[8].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[8].getMapLocation()))
                {
                    x += nearbyTiles[8].getMapLocation().x;
                    y += nearbyTiles[8].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[9]);
                if(nearbyTiles[9].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[9].getMapLocation()))
                {
                    x += nearbyTiles[9].getMapLocation().x;
                    y += nearbyTiles[9].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[10]);
                if(nearbyTiles[10].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[10].getMapLocation()))
                {
                    x += nearbyTiles[10].getMapLocation().x;
                    y += nearbyTiles[10].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[11]);
                if(nearbyTiles[11].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[11].getMapLocation()))
                {
                    x += nearbyTiles[11].getMapLocation().x;
                    y += nearbyTiles[11].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[12]);
                if(nearbyTiles[12].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[12].getMapLocation()))
                {
                    x += nearbyTiles[12].getMapLocation().x;
                    y += nearbyTiles[12].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[13]);
                if(nearbyTiles[13].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[13].getMapLocation()))
                {
                    x += nearbyTiles[13].getMapLocation().x;
                    y += nearbyTiles[13].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[14]);
                if(nearbyTiles[14].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[14].getMapLocation()))
                {
                    x += nearbyTiles[14].getMapLocation().x;
                    y += nearbyTiles[14].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[15]);
                if(nearbyTiles[15].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[15].getMapLocation()))
                {
                    x += nearbyTiles[15].getMapLocation().x;
                    y += nearbyTiles[15].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[16]);
                if(nearbyTiles[16].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[16].getMapLocation()))
                {
                    x += nearbyTiles[16].getMapLocation().x;
                    y += nearbyTiles[16].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[17]);
                if(nearbyTiles[17].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[17].getMapLocation()))
                {
                    x += nearbyTiles[17].getMapLocation().x;
                    y += nearbyTiles[17].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[18]);
                if(nearbyTiles[18].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[18].getMapLocation()))
                {
                    x += nearbyTiles[18].getMapLocation().x;
                    y += nearbyTiles[18].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[19]);
                if(nearbyTiles[19].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[19].getMapLocation()))
                {
                    x += nearbyTiles[19].getMapLocation().x;
                    y += nearbyTiles[19].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[20]);
                if(nearbyTiles[20].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[20].getMapLocation()))
                {
                    x += nearbyTiles[20].getMapLocation().x;
                    y += nearbyTiles[20].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[21]);
                if(nearbyTiles[21].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[21].getMapLocation()))
                {
                    x += nearbyTiles[21].getMapLocation().x;
                    y += nearbyTiles[21].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[22]);
                if(nearbyTiles[22].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[22].getMapLocation()))
                {
                    x += nearbyTiles[22].getMapLocation().x;
                    y += nearbyTiles[22].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[23]);
                if(nearbyTiles[23].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[23].getMapLocation()))
                {
                    x += nearbyTiles[23].getMapLocation().x;
                    y += nearbyTiles[23].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[24]);
                if(nearbyTiles[24].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[24].getMapLocation()))
                {
                    x += nearbyTiles[24].getMapLocation().x;
                    y += nearbyTiles[24].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[25]);
                if(nearbyTiles[25].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[25].getMapLocation()))
                {
                    x += nearbyTiles[25].getMapLocation().x;
                    y += nearbyTiles[25].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[26]);
                if(nearbyTiles[26].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[26].getMapLocation()))
                {
                    x += nearbyTiles[26].getMapLocation().x;
                    y += nearbyTiles[26].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[27]);
                if(nearbyTiles[27].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[27].getMapLocation()))
                {
                    x += nearbyTiles[27].getMapLocation().x;
                    y += nearbyTiles[27].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[28]);
                if(nearbyTiles[28].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[28].getMapLocation()))
                {
                    x += nearbyTiles[28].getMapLocation().x;
                    y += nearbyTiles[28].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[29]);
                if(nearbyTiles[29].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[29].getMapLocation()))
                {
                    x += nearbyTiles[29].getMapLocation().x;
                    y += nearbyTiles[29].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[30]);
                if(nearbyTiles[30].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[30].getMapLocation()))
                {
                    x += nearbyTiles[30].getMapLocation().x;
                    y += nearbyTiles[30].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[31]);
                if(nearbyTiles[31].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[31].getMapLocation()))
                {
                    x += nearbyTiles[31].getMapLocation().x;
                    y += nearbyTiles[31].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[32]);
                if(nearbyTiles[32].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[32].getMapLocation()))
                {
                    x += nearbyTiles[32].getMapLocation().x;
                    y += nearbyTiles[32].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[33]);
                if(nearbyTiles[33].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[33].getMapLocation()))
                {
                    x += nearbyTiles[33].getMapLocation().x;
                    y += nearbyTiles[33].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[34]);
                if(nearbyTiles[34].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[34].getMapLocation()))
                {
                    x += nearbyTiles[34].getMapLocation().x;
                    y += nearbyTiles[34].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[35]);
                if(nearbyTiles[35].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[35].getMapLocation()))
                {
                    x += nearbyTiles[35].getMapLocation().x;
                    y += nearbyTiles[35].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[36]);
                if(nearbyTiles[36].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[36].getMapLocation()))
                {
                    x += nearbyTiles[36].getMapLocation().x;
                    y += nearbyTiles[36].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[37]);
                if(nearbyTiles[37].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[37].getMapLocation()))
                {
                    x += nearbyTiles[37].getMapLocation().x;
                    y += nearbyTiles[37].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[38]);
                if(nearbyTiles[38].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[38].getMapLocation()))
                {
                    x += nearbyTiles[38].getMapLocation().x;
                    y += nearbyTiles[38].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[39]);
                if(nearbyTiles[39].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[39].getMapLocation()))
                {
                    x += nearbyTiles[39].getMapLocation().x;
                    y += nearbyTiles[39].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[40]);
                if(nearbyTiles[40].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[40].getMapLocation()))
                {
                    x += nearbyTiles[40].getMapLocation().x;
                    y += nearbyTiles[40].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[41]);
                if(nearbyTiles[41].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[41].getMapLocation()))
                {
                    x += nearbyTiles[41].getMapLocation().x;
                    y += nearbyTiles[41].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[42]);
                if(nearbyTiles[42].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[42].getMapLocation()))
                {
                    x += nearbyTiles[42].getMapLocation().x;
                    y += nearbyTiles[42].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[43]);
                if(nearbyTiles[43].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[43].getMapLocation()))
                {
                    x += nearbyTiles[43].getMapLocation().x;
                    y += nearbyTiles[43].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[44]);
                if(nearbyTiles[44].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[44].getMapLocation()))
                {
                    x += nearbyTiles[44].getMapLocation().x;
                    y += nearbyTiles[44].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[45]);
                if(nearbyTiles[45].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[45].getMapLocation()))
                {
                    x += nearbyTiles[45].getMapLocation().x;
                    y += nearbyTiles[45].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[46]);
                if(nearbyTiles[46].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[46].getMapLocation()))
                {
                    x += nearbyTiles[46].getMapLocation().x;
                    y += nearbyTiles[46].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[47]);
                if(nearbyTiles[47].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[47].getMapLocation()))
                {
                    x += nearbyTiles[47].getMapLocation().x;
                    y += nearbyTiles[47].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[48]);
                if(nearbyTiles[48].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[48].getMapLocation()))
                {
                    x += nearbyTiles[48].getMapLocation().x;
                    y += nearbyTiles[48].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[49]);
                if(nearbyTiles[49].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[49].getMapLocation()))
                {
                    x += nearbyTiles[49].getMapLocation().x;
                    y += nearbyTiles[49].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[50]);
                if(nearbyTiles[50].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[50].getMapLocation()))
                {
                    x += nearbyTiles[50].getMapLocation().x;
                    y += nearbyTiles[50].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[51]);
                if(nearbyTiles[51].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[51].getMapLocation()))
                {
                    x += nearbyTiles[51].getMapLocation().x;
                    y += nearbyTiles[51].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[52]);
                if(nearbyTiles[52].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[52].getMapLocation()))
                {
                    x += nearbyTiles[52].getMapLocation().x;
                    y += nearbyTiles[52].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[53]);
                if(nearbyTiles[53].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[53].getMapLocation()))
                {
                    x += nearbyTiles[53].getMapLocation().x;
                    y += nearbyTiles[53].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[54]);
                if(nearbyTiles[54].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[54].getMapLocation()))
                {
                    x += nearbyTiles[54].getMapLocation().x;
                    y += nearbyTiles[54].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[55]);
                if(nearbyTiles[55].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[55].getMapLocation()))
                {
                    x += nearbyTiles[55].getMapLocation().x;
                    y += nearbyTiles[55].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[56]);
                if(nearbyTiles[56].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[56].getMapLocation()))
                {
                    x += nearbyTiles[56].getMapLocation().x;
                    y += nearbyTiles[56].getMapLocation().y;
                    numEnemyTiles++;
                }
            }
            case 64->
            {
                //Symmetry.processTile(nearbyTiles[0]);
                if(nearbyTiles[0].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[0].getMapLocation()))
                {
                    x += nearbyTiles[0].getMapLocation().x;
                    y += nearbyTiles[0].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[1]);
                if(nearbyTiles[1].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[1].getMapLocation()))
                {
                    x += nearbyTiles[1].getMapLocation().x;
                    y += nearbyTiles[1].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[2]);
                if(nearbyTiles[2].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[2].getMapLocation()))
                {
                    x += nearbyTiles[2].getMapLocation().x;
                    y += nearbyTiles[2].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[3]);
                if(nearbyTiles[3].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[3].getMapLocation()))
                {
                    x += nearbyTiles[3].getMapLocation().x;
                    y += nearbyTiles[3].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[4]);
                if(nearbyTiles[4].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[4].getMapLocation()))
                {
                    x += nearbyTiles[4].getMapLocation().x;
                    y += nearbyTiles[4].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[5]);
                if(nearbyTiles[5].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[5].getMapLocation()))
                {
                    x += nearbyTiles[5].getMapLocation().x;
                    y += nearbyTiles[5].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[6]);
                if(nearbyTiles[6].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[6].getMapLocation()))
                {
                    x += nearbyTiles[6].getMapLocation().x;
                    y += nearbyTiles[6].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[7]);
                if(nearbyTiles[7].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[7].getMapLocation()))
                {
                    x += nearbyTiles[7].getMapLocation().x;
                    y += nearbyTiles[7].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[8]);
                if(nearbyTiles[8].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[8].getMapLocation()))
                {
                    x += nearbyTiles[8].getMapLocation().x;
                    y += nearbyTiles[8].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[9]);
                if(nearbyTiles[9].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[9].getMapLocation()))
                {
                    x += nearbyTiles[9].getMapLocation().x;
                    y += nearbyTiles[9].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[10]);
                if(nearbyTiles[10].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[10].getMapLocation()))
                {
                    x += nearbyTiles[10].getMapLocation().x;
                    y += nearbyTiles[10].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[11]);
                if(nearbyTiles[11].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[11].getMapLocation()))
                {
                    x += nearbyTiles[11].getMapLocation().x;
                    y += nearbyTiles[11].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[12]);
                if(nearbyTiles[12].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[12].getMapLocation()))
                {
                    x += nearbyTiles[12].getMapLocation().x;
                    y += nearbyTiles[12].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[13]);
                if(nearbyTiles[13].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[13].getMapLocation()))
                {
                    x += nearbyTiles[13].getMapLocation().x;
                    y += nearbyTiles[13].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[14]);
                if(nearbyTiles[14].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[14].getMapLocation()))
                {
                    x += nearbyTiles[14].getMapLocation().x;
                    y += nearbyTiles[14].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[15]);
                if(nearbyTiles[15].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[15].getMapLocation()))
                {
                    x += nearbyTiles[15].getMapLocation().x;
                    y += nearbyTiles[15].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[16]);
                if(nearbyTiles[16].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[16].getMapLocation()))
                {
                    x += nearbyTiles[16].getMapLocation().x;
                    y += nearbyTiles[16].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[17]);
                if(nearbyTiles[17].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[17].getMapLocation()))
                {
                    x += nearbyTiles[17].getMapLocation().x;
                    y += nearbyTiles[17].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[18]);
                if(nearbyTiles[18].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[18].getMapLocation()))
                {
                    x += nearbyTiles[18].getMapLocation().x;
                    y += nearbyTiles[18].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[19]);
                if(nearbyTiles[19].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[19].getMapLocation()))
                {
                    x += nearbyTiles[19].getMapLocation().x;
                    y += nearbyTiles[19].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[20]);
                if(nearbyTiles[20].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[20].getMapLocation()))
                {
                    x += nearbyTiles[20].getMapLocation().x;
                    y += nearbyTiles[20].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[21]);
                if(nearbyTiles[21].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[21].getMapLocation()))
                {
                    x += nearbyTiles[21].getMapLocation().x;
                    y += nearbyTiles[21].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[22]);
                if(nearbyTiles[22].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[22].getMapLocation()))
                {
                    x += nearbyTiles[22].getMapLocation().x;
                    y += nearbyTiles[22].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[23]);
                if(nearbyTiles[23].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[23].getMapLocation()))
                {
                    x += nearbyTiles[23].getMapLocation().x;
                    y += nearbyTiles[23].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[24]);
                if(nearbyTiles[24].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[24].getMapLocation()))
                {
                    x += nearbyTiles[24].getMapLocation().x;
                    y += nearbyTiles[24].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[25]);
                if(nearbyTiles[25].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[25].getMapLocation()))
                {
                    x += nearbyTiles[25].getMapLocation().x;
                    y += nearbyTiles[25].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[26]);
                if(nearbyTiles[26].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[26].getMapLocation()))
                {
                    x += nearbyTiles[26].getMapLocation().x;
                    y += nearbyTiles[26].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[27]);
                if(nearbyTiles[27].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[27].getMapLocation()))
                {
                    x += nearbyTiles[27].getMapLocation().x;
                    y += nearbyTiles[27].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[28]);
                if(nearbyTiles[28].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[28].getMapLocation()))
                {
                    x += nearbyTiles[28].getMapLocation().x;
                    y += nearbyTiles[28].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[29]);
                if(nearbyTiles[29].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[29].getMapLocation()))
                {
                    x += nearbyTiles[29].getMapLocation().x;
                    y += nearbyTiles[29].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[30]);
                if(nearbyTiles[30].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[30].getMapLocation()))
                {
                    x += nearbyTiles[30].getMapLocation().x;
                    y += nearbyTiles[30].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[31]);
                if(nearbyTiles[31].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[31].getMapLocation()))
                {
                    x += nearbyTiles[31].getMapLocation().x;
                    y += nearbyTiles[31].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[32]);
                if(nearbyTiles[32].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[32].getMapLocation()))
                {
                    x += nearbyTiles[32].getMapLocation().x;
                    y += nearbyTiles[32].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[33]);
                if(nearbyTiles[33].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[33].getMapLocation()))
                {
                    x += nearbyTiles[33].getMapLocation().x;
                    y += nearbyTiles[33].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[34]);
                if(nearbyTiles[34].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[34].getMapLocation()))
                {
                    x += nearbyTiles[34].getMapLocation().x;
                    y += nearbyTiles[34].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[35]);
                if(nearbyTiles[35].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[35].getMapLocation()))
                {
                    x += nearbyTiles[35].getMapLocation().x;
                    y += nearbyTiles[35].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[36]);
                if(nearbyTiles[36].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[36].getMapLocation()))
                {
                    x += nearbyTiles[36].getMapLocation().x;
                    y += nearbyTiles[36].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[37]);
                if(nearbyTiles[37].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[37].getMapLocation()))
                {
                    x += nearbyTiles[37].getMapLocation().x;
                    y += nearbyTiles[37].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[38]);
                if(nearbyTiles[38].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[38].getMapLocation()))
                {
                    x += nearbyTiles[38].getMapLocation().x;
                    y += nearbyTiles[38].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[39]);
                if(nearbyTiles[39].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[39].getMapLocation()))
                {
                    x += nearbyTiles[39].getMapLocation().x;
                    y += nearbyTiles[39].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[40]);
                if(nearbyTiles[40].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[40].getMapLocation()))
                {
                    x += nearbyTiles[40].getMapLocation().x;
                    y += nearbyTiles[40].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[41]);
                if(nearbyTiles[41].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[41].getMapLocation()))
                {
                    x += nearbyTiles[41].getMapLocation().x;
                    y += nearbyTiles[41].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[42]);
                if(nearbyTiles[42].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[42].getMapLocation()))
                {
                    x += nearbyTiles[42].getMapLocation().x;
                    y += nearbyTiles[42].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[43]);
                if(nearbyTiles[43].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[43].getMapLocation()))
                {
                    x += nearbyTiles[43].getMapLocation().x;
                    y += nearbyTiles[43].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[44]);
                if(nearbyTiles[44].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[44].getMapLocation()))
                {
                    x += nearbyTiles[44].getMapLocation().x;
                    y += nearbyTiles[44].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[45]);
                if(nearbyTiles[45].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[45].getMapLocation()))
                {
                    x += nearbyTiles[45].getMapLocation().x;
                    y += nearbyTiles[45].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[46]);
                if(nearbyTiles[46].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[46].getMapLocation()))
                {
                    x += nearbyTiles[46].getMapLocation().x;
                    y += nearbyTiles[46].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[47]);
                if(nearbyTiles[47].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[47].getMapLocation()))
                {
                    x += nearbyTiles[47].getMapLocation().x;
                    y += nearbyTiles[47].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[48]);
                if(nearbyTiles[48].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[48].getMapLocation()))
                {
                    x += nearbyTiles[48].getMapLocation().x;
                    y += nearbyTiles[48].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[49]);
                if(nearbyTiles[49].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[49].getMapLocation()))
                {
                    x += nearbyTiles[49].getMapLocation().x;
                    y += nearbyTiles[49].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[50]);
                if(nearbyTiles[50].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[50].getMapLocation()))
                {
                    x += nearbyTiles[50].getMapLocation().x;
                    y += nearbyTiles[50].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[51]);
                if(nearbyTiles[51].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[51].getMapLocation()))
                {
                    x += nearbyTiles[51].getMapLocation().x;
                    y += nearbyTiles[51].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[52]);
                if(nearbyTiles[52].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[52].getMapLocation()))
                {
                    x += nearbyTiles[52].getMapLocation().x;
                    y += nearbyTiles[52].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[53]);
                if(nearbyTiles[53].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[53].getMapLocation()))
                {
                    x += nearbyTiles[53].getMapLocation().x;
                    y += nearbyTiles[53].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[54]);
                if(nearbyTiles[54].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[54].getMapLocation()))
                {
                    x += nearbyTiles[54].getMapLocation().x;
                    y += nearbyTiles[54].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[55]);
                if(nearbyTiles[55].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[55].getMapLocation()))
                {
                    x += nearbyTiles[55].getMapLocation().x;
                    y += nearbyTiles[55].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[56]);
                if(nearbyTiles[56].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[56].getMapLocation()))
                {
                    x += nearbyTiles[56].getMapLocation().x;
                    y += nearbyTiles[56].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[57]);
                if(nearbyTiles[57].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[57].getMapLocation()))
                {
                    x += nearbyTiles[57].getMapLocation().x;
                    y += nearbyTiles[57].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[58]);
                if(nearbyTiles[58].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[58].getMapLocation()))
                {
                    x += nearbyTiles[58].getMapLocation().x;
                    y += nearbyTiles[58].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[59]);
                if(nearbyTiles[59].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[59].getMapLocation()))
                {
                    x += nearbyTiles[59].getMapLocation().x;
                    y += nearbyTiles[59].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[60]);
                if(nearbyTiles[60].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[60].getMapLocation()))
                {
                    x += nearbyTiles[60].getMapLocation().x;
                    y += nearbyTiles[60].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[61]);
                if(nearbyTiles[61].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[61].getMapLocation()))
                {
                    x += nearbyTiles[61].getMapLocation().x;
                    y += nearbyTiles[61].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[62]);
                if(nearbyTiles[62].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[62].getMapLocation()))
                {
                    x += nearbyTiles[62].getMapLocation().x;
                    y += nearbyTiles[62].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[63]);
                if(nearbyTiles[63].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[63].getMapLocation()))
                {
                    x += nearbyTiles[63].getMapLocation().x;
                    y += nearbyTiles[63].getMapLocation().y;
                    numEnemyTiles++;
                }
            }
            case 69->
            {
                //Symmetry.processTile(nearbyTiles[0]);
                if(nearbyTiles[0].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[0].getMapLocation()))
                {
                    x += nearbyTiles[0].getMapLocation().x;
                    y += nearbyTiles[0].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[1]);
                if(nearbyTiles[1].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[1].getMapLocation()))
                {
                    x += nearbyTiles[1].getMapLocation().x;
                    y += nearbyTiles[1].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[2]);
                if(nearbyTiles[2].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[2].getMapLocation()))
                {
                    x += nearbyTiles[2].getMapLocation().x;
                    y += nearbyTiles[2].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[3]);
                if(nearbyTiles[3].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[3].getMapLocation()))
                {
                    x += nearbyTiles[3].getMapLocation().x;
                    y += nearbyTiles[3].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[4]);
                if(nearbyTiles[4].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[4].getMapLocation()))
                {
                    x += nearbyTiles[4].getMapLocation().x;
                    y += nearbyTiles[4].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[5]);
                if(nearbyTiles[5].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[5].getMapLocation()))
                {
                    x += nearbyTiles[5].getMapLocation().x;
                    y += nearbyTiles[5].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[6]);
                if(nearbyTiles[6].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[6].getMapLocation()))
                {
                    x += nearbyTiles[6].getMapLocation().x;
                    y += nearbyTiles[6].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[7]);
                if(nearbyTiles[7].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[7].getMapLocation()))
                {
                    x += nearbyTiles[7].getMapLocation().x;
                    y += nearbyTiles[7].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[8]);
                if(nearbyTiles[8].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[8].getMapLocation()))
                {
                    x += nearbyTiles[8].getMapLocation().x;
                    y += nearbyTiles[8].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[9]);
                if(nearbyTiles[9].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[9].getMapLocation()))
                {
                    x += nearbyTiles[9].getMapLocation().x;
                    y += nearbyTiles[9].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[10]);
                if(nearbyTiles[10].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[10].getMapLocation()))
                {
                    x += nearbyTiles[10].getMapLocation().x;
                    y += nearbyTiles[10].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[11]);
                if(nearbyTiles[11].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[11].getMapLocation()))
                {
                    x += nearbyTiles[11].getMapLocation().x;
                    y += nearbyTiles[11].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[12]);
                if(nearbyTiles[12].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[12].getMapLocation()))
                {
                    x += nearbyTiles[12].getMapLocation().x;
                    y += nearbyTiles[12].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[13]);
                if(nearbyTiles[13].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[13].getMapLocation()))
                {
                    x += nearbyTiles[13].getMapLocation().x;
                    y += nearbyTiles[13].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[14]);
                if(nearbyTiles[14].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[14].getMapLocation()))
                {
                    x += nearbyTiles[14].getMapLocation().x;
                    y += nearbyTiles[14].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[15]);
                if(nearbyTiles[15].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[15].getMapLocation()))
                {
                    x += nearbyTiles[15].getMapLocation().x;
                    y += nearbyTiles[15].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[16]);
                if(nearbyTiles[16].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[16].getMapLocation()))
                {
                    x += nearbyTiles[16].getMapLocation().x;
                    y += nearbyTiles[16].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[17]);
                if(nearbyTiles[17].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[17].getMapLocation()))
                {
                    x += nearbyTiles[17].getMapLocation().x;
                    y += nearbyTiles[17].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[18]);
                if(nearbyTiles[18].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[18].getMapLocation()))
                {
                    x += nearbyTiles[18].getMapLocation().x;
                    y += nearbyTiles[18].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[19]);
                if(nearbyTiles[19].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[19].getMapLocation()))
                {
                    x += nearbyTiles[19].getMapLocation().x;
                    y += nearbyTiles[19].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[20]);
                if(nearbyTiles[20].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[20].getMapLocation()))
                {
                    x += nearbyTiles[20].getMapLocation().x;
                    y += nearbyTiles[20].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[21]);
                if(nearbyTiles[21].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[21].getMapLocation()))
                {
                    x += nearbyTiles[21].getMapLocation().x;
                    y += nearbyTiles[21].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[22]);
                if(nearbyTiles[22].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[22].getMapLocation()))
                {
                    x += nearbyTiles[22].getMapLocation().x;
                    y += nearbyTiles[22].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[23]);
                if(nearbyTiles[23].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[23].getMapLocation()))
                {
                    x += nearbyTiles[23].getMapLocation().x;
                    y += nearbyTiles[23].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[24]);
                if(nearbyTiles[24].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[24].getMapLocation()))
                {
                    x += nearbyTiles[24].getMapLocation().x;
                    y += nearbyTiles[24].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[25]);
                if(nearbyTiles[25].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[25].getMapLocation()))
                {
                    x += nearbyTiles[25].getMapLocation().x;
                    y += nearbyTiles[25].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[26]);
                if(nearbyTiles[26].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[26].getMapLocation()))
                {
                    x += nearbyTiles[26].getMapLocation().x;
                    y += nearbyTiles[26].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[27]);
                if(nearbyTiles[27].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[27].getMapLocation()))
                {
                    x += nearbyTiles[27].getMapLocation().x;
                    y += nearbyTiles[27].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[28]);
                if(nearbyTiles[28].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[28].getMapLocation()))
                {
                    x += nearbyTiles[28].getMapLocation().x;
                    y += nearbyTiles[28].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[29]);
                if(nearbyTiles[29].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[29].getMapLocation()))
                {
                    x += nearbyTiles[29].getMapLocation().x;
                    y += nearbyTiles[29].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[30]);
                if(nearbyTiles[30].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[30].getMapLocation()))
                {
                    x += nearbyTiles[30].getMapLocation().x;
                    y += nearbyTiles[30].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[31]);
                if(nearbyTiles[31].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[31].getMapLocation()))
                {
                    x += nearbyTiles[31].getMapLocation().x;
                    y += nearbyTiles[31].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[32]);
                if(nearbyTiles[32].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[32].getMapLocation()))
                {
                    x += nearbyTiles[32].getMapLocation().x;
                    y += nearbyTiles[32].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[33]);
                if(nearbyTiles[33].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[33].getMapLocation()))
                {
                    x += nearbyTiles[33].getMapLocation().x;
                    y += nearbyTiles[33].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[34]);
                if(nearbyTiles[34].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[34].getMapLocation()))
                {
                    x += nearbyTiles[34].getMapLocation().x;
                    y += nearbyTiles[34].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[35]);
                if(nearbyTiles[35].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[35].getMapLocation()))
                {
                    x += nearbyTiles[35].getMapLocation().x;
                    y += nearbyTiles[35].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[36]);
                if(nearbyTiles[36].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[36].getMapLocation()))
                {
                    x += nearbyTiles[36].getMapLocation().x;
                    y += nearbyTiles[36].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[37]);
                if(nearbyTiles[37].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[37].getMapLocation()))
                {
                    x += nearbyTiles[37].getMapLocation().x;
                    y += nearbyTiles[37].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[38]);
                if(nearbyTiles[38].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[38].getMapLocation()))
                {
                    x += nearbyTiles[38].getMapLocation().x;
                    y += nearbyTiles[38].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[39]);
                if(nearbyTiles[39].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[39].getMapLocation()))
                {
                    x += nearbyTiles[39].getMapLocation().x;
                    y += nearbyTiles[39].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[40]);
                if(nearbyTiles[40].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[40].getMapLocation()))
                {
                    x += nearbyTiles[40].getMapLocation().x;
                    y += nearbyTiles[40].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[41]);
                if(nearbyTiles[41].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[41].getMapLocation()))
                {
                    x += nearbyTiles[41].getMapLocation().x;
                    y += nearbyTiles[41].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[42]);
                if(nearbyTiles[42].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[42].getMapLocation()))
                {
                    x += nearbyTiles[42].getMapLocation().x;
                    y += nearbyTiles[42].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[43]);
                if(nearbyTiles[43].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[43].getMapLocation()))
                {
                    x += nearbyTiles[43].getMapLocation().x;
                    y += nearbyTiles[43].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[44]);
                if(nearbyTiles[44].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[44].getMapLocation()))
                {
                    x += nearbyTiles[44].getMapLocation().x;
                    y += nearbyTiles[44].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[45]);
                if(nearbyTiles[45].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[45].getMapLocation()))
                {
                    x += nearbyTiles[45].getMapLocation().x;
                    y += nearbyTiles[45].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[46]);
                if(nearbyTiles[46].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[46].getMapLocation()))
                {
                    x += nearbyTiles[46].getMapLocation().x;
                    y += nearbyTiles[46].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[47]);
                if(nearbyTiles[47].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[47].getMapLocation()))
                {
                    x += nearbyTiles[47].getMapLocation().x;
                    y += nearbyTiles[47].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[48]);
                if(nearbyTiles[48].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[48].getMapLocation()))
                {
                    x += nearbyTiles[48].getMapLocation().x;
                    y += nearbyTiles[48].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[49]);
                if(nearbyTiles[49].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[49].getMapLocation()))
                {
                    x += nearbyTiles[49].getMapLocation().x;
                    y += nearbyTiles[49].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[50]);
                if(nearbyTiles[50].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[50].getMapLocation()))
                {
                    x += nearbyTiles[50].getMapLocation().x;
                    y += nearbyTiles[50].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[51]);
                if(nearbyTiles[51].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[51].getMapLocation()))
                {
                    x += nearbyTiles[51].getMapLocation().x;
                    y += nearbyTiles[51].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[52]);
                if(nearbyTiles[52].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[52].getMapLocation()))
                {
                    x += nearbyTiles[52].getMapLocation().x;
                    y += nearbyTiles[52].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[53]);
                if(nearbyTiles[53].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[53].getMapLocation()))
                {
                    x += nearbyTiles[53].getMapLocation().x;
                    y += nearbyTiles[53].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[54]);
                if(nearbyTiles[54].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[54].getMapLocation()))
                {
                    x += nearbyTiles[54].getMapLocation().x;
                    y += nearbyTiles[54].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[55]);
                if(nearbyTiles[55].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[55].getMapLocation()))
                {
                    x += nearbyTiles[55].getMapLocation().x;
                    y += nearbyTiles[55].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[56]);
                if(nearbyTiles[56].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[56].getMapLocation()))
                {
                    x += nearbyTiles[56].getMapLocation().x;
                    y += nearbyTiles[56].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[57]);
                if(nearbyTiles[57].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[57].getMapLocation()))
                {
                    x += nearbyTiles[57].getMapLocation().x;
                    y += nearbyTiles[57].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[58]);
                if(nearbyTiles[58].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[58].getMapLocation()))
                {
                    x += nearbyTiles[58].getMapLocation().x;
                    y += nearbyTiles[58].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[59]);
                if(nearbyTiles[59].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[59].getMapLocation()))
                {
                    x += nearbyTiles[59].getMapLocation().x;
                    y += nearbyTiles[59].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[60]);
                if(nearbyTiles[60].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[60].getMapLocation()))
                {
                    x += nearbyTiles[60].getMapLocation().x;
                    y += nearbyTiles[60].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[61]);
                if(nearbyTiles[61].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[61].getMapLocation()))
                {
                    x += nearbyTiles[61].getMapLocation().x;
                    y += nearbyTiles[61].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[62]);
                if(nearbyTiles[62].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[62].getMapLocation()))
                {
                    x += nearbyTiles[62].getMapLocation().x;
                    y += nearbyTiles[62].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[63]);
                if(nearbyTiles[63].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[63].getMapLocation()))
                {
                    x += nearbyTiles[63].getMapLocation().x;
                    y += nearbyTiles[63].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[64]);
                if(nearbyTiles[64].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[64].getMapLocation()))
                {
                    x += nearbyTiles[64].getMapLocation().x;
                    y += nearbyTiles[64].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[65]);
                if(nearbyTiles[65].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[65].getMapLocation()))
                {
                    x += nearbyTiles[65].getMapLocation().x;
                    y += nearbyTiles[65].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[66]);
                if(nearbyTiles[66].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[66].getMapLocation()))
                {
                    x += nearbyTiles[66].getMapLocation().x;
                    y += nearbyTiles[66].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[67]);
                if(nearbyTiles[67].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[67].getMapLocation()))
                {
                    x += nearbyTiles[67].getMapLocation().x;
                    y += nearbyTiles[67].getMapLocation().y;
                    numEnemyTiles++;
                }
                //Symmetry.processTile(nearbyTiles[68]);
                if(nearbyTiles[68].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[68].getMapLocation()))
                {
                    x += nearbyTiles[68].getMapLocation().x;
                    y += nearbyTiles[68].getMapLocation().y;
                    numEnemyTiles++;
                }
            }
            default -> {
                //now, check if we can see any enemy tiles
                for (MapInfo tile : nearbyTiles) {
                    //Symmetry.processTile(tile);
                    if (tile.getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(tile.getMapLocation())) {
                        x += tile.getMapLocation().x;
                        y += tile.getMapLocation().y;
                        numEnemyTiles++;
                    }
                }
            }
        }

        averageEnemyPaint = (numEnemyTiles == 0) ? null : new MapLocation(x / numEnemyTiles, y / numEnemyTiles);
    }

    //returns a basic location to attack - used when very close to edge
    public static MapLocation basicBestAttack() {
        MapInfo bestTile = null;
        int bestDist = Integer.MAX_VALUE;
        boolean isEnemyTile = false;
        MapLocation curLoc = rc.getLocation();
        PaintType paint;
        for(MapInfo tile : nearbyTiles) {
            paint = tile.getPaint();
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
        if(!farFromEdgeNonMovement( rc.getLocation())) return cheapBestAttack( fightingTower, minScore);
        //keeps track of total potential points, so we can short circuit and save bytecode if possible
        int totalPoints = 0;
        int[] localSquares = new int[81];
        int[] potentialAttackSquares = new int[81];
        //int index = 0;
        int totalTracker = 0;
        PaintType paint;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[0].getPaint();
        if(nearbyTiles[0].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[0].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[0].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[0].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[1].getPaint();
        if(nearbyTiles[1].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[1].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[1].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[1].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[2].getPaint();
        if(nearbyTiles[2].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[2].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[2].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[2].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[3].getPaint();
        if(nearbyTiles[3].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[3].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[3].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[3].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[4].getPaint();
        if(nearbyTiles[4].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[4].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[4].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[4].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[5].getPaint();
        if(nearbyTiles[5].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[5].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[5].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[5].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[6].getPaint();
        if(nearbyTiles[6].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[6].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[6].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[6].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[7].getPaint();
        if(nearbyTiles[7].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[7].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[7].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[7].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[8].getPaint();
        if(nearbyTiles[8].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[8].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[8].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[8].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[9].getPaint();
        if(nearbyTiles[9].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[9].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[9].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[9].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[10].getPaint();
        if(nearbyTiles[10].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[10].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[10].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[10].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[11].getPaint();
        if(nearbyTiles[11].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[11].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[11].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[11].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[12].getPaint();
        if(nearbyTiles[12].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[12].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[12].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[12].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[13].getPaint();
        if(nearbyTiles[13].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[13].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[13].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[13].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[14].getPaint();
        if(nearbyTiles[14].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[14].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[14].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[14].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[15].getPaint();
        if(nearbyTiles[15].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[15].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[15].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[15].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[16].getPaint();
        if(nearbyTiles[16].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[16].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[16].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[16].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[17].getPaint();
        if(nearbyTiles[17].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[17].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[17].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[17].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[18].getPaint();
        if(nearbyTiles[18].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[18].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[18].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[18].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[19].getPaint();
        if(nearbyTiles[19].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[19].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[19].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[19].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[20].getPaint();
        if(nearbyTiles[20].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[20].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[20].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[20].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[21].getPaint();
        if(nearbyTiles[21].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[21].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[21].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[21].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[22].getPaint();
        if(nearbyTiles[22].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[22].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[22].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[22].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[23].getPaint();
        if(nearbyTiles[23].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[23].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[23].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[23].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[24].getPaint();
        if(nearbyTiles[24].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[24].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[24].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[24].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[25].getPaint();
        if(nearbyTiles[25].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[25].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[25].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[25].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[26].getPaint();
        if(nearbyTiles[26].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[26].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[26].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[26].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[27].getPaint();
        if(nearbyTiles[27].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[27].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[27].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[27].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[28].getPaint();
        if(nearbyTiles[28].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[28].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[28].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[28].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[29].getPaint();
        if(nearbyTiles[29].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[29].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[29].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[29].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[30].getPaint();
        if(nearbyTiles[30].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[30].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[30].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[30].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[31].getPaint();
        if(nearbyTiles[31].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[31].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[31].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[31].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[32].getPaint();
        if(nearbyTiles[32].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[32].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[32].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[32].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[33].getPaint();
        if(nearbyTiles[33].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[33].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[33].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[33].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[34].getPaint();
        if(nearbyTiles[34].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[34].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[34].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[34].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[35].getPaint();
        if(nearbyTiles[35].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[35].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[35].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[35].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[36].getPaint();
        if(nearbyTiles[36].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[36].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[36].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[36].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[37].getPaint();
        if(nearbyTiles[37].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[37].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[37].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[37].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[38].getPaint();
        if(nearbyTiles[38].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[38].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[38].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[38].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[39].getPaint();
        if(nearbyTiles[39].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[39].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[39].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[39].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[40].getPaint();
        if(nearbyTiles[40].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[40].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[40].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[40].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[41].getPaint();
        if(nearbyTiles[41].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[41].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[41].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[41].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[42].getPaint();
        if(nearbyTiles[42].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[42].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[42].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[42].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[43].getPaint();
        if(nearbyTiles[43].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[43].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[43].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[43].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[44].getPaint();
        if(nearbyTiles[44].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[44].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[44].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[44].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[45].getPaint();
        if(nearbyTiles[45].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[45].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[45].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[45].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[46].getPaint();
        if(nearbyTiles[46].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[46].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[46].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[46].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[47].getPaint();
        if(nearbyTiles[47].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[47].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[47].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[47].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[48].getPaint();
        if(nearbyTiles[48].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[48].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[48].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[48].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[49].getPaint();
        if(nearbyTiles[49].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[49].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[49].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[49].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[50].getPaint();
        if(nearbyTiles[50].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[50].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[50].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[50].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[51].getPaint();
        if(nearbyTiles[51].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[51].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[51].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[51].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[52].getPaint();
        if(nearbyTiles[52].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[52].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[52].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[52].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[53].getPaint();
        if(nearbyTiles[53].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[53].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[53].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[53].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[54].getPaint();
        if(nearbyTiles[54].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[54].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[54].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[54].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[55].getPaint();
        if(nearbyTiles[55].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[55].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[55].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[55].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[56].getPaint();
        if(nearbyTiles[56].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[56].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[56].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[56].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[57].getPaint();
        if(nearbyTiles[57].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[57].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[57].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[57].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[58].getPaint();
        if(nearbyTiles[58].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[58].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[58].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[58].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[59].getPaint();
        if(nearbyTiles[59].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[59].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[59].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[59].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[60].getPaint();
        if(nearbyTiles[60].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[60].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[60].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[60].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[61].getPaint();
        if(nearbyTiles[61].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[61].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[61].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[61].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[62].getPaint();
        if(nearbyTiles[62].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[62].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[62].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[62].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[63].getPaint();
        if(nearbyTiles[63].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[63].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[63].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[63].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[64].getPaint();
        if(nearbyTiles[64].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[64].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[64].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[64].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[65].getPaint();
        if(nearbyTiles[65].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[65].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[65].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[65].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[66].getPaint();
        if(nearbyTiles[66].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[66].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[66].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[66].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[67].getPaint();
        if(nearbyTiles[67].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[67].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[67].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[67].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;
        while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80)
        {
            totalTracker++;
        }
        paint = nearbyTiles[68].getPaint();
        if(nearbyTiles[68].hasRuin())
        {
            if(seenEnemyTower != null && nearbyTiles[68].getMapLocation().equals(seenEnemyTower.getLocation()))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
        }
        else if(paint.isEnemy())
        {
            if(nearestUnfilledRuin != null && nearbyTiles[68].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8))
            {
                localSquares[totalTracker] += 3;
                totalPoints += 3;
            }
            else
            {
                localSquares[totalTracker] += 2;
                totalPoints += 2;
            }
        }
        else if(!paint.isAlly() && nearbyTiles[68].isPassable())
        {
            localSquares[totalTracker]++;
        }
        totalTracker++;



//        for(int index = 0; index < 69; index++) {
//            while(totalTracker == 0 || totalTracker == 1 || totalTracker == 7 || totalTracker == 8 || totalTracker == 9 || totalTracker == 17 || totalTracker == 63 || totalTracker == 71 || totalTracker == 72 || totalTracker == 73 || totalTracker == 79 || totalTracker == 80) {
//                totalTracker++;
//            }
//            paint = nearbyTiles[index].getPaint();
//            if(nearbyTiles[index].hasRuin()) {
//                if(seenEnemyTower != null && nearbyTiles[index].getMapLocation().equals(seenEnemyTower.getLocation())) {
//                    localSquares[totalTracker] += 3;
//                    totalPoints += 3;
//                }
//            }
//            //favor the enemy most, but also like empty squares
//            else if(paint.isAlly()) {
//                if(seenEnemyTower == null || !nearbyTiles[index].getMapLocation().isWithinDistanceSquared(seenEnemyTower.getLocation(), 4)) {
//                    localSquares[totalTracker]--;
//                }
//            }
//            else if(paint.isEnemy()){
//                if(nearestUnfilledRuin != null && nearbyTiles[index].getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
//                    localSquares[totalTracker]+= 2;
//                    totalPoints+= 2;
//                }
//                else {
//                    localSquares[totalTracker] += 1;
//                    totalPoints += 1;
//                }
//            }
//            totalTracker++;
//        }
        // Unrolled loop for all 69 iterations

        if(totalPoints < minScore) {return null;}
//        for(int i = 20; i <= 24; i++) {
//            for(int j = 10; j >= 8; j--) {
//                potentialAttackSquares[i] += localSquares[i + j];
//                potentialAttackSquares[i] += localSquares[i-j];
//            }
//            for(int j = -1; j <= 1; j++) {
//                potentialAttackSquares[i] += localSquares[i + j];
//            }
//            potentialAttackSquares[i] += (localSquares[i - 18]  < 2) ? localSquares[i - 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 18]  < 2) ? localSquares[i + 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i - 2]  < 2) ? localSquares[i - 2] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 2]  < 2) ? localSquares[i + 2] : 0;
//        }
//        for(int i = 29; i <= 33; i++) {
//            for(int j = 10; j >= 8; j--) {
//                potentialAttackSquares[i] += localSquares[i + j];
//                potentialAttackSquares[i] += localSquares[i-j];
//            }
//            for(int j = -1; j <= 1; j++) {
//                potentialAttackSquares[i] += localSquares[i + j];
//            }
//            potentialAttackSquares[i] += (localSquares[i - 18]  < 2) ? localSquares[i - 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 18]  < 2) ? localSquares[i + 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i - 2]  < 2) ? localSquares[i - 2] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 2]  < 2) ? localSquares[i + 2] : 0;
//        }
//        for(int i = 38; i <= 42; i++) {
//            for(int j = 10; j >= 8; j--) {
//                potentialAttackSquares[i] += localSquares[i + j];
//                potentialAttackSquares[i] += localSquares[i-j];
//            }
//            for(int j = -1; j <= 1; j++) {
//                potentialAttackSquares[i] += localSquares[i + j];
//            }
//            potentialAttackSquares[i] += (localSquares[i - 18]  < 2) ? localSquares[i - 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 18]  < 2) ? localSquares[i + 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i - 2]  < 2) ? localSquares[i - 2] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 2]  < 2) ? localSquares[i + 2] : 0;
//        }
//        for(int i = 47; i <= 51; i++) {
//            for(int j = 10; j >= 8; j--) {
//                potentialAttackSquares[i] += localSquares[i + j];
//                potentialAttackSquares[i] += localSquares[i-j];
//            }
//            for(int j = -1; j <= 1; j++) {
//                potentialAttackSquares[i] += localSquares[i + j];
//            }
//            potentialAttackSquares[i] += (localSquares[i - 18]  < 2) ? localSquares[i - 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 18]  < 2) ? localSquares[i + 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i - 2]  < 2) ? localSquares[i - 2] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 2]  < 2) ? localSquares[i + 2] : 0;
//        }
//        for(int i = 56; i <= 60; i++) {
//            for(int j = 10; j >= 8; j--) {
//                potentialAttackSquares[i] += localSquares[i + j];
//                potentialAttackSquares[i] += localSquares[i-j];
//            }
//            for(int j = -1; j <= 1; j++) {
//                potentialAttackSquares[i] += localSquares[i + j];
//            }
//            potentialAttackSquares[i] += (localSquares[i - 18]  < 2) ? localSquares[i - 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 18]  < 2) ? localSquares[i + 18] : 0;
//            potentialAttackSquares[i] += (localSquares[i - 2]  < 2) ? localSquares[i - 2] : 0;
//            potentialAttackSquares[i] += (localSquares[i + 2]  < 2) ? localSquares[i + 2] : 0;
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
        potentialAttackSquares[20] += (localSquares[20 - 18]  < 2) ? localSquares[20 - 18] : 0;
        potentialAttackSquares[20] += (localSquares[20 + 18]  < 2) ? localSquares[20 + 18] : 0;
        potentialAttackSquares[20] += (localSquares[20 - 2]  < 2) ? localSquares[20 - 2] : 0;
        potentialAttackSquares[20] += (localSquares[20 + 2]  < 2) ? localSquares[20 + 2] : 0;
        potentialAttackSquares[21] += localSquares[21 + 10];
        potentialAttackSquares[21] += localSquares[21 - 10];
        potentialAttackSquares[21] += localSquares[21 + 9];
        potentialAttackSquares[21] += localSquares[21 - 9];
        potentialAttackSquares[21] += localSquares[21 + 8];
        potentialAttackSquares[21] += localSquares[21 - 8];
        potentialAttackSquares[21] += localSquares[21 + -1];
        potentialAttackSquares[21] += localSquares[21 ];
        potentialAttackSquares[21] += localSquares[21 + 1];
        potentialAttackSquares[21] += (localSquares[21 - 18]  < 2) ? localSquares[21 - 18] : 0;
        potentialAttackSquares[21] += (localSquares[21 + 18]  < 2) ? localSquares[21 + 18] : 0;
        potentialAttackSquares[21] += (localSquares[21 - 2]  < 2) ? localSquares[21 - 2] : 0;
        potentialAttackSquares[21] += (localSquares[21 + 2]  < 2) ? localSquares[21 + 2] : 0;
        potentialAttackSquares[22] += localSquares[22 + 10];
        potentialAttackSquares[22] += localSquares[22 - 10];
        potentialAttackSquares[22] += localSquares[22 + 9];
        potentialAttackSquares[22] += localSquares[22 - 9];
        potentialAttackSquares[22] += localSquares[22 + 8];
        potentialAttackSquares[22] += localSquares[22 - 8];
        potentialAttackSquares[22] += localSquares[22 + -1];
        potentialAttackSquares[22] += localSquares[22 ];
        potentialAttackSquares[22] += localSquares[22 + 1];
        potentialAttackSquares[22] += (localSquares[22 - 18]  < 2) ? localSquares[22 - 18] : 0;
        potentialAttackSquares[22] += (localSquares[22 + 18]  < 2) ? localSquares[22 + 18] : 0;
        potentialAttackSquares[22] += (localSquares[22 - 2]  < 2) ? localSquares[22 - 2] : 0;
        potentialAttackSquares[22] += (localSquares[22 + 2]  < 2) ? localSquares[22 + 2] : 0;
        potentialAttackSquares[23] += localSquares[23 + 10];
        potentialAttackSquares[23] += localSquares[23 - 10];
        potentialAttackSquares[23] += localSquares[23 + 9];
        potentialAttackSquares[23] += localSquares[23 - 9];
        potentialAttackSquares[23] += localSquares[23 + 8];
        potentialAttackSquares[23] += localSquares[23 - 8];
        potentialAttackSquares[23] += localSquares[23 + -1];
        potentialAttackSquares[23] += localSquares[23 ];
        potentialAttackSquares[23] += localSquares[23 + 1];
        potentialAttackSquares[23] += (localSquares[23 - 18]  < 2) ? localSquares[23 - 18] : 0;
        potentialAttackSquares[23] += (localSquares[23 + 18]  < 2) ? localSquares[23 + 18] : 0;
        potentialAttackSquares[23] += (localSquares[23 - 2]  < 2) ? localSquares[23 - 2] : 0;
        potentialAttackSquares[23] += (localSquares[23 + 2]  < 2) ? localSquares[23 + 2] : 0;
        potentialAttackSquares[24] += localSquares[24 + 10];
        potentialAttackSquares[24] += localSquares[24 - 10];
        potentialAttackSquares[24] += localSquares[24 + 9];
        potentialAttackSquares[24] += localSquares[24 - 9];
        potentialAttackSquares[24] += localSquares[24 + 8];
        potentialAttackSquares[24] += localSquares[24 - 8];
        potentialAttackSquares[24] += localSquares[24 + -1];
        potentialAttackSquares[24] += localSquares[24 ];
        potentialAttackSquares[24] += localSquares[24 + 1];
        potentialAttackSquares[24] += (localSquares[24 - 18]  < 2) ? localSquares[24 - 18] : 0;
        potentialAttackSquares[24] += (localSquares[24 + 18]  < 2) ? localSquares[24 + 18] : 0;
        potentialAttackSquares[24] += (localSquares[24 - 2]  < 2) ? localSquares[24 - 2] : 0;
        potentialAttackSquares[24] += (localSquares[24 + 2]  < 2) ? localSquares[24 + 2] : 0;
        potentialAttackSquares[29] += localSquares[29 + 10];
        potentialAttackSquares[29] += localSquares[29 - 10];
        potentialAttackSquares[29] += localSquares[29 + 9];
        potentialAttackSquares[29] += localSquares[29 - 9];
        potentialAttackSquares[29] += localSquares[29 + 8];
        potentialAttackSquares[29] += localSquares[29 - 8];
        potentialAttackSquares[29] += localSquares[29 + -1];
        potentialAttackSquares[29] += localSquares[29 ];
        potentialAttackSquares[29] += localSquares[29 + 1];
        potentialAttackSquares[29] += (localSquares[29 - 18]  < 2) ? localSquares[29 - 18] : 0;
        potentialAttackSquares[29] += (localSquares[29 + 18]  < 2) ? localSquares[29 + 18] : 0;
        potentialAttackSquares[29] += (localSquares[29 - 2]  < 2) ? localSquares[29 - 2] : 0;
        potentialAttackSquares[29] += (localSquares[29 + 2]  < 2) ? localSquares[29 + 2] : 0;
        potentialAttackSquares[30] += localSquares[30 + 10];
        potentialAttackSquares[30] += localSquares[30 - 10];
        potentialAttackSquares[30] += localSquares[30 + 9];
        potentialAttackSquares[30] += localSquares[30 - 9];
        potentialAttackSquares[30] += localSquares[30 + 8];
        potentialAttackSquares[30] += localSquares[30 - 8];
        potentialAttackSquares[30] += localSquares[30 + -1];
        potentialAttackSquares[30] += localSquares[30 ];
        potentialAttackSquares[30] += localSquares[30 + 1];
        potentialAttackSquares[30] += (localSquares[30 - 18]  < 2) ? localSquares[30 - 18] : 0;
        potentialAttackSquares[30] += (localSquares[30 + 18]  < 2) ? localSquares[30 + 18] : 0;
        potentialAttackSquares[30] += (localSquares[30 - 2]  < 2) ? localSquares[30 - 2] : 0;
        potentialAttackSquares[30] += (localSquares[30 + 2]  < 2) ? localSquares[30 + 2] : 0;
        potentialAttackSquares[31] += localSquares[31 + 10];
        potentialAttackSquares[31] += localSquares[31 - 10];
        potentialAttackSquares[31] += localSquares[31 + 9];
        potentialAttackSquares[31] += localSquares[31 - 9];
        potentialAttackSquares[31] += localSquares[31 + 8];
        potentialAttackSquares[31] += localSquares[31 - 8];
        potentialAttackSquares[31] += localSquares[31 + -1];
        potentialAttackSquares[31] += localSquares[31 ];
        potentialAttackSquares[31] += localSquares[31 + 1];
        potentialAttackSquares[31] += (localSquares[31 - 18]  < 2) ? localSquares[31 - 18] : 0;
        potentialAttackSquares[31] += (localSquares[31 + 18]  < 2) ? localSquares[31 + 18] : 0;
        potentialAttackSquares[31] += (localSquares[31 - 2]  < 2) ? localSquares[31 - 2] : 0;
        potentialAttackSquares[31] += (localSquares[31 + 2]  < 2) ? localSquares[31 + 2] : 0;
        potentialAttackSquares[32] += localSquares[32 + 10];
        potentialAttackSquares[32] += localSquares[32 - 10];
        potentialAttackSquares[32] += localSquares[32 + 9];
        potentialAttackSquares[32] += localSquares[32 - 9];
        potentialAttackSquares[32] += localSquares[32 + 8];
        potentialAttackSquares[32] += localSquares[32 - 8];
        potentialAttackSquares[32] += localSquares[32 + -1];
        potentialAttackSquares[32] += localSquares[32 ];
        potentialAttackSquares[32] += localSquares[32 + 1];
        potentialAttackSquares[32] += (localSquares[32 - 18]  < 2) ? localSquares[32 - 18] : 0;
        potentialAttackSquares[32] += (localSquares[32 + 18]  < 2) ? localSquares[32 + 18] : 0;
        potentialAttackSquares[32] += (localSquares[32 - 2]  < 2) ? localSquares[32 - 2] : 0;
        potentialAttackSquares[32] += (localSquares[32 + 2]  < 2) ? localSquares[32 + 2] : 0;
        potentialAttackSquares[33] += localSquares[33 + 10];
        potentialAttackSquares[33] += localSquares[33 - 10];
        potentialAttackSquares[33] += localSquares[33 + 9];
        potentialAttackSquares[33] += localSquares[33 - 9];
        potentialAttackSquares[33] += localSquares[33 + 8];
        potentialAttackSquares[33] += localSquares[33 - 8];
        potentialAttackSquares[33] += localSquares[33 + -1];
        potentialAttackSquares[33] += localSquares[33 ];
        potentialAttackSquares[33] += localSquares[33 + 1];
        potentialAttackSquares[33] += (localSquares[33 - 18]  < 2) ? localSquares[33 - 18] : 0;
        potentialAttackSquares[33] += (localSquares[33 + 18]  < 2) ? localSquares[33 + 18] : 0;
        potentialAttackSquares[33] += (localSquares[33 - 2]  < 2) ? localSquares[33 - 2] : 0;
        potentialAttackSquares[33] += (localSquares[33 + 2]  < 2) ? localSquares[33 + 2] : 0;
        potentialAttackSquares[38] += localSquares[38 + 10];
        potentialAttackSquares[38] += localSquares[38 - 10];
        potentialAttackSquares[38] += localSquares[38 + 9];
        potentialAttackSquares[38] += localSquares[38 - 9];
        potentialAttackSquares[38] += localSquares[38 + 8];
        potentialAttackSquares[38] += localSquares[38 - 8];
        potentialAttackSquares[38] += localSquares[38 + -1];
        potentialAttackSquares[38] += localSquares[38 ];
        potentialAttackSquares[38] += localSquares[38 + 1];
        potentialAttackSquares[38] += (localSquares[38 - 18]  < 2) ? localSquares[38 - 18] : 0;
        potentialAttackSquares[38] += (localSquares[38 + 18]  < 2) ? localSquares[38 + 18] : 0;
        potentialAttackSquares[38] += (localSquares[38 - 2]  < 2) ? localSquares[38 - 2] : 0;
        potentialAttackSquares[38] += (localSquares[38 + 2]  < 2) ? localSquares[38 + 2] : 0;
        potentialAttackSquares[39] += localSquares[39 + 10];
        potentialAttackSquares[39] += localSquares[39 - 10];
        potentialAttackSquares[39] += localSquares[39 + 9];
        potentialAttackSquares[39] += localSquares[39 - 9];
        potentialAttackSquares[39] += localSquares[39 + 8];
        potentialAttackSquares[39] += localSquares[39 - 8];
        potentialAttackSquares[39] += localSquares[39 + -1];
        potentialAttackSquares[39] += localSquares[39 ];
        potentialAttackSquares[39] += localSquares[39 + 1];
        potentialAttackSquares[39] += (localSquares[39 - 18]  < 2) ? localSquares[39 - 18] : 0;
        potentialAttackSquares[39] += (localSquares[39 + 18]  < 2) ? localSquares[39 + 18] : 0;
        potentialAttackSquares[39] += (localSquares[39 - 2]  < 2) ? localSquares[39 - 2] : 0;
        potentialAttackSquares[39] += (localSquares[39 + 2]  < 2) ? localSquares[39 + 2] : 0;
        potentialAttackSquares[40] += localSquares[40 + 10];
        potentialAttackSquares[40] += localSquares[40 - 10];
        potentialAttackSquares[40] += localSquares[40 + 9];
        potentialAttackSquares[40] += localSquares[40 - 9];
        potentialAttackSquares[40] += localSquares[40 + 8];
        potentialAttackSquares[40] += localSquares[40 - 8];
        potentialAttackSquares[40] += localSquares[40 + -1];
        potentialAttackSquares[40] += localSquares[40 ];
        potentialAttackSquares[40] += localSquares[40 + 1];
        potentialAttackSquares[40] += (localSquares[40 - 18]  < 2) ? localSquares[40 - 18] : 0;
        potentialAttackSquares[40] += (localSquares[40 + 18]  < 2) ? localSquares[40 + 18] : 0;
        potentialAttackSquares[40] += (localSquares[40 - 2]  < 2) ? localSquares[40 - 2] : 0;
        potentialAttackSquares[40] += (localSquares[40 + 2]  < 2) ? localSquares[40 + 2] : 0;
        potentialAttackSquares[41] += localSquares[41 + 10];
        potentialAttackSquares[41] += localSquares[41 - 10];
        potentialAttackSquares[41] += localSquares[41 + 9];
        potentialAttackSquares[41] += localSquares[41 - 9];
        potentialAttackSquares[41] += localSquares[41 + 8];
        potentialAttackSquares[41] += localSquares[41 - 8];
        potentialAttackSquares[41] += localSquares[41 + -1];
        potentialAttackSquares[41] += localSquares[41 ];
        potentialAttackSquares[41] += localSquares[41 + 1];
        potentialAttackSquares[41] += (localSquares[41 - 18]  < 2) ? localSquares[41 - 18] : 0;
        potentialAttackSquares[41] += (localSquares[41 + 18]  < 2) ? localSquares[41 + 18] : 0;
        potentialAttackSquares[41] += (localSquares[41 - 2]  < 2) ? localSquares[41 - 2] : 0;
        potentialAttackSquares[41] += (localSquares[41 + 2]  < 2) ? localSquares[41 + 2] : 0;
        potentialAttackSquares[42] += localSquares[42 + 10];
        potentialAttackSquares[42] += localSquares[42 - 10];
        potentialAttackSquares[42] += localSquares[42 + 9];
        potentialAttackSquares[42] += localSquares[42 - 9];
        potentialAttackSquares[42] += localSquares[42 + 8];
        potentialAttackSquares[42] += localSquares[42 - 8];
        potentialAttackSquares[42] += localSquares[42 + -1];
        potentialAttackSquares[42] += localSquares[42 ];
        potentialAttackSquares[42] += localSquares[42 + 1];
        potentialAttackSquares[42] += (localSquares[42 - 18]  < 2) ? localSquares[42 - 18] : 0;
        potentialAttackSquares[42] += (localSquares[42 + 18]  < 2) ? localSquares[42 + 18] : 0;
        potentialAttackSquares[42] += (localSquares[42 - 2]  < 2) ? localSquares[42 - 2] : 0;
        potentialAttackSquares[42] += (localSquares[42 + 2]  < 2) ? localSquares[42 + 2] : 0;
        potentialAttackSquares[47] += localSquares[47 + 10];
        potentialAttackSquares[47] += localSquares[47 - 10];
        potentialAttackSquares[47] += localSquares[47 + 9];
        potentialAttackSquares[47] += localSquares[47 - 9];
        potentialAttackSquares[47] += localSquares[47 + 8];
        potentialAttackSquares[47] += localSquares[47 - 8];
        potentialAttackSquares[47] += localSquares[47 + -1];
        potentialAttackSquares[47] += localSquares[47 ];
        potentialAttackSquares[47] += localSquares[47 + 1];
        potentialAttackSquares[47] += (localSquares[47 - 18]  < 2) ? localSquares[47 - 18] : 0;
        potentialAttackSquares[47] += (localSquares[47 + 18]  < 2) ? localSquares[47 + 18] : 0;
        potentialAttackSquares[47] += (localSquares[47 - 2]  < 2) ? localSquares[47 - 2] : 0;
        potentialAttackSquares[47] += (localSquares[47 + 2]  < 2) ? localSquares[47 + 2] : 0;
        potentialAttackSquares[48] += localSquares[48 + 10];
        potentialAttackSquares[48] += localSquares[48 - 10];
        potentialAttackSquares[48] += localSquares[48 + 9];
        potentialAttackSquares[48] += localSquares[48 - 9];
        potentialAttackSquares[48] += localSquares[48 + 8];
        potentialAttackSquares[48] += localSquares[48 - 8];
        potentialAttackSquares[48] += localSquares[48 + -1];
        potentialAttackSquares[48] += localSquares[48 ];
        potentialAttackSquares[48] += localSquares[48 + 1];
        potentialAttackSquares[48] += (localSquares[48 - 18]  < 2) ? localSquares[48 - 18] : 0;
        potentialAttackSquares[48] += (localSquares[48 + 18]  < 2) ? localSquares[48 + 18] : 0;
        potentialAttackSquares[48] += (localSquares[48 - 2]  < 2) ? localSquares[48 - 2] : 0;
        potentialAttackSquares[48] += (localSquares[48 + 2]  < 2) ? localSquares[48 + 2] : 0;
        potentialAttackSquares[49] += localSquares[49 + 10];
        potentialAttackSquares[49] += localSquares[49 - 10];
        potentialAttackSquares[49] += localSquares[49 + 9];
        potentialAttackSquares[49] += localSquares[49 - 9];
        potentialAttackSquares[49] += localSquares[49 + 8];
        potentialAttackSquares[49] += localSquares[49 - 8];
        potentialAttackSquares[49] += localSquares[49 + -1];
        potentialAttackSquares[49] += localSquares[49 ];
        potentialAttackSquares[49] += localSquares[49 + 1];
        potentialAttackSquares[49] += (localSquares[49 - 18]  < 2) ? localSquares[49 - 18] : 0;
        potentialAttackSquares[49] += (localSquares[49 + 18]  < 2) ? localSquares[49 + 18] : 0;
        potentialAttackSquares[49] += (localSquares[49 - 2]  < 2) ? localSquares[49 - 2] : 0;
        potentialAttackSquares[49] += (localSquares[49 + 2]  < 2) ? localSquares[49 + 2] : 0;
        potentialAttackSquares[50] += localSquares[50 + 10];
        potentialAttackSquares[50] += localSquares[50 - 10];
        potentialAttackSquares[50] += localSquares[50 + 9];
        potentialAttackSquares[50] += localSquares[50 - 9];
        potentialAttackSquares[50] += localSquares[50 + 8];
        potentialAttackSquares[50] += localSquares[50 - 8];
        potentialAttackSquares[50] += localSquares[50 + -1];
        potentialAttackSquares[50] += localSquares[50 ];
        potentialAttackSquares[50] += localSquares[50 + 1];
        potentialAttackSquares[50] += (localSquares[50 - 18]  < 2) ? localSquares[50 - 18] : 0;
        potentialAttackSquares[50] += (localSquares[50 + 18]  < 2) ? localSquares[50 + 18] : 0;
        potentialAttackSquares[50] += (localSquares[50 - 2]  < 2) ? localSquares[50 - 2] : 0;
        potentialAttackSquares[50] += (localSquares[50 + 2]  < 2) ? localSquares[50 + 2] : 0;
        potentialAttackSquares[51] += localSquares[51 + 10];
        potentialAttackSquares[51] += localSquares[51 - 10];
        potentialAttackSquares[51] += localSquares[51 + 9];
        potentialAttackSquares[51] += localSquares[51 - 9];
        potentialAttackSquares[51] += localSquares[51 + 8];
        potentialAttackSquares[51] += localSquares[51 - 8];
        potentialAttackSquares[51] += localSquares[51 + -1];
        potentialAttackSquares[51] += localSquares[51 ];
        potentialAttackSquares[51] += localSquares[51 + 1];
        potentialAttackSquares[51] += (localSquares[51 - 18]  < 2) ? localSquares[51 - 18] : 0;
        potentialAttackSquares[51] += (localSquares[51 + 18]  < 2) ? localSquares[51 + 18] : 0;
        potentialAttackSquares[51] += (localSquares[51 - 2]  < 2) ? localSquares[51 - 2] : 0;
        potentialAttackSquares[51] += (localSquares[51 + 2]  < 2) ? localSquares[51 + 2] : 0;
        potentialAttackSquares[56] += localSquares[56 + 10];
        potentialAttackSquares[56] += localSquares[56 - 10];
        potentialAttackSquares[56] += localSquares[56 + 9];
        potentialAttackSquares[56] += localSquares[56 - 9];
        potentialAttackSquares[56] += localSquares[56 + 8];
        potentialAttackSquares[56] += localSquares[56 - 8];
        potentialAttackSquares[56] += localSquares[56 + -1];
        potentialAttackSquares[56] += localSquares[56 ];
        potentialAttackSquares[56] += localSquares[56 + 1];
        potentialAttackSquares[56] += (localSquares[56 - 18]  < 2) ? localSquares[56 - 18] : 0;
        potentialAttackSquares[56] += (localSquares[56 + 18]  < 2) ? localSquares[56 + 18] : 0;
        potentialAttackSquares[56] += (localSquares[56 - 2]  < 2) ? localSquares[56 - 2] : 0;
        potentialAttackSquares[56] += (localSquares[56 + 2]  < 2) ? localSquares[56 + 2] : 0;
        potentialAttackSquares[57] += localSquares[57 + 10];
        potentialAttackSquares[57] += localSquares[57 - 10];
        potentialAttackSquares[57] += localSquares[57 + 9];
        potentialAttackSquares[57] += localSquares[57 - 9];
        potentialAttackSquares[57] += localSquares[57 + 8];
        potentialAttackSquares[57] += localSquares[57 - 8];
        potentialAttackSquares[57] += localSquares[57 + -1];
        potentialAttackSquares[57] += localSquares[57 ];
        potentialAttackSquares[57] += localSquares[57 + 1];
        potentialAttackSquares[57] += (localSquares[57 - 18]  < 2) ? localSquares[57 - 18] : 0;
        potentialAttackSquares[57] += (localSquares[57 + 18]  < 2) ? localSquares[57 + 18] : 0;
        potentialAttackSquares[57] += (localSquares[57 - 2]  < 2) ? localSquares[57 - 2] : 0;
        potentialAttackSquares[57] += (localSquares[57 + 2]  < 2) ? localSquares[57 + 2] : 0;
        potentialAttackSquares[58] += localSquares[58 + 10];
        potentialAttackSquares[58] += localSquares[58 - 10];
        potentialAttackSquares[58] += localSquares[58 + 9];
        potentialAttackSquares[58] += localSquares[58 - 9];
        potentialAttackSquares[58] += localSquares[58 + 8];
        potentialAttackSquares[58] += localSquares[58 - 8];
        potentialAttackSquares[58] += localSquares[58 + -1];
        potentialAttackSquares[58] += localSquares[58 ];
        potentialAttackSquares[58] += localSquares[58 + 1];
        potentialAttackSquares[58] += (localSquares[58 - 18]  < 2) ? localSquares[58 - 18] : 0;
        potentialAttackSquares[58] += (localSquares[58 + 18]  < 2) ? localSquares[58 + 18] : 0;
        potentialAttackSquares[58] += (localSquares[58 - 2]  < 2) ? localSquares[58 - 2] : 0;
        potentialAttackSquares[58] += (localSquares[58 + 2]  < 2) ? localSquares[58 + 2] : 0;
        potentialAttackSquares[59] += localSquares[59 + 10];
        potentialAttackSquares[59] += localSquares[59 - 10];
        potentialAttackSquares[59] += localSquares[59 + 9];
        potentialAttackSquares[59] += localSquares[59 - 9];
        potentialAttackSquares[59] += localSquares[59 + 8];
        potentialAttackSquares[59] += localSquares[59 - 8];
        potentialAttackSquares[59] += localSquares[59 + -1];
        potentialAttackSquares[59] += localSquares[59 ];
        potentialAttackSquares[59] += localSquares[59 + 1];
        potentialAttackSquares[59] += (localSquares[59 - 18]  < 2) ? localSquares[59 - 18] : 0;
        potentialAttackSquares[59] += (localSquares[59 + 18]  < 2) ? localSquares[59 + 18] : 0;
        potentialAttackSquares[59] += (localSquares[59 - 2]  < 2) ? localSquares[59 - 2] : 0;
        potentialAttackSquares[59] += (localSquares[59 + 2]  < 2) ? localSquares[59 + 2] : 0;
        potentialAttackSquares[60] += localSquares[60 + 10];
        potentialAttackSquares[60] += localSquares[60 - 10];
        potentialAttackSquares[60] += localSquares[60 + 9];
        potentialAttackSquares[60] += localSquares[60 - 9];
        potentialAttackSquares[60] += localSquares[60 + 8];
        potentialAttackSquares[60] += localSquares[60 - 8];
        potentialAttackSquares[60] += localSquares[60 + -1];
        potentialAttackSquares[60] += localSquares[60 ];
        potentialAttackSquares[60] += localSquares[60 + 1];
        potentialAttackSquares[60] += (localSquares[60 - 18]  < 2) ? localSquares[60 - 18] : 0;
        potentialAttackSquares[60] += (localSquares[60 + 18]  < 2) ? localSquares[60 + 18] : 0;
        potentialAttackSquares[60] += (localSquares[60 - 2]  < 2) ? localSquares[60 - 2] : 0;
        potentialAttackSquares[60] += (localSquares[60 + 2]  < 2) ? localSquares[60 + 2] : 0;
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
        return new MapLocation(rc.getLocation().x + offSetX, rc.getLocation().y + offSetY);
    }

    //returns the best attack adjacent to the robot - cheaper, and allows the robot to go closer to the edge
    public static MapLocation cheapBestAttack(boolean fightingTower, int minScore) throws GameActionException {
        if(!farFromEdge( rc.getLocation())) return basicBestAttack();
        //int price = Clock.getBytecodesLeft();
        //keeps track of total potential points, so we can short circuit and save bytecode if possible
        int totalPoints = 0;
        int[] localSquares = new int[45];
        int[] potentialAttackSquares = new int[45];
        int index = 0;
        for(MapInfo tile : rc.senseNearbyMapInfos(13)) {
            if(index == 0 || index == 4 || index == 5 || index == 11 || index == 33 || index == 39 || index == 40 || index == 44) {
                index++;
                continue;
            }
            PaintType paint = tile.getPaint();
//            if(tile.hasRuin()) {
//                if(fightingTower && rc.senseRobotAtLocation(tile.getMapLocation()) != null && rc.senseRobotAtLocation(tile.getMapLocation()) != null && rc.senseRobotAtLocation(tile.getMapLocation()).team == rc.getTeam().opponent()) {
//                    paint = PaintType.ENEMY_PRIMARY;
//                }
//                else {
//                    paint = PaintType.ALLY_PRIMARY;
//                }
//            }
//            else if(tile.isWall()) {
//                paint = PaintType.ALLY_PRIMARY;
//            }
            //favor the enemy most, but also like empty squares
            if(tile.hasRuin()) {
                if(seenEnemyTower != null && seenEnemyTower.getLocation().equals(tile.getMapLocation())) {
                    localSquares[index] += 3;
                    totalPoints += 3;
                }
            }
            else if(paint.isEnemy()){
                if(nearestUnfilledRuin != null && tile.getMapLocation().isWithinDistanceSquared(nearestUnfilledRuin, 8)) {
                    localSquares[index] += 3;
                    totalPoints+= 3;
                }
                else {
                    localSquares[index] += 2;
                    totalPoints += 2;
                }
            }
            else if(!paint.isAlly() && tile.isPassable()) {
                localSquares[index]++;
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
        potentialAttackSquares[14] += (localSquares[14 - 13]  < 2) ? localSquares[14 - 13] : 0;
        potentialAttackSquares[14] += (localSquares[14 + 14]  < 2) ? localSquares[14 + 14] : 0;
        potentialAttackSquares[14] += (localSquares[14 - 2]  < 2) ? localSquares[14 - 2] : 0;
        potentialAttackSquares[14] += (localSquares[14 + 2]  < 2) ? localSquares[14 + 2] : 0;

        potentialAttackSquares[15] += localSquares[15 + 8];
        potentialAttackSquares[15] += localSquares[15 - 8];
        potentialAttackSquares[15] += localSquares[15 + 7];
        potentialAttackSquares[15] += localSquares[15 - 7];
        potentialAttackSquares[15] += localSquares[15 + 6];
        potentialAttackSquares[15] += localSquares[15 - 6];
        potentialAttackSquares[15] += localSquares[15 + -1];
        potentialAttackSquares[15] += localSquares[15 ];
        potentialAttackSquares[15] += localSquares[15 + 1];
        potentialAttackSquares[15] += (localSquares[15 - 13]  < 2) ? localSquares[15 - 13] : 0;
        potentialAttackSquares[15] += (localSquares[15 + 14]  < 2) ? localSquares[15 + 14] : 0;
        potentialAttackSquares[15] += (localSquares[15 - 2]  < 2) ? localSquares[15 - 2] : 0;
        potentialAttackSquares[15] += (localSquares[15 + 2]  < 2) ? localSquares[15 + 2] : 0;

        potentialAttackSquares[16] += localSquares[16 + 8];
        potentialAttackSquares[16] += localSquares[16 - 8];
        potentialAttackSquares[16] += localSquares[16 + 7];
        potentialAttackSquares[16] += localSquares[16 - 7];
        potentialAttackSquares[16] += localSquares[16 + 6];
        potentialAttackSquares[16] += localSquares[16 - 6];
        potentialAttackSquares[16] += localSquares[16 + -1];
        potentialAttackSquares[16] += localSquares[16 ];
        potentialAttackSquares[16] += localSquares[16 + 1];
        potentialAttackSquares[16] += (localSquares[16 - 13]  < 2) ? localSquares[16 - 13] : 0;
        potentialAttackSquares[16] += (localSquares[16 + 14]  < 2) ? localSquares[16 + 14] : 0;
        potentialAttackSquares[16] += (localSquares[16 - 2]  < 2) ? localSquares[16 - 2] : 0;
        potentialAttackSquares[16] += (localSquares[16 + 2]  < 2) ? localSquares[16 + 2] : 0;

        potentialAttackSquares[21] += localSquares[21 + 8];
        potentialAttackSquares[21] += localSquares[21 - 8];
        potentialAttackSquares[21] += localSquares[21 + 7];
        potentialAttackSquares[21] += localSquares[21 - 7];
        potentialAttackSquares[21] += localSquares[21 + 6];
        potentialAttackSquares[21] += localSquares[21 - 6];
        potentialAttackSquares[21] += localSquares[21 + -1];
        potentialAttackSquares[21] += localSquares[21 ];
        potentialAttackSquares[21] += localSquares[21 + 1];
        potentialAttackSquares[21] += (localSquares[21 - 14]  < 2) ? localSquares[21 - 14] : 0;
        potentialAttackSquares[21] += (localSquares[21 + 14]  < 2) ? localSquares[21 + 14] : 0;
        potentialAttackSquares[21] += (localSquares[21 - 2]  < 2) ? localSquares[21 - 2] : 0;
        potentialAttackSquares[21] += (localSquares[21 + 2]  < 2) ? localSquares[21 + 2] : 0;
        potentialAttackSquares[22] += localSquares[22 + 8];
        potentialAttackSquares[22] += localSquares[22 - 8];
        potentialAttackSquares[22] += localSquares[22 + 7];
        potentialAttackSquares[22] += localSquares[22 - 7];
        potentialAttackSquares[22] += localSquares[22 + 6];
        potentialAttackSquares[22] += localSquares[22 - 6];
        potentialAttackSquares[22] += localSquares[22 + -1];
        potentialAttackSquares[22] += localSquares[22 ];
        potentialAttackSquares[22] += localSquares[22 + 1];
        potentialAttackSquares[22] += (localSquares[22 - 14]  < 2) ? localSquares[22 - 14] : 0;
        potentialAttackSquares[22] += (localSquares[22 + 14]  < 2) ? localSquares[22 + 14] : 0;
        potentialAttackSquares[22] += (localSquares[22 - 2]  < 2) ? localSquares[22 - 2] : 0;
        potentialAttackSquares[22] += (localSquares[22 + 2]  < 2) ? localSquares[22 + 2] : 0;
        potentialAttackSquares[23] += localSquares[23 + 8];
        potentialAttackSquares[23] += localSquares[23 - 8];
        potentialAttackSquares[23] += localSquares[23 + 7];
        potentialAttackSquares[23] += localSquares[23 - 7];
        potentialAttackSquares[23] += localSquares[23 + 6];
        potentialAttackSquares[23] += localSquares[23 - 6];
        potentialAttackSquares[23] += localSquares[23 + -1];
        potentialAttackSquares[23] += localSquares[23 ];
        potentialAttackSquares[23] += localSquares[23 + 1];
        potentialAttackSquares[23] += (localSquares[23 - 14]  < 2) ? localSquares[23 - 14] : 0;
        potentialAttackSquares[23] += (localSquares[23 + 14]  < 2) ? localSquares[23 + 14] : 0;
        potentialAttackSquares[23] += (localSquares[23 - 2]  < 2) ? localSquares[23 - 2] : 0;
        potentialAttackSquares[23] += (localSquares[23 + 2]  < 2) ? localSquares[23 + 2] : 0;
        potentialAttackSquares[28] += localSquares[28 + 8];
        potentialAttackSquares[28] += localSquares[28 - 8];
        potentialAttackSquares[28] += localSquares[28 + 7];
        potentialAttackSquares[28] += localSquares[28 - 7];
        potentialAttackSquares[28] += localSquares[28 + 6];
        potentialAttackSquares[28] += localSquares[28 - 6];
        potentialAttackSquares[28] += localSquares[28 + -1];
        potentialAttackSquares[28] += localSquares[28 ];
        potentialAttackSquares[28] += localSquares[28 + 1];
        potentialAttackSquares[28] += (localSquares[28 - 14]  < 2) ? localSquares[28 - 14] : 0;
        potentialAttackSquares[28] += (localSquares[28 + 13]  < 2) ? localSquares[28 + 13] : 0;
        potentialAttackSquares[28] += (localSquares[28 - 2]  < 2) ? localSquares[28 - 2] : 0;
        potentialAttackSquares[28] += (localSquares[28 + 2]  < 2) ? localSquares[28 + 2] : 0;
        potentialAttackSquares[29] += localSquares[29 + 8];
        potentialAttackSquares[29] += localSquares[29 - 8];
        potentialAttackSquares[29] += localSquares[29 + 7];
        potentialAttackSquares[29] += localSquares[29 - 7];
        potentialAttackSquares[29] += localSquares[29 + 6];
        potentialAttackSquares[29] += localSquares[29 - 6];
        potentialAttackSquares[29] += localSquares[29 + -1];
        potentialAttackSquares[29] += localSquares[29 ];
        potentialAttackSquares[29] += localSquares[29 + 1];
        potentialAttackSquares[29] += (localSquares[29 - 14]  < 2) ? localSquares[29 - 14] : 0;
        potentialAttackSquares[29] += (localSquares[29 + 13]  < 2) ? localSquares[29 + 13] : 0;
        potentialAttackSquares[29] += (localSquares[29 - 2]  < 2) ? localSquares[29 - 2] : 0;
        potentialAttackSquares[29] += (localSquares[29 + 2]  < 2) ? localSquares[29 + 2] : 0;
        potentialAttackSquares[30] += localSquares[30 + 8];
        potentialAttackSquares[30] += localSquares[30 - 8];
        potentialAttackSquares[30] += localSquares[30 + 7];
        potentialAttackSquares[30] += localSquares[30 - 7];
        potentialAttackSquares[30] += localSquares[30 + 6];
        potentialAttackSquares[30] += localSquares[30 - 6];
        potentialAttackSquares[30] += localSquares[30 + -1];
        potentialAttackSquares[30] += localSquares[30 ];
        potentialAttackSquares[30] += localSquares[30 + 1];
        potentialAttackSquares[30] += (localSquares[30 - 14]  < 2) ? localSquares[30 - 14] : 0;
        potentialAttackSquares[30] += (localSquares[30 + 13]  < 2) ? localSquares[30 + 13] : 0;
        potentialAttackSquares[30] += (localSquares[30 - 2]  < 2) ? localSquares[30 - 2] : 0;
        potentialAttackSquares[30] += (localSquares[30 + 2]  < 2) ? localSquares[30 + 2] : 0;
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
        return new MapLocation(rc.getLocation().x + offSetX, rc.getLocation().y + offSetY);
    }
    //checks whether we should still "claim" this ruin
    //if there is an obstacle stopping us from completing it, then we should abandon t
    public static boolean needsClearing() throws GameActionException {
        if(rc.canSenseRobotAtLocation(nearestUnfilledRuin)) {
            return false;
        }
        if (rc.getLocation().distanceSquaredTo(nearestUnfilledRuin) > 8 && !checkedRuin.contains(nearestUnfilledRuin)) {
            return true;
        }
        switch (tilesNearRuin.length) {
            case 1 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 2 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    return true;
                }
            }
            case 3 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 4 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 5 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 6 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 7 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 8 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 9 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 10 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 11 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 12 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 13 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 14 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 15 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 16 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 17 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 18 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[17].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 19 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[17].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[18].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 20 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[17].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[18].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[19].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 21 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[17].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[18].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[19].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[20].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 22 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[17].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[18].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[19].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[20].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[21].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 23 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[17].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[18].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[19].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[20].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[21].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[22].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 24 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[17].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[18].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[19].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[20].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[21].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[22].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[23].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
            case 25 -> {
                if (tilesNearRuin[0].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[1].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[2].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[3].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[4].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[5].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[6].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[7].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[8].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[9].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[10].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[11].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[12].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[13].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[14].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[15].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[16].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[17].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[18].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[19].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[20].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[21].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[22].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[23].getPaint().isEnemy()) {
                    
                    return true;
                }
                if (tilesNearRuin[24].getPaint().isEnemy()) {
                    
                    return true;
                }
            }
        }
        return false;
    }
}
