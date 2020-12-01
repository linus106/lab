package com.linus.lab.algorithm.binarytree;

/**
 * @Author wangxiangyu
 * @Date 2020/11/25 15:54
 * @Description TODO
 */
public class CountCompleteTreeNodes {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if (leftHeight > rightHeight) {
            return countNodes(root.left) + (1 << rightHeight);
        } else {
            return countNodes(root.right) + (1 << leftHeight);
        }
    }

    private int height(TreeNode node) {
        int n = 0;
        while (node != null) {
            n++;
            node = node.left;
        }
        return n;
    }

    public int countNodesBruteForce(TreeNode root) {
        if (root == null) return 0;
        return countNodesBruteForce(root.left) + countNodesBruteForce(root.right) + 1;
    }

    public static void main(String[] args) {

        System.out.println(1 << 3);
    }
}
