package aMaz;

/**
 * Created by shanshan on 2/13/19.
 * Given a Binary Search Tree (BST), zigZagConversion it to a Greater Tree such that every key of the original BST
 * is changed to the original key plus sum of all keys greater than the original key in BST.
 * Input: The root of a Binary Search Tree like this:
 * 5
 * /   \
 * 2     13
 * Output: The root of a Greater Tree like this:
 * 18
 * /   \
 * 20     13
 */
public class ConvertBSTtoGreaterTree {

    private TreeNode prev = null;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        helper(root);
        return root;
    }

    public void helper(TreeNode root) {
        if (root == null) return;
        helper(root.right);
        if (prev != null) {
            root.val += prev.val;
        }
        prev = root;
        helper(root.left);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
