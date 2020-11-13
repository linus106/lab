package com.linus.lab.algorithm.trie;

import java.util.*;

/**
 * @Author wangxiangyu
 * @Date 2020/11/13 17:02
 * @Description TODO
 */
public class StreamOfCharacters {

    private Trie root = new Trie();

    public StreamOfCharacters(String[] words) {
        Arrays.stream(words).forEach(root::insert);
    }

    private Set<Trie> nodes = new HashSet<>();

    public boolean query(char letter) {
        nodes.add(root);
        Set<Trie> newNodes = new HashSet<>();
        boolean bingo = false;
        for (Trie node : nodes) {
            int index = letter - 'a';
            if (node.sub[index] != null) {
                newNodes.add(node.sub[index]);
                if (node.sub[index].isWord) {
                    bingo = true;
                }
            }
        }
        nodes = newNodes;
        return bingo;
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

    }

    public static void main(String[] args) {

        StreamOfCharacters o = new StreamOfCharacters(new String[]{"cd","f","kl"});


        char[] stream = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'h', 'i', 'g', 'k', 'l', 'm', 'n', 'o', 'p', 'q'};

        for (char c : stream) {
            System.out.println(c + ":" + o.query(c));
        }


    }
}
