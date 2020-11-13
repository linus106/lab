package com.linus.lab.algorithm.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author wangxiangyu
 * @Date 2020/11/12 10:49
 * @Description TODO
 * https://leetcode-cn.com/problems/palindrome-pairs/
 */
public class PalindromePairs {


    List<List<Integer>> res = new ArrayList<>();

    /**
     * 基于字典树，记录每个节点后缀串中哪些是回文串
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        Trie root = new Trie();
        for (int i = 0; i < words.length; i++) {
            root.insert(new StringBuffer(words[i]).reverse().toString(), i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            dfs(word, 0, root, i);
        }
        return res;
    }

    private void dfs(String word, int start, Trie node, int wordIndex) {
        // word traverse to end
        if (start == word.length()) {
            // if trie traverse to end too
            if (node.isWord && node.i != wordIndex) {
                res.add(Arrays.asList(wordIndex, node.i));
            }
            node.suffixPalindrome.stream()
                    .filter(i->i != wordIndex)
                    .forEach(i->res.add(Arrays.asList(wordIndex, i) ));
        } else {// word not traverse to end yet
            if (node.isWord && node.i != wordIndex && isPalindrome(word.substring(start))) {
                res.add(Arrays.asList(wordIndex, node.i));
            }
            // dfs
            char c = word.charAt(start);
            Trie nextNode = node.sub[c - 'a'];
            if (nextNode != null) {
                dfs(word, start + 1, nextNode, wordIndex);
            }
        }
    }

    private boolean isPalindrome(String word) {
        if (word.length() == 0) return false;
        int l = 0, r = word.length() - 1;
        while (l <= r) {
            if (word.charAt(l) != word.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    private class Trie {

        Trie[] sub;
        boolean isWord;
        List<Integer> suffixPalindrome = new ArrayList<>();
        int i;

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
        public void insert(String word, int i) {
            if (word.length() == 0) {
                isWord = true;
                this.i = i;
                return;
            }
            if (isPalindrome(word)) {
                suffixPalindrome.add(i);
            }

            char firstChar = word.charAt(0);
            int index = firstChar - 'a';
            if (sub[index] == null) sub[index] = new Trie();

            sub[index].insert(word.substring(1), i);
        }

    }

    public static void main(String[] args) {
        List<List<Integer>> res = new PalindromePairs().palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"});
        System.out.println(res);
    }
}
