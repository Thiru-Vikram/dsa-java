import java.util.*;

public class Leetcode778 {
    // Revision Note – LC 778: Swim in Rising Water
    // We need the minimum time required to move from (0,0) to (n-1,n-1) in an n × n
    // grid, where the time equals the maximum height encountered on the path.
    // This is a classic Dijkstra’s Algorithm problem.
    // Approach:
    // Use a min-heap (priority queue) storing (height, row, col).
    // Start from (0,0) with its height.
    // Maintain a visited[n][n] array to avoid reprocessing cells.
    // Always pick the cell with the minimum height from the PQ (Dijkstra).
    // Track the running max height seen so far; this represents the time needed to
    // reach that cell.
    // Expand in 4 directions; push valid and unvisited neighbors into PQ.
    // Once we reach (n-1,n-1), the current maxHeight is the answer.
    // Time Complexity
    // O(n² log n)
    // Each of the n² cells is pushed into the PQ once.
    // Space Complexity
    // O(n²)
    // Visited array + PQ storing up to n² elements.
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int max = 0;
        boolean[][] visted = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // {height, row, col};
        pq.offer(new int[] { grid[0][0], 0, 0 });
        while (!pq.isEmpty()) {
            int[] curCell = pq.poll();
            int height = curCell[0];
            int r = curCell[1];
            int c = curCell[2];
            max = Math.max(max, height);
            if (r == n - 1 && c == n - 1)
                return max;
            if (visted[r][c])
                continue;
            visted[r][c] = true;

            int[] drow = { -1, 0, 1, 0 };
            int[] dcol = { 0, 1, 0, -1 };
            for (int i = 0; i < 4; i++) {
                int newr = r + drow[i];
                int newc = c + dcol[i];
                if (newr >= 0 && newr < n && newc >= 0 && newc < n &&
                        !visted[newr][newc]) {
                    pq.offer(new int[] { grid[newr][newc], newr, newc });
                }
            }
        }
        return -1;// never executes;
    }
}