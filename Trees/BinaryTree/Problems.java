package Trees.BinaryTree;

import java.util.*;

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

    // invert binary tree leetcode 226
    // tc is o(n) sc is o(h)
    // algo is check node is null means leave
    // else swap left with right side.
    public Node invertTree(Node root) {
        if (root == null)
            return null;

        // swap left with right
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left); // do for left side
        invertTree(root.right); // do for right side

        return root;
    }

    // The diameter of a binary tree is the length of the
    // longest path between any two nodes in the tree.
    // skew tree example
    // 1
    // /
    // 2
    // /
    // 3
    // /
    // 4
    // Diameter = 3 (path: 4 → 3 → 2 → 1)

    // tc is o(n) sc is o(h)
    public int diameterOfBinaryTree(Node root) {
        int[] diameter = new int[1];
        helper(root, diameter);
        return diameter[0];
    }

    private int helper(Node root, int[] diameter) {
        if (root == null)
            return 0;

        int leftH = helper(root.left, diameter); // left height
        int rightH = helper(root.right, diameter); // right height
        diameter[0] = Math.max(diameter[0], leftH + rightH);
        // Return height of THIS node (max of children + 1)
        return Math.max(leftH, rightH) + 1;
    }

    // check binary tree is identical or not
    // tc is o(min(p, q)) cause stops at first mismatch
    // sc is o(min(h1, h2))
    public boolean isIdentical(Node p, Node q) {
        if (p == null && q == null)
            return false;
        if (p == null || q == null)
            return false;

        return (p.val == q.val) &&
                isIdentical(p.left, q.left) &&
                isIdentical(p.right, q.right);
    }

    // find max path sum in binary tree
    // tc is o(n) sc is o(h)
    public int maxPathSum(Node root) {
        int[] maxValue = new int[1];
        maxValue[0] = Integer.MIN_VALUE;
        findMaxPath(root, maxValue);
        return maxValue[0];
    }

    private int findMaxPath(Node root, int[] maxValue) {
        if (root == null)
            return 0;

        // Ignore negative paths (if sum < 0, take 0 instead)
        int left = Math.max(0, findMaxPath(root.left, maxValue));
        int right = Math.max(0, findMaxPath(root.right, maxValue));

        // Update max: path through current node + left + right
        maxValue[0] = Math.max(maxValue[0], left + right + root.val);

        // take any one left or right child with root.
        return Math.max(left, right) + root.val;
    }

    // print the boundary nodes means root -> left -> leaf -> right
    // TC: O(n) — visits each node once
    // SC: O(h) + O(leaf count) — recursion stack + temporary storage for right
    // boundary
    private void addRightBoundary(Node root, List<Integer> ans) {
        Node curr = root.right;
        List<Integer> temp = new ArrayList<>();
        while (curr != null) {
            if (!leafNode(curr)) {
                temp.add(curr.val);
            }
            if (curr.right != null) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        // right boundary is bottom to up so adding from back
        for (int i = temp.size() - 1; i >= 0; i--) {
            ans.add(temp.get(i));
        }
    }

    private void addLeafBoundary(Node root, List<Integer> ans) {
        if (leafNode(root)) {
            ans.add(root.val);
            return;
        }
        if (root.left != null) {
            addLeafBoundary(root.left, ans);
        }
        if (root.right != null) {
            addLeafBoundary(root.right, ans);
        }

    }

    private void addLeftBoundary(Node root, List<Integer> ans) {
        Node curr = root.left;
        while (curr != null) {
            if (!leafNode(curr)) {
                ans.add(curr.val);
            }
            if (curr.left != null) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }

    private boolean leafNode(Node root) {
        return root.left == null && root.right == null;
    }

    public List<Integer> printBoundary(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (!leafNode(root)) {
            ans.add(root.val);
        }

        addLeftBoundary(root, ans);
        addLeafBoundary(root, ans);
        addRightBoundary(root, ans);

        return ans;
    }

    public static void main(String[] args) {

    }
}
