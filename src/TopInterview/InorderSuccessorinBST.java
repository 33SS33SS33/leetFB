package TopInterview;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given a binary search tree and a node in it, find the in-order inorderSuccessorinBST of that node in the BST.
 * Note: If the given node has no in-order inorderSuccessorinBST in the tree, return null.
 * 只有往左走的时候 也就是p的值比root得小的时候 就尝试去找个更小的 需要记录succ就可以
 */
public class InorderSuccessorinBST {
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
        TreeNode res1 = new InorderSuccessorinBST().inorderSuccessorinBSTa(root, n1);
        TreeNode res2 = new InorderSuccessorinBST().inorderSuccessorinBST(root, n1);
        System.out.println(res1.val);
        System.out.println(res2.val);
    }

    public TreeNode inorderSuccessorinBSTa(TreeNode root, TreeNode p) {
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

    /**
     * Given a binary search tree and a node in it, find the in-order inorderSuccessorinBST of that node in the BST.
     * Note: If the given node has no in-order inorderSuccessorinBST in the tree, return null.
     * 只有往左走的时候 也就是p的值比root得小的时候 就尝试去找个更小的 需要记录succ就可以
     */
    public TreeNode inorderSuccessorinBST(TreeNode root, TreeNode p) {
        if (root == null)
            return null;
        if (root.val <= p.val) {
            return inorderSuccessorinBST(root.right, p);
        } else {
            TreeNode left = inorderSuccessorinBST(root.left, p);
            return (left != null) ? left : root;
        }
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
