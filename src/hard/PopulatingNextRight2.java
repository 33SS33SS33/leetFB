package hard;

/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * What if the given tree could be any binary tree? Would your previous
 * solution still work?
 * Note:
 * 
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \    \
 *     4-> 5 -> 7 -> NULL
 * 
 * Tags: Tree ,DFS
 */
/**普通二叉树*/
/*Most likely this can be implemented recursively, because you can identify the linking of nodes as sub-problems.
The main difficulty of this problem is linking rightChild with the nextSibling of rightChild.
Each node has no parent pointer. Therefore, there is no way linking the rightChild with its nextSibling at a level.*/
class PopulatingNextRight2 {
    public static void main(String[] args) {
        TreeLinkNode root= buildTree();
        PopulatingNextRight2 r=new PopulatingNextRight2();
        r.connect(root);
    }
    
    /**
     * Store the head of next level
     * Store previous node 
     * Do level order traversal with a pointer
     */
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode cur = root;  // current node of current level
        TreeLinkNode prev; // previous node
        TreeLinkNode nextHead; // nextHead of the next level
        while (cur != null) {
            nextHead = null;
            prev = null;
            while (cur != null) {
                if (cur.left != null) { // left child
                    if (prev != null) prev.next = cur.left;
                    else nextHead = cur.left; // set nextHead
                    prev = cur.left; // move right
                }
                if (cur.right != null) { // right child
                    if (prev != null) prev.next = cur.right;
                    else nextHead = cur.right; // set nextHead
                    prev = cur.right; // move right
                }
                cur = cur.next; // move right to next node in same level
            }
            // move to next level
            cur = nextHead;
        }
    }

    private static TreeLinkNode buildTree() {
        TreeLinkNode t0 = new TreeLinkNode(20);
        TreeLinkNode t1 = new TreeLinkNode(9);
        TreeLinkNode t2 = new TreeLinkNode(49);
        TreeLinkNode t3 = new TreeLinkNode(5);
        TreeLinkNode t4 = new TreeLinkNode(12);
        TreeLinkNode t5 = new TreeLinkNode(15);
        TreeLinkNode t6 = new TreeLinkNode(23);
        TreeLinkNode t7 = new TreeLinkNode(52);
        TreeLinkNode t8 = new TreeLinkNode(50);
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
    public static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }

}