package com.linus.lab.algorithm.sampling_random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author wangxiangyu
 * @Date 2020/9/29 16:37
 * @Description TODO
 */
public class RandomPointInNonOverlappingRectangles {

    private int[] range;
    private int[][] rects;
    Random randomObj  = new Random();

    public RandomPointInNonOverlappingRectangles(int[][] rects) {
        range = new int[rects.length + 1];
        this.rects = rects;
        for (int i = 0; i < rects.length; i++) {
            int[] rect = rects[i];
            int length = (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
            range[i + 1] = range[i] + length;
        }
    }

    public int[] pick() {
        int random = randomObj.nextInt(range[range.length - 1]);

        int l = 0, r = range.length - 2;
        while (r >= l) {
            int mid = (l + r) / 2;
            if (random >= range[mid + 1]) {
                l = mid + 1;
            } else if (random < range[mid]) {
                r = mid - 1;
            } else {
                int offset = random - range[mid];
                int[] rect = rects[mid];
                int rowLength = rect[2] - rect[0] + 1;
                return new int[]{rect[0] + offset%rowLength, rect[1] + offset/rowLength};
            }
        }
        return null;
    }


    public static void main(String[] args) {
        for (int j = 0 ;j < 10; j ++) {
            int[] result = new RandomPointInNonOverlappingRectangles(new int[][] {
                    new int []{1,1,5,5}
            }).pick();

            for (int i : result) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
