package com.linus.lab.algorithm.line_sweep;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author wangxiangyu
 * @Date 2020/9/30 15:29
 * @Description TODO
 */
public class RemoveCoveredIntervals {

    public int removeCoveredIntervals(int[][] intervals) {

        Set<int[]> covered = new HashSet<>();

        int deleted = 0;

        for (int i = 0; i < intervals.length; i++) {
            for (int j = 0; j < intervals.length; j++) {
                if (i != j) {
                    if (intervals[j][0] <= intervals[i][0] && intervals[j][1] >= intervals[i][1]) {
                        deleted++;
                        break;
                    }
                }
            }
        }
        return intervals.length - deleted;

    }

    public static void main(String[] args) {
        int result = new RemoveCoveredIntervals().removeCoveredIntervals(new int[][]{
                new int[]{1,4},
                new int[]{3,6},
                new int[]{2,8}
        });
        System.out.println(result);
    }
}
