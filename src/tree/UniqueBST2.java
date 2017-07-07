package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n, <strong>generate</strong> all structurally unique BST's (binary
 * search trees) that store values 1...n.
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * Tags: Tree, DP, Backtracking
 * 递归
 * 首先构造根节点  然后构造左子树 然后构造右子树 然后递归
 * 第二次看了思路 重要
 * https://leetcode.com/discuss/29532/dp-solution-in-python
 * 还有dp解法 未实现
 */
class UniqueBST2 {
    public static void main(String[] args) {

    }

    /**
     * 1..n is the in-order traversal for any BST with nodes 1 to n.
     * if pick i-th node as root
     * the left subtree will contain elements 1 to (i-1)
     * and the right subtree will contain elements (i+1) to n.
     * use recursive calls to get back all possible trees for left and right
     * subtrees and combine them in all possible ways with the root.
     */
    public List<TreeNode> generateTreesa(int n) {
        return genTrees2(1, n);
    }

    public List<TreeNode> genTrees2(int start, int end) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        if (start > end) {
            list.add(null);
            return list;
        }
        if (start == end) {
            list.add(new TreeNode(start));
            return list;
        }
        List<TreeNode> left, right;
        for (int i = start; i <= end; i++) {
            left = genTrees2(start, i - 1);
            right = genTrees2(i + 1, end);
            for (TreeNode lnode : left) {
                for (TreeNode rnode : right) {
                    /*there exists a combination for each tree*/
                    TreeNode root = new TreeNode(i);
                    root.left = lnode;
                    root.right = rnode;
                    list.add(root);
                }
            }
        }
        return list;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
