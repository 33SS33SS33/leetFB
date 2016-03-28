package tree;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
public class CountCompleteTreeNodes {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(6);
        TreeNode n6 = new TreeNode(7);
        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;
        System.out.println(new CountCompleteTreeNodes().countNodes(root));
        System.out.println(new CountCompleteTreeNodes().countNodesB(root));
    }

    public int countNodes(TreeNode root) {
        int h = height(root);
        countLeaves(root, h);
        if (!stop) {
            // perfect tree
            return perfectTreeNodeCount(h);
        }
        return perfectTreeNodeCount(h - 1) + leaves;
    }

    int     leaves = 0;
    boolean stop   = false;
    void countLeaves(TreeNode root, int heightToLeaf) {
        if (root == null)
            return;
        if (stop)
            return;
        if (heightToLeaf == 2) {
            if (root.right != null) {
                leaves += 2;
            } else {
                // at lease one is null
                stop = true;
                if (root.left != null) {
                    leaves += 1;
                }
            }
            return;
        }
        countLeaves(root.left, heightToLeaf - 1);
        countLeaves(root.right, heightToLeaf - 1);
    }

    int perfectTreeNodeCount(int height) {
        if (height == 0)
            return 0;
        if (height == 1)
            return 1;
        return (int) Math.pow(2, height) - 1;
    }

    int height(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + height(root.left);
    }

    /**
     * creek-------
     * Steps to solve this problem:
     * 1) get the height of left-most part
     * 2) get the height of right-most part
     * 3) when they are equal, the # of nodes = 2^h -1
     * 4) when they are not equal, recursively get # of nodes from left&right sub-trees
     * Time complexity is O(h^2)
     */
    public int countNodesB(TreeNode root) {
        if (root == null)
            return 0;
        int left = getLeftHeight(root) + 1;
        int right = getRightHeight(root) + 1;
        if (left == right) {
            return (2 << (left - 1)) - 1;
        } else {
            return countNodesB(root.left) + countNodesB(root.right) + 1;
        }
    }
    public int getLeftHeight(TreeNode n) {
        if (n == null)
            return 0;
        int height = 0;
        while (n.left != null) {
            height++;
            n = n.left;
        }
        return height;
    }
    public int getRightHeight(TreeNode n) {
        if (n == null)
            return 0;
        int height = 0;
        while (n.right != null) {
            height++;
            n = n.right;
        }
        return height;
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
