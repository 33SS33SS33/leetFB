package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
public class BinaryTreePaths {
    public static void main(String[] args) {
        TreeNode head = buildTree();
        System.out.println(new BinaryTreePaths().binaryTreePaths(head).toString());
    }
    //
    //    List<String> merge(int v, List<String> subPath){
    //        return subPath.stream()
    //                .map(p -> v + "->" + p)
    //                .collect(Collectors.toList());
    //    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> path = new ArrayList<String>();
        if (root == null)
            return path;

        if (root.left == null && root.right == null) {
            // leaf
            return Arrays.asList("" + root.val);
        }

        //        if(root.left != null){
        //            path.addAll(merge(root.val, binaryTreePaths(root.left)));
        //        }
        //
        //        if(root.right != null) {
        //            path.addAll(merge(root.val, binaryTreePaths(root.right)));
        //        }

        return path;
    }

    private static TreeNode buildTree() {
        TreeNode t0 = new TreeNode(20);
        TreeNode t1 = new TreeNode(9);
        TreeNode t2 = new TreeNode(49);

        TreeNode t3 = new TreeNode(5);
        TreeNode t4 = new TreeNode(12);
        TreeNode t5 = new TreeNode(15);

        TreeNode t6 = new TreeNode(23);
        TreeNode t7 = new TreeNode(52);
        TreeNode t8 = new TreeNode(50);

        t0.left = t1;
        t0.right = t2;
        t1.left = t3;
        t1.right = t4;
        t4.right = t5;
        t2.left = t6;
        t2.right = t7;
        t7.left = t8;

        return t0;
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
