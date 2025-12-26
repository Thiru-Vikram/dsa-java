package Arrays;

import java.util.*;

public class Leetcode1 {

    // brute - force
    // tc is o(n^2) and sc is o(1).
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] {};
    }

    // optimal - solution for unsorted array.
    // tc is o(n) and sc is o(n).
    public int[] twoSum2(int[] array, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            int a = array[i];
            int more = target - a;
            if (map.containsKey(more)) {
                return new int[] { map.get(more), i };
            } else {
                map.put(a, i);
            }
        }
        return new int[] { -1, -1 };
    }

    // optimal - solution for sorted array.
    // tc is o(n) and sc is o(1).
    public int[] twoSum3(int[] arr, int target) {
        int i = 0;
        int j = arr.length - 1;

        while (i < j) {
            int sum = arr[i] + arr[j];

            if (sum == target) {
                return new int[] { i, j };
            } else if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            }
        }
        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {

    }
}