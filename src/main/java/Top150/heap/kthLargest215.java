package Top150.heap;


import java.util.*;

public class kthLargest215 {
    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4}; int k = 2;
        System.out.println(findKthLargest1(nums, k));
    }

//  Counting sort [frequency counter]; time: O(m+n), space: O(m) [fast]
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

//    quick select; time: O(n) [worst case O(n^2) but highly unlikely], space: O(n)
//    ps: in general quick select algo is used for kth smallest, to reverse this...we fill "Greater than" into "left"..
    public static int findKthLargest1(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        for(int num : nums) {
            list.add(num);
        }
        return quickSelect(list, k);
    }
    private static int quickSelect(List<Integer> nums, int k) {
        int pivotIndex = new Random().nextInt(nums.size());
        int pivot = nums.get(pivotIndex);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();
        for(int num : nums) {
            if(num > pivot) {
                left.add(num);
            } else if(num < pivot) {
                right.add(num);
            } else {
                mid.add(num);
            }
        }
        if(k <= left.size()) {
            return quickSelect(left, k);
        }
        if(left.size() + mid.size() < k) {
            return quickSelect(right, k - left.size() - mid.size());
        }
        return pivot;
    }

//    min heap using priority queue; time: O(nlogk), space: O(k) where k is the heap size [slow]
    public static int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int num : nums) {
            heap.add(num);
            if(heap.size() > k) {
                heap.remove();
            }
        }
        return heap.peek();
    }


//    time: O(nlogn), space: O(logn)
    public static int findKthLargest3(int[] nums, int k) {
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