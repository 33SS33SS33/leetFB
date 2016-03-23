package tree;

/**
 * Given a binary tree, determine if it is height-balanced.
 * <p/>
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more
 * than 1.
 * <p/>
 * Tags: Tree, DFS
 */
class BalancedBT {
    public static void main(String[] args) {
        TreeNode head = buildTree();
        System.out.println(new BalancedBT().isBalanced(head));
        System.out.println(new BalancedBT().isBalanced2(head));
        System.out.println(new BalancedBT().isBalancedC(head));
    }

    private boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }

    /**
     * Modification of max depth
     * If current node is null, return 0
     * Compare left depth with right depth
     * If the difference is bigger than 1, set isBalance false
     * Otherwise go on to the rest of the nodes
     */
    private int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
            return -1;
        return Math.max(left, right) + 1;
    }

    public boolean isBalanced2(TreeNode root) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if (root == null)
            return true;
        return Math.abs(height(root.left) - height(root.right)) <= 1 &&
                isBalanced2(root.left) && isBalanced2(root.right);
        //        return isBalanced2();
    }

    int height(TreeNode root) {
        if (root == null)
            return 0;
        else
            return Math.max(height(root.left), height(root.right)) + 1;
    }

    /**
     * --------creek------
     */
    public boolean isBalancedC(TreeNode root) {
        if (root == null)
            return true;
        if (getHeight(root) == -1)
            return false;
        return true;
    }

    public int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (left == -1 || right == -1)
            return -1;
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    public static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static TreeNode buildTree() {
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
}