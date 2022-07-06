package top50Google;

import aFB.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 * <p>
 * Collect all the leaf nodes.
 * Remove all the leaf nodes.
 * Repeat until the tree is empty.
 */
public class FindLeavesofBinaryTree {
    private List<List<Integer>> solution;

    private int getHeight(TreeNode root) {
        // return -1 for null nodes
        if (root == null) {
            return -1;
        }

        // first calculate the height of the left and right children
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        int currHeight = Math.max(leftHeight, rightHeight) + 1;
        if (this.solution.size() == currHeight) {
            this.solution.add(new ArrayList<>());
        }

        this.solution.get(currHeight).add(root.val);
        return currHeight;
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        this.solution = new ArrayList<>();
        getHeight(root);

        return this.solution;
    }
}
