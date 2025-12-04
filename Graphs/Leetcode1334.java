package Graphs;

public class Leetcode1334 {

    // Revision Note – Find the City With Minimum Reachable Neighbors
    // Input gives edges as (src, dest, weight) and a distanceThreshold.
    // Goal: For each city, count how many other cities are reachable with total
    // path cost ≤ threshold.
    // Use Floyd-Warshall algorithm to compute all-pairs shortest paths (APSP).
    // Build an n × n matrix where diagonal = 0, others = ∞.
    // Update direct edges.
    // Run Floyd-Warshall: matrix[i][j] = min(matrix[i][j], matrix[i][k] +
    // matrix[k][j]).
    // After APSP, for each city i, count how many cities j satisfy:
    // i != j and matrix[i][j] <= threshold.
    // Track the city with the minimum count.
    // If two cities have the same count, return the city with the larger index.
    // Time Complexity: O(n³)
    // Space Complexity: O(n²)

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] matrix = new int[n][n];
        int m = edges.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.MAX_VALUE;
                if (i == j)
                    matrix[i][j] = 0;
            }
        }

        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            matrix[u][v] = wt;
            matrix[v][u] = wt;
        }

        // Floyd-Warshall algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][k] == Integer.MAX_VALUE ||
                            matrix[k][j] == Integer.MAX_VALUE)
                        continue;
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        int minCount = n;
        int cityNo = -1;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && matrix[i][j] <= distanceThreshold) { // Fixed: exclude self
                    cnt++;
                }
            }

            if (cnt <= minCount) {
                minCount = cnt;
                cityNo = i;
            }
        }

        return cityNo;
    }

    public static void main(String[] args) {

    }
}