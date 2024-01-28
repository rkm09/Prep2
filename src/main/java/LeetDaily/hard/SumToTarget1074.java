package LeetDaily.hard;

public class SumToTarget1074 {
    private static int res = 0;
    public static void main(String[] args) {
        int[][] matrix = {{0,1,0},{1,1,1},{0,1,0}};
        System.out.println(numSubmatrixSumTarget(matrix, 0));
    }
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 0;
        for(int i = 0 ; i < m ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(matrix[i][j] == target) {
                    ans++;
                }
                if(matrix[i][j] + matrix[i + 1][j + 1] == target){

                }
            }
        }
        return ans;
    }

    public static int numSubmatrixSumTarget1(int[][] matrix, int target) {
        helper(matrix, target, 0, 0);
        return res;
    }
    private static int helper(int[][] matrix, int t, int i, int j) {
        if(i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) {
            return -1;
        }
        if(matrix[i][j] == t) {
            return res++;
        }
        res = helper(matrix, t, i + 1, j + 1) + helper(matrix, t, i - 1, j - 1);
        return res;
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