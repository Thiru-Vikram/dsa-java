package LinkedList;

import java.util.PriorityQueue;

class Node {

    int val;
    Node next;

    Node() {

    }

    Node(int val) {
        this.val = val;
        this.next = null;
    }

    Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

}

public class SingleLinkedList {

    // Reverse LL
    // tc and sc is o(n) and o(1)
    public static Node reverseLL(Node head) {

        Node temp = head;
        Node prev = null;
        while (temp != null) {
            Node frnt = temp.next;
            temp.next = prev;
            prev = temp;
            temp = frnt;
        }
        return prev;
    }

    // check LL has cycle or not
    // tc is o(n) sc is o(1)
    public static boolean hasCycle(Node head) {

        Node fast = head;
        Node slow = head;

        // If fast is at the last node (not null), but fast.next is null, then
        // fast.next.next throws a NullPointerException to prevent this we use
        // fast.next != null condtion.
        // cur fast not null and fast.next not null
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow)
                return true;
        }
        return false;
    }

    // merge two Linked List
    // tc is o(m + n) and sc is o(1)
    public static Node mergeTwoLL(Node head1, Node head2) {

        // dummy node
        Node dummy = new Node();
        // pointer
        Node curr = dummy;

        while (head1 != null && head2 != null) {

            // compare and connect
            if (head1.val <= head2.val) {
                curr.next = head1;
                head1 = head1.next;
            } else {
                curr.next = head2;
                head2 = head2.next;
            }

            // move pointer
            curr = curr.next;

        }

        // connect to the remaining nodes of the head
        curr.next = (head1 != null) ? head1 : head2;

        return dummy.next;

    }

    // merge k sorted list
    // algo is simple add one by one node in pq and make the LL
    // tc is You do n total push + poll operations (one per node)
    // Each push/poll costs O(log k) Total: O(n log k)
    // sc is o(k) pq stores at most k nodes
    public static Node mergeKList(Node[] heads) {

        // pq stores nodes of each list folows min heap
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        // at first only head of each list is added
        for (Node head : heads) {
            if (head != null) {
                pq.add(head);
            }
        }

        // dummy node and pointer
        Node dummy = new Node();
        Node curr = dummy;

        while (!pq.isEmpty()) {
            // smallest ele in pq
            Node smallest = pq.poll();
            curr.next = smallest; // making list
            curr = curr.next; // move curr
            // if that nodes next is not null add it in pq
            // means refilling the pq
            if (smallest.next != null) {
                pq.add(smallest.next);
            }
        }

        return dummy.next;

    }

}
