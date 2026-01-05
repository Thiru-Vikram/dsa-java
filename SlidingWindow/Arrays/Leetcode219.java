package SlidingWindow.Arrays;

import java.util.HashSet;

public class Leetcode219 {

    // brute force tc is o(n^2) loops , sc is o(1).
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && j - i <= k) {
                    return true;
                }
            }
        }

        return false;
    }

    // average using hashmap tc is o(n) and sc is o(n) refer in leetcode.

    // optimal tc is o(n) and sc is o(min(n, k)).
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        HashSet<Integer> window = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            // checking condition only at most k ele in window
            // If window size exceeds k, remove the leftmost element
            if (i > k) {
                window.remove(nums[i - k - 1]);
            }

            // If current element is already in the window, we found a duplicate
            if (!window.add(nums[i])) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

    }
}