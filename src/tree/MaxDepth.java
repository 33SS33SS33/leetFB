package tree;

/**
 * Given a binary tree, find its maximum depth.
 * <p/>
 * The maximum depth is the number of nodes along the longest path from the
 * root node down to the farthest leaf node.
 * <p/>
 * Tags: Tree, DFS
 */
class MaxDepth {
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
        System.out.println(new MaxDepth().maxDepth(root));
    }

    /**
     * Recursive, O(n)
     * If tree is empty, return 0
     * Else
     * Get the max depth of left subtree recursively
     * Get the max depth of right subtree recursively
     * Get the max of max depths of left and right subtrees and add 1 to it
     */
    private int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}