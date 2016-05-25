package tree;

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
 */

/**这个题可以用BFS还有DFS BFS比较好
 自己的解法类似BFS
 把节点一排一排的入栈 然后有一个direction变量  可以用来规定入栈的顺序 从左到右 还是从右到左
 然后下次就反过来
 然后每次先遍历栈里的元素 这样就得到当前层的答案  然后再把下一层的入栈
 */

class BinaryTreeZigZag {
    public static void main(String[] args) {
        TreeNode head = buildTree();
        System.out.println(new BinaryTreeZigZag().zigzagLevelOrder(head).toString());
        System.out.println(new BinaryTreeZigZag().zigzagLevelOrderB(head).toString());
        System.out.println(new BinaryTreeZigZag().zigzagLevelOrderC(head).toString());
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

    /**
     * Use two lists, one for cur level, one for next level
     * Use a binary flag to determine whether we toggle the order of current level or not
     * Update flag after each level
     */
    public List<List<Integer>> zigzagLevelOrderB(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null)
            return res;
        List<TreeNode> level = new ArrayList<TreeNode>();
        level.add(root);
        boolean toggle = false;
        while (!level.isEmpty()) {
            List<Integer> curLevel = new ArrayList<Integer>();
            List<TreeNode> nextLevel = new ArrayList<TreeNode>();
            while (!level.isEmpty()) {
                TreeNode temp = level.remove(0);
                if (!toggle)
                    curLevel.add(temp.val);
                else
                    curLevel.add(0, temp.val); // insert to front
                if (temp.left != null)
                    nextLevel.add(temp.left);
                if (temp.right != null)
                    nextLevel.add(temp.right);
            }
            res.add(curLevel);
            level = nextLevel;
            toggle = toggle ? false : true;
        }
        return res;
    }

    /**
     * use stack
     */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrderC(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (root == null)
            return res;
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        int level = 1;
        ArrayList<Integer> item = new ArrayList<Integer>();
        item.add(root.val);
        res.add(item);
        stack.push(root);
        while (!stack.isEmpty()) {
            LinkedList<TreeNode> newStack = new LinkedList<TreeNode>();
            item = new ArrayList<Integer>();
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (level % 2 == 0) {
                    if (node.left != null) {
                        newStack.push(node.left);
                        item.add(node.left.val);
                    }
                    if (node.right != null) {
                        newStack.push(node.right);
                        item.add(node.right.val);
                    }
                } else {
                    if (node.right != null) {
                        newStack.push(node.right);
                        item.add(node.right.val);
                    }
                    if (node.left != null) {
                        newStack.push(node.left);
                        item.add(node.left.val);
                    }
                }
            }
            level++;
            if (item.size() > 0)
                res.add(item);
            stack = newStack;
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
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
