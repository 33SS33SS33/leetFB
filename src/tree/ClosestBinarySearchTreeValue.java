package tree;

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
        System.out.println(new ClosestBinarySearchTreeValue().closestValue(root, 7));
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
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
