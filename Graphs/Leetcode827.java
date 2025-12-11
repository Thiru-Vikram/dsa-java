import java.util.*;

// Revision Note – LC 827: Making A Large Island
// We need the maximum island area after changing at most one 0 → 1.
// Steps:
// Treat every cell as a node using index: node = row * n + col.
// Use Disjoint Set (Union by Size) to connect all 1-cells with their 4-direction neighbors.
// This gives the size of each island/component directly.
// After building components, scan the grid again:
// For each 0-cell, check its unique neighboring components (up to 4 directions).
// Sum their sizes + 1 (for flipping this 0).
// Track the maximum area across all flips.
// If the grid already has no 0s, the answer is simply total grid area.
// Time Complexity
// O(n² α(n²)) for DSU unions + O(n²) scanning
// Overall: O(n²)
// Space Complexity
// O(n²) DSU parent/size arrays + visited component sets.

class DisjointSet {
    ArrayList<Integer> size = new ArrayList<>();
    ArrayList<Integer> parent = new ArrayList<>();

    DisjointSet(int n) {
        for (int i = 0; i < n; i++) {
            size.add(1);
            parent.add(i);
        }
    }

    public int findParent(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        int ult_p = findParent(parent.get(node));
        parent.set(node, ult_p);
        return parent.get(node);
    }

    public void unionBySize(int u, int v) {
        int ult_u = findParent(u);
        int ult_v = findParent(v);
        if (ult_u == ult_v) {
            return;
        }
        if (size.get(ult_u) < size.get(ult_v)) {
            parent.set(ult_u, ult_v);
            size.set(ult_v, size.get(ult_u) + size.get(ult_v));
        } else {
            parent.set(ult_v, ult_u);
            size.set(ult_u, size.get(ult_u) + size.get(ult_v));
        }
    }
}

class Leetcode827 {
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n * n);

        // connecting the 1s.
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0)
                    continue;
                int[] drow = { -1, 0, 1, 0 };
                int[] dcol = { 0, 1, 0, -1 };
                for (int i = 0; i < 4; i++) {
                    int newr = r + drow[i];
                    int newc = c + dcol[i];
                    if (newr >= 0 && newr < n && newc >= 0 && newc < n
                            && grid[newr][newc] == 1) {
                        int nodeNum = r * n + c;
                        int adjNodeNum = newr * n + newc;
                        ds.unionBySize(nodeNum, adjNodeNum);
                    }
                }
            }
        }

        // counting the 1s with replacing zero.
        int max = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1)
                    continue;
                int[] drow = { -1, 0, 1, 0 };
                int[] dcol = { 0, 1, 0, -1 };
                HashSet<Integer> components = new HashSet<>();
                for (int i = 0; i < 4; i++) {
                    int newr = r + drow[i];
                    int newc = c + dcol[i];
                    if (newr >= 0 && newr < n && newc >= 0 && newc < n) {
                        if (grid[newr][newc] == 1) {
                            components.add(ds.findParent(newr * n + newc));
                        }
                    }
                }
                int totalSize = 0;
                for (int parent : components) {
                    totalSize += ds.size.get(parent);
                }
                max = Math.max(max, totalSize + 1);
            }
        }
        for (int cellNo = 0; cellNo < n * n; cellNo++) {
            max = Math.max(max, ds.size.get(ds.findParent(cellNo)));
        }
        return max;
    }
}