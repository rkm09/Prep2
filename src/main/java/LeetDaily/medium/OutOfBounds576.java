package LeetDaily.medium;

import java.util.Arrays;

public class OutOfBounds576 {
    private static final int M = 1000000007;
    private static int[][][] memo;
    public static void main(String[] args) {
        System.out.println(findPaths(2,2,2,0,0));
    }

//    bottom up dp; time: O(n), space: O(n)
    public static int findPaths1(int m, int n, int maxMove, int startRow, int startColumn) {
        return 0;
    }

//    recursion with memoization; time: O(mnN), space: O(mnN) where N is the maxMove
    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        memo = new int[m][n][maxMove + 1];
        for(int[][] var : memo) for(int[] v : var) Arrays.fill(v, -1);
        return findAllPaths(m, n, maxMove, startRow, startColumn);
    }
    private static int findAllPaths(int m, int n, int maxMove, int i, int j) {
        if(i == m || j == n || i < 0 || j < 0) {
            return 1;
        }
        if(maxMove == 0) {
            return 0;
        }
        if(memo[i][j][maxMove] != -1) {
            return memo[i][j][maxMove];
        }
        memo[i][j][maxMove] = (
                (findAllPaths(m, n, maxMove - 1, i + 1, j) +
                findAllPaths(m, n, maxMove - 1, i - 1, j)) % M +
                (findAllPaths(m, n, maxMove - 1, i, j + 1) +
                        findAllPaths(m, n, maxMove - 1, i, j - 1)) % M
        ) % M;
        return memo[i][j][maxMove];
    }

//    [TLE] brute force recursion; time: O(4^n), space: O(n)
    public static int findPathsX(int m, int n, int maxMove, int startRow, int startColumn) {
        if (startRow == m || startColumn == n || startRow < 0 || startColumn < 0) {
            return 1;
        }
        if (maxMove == 0) {
            return 0;
        }
        return findPathsX(m, n, maxMove - 1, startRow + 1, startColumn) +
                findPathsX(m, n, maxMove - 1, startRow - 1, startColumn) +
                findPathsX(m, n, maxMove - 1, startRow, startColumn + 1) +
                findPathsX(m, n, maxMove - 1, startRow, startColumn - 1);
    }
}

/*
There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary). You can apply at most maxMove moves to the ball.
Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary. Since the answer can be very large, return it modulo 109 + 7.
Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
Output: 6
Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
Output: 12
Constraints:
1 <= m, n <= 50
0 <= maxMove <= 50
0 <= startRow < m
0 <= startColumn < n
 */