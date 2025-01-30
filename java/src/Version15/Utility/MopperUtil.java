package Version15.Utility;

import battlecode.common.*;

import static Version15.Robots.Mopper.*;
import static Version15.RobotPlayer.*;

public class MopperUtil {

    public static void checkClearing(int index) {
        if (!needsClearing && nearbyRuin != null && nearbyTiles[index].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8)) {
            needsClearing = true;
        }
    }

    public static void scanNearbyTiles() throws GameActionException {
        int x = 0;
        int y = 0;
        int count = 0;
        FastIterableLocSet behindWall = WallChecker_FullVision.findOverWallTiles(staticRC);
        MapLocation tileLoc;
        switch (nearbyTiles.length) {
            case 57 -> {
                //Symmetry.processTile(nearbyTiles[0]);
                tileLoc = nearbyTiles[0].getMapLocation();
                if (nearbyTiles[0].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[1]);
                tileLoc = nearbyTiles[1].getMapLocation();
                if (nearbyTiles[1].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[2]);
                tileLoc = nearbyTiles[2].getMapLocation();
                if (nearbyTiles[2].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[3]);
                tileLoc = nearbyTiles[3].getMapLocation();
                if (nearbyTiles[3].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[4]);
                tileLoc = nearbyTiles[4].getMapLocation();
                if (nearbyTiles[4].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[5]);
                tileLoc = nearbyTiles[5].getMapLocation();
                if (nearbyTiles[5].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[6]);
                tileLoc = nearbyTiles[6].getMapLocation();
                if (nearbyTiles[6].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[7]);
                tileLoc = nearbyTiles[7].getMapLocation();
                if (nearbyTiles[7].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[8]);
                tileLoc = nearbyTiles[8].getMapLocation();
                if (nearbyTiles[8].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[9]);
                tileLoc = nearbyTiles[9].getMapLocation();
                if (nearbyTiles[9].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[10]);
                tileLoc = nearbyTiles[10].getMapLocation();
                if (nearbyTiles[10].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[11]);
                tileLoc = nearbyTiles[11].getMapLocation();
                if (nearbyTiles[11].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[12]);
                tileLoc = nearbyTiles[12].getMapLocation();
                if (nearbyTiles[12].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[13]);
                tileLoc = nearbyTiles[13].getMapLocation();
                if (nearbyTiles[13].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[14]);
                tileLoc = nearbyTiles[14].getMapLocation();
                if (nearbyTiles[14].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[15]);
                tileLoc = nearbyTiles[15].getMapLocation();
                if (nearbyTiles[15].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[16]);
                tileLoc = nearbyTiles[16].getMapLocation();
                if (nearbyTiles[16].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[17]);
                tileLoc = nearbyTiles[17].getMapLocation();
                if (nearbyTiles[17].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[18]);
                tileLoc = nearbyTiles[18].getMapLocation();
                if (nearbyTiles[18].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[19]);
                tileLoc = nearbyTiles[19].getMapLocation();
                if (nearbyTiles[19].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[20]);
                tileLoc = nearbyTiles[20].getMapLocation();
                if (nearbyTiles[20].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[21]);
                tileLoc = nearbyTiles[21].getMapLocation();
                if (nearbyTiles[21].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[22]);
                tileLoc = nearbyTiles[22].getMapLocation();
                if (nearbyTiles[22].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[23]);
                tileLoc = nearbyTiles[23].getMapLocation();
                if (nearbyTiles[23].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[24]);
                tileLoc = nearbyTiles[24].getMapLocation();
                if (nearbyTiles[24].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[25]);
                tileLoc = nearbyTiles[25].getMapLocation();
                if (nearbyTiles[25].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[26]);
                tileLoc = nearbyTiles[26].getMapLocation();
                if (nearbyTiles[26].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[27]);
                tileLoc = nearbyTiles[27].getMapLocation();
                if (nearbyTiles[27].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[28]);
                tileLoc = nearbyTiles[28].getMapLocation();
                if (nearbyTiles[28].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[29]);
                tileLoc = nearbyTiles[29].getMapLocation();
                if (nearbyTiles[29].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[30]);
                tileLoc = nearbyTiles[30].getMapLocation();
                if (nearbyTiles[30].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[31]);
                tileLoc = nearbyTiles[31].getMapLocation();
                if (nearbyTiles[31].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[32]);
                tileLoc = nearbyTiles[32].getMapLocation();
                if (nearbyTiles[32].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[33]);
                tileLoc = nearbyTiles[33].getMapLocation();
                if (nearbyTiles[33].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[34]);
                tileLoc = nearbyTiles[34].getMapLocation();
                if (nearbyTiles[34].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[35]);
                tileLoc = nearbyTiles[35].getMapLocation();
                if (nearbyTiles[35].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[36]);
                tileLoc = nearbyTiles[36].getMapLocation();
                if (nearbyTiles[36].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[37]);
                tileLoc = nearbyTiles[37].getMapLocation();
                if (nearbyTiles[37].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[38]);
                tileLoc = nearbyTiles[38].getMapLocation();
                if (nearbyTiles[38].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[39]);
                tileLoc = nearbyTiles[39].getMapLocation();
                if (nearbyTiles[39].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[40]);
                tileLoc = nearbyTiles[40].getMapLocation();
                if (nearbyTiles[40].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[41]);
                tileLoc = nearbyTiles[41].getMapLocation();
                if (nearbyTiles[41].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[42]);
                tileLoc = nearbyTiles[42].getMapLocation();
                if (nearbyTiles[42].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[43]);
                tileLoc = nearbyTiles[43].getMapLocation();
                if (nearbyTiles[43].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[44]);
                tileLoc = nearbyTiles[44].getMapLocation();
                if (nearbyTiles[44].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[45]);
                tileLoc = nearbyTiles[45].getMapLocation();
                if (nearbyTiles[45].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[46]);
                tileLoc = nearbyTiles[46].getMapLocation();
                if (nearbyTiles[46].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[47]);
                tileLoc = nearbyTiles[47].getMapLocation();
                if (nearbyTiles[47].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[48]);
                tileLoc = nearbyTiles[48].getMapLocation();
                if (nearbyTiles[48].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[49]);
                tileLoc = nearbyTiles[49].getMapLocation();
                if (nearbyTiles[49].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[50]);
                tileLoc = nearbyTiles[50].getMapLocation();
                if (nearbyTiles[50].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[51]);
                tileLoc = nearbyTiles[51].getMapLocation();
                if (nearbyTiles[51].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[52]);
                tileLoc = nearbyTiles[52].getMapLocation();
                if (nearbyTiles[52].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[53]);
                tileLoc = nearbyTiles[53].getMapLocation();
                if (nearbyTiles[53].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[54]);
                tileLoc = nearbyTiles[54].getMapLocation();
                if (nearbyTiles[54].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[55]);
                tileLoc = nearbyTiles[55].getMapLocation();
                if (nearbyTiles[55].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[56]);
                tileLoc = nearbyTiles[56].getMapLocation();
                if (nearbyTiles[56].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
            }
            case 64 -> {
                //Symmetry.processTile(nearbyTiles[0]);
                tileLoc = nearbyTiles[0].getMapLocation();
                if (nearbyTiles[0].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[1]);
                tileLoc = nearbyTiles[1].getMapLocation();
                if (nearbyTiles[1].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[2]);
                tileLoc = nearbyTiles[2].getMapLocation();
                if (nearbyTiles[2].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[3]);
                tileLoc = nearbyTiles[3].getMapLocation();
                if (nearbyTiles[3].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[4]);
                tileLoc = nearbyTiles[4].getMapLocation();
                if (nearbyTiles[4].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[5]);
                tileLoc = nearbyTiles[5].getMapLocation();
                if (nearbyTiles[5].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[6]);
                tileLoc = nearbyTiles[6].getMapLocation();
                if (nearbyTiles[6].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[7]);
                tileLoc = nearbyTiles[7].getMapLocation();
                if (nearbyTiles[7].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[8]);
                tileLoc = nearbyTiles[8].getMapLocation();
                if (nearbyTiles[8].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[9]);
                tileLoc = nearbyTiles[9].getMapLocation();
                if (nearbyTiles[9].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[10]);
                tileLoc = nearbyTiles[10].getMapLocation();
                if (nearbyTiles[10].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[11]);
                tileLoc = nearbyTiles[11].getMapLocation();
                if (nearbyTiles[11].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[12]);
                tileLoc = nearbyTiles[12].getMapLocation();
                if (nearbyTiles[12].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[13]);
                tileLoc = nearbyTiles[13].getMapLocation();
                if (nearbyTiles[13].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[14]);
                tileLoc = nearbyTiles[14].getMapLocation();
                if (nearbyTiles[14].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[15]);
                tileLoc = nearbyTiles[15].getMapLocation();
                if (nearbyTiles[15].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[16]);
                tileLoc = nearbyTiles[16].getMapLocation();
                if (nearbyTiles[16].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[17]);
                tileLoc = nearbyTiles[17].getMapLocation();
                if (nearbyTiles[17].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[18]);
                tileLoc = nearbyTiles[18].getMapLocation();
                if (nearbyTiles[18].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[19]);
                tileLoc = nearbyTiles[19].getMapLocation();
                if (nearbyTiles[19].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[20]);
                tileLoc = nearbyTiles[20].getMapLocation();
                if (nearbyTiles[20].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[21]);
                tileLoc = nearbyTiles[21].getMapLocation();
                if (nearbyTiles[21].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[22]);
                tileLoc = nearbyTiles[22].getMapLocation();
                if (nearbyTiles[22].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[23]);
                tileLoc = nearbyTiles[23].getMapLocation();
                if (nearbyTiles[23].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[24]);
                tileLoc = nearbyTiles[24].getMapLocation();
                if (nearbyTiles[24].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[25]);
                tileLoc = nearbyTiles[25].getMapLocation();
                if (nearbyTiles[25].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[26]);
                tileLoc = nearbyTiles[26].getMapLocation();
                if (nearbyTiles[26].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[27]);
                tileLoc = nearbyTiles[27].getMapLocation();
                if (nearbyTiles[27].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[28]);
                tileLoc = nearbyTiles[28].getMapLocation();
                if (nearbyTiles[28].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[29]);
                tileLoc = nearbyTiles[29].getMapLocation();
                if (nearbyTiles[29].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[30]);
                tileLoc = nearbyTiles[30].getMapLocation();
                if (nearbyTiles[30].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[31]);
                tileLoc = nearbyTiles[31].getMapLocation();
                if (nearbyTiles[31].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[32]);
                tileLoc = nearbyTiles[32].getMapLocation();
                if (nearbyTiles[32].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[33]);
                tileLoc = nearbyTiles[33].getMapLocation();
                if (nearbyTiles[33].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[34]);
                tileLoc = nearbyTiles[34].getMapLocation();
                if (nearbyTiles[34].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[35]);
                tileLoc = nearbyTiles[35].getMapLocation();
                if (nearbyTiles[35].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[36]);
                tileLoc = nearbyTiles[36].getMapLocation();
                if (nearbyTiles[36].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[37]);
                tileLoc = nearbyTiles[37].getMapLocation();
                if (nearbyTiles[37].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[38]);
                tileLoc = nearbyTiles[38].getMapLocation();
                if (nearbyTiles[38].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[39]);
                tileLoc = nearbyTiles[39].getMapLocation();
                if (nearbyTiles[39].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[40]);
                tileLoc = nearbyTiles[40].getMapLocation();
                if (nearbyTiles[40].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[41]);
                tileLoc = nearbyTiles[41].getMapLocation();
                if (nearbyTiles[41].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[42]);
                tileLoc = nearbyTiles[42].getMapLocation();
                if (nearbyTiles[42].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[43]);
                tileLoc = nearbyTiles[43].getMapLocation();
                if (nearbyTiles[43].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[44]);
                tileLoc = nearbyTiles[44].getMapLocation();
                if (nearbyTiles[44].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[45]);
                tileLoc = nearbyTiles[45].getMapLocation();
                if (nearbyTiles[45].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[46]);
                tileLoc = nearbyTiles[46].getMapLocation();
                if (nearbyTiles[46].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[47]);
                tileLoc = nearbyTiles[47].getMapLocation();
                if (nearbyTiles[47].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[48]);
                tileLoc = nearbyTiles[48].getMapLocation();
                if (nearbyTiles[48].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[49]);
                tileLoc = nearbyTiles[49].getMapLocation();
                if (nearbyTiles[49].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[50]);
                tileLoc = nearbyTiles[50].getMapLocation();
                if (nearbyTiles[50].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[51]);
                tileLoc = nearbyTiles[51].getMapLocation();
                if (nearbyTiles[51].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[52]);
                tileLoc = nearbyTiles[52].getMapLocation();
                if (nearbyTiles[52].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[53]);
                tileLoc = nearbyTiles[53].getMapLocation();
                if (nearbyTiles[53].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[54]);
                tileLoc = nearbyTiles[54].getMapLocation();
                if (nearbyTiles[54].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[55]);
                tileLoc = nearbyTiles[55].getMapLocation();
                if (nearbyTiles[55].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[56]);
                tileLoc = nearbyTiles[56].getMapLocation();
                if (nearbyTiles[56].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[57]);
                tileLoc = nearbyTiles[57].getMapLocation();
                if (nearbyTiles[57].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[58]);
                tileLoc = nearbyTiles[58].getMapLocation();
                if (nearbyTiles[58].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[59]);
                tileLoc = nearbyTiles[59].getMapLocation();
                if (nearbyTiles[59].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[60]);
                tileLoc = nearbyTiles[60].getMapLocation();
                if (nearbyTiles[60].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[61]);
                tileLoc = nearbyTiles[61].getMapLocation();
                if (nearbyTiles[61].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[62]);
                tileLoc = nearbyTiles[62].getMapLocation();
                if (nearbyTiles[62].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[63]);
                tileLoc = nearbyTiles[63].getMapLocation();
                if (nearbyTiles[63].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
            }
            case 69 -> {
                //Symmetry.processTile(nearbyTiles[0]);
                tileLoc = nearbyTiles[0].getMapLocation();
                if (nearbyTiles[0].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[1]);
                tileLoc = nearbyTiles[1].getMapLocation();
                if (nearbyTiles[1].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[2]);
                tileLoc = nearbyTiles[2].getMapLocation();
                if (nearbyTiles[2].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[3]);
                tileLoc = nearbyTiles[3].getMapLocation();
                if (nearbyTiles[3].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[4]);
                tileLoc = nearbyTiles[4].getMapLocation();
                if (nearbyTiles[4].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[5]);
                tileLoc = nearbyTiles[5].getMapLocation();
                if (nearbyTiles[5].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[6]);
                tileLoc = nearbyTiles[6].getMapLocation();
                if (nearbyTiles[6].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[7]);
                tileLoc = nearbyTiles[7].getMapLocation();
                if (nearbyTiles[7].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[8]);
                tileLoc = nearbyTiles[8].getMapLocation();
                if (nearbyTiles[8].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[9]);
                tileLoc = nearbyTiles[9].getMapLocation();
                if (nearbyTiles[9].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[10]);
                tileLoc = nearbyTiles[10].getMapLocation();
                if (nearbyTiles[10].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[11]);
                tileLoc = nearbyTiles[11].getMapLocation();
                if (nearbyTiles[11].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[12]);
                tileLoc = nearbyTiles[12].getMapLocation();
                if (nearbyTiles[12].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[13]);
                tileLoc = nearbyTiles[13].getMapLocation();
                if (nearbyTiles[13].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[14]);
                tileLoc = nearbyTiles[14].getMapLocation();
                if (nearbyTiles[14].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[15]);
                tileLoc = nearbyTiles[15].getMapLocation();
                if (nearbyTiles[15].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[16]);
                tileLoc = nearbyTiles[16].getMapLocation();
                if (nearbyTiles[16].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[17]);
                tileLoc = nearbyTiles[17].getMapLocation();
                if (nearbyTiles[17].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[18]);
                tileLoc = nearbyTiles[18].getMapLocation();
                if (nearbyTiles[18].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[19]);
                tileLoc = nearbyTiles[19].getMapLocation();
                if (nearbyTiles[19].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[20]);
                tileLoc = nearbyTiles[20].getMapLocation();
                if (nearbyTiles[20].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[21]);
                tileLoc = nearbyTiles[21].getMapLocation();
                if (nearbyTiles[21].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[22]);
                tileLoc = nearbyTiles[22].getMapLocation();
                if (nearbyTiles[22].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[23]);
                tileLoc = nearbyTiles[23].getMapLocation();
                if (nearbyTiles[23].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[24]);
                tileLoc = nearbyTiles[24].getMapLocation();
                if (nearbyTiles[24].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[25]);
                tileLoc = nearbyTiles[25].getMapLocation();
                if (nearbyTiles[25].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[26]);
                tileLoc = nearbyTiles[26].getMapLocation();
                if (nearbyTiles[26].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[27]);
                tileLoc = nearbyTiles[27].getMapLocation();
                if (nearbyTiles[27].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[28]);
                tileLoc = nearbyTiles[28].getMapLocation();
                if (nearbyTiles[28].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[29]);
                tileLoc = nearbyTiles[29].getMapLocation();
                if (nearbyTiles[29].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[30]);
                tileLoc = nearbyTiles[30].getMapLocation();
                if (nearbyTiles[30].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[31]);
                tileLoc = nearbyTiles[31].getMapLocation();
                if (nearbyTiles[31].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[32]);
                tileLoc = nearbyTiles[32].getMapLocation();
                if (nearbyTiles[32].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[33]);
                tileLoc = nearbyTiles[33].getMapLocation();
                if (nearbyTiles[33].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[34]);
                tileLoc = nearbyTiles[34].getMapLocation();
                if (nearbyTiles[34].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[35]);
                tileLoc = nearbyTiles[35].getMapLocation();
                if (nearbyTiles[35].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[36]);
                tileLoc = nearbyTiles[36].getMapLocation();
                if (nearbyTiles[36].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[37]);
                tileLoc = nearbyTiles[37].getMapLocation();
                if (nearbyTiles[37].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[38]);
                tileLoc = nearbyTiles[38].getMapLocation();
                if (nearbyTiles[38].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[39]);
                tileLoc = nearbyTiles[39].getMapLocation();
                if (nearbyTiles[39].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[40]);
                tileLoc = nearbyTiles[40].getMapLocation();
                if (nearbyTiles[40].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[41]);
                tileLoc = nearbyTiles[41].getMapLocation();
                if (nearbyTiles[41].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[42]);
                tileLoc = nearbyTiles[42].getMapLocation();
                if (nearbyTiles[42].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[43]);
                tileLoc = nearbyTiles[43].getMapLocation();
                if (nearbyTiles[43].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[44]);
                tileLoc = nearbyTiles[44].getMapLocation();
                if (nearbyTiles[44].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[45]);
                tileLoc = nearbyTiles[45].getMapLocation();
                if (nearbyTiles[45].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[46]);
                tileLoc = nearbyTiles[46].getMapLocation();
                if (nearbyTiles[46].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[47]);
                tileLoc = nearbyTiles[47].getMapLocation();
                if (nearbyTiles[47].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[48]);
                tileLoc = nearbyTiles[48].getMapLocation();
                if (nearbyTiles[48].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[49]);
                tileLoc = nearbyTiles[49].getMapLocation();
                if (nearbyTiles[49].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[50]);
                tileLoc = nearbyTiles[50].getMapLocation();
                if (nearbyTiles[50].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[51]);
                tileLoc = nearbyTiles[51].getMapLocation();
                if (nearbyTiles[51].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[52]);
                tileLoc = nearbyTiles[52].getMapLocation();
                if (nearbyTiles[52].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[53]);
                tileLoc = nearbyTiles[53].getMapLocation();
                if (nearbyTiles[53].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[54]);
                tileLoc = nearbyTiles[54].getMapLocation();
                if (nearbyTiles[54].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[55]);
                tileLoc = nearbyTiles[55].getMapLocation();
                if (nearbyTiles[55].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[56]);
                tileLoc = nearbyTiles[56].getMapLocation();
                if (nearbyTiles[56].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[57]);
                tileLoc = nearbyTiles[57].getMapLocation();
                if (nearbyTiles[57].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[58]);
                tileLoc = nearbyTiles[58].getMapLocation();
                if (nearbyTiles[58].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[59]);
                tileLoc = nearbyTiles[59].getMapLocation();
                if (nearbyTiles[59].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[60]);
                tileLoc = nearbyTiles[60].getMapLocation();
                if (nearbyTiles[60].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[61]);
                tileLoc = nearbyTiles[61].getMapLocation();
                if (nearbyTiles[61].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[62]);
                tileLoc = nearbyTiles[62].getMapLocation();
                if (nearbyTiles[62].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[63]);
                tileLoc = nearbyTiles[63].getMapLocation();
                if (nearbyTiles[63].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[64]);
                tileLoc = nearbyTiles[64].getMapLocation();
                if (nearbyTiles[64].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[65]);
                tileLoc = nearbyTiles[65].getMapLocation();
                if (nearbyTiles[65].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[66]);
                tileLoc = nearbyTiles[66].getMapLocation();
                if (nearbyTiles[66].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[67]);
                tileLoc = nearbyTiles[67].getMapLocation();
                if (nearbyTiles[67].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
                //Symmetry.processTile(nearbyTiles[68]);
                tileLoc = nearbyTiles[68].getMapLocation();
                if (nearbyTiles[68].getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                    if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                        needsClearing = true;
                    }
                    x += tileLoc.x;
                    y += tileLoc.y;
                    count++;
                }
            }
            default -> {
                for (MapInfo tile : nearbyTiles) {
                    tileLoc = tile.getMapLocation();
                    //Symmetry.processTile(tile);
                    if (tile.getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
                        if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
                            needsClearing = true;
                        }
                        x += tileLoc.x;
                        y += tileLoc.y;
                        count++;
                    }
                }
            }
        }


//        for (MapInfo tile : nearbyTiles) {
//            tileLoc = tile.getMapLocation();
//            Symmetry.proccessTile(tile);
//            if (tile.getPaint().isEnemy() && !behindWall.contains(tileLoc)) {
//                if (!needsClearing && nearbyRuin != null && tileLoc.isWithinDistanceSquared(nearbyRuin, 8)) {
//                    needsClearing = true;
//                }
//                x += tileLoc.x;
//                y += tileLoc.y;
//                count++;
//            }
//        }
        numEnemyTiles = count;
        averageEnemyPaint = (count == 0) ? null : new MapLocation(x / count, y / count);
    }
}

