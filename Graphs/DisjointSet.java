package Graphs;

import java.util.*;

public class DisjointSet {

    // Disjoint Set (Union–Find) – Revision Notes
    // A graph may have multiple components.
    // Example:
    // Component 1 → 1–2–3–4
    // Component 2 → 5–6–7–8
    // If we ask “Are 1 and 5 in the same component?”, doing DFS/BFS from 1 will
    // never reach 5, but it takes O(N + E) time.
    // Disjoint Set allows us to check this in O(1) (amortized) by simply comparing
    // their parents → find(1) != find(5).
    // It is mainly used for dynamic graphs where edges are added continuously (like
    // Kruskal’s MST).
    // Time complexity: O(4α) ≈ constant, because α (inverse Ackermann) grows
    // extremely slowly.
    ArrayList<Integer> rank = new ArrayList<>();
    ArrayList<Integer> parent = new ArrayList<>();
    // use size or rank any one.
    // both are same time which is O(4 alpha) near to constant.
    ArrayList<Integer> size = new ArrayList<>();

    // At first rank 1 for all, parent is node itself.
    DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            rank.add(1);
            parent.add(i);
            size.add(1);
        }
    }

    public int findYourParent(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        int ultimateParent = findYourParent(parent.get(node));
        parent.set(node, ultimateParent);
        return parent.get(node);
    }

    // here u will update the rank means height.
    public void unionByRank(int u, int v) {
        int ulp_u = findYourParent(u);
        int ulp_v = findYourParent(v);
        if (ulp_u == ulp_v)
            return;
        if (rank.get(ulp_u) < rank.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
        } else if (rank.get(ulp_v) < rank.get(ulp_u)) {
            parent.set(ulp_v, ulp_u);
        } else {
            parent.set(ulp_v, ulp_u);
            int rankU = rank.get(ulp_u);
            rank.set(ulp_u, rankU + 1);
        }
    }

    // by size means it contains total nodes in that component.
    public void unionBySize(int u, int v) {
        int ulp_u = findYourParent(u);
        int ulp_v = findYourParent(v);
        if (ulp_u == ulp_v)
            return;
        if (size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
        } else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_v) + size.get(ulp_u));
        }
    }

    public static void main(String[] args) {

        DisjointSet ds = new DisjointSet(7);
        ds.unionByRank(1, 2);
        ds.unionByRank(2, 3);
        ds.unionByRank(4, 5);
        ds.unionByRank(6, 7);
        ds.unionByRank(5, 6);

        // if 3 and 7 same or not
        if (ds.findYourParent(3) == ds.findYourParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }

        ds.unionByRank(3, 7);
        if (ds.findYourParent(3) == ds.findYourParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }
    }
}