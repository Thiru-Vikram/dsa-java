package Neetcode150.Graphs;

// q is return the max area of island in the grid 1 - land, 0 - water
// we use dfs here cause we need to traverse in deep
// a is we need to traverse in the grid using the dfs and then if cell is one then call the another function where it says the total area of that island means curr cells connected 1s left, right, up, down

// Time Complexity: O(m × n)
// Every cell is visited at most once because once a land cell is explored, it is marked as visited (0).
// Space Complexity: O(m × n) in the worst case
// The recursion stack can grow up to m × n when the entire grid forms one large island.

// 695. Max Area of Island
public class Leetcode695 {

    // func to find the area of island
    private static int areaOfIsland(int i, int j, int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        if (i >= 0 && i < n && j >= 0 && j < m && grid[i][j] == 1) {
            grid[i][j] = 0; // mark as visited
            // 1 ic curr cell + four dirs (1,0) (-1, 0) (0 ,1) (0, -1)
            return 1 + areaOfIsland(i + 1, j, grid) + areaOfIsland(i - 1, j, grid) + areaOfIsland(i, j + 1, grid) +
                    areaOfIsland(i, j - 1, grid);
        }
        return 0;
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // if cell is 1
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, areaOfIsland(i, j, grid));
                }
            }
        }
        return maxArea;

    }

    public static void main(String[] args) {

        int[][] grid = {
                { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }

        };

        System.out.print(maxAreaOfIsland(grid));

    }

}
