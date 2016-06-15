package tree;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/4/8.
 * For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a
 * rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs).
 * Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 * Format The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of
 * undirected edges (each edge is a pair of labels).
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as
 * [1, 0] and thus will not appear together in edges.
 **/
public class MinimumHeightTrees {
    public static void main(String[] args) {
        MinimumHeightTrees mht = new MinimumHeightTrees();
        List<Integer> res = mht.findMinHeightTreesa(6,
                new int[][] { { 0, 3 }, { 1, 3 }, { 2, 3 }, { 4, 3 }, { 5, 4 } });
        System.out.println(res.toString());
    }

    /**best*/
    public List<Integer> findMinHeightTreesa(int n, int[][] edges) {
        if (n == 1)
            return Collections.singletonList(0);
        List<Set<Integer>> adj = new ArrayList<Set<Integer>>();
        for (int i = 0; i < n; ++i)
            adj.add(new HashSet<Integer>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        List<Integer> leaves = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i)
            if (adj.get(i).size() == 1)
                leaves.add(i);
        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<Integer>();
            for (int i : leaves) {
                int j = adj.get(i).iterator().next();
                adj.get(j).remove(i);
                if (adj.get(j).size() == 1)
                    newLeaves.add(j);
            }
            leaves = newLeaves;
        }
        return leaves;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1)
            return Collections.singletonList(0);
        if (n == 2)
            return Arrays.asList(0, 1);
        // build graph
        List<Set<Integer>> adj = new ArrayList<Set<Integer>>(n);
        for (int i = 0; i < n; i++)
            adj.add(new HashSet<Integer>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        // find leaves
        LinkedList<Integer> leaves = new LinkedList<Integer>(); // better memory usage
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() == 1)
                leaves.offer(i);
        }
        while (n > 2) {
            int numLeaf = leaves.size();
            n -= numLeaf;
            for (int i = 0; i < numLeaf; i++) {
                // update graph
                int curNode = leaves.poll();
                int j = adj.get(curNode).iterator().next();
                adj.get(j).remove(curNode);
                if (adj.get(j).size() == 1)
                    leaves.offer(j); // new leaves
            }
        }
        return leaves;
    }
}
