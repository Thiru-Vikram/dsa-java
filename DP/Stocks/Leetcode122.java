package DP.Stocks;

import java.util.Arrays;

// Best Time to Buy and Sell Stock II - mutiple transaction return max profit.

public class Leetcode122 {

    // tc is o(2^(n)) sc i o(n)
    private int helper(int i, int j, int[] prices) {
        int n = prices.length;
        // reach end return 0
        if (i == n)
            return 0;
        int profit = 0;

        // 1 - means buy next, 0 - means sell next
        if (j == 1) {
            // buy, u lose money so -price move next sell || not buy, move next buy
            profit = Math.max(-prices[i] + helper(i + 1, 0, prices), 0 + helper(i + 1, 1, prices));
        } else {
            // sell, u gain money so +price move next buy || not sell, move next sell
            profit = Math.max(prices[i] + helper(i + 1, 1, prices), 0 + helper(i + 1, 0, prices));
        }

        return profit;
    }

    public int maxProfit(int[] prices) {

        // starting idx, at start u have nothing so buy 1
        return helper(0, 1, prices);
    }

    // memoization sol
    // tc is o(2 * n) = o(n) and sc is o(n * 2) + o(n) = o(n)
    private int helper(int i, int j, int[] prices, int[][] dp) {
        int n = prices.length;
        // reach end return 0
        if (i == n)
            return 0;

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // 1 - means buy next, 0 - means sell next
        if (j == 1) {
            // buy, u lose money so -price move next sell || not buy, move next buy
            dp[i][j] = Math.max(-prices[i] + helper(i + 1, 0, prices, dp),
                    0 + helper(i + 1, 1, prices, dp));
        } else {
            // sell, u gain money so +price move next buy || not sell, move next sell
            dp[i][j] = Math.max(prices[i] + helper(i + 1, 1, prices, dp),
                    0 + helper(i + 1, 0, prices, dp));
        }

        return dp[i][j];
    }

    public int maxProfit2(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        // starting idx, at start u have nothing, buy = 1
        return helper(0, 1, prices, dp); // f(idx, buy)
    }

    // tabulation sol
    // tc is o(2 * n) = o(n) and sc is o(n * 2) = o(n)
    public int maxProfit3(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n + 1][2];

        dp[n][1] = dp[n][0] = 0; // base case

        for (int i = n - 1; i >= 0; i--) { // idx reverse last -> first
            for (int j = 0; j <= 1; j++) { // buy/sell reverse 0 -> 1
                if (j == 1) {
                    // buy
                    dp[i][j] = Math.max(-prices[i] + dp[i + 1][0], 0 + dp[i + 1][1]);
                } else {
                    // sell
                    dp[i][j] = Math.max(prices[i] + dp[i + 1][1], 0 + dp[i + 1][0]);
                }
            }
        }
        return dp[0][1]; // f(idx, buy)
    }

    // space optimization
    // tc is o(n) and sc is o(1)
    public int maxProfit4(int[] prices) {

        int n = prices.length;
        int[] ahead = { 0, 0 };
        int[] curr = { 0, 0 };

        for (int i = n - 1; i >= 0; i--) { // idx reverse last -> first
            for (int j = 0; j <= 1; j++) { // buy/sell 0 -> 1
                if (j == 1) {
                    // buy
                    curr[j] = Math.max(-prices[i] + ahead[0], 0 + ahead[1]);
                } else {
                    // sell
                    curr[j] = Math.max(prices[i] + ahead[1], 0 + ahead[0]);
                }
            }
            ahead = curr;
            curr = new int[2];
        }
        return ahead[1];
    }
}
