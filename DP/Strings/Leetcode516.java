public class Leetcode516 {

    // we need the longest palindrome of sunsequence for that if we reverse the
    // given string and use the lcs we will get the answer the answer itself will
    // the palindrome. tc and sc is o(n^2)
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

    public static int longestPalindromeSubseq(String s) {

        String str1 = s;
        StringBuilder sb = new StringBuilder(str1);
        sb.reverse();
        String str2 = sb.toString();

        return longestCommonSubsequence(str1, str2);
    }

    public static void main(String[] args) {

    }

}
