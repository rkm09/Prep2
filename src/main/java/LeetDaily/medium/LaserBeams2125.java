package LeetDaily.medium;

public class LaserBeams2125 {
    public static void main(String[] args) {
        String[] bank = {"011001","000000","010100","001000"};
        System.out.println(numberOfBeams(bank));
    }

//    time: O(m*n), space: O(1) where m is number of strings, n is the length of each string [faster]
    public static int numberOfBeams(String[] bank) {
        int prev = 0, ans = 0;
        for(String s : bank) {
            int count = 0;
            for(char c : s.toCharArray()) {
                if(c == '1') {
                    count++;
                }
            }
            if(count > 0) {
                ans += count * prev;
                prev = count;
            }
        }
        return ans;
    }

//    [def]; time: O(m*n); space: O(n)
    public static int numberOfBeams1(String[] bank) {
        int m = bank.length;
        int[] counts = new int[m];
        int idx = 0;
        for(String s : bank) {
            for(char c : s.toCharArray()) {
                if(c == '1') {
                    counts[idx]++;
                }
            }
            idx++;
        }
        int res = 0;
        for(int i = 0 ; i < m ; i++) {
            if(counts[i] == 0) continue;
            for(int j = i + 1; j < m ; j++) {
                if(counts[j] == 0) continue;
                res += counts[i] * counts[j];
                break;
            }
        }
        return res;
    }
}

/*
Anti-theft security devices are activated inside a bank. You are given a 0-indexed binary string array bank representing the floor plan of the bank, which is an m x n 2D matrix. bank[i] represents the ith row, consisting of '0's and '1's. '0' means the cell is empty, while'1' means the cell has a security device.
There is one laser beam between any two security devices if both conditions are met:
The two devices are located on two different rows: r1 and r2, where r1 < r2.
For each row i where r1 < i < r2, there are no security devices in the ith row.
Laser beams are independent, i.e., one beam does not interfere nor join with another.
Return the total number of laser beams in the bank.
Input: bank = ["011001","000000","010100","001000"]
Output: 8
Explanation: Between each of the following device pairs, there is one beam. In total, there are 8 beams:
 * bank[0][1] -- bank[2][1]
 * bank[0][1] -- bank[2][3]
 * bank[0][2] -- bank[2][1]
 * bank[0][2] -- bank[2][3]
 * bank[0][5] -- bank[2][1]
 * bank[0][5] -- bank[2][3]
 * bank[2][1] -- bank[3][2]
 * bank[2][3] -- bank[3][2]
Note that there is no beam between any device on the 0th row with any on the 3rd row.
This is because the 2nd row contains security devices, which breaks the second condition.
Input: bank = ["000","111","000"]
Output: 0
Explanation: There does not exist two devices located on two different rows.
Constraints:
m == bank.length
n == bank[i].length
1 <= m, n <= 500
bank[i][j] is either '0' or '1'.
 */