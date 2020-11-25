package com.linus.lab.algorithm.sort;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

/**
 * @Author wangxiangyu
 * @Date 2020/11/23 17:56
 * @Description TODO
 */
public class SortArrayByParityII {

    public int[] sortArrayByParityIIOpt(int[] A) {
        int even = 0, odd = 1;

        while (even < A.length && odd < A.length) {
            while (even < A.length && A[even] % 2 == 0) {
                even += 2;
            }
            while (odd < A.length && A[odd] % 2 == 1) {
                odd += 2;
            }

            if (even < A.length) {
                int temp = A[even];
                A[even] = A[odd];
                A[odd] = temp;
            }
        }
        return A;
    }

    public int[] sortArrayByParityII(int[] A) {
        int[] res = new int[A.length];
        int even = 0, odd = 1;
        for (int n : A) {
            if (n % 2 == 0) {
                res[even] = n;
                even += 2;
            } else {
                res[odd] = n;
                odd += 2;
            }
        }
        return res;
    }


    public static void main(String[] args) {

    }
}
