package com.linus.lab.algorithm.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/10
 */
public class CountSubstringsThatDifferByOneCharacter {

    public int countSubstringsBruteForce(String s, String t) {
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                int diff = 0;
                for (int k = 0; i + k < s.length() && j + k < t.length() && diff < 2; k++) {
                    if (s.charAt(i + k) != t.charAt(j + k)) diff++;
                    if (diff == 1) res++;
                }
            }
        }
        return res;

    }

    public int countSubstrings(String s, String t) {
        int res = 0;
        for (int offset = -t.length() + 1; offset <= s.length() - 1; offset++) {//遍历对其方式
            int start = Math.max(0, offset);
            int end = Math.min(s.length() - 1, offset + t.length() - 1);

            // 标记不一样字符的位置为1，其余位置为0
            int[] diff = new int[end - start + 2];
            for (int i = start; i <= end; i++) {
                if (s.charAt(i) != t.charAt(i - offset)) {
                    diff[i - start] = 1;
                }
            }
            diff[diff.length - 1] = 1;//追加一个辅助位

            // 计算1之间的距离,   001000->围绕中间1产生的子串数量等于 (2 + 1) * (3 + 1)
            int p = -1, lastRange = 0;
            for (int i = 0; i < diff.length; i++) {
                if (diff[i] == 1) {
                    int range = i - p;
                    res += (range * lastRange);
                    lastRange = range;
                    p = i;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        int result = new CountSubstringsThatDifferByOneCharacter().countSubstrings("aba", "baba");
        System.out.println(result);

    }
}
