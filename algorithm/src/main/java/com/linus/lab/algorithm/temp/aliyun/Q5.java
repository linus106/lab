package com.linus.lab.algorithm.temp.aliyun;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/6
 * https://tianchi.aliyun.com/oj/15193368247341694/33967147358294629
 */
public class Q5 {

    /**
     * @param s1: the string 1
     * @param s2: the string 2
     * @return: judge can s1 change to s2
     */
    public boolean judge(String s1, String s2) {
        // write your code here


        if (s1.length() != s2.length()) {
            return false;
        }
        int lenth = s1.length();

        for (int i = 0;i<s2.length();i++) {
            if (s2.charAt(i) == s1.charAt(0)) {
                for (int j = 1; j < lenth;j++) {
                    if (s1.charAt(j) != s2.charAt((j+i) % lenth)) {
                        break;
                    }
                    if(j == lenth -1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Q5().judge("abcd", "bcda"));
        System.out.println(new Q5().judge("abcd", "abdc"));
    }
}
