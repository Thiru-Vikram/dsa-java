package OnlineAssignment;

import java.util.*;

public class CodeathonJan_26 {

    public static void main(String[] args) {

        // Problem Statement
        // Viswas wants to spend his weekend watching some short films. He got a finite
        // array of short films in front of him. He prefers to watch the highly-rated
        // short film first. As he already knows selection sorting, he wants to sort
        // them in such a fashion. Write a code to exhibit how the sorting algorithm
        // flows step by step in his perception.

        // Note:
        // Observe the output carefully to find the steps of the sorting technique from
        // his perception. It's nothing but the descending sort achieved by swapping the
        // maximum elements to the front of every iteration.

        // Input format :
        // The first line of input consists of the array size.
        // The second line of input consists of the array elements.

        // Output format :
        // The output prints how the algorithm flows step by step in his perception as
        // described in the problem statement.

        // Refer to the sample output for format specifications.
        // Sample test cases :
        // Input 1 :
        // 5
        // 3 4 5 1 2
        // Output 1 :
        // After his step : 1
        // 5 4 3 1 2
        // After his step : 2
        // 5 4 3 1 2
        // After his step : 3
        // 5 4 3 1 2
        // After his step : 4
        // 5 4 3 2 1
        // Finally, he got it...
        // 5 4 3 2 1

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }

        // Selection sort in descending order
        for (int i = 0; i < n - 1; i++) {
            // Find maximum element in remaining array
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }

            // Swap maximum element to current position
            int temp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = temp;

            // Print after each step
            System.out.println("After his step : " + (i + 1));
            for (int k = 0; k < n; k++) {
                System.out.print(arr[k]);
                if (k < n - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        // Print final result
        System.out.println("Finally, he got it...");
        for (int k = 0; k < n; k++) {
            System.out.print(arr[k]);
            if (k < n - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        // Problem Statement
        // Pattern Matching with Prasath
        // You and Prasath are playing a String game in which you guys have to find the
        // occurrence of the pattern in the main string. In order to help Prasath to
        // score more you came up with an idea to automate it by coding. Write a
        // suitable code to achieve

        // the same.
        // P.S:
        // Consider there is no case sensitivity and you need to print the starting
        // position of the pattern in the main string.

        // Example
        // Input
        // abcabCdabc
        // abcdabc
        // Output
        // 4

        // Explanation
        // After ignoring the case, the pattern is found from the 4th character in the
        // main string so the output is 4.
        // Input format :
        // The first line of input is the main String.
        // The second line of input is the pattern.
        // Output format :
        // The output prints the position number of occurrences of the pattern in the
        // main string. Otherwise, prints "Not Found".

        // Sample test cases :
        // Input 1 :
        // captainCaPtain
        // cAp
        // Output 1 :
        // Found at 1
        // Found at 8
        // Input 2 :
        // four
        // FOUT
        // Output 2 :
        // Not Found

        Scanner sc = new Scanner(System.in);

        String mainString = sc.nextLine();
        String pattern = sc.nextLine();

        // Convert both to lowercase for case-insensitive comparison
        String mainLower = mainString.toLowerCase();
        String patternLower = pattern.toLowerCase();

        boolean found = false;
        int patternLen = pattern.length();
        int mainLen = mainString.length();

        // Search for pattern in main string
        for (int i = 0; i <= mainLen - patternLen; i++) {
            // Extract substring of same length as pattern
            String substring = mainLower.substring(i, i + patternLen);

            // Check if it matches the pattern
            if (substring.equals(patternLower)) {
                System.out.println("Found at " + (i + 1)); // 1-based indexing
                found = true;
            }
        }

        // If no occurrence found
        if (!found) {
            System.out.println("Not Found");
        }

        sc.close();

    }

}
