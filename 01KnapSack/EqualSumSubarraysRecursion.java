import java.util.Arrays;

class EqualSumSubArray {
    public static boolean canPartitionArray(int[] nums) {
        int numsLen = nums.length;
        int arraySum = 0;
        // calculate the sum of array
        for (int i : nums)
            arraySum += i;
        // if arraySum is odd, it cannot be partitioned into equal sum subarrays
        if (arraySum % 2 != 0)
            return false;
        // calculating the target subarray sum
        int targetSum = arraySum / 2;
        return isSubArraySum(nums, targetSum, numsLen);
    }

    public static boolean isSubArraySum(int[] nums, int targetSum, int numsLen) {
        if (targetSum == 0)
            return true; // subarray formed with required half-sum
        if ((numsLen == 0) || (targetSum < 0))
            return false;

        // here we will perform two operations:
        // 1. include the current element therefore 'targetSum'
        // will be updated as 'targetSum - current element'
        // or
        // 2. exclude current element therefore no need to update 'targetSum'
        boolean result = isSubArraySum(nums, targetSum, numsLen - 1)
                || isSubArraySum(nums, targetSum - nums[numsLen - 1], numsLen - 1);
        return result;
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

        // You can uncomment the lines below and check how this recursive solution
        // causes a time-out

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
            System.out.print(i + 1);
            System.out.print(".\t Given array: " + Arrays.toString(array[i]));
            System.out.print(
                    "\n\t Can we partition the array into equal sum subarrays? " + canPartitionArray(array[i]) + "\n");
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}