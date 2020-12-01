package com.linus.lab.algorithm.binarytree;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * @Author wangxiangyu
 * @Date 2020/11/25 17:57
 * @Description TODO
 */
public class DivideTwoIntegers {

    long div(long a, long b) {
        if (a < b) return 0;
        long ans = 1;
        long acc = b;
        while (a - acc >= acc) {
            ans += ans;
            acc += acc;
        }
        return ans + div(a - acc, b);
    }


    public int divide(int dividend, int divisor) {
        boolean nagtive = (dividend < 0) ^ (divisor < 0);

        long dividendLong = Math.abs(Long.valueOf(dividend));
        long divisorLong = Math.abs(Long.valueOf(divisor));
        long times = div(dividendLong, divisorLong);

        int res;
        if (!nagtive) {
            if (times > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else {
                return Long.valueOf(times).intValue();
            }
        } else {
            if (-times < Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            } else {
                return Long.valueOf(-times).intValue();
            }
        }
    }


    public static void main(String[] args) {
        DivideTwoIntegers o = new DivideTwoIntegers();
        int res = o.divide(10, 3);
        System.out.println(res);
    }

}
