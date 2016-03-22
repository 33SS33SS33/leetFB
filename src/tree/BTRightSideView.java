package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
public class BTRightSideView {
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
        System.out.println(new BTRightSideView().rightSideView(root));
        System.out.println(new BTRightSideView().rightSideViewB(root));
    }

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null)
            return new ArrayList<Integer>();
        List<Integer> rt = new ArrayList<Integer>();
        rt.add(root.val);
        List<Integer> left = rightSideView(root.left);
        List<Integer> right = rightSideView(root.right);
        rt.addAll(right);
        if (left.size() > right.size()) {
            rt.addAll(left.subList(right.size(), left.size()));
        }
        return rt;
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

    static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
