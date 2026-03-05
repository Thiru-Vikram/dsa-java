package Math;

import java.util.HashSet;

public class Problems {

    // finding hcf Euclidean Algorithm
    // tc is o(log min(a,b)) sc is o(1).
    public static int findHCF(int a, int b) {

        while (a > 0 && b > 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
            if (a == 0)
                return b;
            if (b == 0)
                return a;
        }
        return 0;
    }

    // Problem: Magic Number Counter
    // Definition: A number is called a "Magic Number" if:

    // Calculate the product of its digits repeatedly until you get a single digit
    // Calculate the square of that single digit
    // Check if the sum of digits of the original number equals the square result

    // Example 1: Is 24 a Magic Number?
    // Step 1: Product of digits: 2 × 4 = 8 (single digit ✓)
    // Step 2: Square: 8² = 64
    // Step 3: Sum of original digits: 2 + 4 = 6
    // Step 4: Does 6 equal 64? NO ✗
    // 24 is NOT a Magic Number

    // True Example 2: Is 112 a Magic Number?
    // Step 1: Product: 1 × 1 × 2 = 2 (single digit ✓)
    // Step 2: Square: 2² = 4
    // Step 3: Sum of original: 1 + 1 + 2 = 4
    // Step 4: Does 4 equal 4? YES ✓
    // 112 IS a Magic Number!

    public int countMagicNum(int[] arr) {
        int n = arr.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int num = arr[i];

            int oriSum = 0;
            int temp = num;
            while (temp != 0) {
                oriSum += temp % 10;
                temp = temp / 10;
            }

            int sd = num;
            while (sd >= 10) {
                int mult = 1;
                while (sd != 0) {
                    mult *= sd % 10;
                    sd = sd / 10;
                }
                sd = mult;
            }

            int squareNum = sd * sd;

            if (squareNum == oriSum) {
                count++;
            }
        }
        return count;
    }

    // Problem: Perfect Sum Factorial Numbers
    // Definition: A number is called a "Perfect Sum Factorial Number" if all the
    // digits of the original number are present in its sum factorial.
    // What is Sum Factorial?

    // Take a number and reduce it to a single digit by repeatedly adding its digits
    // Calculate the factorial of that single digit
    // Check if all digits of the original number exist in the factorial result

    // Example 1: Is 122 a Perfect Sum Factorial Number?
    // Step 1: Sum of digits: 1 + 2 + 2 = 5 (single digit ✓)
    // Step 2: Factorial: 5! = 5 × 4 × 3 × 2 × 1 = 120
    // Step 3: Original digits: {1, 2}
    // Step 4: Factorial digits: {1, 2, 0}
    // Step 5: Are all original digits {1, 2} present in {1, 2, 0}? YES ✓
    // 122 IS a Perfect Sum Factorial Number!

    // Example 2: Is 46 a Perfect Sum Factorial Number?
    // Step 1: Sum: 4 + 6 = 10 → 1 + 0 = 1 (single digit ✓)
    // Step 2: Factorial: 1! = 1
    // Step 3: Original digits: {4, 6}
    // Step 4: Factorial digits: {1}
    // Step 5: Are {4, 6} present in {1}? NO ✗
    // 46 is NOT a Perfect Sum Factorial Number

    public int countPerfectSum(int[] arr) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int num = arr[i];

            HashSet<Integer> orgSet = new HashSet<>();
            int temp = num;
            while (temp != 0) {
                orgSet.add(temp % 10);
                temp = temp / 10;
            }

            int sd = num;
            while (sd >= 10) {
                int sum = 0;
                while (sd != 0) {
                    sum += sd % 10;
                    sd = sd / 10;
                }
                sd = sum;
            }

            int fact = 1;
            for (int j = 1; j <= sd; j++) {
                fact = fact * j;
            }

            HashSet<Integer> factSet = new HashSet<>();
            int temp2 = fact;
            while (temp2 != 0) {
                factSet.add(temp2 % 10);
                temp2 = temp2 / 10;
            }

            if (factSet.containsAll(orgSet)) {
                count++;
            }

        }
        return count;
    }

    public static void main(String[] args) {

    }

}
