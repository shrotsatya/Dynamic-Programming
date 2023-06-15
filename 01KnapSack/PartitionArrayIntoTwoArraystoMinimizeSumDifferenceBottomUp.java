class MinimumPartitionArraySumDifference {

    public static int minimumPartitionArraySumDifference(int[] nums) {

        // Calculating the sum of the original array
        int sumArray = 0;

        for (int i = 0; i < nums.length; i++) {
            sumArray = sumArray + nums[i];
        }

        // Calculating the number of rows and columns in the 2-D array
        int rows = nums.length;
        int cols = (int) (Math.floor(sumArray / 2) + 1);

        int[][] dp = new int[rows][cols];

        // Initializing the 2-D array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dp[i][j] = -1;
            }
        }

        // The first column will be initialized to all 1s, since a sum s = 0
        // will always be true if no elements are added to the subset
        for (int i = 0; i < rows; i++) {
            dp[i][0] = 1;
        }

        // For the first row, each entry will be 1 if the sum s is equal to the
        // first element, and 0 otherwise
        for (int s = 1; s < cols; s++) {
            int element = (nums[0] == s) ? 1 : 0;
            dp[0][s] = element;
        }

        // Iterating and filling the dp array
        for (int i = 1; i < rows; i++) {
            for (int s = 1; s < cols; s++) {
                // Check if sum s can be obtained without nums[i] in the array
                if (dp[i - 1][s] == 1)
                    dp[i][s] = dp[i - 1][s];

                // Check if sum s can be obtained with nums[i] in the array
                else if (s >= nums[i])
                    dp[i][s] = dp[i - 1][s - nums[i]];

                // If neither of the above two conditions is true, sum s can not be
                // obtained with nums[i] included in the array
                else
                    dp[i][s] = 0;
            }
        }

        // Find the largest index in the last row which is 1 and return the absolute
        // difference between the two sums
        int result = 0;
        for (int s = cols - 1; s >= 0; s--) {
            if (dp[rows - 1][s] == 1) {
                int sum1 = s;
                int sum2 = sumArray - sum1;
                result = Math.abs(sum2 - sum1);
                break;
            }
        }
        return result;
    }

    // Driver Code
    public static void main(String[] args) {

        int[][] inputs = { { 5, 4, 4, 7, 2, 9 }, { 3, 25, 4, 12, 2 }, { 3, 7, 4, 12, 2 },
                { 1, 1, 1, 1000, 1 }, { 45, 2, 9, 87, 9, 12, 54, 56 } };

        // Let's uncomment this and check the effect of dynamic programming using
        // tabulation
        // int temp[][] = Arrays.copyOf(inputs, inputs.length + 1);
        // temp[inputs.length] = new int[]{28, 20, 19, 39, 51, 92, 41, 9, 79, 46,
        // 63, 77, 65, 10, 24, 5, 92, 2, 39, 68, 99, 60, 69, 88, 93, 99, 51, 44,
        // 67, 2, 90, 8, 9, 89, 48, 76, 58, 87, 77, 62, 22, 92, 86, 86, 46, 13,
        // 12, 75, 38, 77, 99, 6, 57, 71, 9, 23, 97, 80, 97, 69, 68, 28, 46, 16,
        // 25, 61, 80, 76, 35, 23, 8, 4, 19, 56, 58, 54, 77, 77, 41, 73, 95, 93,
        // 35, 70, 74, 90, 96, 46, 63, 32, 40, 32, 50, 2, 60, 73, 87, 61, 60, 42};
        // inputs = temp;

        for (int i = 0; i < inputs.length; i++) {
            System.out.println(i + 1 + ".\tnums: " + Arrays.toString(inputs[i]) +
                    "\n\n\tThe minimum difference in sums between the partitioned arrays is: "
                    + minimumPartitionArraySumDifference(inputs[i]));

            System.out.println(PrintHyphens.repeat("-", 100));
        }
    }
}