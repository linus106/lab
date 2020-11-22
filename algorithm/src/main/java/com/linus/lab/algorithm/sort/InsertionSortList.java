package com.linus.lab.algorithm.sort;

import java.util.Stack;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/20
 */
public class InsertionSortList {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }


    public ListNode insertionSortListOpt(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = head, curr = head.next;
        while (curr != null) {
            if (tail.val <= curr.val) {
                tail = tail.next;
            } else {
                ListNode temp = dummy;
                while (temp.next.val < curr.val) {
                    temp = temp.next;
                }
                tail.next = curr.next;
                curr.next = temp.next;
                temp.next = curr;
            }
            curr = tail.next;
        }
        return dummy.next;

    }


    /**
     * 双栈
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {

        Stack<ListNode> s1 = new Stack<>();
        s1.push(new ListNode(Integer.MIN_VALUE));
        Stack<ListNode> s2 = new Stack<>();
        s2.push(new ListNode(Integer.MAX_VALUE));

        ListNode node = head;
        while (node != null) {
            if (node.val > s2.peek().val) {
                while (node.val > s2.peek().val) {
                    s1.push(s2.pop());
                }
            } else if (node.val < s1.peek().val) {

                while (node.val < s1.peek().val) {
                    s2.push(s1.pop());
                }
            }
            s2.push(node);
            node = node.next;
        }

        while(!s1.empty()) {
            s2.push(s1.pop());
        }

        ListNode newHead = s2.peek();
        int length = s2.size();
        for (int i = 0; i < length - 2; i++) {
            s2.pop().next = s2.peek();
        }
        s2.pop().next = null;
        return newHead.next;
    }

    public static void main(String[] args) {

        ListNode n1 = new ListNode(-1);
        ListNode n2 = new ListNode(5);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(0);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;


        InsertionSortList o = new InsertionSortList();
        ListNode res = o.insertionSortList(n1);
        System.out.println();

    }


}
