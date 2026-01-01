package Arrays;

// 125. Valid Palindrome
public class Leetcode125 {

    // brute - force
    // tc is o(n) and sc is o(n).
    public static boolean isPalindrome(String str) {

        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int n = str.length();
        StringBuilder sb = new StringBuilder();

        for (int i = n - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }

        String ans = sb.toString();

        return str.equals(ans);
    }

    // optimal
    // tc is o(n) and sc is o(1).
    public boolean isPalindrome2(String s) {

        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;

    }

    public static void main(String[] args) {

    }

}
