package Heap;

import java.util.*;

public class Leetcode621 {

    // optimal
    // tc is o(n) cause eveything is constant sc is o(1).
    public int leastInterval(char[] tasks, int n) {

        // creating and updating count of each char
        int[] charCount = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            charCount[tasks[i] - 'A']++;
        }
        Arrays.sort(charCount); // sortning but const.
        int maxFreq = charCount[25];
        int idle = (maxFreq - 1) * n;// finding idle using formula.
        // finding next min from last before and reducing the idle.
        for (int i = 24; i >= 0; i--) {
            idle -= Math.min(maxFreq - 1, charCount[i]);
            idle = Math.max(0, idle);
        }

        // length + idle = answer.
        return tasks.length + idle;
    }

    public static void main(String[] args) {

    }
}