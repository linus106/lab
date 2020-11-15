package com.linus.lab.algorithm.linked_list;

import java.util.List;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/15
 */
public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode p = head;//负责遍历
        ListNode currGroupHead = head;//负责记录当前group的头
        ListNode lastGroupHead = null;//负责记录上一个group的头
        ListNode newHead = head;//负责记录最终链表的头
        int n = 0;
        while (true) {
            if (p == null) break;
            n++;
            if (n == 1) {
                currGroupHead = p;
            }
            if (n == k) {
                ListNode h = reverseGroup(currGroupHead, k);

                if (head == currGroupHead) {//说明是第一个group
                    newHead = h;
                } else {
                    ListNode lastGroupTail = lastGroupHead;
                    for (int i = 1; i < k; i++) {
                        lastGroupTail = lastGroupTail.next;
                    }
                    lastGroupTail.next = h;
                }
                n = 0;//reset
                lastGroupHead = h;
                p = currGroupHead.next;
            } else {
                p = p.next;
            }
        }
        return newHead;
    }

    public ListNode reverseGroup(ListNode groupHead, int k) {

        ListNode newHead = groupHead;
        ListNode p = groupHead;
        ListNode p1 = p.next;
        for (int i = 1; i < k; i++) {
            ListNode pInner = p;
            ListNode p1Inner = p1;
            newHead = p1Inner;
            p = p1;
            p1 = p1.next;

            p1Inner.next = pInner;
        }
        groupHead.next = p1;

        return newHead;
    }




    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;


        ReverseNodesInKGroup o = new ReverseNodesInKGroup();
        ListNode res = o.reverseKGroup(n1, 4);


        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
