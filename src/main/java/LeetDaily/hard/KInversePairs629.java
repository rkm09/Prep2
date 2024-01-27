package LeetDaily.hard;

public class KInversePairs629 {
    private static final int M = (int) 1e9 + 7;
    public static void main(String[] args) {
        System.out.println(kInversePairs(4, 2));
    }
    public static int kInversePairs(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 0 ; j <= k ; j++) {
                if(j == 0) {
                    dp[i][j] = 1;
                } else {
                    for(int p = 0 ; p <= Math.min(j, i - 1) ; p++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - p]) % M;
                    }
                }
            }
        }
        return dp[n][k];
    }
}

/*
For an integer array nums, an inverse pair is a pair of integers [i, j] where 0 <= i < j < nums.length and nums[i] > nums[j].
Given two integers n and k, return the number of different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs. Since the answer can be huge, return it modulo 109 + 7;
Example 1:
Input: n = 3, k = 0
Output: 1
Explanation: Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pairs.
Example 2:
Input: n = 3, k = 1
Output: 2
Explanation: The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
Constraints:
1 <= n <= 1000
0 <= k <= 1000
 */