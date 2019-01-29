package aFB;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given a singly linked list, determine if it is a palindrome.
 * Example 1:
 * Input: 1->2
 * Output: false
 * Example 2:
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 * 首先快慢指针找中点
 * 然后把中点之后的翻转
 * 然后翻转过后的和开头的挨个比较
 */
public class PalindromeLinkedList {
    public static void main(String[] args) {
        PalindromeLinkedList r = new PalindromeLinkedList();
        ListNode head = buildList();
        System.out.println(palindromeLinkedList(head));
        ListNode head3 = buildList();
        System.out.println(palindromeLinkedListc(head3));
    }

    public static boolean palindromeLinkedList(ListNode head) {
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
     * We can create a new list in reversed order and then compare each node. The time and space are O(n)
     */
    public static boolean palindromeLinkedListc(ListNode head) {
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
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
