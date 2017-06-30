package tree;

/**
 * Given two binary trees, write a function to check if they are equal or not.
 * Two binary trees are considered equal if they are structurally identical
 * and the nodes have the same value.
 * Tags: Tree, DFS
 * <p>
 * 先序遍历
 */
class SameTree {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(6);
        root1.left = n1;
        root1.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.right = n5;

        TreeNode root2 = new TreeNode(1);

        root2.left = n1;
        root2.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.right = n5;
        System.out.println(new SameTree().isSameTree(root1, root2));
        System.out.println(new SameTree().isSameTreeA(root1, root2));
        System.out.println(new SameTree().isSameTreeB(root1, root2));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val == q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        return false;
    }

    /**
     * Recursive, pre-order check
     * If both node's values are the same, left subtrees are same and so right
     * Return true, otherwise return false
     */
    public static boolean isSameTreeA(TreeNode p, TreeNode q) {
        if (p == null || q == null)
            return p == q; // if one of them is null, it will return false. both null, true.
        return p.val == q.val && isSameTreeA(p.left, q.left) && isSameTreeA(p.right,
                q.right); // equal val, equal subtrees
    }

    /*时间复杂度是O(n)，空间复杂度是O(logn)*/
    public boolean isSameTreeB(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        return isSameTreeB(p.left, q.left) && isSameTreeB(p.right, q.right);
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