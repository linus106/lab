package com.linus.lab.algorithm.topologicalsort;

import java.util.*;

/**
 * @Author wangxiangyu
 * @Date 2020/9/29 10:54
 * @Description TODO
 */
public class CourseScheduleII {

    public int[] findOrderBFS(int numCourses, int[][] prerequisites) {
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

        List<Integer> orderedCourse = new ArrayList<>();

        // 4 excute queue
        while (!queue.isEmpty()) {
            int course = queue.poll();
            orderedCourse.add(course);
            for(int succesor : succesorsLists.get(course)) {
                if (--predecessorsCounts[succesor] == 0) {
                    queue.add(succesor);
                }
            }
        }
        if (orderedCourse.size() == numCourses) {
            return orderedCourse.stream().mapToInt(Integer::valueOf).toArray();
        } else {
            return new int[]{};
        }
    }








    private List<List<Integer>> succesorsLists;
    private int[] staus ;//0 not learn yet  1 learning 2learned
    List<Integer> orderedCourse = new ArrayList<>();
    public int[] findOrderDFS(int numCourses, int[][] prerequisites) {

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
            if (!dfs(course)) return new int[]{};
        }
        Collections.reverse(orderedCourse);
        return orderedCourse.stream().mapToInt(Integer::valueOf).toArray();
    }

    public boolean dfs(int course) {
        if (staus[course] == 2) {
            return true;
        }
        if (staus[course] == 1) return false;
        staus[course] = 1;
        for (int succesor : succesorsLists.get(course)) {
            if (!dfs(succesor)) return false;
        }
        staus[course] = 2;
        orderedCourse.add(course);
        return true;
    }


    public static void main(String[] args) {
        int[] result = new CourseScheduleII().findOrderDFS(2, new int[][]{new int[]{1, 0}});
        for (int i : result) {
            System.out.println(i);
        }
    }
}
