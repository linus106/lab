package com.linus.lab.algorithm.greedy;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/16
 */
public class QueueReconstructionByHeightOpt {

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (p1, p2) -> p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0]);

        LinkedList<int[]> res = new LinkedList<>();
        for (int[] person : people) {
            res.add(person[1], person);
        }
        return res.toArray(people);
    }


    public static void main(String[] args) {
        QueueReconstructionByHeightOpt o = new QueueReconstructionByHeightOpt();

        int[][] res = o.reconstructQueue(new int[][]{
                new int[]{7, 0},
                new int[]{4, 4},
                new int[]{7, 1},
                new int[]{5, 0},
                new int[]{6, 1},
                new int[]{5, 2}
        });

        for (int[] re : res) {
            System.out.println(re[0] + ":" + re[1]);
        }
    }
}
