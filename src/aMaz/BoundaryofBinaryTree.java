package aMaz;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by shanshan on 8/28/18.
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
 * Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.
 * The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.
 * The right-most node is also defined by the same way with left and right exchanged.
 * Input:
 * 1
 * \
 * 2
 * / \
 * 3   4
 * Ouput:
 * [1, 3, 4, 2]
 * Explanation:
 * The root doesn't have left subtree, so the root itself is left boundary.
 * The leaves are node 3 and 4.
 * The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
 * So order them in anti-clockwise without duplicates and we have [1,3,4,2].
 * <p>
 * Input:
 * ____1_____
 * /          \
 * 2            3
 * / \          /
 * 4   5        6
 * / \      / \
 * 7   8    9  10
 * Ouput:
 * [1,2,4,7,8,9,10,6,3]
 * Explanation:
 * The left boundary are node 1,2,4. (4 is the left-most node according to definition)
 * The leaves are node 4,7,8,9,10.
 * The right boundary are node 1,3,6,10. (10 is the right-most node).
 * So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
 */
public class BoundaryofBinaryTree {
    //best
    List<Integer> nodes = new ArrayList<>(1000);

    public List<Integer> boundaryOfBinaryTreeA(TreeNode root) {
        if (root == null) return nodes;

        nodes.add(root.val);
        leftBoundary(root.left);
        leaves(root.left);
        leaves(root.right);
        rightBoundary(root.right);

        return nodes;
    }

    public void leftBoundary(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return;
        nodes.add(root.val);
        if (root.left == null) leftBoundary(root.right);
        else leftBoundary(root.left);
    }

    public void rightBoundary(TreeNode root) {
        if (root == null || (root.right == null && root.left == null)) return;
        if (root.right == null) rightBoundary(root.left);
        else rightBoundary(root.right);
        nodes.add(root.val); // add after child visit(reverse)
    }

    public void leaves(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            nodes.add(root.val);
            return;
        }
        leaves(root.left);
        leaves(root.right);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    ////////////////////////////////////////////////////////////
    public boolean isLeaf(TreeNode t) {
        return t.left == null && t.right == null;
    }

    public void addLeaves(List<Integer> res, TreeNode root) {
        if (isLeaf(root)) {
            res.add(root.val);
        } else {
            if (root.left != null) {
                addLeaves(res, root.left);
            }
            if (root.right != null) {
                addLeaves(res, root.right);
            }
        }
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        if (!isLeaf(root)) {
            res.add(root.val);
        }
        TreeNode t = root.left;
        while (t != null) {
            if (!isLeaf(t)) {
                res.add(t.val);
            }
            if (t.left != null) {
                t = t.left;
            } else {
                t = t.right;
            }

        }
        addLeaves(res, root);
        Stack<Integer> s = new Stack<>();
        t = root.right;
        while (t != null) {
            if (!isLeaf(t)) {
                s.push(t.val);
            }
            if (t.right != null) {
                t = t.right;
            } else {
                t = t.left;
            }
        }
        while (!s.empty()) {
            res.add(s.pop());
        }
        return res;
    }

}
