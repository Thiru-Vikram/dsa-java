package DP.Strings;

import java.util.Arrays;

public class Leetcode115 {

    private int helper(int i, int j, String s1, String s2, int[][] dp) {
        if (j < 0)
            return 1; // Found complete match of s2
        if (i < 0)
            return 0; // Exhausted s1 without matching all of s2

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            dp[i][j] = helper(i - 1, j - 1, s1, s2, dp) + helper(i - 1, j, s1, s2, dp);
        } else {
            dp[i][j] = helper(i - 1, j, s1, s2, dp);
        }

        return dp[i][j];
    }

    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m][n]; // ✓ CORRECT: dp[i][j] where i < m, j < n

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return helper(m - 1, n - 1, s, t, dp);
    }

    // memoization sol
    // Time: O(m × n) - each state computed once
    // Space: O(m × n) for DP array + O(m + n) for recursion stack
    private int helper2(int i, int j, String s1, String s2, int[][] dp) {
        if (j < 0)
            return 1; // CHECK J FIRST! Found complete match of s2
        if (i < 0)
            return 0; // Exhausted s1 without matching all of s2

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {// matches
            // try two possible way use this or move back and find another
            return dp[i][j] = (helper2(i - 1, j - 1, s1, s2, dp) + helper2(i - 1, j, s1, s2, dp));
        } else {
            // move back try
            return dp[i][j] = helper2(i - 1, j, s1, s2, dp);
        }
    }

    public int numDistinct2(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return helper2(m - 1, n - 1, s, t, dp);
    }

    // tabulation sol
    // Time: O(m × n) - each state computed once
    // Space: O(m × n) for DP array
    public int numDistinct3(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[][] dp = new int[m + 1][n + 1];

        // Base case: empty t can be matched in exactly 1 way (select nothing)
        for (int i = 0; i <= m; i++)
            dp[i][0] = 1;

        // dp[0][j] for j > 0 remains 0 (empty s cannot match non-empty t)
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // Use this character + don't use this character
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    // Skip this character in s
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }

}
