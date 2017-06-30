package medium;

/**
 * Reverse a linked list from position m to n
 * Do it in-place and in one-pass
 * Eg:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4
 * Return 1->4->3->2->5->NULL
 * Note:
 * 1 <= m <= n <= length of the list
 * Tags: Linkedlist
 * 用一个假的链表头
 * 保存reverse之前的节点 然后每个元素逐个倒转 然后再把倒转部分的头和尾颠倒一下
 */
class ReverseLinkedList2 {
    public static void main(String[] args) {
        ListNode head = buildTestList1();
        reverseBetween(head, 2, 6);
        while (head.next != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.print(head.val);
    }

    public ListNode reverseBetweena(ListNode head, int m, int n) {
        if (head == null)
            return null;
        ListNode dummy = new ListNode(0); // create a dummy node to mark the head ofthis list
        dummy.next = head;
        ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
        for (int i = 0; i < m - 1; i++)
            pre = pre.next;
        ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
        ListNode then = start.next; // a pointer to a node that will be reversed
        // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
        // dummy-> 1 -> 2 -> 3 -> 4 -> 5
        for (int i = 0; i < n - m; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }
        // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
        // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)
        return dummy.next;
    }

    /**
     * Move pointers to m
     * Then insert next code to sublist's head till we reach n
     * Reconnect sublist with original list after reversing
     * We need 1 dummy head, head and tail for sublist,
     * And cur for current node, preCur for dummy head of sublist
     * 5 pointers in total
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (m >= n || head == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 1; i < m; i++)
            pre = pre.next;
        ListNode cur = pre.next;
        for (int i = m; i < n; i++) { // insert next to head to reverse
            ListNode temp = cur.next.next;
            cur.next.next = pre.next;
            pre.next = cur.next;
            cur.next = temp;
        }
        return dummy.next;
    }

    public static ListNode reverseBetweenB(ListNode head, int m, int n) {
        if (head == null)
            return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode preNode = dummy;
        int i = 1;
        while (preNode.next != null && i < m) {
            preNode = preNode.next;
            i++;
        }
        if (i < m)
            return head;
        ListNode mNode = preNode.next;
        ListNode cur = mNode.next;
        while (cur != null && i < n) {
            ListNode next = cur.next;
            cur.next = preNode.next;
            preNode.next = cur;
            mNode.next = next;
            cur = next;
            i++;
        }
        return dummy.next;
    }

    /**
     * creek--
     */
    public ListNode reverseBetweenC(ListNode head, int m, int n) {
        if (m == n)
            return head;
        ListNode prev = null;//track (m-1)th node
        ListNode first = new ListNode(0);//first's next points to mth
        ListNode second = new ListNode(0);//second's next points to (n+1)th
        int i = 0;
        ListNode p = head;
        while (p != null) {
            i++;
            if (i == m - 1) {
                prev = p;
            }
            if (i == m) {
                first.next = p;
            }
            if (i == n) {
                second.next = p.next;
                p.next = null;
            }
            p = p.next;
        }
        if (first.next == null)
            return head;
        // reverse list [m, n]
        ListNode p1 = first.next;
        ListNode p2 = p1.next;
        p1.next = second.next;
        while (p1 != null && p2 != null) {
            ListNode t = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = t;
        }
        //connect to previous part
        if (prev != null)
            prev.next = p1;
        else
            return p1;

        return head;
    }

    static ListNode buildTestList1() {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode forth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        ListNode sixth = new ListNode(6);
        head.next = second;
        second.next = third;
        third.next = forth;
        forth.next = fifth;
        fifth.next = sixth;
        return head;
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
