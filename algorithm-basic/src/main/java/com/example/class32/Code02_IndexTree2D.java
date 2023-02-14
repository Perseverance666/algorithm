package com.example.class32;

/**
 * @Date: 2023/2/14 14:51
 * 二维IndexTree实现
 */
public class Code02_IndexTree2D {
    public static class IndexTree2D {
        private int[][] tree;
        private int[][] nums;
        private int N;
        private int M;

        public IndexTree2D(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            N = matrix.length;
            M = matrix[0].length;
            tree = new int[N + 1][M + 1];
            nums = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    update(i, j, matrix[i][j]);
                }
            }
        }

        private int sum(int row, int col) {
            int sum = 0;
            for (int i = row + 1; i > 0; i -= i & (-i)) {
                for (int j = col + 1; j > 0; j -= j & (-j)) {
                    sum += tree[i][j];
                }
            }
            return sum;
        }

        public void update(int row, int col, int val) {
            if (N == 0 || M == 0) {
                return;
            }
            int add = val - nums[row][col]; //update改成add的代码形式。更新成val相当于加add
            nums[row][col] = val;
            for (int i = row + 1; i <= N; i += i & (-i)) {
                for (int j = col + 1; j <= M; j += j & (-j)) {
                    tree[i][j] += add;
                }
            }
        }
        //求[row1,col1] ~ [row2,col2]这个区间的累加和
        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (N == 0 || M == 0) {
                return 0;
            }
            return sum(row2, col2) + sum(row1 - 1, col1 - 1) - sum(row1 - 1, col2) - sum(row2, col1 - 1);
        }
    }

}
