package com.linus.lab.algorithm.memory;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author wangxiangyu
 * @Date 2020/8/10 17:27
 * @Description TODO
 */
public class DivingBoardLcci {


    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[]{};
        }
        int base = shorter * k;
        int cut = longer - shorter;
        if (cut == 0) {
            return new int[]{base};
        }
        int[] result = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            result[i] = base + i * cut;
        }
        return result;
    }


    public static void main(String[] args) {
        int[] result = new DivingBoardLcci().divingBoard(1, 2, 3);
        System.out.println(result);
    }
}
