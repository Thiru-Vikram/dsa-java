package DP.Stocks;

public class Leetcode714 {

    // same as 2 prob ultimate tarscation with only adding fee
    // tc is (n * 2) and sc is o(1).
    public int maxProfit(int[] prices, int fee) {

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
                    curr[j] = Math.max(prices[i] - fee + ahead[1], 0 + ahead[0]);
                }
            }
            ahead = curr;
            curr = new int[2];
        }
        return ahead[1];

    }
}
