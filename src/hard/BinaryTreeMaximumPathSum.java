package hard;

import java.util.ArrayList;

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
 */

/** 后序遍历 然后返回最大值
 * 注意一下返回值  如果是本身节点然后加上左右节点 这个值不应该返回  因为父节点无法使用这个path   所以使用self.res直接对res进行更新
 * 所以返回值只是返回当前节点  带上左或者右某一条路径 或者都不带 值返回本身  所以在处理left和right的时候 要把负数变为0
 */
class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        TreeNode head = buildTree();
        System.out.println(new BinaryTreeMaximumPathSum().maxPathSum(head));
        System.out.println(new BinaryTreeMaximumPathSum().maxPathSumB(head));
    }

    /**
     * Post order traversal
     * Get path sum of left and right sub trees
     * curMax of this level can be root's value v or v+left or v+right
     * max sum can be biggest of prevMax, curMax, and left + right + root.val
     */
    int max;

    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;
        max = root.val;
        helper(root);
        return max;
    }

    int helper(TreeNode root) {
        if (root == null)
            return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        // calculate current max, only one branch
        int curMax = Math.max(root.val, Math.max(left, right) + root.val);
        // update max
        max = Math.max(max, Math.max(curMax, left + right + root.val));
        return curMax; // note that return curMax here for result of upper level
    }

    /**
     * 在这里树没有被看成有向图，而是被当成无向图来寻找路径。
     * 因为这个路径的灵活性，我们需要对递归返回值进行一些调整，而不是通常的返回要求的结果。
     * 在这里，函数的返回值定义为以自己为根的一条从根到子结点的最长路径（这里路径就不是当成无向图了，必须往单方向走）。
     * 这个返回值是为了提供给它的父结点计算自身的最长路径用，而结点自身的最长路径（也就是可以从左到右那种）则只需计算然后更新即可。
     * 这样一来，一个结点自身的最长路径就是它的左子树返回值（如果大于0的话），加上右子树的返回值（如果大于0的话），再加上自己的值。
     * 而返回值则是自己的值加上左子树返回值，右子树返回值或者0（注意这里是“或者”，而不是“加上”，因为返回值只取一支的路径和）。
     * 在过程中求得当前最长路径时比较一下是不是目前最长的，如果是则更新
     */
    /**算法的本质还是一次树的遍历，所以复杂度是O(n)。而空间上仍然是栈大小O(logn)*/
    public int maxPathSumB(TreeNode root) {
        if (root == null)
            return 0;
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(Integer.MIN_VALUE);
        helper(root, res);
        return res.get(0);
    }

    private int helper(TreeNode root, ArrayList<Integer> res) {
        if (root == null)
            return 0;
        int left = helper(root.left, res);
        int right = helper(root.right, res);
        int cur = root.val + (left > 0 ? left : 0) + (right > 0 ? right : 0);
        if (cur > res.get(0))
            res.set(0, cur);
        return root.val + Math.max(left, Math.max(right, 0));
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
        int      val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}