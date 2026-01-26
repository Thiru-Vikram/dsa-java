package DP;

import java.util.Arrays;

public class Leetcode64 {

    // Time Complexity: O(2^(m+n)) ← exponential, very slow!
    // Space Complexity: O(m + n) ← recursion stack depth
    private int helper(int m, int n, int[][] grid) {

        if (m < 0 || n < 0) { // path is in valid taking max int value as path sum
            return Integer.MAX_VALUE; // so that in is ignored while taking min value in res
        }

        if (m == 0 && n == 0) {
            return grid[0][0]; // adding the base value to the path value
        }

        // exploring right and down along sum
        int right = helper(m, n - 1, grid);
        int down = helper(m - 1, n, grid);

        // base value + min of (right path, left path)
        return grid[m][n] + Math.min(right, down);

    }

    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        return helper(m - 1, n - 1, grid);
    }

    // memoization solution
    // tc is o(m * n) sc is o(m*n) + o(m + n) overall o(m * n);
    private int helper(int m, int n, int[][] grid, int[][] dp) {

        if (m < 0 || n < 0) { // path is invalid taking max int value as path sum
            return Integer.MAX_VALUE; // ignored this while taking min value in res
        }

        // when u reach base adding the base value to the path value
        if (m == 0 && n == 0) {
            return grid[0][0];
        }

        // checking already solved or not before exploring
        if (dp[m][n] != -1) {
            return dp[m][n];
        }

        // exploring right and down along sum
        int right = helper(m, n - 1, grid, dp);
        int down = helper(m - 1, n, grid, dp);

        // store in dp arr
        dp[m][n] = grid[m][n] + Math.min(right, down);

        return dp[m][n];
    }

    public int minPathSum2(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return helper(m - 1, n - 1, grid, dp);
    }

    // tabulation sol
    // tc is o(m*n) sc is o(m*n);
    public int minPathSum3(int[][] grid) {

        int m = grid.length; // row
        int n = grid[0].length; // col
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];

        // fill frst row as prefix sum
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // fill frst col prefix sum
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // fill reminaing cells
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int right = dp[i - 1][j];
                int down = dp[i][j - 1];

                dp[i][j] = grid[i][j] + Math.min(right, down);
            }
        }

        // ans in bottom - right;
        return dp[m - 1][n - 1];
    }
}
