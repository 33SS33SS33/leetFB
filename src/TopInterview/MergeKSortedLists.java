package TopInterview;

import java.util.*;

/**
 * HARD
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * Example:
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 * Tags: Divide and Conquer, Linkedlist, Heap
 * 使用priority queue 来做
 * 放进去tuple的原因是因为要不然heap无法排序
 * 整体思路就是用所有链表的头一个节点建立heap
 * 然后从heap里弹出最小的元素 同时压进去啊这个最小元素的下一个元素  一直到最后
 */
class MergeKSortedLists {
    public static void main(String[] args) {
        ListNode head1 = buildList();
        ListNode head2 = buildList2();
        List<ListNode> lists = new ArrayList<ListNode>();
        lists.add(head1);
        lists.add(head2);
        ListNode res = new MergeKSortedLists().mergeKSortedLista(lists);

        while (res != null) {
            System.out.print(res.val + ",");
            res = res.next;
        }
    }

    /**
     * Use a heap, O(n * log(k)) 最好的
     * 维护一个大小为k的堆，每次取堆顶的最小元素放到结果中，然后读取该元素的下一个元素放入堆中，重新维护好。
     * 因为每个链表是有序的，每次又是去当前k个元素中最小的，所以当所有链表都读完时结束，这个时候所有元素按从小到大放在结果链表中。
     * 这个算法每个元素要读取一次，即是k*n次，然后每次读取元素要把新元素插入堆中要logk的复杂度，所以总时间复杂度是O(nklogk)。
     * 空间复杂度是堆的大小，即为O(k)。
     * //http://blog.csdn.net/linhuanmars/article/details/19899259
     */
    public ListNode mergeKSortedLista(List<ListNode> lists) {
        if (lists == null || lists.size() == 0)
            return null;
        // Build priority queue
        Queue<ListNode> queue = new PriorityQueue<>(lists.size(), Comparator.comparingInt(n1 -> n1.val));
        for (ListNode n : lists)
            if (n != null)
                queue.add(n);
        ListNode dummy = new ListNode(0); // set dummy head
        ListNode tail = dummy;
        while (!queue.isEmpty()) { // build next
            tail.next = queue.poll();
            tail = tail.next;
            if (tail.next != null)   ///?????????
                queue.add(tail.next);
        }
        return dummy.next;
    }

    /**
     * 第二种
     * Divide and conquer  merge two halves, divide to merge two lists
     */
/*
    public ListNode mergeKSortedListb(ArrayList<ListNode> lists) {
        if (lists == null || lists.size() == 0)
            return null;
        return helper(lists, 0, lists.size() - 1);
    }

    private ListNode helper(ArrayList<ListNode> lists, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            return merge(helper(lists, l, m), helper(lists, m + 1, r));
        }
        return lists.get(l);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        dummy.next = l1;
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l1 = l1.next;
            } else {
                ListNode next = l2.next;
                cur.next = l2;
                l2.next = l1;
                l2 = next;
            }
            cur = cur.next;
        }
        if (l2 != null)
            cur.next = l2;
        return dummy.next;
    }
*/

    static ListNode buildList() {
        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(6);
        node0.next = node1;
        node1.next = node2;
        return node0;
    }

    static ListNode buildList2() {
        ListNode node0 = new ListNode(3);
        ListNode node1 = new ListNode(7);
        ListNode node2 = new ListNode(9);
        node0.next = node1;
        node1.next = node2;
        return node0;
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
