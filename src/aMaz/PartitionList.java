package aMaz;

class PartitionList {
    public static void main(String[] args) {
        ListNode head = buildTestList1();
        partitionList(head, 3);
        while (head.next != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println(head.val);
    }

    /**
     * Given a linked list and a value x, palindromePartition it such that all nodes less
     * than x come before nodes greater than or equal to x.
     * You should preserve the original relative order of the nodes in each of the two partitions.
     * Given 1->4->3->2->5->2 and x = 3,
     * return 1->2->2->4->3->5.
     * Tags: Linkedlist, Two Pointers
     * 创建两个队列 一个是小于x的 一个是大于等于x的
     * 然后遍历链表  然后将当前元素分配到两个队列 再将两个队列连起来即可
     * 注意要仔细处理结尾 否则会有环
     * hi_curr.next = None
     */
    public static ListNode partitionList(ListNode head, int x) {
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
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
