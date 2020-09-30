package com.linus.lab.algorithm.sampling_random;

import java.util.*;

/**
 * @Author wangxiangyu
 * @Date 2020/9/30 14:11
 * @Description TODO
 */
public class RandomPickWithBlacklist {

    private Map<Integer, Integer> mapping = new HashMap<>();

    private int avaliableCount = 0;

    Random randomHelper = new Random();

    public RandomPickWithBlacklist(int N, int[] blacklist) {

        avaliableCount = N - blacklist.length;
        Set<Integer> whiteSet = new HashSet<>();
        for (int i = avaliableCount ; i < N; i++ ) {
            whiteSet.add(i);
        }
        for (int black : blacklist) {
            whiteSet.remove(black);
        }

        Iterator<Integer> iter = whiteSet.iterator();
        for (int black : blacklist) {
            if (black < avaliableCount) {
                mapping.put(black, iter.next());
            }
        }
    }

    public int pick() {
        Integer random = randomHelper.nextInt(avaliableCount);
        return mapping.getOrDefault(random, random);
    }

    public static void main(String[] args) {
        RandomPickWithBlacklist obj = new RandomPickWithBlacklist(5, new int[]{3, 2, 0});

        for (int j = 0 ;j < 10; j ++) {
            int result = obj.pick();
            System.out.println(result);
        }
    }
}
