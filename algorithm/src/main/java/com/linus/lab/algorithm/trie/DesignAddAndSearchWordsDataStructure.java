package com.linus.lab.algorithm.trie;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/9
 * https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/
 */
public class DesignAddAndSearchWordsDataStructure {

    private Trie root;

    /**
     * Initialize your data structure here.
     */
    public DesignAddAndSearchWordsDataStructure() {
        root = new Trie();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        root.insert(word);
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return root.search(word);
    }

    private class Trie {
        Trie[] sub;
        boolean isWord;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            sub = new Trie[26];
            isWord = false;
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
            if (sub[index] == null) sub[index] = new Trie();

            sub[index].insert(word.substring(1));
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            if (word.length() == 0) {
                return isWord;
            }

            char firstChar = word.charAt(0);
            if (firstChar == '.') {
                for (Trie trie : sub) {
                    if (trie != null && trie.search(word.substring(1))) {
                        return true;
                    }
                }
                return false;
            } else {
                int index = firstChar - 'a';
                if (sub[index] == null) return false;

                return sub[index].search(word.substring(1));
            }
        }
    }

    public static void main(String[] args) {

    }
}
