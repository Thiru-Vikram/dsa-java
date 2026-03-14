package Algorithms.DFS;

import java.util.*;

// Yes! For DFS you always need a starting point. It depends on the problem type:

// 3 Common Cases
// Case 1 — Start from a specific cell (e.g., path exists from top-left to bottom-right)
// javadfs(0, 0, ...); // fixed start

// Case 2 — Start from every cell (like Word Search — word can begin anywhere)
// javafor (int i = 0; i < m; i++) {
//     for (int j = 0; j < n; j++) {
//         dfs(i, j, ...); // try every cell as start
//     }
// }

// Case 3 — Start from boundary cells (e.g., flood fill from edges)
// java// only trigger DFS from border cells
// for (int i = 0; i < m; i++) {
//     dfs(i, 0, ...);      // left border
//     dfs(i, n-1, ...);    // right border
// }
// for (int j = 0; j < n; j++) {
//     dfs(0, j, ...);      // top border
//     dfs(m-1, j, ...);    // bottom border
// }

// How to Decide?
// Problem says...Starting point"From source to destination"Fixed cell"Does X exist anywhere in grid"Every cell"Connected to boundary"Border cells only

public class Dfs {

    // ============ GRAPH DFS (Recursive) ============

    void dfs_graph(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {

        visited[node] = true;

        // DO YOUR WORK HERE with node

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs_graph(neighbor, visited, adj); // recursive call
            }
        }
    }

    // HOW TO CALL:
    // boolean[] visited = new boolean[n];
    // dfs_graph(startNode, visited, adj);

    // ============ GRID DFS (Recursive) ============

    int n = 0; // number of rows
    int m = 0; // number of cols
    int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    void dfs_grid(int row, int col, char[][] grid, boolean[][] visited) {

        visited[row][col] = true;

        // DO YOUR WORK HERE with row, col

        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow >= 0 && newRow < n && // boundary check
                    newCol >= 0 && newCol < m &&
                    !visited[newRow][newCol]) { // not visited

                dfs_grid(newRow, newCol, grid, visited);
            }
        }
    }

    // HOW TO CALL:
    // boolean[][] visited = new boolean[n][m];
    // dfs_grid(startRow, startCol, grid, visited);

    // ============ TREE DFS (Recursive) ============

    // Inorder → Left, Root, Right (gives sorted order in BST)
    // Preorder → Root, Left, Right (used to copy tree)
    // Postorder → Left, Right, Root (used to delete tree)

    // TreeNode structure
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    void inorder(TreeNode root) {
        if (root == null)
            return; // base case
        inorder(root.left); // Left
        // DO YOUR WORK HERE with root.val
        inorder(root.right); // Right
    }

    void preorder(TreeNode root) {
        if (root == null)
            return; // base case
        // DO YOUR WORK HERE with root.val
        preorder(root.left); // Left
        preorder(root.right); // Right
    }

    void postorder(TreeNode root) {
        if (root == null)
            return; // base case
        postorder(root.left); // Left
        postorder(root.right); // Right
        // DO YOUR WORK HERE with root.val
    }

    // ## When to use which:

    // GRAPH DFS → connected components
    // cycle detection
    // path exists between nodes

    // GRID DFS → flood fill
    // island counting
    // maze problems

    // TREE DFS → inorder → sorted order in BST
    // preorder → copy/print tree
    // postorder→ delete tree, height

    // ## What CHANGES problem to problem:

    // 1. Starting point → which node/cell to start from
    // 2. Visited condition → what counts as "can visit"
    // 3. Work inside loop → what to do at each node/cell
    // 4. Base case → when to stop recursion

    // CORE STRUCTURE never changes! ✅

    // ## BFS vs DFS:

    // BFS DFS
    // ──────────────────────────────────────
    // Queue Stack/Recursion
    // Level by level Deep first
    // Shortest path Path exists?
    // q.poll() recursive call
    // q.add() dfs(neighbor)

    // tree
    // 1
    // / \
    // 2 3
    // / \ \
    // 4 5 6

    // BFS visits: 1→2→3→4→5→6 (level by level)
    // DFS visits: 1→2→4→5→3→6 (deep first)

    // Inorder: 4→2→5→1→3→6
    // Preorder: 1→2→4→5→3→6
    // Postorder: 4→5→2→6→3→1

    // graph
    // 1 — 2 — 3
    // | |
    // 4 — 5 — 6

    // BFS from 1: 1→2→4→3→5→6
    // DFS from 1: 1→2→3→6→5→4

    // grid
    // 1 2 3
    // 4 5 6
    // 7 8 9

    // BFS from 1: 1→2→4→3→5→7→6→8→9
    // DFS from 1: 1→2→3→6→5→4→7→8→9

}