package Algorithms.BFS;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {

    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // int[] -> row, col
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    // adding 2 -> starting point in q.
                    q.add(new int[] { i, j });
                    // cnting 1's in grid.
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        // up, down, left, right
        int[][] dirc = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        int time = 0;
        int change = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            boolean anyRotted = false;

            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int row = curr[0];
                int col = curr[1];

                for (int[] dic : dirc) {
                    // new row , col
                    int newRow = row + dic[0];
                    int newCol = col + dic[1];

                    // boundary & condition
                    if (newRow >= 0 && newRow < n &&
                            newCol >= 0 && newCol < m &&
                            grid[newRow][newCol] == 1) {

                        // change
                        grid[newRow][newCol] = 2;
                        change++;
                        anyRotted = true;
                        // adding new starting point to q
                        q.add(new int[] { newRow, newCol });
                    }
                }
            }
            if (anyRotted)
                time++;
        }

        return change == fresh ? time : -1;
    }
}
