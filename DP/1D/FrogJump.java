import java.util.*;

// # Frog Jump – Recursion

// ## Problem Statement

// You are given an array `arr` where `arr[i]` represents the height of the `i-th` stair. A frog starts from the first stair (index `0`) and needs to reach the last stair (index `n-1`).

// The frog can jump either:
// - **1 stair**
// - **2 stairs**

// The cost of jumping from stair `i` to stair `j` is:

// `|arr[i] - arr[j]|`

// Your task is to find the **minimum total cost** required for the frog to reach the last stair.

// ## Sample Input

// ```text
// arr = [10, 20, 30, 10]
// ```

// ## Sample Output

// ```text
// 20
// ```

// ## Explanation

// The frog can take the path:
// ```text
// 10 → 20 → 10
// ```

// The total cost is:
// ```text
// |20 - 10| + |10 - 20|
// = 10 + 10
// = 20
// ```

// Therefore, the minimum cost required is **20**.

public class FrogJump {

    // recursion sol
    // Time Complexity : O(2^n)
    // Space Complexity : O(n)
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

    public static int frogJump(int[] arr) {
        return helper(arr.length - 1, arr);
    }

    // memo sol
    // Time Complexity : O(n)
    // Space Complexity : O(n)
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

    public static int frogJump2(int[] arr) {
        int[] dp = new int[arr.length];

        return helper2(arr.length - 1, arr, dp);
    }

    public static void main(String[] args) {

    }
}