package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * <p/>
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * return [1,3,2].
 * <p/>
 * Note: Recursive solution is trivial, could you do it iteratively?
 * <p/>
 * Tags: Tree, HashTable, Stack
 */
class BTInOrder {
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
        System.out.println(new BTInOrder().inorderTraversal(root));
        System.out.println(new BTInOrder().inorderTraversalB(root));
        System.out.println(new BTInOrder().inorderTraversalC(root));
    }

    /**
     * Stack solution, O(n) Space
     * Use a stack to store TreeNodes
     * Go to left most and add each node
     * Pop the node from stack, add its value, and try to go right
     * Stop if stack is empty or node is null
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        while (!s.isEmpty() || root != null) {
            // check whether current node is null
            if (root != null) { // current node is not null
                s.push(root);
                root = root.left;
            } else { // current node is null, pop and go right
                root = s.pop();
                result.add(root.val); // visit()
                root = root.right;
            }
        }
        return result;
    }

    /**
     * <strong>Morris Traversal</strong>
     * O(1) space
     * Use cur for current node, pre for predecessor of cur node
     * Check whether left subtree exists
     * If yes, find rightmost node in left subtree
     * Check whether rightmost node is connected with cur node
     * If connected, break the connection and visit and move to right subtree
     * Otherwise, connect and traverse left subtree
     * If no, visit cur node and traverse right subtree
     */
    public static List<Integer> inorderTraversalB(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null)
            return res;
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val); // visit
                cur = cur.right; // move to right
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;
                if (pre.right == null) { // connect with cur
                    pre.right = cur;
                    cur = cur.left; // traverse left subtree
                } else { // left subtree is done
                    pre.right = null;
                    res.add(cur.val); // visit
                    cur = cur.right; // move to right
                }
            }
        }
        return res;
    }

    /**
     * The recursive solution is trivial.
     */
    List<Integer> result = new ArrayList<Integer>();

    public List<Integer> inorderTraversalC(TreeNode root) {
        if (root != null) {
            helper(root);
        }
        return result;
    }

    public void helper(TreeNode p) {
        if (p.left != null)
            helper(p.left);
        result.add(p.val);
        if (p.right != null)
            helper(p.right);
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
