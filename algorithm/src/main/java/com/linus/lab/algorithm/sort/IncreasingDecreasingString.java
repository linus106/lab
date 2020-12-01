package com.linus.lab.algorithm.sort;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author wangxiangyu
 * @Date 2020/11/25 17:40
 * @Description TODO
 */
public class IncreasingDecreasingString {

    public String sortString(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder sb= new StringBuilder();
        while (sb.length() < s.length()) {
            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) {
                    sb.append((char)(i + 'a'));
                    count[i]--;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (count[i] > 0) {
                    sb.append((char)(i + 'a'));
                    count[i]--;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        IncreasingDecreasingString o = new IncreasingDecreasingString();
        o.sortString("aaaabbbbcccc");

    }
}
