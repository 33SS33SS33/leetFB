package aFB;

/**
 * Write a function to reverse a linked list
 * Tags: LinkedList
 * 递归的方法应该看一看
 * 都写了
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
        Node reversed = r.reverseLinkedListA(n1);
        Node cur = reversed;
        while (cur != null) {
            System.out.print(cur.next != null ? cur.val + "->" : cur.val);
            cur = cur.next;
        }
    }

    /**
     * 递归
     * Recursive
     * Divide the list in 2 parts - first node and rest of the linked list
     * Call reverse for the rest of the linked list
     * Link rest to first
     * Fix head pointer
     */
    Node reverseLinkedListA(Node head) {
        if (head == null || head.next == null)
            return head;
        Node temp = reverseLinkedListA(head.next);
        head.next.next = head;
        head.next = null;
        return temp;
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

    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

}
