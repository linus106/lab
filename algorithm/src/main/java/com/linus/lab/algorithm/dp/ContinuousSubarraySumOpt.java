package com.linus.lab.algorithm.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author wangxiangyu
 * @Date 2020/12/1 18:30
 * @Description TODO
 */
public class ContinuousSubarraySumOpt {

    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k;
            }

            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }


    public static void main(String[] args) {

        ContinuousSubarraySumOpt o = new ContinuousSubarraySumOpt();
        boolean res = o.checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6);
        System.out.println(res);

    }
}
