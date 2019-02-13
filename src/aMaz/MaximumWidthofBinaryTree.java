package aMaz;

import java.util.HashMap;

/**
 * Created by shanshan on 2/13/19. TODO
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels.
 * The binary tree has the same structure as a full binary tree, but some nodes are null.
 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level,
 * where the null nodes between the end-nodes are also counted into the length calculation.
 * Input:
 * 1
 * /   \
 * 3     2
 * / \     \
 * 5   3     9
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 */
public class MaximumWidthofBinaryTree {

    public int widthOfBinaryTree(TreeNode root) {
        int[] max = new int[1];
        HashMap<Integer, Integer> left = new HashMap<>();
        dfs(root, 0, 0, left, max);
        return max[0];
    }

    public void dfs(TreeNode root, int depth, int pos, HashMap<Integer, Integer> left, int[] max) {
        if (root == null) return;
        if (!left.containsKey(depth)) {
            left.put(depth, pos);
        }
        max[0] = Math.max(max[0], pos - left.get(depth) + 1);
        dfs(root.left, depth + 1, 2 * pos, left, max);
        dfs(root.right, depth + 1, 2 * pos + 1, left, max);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
