package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
/**用个dummy头就比较方便*/
public class RemoveLinkedListElements {
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

    class ListNode {
        int      val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
