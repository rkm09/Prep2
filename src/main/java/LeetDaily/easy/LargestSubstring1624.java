package LeetDaily.easy;

public class LargestSubstring1624 {
    public static void main(String[] args) {
        String s = "abca";
        System.out.println(maxLengthBetweenEqualCharacters(s));
    }
    public static int maxLengthBetweenEqualCharacters(String s) {
        final int n = s.length();
        if(n < 2) return -1;
        if(n == 2) {
            return (s.charAt(0) == s.charAt(1)) ? 0 : -1;
        }
        char[] carr = s.toCharArray();
        int[] counts = new int[26];
        int res = Integer.MIN_VALUE;
        for(char c : carr) {
            counts[c - 'a']++;
        }
        for(int i = n - 1 ; i >= 0 ; i--) {
            if(counts[carr[i] - 'a'] > 1) {
                res = Math.max(res, i - s.indexOf(carr[i]) - 1);
            }
        }
        return res > 0 ? res : -1;
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