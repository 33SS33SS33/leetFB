package aMaz;

/**
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 * Tags: Linkedlist
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode head1 = buildList();
        ListNode head2 = buildList2();
        ListNode res = new MergeTwoSortedLists().mergeTwoSortedLists(head1, head2);

        while (res.next != null) {
            System.out.print(res.val + ",");
            res = res.next;
        }
    }

    /**
     * 递归  最好的
     * Recursive
     * the order of l1, l2 doesn't matter
     */
    public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        // next node should be the result of comparison
        if (l1.val < l2.val) {
            l1.next = mergeTwoSortedLists(l1.next, l2); // notice l1.next
            return l1;
        } else {
            l2.next = mergeTwoSortedLists(l1, l2.next); // notice l2.next
            return l2;
        }
    }

    /**
     * 迭代
     * iterasive
     * 算法时间复杂度是O(m+n),m和n分别是两条链表的长度，空间复杂度是O(1)
     */
    public ListNode mergeTwoSortedListsb(ListNode l1, ListNode l2) {
        ListNode helper = new ListNode(0);
        ListNode pre = helper;
        helper.next = l1;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                ListNode next = l2.next;
                l2.next = pre.next;
                pre.next = l2;
                l2 = next;
            } else {
                l1 = l1.next;
            }
            pre = pre.next;
        }
        if (l2 != null) {
            pre.next = l2;
        }
        return helper.next;
    }

    static ListNode buildList() {
        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(6);
        node0.next = node1;
        node1.next = node2;
        return node0;
    }

    static ListNode buildList2() {
        ListNode node0 = new ListNode(3);
        ListNode node1 = new ListNode(7);
        ListNode node2 = new ListNode(9);
        node0.next = node1;
        node1.next = node2;
        return node0;
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int value) {
            this.val = value;
        }
    }
}