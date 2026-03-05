package Basics;

import java.util.*;

public class Intervals {

    // remove intervals func
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
    // int[][] activities = {{1,3}, {2,5}, {4,6}, {6,7}};
    // Selected Activities: [1, 3] [4, 6] [6, 7]

    // merge intervals func
    public static List<int[]> activitySelection2(int[][] activities) {
        Arrays.sort(activities, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        result.add(activities[0]);

        for (int i = 1; i < activities.length; i++) {
            int[] last = result.get(result.size() - 1);
            int[] curr = activities[i];

            if (curr[0] <= last[1]) {
                last[1] = Math.max(last[1], curr[1]);
            } else {
                result.add(curr);
            }
        }
        return result;
    }
    // int[][] activities = {{1,4}, {2,3}, {5,6}, {9,10}};
    // Selected Activities: [1, 4] [5, 6] [9, 10]

}
