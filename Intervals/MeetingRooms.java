package Intervals;

import java.util.*;

public class MeetingRooms {

    // q:- given intervals check person can attend all the meetings
    // means if there is overlap return false else true.
    // tc is o(n log n) sc is o(n)
    public static boolean meetingRooms(int[][] intervals) {

        int n = intervals.length;
        if (n == 0)
            return true;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // first end time
        int prev = intervals[0][1];

        for (int i = 1; i < n; i++) {
            // curr start time
            int curr = intervals[i][0];
            if (curr < prev) {
                return false;
            }
            prev = intervals[i][1];
        }

        return true;
    }

    // Meeting rooms - II
    // q:- find min room need to organize the all meetings
    // means if u have same time two meetings u need two rooms &
    // if one meeting is finished u can use same room for another meeting
    // tc is o(n log n) sc is o(n)
    public static int meetingRooms2(int[][] intervals) {

        int n = intervals.length;
        // sort according to start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // pq for storing end time of each interval follows min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // push first meeting end time
        pq.add(intervals[0][1]);

        for (int i = 1; i < n; i++) {
            int[] curr = intervals[i];
            // check peek is <= curr start time
            // means meeting ended pop that from pq use same room again
            // if not add to pq means take another room
            if (pq.peek() <= curr[0]) {
                pq.poll();
            }
            pq.add(curr[1]);
        }

        return pq.size(); // tells how many rooms needed
    }

}
