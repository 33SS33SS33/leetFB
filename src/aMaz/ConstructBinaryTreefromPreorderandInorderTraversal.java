package aMaz;

import java.util.*;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree
 * Note:
 * You may assume that duplicates do not wordSearchb in the tree.
 * Tags: Tree, Array, DFS
 */
class ConstructBinaryTreefromPreorderandInorderTraversal {
    public static void main(String[] args) {
        int[] preorder = {1, 2, 4, 5, 3, 6};
        int[] inorder = {4, 2, 5, 1, 3, 6};
        TreeNode root2 = new ConstructBinaryTreefromPreorderandInorderTraversal().constructBinaryTreefromPreorderandInorderTraversal(preorder, inorder);
//        TreeNode root = new ConstructBinaryTreefromPreorderandInorderTraversal().buildTreeA(preorder, inorder);
    }

    int preorderIndex;
    Map<Integer, Integer> inorderIndexMap;

    public TreeNode constructBinaryTreefromPreorderandInorderTraversal(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        // build a hashmap to store value -> its index relations
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return arrayToTree(preorder, 0, preorder.length - 1);
    }

    private TreeNode arrayToTree(int[] preorder, int left, int right) {
        // if there are no elements to construct the tree
        if (left > right) return null;

        // select the preorder_index element as the root and increment it
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // build left and right subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
        root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right);
        return root;
    }

    /**
     * 对于寻找根所对应的下标，我们可以先建立一个HashMap，以免后面需要进行线行搜索，
     * 这样每次递归中就只需要常量操作就可以完成对根的确定和左右子树的分割。
     * 算法最终相当于一次树的遍历，每个结点只会被访问一次，所以时间复杂度是O(n)。
     * 而空间我们需要建立一个map来存储元素到下标的映射，所以是O(n)。
     */

/*    public TreeNode constructBinaryTreefromPreorderandInorderTraversal(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null)
            return null;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode helper(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR,
                            HashMap<Integer, Integer> map) {
        if (preL > preR || inL > inR)
            return null;
        TreeNode root = new TreeNode(preorder[preL]);
        int index = map.get(root.val);
        root.left = helper(preorder, preL + 1, index - inL + preL, inorder, inL, index - 1, map);
        root.right = helper(preorder, preL + index - inL + 1, preR, inorder, index + 1, inR, map);
        return root;
    }*/

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
