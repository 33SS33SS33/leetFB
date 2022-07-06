package TopInterview;

import java.util.*;

public class InsertDeleteGetRandomO1 {
    /**
     * Implement the RandomizedSet class:
     * RandomizedSet() Initializes the RandomizedSet object.
     * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
     * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
     * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
     * You must implement the functions of the class such that each function works in average O(1) time complexity.
     * Input
     * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
     * [[], [1], [2], [2], [], [1], [2], []]
     * Output
     * [null, true, false, true, 2, true, false, 2]
     */
    Map<Integer, Integer> dict;
    List<Integer> list;
    Random rand = new Random();

    /**
     * Initialize your data structure here.
     */
    public InsertDeleteGetRandomO1() {
        dict = new HashMap();
        list = new ArrayList();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (dict.containsKey(val)) return false;

        dict.put(val, list.size());
        list.add(list.size(), val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!dict.containsKey(val)) return false;

        // move the last element to the place idx of the element to delete
        int lastElement = list.get(list.size() - 1);
        int idx = dict.get(val);
        list.set(idx, lastElement);
        dict.put(lastElement, idx);
        // delete the last element
        list.remove(list.size() - 1);
        dict.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
