import java.util.*;

// Top → Bottom
// Recursionn --> 1 → 0 --> Starts from the destination and recursively goes backward
// Top → Bottom
// Memoizationn --> 1 → 0 --> Same recursion direction, but stores already-computed states
// Bottom → Top
// Tabulation --> 0 → n-1 --> Starts from the base case and builds the answer forward

// Climbing stairs
class Leetcode70 {

    // brute force is recursion sol
    // tc is o(2^n) sc is o(n)
    public int climbStairs(int n) {

        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    // optimal sol is using dp like fibo series
    // tc is o(n) sc is o(1)
    public int optimal(int n) {

        if (n == 0)
            return 1;
        if (n == 1)
            return 1;

        int prev = 1; //
        int prev2 = 1;

        for (int i = 2; i <= n; i++) {
            int next = prev + prev2;
            prev2 = prev;
            prev = next;
        }
        return prev;
    }
}