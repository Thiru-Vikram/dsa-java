import java.util.*;

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

    // brute force tc is o(n) sc is o(1)
    public static int longestSubarrayWithAbsDiff(int[] arr, int limit) {

        int n = arr.length;

        int ans = 0;
        for (int i = 0; i < n; i++) {

            int cnt = 1;
            for (int j = i + 1; j < n; j++) {

                int val = Math.abs(arr[j - 1] - arr[j]);

                if (val <= limit) {
                    cnt++;
                } else {
                    break;
                }

            }
            ans = Math.max(cnt, ans);
        }

        return ans;
    }

    // optimal sol tc is o(n) sc is o(1) two pointers
    public static int longestSubarrayWithAbsDiff2(int[] arr, int limit) {

        int n = arr.length;
        if (n == 0)
            return 0;

        int ans = 0;
        int left = 0;
        for (int right = 1; right < n; right++) {

            int val = Math.abs(arr[right - 1] - arr[right]);

            if (val > limit)
                left = right;

            ans = Math.max(ans, right - left + 1);

        }

        return ans;
    }

    // "I use dynamic programming based on the last character. I maintain three
    // states representing the number of valid strings ending in A, B, and C. A
    // cannot follow A, and B cannot follow C. Therefore, the transitions are newA =
    // dpB + dpC, newB = dpA + dpB, and newC = dpA + dpB + dpC. I iterate N times
    // and take the sum of the three states. The time complexity is O(N) and the
    // space complexity is O(1)."
    public static int countStrings(int n) {

        long MOD = 1000000007;

        long dpA = 1;
        long dpB = 1;
        long dpC = 1;

        for (int i = 2; i <= n; i++) {

            long newA = (dpB + dpC) % MOD;
            long newB = (dpA + dpB) % MOD;
            long newC = (dpA + dpB + dpC) % MOD;

            dpA = newA;
            dpB = newB;
            dpC = newC;
        }

        return (int) ((dpA + dpB + dpC) % MOD);
    }

    // tc is o(t * m) sc is o(t + m)
    public static int standingPassengerKm(int[][] arr, int c) {

        int t = arr.length;

        int maxLen = Integer.MIN_VALUE;

        // Find maximum location
        for (int i = 0; i < t; i++) {
            maxLen = Math.max(maxLen, arr[i][2]);
        }

        // res[i] = passengers on segment i -> i+1
        int[] res = new int[maxLen];

        for (int i = 0; i < t; i++) {

            int p = arr[i][0];
            int from = arr[i][1];
            int to = arr[i][2];

            for (int j = from - 1; j < to - 1; j++) {
                res[j] += p;
            }
        }

        int ans = 0;

        for (int i = 0; i < maxLen - 1; i++) {

            if (res[i] > c) {
                ans += res[i] - c;
            }
        }

        return ans;
    }

    public static int minTotalTravelCost(int[] a, int[] b) {

        int totalEmployee = a.length;
        int n = totalEmployee / 2;

        int[] diff = new int[totalEmployee];
        int totalCost = 0;

        for (int i = 0; i < totalEmployee; i++) {

            int bCost = Math.min(a[i], b[i]) + b[i];

            totalCost += bCost;

            diff[i] = a[i] - bCost;

        }

        Arrays.sort(diff);

        for (int i = 0; i < n; i++) {
            totalCost += diff[i];
        }

        return totalCost;
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