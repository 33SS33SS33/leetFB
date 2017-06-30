package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * 核心思路就是把左边的换成右边的样子 所以用后序的遍历方式 然后新建一棵树就行了
 * 就是注意建立新树的时候的顺序即可 注意递归的返回值 特别是not root or not root.left
 * Root                  L
 * /  \                  /  \
 * L    R              R   Root
 * 1. 对于一个parent来说，假如有right node，必须得有left node。而有left node，right node可以为空。
 * 而right node必须为叶子节点。所以该树每层至多有2个节点，并且2节点有共同的parent。
 * 2. 所以对于最底层来说，必有一个left node，而这个left node则为整个新树的根 —— 例子中的4为最底层的左节点，最后成为新树的root。
 */
public class BTUpsideDown {
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
        System.out.println(
                new BTUpsideDown().levelOrder(new BTUpsideDown().upsideDownBinaryTree(root)));
    }

    /**
     * Just think about how you can save the tree information 最好的
     * you need before changing the tree structure.
     */
    public TreeNode UpsideDownBinaryTreea(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null;
        TreeNode next = null;
        TreeNode temp = null;
        while (curr != null) {
            next = curr.left;
            curr.left = temp;
            temp = curr.right;
            curr.right = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public TreeNode upsideDownBinaryTreeb(TreeNode root) {
        if (root == null || root.left == null && root.right == null)
            return root;
        TreeNode newRoot = upsideDownBinaryTreeb(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }

    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        inOrder(root);
        TreeNode newRoot = queue.poll();
        root = newRoot;
        while (!queue.isEmpty()) {
            root.right = queue.poll();
            root.left = queue.poll();
            root = root.right;
        }
        return newRoot;
    }

    void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        queue.add(root);
        if (root.left != null) {
            queue.add(root.right);
        }
        // bad side effect
        root.left = null;
        root.right = null;
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

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
