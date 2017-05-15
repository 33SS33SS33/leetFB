package aFB;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * 和上道题基本类似
 * 只是这道题是要输出访问顺序 所以在访问的时候用list把节点记下来就行了
 * 注意一下当prerequisties为空的时候的情况  这个时候 就应该返回所有的course number 因为没有前置 所以也没有顺序
 * stack = [x for x in xrange(numCourses) if not preNeighbor[x]] 注意一下xrange的范围要用课程数量 因为用的是defaultdict
 * 这样才会为每一个课程数量创建一个对应的key
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 * There may be multiple correct orders, you just need to return one of them.
 * If it is impossible to finish all courses, return an empty array.
 * For example:
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
 * Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
 */
public class CourseSchedule2 {
    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}};
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        int[] res = new CourseSchedule2().findOrder(2, prerequisites);
        for (int i : res) {
            System.out.println(i);
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            throw new IllegalArgumentException("illegal prerequisites array");
        }
        int len = prerequisites.length;
        //if there is no prerequisites, return a sequence of courses
        if (len == 0) {
            int[] res = new int[numCourses];
            for (int m = 0; m < numCourses; m++) {
                res[m] = m;
            }
            return res;
        }
        //records the number of prerequisites each course (0,...,numCourses-1) requires
        int[] pCounter = new int[numCourses];
        for (int i = 0; i < len; i++) {
            pCounter[prerequisites[i][0]]++;
        }
        //stores courses that have no prerequisites
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (pCounter[i] == 0) {
                queue.add(i);
            }
        }
        int numNoPre = queue.size();
        //initialize result
        int[] result = new int[numCourses];
        int j = 0;

        while (!queue.isEmpty()) {
            int c = queue.remove();
            result[j++] = c;
            for (int i = 0; i < len; i++) {
                if (prerequisites[i][1] == c) {
                    pCounter[prerequisites[i][0]]--;
                    if (pCounter[prerequisites[i][0]] == 0) {
                        queue.add(prerequisites[i][0]);
                        numNoPre++;
                    }
                }
            }
        }
        //return result
        if (numNoPre == numCourses) {
            return result;
        } else {
            return new int[0];
        }
    }

    /**
     * 最好的
     */
    //https://leetcode.com/discuss/35605/two-ac-solution-in-java-using-bfs-and-dfs-with-explanation
    public int[] findOrderB(int numCourses, int[][] prerequisites) {
        int[] incLinkCounts = new int[numCourses];
        List<List<Integer>> adjs = new ArrayList<List<Integer>>(numCourses);
        initialiseGraph(incLinkCounts, adjs, prerequisites);
        //return solveByBFS(incLinkCounts, adjs);
        return solveByDFS(adjs);
    }

    private void initialiseGraph(int[] incLinkCounts, List<List<Integer>> adjs,
                                 int[][] prerequisites) {
        int n = incLinkCounts.length;
        while (n-- > 0)
            adjs.add(new ArrayList<Integer>());
        for (int[] edge : prerequisites) {
            incLinkCounts[edge[0]]++;
            adjs.get(edge[1]).add(edge[0]);
        }
    }

    private int[] solveByBFS(int[] incLinkCounts, List<List<Integer>> adjs) {
        int[] order = new int[incLinkCounts.length];
        Queue<Integer> toVisit = new ArrayDeque<Integer>();
        for (int i = 0; i < incLinkCounts.length; i++) {
            if (incLinkCounts[i] == 0)
                toVisit.offer(i);
        }
        int visited = 0;
        while (!toVisit.isEmpty()) {
            int from = toVisit.poll();
            order[visited++] = from;
            for (int to : adjs.get(from)) {
                incLinkCounts[to]--;
                if (incLinkCounts[to] == 0)
                    toVisit.offer(to);
            }
        }
        return visited == incLinkCounts.length ? order : new int[0];
    }

    private int[] solveByDFS(List<List<Integer>> adjs) {
        BitSet hasCycle = new BitSet(1);
        BitSet visited = new BitSet(adjs.size());
        BitSet onStack = new BitSet(adjs.size());
        Deque<Integer> order = new ArrayDeque<Integer>();
        for (int i = adjs.size() - 1; i >= 0; i--) {
            if (visited.get(i) == false && hasOrder(i, adjs, visited, onStack, order) == false)
                return new int[0];
        }
        int[] orderArray = new int[adjs.size()];
        for (int i = 0; !order.isEmpty(); i++)
            orderArray[i] = order.pop();
        return orderArray;
    }

    private boolean hasOrder(int from, List<List<Integer>> adjs, BitSet visited, BitSet onStack,
                             Deque<Integer> order) {
        visited.set(from);
        onStack.set(from);
        for (int to : adjs.get(from)) {
            if (visited.get(to) == false) {
                if (hasOrder(to, adjs, visited, onStack, order) == false)
                    return false;
            } else if (onStack.get(to) == true) {
                return false;
            }
        }
        onStack.clear(from);
        order.push(from);
        return true;
    }

}
