import java.util.*;

// Revision Note – Critical Connections (Bridges in Graph)
// The problem is to find all bridges in an undirected graph.
// An edge is a bridge if removing it disconnects the graph.
// Approach (Tarjan’s Algorithm using DFS):
// Build the adjacency list from the given edges.
// Maintain three arrays:
// vis[] → marks visited nodes
// tin[] → time of insertion (DFS entry time)
// low[] → lowest time reachable excluding parent edge
// Perform DFS:
// Set tin[node] = low[node] = timer++.
// For each neighbor:
// If unvisited, DFS and update low[node].
// If low[child] > tin[node], then the edge (node, child) is a bridge.
// Store all such edges in the result list.
// Time Complexity
// O(V + E) (DFS traversal)
// Space Complexity
// O(V + E) for adjacency list + O(V) for arrays and recursion stack.

class Solution {
    private int timer = 1;

    private void dfs(int node, int parent, int[] vis, ArrayList<ArrayList<Integer>> adj,
            int[] tin, int[] low, List<List<Integer>> bridges) {
        vis[node] = 1;
        tin[node] = low[node] = timer;
        timer++;

        for (int it : adj.get(node)) {
            if (it == parent)
                continue;

            if (vis[it] == 0) {
                dfs(it, node, vis, adj, tin, low, bridges);
                low[node] = Math.min(low[node], low[it]);

                // Bridge condition: child can't reach back to node or earlier
                if (low[it] > tin[node]) { // Fixed!
                    bridges.add(Arrays.asList(it, node));
                }
            } else {
                // Back edge found
                low[node] = Math.min(low[node], tin[it]); // Use tin[it], not low[it]
            }
        }
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (List<Integer> it : connections) {
            int u = it.get(0);
            int v = it.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] vis = new int[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        List<List<Integer>> bridges = new ArrayList<>();

        dfs(0, -1, vis, adj, tin, low, bridges);

        return bridges;
    }
}