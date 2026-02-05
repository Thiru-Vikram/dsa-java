package DP.Stocks;

public class Leetcode188 {

    // TC: O(n * 2 * k) = O(n * k), SC: O(2 * k) = O(k)
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        // ahead[buy][cap] - buy: 0 or 1, cap: 0 to k
        int[][] ahead = new int[2][k + 1];
        int[][] curr = new int[2][k + 1];

        // Base case: when idx == n, profit is 0 (already initialized)

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 0; cap <= k; cap++) {

                    if (cap == 0) {
                        curr[buy][cap] = 0;
                        continue;
                    }

                    if (buy == 1) {
                        // Can buy: either buy now or skip
                        curr[buy][cap] = Math.max(
                                -prices[idx] + ahead[0][cap], // buy now
                                0 + ahead[1][cap] // skip
                        );
                    } else {
                        // Can sell: either sell now or skip
                        curr[buy][cap] = Math.max(
                                prices[idx] + ahead[1][cap - 1], // sell now
                                0 + ahead[0][cap] // skip
                        );
                    }
                }
            }

            // Swap arrays
            int[][] temp = ahead;
            ahead = curr;
            curr = temp;
        }

        return ahead[1][k];
    }

}
