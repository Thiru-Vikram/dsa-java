import java.util.*;

// House Robber 1
public class Leetcode198 {

    // Brute Force (Recursive)
    // Time Complexity: O(2^n) - exponential because we're exploring all
    // combinations
    // Space Complexity: O(n) - recursion call stack
    public int rob(int[] nums) {
        return helper(nums, 0);
    }

    private int helper(int[] nums, int idx) {
        if (idx >= nums.length)
            return 0;

        // Option 1: Rob current house + max from idx+2 onwards
        int robCurrent = nums[idx] + helper(nums, idx + 2);

        // Option 2: Skip current house + max from idx+1 onwards
        int skipCurrent = helper(nums, idx + 1);

        return Math.max(robCurrent, skipCurrent);
    }

    // memo sol
    // TC = O(n) SC = O(n) + o(n) stack
    public static int memoization(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1); // Use -1 to properly track
        return helper2(0, arr, dp);
    }

    public static int helper2(int idx, int[] arr, int[] dp) {
        if (idx >= arr.length)
            return 0;
        if (dp[idx] != -1)
            return dp[idx];

        int take = arr[idx] + helper2(idx + 2, arr, dp);
        int notTake = helper2(idx + 1, arr, dp);

        return dp[idx] = Math.max(take, notTake);
    }

    // bottom to top
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int tabulation(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            // Either rob current + dp[i-2], or skip current (take dp[i-1])
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[n - 1];
    }

    // Space-Optimized
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int spaceOptimised(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(nums[i] + prev2, prev1);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

}
