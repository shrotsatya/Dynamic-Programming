import java.util.stream.*;
import java.util.*;

class TargetSum {
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
            Arrays.fill(row, 0);
        dp[0][total + arr[0]] = 1;
        dp[0][total - arr[0]] += 1;

        // For every integer
        for (int i = 1; i < arr.length; ++i)
            // For every possible target sum
            for (int t = -total; t < total + 1; ++t)
                // If at least one expression (during previous iterations) evaluated to this
                // target sum
                if (dp[i - 1][total + t] > 0) {
                    dp[i][total + t + arr[i]] += dp[i - 1][total + t];
                    dp[i][total + t - arr[i]] += dp[i - 1][total + t];
                }

        return dp[arr.length - 1][T + total];
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