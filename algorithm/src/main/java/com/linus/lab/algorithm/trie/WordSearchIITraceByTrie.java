package com.linus.lab.algorithm.trie;

import javafx.util.Pair;

import java.util.*;
import java.util.List;

/**
 * @Author wangxiangyu
 * @Date 2020/11/10 15:45
 * @Description TODO
 */
public class WordSearchIITraceByTrie {

    private int xLength, yLength;
    private Set<String> res = new HashSet<>();
    private char[][] board;

    private boolean inRange(int x, int y) {
        return x >= 0 && x < xLength && y >= 0 && y < yLength;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Trie root = new Trie("");
        this.board = board;
        Arrays.stream(words).forEach(root::insert);
        xLength = board.length;
        yLength = board[0].length;

        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                char c = board[i][j];
                int index = c - 'a';
                if (root.sub[index] != null) {
                    root.sub[index].memo.add(Arrays.asList(new Pair<>(i, j)));
                }
            }
        }

        for (Trie trie : root.sub) {
            if (trie != null) dfs(trie);
        }

        return new ArrayList<>(res);
    }

    public void dfs(Trie node) {
        if (node == null || node.memo.size() == 0) return;

        if (node.isWord) {
            res.add(node.str);
        }

        for (List<Pair<Integer, Integer>> trace : node.memo) {
            Pair<Integer, Integer> lastPair = trace.get(trace.size() - 1);
            List<int[]> fourOptions = Arrays.asList(new int[]{lastPair.getKey(), lastPair.getValue() + 1},
                    new int[]{lastPair.getKey(), lastPair.getValue() - 1},
                    new int[]{lastPair.getKey() + 1, lastPair.getValue()},
                    new int[]{lastPair.getKey() - 1, lastPair.getValue()});

            for (int[] newXY : fourOptions) {
                int x = newXY[0], y = newXY[1];

                if (inRange(x, y) && !trace.contains(new Pair(x, y))) {
                    char nextC = board[x][y];
                    Trie nextNode = node.sub[nextC - 'a'];
                    if (nextNode != null) {
                        List<Pair<Integer, Integer>> copyedTrace = new ArrayList<>();
                        copyedTrace.addAll(trace);
                        copyedTrace.add(new Pair<>(x, y));
                        nextNode.memo.add(copyedTrace);
                    }
                }
            }
        }

        for (Trie trie : node.sub) {
            if (trie != null) dfs(trie);
        }

    }



    private class Trie {

        Trie[] sub;
        boolean isWord;
        List<List<Pair<Integer, Integer>>> memo = new ArrayList<>();
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
        WordSearchIITraceByTrie o = new WordSearchIITraceByTrie();

        long t1 = System.currentTimeMillis();
        List<String> res = o.findWords(new char[][]{
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'},
                new char[]{'a','a','a','a','a','a','a','a','a','a','a','a'}
        }, new String[] {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"});


//        List<String> res = o.findWords(new char[][]{
//                new char[]{'o','a','a','n'},
//                new char[]{'e','t','a','e'},
//                new char[]{'i','h','k','r'},
//                new char[]{'i','f','l','v'}
//        }, new String[] {"oath","pea","eat","rain"});

        System.out.println(res);

        System.out.println(System.currentTimeMillis() - t1);

    }
}
