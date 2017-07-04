package tree;

import java.util.LinkedList;

/**
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the
 * root node down to the farthest leaf node.
 * Tags: Tree, DFS
 * 使用自底向上bottom-up的递归
 */
class MaxDepth {
    public static void main(String[] args) {
        TreeNode head = buildTree();
        System.out.println(new MaxDepth().maxDeptha(head));
        System.out.println(new MaxDepth().maxDepthA(head));
        System.out.println(new MaxDepth().maxDepthB(head));
    }

    public int maxDeptha(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDeptha(root.left), maxDeptha(root.right));
    }

    /**
     * Recursive, O(n) 递归
     */
    private int maxDepthA(TreeNode root) {
        if (root == null)
            return 0;
        int left = maxDepthA(root.left);
        int right = maxDepthA(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 非递归解法一般采用层序遍历(相当于图的BFS），因为如果使用其他遍历方式也需要同样的复杂度O(n).
     * 层序遍历理解上直观一些，维护到最后的level便是树的深度
     */
    public int maxDepthB(TreeNode root) {
        if (root == null)
            return 0;
        int level = 0;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int curNum = 1; //num of nodes left in current level
        int nextNum = 0; //num of nodes in next level
        while (!queue.isEmpty()) {
            TreeNode n = queue.poll();
            curNum--;
            if (n.left != null) {
                queue.add(n.left);
                nextNum++;
            }
            if (n.right != null) {
                queue.add(n.right);
                nextNum++;
            }
            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
                level++;
            }
        }
        return level;
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