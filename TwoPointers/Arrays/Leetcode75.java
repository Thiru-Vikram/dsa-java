package Arrays;

import java.util.Arrays;

// Sort Colors
public class Leetcode75 {

    // brute - force
    // bubble sort
    // tc is o(n^2) and sc is o(1)
    public static void sortColors2(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // tc is o(n log n) and sc is o(1).
    public static void sortColors(int[] arr) {
        Arrays.sort(arr); // O(n log n)
    }

    // optimal - single pass
    // tc is o(n) and sc is o(1).
    public int[] sortColors3(int[] array) {

        int low = 0, mid = 0, high = array.length - 1;

        while (mid <= high) {

            if (array[mid] == 0) {
                swap(array, mid, low);
                low++;
                mid++;
            } else if (array[mid] == 1) {
                mid++;
            } else {
                swap(array, mid, high);
                high--;
            }
        }

        return array;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {

    }

}
