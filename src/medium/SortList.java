package medium;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * Tags: Linkedlist, Sort
 * 使用mergesort即可 每次都用快慢指针找中点 然后分割链表
 * 注意快指针要是头指针的下一个节点 否则分割只有两个元素的链表会分不开 （因为slow会向前走一步）
 * 而且当分成两部分的时候  slow就是前一部分的末尾 记得用none来结尾 然后下一部分的开始是slow.Next
 * 也需要练习手写最普通的mergesort mergesort有迭代和递归两种 未实现
 */
class SortList {
    public static void main(String[] args) {

    }

    /**
     * 最好的
     */
    public ListNode sortLista(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode f = head.next.next;
        ListNode p = head;
        while (f != null && f.next != null) {
            p = p.next;
            f = f.next.next;
        }
        ListNode h2 = sortLista(p.next);
        p.next = null;
        return merge(sortLista(head), h2);
    }

    public ListNode merge(ListNode h1, ListNode h2) {
        ListNode hn = new ListNode(Integer.MIN_VALUE);
        ListNode c = hn;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                c.next = h1;
                h1 = h1.next;
            } else {
                c.next = h2;
                h2 = h2.next;
            }
            c = c.next;
        }
        if (h1 != null)
            c.next = h1;
        if (h2 != null)
            c.next = h2;
        return hn.next;
    }

    /**
     * Get list length and do merge sort
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode tail = head;
        int len = 0;
        while (tail != null) {
            tail = tail.next;
            len++;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        head = mergeSort(dummy, head, len);
        return head;
    }

    /**
     * Cut into two halves
     * Sort left half first, move to right half's pre head and sort right
     * Merge two halves
     * Insert node in latter part if its smaller than current node
     */
    public ListNode mergeSort(ListNode preHead, ListNode head, int len) {
        if (head == null || len <= 1)
            return head;
        int left = len / 2;
        int right = len - left;
        // sort left
        head = mergeSort(preHead, head, left);
        // sort right
        ListNode pMid = head;
        for (int i = 0; i < left - 1; i++)
            pMid = pMid.next;
        mergeSort(pMid, pMid.next, right);
        // merge
        ListNode pre1 = preHead;
        ListNode p1 = head;
        ListNode pre2 = pMid;
        ListNode p2 = pMid.next;
        if (p1.val > p2.val)
            head = p2; // switch head
        while (left > 0 && right > 0) {
            // merge second half to first half
            if (p1.val > p2.val) {
                pre2.next = p2.next; // insert p2 before p1
                p2.next = p1;
                pre1.next = p2;
                // set to next
                pre1 = p2;
                p2 = pre2.next;
                right--;
            } else {
                // set to next
                pre1 = p1;
                p1 = p1.next;
                left--;
            }
        }
        return head;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
