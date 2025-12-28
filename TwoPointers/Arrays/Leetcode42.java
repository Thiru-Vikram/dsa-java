package Arrays;

// Trapping Rain Water
public class Leetcode42 {

    // brute - force
    // tc is o(n^2) for loops and sc iso(1) for variables.
    public int trap(int[] height) {
        int n = height.length;
        int total = 0;

        // For each position, find the water it can trap
        for (int i = 0; i < n; i++) {
            // Find max height to the left
            int leftMax = 0;
            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }

            // Find max height to the right
            int rightMax = 0;
            for (int j = i; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            // Water trapped at position i = min(leftMax, rightMax) - height[i]
            total += Math.min(leftMax, rightMax) - height[i];
        }

        return total;
    }

    // optimal
    // tc is o(n) for traversal, sc is o(1) only vairables
    public int trap2(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;
        int total = 0;

        while (left < right) {

            if (height[left] <= height[right]) {

                if (leftMax > height[left]) {
                    total += leftMax - height[left];
                } else {
                    leftMax = height[left];
                }
                left++;

            } else {

                if (rightMax > height[right]) {
                    total += rightMax - height[right];
                } else {
                    rightMax = height[right];
                }
                right--;
            }
        }
        return total;
    }

    public static void main(String[] args) {

    }
}
