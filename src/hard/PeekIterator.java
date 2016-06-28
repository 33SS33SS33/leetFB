package hard;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 * Google:
 * Implement peek() for java iterator()
 * Example [1,2,3,4,5]
 * peek() = 1, peek() = 1, next() = 1, peek() = 2, next() = 2, peek() = 3
 * hasNext() = true,
 * Tags: Data Structures
 */

//初始化的时候 直接取到下一个元素值就行  然后完全基于下一个元素来返回
class PeekIterator<T> implements Iterator<T> {
    private final Iterator<T> iterator;
    /**
     * Store next item
     */
    private       T           nextItem;

    public PeekIterator(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    @Override public boolean hasNext() {
        if (nextItem != null)
            return true; // check next item
        if (iterator.hasNext())
            nextItem = iterator.next(); // set next item
        return nextItem != null;
    }

    @Override public T next() {
        if (!hasNext())
            throw new NullPointerException();
        T temp = nextItem;
        nextItem = null; // reset next
        return temp;
    }

    public T peek() {
        if (!hasNext())
            throw new NullPointerException();
        return nextItem;
    }

    @Override public void remove() {
        throw new UnsupportedOperationException();
    }
}
