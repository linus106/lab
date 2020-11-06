package com.linus.lab.algorithm.trie;

/**
 * @Author wangxiangyu
 * @Date 2020/11/6 18:12
 * @Description TODO
 */
public class ImplementMagicDictionary {

    Trie trie;

    public ImplementMagicDictionary() {
        trie = new Trie();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            trie.insert(word);
        }
    }

    public boolean search(String searchWord) {
        return doSearch(searchWord, trie);
    }

    private boolean doSearch(String searchWord, Trie node) {
        char firstChar = searchWord.charAt(0);
        String tail = searchWord.substring(1 );
        int index = firstChar - 'a';
        if (searchWord.length() == 1) {
            for (int i = 0; i < node.subs.length; i++) {
                if (i != index && node.subs[i] != null && node.subs[i].isWord) {
                    return true;
                }
            }
            return false;
        }


        boolean contains = false;
        for (int i = 0; i < node.subs.length; i++) {
            if (node.subs[i] != null) {
                Trie sub = node.subs[i];
                if (i == index) {
                    contains = doSearch(tail, sub);
                } else {
                    contains = sub.search(tail);
                }
                if (contains) return true;
            }
        }

        return false;

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

        boolean search(String word) {
            if (word.length() == 0) {
                return isWord;
            }

            char firstChar = word.charAt(0);
            int index = firstChar - 'a';
            if (subs[index] == null) return false;

            return subs[index].search(word.substring(1));
        }
    }

    public static void main(String[] args) {

        ImplementMagicDictionary obj = new ImplementMagicDictionary();
        obj.buildDict(new String[]{"hello", "leetcode"});
        System.out.println(obj.search("hello"));
        System.out.println(obj.search("hhllo"));
        System.out.println(obj.search("hell"));
        System.out.println(obj.search("leetcoded"));

    }


}
