package LeetDaily.medium;


import java.util.Arrays;
import java.util.Stack;

public class SumSubArrayMins907 {
    public static void main(String[] args) {
        int[] arr = {3,1,2,1,4};
        System.out.println(sumSubarrayMins(arr));
    }

//   Monotonic stack [used to calculate the range where an element is the min]; time: O(n), space: O(n)
//   Monotonic stacks are used to calculate the previous smaller element and the next smaller element in linear time complexity; (two types: increasing and decreasing)
    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int MOD = (int) 1e9 + 7;
        long minSubSum = 0;
        Stack<Integer> stack = new Stack<>();
//        till n as a marker for popping out remaining elements if any from the stack
        for(int i = 0 ; i <= n ; i++) {
            while(!stack.isEmpty() && (i == n || arr[i] <= arr[stack.peek()])) {
                int mid = stack.pop();
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
                int rightBoundary = i;
                long count = ((mid - leftBoundary) * (rightBoundary - mid)) % MOD;
                minSubSum += (arr[mid] * count) % MOD;
                minSubSum %= MOD;
            }
            stack.push(i);
        }
        return (int) minSubSum;
    }

//    brute force [def]; TLE :| ; time: O(n^2), space: O(1)
    public static int sumSubarrayMinsX(int[] arr) {
        int n = arr.length;
        final int MOD = (int) 1e9 + 7;
        long sum = 0;
        for(int i = 0 ; i < n ; i++){
            sum += arr[i];
            int min = arr[i];
            for(int j = i + 1 ; j < n ; j++) {
                min = Math.min(min, arr[j]);
                sum = (sum + min) % MOD;
            }
        }
        return (int) sum % MOD;
    }
}

/*
Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.
Example 1:
Input: arr = [3,1,2,4]
Output: 17
Explanation:
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
Example 2:
Input: arr = [11,81,94,43,3]
Output: 444
Constraints:
1 <= arr.length <= 3 * 104
1 <= arr[i] <= 3 * 104
 */