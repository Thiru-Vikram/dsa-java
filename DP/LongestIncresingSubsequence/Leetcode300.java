package DP.LongestIncresingSubsequence;

import java.util.Arrays;

public class Leetcode300 {
    // brute force
    // tc is o(2^n) sc is o(n)
    private int helper(int idx, int prev_idx, int[] nums) {
        int n = nums.length;
        // base case
        if (idx == n)
            return 0;

        // explore
        // not take - 0 + move next, prev idx same cause still u didnt take anything
        int len = 0 + helper(idx + 1, prev_idx, nums); // not take
        if (prev_idx == -1 || nums[idx] > nums[prev_idx]) {
            // take - 1 + move next, update curr idx to prev_idx
            // return max in both
            len = Math.max(len, 1 + helper(idx + 1, idx, nums));
        }
        return len;
    }

    public int lengthOfLIS(int[] nums) {
        // starting idx, at start prev idx is -1
        return helper(0, -1, nums);
    }

    // memoization sol
    // tc is o(n * n) sc is o(n * n) array + o(n) stack
    private int helper(int idx, int prev_idx, int[] nums, int[][] dp) {
        int n = nums.length;
        // base case
        if (idx == n)
            return 0;

        // we store every ele ans in next idx so +1
        if (dp[idx][prev_idx + 1] != -1) {
            return dp[idx][prev_idx + 1];
        }

        // explore
        // not take - 0 + move next, prev idx same cause still u didnt take anything
        int len = 0 + helper(idx + 1, prev_idx, nums, dp); // not take
        if (prev_idx == -1 || nums[idx] > nums[prev_idx]) {
            // take - 1 + move next, update curr idx to prev_idx
            // return max in both
            len = Math.max(len, 1 + helper(idx + 1, idx, nums, dp));
        }
        return len;
    }

    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        // cannot store -1 in size for prev idx, so we do coordinate change
        // store -1 in o idx, o in 1, 1 in 2.. so on we need n+1
        int[][] dp = new int[n][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return helper(0, -1, nums, dp);
    }

    // tabulation sol
    // tc is o(n * n) sc is o(n * n)
    public int lengthOfLIS3(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][n + 1];

        // Base case: dp[n][...] = 0 (already initialized)

        // Fill the table from bottom to top
        for (int idx = n - 1; idx >= 0; idx--) {
            for (int prev_idx = idx - 1; prev_idx >= -1; prev_idx--) {

                // Not take current element
                int notTake = dp[idx + 1][prev_idx + 1];

                // Take current element (if valid)
                int take = 0;
                if (prev_idx == -1 || nums[idx] > nums[prev_idx]) {
                    take = 1 + dp[idx + 1][idx + 1];
                }

                dp[idx][prev_idx + 1] = Math.max(notTake, take);
            }
        }

        return dp[0][0]; // Starting from idx=0, prev_idx=-1 (shifted to 0)
    }
}
