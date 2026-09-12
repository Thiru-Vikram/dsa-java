import java.util.*;

// Dynamic Programming : Frog Jump

// Problem Statement: Given a number of stairs and a frog, the frog wants to climb from the 0th stair to the (N-1)th stair. At a time the frog can climb either one or two steps. A height[N] array is also given. Whenever the frog jumps from a stair i to stair j, the energy consumed in the jump is abs(height[i]- height[j]), where abs() means the absolute difference. We need to return the minimum energy that can be used by the frog to jump from stair 0 to stair N-1..

// Example 1:
// Input: heights = [2, 1, 3, 5, 4]
// Output: 2
// Explanation: One possible route can be,
// 0th step -> 2nd Step = abs(2 - 3) = 1
// 2nd step -> 4th step = abs(3 - 4) = 1
// Total = 1 + 1 = 2.

// Example 2:
// Input: heights = [7, 5, 1, 2, 6]
// Output: 9
// Explanation: One possible route can be,
// 0th step -> 1st Step = abs(7 - 5) = 2
// 1st step -> 3rd step = abs(5 - 2) = 3
// 3rd step -> 4th step = abs(2 - 6) = 4
// Total = 2 + 3 + 4 = 9.

public class FrogJump {

    // recursion sol
    // Time Complexity : O(2^n)
    // Space Complexity : O(n)
    public static int frogJump(int[] arr) {
        return helper(arr.length - 1, arr);
    }

    public static int helper(int idx, int[] arr) {
        if (idx == 0)
            return 0;

        // 1 step
        int left = helper(idx - 1, arr)
                + Math.abs(arr[idx] - arr[idx - 1]);

        // 2 steps
        int right = Integer.MAX_VALUE;

        // if sometimes if u r in 2 idx u cannot jump again 2 steps right
        // so we check boundary condition.
        if (idx > 1) {
            right = helper(idx - 2, arr)
                    + Math.abs(arr[idx] - arr[idx - 2]);
        }

        return Math.min(left, right);
    }

    // memo sol
    // Time Complexity : O(n)
    // Space Complexity : O(n) + o(n) stack
    public static int memoization(int[] arr) {
        int[] dp = new int[arr.length];

        return helper2(arr.length - 1, arr, dp);
    }

    public static int helper2(int idx, int[] arr, int[] dp) {

        if (idx == 0)
            return 0;

        // Return if already solved
        if (dp[idx] != 0)
            return dp[idx];

        // 1 step
        int left = helper2(idx - 1, arr, dp)
                + Math.abs(arr[idx] - arr[idx - 1]);

        // 2 steps
        int right = Integer.MAX_VALUE;

        if (idx > 1) {
            right = helper2(idx - 2, arr, dp)
                    + Math.abs(arr[idx] - arr[idx - 2]);
        }

        // Store answer in dp
        return dp[idx] = Math.min(left, right);
    }

    // Bottom to top -> Tabulation
    // TC: O(n), SC: O(n)
    public static int tabulation(int[] arr) {
        int n = arr.length;

        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            // 1 step
            int onestep = dp[i - 1]
                    + Math.abs(arr[i] - arr[i - 1]);

            int ans = onestep;
            // 2 steps
            if (i - 2 >= 0) {
                int twosteps = dp[i - 2]
                        + Math.abs(arr[i] - arr[i - 2]);

                ans = Math.min(onestep, twosteps);
            }
            dp[i] = ans;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {

    }
}