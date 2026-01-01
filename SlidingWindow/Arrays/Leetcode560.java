package SlidingWindow.Arrays;

import java.util.HashMap;

// 560. Subarray Sum Equals K
public class Leetcode560 {

    // brute - force
    // tc is o(n^2) for two loops, sc is o(1).
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int n = nums.length;

        // Try every possible subarray
        for (int i = 0; i < n; i++) { // Start index
            int sum = 0;
            for (int j = i; j < n; j++) { // End index
                sum += nums[j]; // Add current element

                if (sum == k) { // Check if sum equals k
                    count++;
                }
            }
        }

        return count;
    }

    // optimal
    // tc is o(n) for traversal, sc is o(n) for map.
    public int subarraySum2(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int count = 0, presum = 0;

        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {

            // 0 to curr presum value.
            presum = presum + nums[i];

            int remove = presum - k;

            // ele is present add that corrspomding value from map to count.
            count += map.getOrDefault(remove, 0);

            // updating the presum value in map.
            map.put(presum, map.getOrDefault(presum, 0) + 1);
        }

        return count;
    }

}
