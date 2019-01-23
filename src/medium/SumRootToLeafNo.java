package medium;

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
 * 用先序遍历 判断到了叶子节点就把当前path的值加起来放进res即可
 */
class SumRootToLeafNo {
    public static void main(String[] args) {
        TreeNode head = buildTree();
        System.out.println(new SumRootToLeafNo().sumNumbers(head));
    }

    /**
     * 这道题思路还是比较明确的，目标是把从根到叶子节点的所有路径得到的整数都累加起来，
     * 递归条件即是把当前的sum乘以10并且加上当前节点传入下一函数，进行递归，最终把左右子树的总和相加。
     * 结束条件的话就是如果一个节点是叶子，那么我们应该累加到结果总和中，
     * 如果节点到了空节点，则不是叶子节点，不需要加入到结果中，直接返回0即可。
     * 算法的本质是一次先序遍历，所以时间是O(n)，空间是栈大小，O(logn)。
     * http://blog.csdn.net/linhuanmars/article/details/22913699
     */
    public int sumNumbers(TreeNode root) {
        return helper2(root, 0);
    }

    private int helper2(TreeNode root, int sum) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return sum * 10 + root.val;
        return helper2(root.left, sum * 10 + root.val) + helper2(root.right, sum * 10 + root.val);
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
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
