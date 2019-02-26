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

    //这个size是point的size
    public static List<List<Integer>> topKClosestPoints(List<List<Integer>> input, int size, int K) {
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(size,
                (e1, e2) -> {
                    int diff = e1.get(0) * e1.get(0) + e1.get(1) * e1.get(1) - e2.get(0) * e2.get(0) - e2.get(1) * e2.get(1);
                    if (diff == 0)
                        diff = e1.get(0) - e2.get(0);
                    if (diff == 0)
                        diff = e1.get(1) - e2.get(1);
                    return diff;
                });
        pq.addAll(input);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < K && i < size; i++) {
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
     *
     */

    /**
     * we can maintain a max-heap with size K. Then for each point, we add it to the heap.
     * Once the size of the heap is greater than K, we pull one from the heap to ensure the size of the heap is always K.
     * Thus, the max heap is always maintain top K smallest elements from the first one to current one.
     * Once the size of the heap is over its maximum capacity, it will exclude the maximum element in it
     */
    //the time complexity is O(NlogK),we can maintain a max-heap with size K.
    //the space complexity is O(K)
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> {
            int diff = p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1];
            if (diff == 0)
                diff = p2[0] - p1[0];
            if (diff == 0)
                diff = p2[1] - p1[1];
            return diff;
        });
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
