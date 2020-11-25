package com.linus.lab.algorithm.linked_list;

import com.linus.lab.algorithm.sort.SortList;

/**
 * @Author wangxiangyu
 * @Date 2020/11/23 17:39
 * @Description TODO
 */
public class OddEvenLinkedList {

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

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode evenHead = head.next;

        ListNode odd = head;
        ListNode even = head.next;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;// before: last odd point to last even; after : last odd point to first even
        return head;
    }

    public static void main(String[] args) {

    }
}
