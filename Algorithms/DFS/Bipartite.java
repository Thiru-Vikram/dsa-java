package Algorithms.DFS;

import java.util.Scanner;

// 785. Is Graph Bipartite?
public class Bipartite {

    // Summary:
    // Adjacency List input (LeetCode)
    // TC - O(V + E), SC - O(V + E)
    // Adjacency Matrix input (Exam)
    // tc - O(V²) sc - O(V²)

    // bipartite graph means given a graph where it can be colored exactly with 2
    // colors
    // such that no 2 adj nodes have same color
    // if graph has cycle with odd len -> then it is not bipartite,
    // if graph has cycle with even len -> then it is bipartite. also linear grpah
    // (no cycle) -> true.

    // in leetcode input is adj list means it says graph =
    // [[1,2,3],[0,2],[0,1,3],[0,2]]
    // 0th node connected to 1,2,3 nodes like wise 1th node --> 0,2

    // sometimes u get adj matrix in 2d arr so that time u need to build adj list
    // and then use this dfs code.

    private static boolean dfs(int node, int col, int[] color, int[][] graph) {
        color[node] = col;

        // checking for the adjacent nodes of present node
        for (int it : graph[node]) {
            // if its not colored go and color it
            if (color[it] == -1) {
                // checking adj node and painting with opposite color
                if (dfs(it, 1 - col, color, graph) == false) {
                    return false;
                }
                // colored, check same color if it is false
            } else if (color[it] == col) {
                // if any adjacent node has same color, not bipartite
                return false;
            }
        }
        return true;
    }

    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];

        // initialize all nodes as uncolored
        for (int i = 0; i < n; i++) {
            color[i] = -1;
        }

        // for multiple components
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                if (dfs(i, 0, color, graph) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // adj matrix
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        // adj matrix to adj list
        int[][] graph = new int[n][];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1)
                    count++;
            }

            graph[i] = new int[count];
            int idx = 0;
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1) {
                    graph[i][idx++] = j;
                }
            }
        }

        System.out.print(isBipartite(graph));

    }

}
