package tree;

/**
 * Given a binary tree
 * struct TreeLinkNode {
     * TreeLinkNode *left;
     * TreeLinkNode *right;
     * TreeLinkNode *next;
 * }
 *
 * Populate each next pointer to point to its next right node. If there is no
 * next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 *
 * Note:
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the
 * same level, and every parent has two children).
 * For example,
 * Given the following perfect binary tree,
 * 1
 * /  \
 * 2    3
 * / \  / \
 * 4  5  6  7
 * After calling your function, the tree should look like:
 * 1 -> NULL
 * /  \
 * 2 -> 3 -> NULL
 * / \  / \
 * 4->5->6->7 -> NULL
 *
 * Tags: Tree, DFS
 */
/**完美二叉树*/

/**首先 设置一个pre来保存每一层最左边的节点
 然后再设置一个cur用来从左到右遍历这一层
 其实主要是用cur来从左到右把下一层的next连起来
 所以cur这一层应该是已经连好的
 然后连完一层 然后pre就到下一层 再用cur来遍历*/
class PopulatingNextRight {
    public static void main(String[] args) {
        TreeLinkNode root = buildTree();
        TreeLinkNode root2 = buildTree();
        PopulatingNextRight r = new PopulatingNextRight();
        r.connectA(root);
        r.connectB(root2);
    }

    /**
     * Iterative
     * Store node in previous line
     */
    public void connectA(TreeLinkNode root) {
        if (root == null)
            return;
        TreeLinkNode pre = root;
        TreeLinkNode cur = null;
        while (pre.left != null) { // no more level if left child is null
            cur = pre;
            while (cur != null) { // work on next level
                cur.left.next = cur.right; // connect left and right
                // connect right child with next node's left child
                if (cur.next != null)
                    cur.right.next = cur.next.left;
                cur = cur.next; // move current to next node
            }
            pre = pre.left; // move to next line
        }
    }

    /**
     * creek---
     */
    public void connectB(TreeLinkNode root) {
        if (root == null)
            return;
        TreeLinkNode lastHead = root;//prevous level's head
        TreeLinkNode lastCurrent = null;//previous level's pointer
        TreeLinkNode currentHead = null;//currnet level's head
        TreeLinkNode current = null;//current level's pointer
        while (lastHead != null) {
            lastCurrent = lastHead;
            while (lastCurrent != null) {
                if (currentHead == null) {
                    currentHead = lastCurrent.left;
                    current = lastCurrent.left;
                } else {
                    current.next = lastCurrent.left;
                    current = current.next;
                }
                if (currentHead != null) {
                    current.next = lastCurrent.right;
                    current = current.next;
                }
                lastCurrent = lastCurrent.next;
            }
            //update last head
            lastHead = currentHead;
            currentHead = null;
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
    static class TreeLinkNode {
        int          val;
        TreeLinkNode left;
        TreeLinkNode right;
        TreeLinkNode next;

        public TreeLinkNode(int val) {
            this.val = val;
        }
    }

}