package Trees.BinaryTree;

import java.util.*;

// for traversal u can use recursion or levelorder its our wish but generaly
// peaople use recursion because in any problem we dont get the skwed tree.
public class Intro {

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
            return true;
        if (p == null || q == null)
            return false;

        return (p.val == q.val) &&
                isIdentical(p.left, q.left) &&
                isIdentical(p.right, q.right);
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

    // leetcode 112 path sum given target need to find path sum
    // root to leaft = target sum
    public boolean pathSum(Node root, int target) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null) {
            return target == root.val;
        }
        boolean leftSum = pathSum(root.left, target - root.val);
        boolean rightSum = pathSum(root.right, target - root.val);
        return leftSum || rightSum;
    }

    // we use level order + col numbers to get ans
    // Top View: for each horizontal column, keep the FIRST node seen (topmost)
    // TC: O(n log n) — n nodes, TreeMap operations cost log n
    // SC: O(n) — queue + map
    public List<Integer> topViewBT(Node root) {

        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        // stores (col, node.val)
        Map<Integer, Integer> treeMap = new TreeMap<>();
        // stores (node, col)
        Queue<Object[]> q = new LinkedList<>();
        // starting node root, col is 0 left -1, -2; right side 1, 2
        q.offer(new Object[] { root, 0 }); // push into q

        while (!q.isEmpty()) {
            Object[] pair = q.poll();
            Node node = (Node) pair[0];
            int col = (int) pair[1];

            // checking is there any prev ele with same col
            // if yes skip else add by this u get all the first ele each col
            // in the bt called top view
            if (!treeMap.containsKey(col)) {
                treeMap.put(col, node.val);
            }

            // push curr node children into q
            // go for left from root -1, -2 ..
            if (node.left != null)
                q.offer(new Object[] { node.left, col - 1 });
            // go right from root 1, 2, ...
            if (node.right != null)
                q.offer(new Object[] { node.right, col + 1 });

        }
        // adding all values of tree which is ans
        // also sorted cause we used tree there
        ans.addAll(treeMap.values());

        return ans;
    }

    // TC: O(n) — each node visited once
    // SC: O(w) — map stores unique columns (worst O(n) if all nodes differ in
    // columns)
    // Top view -> if(!colMap.containsKey(col)) — skip if already filled
    // Bottom view -> colMap.put(col, node.val) — always overwrite
    public List<Integer> bottomViewBT(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        Map<Integer, Integer> treeMap = new HashMap<>();
        Queue<Object[]> q = new LinkedList<>();
        q.offer(new Object[] { root, 0 });

        while (!q.isEmpty()) {
            Object[] pair = q.poll();
            Node node = (Node) pair[0];
            int col = (int) pair[1];

            // overwrite always so at last u get bottom most node
            // that is the ans
            treeMap.put(col, node.val);

            if (node.left != null) {
                q.offer(new Object[] { node.left, col - 1 });
            }
            if (node.right != null) {
                q.offer(new Object[] { node.right, col + 1 });
            }
        }
        ans.addAll(treeMap.values());
        return ans;
    }

    public static void main(String[] args) {

    }
}
