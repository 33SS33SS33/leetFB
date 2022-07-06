package top50Google;

import aFB.TreeNode;

public class StepByStepDirectionsFromaBinaryTreeNodetoAnother {
    /**
     * You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.
     * Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
     * 'L' means to go from a node to its left child node.
     * 'R' means to go from a node to its right child node.
     * 'U' means to go from a node to its parent node.
     * Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
     * Output: "UURL"
     * Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
     */
    private boolean find(TreeNode n, int val, StringBuilder sb) {
        if (n.val == val)
            return true;
        if (n.left != null && find(n.left, val, sb))
            sb.append("L");
        else if (n.right != null && find(n.right, val, sb))
            sb.append("R");
        return sb.length() > 0;
    }

    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder s = new StringBuilder(), d = new StringBuilder();
        find(root, startValue, s);
        find(root, destValue, d);
        int i = 0, max_i = Math.min(d.length(), s.length());
        while (i < max_i && s.charAt(s.length() - i - 1) == d.charAt(d.length() - i - 1))
            ++i;
        return "U".repeat(s.length() - i) + d.reverse().toString().substring(i);
    }
}
