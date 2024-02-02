package LeetDaily.medium;

import java.util.ArrayList;
import java.util.List;

public class SequentialDigits1291 {
    public static void main(String[] args) {
        System.out.println(sequentialDigits(100, 300));
    }

//    sliding window (faster); time: O(1), space: O(1) [time: since low is 2 and high is 9, hence the loops can execute no more than  8*8 = 64 times]
    public static List<Integer> sequentialDigits(int low, int high) {
        List<Integer> nums = new ArrayList<>();
        String sample = "123456789";
        int lowLength = String.valueOf(low).length();
        int highLength = String.valueOf(high).length();
        int n = 10;
        for(int length = lowLength ; length < highLength + 1 ; length++){
            for(int start = 0; start < n - length ; start++) {
                int num = Integer.parseInt(sample.substring(start, start + length));
                if(num >= low && num <= high)
                    nums.add(num);
            }
        }
        return nums;
    }

//    pre computation; time: O(1), space: O(1) [slower]
//    there are 36 integers in between. 9-2+1=8, 9-3+1=7...
    public List<Integer> sequentialDigits1(int low, int high) {
        Seq s = new Seq();
        List<Integer> output = new ArrayList<>();
        for(int num : s.nums) {
            if(num >= low && num <= high)
                output.add(num);
        }
        return output;
    }

    class Seq {
        List<Integer> nums = new ArrayList<>();
        Seq() {
            String sample = "123456789";
            int n = 10;
            for(int length = 2 ; length < 10 ; length++) {
                for(int start = 0 ; start < n - length ; start++) {
                    int num = Integer.parseInt(sample.substring(start, start + length));
                    nums.add(num);
                }
            }
        }
    }

}

/*
An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
Example 1:
Input: low = 100, high = 300
Output: [123,234]
Example 2:
Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]
Constraints:
10 <= low <= high <= 10^9
 */