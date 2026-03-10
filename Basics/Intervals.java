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

    // remove intervals func
    // int[][] activities = {{1,3}, {2,5}, {4,6}, {6,7}};
    // Selected Activities: [1, 3] [4, 6] [6, 7]
    // tc is o(n log n) sc is o(n)
    public static List<int[]> activitySelection(int[][] activities) {
        Arrays.sort(activities, (a, b) -> a[1] - b[1]);

        List<int[]> result = new ArrayList<>();
        result.add(activities[0]);
        int lastEnd = activities[0][1];

        for (int i = 1; i < activities.length; i++) {
            if (activities[i][0] >= lastEnd) {
                result.add(activities[i]);
                lastEnd = activities[i][1];
            }
        }
        return result;
    }

}
