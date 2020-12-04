package com.linus.lab.algorithm.dp;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Author wangxiangyu
 * @Date 2020/12/2 17:08
 * @Description TODO
 */
public class CreateMaximumNumber {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        int k1 = Math.min(k, nums1.length);
        int k2 = k - k1;
        int[] res = new int[k];
        while (k1 >= 0 && k2 <= nums2.length) {
            int[] res1 = removeKDigits(nums1, k1);
            int[] res2 = removeKDigits(nums2, k2);
            int[] cur = merge(res1, res2);

            for (int i = 0; i < k; i++) {
                if (res[i] > cur[i]) {
                    break;
                } else if (res[i] < cur[i]){
                    res = cur;
                    break;
                }
            }
            k1--;
            k2++;
        }
        return res;
    }

    public int compare(int[] nums1, int i1, int[] nums2, int i2) {
        while (i1 < nums1.length && i2 < nums2.length) {
            if (nums1[i1] != nums2[i2]) {
                return nums1[i1] - nums2[i2];
            }
            i1++;
            i2++;
        }
        return i1 == nums1.length ? -1 : 1;
    }

    private int[] merge(int[] res1, int[] res2) {
        int[] res = new int[res1.length + res2.length];
        int i1 = 0, i2 = 0;
        int i = 0;
        while(i1 < res1.length && i2 < res2.length) {
            if (compare(res1, i1, res2, i2) > 0) {
                res[i++] = res1[i1++];
            } else {
                res[i++] = res2[i2++];
            }
        }
        if (i1 < res1.length) {
            System.arraycopy(res1, i1, res, i, res1.length - i1);
        }
        if (i2 < res2.length) {
            System.arraycopy(res2, i2, res, i, res2.length - i2);
        }
        return res;
    }

    private int[] removeKDigits(int[] nums, int remain) {
        Stack<Integer> stack = new Stack<>();
        int k = nums.length - remain;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            while (!stack.empty() && n > stack.peek() && 0 < k) {
                stack.pop();
                k--;
            }
            stack.push(n);
        }
        while (k-- > 0) stack.pop();
        Integer[] temp = new Integer[remain];
        stack.copyInto(temp);
        int[] res = new int[remain];
        for (int i = 0; i < remain; i++) {
            res[i] = temp[i];
        }
        return res;
    }

    public static void main(String[] args) {


    }
}
