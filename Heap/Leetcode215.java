package Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Leetcode215 {

    // tc is o(n log n) sc is o(1).
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    // optimal
    // tc is o(n log k) cause only adds the k ele in pq
    // sc is O(k) cause stores only k ele in pq
    public int findKthLargest2(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {

            pq.add(nums[i]);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        return pq.peek();
    }

}
