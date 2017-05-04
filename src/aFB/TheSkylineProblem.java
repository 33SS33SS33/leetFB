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
        int[][] num = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        System.out.println(new TheSkylineProblem().getSkylinea(num));

    }

    public List<int[]> getSkylinea(int[][] buildings) {
        if (buildings.length == 0)
            return new LinkedList<int[]>();
        return recurSkyline(buildings, 0, buildings.length - 1);
    }

    private LinkedList<int[]> recurSkyline(int[][] buildings, int p, int q) {
        if (p < q) {
            int mid = p + (q - p) / 2;
            return merge(recurSkyline(buildings, p, mid), recurSkyline(buildings, mid + 1, q));
        } else {
            LinkedList<int[]> rs = new LinkedList<int[]>();
            rs.add(new int[] { buildings[p][0], buildings[p][2] });
            rs.add(new int[] { buildings[p][1], 0 });
            return rs;
        }
    }

    private LinkedList<int[]> merge(LinkedList<int[]> l1, LinkedList<int[]> l2) {
        LinkedList<int[]> rs = new LinkedList<int[]>();
        int h1 = 0, h2 = 0;
        while (l1.size() > 0 && l2.size() > 0) {
            int x = 0, h = 0;
            if (l1.getFirst()[0] < l2.getFirst()[0]) {
                x = l1.getFirst()[0];
                h1 = l1.getFirst()[1];
                h = Math.max(h1, h2);
                l1.removeFirst();
            } else if (l1.getFirst()[0] > l2.getFirst()[0]) {
                x = l2.getFirst()[0];
                h2 = l2.getFirst()[1];
                h = Math.max(h1, h2);
                l2.removeFirst();
            } else {
                x = l1.getFirst()[0];
                h1 = l1.getFirst()[1];
                h2 = l2.getFirst()[1];
                h = Math.max(h1, h2);
                l1.removeFirst();
                l2.removeFirst();
            }
            if (rs.size() == 0 || h != rs.getLast()[1]) {
                rs.add(new int[] { x, h });
            }
        }
        rs.addAll(l1);
        rs.addAll(l2);
        return rs;
    }

    /*    public List<int[]> getSkylineb(int[][] buildings) {
            List<int[]> result = new ArrayList<>();
            List<int[]> height = new ArrayList<>();
            for(int[] b:buildings) {
                height.add(new int[]{b[0], -b[2]});
                height.add(new int[]{b[1], b[2]});
            }
            Collections.sort(height, (a, b) -> {
                if(a[0] != b[0])
                    return a[0] - b[0];
                return a[1] - b[1];
            });
            Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
            pq.offer(0);
            int prev = 0;
            for(int[] h:height) {
                if(h[1] < 0) {
                    pq.offer(-h[1]);
                } else {
                    pq.remove(h[1]);
                }
                int cur = pq.peek();
                if(prev != cur) {
                    result.add(new int[]{h[0], cur});
                    prev = cur;
                }
            }
            return result;
        }*/
    static int li(int[] building) {
        return building[0];
    }

    static int ri(int[] building) {
        return building[1];
    }

    static int hi(int[] building) {
        return building[2];
    }

    static class SortedBuilds {
        int[][] buildings;
        int                  p        = 0;
        /**
         * 需要写PriorityQueue
         */
        //        PriorityQueue<int[]> inserted = new PriorityQueue<>((a, b) -> li(a) - li(b));
        PriorityQueue<int[]> inserted = new PriorityQueue(10, null);

        SortedBuilds(int[][] buildings) {
            this.buildings = buildings;
        }

        boolean hasNext() {
            return p < buildings.length || !inserted.isEmpty();
        }

        int[] next() {
            if (p < buildings.length && !inserted.isEmpty()) {
                if (li(buildings[p]) < li(inserted.peek())) {
                    return buildings[p++];
                } else {
                    return inserted.poll();
                }
            } else if (p < buildings.length) {
                return buildings[p++];
            } else { // !inserted.isEmpty())
                return inserted.poll();
            }
        }

        void insert(int[] building) {
            inserted.add(building);
        }
    }

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> all = new ArrayList<int[]>();
        if (buildings.length == 0)
            return all;
        SortedBuilds sortedBuilds = new SortedBuilds(buildings);
        int[] a = sortedBuilds.next();
        while (sortedBuilds.hasNext()) {
            int[] b = sortedBuilds.next();
            if (ri(a) == li(b) && hi(a) == hi(b)) {
                a = new int[] { li(a), ri(b), hi(a) };
                continue;
            }
            // a.r b.l
            if (ri(a) <= li(b)) {
                all.add(new int[] { li(a), hi(a) });
                if (ri(a) < li(b)) {
                    all.add(new int[] { ri(a), 0 });
                }
                a = b;
                continue;
            }
            // a.l b.l
            if (li(a) == li(b)) {
                // make a higher than b
                if (hi(a) < hi(b)) {
                    sortedBuilds.insert(a);
                    a = b;
                    continue;
                }
                if (ri(a) < ri(b)) {
                    sortedBuilds.insert(new int[] { ri(a), ri(b), hi(b) });
                }
                // else drop b (b inside a)
                continue;
            }
            //
            if (hi(a) < hi(b)) {
                all.add(new int[] { li(a), hi(a) });
                if (ri(a) > ri(b)) {
                    sortedBuilds.insert(new int[] { ri(b), ri(a), hi(a) });
                }
                a = b;
                continue;
            }
            // a.h >= b.h
            if (ri(a) < ri(b)) {
                sortedBuilds.insert(new int[] { ri(a), ri(b), hi(b) });
            }
            // else drop b (b inside a)
        }
        all.add(new int[] { li(a), hi(a) });
        all.add(new int[] { ri(a), 0 });
        return all;
    }
}
