package com.linus.lab.algorithm.reservoir;

import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author wangxiangyu
 * @Date 2020/8/7 11:49
 * @Description TODO
 * https://leetcode-cn.com/problems/linked-list-random-node/
 * LeetCode398
 * 给定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。
 *
 * 如果链表十分大且长度未知，如何解决这个问题？你能否使用常数级空间复杂度实现？
 */
public class LinkedListRandomNode {

    private ListNode head;


    public LinkedListRandomNode(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        int val = 0;
        ListNode current = head;
        int currentCount = 1;
        Random random = new Random();
        while (current != null) {
            if(random.nextInt(currentCount) == 0) val = current.val;
            current = current.next;
            currentCount++;
        }

        return val;
    }

    public static void main(String[] args) {
        Map<Integer, Long> statisticsResult = Stream.iterate(0, i->i+1).limit(10000).map(i->{

            // 初始化一个单链表 [1,2,3].
            ListNode head = new ListNode(1);
            head.next = new ListNode(2);
            head.next.next = new ListNode(3);
            int result = new LinkedListRandomNode(head).getRandom();
            return result;
        }).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        statisticsResult.forEach((i,count)->{
            System.out.println(String.format("%d-%d",i,count));
        });
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
