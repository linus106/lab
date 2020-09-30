package com.linus.lab.algorithm.sampling_random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author wangxiangyu
 * @Date 2020/9/30 10:43
 * @Description TODO
 */
public class RandomFlipMatrix {


    private int currentSize;
    private int rows;
    private int cols;
    private Random randomHelper = new Random();
    private Map<Integer, Integer> occupied = new HashMap<>();

    public RandomFlipMatrix(int n_rows, int n_cols) {
        rows = n_rows;
        cols = n_cols;
        currentSize = n_rows * n_cols;
    }

    public int[] flip() {
        Integer random = randomHelper.nextInt(currentSize--);
        int offset = occupied.getOrDefault(random, random);
        occupied.put(random, occupied.getOrDefault(currentSize, currentSize));
        return new int[]{offset / cols, offset % cols};
    }

    public void reset() {
        occupied.clear();
        currentSize = rows * cols;
    }

    public static void main(String[] args) {
        RandomFlipMatrix object = new RandomFlipMatrix(1, 2);
        for (int i = 0;i < 2;i ++) {
            int[] result = object.flip();
            for (int j : result) {
                System.out.print(j);
                System.out.print(" ");
            }
            System.out.println();
        }

    }
}
