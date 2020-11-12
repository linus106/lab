package com.linus.lab.algorithm.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/11
 */
public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {
        Trie root = new Trie();
        Arrays.stream(words).forEach(root::insert);



        return null;
    }


    private void dfs() {

    }

    private class Trie {
        Trie[] subs = new Trie[26];
        boolean isWord = false;

        void insert(String word) {
            if (word.length() == 0) {
                isWord = true;
                return;
            }

            char firstChar = word.charAt(0);
            int index = firstChar - 'a';
            if (subs[index] == null) subs[index] = new Trie();

            subs[index].insert(word.substring(1));
        }

    }

    public static void main(String[] args) {
        List<List<Integer>> res = new PalindromePairs().palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"});
        System.out.println(res);
    }
}
