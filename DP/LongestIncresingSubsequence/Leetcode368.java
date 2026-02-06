package DP.LongestIncresingSubsequence;

import java.util.*;

public class Leetcode368 {

    // same as the 300 prob just the condition changed same code
    // longest increasing subsequence to longest divisible subsequence
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums); // Sort first!

        int n = nums.length;
        int[] dp = new int[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) { // Changed condition
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }
        }

        int maxIdx = 0;
        for (int i = 1; i < n; i++) {
            if (dp[i] > dp[maxIdx]) {
                maxIdx = i;
            }
        }

        List<Integer> result = new ArrayList<>();
        int idx = maxIdx;
        while (idx != -1) {
            result.add(0, nums[idx]);
            idx = parent[idx];
        }

        return result;
    }

}
