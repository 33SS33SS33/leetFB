package aMaz;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Invert a binary tree.
 Input:
 4
 /   \
 2     7
 / \   / \
 1   3 6   9
 Output:
 4
 /   \
 7     2
 / \   / \
 9   6 3   1
 * 用BFS来翻转
 */
public class InvertBinaryTree {
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
        TreeNode res = new InvertBinaryTree().invertBinaryTree(root);
//        System.out.println(new InvertBinaryTree().levelOrder(res));

    }

    public TreeNode invertBinaryTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode newLeft = invertBinaryTree(root.right);
        TreeNode newRight = invertBinaryTree(root.left);
        root.left = newLeft;
        root.right = newRight;
        return root;
    }

    public TreeNode invertBinaryTreeb(TreeNode root) {
        if (root == null) {
            return null;
        }
        final Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            final TreeNode node = queue.poll();
            final TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
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
