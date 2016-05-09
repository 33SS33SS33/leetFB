package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
/**Given a binary tree, return all root-to-leaf paths.
 For example, given the following binary tree:
    1
  /   \
 2     3
 \
  5
 All root-to-leaf paths are:
 ["1->2->5", "1->3"]*/

/**用DFS就行
 但是Path的操作一定要注意!!! 重要
 如果在函数体内操作path  那么会修改path的值 比如Path+= [1] 会影响后面的path的值
 在函数参数的地方操作 其实是生成了一个新的变量path传给了下一次递归的函数就不会有问题*/
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
