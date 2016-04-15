package medium;


/**
 * Created by GAOSHANSHAN835 on 2016/4/15.
 */
public class OddEvenLinkedList {
    public static void main(String[] args) {
        OddEvenLinkedList r = new OddEvenLinkedList();
        ListNode head = buildList();
        System.out.print(oddEvenList(head));
    }

    /**
     * 将一个链表内的奇数元素放在前面，偶数元素放在后面
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
    static ListNode buildList() {
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
