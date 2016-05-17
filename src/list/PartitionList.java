package list;

/**
 * Given a linked list and a value x, partition it such that all nodes less
 * than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the
 * two partitions.
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.

 * Tags: Linkedlist, Two Pointers
 */

/**
 * 创建两个队列 一个是小于x的 一个是大于等于x的
 * 然后遍历链表  然后将当前元素分配到两个队列 再将两个队列连起来即可
 * 注意要仔细处理结尾 否则会有环
 * hi_curr.next = None
 */
class PartitionList {
    public static void main(String[] args) {
        ListNode head = buildTestList1();
        ListNode head2 = buildTestList1();
        ListNode head3 = buildTestList1();
        partition(head, 3);
        partitionB(head2, 3);
        partitionC(head3, 3);
        while (head.next != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println(head.val);

        while (head2.next != null) {
            System.out.print(head2.val + "->");
            head2 = head2.next;
        }
        System.out.println(head2.val);
        while (head3.next != null) {
            System.out.print(head3.val + "->");
            head3 = head3.next;
        }
        System.out.println(head3.val);
    }

    /**
     * Build left and right lists and concatenate
     */
    public static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null)
            return head;
        ListNode cur = head;
        ListNode pre1 = new ListNode(0);
        ListNode p = pre1;
        ListNode pre2 = new ListNode(0);
        ListNode q = pre2;
        // Partition into two halves.        
        while (cur != null) {
            if (cur.val < x) {
                p.next = cur;
                p = p.next;
            } else {
                q.next = cur;
                q = q.next;
            }
            cur = cur.next;
        }
        //concatenate two havles
        q.next = null; // Make sure the last node points to null 
        p.next = pre2.next;
        return pre1.next;
    }

    /**
     * Move greater and equal value nodes to tail
     */
    public static ListNode partitionB(ListNode head, int x) {
        if (head == null || head.next == null)
            return head; // too short
        ListNode dummy = new ListNode(0); // create a dummy node
        dummy.next = head;
        ListNode p = dummy;
        ListNode start = dummy;
        while (p != null && p.next != null) {
            if (p.next.val >= x)
                p = p.next;
            else { // move smaller nodes to start
                if (p == start) {  // don't forget the edge cases when p == start
                    start = start.next;
                    p = p.next;
                } else {
                    ListNode tmp = p.next; // move to start
                    p.next = tmp.next;
                    tmp.next = start.next;
                    start.next = tmp;
                    start = tmp; // don't forget to move start.
                }
            }
        }
        return dummy.next;
    }

    public static ListNode partitionC(ListNode head, int x) {
        if (head == null)
            return null;
        ListNode helper = new ListNode(0);
        helper.next = head;
        ListNode walker = helper;
        ListNode runner = helper;
        while (runner.next != null) {
            if (runner.next.val < x) {
                if (walker != runner) {
                    ListNode next = runner.next.next;
                    runner.next.next = walker.next;
                    walker.next = runner.next;
                    runner.next = next;
                } else
                    runner = runner.next;
                walker = walker.next;
            } else {
                runner = runner.next;
            }
        }
        return helper.next;
    }

    public ListNode partitionD(ListNode head, int x) {
        if (head == null)
            return null;
        ListNode fakeHead1 = new ListNode(0);
        ListNode fakeHead2 = new ListNode(0);
        fakeHead1.next = head;
        ListNode p = head;
        ListNode prev = fakeHead1;
        ListNode p2 = fakeHead2;
        while (p != null) {
            if (p.val < x) {
                p = p.next;
                prev = prev.next;
            } else {
                p2.next = p;
                prev.next = p.next;
                p = prev.next;
                p2 = p2.next;
            }
        }
        // close the list
        p2.next = null;
        prev.next = fakeHead2.next;
        return fakeHead1.next;
    }

    static ListNode buildTestList1() {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(4);
        ListNode third = new ListNode(3);
        ListNode forth = new ListNode(2);
        ListNode fifth = new ListNode(5);
        ListNode sixth = new ListNode(2);
        head.next = second;
        second.next = third;
        third.next = forth;
        forth.next = fifth;
        fifth.next = sixth;
        return head;
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
