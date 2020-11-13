package com.linus.lab.algorithm.trie;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author wangxiangyu
 * @Date 2020/11/11 11:56
 * @Description TODO
 */
public class MultiSearchIcci {

    public int[][] multiSearchBruteForce(String big, String[] smalls) {
        Set<String> smallSet = new HashSet<>();
        Arrays.stream(smalls).forEach(smallSet::add);

        int length = big.length();
        Map<String, List<Integer>> resMap = new HashMap<>();

        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                String s = big.substring(i, j + 1);
                if (smallSet.contains(s)) {
                    resMap.putIfAbsent(s, new ArrayList<>());
                    resMap.get(s).add(i);
                }
            }
        }

        int[][] finalRes = new int[smalls.length][];
        for (int k = 0; k < smalls.length; k++) {
            finalRes[k] = resMap.getOrDefault(smalls[k], new ArrayList<>())
                    .stream().mapToInt(Integer::intValue).toArray();
        }

        return finalRes;
    }


    public int[][] multiSearch(String big, String[] smalls) {
        Map<String, List<Integer>> resMap = new HashMap<>();
        Trie root = new Trie("");
        Arrays.stream(smalls).forEach(root::insert);

        int length = big.length();
        for (int i = 0; i < length; i++) {
            Trie node = root;
            for (int j = i; j < big.length(); j++) {
                node = node.sub[big.charAt(j) - 'a'];
                if (node == null) {
                    break;
                }
                if (node.isWord) {
                    resMap.putIfAbsent(node.str, new ArrayList<>());
                    resMap.get(node.str).add(i);
                }
            }
        }

        int[][] finalRes = new int[smalls.length][];
        for (int k = 0; k < smalls.length; k++) {
            finalRes[k] = resMap.getOrDefault(smalls[k], new ArrayList<>())
                    .stream().mapToInt(Integer::intValue).toArray();
        }

        return finalRes;
    }

    private class Trie {

        Trie[] sub;
        boolean isWord;
        String str;

        /**
         * Initialize your data structure here.
         */
        public Trie(String word) {
            sub = new Trie[26];
            isWord = false;
            str = word;
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (word.length() == 0) {
                isWord = true;
                return;
            }

            char firstChar = word.charAt(0);
            int index = firstChar - 'a';
            if (sub[index] == null) sub[index] = new Trie(str + firstChar);

            sub[index].insert(word.substring(1));
        }

    }

    public static void main(String[] args) {
        int[][] result = new MultiSearchIcci()
                .multiSearch("mississippi",
                        new String[]{"is", "ppi", "hi", "sis", "i", "ssippi"});
        System.out.println(result);

    }
}
