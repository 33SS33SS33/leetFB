package aMaz;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
public class LowestCommonAncestorofaBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(3);
        TreeNode n5 = new TreeNode(6);
        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.right = n5;
        System.out.println(new LowestCommonAncestorofaBST().lowestCommonAncestorBST(root, n3, n4).val);
    }

    public aFB.TreeNode lowestCommonAncestor(aFB.TreeNode root, aFB.TreeNode p, aFB.TreeNode q) {
        if (root == null || root == p || root == q) return root;
        aFB.TreeNode left = lowestCommonAncestor(root.left, p, q);
        aFB.TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    //迭代
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


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
