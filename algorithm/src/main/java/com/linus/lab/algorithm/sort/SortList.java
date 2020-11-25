package com.linus.lab.algorithm.sort;

import java.util.List;

/**
 * @Author wangxiangyu
 * @Date 2020/11/23 13:46
 * @Description TODO
 */
public class SortList {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rightHead = slow.next;
        slow.next = null;

        ListNode s1 = sortList(head);
        ListNode s2 = sortList(rightHead);

        return merge(s1, s2);
    }

    private ListNode merge(ListNode s1, ListNode s2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        while (s1 != null && s2 != null) {
            ListNode next;
            if (s1.val < s2.val) {
                next = s1;
                s1 = s1.next;
            } else {
                next = s2;
                s2 = s2.next;
            }
            curr.next = next;
            curr = next;
        }
        if (s1 != null) {
            curr.next = s1;
        } else {
            curr.next = s2;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        SortList o = new SortList();
        ListNode res = o.sortList(n1);

        System.out.println(res);
    }
}
