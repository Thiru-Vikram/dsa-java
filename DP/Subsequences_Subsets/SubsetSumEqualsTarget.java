package DP.Subsequences_Subsets;

public class SubsetSumEqualsTarget {

    // tc is o(2^n) for each ele it runs two times, sc is o(n) stack space
    private static boolean helper(int idx, int target, int[] arr) {

        if (target == 0)
            return true;
        if (idx == 0)
            return (arr[0] == target);

        boolean notTake = helper(idx - 1, target, arr);
        boolean take = false;
        if (target >= arr[idx]) {
            take = helper(idx - 1, target - arr[idx], arr);
        }

        return (take || notTake);
    }

    public static boolean subsetSumEqualToTarget(int[] arr, int target) {
        int n = arr.length;

        return helper(n - 1, target, arr);
    }

    // memoization sol
    // tc is o(n * target), sc is o(n) stack + o(n * target) array
    private static boolean helper2(int idx, int target, int[] arr, Boolean[][] dp) {

        if (target == 0)
            return true;
        if (idx == 0)
            return (arr[0] == target);

        if (dp[idx][target] != null) {
            return dp[idx][target];
        }

        boolean notTake = helper2(idx - 1, target, arr, dp);
        boolean take = false;
        if (target >= arr[idx]) {
            take = helper2(idx - 1, target - arr[idx], arr, dp);
        }

        dp[idx][target] = (take || notTake);

        return dp[idx][target];
    }

    public static boolean subsetSumEqualToTarget2(int[] arr, int target) {
        int n = arr.length;

        Boolean[][] dp = new Boolean[n][target + 1];

        return helper2(n - 1, target, arr, dp);
    }

    // tabulation sol
    // tc is o(n * target) sc is o(n * target)
    public static boolean subsetSumEqualToTarget3(int[] arr, int target) {
        int n = arr.length;

        boolean[][] dp = new boolean[n][target + 1];

        // Base case: target = 0 is always achievable (empty subset)
        for (int i = 0; i < n; i++)
            dp[i][0] = true;

        // Base case: first element
        if (arr[0] <= target) // Important check!
            dp[0][arr[0]] = true;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                boolean notTake = dp[i - 1][j];
                boolean take = false;
                if (arr[i] <= j) { // Fixed: check against j, not target
                    take = dp[i - 1][j - arr[i]];
                }
                dp[i][j] = take || notTake;
            }
        }

        return dp[n - 1][target];
    }

    public static void main(String[] args) {

        int[] arr = { 1, 2, 3, 4 };

        System.out.println(subsetSumEqualToTarget(arr, 4));

    }

}
