package com.linus.lab.algorithm.trie;

/**
 * @Author wangxiangyu
 * @Date 2020/10/28 16:56
 * @Description TODO
 */
public class ImplementTriePrefixTree {

    private class Trie {

        Trie[] sub;
        boolean isWord;

        /** Initialize your data structure here. */
        public Trie() {
            sub = new Trie[26];
            isWord = false;
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if (word.length() == 0) {
                isWord = true;
                return;
            }

            char firstChar = word.charAt(0);
            int index = firstChar - 'a';
            if (sub[index] == null) sub[index] = new Trie();

            sub[index].insert(word.substring(1));
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            if (word.length() == 0) {
                return isWord;
            }

            char firstChar = word.charAt(0);
            int index = firstChar - 'a';
            if (sub[index] == null) return false;

            return sub[index].search(word.substring(1));
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            if (prefix.length() == 0) {
                return true;
            }

            char firstChar = prefix.charAt(0);
            int index = firstChar - 'a';
            if (sub[index] == null) return false;

            return sub[index].startsWith(prefix.substring(1));
        }

    }

}
