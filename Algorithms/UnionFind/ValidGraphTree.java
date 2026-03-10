package Algorithms.UnionFind;

// PROBLEM: Given n nodes (0 to n-1) and a list of edges, determine if they form a valid tree.
//
// A valid tree must satisfy TWO conditions:
//   1. No cycles
//   2. All nodes are connected (only one component)
//
// WHY UNION-FIND works here:
//   Union-Find is perfect for problems that ask about connectivity and cycle detection in a graph.
//   - It tracks which nodes belong to the same component.
//   - When we try to union two nodes that are already in the same component,
//     it means adding that edge would create a cycle -> not a valid tree.
//
// KEY INSIGHT:
//   A graph with n nodes is a valid tree if and only if:
//     1. It has EXACTLY n-1 edges
//        - fewer edges -> graph is disconnected (multiple components)
//        - more edges  -> graph must have a cycle
//     2. None of those n-1 edges create a cycle
//
//   So the check is simple:
//     - First guard: if edges.length != n-1, immediately return false
//     - Then process each edge with union-find: if any edge connects two already-connected
//       nodes, a cycle exists -> return false
//     - If all edges are processed without a cycle, the graph is a valid tree
//
// WHY NOT DFS/BFS?
//   DFS/BFS also works, but Union-Find is more elegant here because:
//   - Cycle detection is O(alpha(n)) ~ O(1) per edge with path compression + union by size
//   - No need to build an adjacency list, works directly on the edges array

public class ValidGraphTree {

    public boolean validTree(int n, int[][] edges) {
        // condition 1: a tree with n nodes must have exactly n-1 edges
        // if not, it's either disconnected (too few) or has a cycle (too many)
        if (edges.length != n - 1)
            return false;
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            // condition 2: try to union the two endpoints of this edge
            // if union returns false, they are already connected -> this edge creates a
            // cycle
            if (!uf.union(edge[0], edge[1]))
                return false;
        }
        // all n-1 edges processed with no cycle -> valid tree
        return true;
    }

    public static void main(String[] args) {

    }
}
