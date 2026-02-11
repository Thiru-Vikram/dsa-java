package DP.Partition;

import java.util.Arrays;

public class Leetcode1043 {

    // recursion sol
    // tc is o(k^n) sc is o(n)
    private int helper(int i, int k, int[] arr) {
        int n = arr.length;
        if (i == n)
            return 0;
        int len = 0;
        int maxEle = Integer.MIN_VALUE;
        int maxSum = 0;
        // starts from i to min of k idx or total len
        for (int j = i; j < Integer.min(n, i + k); j++) {
            len++;
            maxEle = Math.max(maxEle, arr[j]);
            // adding cur sum and moving next partition j +1
            int sum = (len * maxEle) + helper(j + 1, k, arr);
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public int maxSumAfterPartitioning(int[] arr, int k) {
        return helper(0, k, arr);

    }

    // memoization sol
    // tc is o(n * k) sc is o(n) + stack o(n)
    private int helper(int i, int k, int[] arr, int[] dp) {
        int n = arr.length;
        if (i == n)
            return 0;
        if (dp[i] != -1) {
            return dp[i];
        }

        int len = 0;
        int maxEle = Integer.MIN_VALUE;
        int maxSum = 0;
        for (int j = i; j < Integer.min(n, i + k); j++) {
            len++;
            maxEle = Math.max(maxEle, arr[j]);
            int sum = (len * maxEle) + helper(j + 1, k, arr, dp);
            maxSum = Math.max(maxSum, sum);
        }
        return dp[i] = maxSum;
    }

    public int maxSumAfterPartitioning2(int[] arr, int k) {
        int n = arr.length;
        // dp array
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return helper(0, k, arr, dp);
    }

    // tabulation sol
    // tc is o(n * k) sc is o(n)
    public int maxSumAfterPartitioning3(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1]; // n + 1

        dp[n] = 0; // base case

        for (int i = n - 1; i >= 0; i--) { // rev n-1 to 0
            int len = 0; // cpy paste
            int maxEle = Integer.MIN_VALUE;
            int maxSum = 0;
            for (int j = i; j < Integer.min(n, i + k); j++) {
                len++;
                maxEle = Math.max(maxEle, arr[j]);
                int sum = (len * maxEle) + dp[j + 1];
                maxSum = Math.max(maxSum, sum);
            }
            dp[i] = maxSum;

        }
        return dp[0];
    }
}