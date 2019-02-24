package amaoa;

import java.util.*;

/**
 * 亚麻建了个新warehouse，从多个地点运东西过去，选择最近的k个点。就是k closest points，用priorityqueue。
 * 这题类似如下题目。
 * Given somepointsand a pointoriginin two dimensional space, findkpoints out of the some points which are nearest toorigin.
 * Return these points sorted by distance, if they are same with distance, sorted by x-axis, otherwise sorted by y-axis.
 * Example
 * Given points =[[4,6],[4,7],[4,4],[2,5],[1,1]], origin =[0, 0], k =3
 * return[[1,1],[2,5],[4,4]]
 * 解题思路参考：
 * 解决办法就是heap（priorityqueue），坑：要自己写comparator，注意比较距离的公式是x*x+y*y.
 */
public class KClosestWarehouse {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(1, 1));
        input.add(Arrays.asList(1, 2));
        input.add(Arrays.asList(1, 3));
        input.add(Arrays.asList(1, 4));
        input.add(Arrays.asList(1, 5));
        input.add(Arrays.asList(3, 5));
        int n = 6;
        int m = 3;
        System.out.println(topKClosestPoints(input, n, m));
    }

    public static List<List<Integer>> topKClosestPoints(List<List<Integer>> input, int n, int m) {
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(n,
                (e1, e2) -> e1.get(0) * e1.get(0) + e1.get(1) * e1.get(1) - e2.get(0) * e2.get(0) - e2.get(1) * e2.get(1));
        for (List<Integer> p : input) {
            pq.add(p);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m && i < n; i++) {
            result.add(pq.remove());
        }
        return result;
    }

    /**
     * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
     * (Here, the distance between two points on a plane is the Euclidean distance.)
     * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
     * Input: points = [[1,3],[-2,2]], K = 1
     * Output: [[-2,2]]
     */
    //the time complexity is O(NlogK),we can maintain a max-heap with size K.
    //the space complexity is O(K)
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
        for (int[] p : points) {
            pq.offer(p);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        int[][] res = new int[K][2];
        while (K > 0) {
            res[--K] = pq.poll();
        }
        return res;
    }
}
