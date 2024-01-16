package Top150.graph;

import java.util.LinkedList;
import java.util.Queue;

public class NumOfIslands200 {
    public static void main(String[] args) {
        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        NumOfIslands200 islands = new NumOfIslands200();
        System.out.println(islands.numIslands(grid));
    }

    //    dfs; time: O(m*n), space: O(m*n); faster
    public  int numIslands1(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int numOfIslands = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int r = 0 ; r < m ; r++) {
            for(int c = 0 ; c < n ; c++) {
//                root node
                if(grid[r][c] == '1') {
                    numOfIslands++;
                    dfs(grid, r, c);
                }
            }
        }
        return numOfIslands;
    }

    private  void dfs(char[][] grid, int r, int c) {
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


//    bfs; time: O(m*n), space: O(m*n)
    public  int numIslands2(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int numOfIslands = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int r = 0 ; r < m ; r++) {
            for (int c = 0; c < n; c++) {
                if(grid[r][c] == '1') {
                    numOfIslands++;
                    grid[r][c] = '0';
                    Queue<Integer> neighbours = new LinkedList();
                    neighbours.offer(r * n + c);
                    while(!neighbours.isEmpty()) {
                        int idx = neighbours.poll();
                        int row = idx / n;
                        int col = idx % n;
                        if(row - 1 >= 0 && grid[row - 1][col] == '1') {
                            neighbours.add((row - 1) * n + col);
                            grid[row-1][col] = '0';
                        }
                        if(row + 1 < m && grid[row + 1][col] == '1') {
                            neighbours.add((row + 1) * n + col);
                            grid[row + 1][col] = '0';
                        }
                        if(col - 1 >= 0 && grid[row][col - 1] == '1') {
                            neighbours.add(row * n + (col - 1));
                            grid[row][col - 1] = '0';
                        }
                        if(col + 1 < n && grid[row][col + 1] == '1') {
                            neighbours.add(row * n + (col + 1));
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }
        return numOfIslands;
    }


//    Union find (disjoint set) data structure;
    class UnionFind {
//    count of connected components
        private int count;
        private int[] parent;
        private int[] rank;
        public UnionFind(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for(int r = 0 ; r < m ; r++) {
                for(int c = 0 ; c < n ; c++) {
                    if(grid[r][c] == '1') {
                        parent[r * n + c] = r * n + c;
                        count++;
                    }
                    rank[r * n + c] = 0;
                }
            }
        }

        //        path compression
        public int find(int i) {
            if(parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        //        union (merge)
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY) {
                if(rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if(rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }

    }

// time: O(m*n), space: O(m*n) [m rows, n cols]
//    Union find takes constant time, when both path compression and union by rank are implemented.
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        UnionFind uf = new UnionFind(grid);
        int m = grid.length;
        int n = grid[0].length;
        for(int r = 0 ; r < m ; r++) {
            for(int c = 0 ; c < n ; c++) {
                if(grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if(r - 1 >= 0 && grid[r - 1][c] == '1') {
                        uf.union(r * n + c, (r - 1) * n + c);
                    }
                    if(r + 1 < m && grid[r + 1][c] == '1') {
                        uf.union(r * n + c, (r + 1) * n + c);
                    }
                    if(c - 1 >= 0 && grid[r][c - 1] == '1') {
                        uf.union(r * n + c, r * n + (c - 1));
                    }
                    if(c + 1 < n && grid[r][c + 1] == '1') {
                        uf.union(r * n + c, r * n + (c + 1));
                    }
                }
            }
        }
        return uf.getCount();
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