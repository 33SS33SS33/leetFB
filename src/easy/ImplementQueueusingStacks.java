package easy;

import java.util.Stack;

/**
 * Created by GAOSHANSHAN835 on 2016/3/3.
 */
public class ImplementQueueusingStacks {

    Stack<Integer> input  = new Stack();
    Stack<Integer> output = new Stack();

    public void push(int x) {
        input.push(x);
    }

    public void pop() {
        peek();
        output.pop();
    }

    public int peek() {
        if (output.empty())
            while (!input.empty())
                output.push(input.pop());
        return output.peek();
    }

    public boolean empty() {
        return input.empty() && output.empty();
    }
}
