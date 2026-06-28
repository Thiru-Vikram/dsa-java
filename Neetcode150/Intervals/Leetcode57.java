package Neetcode150.Intervals;

// 57. Insert Interval
import java.util.*;

// q is insert the intervals in between and merge them
// a is 1. three steps add intervals which comes before new one
// 2. merge the intervals 
// 3. add remaining intervals at last

public class Leetcode57 {

    // tc is o(n) and sc is o(n)
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int i = 0;
        List<int[]> list = new ArrayList<>();

        // adding intervals which comes before new one
        while (i < n && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i]);
            i++;
        }

        // merge overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            // 0 - min b/w two intervals
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            // 1 - max b/w two intervals
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        list.add(newInterval);

        // add remaining intervals
        while (i < n) {
            list.add(intervals[i]);
            i++;
        }

        return list.toArray(new int[list.size()][]);
    }

    public static void main(String[] args) {

        int[][] grid = {
                { 1, 2 },
                { 3, 5 },
                { 6, 7 },
                { 8, 10 },
                { 12, 16 }
        };
        int[] newInt = { 4, 8 };
        int[][] ans = insert(grid, newInt);
        for (int[] i : ans) {
            System.out.println(Arrays.toString(i));
        }

    }

}
