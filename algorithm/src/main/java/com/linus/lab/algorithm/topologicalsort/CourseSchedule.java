package com.linus.lab.algorithm.topologicalsort;

import jdk.nashorn.internal.runtime.options.Option;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author wangxiangyu
 * @Date 2020/9/27 15:44
 * @Description https://leetcode-cn.com/problems/course-schedule/
 */
public class CourseSchedule {


    /**
     * @param numCourses
     * @param prerequisites
     * @return
     * 找到没有前继的课程，依次其后继边，如果后继节点的不再存在前继课程，则把后继节点加入队列，循环删除。
     */
    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {

        // 1 init variables
        int[] predecessorsCounts = new int[numCourses];
        List<List<Integer>> succesorsLists = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0;i<numCourses;i++) {
            succesorsLists.add(new ArrayList<>());
        }

        // 2 fill value
        for (int[] prerequisite : prerequisites) {
            predecessorsCounts[prerequisite[0]]++;
            succesorsLists.get(prerequisite[1]).add(prerequisite[0]);
        }

        // 3 init queue
        for(int i = 0; i < numCourses; i++) {
            if(predecessorsCounts[i] == 0) queue.add(i);
        }

        // 4 excute queue
        while (!queue.isEmpty()) {
            int course = queue.poll();
            numCourses--;
            for(int succesor : succesorsLists.get(course)) {
                if (--predecessorsCounts[succesor] == 0) {
                    queue.add(succesor);
                }
            }
        }
        return numCourses == 0;
    }


    /**
     * 类似spring的循环依赖思想
     *
     * 1 要(学|评估)一门课，先要(学|评估)他的前置课。
     * 2 先尝试看看这门课是否评估完成，如果完成直接返回。
     * 3 先标记这门课正在(学|评估)，在去(学|评估)前置课
     * 4 如果课程没有其他前置课，则(学|评估)这门课
     * 5 标记这门课为(学|评估)完成
     * 6 如果在标记正在(学|评估)的时候发现已经正在(学|评估)，则说明有循环依赖
     *
     * 1 要创建一个bean，要先创建依赖的bean。
     * 2 先从一级缓存中取，如果有直接返回。
     * 3 先标记这个bean正在创建，然后在去创建依赖的bean
     * 4 如果bean没有其他任何依赖，就开始创建自己
     * 5 创建完了把bean放到一级缓存
     *
     * 6 如果在标记正在(学|评估)的时候发现已经创建，则说明有环
     * 先标记正在创建，在去创建依赖的其他bean
     */


    private List<List<Integer>> succesorsLists;
    private int[] staus ;//0 not learn yet  1 learning 2learned
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {

        // 1 init variables
        succesorsLists = new ArrayList<>();
        staus = new int[numCourses];
        for (int i=0;i<numCourses;i++) {
            succesorsLists.add(new ArrayList<>());
        }

        // 2 fill value
        for (int[] prerequisite : prerequisites) {
            succesorsLists.get(prerequisite[1]).add(prerequisite[0]);
        }

        for (int course = 0; course < numCourses; course++) {
            if (!dfs(course)) return false;
        }
        return true;
    }

    public boolean dfs(int course) {
        if (staus[course] == 2) return true;
        if (staus[course] == 1) return false;
        staus[course] = 1;
        for (int succesor : succesorsLists.get(course)) {
            if (!dfs(succesor)) return false;
        }
        staus[course] = 2;
        return true;
    }


    public static void main(String[] args) {
        boolean result = new CourseSchedule().canFinishDFS(2, new int[][]{new int[]{1, 0}});
        System.out.println(result);
    }
}
