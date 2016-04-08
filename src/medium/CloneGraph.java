package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 */

import java.util.*;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a
 * list of its neighbors.
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 *
 * We use # as a separator for each node, and , as a separator for node label
 * and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 *
 * The graph has a total of three nodes, and therefore contains three parts as
 * separated by #.
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming
 * a self-cycle.
 * Visually, the graph looks like the following:
 * <p/>
 * 1
 * / \
 * /   \
 * 0 --- 2
 * / \
 * \_/
 * <p/>
 * Tags: DFS, BFS, Graph
 */
class CloneGraph {
    public static void main(String[] args) {

    }

    /*这几种方法的时间复杂度都是O(n)（每个结点访问一次），
    而空间复杂度则是栈或者队列的大小加上HashMap的大小，也不会超过O(n)。*/
    /**------creek-------*/
    public UndirectedGraphNode cloneGraphB(UndirectedGraphNode node) {
        if (node == null)
            return null;
        LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        UndirectedGraphNode newHead = new UndirectedGraphNode(node.label);
        queue.add(node);
        map.put(node, newHead);
        while (!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.pop();
            List<UndirectedGraphNode> currNeighbors = curr.neighbors;
            for (UndirectedGraphNode aNeighbor : currNeighbors) {
                if (!map.containsKey(aNeighbor)) {
                    UndirectedGraphNode copy = new UndirectedGraphNode(aNeighbor.label);
                    map.put(aNeighbor, copy);
                    map.get(curr).neighbors.add(copy);
                    queue.add(aNeighbor);
                } else {
                    map.get(curr).neighbors.add(map.get(aNeighbor));
                }
            }
        }
        return newHead;
    }

    /**
     * BFS, O(n) Time, O(n) Space 广度优先
     * Use map<Integer, UndirectedGraphNode> to represent graph
     * Get node from queue
     * Check whether in graph already
     * Add neighbors to queue
     */
    public UndirectedGraphNode cloneGraphA(UndirectedGraphNode node) {
        if (node == null)
            return null;
        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        Map<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
        q.add(node);
        while (!q.isEmpty()) { // BFS
            UndirectedGraphNode cur = q.poll();
            if (!map.containsKey(cur.label))
                map.put(cur.label, new UndirectedGraphNode(cur.label)); // put in map to set visited
            if (cur.neighbors != null) {
                for (UndirectedGraphNode n : cur.neighbors) {
                    if (!map.containsKey(n.label)) {
                        q.add(n);
                        map.put(n.label, new UndirectedGraphNode(n.label));
                    }
                    // add to neighbors
                    map.get(cur.label).neighbors.add(map.get(n.label));
                }
            }
        }
        return map.get(node.label);
    }

    public UndirectedGraphNode cloneGraphC(UndirectedGraphNode node) {
        if (node == null)
            return null;
        // visit
        HashMap<UndirectedGraphNode, UndirectedGraphNode> clone = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        Deque<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.add(node);
        while (queue.size() > 0) {
            UndirectedGraphNode n = queue.poll();
            if (!clone.containsKey(n)) {
                clone.put(n, new UndirectedGraphNode(n.label));
                for (UndirectedGraphNode neighbor : n.neighbors) {
                    queue.add(neighbor);
                }
            }
        }
        queue.add(node);
        HashSet<UndirectedGraphNode> visit = new HashSet<UndirectedGraphNode>();
        while (queue.size() > 0) {
            UndirectedGraphNode n = queue.poll();
            if (!visit.contains(n)) {
                visit.add(n);
                UndirectedGraphNode c = clone.get(n);
                for (UndirectedGraphNode neighbor : n.neighbors) {
                    c.neighbors.add(clone.get(neighbor));
                    queue.add(neighbor);
                }
            }
        }
        return clone.get(node);
    }

    /*深度优先 非递归*/
    public UndirectedGraphNode cloneGraphD(UndirectedGraphNode node) {
        if (node == null)
            return null;
        LinkedList<UndirectedGraphNode> stack = new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        stack.push(node);
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        map.put(node, copy);
        while (!stack.isEmpty()) {
            UndirectedGraphNode cur = stack.pop();
            for (int i = 0; i < cur.neighbors.size(); i++) {
                if (!map.containsKey(cur.neighbors.get(i))) {
                    copy = new UndirectedGraphNode(cur.neighbors.get(i).label);
                    map.put(cur.neighbors.get(i), copy);
                    stack.push(cur.neighbors.get(i));
                }
                map.get(cur).neighbors.add(map.get(cur.neighbors.get(i)));
            }
        }
        return map.get(node);
    }

    /*深度优先 递归实现*/
    public UndirectedGraphNode cloneGraphD2(UndirectedGraphNode node) {
        if (node == null)
            return null;
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        map.put(node, copy);
        helper(node, map);
        return copy;
    }
    private void helper(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> map) {
        for (int i = 0; i < node.neighbors.size(); i++) {
            UndirectedGraphNode cur = node.neighbors.get(i);
            if (!map.containsKey(cur)) {
                UndirectedGraphNode copy = new UndirectedGraphNode(cur.label);
                map.put(cur, copy);
                helper(cur, map);
            }
            map.get(node).neighbors.add(map.get(cur));
        }
    }

    class UndirectedGraphNode {
        int                       label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }
}

