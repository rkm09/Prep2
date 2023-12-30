package LeetDaily.hard;

public class ValidPalindrome1216 {
    private static Integer[][] memo;
    public static void main(String[] args) {
        String s = "bacabaaa"; int k = 2;
        System.out.println(isValidPalindrome1(s, k));
    }

//    Top down DP(2D); time: O(n^2), space: O(n^2)
//    In this case top down more efficient
    public static boolean isValidPalindrome(String s, int k) {
        final int n = s.length();
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

    //    Bottom up DP(2D); time: O(n^2), space: O(n^2)
    public static boolean isValidPalindrome1(String s, int k) {
        final int n = s.length();
        int[][] memo = new int[n][n];
        for(int i = n - 2 ; i >= 0 ; i--) {
            for(int j = i + 1; j < n ; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    memo[i][j] = memo[i+1][j-1];
                } else {
                    memo[i][j] = 1 + Math.min(memo[i+1][j], memo[i][j-1]);
                }
            }
        }
        return memo[0][n-1] <= k;
    }

//    Bottom up DP(1D); time: O(n^2), space: O(1)
    public static boolean isValidPalindrome2(String s, int k) {
        final int n = s.length();
        int[] memo = new int[n];
        int prev, temp;
        for(int i = n - 2 ; i >= 0 ; i--) {
//            store memo[i+1][j-1]
            prev = 0;
            for(int j = i + 1; j < n ; j++) {
//                store memo[i+1][j]
                temp = memo[j];
                if(s.charAt(i) == s.charAt(j)) {
                    memo[j] = prev;
                } else {
//                  memo[j] will contain the value for memo[i+1][j]
//                  memo[j-1] will contain the value for memo[i][j-1]
                    memo[j] = 1 + Math.min(memo[j], memo[j-1]);
                }
                prev = temp;
            }
        }
        return memo[n-1] <= k;
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

Top down DP:
Complexity Analysis

Time complexity : O(n^2)
Where n is the length of string s. This is due to the fact that we try to find result for all combinations of i and j where i and j range from 0 to n, to compute a combination we perform an O(1) operation thus the final complexity is O(n^2)

Space complexity : O(n^2)
Where n is the length of string s. This is due to caching all the results in memo table. The recursion stack depth can reach at max O(n) depth. Thus, the complexity is dominated by the space required for memo.


 */