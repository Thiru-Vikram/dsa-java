package Neetcode150.Intervals;

import java.util.*;

// 56. Merge Intervals
public class Leetcode56 {

    // tc is o(n log n) sc is o(n)
    public static int[][] merge(int[][] intervals) {

        // sort according to start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]); // add first one

        for (int i = 1; i < intervals.length; i++) {
            int[] prev = list.get(list.size() - 1);
            int[] curr = intervals[i];

            // check curr start <= prev end
            // then it merges
            if (curr[0] <= prev[1]) {
                prev[1] = Math.max(curr[1], prev[1]);
            } else {
                list.add(intervals[i]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    public static void main(String[] args) {

        int[][] grid = {
                { 4, 7 },
                { 1, 4 }
        };

        int[][] ans = merge(grid);
        for (int[] i : ans) {
            System.out.println(Arrays.toString(i));
        }

    }

}
