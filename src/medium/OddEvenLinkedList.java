package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/4/15.
 */

/**
 * "Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 * Example:
 * Given 1->2->3->4->5->NULL,
 * return 1->3->5->2->4->NULL.
 * Note:
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ..."
 * 就是当长度为奇数和长度为偶数的时候 都画个图 来思考一下就行
 */
public class OddEvenLinkedList {
    public static void main(String[] args) {
        OddEvenLinkedList r = new OddEvenLinkedList();
        ListNode head = buildList();
        System.out.print(oddEvenList(head));
    }

    public ListNode oddEvenLista(ListNode head) {
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

    /**
     * 将一个链表内的奇数元素放在前面，偶数元素放在后面
     *
     * @param head 链表首节点
     * @return 转换后的链表
     */
    public static ListNode oddEvenList(ListNode head) {
        //输入合法性判断
        if (head == null) {
            return null;
        } else if (head.next == null) {
            return head;
        }
        ListNode odd = new ListNode(0);  //奇数链表：仅存放奇数位置节点
        ListNode oddCurr = odd;          //奇数链表的链表尾节点
        ListNode even = new ListNode(0); //偶数链表：仅存放偶数位置节点
        ListNode evenCurr = even;        //偶数链表的链表尾节点
        //分别生成奇数链表和偶数链表
        ListNode tmp = head;
        int counter = 0;
        while (tmp != null) {
            counter++;
            if (counter % 2 != 0) {
                oddCurr.next = new ListNode(tmp.val);
                oddCurr = oddCurr.next;
            } else {
                evenCurr.next = new ListNode(tmp.val);
                evenCurr = evenCurr.next;
            }
            tmp = tmp.next;
        }
        oddCurr.next = even.next; //偶数链表接在奇数链表后面
        return odd.next;
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
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
