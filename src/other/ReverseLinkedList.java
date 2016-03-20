package other;

/**
 * Write a function to reverse a linked list
 * 
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
        Node reversed = r.reverse(n1);
        Node cur = reversed;
        while (cur != null) {
            System.out.print(cur.next != null ? cur.val + "->" : cur.val);
            cur = cur.next;
        }
    }
    
    /**
     * Recursive
     * Divide the list in 2 parts - first node and rest of the linked list
     * Call reverse for the rest of the linked list
     * Link rest to first
     * Fix head pointer
     */
    Node reverse(Node head) {
        if (head == null || head.next == null) return head;
        Node temp = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return temp;
    }

    /**creek--Iterative-*/
    public ListNode reverseListB(ListNode head) {
        if(head==null || head.next == null)
            return head;

        ListNode p1 = head;
        ListNode p2 = head.next;

        head.next = null;
        while(p1!= null && p2!= null){
            ListNode t = p2.next;
            p2.next = p1;
            p1 = p2;
            if (t!=null){
                p2 = t;
            }else{
                break;
            }
        }
        return p2;
    }

    static class Node {
        int val;
        Node next;
        
        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
}
