package hard;

import java.util.*;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 * 
 * Tags: Divide and Conquer, Linkedlist, Heap
 */
class MergeKSortedList {
    public static void main(String[] args) {
        
    }
    
    /**
     * Use a heap, O(n * log(k))
     */
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
    
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    /**
     * Divide and conquer
     * merge two halves, divide to merge two lists
     */
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

    public ListNode mergeKLists2(List<ListNode> lists) {
        /*base cases*/
        if (lists.size() == 0) return null;
        if (lists.size() == 1) return lists.get(0);
        if (lists.size() == 2) return mergeTwoLists(lists.get(0), lists.get(1));
        /*merge two halves*/
        return mergeTwoLists(mergeKLists(lists.subList(0, lists.size()/2)), 
            mergeKLists(lists.subList(lists.size()/2, lists.size())));
    }

    public ListNode mergeTwoListsB(ListNode l1, ListNode l2) {

        ListNode p1 = l1;
        ListNode p2 = l2;

        ListNode fakeHead = new ListNode(0);
        ListNode p = fakeHead;

        while(p1 != null && p2 != null){
            if(p1.val <= p2.val){
                p.next = p1;
                p1 = p1.next;
            }else{
                p.next = p2;
                p2 = p2.next;
            }

            p = p.next;
        }

        if(p1 != null)
            p.next = p1;
        if(p2 != null)
            p.next = p2;

        return fakeHead.next;
    }
}
