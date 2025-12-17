package DP;

import java.util.*;

public class Leetcode78 {

    // Subsets (Power Set) – Revision Notes
    // Given a string or array of length **n**, the total number of possible subsets
    // (including the empty subset) is: **2^n**
    // This is because for every element, we have exactly **two choices**:
    // 1. Include the element
    // 2. Exclude the element
    // If `s = "abc"` and `n = 3`, the subsets are:
    // "", a, b, c, ab, ac, bc, abc
    // Total subsets = `2^3 = 8`
    // Bit Manipulation Intuition
    // Each subset can be represented using a **binary number (mask)** of length
    // `n`.
    // * `1` → element is picked
    // * `0` → element is not picked

    // Example
    // If the binary number is `5`:
    // 5 → 101 (binary)
    // Indexing from right to left (`0, 1, 2`):
    // * Index 0 → `1` → pick element
    // * Index 1 → `0` → do not pick
    // * Index 2 → `1` → pick element

    // To check whether the i-th bit is set, use:
    // (n & (1 << i)) != 0
    // * If the result is **not equal to zero**, the bit is set
    // * If the result is **zero**, the bit is not set

    // ### Example
    // Check 2nd bit of `101`:
    // 1 0 1
    // & 1 0 0
    // --------
    // 1 0 0 → bit is set

    // Check 1st bit:
    // 1 0 1
    // & 0 1 0
    // --------
    // 0 0 0 → bit is not set

    // ## Approach 1: Recursion / Backtracking
    // At every index, we have two choices:
    // 1. Do not pick the current element
    // 2. Pick the current element

    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void backtrack(int[] nums, int index, List<Integer> current, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(current)); // create a copy
            return;
        }

        // Option 1: not pick
        backtrack(nums, index + 1, current, ans);

        // Option 2: pick
        current.add(nums[index]);
        backtrack(nums, index + 1, current, ans);
        current.remove(current.size() - 1); // backtrack
    }

    // ### Time and Space Complexity

    // * **Time Complexity:** `O(n × 2^n)`
    // * **Space Complexity:** `O(n × 2^n)` (to store all subsets)
    // * Extra recursion stack space: `O(n)`

    // ## Approach 2: Bit Manipulation (Iterative)

    // Every number from `0` to `(2^n - 1)` represents one subset.

    public List<List<Integer>> subsets2(int[] nums) {
        int totalSubsets = 1 << nums.length; // 2^n
        List<List<Integer>> ans = new ArrayList<>();

        for (int mask = 0; mask < totalSubsets; mask++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                // 1 means pick, 0 means not pick
                if ((mask & (1 << j)) != 0) {
                    list.add(nums[j]);
                }
            }
            ans.add(list);
        }
        return ans;
    }

    // ### Time and Space Complexity
    // * **Time Complexity:** `O(n × 2^n)`
    // * **Space Complexity:** `O(n × 2^n)`
    // * No recursion stack, so slightly faster than recursive approach

}
