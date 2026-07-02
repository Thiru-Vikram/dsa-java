package Neetcode150.Intervals;

import java.util.*;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

// q is return the max num of rooms needed for meeting
// a is we use pq here add the end time to pq now compare curr start time is >=
// prev
// means no collide so remove ele in pq add this if not we need another room add
// it pq

// 253. Meeting Rooms II
public class Leetcode253 {

    // tc is o(n log n) sc is o(n log n)
    public static int minMeetingRooms(List<Interval> intervals) {

        int n = intervals.size();
        if (intervals == null || n == 0)
            return 0;

        intervals.sort((a, b) -> a.start - b.start);
        // default pq is min
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(intervals.get(0).end);

        for (int i = 1; i < n; i++) {

            int curr = intervals.get(i).start;
            // if curr start time >= prev end time no collide
            // we can use same room so pop that prev and add curr
            if (curr >= pq.peek()) {
                pq.poll();
            }
            // if its collide we need another room add it to pq
            pq.add(intervals.get(i).end);
        }

        return pq.size();
    }

    public static void main(String[] args) {

        List<Interval> list = new ArrayList<>();
        list.add(new Interval(0, 40));
        list.add(new Interval(5, 10));
        list.add(new Interval(15, 20));
        System.out.print(minMeetingRooms(list));

    }

}
