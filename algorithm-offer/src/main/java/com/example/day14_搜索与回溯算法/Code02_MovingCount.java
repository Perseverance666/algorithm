package com.example.day14_搜索与回溯算法;

/**
 * @Date: 2023/3/15 14:49
 * 面试题13. 机器人的运动范围
 */
public class Code02_MovingCount {
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return dfs(visited,m,n,0,0,k);
    }
    public static int dfs(boolean[][] visited,int m,int n,int i,int j,int k){
        if(i < 0 || i >= m || j < 0 || j >= n || visited[i][j]){
            return 0;
        }
        int shiI = i / 10;
        int geI = i % 10;
        int shiJ = j / 10;
        int geJ = j % 10;
        if(shiI + geI + shiJ + geJ > k){
            return 0;
        }else {
            visited[i][j] = true;
            int up = dfs(visited, m, n, i-1, j, k);
            int down = dfs(visited, m, n, i+1, j, k);
            int left = dfs(visited, m, n, i, j-1, k);
            int right =dfs(visited, m, n, i, j+1, k);
            visited[i][j] = false;
            return 1 + up + down + left + right;
        }
    }
}
