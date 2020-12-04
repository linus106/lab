package com.linus.lab.algorithm.dp;

import java.lang.reflect.Array;

/**
 * @Author wangxiangyu
 * @Date 2020/12/4 15:30
 * @Description https://leetcode-cn.com/problems/matrix-block-sum
 * 二维数组前缀和
 */
public class MatrixBlockSum {

    public int[][] matrixBlockSum(int[][] mat, int K) {
        int r = mat.length;
        int c = mat[0].length;
        int[][] prefix = new int[r + 1][c + 1];// +1 for easy coding
        int[][] res = new int[r][c];

        for (int i = 1; i <= r; i++) {//构建前缀二维数组
            for (int j = 1; j <= c; j++) {
                prefix[i][j] = mat[i - 1][j - 1] + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int iMin = Math.max(0, i - K);
                int iMax = Math.min(r - 1, i + K);

                int jMin = Math.max(0, j - K);
                int jMax = Math.min(c - 1, j + K);
                res[i][j] = prefix[iMax + 1][jMax + 1]
                        - prefix[iMax + 1][jMin] - prefix[iMin][jMax + 1] + prefix[iMin][jMin];
            }
        }
        return res;
    }

    public static void main(String[] args) {

        MatrixBlockSum o = new MatrixBlockSum();
        int[][] res = o.matrixBlockSum(new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 9}
        }, 1);

        for (int[] re : res) {
            for (int i : re) {
                System.out.println(i);
            }
        }

    }
}
