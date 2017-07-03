package aFB;

/**
 * Created by krystal on 5/4/17.
 */
public class DiameterofBinaryTree {
    //https://discuss.leetcode.com/topic/83456/java-solution-maxdepth
    int max = 0;

    public int diameterOfBinaryTree(LowestCommonAncestorofaBST.TreeNode root) {
        maxDepth(root);
        return max;
    }

    private int maxDepth(LowestCommonAncestorofaBST.TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }

}
