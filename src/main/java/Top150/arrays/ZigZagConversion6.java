package Top150.arrays;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion6 {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING"; int numRows = 3;
        System.out.println(convert(s, numRows));
    }

//    String traversal; time: O(n), space: O(1)
    public static String convert(String s, int numRows) {
        if(numRows == 1) return s;
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int charsInSection = 2 * (numRows - 1);
        for(int currRow = 0 ; currRow < numRows ; currRow++) {
            int index = currRow;
            while(index < n) {
               sb.append(s.charAt(index));
               if(currRow != 0  && currRow != numRows - 1) {
                   int charsInBetween = charsInSection - 2 * currRow;
                   int secondIndex = index + charsInBetween;
                   if(secondIndex < n) {
                       sb.append(s.charAt(secondIndex));
                   }
               }
               index += charsInSection;
            }
        }
        return sb.toString();
    }
}

/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:
string convert(string s, int numRows);
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
Example 3:
Input: s = "A", numRows = 1
Output: "A"

Constraints:
1 <= s.length <= 1000
s consists of English letters (lower-case and upper-case), ',' and '.'.
1 <= numRows <= 1000

 */