package aMaz;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * 迭代递归都可以写 都实现
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
        System.out.println(new LowestCommonAncestorofaBST().lowestCommonAncestorBSTB(root, n3, n4).val);
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

    /**
     * creek 递归
     * This problem can be solved by using BST property
     * i.e., left < parent < right for each node. There are 3 cases to handle.
     */
    public TreeNode lowestCommonAncestorBSTB(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val)
            return lowestCommonAncestorBSTB(root, q, p);
        TreeNode m = root;
        if (m.val > p.val && m.val < q.val) {
            return m;
        }
        else if (m.val > p.val && m.val > q.val) {
            return lowestCommonAncestorBSTB(root.left, p, q);
        }
        else if (m.val < p.val && m.val < q.val) {
            return lowestCommonAncestorBSTB(root.right, p, q);
        }
        return root;
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
