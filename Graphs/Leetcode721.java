import java.util.*;

// Short Revision – Accounts Merge (LC 721)
// Use Disjoint Set on accounts.
// Map each email to an account index.
// If an email repeats, union the current account with the previously stored account.
// After all unions, group emails by their ultimate parent, sort them, and prepend the account name.
// Each connected component becomes one merged account.
// Time Complexity - O(N * K * α(N) + M log M)
// Space Complexity - O(N + M)

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
        int ult_p = findParent(parent.get(node));
        parent.set(node, ult_p);
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

public class Leetcode721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        HashMap<String, Integer> mapMailNode = new HashMap<String, Integer>();

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);

                if (mapMailNode.containsKey(mail) == false) {
                    mapMailNode.put(mail, i);
                } else {
                    ds.unionByRank(i, mapMailNode.get(mail));
                }
            }
        }

        ArrayList<String>[] mergeMail = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            mergeMail[i] = new ArrayList<String>();
        }

        for (Map.Entry<String, Integer> it : mapMailNode.entrySet()) {
            String mail = it.getKey();
            int node = ds.findParent(it.getValue());
            mergeMail[node].add(mail);
        }

        List<List<String>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (mergeMail[i].size() == 0)
                continue;
            Collections.sort(mergeMail[i]);
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));
            for (String it : mergeMail[i]) {
                temp.add(it);
            }
            ans.add(temp);
        }
        return ans;
    }
}