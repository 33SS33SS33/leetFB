package aFB;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 * 分别将每个线段的左边节点与右边节点存到新的vector height中，根据x坐标值排序，然后遍历求拐点。
 * 求拐点的时候用一个最大化heap来保存当前的楼顶高度，遇到左边节点，就在heap中插入高度信息，
 * 遇到右边节点就从heap中删除高度。分别用pre与cur来表示之前的高度与当前的高度，当cur != pre的时候说明出现了拐点。
 * 在从heap中删除元素时要注意，我使用priority_queue来实现，priority_queue并不提供删除的操作，
 * 所以又用了别外一个unordered_map来标记要删除的元素。在从heap中pop的时候先看有没有被标记过，如果标记过，
 * 就一直pop直到空或都找到没被标记过的值。别外在排序的时候要注意，如果两个节点的x坐标相同，
 * 我们就要考虑节点的其它属性来排序以避免出现冗余的答案。且体的规则就是如果都是左节点，就按y坐标从大到小排，
 * 如果都是右节点，按y坐标从小到大排，一个左节点一个右节点，就让左节点在前。
 * 第一个while就是先把左边坐标比当前坐标小的楼都加入堆里  第二个while是如果最高的楼已经要被当前位置pass了  就踢出去
 */
public class TheSkylineProblem {
    public static void main(String[] args) {
        int[][] num = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        System.out.println(new TheSkylineProblem().theSkylineProblem(num));
    }

    //最好的 不懂啊～～！！
    //https://discuss.leetcode.com/topic/22482/short-java-solution/4
    public static List<int[]> theSkylineProblem(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for (int[] b : buildings) {
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }
        Collections.sort(height, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);
        int prev = 0;
        for (int[] h : height) {
            if (h[1] < 0) {
                pq.offer(-h[1]);
            } else {
                pq.remove(h[1]);
            }
            int cur = pq.peek();
            if (prev != cur) {
                result.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return result;
    }

}
