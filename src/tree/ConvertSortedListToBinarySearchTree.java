package tree;

import java.util.ArrayList;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees
 * of every node never differ by more than 1.
 * Given the sorted linked list: [-10,-3,0,5,9],
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 * Tags: DFS, Linked list
 * 使用快慢指针来找中点
 * 另外一种o(N)的算法 在右边的链接里 http://bangbingsyb.blogspot.com/2014/11/leetcode-convert-sorted-list-to-binary.html
 */
class ConvertSortedListToBinarySearchTree {
    public static void main(String[] args) {

    }

    public TreeNode convertSortedListToBinarySearchTree(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode slow = head, pre = null, fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null; //cut left sub list here
        TreeNode n = new TreeNode(slow.val);
        n.left = convertSortedListToBinarySearchTree(head);
        n.right = convertSortedListToBinarySearchTree(slow.next);
        return n;
    }

    /**
     * 这里的问题是对于一个链表我们是不能常量时间访问它的中间元素的。这时候就要利用到树的中序遍历了，
     * 按照递归中序遍历的顺序对链表结点一个个进行访问，而我们要构造的二分查找树正是按照链表的顺序来的。
     * 思路就是先对左子树进行递归，然后将当前结点作为根，迭代到下一个链表结点，最后在递归求出右子树即可。
     * 整体过程就是一次中序遍历，时间复杂度是O(n)，空间复杂度是栈空间O(logn)。
     */
    public TreeNode convertSortedListToBinarySearchTreeb(ListNode head) {
        if (head == null)
            return null;
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        ArrayList<ListNode> list = new ArrayList<>();
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
        list.set(0, list.get(0).next);   //?????
        root.right = helper(list, m + 1, r);
        return root;
    }

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}