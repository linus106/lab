package com.linus.lab.algorithm.binary;

/**
 * @Author wangxiangyu
 * @Date 2020/8/18 17:38
 * @Description TODO
 * https://leetcode-cn.com/problems/binode-lcci/
 */
public class BinarySearchTreeToGreaterSumTree {

    int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return root;
        }
        bstToGst(root.right);
        sum += root.val;
        root.val = sum;
        bstToGst(root.left);
        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {

    }
}