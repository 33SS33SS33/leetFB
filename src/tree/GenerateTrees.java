package tree;

import java.util.ArrayList;

/**
 * Created by GAOSHANSHAN835 on 2016/1/4.
 * 错的
 */
public class GenerateTrees {
    public static void main(String[] args) {
        ArrayList<TreeNode> res = new GenerateTrees().generateTrees(4);
        for (TreeNode r : res) {
            System.out.print(r.value);
        }
    }

    public ArrayList<TreeNode> generateTrees(int n) {
        return helper(1, n);
    }

    private ArrayList<TreeNode> helper(int left, int right) {
        ArrayList<TreeNode> res = new ArrayList<TreeNode>();
        if (left > right) {
            res.add(null);
            return res;
        }
        for (int i = left; i <= right; i++) {
            ArrayList<TreeNode> leftList = helper(left, i - 1);
            ArrayList<TreeNode> rightList = helper(i + 1, right);
            for (int j = 0; j < leftList.size(); j++) {
                for (int k = 0; k < rightList.size(); k++) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftList.get(j);
                    root.right = rightList.get(k);
                    res.add(root);
                }
            }
        }
        return res;
    }

    class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

}
