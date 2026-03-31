package Trees.BinaryTree;

import java.util.*;

public class Node {

    int val;
    Node left;
    Node right;

    Node(int val) {
        this.val = val;
    }

    // preorder traversal recursion sol
    // TC: O(n) - where n is the number of nodes, each node is visited exactly once
    // SC: O(h) - where h is the height of the tree (recursion call stack)
    // Best case: O(log n) for balanced tree, Worst case: O(n) for skewed tree
    public List<Integer> preorderTraversal(Node root) {

        List<Integer> ans = new ArrayList<>();
        preorder(root, ans);

        return ans;
    }

    private void preorder(Node root, List<Integer> ans) {
        // root > left > right
        if (root == null)
            return;
        ans.add(root.val);
        preorder(root.left, ans);
        preorder(root.right, ans);
    }

    // preorder traversal iterative sol
    // TC: O(n) - where n is the number of nodes, each node is visited exactly once
    // SC: O(h) - where h is the height of the tree (stack holds nodes)
    // Best case: O(log n) for balanced tree, Worst case: O(n) for skewed tree
    public List<Integer> preorderTraversal2(Node root) {

        List<Integer> ans = new ArrayList<>();
        Stack<Node> st = new Stack<>();

        // adding root to st
        if (root == null)
            return ans;
        st.push(root);

        while (!st.isEmpty()) {
            root = st.pop();
            ans.add(root.val);

            // why right first means we using stack follows lifo operation
            // right and left we get from stack left comes first
            // root -> left -> right
            // right to st
            if (root.right != null) {
                st.push(root.right);
            }
            // left to st
            if (root.left != null) {
                st.push(root.left);
            }
        }

        return ans;
    }

    // inorder traversal recursion sol
    // TC: O(n) - where n is the number of nodes, each node is visited exactly once
    // SC: O(h) - where h is the height of the tree (recursion call stack)
    // Best case: O(log n) for balanced tree, Worst case: O(n) for skewed tree
    public List<Integer> inorderTraversal(Node root) {

        List<Integer> ans = new ArrayList<>();
        inorder(root, ans);

        return ans;
    }

    private void inorder(Node root, List<Integer> ans) {
        // left > root > right
        if (root == null)
            return;
        inorder(root.left, ans);
        ans.add(root.val);
        inorder(root.right, ans);
    }

    // postorder Traversal recursion sol
    // TC: O(n) - where n is the number of nodes, each node is visited exactly once
    // SC: O(h) - where h is the height of the tree (recursion call stack)
    // Best case: O(log n) for balanced tree, Worst case: O(n) for skewed tree
    public List<Integer> postorderTraversal(Node root) {
        List<Integer> ans = new ArrayList<>();
        postorder(root, ans);
        return ans;
    }

    private void postorder(Node root, List<Integer> ans) {
        // left > right > root
        if (root == null)
            return;
        postorder(root.left, ans);
        postorder(root.right, ans);
        ans.add(root.val);
    }

    public static void main(String[] args) {

    }
}