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
        System.out.println(new LevelOrderBottomUp().levelOrderBottomB(root).toString());
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
     * creek  --level order然后对结果进行一次reverse。时间上和空间上仍是O(n)。
     */
    public List<ArrayList<Integer>> levelOrderBottomB(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> current = new LinkedList<TreeNode>();
        LinkedList<TreeNode> next = new LinkedList<TreeNode>();
        current.offer(root);
        ArrayList<Integer> numberList = new ArrayList<Integer>();
        // need to track when each level starts
        while (!current.isEmpty()) {
            TreeNode head = current.poll();
            numberList.add(head.val);
            if (head.left != null) {
                next.offer(head.left);
            }
            if (head.right != null) {
                next.offer(head.right);
            }
            if (current.isEmpty()) {
                current = next;
                next = new LinkedList<TreeNode>();
                result.add(numberList);
                numberList = new ArrayList<Integer>();
            }
        }
        //        Collections.reverse(result);
        //        return result;
        ArrayList<ArrayList<Integer>> reversedResult = new ArrayList<ArrayList<Integer>>();
        for (int i = result.size() - 1; i >= 0; i--) {
            reversedResult.add(result.get(i));
        }
        return reversedResult;
    }

    public static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}