package com.linus.lab.algorithm.dp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author wangxiangyu
 * @Date 2020/12/10
 * @Description TODO
 */
public class PalindromePartitioning {

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if (s.length() == 0) return res;
        dp = new int[s.length()][s.length()];
        p = manacher(s);
        backTrace(s, 0, new LinkedList<>());
        return res;
    }

    public void backTrace(String s, int start, LinkedList<String> path) {
        if (s.length() == start) {//临界
            res.add(new ArrayList<>(path));
        }
        for (int i = start; i < s.length(); i++) {//遍历子情况
            if (isPalinedromeManacher(s, start, i)) {
                path.add(s.substring(start, i + 1));
                backTrace(s, i + 1, path);
                path.removeLast();//回溯擦除
            }
        }
    }


    private boolean isPalinedrome(String s, int begin, int end) {
        int l = begin, r = end;
        while (l <= r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    private int[] p;

    private boolean isPalinedromeManacher(String s, int begin, int end) {
        int l = 2 * begin + 1;
        int r = 2 * end + 1;
        int index = (l + r) >> 1;
        return p[index] >= ((r - l) >> 1) + 1;
    }


    private int[][] dp;

    private boolean isPalinedromeDp(String s, int begin, int end) {
        int l = begin, r = end;
        if (dp[begin][end] != 0) {
            return dp[begin][end] > 0;
        }
        while (l <= r) {
            if (s.charAt(l) != s.charAt(r)) {
                dp[begin][end] = -1;
                return false;
            }
            l++;
            r--;
        }
        dp[begin][end] = 1;
        return true;
    }


    private String manacherPrepare(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (char c : s.toCharArray()) {
            sb.append(c).append('#');
        }
        return sb.toString();
    }

    public int[] manacher(String s) {
        String mStr = manacherPrepare(s);
        int[] p = new int[mStr.length()];
        int C = 0, R = 0;
        for (int i = 0; i < mStr.length(); i++) {
            if (R > i) {
                p[i] = Math.min(p[2 * C - i], R - i);
            }
            int l, r;
            while ((r = i + p[i] + 1) < mStr.length() && (l = i - p[i] - 1) >= 0 && mStr.charAt(l) == mStr.charAt(r)) {
                p[i]++;
            }
            if (i + p[i] > R) {
                C = i;
                R = i + p[i];
            }

        }
        return p;
    }

    public static void main(String[] args) {
        PalindromePartitioning o = new PalindromePartitioning();
        List<List<String>> res = o.partition("aab");
        System.out.println(res);
    }
}
