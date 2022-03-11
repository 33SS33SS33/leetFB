package aMaz;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shanshan on 17/2/17. HARD
 * Design a data structure that supports all following operations in average O(1) time.
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 * // Returns false as 2 does not wordSearchb in the set.
 * randomSet.remove(2);
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 */
public class InsertDeleteGetRandomO1 {

    public class InsertDeleteGetRandom {
        ArrayList<Integer> nums;
        HashMap<Integer, Integer> locs;
        java.util.Random rand = new java.util.Random();

        public InsertDeleteGetRandom() {
            nums = new ArrayList<Integer>();
            locs = new HashMap<Integer, Integer>();
        }

        public boolean insert(int val) {
            boolean contain = locs.containsKey(val);
            if (contain)
                return false;
            locs.put(val, nums.size());
            nums.add(val);
            return true;
        }

        public boolean remove(int val) {
            boolean contain = locs.containsKey(val);
            if (!contain)
                return false;
            int loc = locs.get(val);
            if (loc < nums.size() - 1) { // not the last one than swap the last one with this val
                int lastone = nums.get(nums.size() - 1);
                nums.set(loc, lastone);
                locs.put(lastone, loc);
            }
            locs.remove(val);
            nums.remove(nums.size() - 1);
            return true;
        }

        public int getRandom() {
            return nums.get(rand.nextInt(nums.size()));
        }
    }

}
