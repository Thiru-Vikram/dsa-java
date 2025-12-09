import java.util.*;

// Revision Notes – LeetCode 947
// The idea is to treat each stone as a node. Two stones belong to the same component if they share either the same row or the same column. Use Disjoint Set (Union–Find) to group such stones.
// For every stone:
// If another stone has the same row or column, union them.
// After processing all stones, count how many connected components exist.
// A component is identified when parent[i] == i.
// Key insight:
// In each connected component, we can remove all stones except one.
// Therefore:
// Answer = Total stones − Number of connected components
// Example:
// If there are 5 stones and they all connect into 1 component → 5 − 1 = 4 stones can be removed.
//tc is o(n^2) - nestedloop and sc is o(n) - two arrays.

class DisjointSet {
    ArrayList<Integer> rank = new ArrayList<>();
    ArrayList<Integer> parent = new ArrayList<>();

    DisjointSet(int n) {
        for (int i = 0; i < n; i++) {
            rank.add(1);
            parent.add(i);
        }
    }

    public int findParent(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        int ult_parent = findParent(parent.get(node));
        parent.set(node, ult_parent);
        return parent.get(node);
    }

    public void unionByRank(int u, int v) {
        int ult_u = findParent(u);
        int ult_v = findParent(v);
        if (ult_u == ult_v) {
            return;
        }
        if (rank.get(ult_u) < rank.get(ult_v)) {
            parent.set(ult_u, ult_v);
        } else if (rank.get(ult_v) < rank.get(ult_u)) {
            parent.set(ult_v, ult_u);
        } else {
            parent.set(ult_v, ult_u);
            int rankU = rank.get(ult_u);
            rank.set(ult_u, rankU + 1);
        }
    }
}

class Leetcode947 {
    public int removeStones(int[][] stones) {

        int n = stones.length;
        DisjointSet ds = new DisjointSet(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    ds.unionByRank(i, j);
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (ds.parent.get(i) == i) {
                cnt++;
            }
        }
        return n - cnt;
    }
}