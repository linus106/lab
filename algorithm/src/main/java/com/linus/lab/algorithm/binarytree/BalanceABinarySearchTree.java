package com.linus.lab.algorithm.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author wangxiangyu
 * @Date 2020/9/24 17:29
 * @Description TODO
 */
public class BalanceABinarySearchTree {

    //Solution 1: size()->build Banlance Tree->fill value
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> orderedValues = new ArrayList<>();
        readValue(root, orderedValues);
        TreeNode newRoot = buildNewTree(orderedValues.size());
        writeValue(newRoot, orderedValues, new AtomicInteger(0));
        return newRoot;
    }

    public void writeValue(TreeNode node, List<Integer> orderedValues, AtomicInteger currentIndex) {
        if (node == null) return;
        writeValue(node.left, orderedValues, currentIndex);

        node.val = orderedValues.get(currentIndex.get());
        currentIndex.incrementAndGet();

        writeValue(node.right, orderedValues, currentIndex);
    }

    public TreeNode buildNewTree(Integer size) {
        TreeNode[] nodes = new TreeNode[size];

        nodes[0] = new TreeNode(0);
        for (int i = 1; i < size; i++) {
            nodes[i] = new TreeNode(0);
            int partentIndex = (i - 1) / 2;
            if (i % 2 == 0) {
                nodes[partentIndex].right = nodes[i];
            } else {
                nodes[partentIndex].left = nodes[i];
            }
        }

        return nodes[0];
    }

    public void readValue(TreeNode node, List<Integer> orderedValues) {
        if (node == null) return;
        readValue(node.left, orderedValues);
        orderedValues.add(node.val);
        readValue(node.right, orderedValues);
    }

    //Solution two: 1: list()->2 build tree by range
    public TreeNode balanceBSTOpt(TreeNode root) {
        List<Integer> orderedValues = new ArrayList<>();
        readValue(root, orderedValues);
        TreeNode newRoot = buildTreeByRange(orderedValues, 0, orderedValues.size() - 1);
        return newRoot;
    }

    private TreeNode buildTreeByRange(List<Integer> orderedValues, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(orderedValues.get(mid));
        root.left = buildTreeByRange(orderedValues, start, mid - 1);
        root.right = buildTreeByRange(orderedValues, mid + 1, end);
        return root;
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);

        n1.right = n2;
        n2.right = n3;
        n3.right = n4;

//        TreeNode newRoot = new BalanceABinarySearchTree().balanceBST(n1);
        TreeNode newRoot2 = new BalanceABinarySearchTree().balanceBSTOpt(n1);
        System.out.println(1);
    }
}
