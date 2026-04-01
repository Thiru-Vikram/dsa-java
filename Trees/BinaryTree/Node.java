package Trees.BinaryTree;

import java.util.*;

// Recursion or iterative sol which is optimal?
// For balanced or small trees: Recursive is preferred — cleaner, easier to read, 
// and has the same asymptotic efficiency.
// For large or skewed trees (deep trees): Iterative is optimal — avoids stack overflow 
// and gives more control over memory usage.

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

        // offer(root)ing root to st
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

    // iterative sol same tc and sc
    public List<Integer> inorderTraversal2(Node root) {

        List<Integer> ans = new ArrayList<>();
        Stack<Node> st = new Stack<>();
        Node node = root; // get node

        // we there is node at left go left left left most
        while (true) {
            if (node != null) {
                st.push(node); // offer(root) to stack
                node = node.left; // move left
            } else { // once reached left most node with no left again
                if (st.isEmpty()) {
                    break;
                }
                node = st.pop(); // pop that
                ans.add(node.val); // offer(root) val to ans
                node = node.right; // move right
                // by this u offer(root) left > root > right
            }
        }

        return ans;
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

    // same tc and sc same
    public List<Integer> postorderTraversal2(Node root) {

        List<Integer> ans = new ArrayList<>();
        Stack<Node> st = new Stack<>();
        Node curr = null;
        Node lastVisited = null;

        while (curr != null || !st.isEmpty()) {
            if (curr != null) {
                st.push(curr);
                curr = curr.left;
            } else {
                Node peekNode = st.peek();
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    curr = peekNode.right;
                } else {
                    ans.add(peekNode.val);
                    lastVisited = st.pop();
                }
            }
        }
        return ans;
    }

    // tc and sc is o(n)
    public List<List<Integer>> levelorderTraversal(Node root) {

        List<List<Integer>> ans = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();

        if (root == null)
            return ans;
        q.offer(root);

        while (!q.isEmpty()) {

            int len = q.size();
            List<Integer> subList = new ArrayList<>();

            for (int i = 0; i < len; i++) {
                if (q.peek().left != null) {
                    q.offer(q.peek().left);
                }
                if (q.peek().right != null) {
                    q.offer(q.peek().right);
                }
                subList.add(q.peek().val);
            }
            ans.add(subList);
        }

        return ans;
    }

    public static void main(String[] args) {

    }
}