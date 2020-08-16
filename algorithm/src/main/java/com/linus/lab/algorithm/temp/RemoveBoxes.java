package com.linus.lab.algorithm.temp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/15
 * https://leetcode-cn.com/problems/remove-boxes/
 */
public class RemoveBoxes {

    public int removeBoxes(int[] boxes) {
        int[][][] dp = new int[100][100][100];

        return doCalc(boxes, dp, 0, boxes.length - 1, 0);
    }

    public int doCalc(int[] boxes, int[][][] dp, int l, int r, int k) {
        if (r < l) {
            return 0;
        }
        while (r > l && boxes[r] == boxes[r - 1]) {
            r--;
            k++;
        }
        if (dp[l][r][k] != 0) {
            return dp[l][r][k];
        }
        int max = (k + 1) * (k + 1) + doCalc(boxes, dp, l, r - 1, 0);
        for (int i = r - 1; i >= l; i--) {
            if (boxes[i] == boxes[r]) {
                max = Math.max(max, doCalc(boxes, dp, l, i, k+1) + doCalc(boxes, dp, i+1, r-1, 0));
            }
        }
        dp[l][r][k] = max;
        return max;
    }


    public static void main(String[] args) {
//        int result = new RemoveBoxesBruteForce().removeBoxes(new int[]{1,3,2,2,2,3,4,3,1});
//        int result = new RemoveBoxesBruteForce().removeBoxes(new int[]{1});
        int result = new RemoveBoxes().removeBoxes(new int[]{3, 8, 8, 5, 5, 3, 9, 2, 4, 4, 6, 5, 8, 4, 8, 6, 9, 6, 2, 8, 6, 4, 1, 9, 5, 3, 10, 5, 3, 3, 9, 8, 8, 6, 5, 3, 7, 4, 9, 6, 3, 9, 4, 3, 5, 10, 7, 6, 10, 7});
        System.out.println(result);
    }


}


