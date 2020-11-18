package com.linus.lab.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/17
 */
public class MatrixCellsInDistanceOrderBucket {


    private int dist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int maxDis = Math.max(r0, R - 1 - r0) + Math.max(c0, C - 1 - c0);
        List<List<int[]>> buckets = new ArrayList<>();
        for (int i = 0; i <= maxDis; i++) {
            buckets.add(new ArrayList<>());
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                buckets.get(dist(r0, c0, i, j)).add(new int[]{i, j});
            }
        }
        int res[][] = new int[R * C][];
        int i = 0;
        for (List<int[]> bucket : buckets) {
            for (int[] ele : bucket) {
                res[i++] = ele;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MatrixCellsInDistanceOrderBucket o = new MatrixCellsInDistanceOrderBucket();
        int[][] res = o.allCellsDistOrder(2, 2, 0, 1);
        for (int[] re : res) {
            System.out.println(re[0] + ":" + re[1]);
        }

    }
}
