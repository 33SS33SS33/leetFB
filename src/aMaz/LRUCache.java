package aMaz;

import java.util.Hashtable;

/**
 * HARD
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and set.
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1.
 * (get means use, so we need to update)
 * set(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently
 * used item before inserting a new item.
 * Tags: Data Structure
 * Use 2 data structures to implement an LRU Cache
 * 1. A Queue which is implemented using a doubly linekd list. The max size of
 * the queue will be equal to cache size. Put most recently used at the end
 * 2. A Hash with Node's value as key and the Node as value
 * 3. A dummy head for Doubly LinkedList
 * 这道题使用ordereddict来做 比较省事 重要
 * 也可以使用dict加deque来做
 * 这里用的少的定义就是插入的比较靠前 所以如果用了一个本身在队列里的 就可以把他踢出来 然后再插入禁区
 */
class LRUCache {
    //https://leetcode.com/problems/lru-cache/discuss/45911/Java-Hashtable-%2B-Double-linked-list-(with-a-touch-of-pseudo-nodes)

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode post;
    }

    /**
     * Always add the new node right after head;
     */
    private void addNode(DLinkedNode node) {
        node.pre = head;
        node.post = head.post;
        head.post.pre = node;
        head.post = node;
    }

    /**
     * Remove an existing node from the linked list.
     */
    private void removeNode(DLinkedNode node) {
        DLinkedNode pre = node.pre;
        DLinkedNode post = node.post;
        pre.post = post;
        post.pre = pre;
    }

    /**
     * Move certain node in between to the head.
     */
    private void moveToHead(DLinkedNode node) {
        this.removeNode(node);
        this.addNode(node);
    }

    // pop the current tail.
    private DLinkedNode popTail() {
        DLinkedNode res = tail.pre;
        this.removeNode(res);
        return res;
    }

    private Hashtable<Integer, DLinkedNode> cache = new Hashtable<>();
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        head.pre = null;

        tail = new DLinkedNode();
        tail.post = null;
        head.post = tail;
        tail.pre = head;
    }

    public int get(int key) {

        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1; // should raise exception here.
        }
        // move the accessed node to the head;
        this.moveToHead(node);
        return node.value;
    }

    public void set(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            this.cache.put(key, newNode);
            this.addNode(newNode);

            ++count;

            if (count > capacity) {
                // pop the tail
                DLinkedNode tail = this.popTail();
                this.cache.remove(tail.key);
                --count;
            }
        } else {
            // update the value.
            node.value = value;
            this.moveToHead(node);
        }
    }
}
