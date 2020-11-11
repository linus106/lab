package com.linus.lab.algorithm.trie;

import javafx.util.Pair;

import java.util.*;

/**
 * @Author wangxiangyu
 * @Date 2020/11/10 15:45
 * @Description TODO
 */
public class WordSearchIITraceBack {

    private List<String> res = new ArrayList<>();
    private char[][] board;
    int xLength, yLength;

    private boolean inRange(int x, int y) {
        return x >= 0 && x < xLength && y >= 0 && y < yLength;
    }

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        xLength = board.length;
        yLength = board[0].length;

        Trie root = new Trie("");
        Arrays.stream(words).forEach(root::insert);

        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                dfs(i, j ,root);
            }
        }
        return new ArrayList<>(res);
    }

    public void dfs(int x, int y, Trie node) {
        char c = board[x][y];
        Trie nextNode = node.sub[c - 'a'];
        if (nextNode == null) return;

        if (nextNode.isWord) {
            res.add(nextNode.str);
            nextNode.isWord = false;
        }

        List<int[]> fourOptions = Arrays.asList(new int[]{x, y + 1},
                new int[]{x, y - 1},
                new int[]{x + 1, y},
                new int[]{x - 1, y});

        board[x][y] = '.';//防止重复回溯
        for (int[] nextXY : fourOptions) {
            int nextX = nextXY[0], nextY = nextXY[1];
            if (inRange(nextX, nextY) && board[nextX][nextY] != '.') {
                dfs(nextX, nextY, nextNode);
            }
        }
        board[x][y] = c;
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
        WordSearchIITraceBack o = new WordSearchIITraceBack();

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
