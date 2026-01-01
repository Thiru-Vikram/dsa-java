package SlidingWindow.Strings;

// 209. Minimum Size Subarray Sum
public class Leetcode209 {

    // brute force tc is o(n^2) and sc is o(1).
    public int minSubArrayLen(int target, int[] nums) {

        int minLen = Integer.MAX_VALUE;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int sum = 0; // Start fresh for each starting position

            for (int j = i; j < n; j++) {
                sum += nums[j]; // Add current element to sum

                if (sum >= target) {
                    int len = j - i + 1;
                    minLen = Math.min(minLen, len); // Keep minimum length
                    break; // No need to extend further from this i
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    // optimal o(n) and o(1).
    public int minSubArrayLen2(int target, int[] nums) {

        int minLen = Integer.MAX_VALUE;
        int n = nums.length;
        int sum = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            // Expand window: add current element
            sum += nums[right];

            // Shrink window: while sum is valid, try to minimize length
            while (sum >= target) {
                int len = right - left + 1;
                minLen = Math.min(minLen, len);

                sum -= nums[left]; // Remove left element
                left++; // Shrink from left
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

}
