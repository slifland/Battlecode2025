from itertools import product
import subprocess

emojiMode = False
emojiMap = {
    'Won': ':heavy_check_mark:',
    'Lost': ':x:',
    'Tied': ':grimacing:',
    'N/A': ':heavy_minus_sign:',
    'Error': ':heavy_exclamation_mark:'
}
errors = []
currentBot = 'Version15'

bots = ['Version14', 'Version11']
botsSet = set(bots)
maps = ['gridworld', 'sayhi', 'Money', 'Mirage', 'Justice', 'gardenworld', 'memstore', 'Thirds', 'TargetPractice', 'Racetrack', 'catface', 'giver', 'quack', 'galaxy', 'leavemealone', 'windmill', 'Piglets2', 'Flower', 'Filter'
       , 'yearofthesnake', 'walalilongla', 'starburst', 'sierpinski', 'sunrise', 'roads', 'rain', 'AlarmClock', 'Barcode', 'BatSignal', 'Brat', 'Bread,' 'Castle', 'Circuit', 'Crab', 'CastleDefense', 'FourCorners', 'Gears', 
       'HungerGames', 'Leaf', 'Oasis', 'Paintball', 'Parking_lot']
mapsSet = set(maps)

matches = set(product(bots, maps))

numWinsMapping = {
    0: 'Lost',
    1: 'Tied',
    2: 'Won',
}


def retrieveGameLength(output):
    startIndex = output.find('wins (round ')
    if startIndex == -1:
        return -1
    endIndex = output.find(')', startIndex)
    if endIndex == -1:
        return -1
    return output[startIndex + len('wins(round ') + 1:endIndex]

def run_match(bot, map):
    print("Running {} vs {} on {}".format(currentBot, bot, map))
    try:
        outputA = str(subprocess.check_output(['./gradlew', 'run', '-PteamA=' + currentBot, '-PteamB=' + bot, '-Pmaps=' + map]))
        outputB = str(subprocess.check_output(['./gradlew', 'run', '-PteamA=' + bot, '-PteamB=' + currentBot, '-Pmaps=' + map]))
    except subprocess.CalledProcessError as exc:
        print("Status: FAIL", exc.returncode, exc.output)
        return 'Error'
    else:
        winAString = '{} (A) wins'.format(currentBot)
        winBString = '{} (B) wins'.format(currentBot)
        loseAString = '{} (B) wins'.format(bot)
        loseBString = '{} (A) wins'.format(bot)

        numWins = 0

        gameLengthA = retrieveGameLength(outputA)
        gameLengthB = retrieveGameLength(outputB)

        if winAString in outputA:
            numWins += 1
        else:
            if not loseAString in outputA:
                return 'Error'
        if winBString in outputB:
            numWins += 1
        else:
            if not loseBString in outputB:
                return 'Error'
        return numWinsMapping[numWins] + ' (' + ', '.join([gameLengthA, gameLengthB]) + ')'


results = {}
# Run matches
for bot, map in matches:
    # Verify match is valid
    if not bot in botsSet or not map in mapsSet:
        errors.append('Unable to parse bot={}, map={}'.format(bot, map))
    # run run_match.py

    results[(bot, map)] = run_match(bot, map)

# Construct table
table = [[results.get((bot, map), 'N/A') for bot in bots] for map in maps]

def replaceWithDictionary(s, mapping):
    for a, b in mapping.items():
        s = s.replace(a, b)
    return s

if emojiMode:
    table = [[replaceWithDictionary(item, emojiMap) for item in row] for row in table]

# Write to file
with open('matches-summary.txt', 'w') as f:
    table = [[''] + bots, [':---:' for i in range(len(bots) + 1)]] + [[map] + row for map, row in zip(maps, table)]
    for line in table:
        f.write('| ')
        f.write(' | '.join(line))
        f.write(' |')
        f.write('\n')
    f.write('\n')
    for error in errors:
        f.write(error)
