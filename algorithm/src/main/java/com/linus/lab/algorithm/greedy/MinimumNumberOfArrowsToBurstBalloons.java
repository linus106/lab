package com.linus.lab.algorithm.greedy;

import java.util.Arrays;

/**
 * @Author wangxiangyu
 * @Date 2020/11/23 11:14
 * @Description TODO
 */
public class MinimumNumberOfArrowsToBurstBalloons {

    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, (a, b) -> {
            if (a[0] < b[0]) {
                return -1;
            } else if (a[0] > b[0]) {
                return 1;
            } else {
                return 0;
            }
        });
        int s = points[0][0],e = points[0][1];
        int n = 1;
        for (int i = 1; i < points.length; i++) {
            int[] point = points[i];
            if (point[0] <= e) {
                s = Math.max(s, point[0]);
                e = Math.min(e, point[1]);
            } else {
                s = point[0];
                e = point[1];
                n++;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        MinimumNumberOfArrowsToBurstBalloons o = new MinimumNumberOfArrowsToBurstBalloons();
        int res = o.findMinArrowShots(new int[][]{new int[]{-2147483646,-2147483645},new int[]{2147483646,2147483647}});

        System.out.println(res);
    }
}
