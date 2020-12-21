package com.linus.lab.algorithm.array;

import java.util.*;

/**
 * @Author wangxiangyu
 * @Date 2020/12/18
 * @Description https://leetcode-cn.com/problems/avoid-flood-in-the-city/
 */
public class AvoidFloodInTheCity {

    public int[] avoidFlood(int[] rains) {
        int[] res = new int[rains.length];

        Queue<Integer> zeroQueue = new LinkedList<>();
        Map<Integer, Integer> fullLakes = new HashMap<>();// key:lake num; value:lake index
        for (int i = 0; i < rains.length; i++) {
            if (rains[i] > 0) {
                res[i] = -1;
                if (fullLakes.containsKey(rains[i])) {// try find one
                    int oldIndex = fullLakes.get(rains[i]);
                    int dryDay = dryByFirstDay(zeroQueue, oldIndex);
                    if (dryDay >= 0) {
                        fullLakes.put(rains[i], i);
                        res[dryDay] = rains[i];
                    } else {
                        return new int[]{};
                    }
                } else {
                    fullLakes.put(rains[i], i);
                }
            } else {
                res[i] = 1;//default dry
                zeroQueue.offer(i);
            }
        }
        return res;
    }

    private Integer dryByFirstDay(Queue<Integer> zeroQueue, int oldIndex) {
        Iterator<Integer> iter = zeroQueue.iterator();
        while (iter.hasNext()) {
            int zeroIndex = iter.next();
            if (zeroIndex > oldIndex) {
                iter.remove();
                return zeroIndex;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        AvoidFloodInTheCity o = new AvoidFloodInTheCity();
        int[] res = o.avoidFlood(new int[]{1, 2, 0, 0, 2, 1});
        Arrays.stream(res).forEach(System.out::println);
    }
}
