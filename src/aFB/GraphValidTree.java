package aFB;

import java.util.Arrays;

/**
 * Created by GAOSHANSHAN835 on 2016/5/7.
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
 * For example:
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 * 树的特点有两个 一个是节点是全连同的 另一个是是一个没有环的图
 * 这里有一个简便方法就是检查edge的数量必须是n-1(对于无向图来说) 这样才有可能是一个树
 * 这道题使用的dfs来检测 整体思路和之前的都类似 需要记录访问过的点 然后把要访问的点不停的入栈 每次弹出栈顶的一个点  然后继续入栈他的邻居
 * 有必要总结一些leetcode里的图的题 总结一下bfs dfs 拓扑排序
 * 没有cycle的图就是树
 */
public class GraphValidTree {
    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        System.out.println(validTree(5, edges));
        System.out.println(validTree(5, edges2));
    }

    /**
     * 啊啊
     */
     //http://www.geeksforgeeks.org/union-find/
    //https://discuss.leetcode.com/topic/21712/ac-java-union-find-solution/6
    public static boolean validTree(int n, int[][] edges) {
        // initialize n isolated islands
        int[] nums = new int[n];
        Arrays.fill(nums, -1);
        // perform union find
        for (int i = 0; i < edges.length; i++) {
            int x = finda(nums, edges[i][0]);
            int y = finda(nums, edges[i][1]);
            // if two vertices happen to be in the same set then there's a cycle
            if (x == y)
                return false;
            // union  ？？
            nums[x] = y;
        }
        return edges.length == n - 1;
    }

    static int finda(int nums[], int i) {
        if (nums[i] == -1)
            return i;
        return finda(nums, nums[i]);
    }

}
