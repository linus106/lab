package com.linus.lab.algorithm.temp.aliyun;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/6
 */
public class Q7 {

    /**
     * @param b:
     * @param c:
     * @param p:
     * @return: return the a
     */
    public double lotteryDraw(int b, int c, int p) {//用二分法逼近解
        // write your code here
        double l = 1, h = 100;

        while (h > l) {
            double mid = (h + l) / 2;
            double calP = calcP(mid, b, c);
            if (Math.abs(calP - p) <= 0.0001) {
                return mid;
            } else if (calP > p) {
                h = mid;
            } else {
                l = mid;
            }
        }
        return -1;
    }

    public double calcP (double a, int b, int c) {//写出正向计算公式
        // write your code here
        double pNotMacthYet = 1;
        double pMatch = a / 100;
        double expect = 0;
        for (int i = 1; i <= b - 1; i++) {
            expect += pNotMacthYet * pMatch * i;
            pNotMacthYet *= (1 - pMatch);
        }


        int j = 1;
        for (; a + c * j < 100; j++) {
            pMatch = (a + j * c) /100;
            expect += pNotMacthYet * pMatch * (b - 1 + j);
            pNotMacthYet *= (1- pMatch);
        }

        expect += pNotMacthYet * 1;

        return 100 / expect;
    }


    public static void main(String[] args) {
        System.out.println(new Q7().lotteryDraw(10,1,10));
    }
}
