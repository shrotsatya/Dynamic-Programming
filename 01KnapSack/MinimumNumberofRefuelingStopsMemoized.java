import java.util.*;
import java.util.stream.*;

class MinimumRefuelStops {
    // This function finds the maximum distance that can be travelled
    // by making "used" refuelling stops, considering fuel stations from index
    // "index" onwards.
    public static int minRefuelStopsHelper(int index, int used, int curFuel, int[][] stations, int[][] memo) {
        // If no refuelling stops are made, memoize and return the current fuel level.
        if (used == 0) {
            memo[index][used] = curFuel;
            return memo[index][used];
        }

        // If more refuelling stops are made than the number of fuel stations remaining,
        // memoize and return -inf (impossible to reach the target distance).
        if (used > index) {
            memo[index][used] = Integer.MIN_VALUE;
            return memo[index][used];
        }

        // if the solution already exists in the memo
        // return the result of the solution from memo
        if (memo[index][used] != -1)
            return memo[index][used];

        // Consider two options:
        // 1. Don't make a refuelling stop at the current fuel station.
        int result1 = minRefuelStopsHelper(index - 1, used, curFuel, stations, memo);

        // 2. Make a refuelling stop at the current fuel station.
        int result2 = minRefuelStopsHelper(index - 1, used - 1, curFuel, stations, memo);

        // Memoize and return the maximum of the two options, but if the fuel at the
        // current fuel station
        // is not enough to reach the next fuel station, return -inf (impossible to
        // reach the target distance).
        memo[index][used] = Math.max(result1,
                result2 < stations[index - 1][0] ? Integer.MIN_VALUE : result2 + stations[index - 1][1]);

        return memo[index][used];
    }

    // This function finds the minimum number of refuelling stops needed
    // to reach the target distance, given a starting fuel level and a list of fuel
    // stations.
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        // Initialize an array to store the maximum distance that can be travelled
        // for each number of refuelling stops.
        int memo[][] = new int[n + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Find the maximum distance that can be travelled for each number of refuelling
        // stops.
        for (int i = 0; i <= n; i++)
            minRefuelStopsHelper(n, i, startFuel, stations, memo);
        int result = -1;

        // Find the minimum number of refuelling stops needed by iterating over memo
        // and finding the first value that is greater than or equal to the target
        // distance.
        for (int i = 0; i <= n; i++) {
            if (memo[n][i] >= target) {
                result = i;
                break;
            }
        }

        return result;
    }

    // driver code
    public static void main(String[] args) {
        int[] target = { 3, 120, 15, 570, 1360 };
        int[] startFuel = { 3, 10, 3, 140, 380 };
        int[][][] stations = {
                {}, { { 10, 60 }, { 20, 25 }, { 30, 30 }, { 60, 40 } },
                { { 2, 5 }, { 3, 1 }, { 6, 3 }, { 12, 6 } },
                { { 140, 200 }, { 160, 130 }, { 310, 200 }, { 330, 250 } },
                { { 310, 160 }, { 380, 620 }, { 700, 89 }, { 850, 190 }, { 990, 360 } }
        };

        // Let's uncomment this and check the effect of dynamic programming using
        // memoization

        // int newTarget[] = Arrays.copyOf(target, target.length + 1);
        // newTarget[target.length] = 1000000;
        // target = newTarget;

        // int newStartFuel[] = Arrays.copyOf(startFuel, startFuel.length + 1);
        // newStartFuel[startFuel.length] = 414538;
        // startFuel = newStartFuel;

        // int newStations[][][] = Arrays.copyOf(stations, stations.length + 1);
        // newStations[stations.length] = new int[][]{{17701, 258307}, {21688, 120216},
        // {25838, 188823}, {26198, 37704}, {28407, 39718}, {31145, 278840}, {45988,
        // 57039}, {47692, 29551}, {50066, 74074}, {68763, 290134}, {75654, 319564},
        // {108910, 149624}, {142069, 96704}, {150496, 373854}, {155633, 381976},
        // {161109, 233140}, {171483, 222053}, {198121, 90013}, {198558, 50745},
        // {210319, 361266}, {261641, 320131}, {268104, 196397}, {277486, 181545},
        // {279048, 87773}, {284251, 109405}, {284873, 194818}, {299812, 44825},
        // {312794, 212098}, {330372, 150854}, {334304, 16462}, {341826, 355076},
        // {354100, 121729}, {357262, 99472}, {373407, 246231}, {380812, 391068},
        // {381660, 58027}, {389426, 16384}, {395377, 184947}, {400549, 61831}, {401765,
        // 19042}, {402418, 342650}, {408596, 88962}, {409064, 58385}, {412807, 242383},
        // {419216, 114847}, {427637, 193263}, {432402, 162662}, {447033, 73018},
        // {448090, 220812}, {485574, 177913}, {493251, 273729}, {530877, 156659},
        // {542882, 246095}, {545263, 265274}, {554612, 139749}, {555368, 283911},
        // {574367, 13098}, {577897, 59461}, {622815, 266350}, {626360, 73504}, {632502,
        // 399587}, {634018, 30667}, {668646, 349506}, {669368, 33506}, {670388,
        // 147943}, {673524, 12301}, {675268, 205610}, {681675, 187082}, {685442,
        // 260254}, {707944, 400378}, {712364, 355269}, {712744, 343848}, {726134,
        // 145162}, {751188, 18215}, {752569, 82417}, {752688, 138680}, {778386,
        // 288719}, {799185, 339462}, {801575, 187526}, {802673, 370065}, {808872,
        // 17555}, {811498, 117063}, {818968, 17978}, {828129, 176546}, {841905,
        // 363935}, {850989, 161126}, {857536, 211958}, {860254, 321030}, {865831,
        // 102775}, {893729,69995}, {903068, 19423}, {905444, 290995}, {914374, 171088},
        // {919957, 97793}, {929407, 307177}, {933008, 235070}, {935948, 115036},
        // {944895, 74525}, {970733, 55200}, {995520, 316123}};
        // stations = newStations;

        for (int i = 0; i < target.length; i++) {
            System.out.print(i + 1);
            System.out.println(".\tStations: " + Arrays.deepToString(stations[i]));
            System.out.println("\tTarget fuel: " + target[i]);
            System.out.println("\tStart fuel: " + startFuel[i]);
            System.out.println(
                    "\n\tMinimum number of Refueling stops: " + minRefuelStops(target[i], startFuel[i], stations[i]));
            Stream.generate(() -> "-").limit(100).forEach(System.out::print);
            System.out.println();
        }
    }
}