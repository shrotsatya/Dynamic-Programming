import java.util.*;

class SubsetSum {
    public static boolean subsetSumRec(int[] sets, int n, int total) {
        // Base Cases
        if (total == 0)
            return true;

        if (n == 0)
            return false;

        // if last element is greater than total we ignore it
        if (sets[n - 1] > total)
            return subsetSumRec(sets, n - 1, total);

        // We either exclude the element or include the element
        return subsetSumRec(sets, n - 1, total) || subsetSumRec(sets, n - 1, total - sets[n - 1]);
    }

    public static boolean subsetSum(int[] sets, int total) // main function
    {
        int n = sets.length;
        return subsetSumRec(sets, n, total);
    }

    // driver code
    public static void main(String args[]) {
        int[][] inputArr = {
                { 3, 5, 8 },
                { 2, 4, 7 },
                { 2, 3, 5 },
                { 1, 2, 3, 7 },
                { 10, 20, 23, 24 }
        };
        int[] total = { 13, 8, 5, 6, 57 };

        // You can uncomment the lines below and check how this recursive solution
        // causes a time-out

        // int newInputArr[][] = Arrays.copyOf(inputArr, inputArr.length + 1);
        // newInputArr[inputArr.length] = new int[]{0, 1, 4, 6, 7, 8, 9, 10, 11, 16, 17,
        // 18, 21, 23, 25, 26, 28, 34,
        // 35, 36, 38, 39, 40, 41, 42, 44, 47, 50, 51, 54, 55, 61, 62, 63, 65, 69, 71,
        // 72,
        // 73, 75, 76, 78, 79, 80, 82, 83, 84, 85, 86, 88, 90, 91, 92, 93, 94, 98, 99,
        // 100,
        // 101, 103, 104, 106, 109, 116, 118, 119};
        // inputArr = newInputArr;

        // int newTotal[] = Arrays.copyOf(total, total.length + 1);
        // newTotal[total.length] = 2593;
        // total = newTotal;

        String result = new String("");
        for (int i = 0; i < total.length; i++) {
            if (subsetSum(inputArr[i], total[i]))
                result = "Yes.";
            else
                result = "No.";
            System.out.println(i + 1 + ".\tDoes a subset of " + Arrays.toString(inputArr[i]) + " sum up to " + total[i]
                    + "?  " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}