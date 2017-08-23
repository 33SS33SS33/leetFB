package aMaz;

/**
 * Write a program to find the node at which the intersection of two singly
 * linked lists begins.
 * For example, the following two linked lists:
 * A:          a1 → a2
 * ↘
 * c1 → c2 → c3
 * ↗
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 * Notes:
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function
 * returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 * Tags: Linkedlist
 * 如果A和B的长度是一样的 那么只需要A和B同时移动 那么第一个相等的点就是交点
 * 所以问题是如何让他们同时移动
 * 所以就用到了首先让A和B从头开始走  然后谁第一个走到了末尾 那么就把它换成另一个的头部 继续走 然后另一个也到了末尾
 * 然后把它也换个头部 这时候两个就同步了
 * 比如A长度是5 B长度是6 当A走到头的时候B还差一个 所以当B到头的是
 * 另一个B刚走了一个 然后另一个是从A开始 这样两个就是同步的了
 */
class IntersectionOfTwoLinkedList {

    /**
     * 最好的
     */
    public ListNode intersectionOfTwoLinkedList(ListNode headA, ListNode headB) {
        if (null == headA || null == headB)
            return null;
        ListNode curA = headA, curB = headB;
        while (curA != curB) {
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
        }
        return curA;
    }

    public ListNode intersectionOfTwoLinkedListb(ListNode headA, ListNode headB) {
        int lenA = length(headA), lenB = length(headB);
        // move headA and headB to the same start point
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        while (lenA < lenB) {
            headB = headB.next;
            lenB--;
        }
        // find the intersection until end
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    private int length(ListNode n) {
        if (n == null)
            return 0;
        int length = 0;
        while (n != null) {
            length++;
            n = n.next;
        }
        return length;
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