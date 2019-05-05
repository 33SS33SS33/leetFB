package aMaz;

class RemoveNthNodeFromEndofList {
    public static void main(String[] args) {

    }

    /**
     * Given a linked list, remove the nth node from the end of list and return its head.
     * Given: 1->2->3->4->5, and n = 2.
     * After removing the second node from the end, the linked list becomes
     * 1->2->3->5.
     * Given n will always be valid. Try to do this in one pass.
     * Tags: Linked list, Two pointers
     * Dummy head and Runner's technique
     */
    public ListNode removeNthNodeFromEndofList(ListNode head, int n) {
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


    class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

}