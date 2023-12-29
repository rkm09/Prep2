package LeetDaily.hard;

import java.util.*;

public class LengthOfOptimalComp1531 {
    public static void main(String[] args) {
        String s = "aaabcccd"; int k = 2;
        System.out.println(getLengthOfOptimalCompression(s, k));
    }
    public static int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length();
        int[][] dp = new int[110][110];
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= n; j++)
                dp[i][j] = 9999;
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                int cnt = 0, del = 0;
                for (int l = i; l >= 1; l--) {
                    if (s.charAt(l - 1) == s.charAt(i - 1))
                        cnt++;
                    else
                        del++;

                    if (j - del >= 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[l - 1][j - del] + 1 + (cnt >= 100 ? 3 : cnt >= 10 ? 2 : cnt >= 2 ? 1 : 0));
                    }
                }
                if (j > 0)
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
            }
        }
        return dp[n][k];
    }
}
/*
public int getLength(int count) {
        if (count == 1) return 1;
        else if (count < 10) return 2;
        else if (count < 100) return 3;
        else return 4;
    }

    public int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n + 1][k + 1];

        for (int i = n; i >= 0; i--) {
            for (int j = 0; j <= k; j++) {
                // Base case - initial length declaration
                if (i == n) {
                    dp[n][j] = 0;
                    continue;
                }

                // Case 1 - delete the ith character if possible (j > 0)
                dp[i][j] = (j > 0) ? dp[i + 1][j - 1] : Integer.MAX_VALUE;

                // Case 2 - we keep the ith index.
                // We have to find the current char's index endpoint
                int possible_del = j, count = 0;
                for (int end = i; end < n && possible_del >= 0; end++) {
                    // Check about the same char "b b b .." for encoding
                    if (s.charAt(end) == s.charAt(i)) {
                        count++;

                        // Assuming the block ends here "aaaa"
                        dp[i][j] = Math.min(dp[i][j], getLength(count) + dp[end + 1][possible_del]);
                    } else {
                        // This character should be deleted to make the length min.
                        possible_del--;
                    }
                }
            }
        }

        return dp[0][k];
    }
    Time complexity: O(n * k * (n - i))

The outer loop runs in O(n) time.
The inner loop runs in O(k) time, where k is the maximum allowed deletions.
Inside the loops, the operations are constant time.
Space complexity:O(n * k) due to the 2D array dp used for memoization
 */
/*
Run-length encoding is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".
Notice that in this problem, we are not adding '1' after single characters.
Given a string s and an integer k. You need to delete at most k characters from s such that the run-length encoded version of s has minimum length.
Find the minimum length of the run-length encoded version of s after deleting at most k characters.
Example 1:
Input: s = "aaabcccd", k = 2
Output: 4
Explanation: Compressing s without deleting anything will give us "a3bc3d" of length 6. Deleting any of the characters 'a' or 'c' would at most decrease the length of the compressed string to 5, for instance delete 2 'a' then we will have s = "abcccd" which compressed is abc3d. Therefore, the optimal way is to delete 'b' and 'd', then the compressed version of s will be "a3c3" of length 4.
Example 2:
Input: s = "aabbaa", k = 2
Output: 2
Explanation: If we delete both 'b' characters, the resulting compressed string would be "a4" of length 2.
Example 3:
Input: s = "aaaaaaaaaaa", k = 0
Output: 3
Explanation: Since k is zero, we cannot delete anything. The compressed string is "a11" of length 3.
Constraints:
1 <= s.length <= 100
0 <= k <= s.length
s contains only lowercase English letters.
 */