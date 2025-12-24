package Strings.Medium;

import java.util.*;

public class Leetcode921 {

    // Brute - force
    // Time Complexity: O(n) ✓ (single pass through string)
    // Space Complexity: O(n) (stack can hold up to n characters in worst case like
    // "(((((")
    public int minAddToMakeValid(String s) {

        int count = 0;
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                st.push(s.charAt(i));
            } else {
                if (st.isEmpty()) {
                    count++;
                } else {
                    st.pop();
                }

            }
        }

        return st.size() + count;
    }

    // optimal
    // Time Complexity: O(n) ✓
    // Space Complexity: O(1) ✓ (only two variables)
    public int minAddToMakeValid2(String s) {
        int open = 0; // unmatched '('
        int close = 0; // unmatched ')'

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--; // match found
                } else {
                    close++; // unmatched ')'
                }
            }
        }

        return open + close;
    }

    public static void main(String[] args) {

    }
}