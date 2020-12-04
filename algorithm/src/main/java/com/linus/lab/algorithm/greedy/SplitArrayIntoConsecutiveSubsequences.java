package com.linus.lab.algorithm.greedy;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author wangxiangyu
 * @Date 2020/12/4 10:53
 * @Description TODO
 */
public class SplitArrayIntoConsecutiveSubsequences {


    public boolean isPossibleOpt(int[] nums) {
        Map<Integer, Integer> countMap = Arrays.stream(nums).mapToObj(n -> n).collect(Collectors.groupingBy(Function.identity(),
                Collectors.reducing(0, e -> 1, Integer::sum)));

        Map<Integer, Integer> endMap = new HashMap<>();
        for (int num : nums) {
            int currCount = countMap.get(num);
            if (currCount > 0) {
                Integer lastValueCount = endMap.getOrDefault(num - 1, 0);
                if (lastValueCount > 0) {
                    endMap.put(num - 1, lastValueCount - 1);
                    endMap.put(num, endMap.getOrDefault(num, 0) + 1);
                } else {
                    int count2 = countMap.getOrDefault(num + 1, 0);
                    int count3 = countMap.getOrDefault(num + 2, 0);
                    if (count2 > 0 && count3 > 0) {
                        countMap.put(num + 1, count2 - 1);
                        countMap.put(num + 2, count3 - 1);
                        endMap.put(num + 2, endMap.getOrDefault(num + 2, 0) + 1);
                    } else {
                        return false;
                    }
                }
                countMap.put(num, currCount - 1);
            }
        }
        return true;
    }

    public boolean isPossible(int[] nums) {

        Map<Integer, PriorityQueue<Integer>> map = new HashMap<Integer, PriorityQueue<Integer>>();

        for (int num : nums) {
            int len = 1;

            if (map.containsKey(num - 1)) {
                PriorityQueue<Integer> pq = map.get(num - 1);
                len = pq.poll() + 1;
                if (pq.isEmpty()) map.remove(num - 1);
            }
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            map.putIfAbsent(num, pq);
            map.get(num).offer(len);
        }

        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            if (entry.getValue().peek() < 3) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SplitArrayIntoConsecutiveSubsequences o = new SplitArrayIntoConsecutiveSubsequences();
        boolean res = o.isPossibleOpt(new int[]{1, 2, 3, 4, 4, 5});
        System.out.println(res);
    }
}
