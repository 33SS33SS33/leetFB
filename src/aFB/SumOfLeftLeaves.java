package aFB;

import java.util.Stack;

/**
 * Find sum of all left leaves in a given Binary Tree
 * Tags: Tree, DFS
 */
class SumOfLeftLeaves {
    public static void main(String[] args) {
        SumOfLeftLeaves s = new SumOfLeftLeaves();
        TreeNode root = s.buildTree();
        System.out.println(s.sumOfLeftLeavesa(root));
        System.out.println(s.sumOfLeftLeavesb(root));
        System.out.println(s.sumOfLeftLeaves(root));
    }

    //递归 最好的
    public int sumOfLeftLeavesa(TreeNode root) {
        if (root == null) return 0;
        int ans = 0;
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null)
                ans += root.left.val;
            else ans += sumOfLeftLeaves(root.left);
        }
        ans += sumOfLeftLeaves(root.right);
        return ans;
    }

    //迭代 最好的
    public int sumOfLeftLeavesb(TreeNode root) {
        if (root == null) return 0;
        int ans = 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                if (node.left.left == null && node.left.right == null)
                    ans += node.left.val;
                else
                    stack.push(node.left);
            }
            if (node.right != null) {
                if (node.right.left != null || node.right.right != null)
                    stack.push(node.right);
            }
        }
        return ans;
    }

    /**
     * DFS, recursive  不要的
     * Make sure current node is not null
     * Check whether left child is leaf node
     * If yes, add its value to result
     * If not, recurse on the left subtree
     * Recurse on the right subtree after that
     */
    public int sumOfLeftLeaves(TreeNode root) {
        int res = 0;
        if (root != null) {
            if (isLeaf(root.left)) {
                res += root.left.val;
            } else {
                res += sumOfLeftLeaves(root.left);
            }
            res += sumOfLeftLeaves(root.right);
        }
        return res;
    }

    private boolean isLeaf(TreeNode n) {
        if (n == null)
            return false;
        if (n.left == null && n.right == null)
            return true;
        return false;
    }

    private TreeNode buildTree() {
        TreeNode t0 = new TreeNode(20);
        TreeNode t1 = new TreeNode(9);
        TreeNode t2 = new TreeNode(49);
        TreeNode t3 = new TreeNode(5);
        TreeNode t4 = new TreeNode(12);
        TreeNode t5 = new TreeNode(15);
        TreeNode t6 = new TreeNode(23);
        TreeNode t7 = new TreeNode(52);
        TreeNode t8 = new TreeNode(50);

        t0.left = t1;
        t0.right = t2;
        t1.left = t3;
        t1.right = t4;
        t4.right = t5;
        t2.left = t6;
        t2.right = t7;
        t7.left = t8;

        return t0;
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
