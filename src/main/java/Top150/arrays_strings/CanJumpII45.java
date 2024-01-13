package Top150.arrays_strings;

import java.util.Arrays;

public class CanJumpII45 {
    static int[] memo;
    public static void main(String[] args) {
        int[] nums  = {2,3,0,1,4};
        System.out.println(jump(nums));
    }

//    greedy; time: O(n), space: O(1); fastest;
//    given constraint: that input has a valid jump
    public static int jump(int[] nums) {
        int n = nums.length, ans = 0;
        int currEnd = 0, currFar = 0;
        for(int i = 0 ; i < n - 1 ; i++) {
           currFar = Math.max(currFar, i + nums[i]);
           if(i == currEnd) {
               ans++;
               currEnd = currFar;
           }
        }
        return ans;
    }

//    [def]; bottom up dp; time : O(n^2), space: O(n)
    public static int jump1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n-1] = 0;
        for(int i = n - 2 ; i >= 0 ; i--) {
            int furthest = Math.min(i + nums[i], n-1);
            for(int j = i + 1 ; j <= furthest ; j++) {
                if(dp[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[j] + 1, dp[i]);
                }
            }
        }
        return dp[0];
    }

//    [def]; top down dp; time: O(n^2), space: O(n)
    public static int jump2(int[] nums) {
        int n = nums.length;
        memo = new int[n];
        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[n-1] = 0;
        return canJumpToPosition(nums, 0);
    }
    private static int canJumpToPosition(int[] nums, int position) {
        if(memo[position] != Integer.MAX_VALUE) {
            return memo[position];
        }
        int furthest = Math.min(position + nums[position], nums.length - 1);
        for(int nextPosition = position + 1 ; nextPosition <= furthest ; nextPosition++) {
                int hops = canJumpToPosition(nums, nextPosition);
                if(hops != Integer.MAX_VALUE) {
                    memo[position] = Math.min(hops + 1, memo[position]);
                }
            }
        return memo[position];
    }
}


/*
You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
Example 1:
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:
Input: nums = [2,3,0,1,4]
Output: 2
Constraints:
1 <= nums.length <= 104
0 <= nums[i] <= 1000
It's guaranteed that you can reach nums[n - 1].
 */