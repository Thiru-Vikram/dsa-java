public class Leetcode1312 {
    // tc is o(n^2) and sc is O(n^2).
    // the algo is if we find lps and the remaing ele should be reversed and added
    // we can make the str palilndrom at max we can rev str and add ans is str len
    // but we need min insertions
    // str len - lps = ans they asked only len so its simple.
    // ex:- abcaa find lps in str -> a a a
    // insert remaing bc in by reverse -> a bc a a -> a bc a cb a
    private static int longestCommonSubsequence(String str1, String str2) {

        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {

                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];
    }

    private static int longestPalindromeSubseq(String s) {

        String str1 = s;
        StringBuilder sb = new StringBuilder(str1);
        sb.reverse();
        String str2 = sb.toString();

        return longestCommonSubsequence(str1, str2);
    }

    public int minInsertions(String s) {
        int n = s.length();
        return n - longestPalindromeSubseq(s);
    }
}
