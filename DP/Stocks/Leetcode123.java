package DP.Stocks;

import java.util.Arrays;

// Best Time to Buy and Sell Stock III - at max two transaction return max profit.

public class Leetcode123 {

    // brute force
    // tc is o(2^n * 2 * cap) sc is o(n)
    private int helper(int idx, int buy, int cap, int[] prices) {
        int n = prices.length;
        if (cap == 0)
            return 0;
        if (idx == n)
            return 0;

        int profit = 0;
        // 1 - buy next
        // 0 - sell next
        if (buy == 1) {
            profit = Math.max(-prices[idx] + helper(idx + 1, 0, cap, prices),
                    0 + helper(idx + 1, 1, cap, prices));
        } else {
            profit = Math.max(prices[idx] + helper(idx + 1, 1, cap - 1, prices),
                    0 + helper(idx + 1, 0, cap, prices));
        }

        return profit;
    }

    public int maxProfit(int[] prices) {
        return helper(0, 1, 2, prices);
    }

    // memoization sol
    // tc is o(n * 2 * cap) sc is o(n) + (n * 2 * cap)
    private int helper(int idx, int buy, int cap, int[] prices, int[][][] dp) {
        int n = prices.length;
        if (cap == 0)
            return 0;
        if (idx == n)
            return 0;

        if (dp[idx][buy][cap] != -1) {
            return dp[idx][buy][cap];
        }

        // 1 - buy next
        // 0 - sell next
        if (buy == 1) {
            dp[idx][buy][cap] = Math.max(-prices[idx] + helper(idx + 1, 0, cap, prices, dp),
                    0 + helper(idx + 1, 1, cap, prices, dp));
        } else {
            dp[idx][buy][cap] = Math.max(prices[idx] + helper(idx + 1, 1, cap - 1, prices, dp),
                    0 + helper(idx + 1, 0, cap, prices, dp));
        }

        return dp[idx][buy][cap];
    }

    public int maxProfit2(int[] prices) {
        int n = prices.length;
        // max values idx - n; buy - 0,1; cap - 0,1,2
        int[][][] dp = new int[n][2][3];
        for (int[][] row : dp) {
            for (int[] col : row) {
                Arrays.fill(col, -1);
            }
        }

        return helper(0, 1, 2, prices, dp);
    }

    // tabulation sol
    // tc is o(n * 2 * cap) sc is (n * 2 * cap)
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        // max values idx - n; buy - 0,1; cap - 0,1,2
        int[][][] dp = new int[n + 1][2][3];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 0; cap <= 2; cap++) {

                    if (cap == 0) {
                        dp[idx][buy][cap] = 0;
                        continue;
                    }

                    if (buy == 1) {
                        dp[idx][buy][cap] = Math.max(-prices[idx] + dp[idx + 1][0][cap],
                                0 + dp[idx + 1][1][cap]);
                    } else {
                        dp[idx][buy][cap] = Math.max(prices[idx] + dp[idx + 1][1][cap - 1],
                                0 + dp[idx + 1][0][cap]);
                    }
                }
            }
        }

        return dp[0][1][2];
    }

    // space optimization
    // tc is o(n * 2 * cap) sc is (2 * cap)
    public int maxProfit4(int[] prices) {
        int n = prices.length;
        // max values idx - n; buy - 0,1; cap - 0,1,2
        int[][] ahead = new int[2][3];
        int[][] curr = new int[2][3];

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 0; cap <= 2; cap++) {

                    if (cap == 0) {
                        curr[buy][cap] = 0;
                        continue;
                    }

                    if (buy == 1) {
                        curr[buy][cap] = Math.max(-prices[idx] + ahead[0][cap],
                                0 + ahead[1][cap]);
                    } else {
                        curr[buy][cap] = Math.max(prices[idx] + ahead[1][cap - 1],
                                0 + ahead[0][cap]);
                    }
                }
            }
            ahead = curr;
            curr = new int[2][3];
        }

        return ahead[1][2];
    }

}
