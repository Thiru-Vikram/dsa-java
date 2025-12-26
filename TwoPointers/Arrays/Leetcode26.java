package Arrays;

import java.util.HashSet;

public class Leetcode26 {

    // brute - force
    // tc is o(n^2) and sc is o(1).
    public int removeDuplicates(int[] arr) {
        int n = arr.length;
        int index = 0;

        for (int i = 0; i < n; i++) {
            boolean isDuplicate = false;

            // Check if arr[i] appeared before
            for (int j = 0; j < index; j++) {
                if (arr[i] == arr[j]) {
                    isDuplicate = true;
                    break;
                }
            }

            // If not duplicate, place it at current index
            if (!isDuplicate) {
                arr[index++] = arr[i];
            }
        }
        return index; // number of unique elements
    }

    // average
    // tc is o(n) for traversal, sc is o(n) for hashmaps.
    public int removeDuplicates2(int[] arr) {
        int n = arr.length;
        HashSet<Integer> set = new HashSet<>();
        int idx = 0;

        for (int i = 0; i < n; i++) {
            int num = arr[i];
            if (!set.contains(num)) {
                set.add(num);
                arr[idx++] = arr[i];
            }
        }
        return idx;
    }

    // optimal solution
    // tc is o(n) for traversal, sc is o(1).
    public int removeDuplicates3(int[] arr) {
        int n = arr.length;
        int i = 0;

        for (int j = 1; j < n; j++) {
            if (arr[j] != arr[i]) {
                i++;
                arr[i] = arr[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {

    }

}
