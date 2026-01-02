package SlidingWindow.Strings;

// 76. Minimum Window Substring
public class Leetcode76 {

    // brute force
    // tc is o(n^2 + (n + m) ~~ o(n^3), sc is o(n) for creating the string.
    public String minWindow(String s, String t) {
        int n = s.length();
        String result = "";
        int minLen = Integer.MAX_VALUE;

        // Generate all substrings
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String substring = s.substring(i, j + 1);

                // Check if this substring contains all characters of t
                if (containsAll(substring, t)) {
                    if (substring.length() < minLen) {
                        minLen = substring.length();
                        result = substring;
                    }
                }
            }
        }

        return result;
    }

    // Helper function to check if str contains all characters of t
    private boolean containsAll(String str, String t) {
        int[] hash = new int[256];

        // Count characters in t
        for (char c : t.toCharArray()) {
            hash[c]++;
        }

        // Subtract characters found in str
        for (char c : str.toCharArray()) {
            hash[c]--;
        }

        // Check if all characters of t are covered
        for (char c : t.toCharArray()) {
            if (hash[c] > 0) {
                return false; // Missing some characters
            }
        }

        return true;
    }

    // optimal
    // tc is o(n + m) n - for loop, m - two while loop, sc is o(1) const.
    public String minWindow2(String s, String t) {

        int n = s.length();
        int m = t.length();
        int[] hash = new int[256];
        int count = 0;
        int minLen = Integer.MAX_VALUE;
        int sIdx = -1;
        int l = 0, r = 0;

        for (int i = 0; i < m; i++) {
            hash[t.charAt(i)]++;
        }
        while (r < n) {
            // Expand window
            if (hash[s.charAt(r)] > 0) {
                count++;
            }
            hash[s.charAt(r)]--;

            // Contract window
            while (count == m) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    sIdx = l;
                }
                hash[s.charAt(l)]++;
                if (hash[s.charAt(l)] > 0) {
                    count--;
                }
                l++;
            }
            r++;
        }
        return (sIdx == -1) ? "" : s.substring(sIdx, sIdx + minLen);

    }

}
