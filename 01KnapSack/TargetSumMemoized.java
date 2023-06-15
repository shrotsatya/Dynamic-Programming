import java.util.stream.*;
import java.util.*;

class TargetSum {
    public static int findTargetSumWaysRec(int[] arr, int T, int total, int i, int sum, int[][] dp) {
        // If all integers are processed
        if (i == arr.length) {
            // If target is reached
            if (sum == T)
                return 1;
            // If target is not reached
            return 0;
        }

        // If we have solved it earlier, then return the result from memory
        if (dp[i][sum + total] != -1)
            return dp[i][sum + total];

        // Calculate both sub-problems and save the results in the memory
        dp[i][sum + total] = findTargetSumWaysRec(arr, T, total, i + 1, sum - arr[i], dp) +
                findTargetSumWaysRec(arr, T, total, i + 1, sum + arr[i], dp);

        return dp[i][sum + total];
    }

    public static int findTargetSumWays(int[] arr, int T) {
        int total = 0;
        for (int num : arr)
            total += num;

        // If the target can't be generated using the given numbers
        if (total < Math.abs(T))
            return 0;

        // Initialize a lookup table
        int[][] dp = new int[arr.length][2 * total + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return findTargetSumWaysRec(arr, T, total, 0, 0, dp);
    }

    // Driver code
    public static void main(String args[]) {
        int[][] arrs = { { 1 }, { 3, 3, 3, 3 }, { 2, 3, 4, 6, 8, 12 }, { 2, 2, 2, 4, 4, 4, 8, 8, 8 } };
        int[] targets = { 1, 6, 15, 18 };

        // Let's uncomment this to see the benefit of using dynamic programming with
        // tabulation

        // int newArrs[][] = Arrays.copyOf(arrs, arrs.length + 1);
        // newArrs[arrs.length] = new int[]{2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2,
        // 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3};
        // arrs = newArrs;

        // int newTargets[] = Arrays.copyOf(targets, targets.length + 1);
        // newTargets[targets.length] = 42;
        // targets = newTargets;

        for (int i = 0; i < arrs.length; ++i) {
            String a = "[" + arrs[i][0];
            for (int j = 1; j < arrs[i].length; ++j)
                a = a + ", " + arrs[i][j];
            a += "]";
            System.out.println(i + 1 + ".\t Input array: " + a);
            System.out.println("\t Target: " + targets[i]);
            System.out.println(
                    "\t Number of ways in which we can find the target sum: " + findTargetSumWays(arrs[i], targets[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}