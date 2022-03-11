package aMaz;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by shanshan on 16/5/9.
 * "用先序遍历 然后再重新构造就可以
 * 应该也可以按层遍历 然后bfs构造 未实现"
 * "Serialization is the process of converting a data structure or object into a sequence of bits
 * so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serializeBT and deserializeBT a binary tree. There is no restriction on
 * how your serialization/deserialization algorithm should aawork.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * For example, you may serializeBT the following tree
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format,
 * so please be creative and come up with different approaches yourself.
 * Note: Do not use class member/global/static variables to store states. Your serializeBT and deserializeBT algorithms should be stateless."
 */
public class SerializeandDeserializeBT {
    public static void main(String[] args) {
        TreeNode head = buildTree();
        System.out.println(new SerializeandDeserializeBT().serializeBT(head));
        System.out.println(new SerializeandDeserializeBT().deserializeBT("1,2,X,X,3,4,X,X,5,X,X"));
    }

    /**
     * The idea is simple: print the tree in pre-order traversal and use "X" to denote null node and split node with ",".
     * We can use a StringBuilder for building the string on the fly. For deserializing,
     * we use a Queue to store the pre-order traversal and since we have "X" as null node.we know exactly how to where to end building subtress.
     */
    private static final String spliter = ",";
    private static final String NN = "X";

    public String serializeBT(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    public TreeNode deserializeBT(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals(NN))
            return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
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
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
