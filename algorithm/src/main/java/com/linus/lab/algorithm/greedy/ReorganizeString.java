package com.linus.lab.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Function;

/**
 * @Author wangxiangyu
 * @Date 2020/12/1 17:35
 * @Description TODO
 */
public class ReorganizeString {


    public String reorganizeString(String S) {
        int[] counts = new int[26];

        PriorityQueue<Node> nodes = new PriorityQueue<>((a, b) -> b.count - a.count);
        for (char c : S.toCharArray()) {
            counts[c - 'a']++;
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                nodes.offer(new Node(counts[i], (char) (i + 'a')));
            }
        }
        if (nodes.peek().count >= ((S.length() + 1) / 2)) {
            return "";
        } else {
            StringBuilder res = new StringBuilder();
            Node lastPoll = null;
            while (!nodes.isEmpty()) {
                Node node = nodes.poll();
                if (lastPoll != null && lastPoll.count > 0) {
                    nodes.add(lastPoll);
                }
                res.append(node.c);
                node.count--;
                lastPoll = node;
            }
            return res.toString();
        }
    }

    private class Node {
        private int count;
        private char c;

        public Node(int count, char c) {
            this.count = count;
            this.c = c;
        }
    }

    public static void main(String[] args) {

    }
}
