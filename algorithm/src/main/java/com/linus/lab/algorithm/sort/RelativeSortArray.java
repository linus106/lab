package com.linus.lab.algorithm.sort;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/19
 */
public class RelativeSortArray {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {

        int[] count = new int[1001];

        for (int n : arr1) {
            count[n]++;
        }

        int[] res = new int[arr1.length];

        int n = 0;
        for (int i : arr2) {
            for (int j = 0; j < count[i]; j++) {
                res[n++] = i;
            }
            count[i] = 0;
        }

        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                res[n++] = i;
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
