package other;

/**
 * Given only a pointer to a node to be deleted in a singly linked list, how do
 * you deleteNodeinaLinkedList it?
 * Tags: LinkedList
 * 其实是把后面的点复制过来 然后把后面的点就删了
 */
class DeleteNodeinaLinkedList {
    public static void main(String[] args) {

    }

    /**
     * Copy the data from the next node to the node to be deleted and deleteNodeinaLinkedList
     * the next node
     */
    void deleteNodeinaLinkedList(Node n) {
        if (n.next == null)
            n = null;
        Node temp = n.next;
        n.val = temp.val;
        n.next = temp.next;
        temp = null;
    }

    public void deleteNode(Node node) {
        if (node != null && node.next != null) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    class Node {
        int val;
        Node next;
    }

}