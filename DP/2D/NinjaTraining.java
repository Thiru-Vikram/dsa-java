import java.util.*;

public class NinjaTraining {

    // brute force recursion sol
    // tc is o(3^n) sc is o(n)
    public static int ninjaTraining(int n, int[][] points) {
        // day = n - 1 (start from the last day), last = 3 (no task restricted on day
        // n-1 initially)
        return helper(n - 1, 3, points);
    }

    public static int helper(int day, int last, int[][] points) {
        // Base case: on day 0, choose the best task != last
        if (day == 0) {
            int maxi = 0;
            for (int task = 0; task < 3; task++) {
                if (task != last) {
                    maxi = Math.max(maxi, points[0][task]);
                }
            }
            return maxi;
        }

        int maxi = 0;
        for (int task = 0; task < 3; task++) {
            if (task != last) {
                int point = points[day][task] + helper(day - 1, task, points);
                maxi = Math.max(maxi, point);
            }
        }
        return maxi;
    }

    // memoization sol
    // tc is o(n) sc is o(n) + o(n)
    public static int memoization(int n, int[][] points) {
        // Fix: Size the second dimension to 4 because 'last' can be 0, 1, 2, or 3
        int[][] dp = new int[n][4];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        return helper2(n - 1, 3, points, dp);
    }

    public static int helper2(int day, int last, int[][] points, int[][] dp) {
        if (day == 0) {
            int maxi = 0;
            for (int task = 0; task < 3; task++) {
                if (task != last) {
                    maxi = Math.max(maxi, points[0][task]);
                }
            }
            return maxi;
        }

        if (dp[day][last] != -1)
            return dp[day][last];

        int maxi = 0;
        for (int task = 0; task < 3; task++) {
            if (task != last) {
                int point = points[day][task] + helper2(day - 1, task, points, dp);
                maxi = Math.max(maxi, point);
            }
        }

        return dp[day][last] = maxi;
    }

    // tc is o(n) sc is o(n)
    public static int tabulation(int n, int[][] points) {
        int[][] dp = new int[n][4];

        // Base case initialization for day 0
        dp[0][0] = Math.max(points[0][1], points[0][2]); // If last was 0, take max of task 1 or 2
        dp[0][1] = Math.max(points[0][0], points[0][2]); // If last was 1, take max of task 0 or 2
        dp[0][2] = Math.max(points[0][0], points[0][1]); // Task 0 or 1
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2])); // max from all 3 tasks

        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0;

                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        int point = points[day][task] + dp[day - 1][task];
                        dp[day][last] = Math.max(dp[day][last], point);
                    }
                }
            }
        }

        return dp[n - 1][3]; // State 3 means no restriction on the final day
    }

    // space optimised
    // what do u need only the prev row to compute next row so just store vales
    // and reuse the 1d array
    // tc is o(n) sc is o(1)
    public static int spaceOptimised(int n, int[][] points) {
        int[] prev = new int[4];

        // Base case initialization for day 0
        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for (int day = 1; day < n; day++) {
            int[] temp = new int[4]; // create temp arr
            for (int last = 0; last < 4; last++) {
                temp[last] = 0; // store curr row ans in temp

                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        int point = points[day][task] + prev[task];
                        // store curr row values in temp
                        temp[last] = Math.max(temp[last], point);
                    }
                }
            }
            // replace the prev row with curr values
            // to use this as prev for next row
            prev = temp;
        }

        return prev[3];
    }

    public static void main(String[] args) {

    }

}
