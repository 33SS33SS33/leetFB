package medium;

/**
 * Sort a linked list using insertion sort.
 * Tags: Linkedlist, Sort
 */
/**模拟插入排序即可 需要用的到假链表头
 注意先不要将dummy和head连起来 这样是两个独立的链表 会比较好 把未排序的链表的元素插入进排序好的链表里
 要不容易打环*/
class InsertionSortList {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n1 = insertionSortList(n1);
        printList(n1);
    }

    public static void printList(ListNode x) {
        if (x != null) {
            System.out.print(x.val + " ");
            while (x.next != null) {
                System.out.print(x.next.val + " ");
                x = x.next;
            }
            System.out.println();
        }

    }

    /**
     * Check the list one by one to find a node that has smaller value than
     * nodes before it and swap
     */
    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode pre = new ListNode(0);
        pre.next = head;
        for (ListNode p = head.next, prev = head; p != null; prev = p, p = p.next) {
            for (ListNode c = pre; c.next != p; c = c.next) {
                if (c.next.val > p.val) {
                    prev.next = p.next; // skip p
                    p.next = c.next; // insert between cur and cur.next
                    c.next = p;
                    p = prev; // p is inserted to somewhere in the front, reset
                    break;
                }
            }
        }
        return pre.next;
    }

    /**
     * creek-----
     */
    public static ListNode insertionSortListB(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode newHead = new ListNode(head.val);
        ListNode pointer = head.next;
        // loop through each element in the list
        while (pointer != null) {
            // insert this element to the new list
            ListNode innerPointer = newHead;
            ListNode next = pointer.next;
            if (pointer.val <= newHead.val) {
                ListNode oldHead = newHead;
                newHead = pointer;
                newHead.next = oldHead;
            } else {
                while (innerPointer.next != null) {
                    if (pointer.val > innerPointer.val && pointer.val <= innerPointer.next.val) {
                        ListNode oldNext = innerPointer.next;
                        innerPointer.next = pointer;
                        pointer.next = oldNext;
                    }
                    innerPointer = innerPointer.next;
                }
                if (innerPointer.next == null && pointer.val > innerPointer.val) {
                    innerPointer.next = pointer;
                    pointer.next = null;
                }
            }
            // finally
            pointer = next;
        }
        return newHead;
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
