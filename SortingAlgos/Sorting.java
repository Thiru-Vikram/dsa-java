package SortingAlgos;

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

    public static void main(String[] args) {

    }

}
