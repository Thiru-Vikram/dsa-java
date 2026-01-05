package Arrays;

import java.util.HashMap;

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

    // optimal tc is o(n) for traversal and sc is o(n) for hashmap storing n
    // elements.
    public boolean containsNearbyDuplicate2(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(nums[i])) {

                int abs = Math.abs(i - map.get(nums[i]));

                if (abs <= k) {
                    return true;
                }
            }

            map.put(nums[i], i);
        }

        return false;
    }

    public static void main(String[] args) {

    }
}