package Top150.heap;

import GenDS.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class kthLargest215 {
    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4}; int k = 2;
        System.out.println(findKthLargest(nums, k));
    }

//    frequency counter; time: O(n), space: O(n)
    public static int findKthLargest(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < nums.length ; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        int n = max - min + 1;
        int[] freq = new int[n];
        for(int num : nums) {
            freq[num - min]++;
        }
        int remaining = k;
        for(int i = n - 1 ; i >= 0 ; i--) {
            remaining -= freq[i];
            if(remaining <= 0) return min + i;
        }
        return -1;
    }

//    time: O(nlogn), space: O(logn)
    public static int findKthLargestX(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

}

/*   1 2 2 3 3 4 5 5 6 | 1 2 3 4 5 6
Given an integer array nums and an integer k, return the kth largest element in the array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.
Can you solve it without sorting?
Example 1:
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Example 2:
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
Constraints:
1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104
 */