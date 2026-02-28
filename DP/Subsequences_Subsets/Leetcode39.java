package DP.Subsequences_Subsets;

import java.util.*;

// 39. Combination Sum -> same as coin change prob denominatons arr with target sum
class Leetcode39 {

    // this is the optimal solu
    // TC: O(2^t) where t = target
    // Because at each step you have 2 choices (take or not take) and the depth goes
    // up to target/arr[i] in worst case.
    // SC: O(k * x)
    // k = average length of each combination
    // x = number of valid combinations
    // Plus O(t) recursion stack depth (worst case you keep taking smallest element)
    private void helper(int idx, int target, int[] arr,
            List<Integer> temp, List<List<Integer>> ans) {

        // Base case: reached first element
        if (idx == 0) {
            // check cur num satisfies the target
            if (target % arr[0] == 0) {
                // then add that many times in ans
                for (int i = 0; i < target / arr[0]; i++) {
                    temp.add(arr[0]);
                }
                ans.add(new ArrayList<>(temp));
                // backtrack
                // here we only use one temp so add into temp add temp to ans clean the temp
                // reuse it so we remove what we added
                for (int i = 0; i < target / arr[0]; i++) {
                    temp.remove(temp.size() - 1);
                }
            }
            return;
        }

        // not take
        // move next, same target, arr, temp, ans
        helper(idx - 1, target, arr, temp, ans);

        // cause we allow mutiple times
        // take (same idx, because we can reuse)
        if (arr[idx] <= target) {
            temp.add(arr[idx]);
            helper(idx, target - arr[idx], arr, temp, ans);
            temp.remove(temp.size() - 1); // backtrack
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int n = candidates.length;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates); // sort for clean order
        helper(n - 1, target, candidates, temp, ans);
        return ans;
    }

    public static void main(String[] args) {

    }

}
