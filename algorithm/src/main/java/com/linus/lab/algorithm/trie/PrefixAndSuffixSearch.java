package com.linus.lab.algorithm.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/15
 */
public class PrefixAndSuffixSearch {

    private Trie rootPrefix = new Trie();
    private Trie rootSuffix = new Trie();

    public PrefixAndSuffixSearch(String[] words) {

        for (int i = 0; i < words.length; i++) {
            rootPrefix.insert(words[i], i);
            rootSuffix.insert(new StringBuilder(words[i]).reverse().toString(), i);
        }
    }

    public int f(String prefix, String suffix) {
        final List<Integer> prefixWeights = rootPrefix.searchWeights(prefix);
        final List<Integer> suffixWeights = rootSuffix.searchWeights(new StringBuilder(suffix).reverse().toString());
        if (prefixWeights == null || suffixWeights == null) return -1;


        int indexSuf = suffixWeights.size() - 1;
        int indexPre = prefixWeights.size() - 1;

        while (indexPre>=0 && indexPre>=0) {
            int currSuf = suffixWeights.get(indexSuf);
            int currPre = prefixWeights.get(indexPre);

            if (currPre > currSuf) {
                indexPre--;
            } else if (currPre < currSuf) {
                indexSuf--;
            } else {
                return currSuf;
            }
        }

        return -1;
    }


    private class Trie {
        Trie[] subs = new Trie[26];
        private List<Integer> weights = new ArrayList<>();

        void insert(String word, int weight) {
            weights.add(weight);
            if (word.length() == 0) {
                return;
            }


            char firstChar = word.charAt(0);
            int index = firstChar - 'a';
            if (subs[index] == null) subs[index] = new Trie();

            subs[index].insert(word.substring(1), weight);
        }

        List<Integer> searchWeights(String prefix) {
            if (prefix.length() == 0) {
                return weights;
            }

            char firstChar = prefix.charAt(0);
            int index = firstChar - 'a';
            if (subs[index] == null) return null;

            return subs[index].searchWeights(prefix.substring(1));
        }
    }

    public static void main(String[] args) {

        PrefixAndSuffixSearch o = new PrefixAndSuffixSearch(new String[] {"cabaabaaaa","ccbcababac","bacaabccba","bcbbcbacaa","abcaccbcaa","accabaccaa","cabcbbbcca","ababccabcb","caccbbcbab","bccbacbcba"});

        System.out.println(o.f("ab","abcaccbcaa"));

    }
}
