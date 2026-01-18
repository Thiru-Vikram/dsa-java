package Heap;

import java.util.PriorityQueue;

class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}

public class Leetcode23 {

    // brute force.
    // tc is O(k × N) sc is o(1).
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        ListNode result = lists[0];

        // Merge each list one by one into result
        for (int i = 1; i < lists.length; i++) {
            result = mergeTwoLists(result, lists[i]);
        }

        return result;
    }

    // Helper: merge two sorted lists
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        current.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    // optimal
    // tc is o(n log k) k means no of lists add k ele in pq.
    // sc is o(k) cause stores k ele in pq
    public ListNode mergeKLists2(ListNode[] lists) {

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the head of each list to the heap
        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!pq.isEmpty()) {
            // Get the smallest node
            ListNode smallest = pq.poll();
            current.next = smallest;
            current = current.next;

            // again add the next ele of smallest in pq
            if (smallest.next != null) {
                pq.add(smallest.next);
            }
        }

        return dummy.next;
    }

}
