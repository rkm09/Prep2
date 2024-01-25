package LeetDaily.medium;

public class LongestCommonSubseq1143 {
    private static int[][] memo;
    private static String t1, t2;
    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(longestCommonSubsequence3(text1, text2));
    }

//   space optimized bottom up dp [further optimization]; time: O(m*n), O(n); fastest
    public static int longestCommonSubsequence(String text1, String text2) {
        if(text1.length() < text2.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }
        int m = text1.length(), n = text2.length();
        int[] current = new int[n + 1];
        int[] previous = new int[n + 1];
        for(int i = m - 1 ; i >= 0 ; i--) {
            for(int j = n - 1 ; j >= 0 ; j--) {
                if(text1.charAt(i) == text2.charAt(j)) {
                    current[j] = 1 + previous[j + 1];
                } else {
                    current[j] = Math.max(previous[j], current[j + 1]);
                }
            }
//            this swapping is needed, else previous and current both reference the same list
//            reuse the previous array instead for the next iteration's current (instead of letting it get garbage collected)
            int[] temp = previous;
            previous = current;
            current = temp;
        }
        return previous[0];
    }

//    space optimized bottom up dp; time: O(m*n), O(n)
    public static int longestCommonSubsequence1(String text1, String text2) {
        if(text1.length() < text2.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }
        int m = text1.length(), n = text2.length();
        int[] previous = new int[n + 1];
        for(int i = m - 1 ; i >= 0 ; i--) {
            int[] current = new int[n + 1];
            for(int j = n - 1 ; j >= 0 ; j--) {
                if(text1.charAt(i) == text2.charAt(j)) {
                    current[j] = 1 + previous[j + 1];
                } else {
                    current[j] = Math.max(previous[j], current[j + 1]);
                }
            }
            previous = current;
        }
        return previous[0];
    }

//    bottom up DP; time: O(M*N), space: O(M*N)
    public static int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dpGrid = new int[m + 1][n + 1];
        for(int i = m - 1 ; i >= 0 ; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if(text1.charAt(i) == text2.charAt(j)) {
                    dpGrid[i][j] = 1 + dpGrid[i + 1][j + 1];
                } else {
                    dpGrid[i][j] = Math.max(dpGrid[i + 1][j], dpGrid[i][j + 1]);
                }
            }
        }
        return dpGrid[0][0];
    }

//    recursion + memoization (improved); time: O(M * N), space: O(M * N)
    public static int longestCommonSubsequence3(String text1, String text2) {
        t1 = text1; t2 = text2;
        memo = new int[t1.length() + 1][t2.length() + 1];
        for(int i = 0 ; i < t1.length() ; i++) {
            for(int j = 0 ; j < t2.length() ; j++) {
                memo[i][j] = -1;
            }
        }
        return memoSolve(0, 0);
    }

    private static int memoSolve(int p1, int p2) {
        if(memo[p1][p2] != -1) {
            return memo[p1][p2];
        }
        int answer;
        if(t1.charAt(p1) == t2.charAt(p2)) {
            answer = 1 + memoSolve(p1 + 1, p2 + 1);
        } else {
            answer = Math.max(memoSolve(p1, p2 + 1), memoSolve(p1 + 1, p2));
        }
        memo[p1][p2] = answer;
        return answer;
    }

//    brute force; memoization + recursion
//    time: O(M * N^2), space: O(M * N); M possible positions of 1st string and N for 2nd => M*N possibilities. For each, solving sub problem => searching through n => O(m.n^2)
    public static int longestCommonSubsequence4(String text1, String text2) {
        t1 = text1; t2 = text2;
        memo = new int[t1.length() + 1][t2.length() + 1];
        for(int i = 0 ; i < t1.length() ; i++) {
            for(int j = 0 ; j < t2.length() ; j++) {
                memo[i][j] = -1;
            }
        }
        return memoSolve4(0, 0);
    }
    private static int memoSolve4(int p1, int p2) {
        if(memo[p1][p2] != -1) {
            return memo[p1][p2];
        }
        int option1 = memoSolve4(p1 + 1, p2);
        int option2 = 0;
        int firstOccurrence = t2.indexOf(t1.charAt(p1), p2);
        if(firstOccurrence != -1) {
            option2 = 1 + memoSolve4(p1 + 1, firstOccurrence + 1);
        }
        memo[p1][p2] = Math.max(option1, option2);
        return memo[p1][p2];
    }
}

/*
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.
Example 1:
Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:
Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:
Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
Constraints:
1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
 */