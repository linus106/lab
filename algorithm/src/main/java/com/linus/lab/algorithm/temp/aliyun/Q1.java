package com.linus.lab.algorithm.temp.aliyun;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/5
 * https://tianchi.aliyun.com/oj/15179470890799741/85251759933690467
 */
public class Q1 {

    /**
     * @param a: Left margin
     * @param b: Right margin
     * @return: return the greatest common multiple
     */
    public long greatestcommonmultiple(int a, int b) {
        // write your code here

        long n = b;

        if (n - a == 2) {
            return n * (n - 1) * (n - 2) / (n % 2 == 0 ? 2: 1);
        }

        if (n % 2 == 1) {
            return n * (n - 1) * (n - 2);
        } else {
            if (n %3 == 0) {
                return (n -1) * (n - 2) * (n - 3);
            } else {
                return n * (n - 1) * (n - 3);
            }
        }
    }
//    public long greatestcommonmultiple(int a, int b) {
//
//
//        if (b % 2 == 1)//奇数
//            return long(b) * (b - 1) * (b - 2);//奇偶奇
//        else if (b % 3 != 0)//不是奇数，是偶数，但不能被3整除
//            //b = 8; 8，7，5 ---> 280
//            // 如果是 8, 7, 6 ---> 168
//            // 如果是 7，6，5 ---> 210
//            return (b) * (b - 1) * (b - 3);
//        return (b - 1) * (b - 2) * (b - 3);
//    }


    public static void main(String[] args) {
//        System.out.println(new Q1().greatestcommonmultiple(4, 12));

//
//        for (int i = 300;i<=1500;i++) {
//            long n = i * (i+1) * (i+2);
//            if (n == 345963416 || n == 691926832) {
//                System.out.println(i);
//            }
//        }
//
//        for (int i = 690;i<=750;i++) {
//            long n = i * (i+2) * (i+3);
//            if (n == 345963416) {
//                System.out.println(i);
//            }
//        }

//        for (int i = 690; i < 750;i++) {//normal
//            long n = i * (i+1) * (i+2);
//            System.out.println(i + ":" + n);
//            if (n == 345963416) {
//                System.out.println("bingo:" + i);
//            }
//        }

//        for (int i = 880; i < 910;i++) {// 2x
//            long n = i * (i+1) * (i+2) / 2;
//            System.out.println(i + ":" + n);
//            if (n == 345963416) {
//                System.out.println("bingo:" + i);
//            }
//        }

//          for (int i = 690; i < 750;i++) {// 2x
//            long n = i * (i+2) * (i+3);
//            System.out.println(i + ":" + n);
//            if (n == 345963416) {
//                System.out.println("bingo:" + i);
//            }
//        }

//          for (int i = 690; i < 750;i++) {// 2x
//            long n = new Q1().greatestcommonmultiple(i-2, i);
//            System.out.println(i + ":" + n);
//            if (n == 345963416) {
//                System.out.println("bingo:" + i);
//            }
//        }

        int n = 345963416;

        int i = 2;
        while (n > 1) {
            if (n % i == 0) {
                System.out.println(i);
                System.out.println(n / i);
                n = n / i;
            } else {
                i++;
            }

        }
    }
}
