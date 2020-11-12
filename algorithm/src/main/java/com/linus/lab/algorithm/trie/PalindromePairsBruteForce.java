package com.linus.lab.algorithm.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/11
 */
public class PalindromePairsBruteForce {

    public List<List<Integer>> palindromePairs(String[] words) {
        int length = words.length;

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (j != i && isPalindrome(words[i] + words[j])) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;

    }

    private boolean isPalindrome(String str) {
        int start = 0, end = str.length() - 1;
        while (start <= end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        List<List<Integer>> res = new PalindromePairsBruteForce().palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"});
        System.out.println(res);
    }
}
