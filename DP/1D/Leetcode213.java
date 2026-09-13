import java.util.*;

public class Leetcode213 {

    // this is brute force recur sol
    // tc is o(2^n) sc is o(n)
    // here same as rob 1 q but we do two times get ans
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];

        int n = nums.length;
        // Option 1: Rob from house 0 to n-2 (exclude the last house)
        int robWithoutLast = helper(nums, 0, n - 2);

        // Option 2: Rob from house 1 to n-1 (exclude the first house)
        int robWithoutFirst = helper(nums, 1, n - 1);

        return Math.max(robWithoutLast, robWithoutFirst);
    }

    private int helper(int[] nums, int idx, int end) {
        if (idx > end)
            return 0;

        // Option 1: Take current house + max from idx + 2 onwards
        int take = nums[idx] + helper(nums, idx + 2, end);

        // Option 2: Skip current house + max from idx + 1 onwards
        int notTake = helper(nums, idx + 1, end);

        return Math.max(take, notTake);
    }

    // memoization sol
    // tc is o(n) sc is o(n) + o(n) stack
    public static int memoization(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];

        int[] dp1 = new int[n];
        Arrays.fill(dp1, -1);
        int skipLastHouse = helper2(0, n - 2, nums, dp1);

        int[] dp2 = new int[n];
        Arrays.fill(dp2, -1);
        int skipFirstHouse = helper2(1, n - 1, nums, dp2);

        return Math.max(skipLastHouse, skipFirstHouse);
    }

    public static int helper2(int st, int end, int[] nums, int[] dp) {
        if (st > end)
            return 0;

        if (dp[st] != -1)
            return dp[st];

        int take = nums[st] + helper2(st + 2, end, nums, dp);
        int notTake = helper2(st + 1, end, nums, dp);

        // store before returning
        return dp[st] = Math.max(take, notTake);

    }

    // tabulation sol
    // tc is o(n) sc is o(n)
    public static int tabulation(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];
        if (n == 2)
            return Math.max(nums[0], nums[1]);

        // Range 1: Skip last house (indices 0 to n-2)
        int[] arr1 = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            arr1[i] = nums[i];
        }

        // Range 2: Skip first house (indices 1 to n-1)
        int[] arr2 = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            arr2[i] = nums[i + 1];
        }

        return Math.max(helper3(arr1), helper3(arr2));
    }

    public static int helper3(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            // Fix: Add nums[i] instead of dp[i]
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {

    }
}
