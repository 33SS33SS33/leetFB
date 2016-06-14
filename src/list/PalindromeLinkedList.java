package list;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * 首先快慢指针找中点
 * 然后把中点之后的翻转
 * 然后翻转过后的和开头的挨个比较
 */
public class PalindromeLinkedList {
    public static void main(String[] args) {
        PalindromeLinkedList r = new PalindromeLinkedList();
        ListNode head = buildList();
        System.out.println(isPalindromeA(head));
        ListNode head2 = buildList();
        System.out.println(isPalindromeB(head2));
        ListNode head3 = buildList();
        System.out.println(isPalindromeC(head3));
    }

    public static boolean isPalindromeA(ListNode head) {
        ListNode m = mid(head);
        m = reverse(m);
        while (m != head && m != null) {
            if (m.val != head.val) {
                return false;
            }
            m = m.next;
            head = head.next;
        }
        return true;
    }

    static ListNode mid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode t = head.next;
            head.next = prev;
            prev = head;

            head = t;
        }
        return prev;
    }

    /**
     * creek good  time is O(n) and space is O(1).
     */
    public static boolean isPalindromeB(ListNode head) {
        if (head == null || head.next == null)
            return true;
        //find list center
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode secondHead = slow.next;
        slow.next = null;
        //reverse second part of the list
        ListNode p1 = secondHead;
        ListNode p2 = p1.next;
        while (p1 != null && p2 != null) {
            ListNode temp = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = temp;
        }
        secondHead.next = null;
        //compare two sublists now
        ListNode p = (p2 == null ? p1 : p2);
        ListNode q = head;
        while (p != null) {
            if (p.val != q.val)
                return false;
            p = p.next;
            q = q.next;
        }
        return true;
    }

    /**
     * We can create a new list in reversed order and then compare each node. The time and space are O(n)
     */
    public static boolean isPalindromeC(ListNode head) {
        if (head == null)
            return true;
        ListNode p = head;
        ListNode prev = new ListNode(head.val);
        while (p.next != null) {
            ListNode temp = new ListNode(p.next.val);
            temp.next = prev;
            prev = temp;
            p = p.next;
        }
        ListNode p1 = head;
        ListNode p2 = prev;
        while (p1 != null) {
            if (p1.val != p2.val)
                return false;

            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    static ListNode buildList() {
        ListNode node0 = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(0);
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        return node0;
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
