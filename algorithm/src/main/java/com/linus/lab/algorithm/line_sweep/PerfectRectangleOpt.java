package com.linus.lab.algorithm.line_sweep;

import javafx.util.Pair;

import java.util.*;

/**
 * @Author wangxiangyu
 * @Date 2020/10/28 10:24
 * @Description TODO
 * https://leetcode-cn.com/problems/perfect-rectangle/
 * 主要是利用了矩形的特性,数据准备：
 * 1、找到x,y的最大最小值。
 * 2、记录总共4N个点的出现次数
 * <p>
 * 完美矩形满足以下的充分必要条件:
 * 1、小矩形面积和 与大矩形的面积一致。
 * 2、4个最大顶点存在并且出现次数为1。
 * 3、最大矩形边上的点出现的次数为2，
 * 4、矩形内的点出现的次数为2或者4。
 */
public class PerfectRectangleOpt {


    public boolean isRectangleCover(int[][] rectangles) {//x1,y1,x2,y2

        Map<Pair, Integer> pointNum = new HashMap<>();
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        int top = Integer.MIN_VALUE, bottom = Integer.MAX_VALUE;
        long sumArea = 0;
        for (int[] rectangle : rectangles) {
            left = Math.min(left, rectangle[0]);
            right = Math.max(right, rectangle[2]);
            top = Math.max(top, rectangle[3]);
            bottom = Math.min(bottom, rectangle[1]);

            Pair leftBottom = new Pair(rectangle[0], rectangle[1]);
            Pair leftTop = new Pair(rectangle[0], rectangle[3]);
            Pair rightBottom = new Pair(rectangle[2], rectangle[1]);
            Pair rightTop = new Pair(rectangle[2], rectangle[3]);
            pointNum.put(leftBottom, pointNum.getOrDefault(leftBottom, 0) + 1);
            pointNum.put(leftTop, pointNum.getOrDefault(leftTop, 0) + 1);
            pointNum.put(rightBottom, pointNum.getOrDefault(rightBottom, 0) + 1);
            pointNum.put(rightTop, pointNum.getOrDefault(rightTop, 0) + 1);

            sumArea += ((rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]));
        }


        if (sumArea == (top - bottom) * (right - left)) {

            // 把最大矩形的顶点先加一 =2，方便统一判断。

            Pair leftBottom = new Pair(left, bottom);
            Pair leftTop = new Pair(left, top);
            Pair rightBottom = new Pair(right, bottom);
            Pair rightTop = new Pair(right, top);
            pointNum.put(leftBottom, pointNum.getOrDefault(leftBottom, 0) + 1);
            pointNum.put(leftTop, pointNum.getOrDefault(leftTop, 0) + 1);
            pointNum.put(rightBottom, pointNum.getOrDefault(rightBottom, 0) + 1);
            pointNum.put(rightTop, pointNum.getOrDefault(rightTop, 0) + 1);

            //存在不为2或者4的点，说明有覆盖和漏的情况同时存在
            return !pointNum.values().stream().filter(n-> n != 2 && n!=4).findAny().isPresent();
        }

        return false;
    }


    public static void main(String[] args) {

//        boolean result = new PerfectRectangleOpt().isRectangleCover(new int[][]{
//                new int[]{1, 1, 3, 3},
//                new int[]{3, 1, 4, 2},
//                new int[]{1, 3, 2, 4},
//                new int[]{2, 2, 4, 4}
//        });

        boolean result = new PerfectRectangleOpt().isRectangleCover(new int[][]{
                new int[]{1, 1, 3, 3},
                new int[]{3, 1, 4, 2},
                new int[]{3, 2, 4, 4},
                new int[]{1, 3, 2, 4},
                new int[]{2, 3, 3, 4}
        });


        System.out.println(result);


//
//        System.out.println(new int[]{1,2}.hashCode());
//        System.out.println(new int[]{1,2}.hashCode());
    }
}
