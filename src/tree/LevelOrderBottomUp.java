package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the bottom-up level order traversal of its
 * nodes' values. (ie, from left to right, level by level from leaf to root).
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its bottom-up level order traversal as:
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 * Tags: Tree, BFS
 * DFS BFS都行 遍历的时候存一下当前结点在的level即可 然后插入位置用负数来选择就可以了
 */
class LevelOrderBottomUp {
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
        System.out.println(new LevelOrderBottomUp().levelOrderBottom(root).toString());
        System.out.println(new LevelOrderBottomUp().levelOrderBottomc(root).toString());
    }

    /**
     * 每次插入链表的头结点
     * Use a level list to store the nodes of this level
     * Add root to it to begin
     * Build next level with current level, add current level value to result
     * Assign next level to current level
     * Add curLevel to first of result each time to get reverse order
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (root == null)
            return res;
        /*store the nodes of the level*/
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> curLevel = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                curLevel.add(n.val);
                if (n.left != null)
                    q.add(n.left);
                if (n.right != null)
                    q.add(n.right);
            }
            res.add(0, curLevel);
        }
        return res;
    }

    /**
     * The addFirst() method of LinkedLinked save us from reverse final result.
     */
    public List<List<Integer>> levelOrderBottomc(TreeNode root) {
        LinkedList<List<Integer>> list = new LinkedList<List<Integer>>();
        addLevel(list, 0, root);
        return list;
    }

    private void addLevel(LinkedList<List<Integer>> list, int level, TreeNode node) {
        if (node == null)
            return;
        if (list.size() - 1 < level)
            list.addFirst(new LinkedList<Integer>());
        list.get(list.size() - 1 - level).add(node.val);
        addLevel(list, level + 1, node.left);
        addLevel(list, level + 1, node.right);
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