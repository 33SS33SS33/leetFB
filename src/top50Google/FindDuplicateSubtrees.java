package top50Google;

import java.util.*;

/**
 * Created by shanshan on 8/27/18.
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees,
 * you only need to return the root node of any one of them.
 * Two trees are duplicate if they have the same structure with same node values.
 */
public class FindDuplicateSubtrees {

    //https://leetcode.com/problems/find-duplicate-subtrees/discuss/106011/Java-Concise-Postorder-Traversal-Solution
    public List<TreeNode> findDuplicateSubtreesa(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        postorder(root, new HashMap<>(), res);
        return res;
    }

    public String postorder(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {
        if (cur == null) return "#";
        String serial = cur.val + "," + postorder(cur.left, map, res) + "," + postorder(cur.right, map, res);//??
        if (map.getOrDefault(serial, 0) == 1) res.add(cur);
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        return serial;
    }

    /**
     * Time Complexity: O(N)O(N), where NN is the number of nodes in the tree. We visit each node once.
     * Space Complexity: O(N)O(N). Every structure we use is using O(1)O(1) storage per node.
     */
    int t;
    Map<String, Integer> trees;
    Map<Integer, Integer> count;
    List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        t = 1;
        trees = new HashMap();
        count = new HashMap();
        ans = new ArrayList();
        lookup(root);
        return ans;
    }

    public int lookup(TreeNode node) {
        if (node == null) return 0;
        String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);
        int uid = trees.computeIfAbsent(serial, x -> t++);
        count.put(uid, count.getOrDefault(uid, 0) + 1);
        if (count.get(uid) == 2)
            ans.add(node);
        return uid;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
