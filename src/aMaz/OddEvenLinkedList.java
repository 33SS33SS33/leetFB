package aMaz;

public class OddEvenLinkedList {
    public static void main(String[] args) {
        ListNode head = buildList();
        System.out.print(oddEvenLinkedList(head));
    }

    /**
     * "Given a singly linked list, group all odd nodes together followed by the even nodes.
     * Please note here we are talking about the node number and not the value in the nodes.
     * You should try to do it in place. The program should run in O(1) space complexity and O(nodes)
     * time complexity.
     * Given 1->2->3->4->5->NULL, return 1->3->5->2->4->NULL.
     * Note: The relative order inside both the even and odd groups should remain as it was in the input.
     * The first node is considered odd, the second node even and so on ..."
     * 就是当长度为奇数和长度为偶数的时候 都画个图 来思考一下就行
     * 将一个链表内的奇数元素放在前面，偶数元素放在后面
     * Time complexity : O(n)O(n). There are total nn nodes and we visit each node once.
     * Space complexity : O(1)O(1). All we need is the four pointers.
     */
    public static ListNode oddEvenLinkedList(ListNode head) {
        if (head != null) {
            ListNode odd = head, even = head.next, evenHead = even;
            while (even != null && even.next != null) {
                odd.next = odd.next.next;
                even.next = even.next.next;
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
        }
        return head;
    }

    public static ListNode buildList() {
        ListNode node0 = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(1);

        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

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
