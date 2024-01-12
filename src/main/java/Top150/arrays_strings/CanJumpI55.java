package Top150.arrays_strings;

import java.util.Arrays;

public class CanJumpI55 {
    public static void main(String[] args) {
//        int[] nums = {2,3,1,1,4};
        int[] nums = {2,5,0,0};
        System.out.println(canJump(nums));
    }

//    [def]; dp
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[n-1] = true;
        for(int i = n - 2 ; i >= 0 ; i--) {
            int dist = n - 1 - i;
            if(nums[i] >= dist) {
                dp[i] = true;
            } else {
                int val = nums[i];
                while(val > 0) {
                    if(dp[i + val]) {
                        dp[i] = true;
                        break;
                    }
                    val--;
                }
            }
        }
        return dp[0];
    }
}

/*
You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
Return true if you can reach the last index, or false otherwise.
Example 1:
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
Constraints:
1 <= nums.length <= 104
0 <= nums[i] <= 105

 */
