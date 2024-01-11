package Top150.matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix54 {
    public static void main(String[] args) {
//        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        System.out.println(spiralOrder(matrix));
    }

//    [def];
    public static List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int cnt = m*n;
        List<Integer> res = new ArrayList<>();
        int row = 0, col = 0, i = 0;
        boolean up = false, down = false, left = false, right = true;
        while(row < m && col < n) {
            if(right) {
                res.add(matrix[row][col++]);
                if(col == n - i) {
                    right = false;
                    down = true;
                    col--;
                    row++;
                }
                if(res.size() == cnt) return res;
            }
            if(down) {
                res.add(matrix[row++][col]);
                if(row == m - i) {
                    down = false;
                    left  = true;
                    row--;
                    col--;
                }
                if(res.size() == cnt) return res;
            }
            if(left) {
                res.add(matrix[row][col--]);
                if(col < i) {
                    left = false;
                    up  = true;
                    col++;
                    row--;
                }
                if(res.size() == cnt) return res;
            }
            if(up) {
                res.add(matrix[row--][col]);
                if(row < i + 1) {
                    up = false;
                    right  = true;
                    row++;
                    col++;
                    i++;
                }
                if(res.size() == cnt) return res;
            }
        }
        return res;
    }
}

/*
Given an m x n matrix, return all elements of the matrix in spiral order.
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
 */