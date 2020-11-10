package com.linus.lab.algorithm.trie;

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
                for (int k = 0; i + k < s.length() && j +k < t.length() && diff < 2; k++) {
                    if (s.charAt(i + k) != t.charAt(j + k)) diff++;
                    if (diff == 1) res++;
                }
            }
        }
        return res;

    }

    public int countSubstrings(String s, String t) {
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                int diff = 0;
                for (int k = 0; i + k < s.length() && j +k < t.length() && diff < 2; k++) {
                    if (s.charAt(i + k) != t.charAt(j + k)) diff++;
                    if (diff == 1) res++;
                }
            }
        }
        return res;

    }
}
