package top50Google;

import java.util.TreeMap;
//https://leetcode.com/problems/snapshot-array/discuss/350562/JavaPython-Binary-Search
public class SnapshotArray {
    /**
     * Implement a SnapshotArray that supports the following interface:
     * <p>
     * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
     * void set(index, val) sets the element at the given index to be equal to val.
     * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
     * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
     */
    TreeMap<Integer, Integer>[] A;
    int snap_id = 0;

    public SnapshotArray(int length) {
        A = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            A[i] = new TreeMap<Integer, Integer>();
            A[i].put(0, 0);
        }
    }

    public void set(int index, int val) {
        A[index].put(snap_id, val);
    }

    public int snap() {
        return snap_id++;
    }

    public int get(int index, int snap_id) {
        return A[index].floorEntry(snap_id).getValue();
    }
}
