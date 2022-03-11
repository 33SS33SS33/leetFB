package aMaz;

/**
 * Reverse a singly linked list. easy
 * Example:
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

class ReverseLinkedList {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        ReverseLinkedList r = new ReverseLinkedList();
        Node reversed = r.reverseLinkedListb(n1);
        Node cur = reversed;
        while (cur != null) {
            System.out.print(cur.next != null ? cur.val + "->" : cur.val);
            cur = cur.next;
        }
    }

    /**
     * 迭代
     */
    public Node reverseLinkedLista(Node head) {
        Node newHead = null;
        while (head != null) {
            Node next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    Node reverseLinkedListb(Node head) {
        if (head == null || head.next == null)
            return head;
        Node temp = reverseLinkedListb(head.next);
        head.next.next = head;
        head.next = null;
        return temp;
    }

    // 递归
/*    public Node reverseList(Node head) {
    *//* recursive solution *//*
        return reverseListInt(head, null);
    }

    private Node reverseListInt(Node head, Node newHead) {
        if (head == null)
            return newHead;
        Node next = head.next;
        head.next = newHead;
        return reverseListInt(next, head);
    }*/

    static class Node {
        int  val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

}
