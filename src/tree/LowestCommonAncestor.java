package tree;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 */

/**
 * Given a binary tree (not a binary search tree) and two values say n1 and n2,
 * write a program to find the least common ancestor.
 * Allow a node to be a descendant of itself
 * <p/>
 * Tags: Tree
 */
class LowestCommonAncestor {
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
        System.out.println(new LowestCommonAncestor().findLca(root, 4, 5).val);
        System.out.println(new LowestCommonAncestor().lowestCommonAncestorB(root, n3, n4).val);
    }

    /**
     * If root is null, just return null
     * If root's value matches with n1 or n2, return root
     * If not, find lca recursively in both left and right subtrees
     * If both are not null, one value in left and the other in right,
     * return root
     * If one is not null, return that one
     */
    public TreeNode findLca(TreeNode root, int n1, int n2) {
        if (root == null)
            return null;
        if (root.val == n1 || root.val == n2)
            return root;
        TreeNode leftLca = findLca(root.left, n1, n2);
        TreeNode rightLca = findLca(root.right, n1, n2);
        if (leftLca != null && rightLca != null)
            return root;
        return leftLca != null ? leftLca : rightLca;
    }

    /**
     * creek------
     */
    public TreeNode lowestCommonAncestorB(TreeNode root, TreeNode p, TreeNode q) {
        return lcaHelper(root, p, q).node;
    }
    public Entity lcaHelper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return new Entity(0, null);
        Entity left = lcaHelper(root.left, p, q);
        if (left.count == 2)
            return left;

        Entity right = lcaHelper(root.right, p, q);
        if (right.count == 2)
            return right;

        int numTotal = left.count + right.count;
        if (root == p || root == q) {
            numTotal++;
        }
        return new Entity(numTotal, root);
    }

    class Entity {
        public int      count;
        public TreeNode node;

        public Entity(int count, TreeNode node) {
            this.count = count;
            this.node = node;
        }
    }

    static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int v) {
            val = v;
        }
    }
}
