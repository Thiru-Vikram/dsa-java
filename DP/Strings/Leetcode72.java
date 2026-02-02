package DP.Strings;

import java.util.Arrays;

public class Leetcode72 {

    // bruteforce
    // tc is o(3^(n+m)) sc is (n + m)
    private int helper(int i, int j, String s1, String s2) {

        // if s1 exhasuted insert remaining s2 ele to match s1
        if (i < 0)
            return j + 1;

        // if s2 exhausted means del remaining s1 ele to match s2
        if (j < 0)
            return i + 1;

        if (s1.charAt(i) == s2.charAt(j)) {
            return 0 + helper(i - 1, j - 1, s1, s2);
        } else {
            // insert means hypothetically inserted j char at i move j
            return Math.min(1 + helper(i, j - 1, s1, s2),
                    Math.min(1 + helper(i - 1, j, s1, s2), 1 + helper(i - 1, j - 1, s1, s2)));
            // delete means del s1 char move i, replace means u matched it move both
            // return min of three operations
        }
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        return helper(m - 1, n - 1, word1, word2);
    }

    // memoization
    // tc is o(m * n) sc is (n + m) + o(m * n)
    private int helper(int i, int j, String s1, String s2, int[][] dp) {

        // if s1 exhasuted insert remaining s2 ele to match s1
        if (i < 0)
            return j + 1;

        // if s2 exhausted means del remaining s1 ele to match s2
        if (j < 0)
            return i + 1;

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            dp[i][j] = 0 + helper(i - 1, j - 1, s1, s2, dp);
        } else {
            // insert, delete, replace
            dp[i][j] = Math.min(1 + helper(i, j - 1, s1, s2, dp),
                    Math.min(1 + helper(i - 1, j, s1, s2, dp), 1 + helper(i - 1, j - 1, s1, s2, dp)));
        }

        return dp[i][j];
    }

    public int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return helper(m - 1, n - 1, word1, word2, dp);
    }

    // tabulation
    // tc is o(m*n) sc is o(m*n)
    public int minDistance3(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Base case: i == 0 (word1 is empty)
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // Need j insertions
        }

        // Base case: j == 0 (word2 is empty)
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // Need i deletions
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // No operation needed
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i][j - 1], // Insert
                            Math.min(
                                    dp[i - 1][j], // Delete
                                    dp[i - 1][j - 1] // Replace
                            ));
                }
            }
        }

        return dp[m][n];
    }

}
