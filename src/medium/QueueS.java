package medium;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by GAOSHANSHAN835 on 2016/3/3.
 * --creek
 */
public class QueueS<E> {
    public static void main(String[] args) {
        QueueS<Integer> q = new QueueS<Integer>(Integer.class, 5);
        q.push(1);
        q.push(2);
        q.push(3);
        q.push(4);
        q.push(5);
        q.pop();
        q.push(6);
        System.out.println(q);
    }

    E[] arr;
    int head = -1;
    int tail = -1;
    int size;

    public QueueS(Class<E> c, int size) {
        E[] newInstance = (E[]) Array.newInstance(c, size);
        this.arr = newInstance;
        this.size = 0;
    }

    boolean push(E e) {
        if (size == arr.length)
            return false;
        head = (head + 1) % arr.length;
        arr[head] = e;
        size++;
        if (tail == -1) {
            tail = head;
        }
        return true;
    }

    boolean pop() {
        if (size == 0) {
            return false;
        }
        E result = arr[tail];
        arr[tail] = null;
        size--;
        tail = (tail + 1) % arr.length;
        if (size == 0) {
            head = -1;
            tail = -1;
        }
        return true;
    }

    E peek() {
        if (size == 0)
            return null;
        return arr[tail];
    }

    public int size() {
        return this.size;
    }

    public String toString() {
        return Arrays.toString(this.arr);
    }

}
