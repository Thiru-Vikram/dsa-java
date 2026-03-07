import java.util.*;

public class Dijkstra {

    public static int[] dijkstra(int src, int n, List<List<int[]>> adj) {

        // dist[i] = shortest distance from src to node i; init to infinity
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // min-heap ordered by distance; stores {dist, node}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] { 0, src });

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int dis = curr[0]; // distance stored in this PQ entry
            int node = curr[1]; // current node

            for (int[] edge : adj.get(node)) {
                int v = edge[0]; // neighbor node
                int wt = edge[1]; // edge weight

                // if curr dis > prev dis leave cause looking for shorter one
                if (dis > dist[node])
                    continue;

                // relax edge: if going through 'node' gives a shorter path to 'v', update
                if (dist[node] + wt < dist[v]) {
                    dist[v] = dist[node] + wt;
                    pq.offer(new int[] { dist[v], v });
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {

    }

}
