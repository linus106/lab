package com.linus.lab.algorithm.line_sweep;

import java.util.*;

/**
 * @Author wangxiangyu
 * @Date 2020/10/28 10:24
 * @Description TODO
 * https://leetcode-cn.com/problems/perfect-rectangle/
 */
public class PerfectRectangleByLineSweep {

    Map<Integer, Integer> discretizationMap = new HashMap<>();
    int[][] yLines;//{x, start(1)|end(-1), y1, y2}
    int[] yPoints;//{y1,y2,y1,y2,...}

    public boolean isRectangleCover(int[][] rectangles) {//x1,y1,x2,y2


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
        int lastX = yLines[0][0];
        int[] yRange = null;
        List<int[]> lastCoveredRange = null;
        boolean isRectangleStarted = false;
        boolean isRectangleClosed = false;
        for (int[] yLine : yLines) {
            if (lastX != yLine[0]) {// x有变动，值得进行进一步判断
                if (lastCoveredRange.size() > 1) {//覆盖范围不能合并成一个,或者有重合
                    return false;
                }
                if (isRectangleStarted) {//矩形已经开始了
                    if (isRectangleClosed) {//矩形已经结束了
                        return false;
                    } else {
                        //矩形结束
                        if (lastCoveredRange.size() == 0) {
                            return false;
                        } else if (yRange[0] != lastCoveredRange.get(0)[0] || yRange[1] != lastCoveredRange.get(0)[1]) {//范围和上次不一致
                            return false;
                        }
                    }
                } else {//矩形刚开始并且range固定下来
                    isRectangleStarted = true;
                    yRange = lastCoveredRange.get(0);
                }
            }
            lastX = yLine[0];
            lastCoveredRange = root.update(discretizationMap.get(yLine[2]), discretizationMap.get(yLine[3]),
                    yLine[1]);
        }

        return true;
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
    private class Node {
        Node left, right;
        int start, end, mid;
        int coveredTimes;

        List<int[]> coveredRange = new LinkedList<>();

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

        List<int[]> update(int y1, int y2, int value) {
            if (y1 >= y2) return new LinkedList<>();
            if (start == y1 && end == y2) {//如果更新的线段刚好被cover，覆盖次数就+1或-1
                coveredTimes += value;
            } else {//如果没刚好被覆盖，则递归交给左右子树处理
                getLeft().update(y1, Math.min(y2, mid), value);
                getRight().update(Math.max(y1, mid), y2, value);
            }

            // 重新计算覆盖的范围
            coveredRange = new LinkedList<>();

            List<int[]> leftCovered = getLeft().coveredRange;
            List<int[]> rightCovered = getRight().coveredRange;

            coveredRange.addAll(leftCovered);
            coveredRange.addAll(rightCovered);
            // 如果两个子树的数据都不为空的话，则尝试合并。其中一个为空，则没有合并的可能性
            if (leftCovered.size() != 0 && rightCovered.size() != 0) {//[0,left-1] + [merge]  + [1,right]
                //尝试用左子树的最后一段范围和右子树第一段范围进行合并
                if (leftCovered.get(leftCovered.size() - 1)[1] == rightCovered.get(0)[0]) {//可以合并
                    int index = leftCovered.size() - 1;//左子树最后一个元素的下标
                    coveredRange.remove(index);//删除两个可以合并的元素,
                    coveredRange.remove(index);
                    coveredRange.add(index, new int[]{leftCovered.get(leftCovered.size() - 1)[0],
                            rightCovered.get(0)[1]});
                }
            }
            for (int i = 0; i < coveredTimes; i++) {//存在y线段正好和当前node匹配全覆盖
                coveredRange.add(new int[]{yPoints[start], yPoints[end]});
            }
            return coveredRange;
        }

    }


    public static void main(String[] args) {
//        boolean result = new PerfectRectangleByLineSweep().isRectangleCover(new int[][]{
//                new int[]{0, 0, 2, 2},
//                new int[]{1, 0, 2, 3},
//                new int[]{1, 0, 3, 1}
//        });

        boolean result = new PerfectRectangleByLineSweep().isRectangleCover(new int[][]{
                new int[]{1, 1, 3, 3},
                new int[]{3, 1, 4, 2},
                new int[]{1, 3, 2, 4},
                new int[]{2, 2, 4, 4}
        });


        System.out.println(result);
    }
}
