package Algorithms.Toposort;

import java.util.*;

// toposort using bfs algo called as the khan's algo
// Topological sort is a linear ordering of vertices in a Directed Acyclic Graph (DAG) 
// where for every directed edge u -> v, vertex u comes before v
// For a graph a -> b and a -> c, a valid topological sort is (a, b, c) or (a, c, b)
// it gives the order of vertices in u before v

// algo is first you find the indegree of each node
// push the zero indegree nodes in q
// now take one by one ele from q and reduce the indegree of adjacent nodes
// remove that edges and reduce the indegree again when indegree is 0 add it in q
// repeat by doing this you will get the order.
public class Toposort {

    public static int[] toposortUsingBfs(int v, List<List<Integer>> adj) {

        // to count and store the indegree of the nodes.
        int[] inDegree = new int[v];
        for (int i = 0; i < v; i++) {
            for (int it : adj.get(i)) {
                inDegree[it]++;
            }
        }
        // to insert the values in the queue whose indegree is 0.
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        int[] topoOrder = new int[v];
        int i = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            // store the answer.
            topoOrder[i++] = node;

            // check for the nodes adj and reduce the indegree and add if indegree is 0.
            for (int it : adj.get(node)) {
                inDegree[it]--;

                if (inDegree[it] == 0) {
                    q.add(it);
                }
            }
        }
        return topoOrder;
    }

}
