package Stack;

import java.util.*;

public class Problems {

    private static int precedence(char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch == '*' || ch == '/') {
            return 2;
        } else if (ch == '^') {
            return 3;
        }
        return -1;
    }

    public static String toPostFix(String str) {

        StringBuilder sb = new StringBuilder();
        Stack<Character> st = new Stack<>();

        for (char ch : str.toCharArray()) {

            if (Character.isLetterOrDigit(ch)) {
                sb.append(ch);
            } else if (ch == '(') {
                st.push(ch);
            } else if (ch == ')') {

                while (!st.isEmpty() && st.peek() != '(') {
                    sb.append(st.pop());
                }
                st.pop();
            } else {
                while (!st.isEmpty() && precedence(ch) <= precedence(st.peek())) {
                    sb.append(st.pop());
                }
                st.push(ch);
            }
        }

        while (!st.isEmpty()) {
            sb.append(st.pop());
        }
        return sb.toString();
    }

    // post fix evalution
    public static int postfixEvalution(String str) {

        Stack<Integer> st = new Stack<>();

        for (char ch : str.toCharArray()) {

            if (Character.isDigit(ch)) {
                st.push(ch - '0');
            } else {

                int b = st.pop();
                int a = st.pop();

                switch (ch) {
                    case '+':
                        st.push(a + b);
                        break;
                    case '-':
                        st.push(a - b);
                        break;
                    case '*':
                        st.push(a * b);
                        break;
                    case '/':
                        st.push(a / b);
                        break;
                    default:
                        break;
                }
            }
        }
        return st.pop();
    }

    // for prefix evalution iterate from right to left
    public static int prefixEvalution(String str) {

        Stack<Integer> st = new Stack<>();

        for (int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            if (Character.isDigit(str.charAt(i))) {
                st.push(str.charAt(i) - '0');
            } else {

                int b = st.pop();
                int a = st.pop();

                switch (ch) {
                    case '+':
                        st.push(a + b);
                        break;
                    case '-':
                        st.push(a - b);
                        break;
                    case '*':
                        st.push(a * b);
                        break;
                    case '/':
                        st.push(a / b);
                        break;
                    default:
                        break;
                }
            }
        }
        return st.pop();
    }

    public static String toPreFix(String str) {

        // 1. reverse given expre
        StringBuilder reversed = new StringBuilder(str).reverse();

        // 2. swap the brackets
        for (int i = 0; i < reversed.length(); i++) {
            if (reversed.charAt(i) == '(') {
                reversed.setCharAt(i, ')');
            } else if (reversed.charAt(i) == ')') {
                reversed.setCharAt(i, '(');
            }
        }

        // 3. convert it into postfix
        StringBuilder sb = new StringBuilder();
        String ans = toPostFix(reversed.toString());
        sb.append(ans);

        return sb.reverse().toString();
    }

}
