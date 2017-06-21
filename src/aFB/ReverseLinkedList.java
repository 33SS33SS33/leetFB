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
        Node reversed = r.reverseListb(n1);
        Node cur = reversed;
        while (cur != null) {
            System.out.print(cur.next != null ? cur.val + "->" : cur.val);
            cur = cur.next;
        }
    }

    /**
     * 递归
     */
    //https://discuss.leetcode.com/topic/13268/in-place-iterative-and-recursive-java-solution
    public Node reverseListb(Node head) {
        return reverseListInt(head, null);
    }

    public Node reverseListInt(Node head, Node newHead) {
        if (head == null)
            return newHead;
        Node next = head.next;
        head.next = newHead;
        return reverseListInt(next, head);
    }

    /**
     * 递归
     * Recursive
     * Divide the list in 2 parts - first node and rest of the linked list
     * Call reverse for the rest of the linked list
     * Link rest to first
     * Fix head pointer
     */
    Node reverseListA(Node head) {
        if (head == null || head.next == null)
            return head;
        Node temp = reverseListA(head.next);
        head.next.next = head;
        head.next = null;
        return temp;
    }

    /**
     * 迭代
     */
    public Node reverseLista(Node head) {
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
