package hard;

/**
 * Given a binary tree, find the maximum path sum.
 * The path may start and end at any node in the tree.
 * For example:
 * Given the below binary tree,
 * 1
 * / \
 * 2   3
 * Return 6.
 * Tags: Tree, DFS
 * For each node like following, there should be four ways existing for max path:
 * 1. Node only
 * 2. L-sub + Node
 * 3. R-sub + Node
 * 4. L-sub + Node + R-sub
 * 后序遍历 然后返回最大值
 * 注意一下返回值  如果是本身节点然后加上左右节点 这个值不应该返回  因为父节点无法使用这个path   所以使用self.res直接对res进行更新
 * 所以返回值只是返回当前节点  带上左或者右某一条路径 或者都不带 值返回本身  所以在处理left和right的时候 要把负数变为0
 */
class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        TreeNode head = buildTree();
        System.out.println(new BinaryTreeMaximumPathSum().maxPathSuma(head));
    }

    /**
     * 最好的
     * A path from start to end, goes up on the tree for 0 or more steps, then goes
     * down for 0 or more steps. Once it goes down, it can't go up. Each path has a
     * highest node, which is also the lowest common ancestor of all other nodes on the path.
     * A recursive method maxPathDown(TreeNode node) (1) computes the
     * maximum path sum with highest node is the input node, update maximum if
     * necessary (2) returns the maximum sum of the path that can be extended to
     * input node's parent.
     */
    int maxValue;

    public int maxPathSuma(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null)
            return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
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