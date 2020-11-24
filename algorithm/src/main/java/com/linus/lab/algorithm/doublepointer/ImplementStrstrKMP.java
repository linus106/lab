package com.linus.lab.algorithm.doublepointer;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/23
 */
public class ImplementStrstrKMP {

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int[] next = getNext(needle);
        return kmp(haystack, needle, next);
    }

    private int kmp(String haystack, String needle, int[] next) {
        int i = 0, j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == needle.length())
            return i - j;
        else
            return -1;
    }

    private int[] getNext(String needle) {
        int[] next = new int[needle.length()];
        next[0] = -1;
        int j = -1;
        int i = 0;
        while (i < needle.length()) {
            if (j == -1 || needle.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                //越界限制
                if (i < needle.length()) next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        ImplementStrstrKMP o = new ImplementStrstrKMP();
        int res = o.strStr("aabaaabaaac", "aabaaac");
        System.out.println(res);

    }
}
