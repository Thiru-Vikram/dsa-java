package Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Neetcode {

    // Longest Substring Without Repeating Characters
    // tc is o(n) and sc is o(1)
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        // 1 based idxing so + 1
        int[] hash = new int[256]; // store last seen index+1 of each char
        int l = 0; // left pointer

        // right pointer
        for (int r = 0; r < n; r++) {
            char c = s.charAt(r);

            // if char seen again reduce left pointer to newest poition
            if (hash[c] > 0) {
                l = Math.max(l, hash[c]);
            }

            // update maxLength
            maxLength = Math.max(maxLength, r - l + 1);

            // store last index+1
            hash[c] = r + 1;
        }

        return maxLength;
    }

    // 424. Longest Repeating Character Replacement
    // tc is o(n) and sc is o(1)
    public int characterReplacement(String s, int k) {
        // left and right pointer
        int l = 0, r = 0;
        int maxLen = 0;
        int maxFreq = 0;
        int[] hash = new int[26];

        while (r < s.length()) {
            // update frequency of current character
            hash[s.charAt(r) - 'A']++;
            // update the maximum frequency of any character in curr window
            maxFreq = Math.max(maxFreq, hash[s.charAt(r) - 'A']);

            // check curr window is invalid (too many replacements needed)
            // window len - max fre of char in that window
            while ((r - l + 1) - maxFreq > k) {
                hash[s.charAt(l) - 'A']--; // reduce freq
                l++; // move left pointer
            }

            // update maxLen with valid window size
            maxLen = Math.max(maxLen, r - l + 1);
            r++; // move right pointer
        }
        return maxLen;
    }

    // 76. Minimum Window Substring
    // tc is o(n + m) and sc is o(1)
    public static String minString(String s, String t) {

        int n = s.length();
        int m = t.length();
        if (m == 0 || m > n)
            return "";

        int minLen = Integer.MAX_VALUE;
        int startIdx = -1;
        int[] hash = new int[256]; // need[c] = how many of char c are still required
        int count = 0; // number of matched chars from t inside current window
        int l = 0, r = 0;

        // Build requirement table from t.
        for (int i = 0; i < m; i++) {
            hash[t.charAt(i)]++;
        }

        // Meaning of need table during sliding window:
        // need[c] > 0 : still need this char
        // need[c] == 0: exact requirement met
        // need[c] < 0 : extra occurrence present in window

        while (r < n) {

            // check this char needed then cnt++
            if (hash[s.charAt(r)] > 0) {
                count++;
            }
            // again reduce it
            hash[s.charAt(r)]--;

            // If all m required chars are matched, try shrinking from left.
            while (count == m) {

                // Update best answer with current valid window.
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    startIdx = l;
                }

                // Remove s[l] from window and give it back to need table.
                // If need becomes > 0, window is no longer valid.
                hash[s.charAt(l)]++;
                if (hash[s.charAt(l)] > 0) {
                    count--;
                }
                l++; // shrink from left
            }
            r++; // expand to next right char
        }

        return startIdx == -1 ? "" : s.substring(startIdx, startIdx + minLen);
    }

    // 242. Valid Anagram
    // tip :- only small or capital -> 26 or mix -> 256 for array size
    // tc is o(n + m) sc is o(1)
    public static boolean isAnagram(String s, String t) {

        int n = s.length();
        int m = t.length();
        if (n != m)
            return false;

        int[] hash = new int[26];

        for (int i = 0; i < n; i++) {
            hash[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < m; i++) {
            hash[t.charAt(i) - 'a']--;
        }

        for (int i : hash) {
            if (i != 0)
                return false;
        }

        return true;
    }

    // 49. Group Anagrams
    // tc is o(n * k) sc is o(n * k) — stores all strings in the map
    public static List<List<String>> groupAnagrams(String[] strs) {

        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            // Frequency array: count each char
            char[] ch = new char[26];
            for (char c : s.toCharArray()) {
                ch[c - 'a']++;
            }
            // Unique key from frequency pattern
            String key = String.valueOf(ch);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {

        String[] arr = { "eat", "tea", "tan", "ate", "nat", "bat" };

        List<List<String>> ans = groupAnagrams(arr);
        for (int i = 0; i < ans.size(); i++) {
            for (int j = 0; j < ans.get(i).size(); j++) {
                System.out.print(ans.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

}
