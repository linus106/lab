package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/3 18:28
 * @Description TODO
 */
public class CountingBits {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = res[i>>1] + (i & 1);
        }
        return res;
    }

    public static void main(String[] args) {

        CountingBits o = new CountingBits();
        int[] res = o.countBits(2);
        for (int re : res) {
            System.out.println(re);
        }

    }
}
