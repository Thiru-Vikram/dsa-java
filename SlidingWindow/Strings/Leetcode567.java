package SlidingWindow.Strings;

import java.util.Arrays;

// 567. Permutation in String
public class Leetcode567 {

    // brute force tc is O((n-m+1) × m) and sc is O(1)
    public boolean checkInclusion(String s1, String s2) {

        if (s1.length() > s2.length()) {
            return false;
        }

        int len1 = s1.length();
        int len2 = s2.length();

        // Check every substring of s2 with length = s1.length()
        for (int i = 0; i <= len2 - len1; i++) {
            String substring = s2.substring(i, i + len1);

            if (isPermutationFreq(s1, substring)) {
                return true;
            }
        }

        return false;
    }

    private boolean isPermutationFreq(String s1, String s2) {
        int[] count = new int[26];

        for (char c : s1.toCharArray()) {
            count[c - 'a']++;
        }

        for (char c : s2.toCharArray()) {
            count[c - 'a']--;
        }

        for (int val : count) {
            if (val != 0)
                return false;
        }

        return true;
    }

    // optimal tc is o(n) and sc is o(1).
    public boolean checkInclusion2(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        // Count frequency of characters in s1
        int[] s1Count = new int[26];
        int[] windowCount = new int[26];

        for (char c : s1.toCharArray()) {
            s1Count[c - 'a']++;
        }

        // Sliding window over s2
        for (int i = 0; i < s2.length(); i++) {
            // Add current character to window
            windowCount[s2.charAt(i) - 'a']++;

            // Remove leftmost character if window is too large
            if (i >= s1.length()) {
                windowCount[s2.charAt(i - s1.length()) - 'a']--;
            }

            // Check if current window matches s1's frequency
            if (i >= s1.length() - 1 && matches(s1Count, windowCount)) {
                return true;
            }
        }

        return false;
    }

    private boolean matches(int[] a, int[] b) {
        return Arrays.equals(a, b);
    }

}
