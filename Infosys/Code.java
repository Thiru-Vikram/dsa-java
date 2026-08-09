
class Code {

    // split a string in balanced string
    // tc is o(n) sc is o(n)
    public static int findMaxSplit(String s) {

        int n = s.length();
        int[] preSum = new int[n];

        // building prefix sum
        int prev = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == 'L') {
                prev++;
                preSum[i] = prev;
            } else {
                prev--;
                preSum[i] = prev;
            }

            if (preSum[i] == 0)
                count++;
        }

        int max = count;
        for (int i = 0; i < n - 1; i++) {

            char curr = s.charAt(i);
            char next = s.charAt(i + 1);

            int currval = preSum[i];
            if (curr != next && currval != 0) {

                if (curr == 'L') {
                    currval -= 2;
                } else {
                    currval += 2;
                }
            }

            if (currval == 0)
                max++;
        }

        return max;

    }

    // recursion
    // Time Complexity:
    // O(8^(N×M)) — a safe upper bound for your implementation.
    // Space Complexity:
    // O(N×M) — recursion stack can go as deep as the number of cells.

    // memo
    // Time Complexity:
    // O(N × M × 2 × 8) = O(N × M)
    // Space Complexity:
    // DP array = O(N × M × 2) = O(N × M)
    // find max possible strictly increasing path along with dash add on
    public static int dfs(int i, int j, int dash, int[][][] dp, int[][] grid, int[][] dir, int n, int m) {

        int ans = 1;

        if (dp[i][j][dash] != 0)
            return dp[i][j][dash];

        for (int[] d : dir) {
            int newRow = i + d[0];
            int newCol = j + d[1];

            if (newRow >= 0 && newRow < n &&
                    newCol >= 0 && newCol < m &&
                    grid[newRow][newCol] > grid[i][j]) {

                ans = Math.max(ans, 1 + dfs(newRow, newCol, 0, dp, grid, dir, n, m));
            }
        }

        if (dash == 0) {
            for (int[] d : dir) {
                int newRow = i + d[0];
                int newCol = j + d[1];

                if (newRow >= 0 && newRow < n &&
                        newCol >= 0 && newCol < m &&
                        grid[newRow][newCol] > grid[i][j]) {

                    ans = Math.max(ans, 1 + dfs(newRow, newCol, 1, dp, grid, dir, n, m));
                }
            }
        }

        dp[i][j][dash] = ans;

        return ans;
    }

    public static void main(String[] args) {

        int[][] grid = {
                { 1, 100, 2, 3 },
                { 4, 5, 6, 7 },
                { 8, 9, 10, 11 },
                { 12, 13, 14, 15 }
        };

        int[][] dir = {
                { 0, 1 },
                { 1, 0 },
                { -1, 0 },
                { 0, -1 }
        };

        int n = grid.length;
        int m = grid[0].length;

        int[][][] dp = new int[n][m][2];

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res = Math.max(res, dfs(i, j, 0, dp, grid, dir, n, m));
            }
        }

        System.out.println("The longest path is : " + res);
    }
}