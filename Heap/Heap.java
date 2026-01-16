package Heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class Heap {

    public void minHeap() {

        // Create a min-heap (default behavior)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add elements (O(log n) time complexity per insertion)
        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(20);
        minHeap.add(15);
        minHeap.add(1);

        // Peek: retrieve the minimum element without removing it (O(1) time)
        System.out.println("Minimum element (peek): " + minHeap.peek()); // Output: 1

        // Poll: retrieve and remove the minimum element (O(log n) time)
        System.out.println("Removed element (poll): " + minHeap.poll()); // Output: 1
        System.out.println("New minimum element (peek): " + minHeap.peek()); // Output: 5

        // Check if the heap is empty
        System.out.println("Is heap empty? " + minHeap.isEmpty());
    }

    public void maxHeap() {
        // Create a Max Heap using PriorityQueue and a reverse order comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Insert elements
        maxHeap.add(10);
        maxHeap.add(30);
        maxHeap.add(20);
        maxHeap.add(5);
        maxHeap.add(50);

        // The peek() method returns the maximum element (the root)
        System.out.println("Maximum element (peek): " + maxHeap.peek()); // Output: 50

        // Extract elements (poll() removes the max element)
        System.out.print("Elements in descending order (poll): ");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " "); // Output: 50 30 20 10 5
        }
    }

    // tc is o(n/2) -> o(n) sc is o(1).
    public static boolean minHeapOrNot(int[] arr) {
        int n = arr.length;

        // Start from the last non-leaf node
        for (int i = (n / 2) - 1; i >= 0; i--) {
            int lChild = (2 * i) + 1;
            int rChild = (2 * i) + 2;

            // idx < total idx && leftchild < parent
            if (lChild < n && arr[lChild] < arr[i]) {
                return false;
            }

            // idx < total idx && rightchild < parent
            if (rChild < n && arr[rChild] < arr[i]) {
                return false;
            }
        }

        return true;
    }

    // tc is o(n) and sc is o(1).
    public static void convertMinHeapToMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = (n / 2) - 1; i >= 0; i--) {
            maxHeapifyIterative(arr, n, i);
        }
    }

    private static void maxHeapifyIterative(int[] arr, int n, int i) {
        while (i < n) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && arr[left] > arr[largest]) {
                largest = left;
            }

            if (right < n && arr[right] > arr[largest]) {
                largest = right;
            }

            if (largest == i)
                break;

            swap(arr, i, largest);
            i = largest;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

    }

}
