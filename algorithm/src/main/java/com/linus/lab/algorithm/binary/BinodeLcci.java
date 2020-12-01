package com.linus.lab.algorithm.binary;

/**
 * @Author wangxiangyu
 * @Date 2020/8/18 17:38
 * @Description TODO
 * https://leetcode-cn.com/problems/binode-lcci/
 */
public class BinodeLcci {

    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode head = null;
        TreeNode left = convertBiNode(root.left);

        if (left == null) {
            head = root;
        } else {
            TreeNode leftTail = left;
            while (leftTail.right != null) {//TODO 可优化:同时返回头尾，传参返回头或尾，循环链表再拆开
                leftTail = leftTail.right;
            }
            head = left;
            leftTail.right = root;
        }
        root.left = null;
        root.right = convertBiNode(root.right);

        return head;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);

        root.left = new TreeNode(2);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(6);

        root.left.left.left = new TreeNode(0);
        new BinodeLcci().convertBiNode(root);
    }
}