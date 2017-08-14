package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
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
 * return
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 * Tags: Tree, DFS
 * 先序遍历二叉树即可
 * 每次从一个节点到下一个节点的时候 就从sum把当前节点的val减掉
 * 然后判断一下到了叶子节点 如果sum等于当前节点的值 就把路径加进去
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
        System.out.println(new PathSum2().pathSuma(root, 7).toString());
        System.out.println(new PathSum2().pathSumB(root, 7).toString());
        System.out.println(new PathSum2().pathSumB(root, 10).toString());
        System.out.println(new PathSum2().pathSumB(root, 4).toString());
    }

    public List<List<Integer>> pathSuma(TreeNode root, int sum) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        List<Integer> currentResult = new LinkedList<Integer>();
        pathSuma(root, sum, currentResult, result);
        return result;
    }

    public void pathSuma(TreeNode root, int sum, List<Integer> currentResult,
                         List<List<Integer>> result) {
        if (root == null)
            return;
        currentResult.add(new Integer(root.val));
        if (root.left == null && root.right == null && sum == root.val) {
            result.add(new LinkedList(currentResult));
            currentResult.remove(currentResult.size() - 1);//don't forget to remove the last integer
            return;
        } else {
            pathSuma(root.left, sum - root.val, currentResult, result);
            pathSuma(root.right, sum - root.val, currentResult, result);
        }
        currentResult.remove(currentResult.size() - 1);
    }

    /**
     * creek
     * 这里的时间复杂度仍然只是一次遍历O(n)，而空间复杂度则取决于满足条件的路径和的数量（假设是k条），则空间是O(klogn)。
     * http://blog.csdn.net/linhuanmars/article/details/23655413
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

    public void dfs(TreeNode t, int sum, ArrayList<ArrayList<Integer>> result,
                    ArrayList<Integer> l) {
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
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}