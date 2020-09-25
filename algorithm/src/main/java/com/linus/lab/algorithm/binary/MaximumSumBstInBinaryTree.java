package com.linus.lab.algorithm.binary;

/**
 * @Author wangxiangyu
 * @Date 2020/9/25 17:02
 * @Description TODO
 */
public class MaximumSumBstInBinaryTree {

    public int maxSumBST(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    Integer maxSum = 0;


    public NodeResult dfs(TreeNode node) {
        if (node == null) return new NodeResult();

        NodeResult leftResult = dfs(node.left);
        NodeResult rightResult = dfs(node.right);

        NodeResult currentResult = new NodeResult();

        currentResult.isBst = leftResult.isBst
                && rightResult.isBst
                && leftResult.max < node.val
                && rightResult.min > node.val;

        currentResult.sum = currentResult.isBst
                ? (leftResult.sum + rightResult.sum + node.val)
                : 0;

        currentResult.max = Math.max(node.val, Math.max(leftResult.max, rightResult.max));
        currentResult.min = Math.min(node.val, Math.min(leftResult.min, rightResult.min));

        maxSum = Math.max(maxSum, currentResult.sum);

        return currentResult;
    }


    static class NodeResult {
        boolean isBst = true;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(4,
                        new TreeNode(2),
                        new TreeNode(4)),
                new TreeNode(3,
                        new TreeNode(2),
                        new TreeNode(5,
                                new TreeNode(4),
                                new TreeNode(6))));

//        TreeNode root = new TreeNode(-4,
//                new TreeNode(-2),
//                new TreeNode(-5));

//        TreeNode root = new TreeNode(4,
//                new TreeNode(8,
//                        new TreeNode(6),
//                        new TreeNode(1)),
//                null);

        System.out.println(new MaximumSumBstInBinaryTree().maxSumBST(root));
    }


}
