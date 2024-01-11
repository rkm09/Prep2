package Top150.matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix54 {
    public static void main(String[] args) {
//        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        System.out.println(spiralOrder2(matrix));
    }


//    update boundaries; time: O(m*n), space: O(1)
    public static List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        List<Integer> spiral = new ArrayList<>();
        int left = 0, right = cols - 1;
        int up = 0, down = rows - 1;
        while(spiral.size() < rows * cols) {
//            left to right >
            for(int col = left ; col <= right ; col++) {
                spiral.add(matrix[up][col]);
            }
//            top to bottom v
            for(int row = up + 1 ; row <= down ; row++) {
                spiral.add(matrix[row][right]);
            }
//            right to left <
//            make sure we are not repeating
            if(up != down) {
                for(int col = right - 1 ; col >= left ; col--) {
                    spiral.add(matrix[down][col]);
                }
            }
//            bottom to top ^
//            make sure we are not repeating
            if(left != right) {
                for(int row = down - 1 ; row > up ; row--) {
                    spiral.add(matrix[row][left]);
                }
            }
            left++; right--;
            up++; down--;
        }
        return spiral;
    }

    //    visited; time: O(m*n), space: O(1), if can't mutate input: O(m*n)
    public static List<Integer> spiralOrder2(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int VISITED = 101;
        List<Integer> spiral = new ArrayList<>();
//        directions: right, down, left, up
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        int currentDirection = 0;
        int changeDirection = 0;
        int row = 0, col = 0;
        spiral.add(matrix[0][0]);
        matrix[0][0] = VISITED;
        while(changeDirection < 2) {
            while(row + directions[currentDirection][0] >= 0 &&
            row + directions[currentDirection][0] < rows &&
            col + directions[currentDirection][1] >= 0 &&
            col + directions[currentDirection][1] < cols &&
            matrix[row + directions[currentDirection][0]][col + directions[currentDirection][1]] != VISITED)
            {
                changeDirection = 0;
                row = row + directions[currentDirection][0];
                col = col + directions[currentDirection][1];
                spiral.add(matrix[row][col]);
                matrix[row][col] = VISITED;
            }
            currentDirection = (currentDirection + 1) % 4;
            changeDirection++;
        }
        return spiral;
    }

//    [def]; time: O(m*n), space: O(1)
    public static List<Integer> spiralOrder1(int[][] matrix) {
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