package LeetDaily.hard;

public class ValidPalindrome1216 {
    public static void main(String[] args) {
        String s = "cacababac"; int k = 3;
        System.out.println(isValidPalindrome(s, k));
    }
    public static boolean isValidPalindrome(String s, int k) {
        char[] carr = s.toCharArray();
        int n = carr.length;
        if(n == 1) return true;
        int i = 0, j = n - 1;
        while(i < j) {
            if(carr[i] != carr[j]) {
                if(k < 1) return false;
                while(k > 0) {
                    if(carr[i+1] == carr[j]) {
                        i++; k--;
                    } else if(carr[i] == carr[j-1]) {
                        j--; k--;
                    }
                }
            }
            i++; j--;
        }
        return true;
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