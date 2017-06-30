package tree;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 * Given a Binary Tree, find the deepest leaf node that is left child of its parent.
 * Tags: Tree, DFS, Backtracking
 */
class DeepestLeftLeafNode {
    public static void main(String[] args) {
        Node root = buildTree();

        /**错的？？*/
        System.out.println(new DeepestLeftLeafNode().deepestLeftLeaf(root));
    }

    public Node deepestLeftLeaf(Node root) {
        Node res = null;
        deepestLeftLeaf(root, 0, 0, false, res);
        return res;
    }

    /**
     * Backtracking
     * If is left child, is leaf node, and level > maxLevel
     * Update result and maxLevel, then return
     */
    public void deepestLeftLeaf(Node root, int level, int maxLevel, boolean isLeft, Node res) {
        if (root == null)
            return;
        if (isLeft && root.left == null && root.right == null && level > maxLevel) {
            res = root;
            maxLevel = level;
            return;
        }
        deepestLeftLeaf(root.left, level + 1, maxLevel, true, res);
        deepestLeftLeaf(root.right, level + 1, maxLevel, false, res);
    }

    private static Node buildTree() {
        Node t0 = new Node(20);
        Node t1 = new Node(9);
        Node t2 = new Node(49);
        Node t3 = new Node(5);
        Node t4 = new Node(12);
        Node t5 = new Node(15);
        Node t6 = new Node(23);
        Node t7 = new Node(52);
        Node t8 = new Node(50);

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

    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

}
