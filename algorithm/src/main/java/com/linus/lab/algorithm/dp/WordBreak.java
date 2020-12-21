package com.linus.lab.algorithm.dp;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/12/20
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        final Set<String> wordSet = wordDict.stream().collect(Collectors.toSet());

        boolean[] dp = new boolean [s.length() + 1];

        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {
        WordBreak o =new WordBreak();

        final boolean res = o.wordBreak("applepenapple", Arrays.asList("apple", "pen"));
        System.out.println(res);
    }
}
