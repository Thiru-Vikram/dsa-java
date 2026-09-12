import java.util.*;

public class FrogJumpKSteps {

    // recursion sol
    // Time Complexity: O(k^n) approximately
    // Space Complexity: O(n) — recursion stack
    public static int frogJumpWithKSteps(int[] arr, int k) {
        return helper(arr.length - 1, arr, k);
    }

    public static int helper(int idx, int[] arr, int k) {

        // Base case
        if (idx == 0)
            return 0;

        int ans = Integer.MAX_VALUE;

        // Try all jumps from 1 to k
        for (int i = 1; i <= k; i++) {

            // Check if the jump is possible
            if (idx - i >= 0) {

                int jumps = helper(idx - i, arr, k)
                        + Math.abs(arr[idx] - arr[idx - i]);

                ans = Math.min(ans, jumps);
            }
        }

        return ans;
    }

    // memoization sol
    // TC = O(n × k)
    // SC = O(n) + o(n) recursive stack
    public static int memoization(int[] arr, int k) {

        int n = arr.length;
        int[] dp = new int[n];

        return helper(n - 1, dp, arr, k);
    }

    public static int helper(int idx, int[] dp, int[] arr, int k) {

        // Base case
        if (idx == 0)
            return 0;

        // Already calculated
        if (dp[idx] != 0)
            return dp[idx];

        int ans = Integer.MAX_VALUE;

        // Try all jumps from 1 to k
        for (int i = 1; i <= k; i++) {

            // Boundary check
            if (idx - i >= 0) {

                int jumps = helper(idx - i, dp, arr, k)
                        + Math.abs(arr[idx] - arr[idx - i]);

                ans = Math.min(ans, jumps);
            }
        }

        // Store answer
        dp[idx] = ans;

        return ans;
    }

    // bottom to top -> tabu
    // tc is o(n * k) sc is o(n)
    public static int tabulation(int[] arr, int k) {

        int n = arr.length;
        int[] dp = new int[n];
        // Cost to reach 0th stair is 0
        dp[0] = 0;

        for (int i = 1; i < n; i++) {

            int ans = Integer.MAX_VALUE;

            // Try all jumps from 1 to k
            for (int j = 1; j <= k; j++) {
                // Boundary check
                if (i - j >= 0) {
                    int jumps = dp[i - j]
                            + Math.abs(arr[i] - arr[i - j]);
                    ans = Math.min(ans, jumps);
                }
            }
            dp[i] = ans;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {

    }

}