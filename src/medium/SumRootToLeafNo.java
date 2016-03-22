package medium;

import java.util.ArrayList;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path
 * could represent a number.
 * <p/>
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * <p/>
 * Find the total sum of all root-to-leaf numbers.
 * <p/>
 * For example,
 * 1
 * / \
 * 2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Return the sum = 12 + 13 = 25.
 * <p/>
 * Tags: Tree, DFS
 */
class SumRootToLeafNo {
    public static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode head = buildTree();
        System.out.println(new SumRootToLeafNo().sumNumbers(head));
        System.out.println(new SumRootToLeafNo().sumNumbersA(head));
        System.out.println(new SumRootToLeafNo().sumNumbersB(head));
    }

    public static int sumNumbers(TreeNode root) {
        int res = 0;
        if (root == null)
            return res;
        return helper(root, 0);
    }

    /**
     * Recursive, DFS
     * Build a helper function to pass cur result
     * If its leaf node, just return the val
     * Otherwise, goes to left root first then right root with current value
     */
    public static int helper(TreeNode root, int x) {
        if (root.right == null && root.left == null)
            return 10 * x + root.val;

        int val = 0;
        if (root.left != null)
            val += helper(root.left, 10 * x + root.val);
        if (root.right != null)
            val += helper(root.right, 10 * x + root.val);
        return val;
    }

    /**
     * creek naive--- be solved by a typical DFS approach-
     */
    public int sumNumbersA(TreeNode root) {
        int result = 0;
        if (root == null)
            return result;

        ArrayList<ArrayList<TreeNode>> all = new ArrayList<ArrayList<TreeNode>>();
        ArrayList<TreeNode> l = new ArrayList<TreeNode>();
        l.add(root);
        dfs(root, l, all);
        for (ArrayList<TreeNode> a : all) {
            StringBuilder sb = new StringBuilder();
            for (TreeNode n : a) {
                sb.append(String.valueOf(n.val));
            }
            int currValue = Integer.valueOf(sb.toString());
            result = result + currValue;
        }
        return result;
    }

    public void dfs(TreeNode n, ArrayList<TreeNode> l, ArrayList<ArrayList<TreeNode>> all) {
        if (n.left == null && n.right == null) {
            ArrayList<TreeNode> t = new ArrayList<TreeNode>();
            t.addAll(l);
            all.add(t);
        }
        if (n.left != null) {
            l.add(n.left);
            dfs(n.left, l, all);
            l.remove(l.size() - 1);
        }
        if (n.right != null) {
            l.add(n.right);
            dfs(n.right, l, all);
            l.remove(l.size() - 1);
        }
    }

    /**
     * creek---better  Same approach, but simpler coding style.
     */
    public int sumNumbersB(TreeNode root) {
        if (root == null)
            return 0;
        return dfs(root, 0, 0);
    }

    public int dfs(TreeNode node, int num, int sum) {
        if (node == null)
            return sum;
        num = num * 10 + node.val;
        // leaf
        if (node.left == null && node.right == null) {
            sum += num;
            return sum;
        }
        // left subtree + right subtree
        sum = dfs(node.left, num, sum) + dfs(node.right, num, sum);
        return sum;
    }

    private static TreeNode buildTree() {
        TreeNode t0 = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);

        t0.left = t1;
        t0.right = t2;

        return t0;
    }
}
