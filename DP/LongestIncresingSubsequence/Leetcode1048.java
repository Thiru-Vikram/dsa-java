package DP.LongestIncresingSubsequence;

import java.util.Arrays;
import java.util.Comparator;

// same as the longestIncreasingSubsequence
// changed to longestStringsubsets sot just little change sort and comparator why this subset means no order so sorting 
public class Leetcode1048 {

    // tc is o(n^2 * l) sorting + loops, sc is o(n)
    private boolean compare(String s1, String s2) {
        if (s1.length() != s2.length() + 1)
            return false;

        int i = 0;
        int j = 0;
        while (i < s1.length()) {
            if (j < s2.length() && s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        return (i == s1.length() && j == s2.length());
    }

    public int longestStrChain(String[] words) {
        int n = words.length;
        int[] ahead = new int[n + 1];
        int[] curr = new int[n + 1];

        // Sort by length: we must build chains from shorter to longer words.
        // Example: "a" → "ba" → "bca" must be processed in that order.
        Arrays.sort(words, Comparator.comparingInt(String::length));

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int prev_idx = idx - 1; prev_idx >= -1; prev_idx--) {

                int notTake = ahead[prev_idx + 1];
                int take = 0;

                // onlly change in comparing the words thats it.
                if (prev_idx == -1 || compare(words[idx], words[prev_idx])) {
                    take = 1 + ahead[idx + 1];
                }

                curr[prev_idx + 1] = Math.max(take, notTake);
            }
            ahead = curr;
            curr = new int[n + 1];
        }

        return ahead[0];
    }
}
