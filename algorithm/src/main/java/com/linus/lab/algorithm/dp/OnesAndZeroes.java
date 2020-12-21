package com.linus.lab.algorithm.dp;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/12/20
 * https://leetcode-cn.com/problems/ones-and-zeroes/
 */
public class OnesAndZeroes {

    public int findMaxForm(String[] strs, int m, int n) {


        //init
        int[][][] dp = new int[strs.length][m + 1][n + 1];


        //init for i=0
        String str0 = strs[0];
        int zeroNum0 = 0, oneNum0 = 0;
        for (char c : str0.toCharArray()) {
            if (c == '0') {
                zeroNum0++;
            } else {
                oneNum0++;
            }
        }
        for (int j = zeroNum0; j <= m; j++) {
            for (int k = oneNum0; k <= n; k++) {
                dp[0][j][k] = 1;
            }
        }


        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            int zeroNum = 0, oneNum = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    zeroNum++;
                } else {
                    oneNum++;
                }
            }

            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j >= zeroNum && k >= oneNum) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeroNum][k - oneNum] + 1);
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }
        return dp[strs.length - 1][m][n];

    }

    public static void main(String[] args) {
        OnesAndZeroes o = new OnesAndZeroes();
        final int res = o.findMaxForm(new String[]{"10","0001","111001","1","0"}, 5, 3);
        System.out.println(res);

    }
}
