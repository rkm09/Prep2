package LeetDaily.easy;

import java.util.HashMap;
import java.util.Map;

public class LargestSubstring1624 {
    public static void main(String[] args) {
        String s = "abca";
        System.out.println(maxLengthBetweenEqualCharacters1(s));
    }

//    hashmap; time: O(n), space: O(1)
    public static int maxLengthBetweenEqualCharacters1(String s) {
        Map<Character, Integer> sub = new HashMap<>();
        int ans = -1;
        for(int i = 0 ; i < s.length() ; i++) {
            char c = s.charAt(i);
            if(sub.containsKey(c)) {
                ans = Math.max(i - sub.get(c) - 1, ans);
            } else {
                sub.put(c, i);
            }
        }
        return ans;
    }

//    [def]; time: O(n); space: O(1) [faster]
    public static int maxLengthBetweenEqualCharacters(String s) {
        final int n = s.length();
        if(n < 2) return -1;
        char[] carr = s.toCharArray();
        if(n == 2) {
            return (carr[0] == carr[1]) ? 0 : -1;
        }
        int[] counts = new int[26];
        int res = -1;
        for(char c : carr) {
            counts[c - 'a']++;
        }
        for(int i = n - 1 ; i >= 0 ; i--) {
            if(counts[carr[i] - 'a'] > 1) {
                res = Math.max(res, i - s.indexOf(carr[i]) - 1);
            }
        }
        return res;
    }
}

/*
Given a string s, return the length of the longest substring between two equal characters, excluding the two characters. If there is no such substring return -1.
A substring is a contiguous sequence of characters within a string.
Example 1:
Input: s = "aa"
Output: 0
Explanation: The optimal substring here is an empty substring between the two 'a's.
Example 2:
Input: s = "abca"
Output: 2
Explanation: The optimal substring here is "bc".
Example 3:
Input: s = "cbzxy"
Output: -1
Explanation: There are no characters that appear twice in s.
Constraints:
1 <= s.length <= 300
s contains only lowercase English letters.
 */