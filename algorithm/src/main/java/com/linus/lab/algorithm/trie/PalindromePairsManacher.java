package com.linus.lab.algorithm.trie;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author wangxiangyu
 * @Date 2020/11/12 10:49
 * @Description TODO
 * https://leetcode-cn.com/problems/palindrome-pairs/
 */
public class PalindromePairsManacher {




    private String manacherPrepare(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (char c : s.toCharArray()) {
            sb.append(c).append('#');
        }
        return sb.toString();
    }

    /**
     * 基于manacher,计算每个字符串的头尾存在哪些子串，遍历这些子串从hashmap里获得 对手串的索引
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> reverseWordMap = new HashMap<>();
        int[][] manacher = new int[words.length][];

        for (int i = 0; i < words.length; i++) {
            String rawStr = words[i];
            StringBuilder stringBuilder = new StringBuilder(rawStr);
            reverseWordMap.put(stringBuilder.reverse().toString(), i);
            String mStr = manacherPrepare(rawStr);
            int[] p = new int[mStr.length()];
            int C = 0, R = 0;
            for (int j = 0; j < mStr.length(); j++) {
                if (R > j) {
                    p[j] = Math.min(p[2 * C - j], R - j);
                }
                int l, r;
                while ((r = j + p[j] + 1) < mStr.length() && (l = j - p[j] - 1) >= 0 && mStr.charAt(l) == mStr.charAt(r)) {
                    p[j]++;
                }
                if (j + p[j] > R) {
                    C = j;
                    R = j + p[j];
                }
                manacher[i] = p;
            }
        }

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            int m = reverseWordMap.getOrDefault(words[i], -1);
            if (m != -1 && i != m) res.add(Arrays.asList(i ,m));

            for (int j = 1; j < manacher[i].length - 1; j++) {
                if (j == manacher[i][j]) {//以回文串开头
                    String word2Search = words[i].substring(j);
                    int n = reverseWordMap.getOrDefault(word2Search, -1);
                    if (n != -1 && i != n) res.add(Arrays.asList(n ,i));
                }

                if (j + manacher[i][j] == manacher[i].length - 1) {//以回文串结尾
                    String word2Search = words[i].substring(0,j - words[i].length());
                    int n = reverseWordMap.getOrDefault(word2Search, -1);
                    if (n != -1 && i != n) res.add(Arrays.asList(i ,n));
                }
            }
        }
        return res;
    }



    public static void main(String[] args) {
        List<List<Integer>> res = new PalindromePairsManacher().palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"});
//        List<List<Integer>> res = new PalindromePairsManacher().palindromePairs(new String[]{"a", ""});
        System.out.println(res);
    }
}
