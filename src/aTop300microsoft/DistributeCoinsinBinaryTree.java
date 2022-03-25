package aTop300microsoft;

import aFB.TreeNode;

/**
 * Input: root = [3,0,0]
 * Output: 2
 * Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
 */
//https://leetcode.com/problems/distribute-coins-in-binary-tree/
public class DistributeCoinsinBinaryTree {
        int ans;
        public int distributeCoins(TreeNode root) {
            ans = 0;
            dfs(root);
            return ans;
        }

        public int dfs(TreeNode node) {
            if (node == null) return 0;
            int L = dfs(node.left);
            int R = dfs(node.right);
            ans += Math.abs(L) + Math.abs(R);
            return node.val + L + R - 1;
        }
}
