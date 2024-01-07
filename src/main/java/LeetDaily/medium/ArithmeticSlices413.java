package LeetDaily.medium;

import java.util.Arrays;

public class ArithmeticSlices413 {
    private static int cnt = 0;
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        System.out.println(numberOfArithmeticSlices1(nums));
    }

//    DP with constant space; time: O(n), space: O(1)
    public static int numberOfArithmeticSlices(int[] nums) {
        final int n = nums.length;
        int dp = 0, sum = 0;
        for(int i = 2 ; i < n ; i++) {
            if(nums[i] - nums[i-1] == nums[i-1] - nums[i-2]) {
                dp = 1 + dp;
                sum += dp;
            } else {
                dp = 0;
            }
        }
        return sum;
    }

//    DP; time: O(n), space: O(n)
    public static int numberOfArithmeticSlices1(int[] nums) {
        final int n = nums.length;
        int sum = 0;
        int[] dp = new int[n];
        for(int i = 2; i < n ; i++) {
            if(nums[i] - nums[i-1] == nums[i-1] - nums[i-2]) {
                dp[i] = 1 + dp[i-1];
                sum += dp[i];
            }
        }
        return sum;
    }

//  recursive; time: O(n), space: O(n)
    public static int numberOfArithmeticSlices2(int[] nums) {
        final int n = nums.length;
        slices(nums,  n-1);
        return cnt;
    }
    private static int slices(int[] nums, int i) {
        if(i < 2) return 0;
        int sum = 0;
        if(nums[i] - nums[i-1] == nums[i-1] - nums[i-2]) {
            sum = 1 + slices(nums, i-1);
            cnt += sum;
        } else {
            slices(nums, i-1);
        }
        return sum;
    }

//    brute force; time: O(n^2), space: O(1)
    public static int numberOfArithmeticSlicesN(int[] nums) {
        final int n = nums.length;
        int count = 0;
        for(int i = 0 ; i < n - 2; i++) {
            int diff = nums[i+1] - nums[i];
            for(int j = i + 2; j < n ; j++) {
                if(nums[j] - nums[j-1] == diff) {
                    count++;
                } else break;
            }
        }
        return count;
    }

//    brute force; same as above but from reverse direction
    public static int numberOfArithmeticSlicesN1(int[] nums) {
        final int n = nums.length;
        int count = 0;
        for(int i = n - 2 ; i >= 0; i--) {
            int diff = nums[i+1] - nums[i];
            for(int j = i + 1; j < n - 1 ; j++) {
                if(nums[j+1] - nums[j] == diff) {
                    count++;
                } else break;
            }
        }
        return count;
    }
}

/*
An integer array is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
Given an integer array nums, return the number of arithmetic subarrays of nums.
A subarray is a contiguous subsequence of the array.
Example 1:
Input: nums = [1,2,3,4]
Output: 3
Explanation: We have 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4] and [1,2,3,4] itself.
Example 2:
Input: nums = [1]
Output: 0
Constraints:
1 <= nums.length <= 5000
-1000 <= nums[i] <= 1000
 */