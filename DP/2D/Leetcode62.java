package DP;

import java.util.Arrays;

public class Leetcode62 {
    // recursive solution.
    // tc is o(2^(m+n))At each position, you make 2 recursive calls (up and left).
    // The maximum depth of recursion is (m-1) + (n-1) = m+n-2. So in the worst
    // case,
    // you have a binary tree of depth approximately m+n, giving you O(2^(m+n))
    // nodes.
    // sc is for each time stack stores the path length o(m+n);
    private int helper(int m, int n) {
        // base case
        if (m == 0 && n == 0) {
            return 1; // count 1 when reach (0, 0);
        }
        if (m < 0 || n < 0) {
            return 0; // do not go outside grid
        }

        // explore
        int up = helper(m - 1, n);
        int down = helper(m, n - 1);

        // sum all the paths
        return up + down;
    }

    public int uniquePaths(int m, int n) {
        return helper(m - 1, n - 1);
    }

    // memoization solution
    // tc is o(m*n); cause for everycell we compute only one time and store
    // so only m * n unique cells
    // sc is stack o(m + n) + array o(m * n) overall o(m * n);
    private int helper(int m, int n, int[][] dp) {

        // base case
        if (m == 0 && n == 0)
            return 1;
        if (m < 0 || n < 0)
            return 0;

        // if already solved use it for ans
        if (dp[m][n] != -1) {
            return dp[m][n];
        }

        // solving
        int up = helper(m, n - 1, dp);
        int down = helper(m - 1, n, dp);

        // storing the ans
        dp[m][n] = up + down;

        // use the ans
        return dp[m][n];
    }

    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return helper(m - 1, n - 1, dp);
    }

    // tabulation solution (bottom - top)
    // tc is o(m * n)
    // sc is o(m * n)
    public int uniquePaths3(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // base case
                if (i == 0 && j == 0) {
                    dp[0][0] = 1;
                    continue;
                }

                int up = 0;
                int left = 0;

                if (i > 0) { // do not exceed out of greed
                    up = dp[i - 1][j];
                }
                if (j > 0) {
                    left = dp[i][j - 1];
                }
                // add and store
                dp[i][j] = up + left;
            }
        }

        return dp[m - 1][n - 1]; // Return bottom-right cell
    }

}
