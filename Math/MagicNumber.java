package Math;

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

public class MagicNumber {

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

    public static void main(String[] args) {

    }

}
