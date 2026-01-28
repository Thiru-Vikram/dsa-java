package DP.Subsequences_Subsets;

public class Leetcode416 {

    // Time Complexity: O(2^n) - exponential
    // Space Complexity: O(n) - recursion stack
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums)
            totalSum += num;

        if (totalSum % 2 != 0)
            return false;

        int target = totalSum / 2;
        return subsetSum(nums, nums.length - 1, target);
    }

    private boolean subsetSum(int[] arr, int index, int target) {
        // Base cases
        if (target == 0)
            return true; // Found a valid subset
        if (index == 0)
            return arr[0] == target; // Only one element left

        // Exclude current element
        boolean notTake = subsetSum(arr, index - 1, target);

        // Include current element (if possible)
        boolean take = false;
        if (arr[index] <= target) {
            take = subsetSum(arr, index - 1, target - arr[index]);
        }

        return take || notTake;
    }

    // memoization sol
    // Time Complexity: O(n × m)
    // Space Complexity: O(n × m) + O(n) recursion stack
    public boolean canPartition2(int[] nums) {
        int totalSum = 0;
        for (int num : nums)
            totalSum += num;

        if (totalSum % 2 != 0)
            return false;

        int target = totalSum / 2;
        Boolean[][] dp = new Boolean[nums.length][target + 1];
        return subsetSum(nums, nums.length - 1, target, dp);
    }

    private boolean subsetSum(int[] arr, int index, int target, Boolean[][] dp) {
        // Base cases
        if (target == 0)
            return true;
        if (index == 0)
            return arr[0] == target;

        // Check if already computed
        if (dp[index][target] != null)
            return dp[index][target];

        // Exclude current element
        boolean notTake = subsetSum(arr, index - 1, target, dp);

        // Include current element (if possible)
        boolean take = false;
        if (arr[index] <= target) {
            take = subsetSum(arr, index - 1, target - arr[index], dp);
        }

        // Store and return result
        dp[index][target] = take || notTake;
        return dp[index][target];
    }

    // tabulation
    // tc is o(n * m), sc is o(n * m)
    public static boolean subsetSumEqualToTarget(int[] arr, int target) {
        int n = arr.length;

        boolean[][] dp = new boolean[n][target + 1];

        // Base case: target = 0 is always achievable (empty subset)
        for (int i = 0; i < n; i++)
            dp[i][0] = true;

        // Base case: first element
        if (arr[0] <= target) // Important check!
            dp[0][arr[0]] = true;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                boolean notTake = dp[i - 1][j];
                boolean take = false;
                if (arr[i] <= j) { // Fixed: check against j, not target
                    take = dp[i - 1][j - arr[i]];
                }
                dp[i][j] = take || notTake;
            }
        }

        return dp[n - 1][target];
    }

    public boolean canPartition3(int[] nums) {
        // if we found one half return true
        int totalSum = 0;
        for (int num : nums)
            totalSum += num;
        if (totalSum % 2 != 0)
            return false;
        int target = totalSum / 2;

        return subsetSumEqualToTarget(nums, target);
    }

}
