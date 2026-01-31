public class Leetcode583 {

    // tc is o(n * m) sc is o(n * m)
    // the algo is find commmon str in both using lcs
    // del ele in str1 and insert ele from str2 add them u get ans
    // ex:- abcd, anc common - ac
    // del bd , insert n in common total - 3
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        return n + m - 2 * longestCommonSubsequence(word1, word2);
    }
}
