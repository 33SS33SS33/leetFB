package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/4/14.
 */
public class ConvertBTtoLinkedList {
    public static void main(String[] args) {
        ConvertBTtoLinkedList tree = new ConvertBTtoLinkedList();
        // Let us create the tree shown in above diagram
        tree.root = new Node(10);
        tree.root.left = new Node(12);
        tree.root.right = new Node(15);
        tree.root.left.left = new Node(25);
        tree.root.left.right = new Node(30);
        tree.root.right.left = new Node(36);
        // Convert to DLL
        Node head = tree.bintree2list(root);
        // Print the converted list
        tree.printList(head);
    }

    static Node root;

    /* This is the core function to convert Tree to list. This function follows
     steps 1 and 2 of the above algorithm */
    Node bintree2listUtil(Node node) {
        // Base case
        if (node == null) {
            return node;
        }
        // Convert the left subtree and link to root
        if (node.left != null) {
            // Convert the left subtree
            Node left = bintree2listUtil(node.left);
            // Find inorder predecessor. After this loop, left
            // will point to the inorder predecessor
            for (; left.right != null; left = left.right)
                ;
            // Make root as next of the predecessor
            left.right = node;
            // Make predecssor as previous of root
            node.left = left;
        }
        // Convert the right subtree and link to root
        if (node.right != null) {
            // Convert the right subtree
            Node right = bintree2listUtil(node.right);
            // Find inorder successor. After this loop, right
            // will point to the inorder successor
            for (; right.left != null; right = right.left)
                ;
            // Make root as previous of successor
            right.left = node;
            // Make successor as next of root
            node.right = right;
        }
        return node;
    }

    Node bintree2list(Node node) {
        // Base case
        if (node == null) {
            return node;
        }
        node = bintree2listUtil(node);
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.right;
        }
    }

    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

}
