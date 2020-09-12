package com.linus.lab.algorithm.temp.aliyun;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/6
 */
public class Q6 {

    /**
     * @param inputQueries: input Queries, means [[m1, d1, m2, d2, x], [m1, d1, m2, d2, x],...]
     * @return: guess whether y1 is leep year
     */
    public String guessYear(int[][] inputQueries) {
        // write your code here
        StringBuilder res = new StringBuilder();
        for (int[] query : inputQueries) {
            res.append(singleQuery(query));
        }
        return res.toString();
    }

    static int[] BASE_DAYS_4_MONTH = new int[]{0,31,59,90,120,151,181,212,243,273,304,334};

    public String singleQuery(int[] query) {
        int m1 = query[0], d1 = query[1], m2 = query[2], d2 = query[3], x = query[4];


        int t1 = BASE_DAYS_4_MONTH[m1 - 1] + d1;
        int t2 = BASE_DAYS_4_MONTH[m2 - 1] + d2;


        boolean skipYear = t1 > t2;


        if (m1 == 2 && d1 ==29) return "R";
        if (skipYear && m2 == 2 && d2 ==29) return "P";


        boolean a = !skipYear && m2>=3 && m1<=2;
        boolean b = skipYear && m1 <=2;
        boolean c = skipYear && m2 >=3;
        boolean existR = (t1 + x - t2) %365 !=0;

        if (a || b) {
            if (existR) {
                return "R";
            } else {
                return "P";
            }
        } else if (c) {
            if (existR) {
                return "P";
            } else {
                return "?";
            }
        } else {
            return "?";
        }
    }

    public static void main(String[] args) {
        System.out.println(new Q6().singleQuery(new int[]{2,28,3,2,2}));
        System.out.println(new Q6().singleQuery(new int[]{2,28,3,1,2}));
        System.out.println(new Q6().singleQuery(new int[]{12,31,1,1,1}));
        System.out.println(new Q6().singleQuery(new int[]{2,16,1,23,341}));
        System.out.println(new Q6().singleQuery(new int[]{2,29,2,29,0}));

        System.out.println(new Q6().singleQuery(new int[]{2,28,3,1,2}));
    }
}
