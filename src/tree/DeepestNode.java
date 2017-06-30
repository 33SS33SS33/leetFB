package tree;

/**
 * Returns the deepest node in a binary tree. If the tree is complete, having
 * two same depth of node, return the rightmost node.
 * Tags: Tree, DFS, Backtracking
 */
class DeepestNode {
    public static void main(String[] args) {
        Node root = buildTree();
        /**错的？？*/
        System.out.println(new DeepestNode().deepestNode(root));
    }

    public Node deepestNode(Node root) {
        Node res = null;
        findDeepest(root, res, 0, 0);
        return res;
    }

    /**
     * Backtracking
     * If level > max, means a deeper node, update result and max level
     * Find more possibility in left and right subtrees
     */
    private void findDeepest(Node root, Node res, int level, int max) {
        if (root == null)
            return;
        if (level > max) {
            res = root;
            max = level;
            return;
        }
        findDeepest(root.left, res, level + 1, max);
        findDeepest(root.right, res, level + 1, max);
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
