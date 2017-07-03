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
        ListNode res = new MergeTwoSortedLists().mergeTwoListsRec(head1, head2);

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
    public ListNode mergeTwoListsRec(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        // next node should be the result of comparison
        if (l1.val < l2.val) {
            l1.next = mergeTwoListsRec(l1.next, l2); // notice l1.next
            return l1;
        } else {
            l2.next = mergeTwoListsRec(l1, l2.next); // notice l2.next
            return l2;
        }
    }

    /**
     * 迭代
     * iterasive
     */
    public ListNode mergeTwoListsB(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode fakeHead = new ListNode(0);
        ListNode p = fakeHead;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        if (p1 != null)
            p.next = p1;
        if (p2 != null)
            p.next = p2;
        return fakeHead.next;
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