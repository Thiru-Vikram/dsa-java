package DP.LongestIncresingSubsequence;

import java.util.Arrays;

// this is tabulation sol of lis little modification to solve the no.ofLIS
public class Leetcode673 {

    // at start
    // nums = [1, 3, 5, 4, 7]
    // dp = [1, 1, 1, 1, 1] Each element is LIS of length 1
    // cnt = [1, 1, 1, 1, 1] Each has count 1

    // tc is o(n^2) sc is o(n)
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n]; // Length of LIS ending at i
        int[] cnt = new int[n]; // Count of LIS ending at i

        // Initialize: each element forms LIS of length 1 with count 1
        Arrays.fill(dp, 1);
        Arrays.fill(cnt, 1);

        int maxi = 1;

        // Build dp and cnt arrays (similar to LIS tabulation)
        for (int idx = 0; idx < n; idx++) {
            for (int prev_idx = 0; prev_idx < idx; prev_idx++) {

                if (nums[prev_idx] < nums[idx]) {

                    if (1 + dp[prev_idx] > dp[idx]) {
                        // Found longer LIS - update length and INHERIT count
                        dp[idx] = 1 + dp[prev_idx];
                        cnt[idx] = cnt[prev_idx]; // Copy count
                    } else if (1 + dp[prev_idx] == dp[idx]) {
                        // Found same length LIS - ADD to count
                        cnt[idx] += cnt[prev_idx]; // Accumulate count
                    }
                }
            }
            maxi = Math.max(maxi, dp[idx]);
        }

        // dp = [1, 2, 3, 3, 4]
        // cnt = [1, 1, 1, 1, 2] ← cnt[4] = 2 (TWO ways to form LIS of length 4)
        // so adding that 2 to ans
        // Count all LIS with maximum length
        int numberOfLIS = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxi) {
                numberOfLIS += cnt[i];
            }
        }

        return numberOfLIS;
    }

}
