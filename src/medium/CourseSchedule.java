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
 * <p/>
 * 这题需要用到拓扑排序
 * 可以用改进的DFS 每次dfs另外传入一个变量 来记录当前的路径 当某个点发现自己已经在路径上了 就说明有环 就返回False 这里要注意 要在递归退出来之后 再将当前的点标记为searched 要不就无法前进到有环的那个点
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
        int[][] prerequisites = { { 1, 0 } };
        int[][] prerequisites2 = { { 1, 0 }, { 0, 1 } };

        System.out.println(new CourseSchedule().canFinisha(2, prerequisites));
        System.out.println(new CourseSchedule().canFinishA(2, prerequisites));
        System.out.println(new CourseSchedule().canFinishB(2, prerequisites));
        System.out.println(new CourseSchedule().canFinisha(2, prerequisites2));
        System.out.println(new CourseSchedule().canFinishA(2, prerequisites2));
        System.out.println(new CourseSchedule().canFinishB(2, prerequisites2));
    }

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

    /**
     * creek BFS------
     */
    public boolean canFinishA(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            throw new IllegalArgumentException("illegal prerequisites array");
        }
        int len = prerequisites.length;
        if (numCourses == 0 || len == 0) {
            return true;
        }
        // counter for number of prerequisites
        int[] pCounter = new int[numCourses];
        for (int i = 0; i < len; i++) {
            pCounter[prerequisites[i][0]]++;
        }
        //store courses that have no prerequisites
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (pCounter[i] == 0) {
                queue.add(i);
            }
        }
        // number of courses that have no prerequisites
        int numNoPre = queue.size();
        while (!queue.isEmpty()) {
            int top = queue.remove();
            for (int i = 0; i < len; i++) {
                // if a course's prerequisite can be satisfied by a course in queue
                if (prerequisites[i][1] == top) {
                    pCounter[prerequisites[i][0]]--;
                    if (pCounter[prerequisites[i][0]] == 0) {
                        numNoPre++;
                        queue.add(prerequisites[i][0]);
                    }
                }
            }
        }
        return numNoPre == numCourses;
    }

    /**
     * creek DFS----------
     */
    public boolean canFinishB(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            throw new IllegalArgumentException("illegal prerequisites array");
        }
        int len = prerequisites.length;
        if (numCourses == 0 || len == 0) {
            return true;
        }
        //track visited courses
        int[] visit = new int[numCourses];
        // use the map to store what courses depend on a course
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        for (int[] a : prerequisites) {
            if (map.containsKey(a[1])) {
                map.get(a[1]).add(a[0]);
            } else {
                ArrayList<Integer> l = new ArrayList<Integer>();
                l.add(a[0]);
                map.put(a[1], l);
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (!canFinishDFS(map, visit, i))
                return false;
        }
        return true;
    }

    private boolean canFinishDFS(HashMap<Integer, ArrayList<Integer>> map, int[] visit, int i) {
        if (visit[i] == -1)
            return false;
        if (visit[i] == 1)
            return true;
        visit[i] = -1;
        if (map.containsKey(i)) {
            for (int j : map.get(i)) {
                if (!canFinishDFS(map, visit, j))
                    return false;
            }
        }
        visit[i] = 1;
        return true;
    }

    /*static class Vertex {
        int id;
        Vertex(int id){
            this.id = id;
        }

        Set<Integer> in  = new HashSet<Integer>();
        Set<Integer> out = new HashSet<Integer>();

        boolean isSink(){
            return out.isEmpty();
        }
    }

    Vertex safe(Vertex[] G, int id){
        if(G[id] == null){
            G[id] = new Vertex(id);
        }

        return G[id];
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        Vertex[] G = new Vertex[numCourses];

        for(int[] p : prerequisites){
            safe(G, p[0]).out.add(p[1]);
            safe(G, p[1]).in.add(p[0]);
        }

        Set<Vertex> S = Arrays.stream(G)
                .filter(v -> v != null)
                .collect(Collectors.toSet());
        loop:
        while(!S.isEmpty()){
            for(Vertex v : S){
                if(v.isSink()){
                    S.remove(v);

                    for(int i : v.in){
                        G[i].out.remove(v.id);
                    }
                    continue loop;
                }
            }
            return false;
        }
        return true;
    }*/
}
