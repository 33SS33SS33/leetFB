package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
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
        TreeNode res = new InvertBinaryTree().invertTree(root);
        System.out.println(new InvertBinaryTree().levelOrder(res));

        TreeNode res2 = new InvertBinaryTree().invertTreeA(root);
        System.out.println(new InvertBinaryTree().levelOrder(res2));

        TreeNode res3 = new InvertBinaryTree().invertTreeB(root);
        System.out.println(new InvertBinaryTree().levelOrder(res3));
    }

    /**
     * 最好的
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode newLeft = invertTree(root.right);
        TreeNode newRight = invertTree(root.left);
        root.left = newLeft;
        root.right = newRight;
        return root;
    }
    /**
     * The above solution is correct, but it is also bound to the application stack, which
     means that it's no so much scalable - (you can find the problem size that will
     overflow the stack and crash your application), so more robust solution would be to
     use stack data structure.
     */

    /**
     * creek   Iterative
     */
    public TreeNode invertTreeB(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();
            if (p.left != null)
                queue.add(p.left);
            if (p.right != null)
                queue.add(p.right);
            TreeNode temp = p.left;
            p.left = p.right;
            p.right = temp;
        }
        return root;
    }

    /**
     * Finally we can easly convert the above solution to BFS - or so called level order
     * traversal.
     */
    public TreeNode invertTreec(TreeNode root) {
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

    /**
     * creek Recursive
     */
    public TreeNode invertTreeA(TreeNode root) {
        if (root != null) {
            helper(root);
        }
        return root;
    }

    public void helper(TreeNode p) {
        TreeNode temp = p.left;
        p.left = p.right;
        p.right = temp;
        if (p.left != null)
            helper(p.left);
        if (p.right != null)
            helper(p.right);
    }

    private List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null)
            return res;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> curLevel = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = queue.poll();
                curLevel.add(n.val);
                if (n.left != null)
                    queue.add(n.left);
                if (n.right != null)
                    queue.add(n.right);
            }
            res.add(curLevel);
        }
        return res;
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
