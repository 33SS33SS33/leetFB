package aMaz;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
public class RemoveLinkedListElements {
    public static void main(String[] args) {
        RemoveLinkedListElements r = new RemoveLinkedListElements();
        ListNode head = buildList();
        System.out.print(r.removeElements(head, 1));
    }

    /**
     * Remove all elements from a linked list of integers that have value val.
     * Input:  1->2->6->3->4->5->6, val = 6
     * Output: 1->2->3->4->5
     * 用个dummy头就比较方便
     */
    public ListNode removeLinkedListElements(ListNode head, int val) {
        ListNode helper = new ListNode(0);
        helper.next = head;
        ListNode p = helper;
        while (p.next != null) {
            if (p.next.val == val) {
                ListNode next = p.next;
                p.next = next.next;
            } else {
                p = p.next;
            }
        }
        return helper.next;
    }

    public ListNode removeElementsa(ListNode head, int val) {
        if (head == null)
            return null;
        head.next = removeElementsa(head.next, val);
        return head.val == val ? head.next : head;
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
        if (head.val == val)
            return removeElements(head.next, val);
        head.next = removeElements(head.next, val);
        return head;
    }


    static ListNode buildList() {
        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(6);
        node0.next = node1;
        node1.next = node2;
        return node0;
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
