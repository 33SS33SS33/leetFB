package tree;

import java.util.Stack;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
public class KthSmallestElementinaBST {
/**错的*/
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
        System.out.println(new KthSmallestElementinaBST().kthSmallest(root,3));
        System.out.println(new KthSmallestElementinaBST().kthSmallestA(root,3));
    }
    boolean reachLeftMost = false;
    boolean stop = false;
    int kth = 0;
    int k = 0;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        search(root);

        return kth;
    }
    void search(TreeNode root) {
        if(stop){
            return;
        }
        // visit
        if(root == null){
            reachLeftMost = true;
            return;
        }
        search(root.left);
        if(reachLeftMost) {
            k--;
        }
        if(k == 0){
            kth = root.val;
            stop = true;
            return;
        }
        search(root.right);
    }

    /**creek  -----We can inorder traverse the tree and get the kth smallest element. Time is O(n).*/
    public int kthSmallestA(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();

        TreeNode p = root;
        int result = 0;
        while(!stack.isEmpty() || p!=null){
            if(p!=null){
                stack.push(p);
                p = p.left;
            }else{
                TreeNode t = stack.pop();
                k--;
                if(k==0)
                    result = t.val;
                p = t.right;
            }
        }
        return result;
    }

    /**creek------We can let each node track the order, i.e.,
     * the number of elements that are less than itself. Time is O(log(n)).*/


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
