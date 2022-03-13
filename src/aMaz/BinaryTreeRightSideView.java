package aMaz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * 用BFS即可
 * BFS一般要用到双端队列 而且要用两个循环 大循环是遍历整个树或图(如果下一层所有节点是空 循环结束)
 * 小循环是访问当前这一层的所有节点 然后压人下一层所有的节点
 * BFS可以一个用while 一个用for 重要
 */
public class BinaryTreeRightSideView {
    public static void main(String[] args) {
        TreeNode root = buildTree();
//        System.out.println(new BinaryTreeRightSideView().binaryTreeRightSideViewb(root));
        System.out.println(new BinaryTreeRightSideView().binaryTreeRightSideView(root));
        System.out.println(new BinaryTreeRightSideView().binaryTreeRightSideViewc(root));
    }

    /**
     * Given a binary tree, imagine yourself standing on the right side of it,
     * return the values of the nodes you can see ordered from top to bottom.
     * Input: [1,2,3,null,5,null,4] Output: [1, 3, 4]
     * Explanation:
     *   1            <---
     * /   \
     * 2     3         <---
     * \     \
     * 5     4       <---
     */
    public List<Integer> binaryTreeRightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightView(root, result, 0);
        return result;
    }

    public void rightView(TreeNode curr, List<Integer> result, int currDepth) {
        if (curr == null) {
            return;
        }
        if (currDepth == result.size()) {
            result.add(curr.val);
        }
        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
    }

    public List<Integer> binaryTreeRightSideViewc(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if(root==null)   return ans;
        Deque<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i<size; i++){
                TreeNode node = q.poll();
                if(i==size-1){
                    ans.add(node.val);
                }
                if(node.left!=null) q.add(node.left);
                if(node.right!=null) q.add(node.right);
            }
        }
        return ans;
    }

    public List<Integer> binaryTreeRightSideViewd(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (queue.size() > 0) {
            //get size here
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode top = queue.remove();
                //the first element in the queue (right-most of the tree)
                if (i == 0) {
                    result.add(top.val);
                }
                //add right first
                if (top.right != null) {
                    queue.add(top.right);
                }
                //add left
                if (top.left != null) {
                    queue.add(top.left);
                }
            }
        }
        return result;
    }

    /**
     * 看懂了
     */
/*    public List<Integer> binaryTreeRightSideViewb(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        List<Integer> rt = new ArrayList<>();
        rt.add(root.val);
        List<Integer> left = binaryTreeRightSideViewb(root.left);
        List<Integer> right = binaryTreeRightSideViewb(root.right);
        rt.addAll(right);
        if (left.size() > right.size()) {
            rt.addAll(left.subList(right.size(), left.size()));
        }
        return rt;
    }*/
    static TreeNode buildTree() {
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
        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
