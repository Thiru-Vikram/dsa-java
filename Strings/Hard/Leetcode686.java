package Strings.Hard;

public class Leetcode686 {

    // Brute - force
    // Time Complexity: O((n + m) * m) where n = length of a, m = length of b
    // Space Complexity: O(n + m)
    public int repeatedStringMatch(String a, String b) {

        StringBuilder str = new StringBuilder(a);
        int count = 1;

        while (str.length() < b.length()) {
            str.append(a);
            count++;
        }
        if (str.toString().contains(b))
            return count;

        str.append(a);
        count++;
        if (str.toString().contains(b))
            return count;

        return -1;
    }

    // optimal, karp - rabin algo
    // Time: O(n + m) average case (O(n*m) worst case with hash collisions, but
    // rare)
    // Space: O(1) if you don't build the repeated string
    public int repeatedStringMatch2(String a, String b) {
        int minRepeat = (int) Math.ceil((double) b.length() / a.length());
        int maxRepeat = minRepeat + 1;

        // Try with minRepeat
        if (rabinKarp(a, b, minRepeat)) {
            return minRepeat;
        }

        // Try with maxRepeat
        if (rabinKarp(a, b, maxRepeat)) {
            return maxRepeat;
        }

        return -1;
    }

    private boolean rabinKarp(String a, String b, int repeat) {
        long MOD = 1_000_000_007;
        long BASE = 256;

        int m = b.length();
        int n = a.length();

        // Calculate hash of pattern b
        long patternHash = 0;
        long power = 1;

        for (int i = 0; i < m; i++) {
            patternHash = (patternHash * BASE + b.charAt(i)) % MOD;
            if (i < m - 1) {
                power = (power * BASE) % MOD;
            }
        }

        // Calculate rolling hash on repeated a
        long textHash = 0;
        int totalLen = n * repeat;

        for (int i = 0; i < totalLen; i++) {
            // Add current character (simulate repeated string)
            textHash = (textHash * BASE + a.charAt(i % n)) % MOD;

            // If window is not full yet, continue
            if (i < m - 1)
                continue;

            // Remove leftmost character if window exceeded
            if (i >= m) {
                int leftChar = a.charAt((i - m) % n);
                textHash = (textHash - leftChar * power % MOD + MOD) % MOD;
            }

            // Compare hashes
            if (textHash == patternHash) {
                // Verify actual match to avoid hash collision
                if (verify(a, b, i - m + 1, repeat)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean verify(String a, String b, int start, int repeat) {
        int n = a.length();
        for (int i = 0; i < b.length(); i++) {
            if (a.charAt((start + i) % n) != b.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }

}