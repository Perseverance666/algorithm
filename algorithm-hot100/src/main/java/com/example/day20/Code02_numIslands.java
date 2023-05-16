package com.example.day20;

/**
 * @Date: 2023/5/15 20:30
 * 200. 岛屿数量
 * <p>
 * https://leetcode.cn/problems/number-of-islands/
 */
public class Code02_numIslands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    //岛屿数+1，并把这个岛屿中的1全部变成0
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }
    //将这个岛屿中的1全部变成0
    public static void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
            //越界或者为0，直接返回
            return;
        }
        //将grid[i][j]变成0，并将其上下左右的1也变成0
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
