import java.util.*;

class SubsetSum {
    // Main function
    public static long countSubsetSum(int[] nums, int targetSum) {
        return countSubsetSumRec(nums, targetSum, 0);
    }

    // Helper function
    public static long countSubsetSumRec(int[] nums, int targetSum, int currentIndex) {
        // base cases
        if (targetSum == 0)
            return 1;
        if (currentIndex >= nums.length)
            return 0;

        // if the number at currentIndex does not exceed the
        // targetSum, we include it
        long sum1 = 0;
        if (nums[currentIndex] <= targetSum)
            sum1 = countSubsetSumRec(
                    nums,
                    targetSum - nums[currentIndex],
                    currentIndex + 1);

        // recursive call, excluding the number at the currentIndex
        long sum2 = countSubsetSumRec(nums, targetSum, currentIndex + 1);

        return sum1 + sum2;
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

        // You can uncomment the lines below and check how this recursive solution
        // causes a time-out
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