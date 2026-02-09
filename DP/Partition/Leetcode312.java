package DP.Partition;

import java.util.Arrays;

// same as cut sticks prob litte changes
public class Leetcode312 {

    // TC: O(n³) - O(n²) unique states (i, j) × O(n) inner loop
    // SC: O(n²) for dp array + O(n) stack
    private int helper(int i, int j, int[] nums) {

        if (i > j)
            return 0;
        int maxCoins = 0;
        for (int idx = i; idx <= j; idx++) {
            // coins = left * curr * right ballon coins
            int coins = nums[i - 1] * nums[idx] * nums[j + 1] + helper(i, idx - 1, nums)
                    + helper(idx + 1, j, nums);
            maxCoins = Math.max(maxCoins, coins);
        }
        return maxCoins;
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] numArr = new int[n + 2];
        numArr[0] = numArr[numArr.length - 1] = 1;
        for (int i = 0; i < n; i++) {
            numArr[i + 1] = nums[i];
        }

        int m = numArr.length;
        return helper(1, m - 2, numArr);
    }

    // memoization sol
    // tc is o(n^3) sc is o(n^2) + o(n)
    private int helper2(int i, int j, int[] nums, int[][] dp) {

        if (i > j)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];

        int maxCoins = 0;
        for (int idx = i; idx <= j; idx++) {
            int coins = nums[i - 1] * nums[idx] * nums[j + 1] + helper2(i, idx - 1, nums, dp)
                    + helper2(idx + 1, j, nums, dp);
            maxCoins = Math.max(maxCoins, coins);
        }
        return dp[i][j] = maxCoins;
    }

    public int maxCoins2(int[] nums) {
        int n = nums.length;
        int[] numArr = new int[n + 2];
        numArr[0] = numArr[numArr.length - 1] = 1;
        for (int i = 0; i < n; i++) {
            numArr[i + 1] = nums[i];
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int m = numArr.length;
        return helper2(1, m - 2, numArr, dp);
    }

    // tabulation sol
    // tc is o(n^3) sc is o(n^2)
    public int maxCoins3(int[] nums) {
        int n = nums.length;
        int[] numArr = new int[n + 2];
        numArr[0] = numArr[numArr.length - 1] = 1;
        for (int i = 0; i < n; i++) {
            numArr[i + 1] = nums[i];
        }
        int[][] dp = new int[n + 2][n + 2];
        int m = numArr.length;

        for (int i = m - 2; i >= 1; i--) {
            for (int j = 1; j <= m - 2; j++) {
                if (i > j)
                    continue;

                int maxCoins = 0;
                for (int idx = i; idx <= j; idx++) {
                    int coins = numArr[i - 1] * numArr[idx] * numArr[j + 1] +
                            dp[i][idx - 1] + dp[idx + 1][j];
                    maxCoins = Math.max(maxCoins, coins);
                }
                dp[i][j] = maxCoins;
            }
        }

        return dp[1][m - 2];
    }

}
