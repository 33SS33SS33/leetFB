package medium;

public class PlusOneLinkedList {
    /**
     * Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
     * You may assume the integer do not contain any leading zero, except the number 0 itself.
     * The digits are stored such that the most significant digit is at the head of the list.
     * Input: 1->2->3   Output: 1->2->4
     */
    public ListNode plusOneLinkedList(ListNode head) {
        if (DFS(head) == 0) {
            return head;
        } else {
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            return newHead;
        }
    }

    public int DFS(ListNode head) {
        if (head == null)
            return 1;
        int carry = DFS(head.next);
        if (carry == 0)
            return 0;
        int val = head.val + 1;
        head.val = val % 10;
        return val / 10;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
