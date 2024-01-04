package LeetDaily.medium;

import java.util.HashMap;
import java.util.Map;

public class MinOpArrayEmpty2870 {
    public static void main(String[] args) {
        int[] nums = {14,12,14,14,12,14,14,12,12,12,12,14,14,12,14,14,14,12,12};
        System.out.println(minOperations1(nums));
    }

//    hashmap & deduction; time: O(n), space: O(n)
//    1:(ex)-1 || 2:1, 3:1 | 4:2, 5:2, 6:2 | 7:3, 8:3, 9:3 | 10:4, 11:4, 12:4 (patterns of 3)
    public static int minOperations1(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for(int k : nums) {
            counts.put(k, counts.getOrDefault(k, 0) + 1);
        }
        int res = 0;
        for(int k : counts.values()) {
            if(k == 1) {
                return -1;
            }
            res += Math.ceil((double) k / 3);
        }
        return res;
    }

//    [def];...slow
    public static int minOperations(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int k : nums) {
            min = Math.min(min, k);
            max = Math.max(max, k);
        }
        int[] freq = new int[max - min + 1];
        for(int k : nums) {
            freq[k - min]++;
        }
        int res = 0;
        for(int k : freq) {
            if(k > 4) {
                res += (k - 4) / 3 + 2;
            } else if (k % 3 == 0) {
                res += k / 3;
            } else if (k % 2 == 0){
                res += k / 2;
            } else {
                return -1;
            }
        }
        return res;
    }
}

/*
You are given a 0-indexed array nums consisting of positive integers.
There are two types of operations that you can apply on the array any number of times:
Choose two elements with equal values and delete them from the array.
Choose three elements with equal values and delete them from the array.
Return the minimum number of operations required to make the array empty, or -1 if it is not possible.
Example 1:
Input: nums = [2,3,3,2,2,4,2,3,4]
Output: 4
Explanation: We can apply the following operations to make the array empty:
- Apply the first operation on the elements at indices 0 and 3. The resulting array is nums = [3,3,2,4,2,3,4].
- Apply the first operation on the elements at indices 2 and 4. The resulting array is nums = [3,3,4,3,4].
- Apply the second operation on the elements at indices 0, 1, and 3. The resulting array is nums = [4,4].
- Apply the first operation on the elements at indices 0 and 1. The resulting array is nums = [].
It can be shown that we cannot make the array empty in less than 4 operations.
Example 2:
Input: nums = [2,1,2,2,3,3]
Output: -1
Explanation: It is impossible to empty the array.
Constraints:
2 <= nums.length <= 105
1 <= nums[i] <= 106
 */
