package easy;

/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 * For example,
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes
 * 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 * Tags: Linked list, Two pointers
 */
class RemoveNthNodeFromEnd {
    public static void main(String[] args) {

    }

    /**
     * Dummy head and Runner's technique
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode p1 = pre;
        ListNode p2 = pre;
        int i = 0;
        while (i < n) {
            p2 = p2.next;
            i++;
        }
        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return pre.next;
    }

    public ListNode removeNthFromEndB(ListNode head, int n) {
        if (head == null)
            return null;
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        //if remove the first node
        if (fast == null) {
            head = head.next;
            return head;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    /**
     * creek naive
     */
    public ListNode removeNthFromEndC(ListNode head, int n) {
        if (head == null)
            return null;
        //get length of list
        ListNode p = head;
        int len = 0;
        while (p != null) {
            len++;
            p = p.next;
        }
        //if remove first node
        int fromStart = len - n + 1;
        if (fromStart == 1)
            return head.next;
        //remove non-first node
        p = head;
        int i = 0;
        while (p != null) {
            i++;
            if (i == fromStart - 1) {
                p.next = p.next.next;
            }
            p = p.next;
        }
        return head;
    }

    class ListNode {
        int      value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

}