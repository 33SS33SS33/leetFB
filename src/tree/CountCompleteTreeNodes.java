package tree;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given a complete binary tree, count the number of nodes.
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last
 * level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * Tags: Tree, Binary Search
 * Similar Problems: (E) Closest Binary Search Tree Value
 * 首先先计算树的高度  树的高度是从0开始算的  然后直接把最左边的子树压到低的出来的就是高度
 * 然后对最下面的一排子树进行二分查找 如果是满二叉树 最底层应该有2^height个节点 所以将最下面的节点编号 0 …..2^height -1
 * 然后进行二分查找 如果中间值有节点 则就继续往右半部分找 否则往左半部分找 直到left > right
 * 本题最关键的是如何找到二叉树的第n个节点 由于之前把最后一层的节点编号了 如果用二进制表示的
 * 下面的节点比如 01节点 就说明是碰见0就先左走 然后碰见1就往右走
 * 还有个递归的解法 第二遍用了 用的是书影博客的解法
 */
public class CountCompleteTreeNodes {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(6);
        TreeNode n6 = new TreeNode(7);
        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;
        System.out.println(new CountCompleteTreeNodes().countNodesa(root));
    }

    /**
     * creek-------
     * Steps to solve this problem:
     * 1) get the height of left-most part
     * 2) get the height of right-most part
     * 3) when they are equal, the # of nodes = 2^h -1
     * 4) when they are not equal, recursively get # of nodes from left&right sub-trees
     * Time complexity is O(h^2)
     */
    public int countNodesa(TreeNode root) {
        if (root == null)
            return 0;
        int left = getLeftHeight(root) + 1;
        int right = getRightHeight(root) + 1;
        if (left == right) {
            return (2 << (left - 1)) - 1;
        } else {
            return countNodesa(root.left) + countNodesa(root.right) + 1;
        }
    }

    public int getLeftHeight(TreeNode n) {
        if (n == null)
            return 0;
        int height = 0;
        while (n.left != null) {
            height++;
            n = n.left;
        }
        return height;
    }

    public int getRightHeight(TreeNode n) {
        if (n == null)
            return 0;
        int height = 0;
        while (n.right != null) {
            height++;
            n = n.right;
        }
        return height;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
