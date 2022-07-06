package aMaz;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 * Given a binary tree (not a binary searchinRotatedSortedArrayb tree) and two values say n1 and n2,
 * write a program to find the least common ancestor.
 * Allow a node to be a descendant of itself
 * Tags: Tree
 * 有递归和迭代两种解法
 * 递归比较好理解 首先检查当前节点是否为None或者p或者q 如果是直接返回
 * 然后递归分别访问当前节点的左右子树 如果左右子树分别找到了两个节点 那么就说明当前的节点就是他们的祖先
 * 如果只有一颗子树找到了节点 就先返回这个节点 然后上面那个子树再决定谁是祖先
 * 比如查找5和4的情况 当查找到3得左子树 然后碰见了5 直接返回 这时候3的右子树会返回None
 * 所以就可以知道4节点是在5节点下面的 直接返回5节点就行了  (4节点其实没有被查询)
 * 右边还有迭代的解法 未实现
 */
class LowestCommonAncestorofABinaryTree {
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
        System.out.println(new LowestCommonAncestorofABinaryTree().lowestCommonAncestorofABinaryTree(root, n1, n4).val);
    }

    public TreeNode lowestCommonAncestorofABinaryTree(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestorofABinaryTree(root.left, p, q);
        TreeNode right = lowestCommonAncestorofABinaryTree(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int v) {
            val = v;
        }
    }

}
