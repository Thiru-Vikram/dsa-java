package Neetcode150.Graphs;

// que is about find of islands in given matrix, 1 is land, 0 is water
// here we use dfs cause we need to traverse deep
// ans is we need to visit each cell and if it is land and not visted means we found 1, and then call the dfs to mark all the neighbour cells as visited and then return cnt

// 200. Number of Islands
public class Leetcode200 {

    private static void dfs(int i, int j, char[][] grid, boolean[][] vis) {
        int n = grid.length;
        int m = grid[0].length;
        vis[i][j] = true; // make cell as marked

        // now go through all its neightbours and mark them as marked
        int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int[] dir : dirs) {
            int newRow = i + dir[0];
            int newCol = j + dir[1];

            if (newRow >= 0 && newRow < n &&
                    newCol >= 0 && newCol < m &&
                    // land + not visited found another call the func again
                    grid[newRow][newCol] == '1' && !vis[newRow][newCol]) {
                dfs(newRow, newCol, grid, vis);
            }
        }
    }

    public static int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // land and not visted
                if (grid[i][j] == '1' && !vis[i][j]) {
                    cnt++; // found 1
                    dfs(i, j, grid, vis); // call to mark vis
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {

        char[][] grid = {
                { '1', '1', '0', '0', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '1' } };

        System.out.print(numIslands(grid));

    }
}
