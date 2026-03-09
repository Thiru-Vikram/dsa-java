package Algorithms.Toposort;

import java.util.*;

public class AlienDictionary {

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

    // same as topo sort, you will return the topological order which gives the
    // alien dictionary order.
    // there are two cases that won't work: when a larger string appears before a
    // shorter string and
    // everything has the same characters up to that point (invalid ordering),
    // and when there is a cycle. e.g., a before b and b before a
    // it's a cycle, which makes a valid ordering impossible.
    // dict contains words, n is the number of words, k is the number of unique
    // characters
    public String alienDictionary(String[] dict, int n, int k) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            String s1 = dict[i];
            String s2 = dict[i + 1];
            int len = Math.min(s1.length(), s2.length());
            for (int ptr = 0; ptr < len; ptr++) {
                if (s1.charAt(ptr) != s2.charAt(ptr)) {
                    adj.get(s1.charAt(ptr) - 'a').add(s2.charAt(ptr) - 'a');
                    break;
                }
            }
        }

        int[] topoOrder = toposortUsingBfs(k, adj);
        String ans = "";
        for (int it : topoOrder) {
            ans = ans + (char) (it + (int) ('a'));
        }

        return ans;
    }
}
