package DP;

import java.util.Arrays;

public class Leetcode63 {

    // brute force recursion sol
    // tc is o(2^(m+n)), sc is o(m+n) path length
    private int helper(int i, int j, int[][] arr) {

        // Out of bounds
        if (i < 0 || j < 0) {
            return 0;
        }

        // Obstacle present
        // check this before reaching the base.
        if (arr[i][j] == 1) {
            return 0;
        }

        // Reached starting point
        if (i == 0 && j == 0) {
            return 1;
        }

        int up = helper(i - 1, j, arr);
        int left = helper(i, j - 1, arr);

        return up + left;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int i = obstacleGrid.length - 1; // last row index
        int j = obstacleGrid[0].length - 1; // last col index

        return helper(i, j, obstacleGrid);
    }

    // memoization sol
    // tc is o(m * n) only one time each cell
    // sc is o(m+n) for stack path len + o(m*n) for dp arr, overall o(m * n)
    private int helper(int i, int j, int[][] arr, int[][] dp) {

        if (i < 0 || j < 0) { // boundary check
            return 0;
        }

        if (arr[i][j] == 1) { // check obstacle before reaching base
            return 0;
        }

        if (i == 0 && j == 0) { // found path add 1
            return 1;
        }

        if (dp[i][j] != -1) { // check already solved or not
            return dp[i][j];
        }

        int up = helper(i - 1, j, arr, dp); // explore
        int down = helper(i, j - 1, arr, dp);

        // store ans
        dp[i][j] = up + down;

        return dp[i][j];

    }

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {

        int i = obstacleGrid.length; // row
        int j = obstacleGrid[0].length; // col

        int[][] dp = new int[i][j];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return helper(i - 1, j - 1, obstacleGrid, dp);

    }

    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {

        int r = obstacleGrid.length;
        int c = obstacleGrid[0].length;

        int[][] dp = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {

                if (obstacleGrid[i][j] == 1) { // check obstacle before base
                    dp[i][j] = 0;
                    continue;
                }

                if (i == 0 && j == 0) { // base case
                    dp[0][0] = 1;
                    continue;
                }

                int up = 0;
                int left = 0;

                if (i > 0) {
                    up = dp[i - 1][j];
                }

                if (j > 0) {
                    left = dp[i][j - 1];
                }

                dp[i][j] = up + left;
            }
        }

        return dp[r - 1][c - 1];
    }

    public static void main(String[] args) {

    }
}