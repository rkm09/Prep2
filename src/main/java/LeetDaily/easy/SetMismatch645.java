package LeetDaily.easy;

import java.util.Arrays;

public class SetMismatch645 {
    public static void main(String[] args) {
        int[] nums = {3,2,2};
        System.out.println(Arrays.toString(findErrorNums(nums)));
    }
    public static int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] freq = new int[n+1];
        for(int i = 0 ; i < n ; i++) {
            freq[nums[i]]++;
        }
        int missing = 0;
        int dup = 0;
        for (int i = 1; i <= n ; i++) {
            if(freq[i] == 0) {
                missing = i;
            }
            else if(freq[i] > 1) {
                dup = i;
            }
        }
        return new int[]{dup, missing};
    }
}

/*
You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.
You are given an integer array nums representing the data status of this set after the error.
Find the number that occurs twice and the number that is missing and return them in the form of an array.
Example 1:
Input: nums = [1,2,2,4]
Output: [2,3]
Example 2:
Input: nums = [1,1]
Output: [1,2]
Constraints:
2 <= nums.length <= 104
1 <= nums[i] <= 104

 */