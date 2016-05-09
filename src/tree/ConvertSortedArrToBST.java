package tree;

/**
 * Given an array where elements are sorted in ascending order, convert it to a
 * <strong>height balanced</strong> BST.
 * Tags: Tree, DFS
 */

/**将数组逐次二分 midpoint就是当前的根 然后左边的数组就是左子树 右边的数组就是右子树*/
class ConvertSortedArrToBST {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        TreeNode root = sortedArrayToBST(arr);
        System.out.println(root.val);
    }

    /*如果要构造这棵树，那就是把中间元素转化为根，然后递归构造左右子树。所以我们还是用二叉树递归的方法来实现，以根作为返回值，
    每层递归函数取中间元素，作为当前根和赋上结点值，然后左右结点接上左右区间的递归函数返回值。
    时间复杂度还是一次树遍历O(n)，总的空间复杂度是栈空间O(logn)加上结果的空间O(n)，额外空间是O(logn)，总体是O(n)*/
    public static TreeNode sortedArrayToBST(int[] num) {
        if (num == null || num.length == 0)
            return null;
        return helper(num, 0, num.length - 1);
    }
    /**
     * Recursive, DFS
     * Divide into left subtree and right subtree with indices range
     * Choose mid point as the root of subtree
     */
    public static TreeNode helper(int[] num, int left, int right) {
        if (left > right)
            return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = helper(num, left, mid - 1); // left and mid -1 
        root.right = helper(num, mid + 1, right); // mid + 1 and right
        return root;
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
