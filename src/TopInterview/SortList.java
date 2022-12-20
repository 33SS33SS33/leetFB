package TopInterview;

import Utils.ListNode;

import java.util.PriorityQueue;

public class SortList {

    public ListNode sortList(ListNode head) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> b.val - a.val);
        while (head != null) {
            pq.add(head);
            head = head.next;
        }

        ListNode newHead = null;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            node.next = newHead;
            newHead = node;
        }
        return newHead;
    }
}
