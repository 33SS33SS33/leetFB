package aTOP50facebook;

import aFB.TreeNode;

public class LowestCommonAncestorofaBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        // make sure p < q
        if (p.val > q.val)
            return lowestCommonAncestorBST(root, q, p);
        // find p <= root <= q
        while ((root.val - p.val) * (root.val - q.val) > 0) {
            //        while (!(p.val <= root.val && root.val <= q.val)) {
            root = root.val > q.val ? root.left : root.right;
        }
        return root;
    }
}
