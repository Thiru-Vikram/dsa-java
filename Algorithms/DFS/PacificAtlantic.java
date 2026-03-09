package Algorithms.DFS;

import java.util.*;

/*
 * PROBLEM: Pacific Atlantic Water Flow (Leetcode 417)
 *
 * Given an m x n matrix of heights, water can flow from a cell to an adjacent
 * cell (up/down/left/right) only if the neighbor's height is <= current cell's height.
 *
 * - Pacific Ocean  touches the TOP and LEFT borders.
 * - Atlantic Ocean touches the BOTTOM and RIGHT borders.
 *
 * Return all cells from which water can flow to BOTH oceans.
 *
 * APPROACH: Reverse DFS (flow water UPHILL from ocean borders inward)
 * - Instead of simulating water flowing down from every cell (expensive),
 *   we do DFS starting FROM the ocean borders going UPHILL (>=).
 * - Any cell reachable from pacific border  --> mark pacific[i][j] = true
 * - Any cell reachable from atlantic border --> mark atlantic[i][j] = true
 * - Answer = cells where both pacific[i][j] && atlantic[i][j] are true.
 *
 * TIME:  O(m * n)  — each cell visited at most twice (once per ocean)
 * SPACE: O(m * n)  — two boolean visited grids + recursion stack
 */
public class PacificAtlantic {

    /*
     * DFS helper — runs UPHILL from a border cell into the grid.
     * 
     * @param i, j — current cell coordinates
     * 
     * @param prev — height of the previous cell (we only move to cells >= prev)
     * 
     * @param matrix — the heights grid
     * 
     * @param ocean — visited array for the current ocean (pacific or atlantic)
     */
    private void dfs(int i, int j, int prev, int[][] matrix, boolean[][] ocean) {

        // 4 directional moves: up, down, right, left
        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

        // out-of-bounds check — stop exploring this path
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length)
            return;

        // skip if current cell is LOWER than previous (water can't flow uphill in
        // reverse)
        // OR if already visited for this ocean (avoid re-processing)
        if (matrix[i][j] < prev || ocean[i][j]) {
            return;
        }

        // mark current cell as reachable by this ocean
        ocean[i][j] = true;

        // explore all 4 neighbors, passing current height as the new "prev"
        for (int[] d : dir) {
            dfs(i + d[0], j + d[1], matrix[i][j], matrix, ocean);
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // edge case: empty grid
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        int row = heights.length;
        int col = heights[0].length;

        List<List<Integer>> res = new ArrayList<>();

        // visited grids — true means this cell can reach the respective ocean
        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];

        // DFS from LEFT column (pacific) and RIGHT column (atlantic)
        for (int i = 0; i < row; i++) {
            dfs(i, 0, Integer.MIN_VALUE, heights, pacific); // left border -> pacific
            dfs(i, col - 1, Integer.MIN_VALUE, heights, atlantic); // right border -> atlantic
        }

        // DFS from TOP row (pacific) and BOTTOM row (atlantic)
        for (int i = 0; i < col; i++) {
            dfs(0, i, Integer.MIN_VALUE, heights, pacific); // top border -> pacific
            dfs(row - 1, i, Integer.MIN_VALUE, heights, atlantic); // bottom border -> atlantic
        }

        // collect cells reachable by BOTH oceans
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

}
