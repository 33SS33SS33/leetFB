package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * return [1,2,3].
 * Note: Recursive solution is trivial, could you do it iteratively?
 * Tags: Tree, Stack
 */
class BTPreOrder {
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
        System.out.println(new BTPreOrder().preorderTraversal(root));
        System.out.println(new BTPreOrder().preorderTraversalB(root));
        System.out.println(new BTPreOrder().preorderTraversalC(root));
    }

    /**
     * 递归
     * 递归是最简单的方法，算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即O(logn)。
     */
    List<Integer> result = new ArrayList<Integer>();

    public List<Integer> preorderTraversalB(TreeNode root) {
        if (root != null) {
            helper(root);
        }
        return result;
    }

    public void helper(TreeNode p) {
        result.add(p.val);
        if (p.left != null)
            helper(p.left);
        if (p.right != null)
            helper(p.right);
    }

    /**
     * 迭代
     * 迭代的做法，其实就是用一个栈来模拟递归的过程。所以算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)。
     * Use a stack
     * Pop current top, and push right first then push left
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null)
            return res;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode curNode = s.pop();
            res.add(curNode.val);
            if (curNode.right != null) // visit 注意啊~~！！！！
                s.push(curNode.right);
            if (curNode.left != null)
                s.push(curNode.left); // left pop first
        }
        return res;
    }

    /**
     * 用线索二叉树  这种方法的缺陷在于会暂时性的改变结点的内容
     * 分别是O(n)和O(1)
     * http://blog.csdn.net/linhuanmars/article/details/21428647
     */
    public ArrayList<Integer> preorderTraversalC(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;
                if (pre.right == null) {
                    res.add(cur.val);
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
