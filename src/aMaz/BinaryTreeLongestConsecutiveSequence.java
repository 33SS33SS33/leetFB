package aMaz;

public class BinaryTreeLongestConsecutiveSequence {
    /**
     * "Given a binary tree, find the length of the longest consecutive sequence path.
     * The path refers to any sequence of nodes from some starting node
     * to any node in the tree along the parent-child connections.
     * The longest consecutive path need to be from parent to child (cannot be the reverse).
     * 1
     * \
     * 3
     * / \
     * 2   4
     *      \
     *      5
     * Longest consecutive sequence path is 3-4-5, so return 3.
     * 2
     * \
     * 3
     * /
     * 2
     * /
     * 1
     * Longest consecutive sequence path is 2-3,not3-2-1, so return 2."
     * "这道题的题意是  要找出来从父亲到孩子方向的最长的递增序列长度(每次递增1)
     * 这道题用dfs来遍历 主要就是dfs的时候 需要一个有一个counter来记录当前的长度
     * 如果断掉了 就把counter置为1重新开始"
     */
    //Just very intuitive depth-first search,
    // send cur node value to the next level and compare it with the next level node.
    private int max = 0;

    public int binaryTreeLongestConsecutiveSequence(TreeNode root) {
        if (root == null)
            return 0;
        helper(root, 0, root.val);
        return max;
    }

    public void helper(TreeNode root, int cur, int target) {
        if (root == null)
            return;
        if (root.val == target)
            cur++;
        else
            cur = 1;
        max = Math.max(cur, max);
        helper(root.left, cur, root.val + 1);
        helper(root.right, cur, root.val + 1);
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
