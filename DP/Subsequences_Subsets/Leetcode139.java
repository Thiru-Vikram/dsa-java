package DP.Subsequences_Subsets;

import java.util.*;

// 139. Word Break
public class Leetcode139 {

    // Recursion (without memo):
    // TC: O(2^n) — at each index you have 2 choices (take this char in current word
    // or cut here), exponential in worst case.
    // SC: O(n) — recursion stack depth max is n (length of string)
    private boolean helper(int idx, String s, Set<String> set) {

        // last idx so true found
        if (idx == s.length()) {
            return true;
        }

        for (int j = idx; j < s.length(); j++) {
            String word = s.substring(idx, j + 1);
            // check word in set & move for next letters in string
            if (set.contains(word) && helper(j + 1, s, set)) {
                return true;
            }
        }
        return false;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        // store unique words
        Set<String> set = new HashSet<>(wordDict);
        return helper(0, s, set);
    }

    // Memoization:
    // TC: O(n²) -> n unique states (each idx from 0 to n)
    // at each state you run a for loop of n and do substring which is O(n)
    // so n * n = O(n²)
    // SC: O(n) recursion stack + O(n) dp array = O(n)
    private boolean helper(int idx, String s, Set<String> wordSet, Boolean[] dp) {

        if (idx == s.length()) {
            return true;
        }

        // already computed
        if (dp[idx] != null) {
            return dp[idx];
        }

        for (int j = idx; j < s.length(); j++) {
            String word = s.substring(idx, j + 1);
            if (wordSet.contains(word) && helper(j + 1, s, wordSet, dp)) {
                dp[idx] = true;
                return true;
            }
        }

        dp[idx] = false;
        return false;
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Boolean[] dp = new Boolean[s.length() + 1];
        return helper(0, s, wordSet, dp);
    }

    // TC: O(n²) SC: O(n) — same as memo but no recursion stack overhead!
    public boolean wordBreak3(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];

        // base case: empty string is always true
        dp[n] = true;

        // fill from right to left (opposite of recursion)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                String word = s.substring(i, j + 1);
                if (wordSet.contains(word) && dp[j + 1]) {
                    dp[i] = true;
                    break; // move to next pair
                }
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {

    }
}
