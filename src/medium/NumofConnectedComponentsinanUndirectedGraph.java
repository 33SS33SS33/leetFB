package medium;

import java.util.HashSet;
import java.util.Set;

/**
 * "Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to find the number of connected components in an undirected graph.
 * Example 1:
 * 0          3
 * |          |
 * 1 --- 2    4
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
 * Example 2:
 * 0           4
 * |           |
 * 1 --- 2 --- 3
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
 * Note:
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected,
 * [0, 1] is the same as [1, 0] and thus will not appear together in edges."
 * <p>
 * 可以用 union find  先用了dfs 注意一下图里有环的情况 还有对于图的初始化 很可能edge里会有漏掉的点 就是那个点是一个单独的店 所以初始化用了 0-n-1
 */
public class NumofConnectedComponentsinanUndirectedGraph {

    private int[] father;

    public int countComponentsa(int n, int[][] edges) {

        Set<Integer> set = new HashSet<Integer>();
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            union(edges[i][0], edges[i][1]);
        }

        for (int i = 0; i < n; i++) {
            set.add(find(i));
        }
        return set.size();
    }

    int find(int node) {
        if (father[node] == node) {
            return node;
        }
        father[node] = find(father[node]);
        return father[node];
    }

    void union(int node1, int node2) {
        father[find(node1)] = find(node2);
    }

    public int countComponents(int n, int[][] edges) {
        int[] roots = new int[n];
        for (int i = 0; i < n; i++) roots[i] = i;

        for (int[] e : edges) {
            int root1 = find(roots, e[0]);
            int root2 = find(roots, e[1]);
            if (root1 != root2) {
                roots[root1] = root2;  // union
                n--;
            }
        }
        return n;
    }

    public int find(int[] roots, int id) {
        while (roots[id] != id) {
            roots[id] = roots[roots[id]];  // optional: path compression
            id = roots[id];
        }
        return id;
    }
}
