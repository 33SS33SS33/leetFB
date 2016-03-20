package hard;

/**
 * Given a binary tree, find the maximum path sum.
 *
 * The path may start and end at any node in the tree.
 *
 * For example:
 * Given the below binary tree,
 *
 *        1
 *       / \
 *      2   3
 * Return 6.
 *
 * Tags: Tree, DFS
 */
class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        TreeNode head= buildTree();
        System.out.println(new BinaryTreeMaximumPathSum().maxPathSum(head));
    }

    int max;

    /**
     * Post order traversal
     * Get path sum of left and right sub trees
     * curMax of this level can be root's value v or v+left or v+right
     * max sum can be biggest of prevMax, curMax, and left + right + root.val
     */
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        max = root.val;
        helper(root);
        return max;
    }

    /**
     * Post order traversal
     */
    int helper(TreeNode root) {
        if (root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        // calculate current max, only one branch
        int curMax = Math.max(root.val, Math.max(left, right) + root.val);
        // update max
        max = Math.max(max, Math.max(curMax, left + right + root.val));
        return curMax; // note that return curMax here for result of upper level
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

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}