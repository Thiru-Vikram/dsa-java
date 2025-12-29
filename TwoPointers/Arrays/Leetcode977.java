package Arrays;

import java.util.*;

public class Leetcode977 {

    // brute - force
    // tc is o(n log n) and sc is o(n)
    public int[] sortedSquares(int[] nums) {

        int n = nums.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            ans[i] = nums[i] * nums[i];
        }

        Arrays.sort(ans);

        return ans;
    }

    // optimal
    // tc is o(n) and sc is o(n)
    public int[] sortedSquares2(int[] nums) {

        int n = nums.length;
        int[] ans = new int[n];

        int left = 0;
        int right = n - 1;
        int idx = n - 1;

        while (left <= right) {
            int leftsqr = nums[left] * nums[left];
            int rightsqr = nums[right] * nums[right];

            if (leftsqr > rightsqr) {
                ans[idx] = leftsqr;
                left++;
            } else {
                ans[idx] = rightsqr;
                right--;
            }
            idx--;
        }
        return ans;
    }

    public static void main(String[] args) {

    }

}
