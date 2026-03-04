package Algorithms;

import java.util.*;

public class Bfs {

    // ============ GRAPH BFS ============
    void bfs_graph(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int node = q.poll();

            // DO YOUR WORK HERE with node

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
    }

    // ============ GRID BFS ============
    void bfs_grid(int startRow, int startCol) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        q.add(new int[] { startRow, startCol });
        visited[startRow][startCol] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int row = curr[0];
            int col = curr[1];

            // DO YOUR WORK HERE with row, col

            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < n && // boundary
                        newCol >= 0 && newCol < m &&
                        !visited[newRow][newCol]) { // not visited

                    visited[newRow][newCol] = true;
                    q.add(new int[] { newRow, newCol });
                }
            }
        }
    }

    // ============ BFS WITH LEVELS (time/distance) ============
    void bfs_levels(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        int level = 0;

        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int size = q.size(); // how many nodes at THIS level

            for (int i = 0; i < size; i++) {
                int node = q.poll();

                // DO YOUR WORK HERE with node, level

                for (int neighbor : adj.get(node)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        q.add(neighbor);
                    }
                }
            }
            level++; // next level
        }
    }

    // ## When to use which:
    // GRAPH BFS → nodes connected by edges
    // eg: friend network, road map

    // GRID BFS → 2D matrix problems
    // eg: shortest path in maze,
    // flood fill, Q5 invasion

    // LEVEL BFS → when TIME or DISTANCE matters
    // eg: Q4 minimum moves,
    // Q5 minimum time to invade

    // ## What CHANGES problem to problem:

    // 1. Starting point → which node/cell to start from
    // 2. Visited condition → what counts as "can visit"
    // 3. Work inside loop → what to do at each node/cell
    // 4. Termination → when to stop (found target?)

    // CORE STRUCTURE never changes! ✅

}
