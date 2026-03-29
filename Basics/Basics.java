package Basics;

import java.util.*;

public class Basics {

    // Use Integer.toString(n)
    // to convert complete num to String
    int num = 123;
    String val = Integer.toString(num);
    // ans -> "123"

    int[] arr = {1,2,3};
    // to use any hasmap prob unique arr string
    String arrStr = Arrays.toString(arr);
    
    // use ch - '0';
    // to convert char num to int
    char ch = '2';
    int val1 = ch - '0';
    // ans -> 2

    // starting ascii values
    // 'a' (lowercase): 97
    // 'A' (uppercase): 65

    // (char) 67 int → char: gives 'C' (ASCII 67)
    // (int) 'C' char → int: gives 67

    // char → int (get numeric position):
    // 'C' - 'A' -> gives 2 (position in alphabet, 0-indexed)
    // '5' - '0' -> gives 5 (digit char → actual int value)

    // int → char (get letter from position):
    // (char)('A' + 2) -> gives 'C'
    // (char)('0' + 5) -> gives '5'

    // Subsets
    // Taking any combination of elements (order doesn't matter)
    // From "abc":
    // "" ← empty set
    // "a"
    // "b"
    // "c"
    // "ab"
    // "ac"
    // "bc"
    // "abc"
    // Total = 2ⁿ (2³ = 8 subsets)

    // Subsequences
    // Same as subsets but order is maintained from original
    // From "abc":
    // ""
    // "a"
    // "b"
    // "c"
    // "ab" ← a comes before b (original order)
    // "ac" ← a comes before c (original order)
    // "bc" ← b comes before c (original order)
    // "abc"
    // Count is also 2ⁿ but order is preserved

    // generate all the permutaions of string
    // "abc" -> abc acb bac bca cba cab
    public static String swap(String s, int i, int j) {
        char[] arr = s.toCharArray();
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return String.valueOf(arr);
    }

    public static void generatePermutations(String s, int start, int end, HashSet<String> set) {
        if (start == end - 1) {
            set.add(s);
            return;
        }
        for (int i = start; i < end; i++) {
            s = swap(s, start, i);
            generatePermutations(s, start + 1, end, set);
            s = swap(s, start, i);
        }
    }

    // here base is 20
    // e -> 5
    // ac -> 23 , a val is 1 * 20 + c val is 3 so 20 + 3 = 23
    public static int numberSystem(String s) {
        int cnt = 1; // unit, 10s , 100s place num
        int sum = 0;
        for (int i = s.length() - 1; i >= 0; i--) { // movind from unit place
            int n = (s.charAt(i) - 'a') + 1; // getting char num
            sum += n * cnt; // sum
            cnt *= 20; // changing unit place value
        }
        return sum;
    }

    // reverse integer
    public static int reverseInt(int num) {
        int res = 0;

        while (num != 0) {
            int digit = num % 10;
            res = (res * 10) + digit;
            num = num / 10;
        }

        return res;
    }

    public static void main(String[] args) {

        // Substrings
        // Continuous/adjacent characters only
        // From "abc":
        // "a"
        // "b"
        // "c"
        // "ab" ← continuous ✅
        // "bc" ← continuous ✅
        // "abc" ← continuous ✅
        // "ac" ← NOT a substring ❌ (not adjacent)

        // generate all the substrings
        String s = "1234";
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                System.out.print(s.substring(i, j + 1));
            }
        }
    }

}
