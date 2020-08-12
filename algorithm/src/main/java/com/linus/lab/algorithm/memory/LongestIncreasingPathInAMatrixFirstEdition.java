package com.linus.lab.algorithm.memory;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author wangxiangyu
 * @Date 2020/8/12 16:09
 * @Description TODO
 */
public class LongestIncreasingPathInAMatrixFirstEdition {

    int rowNum = 0;
    int columnNum = 0;
    int[][] matrix;
    int[][] increasingPathLenth;
    int[][] decreasingPathLenth;

    public int longestIncreasingPath(int[][] matrix) {
        this.rowNum = matrix.length;
        this.columnNum = rowNum != 0 ? matrix[0].length : 0;
        this.matrix = matrix;
        this.increasingPathLenth = new int[rowNum][columnNum];
        this.decreasingPathLenth = new int[rowNum][columnNum];

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                increasingPathLenth[i][j] = -1;
                decreasingPathLenth[i][j] = -1;
            }
        }

        int maxLenth = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                maxLenth = Math.max(maxLenth,
                        lookForIncreasingOrDecreasingPath(i, j, true) + lookForIncreasingOrDecreasingPath(i, j, false) + 1);
            }
        }
        return maxLenth;
    }

    public int lookForIncreasingOrDecreasingPath(int row, int column, boolean isIncresing) {

        if (isIncresing && increasingPathLenth[row][column] != -1) {
            return increasingPathLenth[row][column];
        } else if (!isIncresing && decreasingPathLenth[row][column] != -1) {
            return decreasingPathLenth[row][column];
        }

        int value = matrix[row][column];
        int maxLenth = Arrays.asList(
                new Pair<Integer, Integer>(row - 1, column),
                new Pair<Integer, Integer>(row + 1, column),
                new Pair<Integer, Integer>(row, column - 1),
                new Pair<Integer, Integer>(row, column + 1)
        ).stream()
                .filter(pair -> pair.getKey() >= 0
                        && pair.getKey() < rowNum
                        && pair.getValue() >= 0
                        && pair.getValue() < columnNum)
                .filter(pair -> matrix[pair.getKey()][pair.getValue()] != value)
                .filter(pair -> matrix[pair.getKey()][pair.getValue()] > value ^ !isIncresing)
                .map(pair -> 1 + lookForIncreasingOrDecreasingPath(pair.getKey(), pair.getValue(), isIncresing))
                .collect(Collectors.maxBy(Integer::compareTo))
                .orElse(0);

        if (isIncresing) {
            increasingPathLenth[row][column] = maxLenth;
        } else {
            decreasingPathLenth[row][column] = maxLenth;
        }
        return maxLenth;
    }


    public static void main(String[] args) {
//        int result = new LongestIncreasingPathInAMatrix().longestIncreasingPath(new int[][]{
//                {9, 9, 4},
//                {6, 6, 8},
//                {2, 1, 1}
//        });

        int result = new LongestIncreasingPathInAMatrixFirstEdition().longestIncreasingPath(new int[][]{});
        System.out.println(result);
    }
}
