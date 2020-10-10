package com.linus.lab.algorithm.line_sweep;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author wangxiangyu
 * @Date 2020/9/30 15:29
 * @Description TODO
 */
public class RemoveCoveredIntervals {

    public int removeCoveredIntervals(int[][] intervals) {
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

    public int removeCoveredIntervalsOpt(int[][] intervals) {

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
            }
        });

        int count = 0;
        int maxEnd = 0;
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            if (end > maxEnd) {
                count++;
                maxEnd = end;
            }
        }
        return count;
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
