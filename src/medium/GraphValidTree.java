package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/5/7.
 */
/**Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
 For example:
 Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], returntrue.
 Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], returnfalse.
 */

/**没有cycle的图就是树*/
public class GraphValidTree {
    public static void main(String[] args) {
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 4 } };
        int[][] edges2 = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 1, 3 }, { 1, 4 } };
        System.out.println(graphValidTree(5, edges));
        System.out.println(graphValidTree(5, edges2));
    }

    public static boolean graphValidTree(int n, int[][] edges) {
        int[] root = new int[n];
        for (int i = 0; i < n; i++)
            root[i] = i;
        for (int i = 0; i < edges.length; i++) {
            int root1 = find(root, edges[i][0]);
            int root2 = find(root, edges[i][1]);
            if (root1 == root2)
                return false;
            root[root2] = root1;
        }
        return edges.length == n - 1;
    }

    private static int find(int[] root, int e) {
        if (root[e] == e)
            return e;
        else
            return find(root, root[e]);
    }
}
