package LeetDaily.hard;

import java.util.HashMap;
import java.util.Map;

public class SumToTarget1074 {
    private static int res = 0;
    public static void main(String[] args) {
        int[][] matrix = {{0,1,0},{1,1,1},{0,1,0}};
        System.out.println(numSubmatrixSumTarget(matrix, 0));
    }

//    prefix sum(cumulative sum); time: O(m^2n), space: O(mn)
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int count = 0, sum = 0;
//      2d  prefix sum; m + 1, n + 1 for extra zero padding
        int[][] ps = new int[m + 1][n + 1];
        for(int i = 1 ; i <= m ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                ps[i][j] = ps[i - 1][j] + ps[i][j - 1] - ps[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        int currSum;
//        fix 2 rows, create 1d from 2d
        for(int r1 = 1 ; r1 <= m ; r1++) {
            for(int r2 = r1 ; r2 <= m ; r2++) {
                map.clear();
                map.put(0, 1);
//                compute 1D prefix sum for all matrices using [r1..r2] rows
                for(int c = 1 ; c <= n ; c++) {
//                    excluding the row above row 1, only focussing on r1 & r2; moving l to r
                    currSum = ps[r2][c] - ps[r1 - 1][c];
                    if(map.containsKey(currSum - target)) {
                        count += map.get(currSum - target);
                    }
                    map.put(currSum, map.getOrDefault(currSum, 0) + 1);
                }
            }
        }

        return count;
    }

//    prefix sum(cumulative sum); time: O(mn^2), space: O(mn)
    public static int numSubmatrixSumTarget1(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int count = 0;
//        create 2d prefix sum
        int[][] ps = new int[m + 1][n + 1];
        for(int i = 1 ; i <= m ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                ps[i][j] = ps[i - 1][j] + ps[i][j - 1] - ps[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
//        map(sum of i, number of occurrences of sum of i)
        Map<Integer, Integer> map = new HashMap<>();
        int currSum;
        for(int c1 = 1 ; c1 <= n ; c1++) {
            for(int c2 = c1 ; c2 <= n ; c2++) {
                map.clear();
                map.put(0, 1);
//                compute 1D prefix sum for all matrices using [c1..c2] cols
                for(int r = 1 ; r <= m ; r++) {
                    currSum = ps[r][c2] - ps[r][c1 - 1];
                    count += map.getOrDefault(currSum - target, 0);
                    map.put(currSum, map.getOrDefault(currSum, 0) + 1);
                }
            }
        }
        return count;
    }
}

/*
Given a matrix and a target, return the number of non-empty submatrices that sum to target.
A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.
Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
Output: 4
Explanation: The four 1x1 submatrices that only contain 0.
Example 2:
Input: matrix = [[1,-1],[-1,1]], target = 0
Output: 5
Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
Example 3:
Input: matrix = [[904]], target = 0
Output: 0
Constraints:
1 <= matrix.length <= 100
1 <= matrix[0].length <= 100
-1000 <= matrix[i] <= 1000
-10^8 <= target <= 10^8
 */