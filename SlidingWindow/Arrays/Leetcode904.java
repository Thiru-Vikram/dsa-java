package SlidingWindow.Arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 904. Fruit Into Baskets
public class Leetcode904 {

    // brute - force
    // tc is o(n^2) and sc is o(1).
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int maxLen = 0;

        // Try every starting position
        for (int i = 0; i < n; i++) {
            Set<Integer> types = new HashSet<>();

            // Try every ending position from i
            for (int j = i; j < n; j++) {
                types.add(fruits[j]);

                // If we have more than 2 types, break
                if (types.size() > 2) {
                    break;
                }

                // Valid subarray with at most 2 types
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }

        return maxLen;
    }

    // optimal
    // tc is o(n), sc is o(1) cause hashmap stores atmost only 3 types.
    public int totalFruit2(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < fruits.length; right++) {
            // Add current fruit to basket and count.
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            // If we have more than 2 types, shrink window from left
            while (basket.size() > 2) {
                // reduce the fruit count.
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                // remove fruit count = 0.
                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }
                left++;
            }

            // Update max length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
