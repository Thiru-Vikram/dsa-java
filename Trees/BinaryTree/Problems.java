package Trees.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class Problems {

    // tc is o(n) all nodes visit once
    // SC: O(h) — recursion call stack (best O(log n) for balanced, worst O(n) for
    // skewed)
    // we use pre order trav left->right->root cause we need to know
    // subtree first to get lca
    public static Node lowestCommonAncestor(Node root, Node p, Node q) {
        // base case if any one node is root then it is lca of both
        if (root == null || p == root || q == root) {
            return root;
        }

        // go left first and right
        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);

        // means p & q are in right subtree so return right
        if (left == null) {
            return right;
            // means p & q in left return left
        } else if (right == null) {
            return left;
            // if both are not null then curr node is splitting them return that.
        } else {
            return root;
        }
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

    // max path sum 2 prob lc 113
    // TC: O(n) — visits all nodes
    // SC: O(n) — in worst case, could have O(n) paths stored
    public List<List<Integer>> pathSum(Node root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        maxPath(root, sum, new ArrayList<Integer>(), res);
        return res;
    }

    private void maxPath(Node root, int sum, List<Integer> sol, List<List<Integer>> res) {
        if (root == null)
            return;

        sol.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            // one path is found add to res
            res.add(new ArrayList<>(sol));
        } else {
            // try for left and then right
            maxPath(root.left, sum - root.val, sol, res);
            maxPath(root.right, sum - root.val, sol, res);
        }

        // backtrack check another path with curr node is not remove go back
        sol.remove(sol.size() - 1);

    }

    // Count nodes in Complete Binary Tree — LC 222, has a O(log²n) trick

    public static void main(String[] args) {

    }

}
