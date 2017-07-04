package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * return [1,3,2].
 * Note: Recursive solution is trivial, could you do it iteratively?
 * Tags: Tree, HashTable, Stack
 */
class BTInOrder {
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
        System.out.println(new BTInOrder().inorderTraversala(root));
        System.out.println(new BTInOrder().inorderTraversal(root));
        System.out.println(new BTInOrder().inorderTraversalB(root));
        System.out.println(new BTInOrder().inorderTraversalC(root));
    }

    public List<Integer> inorderTraversala(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
        return list;
    }

    /**
     * The recursive solution is trivial. 递归法
     * 算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即O(logn)
     */
    public List<Integer> inorderTraversalC(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root != null) {
            helper(root, result);
        }
        return result;
    }

    public void helper(TreeNode p, List<Integer> result) {
        if (p.left != null)
            helper(p.left, result);
        result.add(p.val);
        if (p.right != null)
            helper(p.right, result);
    }

    /**
     * -----迭代法  时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)
     * Stack solution, O(n) Space
     * Use a stack to store TreeNodes
     * Go to left most and add each node
     * Pop the node from stack, add its value, and try to go right
     * Stop if stack is empty or node is null
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        while (!s.isEmpty() || root != null) {
            // check whether current node is null
            if (root != null) { // current node is not null
                s.push(root);
                root = root.left;
            } else { // current node is null, pop and go right
                root = s.pop();
                result.add(root.val); // visit()
                root = root.right;
            }
        }
        return result;
    }

    /**
     * O(n)，仍然是一个线性算法。空间复杂度的话我们分析过了，只是两个辅助指针，所以是O(1)。
     * <strong>Morris Traversal</strong>
     * O(1) space
     * Use cur for current node, pre for predecessor of cur node
     * Check whether left subtree exists
     * If yes, find rightmost node in left subtree
     * Check whether rightmost node is connected with cur node
     * If connected, break the connection and visit and move to right subtree
     * Otherwise, connect and traverse left subtree
     * If no, visit cur node and traverse right subtree
     * Morris遍历方法用了线索二叉树，这个方法不需要为每个节点额外分配指针指向其前驱和后继结点，
     * 而是利用叶子节点中的右空指针指向中序遍历下的后继节点就可以了。
     * 算法具体分情况如下：
     * 1. 如果当前结点的左孩子为空，则输出当前结点并将其当前节点赋值为右孩子。
     * 2. 如果当前节点的左孩子不为空，则寻找当前节点在中序遍历下的前驱节点（也就是当前结点左子树的最右孩子）。接下来分两种情况：
     * a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点（做线索使得稍后可以重新返回父结点）。然后将当前节点更新为当前节点的左孩子。
     * b) 如果前驱节点的右孩子为当前节点，表明左子树已经访问完，可以访问当前节点。将它的右孩子重新设为空（恢复树的结构）。
     * 输出当前节点。当前节点更新为当前节点的右孩子。
     */
    public static List<Integer> inorderTraversalB(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null)
            return res;
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val); // visit
                cur = cur.right; // move to right
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;
                if (pre.right == null) { // connect with cur
                    pre.right = cur;
                    cur = cur.left; // traverse left subtree
                } else { // left subtree is done
                    pre.right = null;
                    res.add(cur.val); // visit
                    cur = cur.right; // move to right
                }
            }
        }
        return res;
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
