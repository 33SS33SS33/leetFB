package aTop300microsoft;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given a binary search tree and a node in it, find the in-order inorderSuccessorinBST of that node in the BST.
 * You will have direct access to the node but not to the root of the tree. Each node will have a reference to its parent node. Below is the definition for Node:
 * class Node {
 * public int val;
 * public Node left;
 * public Node right;
 * public Node parent;
 * }
 * 只有往左走的时候 也就是p的值比root得小的时候 就尝试去找个更小的 需要记录succ就可以
 */
public class InorderSuccessorinBSTII {
    public Node inorderSuccessor(Node x) {
        // the successor is somewhere lower in the right subtree
        if (x.right != null) {
            x = x.right;
            while (x.left != null) x = x.left;
            return x;
        }

        // the successor is somewhere upper in the tree
        while (x.parent != null && x == x.parent.right) x = x.parent;
        return x.parent;
    }



    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
}
