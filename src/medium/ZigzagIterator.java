package medium;

import java.util.Iterator;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
//和之前有道题类似  使用队列就行 每次弹出来 再插入进去 需要总结一下各个数据结构的用法 重要
public class ZigzagIterator {

    private Iterator<Integer> i, j, tmp;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        i = v2.iterator();
        j = v1.iterator();
    }

    public int nexta() {
        if (j.hasNext()) {
            tmp = j;
            j = i;
            i = tmp;
        }
        return i.next();
    }

    public boolean hasNexta() {
        return i.hasNext() || j.hasNext();
    }

    Iterator<Integer>[] ivs;
    int p = 0;

 /*   public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        ivs = new Iterator[] { v1.iterator(), v2.iterator() };
    }*/

    public int next() {
        for (; ; ) {
            Iterator<Integer> i = ivs[p++ % ivs.length];
            if (i.hasNext()) {
                return i.next();
            }
        }
    }

    public boolean hasNext() {
        for (Iterator i : ivs) {
            if (i.hasNext()) {
                return true;
            }
        }
        return false;
    }

}
