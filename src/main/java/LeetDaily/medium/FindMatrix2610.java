package LeetDaily.medium;

import GenDS.Pair;

import java.util.*;

public class FindMatrix2610 {
    public static void main(String[] args) {
        int[] nums = {1,3,4,1,2,3,1};
        List<List<Integer>> res = findMatrix1(nums);
        for(List<Integer> li : res) {
            System.out.println(li);
        }
    }

//    frequency counter; time: O(n), space: O(n)
//    ps: could build the counter this way, 'coz of the constraint given
    public static List<List<Integer>> findMatrix1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int[] freq = new int[nums.length + 1];
        for(int num : nums) {
            if(freq[num] >= res.size()) {
                res.add(new ArrayList<>());
            }
            res.get(freq[num]).add(num);
            freq[num]++;
        }
        return res;
    }

//    [def]; hashmap; time: O(n^2), space: O(n)
    public static List<List<Integer>> findMatrix(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        int maxCount = 0;
        for(int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
            maxCount = Math.max(maxCount, counts.get(num));
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int r = 0 ; r < maxCount ; r++) {
            List<Integer> row = new ArrayList<>();
            for(int key : counts.keySet()) {
                int count = counts.get(key);
                if(!row.contains(key) && count != 0) {
                    row.add(key);
                    counts.put(key, --count);
                }
            }
            res.add(row);
        }
        return  res;
    }
}

/*
You are given an integer array nums. You need to create a 2D array from nums satisfying the following conditions:
The 2D array should contain only the elements of the array nums.
Each row in the 2D array contains distinct integers.
The number of rows in the 2D array should be minimal.
Return the resulting array. If there are multiple answers, return any of them.
Note that the 2D array can have a different number of elements on each row.
Example 1:
Input: nums = [1,3,4,1,2,3,1]
Output: [[1,3,4,2],[1,3],[1]]
Explanation: We can create a 2D array that contains the following rows:
- 1,3,4,2
- 1,3
- 1
All elements of nums were used, and each row of the 2D array contains distinct integers, so it is a valid answer.
It can be shown that we cannot have less than 3 rows in a valid array.
Example 2:
Input: nums = [1,2,3,4]
Output: [[4,3,2,1]]
Explanation: All elements of the array are distinct, so we can keep all of them in the first row of the 2D array.
Constraints:
1 <= nums.length <= 200
1 <= nums[i] <= nums.length
 */