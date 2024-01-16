package Top150.graph;

public class NumOfIslands200 {
    public static void main(String[] args) {
        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(numIslands(grid));
    }

//    dfs; time: O(m*n), space: O(m*n)
    public static int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int numOfIslands = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int r = 0 ; r < m ; r++) {
            for(int c = 0 ; c < n ; c++) {
                if(grid[r][c] == '1') {
                    numOfIslands++;
                    dfs(grid, r, c);
                }
            }
        }
        return numOfIslands;
    }
    private static void dfs(char[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;
        if(r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == '0') {
            return;
        }
//        mark visited
        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
}

/*
Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
Example 1:
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
 */