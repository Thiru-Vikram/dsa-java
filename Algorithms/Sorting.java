package Algorithms;

import java.util.*;

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

    // quick sort
    // 1. pick pivot, 1st or last or mid or random
    // place pivot in its crt place in sorted arr
    // 2. larger on left, smaller on right and swap
    // 3. place pivot at crt pos
    // reapet the process
    // tc is Best Case -> O(n log n), Average Case -> O(n log n), Worst Case ->
    // O(n²)
    // sc is Best Case -> O(log n), Average Case -> O(log n), Worst Case -> O(n)

    public static int[] quickSort(int[] array) {
        qS(array, 0, array.length - 1);
        return array;
    }

    private static void qS(int[] array, int low, int high) {

        if (low < high) {

            int pIndex = partition(array, low, high);
            qS(array, low, pIndex - 1); // left arr
            qS(array, pIndex + 1, high); // right arr
        }
    }

    private static int partition(int[] array, int low, int high) {

        // arr fst ele as pivot
        int pivot = array[low];
        int i = low;
        int j = high;

        while (i < j) {
            // finding next element > pivot stops at greater ele
            while (array[i] <= pivot && i <= high - 1) {
                i++;
            }
            // finding next element <= pivot stops at smaller ele
            while (array[j] > pivot && j >= low + 1) {
                j--;
            }
            // swapping small to left and large to right
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // swapping low pivot to crt place
        int temp = array[low];
        array[low] = array[j];
        array[j] = temp;

        return j; // partition ele
    }

    public static void main(String[] args) {

        int[] arr = { 4, 6, 8, 1, 9, 7, 3, 5, 2 };

        System.out.println(Arrays.toString(quickSort(arr)));

    }

}
