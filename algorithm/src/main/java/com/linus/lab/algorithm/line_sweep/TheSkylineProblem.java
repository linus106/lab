package com.linus.lab.algorithm.line_sweep;

import com.sun.javafx.geom.transform.Identity;

import java.util.*;

/**
 * @Author wangxiangyu
 * @Date 2020/10/9 17:50
 * @Description TODO
 * https://leetcode-cn.com/problems/the-skyline-problem/
 */
public class TheSkylineProblem {


    /**
     * TreeMap红黑树的删增效率比PriorityQueue的堆要更高一些；40ms vs 317ms
     *
     */
    public List<List<Integer>> getSkylineByLineSweepTreeMap(int[][] buildings) {

        PriorityQueue<int[]> all = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int[] building : buildings) {
            all.offer(new int[]{building[0], -building[2]});// 符号区分大楼的开始和结束
            all.offer(new int[]{building[1], building[2]});
        }


        List<List<Integer>> result = new ArrayList<>();
        TreeMap<Integer, Integer> currentHeights = new TreeMap<>(Comparator.comparingInt(Integer::intValue));
        currentHeights.put(0, 0);
        int lastHeight = 0;
        while(!all.isEmpty()) {
            int[] point = all.poll();
            if (point[1] < 0) {//start of building
                currentHeights.put(point[1], currentHeights.getOrDefault(point[1], 0) + 1);
            } else {//end of building
                currentHeights.put(-point[1], currentHeights.get(-point[1]) - 1);
                if (currentHeights.get(-point[1]) == 0) currentHeights.remove(-point[1]);
            }
            if (lastHeight != -currentHeights.firstKey()) {
                lastHeight =  -currentHeights.firstKey();
                result.add(Arrays.asList(point[0], lastHeight));
            }
        }

        return result;
    }

    public List<List<Integer>> getSkylineByLineSweep(int[][] buildings) {

        PriorityQueue<int[]> all = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int[] building : buildings) {
            all.offer(new int[]{building[0], -building[2]});// 符号区分大楼的开始和结束
            all.offer(new int[]{building[1], building[2]});
        }


        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<Integer> currentHeights = new PriorityQueue<>(Comparator.comparingInt(Integer::intValue));
        currentHeights.add(0);
        int lastHeight = 0;
        while(!all.isEmpty()) {
            int[] point = all.poll();
            if (point[1] < 0) {//start of building
                currentHeights.offer(point[1]);
            } else {//end of building
                currentHeights.remove(-point[1]);
            }
            if (lastHeight != -currentHeights.peek()) {
                lastHeight =  -currentHeights.peek();
                result.add(Arrays.asList(point[0], lastHeight));
            }
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

        List<List<Integer>> result = new TheSkylineProblem().getSkylineByLineSweepTreeMap(
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
