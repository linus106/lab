package com.linus.lab.algorithm.greedy;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/16
 */
public class QueueReconstructionByHeight {

    public int[][] reconstructQueue(int[][] people) {
        LinkedList<Node> list = new LinkedList();
        for (int i = 0; i < people.length; i++) {
            list.add(new Node(people[i][0], people[i][1], i));
        }

        int[][] res = new int[people.length][];
        for (int i = 0; i < people.length; i++) {
            Node minNode = findFirst(list);
            list.remove(minNode);
            list.stream().forEach(node->{
                if (node.h <= minNode.h) {
                    node.k--;
                }
            });

            res[i] = people[minNode.index];
        }
        return res;
    }

    private Node findFirst(LinkedList<Node> list) {
        int min = Integer.MAX_VALUE;
        Node minNode = null;
        for (Node node : list) {

            if (node.k == 0) {
                if (node.h < min) {
                    min = node.h;
                    minNode = node;
                }
            }
        }
        return minNode;
    }

    private class Node {
        int h, k, index;
        public Node(int h, int k, int index) {
            this.h = h;
            this.k = k;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        QueueReconstructionByHeight o = new QueueReconstructionByHeight();

        int[][] res = o.reconstructQueue(new int[][]{
                new int[]{7,0},
                new int[]{4,4},
                new int[]{7,1},
                new int[]{5,0},
                new int[]{6,1},
                new int[]{5,2}
        });

        for (int[] re : res) {
            System.out.println(re[0] + ":" + re[1]);
        }
    }
}
