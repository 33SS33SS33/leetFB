package tree;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more than 1.
 * Tags: Tree, DFS
 * 还是自底向上的得到深度值 然后计算
 */
class BalancedBT {
    public static void main(String[] args) {
        TreeNode head = new BalancedBT().buildTree();
        System.out.println(new BalancedBT().isBalancedA(head));
    }

    public boolean isBalancedA(TreeNode root) {
        if (root == null)
            return true;
        return Math.abs(height(root.left) - height(root.right)) <= 1 &&
                isBalancedA(root.left) && isBalancedA(root.right);
    }

    int height(TreeNode root) {
        if (root == null)
            return 0;
        else
            return Math.max(height(root.left), height(root.right)) + 1;
    }

    public static TreeNode buildTree() {
        TreeNode t0 = new TreeNode(20);
        TreeNode t1 = new TreeNode(9);
        TreeNode t2 = new TreeNode(49);
        TreeNode t3 = new TreeNode(5);
        TreeNode t4 = new TreeNode(12);
        TreeNode t5 = new TreeNode(15);
        TreeNode t6 = new TreeNode(23);
        TreeNode t7 = new TreeNode(52);
        TreeNode t8 = new TreeNode(50);

        t0.left = t1;
        t0.right = t2;
        t1.left = t3;
        t1.right = t4;
        t4.right = t5;
        t2.left = t6;
        t2.right = t7;
        t7.left = t8;

        return t0;
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