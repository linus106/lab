package com.linus.lab.algorithm.memory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author wangxiangyu
 * @Date 2020/8/10 17:50
 * @Description TODO
 */
public class ReSpaceLcci {






    /**
     * @param dictionary
     * @param sentence
     * @return
     * 动态规划思想
     * dp[i]，前i个字符的值
     * dp[i+1] = Math.min(dp[i] + 1(最坏情况), dp[i+1-word.length()](i+1参与匹配的情况，可能有多个))
     * 遍历dic的过程改为从字典树中查找，一方面一次性找到所有的匹配，另一方面减少了substring的次数
     */
    public int respaceByDpAndTrie(String[] dictionary, String sentence) {
        Trie trie = new Trie();
        Arrays.asList(dictionary).forEach(trie::addWord);

        if (sentence.length() == 0) {
            return 0;
        }
        int[] dp = new int[sentence.length() + 1];
        for (int i = 1; i <= sentence.length(); i++) {
            dp[i] = dp[i - 1] + 1;
            for (Integer matchLength : trie.searchSameSuffixLengths(sentence.substring(0, i))) {
                dp[i] = Math.min(dp[i], dp[i - matchLength]);
            }
        }
        return dp[sentence.length()];
    }


    /**
     * @param dictionary
     * @param sentence
     * @return
     * 动态规划思想
     * dp[i]，前i个字符的值
     * dp[i+1] = Math.min(dp[i] + 1(最坏情况), dp[i+1-word.length()](i+1参与匹配的情况，可能有多个))
     */
    public int respaceByDp(String[] dictionary, String sentence) {
        if (sentence.length() == 0) {
            return 0;
        }
        int[] dp = new int[sentence.length() + 1];
        for (int i = 1; i <= sentence.length(); i++) {
            dp[i] = dp[i - 1] + 1;
            for (String word : dictionary) {
                if (i >= word.length() && word.equals(sentence.substring(i - word.length(), i))) {
                    dp[i] = Math.min(dp[i], dp[i - word.length()]);
                }
            }
        }
        return dp[sentence.length()];
    }


    private Map<String, Integer> costCache;

    public int respace(String[] dictionary, String sentence) {
        costCache = Arrays.asList(dictionary).stream().collect(Collectors.toMap(Function.identity(),
                (dic) -> 0, (v1, v2) -> 0));
        return doRespace(sentence);
    }


    public int doRespace(String sentence) {
        if (sentence.length() == 0) {
            return 0;
        }
        Integer min = costCache.get(sentence);
        if (min != null) {
            return min;
        }
        if (sentence.length() == 1) {
            costCache.put(sentence, 1);
            return 1;
        }

        min = Integer.MAX_VALUE;
        for (int i = 1; i < sentence.length(); i++) {
            int cost = doRespace(sentence.substring(0, i)) + doRespace(sentence.substring(i));
            min = Math.min(min, cost);
        }
        costCache.put(sentence, min);
        return min;
    }


    public static void main(String[] args) {
//        int result = new ReSpaceLcci().respaceByDpAndTrie(new String[]{"looked","just","like","her","brother"},
//                "jesslookedjustliketimherbrother");

        int result = new ReSpaceLcci().respaceByDpAndTrie(new String[]{"qqqqqqqqq", "qqqqq", "qq", "qqq", "qqqqqq", "qqqqqq", "q"},
                "qqqq");
        System.out.println(result);
    }
}

/**
 * 字典树数据结构，能更高效的帮忙确认 目标词是否存在
 */
class Trie {

    Node root;

    public Trie() {
        this.root = new Node();
    }

    void addWord(String word) {
        Node current = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            Character target = word.charAt(i);
            Node next = current.next.get(target);
            if (next == null) {
                next = new Node();
                current.next.put(target, next);
            }
            current = next;

            if (i == 0) {
                current.isWord = true;
            }
        }
    }

    List<Integer> searchSameSuffixLengths(String word) {
        Node current = root;
        List<Integer> result = new ArrayList<>();
        for (int i = word.length() - 1; i >= 0; i--) {
            Character target = word.charAt(i);
            Node next = current.next.get(target);
            if (next == null) {
                return result;
            }
            if (next.isWord) {
                result.add(word.length() - i);
            }
            current = next;
        }
        return result;
    }



    class Node{
        public Node() {
            this.next = new HashMap<>();
        }

        Map<Character, Node> next;

        boolean isWord = false;
    }

}