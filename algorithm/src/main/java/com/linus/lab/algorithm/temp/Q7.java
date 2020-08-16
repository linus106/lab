package com.linus.lab.algorithm.temp;

import java.util.Arrays;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/16
 * https://leetcode-cn.com/problems/magnetic-force-between-two-balls/comments/
 */
public class Q7 {


    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int l = 1, r = position[position.length - 1] - position[0];//框定k值(最大距离)的范围
        while (l < r) {
            int mid = (r + l + 1) / 2;
            if (check(position, m, mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    public boolean check(int[] position, int m, int k) {//检查当前k是否满足能排出m个选项
        int last = -k;
        for (int val : position) {
            if (val - last >= k) {
                m--;
                last = val;
            }
        }
        return m <= 0;
    }


    public int maxDistanceBruteForce(int[] position, int m) {
        Arrays.sort(position);
        return doMaxDistance(position, 1, m - 1, 0);
    }

    public int doMaxDistance(int[] position, int i, int m, int last) {
        if (m <= 0) {
            return Integer.MAX_VALUE;
        }
        if (position.length - i < m) {
            return -1;
        }
        int max = 1;
        for (int j = i; j < position.length - m + 1; j++) {
            int planA = Math.min(position[i] - position[last], doMaxDistance(position, i + 1, m - 1, i));
            int planB = doMaxDistance(position, i + 1, m, last);
            max = Math.max(planA, planB);
        }
        return max;
    }

    public static void main(String[] args) {
//        int result = new Q7().maxDistance(new int []{5,4,3,2,1,1000000000}, 2);
//        int result = new Q7().maxDistance(new int []{1,2}, 2);
        int result = new Q7().maxDistance(new int[]{269826447, 974181916, 225871443, 189215924, 664652743, 592895362, 754562271, 335067223, 996014894, 510353008, 48640772, 228945137}, 3);
        System.out.println(result);

    }
}
