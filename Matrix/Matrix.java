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

}
