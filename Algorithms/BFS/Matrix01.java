package Algorithms.BFS;

import java.util.LinkedList;
import java.util.Queue;

// the prob is we need to find nearst 0 for 1 but we reversed 
// we are finding nearest one so we took 0 as staring point.
// tc is o(n * m) & sc is o(n * m)
public class Matrix01 {

    public static int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] ans = new int[n][m];

        Queue<int[]> q = new LinkedList<>();
        int distance = 0;

        // here is 0 is starting point
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // sdding 0 to q
                if (mat[i][j] == 0) {
                    q.add(new int[] { i, j });
                    // storing 0 in ans cause it doesnt change
                    ans[i][j] = 0;
                } else {
                    // remaining 1 in mat are marked -1 in ans means not visited
                    ans[i][j] = -1;
                }
            }
        }

        // four directions
        int[][] dirc = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int row = curr[0];
                int col = curr[1];

                for (int[] dic : dirc) {
                    int newRow = row + dic[0];
                    int newCol = col + dic[1];

                    // boundary & checking ans is not visited
                    if (newRow >= 0 && newRow < n &&
                            newCol >= 0 && newCol < m &&
                            ans[newRow][newCol] == -1) {

                        // here ans dis is curr d + 1 cause
                        // we moved to new row col that is 1 dis
                        ans[newRow][newCol] = distance + 1;
                        q.add(new int[] { newRow, newCol });
                    }
                }
            }
            distance++;
        }

        return ans;
    }
}
