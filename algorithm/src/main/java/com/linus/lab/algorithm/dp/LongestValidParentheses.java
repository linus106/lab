package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/25
 * @Description TODO
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {

        int[] dp = new int[s.length() + 1];

        dp[0] = 0;
        for (int i = 1; i <= s.length(); i++) {
            if (s.charAt(i - 1) == '(') {
                dp[i] = 0;
            } else {



            }
        }
        return 0;

    }

    public static void main(String[] args) {

    }
}
