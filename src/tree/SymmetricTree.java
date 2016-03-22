package tree;

import java.util.Stack;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric
 * around its center).
 * <p/>
 * For example, this binary tree is symmetric:
 * <p/>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * <p/>
 * But the following is not:
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * <p/>
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 * <p/>
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

    /**
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

    /**
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

    /**
     * --------creek--------
     */
    public boolean isSymmetricC(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode l, TreeNode r) {
        if (l == null && r == null) {
            return true;
        } else if (r == null || l == null) {
            return false;
        }
        if (l.val != r.val)
            return false;
        if (!isSymmetric(l.left, r.right))
            return false;
        if (!isSymmetric(l.right, r.left))
            return false;

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