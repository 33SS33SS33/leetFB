package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by GAOSHANSHAN835 on 2015/12/28.
 */

/**
 * Given a binary tree, find its minimum depth.
 * <p/>
 * The minimum depth is the number of nodes along the shortest path from the
 * root node down to the nearest leaf node.
 * <p/>
 * Tags: Tree, DFS
 */
public class MinDepth {
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

        System.out.println(new MinDepth().minDepthA(root));
        System.out.println(new MinDepth().minDepthB(root));
        System.out.println(new MinDepth().minDepth(root));
    }

    /**
     * Recursive 递归*/
     /* Get minDepth of left and right subtree
     * If one side is 0, return the other side plus 1
     * Return the smaller one + 1
     */
    public int minDepthA(TreeNode root) {
        if (root == null)
            return 0;
        int left = minDepthA(root.left);
        int right = minDepthA(root.right);
        if (left == 0)
            return right + 1;
        if (right == 0)
            return left + 1;
        return Math.min(left, right) + 1; // plus root
    }

    /**
     * creek----
     */
    public int minDepthB(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
        LinkedList<Integer> counts = new LinkedList<Integer>();
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
    }

    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        TreeNode rightMost = root;
        int depth = 1;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.left == null && node.right == null)
                break;
            if (node.left != null)
                q.add(node.left);
            if (node.right != null)
                q.add(node.right);
            if (node == rightMost) {
                depth++;
                rightMost = (node.right != null) ? node.right : node.left;
            }
        }
        return depth;
    }

    static class TreeNode {
        int      value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }
}
