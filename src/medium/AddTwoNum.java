package medium;

/**
 * You are given two linked lists representing two non-negative numbers. The
 * digits are stored in reverse order and each of their nodes contain a single
 * digit. Add the two numbers and return it as a linked list.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Tags: Linkedlist, Math
 */

/**
 * 实现中注意维护进位，陷阱的话记住最后还要判一下有没有进位，如果有再生成一位-
 */
class AddTwoNum {
    public static void main(String[] args) {
        AddTwoNum s = new AddTwoNum();
        ListNode r1 = buildList1();
        ListNode r2 = buildList2();
        ListNode head = s.addTwoNumbers(r1, r2);
        while (head != null) {
            System.out.print(head.next == null ? head.val : head.val + "->");
            head = head.next;
        }
    }

    /**
     * Create a pre head pointer
     * Build list node one by one
     * Use sum to track the current sum of nodes, or node
     * Reset sum using sum /= 10
     * Note whether there is carry for last digit
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode pre = new ListNode(0); // set pre head
        ListNode d = pre;
        int sum = 0; // the sum of two nodes
        while (c1 != null || c2 != null) { // traverse longer list
            if (c1 != null) { // add one list 
                sum += c1.val;
                c1 = c1.next; // move on 
            }
            if (c2 != null) { // add another list
                sum += c2.val;
                c2 = c2.next; // move on 
            }
            // build next node
            d.next = new ListNode(sum % 10); // digit for current node
            sum /= 10; // carry
            d = d.next;
        }
        if (sum == 1)
            d.next = new ListNode(1); // note that can have carry at the last digit
        return pre.next;
    }

    public ListNode addTwoNumbersB(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(0);
        ListNode head = prev;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            ListNode cur = new ListNode(0);
            int sum = ((l2 == null) ? 0 : l2.val) + ((l1 == null) ? 0 : l1.val) + carry;

            cur.val = sum % 10;
            carry = sum / 10;
            prev.next = cur;
            prev = cur;
            l1 = (l1 == null) ? l1 : l1.next;
            l2 = (l2 == null) ? l2 : l2.next;
        }
        return head.next;
    }

    public static class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    static ListNode buildList1() {
        ListNode node0 = new ListNode(2);
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(6);
        node0.next = node1;
        node1.next = node2;
        return node0;
    }

    static ListNode buildList2() {
        ListNode node0 = new ListNode(5);
        ListNode node1 = new ListNode(6);
        ListNode node2 = new ListNode(4);
        node0.next = node1;
        node1.next = node2;
        return node0;
    }

}
