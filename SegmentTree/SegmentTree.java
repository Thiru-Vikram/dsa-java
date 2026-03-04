public class SegmentTree {

    int[] tree;
    int n;

    // ─────────────────────────────────────────────────────────
    // CONSTRUCTOR — call this to set up
    // Pass your array directly
    // ─────────────────────────────────────────────────────────
    SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n]; // Always 4x the array size
        build(arr, 1, 0, n - 1);
    }

    // ─────────────────────────────────────────────────────────
    // MERGE — ⭐ THIS IS THE ONLY LINE YOU CHANGE PER PROBLEM
    //
    // For SUM → return a + b;
    // For MIN → return Math.min(a, b);
    // For MAX → return Math.max(a, b);
    // For GCD → return gcd(a, b);
    // ─────────────────────────────────────────────────────────
    int merge(int a, int b) {
        return a + b; // ← CHANGE THIS LINE ONLY
    }

    // ─────────────────────────────────────────────────────────
    // IDENTITY VALUE — used when a segment is out of range
    //
    // For SUM → 0
    // For MIN → Integer.MAX_VALUE
    // For MAX → Integer.MIN_VALUE
    // ─────────────────────────────────────────────────────────
    int IDENTITY = 0; // ← CHANGE THIS TOO if needed

    // ─────────────────────────────────────────────────────────
    // BUILD — builds the tree from the array O(n)
    // You don't need to call this manually (constructor does it)
    // ─────────────────────────────────────────────────────────
    void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2 * node, start, mid);
            build(arr, 2 * node + 1, mid + 1, end);
            tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
        }
    }

    // ─────────────────────────────────────────────────────────
    // QUERY — get result from index L to R O(log n)
    // Call: st.query(L, R)
    // ─────────────────────────────────────────────────────────
    int query(int L, int R) {
        return queryHelper(1, 0, n - 1, L, R);
    }

    private int queryHelper(int node, int start, int end, int L, int R) {
        if (R < start || end < L) {
            return IDENTITY; // Completely outside range
        }
        if (L <= start && end <= R) {
            return tree[node]; // Completely inside range
        }
        int mid = (start + end) / 2;
        int left = queryHelper(2 * node, start, mid, L, R);
        int right = queryHelper(2 * node + 1, mid + 1, end, L, R);
        return merge(left, right); // Partial overlap — combine children
    }

    // ─────────────────────────────────────────────────────────
    // UPDATE — change arr[idx] to newVal O(log n)
    // Call: st.update(idx, newVal)
    // ─────────────────────────────────────────────────────────
    void update(int idx, int newVal) {
        updateHelper(1, 0, n - 1, idx, newVal);
    }

    private void updateHelper(int node, int start, int end, int idx, int newVal) {
        if (start == end) {
            tree[node] = newVal; // Found the leaf — update it
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) {
                updateHelper(2 * node, start, mid, idx, newVal);
            } else {
                updateHelper(2 * node + 1, mid + 1, end, idx, newVal);
            }
            tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
        }
    }

    // ─────────────────────────────────────────────────────────
    // MAIN — Test / Demo
    // ─────────────────────────────────────────────────────────
    public static void main(String[] args) {

        int[] arr = { 1, 3, 5, 7, 9, 11 };

        SegmentTree st = new SegmentTree(arr);

        // Query: sum from index 1 to 4 → 3+5+7+9 = 24
        System.out.println("Sum [1,4] = " + st.query(1, 4)); // 24

        // Update: index 2 becomes 10 (was 5)
        st.update(2, 10);

        // Query again: sum from 1 to 4 → 3+10+7+9 = 29
        System.out.println("Sum [1,4] after update = " + st.query(1, 4)); // 29

        // Full array sum → 1+3+10+7+9+11 = 41
        System.out.println("Total sum = " + st.query(0, 5)); // 41
    }
}

// ============================================================
// HOW TO ADAPT FOR DIFFERENT PROBLEMS
// ============================================================
//
// ✅ RANGE SUM (default above):
// merge → return a + b;
// IDENTITY = 0;
//
// ✅ RANGE MINIMUM:
// merge → return Math.min(a, b);
// IDENTITY = Integer.MAX_VALUE;
//
// ✅ RANGE MAXIMUM:
// merge → return Math.max(a, b);
// IDENTITY = Integer.MIN_VALUE;
//
// ✅ RANGE GCD:
// merge → return gcd(a, b);
// IDENTITY = 0;
// (add helper: int gcd(int a, int b) { return b == 0 ? a : gcd(b, a%b); })
//
// ============================================================
// CHEAT SHEET
// ============================================================
//
// tree size → 4 * n
// root node → always 1
// left child → 2 * node
// right child → 2 * node + 1
// build time → O(n)
// query time → O(log n)
// update time → O(log n)
//
// ============================================================