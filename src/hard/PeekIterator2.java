package hard;

import java.util.Iterator;

/**
 * Created by GAOSHANSHAN835 on 2016/4/8.
 */
public  class PeekIterator2 implements Iterator<Integer> {

        private Integer next;
        private Iterator<Integer> iter;

        public PeekIterator2(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iter = iterator;
            if (iterator.hasNext()) {
                next = iterator.next();
            }
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return next;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            int res = next;
            next = iter.hasNext() ? iter.next() : null;
            return res;
        }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
        public boolean hasNext() {
            return next != null;
        }

}
