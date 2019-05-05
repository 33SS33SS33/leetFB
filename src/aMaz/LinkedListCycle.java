package aMaz;


class LinkedListCycle {
    public static void main(String[] args) {
        ListNode head = buildList();
        System.out.print(linkedListCycle(head));
    }

    /**
     * Runnner's technique 最好的
     * Check the next and next next of faster node is slower node or not.
     * <p>
     * Given a linked list, determine if it has a cycle in it.
     * Follow up: Can you solve it without using extra space?
     * Tags: Linkedlist, Two pointers
     */
    public static boolean linkedListCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                return true;
        }
        return false;
    }

    static ListNode buildList() {
        ListNode node0 = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(1);

        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        return node0;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}