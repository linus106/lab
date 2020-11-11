package com.linus.lab.algorithm.trie;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author wangxiangyu
 * @Date 2020/11/9 18:22
 * @Description TODO
 * https://leetcode-cn.com/problems/replace-words/
 */
public class ReplaceWords {

    public String replaceWords(List<String> dictionary, String sentence) {

        final Trie root = new Trie();
        dictionary.forEach(root::insert);
        List<String> words =
                Arrays.stream(sentence.split(" ")).map(raw->{
                    String str = root.searchRoot(raw, "");
                    return (str == null ? raw : str);
                }).collect(Collectors.toList());
        return String.join(" ", words);
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
        public String searchRoot(String word, String root) {
            if (isWord) return root;
            if (word.length() == 0) {
                return null;
            }
            char firstChar = word.charAt(0);
            int index = firstChar - 'a';
            if (sub[index] == null) return null;
            return sub[index].searchRoot(word.substring(1), root + firstChar);
        }

    }

    public static void main(String[] args) {
        String res = new ReplaceWords().replaceWords(Arrays.asList("cat","bat","rat"), "the cattle was rattled by the" +
                " battery");
        System.out.println(res);
    }
}
