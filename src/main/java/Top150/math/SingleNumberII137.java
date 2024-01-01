package Top150.math;

import java.util.*;

public class SingleNumberII137 {
    public static void main(String[] args) {
        int[] nums = {2,2,2,3,1,1,4,1,4,4};
        System.out.println(singleNumber3(nums));
    }

//  Bit manipulation; (add bit-by-bit for all bits and append)
//    time: O(n), space: O(1)
    public static int singleNumber3(int[] nums) {
        int loner = 0;
        for(int shift = 0 ; shift < 32 ; shift++) {
            int bitSum = 0;
            for(int num : nums) {
                bitSum += (num >> shift) & 1;
            }
            int lonerBit = bitSum % 3;
            loner |= (lonerBit << shift) ;
        }
        return loner;
    }

//    [def] hashmap; time: O(n), space: O(n)
    public static int singleNumber(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++) {
            counts.put(nums[i], counts.getOrDefault(nums[i], 0) + 1);
        }
        for(int count : counts.keySet()) {
            if(counts.get(count) % 3 != 0) return count;
        }
        return -1;
    }

//    sorting; time: O(nlogn), space: O(logn)
    public static int singleNumber2(int[] nums) {
        Arrays.sort(nums);
        final int n = nums.length;
        for(int i = 0 ; i < n - 1; i += 3) {
            if(nums[i] == nums[i+1]) continue;
            else return nums[i];
        }
        return nums[n - 1];
    }

    //    Math; time: O(n), space: O(n); not that optimal
    public static int singleNumber1(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        Long sum = 0L;
        Long setSum = 0L;
        for(int num : nums) {
            numSet.add(num);
            sum += num;
        }
        for(int num : numSet) {
            setSum += num;
        }

        return (int) ((3 * setSum - sum) / 2);
    }
}

/*
Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
You must implement a solution with a linear runtime complexity and use only constant extra space.
Example 1:
Input: nums = [2,2,3,2]
Output: 3
Example 2:
Input: nums = [0,1,0,1,0,1,99]
Output: 99
Constraints:
1 <= nums.length <= 3 * 104
-231 <= nums[i] <= 231 - 1
Each element in nums appears exactly three times except for one element which appears once.

Bit Manipulation:

-- (A XOR B) = (A + B) MOD 2 [Modulo addition 2] [bit by bit]
-- Other properties of XOR:
    - A XOR 0 = A
    - A XOR A = 0
    - A XOR B XOR A = B [Get diff number]
        - XOR can be used to swap numbers too
        - A = A XOR B ::: This will mix A and B
        - B = A XOR B ::: This will make B equal to A = A
        - A = A XOR B ::: This will make A now equal to B = B
-- XOR is a Modulo 2 operation
-- In this example, we need a Modulo 3 operation;
-- If we add all integers Modulo 3, then we will be left out with the integer which appears once.
-- Adding all numbers and taking modulo is not the solution, instead addition of ith bit of all numbers will give
   the ith bit of the loner;
-- Getting the 0th bit of a number : num & 1
     -- to get the ith bit: (num >> shift) & 1
        (right shift will bring the ith bit to 0th position)
     -- bitwise OR to shift back the loner bit at the right position
 */