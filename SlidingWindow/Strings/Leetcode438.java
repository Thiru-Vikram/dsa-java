package SlidingWindow.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode438 {

    // brute - force
    // tc is o(n^2) and sc is o(1).
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int m = p.length();
        int n = s.length();

        if (n < m)
            return ans;

        // Try every substring of length m
        for (int i = 0; i <= n - m; i++) {
            String substring = s.substring(i, i + m);

            if (isAnagram(substring, p)) {
                ans.add(i);
            }
        }

        return ans;
    }

    // Helper method to check if two strings are anagrams
    private boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        int[] count = new int[26];

        // Count characters in s1
        for (char c : s1.toCharArray()) {
            count[c - 'a']++;
        }

        // Subtract characters in s2
        for (char c : s2.toCharArray()) {
            count[c - 'a']--;
        }

        // Check if all counts are zero
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0)
                return false;
        }

        return true;
    }

    // optimal
    // tc is o(n), sc is o(1) cause const arrays.
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> ans = new ArrayList<>();

        if (s.length() < p.length())
            return ans;

        int[] pHash = new int[26];
        int[] sHash = new int[26];
        int m = p.length();

        // Build hash for pattern p
        for (int i = 0; i < m; i++) {
            pHash[p.charAt(i) - 'a']++;
        }

        // Sliding window
        for (int right = 0; right < s.length(); right++) {
            // Add current character to window
            sHash[s.charAt(right) - 'a']++;

            // If window size exceeds p's length, remove leftmost character
            if (right >= m) {
                sHash[s.charAt(right - m) - 'a']--;
            }

            // If window size equals m, check if it's an anagram
            if (right >= m - 1 && Arrays.equals(sHash, pHash)) {
                ans.add(right - m + 1);
            }
        }

        return ans;
    }
}
