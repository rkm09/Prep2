package Top150.arrays;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion6 {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING"; int numRows = 3;
        System.out.println(convert(s, numRows));
    }
    public static String convert(String s, int numRows) {
        final int n = s.length();
        StringBuilder sb = new StringBuilder();
        char[][] grid = new char[numRows][n];
        char[] carr = s.toCharArray();
        int k = 0;
        for(int r = 0 ; r < numRows ; r++) {
            int c = r;
            while(c < n) {
                grid[r][c] = carr[c];
                c = c + numRows;
                k++;
            }
        }
        for(int r = 0 ; r < numRows ; r++) {
            for(int c = 0 ; c < k ; c++) {
                sb.append(grid[r][c]);
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