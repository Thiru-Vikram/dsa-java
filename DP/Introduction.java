package DP;

import java.util.Arrays;

public class Introduction {

    // 1. Pure Recursion
    public int fibonaci(int n) {
        if (n <= 1)
            return n;
        return fibonaci(n - 1) + fibonaci(n - 2);
    }
    // TC: O(2^n) - Not O(n)! Each call branches into 2 more calls.
    // SC: O(n) - Maximum recursion depth.

    // 2. Memoization (Top-Down DP)
    public int dpFibonaci(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return dpFibonaciHelper(n, dp);
    }

    private int dpFibonaciHelper(int n, int[] dp) {
        if (n <= 1)
            return n;
        if (dp[n] != -1)
            return dp[n];
        return dp[n] = dpFibonaciHelper(n - 1, dp) + dpFibonaciHelper(n - 2, dp);
    }
    // TC: O(n) ✓ - Each subproblem computed once
    // SC: O(n) + O(n) ✓ - Array + recursion stack

    // 3. Tabulation (Bottom-Up DP)
    public int tabFibonaci(int n) {
        if (n <= 1)
            return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    // TC: O(n) ✓
    // SC: O(n) ✓ - No recursion stack

    // 4. Space Optimized
    public int optimalFibonaci(int n) {
        if (n <= 1)
            return n;
        int prev2 = 0;
        int prev = 1;
        for (int i = 2; i <= n; i++) {
            int curi = prev2 + prev;
            prev2 = prev;
            prev = curi;
        }
        return prev;
    }
    // TC: O(n) ✓
    // SC: O(1) ✓

}
