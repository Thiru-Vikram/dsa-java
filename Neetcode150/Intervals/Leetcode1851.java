package Neetcode150.Intervals;

import java.util.*;

// q is check the queries num and return its smallest len interval 
// ex 2, 2 falls in two intervals 1,4 -> len 4 and 2,4 -> len is 3
// and is 3 smallest len like wise find for all

// ans is we need to sort queries and intervals start time
// we use min heap to store len, endtime so here when u push we will get the ans min len of that query
// update the ans in the ans array
public class Leetcode1851 {

    // tc is o(n log n) + o(q log n)
    // sc is o(n)
    public int[] minInterval(int[][] intervals, int[] queries) {

        int m = intervals.length;
        int n = queries.length;

        // storing queries with idx
        int[][] qs = new int[n][2];
        for (int i = 0; i < n; i++) {
            qs[i][0] = queries[i];
            qs[i][1] = i;
        }

        // sorting intervals acc to start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Arrays.sort(qs, (a, b) -> a[0] - b[0]); // acc to queries

        // len of interval, endtime, min heap
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                // if len is same sort acc to endtime else len
                (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int[] result = new int[n];

        int j = 0;
        for (int i = 0; i < n; i++) {
            int queryVal = qs[i][0];
            int queryIdx = qs[i][1];

            // add ele interval start time <= curr queryval
            while (j < m && intervals[j][0] <= queryVal) {
                // len, endtime
                pq.add(new int[] { (intervals[j][1] - intervals[j][0]) + 1,
                        intervals[j][1] });
                j++;
            }

            // remove intervals which is end time is < queryVal
            while (!pq.isEmpty() && pq.peek()[1] < queryVal) {
                pq.remove();
            }

            // store ans in curr idx
            result[queryIdx] = pq.isEmpty() ? -1 : pq.peek()[0];
        }

        return result;

    }

    public static void main(String[] args) {

    }

}
