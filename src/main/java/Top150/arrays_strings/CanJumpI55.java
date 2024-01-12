package Top150.arrays_strings;

import java.util.Arrays;

public class CanJumpI55 {
    private static Index[] memo;
    public static void main(String[] args) {
//        int[] nums = {2,3,1,1,4};
        int[] nums = {2,5,0,0};
        System.out.println(canJump(nums));
    }

//    greedy(like a constant space bottom up dp); time: O(n), space: O(1); fastest;
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        int lastPos = n - 1;
        for(int i = lastPos - 1; i >= 0 ; i--) {
            if(i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

//    refined bottom up dp; time: O(n^2), space: O(n)
    public static boolean canJump2(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[n-1] = true;
        for(int i = n - 2 ; i >= 0 ; i--) {
            int furthest = Math.min(i + nums[i], n - 1);
            for(int j = i + 1 ; j <= furthest ; j++) {
                if(dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    enum Index {
        GOOD, BAD, UNKNOWN
    }

    //   another using enum; bottom up dp; time: O(n^2), space: O(n)
    public static boolean canJump3(int[] nums) {
        int n = nums.length;
        Index[] dp = new Index[n];
        for(int i = 0 ; i < n ; i++) {
            dp[i] = Index.UNKNOWN;
        }
        dp[n-1] = Index.GOOD;
        for(int i = n - 2; i >= 0 ; i--) {
            int furthest = Math.min(i + nums[i], n-1);
            for(int j = i + 1 ; j <= furthest ; j++) {
                if(dp[j] == Index.GOOD) {
                    dp[i] = Index.GOOD;
                    break;
                }
            }
        }
        return dp[0] == Index.GOOD;
    }

//    top down dp; recursive; time: O(n^2), space: O(n)
    public static boolean canJump4(int[] nums) {
        int n = nums.length;
        memo = new Index[n];
        for(int i = 0 ; i < n ; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[n-1] = Index.GOOD;
        return canJumpFromPosition(nums, 0);
    }
    private static boolean canJumpFromPosition(int[] nums, int position) {
        if(memo[position] != Index.UNKNOWN) {
            return memo[position] == Index.GOOD ? true : false;
        }
        int furthest = Math.min(position + nums[position], nums.length - 1);
        for(int nextPosition = position + 1 ; nextPosition <= furthest ; nextPosition++) {
            if(canJumpFromPosition(nums, nextPosition)) {
                memo[position] = Index.GOOD;
                return true;
            }
        }
        memo[position] = Index.BAD;
        return false;
    }


    //    [def]; bottom up dp; time: O(n^2), space: O(n); better than top down
    public static boolean canJump1(int[] nums) {
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
