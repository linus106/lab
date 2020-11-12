package com.linus.lab.algorithm.palindrome;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/12
 */
public class LongestPalindromicSubstring {

    private String manacherPrepare(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (char c : s.toCharArray()) {
            sb.append(c).append('#');
        }
        return sb.toString();
    }

    public String longestPalindrome(String s) {
        String mStr = manacherPrepare(s);
        int[] p = new int[mStr.length()];
        int C = 0, R = 0;
        int maxIndex = 0;
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
            if (p[i] > p[maxIndex]) {
                maxIndex = i;
            }
        }

        return s.substring((maxIndex - p[maxIndex]) / 2, (maxIndex + p[maxIndex]) / 2);
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring o = new LongestPalindromicSubstring();
        System.out.println(o.longestPalindrome("babad"));

    }
}
