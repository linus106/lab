package com.linus.lab.algorithm.line_sweep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author wangxiangyu
 * @Date 2020/10/9 17:50
 * @Description TODO
 * https://leetcode-cn.com/problems/the-skyline-problem/
 */
public class TheSkylineProblem {

    public List<List<Integer>> getSkylineByLineSweep(int[][] buildings) {

        PriorityQueue<int[]> all = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int[] building : buildings) {
            all.offer(new int[]{building[0], -building[2]});// 符号区分大楼的开始和结束
            all.offer(new int[]{building[1], building[2]});
        }


        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<Integer> currentHeights = new PriorityQueue<>((a, b) -> a - b);
        while(!all.isEmpty()) {

        }

        return result;
    }


    /**
     * 分治+合并
     *
     * @param buildings
     * @return
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings.length == 0) {
            return new ArrayList<>();
        }
        if (buildings.length == 1) {
            return Arrays.asList(
                    Arrays.asList(buildings[0][0], buildings[0][2]),
                    Arrays.asList(buildings[0][1], 0)
            );
        }
        int mid = buildings.length >> 1;
        List<List<Integer>> left = getSkyline(Arrays.copyOfRange(buildings, 0, mid));
        List<List<Integer>> right = getSkyline(Arrays.copyOfRange(buildings, mid, buildings.length));
        return merge(left, right);
    }

    private List<List<Integer>> merge(List<List<Integer>> left, List<List<Integer>> right) {
        int leftHeight = 0, rightHeight = 0;
        int leftIndex = 0, rightIndex = 0;
        List<List<Integer>> result = new ArrayList<>();
        while (leftIndex < left.size() && rightIndex < right.size()) {
            List<Integer> point = null;
            if (left.get(leftIndex).get(0) < right.get(rightIndex).get(0)) {//先遇到left的点
                point = Arrays.asList(left.get(leftIndex).get(0), Math.max(left.get(leftIndex).get(1), rightHeight));//生成潜在的点
                leftHeight = left.get(leftIndex).get(1);//更新高度
                leftIndex++;//更新下标
            } else if (left.get(leftIndex).get(0) > right.get(rightIndex).get(0)) {// right turn
                point = Arrays.asList(right.get(rightIndex).get(0), Math.max(right.get(rightIndex).get(1), leftHeight));
                rightHeight = right.get(rightIndex).get(1);
                rightIndex++;
            } else {
                point = Arrays.asList(left.get(leftIndex).get(0), Math.max(left.get(leftIndex).get(1), right.get(rightIndex).get(1)));
                leftHeight = left.get(leftIndex).get(1);
                leftIndex++;
                rightHeight = right.get(rightIndex).get(1);
                rightIndex++;
            }
            if (result.size() == 0 || !result.get(result.size() - 1).get(1).equals(point.get(1))) {//高度重复的去掉
                result.add(point);
            }

        }
        if (leftIndex == left.size()) {
            result.addAll(right.subList(rightIndex, right.size()));
        } else {
            result.addAll(left.subList(leftIndex, left.size()));
        }
        return result;
    }


    public static void main(String[] args) {

        List<List<Integer>> result = new TheSkylineProblem().getSkyline(
                new int[][]{
                        new int[]{2, 9, 10},
                        new int[]{3, 7, 15},
                        new int[]{5, 12, 12},
                        new int[]{15, 20, 10},
                        new int[]{19, 24, 8}
                }
        );

        for (List<Integer> single : result) {
            System.out.println(single.get(0) + "-" + single.get(1));
        }

    }
}
