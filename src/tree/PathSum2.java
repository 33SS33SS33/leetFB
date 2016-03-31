package tree;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's
 * sum equals the given sum.
 * For example:
 * Given the below binary tree and sum = 22,
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 *
 * return
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 * Tags: Tree, DFS
 */
class PathSum2 {
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
        System.out.println(new PathSum2().pathSumA(root, 7).toString());
        System.out.println(new PathSum2().pathSumB(root, 7).toString());
        System.out.println(new PathSum2().pathSumA(root, 10).toString());
        System.out.println(new PathSum2().pathSumB(root, 10).toString());
        System.out.println(new PathSum2().pathSumA(root, 4).toString());
        System.out.println(new PathSum2().pathSumB(root, 4).toString());
    }

    public List<List<Integer>> pathSumA(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null)
            return res;
        pathSum(root, sum, new ArrayList<Integer>(), res);
        return res;
    }

    /**
     * DFS or backtracking
     * Note that we can't pass path directly
     * Dereference before recursing
     */
    public void pathSum(TreeNode root, int sum, List<Integer> path, List<List<Integer>> res) {
        if (root == null)
            return; // return if current node is null
        sum -= root.val; // update sum
        if (root.left == null && root.right == null && sum == 0) {
            path.add(root.val);
            res.add(new ArrayList<Integer>(path)); // add difference path
            path.remove(path.size() - 1); // reset path here!
            return;
        }
        path.add(root.val); // add value to current path
        pathSum(root.left, sum, path, res);
        pathSum(root.right, sum, path, res);
        path.remove(path.size() - 1);
    }

    /**
     * creek-----
     * 这里的时间复杂度仍然只是一次遍历O(n)，而空间复杂度则取决于满足条件的路径和的数量（假设是k条），则空间是O(klogn)。
     */
    public List<ArrayList<Integer>> pathSumB(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null)
            return result;
        ArrayList<Integer> l = new ArrayList<Integer>();
        l.add(root.val);
        dfs(root, sum - root.val, result, l);
        return result;
    }
    public void dfs(TreeNode t, int sum, ArrayList<ArrayList<Integer>> result,ArrayList<Integer> l) {
        if (t.left == null && t.right == null && sum == 0) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.addAll(l);
            result.add(temp);
        }
        if (t.left != null) { //search path of left node
            l.add(t.left.val);
            dfs(t.left, sum - t.left.val, result, l);
            l.remove(l.size() - 1);
        }
        if (t.right != null) {//search path of right node
            l.add(t.right.val);
            dfs(t.right, sum - t.right.val, result, l);
            l.remove(l.size() - 1);
        }
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