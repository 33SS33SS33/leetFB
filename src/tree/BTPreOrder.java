package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * 
 * For example:
 * Given binary tree {1,#,2,3},
 *    1
 *     \
 *      2
 *     /
 *    3
 * return [1,2,3].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 * 
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
    }

    /**
     * Iterative
     * Use a stack
     * Pop current top, and push right first then push left
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode curNode = s.pop();
            res.add(curNode.val); // visit
            if (curNode.right != null) s.push(curNode.right);
            if (curNode.left != null) s.push(curNode.left); // left pop first
        }
        return res;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * Recursive
     */

    List<Integer> result = new ArrayList<Integer>();
    public List<Integer> preorderTraversalB(TreeNode root) {
        if(root !=null){
            helper(root);
        }
        return result;
    }

    public void helper(TreeNode p){
        result.add(p.val);
        if(p.left!=null)
            helper(p.left);
        if(p.right!=null)
            helper(p.right);
    }

}
