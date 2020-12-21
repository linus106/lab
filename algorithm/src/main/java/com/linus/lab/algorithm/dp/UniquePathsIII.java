package com.linus.lab.algorithm.dp;

/**
 * @Author wangxiangyu
 * @Date 2020/12/18
 * @Description TODO
 */
public class UniquePathsIII {

    int[][] grid;
    int fullPathCount;
    int startX = -1, startY = -1;
    int m, n;
    int[][] fourDirections = new int[][]{
            new int[]{0, 1},
            new int[]{1, 0},
            new int[]{0, -1},
            new int[]{-1, 0}
    };
    int res = 0;

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        int blockCount = 0;
        this.m = grid.length;
        this.n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                } else if (grid[i][j] == -1) {
                    blockCount++;
                }
            }
        }
        this.fullPathCount = m * n - blockCount - 1;
        backTracing(startX, startY, 0);
        return res;
    }

    public void backTracing(int i, int j, int walkCount) {
        boolean inRange = i >= 0 && i < m && j >= 0 && j < n;
        if (!inRange) {
            return;
        }
        if (grid[i][j] == 2) {//to end
            if (walkCount == fullPathCount) {
                res++;
            }
        } else if (grid[i][j] == 0 || grid[i][j] == 1) {// legal node
            grid[i][j] = -1;// add block
            for (int[] direction : fourDirections) {
                backTracing(direction[0] + i, direction[1] + j, walkCount + 1);
            }
            grid[i][j] = 0;// release block
        }
    }

    public static void main(String[] args) {
        UniquePathsIII o = new UniquePathsIII();
//        int res = o.uniquePathsIII(new int[][]{
//                new int[]{1, 0, 0, 0},
//                new int[]{0, 0, 0, 0},
//                new int[]{0, 0, 2, -1}
//        });
        int res = o.uniquePathsIII(new int[][]{
                new int[]{1, 0},
                new int[]{2, 0}
        });
        System.out.println(res);
    }
}
