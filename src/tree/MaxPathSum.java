package tree;

/**
 * Created by GAOSHANSHAN835 on 2015/12/28.
 */

/**树的路径的最大值，不一定从根开始*/
public class MaxPathSum {
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
        System.out.println(new MaxPathSum().maxPathSum(root));
        System.out.println(new MaxPathSum().maxPathSumB(root));
    }

    private int maxSum;
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        findMax(root);
        return maxSum;
    }
    private int findMax(TreeNode p) {
        if (p == null) return 0;
        int left = findMax(p.left);
        int right = findMax(p.right);
        maxSum = Math.max(p.val + left + right, maxSum);
        int ret = p.val + Math.max(left, right);
        return ret > 0 ? ret : 0;
    }


    /**creek-----*/
    public int maxPathSumB(TreeNode root) {
        int max[] = new int[1];
        max[0] = Integer.MIN_VALUE;
        calculateSum(root, max);
        return max[0];
    }

    public int calculateSum(TreeNode root, int[] max) {
        if (root == null)
            return 0;
        int left = calculateSum(root.left, max);
        int right = calculateSum(root.right, max);
        int current = Math.max(root.val, Math.max(root.val + left, root.val + right));
        max[0] = Math.max(max[0], Math.max(current, left + root.val + right));
        return current;
    }

    static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
