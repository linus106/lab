package com.linus.lab.algorithm.dp;

import java.util.*;

/**
 * @Author wangxiangyu
 * @Date 2020/12/3 16:47
 * @Description TODO
 * https://leetcode-cn.com/problems/minimum-jumps-to-reach-home/
 */
public class MinimumJumpsToReachHome {


    private class Task {
        int pos;
        int lastMove;//1-back 0-forward or null
        int moves;

        public Task(int pos, int lastMove, int moves) {
            this.pos = pos;
            this.lastMove = lastMove;
            this.moves = moves;
        }
    }

    /**
     * 题目设定说明的信息可证明跳跃的区间在0-6000
     * 把visit标记和forbidden标记整合,前进之后设置visit
     * BFS
     */
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        if (x == 0) return 0;
        int[] forbid = new int[6001];
        Arrays.stream(forbidden).forEach(i->forbid[i] = 1);
        Queue<Task> queue = new LinkedList<>();
        queue.offer(new Task(0, 0, 0));
        forbid[0] = 1;

        while (!queue.isEmpty()) {
            Task t = queue.poll();
            if (t.pos == x) {
                return t.moves;
            }

            // forward
            int nextPost = t.pos + a;
            if (nextPost <= 6000 && forbid[nextPost] == 0) {
                forbid[nextPost] = 1;
                queue.offer(new Task(nextPost, 0, t.moves + 1));
            }

            if (t.lastMove == 0) {//back
                nextPost = t.pos - b;
                if (nextPost >=0 && forbid[nextPost] == 0) {
                    queue.offer(new Task(nextPost, 1, t.moves + 1));
                }
            }

        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
