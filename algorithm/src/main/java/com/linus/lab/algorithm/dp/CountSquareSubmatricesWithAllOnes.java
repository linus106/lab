package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/7
 * @Description TODO
 */
public class CountSquareSubmatricesWithAllOnes {

    public int countSquares(int[][] matrix) {
        int row = matrix.length, column = matrix[0].length;
        int[][] dp = new int[row + 1][column + 1];
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int dpI = i + 1, dpJ = j + 1;
                if (matrix[i][j] != 0) {//0
                    dp[dpI][dpJ] = Math.min(Math.min(dp[dpI - 1][dpJ], dp[dpI][dpJ - 1]), dp[dpI - 1][dpJ - 1])+ 1;
                    res += dp[dpI][dpJ];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {


        int[][] input = new int[][]{new int[]{0,1,1,1}, new int[]{1,1,1,1}, new int[]{0,1,1,1}};
        CountSquareSubmatricesWithAllOnes o = new CountSquareSubmatricesWithAllOnes();
        int res = o.countSquares(input);
        System.out.println(res);

    }
}
