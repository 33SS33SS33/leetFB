package aMaz;

import java.util.Iterator;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Implement an iterator to flattenBinaryTreeToLinkedListb a 2d vector.
 * Example:
 * Input: 2d vector =
 * [
 * [1,2],
 * [3],
 * [4,5,6]
 * ]
 * Output: [1,2,3,4,5,6]
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,2,3,4,5,6]
 * Follow up:
 * As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 * 先把2d转成一个一维list即可
 */
public class Flatten2DVector implements Iterator<Integer> {

    private Iterator<List<Integer>> row_itr; //row iterator
    private Iterator<Integer> col_itr;       //col iterator

    public Flatten2DVector(List<List<Integer>> vec2d) {
        row_itr = vec2d.iterator();
        //if row_itr doesn't have next, it means vec2d is empty
        //in this case, col_itr won't get initialized
        if (row_itr.hasNext())
            col_itr = row_itr.next().iterator();
    }

    @Override
    public Integer next() {
        //we won't call next() unless we are sure that hasNext() is true, meaning col_itr.hasNext() is true
        return col_itr.next();
    }

    @Override
    public boolean hasNext() {
        //check whether vec2d is empty
        if (col_itr == null) return false;

        //important!!! we do update here in hasNext()
        //update col_itr and row_itr until col_itr.hasNext() or row_itr.hasNext() is false
        while (!col_itr.hasNext() && row_itr.hasNext())
            col_itr = row_itr.next().iterator();

        return col_itr.hasNext();
    }
    /**
     * I first hold the 2D List inside a Iterator of List this allows me to operate on the subsequent list once needed.
     * I then remove the first list from the 2D List and store as row which is evaluated when next() & hasNext() are called.
     * I then want to ensure row iterator is pointing to a none empty list so i call the getNextRow() method.
     * next() and hashNext() are now very simple. next() returns the next element of the current list then ensures row isn't empty.
     * hasNext() checks row isn't null and has a next value.
     */
/*    Iterator<List<Integer>> itrs;
    Iterator<Integer> row;

    public Flatten2DVector(List<List<Integer>> vec2d) {
        if (vec2d == null || vec2d.size() == 0) return;
        itrs = vec2d.iterator();
        row = itrs.next().iterator();
        getNextRow();
    }

    private void getNextRow() {
        while (!row.hasNext() && itrs.hasNext()) row = itrs.next().iterator();
    }

    public int next() {
        int next = row.next();
        getNextRow();
        return next;
    }

    public boolean hasNext() {
        return row != null && row.hasNext();
    }*/


/*    private Iterator<List<Integer>> i;
    private Iterator<Integer> j;

    public Flatten2DVector(List<List<Integer>> vec2d) {
        i = vec2d.iterator();
    }

    public int next() {
        hasNext();
        return j.next();
    }

    public boolean hasNext() {
        while ((j == null || !j.hasNext()) && i.hasNext())
            j = i.next().iterator();
        return j != null && j.hasNext();
    }*/

}
