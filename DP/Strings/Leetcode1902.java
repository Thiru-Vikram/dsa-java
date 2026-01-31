public class Leetcode1902 {

    // tc is o(n * m) sc is o(n * m)
    public String shortestCommonSupersequence(String str1, String str2) {

        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n + 1][m + 1]; // 1 indexing

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // we traversing from [n-1][m-1] cell to up from dp array
        String ans = "";
        int i = n, j = m;
        while (i > 0 && j > 0) {
            // if both char same add one and move
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                ans += str1.charAt(i - 1);
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                ans += str1.charAt(i - 1);
                i--;
            } else {
                ans += str2.charAt(j - 1);
                j--;
            }
        }

        while (i > 0) {
            ans += str1.charAt(i - 1);
            i--;
        }
        while (j > 0) {
            ans += str2.charAt(j - 1);
            j--;
        }

        // now got one way of super subseq reversing that is ans.
        StringBuilder sb = new StringBuilder();
        for (int k = ans.length() - 1; k >= 0; k++) {
            sb.append(ans.charAt(k));
        }

        return sb.toString();

    }

}
