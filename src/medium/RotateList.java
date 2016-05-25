package medium;

/**
 * Given a list, rotate the list to the right by k places, where k is
 * non-negative.
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 * Tags: Linked List, Two Pointers
 */

/**
 * 就是把右边的元素挪到左边 挪动k个 k有可能比链表长度长  如果等于链表长度其实就是原来的链表
 * 首先找到链表的末尾并且计算链表的长度
 * 然后把链表的末尾和链表头连接起来 再往前跑length - k%length 然后在这个位置把链表断开即可
 * 即pointer此时的next就是链表头 然后把pointer的next指向none
 */
class RotateList {
    public static void main(String[] args) {
        ListNode head = buildTestList1();
        ListNode root = rotateRight(head, 2);
        printList(root);
    }

    /**
     * 最好的
     * Two pointers
     * Move fast pointer to the end of the list to get length
     * Move slow pointer to len - n % len to get the break point
     * Connect fast with head, update new head
     * Set slow.next to null to unlink the list
     */
    public static ListNode rotateRight(ListNode head, int n) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        // get length and move fast to the end of list
        int len;
        for (len = 0; fast.next != null; len++)
            fast = fast.next;
        // get the len-n%len th node
        for (int j = len - n % len; j > 0; j--)
            slow = slow.next;
        fast.next = dummy.next;
        dummy.next = slow.next;
        slow.next = null; // break linkedlist
        return dummy.next;
    }

    /**
     * My own implementation
     * Calculate length of list, then adjust n in range
     * Break the list at right point, which is len - n % len
     */
    public static ListNode rotateRightB(ListNode head, int n) {
        if (head == null || head.next == null)
            return head;
        int len = listLength(head);
        n %= len;
        if (n == 0)
            return head;
        if (n < 0)
            n += len;
        n = len - n;
        ListNode p = new ListNode(0);
        p.next = head;
        while (n > 0) {
            p = p.next;
            n--;
        }
        ListNode newHead = p.next;
        p.next = null;
        ListNode cur = newHead;
        while (cur.next != null)
            cur = cur.next;
        cur.next = head;
        return newHead;
    }

    static int listLength(ListNode head) {
        ListNode cur = head;
        int res = 0;
        while (cur != null) {
            cur = cur.next;
            res++;
        }
        return res;
    }

    private static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + "->");
            cur = cur.next;
        }
        System.out.println("NULL");
    }

    static ListNode buildTestList1() {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode forth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        head.next = second;
        second.next = third;
        third.next = forth;
        forth.next = fifth;
        return head;
    }

    static class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}