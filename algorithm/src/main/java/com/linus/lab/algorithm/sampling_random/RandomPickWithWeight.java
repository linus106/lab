package com.linus.lab.algorithm.sampling_random;

import java.util.Random;

/**
 * @Author wangxiangyu
 * @Date 2020/9/30 11:52
 * @Description TODO
 */
public class RandomPickWithWeight {

    int[] weightSum;
    Random randomObj  = new Random();

    public RandomPickWithWeight(int[] w) {
        int sum = 0;
        weightSum = new int[w.length];
        for (int i = 0; i< w.length;i++) {
            sum += w[i];
            weightSum[i] = sum;
        }
    }

    public int pickIndex() {
        int random = randomObj.nextInt(weightSum[weightSum.length - 1]);

        int l = 0, r = weightSum.length - 1;
        while (r != l) {
            int mid = (l + r) / 2;
            if (random >= weightSum[mid]) {
                l = mid + 1;
            }  else {
                r = mid;
            }
        }
        return l;
    }

    public static void main(String[] args) {

    }
}
