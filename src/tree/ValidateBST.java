package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Tags: Tree, DFS
 */

/**
 * 使用先序遍历
 * 如果访问了左节点  则当前点得值是左节点的最大值
 * 如果访问了右节点  则当前点得值是右节点的最小值
 * 然后递归更新最大最小值即可
 */
class ValidateBST {
    public static void main(String[] args) {
        TreeNode r = new TreeNode(Integer.MAX_VALUE);
        ValidateBST v = new ValidateBST();
        System.out.println(v.isValidBST(r));
        System.out.println(v.isValidBSTA(r));
        System.out.println(v.isValidBSTB(r));
        System.out.println(v.isValidBSTC(r));
        System.out.println(v.isValidBSTD(r));
    }

    /*时间复杂度是O(n)，空间复杂度是O(logn)*/
    /**
     * Recursive
     * Check current node
     * Check left subtree
     * Compare with current node and set predecessor
     * Check right subtree
     */
    Integer pred = null;

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        if (!isValidBST(root.left))
            return false;
        // visit
        if (pred != null && root.val <= pred)
            return false;
        pred = root.val; // set
        if (!isValidBST(root.right))
            return false;
        return true;
    }

    /**
     * Recursive------- still cost O(n)
     * Failed if input include Integer MAX and Integer MIN
     */
    public boolean isValidBSTB(TreeNode root) {
        return isValidBSTB(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // add range of current value and do recursive check
    public boolean isValidBSTB(TreeNode root, int min, int max) {
        return root == null || root.val > min && root.val < max && isValidBSTB(root.left, min,
                root.val) && isValidBSTB(root.right, root.val, max);
    }

    /**
     * Inorder traversal, generate a list, should be increasing order
     */
    public boolean isValidBSTC(TreeNode root) {
        if (root == null)
            return true;
        List<Integer> result = new ArrayList<Integer>();
        inOrderList(root, result);
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i) >= result.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public void inOrderList(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        inOrderList(root.left, res);
        res.add(root.val);
        inOrderList(root.right, res);
    }

    /**
     * Preorder
     * Check if root.val is bigger than value of rightmost node in left subtree
     * and smaller than value of leftmost node in right subtree.
     */
    public boolean isValidBSTD(TreeNode root) {
        if (root == null)
            return true;
        TreeNode temp = null;
        if (root.left != null) {
            temp = root.left;
            while (temp.right != null) { // move to right most 
                temp = temp.right;
            }
            if (temp.val >= root.val)
                return false;
        }
        if (root.right != null) {
            temp = root.right;
            while (temp.left != null) { // move to left most
                temp = temp.left;
            }
            if (temp.val <= root.val)
                return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    /**
     * creek------Iterative
     */
    public boolean isValidBSTA(TreeNode root) {
        if (root == null)
            return true;
        LinkedList<BNode> queue = new LinkedList<BNode>();
        queue.add(new BNode(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
        while (!queue.isEmpty()) {
            BNode b = queue.poll();
            if (b.n.val <= b.left || b.n.val >= b.right) {
                return false;
            }
            if (b.n.left != null) {
                queue.offer(new BNode(b.n.left, b.left, b.n.val));
            }
            if (b.n.right != null) {
                queue.offer(new BNode(b.n.right, b.n.val, b.right));
            }
        }
        return true;
    }

    //define a BNode class with TreeNode and it's boundaries
    class BNode {
        TreeNode n;
        double   left;
        double   right;

        public BNode(TreeNode n, double left, double right) {
            this.n = n;
            this.left = left;
            this.right = right;
        }
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
