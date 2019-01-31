package aMaz;

import java.util.Stack;

/**
 * Given two binary trees, write a function to check if they are equal or not.
 * Two binary trees are considered equal if they are structurally identical
 * and the nodes have the same value.
 * Tags: Tree, DFS
 * 先序遍历
 */
class SameTree {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(6);
        root1.left = n1;
        root1.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.right = n5;

        TreeNode root2 = new TreeNode(1);

        root2.left = n1;
        root2.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.right = n5;
        System.out.println(new SameTree().sameTree(root1, root2));
    }

    /**
     * Recursive, pre-order check
     * If both node's values are the same, left subtrees are same and so right
     * Return true, otherwise return false
     */
    public boolean sameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val == q.val)
            return sameTree(p.left, q.left) && sameTree(p.right, q.right);
        return false;
    }

    public boolean sameTreeb(TreeNode p, TreeNode q) {
        Stack<TreeNode> stackP = new Stack<>();
        Stack<TreeNode> stackQ = new Stack<>();
        if (p != null) stackP.push(p);
        if (q != null) stackQ.push(q);
        while (!stackP.isEmpty() && !stackQ.isEmpty()) {
            TreeNode pn = stackP.pop();
            TreeNode qn = stackQ.pop();
            if (pn.val != qn.val)
                return false;
            if (pn.right != null)
                stackP.push(pn.right);
            if (qn.right != null)
                stackQ.push(qn.right);
            if (stackP.size() != stackQ.size())
                return false;
            if (pn.left != null)
                stackP.push(pn.left);
            if (qn.left != null)
                stackQ.push(qn.left);
            if (stackP.size() != stackQ.size())
                return false;
        }
        return stackP.size() == stackQ.size();
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