package hard;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * return [3,2,1].
 * Note: Recursive solution is trivial, could you do it iteratively?
 * Tags: Tree, Stack
 * 最简单的办法
 * pre-order traversal is root-left-right, and post order is left-right-root. modify the code for pre-order to make it root-right-left,
 * and then reverse the output so that we can get left-right-root . 就是把先序遍历变成 根-右子树-左子树 然后再把结果倒序
 * 还有一种方法就是 设置一个标志位 记录当前节点有没有访问过  标志位为true的节点说明已经遍历了他的左子树和右子树 然后才可以遍历当前的节点
 */
class BTPostOrder {
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
        System.out.println(new BTPostOrder().postorderTraversalA(root));
        System.out.println(new BTPostOrder().postorderTraversalA2(root));
    }

    //http://blog.csdn.net/linhuanmars/article/details/22009351
    /**
     * 递归
     * 算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即O(logn)
     */
    public ArrayList<Integer> postorderTraversalA(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, ArrayList<Integer> res) {
        if (root == null)
            return;
        helper(root.left, res);
        helper(root.right, res);
        res.add(root.val);
    }

    /**
     * 很好的思想~~
     * post order: left - right - root
     * modify pre order: root - left - right to root - right - left
     * reverse the result
     */
    public List<Integer> postorderTraversalA2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null)
            return res;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode curNode = s.pop();
            res.add(curNode.val); // visit
            if (curNode.left != null)  //注意啊
                s.push(curNode.left);
            if (curNode.right != null)
                s.push(curNode.right);
        }
        Collections.reverse(res);
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