import java.util.*;

// Problem 1319: Number of Operations to Make Network Connected — Revision Note
// You are given n computers (nodes) and some existing connections (edges). The goal is to determine how many extra cables are required to connect all computers into a single network.
// Key Logic:
// Treat each computer as a node in a graph.
// Use Disjoint Set Union (Union–Find) to group connected computers.
// After processing all connections:
// Each connected component will have one representative (its parent points to itself).
// Count how many components are formed — this equals the number of nodes whose parent is itself.
// To connect k components into one network, you need at least k - 1 extra cables.
// If total cables < n - 1, return -1 because it is impossible to connect all computers.

class DisjointSet {

    ArrayList<Integer> rank = new ArrayList<>();
    ArrayList<Integer> parent = new ArrayList<>();

    public DisjointSet(int n) {
        for (int i = 0; i < n; i++) {
            rank.add(1);
            parent.add(i);
        }
    }

    public int findParent(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        int ultP = findParent(parent.get(node));
        parent.set(node, ultP);
        return parent.get(node);
    }

    public void unionByRank(int u, int v) {
        int ulp_u = findParent(u);
        int ulp_v = findParent(v);
        if (ulp_u == ulp_v) {
            return;
        }
        if (rank.get(ulp_u) < rank.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
        } else if (rank.get(ulp_v) < rank.get(ulp_u)) {
            parent.set(ulp_v, ulp_u);
        } else {
            parent.set(ulp_v, ulp_u);
            int rankU = rank.get(ulp_u); // ✅ Get rank
            rank.set(ulp_u, rankU + 1); // ✅ Update rank
        }
    }

    public int countComponents() {
        int components = 0;
        for (int i = 0; i < parent.size(); i++) {
            if (parent.get(i) == i) { // This node is a root
                components++;
            }
        }
        return components;
    }
}

public class Leetcode1319 {
    public int makeConnected(int n, int[][] connections) {

        int len = connections.length;
        // no enough cables
        if (len < n - 1) {
            return -1;
        }

        DisjointSet ds = new DisjointSet(n);
        for (int i = 0; i < connections.length; i++) {
            int u = connections[i][0]; // First computer in connection
            int v = connections[i][1]; // Second computer in connection
            ds.unionByRank(u, v); // Union them
        }

        int compo = ds.countComponents();

        return compo - 1;
    }
}