package com.linus.lab.algorithm.line_sweep;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/11
 */
public class RectangleAreaII {

    public int rectangleArea(int[][] rectangles) {
        Map<Integer, List<Pair<Integer, Integer>>> all = new HashMap<>();

        for (int i = 0; i < rectangles.length; i++) {
            int[] rectangle = rectangles[i];//{x1,y1,x2,y2}
            Pair<Integer, Integer> pair = new Pair(rectangle[1], rectangle[3]);

            all.putIfAbsent(rectangle[0], new ArrayList<>());
            all.get(rectangle[0]).add(pair);

            all.putIfAbsent(rectangle[2], new ArrayList<>());
            all.get(rectangle[2]).add(pair);
        }

        TreeSet<Pair<Integer, Integer>> currentLines = new TreeSet<>((a,b)-> {
            if (a == b) return 0;
            if (a.getKey() - b.getKey() != 0) return a.getKey() - b.getKey();
            if (a.getValue() - b.getValue() != 0) return a.getValue() - b.getValue();
            return System.identityHashCode(a) - System.identityHashCode(b);
        });
        long sumArea = 0;
        int lastX = -1;
        int lastHeight = 0;
        List<Map.Entry<Integer, List<Pair<Integer, Integer>>>> ordered =
                all.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey)).collect(Collectors.toList());
        for (Map.Entry<Integer, List<Pair<Integer, Integer>>> entry : ordered) {
            Integer x = entry.getKey();
            for (Pair<Integer, Integer> line : entry.getValue()) {
                if (currentLines.contains(line)) {
                    currentLines.remove(line);
                } else {
                    currentLines.add(line);
                }
            }
            if (x != lastX) {
                sumArea += (long)(x - lastX) * lastHeight;
                lastX = x;
                lastHeight = calcHeight(currentLines);//TODO recalc height
            }
        }
        return Long.valueOf(sumArea % 1000000007).intValue();
    }

    private int calcHeight(TreeSet<Pair<Integer, Integer>> lineSet) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        for (Pair<Integer, Integer> pair : lineSet) {
            pq.offer(new int[]{pair.getKey(), 1});
            pq.offer(new int[]{pair.getValue(), -1});
        }
        int currentNum = 0;
        int lastX = 0;
        int totalLength = 0;
        while (!pq.isEmpty()) {
            int point[] = pq.poll();
            if (lastX != point[0] && currentNum > 0) {
                totalLength += (point[0] - lastX);
            }
            currentNum += point[1];
            lastX = point[0];
        }

        return totalLength;
    }

    public static void main(String[] args) {

    }
}
