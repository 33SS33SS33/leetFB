package aMaz;

/**
 * Created by shanshan on 2/13/19.
 * Given a binary search tree with non-negative values,
 * find the minimum absolute difference between values of any two nodes.
 * Input:
 * 1
 * \
 * 3
 * /
 * 2
 * Output:1
 * Explanation:The minimum absolute difference is 1,
 * which is the difference between 2 and 1 (or between 2 and 3).
 * Note: There are at least two nodes in this BST.
 */
public class MinimumAbsoluteDifferenceinBST {

    int min = Integer.MAX_VALUE;
    Integer prev = null;

    public int minimumAbsoluteDifferenceinBST(TreeNode root) {
        if (root == null)
            return min;
        minimumAbsoluteDifferenceinBST(root.left);
        if (prev != null) {
            min = Math.min(min, root.val - prev);
        }
        prev = root.val;
        minimumAbsoluteDifferenceinBST(root.right);
        return min;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.val = value;
        }
    }
}
