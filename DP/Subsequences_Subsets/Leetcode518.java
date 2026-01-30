package DP.Subsequences_Subsets;

import java.util.Arrays;

public class Leetcode518 {

    // Recursion
    // tc is o(2^n+target) sc is o(n+target)
    private int helper(int idx, int target, int[] coins) {
        // Base case: found a valid way to make amount 0
        if (target == 0)
            return 1;

        // Base case: reached first coin
        if (idx == 0) {
            return (target % coins[0] == 0) ? 1 : 0;
        }

        // Don't take current coin
        int notTake = helper(idx - 1, target, coins);

        // Take current coin (can take multiple times)
        int take = 0;
        if (coins[idx] <= target) {
            take = helper(idx, target - coins[idx], coins);
        }

        return take + notTake; // SUM of ways
    }

    public int change(int amount, int[] coins) {
        int n = coins.length;
        return helper(n - 1, amount, coins);
    }

    // memoization sol
    // tc is o(n * amount) sc is o(n * m) + o(n + m) stack
    private int helper(int idx, int target, int[] coins, int[][] dp) {
        if (target == 0)
            return 1;

        if (idx == 0) {
            return (target % coins[0] == 0) ? 1 : 0;
        }
        if (dp[idx][target] != -1) {
            return dp[idx][target];
        }
        int notTake = helper(idx - 1, target, coins, dp);

        int take = 0;
        if (coins[idx] <= target) {
            take = helper(idx, target - coins[idx], coins, dp);
        }

        dp[idx][target] = take + notTake;

        return dp[idx][target];
    }

    public int change2(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return helper(n - 1, amount, coins, dp);
    }

    // tabulation sol
    // tc is o(n ^ 2) sc is o(n * amount)
    public int change3(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int t = 0; t <= amount; t++) {
            if (t % coins[0] == 0) {
                dp[0][t] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= amount; j++) {

                int notTake = dp[i - 1][j];
                int take = 0;
                if (coins[i] <= j) {
                    take = dp[i][j - coins[i]];
                }

                dp[i][j] = notTake + take;
            }
        }

        return dp[n - 1][amount];
    }

}
