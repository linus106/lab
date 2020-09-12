package com.linus.lab.algorithm.temp;

import java.util.*;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/16
 * https://leetcode-cn.com/contest/weekly-contest-203/problems/find-latest-group-of-size-m/
 */
public class Q11 {

//    public int findLatestStep(int[] arr, int m) {
//
//        int last = -1;
//        LinkedList<Integer> list = new LinkedList<>();
//        list.add(0);
//        list.add(arr.length + 1);
//        for (int i = arr.length - 1; i >= 0; i--) {
//            int index = arr[i];
//
//            int begin = list.getFirst();
//            int end = list.getLast();
//
//            Iterator<Integer> iterator = list.iterator();
//            while (iterator.hasNext()) {
//                int val = iterator.next();
//                if (val < index) {
//                    begin = val;
//                } else if (val > index) {
//                    end = val;
//                    break;
//                }
//            }
//
//
//            if (end - index == m || index - begin == m) {
//                return i;
//            }
//
//            list.addFirst();
//        }
//
//        return last;
//    }
//
//    public int help(int[] arr, int m, int l ,int r) {
//        if (r - l < m) {
//            return -1;
//        }
//
//        int last = -1;
//        for (int i = 0; i < arr.length; i++) {
//
//        }
//
//        return last;
//    }

//
//
//    public static void main(String[] args) {
////        List<Integer> result = new Q9().mostVisited(4, new int[]{1, 3, 1, 2});
//
//        int result = new Q11().maxCoins(new int[]{});
//        System.out.println(result);
//    }

}
