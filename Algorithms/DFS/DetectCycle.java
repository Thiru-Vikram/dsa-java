package Algorithms.DFS;

import java.util.*;

// detect cycle in the undirected graph
public class DetectCycle {

    // Time Complexity: O(V + E)
    // Each vertex is visited once, and each edge is traversed once across all DFS
    // calls.

    // Space Complexity: O(V)
    // vis[] array: O(V)
    // Recursion call stack (DFS depth): O(V) in the worst case (a linear chain
    // graph)
    // Adjacency list is input, not counted as auxiliary space.

    private static boolean dfs(int node, int parent, boolean[] vis, ArrayList<ArrayList<Integer>> adj) {

        vis[node] = true;

        for (int neigh : adj.get(node)) {
            // neigh not vis go deeper
            if (!vis[neigh]) {
                // check whether is vis if vis true else false
                // neigh is child curr node is parent
                if (dfs(neigh, node, vis, adj)) {
                    return true;
                }
                // neigh is vis check is he parent yes -> false casue you are coming from him
                // else true cause u alredy meet him;
            } else if (neigh != parent) {
                return true;
            }
        }

        return false;

    }

    public static boolean detectCycle(int v, ArrayList<ArrayList<Integer>> adj) {

        boolean[] vis = new boolean[v];

        for (int i = 0; i < v; i++) {
            // check for all components cause at middle it may disconnected
            if (!vis[i]) {
                // at fist we dont have parent -1
                if (dfs(i, -1, vis, adj)) {
                    return true;
                }
            }
        }
        return false;
    }

}
