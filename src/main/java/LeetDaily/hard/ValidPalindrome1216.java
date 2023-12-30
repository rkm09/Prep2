package LeetDaily.hard;

public class ValidPalindrome1216 {
    private static Integer[][] memo;
    public static void main(String[] args) {
        String s = "bacabaaa"; int k = 2;
        System.out.println(isValidPalindrome(s, k));
    }
    public static boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        memo = new Integer[n][n];
        return isValidPalindrome(s, 0, n - 1) <= k;
    }
    private static int isValidPalindrome(String s, int i, int j) {
        if(i == j) {
            return 0;
        }
        if(i == j - 1) {
            return s.charAt(i) != s.charAt(j) ? 1 : 0;
        }
        if(memo[i][j] != null) {
            return memo[i][j];
        }
        if(s.charAt(i) == s.charAt(j)) {
            return memo[i][j] = isValidPalindrome(s, i + 1, j - 1);
        }

        return memo[i][j] = 1 + Math.min(isValidPalindrome(s, i + 1, j), isValidPalindrome(s, i, j - 1));
    }
}
/*
Given a string s and an integer k, return true if s is a k-palindrome.
A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.
Example 1:
Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.
Example 2:
Input: s = "abbababa", k = 1
Output: true
Constraints:
1 <= s.length <= 1000
s consists of only lowercase English letters.
1 <= k <= s.length
 */