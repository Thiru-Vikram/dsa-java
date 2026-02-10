package DP.Partition;

import java.util.Arrays;

public class Leetcode132 {

    // recursion sol
    // tc is O(2^n), sc is O(n)
    private int helper(int i, String s) {
        int n = s.length();
        // base case
        if (i == n)
            return 0;

        // finding palindrome
        int minCost = Integer.MAX_VALUE;
        for (int j = i; j < n; j++) {
            String str = s.substring(i, j + 1);
            if (isPalindrome(str)) {
                // found 1 palin so 1 partition + next str is j + 1
                int cost = 1 + helper(j + 1, s);
                minCost = Math.min(minCost, cost);
            }
        }
        return minCost;
    }

    private boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--))
                return false;
        }
        return true;
    }

    public int minCut(String s) {
        // start with 0 we use front partition here
        // We count partitions and subtract 1 to get cuts.
        return helper(0, s) - 1;
    }

    // memoization sol
    // n states, each state j runs, for i,j palindrome check
    // tc is Total: O(n) × O(n) × O(n) = O(n³)
    // sc is O(n) + O(n) stack
    private int helper2(int i, String s, int[] dp) {
        int n = s.length();
        // base case
        if (i == n)
            return 0;

        if (dp[i] != -1) {
            return dp[i];
        }

        // finding palindrome
        int minCost = Integer.MAX_VALUE;
        for (int j = i; j < n; j++) {
            String str = s.substring(i, j + 1);
            if (isPalindrome(str)) {
                // found 1 palin so 1 partition + next str is j + 1
                int cost = 1 + helper2(j + 1, s, dp);
                minCost = Math.min(minCost, cost);
            }
        }
        return dp[i] = minCost;
    }

    public int minCut2(String s) {
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return helper2(0, s, dp) - 1;
    }

    // tabulation sol
    // tc is O(n^3), sc is O(n)
    public int minCut3(String s) {

        int n = s.length();
        int[] dp = new int[n + 1];

        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) { // rev n-1 -> 0

            int minCost = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                String str = s.substring(i, j + 1);
                if (isPalindrome(str)) {
                    int cost = 1 + dp[j + 1];
                    minCost = Math.min(minCost, cost);
                }
            }
            dp[i] = minCost;
        }
        return dp[0] - 1;
    }
}
