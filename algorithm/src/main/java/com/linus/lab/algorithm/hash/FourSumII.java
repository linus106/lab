package com.linus.lab.algorithm.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangxiangyu
 * @Date 2020/11/27 10:09
 * @Description TODO
 */
public class FourSumII {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int n = A.length;
        Map<Integer, Integer> map = new HashMap<>();

        int res = 0;

        for (int i : A) {
            for (int j : B) {
                Integer sum = i + j;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        for (int i : C) {
            for (int j : D) {
                Integer sum = i + j;
                res += (map.getOrDefault(-sum, 0));
            }
        }
        return res;
    }


}
