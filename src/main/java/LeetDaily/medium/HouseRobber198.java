package LeetDaily.medium;

import java.util.Arrays;

public class HouseRobber198 {
    private static int[] memo;
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(rob(nums));
    }

//    Space optimized DP; time: O(n), space: O(1)
    public static int rob(int[] nums) {
        int n = nums.length;
        if(n == 0) {
            return 0;
        }
        int robNextPlusOne = 0;
        int robNext = nums[n - 1];
        for(int i = n - 2; i >= 0 ; i--) {
            int current = Math.max(robNext, nums[i] + robNextPlusOne);
            robNextPlusOne = robNext;
            robNext = current;
        }
        return robNext;
    }

//    Bottom up DP; time: O(n), space: O(n)
    public static int rob1(int[] nums) {
        int n = nums.length;
        if(n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
//        base case initialization
        dp[n] = 0;
        dp[n - 1] = nums[n - 1];

        for(int i = n - 2 ; i >= 0 ; i--) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        return dp[0];
    }

//    recursion with memo; time: O(n), space: O(n)
    public static int rob2(int[] nums) {
        memo = new int[100];
        Arrays.fill(memo, -1);
        return robFrom(0, nums);
    }
    private static int robFrom(int i, int[] nums) {
        if(i >= nums.length) {
            return 0;
        }
        if(memo[i] > -1) {
            return memo[i];
        }
        int ans = Math.max(robFrom(i + 1, nums), nums[i] + robFrom(i + 2, nums));
        memo[i] = ans;
        return ans;
    }
}

/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 400
 */