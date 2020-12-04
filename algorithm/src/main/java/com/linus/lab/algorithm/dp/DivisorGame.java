package com.linus.lab.algorithm.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangxiangyu
 * @Date 2020/12/4
 * @Description leetcode-cn.com/problems/divisor-game
 */
public class DivisorGame {


    /**
     * 奇数-奇数必然变成偶数
     * 偶数-1 可以变成奇数
     *
     * 因此面对偶数的人可以一直让对方陷入奇数
     * 面对奇数的人只能返回一个偶数
     *
     * 初始值：
     * 1 : false
     * 2 : true
     * 3 ：fasle
     *
     * 因此拿到偶数的人必胜，反之必败
     */
    public boolean divisorGame(int N) {
        return N % 2==0;
    }

    public static void main(String[] args) {

        DivisorGame o = new DivisorGame();
        boolean res = o.divisorGame(9);
        System.out.println(res);

    }
}
