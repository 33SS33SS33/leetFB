package aMaz;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
public class ClosestBinarySearchTreeValue {
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
        System.out.println(closestBinarySearchTreeValuea(root, 7));
        System.out.println(closestBinarySearchTreeValueb(root, 7));
    }

    /**
     * Given a non-empty binary search tree and a target value,
     * find the value in the BST that is closest to the target.
     * Note: Given target value is a floating point.
     * You are guaranteed to have only one unique value in the BST that is closest to the target.
     * Input: root = [4,2,5,1,3], target = 3.714286
     * 4
     * / \
     * 2   5
     * / \
     * 1   3  Output: 4
     * 就是小了就往右走 大了就往左走 每次都计算一下最小值即可
     */
    public static int closestBinarySearchTreeValueb(TreeNode root, double target) {
        int ret = root.val;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - ret)) {
                ret = root.val;
            }
            root = root.val > target ? root.left : root.right;
        }
        return ret;
    }

    public static int closestBinarySearchTreeValuea(TreeNode root, double target) {
        int a = root.val;
        TreeNode kid = target < a ? root.left : root.right;
        if (kid == null)
            return a;
        int b = closestBinarySearchTreeValuea(kid, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
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
