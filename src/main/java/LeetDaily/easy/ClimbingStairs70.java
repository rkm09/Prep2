package LeetDaily.easy;

public class ClimbingStairs70 {
    public static void main(String[] args) {
        System.out.println(climbStairs3(5));
    }

//    constant space dp; fibonacci; time: O(n), space: O(1)
    public static int climbStairs2(int n) {
        if(n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for(int i = 3; i <= n ; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

//    DP; time: O(n), space: O(n)
    public static int climbStairs3(int n) {
        if(n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3 ; i <= n ; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

//    recursion with memoization; time: O(n), space: O(n)
    public static int climbStairs4(int n) {
        int[] memo = new int[n + 1];
        return climbStairs(n, 0, memo);
    }
    private static int climbStairs(int n, int i, int[] memo) {
        if(i > n) {
            return 0;
        }
        if(i == n) {
            return 1;
        }
        if(memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climbStairs(n, i + 1, memo) + climbStairs(n , i + 2, memo);
        return memo[i];
    }

//    brute force recursion [TLE]; time: O(2^n), space: O(n)
    public static int climbStairsX(int n) {
        return climbStairsX(n, 0);
    }
    private static int climbStairsX(int n, int i) {
        if(i > n) {
            return 0;
        }
        if(i == n) {
            return 1;
        }
        return climbStairsX(n, i + 1) + climbStairsX(n , i + 2);
    }

}

/*
You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
Example 1:
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:
Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
Constraints:
1 <= n <= 45
 */
