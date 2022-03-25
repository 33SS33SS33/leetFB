package aTop300microsoft;

import aFB.TreeNode;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given a binary search tree and a node in it, find the in-order inorderSuccessorinBST of that node in the BST.
 * Note: If the given node has no in-order inorderSuccessorinBST in the tree, return null.
 * 只有往左走的时候 也就是p的值比root得小的时候 就尝试去找个更小的 需要记录succ就可以
 */
public class InorderSuccessorinBST {

    public TreeNode inorderSuccessorinBST(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null) {
            if (p.val >= root.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }
}
