package Algorithms.UnionFind;

// Number of Connected Components in an Undirected Graph (Leetcode Premium)
// TIME COMPLEXITY:  O(n + E * alpha(n))
//   - O(n) : UnionFind initialization (n nodes)
//   - O(E * alpha(n)): E edges, each union call is nearly O(1) due to
// path compression + union by size (alpha = inverse Ackermann ~ constant)
//   - O(n) : countComponents() scans all n nodes

// SPACE COMPLEXITY: O(n)
// parent and size arraylists each of size n
public class NumberOfConnectedComponents {

    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }

        return uf.countComponents();
    }

}
