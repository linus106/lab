package com.linus.lab.algorithm.sort;

import java.util.Arrays;
import java.util.List;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/17
 */
public class MatrixCellsInDistanceOrder {


    private boolean inRange(int x, int y, int R, int C) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }


    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int maxDis = Math.max(r0, R-1-r0) + Math.max(c0, C-1-c0);

        int res[][] = new int[R*C][];
        res[0] = new int[]{r0,c0};
        int n = 1;
        for (int dis = 1; dis <= maxDis; dis++) {
            for (int xDis = 0; xDis <= dis; xDis++) {
                List<int[]> options = null;
                if (xDis == 0) {
                    options = Arrays.asList(new int[]{r0, c0 + (dis - xDis)},
                            new int[]{r0, c0 - (dis - xDis)});
                } else if (xDis == dis) {
                    options = Arrays.asList(new int[]{r0 + xDis, c0},
                            new int[]{r0 - xDis, c0});
                } else {
                    options = Arrays.asList(new int[]{r0 + xDis, c0 + (dis - xDis)},
                            new int[]{r0 - xDis, c0 + (dis - xDis)},
                            new int[]{r0 + xDis, c0 - (dis - xDis)},
                            new int[]{r0 - xDis, c0 - (dis - xDis)});
                }

                for (int[] option : options) {
                    if (option[0] >= 0 && option[0] < R && option[1] >= 0 && option[1] < C) {
                        res[n++] = option;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MatrixCellsInDistanceOrder o = new MatrixCellsInDistanceOrder();
        int[][] res = o.allCellsDistOrder(2,2,0,1);
        for (int[] re : res) {
            System.out.println(re[0] + ":" + re[1]);
        }

    }
}
