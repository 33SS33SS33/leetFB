package hard;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end
 * should remain as it is.
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * Only constant memory is allowed.
 * For example,
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 * Tags: Linked list
 */
class ReverseNodesInKGroup {
    public static void main(String[] args) {
        ReverseNodesInKGroup r = new ReverseNodesInKGroup();
        ListNode head = buildList();
        ListNode r2 = r.reverseKGroup(head, 3);
        while (r2 != null) {
            System.out.print(r2.next == null ? r2.val : r2.val + "->");
            r2 = r2.next;
        }
    }

    /**
     * Use pre and cur to store the node before the sublist and head of sublist
     * Insert next to head each time to reverse a list
     * Stop when the next of current node is next group's head
     * Go ahead move pre and cur, and reverse the next group
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 0)
            return head;
        if (k == 0 || k == 1)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy; // current group dummy
        ListNode cur = head; // current group head
        while (cur != null) {
            ListNode p = pre.next;
            int group = k;
            while (p != null && group > 0) { // move p to next head as stop node
                group--;
                p = p.next;
            }
            if (group > 0)
                break; // end of list
            while (cur.next != p) { // note it's cur.next here
                ListNode nt = cur.next.next; // insert cur.next to head
                cur.next.next = pre.next;
                pre.next = cur.next;
                cur.next = nt; // unlink cur with cur next, link with next next
            }
            pre = cur; // move pre to current group's tail
            cur = cur.next; // move cur to next group's head
        }
        return dummy.next;
    }

    static ListNode buildList() {
        ListNode node0 = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        return node0;
    }

    public static class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
