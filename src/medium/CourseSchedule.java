package medium;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * For example:
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0,
 * and to take course 0 you should also have finished course 1. So it is impossible.
 * 这题需要用到拓扑排序
 * 可以用改进的DFS 每次dfs另外传入一个变量 来记录当前的路径 当某个点发现自己已经在路径上了 就说明有环 就返回False
 * 这里要注意 要在递归退出来之后 再将当前的点标记为searched 要不就无法前进到有环的那个点
 * 没有实现DFS
 * 实现了BFS的拓扑排序
 * 需要用两个数组记录入度和出度 入度是数字 出度要记录有哪些节点
 * 先遍历prerequisites建立每个course的入度还有出度
 * 然后建立一个queue用来记录入度为0的course 先循环一次存入当前入度为0的course
 * 然后开始循环 从queue中弹出一个元素 然后将这个元素出度上的元素的入度都减1 如果入度减完变成0了就入队 直到找不到入度为0的节点
 * 如果弹出所有点得数量不等于总数 就说明有环
 * https://leetcode.com/discuss/61618/ac-python-topological-sort-52-ms-solution-o-time-and-v-e-space 
 * https://leetcode.com/discuss/37781/60ms-concise-python-solution-using-topological-sort
 * http://songlee24.github.io/2015/05/07/topological-sorting/
 * 有环就返回false
 */
public class CourseSchedule {
    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}};
        int[][] prerequisites2 = {{1, 0}, {0, 1}};

        System.out.println(new CourseSchedule().canFinisha(2, prerequisites));
        System.out.println(new CourseSchedule().canFinisha(2, prerequisites2));
    }

    /**
     * 最好的 BFS Topological sort
     */
    public boolean canFinisha(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses]; // i -> j
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int ready = prerequisites[i][0];
            int pre = prerequisites[i][1];
            if (matrix[pre][ready] == 0)
                indegree[ready]++; //duplicate case
            matrix[pre][ready] = 1;
        }
        int count = 0;
        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i = 0; i < numCourses; i++) {
                if (matrix[course][i] != 0) {
                    if (--indegree[i] == 0)
                        queue.offer(i);
                }
            }
        }
        return count == numCourses;
    }

}
