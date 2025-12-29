package SlidingWindow.Strings;

import java.util.HashSet;
import java.util.Set;

// 3. Longest Substring Without Repeating Characters
public class Leetcode3 {

    // brute - force
    // tc is o(n^3) and sc is o(n)
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;

        // Try all possible starting points
        for (int i = 0; i < n; i++) {
            // Try all possible ending points
            for (int j = i; j < n; j++) {
                // Check if substring s[i...j] has all unique characters
                if (allUnique(s, i, j)) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }

        return maxLength;
    }

    // Helper function to check if substring has all unique characters
    private boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();

        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                return false; // duplicate found
            }
            set.add(c);
        }

        return true; // all unique
    }

    // optimal
    // tc is o(n) for traversal, sc is o(1) bec array size is constant.
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int maxLength = 0;
        int[] hash = new int[256]; // store last index+1 of each char
        int l = 0; // left pointer

        for (int r = 0; r < n; r++) {
            char c = s.charAt(r);

            // if already seen and inside current window, l and r is the window.
            if (hash[c] > 0) {
                l = Math.max(l, hash[c]); // moves forward to shrink
            }

            // update maxLength
            // store the particular window len into maxlen
            maxLength = Math.max(maxLength, r - l + 1);

            // store last index+1
            // hash contains each char latest index
            hash[c] = r + 1;
        }

        return maxLength;
    }

    public static void main(String[] args) {

    }

}
