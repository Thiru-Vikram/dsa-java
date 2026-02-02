package DP.Strings;

public class Leetcode44 {

    // brute force
    // tc is o(2^(n+m)) sc is o(n + m)
    private boolean helper(int i, int j, String s, String p) {
        // Both exhausted - match found
        if (i < 0 && j < 0)
            return true;

        // Pattern exhausted but string has chars - no match
        if (j < 0 && i >= 0)
            return false;

        // String exhausted, check if remaining pattern is all '*'
        if (i < 0 && j >= 0) {
            for (int jj = 0; jj <= j; jj++) {
                if (p.charAt(jj) != '*')
                    return false;
            }
            return true;
        }

        // Characters match or pattern has '?'
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            return helper(i - 1, j - 1, s, p);
        }

        // Pattern has '*' - try both options
        if (p.charAt(j) == '*') {
            // '*' matches empty OR '*' matches current char in s
            return helper(i, j - 1, s, p) || helper(i - 1, j, s, p);
        }

        // No match
        return false;
    }

    public boolean isMatch(String s, String p) {
        return helper(s.length() - 1, p.length() - 1, s, p);
    }

    // tc is O(m * n), sc is O(m + n) + O(m * n)
    private boolean helper(int i, int j, String s, String p, Boolean[][] dp) {
        // Both exhausted - match found
        if (i < 0 && j < 0)
            return true;

        // Pattern exhausted but string has chars - no match
        if (j < 0 && i >= 0)
            return false;

        // String exhausted, check if remaining pattern is all '*'
        if (i < 0 && j >= 0) {
            for (int jj = 0; jj <= j; jj++) {
                if (p.charAt(jj) != '*')
                    return false;
            }
            return true;
        }

        // Check memoization
        if (dp[i][j] != null) {
            return dp[i][j];
        }

        boolean result;

        // Characters match or pattern has '?'
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            result = helper(i - 1, j - 1, s, p, dp);
        }
        // Pattern has '*' - try both options
        else if (p.charAt(j) == '*') {
            // '*' matches empty OR '*' matches current char in s
            result = helper(i, j - 1, s, p, dp) || helper(i - 1, j, s, p, dp);
        }
        // No match
        else {
            result = false;
        }

        dp[i][j] = result;
        return result;
    }

    public boolean isMatch2(String s, String p) {
        int m = s.length();
        int n = p.length();
        Boolean[][] dp = new Boolean[m][n]; // Use Boolean wrapper
        return helper(m - 1, n - 1, s, p, dp);
    }

    // tc is O(m * n), sc is O(m * n)
    public boolean isMatch3(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Base case: empty string matches empty pattern
        dp[0][0] = true;

        // Base case: empty string (i=0), check if pattern is all '*'
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1]; // '*' can match empty
            }
            // else dp[0][j] = false (default)
        }

        // Base case: non-empty string (i>0), empty pattern (j=0)
        // Already false by default initialization

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                // Characters match or pattern has '?'
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // Pattern has '*'
                else if (p.charAt(j - 1) == '*') {
                    // '*' matches empty OR '*' matches one or more chars
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
                // else dp[i][j] = false (no match, default)
            }
        }

        return dp[m][n];
    }

}
