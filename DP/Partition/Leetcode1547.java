package DP.Partition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Leetcode1547 {

    // recursion sol
    // tc is o(2^m) sc is o(m), m - no of cuts
    private int helper(int i, int j, int[] cuts) {

        if (i > j)
            return 0;

        int minCost = Integer.MAX_VALUE;
        for (int idx = i; idx <= j; idx++) {
            // cost = len of cur stick + find for left stick + find for right stick
            int cost = cuts[j + 1] - cuts[i - 1] + helper(i, idx - 1, cuts) + helper(idx + 1, j, cuts);
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }

    public int minCost(int n, int[] cuts) {

        // convert from arr to list
        List<Integer> cutsList = new ArrayList<>();
        for (int i = 0; i < cuts.length; i++) {
            cutsList.add(cuts[i]);
        }

        // add starting and ending boundaries
        cutsList.add(0);
        cutsList.add(n);

        Collections.sort(cutsList);

        // convert from list to arr
        int[] cutsArr = new int[cutsList.size()];
        for (int i = 0; i < cutsArr.length; i++) {
            cutsArr[i] = cutsList.get(i);
        }

        int m = cutsArr.length;

        // we start from 1 and m-2 cause both left and right
        // we added the boundary
        return helper(1, m - 2, cutsArr);

    }

    // memoization sol
    // tc is o(m^3) two changing var i,j m^2 + onr forloop o(m)
    // sc is o(m^2) array + stack space
    private int helper2(int i, int j, int[] cuts, int[][] dp) {

        if (i > j)
            return 0;

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int minCost = Integer.MAX_VALUE;
        for (int idx = i; idx <= j; idx++) {
            int cost = cuts[j + 1] - cuts[i - 1] + helper2(i, idx - 1, cuts, dp) + helper2(idx + 1, j, cuts, dp);
            minCost = Math.min(minCost, cost);
        }
        return dp[i][j] = minCost;
    }

    public int minCost2(int n, int[] cuts) {

        List<Integer> cutsList = new ArrayList<>();
        for (int i = 0; i < cuts.length; i++) {
            cutsList.add(cuts[i]);
        }

        cutsList.add(0);
        cutsList.add(n);

        Collections.sort(cutsList);

        int[] cutsArr = new int[cutsList.size()];
        for (int i = 0; i < cutsArr.length; i++) {
            cutsArr[i] = cutsList.get(i);
        }

        // add dp array
        int m = cutsArr.length;
        int[][] dp = new int[m + 1][m + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return helper2(1, m - 2, cutsArr, dp);

    }

    // tabulation sol
    // tc is O(m^3), sc is O(m^2)
    public int minCost3(int n, int[] cuts) {

        List<Integer> cutsList = new ArrayList<>();
        for (int i = 0; i < cuts.length; i++) {
            cutsList.add(cuts[i]);
        }

        cutsList.add(0);
        cutsList.add(n);

        Collections.sort(cutsList);

        int[] cutsArr = new int[cutsList.size()];
        for (int i = 0; i < cutsArr.length; i++) {
            cutsArr[i] = cutsList.get(i);
        }

        int m = cutsArr.length;
        int[][] dp = new int[m][m];

        for (int i = m - 2; i >= 1; i--) {
            for (int j = 1; j <= m - 2; j++) {

                if (i > j)
                    continue;

                int minCost = Integer.MAX_VALUE;
                for (int idx = i; idx <= j; idx++) {
                    int cost = cutsArr[j + 1] - cutsArr[i - 1] + dp[i][idx - 1]
                            + dp[idx + 1][j];
                    minCost = Math.min(minCost, cost);
                }
                dp[i][j] = minCost;
            }
        }
        return dp[1][m - 2];

    }

    public static void main(String[] args) {

    }
}