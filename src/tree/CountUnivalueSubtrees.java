package tree;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given a binary tree, count the number of uni-value subtrees.
 * A Uni-value subtree means all nodes of the subtree have the same value.
 * For example:
 * Given binary tree,
 * 5
 * / \
 * 1   5
 * / \   \
 * 5   5   5
 * return 4.
 * <p/>
 * 只要叶子节点 就一定是一个univalue tree 所以使用后序遍历即可 如果自己的左右子树都是 那么自己的值又和左右子树都一样 那么就把自己也算上(记得考虑子树是none的情况)
 * 然后返回即可
 */
public class CountUnivalueSubtrees {
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
        System.out.println(new CountUnivalueSubtrees().countUnivalSubtreesa(root));
        System.out.println(new CountUnivalueSubtrees().countUnivalSubtreesa2(root));
    }

    int count = 0;

    public int countUnivalSubtreesa(TreeNode root) {
        all(root, 0);
        return count;
    }

    boolean all(TreeNode root, int val) {
        if (root == null)
            return true;
        if (!all(root.left, root.val) | !all(root.right, root.val))
            return false;
        count++;
        return root.val == val;
    }

    public static class TreeNode {
        Integer  val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int countUnivalSubtreesa2(TreeNode root) {
        int[] count = new int[1];
        helper(root, count);
        return count[0];
    }

    private boolean helper(TreeNode node, int[] count) {
        if (node == null) {
            return true;
        }
        boolean left = helper(node.left, count);
        boolean right = helper(node.right, count);
        if (left && right) {
            if (node.left != null && node.val != node.left.val) {
                return false;
            }
            if (node.right != null && node.val != node.right.val) {
                return false;
            }
            count[0]++;
            return true;
        }
        return false;
    }
}
