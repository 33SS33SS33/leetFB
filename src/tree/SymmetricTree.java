package tree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric
 * around its center).
 * For example, this binary tree is symmetric:
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 *
 * But the following is not:
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 * Tags: Tree, DFS, Stack
 */
class SymmetricTree {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(4);
        TreeNode n6 = new TreeNode(3);
        root1.left = n1;
        root1.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;

        TreeNode root2 = new TreeNode(1);
        TreeNode n21 = new TreeNode(2);
        TreeNode n22 = new TreeNode(2);
        TreeNode n23 = new TreeNode(3);
        TreeNode n24 = new TreeNode(3);

        root2.left = n21;
        root2.right = n22;
        n21.right = n23;
        n22.right = n24;
        System.out.println(new SymmetricTree().isSymmetric(root1));
        System.out.println(new SymmetricTree().isSymmetricRec(root1));
        System.out.println(new SymmetricTree().isSymmetricC(root1));
        System.out.println(new SymmetricTree().isSymmetric(root2));
        System.out.println(new SymmetricTree().isSymmetricRec(root2));
        System.out.println(new SymmetricTree().isSymmetricC(root2));
    }

    /**递归  算法的时间复杂度是树的遍历O(n)，空间复杂度同样与树遍历相同是O(logn)
     * Recursive, pre-order traversal
     * Check two symmetric nodes a time
     */
    private boolean isSymmetricRec(TreeNode root) {
        if (root == null)
            return true;
        return helper(root.left, root.right);
    }

    private boolean helper(TreeNode n1, TreeNode n2) {
        if (n1 == null || n2 == null)
            return n1 == n2;
        return n1.val == n2.val && helper(n1.left, n2.right) && helper(n1.right, n2.left);
    }


    /** 非递归方法是使用层序遍历来判断对称性质
     * Use a stack to store nodes in order
     * Then pop and compare
     */
    private boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root.left);
        s.push(root.right);
        while (!s.isEmpty()) {
            TreeNode n1 = s.pop();
            TreeNode n2 = s.pop();
            if (n1 == null && n2 == null)
                continue;
            if (n1 == null || n2 == null || n1.val != n2.val)
                return false;
            s.push(n1.left); // add those pairs that should be symmetric
            s.push(n2.right);
            s.push(n1.right);
            s.push(n2.left);
        }
        return true;
    }

    public boolean isSymmetricC(TreeNode root) {
        if (root == null)
            return true;
        if (root.left == null && root.right == null)
            return true;
        if (root.left == null || root.right == null)
            return false;
        LinkedList<TreeNode> q1 = new LinkedList<TreeNode>();
        LinkedList<TreeNode> q2 = new LinkedList<TreeNode>();
        q1.add(root.left);
        q2.add(root.right);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode n1 = q1.poll();
            TreeNode n2 = q2.poll();

            if (n1.val != n2.val)
                return false;
            if (n1.left == null && n2.right != null || n1.left != null && n2.right == null)
                return false;
            if (n1.right == null && n2.left != null || n1.right != null && n2.left == null)
                return false;
            if (n1.left != null && n2.right != null) {
                q1.add(n1.left);
                q2.add(n2.right);
            }
            if (n1.right != null && n2.left != null) {
                q1.add(n1.right);
                q2.add(n2.left);
            }
        }
        return true;
    }

    public static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}