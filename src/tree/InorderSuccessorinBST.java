package tree;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */

/**
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * Note: If the given node has no in-order successor in the tree, return null.
 * 只有往左走的时候 也就是p的值比root得小的时候 就尝试去找个更小的 需要记录succ就可以
 */
public class InorderSuccessorinBST {
    /**
     * 错的
     */
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
        TreeNode res = new InorderSuccessorinBST().inorderSuccessor(root, n1);
        System.out.println(res.val);
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == p) {
            return leftMost(p.right);
        }
        if (p.val < root.val) {
            p = inorderSuccessor(root.left, p);
            if (p == null) {
                return root;
            }
            return p;
        }
        return inorderSuccessor(root.right, p);
    }

    TreeNode leftMost(TreeNode root) {
        if (root == null)
            return null;
        if (root.left != null)
            return leftMost(root.left);
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
