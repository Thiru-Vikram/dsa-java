package DP.Subsequences_Subsets;

import java.util.Arrays;

public class Leetcode322 {

    // tc is o(2^n) sc is O(n), n is amount
    private int helper(int idx, int target, int[] coins) {
        // Base case: reached first coin
        if (idx == 0) {
            if (target % coins[0] == 0) {
                return target / coins[0];
            } else {
                return (int) 1e9; // Impossible
            }
        }

        // Don't take current coin
        int notTake = helper(idx - 1, target, coins);

        // Take current coin
        int take = (int) 1e9;
        // check coin < target
        if (coins[idx] <= target) { // add 1 and reduce coin in target and continue same.
            take = 1 + helper(idx, target - coins[idx], coins);
        }

        return Math.min(notTake, take);
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;

        int result = helper(coins.length - 1, amount, coins);
        return result >= 1e9 ? -1 : result;
    }

    // memoization sol
    // tc is o(n * amount) and sc is o(n) + o(n * amount)
    private int helper(int idx, int target, int[] coins, int[][] dp) {
        if (idx == 0) {
            if (target % coins[0] == 0) {
                return target / coins[0];
            } else {
                return (int) 1e9;
            }
        }

        if (dp[idx][target] != -1) {
            return dp[idx][target];
        }

        int notTake = helper(idx - 1, target, coins, dp);

        int take = (int) 1e9;
        if (coins[idx] <= target) {
            take = 1 + helper(idx, target - coins[idx], coins, dp);
        }

        dp[idx][target] = Math.min(notTake, take);
        return dp[idx][target];
    }

    public int coinChange2(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        int n = coins.length;

        int[][] dp = new int[n][amount + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int result = helper(n - 1, amount, coins, dp);
        return result >= 1e9 ? -1 : result;
    }

    // tabulation sol
    // TC: O(n × amount) — nested loops
    // SC: O(n × amount) — DP table
    public int coinChange3(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int target = 0; target <= amount; target++) {
            if (target % coins[0] == 0) {
                dp[0][target] = target / coins[0];
            } else {
                dp[0][target] = (int) 1e9;
            }
        }

        for (int idx = 1; idx < n; idx++) {
            for (int target = 0; target <= amount; target++) {

                int notTake = dp[idx - 1][target];

                int take = (int) 1e9;
                if (coins[idx] <= target) {
                    take = 1 + dp[idx][target - coins[idx]];
                }

                dp[idx][target] = Math.min(notTake, take);
            }
        }

        int res = dp[n - 1][amount];
        return res >= (int) 1e9 ? -1 : res;
    }
}