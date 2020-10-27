package com.linus.lab.algorithm.line_sweep;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/11
 * https://leetcode-cn.com/problems/rectangle-area-ii/submissions/
 */
public class RectangleAreaIIByLineTree {


    Map<Integer, Integer> discretizationMap = new HashMap<>();
    int[][] yLines;//{x, start(1)|end(-1), y1, y2}
    int[] yPoints;//{y1,y2,y1,y2,...}

    public int rectangleArea(int[][] rectangles) {//x1,y1,x2,y2


        //1 准备两部分数据：沿着x轴扫描线的竖线数组 and 所有矩形在y轴上的坐标映射
        yLines = new int[rectangles.length * 2][];
        yPoints = new int[rectangles.length * 2];
        int i = 0, j = 0;
        for (int[] rectangle : rectangles) {
            yLines[i++] = new int[]{rectangle[1], 1, rectangle[0], rectangle[2]};
            yLines[i++] = new int[]{rectangle[3], -1, rectangle[0], rectangle[2]};
            yPoints[j++] = rectangle[0];
            yPoints[j++] = rectangle[2];
        }
        Arrays.sort(yLines, Comparator.comparingInt(yLine -> yLine[0]));
        Arrays.sort(yPoints);

        //2 对y轴上的点离散化处理，减少后边线段树的计算量
        //discretization   for example: 100,200,300,300,301 map to 0,1,3,3,4; could be duplicated
        for (int k = 0; k < yPoints.length; k++) {
            discretizationMap.put(yPoints[k], k);
        }


        //3 按x从小到大的顺序，循环竖线数组，累加计算面积
        Node root = new Node(0, yPoints.length - 1);
        int lastX = -1;
        int yLength = 0;
        long sumArea = 0;
        for (int[] yLine : yLines) {
            sumArea += (long)(yLine[0] - lastX) * yLength;
            lastX = yLine[0];
            yLength = root.update(discretizationMap.get(yLine[2]), discretizationMap.get(yLine[3]), yLine[1]);
        }

        return Long.valueOf(sumArea % 1000000007).intValue();
    }


    /**
     * 线段树     覆盖次数0|覆盖长度51(长度需要通过yPoints 进行离散化的还原)
     *                  |
     *               [0,99]
     *           [0,49]  [49,99]   ->   覆盖次数1|覆盖长度50
     *         ....................
     *      [0,1] [1,2] [97,98] [98,99]
     *       |
     * 覆盖次数2|覆盖长度1
     */
    class Node {
        Node left, right;
        int start, end, mid;
        int coveredLength, coveredTimes;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.mid = (start + end) / 2;
        }

        public Node getLeft() {
            if (left == null) left = new Node(start, mid);
            return left;
        }

        public Node getRight() {
            if (right == null) right = new Node(mid, end);
            return right;
        }

        int update(int y1, int y2, int value) {
            if (y1 >= y2) return 0;
            if (start == y1 && end == y2) {//如果更新的线段刚好被cover，覆盖次数就+1或-1
                coveredTimes += value;
            } else {//如果没刚好被覆盖，则递归交给左右子树处理
                getLeft().update(y1, Math.min(y2, mid), value);
                getRight().update(Math.max(y1, mid), y2, value);
            }

            // 上面更新完，这里计算更新后的值，如果当前节点刚好覆盖，则直接到yPoints找实际的覆盖长度
            // 如果没被当前node覆盖，则递归左右子树，把两边覆盖的范围相加。
            coveredLength = coveredTimes > 0 ? (yPoints[end] - yPoints[start])
                    : getLeft().coveredLength + getRight().coveredLength;
            return coveredLength;
        }

    }


    public static void main(String[] args) {
        int result = new RectangleAreaIIByLineTree().rectangleArea(new int[][]{
                new int[]{0, 0, 2, 2},
                new int[]{1, 0, 2, 3},
                new int[]{1, 0, 3, 1}
        });
        System.out.println(result);
    }
}
