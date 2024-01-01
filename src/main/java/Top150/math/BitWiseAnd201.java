package Top150.math;

public class BitWiseAnd201 {
    public static void main(String[] args) {
        int left = 5, right = 7;
        System.out.println(rangeBitwiseAnd1(left, right));
    }

//    time: O(1), space: O(1); stop the loop when both left & right get reduced to a common prefix value
//    get the common prefix for numbers in the range & then append zeroes at the end [for the unequal part] for result;
    public static int rangeBitwiseAnd1(int left, int right) {
        int shift = 0;
        while(left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }
        return left << shift;
    }

//    b&k algo; time: O(1), space: O(1); also a similar approach to above, but slightly faster
//    turn off the rightmost 1 bit using [k & (k-1)] operation
//    locate the common prefix; everything different evaluates to 0
    public static int rangeBitwiseAnd(int left, int right) {
        while(left < right) {
            right = right & (right - 1);
        }
        return left & right;
    }

//    [def] trial with either TLE or out of memory for input 3 :|  tried approach similar to single number
    public static int rangeBitwiseAndX(int left, int right) {
        int rangeBitAnd = 0;
        int n = right - left + 1;
        int num = left;
        for(int shift = 0 ; shift < 32 ; shift++) {
            int bitAnd = (left >> shift) & 1;
            left++;
           for(int i = 1 ; i < n ; i++) {
                bitAnd &= (left >> shift) & 1;
            }
            rangeBitAnd |= (bitAnd << shift);
            left = num;
        }
        return rangeBitAnd;
    }
}

/*
Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.
Example 1:
Input: left = 5, right = 7
Output: 4
Example 2:
Input: left = 0, right = 0
Output: 0
Example 3:
Input: left = 1, right = 2147483647
Output: 0
Constraints:
0 <= left <= right <= 231 - 1
 */