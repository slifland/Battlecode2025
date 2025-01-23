package Version13.Utility;
import Version13.Robots.RobotPlayer;
import battlecode.common.*;

import static Version13.Robots.Mopper.*;
import static Version13.Robots.RobotPlayer.*;

public class MopperUtil {

    public static void checkSymmetry(int index) {
        if(knownSymmetry == RobotPlayer.Symmetry.Unknown) {
            map[nearbyTiles[index].getMapLocation().x][nearbyTiles[index].getMapLocation().y] = (nearbyTiles[index].isPassable()) ? 1 : (nearbyTiles[index].isWall()) ? 2 : 3;
            if(!nearbyTiles[index].isPassable())  Utilities.validateSymmetry(nearbyTiles[index].getMapLocation(), nearbyTiles[index].hasRuin());
        }
    }
    public static void checkClearing(int index){
        if(!needsClearing && nearbyRuin != null && nearbyTiles[index].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8)) {
            needsClearing = true;
        }
    }
    public static void scanNearbyTiles() throws GameActionException {
        int x = 0;
        int y = 0;
        int count = 0;
        switch(nearbyTiles.length)
        {
            case 57->
            {
                Utilities.attemptCompleteResourcePattern(nearbyTiles[0].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[0].getMapLocation().x][nearbyTiles[0].getMapLocation().y] = (nearbyTiles[0].isPassable()) ? 1 : (nearbyTiles[0].isWall()) ? 2 : 3;
                    if(!nearbyTiles[0].isPassable())  Utilities.validateSymmetry(nearbyTiles[0].getMapLocation(), nearbyTiles[0].hasRuin());
                }
                if(nearbyTiles[0].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[0].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[0].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[0].getMapLocation().x;
                    y += nearbyTiles[0].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[1].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[1].getMapLocation().x][nearbyTiles[1].getMapLocation().y] = (nearbyTiles[1].isPassable()) ? 1 : (nearbyTiles[1].isWall()) ? 2 : 3;
                    if(!nearbyTiles[1].isPassable())  Utilities.validateSymmetry(nearbyTiles[1].getMapLocation(), nearbyTiles[1].hasRuin());
                }
                if(nearbyTiles[1].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[1].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[1].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[1].getMapLocation().x;
                    y += nearbyTiles[1].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[2].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[2].getMapLocation().x][nearbyTiles[2].getMapLocation().y] = (nearbyTiles[2].isPassable()) ? 1 : (nearbyTiles[2].isWall()) ? 2 : 3;
                    if(!nearbyTiles[2].isPassable())  Utilities.validateSymmetry(nearbyTiles[2].getMapLocation(), nearbyTiles[2].hasRuin());
                }
                if(nearbyTiles[2].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[2].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[2].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[2].getMapLocation().x;
                    y += nearbyTiles[2].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[3].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[3].getMapLocation().x][nearbyTiles[3].getMapLocation().y] = (nearbyTiles[3].isPassable()) ? 1 : (nearbyTiles[3].isWall()) ? 2 : 3;
                    if(!nearbyTiles[3].isPassable())  Utilities.validateSymmetry(nearbyTiles[3].getMapLocation(), nearbyTiles[3].hasRuin());
                }
                if(nearbyTiles[3].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[3].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[3].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[3].getMapLocation().x;
                    y += nearbyTiles[3].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[4].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[4].getMapLocation().x][nearbyTiles[4].getMapLocation().y] = (nearbyTiles[4].isPassable()) ? 1 : (nearbyTiles[4].isWall()) ? 2 : 3;
                    if(!nearbyTiles[4].isPassable())  Utilities.validateSymmetry(nearbyTiles[4].getMapLocation(), nearbyTiles[4].hasRuin());
                }
                if(nearbyTiles[4].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[4].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[4].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[4].getMapLocation().x;
                    y += nearbyTiles[4].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[5].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[5].getMapLocation().x][nearbyTiles[5].getMapLocation().y] = (nearbyTiles[5].isPassable()) ? 1 : (nearbyTiles[5].isWall()) ? 2 : 3;
                    if(!nearbyTiles[5].isPassable())  Utilities.validateSymmetry(nearbyTiles[5].getMapLocation(), nearbyTiles[5].hasRuin());
                }
                if(nearbyTiles[5].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[5].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[5].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[5].getMapLocation().x;
                    y += nearbyTiles[5].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[6].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[6].getMapLocation().x][nearbyTiles[6].getMapLocation().y] = (nearbyTiles[6].isPassable()) ? 1 : (nearbyTiles[6].isWall()) ? 2 : 3;
                    if(!nearbyTiles[6].isPassable())  Utilities.validateSymmetry(nearbyTiles[6].getMapLocation(), nearbyTiles[6].hasRuin());
                }
                if(nearbyTiles[6].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[6].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[6].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[6].getMapLocation().x;
                    y += nearbyTiles[6].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[7].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[7].getMapLocation().x][nearbyTiles[7].getMapLocation().y] = (nearbyTiles[7].isPassable()) ? 1 : (nearbyTiles[7].isWall()) ? 2 : 3;
                    if(!nearbyTiles[7].isPassable())  Utilities.validateSymmetry(nearbyTiles[7].getMapLocation(), nearbyTiles[7].hasRuin());
                }
                if(nearbyTiles[7].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[7].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[7].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[7].getMapLocation().x;
                    y += nearbyTiles[7].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[8].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[8].getMapLocation().x][nearbyTiles[8].getMapLocation().y] = (nearbyTiles[8].isPassable()) ? 1 : (nearbyTiles[8].isWall()) ? 2 : 3;
                    if(!nearbyTiles[8].isPassable())  Utilities.validateSymmetry(nearbyTiles[8].getMapLocation(), nearbyTiles[8].hasRuin());
                }
                if(nearbyTiles[8].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[8].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[8].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[8].getMapLocation().x;
                    y += nearbyTiles[8].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[9].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[9].getMapLocation().x][nearbyTiles[9].getMapLocation().y] = (nearbyTiles[9].isPassable()) ? 1 : (nearbyTiles[9].isWall()) ? 2 : 3;
                    if(!nearbyTiles[9].isPassable())  Utilities.validateSymmetry(nearbyTiles[9].getMapLocation(), nearbyTiles[9].hasRuin());
                }
                if(nearbyTiles[9].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[9].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[9].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[9].getMapLocation().x;
                    y += nearbyTiles[9].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[10].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[10].getMapLocation().x][nearbyTiles[10].getMapLocation().y] = (nearbyTiles[10].isPassable()) ? 1 : (nearbyTiles[10].isWall()) ? 2 : 3;
                    if(!nearbyTiles[10].isPassable())  Utilities.validateSymmetry(nearbyTiles[10].getMapLocation(), nearbyTiles[10].hasRuin());
                }
                if(nearbyTiles[10].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[10].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[10].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[10].getMapLocation().x;
                    y += nearbyTiles[10].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[11].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[11].getMapLocation().x][nearbyTiles[11].getMapLocation().y] = (nearbyTiles[11].isPassable()) ? 1 : (nearbyTiles[11].isWall()) ? 2 : 3;
                    if(!nearbyTiles[11].isPassable())  Utilities.validateSymmetry(nearbyTiles[11].getMapLocation(), nearbyTiles[11].hasRuin());
                }
                if(nearbyTiles[11].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[11].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[11].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[11].getMapLocation().x;
                    y += nearbyTiles[11].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[12].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[12].getMapLocation().x][nearbyTiles[12].getMapLocation().y] = (nearbyTiles[12].isPassable()) ? 1 : (nearbyTiles[12].isWall()) ? 2 : 3;
                    if(!nearbyTiles[12].isPassable())  Utilities.validateSymmetry(nearbyTiles[12].getMapLocation(), nearbyTiles[12].hasRuin());
                }
                if(nearbyTiles[12].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[12].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[12].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[12].getMapLocation().x;
                    y += nearbyTiles[12].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[13].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[13].getMapLocation().x][nearbyTiles[13].getMapLocation().y] = (nearbyTiles[13].isPassable()) ? 1 : (nearbyTiles[13].isWall()) ? 2 : 3;
                    if(!nearbyTiles[13].isPassable())  Utilities.validateSymmetry(nearbyTiles[13].getMapLocation(), nearbyTiles[13].hasRuin());
                }
                if(nearbyTiles[13].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[13].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[13].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[13].getMapLocation().x;
                    y += nearbyTiles[13].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[14].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[14].getMapLocation().x][nearbyTiles[14].getMapLocation().y] = (nearbyTiles[14].isPassable()) ? 1 : (nearbyTiles[14].isWall()) ? 2 : 3;
                    if(!nearbyTiles[14].isPassable())  Utilities.validateSymmetry(nearbyTiles[14].getMapLocation(), nearbyTiles[14].hasRuin());
                }
                if(nearbyTiles[14].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[14].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[14].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[14].getMapLocation().x;
                    y += nearbyTiles[14].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[15].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[15].getMapLocation().x][nearbyTiles[15].getMapLocation().y] = (nearbyTiles[15].isPassable()) ? 1 : (nearbyTiles[15].isWall()) ? 2 : 3;
                    if(!nearbyTiles[15].isPassable())  Utilities.validateSymmetry(nearbyTiles[15].getMapLocation(), nearbyTiles[15].hasRuin());
                }
                if(nearbyTiles[15].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[15].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[15].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[15].getMapLocation().x;
                    y += nearbyTiles[15].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[16].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[16].getMapLocation().x][nearbyTiles[16].getMapLocation().y] = (nearbyTiles[16].isPassable()) ? 1 : (nearbyTiles[16].isWall()) ? 2 : 3;
                    if(!nearbyTiles[16].isPassable())  Utilities.validateSymmetry(nearbyTiles[16].getMapLocation(), nearbyTiles[16].hasRuin());
                }
                if(nearbyTiles[16].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[16].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[16].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[16].getMapLocation().x;
                    y += nearbyTiles[16].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[17].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[17].getMapLocation().x][nearbyTiles[17].getMapLocation().y] = (nearbyTiles[17].isPassable()) ? 1 : (nearbyTiles[17].isWall()) ? 2 : 3;
                    if(!nearbyTiles[17].isPassable())  Utilities.validateSymmetry(nearbyTiles[17].getMapLocation(), nearbyTiles[17].hasRuin());
                }
                if(nearbyTiles[17].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[17].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[17].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[17].getMapLocation().x;
                    y += nearbyTiles[17].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[18].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[18].getMapLocation().x][nearbyTiles[18].getMapLocation().y] = (nearbyTiles[18].isPassable()) ? 1 : (nearbyTiles[18].isWall()) ? 2 : 3;
                    if(!nearbyTiles[18].isPassable())  Utilities.validateSymmetry(nearbyTiles[18].getMapLocation(), nearbyTiles[18].hasRuin());
                }
                if(nearbyTiles[18].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[18].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[18].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[18].getMapLocation().x;
                    y += nearbyTiles[18].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[19].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[19].getMapLocation().x][nearbyTiles[19].getMapLocation().y] = (nearbyTiles[19].isPassable()) ? 1 : (nearbyTiles[19].isWall()) ? 2 : 3;
                    if(!nearbyTiles[19].isPassable())  Utilities.validateSymmetry(nearbyTiles[19].getMapLocation(), nearbyTiles[19].hasRuin());
                }
                if(nearbyTiles[19].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[19].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[19].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[19].getMapLocation().x;
                    y += nearbyTiles[19].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[20].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[20].getMapLocation().x][nearbyTiles[20].getMapLocation().y] = (nearbyTiles[20].isPassable()) ? 1 : (nearbyTiles[20].isWall()) ? 2 : 3;
                    if(!nearbyTiles[20].isPassable())  Utilities.validateSymmetry(nearbyTiles[20].getMapLocation(), nearbyTiles[20].hasRuin());
                }
                if(nearbyTiles[20].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[20].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[20].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[20].getMapLocation().x;
                    y += nearbyTiles[20].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[21].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[21].getMapLocation().x][nearbyTiles[21].getMapLocation().y] = (nearbyTiles[21].isPassable()) ? 1 : (nearbyTiles[21].isWall()) ? 2 : 3;
                    if(!nearbyTiles[21].isPassable())  Utilities.validateSymmetry(nearbyTiles[21].getMapLocation(), nearbyTiles[21].hasRuin());
                }
                if(nearbyTiles[21].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[21].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[21].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[21].getMapLocation().x;
                    y += nearbyTiles[21].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[22].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[22].getMapLocation().x][nearbyTiles[22].getMapLocation().y] = (nearbyTiles[22].isPassable()) ? 1 : (nearbyTiles[22].isWall()) ? 2 : 3;
                    if(!nearbyTiles[22].isPassable())  Utilities.validateSymmetry(nearbyTiles[22].getMapLocation(), nearbyTiles[22].hasRuin());
                }
                if(nearbyTiles[22].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[22].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[22].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[22].getMapLocation().x;
                    y += nearbyTiles[22].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[23].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[23].getMapLocation().x][nearbyTiles[23].getMapLocation().y] = (nearbyTiles[23].isPassable()) ? 1 : (nearbyTiles[23].isWall()) ? 2 : 3;
                    if(!nearbyTiles[23].isPassable())  Utilities.validateSymmetry(nearbyTiles[23].getMapLocation(), nearbyTiles[23].hasRuin());
                }
                if(nearbyTiles[23].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[23].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[23].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[23].getMapLocation().x;
                    y += nearbyTiles[23].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[24].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[24].getMapLocation().x][nearbyTiles[24].getMapLocation().y] = (nearbyTiles[24].isPassable()) ? 1 : (nearbyTiles[24].isWall()) ? 2 : 3;
                    if(!nearbyTiles[24].isPassable())  Utilities.validateSymmetry(nearbyTiles[24].getMapLocation(), nearbyTiles[24].hasRuin());
                }
                if(nearbyTiles[24].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[24].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[24].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[24].getMapLocation().x;
                    y += nearbyTiles[24].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[25].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[25].getMapLocation().x][nearbyTiles[25].getMapLocation().y] = (nearbyTiles[25].isPassable()) ? 1 : (nearbyTiles[25].isWall()) ? 2 : 3;
                    if(!nearbyTiles[25].isPassable())  Utilities.validateSymmetry(nearbyTiles[25].getMapLocation(), nearbyTiles[25].hasRuin());
                }
                if(nearbyTiles[25].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[25].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[25].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[25].getMapLocation().x;
                    y += nearbyTiles[25].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[26].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[26].getMapLocation().x][nearbyTiles[26].getMapLocation().y] = (nearbyTiles[26].isPassable()) ? 1 : (nearbyTiles[26].isWall()) ? 2 : 3;
                    if(!nearbyTiles[26].isPassable())  Utilities.validateSymmetry(nearbyTiles[26].getMapLocation(), nearbyTiles[26].hasRuin());
                }
                if(nearbyTiles[26].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[26].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[26].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[26].getMapLocation().x;
                    y += nearbyTiles[26].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[27].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[27].getMapLocation().x][nearbyTiles[27].getMapLocation().y] = (nearbyTiles[27].isPassable()) ? 1 : (nearbyTiles[27].isWall()) ? 2 : 3;
                    if(!nearbyTiles[27].isPassable())  Utilities.validateSymmetry(nearbyTiles[27].getMapLocation(), nearbyTiles[27].hasRuin());
                }
                if(nearbyTiles[27].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[27].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[27].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[27].getMapLocation().x;
                    y += nearbyTiles[27].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[28].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[28].getMapLocation().x][nearbyTiles[28].getMapLocation().y] = (nearbyTiles[28].isPassable()) ? 1 : (nearbyTiles[28].isWall()) ? 2 : 3;
                    if(!nearbyTiles[28].isPassable())  Utilities.validateSymmetry(nearbyTiles[28].getMapLocation(), nearbyTiles[28].hasRuin());
                }
                if(nearbyTiles[28].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[28].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[28].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[28].getMapLocation().x;
                    y += nearbyTiles[28].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[29].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[29].getMapLocation().x][nearbyTiles[29].getMapLocation().y] = (nearbyTiles[29].isPassable()) ? 1 : (nearbyTiles[29].isWall()) ? 2 : 3;
                    if(!nearbyTiles[29].isPassable())  Utilities.validateSymmetry(nearbyTiles[29].getMapLocation(), nearbyTiles[29].hasRuin());
                }
                if(nearbyTiles[29].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[29].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[29].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[29].getMapLocation().x;
                    y += nearbyTiles[29].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[30].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[30].getMapLocation().x][nearbyTiles[30].getMapLocation().y] = (nearbyTiles[30].isPassable()) ? 1 : (nearbyTiles[30].isWall()) ? 2 : 3;
                    if(!nearbyTiles[30].isPassable())  Utilities.validateSymmetry(nearbyTiles[30].getMapLocation(), nearbyTiles[30].hasRuin());
                }
                if(nearbyTiles[30].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[30].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[30].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[30].getMapLocation().x;
                    y += nearbyTiles[30].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[31].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[31].getMapLocation().x][nearbyTiles[31].getMapLocation().y] = (nearbyTiles[31].isPassable()) ? 1 : (nearbyTiles[31].isWall()) ? 2 : 3;
                    if(!nearbyTiles[31].isPassable())  Utilities.validateSymmetry(nearbyTiles[31].getMapLocation(), nearbyTiles[31].hasRuin());
                }
                if(nearbyTiles[31].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[31].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[31].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[31].getMapLocation().x;
                    y += nearbyTiles[31].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[32].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[32].getMapLocation().x][nearbyTiles[32].getMapLocation().y] = (nearbyTiles[32].isPassable()) ? 1 : (nearbyTiles[32].isWall()) ? 2 : 3;
                    if(!nearbyTiles[32].isPassable())  Utilities.validateSymmetry(nearbyTiles[32].getMapLocation(), nearbyTiles[32].hasRuin());
                }
                if(nearbyTiles[32].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[32].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[32].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[32].getMapLocation().x;
                    y += nearbyTiles[32].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[33].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[33].getMapLocation().x][nearbyTiles[33].getMapLocation().y] = (nearbyTiles[33].isPassable()) ? 1 : (nearbyTiles[33].isWall()) ? 2 : 3;
                    if(!nearbyTiles[33].isPassable())  Utilities.validateSymmetry(nearbyTiles[33].getMapLocation(), nearbyTiles[33].hasRuin());
                }
                if(nearbyTiles[33].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[33].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[33].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[33].getMapLocation().x;
                    y += nearbyTiles[33].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[34].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[34].getMapLocation().x][nearbyTiles[34].getMapLocation().y] = (nearbyTiles[34].isPassable()) ? 1 : (nearbyTiles[34].isWall()) ? 2 : 3;
                    if(!nearbyTiles[34].isPassable())  Utilities.validateSymmetry(nearbyTiles[34].getMapLocation(), nearbyTiles[34].hasRuin());
                }
                if(nearbyTiles[34].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[34].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[34].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[34].getMapLocation().x;
                    y += nearbyTiles[34].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[35].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[35].getMapLocation().x][nearbyTiles[35].getMapLocation().y] = (nearbyTiles[35].isPassable()) ? 1 : (nearbyTiles[35].isWall()) ? 2 : 3;
                    if(!nearbyTiles[35].isPassable())  Utilities.validateSymmetry(nearbyTiles[35].getMapLocation(), nearbyTiles[35].hasRuin());
                }
                if(nearbyTiles[35].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[35].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[35].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[35].getMapLocation().x;
                    y += nearbyTiles[35].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[36].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[36].getMapLocation().x][nearbyTiles[36].getMapLocation().y] = (nearbyTiles[36].isPassable()) ? 1 : (nearbyTiles[36].isWall()) ? 2 : 3;
                    if(!nearbyTiles[36].isPassable())  Utilities.validateSymmetry(nearbyTiles[36].getMapLocation(), nearbyTiles[36].hasRuin());
                }
                if(nearbyTiles[36].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[36].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[36].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[36].getMapLocation().x;
                    y += nearbyTiles[36].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[37].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[37].getMapLocation().x][nearbyTiles[37].getMapLocation().y] = (nearbyTiles[37].isPassable()) ? 1 : (nearbyTiles[37].isWall()) ? 2 : 3;
                    if(!nearbyTiles[37].isPassable())  Utilities.validateSymmetry(nearbyTiles[37].getMapLocation(), nearbyTiles[37].hasRuin());
                }
                if(nearbyTiles[37].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[37].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[37].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[37].getMapLocation().x;
                    y += nearbyTiles[37].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[38].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[38].getMapLocation().x][nearbyTiles[38].getMapLocation().y] = (nearbyTiles[38].isPassable()) ? 1 : (nearbyTiles[38].isWall()) ? 2 : 3;
                    if(!nearbyTiles[38].isPassable())  Utilities.validateSymmetry(nearbyTiles[38].getMapLocation(), nearbyTiles[38].hasRuin());
                }
                if(nearbyTiles[38].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[38].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[38].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[38].getMapLocation().x;
                    y += nearbyTiles[38].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[39].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[39].getMapLocation().x][nearbyTiles[39].getMapLocation().y] = (nearbyTiles[39].isPassable()) ? 1 : (nearbyTiles[39].isWall()) ? 2 : 3;
                    if(!nearbyTiles[39].isPassable())  Utilities.validateSymmetry(nearbyTiles[39].getMapLocation(), nearbyTiles[39].hasRuin());
                }
                if(nearbyTiles[39].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[39].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[39].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[39].getMapLocation().x;
                    y += nearbyTiles[39].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[40].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[40].getMapLocation().x][nearbyTiles[40].getMapLocation().y] = (nearbyTiles[40].isPassable()) ? 1 : (nearbyTiles[40].isWall()) ? 2 : 3;
                    if(!nearbyTiles[40].isPassable())  Utilities.validateSymmetry(nearbyTiles[40].getMapLocation(), nearbyTiles[40].hasRuin());
                }
                if(nearbyTiles[40].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[40].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[40].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[40].getMapLocation().x;
                    y += nearbyTiles[40].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[41].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[41].getMapLocation().x][nearbyTiles[41].getMapLocation().y] = (nearbyTiles[41].isPassable()) ? 1 : (nearbyTiles[41].isWall()) ? 2 : 3;
                    if(!nearbyTiles[41].isPassable())  Utilities.validateSymmetry(nearbyTiles[41].getMapLocation(), nearbyTiles[41].hasRuin());
                }
                if(nearbyTiles[41].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[41].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[41].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[41].getMapLocation().x;
                    y += nearbyTiles[41].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[42].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[42].getMapLocation().x][nearbyTiles[42].getMapLocation().y] = (nearbyTiles[42].isPassable()) ? 1 : (nearbyTiles[42].isWall()) ? 2 : 3;
                    if(!nearbyTiles[42].isPassable())  Utilities.validateSymmetry(nearbyTiles[42].getMapLocation(), nearbyTiles[42].hasRuin());
                }
                if(nearbyTiles[42].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[42].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[42].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[42].getMapLocation().x;
                    y += nearbyTiles[42].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[43].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[43].getMapLocation().x][nearbyTiles[43].getMapLocation().y] = (nearbyTiles[43].isPassable()) ? 1 : (nearbyTiles[43].isWall()) ? 2 : 3;
                    if(!nearbyTiles[43].isPassable())  Utilities.validateSymmetry(nearbyTiles[43].getMapLocation(), nearbyTiles[43].hasRuin());
                }
                if(nearbyTiles[43].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[43].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[43].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[43].getMapLocation().x;
                    y += nearbyTiles[43].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[44].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[44].getMapLocation().x][nearbyTiles[44].getMapLocation().y] = (nearbyTiles[44].isPassable()) ? 1 : (nearbyTiles[44].isWall()) ? 2 : 3;
                    if(!nearbyTiles[44].isPassable())  Utilities.validateSymmetry(nearbyTiles[44].getMapLocation(), nearbyTiles[44].hasRuin());
                }
                if(nearbyTiles[44].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[44].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[44].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[44].getMapLocation().x;
                    y += nearbyTiles[44].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[45].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[45].getMapLocation().x][nearbyTiles[45].getMapLocation().y] = (nearbyTiles[45].isPassable()) ? 1 : (nearbyTiles[45].isWall()) ? 2 : 3;
                    if(!nearbyTiles[45].isPassable())  Utilities.validateSymmetry(nearbyTiles[45].getMapLocation(), nearbyTiles[45].hasRuin());
                }
                if(nearbyTiles[45].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[45].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[45].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[45].getMapLocation().x;
                    y += nearbyTiles[45].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[46].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[46].getMapLocation().x][nearbyTiles[46].getMapLocation().y] = (nearbyTiles[46].isPassable()) ? 1 : (nearbyTiles[46].isWall()) ? 2 : 3;
                    if(!nearbyTiles[46].isPassable())  Utilities.validateSymmetry(nearbyTiles[46].getMapLocation(), nearbyTiles[46].hasRuin());
                }
                if(nearbyTiles[46].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[46].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[46].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[46].getMapLocation().x;
                    y += nearbyTiles[46].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[47].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[47].getMapLocation().x][nearbyTiles[47].getMapLocation().y] = (nearbyTiles[47].isPassable()) ? 1 : (nearbyTiles[47].isWall()) ? 2 : 3;
                    if(!nearbyTiles[47].isPassable())  Utilities.validateSymmetry(nearbyTiles[47].getMapLocation(), nearbyTiles[47].hasRuin());
                }
                if(nearbyTiles[47].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[47].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[47].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[47].getMapLocation().x;
                    y += nearbyTiles[47].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[48].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[48].getMapLocation().x][nearbyTiles[48].getMapLocation().y] = (nearbyTiles[48].isPassable()) ? 1 : (nearbyTiles[48].isWall()) ? 2 : 3;
                    if(!nearbyTiles[48].isPassable())  Utilities.validateSymmetry(nearbyTiles[48].getMapLocation(), nearbyTiles[48].hasRuin());
                }
                if(nearbyTiles[48].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[48].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[48].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[48].getMapLocation().x;
                    y += nearbyTiles[48].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[49].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[49].getMapLocation().x][nearbyTiles[49].getMapLocation().y] = (nearbyTiles[49].isPassable()) ? 1 : (nearbyTiles[49].isWall()) ? 2 : 3;
                    if(!nearbyTiles[49].isPassable())  Utilities.validateSymmetry(nearbyTiles[49].getMapLocation(), nearbyTiles[49].hasRuin());
                }
                if(nearbyTiles[49].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[49].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[49].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[49].getMapLocation().x;
                    y += nearbyTiles[49].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[50].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[50].getMapLocation().x][nearbyTiles[50].getMapLocation().y] = (nearbyTiles[50].isPassable()) ? 1 : (nearbyTiles[50].isWall()) ? 2 : 3;
                    if(!nearbyTiles[50].isPassable())  Utilities.validateSymmetry(nearbyTiles[50].getMapLocation(), nearbyTiles[50].hasRuin());
                }
                if(nearbyTiles[50].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[50].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[50].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[50].getMapLocation().x;
                    y += nearbyTiles[50].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[51].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[51].getMapLocation().x][nearbyTiles[51].getMapLocation().y] = (nearbyTiles[51].isPassable()) ? 1 : (nearbyTiles[51].isWall()) ? 2 : 3;
                    if(!nearbyTiles[51].isPassable())  Utilities.validateSymmetry(nearbyTiles[51].getMapLocation(), nearbyTiles[51].hasRuin());
                }
                if(nearbyTiles[51].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[51].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[51].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[51].getMapLocation().x;
                    y += nearbyTiles[51].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[52].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[52].getMapLocation().x][nearbyTiles[52].getMapLocation().y] = (nearbyTiles[52].isPassable()) ? 1 : (nearbyTiles[52].isWall()) ? 2 : 3;
                    if(!nearbyTiles[52].isPassable())  Utilities.validateSymmetry(nearbyTiles[52].getMapLocation(), nearbyTiles[52].hasRuin());
                }
                if(nearbyTiles[52].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[52].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[52].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[52].getMapLocation().x;
                    y += nearbyTiles[52].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[53].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[53].getMapLocation().x][nearbyTiles[53].getMapLocation().y] = (nearbyTiles[53].isPassable()) ? 1 : (nearbyTiles[53].isWall()) ? 2 : 3;
                    if(!nearbyTiles[53].isPassable())  Utilities.validateSymmetry(nearbyTiles[53].getMapLocation(), nearbyTiles[53].hasRuin());
                }
                if(nearbyTiles[53].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[53].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[53].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[53].getMapLocation().x;
                    y += nearbyTiles[53].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[54].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[54].getMapLocation().x][nearbyTiles[54].getMapLocation().y] = (nearbyTiles[54].isPassable()) ? 1 : (nearbyTiles[54].isWall()) ? 2 : 3;
                    if(!nearbyTiles[54].isPassable())  Utilities.validateSymmetry(nearbyTiles[54].getMapLocation(), nearbyTiles[54].hasRuin());
                }
                if(nearbyTiles[54].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[54].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[54].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[54].getMapLocation().x;
                    y += nearbyTiles[54].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[55].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[55].getMapLocation().x][nearbyTiles[55].getMapLocation().y] = (nearbyTiles[55].isPassable()) ? 1 : (nearbyTiles[55].isWall()) ? 2 : 3;
                    if(!nearbyTiles[55].isPassable())  Utilities.validateSymmetry(nearbyTiles[55].getMapLocation(), nearbyTiles[55].hasRuin());
                }
                if(nearbyTiles[55].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[55].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[55].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[55].getMapLocation().x;
                    y += nearbyTiles[55].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[56].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[56].getMapLocation().x][nearbyTiles[56].getMapLocation().y] = (nearbyTiles[56].isPassable()) ? 1 : (nearbyTiles[56].isWall()) ? 2 : 3;
                    if(!nearbyTiles[56].isPassable())  Utilities.validateSymmetry(nearbyTiles[56].getMapLocation(), nearbyTiles[56].hasRuin());
                }
                if(nearbyTiles[56].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[56].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[56].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[56].getMapLocation().x;
                    y += nearbyTiles[56].getMapLocation().y;
                    count++;
                }
            }
            case 64->
            {
                Utilities.attemptCompleteResourcePattern(nearbyTiles[0].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[0].getMapLocation().x][nearbyTiles[0].getMapLocation().y] = (nearbyTiles[0].isPassable()) ? 1 : (nearbyTiles[0].isWall()) ? 2 : 3;
                    if(!nearbyTiles[0].isPassable())  Utilities.validateSymmetry(nearbyTiles[0].getMapLocation(), nearbyTiles[0].hasRuin());
                }
                if(nearbyTiles[0].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[0].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[0].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[0].getMapLocation().x;
                    y += nearbyTiles[0].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[1].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[1].getMapLocation().x][nearbyTiles[1].getMapLocation().y] = (nearbyTiles[1].isPassable()) ? 1 : (nearbyTiles[1].isWall()) ? 2 : 3;
                    if(!nearbyTiles[1].isPassable())  Utilities.validateSymmetry(nearbyTiles[1].getMapLocation(), nearbyTiles[1].hasRuin());
                }
                if(nearbyTiles[1].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[1].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[1].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[1].getMapLocation().x;
                    y += nearbyTiles[1].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[2].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[2].getMapLocation().x][nearbyTiles[2].getMapLocation().y] = (nearbyTiles[2].isPassable()) ? 1 : (nearbyTiles[2].isWall()) ? 2 : 3;
                    if(!nearbyTiles[2].isPassable())  Utilities.validateSymmetry(nearbyTiles[2].getMapLocation(), nearbyTiles[2].hasRuin());
                }
                if(nearbyTiles[2].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[2].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[2].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[2].getMapLocation().x;
                    y += nearbyTiles[2].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[3].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[3].getMapLocation().x][nearbyTiles[3].getMapLocation().y] = (nearbyTiles[3].isPassable()) ? 1 : (nearbyTiles[3].isWall()) ? 2 : 3;
                    if(!nearbyTiles[3].isPassable())  Utilities.validateSymmetry(nearbyTiles[3].getMapLocation(), nearbyTiles[3].hasRuin());
                }
                if(nearbyTiles[3].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[3].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[3].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[3].getMapLocation().x;
                    y += nearbyTiles[3].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[4].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[4].getMapLocation().x][nearbyTiles[4].getMapLocation().y] = (nearbyTiles[4].isPassable()) ? 1 : (nearbyTiles[4].isWall()) ? 2 : 3;
                    if(!nearbyTiles[4].isPassable())  Utilities.validateSymmetry(nearbyTiles[4].getMapLocation(), nearbyTiles[4].hasRuin());
                }
                if(nearbyTiles[4].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[4].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[4].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[4].getMapLocation().x;
                    y += nearbyTiles[4].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[5].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[5].getMapLocation().x][nearbyTiles[5].getMapLocation().y] = (nearbyTiles[5].isPassable()) ? 1 : (nearbyTiles[5].isWall()) ? 2 : 3;
                    if(!nearbyTiles[5].isPassable())  Utilities.validateSymmetry(nearbyTiles[5].getMapLocation(), nearbyTiles[5].hasRuin());
                }
                if(nearbyTiles[5].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[5].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[5].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[5].getMapLocation().x;
                    y += nearbyTiles[5].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[6].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[6].getMapLocation().x][nearbyTiles[6].getMapLocation().y] = (nearbyTiles[6].isPassable()) ? 1 : (nearbyTiles[6].isWall()) ? 2 : 3;
                    if(!nearbyTiles[6].isPassable())  Utilities.validateSymmetry(nearbyTiles[6].getMapLocation(), nearbyTiles[6].hasRuin());
                }
                if(nearbyTiles[6].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[6].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[6].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[6].getMapLocation().x;
                    y += nearbyTiles[6].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[7].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[7].getMapLocation().x][nearbyTiles[7].getMapLocation().y] = (nearbyTiles[7].isPassable()) ? 1 : (nearbyTiles[7].isWall()) ? 2 : 3;
                    if(!nearbyTiles[7].isPassable())  Utilities.validateSymmetry(nearbyTiles[7].getMapLocation(), nearbyTiles[7].hasRuin());
                }
                if(nearbyTiles[7].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[7].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[7].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[7].getMapLocation().x;
                    y += nearbyTiles[7].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[8].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[8].getMapLocation().x][nearbyTiles[8].getMapLocation().y] = (nearbyTiles[8].isPassable()) ? 1 : (nearbyTiles[8].isWall()) ? 2 : 3;
                    if(!nearbyTiles[8].isPassable())  Utilities.validateSymmetry(nearbyTiles[8].getMapLocation(), nearbyTiles[8].hasRuin());
                }
                if(nearbyTiles[8].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[8].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[8].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[8].getMapLocation().x;
                    y += nearbyTiles[8].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[9].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[9].getMapLocation().x][nearbyTiles[9].getMapLocation().y] = (nearbyTiles[9].isPassable()) ? 1 : (nearbyTiles[9].isWall()) ? 2 : 3;
                    if(!nearbyTiles[9].isPassable())  Utilities.validateSymmetry(nearbyTiles[9].getMapLocation(), nearbyTiles[9].hasRuin());
                }
                if(nearbyTiles[9].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[9].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[9].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[9].getMapLocation().x;
                    y += nearbyTiles[9].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[10].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[10].getMapLocation().x][nearbyTiles[10].getMapLocation().y] = (nearbyTiles[10].isPassable()) ? 1 : (nearbyTiles[10].isWall()) ? 2 : 3;
                    if(!nearbyTiles[10].isPassable())  Utilities.validateSymmetry(nearbyTiles[10].getMapLocation(), nearbyTiles[10].hasRuin());
                }
                if(nearbyTiles[10].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[10].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[10].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[10].getMapLocation().x;
                    y += nearbyTiles[10].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[11].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[11].getMapLocation().x][nearbyTiles[11].getMapLocation().y] = (nearbyTiles[11].isPassable()) ? 1 : (nearbyTiles[11].isWall()) ? 2 : 3;
                    if(!nearbyTiles[11].isPassable())  Utilities.validateSymmetry(nearbyTiles[11].getMapLocation(), nearbyTiles[11].hasRuin());
                }
                if(nearbyTiles[11].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[11].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[11].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[11].getMapLocation().x;
                    y += nearbyTiles[11].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[12].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[12].getMapLocation().x][nearbyTiles[12].getMapLocation().y] = (nearbyTiles[12].isPassable()) ? 1 : (nearbyTiles[12].isWall()) ? 2 : 3;
                    if(!nearbyTiles[12].isPassable())  Utilities.validateSymmetry(nearbyTiles[12].getMapLocation(), nearbyTiles[12].hasRuin());
                }
                if(nearbyTiles[12].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[12].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[12].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[12].getMapLocation().x;
                    y += nearbyTiles[12].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[13].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[13].getMapLocation().x][nearbyTiles[13].getMapLocation().y] = (nearbyTiles[13].isPassable()) ? 1 : (nearbyTiles[13].isWall()) ? 2 : 3;
                    if(!nearbyTiles[13].isPassable())  Utilities.validateSymmetry(nearbyTiles[13].getMapLocation(), nearbyTiles[13].hasRuin());
                }
                if(nearbyTiles[13].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[13].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[13].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[13].getMapLocation().x;
                    y += nearbyTiles[13].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[14].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[14].getMapLocation().x][nearbyTiles[14].getMapLocation().y] = (nearbyTiles[14].isPassable()) ? 1 : (nearbyTiles[14].isWall()) ? 2 : 3;
                    if(!nearbyTiles[14].isPassable())  Utilities.validateSymmetry(nearbyTiles[14].getMapLocation(), nearbyTiles[14].hasRuin());
                }
                if(nearbyTiles[14].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[14].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[14].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[14].getMapLocation().x;
                    y += nearbyTiles[14].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[15].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[15].getMapLocation().x][nearbyTiles[15].getMapLocation().y] = (nearbyTiles[15].isPassable()) ? 1 : (nearbyTiles[15].isWall()) ? 2 : 3;
                    if(!nearbyTiles[15].isPassable())  Utilities.validateSymmetry(nearbyTiles[15].getMapLocation(), nearbyTiles[15].hasRuin());
                }
                if(nearbyTiles[15].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[15].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[15].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[15].getMapLocation().x;
                    y += nearbyTiles[15].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[16].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[16].getMapLocation().x][nearbyTiles[16].getMapLocation().y] = (nearbyTiles[16].isPassable()) ? 1 : (nearbyTiles[16].isWall()) ? 2 : 3;
                    if(!nearbyTiles[16].isPassable())  Utilities.validateSymmetry(nearbyTiles[16].getMapLocation(), nearbyTiles[16].hasRuin());
                }
                if(nearbyTiles[16].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[16].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[16].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[16].getMapLocation().x;
                    y += nearbyTiles[16].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[17].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[17].getMapLocation().x][nearbyTiles[17].getMapLocation().y] = (nearbyTiles[17].isPassable()) ? 1 : (nearbyTiles[17].isWall()) ? 2 : 3;
                    if(!nearbyTiles[17].isPassable())  Utilities.validateSymmetry(nearbyTiles[17].getMapLocation(), nearbyTiles[17].hasRuin());
                }
                if(nearbyTiles[17].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[17].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[17].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[17].getMapLocation().x;
                    y += nearbyTiles[17].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[18].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[18].getMapLocation().x][nearbyTiles[18].getMapLocation().y] = (nearbyTiles[18].isPassable()) ? 1 : (nearbyTiles[18].isWall()) ? 2 : 3;
                    if(!nearbyTiles[18].isPassable())  Utilities.validateSymmetry(nearbyTiles[18].getMapLocation(), nearbyTiles[18].hasRuin());
                }
                if(nearbyTiles[18].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[18].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[18].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[18].getMapLocation().x;
                    y += nearbyTiles[18].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[19].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[19].getMapLocation().x][nearbyTiles[19].getMapLocation().y] = (nearbyTiles[19].isPassable()) ? 1 : (nearbyTiles[19].isWall()) ? 2 : 3;
                    if(!nearbyTiles[19].isPassable())  Utilities.validateSymmetry(nearbyTiles[19].getMapLocation(), nearbyTiles[19].hasRuin());
                }
                if(nearbyTiles[19].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[19].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[19].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[19].getMapLocation().x;
                    y += nearbyTiles[19].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[20].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[20].getMapLocation().x][nearbyTiles[20].getMapLocation().y] = (nearbyTiles[20].isPassable()) ? 1 : (nearbyTiles[20].isWall()) ? 2 : 3;
                    if(!nearbyTiles[20].isPassable())  Utilities.validateSymmetry(nearbyTiles[20].getMapLocation(), nearbyTiles[20].hasRuin());
                }
                if(nearbyTiles[20].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[20].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[20].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[20].getMapLocation().x;
                    y += nearbyTiles[20].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[21].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[21].getMapLocation().x][nearbyTiles[21].getMapLocation().y] = (nearbyTiles[21].isPassable()) ? 1 : (nearbyTiles[21].isWall()) ? 2 : 3;
                    if(!nearbyTiles[21].isPassable())  Utilities.validateSymmetry(nearbyTiles[21].getMapLocation(), nearbyTiles[21].hasRuin());
                }
                if(nearbyTiles[21].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[21].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[21].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[21].getMapLocation().x;
                    y += nearbyTiles[21].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[22].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[22].getMapLocation().x][nearbyTiles[22].getMapLocation().y] = (nearbyTiles[22].isPassable()) ? 1 : (nearbyTiles[22].isWall()) ? 2 : 3;
                    if(!nearbyTiles[22].isPassable())  Utilities.validateSymmetry(nearbyTiles[22].getMapLocation(), nearbyTiles[22].hasRuin());
                }
                if(nearbyTiles[22].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[22].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[22].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[22].getMapLocation().x;
                    y += nearbyTiles[22].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[23].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[23].getMapLocation().x][nearbyTiles[23].getMapLocation().y] = (nearbyTiles[23].isPassable()) ? 1 : (nearbyTiles[23].isWall()) ? 2 : 3;
                    if(!nearbyTiles[23].isPassable())  Utilities.validateSymmetry(nearbyTiles[23].getMapLocation(), nearbyTiles[23].hasRuin());
                }
                if(nearbyTiles[23].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[23].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[23].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[23].getMapLocation().x;
                    y += nearbyTiles[23].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[24].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[24].getMapLocation().x][nearbyTiles[24].getMapLocation().y] = (nearbyTiles[24].isPassable()) ? 1 : (nearbyTiles[24].isWall()) ? 2 : 3;
                    if(!nearbyTiles[24].isPassable())  Utilities.validateSymmetry(nearbyTiles[24].getMapLocation(), nearbyTiles[24].hasRuin());
                }
                if(nearbyTiles[24].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[24].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[24].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[24].getMapLocation().x;
                    y += nearbyTiles[24].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[25].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[25].getMapLocation().x][nearbyTiles[25].getMapLocation().y] = (nearbyTiles[25].isPassable()) ? 1 : (nearbyTiles[25].isWall()) ? 2 : 3;
                    if(!nearbyTiles[25].isPassable())  Utilities.validateSymmetry(nearbyTiles[25].getMapLocation(), nearbyTiles[25].hasRuin());
                }
                if(nearbyTiles[25].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[25].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[25].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[25].getMapLocation().x;
                    y += nearbyTiles[25].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[26].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[26].getMapLocation().x][nearbyTiles[26].getMapLocation().y] = (nearbyTiles[26].isPassable()) ? 1 : (nearbyTiles[26].isWall()) ? 2 : 3;
                    if(!nearbyTiles[26].isPassable())  Utilities.validateSymmetry(nearbyTiles[26].getMapLocation(), nearbyTiles[26].hasRuin());
                }
                if(nearbyTiles[26].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[26].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[26].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[26].getMapLocation().x;
                    y += nearbyTiles[26].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[27].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[27].getMapLocation().x][nearbyTiles[27].getMapLocation().y] = (nearbyTiles[27].isPassable()) ? 1 : (nearbyTiles[27].isWall()) ? 2 : 3;
                    if(!nearbyTiles[27].isPassable())  Utilities.validateSymmetry(nearbyTiles[27].getMapLocation(), nearbyTiles[27].hasRuin());
                }
                if(nearbyTiles[27].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[27].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[27].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[27].getMapLocation().x;
                    y += nearbyTiles[27].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[28].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[28].getMapLocation().x][nearbyTiles[28].getMapLocation().y] = (nearbyTiles[28].isPassable()) ? 1 : (nearbyTiles[28].isWall()) ? 2 : 3;
                    if(!nearbyTiles[28].isPassable())  Utilities.validateSymmetry(nearbyTiles[28].getMapLocation(), nearbyTiles[28].hasRuin());
                }
                if(nearbyTiles[28].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[28].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[28].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[28].getMapLocation().x;
                    y += nearbyTiles[28].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[29].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[29].getMapLocation().x][nearbyTiles[29].getMapLocation().y] = (nearbyTiles[29].isPassable()) ? 1 : (nearbyTiles[29].isWall()) ? 2 : 3;
                    if(!nearbyTiles[29].isPassable())  Utilities.validateSymmetry(nearbyTiles[29].getMapLocation(), nearbyTiles[29].hasRuin());
                }
                if(nearbyTiles[29].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[29].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[29].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[29].getMapLocation().x;
                    y += nearbyTiles[29].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[30].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[30].getMapLocation().x][nearbyTiles[30].getMapLocation().y] = (nearbyTiles[30].isPassable()) ? 1 : (nearbyTiles[30].isWall()) ? 2 : 3;
                    if(!nearbyTiles[30].isPassable())  Utilities.validateSymmetry(nearbyTiles[30].getMapLocation(), nearbyTiles[30].hasRuin());
                }
                if(nearbyTiles[30].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[30].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[30].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[30].getMapLocation().x;
                    y += nearbyTiles[30].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[31].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[31].getMapLocation().x][nearbyTiles[31].getMapLocation().y] = (nearbyTiles[31].isPassable()) ? 1 : (nearbyTiles[31].isWall()) ? 2 : 3;
                    if(!nearbyTiles[31].isPassable())  Utilities.validateSymmetry(nearbyTiles[31].getMapLocation(), nearbyTiles[31].hasRuin());
                }
                if(nearbyTiles[31].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[31].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[31].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[31].getMapLocation().x;
                    y += nearbyTiles[31].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[32].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[32].getMapLocation().x][nearbyTiles[32].getMapLocation().y] = (nearbyTiles[32].isPassable()) ? 1 : (nearbyTiles[32].isWall()) ? 2 : 3;
                    if(!nearbyTiles[32].isPassable())  Utilities.validateSymmetry(nearbyTiles[32].getMapLocation(), nearbyTiles[32].hasRuin());
                }
                if(nearbyTiles[32].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[32].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[32].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[32].getMapLocation().x;
                    y += nearbyTiles[32].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[33].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[33].getMapLocation().x][nearbyTiles[33].getMapLocation().y] = (nearbyTiles[33].isPassable()) ? 1 : (nearbyTiles[33].isWall()) ? 2 : 3;
                    if(!nearbyTiles[33].isPassable())  Utilities.validateSymmetry(nearbyTiles[33].getMapLocation(), nearbyTiles[33].hasRuin());
                }
                if(nearbyTiles[33].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[33].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[33].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[33].getMapLocation().x;
                    y += nearbyTiles[33].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[34].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[34].getMapLocation().x][nearbyTiles[34].getMapLocation().y] = (nearbyTiles[34].isPassable()) ? 1 : (nearbyTiles[34].isWall()) ? 2 : 3;
                    if(!nearbyTiles[34].isPassable())  Utilities.validateSymmetry(nearbyTiles[34].getMapLocation(), nearbyTiles[34].hasRuin());
                }
                if(nearbyTiles[34].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[34].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[34].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[34].getMapLocation().x;
                    y += nearbyTiles[34].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[35].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[35].getMapLocation().x][nearbyTiles[35].getMapLocation().y] = (nearbyTiles[35].isPassable()) ? 1 : (nearbyTiles[35].isWall()) ? 2 : 3;
                    if(!nearbyTiles[35].isPassable())  Utilities.validateSymmetry(nearbyTiles[35].getMapLocation(), nearbyTiles[35].hasRuin());
                }
                if(nearbyTiles[35].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[35].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[35].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[35].getMapLocation().x;
                    y += nearbyTiles[35].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[36].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[36].getMapLocation().x][nearbyTiles[36].getMapLocation().y] = (nearbyTiles[36].isPassable()) ? 1 : (nearbyTiles[36].isWall()) ? 2 : 3;
                    if(!nearbyTiles[36].isPassable())  Utilities.validateSymmetry(nearbyTiles[36].getMapLocation(), nearbyTiles[36].hasRuin());
                }
                if(nearbyTiles[36].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[36].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[36].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[36].getMapLocation().x;
                    y += nearbyTiles[36].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[37].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[37].getMapLocation().x][nearbyTiles[37].getMapLocation().y] = (nearbyTiles[37].isPassable()) ? 1 : (nearbyTiles[37].isWall()) ? 2 : 3;
                    if(!nearbyTiles[37].isPassable())  Utilities.validateSymmetry(nearbyTiles[37].getMapLocation(), nearbyTiles[37].hasRuin());
                }
                if(nearbyTiles[37].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[37].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[37].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[37].getMapLocation().x;
                    y += nearbyTiles[37].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[38].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[38].getMapLocation().x][nearbyTiles[38].getMapLocation().y] = (nearbyTiles[38].isPassable()) ? 1 : (nearbyTiles[38].isWall()) ? 2 : 3;
                    if(!nearbyTiles[38].isPassable())  Utilities.validateSymmetry(nearbyTiles[38].getMapLocation(), nearbyTiles[38].hasRuin());
                }
                if(nearbyTiles[38].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[38].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[38].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[38].getMapLocation().x;
                    y += nearbyTiles[38].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[39].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[39].getMapLocation().x][nearbyTiles[39].getMapLocation().y] = (nearbyTiles[39].isPassable()) ? 1 : (nearbyTiles[39].isWall()) ? 2 : 3;
                    if(!nearbyTiles[39].isPassable())  Utilities.validateSymmetry(nearbyTiles[39].getMapLocation(), nearbyTiles[39].hasRuin());
                }
                if(nearbyTiles[39].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[39].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[39].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[39].getMapLocation().x;
                    y += nearbyTiles[39].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[40].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[40].getMapLocation().x][nearbyTiles[40].getMapLocation().y] = (nearbyTiles[40].isPassable()) ? 1 : (nearbyTiles[40].isWall()) ? 2 : 3;
                    if(!nearbyTiles[40].isPassable())  Utilities.validateSymmetry(nearbyTiles[40].getMapLocation(), nearbyTiles[40].hasRuin());
                }
                if(nearbyTiles[40].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[40].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[40].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[40].getMapLocation().x;
                    y += nearbyTiles[40].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[41].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[41].getMapLocation().x][nearbyTiles[41].getMapLocation().y] = (nearbyTiles[41].isPassable()) ? 1 : (nearbyTiles[41].isWall()) ? 2 : 3;
                    if(!nearbyTiles[41].isPassable())  Utilities.validateSymmetry(nearbyTiles[41].getMapLocation(), nearbyTiles[41].hasRuin());
                }
                if(nearbyTiles[41].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[41].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[41].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[41].getMapLocation().x;
                    y += nearbyTiles[41].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[42].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[42].getMapLocation().x][nearbyTiles[42].getMapLocation().y] = (nearbyTiles[42].isPassable()) ? 1 : (nearbyTiles[42].isWall()) ? 2 : 3;
                    if(!nearbyTiles[42].isPassable())  Utilities.validateSymmetry(nearbyTiles[42].getMapLocation(), nearbyTiles[42].hasRuin());
                }
                if(nearbyTiles[42].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[42].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[42].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[42].getMapLocation().x;
                    y += nearbyTiles[42].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[43].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[43].getMapLocation().x][nearbyTiles[43].getMapLocation().y] = (nearbyTiles[43].isPassable()) ? 1 : (nearbyTiles[43].isWall()) ? 2 : 3;
                    if(!nearbyTiles[43].isPassable())  Utilities.validateSymmetry(nearbyTiles[43].getMapLocation(), nearbyTiles[43].hasRuin());
                }
                if(nearbyTiles[43].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[43].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[43].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[43].getMapLocation().x;
                    y += nearbyTiles[43].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[44].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[44].getMapLocation().x][nearbyTiles[44].getMapLocation().y] = (nearbyTiles[44].isPassable()) ? 1 : (nearbyTiles[44].isWall()) ? 2 : 3;
                    if(!nearbyTiles[44].isPassable())  Utilities.validateSymmetry(nearbyTiles[44].getMapLocation(), nearbyTiles[44].hasRuin());
                }
                if(nearbyTiles[44].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[44].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[44].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[44].getMapLocation().x;
                    y += nearbyTiles[44].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[45].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[45].getMapLocation().x][nearbyTiles[45].getMapLocation().y] = (nearbyTiles[45].isPassable()) ? 1 : (nearbyTiles[45].isWall()) ? 2 : 3;
                    if(!nearbyTiles[45].isPassable())  Utilities.validateSymmetry(nearbyTiles[45].getMapLocation(), nearbyTiles[45].hasRuin());
                }
                if(nearbyTiles[45].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[45].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[45].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[45].getMapLocation().x;
                    y += nearbyTiles[45].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[46].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[46].getMapLocation().x][nearbyTiles[46].getMapLocation().y] = (nearbyTiles[46].isPassable()) ? 1 : (nearbyTiles[46].isWall()) ? 2 : 3;
                    if(!nearbyTiles[46].isPassable())  Utilities.validateSymmetry(nearbyTiles[46].getMapLocation(), nearbyTiles[46].hasRuin());
                }
                if(nearbyTiles[46].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[46].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[46].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[46].getMapLocation().x;
                    y += nearbyTiles[46].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[47].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[47].getMapLocation().x][nearbyTiles[47].getMapLocation().y] = (nearbyTiles[47].isPassable()) ? 1 : (nearbyTiles[47].isWall()) ? 2 : 3;
                    if(!nearbyTiles[47].isPassable())  Utilities.validateSymmetry(nearbyTiles[47].getMapLocation(), nearbyTiles[47].hasRuin());
                }
                if(nearbyTiles[47].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[47].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[47].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[47].getMapLocation().x;
                    y += nearbyTiles[47].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[48].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[48].getMapLocation().x][nearbyTiles[48].getMapLocation().y] = (nearbyTiles[48].isPassable()) ? 1 : (nearbyTiles[48].isWall()) ? 2 : 3;
                    if(!nearbyTiles[48].isPassable())  Utilities.validateSymmetry(nearbyTiles[48].getMapLocation(), nearbyTiles[48].hasRuin());
                }
                if(nearbyTiles[48].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[48].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[48].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[48].getMapLocation().x;
                    y += nearbyTiles[48].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[49].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[49].getMapLocation().x][nearbyTiles[49].getMapLocation().y] = (nearbyTiles[49].isPassable()) ? 1 : (nearbyTiles[49].isWall()) ? 2 : 3;
                    if(!nearbyTiles[49].isPassable())  Utilities.validateSymmetry(nearbyTiles[49].getMapLocation(), nearbyTiles[49].hasRuin());
                }
                if(nearbyTiles[49].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[49].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[49].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[49].getMapLocation().x;
                    y += nearbyTiles[49].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[50].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[50].getMapLocation().x][nearbyTiles[50].getMapLocation().y] = (nearbyTiles[50].isPassable()) ? 1 : (nearbyTiles[50].isWall()) ? 2 : 3;
                    if(!nearbyTiles[50].isPassable())  Utilities.validateSymmetry(nearbyTiles[50].getMapLocation(), nearbyTiles[50].hasRuin());
                }
                if(nearbyTiles[50].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[50].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[50].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[50].getMapLocation().x;
                    y += nearbyTiles[50].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[51].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[51].getMapLocation().x][nearbyTiles[51].getMapLocation().y] = (nearbyTiles[51].isPassable()) ? 1 : (nearbyTiles[51].isWall()) ? 2 : 3;
                    if(!nearbyTiles[51].isPassable())  Utilities.validateSymmetry(nearbyTiles[51].getMapLocation(), nearbyTiles[51].hasRuin());
                }
                if(nearbyTiles[51].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[51].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[51].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[51].getMapLocation().x;
                    y += nearbyTiles[51].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[52].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[52].getMapLocation().x][nearbyTiles[52].getMapLocation().y] = (nearbyTiles[52].isPassable()) ? 1 : (nearbyTiles[52].isWall()) ? 2 : 3;
                    if(!nearbyTiles[52].isPassable())  Utilities.validateSymmetry(nearbyTiles[52].getMapLocation(), nearbyTiles[52].hasRuin());
                }
                if(nearbyTiles[52].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[52].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[52].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[52].getMapLocation().x;
                    y += nearbyTiles[52].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[53].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[53].getMapLocation().x][nearbyTiles[53].getMapLocation().y] = (nearbyTiles[53].isPassable()) ? 1 : (nearbyTiles[53].isWall()) ? 2 : 3;
                    if(!nearbyTiles[53].isPassable())  Utilities.validateSymmetry(nearbyTiles[53].getMapLocation(), nearbyTiles[53].hasRuin());
                }
                if(nearbyTiles[53].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[53].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[53].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[53].getMapLocation().x;
                    y += nearbyTiles[53].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[54].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[54].getMapLocation().x][nearbyTiles[54].getMapLocation().y] = (nearbyTiles[54].isPassable()) ? 1 : (nearbyTiles[54].isWall()) ? 2 : 3;
                    if(!nearbyTiles[54].isPassable())  Utilities.validateSymmetry(nearbyTiles[54].getMapLocation(), nearbyTiles[54].hasRuin());
                }
                if(nearbyTiles[54].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[54].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[54].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[54].getMapLocation().x;
                    y += nearbyTiles[54].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[55].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[55].getMapLocation().x][nearbyTiles[55].getMapLocation().y] = (nearbyTiles[55].isPassable()) ? 1 : (nearbyTiles[55].isWall()) ? 2 : 3;
                    if(!nearbyTiles[55].isPassable())  Utilities.validateSymmetry(nearbyTiles[55].getMapLocation(), nearbyTiles[55].hasRuin());
                }
                if(nearbyTiles[55].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[55].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[55].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[55].getMapLocation().x;
                    y += nearbyTiles[55].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[56].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[56].getMapLocation().x][nearbyTiles[56].getMapLocation().y] = (nearbyTiles[56].isPassable()) ? 1 : (nearbyTiles[56].isWall()) ? 2 : 3;
                    if(!nearbyTiles[56].isPassable())  Utilities.validateSymmetry(nearbyTiles[56].getMapLocation(), nearbyTiles[56].hasRuin());
                }
                if(nearbyTiles[56].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[56].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[56].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[56].getMapLocation().x;
                    y += nearbyTiles[56].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[57].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[57].getMapLocation().x][nearbyTiles[57].getMapLocation().y] = (nearbyTiles[57].isPassable()) ? 1 : (nearbyTiles[57].isWall()) ? 2 : 3;
                    if(!nearbyTiles[57].isPassable())  Utilities.validateSymmetry(nearbyTiles[57].getMapLocation(), nearbyTiles[57].hasRuin());
                }
                if(nearbyTiles[57].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[57].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[57].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[57].getMapLocation().x;
                    y += nearbyTiles[57].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[58].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[58].getMapLocation().x][nearbyTiles[58].getMapLocation().y] = (nearbyTiles[58].isPassable()) ? 1 : (nearbyTiles[58].isWall()) ? 2 : 3;
                    if(!nearbyTiles[58].isPassable())  Utilities.validateSymmetry(nearbyTiles[58].getMapLocation(), nearbyTiles[58].hasRuin());
                }
                if(nearbyTiles[58].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[58].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[58].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[58].getMapLocation().x;
                    y += nearbyTiles[58].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[59].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[59].getMapLocation().x][nearbyTiles[59].getMapLocation().y] = (nearbyTiles[59].isPassable()) ? 1 : (nearbyTiles[59].isWall()) ? 2 : 3;
                    if(!nearbyTiles[59].isPassable())  Utilities.validateSymmetry(nearbyTiles[59].getMapLocation(), nearbyTiles[59].hasRuin());
                }
                if(nearbyTiles[59].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[59].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[59].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[59].getMapLocation().x;
                    y += nearbyTiles[59].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[60].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[60].getMapLocation().x][nearbyTiles[60].getMapLocation().y] = (nearbyTiles[60].isPassable()) ? 1 : (nearbyTiles[60].isWall()) ? 2 : 3;
                    if(!nearbyTiles[60].isPassable())  Utilities.validateSymmetry(nearbyTiles[60].getMapLocation(), nearbyTiles[60].hasRuin());
                }
                if(nearbyTiles[60].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[60].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[60].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[60].getMapLocation().x;
                    y += nearbyTiles[60].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[61].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[61].getMapLocation().x][nearbyTiles[61].getMapLocation().y] = (nearbyTiles[61].isPassable()) ? 1 : (nearbyTiles[61].isWall()) ? 2 : 3;
                    if(!nearbyTiles[61].isPassable())  Utilities.validateSymmetry(nearbyTiles[61].getMapLocation(), nearbyTiles[61].hasRuin());
                }
                if(nearbyTiles[61].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[61].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[61].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[61].getMapLocation().x;
                    y += nearbyTiles[61].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[62].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[62].getMapLocation().x][nearbyTiles[62].getMapLocation().y] = (nearbyTiles[62].isPassable()) ? 1 : (nearbyTiles[62].isWall()) ? 2 : 3;
                    if(!nearbyTiles[62].isPassable())  Utilities.validateSymmetry(nearbyTiles[62].getMapLocation(), nearbyTiles[62].hasRuin());
                }
                if(nearbyTiles[62].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[62].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[62].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[62].getMapLocation().x;
                    y += nearbyTiles[62].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[63].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[63].getMapLocation().x][nearbyTiles[63].getMapLocation().y] = (nearbyTiles[63].isPassable()) ? 1 : (nearbyTiles[63].isWall()) ? 2 : 3;
                    if(!nearbyTiles[63].isPassable())  Utilities.validateSymmetry(nearbyTiles[63].getMapLocation(), nearbyTiles[63].hasRuin());
                }
                if(nearbyTiles[63].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[63].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[63].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[63].getMapLocation().x;
                    y += nearbyTiles[63].getMapLocation().y;
                    count++;
                }
            }
            case 69->
            {
                Utilities.attemptCompleteResourcePattern(nearbyTiles[0].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[0].getMapLocation().x][nearbyTiles[0].getMapLocation().y] = (nearbyTiles[0].isPassable()) ? 1 : (nearbyTiles[0].isWall()) ? 2 : 3;
                    if(!nearbyTiles[0].isPassable())  Utilities.validateSymmetry(nearbyTiles[0].getMapLocation(), nearbyTiles[0].hasRuin());
                }
                if(nearbyTiles[0].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[0].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[0].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[0].getMapLocation().x;
                    y += nearbyTiles[0].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[1].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[1].getMapLocation().x][nearbyTiles[1].getMapLocation().y] = (nearbyTiles[1].isPassable()) ? 1 : (nearbyTiles[1].isWall()) ? 2 : 3;
                    if(!nearbyTiles[1].isPassable())  Utilities.validateSymmetry(nearbyTiles[1].getMapLocation(), nearbyTiles[1].hasRuin());
                }
                if(nearbyTiles[1].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[1].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[1].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[1].getMapLocation().x;
                    y += nearbyTiles[1].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[2].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[2].getMapLocation().x][nearbyTiles[2].getMapLocation().y] = (nearbyTiles[2].isPassable()) ? 1 : (nearbyTiles[2].isWall()) ? 2 : 3;
                    if(!nearbyTiles[2].isPassable())  Utilities.validateSymmetry(nearbyTiles[2].getMapLocation(), nearbyTiles[2].hasRuin());
                }
                if(nearbyTiles[2].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[2].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[2].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[2].getMapLocation().x;
                    y += nearbyTiles[2].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[3].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[3].getMapLocation().x][nearbyTiles[3].getMapLocation().y] = (nearbyTiles[3].isPassable()) ? 1 : (nearbyTiles[3].isWall()) ? 2 : 3;
                    if(!nearbyTiles[3].isPassable())  Utilities.validateSymmetry(nearbyTiles[3].getMapLocation(), nearbyTiles[3].hasRuin());
                }
                if(nearbyTiles[3].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[3].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[3].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[3].getMapLocation().x;
                    y += nearbyTiles[3].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[4].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[4].getMapLocation().x][nearbyTiles[4].getMapLocation().y] = (nearbyTiles[4].isPassable()) ? 1 : (nearbyTiles[4].isWall()) ? 2 : 3;
                    if(!nearbyTiles[4].isPassable())  Utilities.validateSymmetry(nearbyTiles[4].getMapLocation(), nearbyTiles[4].hasRuin());
                }
                if(nearbyTiles[4].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[4].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[4].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[4].getMapLocation().x;
                    y += nearbyTiles[4].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[5].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[5].getMapLocation().x][nearbyTiles[5].getMapLocation().y] = (nearbyTiles[5].isPassable()) ? 1 : (nearbyTiles[5].isWall()) ? 2 : 3;
                    if(!nearbyTiles[5].isPassable())  Utilities.validateSymmetry(nearbyTiles[5].getMapLocation(), nearbyTiles[5].hasRuin());
                }
                if(nearbyTiles[5].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[5].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[5].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[5].getMapLocation().x;
                    y += nearbyTiles[5].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[6].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[6].getMapLocation().x][nearbyTiles[6].getMapLocation().y] = (nearbyTiles[6].isPassable()) ? 1 : (nearbyTiles[6].isWall()) ? 2 : 3;
                    if(!nearbyTiles[6].isPassable())  Utilities.validateSymmetry(nearbyTiles[6].getMapLocation(), nearbyTiles[6].hasRuin());
                }
                if(nearbyTiles[6].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[6].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[6].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[6].getMapLocation().x;
                    y += nearbyTiles[6].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[7].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[7].getMapLocation().x][nearbyTiles[7].getMapLocation().y] = (nearbyTiles[7].isPassable()) ? 1 : (nearbyTiles[7].isWall()) ? 2 : 3;
                    if(!nearbyTiles[7].isPassable())  Utilities.validateSymmetry(nearbyTiles[7].getMapLocation(), nearbyTiles[7].hasRuin());
                }
                if(nearbyTiles[7].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[7].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[7].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[7].getMapLocation().x;
                    y += nearbyTiles[7].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[8].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[8].getMapLocation().x][nearbyTiles[8].getMapLocation().y] = (nearbyTiles[8].isPassable()) ? 1 : (nearbyTiles[8].isWall()) ? 2 : 3;
                    if(!nearbyTiles[8].isPassable())  Utilities.validateSymmetry(nearbyTiles[8].getMapLocation(), nearbyTiles[8].hasRuin());
                }
                if(nearbyTiles[8].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[8].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[8].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[8].getMapLocation().x;
                    y += nearbyTiles[8].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[9].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[9].getMapLocation().x][nearbyTiles[9].getMapLocation().y] = (nearbyTiles[9].isPassable()) ? 1 : (nearbyTiles[9].isWall()) ? 2 : 3;
                    if(!nearbyTiles[9].isPassable())  Utilities.validateSymmetry(nearbyTiles[9].getMapLocation(), nearbyTiles[9].hasRuin());
                }
                if(nearbyTiles[9].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[9].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[9].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[9].getMapLocation().x;
                    y += nearbyTiles[9].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[10].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[10].getMapLocation().x][nearbyTiles[10].getMapLocation().y] = (nearbyTiles[10].isPassable()) ? 1 : (nearbyTiles[10].isWall()) ? 2 : 3;
                    if(!nearbyTiles[10].isPassable())  Utilities.validateSymmetry(nearbyTiles[10].getMapLocation(), nearbyTiles[10].hasRuin());
                }
                if(nearbyTiles[10].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[10].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[10].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[10].getMapLocation().x;
                    y += nearbyTiles[10].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[11].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[11].getMapLocation().x][nearbyTiles[11].getMapLocation().y] = (nearbyTiles[11].isPassable()) ? 1 : (nearbyTiles[11].isWall()) ? 2 : 3;
                    if(!nearbyTiles[11].isPassable())  Utilities.validateSymmetry(nearbyTiles[11].getMapLocation(), nearbyTiles[11].hasRuin());
                }
                if(nearbyTiles[11].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[11].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[11].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[11].getMapLocation().x;
                    y += nearbyTiles[11].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[12].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[12].getMapLocation().x][nearbyTiles[12].getMapLocation().y] = (nearbyTiles[12].isPassable()) ? 1 : (nearbyTiles[12].isWall()) ? 2 : 3;
                    if(!nearbyTiles[12].isPassable())  Utilities.validateSymmetry(nearbyTiles[12].getMapLocation(), nearbyTiles[12].hasRuin());
                }
                if(nearbyTiles[12].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[12].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[12].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[12].getMapLocation().x;
                    y += nearbyTiles[12].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[13].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[13].getMapLocation().x][nearbyTiles[13].getMapLocation().y] = (nearbyTiles[13].isPassable()) ? 1 : (nearbyTiles[13].isWall()) ? 2 : 3;
                    if(!nearbyTiles[13].isPassable())  Utilities.validateSymmetry(nearbyTiles[13].getMapLocation(), nearbyTiles[13].hasRuin());
                }
                if(nearbyTiles[13].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[13].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[13].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[13].getMapLocation().x;
                    y += nearbyTiles[13].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[14].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[14].getMapLocation().x][nearbyTiles[14].getMapLocation().y] = (nearbyTiles[14].isPassable()) ? 1 : (nearbyTiles[14].isWall()) ? 2 : 3;
                    if(!nearbyTiles[14].isPassable())  Utilities.validateSymmetry(nearbyTiles[14].getMapLocation(), nearbyTiles[14].hasRuin());
                }
                if(nearbyTiles[14].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[14].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[14].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[14].getMapLocation().x;
                    y += nearbyTiles[14].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[15].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[15].getMapLocation().x][nearbyTiles[15].getMapLocation().y] = (nearbyTiles[15].isPassable()) ? 1 : (nearbyTiles[15].isWall()) ? 2 : 3;
                    if(!nearbyTiles[15].isPassable())  Utilities.validateSymmetry(nearbyTiles[15].getMapLocation(), nearbyTiles[15].hasRuin());
                }
                if(nearbyTiles[15].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[15].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[15].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[15].getMapLocation().x;
                    y += nearbyTiles[15].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[16].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[16].getMapLocation().x][nearbyTiles[16].getMapLocation().y] = (nearbyTiles[16].isPassable()) ? 1 : (nearbyTiles[16].isWall()) ? 2 : 3;
                    if(!nearbyTiles[16].isPassable())  Utilities.validateSymmetry(nearbyTiles[16].getMapLocation(), nearbyTiles[16].hasRuin());
                }
                if(nearbyTiles[16].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[16].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[16].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[16].getMapLocation().x;
                    y += nearbyTiles[16].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[17].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[17].getMapLocation().x][nearbyTiles[17].getMapLocation().y] = (nearbyTiles[17].isPassable()) ? 1 : (nearbyTiles[17].isWall()) ? 2 : 3;
                    if(!nearbyTiles[17].isPassable())  Utilities.validateSymmetry(nearbyTiles[17].getMapLocation(), nearbyTiles[17].hasRuin());
                }
                if(nearbyTiles[17].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[17].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[17].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[17].getMapLocation().x;
                    y += nearbyTiles[17].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[18].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[18].getMapLocation().x][nearbyTiles[18].getMapLocation().y] = (nearbyTiles[18].isPassable()) ? 1 : (nearbyTiles[18].isWall()) ? 2 : 3;
                    if(!nearbyTiles[18].isPassable())  Utilities.validateSymmetry(nearbyTiles[18].getMapLocation(), nearbyTiles[18].hasRuin());
                }
                if(nearbyTiles[18].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[18].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[18].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[18].getMapLocation().x;
                    y += nearbyTiles[18].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[19].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[19].getMapLocation().x][nearbyTiles[19].getMapLocation().y] = (nearbyTiles[19].isPassable()) ? 1 : (nearbyTiles[19].isWall()) ? 2 : 3;
                    if(!nearbyTiles[19].isPassable())  Utilities.validateSymmetry(nearbyTiles[19].getMapLocation(), nearbyTiles[19].hasRuin());
                }
                if(nearbyTiles[19].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[19].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[19].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[19].getMapLocation().x;
                    y += nearbyTiles[19].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[20].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[20].getMapLocation().x][nearbyTiles[20].getMapLocation().y] = (nearbyTiles[20].isPassable()) ? 1 : (nearbyTiles[20].isWall()) ? 2 : 3;
                    if(!nearbyTiles[20].isPassable())  Utilities.validateSymmetry(nearbyTiles[20].getMapLocation(), nearbyTiles[20].hasRuin());
                }
                if(nearbyTiles[20].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[20].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[20].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[20].getMapLocation().x;
                    y += nearbyTiles[20].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[21].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[21].getMapLocation().x][nearbyTiles[21].getMapLocation().y] = (nearbyTiles[21].isPassable()) ? 1 : (nearbyTiles[21].isWall()) ? 2 : 3;
                    if(!nearbyTiles[21].isPassable())  Utilities.validateSymmetry(nearbyTiles[21].getMapLocation(), nearbyTiles[21].hasRuin());
                }
                if(nearbyTiles[21].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[21].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[21].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[21].getMapLocation().x;
                    y += nearbyTiles[21].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[22].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[22].getMapLocation().x][nearbyTiles[22].getMapLocation().y] = (nearbyTiles[22].isPassable()) ? 1 : (nearbyTiles[22].isWall()) ? 2 : 3;
                    if(!nearbyTiles[22].isPassable())  Utilities.validateSymmetry(nearbyTiles[22].getMapLocation(), nearbyTiles[22].hasRuin());
                }
                if(nearbyTiles[22].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[22].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[22].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[22].getMapLocation().x;
                    y += nearbyTiles[22].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[23].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[23].getMapLocation().x][nearbyTiles[23].getMapLocation().y] = (nearbyTiles[23].isPassable()) ? 1 : (nearbyTiles[23].isWall()) ? 2 : 3;
                    if(!nearbyTiles[23].isPassable())  Utilities.validateSymmetry(nearbyTiles[23].getMapLocation(), nearbyTiles[23].hasRuin());
                }
                if(nearbyTiles[23].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[23].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[23].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[23].getMapLocation().x;
                    y += nearbyTiles[23].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[24].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[24].getMapLocation().x][nearbyTiles[24].getMapLocation().y] = (nearbyTiles[24].isPassable()) ? 1 : (nearbyTiles[24].isWall()) ? 2 : 3;
                    if(!nearbyTiles[24].isPassable())  Utilities.validateSymmetry(nearbyTiles[24].getMapLocation(), nearbyTiles[24].hasRuin());
                }
                if(nearbyTiles[24].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[24].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[24].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[24].getMapLocation().x;
                    y += nearbyTiles[24].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[25].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[25].getMapLocation().x][nearbyTiles[25].getMapLocation().y] = (nearbyTiles[25].isPassable()) ? 1 : (nearbyTiles[25].isWall()) ? 2 : 3;
                    if(!nearbyTiles[25].isPassable())  Utilities.validateSymmetry(nearbyTiles[25].getMapLocation(), nearbyTiles[25].hasRuin());
                }
                if(nearbyTiles[25].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[25].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[25].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[25].getMapLocation().x;
                    y += nearbyTiles[25].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[26].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[26].getMapLocation().x][nearbyTiles[26].getMapLocation().y] = (nearbyTiles[26].isPassable()) ? 1 : (nearbyTiles[26].isWall()) ? 2 : 3;
                    if(!nearbyTiles[26].isPassable())  Utilities.validateSymmetry(nearbyTiles[26].getMapLocation(), nearbyTiles[26].hasRuin());
                }
                if(nearbyTiles[26].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[26].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[26].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[26].getMapLocation().x;
                    y += nearbyTiles[26].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[27].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[27].getMapLocation().x][nearbyTiles[27].getMapLocation().y] = (nearbyTiles[27].isPassable()) ? 1 : (nearbyTiles[27].isWall()) ? 2 : 3;
                    if(!nearbyTiles[27].isPassable())  Utilities.validateSymmetry(nearbyTiles[27].getMapLocation(), nearbyTiles[27].hasRuin());
                }
                if(nearbyTiles[27].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[27].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[27].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[27].getMapLocation().x;
                    y += nearbyTiles[27].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[28].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[28].getMapLocation().x][nearbyTiles[28].getMapLocation().y] = (nearbyTiles[28].isPassable()) ? 1 : (nearbyTiles[28].isWall()) ? 2 : 3;
                    if(!nearbyTiles[28].isPassable())  Utilities.validateSymmetry(nearbyTiles[28].getMapLocation(), nearbyTiles[28].hasRuin());
                }
                if(nearbyTiles[28].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[28].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[28].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[28].getMapLocation().x;
                    y += nearbyTiles[28].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[29].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[29].getMapLocation().x][nearbyTiles[29].getMapLocation().y] = (nearbyTiles[29].isPassable()) ? 1 : (nearbyTiles[29].isWall()) ? 2 : 3;
                    if(!nearbyTiles[29].isPassable())  Utilities.validateSymmetry(nearbyTiles[29].getMapLocation(), nearbyTiles[29].hasRuin());
                }
                if(nearbyTiles[29].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[29].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[29].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[29].getMapLocation().x;
                    y += nearbyTiles[29].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[30].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[30].getMapLocation().x][nearbyTiles[30].getMapLocation().y] = (nearbyTiles[30].isPassable()) ? 1 : (nearbyTiles[30].isWall()) ? 2 : 3;
                    if(!nearbyTiles[30].isPassable())  Utilities.validateSymmetry(nearbyTiles[30].getMapLocation(), nearbyTiles[30].hasRuin());
                }
                if(nearbyTiles[30].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[30].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[30].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[30].getMapLocation().x;
                    y += nearbyTiles[30].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[31].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[31].getMapLocation().x][nearbyTiles[31].getMapLocation().y] = (nearbyTiles[31].isPassable()) ? 1 : (nearbyTiles[31].isWall()) ? 2 : 3;
                    if(!nearbyTiles[31].isPassable())  Utilities.validateSymmetry(nearbyTiles[31].getMapLocation(), nearbyTiles[31].hasRuin());
                }
                if(nearbyTiles[31].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[31].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[31].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[31].getMapLocation().x;
                    y += nearbyTiles[31].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[32].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[32].getMapLocation().x][nearbyTiles[32].getMapLocation().y] = (nearbyTiles[32].isPassable()) ? 1 : (nearbyTiles[32].isWall()) ? 2 : 3;
                    if(!nearbyTiles[32].isPassable())  Utilities.validateSymmetry(nearbyTiles[32].getMapLocation(), nearbyTiles[32].hasRuin());
                }
                if(nearbyTiles[32].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[32].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[32].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[32].getMapLocation().x;
                    y += nearbyTiles[32].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[33].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[33].getMapLocation().x][nearbyTiles[33].getMapLocation().y] = (nearbyTiles[33].isPassable()) ? 1 : (nearbyTiles[33].isWall()) ? 2 : 3;
                    if(!nearbyTiles[33].isPassable())  Utilities.validateSymmetry(nearbyTiles[33].getMapLocation(), nearbyTiles[33].hasRuin());
                }
                if(nearbyTiles[33].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[33].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[33].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[33].getMapLocation().x;
                    y += nearbyTiles[33].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[34].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[34].getMapLocation().x][nearbyTiles[34].getMapLocation().y] = (nearbyTiles[34].isPassable()) ? 1 : (nearbyTiles[34].isWall()) ? 2 : 3;
                    if(!nearbyTiles[34].isPassable())  Utilities.validateSymmetry(nearbyTiles[34].getMapLocation(), nearbyTiles[34].hasRuin());
                }
                if(nearbyTiles[34].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[34].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[34].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[34].getMapLocation().x;
                    y += nearbyTiles[34].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[35].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[35].getMapLocation().x][nearbyTiles[35].getMapLocation().y] = (nearbyTiles[35].isPassable()) ? 1 : (nearbyTiles[35].isWall()) ? 2 : 3;
                    if(!nearbyTiles[35].isPassable())  Utilities.validateSymmetry(nearbyTiles[35].getMapLocation(), nearbyTiles[35].hasRuin());
                }
                if(nearbyTiles[35].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[35].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[35].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[35].getMapLocation().x;
                    y += nearbyTiles[35].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[36].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[36].getMapLocation().x][nearbyTiles[36].getMapLocation().y] = (nearbyTiles[36].isPassable()) ? 1 : (nearbyTiles[36].isWall()) ? 2 : 3;
                    if(!nearbyTiles[36].isPassable())  Utilities.validateSymmetry(nearbyTiles[36].getMapLocation(), nearbyTiles[36].hasRuin());
                }
                if(nearbyTiles[36].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[36].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[36].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[36].getMapLocation().x;
                    y += nearbyTiles[36].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[37].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[37].getMapLocation().x][nearbyTiles[37].getMapLocation().y] = (nearbyTiles[37].isPassable()) ? 1 : (nearbyTiles[37].isWall()) ? 2 : 3;
                    if(!nearbyTiles[37].isPassable())  Utilities.validateSymmetry(nearbyTiles[37].getMapLocation(), nearbyTiles[37].hasRuin());
                }
                if(nearbyTiles[37].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[37].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[37].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[37].getMapLocation().x;
                    y += nearbyTiles[37].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[38].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[38].getMapLocation().x][nearbyTiles[38].getMapLocation().y] = (nearbyTiles[38].isPassable()) ? 1 : (nearbyTiles[38].isWall()) ? 2 : 3;
                    if(!nearbyTiles[38].isPassable())  Utilities.validateSymmetry(nearbyTiles[38].getMapLocation(), nearbyTiles[38].hasRuin());
                }
                if(nearbyTiles[38].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[38].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[38].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[38].getMapLocation().x;
                    y += nearbyTiles[38].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[39].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[39].getMapLocation().x][nearbyTiles[39].getMapLocation().y] = (nearbyTiles[39].isPassable()) ? 1 : (nearbyTiles[39].isWall()) ? 2 : 3;
                    if(!nearbyTiles[39].isPassable())  Utilities.validateSymmetry(nearbyTiles[39].getMapLocation(), nearbyTiles[39].hasRuin());
                }
                if(nearbyTiles[39].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[39].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[39].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[39].getMapLocation().x;
                    y += nearbyTiles[39].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[40].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[40].getMapLocation().x][nearbyTiles[40].getMapLocation().y] = (nearbyTiles[40].isPassable()) ? 1 : (nearbyTiles[40].isWall()) ? 2 : 3;
                    if(!nearbyTiles[40].isPassable())  Utilities.validateSymmetry(nearbyTiles[40].getMapLocation(), nearbyTiles[40].hasRuin());
                }
                if(nearbyTiles[40].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[40].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[40].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[40].getMapLocation().x;
                    y += nearbyTiles[40].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[41].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[41].getMapLocation().x][nearbyTiles[41].getMapLocation().y] = (nearbyTiles[41].isPassable()) ? 1 : (nearbyTiles[41].isWall()) ? 2 : 3;
                    if(!nearbyTiles[41].isPassable())  Utilities.validateSymmetry(nearbyTiles[41].getMapLocation(), nearbyTiles[41].hasRuin());
                }
                if(nearbyTiles[41].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[41].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[41].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[41].getMapLocation().x;
                    y += nearbyTiles[41].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[42].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[42].getMapLocation().x][nearbyTiles[42].getMapLocation().y] = (nearbyTiles[42].isPassable()) ? 1 : (nearbyTiles[42].isWall()) ? 2 : 3;
                    if(!nearbyTiles[42].isPassable())  Utilities.validateSymmetry(nearbyTiles[42].getMapLocation(), nearbyTiles[42].hasRuin());
                }
                if(nearbyTiles[42].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[42].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[42].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[42].getMapLocation().x;
                    y += nearbyTiles[42].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[43].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[43].getMapLocation().x][nearbyTiles[43].getMapLocation().y] = (nearbyTiles[43].isPassable()) ? 1 : (nearbyTiles[43].isWall()) ? 2 : 3;
                    if(!nearbyTiles[43].isPassable())  Utilities.validateSymmetry(nearbyTiles[43].getMapLocation(), nearbyTiles[43].hasRuin());
                }
                if(nearbyTiles[43].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[43].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[43].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[43].getMapLocation().x;
                    y += nearbyTiles[43].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[44].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[44].getMapLocation().x][nearbyTiles[44].getMapLocation().y] = (nearbyTiles[44].isPassable()) ? 1 : (nearbyTiles[44].isWall()) ? 2 : 3;
                    if(!nearbyTiles[44].isPassable())  Utilities.validateSymmetry(nearbyTiles[44].getMapLocation(), nearbyTiles[44].hasRuin());
                }
                if(nearbyTiles[44].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[44].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[44].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[44].getMapLocation().x;
                    y += nearbyTiles[44].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[45].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[45].getMapLocation().x][nearbyTiles[45].getMapLocation().y] = (nearbyTiles[45].isPassable()) ? 1 : (nearbyTiles[45].isWall()) ? 2 : 3;
                    if(!nearbyTiles[45].isPassable())  Utilities.validateSymmetry(nearbyTiles[45].getMapLocation(), nearbyTiles[45].hasRuin());
                }
                if(nearbyTiles[45].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[45].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[45].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[45].getMapLocation().x;
                    y += nearbyTiles[45].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[46].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[46].getMapLocation().x][nearbyTiles[46].getMapLocation().y] = (nearbyTiles[46].isPassable()) ? 1 : (nearbyTiles[46].isWall()) ? 2 : 3;
                    if(!nearbyTiles[46].isPassable())  Utilities.validateSymmetry(nearbyTiles[46].getMapLocation(), nearbyTiles[46].hasRuin());
                }
                if(nearbyTiles[46].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[46].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[46].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[46].getMapLocation().x;
                    y += nearbyTiles[46].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[47].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[47].getMapLocation().x][nearbyTiles[47].getMapLocation().y] = (nearbyTiles[47].isPassable()) ? 1 : (nearbyTiles[47].isWall()) ? 2 : 3;
                    if(!nearbyTiles[47].isPassable())  Utilities.validateSymmetry(nearbyTiles[47].getMapLocation(), nearbyTiles[47].hasRuin());
                }
                if(nearbyTiles[47].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[47].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[47].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[47].getMapLocation().x;
                    y += nearbyTiles[47].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[48].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[48].getMapLocation().x][nearbyTiles[48].getMapLocation().y] = (nearbyTiles[48].isPassable()) ? 1 : (nearbyTiles[48].isWall()) ? 2 : 3;
                    if(!nearbyTiles[48].isPassable())  Utilities.validateSymmetry(nearbyTiles[48].getMapLocation(), nearbyTiles[48].hasRuin());
                }
                if(nearbyTiles[48].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[48].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[48].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[48].getMapLocation().x;
                    y += nearbyTiles[48].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[49].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[49].getMapLocation().x][nearbyTiles[49].getMapLocation().y] = (nearbyTiles[49].isPassable()) ? 1 : (nearbyTiles[49].isWall()) ? 2 : 3;
                    if(!nearbyTiles[49].isPassable())  Utilities.validateSymmetry(nearbyTiles[49].getMapLocation(), nearbyTiles[49].hasRuin());
                }
                if(nearbyTiles[49].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[49].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[49].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[49].getMapLocation().x;
                    y += nearbyTiles[49].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[50].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[50].getMapLocation().x][nearbyTiles[50].getMapLocation().y] = (nearbyTiles[50].isPassable()) ? 1 : (nearbyTiles[50].isWall()) ? 2 : 3;
                    if(!nearbyTiles[50].isPassable())  Utilities.validateSymmetry(nearbyTiles[50].getMapLocation(), nearbyTiles[50].hasRuin());
                }
                if(nearbyTiles[50].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[50].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[50].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[50].getMapLocation().x;
                    y += nearbyTiles[50].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[51].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[51].getMapLocation().x][nearbyTiles[51].getMapLocation().y] = (nearbyTiles[51].isPassable()) ? 1 : (nearbyTiles[51].isWall()) ? 2 : 3;
                    if(!nearbyTiles[51].isPassable())  Utilities.validateSymmetry(nearbyTiles[51].getMapLocation(), nearbyTiles[51].hasRuin());
                }
                if(nearbyTiles[51].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[51].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[51].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[51].getMapLocation().x;
                    y += nearbyTiles[51].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[52].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[52].getMapLocation().x][nearbyTiles[52].getMapLocation().y] = (nearbyTiles[52].isPassable()) ? 1 : (nearbyTiles[52].isWall()) ? 2 : 3;
                    if(!nearbyTiles[52].isPassable())  Utilities.validateSymmetry(nearbyTiles[52].getMapLocation(), nearbyTiles[52].hasRuin());
                }
                if(nearbyTiles[52].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[52].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[52].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[52].getMapLocation().x;
                    y += nearbyTiles[52].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[53].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[53].getMapLocation().x][nearbyTiles[53].getMapLocation().y] = (nearbyTiles[53].isPassable()) ? 1 : (nearbyTiles[53].isWall()) ? 2 : 3;
                    if(!nearbyTiles[53].isPassable())  Utilities.validateSymmetry(nearbyTiles[53].getMapLocation(), nearbyTiles[53].hasRuin());
                }
                if(nearbyTiles[53].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[53].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[53].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[53].getMapLocation().x;
                    y += nearbyTiles[53].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[54].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[54].getMapLocation().x][nearbyTiles[54].getMapLocation().y] = (nearbyTiles[54].isPassable()) ? 1 : (nearbyTiles[54].isWall()) ? 2 : 3;
                    if(!nearbyTiles[54].isPassable())  Utilities.validateSymmetry(nearbyTiles[54].getMapLocation(), nearbyTiles[54].hasRuin());
                }
                if(nearbyTiles[54].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[54].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[54].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[54].getMapLocation().x;
                    y += nearbyTiles[54].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[55].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[55].getMapLocation().x][nearbyTiles[55].getMapLocation().y] = (nearbyTiles[55].isPassable()) ? 1 : (nearbyTiles[55].isWall()) ? 2 : 3;
                    if(!nearbyTiles[55].isPassable())  Utilities.validateSymmetry(nearbyTiles[55].getMapLocation(), nearbyTiles[55].hasRuin());
                }
                if(nearbyTiles[55].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[55].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[55].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[55].getMapLocation().x;
                    y += nearbyTiles[55].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[56].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[56].getMapLocation().x][nearbyTiles[56].getMapLocation().y] = (nearbyTiles[56].isPassable()) ? 1 : (nearbyTiles[56].isWall()) ? 2 : 3;
                    if(!nearbyTiles[56].isPassable())  Utilities.validateSymmetry(nearbyTiles[56].getMapLocation(), nearbyTiles[56].hasRuin());
                }
                if(nearbyTiles[56].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[56].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[56].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[56].getMapLocation().x;
                    y += nearbyTiles[56].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[57].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[57].getMapLocation().x][nearbyTiles[57].getMapLocation().y] = (nearbyTiles[57].isPassable()) ? 1 : (nearbyTiles[57].isWall()) ? 2 : 3;
                    if(!nearbyTiles[57].isPassable())  Utilities.validateSymmetry(nearbyTiles[57].getMapLocation(), nearbyTiles[57].hasRuin());
                }
                if(nearbyTiles[57].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[57].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[57].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[57].getMapLocation().x;
                    y += nearbyTiles[57].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[58].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[58].getMapLocation().x][nearbyTiles[58].getMapLocation().y] = (nearbyTiles[58].isPassable()) ? 1 : (nearbyTiles[58].isWall()) ? 2 : 3;
                    if(!nearbyTiles[58].isPassable())  Utilities.validateSymmetry(nearbyTiles[58].getMapLocation(), nearbyTiles[58].hasRuin());
                }
                if(nearbyTiles[58].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[58].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[58].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[58].getMapLocation().x;
                    y += nearbyTiles[58].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[59].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[59].getMapLocation().x][nearbyTiles[59].getMapLocation().y] = (nearbyTiles[59].isPassable()) ? 1 : (nearbyTiles[59].isWall()) ? 2 : 3;
                    if(!nearbyTiles[59].isPassable())  Utilities.validateSymmetry(nearbyTiles[59].getMapLocation(), nearbyTiles[59].hasRuin());
                }
                if(nearbyTiles[59].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[59].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[59].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[59].getMapLocation().x;
                    y += nearbyTiles[59].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[60].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[60].getMapLocation().x][nearbyTiles[60].getMapLocation().y] = (nearbyTiles[60].isPassable()) ? 1 : (nearbyTiles[60].isWall()) ? 2 : 3;
                    if(!nearbyTiles[60].isPassable())  Utilities.validateSymmetry(nearbyTiles[60].getMapLocation(), nearbyTiles[60].hasRuin());
                }
                if(nearbyTiles[60].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[60].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[60].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[60].getMapLocation().x;
                    y += nearbyTiles[60].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[61].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[61].getMapLocation().x][nearbyTiles[61].getMapLocation().y] = (nearbyTiles[61].isPassable()) ? 1 : (nearbyTiles[61].isWall()) ? 2 : 3;
                    if(!nearbyTiles[61].isPassable())  Utilities.validateSymmetry(nearbyTiles[61].getMapLocation(), nearbyTiles[61].hasRuin());
                }
                if(nearbyTiles[61].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[61].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[61].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[61].getMapLocation().x;
                    y += nearbyTiles[61].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[62].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[62].getMapLocation().x][nearbyTiles[62].getMapLocation().y] = (nearbyTiles[62].isPassable()) ? 1 : (nearbyTiles[62].isWall()) ? 2 : 3;
                    if(!nearbyTiles[62].isPassable())  Utilities.validateSymmetry(nearbyTiles[62].getMapLocation(), nearbyTiles[62].hasRuin());
                }
                if(nearbyTiles[62].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[62].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[62].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[62].getMapLocation().x;
                    y += nearbyTiles[62].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[63].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[63].getMapLocation().x][nearbyTiles[63].getMapLocation().y] = (nearbyTiles[63].isPassable()) ? 1 : (nearbyTiles[63].isWall()) ? 2 : 3;
                    if(!nearbyTiles[63].isPassable())  Utilities.validateSymmetry(nearbyTiles[63].getMapLocation(), nearbyTiles[63].hasRuin());
                }
                if(nearbyTiles[63].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[63].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[63].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[63].getMapLocation().x;
                    y += nearbyTiles[63].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[64].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[64].getMapLocation().x][nearbyTiles[64].getMapLocation().y] = (nearbyTiles[64].isPassable()) ? 1 : (nearbyTiles[64].isWall()) ? 2 : 3;
                    if(!nearbyTiles[64].isPassable())  Utilities.validateSymmetry(nearbyTiles[64].getMapLocation(), nearbyTiles[64].hasRuin());
                }
                if(nearbyTiles[64].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[64].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[64].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[64].getMapLocation().x;
                    y += nearbyTiles[64].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[65].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[65].getMapLocation().x][nearbyTiles[65].getMapLocation().y] = (nearbyTiles[65].isPassable()) ? 1 : (nearbyTiles[65].isWall()) ? 2 : 3;
                    if(!nearbyTiles[65].isPassable())  Utilities.validateSymmetry(nearbyTiles[65].getMapLocation(), nearbyTiles[65].hasRuin());
                }
                if(nearbyTiles[65].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[65].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[65].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[65].getMapLocation().x;
                    y += nearbyTiles[65].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[66].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[66].getMapLocation().x][nearbyTiles[66].getMapLocation().y] = (nearbyTiles[66].isPassable()) ? 1 : (nearbyTiles[66].isWall()) ? 2 : 3;
                    if(!nearbyTiles[66].isPassable())  Utilities.validateSymmetry(nearbyTiles[66].getMapLocation(), nearbyTiles[66].hasRuin());
                }
                if(nearbyTiles[66].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[66].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[66].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[66].getMapLocation().x;
                    y += nearbyTiles[66].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[67].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[67].getMapLocation().x][nearbyTiles[67].getMapLocation().y] = (nearbyTiles[67].isPassable()) ? 1 : (nearbyTiles[67].isWall()) ? 2 : 3;
                    if(!nearbyTiles[67].isPassable())  Utilities.validateSymmetry(nearbyTiles[67].getMapLocation(), nearbyTiles[67].hasRuin());
                }
                if(nearbyTiles[67].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[67].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[67].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[67].getMapLocation().x;
                    y += nearbyTiles[67].getMapLocation().y;
                    count++;
                }
                Utilities.attemptCompleteResourcePattern(nearbyTiles[68].getMapLocation());
                if(knownSymmetry == RobotPlayer.Symmetry.Unknown)
                {
                    map[nearbyTiles[68].getMapLocation().x][nearbyTiles[68].getMapLocation().y] = (nearbyTiles[68].isPassable()) ? 1 : (nearbyTiles[68].isWall()) ? 2 : 3;
                    if(!nearbyTiles[68].isPassable())  Utilities.validateSymmetry(nearbyTiles[68].getMapLocation(), nearbyTiles[68].hasRuin());
                }
                if(nearbyTiles[68].getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(nearbyTiles[68].getMapLocation()))
                {
                    if(!needsClearing && nearbyRuin != null && nearbyTiles[68].getMapLocation().isWithinDistanceSquared(nearbyRuin, 8))
                    {
                        needsClearing = true;
                    }
                    x += nearbyTiles[68].getMapLocation().x;
                    y += nearbyTiles[68].getMapLocation().y;
                    count++;
                }
            }
            default -> {
                for(MapInfo tile : nearbyTiles) {
                    Utilities.attemptCompleteResourcePattern(tile.getMapLocation());
                    if(knownSymmetry == RobotPlayer.Symmetry.Unknown) {
                        map[tile.getMapLocation().x][tile.getMapLocation().y] = (tile.isPassable()) ? 1 : (tile.isWall()) ? 2 : 3;
                        if(!tile.isPassable())  Utilities.validateSymmetry(tile.getMapLocation(), tile.hasRuin());
                    }
                    if(tile.getPaint().isEnemy() && !Utilities.basicLocationIsBehindWall(tile.getMapLocation())) {
                        if(!needsClearing && nearbyRuin != null && tile.getMapLocation().isWithinDistanceSquared(nearbyRuin, 8)) {
                            needsClearing = true;
                        }
                        x += tile.getMapLocation().x;
                        y += tile.getMapLocation().y;
                        count++;
                    }
                }
            }
        }
        numEnemyTiles = count;
        averageEnemyPaint = (count == 0) ? null : new MapLocation(x / count, y / count);
    }
}

