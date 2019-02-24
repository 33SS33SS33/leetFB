package amaoa;

import java.util.*;

/**
 * 给了一些itemAssociation, 如果一个item既在association A里面出现过，又在association B里面出现过，那么就把A和B合并成一个association。求全部合并后最大的association。
 * 如果两个association一样大，返回lexicographic order的第一个。
 * input：String[][] itemAssociation
 * return: String[].
 * example:
 * input:
 * [itemA, itemB]
 * [itemB, itemC]
 * [itemD, itemE]
 * 合并之后：
 * [itemA, itemB, itemC].,
 * [itemD, itemE].
 * 第一个有三个item最多，于是返回[itemA, itemB, itemC]
 * 解题思路参考：
 * 类似路径问题其实DFS，BFS和UnionFind都可以解决。这个因为是求最大的集合，用unionfind复杂度最好。
 * 坑：unionfind的模板是int数组，所以要把item放到一个hashmap里面产生唯一下标。根据下标做集合合并操作。合并时候注意size的计算是两个老大直接计算。输出结果需要费点劲。
 */

public class BigUnionItemAssociation1 {
    public static void main(String[] args) {
        String[][] lists = {{"1", "5"},
                {"1", "6"},
//    			            {"1","7"},
//    			            {"2","1"},
                {"2", "3"},
//    			            {"6","2"},
                {"2", "7"},
//    			            {"3","5"},
                {"3", "7"},
                {"4", "2"},
//    			            {"4","9"},
                {"9", "8"}};

        System.out.println(bigU(lists));
    }

    public static Set<String> bigU(String[][] lists) {
        Map<String, Set<String>> map = new HashMap<>();
        if (lists == null || lists.length == 0) {
            return null;
        }
        for (String[] list : lists) {
            if (list == null || list.length == 0) {
                continue;
            }
            if (!map.containsKey(list[0])) {
                map.put(list[0], new HashSet<>(Arrays.asList(list[1])));
            } else {
                map.get(list[0]).add(list[1]);
            }
            if (!map.containsKey(list[1])) {
                map.put(list[1], new HashSet<>(Arrays.asList(list[0])));
            } else {
                map.get(list[0]).add(list[1]);
            }
        }
        PriorityQueue<Set<String>> pq = new PriorityQueue<>((set1, set2) -> set2.size() - set1.size());
        for (String curr : map.keySet()) {
            boolean exist = false;
            for (Set<String> se : pq) {
                if (se.contains(curr)) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                HashSet<String> set = new HashSet<>();
                Queue<String> q = new LinkedList<>();
                q.offer(curr);
                while (!q.isEmpty()) {
                    String qcurr = q.poll();
                    if (!set.contains(qcurr)) {
                        set.add(qcurr);
                    }
                    for (String x : map.get(qcurr)) {
                        if (!set.contains(x) && !q.contains(x)) {
                            q.offer(x);
                        }
                    }
                }
                pq.add(set);
            }
        }
//    	String[] re = (String[]) pq.poll().toArray();
        return pq.poll();
    }


   /* public static String[] bigU2(String[][] items) {
        int n = items.length;
        int index = 0;
        Map<String, Integer> hash = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!hash.containsKey(items[i][0])) hash.put(items[i][0], index++);
            if (!hash.containsKey(items[i][1])) hash.put(items[i][1], index++);
        }
        UnionFind un = new UnionFind(hash.size());
        for (int i = 0; i < n; i++) {
            un.union(hash.get(items[i][0]), hash.get(items[i][1]));
        }
        int maxIndex = un.findMaxSize();
        for (Map.Entry<String, Integer> entry : hash.entrySet()) {
            String key = entry.getKey();
            Integer ind = entry.getValue();
            if (un.find(ind) == maxIndex) {
                System.out.println(key);
            }
        }
        return null;
    }

    static class UnionFind {
        int[] father = null;
        int[] size = null;

        UnionFind(int n) {
            father = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i; //father is itself
                size[i] = 1;
            }
        }

        void union(int a, int b) {
            int root_a = find(a);
            int root_b = find(b);
            if (root_a != root_b) {
                father[root_b] = root_a; //father merge
                size[root_a] += size[root_b];
            }
        }

        int find(int x) {
            if (father[x] == x) {
                return x;
            }

            return father[x] = find(father[x]);
        }

        int findMaxSize() {
            int max = 0;
            int index = 0;
            for (int i = 0; i < size.length; i++) {
                if (size[i] > max) {
                    max = size[i];
                    index = i;
                }
            }
            return index;
        }
    }*/

}
