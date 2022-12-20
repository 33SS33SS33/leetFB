package TopInterview;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Populate each next pointer to point to its next right node. If there is no
 * next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
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
 * Tags: Tree, DFS
 * 完美二叉树
 * 首先 设置一个pre来保存每一层最左边的节点
 * 然后再设置一个cur用来从左到右遍历这一层
 * 其实主要是用cur来从左到右把下一层的next连起来
 * 所以cur这一层应该是已经连好的
 * 然后连完一层 然后pre就到下一层 再用cur来遍历
 */
class PopulatingNextRightPointersinEachNode {
    public static void main(String[] args) {
        PopulatingNextRightPointersinEachNode r = new PopulatingNextRightPointersinEachNode();
    }

    public Node PopulatingNextRightPointersinEachNode(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> Q = new LinkedList<Node>();
        Q.add(root);
        while (Q.size() > 0) {
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                Node node = Q.poll();
                // This check is important. We don't want to
                // establish any wrong connections. The queue will
                // contain nodes from 2 levels at most at any
                // point in time. This check ensures we only
                // don't establish next pointers beyond the end
                // of a level
                if (i < size - 1) {
                    node.next = Q.peek();
                }
                if (node.left != null) {
                    Q.add(node.left);
                }
                if (node.right != null) {
                    Q.add(node.right);
                }
            }
        }
        // Since the tree has now been modified, return the root node
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}