package Trees.BinaryTree;

import java.util.*;
import Trees.BinaryTree.Node;

// for traversal u can use recursion or levelorder its our wish but generaly
// peaople use recursion because in any problem we dont get the skwed tree.
public class Problems {

    // Find height or depth or total levels of binary tree
    // TC: O(n) - where n is the number of nodes, each node is visited exactly once
    // SC: O(h) - where h is the height of the tree (recursion call stack)
    // Best case: O(log n) for balanced tree, Worst case: O(n) for skewed tree
    public int height(Node root) {

        if (root == null)
            return 0;

        int left = height(root.left);
        int right = height(root.right);

        // curr node + max of left or right node
        return 1 + Math.max(left, right);
    }

    // check the binary tree is balanced or not
    // balanced means leftHeight - rightHeight = -1, 0, 1
    // TC: O(n) - each node visited once (early termination doesn't improve worst
    // case)
    // SC: O(h) - recursion call stack, Best case: O(log n), Worst case: O(n)
    public boolean isBalanced(Node root) {
        return dfsHeight(root) != -1;
    }

    private int dfsHeight(Node root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = dfsHeight(root.left);
        if (leftHeight == -1)
            return -1;
        int rightHeight = dfsHeight(root.right);
        if (rightHeight == -1)
            return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {

    }
}
