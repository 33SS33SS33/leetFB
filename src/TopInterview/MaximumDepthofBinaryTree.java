package TopInterview;

import java.util.LinkedList;

class MaximumDepthofBinaryTree {
    public static void main(String[] args) {
        TreeNode head = buildTree();
        System.out.println(new MaximumDepthofBinaryTree().maximumDepthofBinaryTree(head));
        System.out.println(new MaximumDepthofBinaryTree().maximumDepthofBinaryTreeb(head));
    }

    /**
     * Given a binary tree, find its maximum depth.The maximum depth is the number of nodes along the longest path from the
     * root node down to the farthest leaf node.
     * Tags: Tree, DFS
     * 使用自底向上bottom-up的递归
     * Recursive, O(n) 递归
     */
    private int maximumDepthofBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        int left = maximumDepthofBinaryTree(root.left);
        int right = maximumDepthofBinaryTree(root.right);
        return Math.max(left, right) + 1;
    }

    public int maximumDepthofBinaryTreeb(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> depths = new LinkedList<>();
        if (root == null) return 0;

        stack.add(root);
        depths.add(1);

        int depth = 0, current_depth = 0;
        while (!stack.isEmpty()) {
            root = stack.pollLast();
            current_depth = depths.pollLast();
            if (root != null) {
                depth = Math.max(depth, current_depth);
                stack.add(root.left);
                stack.add(root.right);
                depths.add(current_depth + 1);
                depths.add(current_depth + 1);
            }
        }
        return depth;
    }

    private static TreeNode buildTree() {
        TreeNode t0 = new TreeNode(20);
        TreeNode t1 = new TreeNode(9);
        TreeNode t2 = new TreeNode(49);
        TreeNode t3 = new TreeNode(5);
        TreeNode t4 = new TreeNode(12);
        TreeNode t5 = new TreeNode(15);
        TreeNode t6 = new TreeNode(23);
        TreeNode t7 = new TreeNode(52);
        TreeNode t8 = new TreeNode(50);

        t0.left = t1;
        t0.right = t2;
        t1.left = t3;
        t1.right = t4;
        t4.right = t5;
        t2.left = t6;
        t2.right = t7;
        t7.left = t8;

        return t0;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

}