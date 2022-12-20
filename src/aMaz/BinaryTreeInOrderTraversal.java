package aMaz;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class BinaryTreeInOrderTraversal {
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
        System.out.println(new BinaryTreeInOrderTraversal().binaryTreeInOrderTraversal(root));
        System.out.println(new BinaryTreeInOrderTraversal().binaryTreeInOrderTraversalb(root));
    }

    /**
     * Given a binary tree, return the inorder traversal of its nodes' values.
     * Given binary tree {1,#,2,3},
     * 1
     * \
     * 2
     * /
     * 3
     * return [1,3,2]. Note: Recursive solution is trivial, could you do it iteratively?
     * Tags: Tree, HashTable, Stack
     * The recursive solution is trivial. 递归法
     * 算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即O(logn)
     */
    public List<Integer> binaryTreeInOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            helper(root, result);
        }
        return result;
    }

    public void helper(TreeNode p, List<Integer> result) {
        if (p.left != null)
            helper(p.left, result);
        result.add(p.val);
        if (p.right != null)
            helper(p.right, result);
    }

    /**
     * 迭代法  时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)
     * Stack solution, O(n) Space
     * Use a stack to store TreeNodes
     * Go to left most and add each node
     * Pop the node from stack, add its value, and try to go right
     * Stop if stack is empty or node is null
     */
    public static List<Integer> binaryTreeInOrderTraversalb(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
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

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
