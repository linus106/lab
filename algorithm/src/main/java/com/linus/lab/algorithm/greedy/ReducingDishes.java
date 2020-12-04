package com.linus.lab.algorithm.greedy;

import java.util.Arrays;

/**
 * @Author wangxiangyu
 * @Date 2020/12/4 10:16
 * @Description TODO
 */
public class ReducingDishes {


    /**
     * -2 -1 0 2 5
     * |
     * gap
     */
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int gap = 0;
        while (gap < satisfaction.length && satisfaction[gap] < 0) {
            gap++;
        }

        int res = 0;
        int add = 0;
        for (int j = gap; j < satisfaction.length; j++) {
            res += (satisfaction[j] * (j - gap + 1));
            add += satisfaction[j];
        }


        int minus = 0;
        for (int j = gap - 1; j >= 0; j--) {
            minus += satisfaction[j];
            if (add + minus >= 0) {
                res += (add + minus);
            } else {
                return res;
            }
        }
        return res;
    }


    public int maxSatisfactionOpt(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int res = 0,add = 0;
        for (int i = satisfaction.length - 1; i >= 0; i--) {
            add += satisfaction[i];
            if (add >= 0) {
                res += add;
            } else {
                break;
            }
        }
        return res;

    }

    public static void main(String[] args) {

        ReducingDishes o = new ReducingDishes();
        int res = o.maxSatisfactionOpt(new int[]{-1, -8, 0, 5, -7});
        System.out.println(res);

    }
}
