package Matrix;

// 48. Rotate Image
public class Leetcode48 {

    // q is need to rotate the arr in 90 degree
    // a is first transpose the arr and then reverse each row.
    // 1 2 3
    // 4 5 6 --> (orig)
    // 7 8 9

    // 7 4 1
    // 2 5 8 --> (transpose)
    // 3 6 9

    // 7 4 1
    // 8 5 2 --> (reverse each rows)
    // 9 6 3

    // Time Complexity: O(n²) and Space Complexity: O(1)
    public static void rotate(int[][] arr) {
        int n = arr.length;

        // transpose the arr
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }

        // reverse the each row
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = arr[i][left];
                arr[i][left] = arr[i][right];
                arr[i][right] = temp;
            }
        }
    }

    public static void main(String[] args) {

    }

}
