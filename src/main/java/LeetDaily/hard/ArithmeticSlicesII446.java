package LeetDaily.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArithmeticSlicesII446 {
    public static void main(String[] args) {
        int[] nums = {1,1,2,3,4,5};
        int[] nums1 = {1,1};
        System.out.println(Arrays.toString(nums1));
        System.out.println(numberOfArithmeticSlices(nums1));
    }
    public static int numberOfArithmeticSlices(int[] nums) {
        final int n = nums.length;
        long ans = 0;
        Map<Integer, Integer>[] cnt = new Map[n];
        for(int i = 0 ; i < n ; i++) {
            cnt[i] = new HashMap<>(i);
            for(int j = 0 ; j < i ; j++) {
                long delta = (long)nums[i] - (long)nums[j];
                if(delta < Integer.MIN_VALUE || delta > Integer.MAX_VALUE) {
                    continue;
                }
                int diff = (int) delta;
                int sum = cnt[j].getOrDefault(diff, 0);
                int origin = cnt[i].getOrDefault(diff, 0);
                cnt[i].put(diff, origin + sum + 1);
                ans += sum;
            }
        }
        System.out.println(Arrays.toString(cnt));
        return (int) ans;
    }
}

/*
Given an integer array nums, return the number of all the arithmetic subsequences of nums.
A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.
A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.
For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
The test cases are generated so that the answer fits in 32-bit integer.
Example 1:
Input: nums = [2,4,6,8,10]
Output: 7
Explanation: All arithmetic subsequence slices are:
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]
Example 2:
Input: nums = [7,7,7,7,7]
Output: 16
Explanation: Any subsequence of this array is arithmetic.
Constraints:
1  <= nums.length <= 1000
-231 <= nums[i] <= 231 - 1
 */