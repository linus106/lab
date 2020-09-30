package com.linus.lab.algorithm.topologicalsort;

import java.util.*;

/**
 * @Author wangxiangyu
 * @Date 2020/9/29 11:12
 * @Description TODO
 * https://leetcode-cn.com/problems/sort-items-by-groups-respecting-dependencies/
 *
 * 思路：双层拓扑排序
 */
public class SortItemsByGroupsRespectingDependencies {

    List<List<Integer>> groupItems = new ArrayList<>();//下标为group
    List<List<Integer>> succesorsWithinGroup = new ArrayList<>();//下标为item
    List<List<Integer>> succesorsCrossGroup = new ArrayList<>();//下标为group
    List<Integer> orderedItems = new ArrayList<>();

    int[] groupStatus = null;   //0 not handle yet      1 handling      2 handled
    int[] itemStatus = null;    //0 not handle yet      1 handling      2 handled

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        for (int i = 0; i < n; i++) {// 组为-1的看做有单独的组
            if (group[i] == -1) {
                group[i] = m;
                m++;
            }
            succesorsWithinGroup.add(new ArrayList<>());
        }
        groupStatus = new int[m];
        itemStatus = new int[n];
        for (int i = 0; i < m; i++) {//初始化 group
            groupItems.add(new ArrayList<>());
            succesorsCrossGroup.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            groupItems.get(group[i]).add(i);//item放到组里
            for (int beforeItem : beforeItems.get(i)) {
                if (group[beforeItem] == group[i]) {//wtihin group
                    succesorsWithinGroup.get(beforeItem).add(i);
                } else {//cross group
                    succesorsCrossGroup.get(group[beforeItem]).add(group[i]);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (!groupDFS(i)) return new int[]{};
        }
        Collections.reverse(orderedItems);
        return orderedItems.stream().mapToInt(Integer::valueOf).toArray();
    }

    public boolean groupDFS(int group) {
        if (groupStatus[group] == 2) {
            return true;
        }
        if (groupStatus[group] == 1) return false;
        groupStatus[group] = 1;
        for (int succesor : succesorsCrossGroup.get(group)) {
            if (!groupDFS(succesor)) return false;
        }

        for (int itemInGroup : groupItems.get(group)) {
            if (!itemDFS(itemInGroup)) return false;
        }
        groupStatus[group] = 2;
        return true;
    }

    public boolean itemDFS(int item) {
        if (itemStatus[item] == 2) {
            return true;
        }
        if (itemStatus[item] == 1) return false;
        itemStatus[item] = 1;
        for (int succesor : succesorsWithinGroup.get(item)) {
            if (!itemDFS(succesor)) return false;
        }
        itemStatus[item] = 2;
        orderedItems.add(item);
        return true;
    }

    public static void main(String[] args) {
        int[] result = new SortItemsByGroupsRespectingDependencies().sortItems(8,2,
                new int[]{-1,-1,1,0,0,1,0,-1},
                Arrays.asList(
                        Arrays.asList(),
                        Arrays.asList(6),
                        Arrays.asList(5),
                        Arrays.asList(6),
                        Arrays.asList(3,6),
                        Arrays.asList(),
                        Arrays.asList(),
                        Arrays.asList()
                ));

        for (int i : result) {
            System.out.println(i);
        }
    }
}
