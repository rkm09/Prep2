package LeetDaily.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArithmeticSlicesII446 {
    public static void main(String[] args) {
        int[] nums = {1,1,2,3,4,5};
//        int[] nums1 = {1,1};
        System.out.println(numberOfArithmeticSlices(nums));
    }

//    DP; time: O(n^2), space: O(n)
    public static int numberOfArithmeticSlices(int[] nums) {
        final int n = nums.length;
        Map<Integer, Integer>[] cnt = new Map[n];
        int ans = 0;
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
                cnt[i].put(diff, sum + origin + 1);
                ans += sum;
            }
        }
        return ans;
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

We can define weak arithmetic subsequences as follows:
Weak arithmetic subsequences are subsequences that consist of at least two elements and if the difference between any two consecutive elements is the same.
There are two properties of weak arithmetic subsequences that are very useful:
- For any pair i, j (i != j), A[i] and A[j] can always form a weak arithmetic subsequence.
- If we can append a new element to a weak arithmetic subsequence and keep it arithmetic, then the new subsequence must be an arithmetic subsequence.

The second property is quite trivial, because the only difference between arithmetic subsequences and weak arithmetic subsequences is their length.

Thus, we can change the state representations accordingly:

f[i][d] denotes the number of weak arithmetic subsequences that ends with A[i] and its common difference is d.

Now the state transitions are quite straightforward:

for all j < i, f[i][A[i] - A[j]] += (f[j][A[i] - A[j]] + 1).
how can we get the number of arithmetic subsequences that are not weak?
Based on property two, when we are appending new elements to existing weak arithmetic subsequences, we are forming arithmetic subsequences. So the first part, f[j][A[i] - A[j]] is the number of new formed arithmetic subsequences, and can be added to the answer.

 */