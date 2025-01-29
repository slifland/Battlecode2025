package Version15.Robots;

import Version15.Pathfinding.Pathfinding;
import Version15.Utility.*;

import battlecode.common.*;
import static Version15.RobotPlayer.*;

public class HunterSplasher
{
    enum HunterState
    {
        Explore, Hunt
    }

    static HunterState currentState = HunterState.Explore;
    static MapLocation randomExploreLocation = null;
    static Symmetry.SymmetryType currentSymmetry;
    static FastIterableLocSet splashedRuins = new FastIterableLocSet();
    static MapLocation ruinToSplash;

    public static void runHunterSplasher() throws GameActionException
    {
        updateInfo();

        if(ruinToSplash != null)
        {
            currentState = HunterState.Hunt;
        }
        else
        {
            currentState = HunterState.Explore;
        }

        switch (currentState)
        {
            case Explore -> explore();
            case Hunt -> hunt();
        }
    }

    static void explore() throws GameActionException
    {
        MapLocation[] possibleRuins = Symmetry.possibleRuins(currentSymmetry);

        int closestDistance = Integer.MAX_VALUE;
        MapLocation closestLocation = null;
        for(MapLocation possibleRuin : possibleRuins)
        {
            if(splashedRuins.contains(possibleRuin)) continue;
            int distance = possibleRuin.distanceSquaredTo(rc.getLocation());
            if(distance < closestDistance)
            {
                closestDistance = distance;
                closestLocation = possibleRuin;
            }
        }

        Direction dirToMove;
        if(closestLocation != null)
            dirToMove = Pathfinding.bugBFS(closestLocation);
        else
            dirToMove = Pathfinding.bugBFS(randomExploreLocation);

        if(closestLocation != null)
        {
            rc.setIndicatorDot(closestLocation, 255, 0, 0);
        }

        if(rc.canMove(dirToMove))
        {
            rc.move(dirToMove);
        }
    }

    static void hunt() throws GameActionException
    {
        if(rc.canAttack(ruinToSplash))
        {
            rc.attack(ruinToSplash);
            splashedRuins.add(ruinToSplash);
            explore();
            return;
        }

        Direction dirToMove = Pathfinding.bugBFS(ruinToSplash);
        if(rc.canMove(dirToMove))
        {
            rc.move(dirToMove);
        }

        if(rc.canAttack(ruinToSplash))
        {
            splashedRuins.add(ruinToSplash);
            rc.attack(ruinToSplash);
        }
    }


    public static void updateInfo() throws GameActionException
    {
        for(MapInfo tile : nearbyTiles)
        {
            Symmetry.processTile(tile);
        }

        if(Symmetry.knownSymmetry == Symmetry.SymmetryType.Unknown)
        {
            currentSymmetry = Symmetry.possibleSymmetry()[0];
        }
        else
        {
            currentSymmetry = Symmetry.knownSymmetry;
        }

        ruinToSplash = null;
        for(MapLocation ruin : nearbyRuins)
        {
            if(!splashedRuins.contains(ruin))
            {
                if(rc.canSenseRobotAtLocation(ruin) || !shouldSplashRuin(ruin))
                {
                    splashedRuins.add(ruin);
                    break;
                }

                ruinToSplash = ruin;
                break;
            }
        }

        if(randomExploreLocation == null || turnCount % 30 == 0)
        {
            randomExploreLocation = Utilities.generateRandomLocation(rc);
        }
    }

    public static boolean shouldSplashRuin(MapLocation ruinToSplash) throws GameActionException
    {
        MapInfo[] tilesToSplash = rc.senseNearbyMapInfos(ruinToSplash, 8);
        int score = 0;
        for(MapInfo tile : tilesToSplash)
        {
            if(tile.getPaint().isAlly())
            {
                score--;
            }
            else if(tile.getPaint().isEnemy())
            {
                score++;
            }
        }
        return score >= 0;
    }
}
