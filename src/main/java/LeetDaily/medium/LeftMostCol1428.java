package LeetDaily.medium;

import java.util.ArrayList;
import java.util.List;

public class LeftMostCol1428 {
    public static void main(String[] args) {
        int row = 3, col = 4;
        BinaryMatrix bin = new BinaryMatrix(row,col);
        int[][] matrix = {{0,0,0,1},{0,0,1,1},{0,1,1,1}};
        bin.setMatrix(matrix);
        System.out.println(leftMostColumnWithOne(bin));
    }

//    approach: start top right, move only left and down ;time: O(n+m), space: O(1); n rows, m cols
    public static int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);
        int currentRow = 0, currentCol = cols - 1;
        while(currentRow < rows && currentCol >= 0) {
            if(binaryMatrix.get(currentRow, currentCol) == 0) {
                currentRow++;
            } else {
                currentCol--;
            }
        }
        return currentCol == cols - 1 ? -1 : currentCol + 1;
    }

//    binary search; time: O(nlogn), space: O(1)
    public static int leftMostColumnWithOne1(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);
        int smallestIndex = cols;
        for(int row = 0 ; row < rows ; row++) {
            int low = 0;
            int high = cols - 1;
            while(low < high) {
                int mid = low + (high - low) / 2;
                if(binaryMatrix.get(row, mid) == 0) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            if(binaryMatrix.get(row, low) == 1) {
                smallestIndex = Math.min(smallestIndex, low);
            }
        }
        return smallestIndex == cols ? -1 : smallestIndex  ;
    }

//  [def];  Too many calls; TLE
    public static int leftMostColumnWithOneX(BinaryMatrix binaryMatrix) {
        List<Integer> dim = binaryMatrix.dimensions();
        int row = dim.get(0);
        int col = dim.get(1);
        for(int c = 0 ; c < col ; c++) {
            for(int r = 0 ; r < row ; r++) {
                if(binaryMatrix.get(r, c) == 1) {
                    return c;
                }
            }
        }
        return -1;
    }
}

class BinaryMatrix {
    int row;
    int col;
    int[][] matrix;
    BinaryMatrix(int row, int col) {
        this.row = row;
        this.col = col;
        matrix = new int[row][col];
    }
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
    public int get(int row, int col) {
        return matrix[row][col];
    }
    public List<Integer> dimensions() {
        List<Integer> dim =  new ArrayList<Integer>();
        dim.add(row); dim.add(col);
        return dim;
    }
}
/*
A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.
Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If such an index does not exist, return -1.
You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:
BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows x cols.
Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.
Input: mat = [[0,0],[1,1]]
Output: 0
Input: mat = [[0,0],[0,1]]
Output: 1
Input: mat = [[0,0],[0,0]]
Output: -1

Constraints:
rows == mat.length
cols == mat[i].length
1 <= rows, cols <= 100
mat[i][j] is either 0 or 1.
 */
