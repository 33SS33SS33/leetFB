package medium;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
/**和上道题基本类似
 只是这道题是要输出访问顺序 所以在访问的时候用list把节点记下来就行了
 注意一下当prerequisties为空的时候的情况  这个时候 就应该返回所有的course number 因为没有前置 所以也没有顺序
 stack = [x for x in xrange(numCourses) if not preNeighbor[x]] 注意一下xrange的范围要用课程数量 因为用的是defaultdict 这样才会为每一个课程数量创建一个对应的key*/
/*There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
There may be multiple correct orders, you just need to return one of them.
If it is impossible to finish all courses, return an empty array.
For example:
2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]*/
public class CourseSchedule2 {
    public static void main(String[] args) {
        int[][] prerequisites = {{1,0}};
        int[][] prerequisites2 = {{1,0},{0,1}};
        int[] res=new CourseSchedule2().findOrder(2,prerequisites);
        for(int i:res) {
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
   /* static class Vertex {

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

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Vertex[] G = new Vertex[numCourses];

        for(int[] p : prerequisites){
            safe(G, p[0]).out.add(p[1]);
            safe(G, p[1]).in.add(p[0]);
        }

        Set<Vertex> S = Arrays.stream(G)
                .filter(v -> v != null)
                .collect(Collectors.toSet());


        LinkedHashSet<Integer> order = new LinkedHashSet<Integer>(numCourses);

        loop:
        while(!S.isEmpty()){

            for(Vertex v : S){
                if(v.isSink()){
                    order.add(v.id);

                    S.remove(v);

                    for(int i : v.in){
                        G[i].out.remove(v.id);
                    }

                    continue loop;
                }
            }

            return new int[]{};
        }

        // fill courses not in G
        order.addAll(IntStream.range(0, numCourses).boxed().collect(Collectors.toSet()));

        return order.stream()
                .mapToInt(i -> i)
                .toArray();
    }*/
}
