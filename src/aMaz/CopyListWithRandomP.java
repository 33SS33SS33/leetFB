package aMaz;

import java.util.*;

/**
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 * Tags: Hashtable, Linkedlist
 * 难点在于如何知道一个点是不是在之前就已经被copy 比如先通过随机的指针建立了节点3
 * 当通过next访问到3的时候如何知道3是之前创建过的
 * 只需要用一个hashtable跟踪一下就知道了
 * 右边的解法用了defaultdict 十分简单 重要
 */
class CopyListWithRandomP {
    public static void main(String[] args) {
        RandomListNode node = buildList();
        RandomListNode res = new CopyListWithRandomP().CopyListWithRandom(node);
        while (res != null) {
            System.out.print(res.label + ",");
            if (res.random != null) {
                System.out.print(res.random.label + ",");
            }
            res = res.next;
        }
    }

    /**
     * Use a hashmap to store map between original node and copy node 递归 最好的 第一解法
     * Get copy node from map
     */
    public RandomListNode CopyListWithRandom(RandomListNode head) {
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        return helper(head, map);
    }

    public RandomListNode helper(RandomListNode node, Map<RandomListNode, RandomListNode> map) {
        if (node == null)
            return null;
        if (map.containsKey(node))
            return map.get(node); // return copy
        RandomListNode res = new RandomListNode(node.label);
        map.put(node, res); // build map
        res.next = helper(node.next, map); // build next
        res.random = helper(node.random, map); // build copy
        return res;
    }

    static RandomListNode buildList() {
        RandomListNode node0 = new RandomListNode(1);
        RandomListNode node1 = new RandomListNode(2);
        RandomListNode node2 = new RandomListNode(6);
        node0.next = node1;
        node0.random = node2;
        node1.next = node2;
        return node0;
    }

    static class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

}
