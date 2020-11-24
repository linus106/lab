package com.linus.lab.algorithm.doublepointer;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/23
 */
public class ImplementStrstr {

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;

        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int j = 1;
                while (j < needle.length() && haystack.charAt(i + j) == needle.charAt(j)) {
                    j++;
                }
                if (j == needle.length()) return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
