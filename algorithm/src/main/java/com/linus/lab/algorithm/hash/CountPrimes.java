package com.linus.lab.algorithm.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @Author wangxiangyu
 * @Date 2020/12/3 15:21
 * @Description TODO
 * https://leetcode-cn.com/problems/count-primes
 */
public class CountPrimes {


    /**
     * 线性筛
     * 避免重复标记
     *
     * 一个合数由  i * smallest_prime负责标记
     * 12  由    6 * 2负责标记
     * 8   由    4 * 2负责标记
     * 6   由    3 * 2负责标记
     *
     */
    public int countPrimesOpt2(int n) {
        List<Integer> primeList = new ArrayList<>();
        int[] isPrime = new int[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i] == 0) {//is prime
                primeList.add(i);
                count++;
            }
            for (int j = 0; j < primeList.size() && primeList.get(j) < n; j++) {
                isPrime[i * primeList.get(j)] = 1;
                if (i % primeList.get(j) == 0) {
                    break;
                }
            }
        }
        return count;
    }

    /**
     * 埃式筛
     */
    public int countPrimesOpt1(int n) {
        int[] isPrime = new int[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i] == 0) {//is prime
                count++;
                if ((long)i * i < n) {
                    for (int j = i * i; j < n; j += i) {//从i * i开始
                        isPrime[(int)j] = 1;
                    }
                }

            }
        }
        return count;
    }


    /**
     * 暴力 + sqrt优化
     */
    public int countPrimes(int n) {
        List<Integer> primeList = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            boolean isPrime = true;
            int limit = (int) Math.sqrt(i);
            for (int j = 0; j < primeList.size() && primeList.get(j) <= limit; j++) {
                if (i % primeList.get(j) == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) primeList.add(i);
        }
        return primeList.size();
    }

    public static void main(String[] args) {

        CountPrimes o = new CountPrimes();
        int res = o.countPrimesOpt1(10);
        System.out.println(res);
    }


}
