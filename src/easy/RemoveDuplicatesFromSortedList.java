package easy;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 * Tags: Linkedlist
 */

/**
 * 要么删掉重复的  要么往前移动
 */
class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedList r = new RemoveDuplicatesFromSortedList();
        ListNode head = buildList();
        System.out.print(r.deleteDuplicatesB(head));
    }

    public ListNode deleteDuplicatesa(ListNode head) {
        if (head == null || head.next == null)
            return head;
        head.next = deleteDuplicatesa(head.next);
        return head.val == head.next.val ? head.next : head;
    }

    /**
     * creek---good---
     */
    public ListNode deleteDuplicatesB(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode p = head;
        while (p != null && p.next != null) {
            if (p.val == p.next.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }

    /**
     * creek---
     */
    public ListNode deleteDuplicatesA(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode prev = head;
        ListNode p = head.next;
        while (p != null) {
            if (p.val == prev.val) {
                prev.next = p.next;
                p = p.next;
                //no change prev
            } else {
                prev = p;
                p = p.next;
            }
        }
        return head;
    }

    /**
     * nested while loop, skip next node
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur.next = cur.next.next; // skip next node
            }
            cur = cur.next; // to next node
        }
        return head;
    }

    public ListNode myDeleteDuplicates(ListNode head) {
        if (head == null)
            return head;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            while (cur != null && pre.val == cur.val) {
                cur = cur.next;
            }
            pre.next = cur;
            pre = cur;
            if (cur != null)
                cur = cur.next;
        }
        return dummyHead.next;
    }

    public static class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    static ListNode buildList() {
        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node0.next = node1;
        node1.next = node2;
        return node0;
    }
}