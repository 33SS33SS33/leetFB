package aMaz;

import java.util.LinkedList;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the
 * root node down to the nearest leaf node.
 * Tags: Tree, DFS
 * 就是分情况  当叶子节点的时候 当只有一个节点的时候 （这俩计算一样） 还有就是两边都有节点的时候
 */
public class MinimumDepthofBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(6);

        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.right = n5;

        System.out.println(new MinimumDepthofBinaryTree().minimumDepthofBinaryTree(root));
        System.out.println(new MinimumDepthofBinaryTree().minimumDepthofBinaryTreeb(root));
//        System.out.println(new MinimumDepthofBinaryTree().minimumDepthofBinaryTreec(root));
    }

    /**
     * Recursive 递归
     */
    public int minimumDepthofBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        int left = minimumDepthofBinaryTree(root.left);
        int right = minimumDepthofBinaryTree(root.right);
        if (left == 0)
            return right + 1;
        if (right == 0)
            return left + 1;
        return Math.min(left, right) + 1; // plus root
    }

    //非递归解法同样采用层序遍历(相当于图的BFS），只是在检测到第一个叶子的时候就可以返回了
    //http://blog.csdn.net/linhuanmars/article/details/19660209
    public int minimumDepthofBinaryTreeb(TreeNode root) {
        if (root == null)
            return 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        int curNum = 0;
        int lastNum = 1;
        int level = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left == null && cur.right == null)
                return level;
            lastNum--;
            if (cur.left != null) {
                queue.offer(cur.left);
                curNum++;
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                curNum++;
            }
            if (lastNum == 0) {
                lastNum = curNum;
                curNum = 0;
                level++;
            }
        }
        return 0;
    }

    /**
     * creek----？？？？
     */
/*    public int minimumDepthofBinaryTreec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> nodes = new LinkedList<>();
        LinkedList<Integer> counts = new LinkedList<>();
        nodes.add(root);
        counts.add(1);
        while (!nodes.isEmpty()) {
            TreeNode curr = nodes.remove();
            int count = counts.remove();
            if (curr.left != null) {
                nodes.add(curr.left);
                counts.add(count + 1);
            }
            if (curr.right != null) {
                nodes.add(curr.right);
                counts.add(count + 1);
            }
            if (curr.left == null && curr.right == null) {
                return count;
            }
        }
        return 0;
    }*/

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

}
