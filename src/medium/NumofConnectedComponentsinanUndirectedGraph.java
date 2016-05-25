package medium;

/**
 * "Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
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
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges."
 */

/**可以用 union find  先用了dfs 注意一下图里有环的情况 还有对于图的初始化 很可能edge里会有漏掉的点 就是那个点是一个单独的店 所以初始化用了 0-n-1	*/
public class NumofConnectedComponentsinanUndirectedGraph {
}
