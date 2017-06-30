package other;

/**
 * Given only a pointer to a node to be deleted in a singly linked list, how do
 * you delete it?
 * Tags: LinkedList
 * 其实是把后面的点复制过来 然后把后面的点就删了
 */
class DeleteNodeinaLinkedList {
    public static void main(String[] args) {

    }

    public void deleteNode(Node node) {
        if(node != null && node.next != null) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

    /**
     * Copy the data from the next node to the node to be deleted and delete
     * the next node
     */
    void delete(Node n) {
        if (n.next == null)
            n = null;
        Node temp = n.next;
        n.val = temp.val;
        n.next = temp.next;
        temp = null;
    }

    class Node {
        int  val;
        Node next;
    }

}