package hard;

import java.util.ArrayList;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a
 * constant space solution?
 * Tags: Tree, DFS
 */

/**
 * 中序遍历二叉树 然后发现不按顺序的存起来 这里要注意 会有两种错误情况
 * 1,5,3,4,2 或者 2,1,3,4,5
 * 这道题还有o(1)的解法 需要用的moris 遍历  未实现
 */
class RecoverBST {
    public static void main(String[] args) {

    }

    TreeNode prev;
    TreeNode first;
    TreeNode second;

    /**
     * Do morris traversal to find those swapped nodes
     */
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        prev = new TreeNode(Integer.MIN_VALUE);
        morrisInorder(root);
        // swap values of first and second
        int t = first.val;
        first.val = second.val;
        second.val = t;
    }

    /**
     * Morris Traversal, link null left node to its inorder predecessor
     * link null right node to its inorder successor
     * Initialize current as root
     * While current is not NULL
     * If current does not have left child
     * a) Print current’s data
     * b) Go to the right, i.e., current = current->right
     * Else
     * a) Make current as right child of the rightmost node in current's left
     * subtree
     * b) Go to this left child, i.e., current = current->left
     */
    void morrisInorder(TreeNode root) {
        TreeNode cur = root;
        TreeNode pred = null;
        while (cur != null) {
            if (cur.left == null) {
                // set first and second if first still doesn't exist
                if (cur.val <= prev.val && first == null) first = prev;
                // set second only if first exists
                if (cur.val <= prev.val && first != null) second = cur;
                prev = cur; // note that previous node needs to be saved
                cur = cur.right; // move to next node
            } else {
                pred = cur.left;
                while (pred.right != null && pred.right != cur) pred = pred.right;
                if (pred.right == null) { // not connected
                    pred.right = cur; // connect predecessor to current node
                    cur = cur.left; // move to left child
                } else { // connected
                    if (cur.val <= prev.val && first == null) first = prev;
                    if (cur.val <= prev.val && first != null) second = cur;
                    pred.right = null; // break connection
                    prev = cur; // previous node needs to be saved
                    cur = cur.right; // move to right child
                }
            }
        }
    }
    /*这道题是要求恢复一颗有两个元素调换错了的二叉查找树。一开始拿到可能会觉得比较复杂，其实观察出规律了就比较简单。
    主要还是利用二叉查找树的主要性质，就是中序遍历是有序的性质。那么如果其中有元素被调换了，意味着中序遍历中必然出现违背有序的情况。
    那么会出现几次呢？有两种情况，如果是中序遍历相邻的两个元素被调换了，很容易想到就只需会出现一次违反情况，
    只需要把这个两个节点记录下来最后调换值就可以；如果是不相邻的两个元素被调换了，举个例子很容易可以发现，会发生两次逆序的情况，
    那么这时候需要调换的元素应该是第一次逆序前面的元素，和第二次逆序后面的元素。比如1234567,1和5调换了，
    会得到5234167，逆序发生在52和41，我们需要把4和1调过来，那么就是52的第一个元素，41的第二个元素调换即可。*/

    /**搞清楚了规律就容易实现了，中序遍历寻找逆序情况，调换的第一个元素，永远是第一个逆序的第一个元素，
     * 而调换的第二个元素如果只有一次逆序，则是那一次的后一个，如果有两次逆序则是第二次的后一个。
     * 算法只需要一次中序遍历，所以时间复杂度是O(n)，空间是栈大小O(logn)*/
    public void recoverTreeB(TreeNode root) {
        if (root == null)
            return;
        ArrayList<TreeNode> pre = new ArrayList<TreeNode>();
        pre.add(null);
        ArrayList<TreeNode> res = new ArrayList<TreeNode>();
        helper(root, pre, res);
        if (res.size() > 0) {
            int temp = res.get(0).val;
            res.get(0).val = res.get(1).val;
            res.get(1).val = temp;
        }
    }
    private void helper(TreeNode root, ArrayList<TreeNode> pre, ArrayList<TreeNode> res) {
        if (root == null) {
            return;
        }
        helper(root.left, pre, res);
        if (pre.get(0) != null && pre.get(0).val > root.val) {
            if (res.size() == 0) {
                res.add(pre.get(0));
                res.add(root);
            } else {
                res.set(1, root);
            }
        }
        pre.set(0, root);
        helper(root.right, pre, res);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
