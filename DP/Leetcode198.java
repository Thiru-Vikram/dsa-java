package DP;

public class Leetcode198 {

    // Brute Force (Recursive)
    public int rob1(int[] nums) {
        return robFrom(nums, 0);
    }

    private int robFrom(int[] nums, int idx) {
        if (idx >= nums.length)
            return 0;

        // Option 1: Rob current house + max from idx+2 onwards
        int robCurrent = nums[idx] + robFrom(nums, idx + 2);

        // Option 2: Skip current house + max from idx+1 onwards
        int skipCurrent = robFrom(nums, idx + 1);

        return Math.max(robCurrent, skipCurrent);
    }
    // Time Complexity: O(2^n) - exponential because we're exploring all
    // combinations
    // Space Complexity: O(n) - recursion call stack

    // Optimal Solution (Dynamic Programming)
    // Approach 1: DP Array
    public int rob2(int[] nums) {
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
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    // Approach 2: Space-Optimized DP
    public int rob3(int[] nums) {
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
    // Time Complexity: O(n)
    // Space Complexity: O(1)
}
