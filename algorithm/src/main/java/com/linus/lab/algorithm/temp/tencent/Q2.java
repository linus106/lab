package com.linus.lab.algorithm.temp.tencent;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/12/13
 *
 * 目标和给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 */
public class Q2 {

    public static void main(String[] args) {

        int S = 0;
        int[] input = new int[]{1,1, 1, 1};

        int sum = 0;
        for (int i = 0; i< input.length;i++) {
            sum += input[i];
        }
        int res = 0;
        if ((sum- S) % 2==0) {
            int target = (sum- S) /2;
            res = helper(target,input,0);
        }
        System.out.println(res);
    }

    public static int helper(int target, int[] input, int l) {
        if (l >= input.length || target < 0) {
            return 0;
        }
        int sum = 0;
        if (target == input[l]) {
            sum += 1;
        } else {
            sum += helper(target - input[l], input, l + 1);
        }
        sum += helper(target, input, l + 1);
        return sum;
    }
}
