package tree;

/**
 * Given a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 * <p/>
 * Tags: DFS, Linked list
 */
class ConvertSortedListToBST {

    public static void main(String[] args) {

    }

    private static ListNode cur;
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        cur = head;
        return buildTree(lengthOfList(head));
        /**Solution 2*/
//        return sortedListToBSTB(0, lengthOfList(head) - 1);
    }

    public static int lengthOfList(ListNode node) {
        int length = 0;
        while (node != null) {
            node = node.next;
            length++;
        }
        return length;
    }

    /**
     * Bottom up approach, O(n) (Instead of top-down, O(nlogn))
     * STEP 1: Take left n/2 nodes and recursively construct the left sub tree.
     * STEP 2: After left sub tree is constructed, we allocate memory for root
     * and link the left sub tree with root.
     * STEP 3: Finally, we recursively construct the right sub tree and link it
     * with root.
     */
    public TreeNode buildTree(int n) {
        if (n == 0)
            return null;
        TreeNode node = new TreeNode(0);
        node.left = buildTree(n / 2);
        node.val = cur.val;
        cur = cur.next;
        node.right = buildTree(n - n / 2
                - 1); // why n - n/2 - 1? The # of nodes in right subtreeis total nodes - nodes in left subtree - root
        return node;
    }


    // build tree bottom-up
    public TreeNode sortedListToBSTB(int start, int end) {
        if (start > end)
            return null;
        // mid
        int mid = (start + end) / 2;
        TreeNode left = sortedListToBSTB(start, mid - 1);
        TreeNode root = new TreeNode(cur.val);
        cur = cur.next;
        TreeNode right = sortedListToBSTB(mid + 1, end);

        root.left = left;
        root.right = right;
        return root;
    }


    class ListNode {
        int      val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}