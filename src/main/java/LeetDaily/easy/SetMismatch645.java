package LeetDaily.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SetMismatch645 {
    public static void main(String[] args) {
        int[] nums = {1,3,3,4};
        System.out.println(Arrays.toString(findErrorNums1(nums)));
    }

//   [def]; counting sort; time: O(n), space: O(n); fastest
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

//    hashmap; time: O(n), space: O(n)
    public static int[] findErrorNums1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int dup = 0, missing = 0;
        for(int i = 1 ; i <= nums.length ; i++) {
            if(map.containsKey(i)) {
                if(map.get(i) == 2) {
                    dup = i;
                }
            } else {
                missing = i;
            }
        }
        return new int[] {dup, missing};
    }

//    sorting; time: O(nlogn), space: O(logn)
    public static int[] findErrorNums2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
//        case: [2,2]..initialize missing with 1
        int dup = -1, missing = 1;
        for(int i = 1 ; i < n ; i++) {
            if(nums[i] == nums[i - 1]) {
                dup = nums[i];
            }
            if(nums[i] > nums[i - 1] + 1) {
                missing = nums[i - 1] + 1;
            }
        }
//        special case like [1,1]
        return new int[] {dup, nums[n - 1] != n ? n : missing};
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