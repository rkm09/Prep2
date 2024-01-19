package LeetDaily.medium;

public class MinFallingPathSum931 {
    public static void main(String[] args) {
        int[][] matrix = {{2,1,3},{6,5,4},{7,8,9}};
        System.out.println(minFallingPathSum2(matrix));
    }

//    top down dp with memoization (dfs approach); time: O(n^2), space: O(n)
    public static int minFallingPathSum2(int[][] matrix) {
        int minPathSum = Integer.MAX_VALUE;
        Integer[][] memo = new Integer[matrix.length][matrix[0].length];
        for(int col = 0 ; col < matrix.length ; col++) {
            minPathSum = Math.min(findMinPathSum(matrix, 0, col, memo), minPathSum);
        }
        return minPathSum;
    }
    private static int findMinPathSum(int[][] matrix, int row, int col, Integer[][] memo) {
//        base case
        if(col < 0 || col == matrix[0].length) {
            return Integer.MAX_VALUE;
        }
//        reached the end; stop and return;
        if(row == matrix.length - 1) {
            return matrix[row][col];
        }
//        return from memo
        if(memo[row][col] != null) {
            return memo[row][col];
        }

        int left = findMinPathSum(matrix, row + 1, col - 1, memo);
        int middle = findMinPathSum(matrix, row + 1, col, memo);
        int right = findMinPathSum(matrix, row + 1, col + 1, memo);

        memo[row][col] = Math.min(left, Math.min(middle, right)) + matrix[row][col];
        return memo[row][col];
    }

//    brute force dfs; TLE; time: O(N.3^N), space: O(N)
    public static int minFallingPathSumX(int[][] matrix) {
        int minPathSum = Integer.MAX_VALUE;
        for(int col = 0 ; col < matrix.length ; col++) {
            minPathSum = Math.min(minPathSum, findMinFallingPathX(matrix, 0, col));
        }
        return minPathSum;
    }
    private static int findMinFallingPathX(int[][] matrix, int row, int col) {
        if(col < 0 || col == matrix[0].length) {
            return Integer.MAX_VALUE;
        }
        if(row == matrix.length - 1) {
            return matrix[row][col];
        }
        int left = findMinFallingPathX(matrix, row + 1, col - 1);
        int middle = findMinFallingPathX(matrix, row + 1, col);
        int right = findMinFallingPathX(matrix, row + 1, col + 1);

        return Math.min(left, Math.min(middle, right)) + matrix[row][col];
    }
}

/*
Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
Output: 13
Input: matrix = [[-19,57],[-40,-5]]
Output: -59
Explanation: The falling path with a minimum sum is shown.
Constraints:
n == matrix.length == matrix[i].length
1 <= n <= 100
-100 <= matrix[i][j] <= 100
 */