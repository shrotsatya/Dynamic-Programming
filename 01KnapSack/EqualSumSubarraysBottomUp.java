import java.util.Arrays;

class EqualSumSubArray {
    public static boolean canPartitionArray(int[] nums) {
        int arraySum = 0;
        int targetSum = 0;
        for (int i : nums) {
            arraySum += i;
        }

        // if 'arraySum' is an odd number, we can't have
        // two subarrays with equal sum
        if (arraySum % 2 != 0) {
            return false;
        } else {
            // We are trying to find a subarray of given numbers
            // that has a total sum of 'arraySum / 2'.
            targetSum = arraySum / 2;
        }
        int numsLen = nums.length;
        // Making a 2-D table
        boolean dp[][] = new boolean[numsLen + 1][targetSum + 1];

        // Intializng the first row with False and first column with True.
        for (int i = 0; i < numsLen + 1; i++) {
            for (int j = 0; j < targetSum + 1; j++) {
                if ((i == 0) && (j == 0))
                    dp[i][j] = true;
                if (i == 0)
                    dp[i][j] = false;
                if (j == 0)
                    dp[i][j] = true;
            }
        }

        // Process all subarrays for all sums
        for (int i = 1; i < numsLen + 1; i++) {
            for (int j = 1; j < targetSum + 1; j++) {
                // if we can find a subset to get the remaining sum
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    // else we can get the sum 'j' without the number at index 'i'
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // return the answer
        return dp[numsLen][targetSum];
    }

    public static void main(String[] args) {
        int[] arr1 = { 3, 1, 1, 2, 2, 1 };
        int[] arr2 = { 1, 3, 7, 3 };
        int[] arr3 = { 1, 2, 3 };
        int[] arr4 = { 1, 2, 5 };
        int[] arr5 = { 1, 3, 4, 8 };
        int[] arr6 = { 1, 2, 3, 2, 3, 5 };
        int[] arr7 = { 1, 5, 3, 2, 3, 19, 3 };
        int[] arr8 = { 1, 2, 3, 5, 3, 2, 1 };
        int[][] array = { arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8 };

        // Let's uncomment this and check the effect of dynamic programming using
        // tabulation

        // int newArr[][] = Arrays.copyOf(array, array.length + 1);
        // newArr[array.length] = new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100,
        // 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
        // 100, 100, 100, 100,
        // 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
        // 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
        // 100,
        // 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
        // 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
        // 100,
        // 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
        // 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
        // 100,
        // 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
        // 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
        // 100,
        // 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
        // 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
        // 100,
        // 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
        // 99, 97};

        // array = newArr;

        for (int i = 0; i < array.length; i++) {
            System.out.print((i + 1) + ".\t Given array: " + Arrays.toString(array[i]));
            System.out.print(
                    "\n\t Can we partition the array into equal sum subarrays? " + canPartitionArray(array[i]) + "\n");
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}