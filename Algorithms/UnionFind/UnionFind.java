package Algorithms.UnionFind;

import java.util.*;

// this is called disjoint set there are two ways to implement it byrank, bysize
// i have used here bysize it stores the no of nodes in each components
class UnionFind {

    ArrayList<Integer> parent = new ArrayList<>();
    ArrayList<Integer> size = new ArrayList<>();

    UnionFind(int n) {
        for (int i = 0; i < n; i++) {
            // at first for every node node itself parent
            parent.add(i);
            // at first 1 is the size for all
            size.add(1);
        }
    }

    // path compression
    // finds the root (ultimate parent) of the component that 'node' belongs to
    // uses path compression: after finding the root, all nodes in the chain
    // point directly to the root, flattening the tree for faster future lookups
    //
    // example: parent chain before -> 3 -> 2 -> 1 -> 0 (root)
    // findParent(3) traverses 3->2->1->0
    // after the call: parent[3]=0, parent[2]=0, parent[1]=0 (all point to root)
    // next call findParent(3) reaches root in 1 step instead of 3
    public int findParent(int node) {
        // base case: node is its own parent, so it is the root of its component
        if (node == parent.get(node)) {
            return node;
        }
        // recursively find the root by following parent pointers
        int ultimateParent = findParent(parent.get(node));
        // path compression: point current node directly to the root
        parent.set(node, ultimateParent);
        return ultimateParent;
    }

    // union by size: merges the components of u and v
    // attaches the smaller component's root under the larger component's root
    // this keeps the tree flat (height O(log n)), making findParent faster
    // returns false if cycle detected (u and v already in same component)
    // example: component A has nodes {0,1,2} (size=3), component B has nodes {3,4}
    // (size=2)
    // union(2, 4) -> roots are 0 (size 3) and 3 (size 2)
    // since size[0] > size[3], attach 3 under 0: parent[3]=0, size[0]=5
    public boolean union(int u, int v) {
        // find roots of both nodes
        int ulp_u = findParent(u);
        int ulp_v = findParent(v);
        // same root means u and v are already connected -> cycle
        if (ulp_u == ulp_v)
            return false;
        // attach smaller tree under larger tree to keep height minimal
        if (size.get(ulp_u) < size.get(ulp_v)) {
            // u's component is smaller, attach its root under v's root
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_u) + size.get(ulp_v)); // update v's root size
        } else {
            // v's component is smaller (or equal), attach its root under u's root
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v)); // update u's root size
        }
        return true; // merge successful
    }
}
