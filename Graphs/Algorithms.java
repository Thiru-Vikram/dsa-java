package Graphs;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Algorithms {
    // Bellman–Ford is used to find the shortest path from a single source to all
    // nodes, and it works even with negative edge weights.
    // For every edge ((u, v)), we perform relaxation:
    // if(dist[u] + wt < dist[v] -> update dist[v].
    // This relaxation is repeated V − 1 times to ensure all shortest paths are
    // computed.
    // Then we run **one extra iteration:
    // If any distance still updates, a negative weight cycle exists → return -1.
    // Otherwise, the `dist[]` array contains the final shortest paths.
    // Time Complexity: O(V X E)
    // Space Complexity: O(V)
    public static int[] bellmanFord(int V, ArrayList<ArrayList<Integer>> edges, int src) {
        int[] dis = new int[V];
        for (int i = 0; i < dis.length; i++) {
            dis[i] = (int) 1e9;
        }
        dis[src] = 0;

        // V - 1 interations to update shortest path in dis arr.
        for (int i = 0; i < V - 1; i++) {
            for (ArrayList<Integer> edge : edges) {
                int u = edge.get(0);
                int v = edge.get(1);
                int wt = edge.get(2);
                if (dis[u] != (int) 1e9 && dis[u] + wt < dis[v]) {
                    dis[v] = dis[u] + wt;
                }
            }
        }

        // vth iteration to check negative cycle.
        for (ArrayList<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int wt = edge.get(2);
            if (dis[u] != (int) 1e9 && dis[u] + wt < dis[v]) {
                return new int[] { -1 };
            }
        }

        return dis;
    }

    // * Floyd–Warshall is a multi-source shortest path algorithm that finds the
    // shortest distances between every pair of nodes.
    // * It also detects negative weight cycles.
    // * The algorithm tries to improve the distance between every pair ((i, j)) by
    // checking if going through a via (intermediate) node k gives a shorter path.
    // * Update rule: cost[i][j] = min(cost[i][j], cost[i][k] + cost[k][j]).
    // * We repeat this for all nodes (k) as the via node.
    // * If at the end cost[i][i] < 0 for any node (i), the graph contains a
    // negative cycle.
    // Time Complexity: O(V^3)
    // Space Complexity: O(V^2)
    public static void floydsWarshall(int[][] matrix) {
        int n = matrix.length;
        // first checking values and if -1 change to infinite.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = (int) 1e9;
                }
                // diagonals are always 0.
                if (i == j)
                    matrix[i][j] = 0;
            }
        }

        // perform algorithm.
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        // to find negative cycle.
        for (int i = 0; i < n; i++) {
            if (matrix[i][i] < 0) {
                System.out.println("Negative Cycle Detected.");
                return;
            }
        }

        // make sure while returning infite to -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == (int) 1e9) {
                    matrix[i][j] = -1;
                }
            }
        }
    }

    // Prim’s Algorithm (Short Notes):
    // Start from node 0 and pick the smallest-weight edge using a min-heap
    // (priority queue).
    // Add that edge to the MST if the node is not visited.
    // Mark the node visited and push all its unvisited neighbours (with their edge
    // weights) into the PQ.
    // Keep picking the smallest edge that connects a new node until all nodes are
    // covered.
    // The total sum of all chosen edges is the MST weight.
    // TC: O(E log V)
    // SC: O(V + E)
    public static int minSpanningTree(int v, ArrayList<ArrayList<ArrayList<Integer>>> adj) {

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x, y) -> x.wt - y.wt);
        int[] vis = new int[v];
        // starting node.
        pq.add(new Pair(0, 0));
        int sum = 0;
        while (pq.size() > 0) {

            int wt = pq.peek().wt;
            int node = pq.peek().node;
            pq.remove();

            if (vis[node] == 1)
                continue;
            vis[node] = 1;
            sum += wt;

            for (int i = 0; i < adj.get(node).size(); i++) {
                int adjWt = adj.get(node).get(i).get(0);
                int adjNode = adj.get(node).get(i).get(1);
                if (vis[adjNode] == 0) {
                    pq.add(new Pair(adjWt, adjNode));
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {

    }
}

class Pair {
    int wt;
    int node;

    Pair(int wt, int node) {
        this.wt = wt;
        this.node = node;
    }
}