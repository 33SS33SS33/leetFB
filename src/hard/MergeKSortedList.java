package hard;

import java.util.*;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 * Tags: Divide and Conquer, Linkedlist, Heap
 */
/*使用priority queue 来做
放进去tuple的原因是因为要不然heap无法排序
整体思路就是用所有链表的头一个节点建立heap
然后从heap里弹出最小的元素 同时压进去啊这个最小元素的下一个元素  一直到最后*/
class MergeKSortedList {
    public static void main(String[] args) {
        ListNode head1=buildList();
        ListNode head2=buildList2();
        List<ListNode> lists=new ArrayList<ListNode>();
        lists.add(head1);
        lists.add(head2);
        ListNode res=new MergeKSortedList().mergeKListsB(lists);

        while(res!=null){
            System.out.print(res.val+",");
            res=res.next;
        }
    }

/**最好的*/
/*维护一个大小为k的堆，每次取堆顶的最小元素放到结果中，然后读取该元素的下一个元素放入堆中，重新维护好。
因为每个链表是有序的，每次又是去当前k个元素中最小的，所以当所有链表都读完时结束，这个时候所有元素按从小到大放在结果链表中。
这个算法每个元素要读取一次，即是k*n次，然后每次读取元素要把新元素插入堆中要logk的复杂度，所以总时间复杂度是O(nklogk)。
空间复杂度是堆的大小，即为O(k)。*/
    public ListNode mergeKListsB(List<ListNode> lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(10, new Comparator<ListNode>() {
            @Override public int compare(ListNode n1, ListNode n2) {
                return n1.val - n2.val;
            }
        });
        for (int i = 0; i < lists.size(); i++) {
            ListNode node = lists.get(i);
            if (node != null) {
                heap.offer(node);
            }
        }
        ListNode head = null;
        ListNode pre = head;
        while (heap.size() > 0) {
            ListNode cur = heap.poll();
            if (head == null) {
                head = cur;
                pre = head;
            } else {
                pre.next = cur;
            }
            pre = cur;
            if (cur.next != null)
                heap.offer(cur.next);
        }
        return head;
    }


    public ListNode mergeKListsA(List<ListNode> lists) {
        /*base cases*/
        if (lists.size() == 0) return null;
        if (lists.size() == 1) return lists.get(0);
        if (lists.size() == 2) return mergeTwoLists(lists.get(0), lists.get(1));
        /*merge two halves*/
        return mergeTwoLists(mergeKLists(lists.subList(0, lists.size()/2)), mergeKLists(lists.subList(lists.size()/2, lists.size())));
    }
    /**Divide and conquer  merge two halves, divide to merge two lists*/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        // next node should be the result of comparison
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2); // notice l1.next
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next); // notice l2.next
            return l2;
        }
    }
    /**Use a heap, O(n * log(k))*/
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) return null;
        // Build priority queue
        Queue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>() {
            @Override
            public int compare(ListNode n1, ListNode n2) {
                return n1.val - n2.val;
            }
        });
        for (ListNode n : lists) if (n != null) queue.add(n);
        ListNode dummy = new ListNode(0); // set dummy head
        ListNode tail = dummy;
        while (!queue.isEmpty()) { // build next
            tail.next = queue.poll();
            tail = tail.next;
            if (tail.next != null) queue.add(tail.next);
        }
        return dummy.next;
    }

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
