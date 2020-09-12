package com.linus.lab.algorithm.temp.aliyun;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/5
 */
public class Q4 {

    /**
     * @param s: string need to be transformed
     * @param k: minimum char can be transformed in one operation
     * @return: minimum times of transforming all char into '1'
     */
    public int perfectString(String s, int k) {
        // Write your code here.


        int temp = -1;
        int sum = 0;
        for (int i =0;i < s.length();i++) {
            if (s.charAt(i) == '0' && temp == -1) {//0 range start
                temp = i;
            } else if (s.charAt(i) == '1' && temp != -1) {//0 range end
                sum += ((i - temp - 1) / k + 1);
                temp = -1;
            }
        }
        if (temp != -1) {
            sum += ((s.length() - temp - 1) / k + 1);
        }
        return sum;
    }

    public static void main(String[] args) {
        int result = new Q4().perfectString("000", 3);
        System.out.println(result);
    }
}
