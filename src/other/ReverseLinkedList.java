package other;

/**
 * Write a function to reverse a linked list
 * Tags: LinkedList
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
        Node reversed = r.reverseListB(n1);
        Node cur = reversed;
        while (cur != null) {
            System.out.print(cur.next != null ? cur.val + "->" : cur.val);
            cur = cur.next;
        }
    }

    /**递归
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

    /**creek--Iterative 迭代
     */
    public Node reverseListB(Node head) {
        if (head == null || head.next == null)
            return head;
        Node p1 = head;
        Node p2 = head.next;
        head.next = null;
        while (p1 != null && p2 != null) {
            Node t = p2.next;
            p2.next = p1;
            p1 = p2;
            if (t != null) {
                p2 = t;
            } else {
                break;
            }
        }
        return p2;
    }

    static class Node {
        int  val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

}
