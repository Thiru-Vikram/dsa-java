package SortingAlgos;

import java.util.ArrayList;
import java.util.Arrays;

public class Sorting {

    // tc is o(n^2) and sc is o(1).
    public static int[] selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) { // can also stop at n-1
            int minIdx = i; // Start with current position

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            // Swap AFTER finding the minimum (outside inner loop)
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }

        return arr;
    }

    // tc is o(n^2) and sc is o(1).
    public static int[] bubbleSort(int[] arr) {

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {

                if (arr[j] > arr[j + 1]) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (swapped == false) {
                break;
            }
        }
        return arr;
    }

    // tc is o(n log n) sc is o(n log n)
    private static void merge(int[] arr, int low, int mid, int high) {

        ArrayList<Integer> temp = new ArrayList<>();

        int left = low; // left arr staring point
        int right = mid + 1; // right arr starting point

        // compare two arr ele and add smallest
        while (left <= mid && right <= high) {

            if (arr[left] < arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                right++;
            }
        }
        // add remaining ele
        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }
        // low = 0, high is n-1, add ans in arr
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low); // it gives 1 idx
        }
    }

    private static void ms(int[] arr, int low, int high) {

        if (low == high)
            return;

        int mid = (low + high) / 2;

        ms(arr, low, mid);
        ms(arr, mid + 1, high);
        merge(arr, low, mid, high);

    }

    public static int[] mergeSort(int[] arr, int n) {

        ms(arr, 0, n - 1);
        return arr;
    }

    public static void main(String[] args) {

        int[] arr = { 4, 6, 8, 1, 9, 7, 3, 5, 2 };

        System.out.println(Arrays.toString(mergeSort(arr, arr.length)));

    }

}
