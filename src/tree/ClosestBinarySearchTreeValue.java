package tree;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * ""Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 * Note:
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target."
 */

/**
 * 就是小了就往右走 大了就往左走
 * 每次都计算一下最小值即可
 * "
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
        System.out.println(new ClosestBinarySearchTreeValue().closestValuea(root, 7));
        System.out.println(new ClosestBinarySearchTreeValue().closestValue(root, 7));
    }

    //最好的
    public int closestValuea(TreeNode root, double target) {
        int a = root.val;
        TreeNode kid = target < a ? root.left : root.right;
        if (kid == null) return a;
        int b = closestValuea(kid, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }

    public int closestValuea2(TreeNode root, double target) {
        int ret = root.val;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - ret)) {
                ret = root.val;
            }
            root = root.val > target ? root.left : root.right;
        }
        return ret;
    }

    public int closestValue(TreeNode root, double target) {
        int closest = root.val;
        if (root.left != null) {
            closest = closestValue(closestValue(root.left, target), closest, target);
        }
        if (root.right != null) {
            closest = closestValue(closestValue(root.right, target), closest, target);
        }
        return closest;
    }

    int closestValue(int v1, int v2, double target) {
        double _v1 = Math.abs(target - v1);
        double _v2 = Math.abs(target - v2);
        if (_v1 < _v2)
            return v1;
        else
            return v2;
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
