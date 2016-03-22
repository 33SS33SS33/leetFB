package tree;

import java.util.*;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * <p/>
 * For example,
 * Given
 * <p/>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * The flattened tree should look like:
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * <p/>
 * Hints:
 * If you notice carefully in the flattened tree, each node's right child
 * points to the next node of a pre-order traversal.
 * <p/>
 * Tags: Tree, DFS
 */
class FlatenBinaryTreeToLinkedList {
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
        n2.left = n5;
        new FlatenBinaryTreeToLinkedList().flattenC(root);
        System.out.println(levelOrder(root));
    }

    /**
     * Add root's right subtree to left node's rightmost child
     * Then set that subtree as root's right subtree
     * And set root's left child to null
     * Move root to its right child and repeat
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) { // check left child
                TreeNode n = root.left;
                while (n.right != null)
                    n = n.right; // rightmost child of left
                n.right = root.right; // insert right subtree to its right
                root.right = root.left; // set left subtree as right subtree
                root.left = null; // set left to null
            }
            root = root.right; // move to right child
        }
    }

    /**
     * creek------
     */
    public void flattenB(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        while (p != null || !stack.empty()) {
            if (p.right != null) {
                stack.push(p.right);
            }
            if (p.left != null) {
                p.right = p.left;
                p.left = null;
            } else if (!stack.empty()) {
                TreeNode temp = stack.pop();
                p.right = temp;
            }
            p = p.right;
        }
    }

    /**
     * Num 2
     */
    public ArrayList<TreeNode> flattenC(TreeNode root) {
        ArrayList<TreeNode> pre = new ArrayList<TreeNode>();
        pre.add(null);
        ArrayList<TreeNode> res = helper(root, pre);
        return res;
    }

    private ArrayList<TreeNode> helper(TreeNode root, ArrayList<TreeNode> pre) {
        if (root == null)
            return null;
        TreeNode right = root.right;
        if (pre.get(0) != null) {
            pre.get(0).left = null;
            pre.get(0).right = root;
        }
        pre.set(0, root);
        helper(root.left, pre);
        helper(right, pre);
        return pre;
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> curLevel = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = queue.poll();
                curLevel.add(n.val);
                if (n.left != null)
                    queue.add(n.left);
                if (n.right != null)
                    queue.add(n.right);
            }
            res.add(curLevel);
        }
        return res;
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