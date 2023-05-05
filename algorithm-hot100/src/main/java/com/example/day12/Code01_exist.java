package com.example.day12;

/**
 * @Date: 2023/5/3 21:04
 * 79. 单词搜索
 * <p>
 * https://leetcode.cn/problems/word-search/
 */
public class Code01_exist {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        char[] str = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, str, visited, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    //(i,j)代表当前元素在矩阵board的位置，k表示当前目标字符在word中的索引
    public static boolean dfs(char[][] board, char[] word, boolean[][] visited, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || word[k] != board[i][j] || visited[i][j]) {
            //当前位置越界，当前字符与word[k]字符不匹配，当前字符访问过，统统返回false
            return false;
        }
        if (k == word.length - 1) {
            //讨论到最后一个字符了，并且word[k] == board[i][j]，则匹配到了word
            return true;
        }
        visited[i][j] = true;//标记当前字符已经访问过了
        boolean up = dfs(board, word, visited, i - 1, j, k + 1);
        boolean down = dfs(board, word, visited, i + 1, j, k + 1);
        boolean left = dfs(board, word, visited, i, j - 1, k + 1);
        boolean right = dfs(board, word, visited, i, j + 1, k + 1);
        visited[i][j] = false; //注意要还原现场!!
        return up || down || left || right;
    }

}
