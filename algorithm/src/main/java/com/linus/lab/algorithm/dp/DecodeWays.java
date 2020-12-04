package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/2 10:51
 * @Description TODO
 */
public class DecodeWays {

    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        char last = s.charAt(0);
        dp[1] = isLegal(last) ? 1 : 0;
        for (int i = 1; i < s.length(); i++) {
            char curr = s.charAt(i);
            dp[i + 1] = (isLegal(curr) ? dp[i] : 0) +(isLegal(last, curr) ? dp[i - 1] : 0);
            last = curr;
        }
        return dp[s.length()];
    }

    private boolean isLegal(char c) {
        return c != '0';
    }

    private boolean isLegal(char first, char second) {
        return first == '1' || (first == '2' && second <= '6');
    }

    public static void main(String[] args) {
        DecodeWays o = new DecodeWays();
        int res = o.numDecodings("226");
        System.out.println(res);

    }
}
