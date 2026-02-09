package DP;

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