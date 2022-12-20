package TopInterview;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class ValidateBinarySearchTree {
    public static void main(String[] args) {
        TreeNode r = new TreeNode(Integer.MAX_VALUE);
        ValidateBinarySearchTree v = new ValidateBinarySearchTree();
        System.out.println(v.isValidBST(r));
        System.out.println(v.isValidBSTb(r));
    }

    /**
     * Inorder traversal, generate a list, should be increasing order
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        List<Integer> result = new ArrayList<Integer>();
        inOrderList(root, result);
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i) >= result.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public void inOrderList(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        inOrderList(root.left, res);
        res.add(root.val);
        inOrderList(root.right, res);
    }

    //https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)

    /**
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     * Assume a BST is defined as follows:
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary searchinRotatedSortedArrayb trees.
     * Tags: Tree, DFS
     * 使用先序遍历
     * 如果访问了左节点  则当前点得值是左节点的最大值
     * 如果访问了右节点  则当前点得值是右节点的最小值
     * 然后递归更新最大最小值即可
     */
    public boolean isValidBSTb(TreeNode root) {
        if (root == null)
            return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && root.val <= pre.val)
                return false;
            pre = root;
            root = root.right;
        }
        return true;
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
