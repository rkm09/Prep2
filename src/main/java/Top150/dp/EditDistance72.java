package Top150.dp;

import java.util.ArrayDeque;
import java.util.Arrays;

public class EditDistance72 {
    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(minDistance(word1, word2));
    }
    public static int minDistance(String word1, String word2) {
        int ans = Integer.MAX_VALUE, m = word1.length(), n = word2.length();
        int[] dp = new int[m];
        Arrays.fill(dp, Integer.MAX_VALUE);
//        int count = 0;
        for(int i = 0 ; i < m ; i++) {
            int count = 0;
            for(int j = 0 ; j < Math.min(m, n); j++) {
                char c1 = word1.charAt(i);
                char c2 = word2.charAt(i);
                if(c1 != c2) {
                    count++;
                }
                if(j + 1 == n) {
                    count += m - n;
                }
            }
            dp[i] = count;
        }
        System.out.println(Arrays.toString(dp));
        for(int k : dp) {
            ans = Math.min(ans, k);
        }
        return ans;
    }
}

/*
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
You have the following three operations permitted on a word:
Insert a character
Delete a character
Replace a character

Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
Constraints:
0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
 */