package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/3/14.
 */

public class MinStackB {
    Node head;

    public void push(int x) {
        if (head == null) {
            head = new Node(x);
        } else {
            Node temp = new Node(x);
            temp.min = Math.min(head.min, x);
            temp.next = head;
            head = temp;
        }
    }

    public void pop() {
        if (head == null)
            return;
        head = head.next;
    }

    public int top() {
        if (head == null)
            return Integer.MAX_VALUE;

        return head.value;
    }

    public int getMin() {
        if (head == null)
            return Integer.MAX_VALUE;

        return head.min;
    }

    class Node {
        int  value;
        int  min;
        Node next;

        Node(int x) {
            value = x;
            next = null;
            min = x;
        }
    }
}