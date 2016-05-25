package tree;

import java.util.*;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * For example,
 * Given
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * The flattened tree should look like:
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * Hints:
 * If you notice carefully in the flattened tree, each node's right child
 * points to the next node of a pre-order traversal.
 * Tags: Tree, DFS
 */

/**
 * 首先 发现flatten之后的树就是按着先序遍历之前的树
 * 设置一个head指针 他表示的是你当前右子树的末尾
 * 然后按着先序依次把节点挪过去
 * 此题关键在于
 * 首先 你把左边的节点挪过去了  但是你要清空此节点本身的左节点
 * 比如(2节点过去了  要把2的left清空掉 要不2的左右节点都有3)
 * 然后因为你是把左边的节点往右边挪 右边的树就被破坏了
 * 所以要在递归最开始  先用 left还有right变量保存原先左右节点的地址
 * <p/>
 * 第二次看了思路 重要
 * 第二次用了中序的思路 首先要缓存住右边的节点
 * 然后flaten左边的节点 通过循环找到尾部  每次都假设调用了一次flatten之后 左边的树都已经是flaten好的 所以只需要连接起来就行了
 * 但是别忘了 连接起来之后要去flaten右边的树
 */

/*递归 迭代*/
class FlatenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(6);
        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.right = n5;
        new FlatenBinaryTreeToLinkedList().flatten(root);
        System.out.println(levelOrder(root));
    }

    /**
     * Add root's right subtree to left node's rightmost child
     * Then set that subtree as root's right subtree
     * And set root's left child to null
     * Move root to its right child and repeat
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) { // check left child
                TreeNode n = root.left;
                while (n.right != null)
                    n = n.right; // rightmost child of left
                n.right = root.right; // insert right subtree to its right
                root.right = root.left; // set left subtree as right subtree
                root.left = null; // set left to null
            }
            root = root.right; // move to right child
        }
    }

    /**
     * creek------
     */
    public void flattenB(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        while (p != null || !stack.empty()) {
            if (p.right != null) {
                stack.push(p.right);
            }
            if (p.left != null) {
                p.right = p.left;
                p.left = null;
            } else if (!stack.empty()) {
                TreeNode temp = stack.pop();
                p.right = temp;
            }
            p = p.right;
        }
    }

    /**
     * Num 2
     */
    public ArrayList<TreeNode> flattenC(TreeNode root) {
        ArrayList<TreeNode> pre = new ArrayList<TreeNode>();
        pre.add(null);
        ArrayList<TreeNode> res = helper(root, pre);
        return res;
    }

    private ArrayList<TreeNode> helper(TreeNode root, ArrayList<TreeNode> pre) {
        if (root == null)
            return null;
        TreeNode right = root.right;
        if (pre.get(0) != null) {
            pre.get(0).left = null;
            pre.get(0).right = root;
        }
        pre.set(0, root);
        helper(root.left, pre);
        helper(right, pre);
        return pre;
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> curLevel = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = queue.poll();
                curLevel.add(n.val);
                if (n.left != null)
                    queue.add(n.left);
                if (n.right != null)
                    queue.add(n.right);
            }
            res.add(curLevel);
        }
        return res;
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