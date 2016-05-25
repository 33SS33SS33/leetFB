package medium;

import java.util.ArrayList;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path
 * could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * For example,
 * 1
 * / \
 * 2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Return the sum = 12 + 13 = 25.
 * Tags: Tree, DFS
 */

/**
 * 用先序遍历 判断到了叶子节点就把当前path的值加起来放进res即可
 */
class SumRootToLeafNo {
    public static void main(String[] args) {
        TreeNode head = buildTree();
        System.out.println(new SumRootToLeafNo().sumNumbersC1(head));
        System.out.println(new SumRootToLeafNo().sumNumbersC2(head));
        System.out.println(new SumRootToLeafNo().sumNumbersA(head));
        System.out.println(new SumRootToLeafNo().sumNumbersB(head));
    }

    public static int sumNumbersC1(TreeNode root) {
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
     * 这道题思路还是比较明确的，目标是把从根到叶子节点的所有路径得到的整数都累加起来，
     * 递归条件即是把当前的sum乘以10并且加上当前节点传入下一函数，进行递归，最终把左右子树的总和相加。
     * 结束条件的话就是如果一个节点是叶子，那么我们应该累加到结果总和中，
     * 如果节点到了空节点，则不是叶子节点，不需要加入到结果中，直接返回0即可。
     * 算法的本质是一次先序遍历，所以时间是O(n)，空间是栈大小，O(logn)。
     */
    public int sumNumbersC2(TreeNode root) {
        return helper2(root, 0);
    }

    private int helper2(TreeNode root, int sum) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return sum * 10 + root.val;
        return helper(root.left, sum * 10 + root.val) + helper(root.right, sum * 10 + root.val);
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

    public static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
