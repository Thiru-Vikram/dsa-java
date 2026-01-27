package DP;

import java.util.*;

public class Leetcode120 {

    // recursion sol travel from o to n - 1.
    // tc is o(2^n) cause col is not incresing only rows
    // sc is o(n) n means no of rows.
    private int helper(int i, int j, List<List<Integer>> triangle) {
        int m = triangle.size();

        // base case: reached last row
        if (i == m - 1) {
            return triangle.get(i).get(j);
        }

        // recursively explore both paths
        int down = triangle.get(i).get(j) + helper(i + 1, j, triangle);
        int diagonal = triangle.get(i).get(j) + helper(i + 1, j + 1, triangle);

        return Math.min(down, diagonal);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        // we travel from top to bottom
        return helper(0, 0, triangle);
    }

    // memoization sol from o to (n - 1)
    // tc is o(n * n) and sc is o(n) stack + o(n * n) for array.
    private int helper2(int i, int j, List<List<Integer>> triangle, int[][] dp) {
        int m = triangle.size();

        // base case: reached last row
        if (i == m - 1) {
            return triangle.get(i).get(j);
        }

        // use res if already solved
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // recursively explore both paths
        int down = triangle.get(i).get(j) + helper2(i + 1, j, triangle, dp);
        int diagonal = triangle.get(i).get(j) + helper2(i + 1, j + 1, triangle, dp);

        dp[i][j] = Math.min(down, diagonal);

        return dp[i][j];
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        int m = triangle.size();

        // use m to exclude outofbound for next arr
        int[][] dp = new int[m][m];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        // we travel from top to bottom
        return helper2(0, 0, triangle, dp);
    }

    // tablulation sol reverse n-1 to 0
    // tc is o(n * n) and sc is o(n * n) array
    public int minimumTotal3(List<List<Integer>> triangle) {
        int m = triangle.size();

        int[][] dp = new int[m][m];

        // base is dynamic it can be any ele in last row
        for (int j = 0; j < m; j++) {
            dp[m - 1][j] = triangle.get(m - 1).get(j);
        }

        // iterate from next row
        for (int i = m - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) { // for each row take col ele
                int down = triangle.get(i).get(j) + dp[i + 1][j];
                int diagonal = triangle.get(i).get(j) + dp[i + 1][j + 1];

                dp[i][j] = Math.min(down, diagonal);
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {

    }
}