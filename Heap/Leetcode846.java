package Heap;

import java.util.TreeMap;

public class Leetcode846 {

    // optimal
    // tc is o(n log n) n for loop, log n for tree insertion
    // sc is o(n) for tree.
    public boolean isNStraightHand(int[] hand, int groupSize) {

        // divisible or not.
        int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }

        // count each card with freq stores only unique and sorted order.
        TreeMap<Integer, Integer> cardCounts = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            cardCounts.put(hand[i], cardCounts.getOrDefault(hand[i], 0) + 1);
        }

        // check from map
        while (!cardCounts.isEmpty()) {
            int firstCard = cardCounts.firstKey(); // first card
            for (int i = 0; i < groupSize; i++) {
                int currCard = firstCard + i;// next card
                // follows consecutive if next card is present, false
                if (!cardCounts.containsKey(currCard)) {
                    return false;
                }

                // freq is 1 remove card
                int count = cardCounts.get(currCard);
                if (count == 1) {
                    cardCounts.remove(currCard);
                } else if (count > 1) {
                    cardCounts.put(currCard, count - 1); // reduce freq
                }
            }
        }

        return true;
    }

}
