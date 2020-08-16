package com.linus.lab.algorithm.temp;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/16
 */
public class Q5 {

    public boolean threeConsecutiveOdds(int[] arr) {
        int count = 0;
        for (int val : arr) {
            if (val %2 == 1) {
                count ++;
            } else {
                count = 0;
            }
            if (count >= 3) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
