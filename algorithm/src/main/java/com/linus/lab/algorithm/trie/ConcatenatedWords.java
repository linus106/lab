package com.linus.lab.algorithm.trie;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/9
=======
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author wangxiangyu
 * @Date 2020/11/10 11:27
 * @Description TODO
 * https://leetcode-cn.com/problems/concatenated-words/
>>>>>>> leetcode trie-backtrace
 */
public class ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        final Trie root = new Trie();
        Arrays.stream(words).filter(s->s.length()>0).forEach(root::insert);
        return Arrays.stream(words).filter(word->dfs(word, 0, root)).collect(Collectors.toList());
    }

    private boolean dfs(String word, int index, Trie root) {
        Trie node = root;
        for (int i = index; i < word.length(); i++) {
            char c = word.charAt(i);
            int pos = c - 'a';
            if (node.sub[pos] == null) return false;

            node = node.sub[pos];
            if (node.isWord && dfs(word, i + 1, root)) {
                return true;
            }
        }
        return index != 0 && node.isWord;
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

    }


    public static void main(String[] args) {
        List<String> result = new ConcatenatedWords().findAllConcatenatedWordsInADict(new String[]{"cats","cat",
                "catsdogcats","dog", "dogcatsdog","hippopotamuses","rat","ratcatdogcat"});
        System.out.println(result);
    }
}
