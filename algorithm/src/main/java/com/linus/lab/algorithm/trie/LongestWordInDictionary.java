package com.linus.lab.algorithm.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangxiangyu
 * @Date 2020/10/28 16:15
 * @Description TODO
 * https://leetcode-cn.com/problems/longest-word-in-dictionary/
 *
 * 对这个问题，暴力解法会更快一些
 *
 * 把所有string都放到一个set里，遍历判断
 */
public class LongestWordInDictionary {

    public String longestWord(String[] words) {
        Node root = new Node();
        Arrays.sort(words);


        int maxLength = 0;
        String maxWord = "";
        for (String word : words) {

            if (root.insert(word) && (word.length() > maxLength)) {
                maxWord = word;
                maxLength = word.length();
            }

        }
        return maxWord;

    }


    private class Node {
//        Map<Character, Node> subMap = new HashMap<>();
        Node[] subNodes = new Node[26];//faster but more space cost than map

        boolean insert(String word) {
            if (word.length() < 1) return true;

            char firstChar = word.charAt(0);
            if (word.length() == 1) {
                if (subNodes[firstChar - 'a'] == null) subNodes[firstChar - 'a'] = new Node();
                return true;
            } else {
                if (subNodes[firstChar - 'a'] == null) {
                    return false;
                } else {
                    return subNodes[firstChar - 'a'].insert(word.substring(1));
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
