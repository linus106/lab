package com.linus.lab.algorithm.temp.interview;

/**
 * @Author wangxiangyu
 * @Date 2020/11/9 14:15
 * @Description TODO
 */
public class Q2 {
    public boolean rotateString(String A, String B) {
        int length = A.length();
        if (length != B.length()) return false;
        if (A.equals(B)) return true;

        for (int offset = 0; offset < length; offset++) {
            boolean match = true;
            for (int offsetA = 0; offsetA < length; offsetA++) {
                int offsetB = (offsetA + offset) % length;

                if (A.charAt(offsetA) != B.charAt(offsetB)) {
                    match =false;
                    break;
                }
            }
            if (match) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        boolean res = new Q2().rotateString("abcde", "abced");
        System.out.println(res);
    }
}
