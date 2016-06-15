package hard;

/**
 * Created by shanshan on 16/5/9.
 * "用先序遍历 然后再重新构造就可以
 * 应该也可以按层遍历 然后bfs构造 未实现"
 * "Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * For example, you may serialize the following tree
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format,
 * so please be creative and come up with different approaches yourself.
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless."
 */
public class SerializeandDeserializeBT {
    public static void main(String[] args) {
        TreeNode head = buildTree();
        System.out.println(new SerializeandDeserializeBT().serialize(head));
        System.out.println(new SerializeandDeserializeBT()
                .deserialize("1,2,null,null,3,4,null,null,5,null,null"));
    }

    /*错的*/
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helperS(root, sb);
        return sb.toString();
    }

    private void helperS(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null").append(",");
            return;
        }
        sb.append(node.val).append(",");
        helperS(node.left, sb);
        helperS(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] vals = data.split("[,]");
        int[] index = new int[] { 0 };
        return helperD(vals, index);
    }

    private TreeNode helperD(String[] vals, int[] index) {
        if (index[0] == vals.length) {
            return null;
        }

        String visiting = vals[index[0]++];
        if (visiting.equals("null")) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.valueOf(visiting));
        node.left = helperD(vals, index);
        node.right = helperD(vals, index);

        return node;
    }

    private static TreeNode buildTree() {
        TreeNode t0 = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(5);

        t0.left = t1;
        t0.right = t2;
        t2.left = t3;
        t2.right = t4;

        return t0;
    }

    public static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
