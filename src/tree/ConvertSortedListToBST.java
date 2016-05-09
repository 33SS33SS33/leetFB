package tree;

import java.util.ArrayList;

/**
 * Given a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 * Tags: DFS, Linked list
 */

/**使用快慢指针来找中点
 另外一种o(N)的算法 在右边的链接里 http://bangbingsyb.blogspot.com/2014/11/leetcode-convert-sorted-list-to-binary.html*/
class ConvertSortedListToBST {
    public static void main(String[] args) {

    }

    private static ListNode cur;
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        cur = head;
        return buildTree(lengthOfList(head));
        /**Solution 2*/
//        return sortedListToBSTB(0, lengthOfList(head) - 1);
    }
    public static int lengthOfList(ListNode node) {
        int length = 0;
        while (node != null) {
            node = node.next;
            length++;
        }
        return length;
    }
    /**
     * Bottom up approach, O(n) (Instead of top-down, O(nlogn))
     * STEP 1: Take left n/2 nodes and recursively construct the left sub tree.
     * STEP 2: After left sub tree is constructed, we allocate memory for root
     * and link the left sub tree with root.
     * STEP 3: Finally, we recursively construct the right sub tree and link it
     * with root.
     */
    public TreeNode buildTree(int n) {
        if (n == 0)
            return null;
        TreeNode node = new TreeNode(0);
        node.left = buildTree(n / 2);
        node.val = cur.val;
        cur = cur.next;
        node.right = buildTree(n - n / 2
                - 1); // why n - n/2 - 1? The # of nodes in right subtreeis total nodes - nodes in left subtree - root
        return node;
    }


    // build tree bottom-up
    public TreeNode sortedListToBSTB(int start, int end) {
        if (start > end)
            return null;
        // mid
        int mid = (start + end) / 2;
        TreeNode left = sortedListToBSTB(start, mid - 1);
        TreeNode root = new TreeNode(cur.val);
        cur = cur.next;
        TreeNode right = sortedListToBSTB(mid + 1, end);
        root.left = left;
        root.right = right;
        return root;
    }

    /*这里的问题是对于一个链表我们是不能常量时间访问它的中间元素的。这时候就要利用到树的中序遍历了，
    按照递归中序遍历的顺序对链表结点一个个进行访问，而我们要构造的二分查找树正是按照链表的顺序来的。
    思路就是先对左子树进行递归，然后将当前结点作为根，迭代到下一个链表结点，最后在递归求出右子树即可。
    整体过程就是一次中序遍历，时间复杂度是O(n)，空间复杂度是栈空间O(logn)。*/
    public TreeNode sortedListToBSTC(ListNode head) {
        if (head == null)
            return null;
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        ArrayList<ListNode> list = new ArrayList<ListNode>();
        list.add(head);
        return helper(list, 0, count - 1);
    }
    private TreeNode helper(ArrayList<ListNode> list, int l, int r) {
        if (l > r)
            return null;
        int m = (l + r) / 2;
        TreeNode left = helper(list, l, m - 1);
        TreeNode root = new TreeNode(list.get(0).val);
        root.left = left;
        list.set(0, list.get(0).next);
        root.right = helper(list, m + 1, r);
        return root;
    }

    class ListNode {
        int      val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}