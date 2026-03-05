package Algorithms.DFS;

// dfs on grid prob
public class NumberOfIslands {

    // tc and sc is n * m
    private void dfs(int row, int col, char[][] grid, boolean[][] vis) {
        int n = grid.length;
        int m = grid[0].length;
        vis[row][col] = true;

        int[][] dirc = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int[] dic : dirc) {
            int newRow = row + dic[0];
            int newCol = col + dic[1];

            if (newRow >= 0 && newRow < n &&
                    newCol >= 0 && newCol < m &&
                    grid[newRow][newCol] == '1' &&
                    !vis[newRow][newCol]) {

                dfs(newRow, newCol, grid, vis); // ✅ recursive call
            }
        }
    }

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // if land and not visited → new island!
                if (grid[i][j] == '1' && !vis[i][j]) {
                    count++; // found new island
                    dfs(i, j, grid, vis); // mark all connected
                }
            }
        }

        return count;
    }

}
