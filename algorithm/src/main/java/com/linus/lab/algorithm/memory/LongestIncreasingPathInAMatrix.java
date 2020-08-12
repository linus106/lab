package com.linus.lab.algorithm.memory;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author wangxiangyu
 * @Date 2020/8/12 16:09
 * @Description TODO
 */
public class LongestIncreasingPathInAMatrix {

    int[][] diffs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int rowNum, columnNum;
    int[][] matrix;
    int[][] memo;

    /**
     * 深度优先遍历 + 记忆化
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        rowNum = matrix.length;
        columnNum = rowNum != 0 ? matrix[0].length : 0;
        this.matrix = matrix;
        this.memo = new int[rowNum][columnNum];


        int maxLenth = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                maxLenth = Math.max(maxLenth, dfs(i,j));
            }
        }
        return maxLenth;
    }



    public int dfs(int row, int column) {

        if (memo[row][column] != 0) {
            return memo[row][column];
        }
        int max = 1;
        for (int[] diff : diffs) {
            int nextRow = row + diff[0];
            int nextColumn = column + diff[1];

            if (nextRow < rowNum && nextRow >=0 && nextColumn < columnNum && nextColumn >=0) {
                if (matrix[nextRow][nextColumn] > matrix[row][column]) {
                    max = Math.max(max, 1 + dfs(nextRow, nextColumn));;
                }
            }
        }

        return memo[row][column] = max;
    }


    public static void main(String[] args) {
        int result = new LongestIncreasingPathInAMatrix().longestIncreasingPath(new int[][]{
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        });

//        int result = new LongestIncreasingPathInAMatrix().longestIncreasingPath(new int[][]{});
        System.out.println(result);
    }
}
