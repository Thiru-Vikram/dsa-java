package Strings;

public class Neetcode {

    // Longest Substring Without Repeating Characters
    // tc is o(n) and sc is o(1)
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        // 1 based idxing so + 1
        int[] hash = new int[256]; // store last seen index+1 of each char
        int l = 0; // left pointer

        // right pointer
        for (int r = 0; r < n; r++) {
            char c = s.charAt(r);

            // if char seen again reduce left pointer to newest poition
            if (hash[c] > 0) {
                l = Math.max(l, hash[c]);
            }

            // update maxLength
            maxLength = Math.max(maxLength, r - l + 1);

            // store last index+1
            hash[c] = r + 1;
        }

        return maxLength;
    }

    // 424. Longest Repeating Character Replacement
    // tc is o(n) and sc is o(1)
    public int characterReplacement(String s, int k) {
        // left and right pointer
        int l = 0, r = 0;
        int maxLen = 0;
        int maxFreq = 0;
        int[] hash = new int[26];

        while (r < s.length()) {
            // update frequency of current character
            hash[s.charAt(r) - 'A']++;
            // update the maximum frequency of any character in curr window
            maxFreq = Math.max(maxFreq, hash[s.charAt(r) - 'A']);

            // check curr window is invalid (too many replacements needed)
            // window len - max fre of char in that window
            while ((r - l + 1) - maxFreq > k) {
                hash[s.charAt(l) - 'A']--; // reduce freq
                l++; // move left pointer
            }

            // update maxLen with valid window size
            maxLen = Math.max(maxLen, r - l + 1);
            r++; // move right pointer
        }
        return maxLen;
    }

}
