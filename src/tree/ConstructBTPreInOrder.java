package tree;

import java.util.*;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * Tags: Tree, Array, DFS
 */
class ConstructBTPreInOrder {
    public static void main(String[] args) {
        int[] preorder = { 1, 2, 4, 5, 3, 6 };
        int[] inorder = { 4, 2, 5, 1, 3, 6 };
        TreeNode root = new ConstructBTPreInOrder().buildTreeA(preorder, inorder);
        TreeNode root2 = new ConstructBTPreInOrder().buildTreeB(preorder, inorder);
        System.out.println(new ConstructBTPreInOrder().levelOrder(root));
        System.out.println(new ConstructBTPreInOrder().levelOrder(root2));
    }

    /**
     * Get root in preorder and search root in inorder
     * Then find range for left subtree and right subtree
     * Recurse down to build subtrees and connect to root
     */
    public TreeNode buildTreeA(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null)
            return null;
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder, int ps, int pe, int is, int ie) {
        if (ps > pe)
            return null;
        int pos = is;
        TreeNode root = new TreeNode(preorder[ps]);
        for (; pos <= ie; pos++) { // find root in inorder, no duplicates
            if (inorder[pos] == preorder[ps])
                break;
        }
        root.left = buildTree(preorder, inorder, ps + 1, ps - is + pos, is,
                pos - 1); // left subtree
        root.right = buildTree(preorder, inorder, ps - is + pos + 1, pe, pos + 1,
                ie); // right subtree
        return root;
    }

    /**
     * 算法最终相当于一次树的遍历，每个结点只会被访问一次，所以时间复杂度是O(n)。
     * 而空间我们需要建立一个map来存储元素到下标的映射，所以是O(n)。
     */
    public TreeNode buildTreeB(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null)
            return null;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode helper(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR,
            HashMap<Integer, Integer> map) {
        if (preL > preR || inL > inR)
            return null;
        TreeNode root = new TreeNode(preorder[preL]);
        int index = map.get(root.val);
        root.left = helper(preorder, preL + 1, index - inL + preL, inorder, inL, index - 1, map);
        root.right = helper(preorder, preL + index - inL + 1, preR, inorder, index + 1, inR, map);
        return root;
    }

    public TreeNode construct(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart,
            int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int val = preorder[preStart];
        TreeNode p = new TreeNode(val);
        //find parent element index from inorder
        int k = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (val == inorder[i]) {
                k = i;
                break;
            }
        }
        p.left = construct(preorder, preStart + 1, preStart + (k - inStart), inorder, inStart, k - 1);
        p.right = construct(preorder, preStart + (k - inStart) + 1, preEnd, inorder, k + 1, inEnd);
        return p;
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
