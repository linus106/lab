package com.linus.lab.algorithm.trie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author wangxiangyu
 * @Date 2020/11/13 17:02
 * @Description TODO
 */
public class StreamOfCharactersSuffix {

    private Trie root = new Trie();

    public StreamOfCharactersSuffix(String[] words) {
        Arrays.stream(words).forEach(word->root.insert(word, word.length() - 1));
    }

    private char[] charStream = new char[40000];

    private int nextIndex = 0;

    public boolean query(char letter) {
        int i = nextIndex;
        charStream[nextIndex++] = letter;
        Trie node = root;

        while (node != null && i >=0) {
            int tIndex = charStream[i--] - 'a';
            node = node.sub[tIndex];
            if (node != null && node.isWord) return true;
        }

        return false;
    }



    private class Trie {

        Trie[] sub;
        boolean isWord;

        /** Initialize your data structure here. */
        public Trie() {
            sub = new Trie[26];
            isWord = false;
        }

        /** Inserts a word into the trie. */
        public void insert(String word, int i) {
            if (i == -1) {
                isWord = true;
                return;
            }

            char firstChar = word.charAt(i);
            int index = firstChar - 'a';
            if (sub[index] == null) sub[index] = new Trie();

            sub[index].insert(word, i - 1);
        }

    }

    public static void main(String[] args) {

        StreamOfCharactersSuffix o = new StreamOfCharactersSuffix(new String[]{"cd","f","kl"});


        char[] stream = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'h', 'i', 'g', 'k', 'l', 'm', 'n', 'o', 'p', 'q'};

        for (char c : stream) {
            System.out.println(c + ":" + o.query(c));
        }


    }
}
