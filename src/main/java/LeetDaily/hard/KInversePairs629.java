package LeetDaily.hard;

public class KInversePairs629 {
    private static final int M = (int) 1e9 + 7;
    private static Integer[][] memo;
    public static void main(String[] args) {
        System.out.println(kInversePairs1(4, 2));
    }

//    recursion with memo; time: O(n.k.min(n,k)), space: O(n.k)
    public static int kInversePairs1(int n, int k) {
        memo = new Integer[1001][1001];
        return kInverse(n, k);
    }
    private static int kInverse(int n, int k) {
        if(n == 0)
            return 0;
        if(k == 0)
            return 1;
        if(memo[n][k] != null) {
            return memo[n][k];
        }
        int inv = 0;
        for(int i = 0 ; i <= Math.min(n - 1, k) ; i++) {
            inv = (inv + kInverse(n - 1, k - i)) % M;
            memo[n][k] = inv;
        }
        return inv;
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