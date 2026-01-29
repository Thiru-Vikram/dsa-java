package DP.Strings;

import java.util.*;

public class Leetcode1143 {

    // brute force.
    // tc is o(2^(n + m)), sc is o(n + m) stack
    private int helper(int idx1, int idx2, String s1, String s2) {
        // if u out of idx add 0
        if (idx1 < 0 || idx2 < 0) {
            return 0;
        }

        // if both char of each str matches add 1
        // reduce idx for both
        if (s1.charAt(idx1) == (s2.charAt(idx2))) {
            return 1 + helper(idx1 - 1, idx2 - 1, s1, s2);
        }

        // if str not matches try two differnt ways take and not take
        // return max from that two path
        return Math.max(helper(idx1 - 1, idx2, s1, s2), helper(idx1, idx2 - 1, s1, s2));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        return helper(n - 1, m - 1, text1, text2);
    }

    // memiozation sol
    // tc is o(n * m), sc is o(n + m) stack + o(n * m) arr.
    private int helper(int idx1, int idx2, String s1, String s2, int[][] dp) {
        // if u out of idx add 0
        if (idx1 < 0 || idx2 < 0) {
            return 0;
        }

        // check before exploring
        if (dp[idx1][idx2] != -1) {
            return dp[idx1][idx2];
        }

        // if matches add 1 or try two diff paths
        if (s1.charAt(idx1) == s2.charAt(idx2)) {
            dp[idx1][idx2] = 1 + helper(idx1 - 1, idx2 - 1, s1, s2, dp);
        } else {
            dp[idx1][idx2] = Math.max(
                    helper(idx1 - 1, idx2, s1, s2, dp),
                    helper(idx1, idx2 - 1, s1, s2, dp));
        }

        // return ans
        return dp[idx1][idx2];
    }

    public int longestCommonSubsequence2(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n][m];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return helper(n - 1, m - 1, text1, text2, dp);
    }

    // tabulation sol
    // tc is o(n * m), sc is o(n * m)
    public int longestCommonSubsequence3(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {

                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {

    }
}