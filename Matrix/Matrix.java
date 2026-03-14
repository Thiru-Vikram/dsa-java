package Matrix;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

    // Set matrix rows/columns to 0 if any cell is 0.
    // Idea: use first row and first column as in-place markers.
    // firstRow/firstCol store whether row 0 or col 0 originally had a zero.
    // TC: O(m * n), SC: O(1)
    public static void setZeroes(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        boolean firstRow = false, firstCol = false;

        // Pass 1: mark rows/cols that must become zero.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (matrix[i][j] == 0) {

                    // Track if first row/col themselves contain zero.
                    if (i == 0)
                        firstRow = true;
                    if (j == 0)
                        firstCol = true;

                    // Mark current row and column.
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // Pass 2: fill inner matrix using markers (skip row 0, col 0).
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Pass 3: fill first row if it originally had a zero.
        if (firstRow) {
            for (int j = 0; j < m; j++) {
                matrix[0][j] = 0;
            }
        }

        // Pass 4: fill first column if it originally had a zero.
        if (firstCol) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    // print spiral matrix
    // tc is o(m * n) sc is o(m * n) result list stores all elements
    public static List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        int n = matrix.length, m = matrix[0].length;
        // top row, bottom row, left side, right side of matrix
        int top = 0, bottom = n - 1, left = 0, right = m - 1;

        while (top <= bottom && left <= right) {
            // Traverse left to right (top row)
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
                // r is const we r at top row top, col is i
            }
            // after printing shrink the top most row so ++
            top++;

            // Traverse top to down (right column)
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
                // c is const we r at right side so right, r is i
            }
            // after printing shrink right most col so --
            right--;

            // Traverse right to left (bottom row)
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                    // r is const we r at bottom so bottom, c is i
                }
            }
            // after printing shrink bottom most row so --
            bottom--;

            // Traverse down to up (left column)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                    // col is const we r at left side so left, r is i
                }
            }
            // after printing shrink left most side so ++
            left++;
        }
        return result;
    }

    // Rotate a square matrix 90 degree clockwise in-place.
    // Mapping: (i, j) -> (j, n - 1 - i)
    // Steps: transpose, then reverse each row.
    // TC: O(n * n), SC: O(1)
    public static void rotate(int[][] matrix) {

        int n = matrix.length;
        if (n == 0 || matrix[0].length != n)
            return;

        // transpose
        for (int i = 0; i < n; i++) {
            // j starts at i, not 0 (avoids double-swapping)
            for (int j = i; j < n; j++) {

                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // reverse each row
        for (int i = 0; i < n; i++) {
            // only go halfway (avoids re-swapping)
            for (int j = 0; j < n / 2; j++) {

                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    // Word Search is worse because at each cell you explore 4 directions
    // recursively up to word length L
    // TC: O(M×N × 4^L)
    // SC: O(M×N) ← vis array + recursion stack depth = L
    private boolean dfsCheck(int row, int col, char[][] board,
            String word, boolean[][] vis, int index) { // idx of char
        // all char matched
        if (index == word.length())
            return true;

        int m = board.length;
        int n = board[0].length;

        // mark visted
        vis[row][col] = true;

        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int[] neigh : dir) {
            int newRow = row + neigh[0];
            int newCol = col + neigh[1];

            if (newRow < m && newRow >= 0 &&
                    newCol < n && newCol >= 0 &&
                    !vis[newRow][newCol] &&
                    board[newRow][newCol] == word.charAt(index)) { // check char match

                if (dfsCheck(newRow, newCol, board, word, vis, index + 1)) {
                    vis[row][col] = false; // mark unvisted for purpose of next path
                    return true;
                }
            }
        }

        // for this path u didnt find it so reuse it for next path
        vis[row][col] = false;
        return false;
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        // no fixed start point so start from every cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Only start DFS if first char matches
                if (board[i][j] == word.charAt(0)) {
                    boolean[][] vis = new boolean[m][n];
                    if (dfsCheck(i, j, board, word, vis, 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
