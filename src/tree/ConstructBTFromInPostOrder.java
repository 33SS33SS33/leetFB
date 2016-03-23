package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * <p/>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p/>
 * Tags: Tree, Array, DFS
 */
class ConstructBTFromInPostOrder {
    public static void main(String[] args) {
        int[] inorder = { 4, 2, 5, 1, 3, 6 };
        int[] postorder = { 4, 5, 2, 6, 3, 1 };
        TreeNode root = new ConstructBTFromInPostOrder().buildTree(inorder, postorder);
        System.out.println(new ConstructBTFromInPostOrder().levelOrder(root));
    }

    /**
     * DFS, find root, find range of left and right sub trees
     * The calculation of post array is trivial
     * For left subtree, ps = ps, pe = ps - is - 1 + pos(offset, including root)
     * For right subtree, ps = pe - ie + pos, pe = pe - 1(without root)
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null)
            return null;
        return buildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder, int is, int ie, int ps, int pe) {
        if (ps > pe)
            return null;
        TreeNode root = new TreeNode(postorder[pe]);
        int pos = is;
        for (; pos <= ie; pos++) {
            if (inorder[pos] == root.val)
                break;
        }
        // Note how to calcuclate the start and end indices for post array
        root.left = buildTree(inorder, postorder, is, pos - 1, ps, ps - is - 1 + pos);
        root.right = buildTree(inorder, postorder, pos + 1, ie, pe - ie + pos, pe - 1);
        return root;
    }

    private List<List<Integer>> levelOrder(TreeNode root) {
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

    public class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}
