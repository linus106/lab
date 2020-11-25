package com.linus.lab.algorithm.hash;

/**
 * @Author wangxiangyu
 * @Date 2020/11/23 13:42
 * @Description TODO
 */
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] countMap = new int[26];

        for (char c : s.toCharArray()) {
            int index = c - 'a';
            countMap[index]++;
        }


        for (char c : t.toCharArray()) {
            int index = c - 'a';
            countMap[index]--;

            if (countMap[index] < 0) {
                return false;
            }
        }


        return true;
    }


    public static void main(String[] args) {

    }
}
