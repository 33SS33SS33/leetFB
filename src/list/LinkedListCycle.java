package list;

/**
 * Given a linked list, determine if it has a cycle in it.
 * <p/>
 * Follow up:
 * Can you solve it without using extra space?
 * <p/>
 * Tags: Linkedlist, Two pointers
 */
class LinkedListCycle {
    public static void main(String[] args) {
        LinkedListCycle2 r = new LinkedListCycle2();
        ListNode head = buildList();
        System.out.print(hasCycle(head));
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

    /**
     * Runnner's technique
     * Check the next and next next of faster node is slower node or not.
     */
    public static boolean hasCycle(ListNode head) {
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

    static class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}