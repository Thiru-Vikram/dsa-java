package DP.Subsequences_Subsets;

public class Leetcode2035 {

    // tc and sc is o(n * target)
    public int minimumDifference(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // DP to find all possible subset sums
        boolean[][] dp = new boolean[n][totalSum + 1];

        // Base case: sum 0 is always possible
        for (int i = 0; i < n; i++)
            dp[i][0] = true;

        // Base case: first element
        if (nums[0] <= totalSum)
            dp[0][nums[0]] = true;

        // Fill DP table
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= totalSum; j++) {
                boolean notTake = dp[i - 1][j];
                boolean take = false;
                if (nums[i] <= j) {
                    take = dp[i - 1][j - nums[i]];
                }
                dp[i][j] = take || notTake;
            }
        }

        // Find minimum difference
        // the last row in dp arr will have all the values from that your substracting
        // the value with totalsum to get diff and then return min from them
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i <= totalSum / 2; i++) { // Fixed: <= instead of
            if (dp[n - 1][i]) {
                int s1 = i;
                int s2 = totalSum - i;
                minDiff = Math.min(minDiff, Math.abs(s2 - s1));
                // Or simply: minDiff = Math.min(minDiff, totalSum - 2 * i);
            }
        }

        return minDiff;
    }
}
