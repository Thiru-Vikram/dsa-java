import java.util.*;

public class NetworkDelayTime {

    // Run Dijkstra from k
    // ↓
    // dist[] has shortest time to reach every node
    // ↓
    // max(dist[]) = time when LAST node receives signal
    // ↓
    // If any dist[i] = infinity → return -1

    // TC: O(E log E) — each edge can push at most one entry to PQ; each poll/offer is O(log E)
    // SC: O(n + E) — adj list O(n+E), dist[] O(n), PQ at most O(E) entries
    public static int networkDelayTime(int[][] times, int n, int k) {

        // Build adjacency list (0-indexed nodes); stores {neighbor, weight}
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        // convert 1-indexed -> 0-indexed
        for (int[] t : times) {
            int u = t[0] - 1; // u - src node
            int v = t[1] - 1; // v - dest node
            int w = t[2]; // w = weight or time etc
            adj.get(u).add(new int[] { v, w });
        }

        // dist[i] = shortest distance from source k to node i; init to infinity
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k - 1] = 0; // k is 1-indexed, convert to 0-indexed

        // min-heap ordered by distance; stores {dist, node}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] { 0, k - 1 });

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int dis = curr[0];
            int node = curr[1];

            // skip stale PQ entry: a shorter path to 'node' was already found
            if (dis > dist[node])
                continue;

            for (int[] edge : adj.get(node)) {
                int v = edge[0];
                int wt = edge[1];

                // relax edge: if going through 'node' gives a shorter path to 'v', update
                if (dist[node] + wt < dist[v]) {
                    dist[v] = dist[node] + wt;
                    pq.offer(new int[] { dist[v], v }); // push updated distance back to PQ
                }
            }
        }

        // answer is the max shortest distance; -1 if any node is unreachable
        int max = 0;
        for (int val : dist) {
            if (val == Integer.MAX_VALUE)
                return -1;
            max = Math.max(max, val);
        }

        return max;
    }

    public static void main(String[] args) {

    }

}
