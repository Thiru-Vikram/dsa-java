package DP.Stocks;

import java.util.Arrays;

public class Leetcode309 {
    // brute force
    // tc is (2^(n * 2)) and sc is o(n)
    private int helper(int idx, int buy, int[] prices) {
        int n = prices.length;
        if (idx >= n)
            return 0;

        int profit = 0;
        if (buy == 1) {
            profit = Math.max(-prices[idx] + helper(idx + 1, 0, prices),
                    helper(idx + 1, 1, prices));
        } else {
            profit = Math.max(prices[idx] + helper(idx + 2, 1, prices),
                    helper(idx + 1, 0, prices));
        }
        return profit;
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 1)
            return 0;

        return helper(0, 1, prices);
    }

    // memoization sol
    // tc is o(n * 2) and sc is o(n * 2) + o(n)
    private int helper(int idx, int buy, int[] prices, int[][] dp) {
        int n = prices.length;
        if (idx >= n)
            return 0;

        if (dp[idx][buy] != -1) {
            return dp[idx][buy];
        }

        if (buy == 1) {
            dp[idx][buy] = Math.max(-prices[idx] + helper(idx + 1, 0, prices, dp),
                    0 + helper(idx + 1, 1, prices, dp));
        } else {
            dp[idx][buy] = Math.max(prices[idx] + helper(idx + 2, 1, prices, dp),
                    0 + helper(idx + 1, 0, prices, dp));
        }

        return dp[idx][buy];
    }

    public int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n == 1)
            return 0;
        int[][] dp = new int[n][2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return helper(0, 1, prices, dp);
    }

    // tabulation sol
    // tc is O(n) and sc is O(n)
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 2][2];

        // Base cases (already 0 by default, but explicit is clearer)
        dp[n][0] = dp[n][1] = 0;
        dp[n + 1][0] = dp[n + 1][1] = 0; // ← Need this for idx+2 case

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 1) {
                    dp[idx][buy] = Math.max(-prices[idx] + dp[idx + 1][0],
                            dp[idx + 1][1]);
                } else {
                    dp[idx][buy] = Math.max(prices[idx] + dp[idx + 2][1],
                            dp[idx + 1][0]);
                }
            }
        }

        return dp[0][1];
    }

    // space optimization sol
    // tc is O(n) and sc is O(1) only three fixed arrays
    public int maxProfit4(int[] prices) {
        int n = prices.length;
        int[] ahead2 = { 0, 0 }; // dp[idx+2]
        int[] ahead1 = { 0, 0 }; // dp[idx+1]
        int[] curr = { 0, 0 }; // dp[idx]

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 1) {
                    curr[buy] = Math.max(-prices[idx] + ahead1[0],
                            ahead1[1]);
                } else {
                    curr[buy] = Math.max(prices[idx] + ahead2[1],
                            ahead1[0]);
                }
            }
            ahead2 = ahead1;
            ahead1 = curr;
            curr = new int[2];
        }

        return ahead1[1];
    }

}
