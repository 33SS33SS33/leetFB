package aFB;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/5/11.
 * "Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from left to right.
 * Examples:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its vertical order traversal as:
 * [
 * [9],
 * [3,15],
 * [20],
 * [7]
 * ]
 * "首先根节点的值是0 然后往左走 就减1 往右走就加1 然后用个字典把值一样的记录到一起就行了
 */

public class BTVerticalOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = buildTree();
        System.out.println(new BTVerticalOrderTraversal().verticalOrderA(root));
        System.out.println(new BTVerticalOrderTraversal().verticalOrder(root));
    }

    //https://discuss.leetcode.com/topic/31954/5ms-java-clean-solution/2
    //最好的
    public List<List<Integer>> verticalOrderA(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        q.add(root);
        cols.add(0);
        int min = 0;
        int max = 0;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int col = cols.poll();
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<Integer>());
            }
            map.get(col).add(node.val);
            if (node.left != null) {
                q.add(node.left);
                cols.add(col - 1);
                min = Math.min(min, col - 1);
            }
            if (node.right != null) {
                q.add(node.right);
                cols.add(col + 1);
                max = Math.max(max, col + 1);
            }
        }
        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;
    }

    //Alternatively, we can calculate the rang first, then insert into buckets
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> cols = new ArrayList<>();
        if (root == null) {
            return cols;
        }
        int[] range = new int[]{0, 0};
        getRange(root, range, 0);
        for (int i = range[0]; i <= range[1]; i++) {
            cols.add(new ArrayList<Integer>());
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();
        queue.add(root);
        colQueue.add(-range[0]);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int col = colQueue.poll();
            cols.get(col).add(node.val);
            if (node.left != null) {
                queue.add(node.left);
                colQueue.add(col - 1);
            }
            if (node.right != null) {
                queue.add(node.right);
                colQueue.add(col + 1);
            }
        }
        return cols;
    }

    public void getRange(TreeNode root, int[] range, int col) {
        if (root == null) {
            return;
        }
        range[0] = Math.min(range[0], col);
        range[1] = Math.max(range[1], col);
        getRange(root.left, range, col - 1);
        getRange(root.right, range, col + 1);
    }

    static TreeNode buildTree() {
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
        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
