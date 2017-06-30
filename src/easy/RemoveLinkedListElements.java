package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 * 用个dummy头就比较方便
 */
public class RemoveLinkedListElements {
    public static void main(String[] args) {
        RemoveLinkedListElements r = new RemoveLinkedListElements();
        ListNode head = buildList();
        System.out.print(r.removeElements(head, 1));
    }

    public ListNode removeElementsa(ListNode head, int val) {
        if (head == null)
            return null;
        head.next = removeElements(head.next, val);
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

    /**
     * creek---
     */
    public ListNode removeElementsB(ListNode head, int val) {
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
