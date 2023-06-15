import java.util.*;

class SubsetSum {
    public static long countSubsetSum(int[] nums, int targetSum) {
        int numsLen = nums.length;
        // lookup table of size len(nums) x (targetSum + 1)
        long lookup[][] = new long[numsLen][targetSum + 1];
        for (long[] row : lookup)
            Arrays.fill(row, 0);

        // Base case 1
        if (nums[0] == 0)
            lookup[0][0] = 2;
        // Base case 2
        else {
            lookup[0][0] = 1;
            if (nums[0] <= targetSum)
                lookup[0][nums[0]] = 1;
        }

        for (int current = 1; current < numsLen; current++) {
            for (int requiredSum = 0; requiredSum <= targetSum; requiredSum++) {
                // included
                long sum1 = 0;
                if (nums[current] <= requiredSum)
                    sum1 = lookup[current - 1][requiredSum - nums[current]];

                // excluded
                long sum2 = lookup[current - 1][requiredSum];

                lookup[current][requiredSum] = sum1 + sum2;
            }
        }

        return lookup[numsLen - 1][targetSum];
    }

    // Driver code
    public static void main(String[] args) {
        int[][] inputNums = {
                { 1 },
                { 11, 33 },
                { 4, 2, 3 },
                { 1, 4, 2, 3 },
                { 1, 2, 7, 4, 5 },
                { 1, 2, 3, 7 }
        };
        int[] targetSums = { 10, 11, 6, 4, 9, 6 };

        // Let's uncomment this and check the effect of dynamic programming using
        // tabulation
        // int newInputNums[][] = Arrays.copyOf(inputNums, inputNums.length + 1);
        // newInputNums[inputNums.length] = new int[]{1, 4, 6, 7, 8, 9, 10, 11, 16, 17,
        // 18, 21,
        // 23, 25, 26, 28, 34, 35, 36, 38, 39, 40, 41, 42, 44, 47, 50,
        // 51, 54, 55, 61, 62, 63, 65, 69, 71, 72, 73, 75, 76, 78, 79,
        // 80, 82, 83, 84, 85, 86, 88, 90, 91, 92, 93, 94, 98, 99, 100,
        // 101, 103, 104, 106, 109, 116, 118, 119};
        // inputNums = newInputNums;

        // int newTargetSums[] = Arrays.copyOf(targetSums, targetSums.length + 1);
        // newTargetSums[targetSums.length] = 2593;
        // targetSums = newTargetSums;

        int index = 0;
        for (int[] input : inputNums) {
            System.out.println((index + 1) + ".\tnums = " + Arrays.toString(input));
            System.out.println("\ttarget sum = " + targetSums[index]);
            System.out.println("\tTotal number of subsets whose sum is equal to the target sum = "
                    + countSubsetSum(input, targetSums[index]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
            index++;
        }
    }
}