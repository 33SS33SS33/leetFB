package aFB;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 */

import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be
 * initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 * Tags: Tree, Stack

 * 用一个栈 先存放所有的左节点
 * 然后如果弹出一个元素 就检查这个节点有没有右节点 有的话就 把右节点入栈
 * 然后再入栈所有右节点的左节点
 * 这道题有o1 o1的解法 见右边
 * 未实现
 */
class BSTIterator {
    public static void main(String[] args) {

    }

    Stack<TreeNode> s;

    /**
     * Push all left child, and left child's left child and on and on to stack
     */
    public BSTIterator(TreeNode root) {
        s = new Stack<TreeNode>();
        pushAll(root); // push the whole left subtree
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !s.isEmpty();
    }

    /**
     * @return the next smallest number s
     */
    public int next() {
        TreeNode n = s.pop();
        pushAll(n.right); // put left subtree of right child
        return n.val;
    }

    void pushAll(TreeNode root) {
        while (root != null) {
            s.push(root);
            root = root.left;
        }
    }

    class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

