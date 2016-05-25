package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */

/**
 * 用BFS即可
 * BFS一般要用到双端队列 而且要用两个循环 大循环是遍历整个树或图(如果下一层所有节点是空 循环结束) 小循环是访问当前这一层的所有节点 然后压人下一层所有的节点
 * BFS可以一个用while 一个用for 重要
 */
public class BTRightSideView {
    public static void main(String[] args) {
        TreeNode root = buildTree();
        System.out.println(new BTRightSideView().rightSideViewA(root));
        System.out.println(new BTRightSideView().rightSideViewC(root));
        System.out.println(new BTRightSideView().rightSideViewB(root));
    }

    /**
     * 看懂了
     */
    public List<Integer> rightSideViewA(TreeNode root) {
        if (root == null)
            return new ArrayList<Integer>();
        List<Integer> rt = new ArrayList<Integer>();
        rt.add(root.val);
        List<Integer> left = rightSideViewA(root.left);
        List<Integer> right = rightSideViewA(root.right);
        rt.addAll(right);
        if (left.size() > right.size()) {
            rt.addAll(left.subList(right.size(), left.size()));
        }
        return rt;
    }

    /**
     * elegant--
     */
    public List<Integer> rightSideViewC(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
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

    public List<Integer> rightSideViewB(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
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
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
