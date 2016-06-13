package tree;

/**
 * Created by GAOSHANSHAN835 on 2016/5/9.
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
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
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * DFS BFS都行 遍历的时候存一下当前结点在的level即可 然后插入位置用负数来选择就可以了
 */
public class BTLevelOrderTraversalII {
    public static void main(String[] args) {
        TreeNode root = buildTree();
        System.out.println(new BTLevelOrderTraversalII().levelOrderBottom(root));
        System.out.println(new BTLevelOrderTraversalII().levelOrderBottomb(root));
        System.out.println(new BTLevelOrderTraversalII().levelOrderBottomc(root));
    }

    /**
     * DFS
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
        if (root == null)
            return wrapList;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();
            for (int i = 0; i < levelNum; i++) {
                if (queue.peek().left != null)
                    queue.offer(queue.peek().left);
                if (queue.peek().right != null)
                    queue.offer(queue.peek().right);
                subList.add(queue.poll().val);
            }
            wrapList.add(0, subList);
        }
        return wrapList;
    }

    /**
     * BFS
     */
    public List<List<Integer>> levelOrderBottomb(TreeNode root) {
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
        levelMaker(wrapList, root, 0);
        return wrapList;
    }

    public void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null)
            return;
        if (level >= list.size()) {
            list.add(0, new LinkedList<Integer>());
        }
        levelMaker(list, root.left, level + 1);
        levelMaker(list, root.right, level + 1);
        list.get(list.size() - level - 1).add(root.val);
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

    public static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
