package Neetcode150.Intervals;

import java.util.*;

// q is return the cnt of the no of overlapping intervals
// a is just check the curr interval start time < prev interval end time 
// then it overlaps cnt ++ else update prev to curr end time

// 435. Non-overlapping Intervals
public class Leetcode435 {

    // tc is o(n log n) sc is o(n)
    public static int eraseOverlapIntervals(int[][] intervals) {
        int ans = 0;

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int prev = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int curr = intervals[i][0];
            if (curr < prev) {
                ans++;
            } else {
                prev = intervals[i][1];
            }

        }
        return ans;

    }

    public static void main(String[] args) {

        int[][] grid = {
                { 1, 2 },
                { 2, 3 },
                { 4, 5 },
                { 5, 6 }
        };

        System.out.print(eraseOverlapIntervals(grid));

    }

}
