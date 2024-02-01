package Top150.arrays_strings;

import java.util.Arrays;

public class ProductExceptItself538 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf1(nums)));
    }

//    constant space [using result array as auxiliary]; time: O(n), space: O(1)
public static int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    res[0] = 1;
//    left array
    for(int i = 1 ; i < n ; i++) {
        res[i] = res[i - 1] * nums[i - 1];
    }
    int right = 1;
    for(int i = n - 1 ; i >= 0 ; i--) {
        res[i] *= right;
        right *= nums[i];
    }

    return res;
}

    public static int[] productExceptSelf1(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[n - 1] = 1;
//        right array
        for(int i = n - 2 ; i >= 0 ; i--) {
            res[i] = res[i + 1] * nums[i + 1];
        }
        int left = 1;
        for(int i = 0 ; i < n ; i++) {
            res[i] *= left;
            left *= nums[i];
        }

        return res;
    }

//    time: O(n), space: O(n)
    public static int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1; right[n - 1] = 1;
        for(int i = 1 ; i < n ; i++) {
           left[i] = left[i - 1] * nums[i - 1];
        }
        for(int i = n - 2 ; i >= 0 ; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }
        for(int i = 0 ; i < n ; i++) {
            res[i] = left[i] * right[i];
        }

        return res;
    }
}

/*
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
You must write an algorithm that runs in O(n) time and without using the division operation.
Example 1:
Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
Constraints:
2 <= nums.length <= 105
-30 <= nums[i] <= 30
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 */