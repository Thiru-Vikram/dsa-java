package Neetcode150.Intervals;

import java.util.*;

class Interval {

    public int start, end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

// q is just check can we attend meetings or not
// a is check curr start time < prev end time then he cannot so return false
// 252. Meeting Rooms
public class Leetcode252 {

    // tc is o(n log n) sc is o(1)
    public static boolean canAttendMeetings(List<Interval> intervals) {

        int n = intervals.size();
        Collections.sort(intervals, (a, b) -> a.start - b.start);

        int prevTime = intervals.get(0).end;
        for (int i = 1; i < n; i++) {
            int currTime = intervals.get(i).start;
            if (currTime < prevTime) {
                return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {

        List<Interval> list = new ArrayList<>();
        list.add(new Interval(0, 3));
        list.add(new Interval(5, 10));
        list.add(new Interval(15, 20));

        System.out.print(canAttendMeetings(list));

    }

}