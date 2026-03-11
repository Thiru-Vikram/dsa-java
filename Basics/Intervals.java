package Basics;

import java.util.*;

public class Intervals {

    // tc and sc is o(n)
    // insert intervals func
    public int[][] insert(int[][] intervals, int[] newInterval) {

        int n = intervals.length;
        int i = 0;
        List<int[]> res = new ArrayList<>();

        // Add intervals that come before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }

        // Merge overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);

        // Add remaining intervals
        while (i < n) {
            res.add(intervals[i]);
            i++;
        }

        return res.toArray(new int[res.size()][]);
    }

    // merge intervals func
    // int[][] activities = {{1,4}, {2,3}, {5,6}, {9,10}};
    // Selected Activities: [1, 4] [5, 6] [9, 10]
    // tc is (n log n) - sort + o(n) loop = o(n log n)
    // sc is o(log n) arrays.sort uses extra space + o(n) for list = o(n)
    public int[][] merge(int[][] intervals) {

        // sort 1st ele
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            // get prev from result
            int[] last = result.get(result.size() - 1);
            int[] curr = intervals[i];

            // if curr start is <= prev end -> merge
            if (curr[0] <= last[1]) {
                last[1] = Math.max(last[1], curr[1]);
                // else add it sepearte straight
            } else {
                result.add(curr);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    // Count removed intervals (Erase Overlap Intervals)
    // TC: O(n log n) SC: O(1)
    public int eraseOverlapIntervals(int[][] intervals) {

        // Sort by end time
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int n = intervals.length;
        int cnt = 0;
        int lastEnd = intervals[0][1];

        for (int i = 1; i < n; i++) {
            if (intervals[i][0] < lastEnd) {
                // Overlap → remove, increment count
                cnt++;
            } else {
                // No overlap → update end time
                lastEnd = intervals[i][1];
            }
        }
        return cnt;
    }

    // Print kept intervals (Activity Selection)
    // TC: O(n log n) SC: O(n)
    public List<int[]> activitySelection(int[][] intervals) {

        // Sort by end time
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]); // Always keep first interval

        int n = intervals.length;
        int lastEnd = intervals[0][1];

        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= lastEnd) {
                // No overlap → keep this interval
                result.add(intervals[i]);
                lastEnd = intervals[i][1];
            }
            // Overlap → skip silently
        }
        return result;
    }
}
