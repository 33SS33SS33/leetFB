package aMaz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes'
 * values. (ie, from left to right, then right to left for the next level and
 * alternate between).
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its zigzag level order traversal as:
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * Tags: Tree, BFS, Stack
 * 这个题可以用BFS还有DFS BFS比较好
 * 自己的解法类似BFS
 * 把节点一排一排的入栈 有一个direction变量 可以用来规定入栈的顺序 从左到右 还是从右到左
 * 下次就反过来
 * 每次先遍历栈里的元素 这样就得到当前层的答案 再把下一层的入栈
 */

class BinaryTreeZigZagLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode head = buildTree();
        System.out.println(new BinaryTreeZigZagLevelOrderTraversal().zigzagLevelOrdera(head).toString());
        System.out.println(new BinaryTreeZigZagLevelOrderTraversal().zigzagLevelOrder(head).toString());
    }

    /**
     * 最好的
     * O(n) solution by using LinkedList along with ArrayList.
     * So insertion in the inner list and outer list are both O(1).
     * Using DFS and creating new lists when needed.
     */
    public List<List<Integer>> zigzagLevelOrdera(TreeNode root) {
        List<List<Integer>> sol = new ArrayList<List<Integer>>();
        travel(root, sol, 0);
        return sol;
    }

    private void travel(TreeNode curr, List<List<Integer>> sol, int level) {
        if (curr == null)
            return;
        if (sol.size() <= level) {
            List<Integer> newLevel = new LinkedList<Integer>();
            sol.add(newLevel);
        }
        List<Integer> collection = sol.get(level);
        if (level % 2 == 0)
            collection.add(curr.val);
        else
            collection.add(0, curr.val);
        travel(curr.left, sol, level + 1);
        travel(curr.right, sol, level + 1);
    }

    /**
     * Use queue to do BFS.
     * Get queue's size to get nodes in each level.
     * Use a boolean to indicate difference level order.
     * Toggle it after a level is finished.
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        boolean toggle = false;
        while (!q.isEmpty()) {
            List<Integer> curLevel = new ArrayList<Integer>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                if (!toggle)
                    curLevel.add(n.val);
                else
                    curLevel.add(0, n.val);
                if (n.left != null)
                    q.add(n.left);
                if (n.right != null)
                    q.add(n.right);
            }
            toggle = !toggle;
            res.add(curLevel);
        }
        return res;
    }


    private static TreeNode buildTree() {
        TreeNode t0 = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(5);
        TreeNode t5 = new TreeNode(6);

        t0.left = t1;
        t0.right = t2;
        t1.left = t3;
        t1.right = t4;
        t2.right = t5;
        return t0;
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
