package easy;

import java.util.Stack;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */

/**
 * 在push的时候用一个临时栈来把顺序倒一下就好 stack存的是queue的顺序 所以剩下的操作都是o1
 */
public class ImplementQueueusingStacks {
    // should use Deque<Integer> stack in modern java
    Stack<Integer> stack = new Stack<Integer>();

    // Push element x to the back of queue.
    public void push(int x) {
        Stack<Integer> rev = new Stack<Integer>();
        while (!stack.empty()) {
            rev.push(stack.pop());
        }
        rev.push(x);
        while (!rev.empty()) {
            stack.push(rev.pop());
        }
    }

    // Removes the element from in front of queue.
    public void pop() {
        stack.pop();
    }

    // Get the front element.
    public int peek() {
        return stack.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack.empty();
    }
}

