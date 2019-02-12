package aMaz;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by shanshan on 2/7/19. TODO
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * Output: [7,4,1]
 */
public class AllNodesDistanceKinBinaryTree {
    //https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143752/JAVA-Graph-%2B-BFS
    public List<Integer> allNodesDistanceKinBinaryTree(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> reverse = new ArrayDeque<>();
        int targetLevel = helper(reverse, target, 0, root);
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.add(target);
        int level = 0;
        TreeNode preNode = null, parentNode = target;
        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = dq.pollFirst();
                if (level == K) res.add(curNode.val);
                if (curNode.left != null && curNode.left != preNode) dq.add(curNode.left);
                if (curNode.right != null && curNode.right != preNode) dq.add(curNode.right);
            }
            preNode = parentNode;
            if (!reverse.isEmpty()) {
                parentNode = reverse.pollLast();
                dq.add(parentNode);
            } else parentNode = null;
            level++;
            if (level > K) break;
        }
        return res;
    }

    private int helper(Deque<TreeNode> dq, TreeNode target, int level, TreeNode root) {
        if (root == null) return -1;
        if (root == target) return level;
        dq.add(root);
        int left = helper(dq, target, level + 1, root.left);
        if (left != -1) return left;
        int right = helper(dq, target, level + 1, root.right);
        if (right == -1) dq.pollLast();
        return right;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
