package LeetDaily.medium;

import java.util.HashMap;
import java.util.Map;

public class SubArraySum560 {
    public static void main(String[] args) {
        int[] nums = {1,-1,0};
        System.out.println(subarraySum(nums, 0));
    }

//    hashmap; time: O(n), space: O(n)
//    based on cumulative sum approach; map stores sum & it's count;
//    if the cumulative sum up to two indices is at diff k: sum[i] - sum[j] = k => the sum of elements lying between them is k.
    public static int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> sumMap = new HashMap();
        sumMap.put(0, 1);
        for(int i = 0 ; i < nums.length ; i++) {
            sum += nums[i];
            if(sumMap.containsKey(sum - k)) {
                count += sumMap.get(sum - k);
            }
            sumMap.put(sum, sumMap.getOrDefault(sum , 0) + 1);
        }
        return count;
    }

//    without extra space; time: O(n^2), space: O(1)
    public static int subarraySum1(int[] nums, int k) {
        int count = 0, n = nums.length;
        for(int start = 0 ; start < n ; start++) {
            int sum = 0;
            for(int end = start ; end < n ; end++) {
                sum += nums[end];
                if(sum == k) count++;
            }
        }
        return count;
    }

    //    [def]; without extra space; time: O(n^2), space: O(1)
    public static int subarraySum2(int[] nums, int k) {
        int count = 0, n = nums.length;
        for(int start = 0 ; start < n ; start++) {
            int sum = nums[start];
            if(sum == k) {
                count++;
            }
            for(int end = start + 1 ; end < n ; end++) {
                sum += nums[end];
                if(sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

//    [TLE] brute force; time: O(n^3), space
    public static int subarraySumX(int[] nums, int k) {
        int count = 0, n = nums.length;
        for(int start = 0 ; start < n ; start++) {
            for(int end = start + 1 ; end <= n ; end++) {
                int sum = 0;
                for(int i = start ; i < end ; i++) {
                    sum += nums[i];
                }
                if(sum == k) count++;
            }
        }
        return count;
    }


}
/*
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
A subarray is a contiguous non-empty sequence of elements within an array.
Example 1:
Input: nums = [1,1,1], k = 2
Output: 2
Example 2:
Input: nums = [1,2,3], k = 3
Output: 2
Constraints:
1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107

 */
