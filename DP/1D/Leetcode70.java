import java.util.*;

// Top → Bottom
// Recursionn --> 1 → 0 --> Starts from the destination and recursively goes backward
// Top → Bottom
// Memoizationn --> 1 → 0 --> Same recursion direction, but stores already-computed states
// Bottom → Top
// Tabulation --> 0 → n-1 --> Starts from the base case and builds the answer forward

class Leetcode70 {
    // tc is o(n) and sc is o(1).
    // to find all the ways we use recursion i.e left + right.
    public int climbStairs(int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return 1;
        int prev = 1; // for climbing stairs 0.
        int prev2 = 1; // for climbing stairs 1.
        for (int i = 2; i <= n; i++) {
            int curi = prev + prev2;
            prev2 = prev;
            prev = curi;
        }
        return prev;
    }
}