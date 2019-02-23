package amaoa;

import java.util.Arrays;

/**
 * Created by shanshan on 2/21/19.
 */
public class FindDistanceTwoGivenNode {
    public static void main(String[] args) {
        FindDistanceTwoGivenNode st = new FindDistanceTwoGivenNode();
        int[] test = {5, 6, 3, 1, 2, 4};
        System.out.println(st.findBST(test, 2, 4));
    }

    public int findBST(int[] nums, int n1, int n2) {
        Arrays.sort(nums);
        TreeNode root = buildBST(nums, 0, nums.length - 1);
        System.out.println(root.val);
        printTree(root);
        return findDistance(root, n1, n2);
    }

    private void printTree(TreeNode root) {
        if (root == null) {
            return;
        }
        printTree(root.left);
        System.out.println(root.val);
        printTree(root.right);
    }

    private TreeNode buildBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(nums[(start + end) / 2]);
        root.left = buildBST(nums, start, (start + end) / 2 - 1);
        root.right = buildBST(nums, (start + end) / 2 + 1, end);

        return root;
    }

    private int findDistance(TreeNode root, int n1, int n2) {
        // find lca and distance to root, formula: dist1 + dist2 - 2*(root, lca node)
        int x = findLevel(root, n1, 1);//exclude it self
        int y = findLevel(root, n2, 1);
        if (x == 0 || y == 0) {
            return -1;
        }
        System.out.println("x" + x + "y" + y);
        int lca = findLevel(root, findLCABST(root, n1, n2).val, 1);

        return (x + y) - 2 * lca;
    }

    private TreeNode findLCABST(TreeNode root, int n1, int n2) {
        if (root == null) {
            return null;
        }
        if (root.val > n1 && root.val > n2) {
            return findLCABST(root.left, n1, n2);
        }
        if (root.val < n1 && root.val < n2) {
            return findLCABST(root.right, n1, n2);
        }
        return root;
    }

    private TreeNode findLCA(TreeNode root, int n1, int n2) {
        if (root == null || root.val == n1 || root.val == n2) {
            return root;
        }
        TreeNode left = findLCA(root.left, n1, n2);
        TreeNode right = findLCA(root.right, n1, n2);
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }

    // this is a bst
    private int findLevel(TreeNode root, int n, int level) {
        if (root == null) {
            return 0;
        }
        if (root.val == n) {
            return level;
        }
        int downlevel = 0;
        if (root.val > n) {
            downlevel = findLevel(root.left, n, level + 1);
        } else {
            downlevel = findLevel(root.right, n, level + 1);
        }
        if (downlevel != 0) {
            return downlevel;
        } else {
            return 0;
        }
    }

    private int findPathLen(TreeNode root, int n) {
        if (root == null) {
            return 0;
        }
        if (root.val == n) {
            return 1;
        }
        if (root.left != null) {
            return (findPathLen(root.left, n) + 1);
        }
        if (root.right != null) {
            return (findPathLen(root.right, n) + 1);
        }
        return 0;
    }

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            left = right = null;
        }
    }
}
